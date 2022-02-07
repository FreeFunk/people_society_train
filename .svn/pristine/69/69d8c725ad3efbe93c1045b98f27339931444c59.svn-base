package com.edgedo.wx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.BusinessException;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.common.util.ObjectUtil;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.constant.WxCommonReidsKeyConstant;
import com.edgedo.pay.wxpay.comunicate.msg.util.SendTemplateMessageUtil;
import com.edgedo.pay.wxpay.sdk.WXPayUtil;
import com.edgedo.pay.wxpay.util.SignUtil;
import com.edgedo.pay.wxpay.util.WeixinUtil;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import com.edgedo.pay.wxpay.util.WxPayUtil;
import com.edgedo.pay.wxpay.util.vo.WxConfig;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.queryvo.SysWxUserView;
import com.edgedo.sys.service.*;
import jodd.util.PropertiesUtil;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/wx")
@Controller
public class WxController extends BaseController {

    @Autowired
    SysWxUserService sysWxUserService;
    @Autowired
    SysWxConfigService sysWxConfigService;
    @Autowired
    SysCityAppInfoService sysCityAppInfoService;
    @Autowired
    SysProviceService sysProviceService;
    @Autowired
    SysCityService sysCityService;
    @Autowired
    SysDwzStaticService sysDwzStaticService;
    @Autowired
    SysWxPayRecordService sysWxPayRecordService;
    @Autowired
    SysWxTemplateMsgconfigService sysWxTemplateMsgconfigService;
    @Autowired
    RedisUtil redisUtil;

    @Value("${wx.config.rootOrginalId}")
    String orginalId;
    @Value("${wx.config.rootUrl}")
    String rootUrl;
    @Value("${wx.session.timeout}")
    Long wx_session_timeout;
    private static final String GET = "GET";

    /**
     * 微信登录方法
     *
     * @throws Exception
     */
    @RequestMapping("/login")
    public void login(String d, String aid, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
        //获取微信公众号的原始id
        SysWxConfig sysWxConfig = WxPayUtil.getSysWxConfig(orginalId);
        String appId = sysWxConfig.getAppId();
        String scope = "snsapi_base";
        String stateStr = "state";
        String redirectUrl = rootUrl + "wx/snsapiBase.jsn?d="+d+"%26aid="+aid;
        redirectUrl = redirectUrl.replaceAll("/", "%2F");
        redirectUrl = redirectUrl.replaceAll(":", "%3A");
        String url = sysWxUserService.getSnsapiUserinfoUrl(appId, redirectUrl, scope, stateStr);
        System.out.println("================url：" + url);
        redirectToUrl(url, httpResponse);
        return;
    }


    /**
     * 用户微信登录
     **/
    @RequestMapping("/snsapiBase")
    public ModelAndView snsapiBase(
            String d,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        System.out.println("================code：" + code);
        if (code == null) {
            //TODO:找不到说明微信没给 那么有问题跳转到公共页面
            return new ModelAndView("redirect:"+rootUrl+"/index.html?d="+d);
        } else {
            //请求微信服务器获得openId  1.公众号的配置信息
            SysWxConfig sysWxConfig = WxPayUtil.getSysWxConfig(orginalId);
            String wxAppId = sysWxConfig.getAppId();
            String appScre = sysWxConfig.getAppSecret();
            String url = WxPayUtil.genOpenIdUrl(wxAppId, appScre, code);
            //发送http请求
            String responseText = WxPayUtil.httpRequest(url, "GET", "");
            //将字符串编译成对象
            WxAccessTokenInfo token = JSONObject.parseObject(responseText, WxAccessTokenInfo.class);
            String openid = token.getOpenid();
            System.out.println("openid==="+openid);
            if (openid != null) {
                //根据openid找用户
                SysWxUserView sysWxUserView = sysWxUserService.selectWxUserByOpenId(openid);
                if (sysWxUserView == null) {
                    sysWxUserService.updateRegWxUser(token);
                }
                //生成一个随机token 作为键 openid作为值存入redis 超时时间是半个小时
                String guid = Guid.guid();
                String wx_access_token = WxCommonReidsKeyConstant.WX_OPEN_ID_PREFIX +guid;
                redisUtil.set(wx_access_token,openid,wx_session_timeout);
                //将token写到cookies
                Cookie cookie = new Cookie("access-token",wx_access_token);
                cookie.setPath("/");
                response.addCookie(cookie);
                if(d!=null && !d.equals("null")){
                    if(d.indexOf("dwr=")<0){//如果没有登录的2次跳转那么替换井号转译
                        d = d.replaceAll("\\*\\*_\\*\\*","#");
                    }
                    if(d.indexOf(".jsn")>=0){
                        if(d.indexOf("?")>=0) {
                            return new ModelAndView("redirect:" + d + "&token=" + wx_access_token);
                        }else{
                            return new ModelAndView("redirect:" + d + "?token=" + wx_access_token);
                        }
                    }else{
                        return new ModelAndView("redirect:" + d);
                    }

                }else{
                    return new ModelAndView("redirect:"+rootUrl+"/index.html?d="+d);
                }

            }else{
                return new ModelAndView("redirect:"+rootUrl+"/index.html?d="+d);
            }

        }
    }

