package com.edgedo.wx.config;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.common.constant.ThirdPartyReqKey;
import com.edgedo.common.shiro.ShiroFilter;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.common.util.IocUtil;
import com.edgedo.common.util.ObjectUtil;
import com.edgedo.common.util.RedisUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangZhen
 * @description
 * @date 2020/3/21
 */
public class WxLoginFilter extends ShiroFilter {
    //缓存中存放token的前缀
    private static final String third_party_token_prefix = "tpt_";
    //缓存工具类
    RedisUtil redisUtil;
    //微信公共服务的url
    String commonWxServiceUrl;
    //token在本地缓存中的超时时长 默认半小时
    int tokenTimeOutSec = 1800;

    /**
     * @Author WangZhen
     * @Description 只传入url
     * @Date 2020/5/20 11:34
     **/
    public WxLoginFilter(String url) {
        this.commonWxServiceUrl = url;
        //获得容器中的redisUtil如果拿到就给
        RedisUtil ru = IocUtil.getBean(RedisUtil.class);
        if(ru!=null){
            redisUtil = ru;
        }
    }

    /**
     * @Author WangZhen
     * @Description 传入url和token缓存的超时时长
     * @Date 2020/5/20 11:34
     **/
    public WxLoginFilter(String url,int tokenTimeOutSec) {
        this.commonWxServiceUrl = url;
        //获得容器中的redisUtil如果拿到就给
        RedisUtil ru = IocUtil.getBean(RedisUtil.class);
        if(ru!=null){
            redisUtil = ru;
        }
        this.tokenTimeOutSec = tokenTimeOutSec;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            return false;
        }
        return true;
    }



    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        String requestUri = httpServletRequest.getRequestURI();
        //如果请求的数据没有扩展名那么就返回index
        if(!(requestUri.endsWith(".jsn") || requestUri.endsWith(".do")|| requestUri.endsWith(".action"))){
            ((HttpServletResponse) response).sendRedirect("/login.html");
            return false;
        }
        String accessToken = getAccessToken(request);
        if (verificationPassAnnotation(request, response, httpServletRequest, accessToken)){
            return true;
        }
        if(ObjectUtil.isEmpty(accessToken)){
            responseError(request, response);
            return false;
        }
        boolean isWx = isWx(request);
        if(!isWx){//如果不是微信客户端不在继续请求微信相关
            return true;
        }
        String key = third_party_token_prefix + accessToken;
        //先从本地缓存中拿，如果本地缓存有那么就不在请求
        if(redisUtil!=null){

            Object obj =  redisUtil.get(key );

            if(obj!=null){//这里面存放openid
                //设置超时 1小时
                redisUtil.expire(key,tokenTimeOutSec);
                String openId = (String)obj;
                if(isWx){
                    //设置属性到requet对象中
                    setAttrOfThirdParty(accessToken,openId,"wechat",request);
                    super.preHandle(request, response);
                    return true;
                }
            }
        }
        if(isWx){
            //根据url地址从公共服务取出openid
            String wxUrl = commonWxServiceUrl;
            String getOpenIdUrl = wxUrl +"/wx/getOId.jsn?token=" + accessToken;
            String json = HttpRequestUtil.sendPostRequest(getOpenIdUrl,new HashMap<String,String>());
            JSONObject  jsonObj = JSONObject.parseObject(json);
            String openId = null;
            String succ = jsonObj.get("success")+"";
            if(succ.equals("true")) {
                Object data = jsonObj.get("data");
                openId = data == null ? null : (data + "");
            }
            if(openId!=null){
                redisUtil.set(key,openId,tokenTimeOutSec);
                //设置属性到requet对象中
                setAttrOfThirdParty(accessToken,openId,"wechat",request);
                super.preHandle(request, response);
                return true;
            }else{
                responseError(request, response);
                return false;
            }
        }

        responseError(request, response);
        return false;

    }

    /**
     * @Author WangZhen
     * @Description 设置三方登录的openid
     * @Date 2020/5/20 11:18
     **/
    private void setAttrOfThirdParty(
             String accessToken,
             String openId,
             String type,
             ServletRequest request){
        Map<String,Object> loginInfoMap = (Map<String,Object>)request.getAttribute(ThirdPartyReqKey.third_party_login_info_map+"");
        if(loginInfoMap==null)loginInfoMap=new HashMap<>();
        loginInfoMap.put(ThirdPartyReqKey.third_party_login_open_id + "",openId);
        //设置第3方类型是wechat微信类型
        loginInfoMap.put(ThirdPartyReqKey.third_party_login_type +"",type);
        loginInfoMap.put(ThirdPartyReqKey.login_token+"",accessToken);
        request.setAttribute(ThirdPartyReqKey.third_party_login_info_map + "",loginInfoMap);


    }


}