    public static void main(String[] args) {
        String d = "https://study.qhd12328.com/wxLogin.jsn?token=sfsdf&sch=1231322**_**/userinfo";
        if(d.indexOf("dwr=")<0){//如果没有登录的2次跳转那么替换井号转译
            d = d.replaceAll("\\*\\*_\\*\\*","#");
        }
        System.out.println(d);
    }

    /**
     * @Author WangZhen
     * @Description 根据token获取登录的OpenId
     * @Date 2020/3/21 8:52
     **/
    @RequestMapping("/getOId")
    public ModelAndView testReadCookie(String token){
        Object obj = redisUtil.get(token,wx_session_timeout);
        ModelAndView mav = new ModelAndView();
        buildResponse(mav,obj);
        return mav;
    }




    //初始化微信菜单
    @RequestMapping("/createWxMenu")
    public ModelAndView createWxMenu() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        OutputStream os = null;
        InputStream is = null;
        try {
            SysWxConfig sysWxConfig = WxPayUtil.getSysWxConfig(orginalId);
            String myToken = sysWxConfig.getAccessToken();
            // 用户权限验证
            String menu = sysWxConfig.getWxMenueJson();
            HttpURLConnection http = (HttpURLConnection) WxCommonUtil.getProxyUrlConn(WeixinUtil.CREATE_MENU_URL + myToken);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            os = http.getOutputStream();
            os.write(menu.getBytes("UTF-8"));// 传入参数
            os.flush();
            os.close();
            is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject jo = JSONObject.parseObject(message);
            //{"errcode":0,"errmsg":"ok"}
            String errcode = jo.getString("errcode");
            String errmsg = jo.getString("errmsg");
            Map<String,Object> resMap = new HashMap<String,Object>();
            if(!"0".equals(errcode)){
                buildErrorResponse(modelAndView,errmsg,errcode);
                return modelAndView;
            }
            buildResponse(modelAndView);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            buildErrorResponse(modelAndView,e.getMessage());
            return modelAndView;
        }finally{
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {}
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
    }

    //消息自动回复
    //接收智慧运管传来的微信消息准备发送
    @RequestMapping("/chat")
    public void chat(HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
        Map<String,String[]> paramTem = request.getParameterMap();
        Map<String,String > paramMap = new HashMap<String,String>();
        for(Map.Entry<String,String[]> entry : paramTem.entrySet()){
            String key = entry.getKey();
            String[] value = entry.getValue();
            if(value.length>0){
                paramMap.put(key,value[0]);
            }
        }
        WxChatCallBackParam param = ObjectUtil.mapToBean(paramMap,WxChatCallBackParam.class);
        System.out.println("======微信调用接口：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        SysWxConfig sysWxConfig = WxPayUtil.getSysWxConfig(orginalId);
        String token = sysWxConfig.getToken();
        String result = null;
        try {
            result = getReplyForRecievedMsg(orginalId,param, token,request);
        } catch (Exception e) {
            e.printStackTrace();
            result = param.getEchostr();
        }
        outAjax1(result);
    }

    /**
     * 处理消息
     * @param originalId
     * @param param
     * @param token
     * @param request
     * @return
     * @throws Exception
     */
    private String getReplyForRecievedMsg(String originalId, WxChatCallBackParam param , String token, HttpServletRequest request)throws Exception{
        String signature = param.getSignature();
        String timestamp = param.getTimestamp();
        String nonce = param.getNonce();
        String encrypt_type = param.getEncrypt_type();
        String msg_signature = param.getMsg_signature();
        String result = "";
        if (GET.equals(request.getMethod())) {
			/*String[] str = { myToken, timestamp, nonce };
	        Arrays.sort(str); // 字典序排序
	        String bigStr = str[0] + str[1] + str[2];
	        // SHA1加密
	        String digest = DigestUtils.shaHex(bigStr).toLowerCase();

	        // 确认请求来至微信
	        if (digest.equals(signature)) {
	        	result = echostr;
	        }*/

            // 通过检验signature对请求进行校验,若校验成功则原样返回echostr,表示接入成功,否则接入失败
            if (SignUtil.checkSignature(token, signature, timestamp, nonce)) {
                result = param.getEchostr();
            }
        } else {
            //System.out.println("=====================POST====================");
            // 通过检验signature对请求进行校验,若校验成功则原样返回echostr,表示接入成功,否则接入失败
            if (!SignUtil.checkSignature(token,signature, timestamp, nonce)) {
                return "";
            }

            /** 读取接收到的xml消息 */
            StringBuffer sb = new StringBuffer();
            String s = "";
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try{
                is = request.getInputStream();
                isr = new InputStreamReader(is, "UTF-8");
                br = new BufferedReader(isr);
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }finally{
                if(is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if(isr != null){
                    try {
                        isr.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if(br != null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            String xml = sb.toString(); //接收到微信端发送过来的xml数据
            //正常的微信处理流程
            try {
                //System.out.println("encrypt_type:"+encrypt_type+";timestamp:"+timestamp+";nonce:"+nonce+";msg_signature:"+msg_signature);
                //System.out.println("xml=============="+xml);
                result = sysWxConfigService.processWechatMag(originalId,encrypt_type,timestamp,nonce,msg_signature,xml);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 调用支付中心的支付功能
     * @param request  prepayId
     * @return
     */
    @RequestMapping("/payByWxWeb")
    public ModelAndView payByWxWeb(
            HttpServletRequest request
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wxpay");
        SortedMap<String,Object> resultParams = new TreeMap<String,Object>();
        String prepayId = request.getParameter("id");
        SysWxPayRecord sysWxPayRecord = sysWxPayRecordService.loadById(prepayId);

        String oraId = sysWxPayRecord.getOraId();
        SysWxPayConfig payConfig = WxPayUtil.getSysWxPayConfig(oraId);

        String appId = sysWxPayRecord.getAppId();
        String payKey = payConfig.getPayKey();
        //主键 用来判断记录ID
        String guid = sysWxPayRecord.getOutTradeNo();
        String payNonceStr = WxCommonUtil.createNonceStr(16);
        resultParams.put("appId", appId);
        resultParams.put("timeStamp", System.currentTimeMillis());
        resultParams.put("nonceStr", payNonceStr);
        resultParams.put("package", "prepay_id="+ sysWxPayRecord.getPrepayId());
        resultParams.put("signType", "MD5");
        String paySign =  WxPayUtil.createSign("UTF-8", resultParams,payKey);
        resultParams.put("packageValue", "prepay_id=" + sysWxPayRecord.getPrepayId());//这里用packageValue是预防package是关键字在js获取值出错
        resultParams.put("result_code", "SUCCESS");
        resultParams.put("paySign" , paySign);//paySign的生成规则和Sign的生成规则一致
        resultParams.put("sendUrl" , payConfig.getSuccessUrl());//付款成功后跳转的页面
        resultParams.put("attach", guid);//附加数据
        resultParams.put("success_url" , sysWxPayRecord.getSuccessUrl());
        resultParams.put("fail_url" , sysWxPayRecord.getFailUrl());
        resultParams.put("prepay_id" , sysWxPayRecord.getPrepayId());
        modelAndView.addObject("prepayResult",resultParams);
        String regUrl = rootUrl+"/sys/wx/payByWxWeb";
        WxConfig wxConfig = null;
        try {
            wxConfig = WxCommonUtil.getWxJsConfig(oraId,regUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("wxConfig", wxConfig);

        return modelAndView;

    }

    /**
     * 接收微信模板消息放入缓存里等待发送
     */
    @RequestMapping("/receiveWxTxMsg")
    @ResponseBody
    public String receiveWxTxMsg(@RequestBody SysWxTxMsgVo sysWxTxMsgVo){
        try{
            //将微信消息放进缓存等待发送
            SysWxTemplateMsgconfig wxTemplateMsgconfig = sysWxTemplateMsgconfigService.loadById(sysWxTxMsgVo.getModelId());
            String modelParams = SendTemplateMessageUtil.genModelParams(sysWxTxMsgVo.getModelParam(),wxTemplateMsgconfig);
            sysWxTxMsgVo.setModelParams(modelParams);
            redisUtil.leftPush(WxCommonReidsKeyConstant.WX_TX_MSG_KEY,sysWxTxMsgVo);
        }catch (Exception e){
            e.printStackTrace();
            return "后台异常!";
        }
        return "";
    }



   /**
    *@Author:ZhaoSiDa
    *@Description: 调用微信公众号预支付接口
    *@DateTime: 2020/6/10 17:22
    */
    @RequestMapping("/prepayInterface")
    public ModelAndView prepayInterface(@RequestBody  Map<String, Object> map){
        ModelAndView modelAndView = new ModelAndView();
        SortedMap<String,Object> resultParams = new TreeMap<String,Object>();
        SysWxPayRecord record = JSON.parseObject(JSON.toJSONString(map.get("payrecord")), SysWxPayRecord.class);
        String userAgent= (String) map.get("userAgent");
        record.setCityAppId("130300");
        //获取小程序的原始id
        SysWxConfig ptWxConfig = WxPayUtil.getSysWxConfig(orginalId);
        SysWxPayConfig payConfig = WxPayUtil.getSysWxPayConfig(orginalId);
        if(ptWxConfig == null){
            resultParams.put("result_code", "FAIL");
            resultParams.put("err_msg","未找到微信支付配置！");
            return buildResponse(modelAndView,resultParams);
        }
        //子项目的回调路径
        record.setAppSuccUrl(record.getNotifyUrl());
        //修改微信支付的回调地址
        String notifyUrl = rootUrl+ "wx/pay.jsn" ;
        record.setNotifyUrl(notifyUrl);

        String appId = ptWxConfig.getAppId();
        String mchid = payConfig.getMchid();
        String payKey = payConfig.getPayKey();
        //主键 用来判断记录ID
        String guid = record.getOutTradeNo();
        SortedMap<String,Object> parameters = new TreeMap<String,Object>();
        String payNonceStr = WxCommonUtil.createNonceStr(16);
        parameters.put("appid",appId);
        parameters.put("mch_id",mchid);
        parameters.put("nonce_str", payNonceStr);
        parameters.put("body", record.getBody());
        parameters.put("out_trade_no",guid);
        parameters.put("total_fee", ""+record.getTotalFee().longValue());
        parameters.put("spbill_create_ip",record.getSpbillCreateIp());
        parameters.put("notify_url",notifyUrl );
        parameters.put("trade_type", record.getTradeType());
        parameters.put("openid", record.getOpenid());
        //附加数据
        parameters.put("attach", guid);
        String sign = WxPayUtil.createSign("UTF-8", parameters,payKey);
        parameters.put("sign", sign);

        String requestXML = WxPayUtil.getRequestXml(parameters);
        System.out.println("requestXML:"+requestXML);
        //预支付操作返回结果
        try {
            Map<String,String> responseMap = WxPayUtil.unifiedOrder(requestXML);
            if("SUCCESS".equals(responseMap.get("return_code")) && "SUCCESS".equals(responseMap.get("result_code")) ){
                //预支付调用成功

                resultParams.put("appId", appId);
                resultParams.put("timeStamp", System.currentTimeMillis());
                resultParams.put("nonceStr", payNonceStr);
                resultParams.put("package", "prepay_id="+responseMap.get("prepay_id"));
                resultParams.put("signType", "MD5");
                String paySign =  WxPayUtil.createSign("UTF-8", resultParams,payKey);
                resultParams.put("packageValue", "prepay_id="+responseMap.get("prepay_id"));//这里用packageValue是预防package是关键字在js获取值出错
                resultParams.put("result_code", "SUCCESS");
                resultParams.put("paySign", paySign);//paySign的生成规则和Sign的生成规则一致
                resultParams.put("sendUrl", payConfig.getSuccessUrl());//付款成功后跳转的页面
                resultParams.put("attach", guid);//附加数据
                char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
                resultParams.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
                resultParams.put("success_url",record.getSuccessUrl());
                resultParams.put("fail_url",record.getFailUrl());
                resultParams.put("prepay_id",responseMap.get("prepay_id"));

                record.setAppId(appId);
                //插入预支付成功的记录
                record.setMchId(mchid);
                record.setNonceStr(payNonceStr);
                record.setOpenid(record.getOpenid());
                record.setPrepayId(responseMap.get("prepay_id"));
                record.setSign(paySign);
                record.setPrepayTime(new Date());
                record.setPrepayBody(WxCommonUtil.transMapToString(resultParams));
                record.setPayType("WX");
                record.setOraId(orginalId);
                sysWxPayRecordService.insert(record);
            }else{
                System.out.println("return_msg===================="+responseMap.get("return_msg"));
                resultParams.put("result_code", "FAIL");
                String errMsg = (responseMap.get("return_msg")==null || "OK".equals(responseMap.get("return_msg"))?"": responseMap.get("return_msg"))+ (responseMap.get("err_code_des")==null?"": responseMap.get("err_code_des"));
                resultParams.put("err_msg",errMsg);
            }
            return buildResponse(modelAndView,resultParams);
        } catch (Exception e) {
            return buildErrorResponse(modelAndView,"下单失败,网络异常!");
        }

    }

   /**
    *@Author:ZhaoSiDa
    *@Description: 调用支付中心的支付功能
    *@DateTime: 2020/6/10 17:28
    */
    @RequestMapping("/payWxWeb")
    public ModelAndView payWxWeb(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SortedMap<String,Object> resultParams = new TreeMap<String,Object>();
        String prepayId = request.getParameter("id");
        //System.out.println("prepayId===="+prepayId);
        SysWxPayRecord sysWxPayRecord = sysWxPayRecordService.loadById(prepayId);

        String oraId = sysWxPayRecord.getOraId();
        SysWxPayConfig payConfig = WxPayUtil.getSysWxPayConfig(oraId);

        String appId = sysWxPayRecord.getAppId();
        String payKey = payConfig.getPayKey();
        //主键 用来判断记录ID
        String guid = sysWxPayRecord.getOutTradeNo();
        String payNonceStr = WxCommonUtil.createNonceStr(16);
        resultParams.put("appId", appId);
        resultParams.put("timeStamp", System.currentTimeMillis());
        resultParams.put("nonceStr", payNonceStr);
        resultParams.put("package", "prepay_id="+ sysWxPayRecord.getPrepayId());
        resultParams.put("signType", "MD5");
        String paySign =  WxPayUtil.createSign("UTF-8", resultParams,payKey);
        resultParams.put("packageValue", "prepay_id=" + sysWxPayRecord.getPrepayId());//这里用packageValue是预防package是关键字在js获取值出错
        resultParams.put("result_code", "SUCCESS");
        resultParams.put("paySign" , paySign);//paySign的生成规则和Sign的生成规则一致
        resultParams.put("sendUrl" , payConfig.getSuccessUrl());//付款成功后跳转的页面
        resultParams.put("attach", guid);//附加数据
        resultParams.put("success_url" , sysWxPayRecord.getSuccessUrl());
        resultParams.put("fail_url" , sysWxPayRecord.getFailUrl());
        resultParams.put("prepay_id" , sysWxPayRecord.getPrepayId());
        modelAndView.addObject("prepayResult",resultParams);
        String regUrl = rootUrl+"wx/payWxWeb";
        //System.out.println("regUrl==="+regUrl);
        WxConfig wxConfig = null;
        try {
            wxConfig = WxCommonUtil.getWxJsConfig(oraId,regUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("wxpay");
        modelAndView.addObject("wxConfig", wxConfig);
        return modelAndView;
    }


    /**
     * 支付回调
     */
    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("response");
        InputStream inStream = null;
        ByteArrayOutputStream outSteam = null;

        try {

            inStream = request.getInputStream();
            outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }

            String result = new String(outSteam.toByteArray(), "utf-8");//获取微信调用我们notify_url的返回信息
//            SortedMap<String, String> map = XMLUtil.doXMLParseToSortedMap(result);
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            SysWxPayRecord payRecord = sysWxConfigService.updateWxPayCallBack(map);
            //给应用发送回调消息--如果应用返回接收到了那么返回微信成功
            String cityAppId = payRecord.getCityAppId();
            String appSuccUrl = payRecord.getAppSuccUrl();
            String payRecordJson = JSON.toJSONString(payRecord);
            com.alibaba.fastjson.JSONObject payRecordObj = JSON.parseObject(payRecordJson);
            payRecordObj.remove("appId");
            payRecordObj.remove("mchId");
            payRecordObj.remove("sign");
            payRecordObj.remove("notifyUrl");
            payRecordObj.remove("appSuccUrl");
            payRecordObj.remove("cityAppId");
            payRecordObj.remove("oraId");
            SortedMap<String,String> param = new TreeMap<String,String>();
            param.put("appId",cityAppId);
            param.put("payRecord",payRecordJson);
            param.put("timestmp",new Date().getTime()+"");
            //CheckParamsAndSignUtil.signParam(param);
            Logger.getLogger(WxController.class).error("====="+appSuccUrl);
            String response = HttpRequestUtil.sendPostRequest(appSuccUrl,param);
            Logger.getLogger(WxController.class).error("====="+response);
            if(response!=null && response.equals("OK")){
                httpResponse.getWriter().write(WxPayUtil.setXML("SUCCESS", "OK"));
                return;
            }else{
                httpResponse.getWriter().write(WxPayUtil.setXML("FAIL", "未完成接收"));
                return;
            }
        }catch (BusinessException eb){
            eb.printStackTrace();
            httpResponse.getWriter().write(WxPayUtil.setXML("FAIL", eb.getMessage()));
            return;
        }catch(Exception e){
            e.printStackTrace();
            httpResponse.getWriter().write(WxPayUtil.setXML("FAIL", "服务器异常"));
            return;
        }finally {
            Logger.getLogger(WxController.class).error("=====3");
            if(outSteam!=null){
                try {
                    outSteam.close();
                } catch (IOException e) {}
            }
            if(inStream!=null){
                try {
                    inStream.close();
                } catch (IOException e) {}
            }
        }

    }
}
