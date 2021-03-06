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
     * ??????????????????
     *
     * @throws Exception
     */
    @RequestMapping("/login")
    public void login(String d, String aid, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
        //??????????????????????????????id
        SysWxConfig sysWxConfig = WxPayUtil.getSysWxConfig(orginalId);
        String appId = sysWxConfig.getAppId();
        String scope = "snsapi_base";
        String stateStr = "state";
        String redirectUrl = rootUrl + "wx/snsapiBase.jsn?d="+d+"%26aid="+aid;
        redirectUrl = redirectUrl.replaceAll("/", "%2F");
        redirectUrl = redirectUrl.replaceAll(":", "%3A");
        String url = sysWxUserService.getSnsapiUserinfoUrl(appId, redirectUrl, scope, stateStr);
        System.out.println("================url???" + url);
        redirectToUrl(url, httpResponse);
        return;
    }


    /**
     * ??????????????????
     **/
    @RequestMapping("/snsapiBase")
    public ModelAndView snsapiBase(
            String d,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        System.out.println("================code???" + code);
        if (code == null) {
            //TODO:??????????????????????????? ????????????????????????????????????
            return new ModelAndView("redirect:"+rootUrl+"/index.html?d="+d);
        } else {
            //???????????????????????????openId  1.????????????????????????
            SysWxConfig sysWxConfig = WxPayUtil.getSysWxConfig(orginalId);
            String wxAppId = sysWxConfig.getAppId();
            String appScre = sysWxConfig.getAppSecret();
            String url = WxPayUtil.genOpenIdUrl(wxAppId, appScre, code);
            //??????http??????
            String responseText = WxPayUtil.httpRequest(url, "GET", "");
            //???????????????????????????
            WxAccessTokenInfo token = JSONObject.parseObject(responseText, WxAccessTokenInfo.class);
            String openid = token.getOpenid();
            System.out.println("openid==="+openid);
            if (openid != null) {
                //??????openid?????????
                SysWxUserView sysWxUserView = sysWxUserService.selectWxUserByOpenId(openid);
                if (sysWxUserView == null) {
                    sysWxUserService.updateRegWxUser(token);
                }
                //??????????????????token ????????? openid???????????????redis ???????????????????????????
                String guid = Guid.guid();
                String wx_access_token = WxCommonReidsKeyConstant.WX_OPEN_ID_PREFIX +guid;
                redisUtil.set(wx_access_token,openid,wx_session_timeout);
                //???token??????cookies
                Cookie cookie = new Cookie("access-token",wx_access_token);
                cookie.setPath("/");
                response.addCookie(cookie);
                if(d!=null && !d.equals("null")){
                    if(d.indexOf("dwr=")<0){//?????????????????????2?????????????????????????????????
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
        if(d.indexOf("dwr=")<0){//?????????????????????2?????????????????????????????????
            d = d.replaceAll("\\*\\*_\\*\\*","#");
        }
        System.out.println(d);
    }

    /**
     * @Author WangZhen
     * @Description ??????token???????????????OpenId
     * @Date 2020/3/21 8:52
     **/
    @RequestMapping("/getOId")
    public ModelAndView testReadCookie(String token){
        Object obj = redisUtil.get(token,wx_session_timeout);
        ModelAndView mav = new ModelAndView();
        buildResponse(mav,obj);
        return mav;
    }




    //?????????????????????
    @RequestMapping("/createWxMenu")
    public ModelAndView createWxMenu() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        OutputStream os = null;
        InputStream is = null;
        try {
            SysWxConfig sysWxConfig = WxPayUtil.getSysWxConfig(orginalId);
            String myToken = sysWxConfig.getAccessToken();
            // ??????????????????
            String menu = sysWxConfig.getWxMenueJson();
            HttpURLConnection http = (HttpURLConnection) WxCommonUtil.getProxyUrlConn(WeixinUtil.CREATE_MENU_URL + myToken);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// ????????????30???
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // ????????????30???
            http.connect();
            os = http.getOutputStream();
            os.write(menu.getBytes("UTF-8"));// ????????????
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

    //??????????????????
    //???????????????????????????????????????????????????
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
        System.out.println("======?????????????????????" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
     * ????????????
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
	        Arrays.sort(str); // ???????????????
	        String bigStr = str[0] + str[1] + str[2];
	        // SHA1??????
	        String digest = DigestUtils.shaHex(bigStr).toLowerCase();

	        // ????????????????????????
	        if (digest.equals(signature)) {
	        	result = echostr;
	        }*/

            // ????????????signature?????????????????????,??????????????????????????????echostr,??????????????????,??????????????????
            if (SignUtil.checkSignature(token, signature, timestamp, nonce)) {
                result = param.getEchostr();
            }
        } else {
            //System.out.println("=====================POST====================");
            // ????????????signature?????????????????????,??????????????????????????????echostr,??????????????????,??????????????????
            if (!SignUtil.checkSignature(token,signature, timestamp, nonce)) {
                return "";
            }

            /** ??????????????????xml?????? */
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
            String xml = sb.toString(); //?????????????????????????????????xml??????
            //???????????????????????????
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
     * ?????????????????????????????????
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
        //?????? ??????????????????ID
        String guid = sysWxPayRecord.getOutTradeNo();
        String payNonceStr = WxCommonUtil.createNonceStr(16);
        resultParams.put("appId", appId);
        resultParams.put("timeStamp", System.currentTimeMillis());
        resultParams.put("nonceStr", payNonceStr);
        resultParams.put("package", "prepay_id="+ sysWxPayRecord.getPrepayId());
        resultParams.put("signType", "MD5");
        String paySign =  WxPayUtil.createSign("UTF-8", resultParams,payKey);
        resultParams.put("packageValue", "prepay_id=" + sysWxPayRecord.getPrepayId());//?????????packageValue?????????package???????????????js???????????????
        resultParams.put("result_code", "SUCCESS");
        resultParams.put("paySign" , paySign);//paySign??????????????????Sign?????????????????????
        resultParams.put("sendUrl" , payConfig.getSuccessUrl());//??????????????????????????????
        resultParams.put("attach", guid);//????????????
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
     * ???????????????????????????????????????????????????
     */
    @RequestMapping("/receiveWxTxMsg")
    @ResponseBody
    public String receiveWxTxMsg(@RequestBody SysWxTxMsgVo sysWxTxMsgVo){
        try{
            //???????????????????????????????????????
            SysWxTemplateMsgconfig wxTemplateMsgconfig = sysWxTemplateMsgconfigService.loadById(sysWxTxMsgVo.getModelId());
            String modelParams = SendTemplateMessageUtil.genModelParams(sysWxTxMsgVo.getModelParam(),wxTemplateMsgconfig);
            sysWxTxMsgVo.setModelParams(modelParams);
            redisUtil.leftPush(WxCommonReidsKeyConstant.WX_TX_MSG_KEY,sysWxTxMsgVo);
        }catch (Exception e){
            e.printStackTrace();
            return "????????????!";
        }
        return "";
    }



   /**
    *@Author:ZhaoSiDa
    *@Description: ????????????????????????????????????
    *@DateTime: 2020/6/10 17:22
    */
    @RequestMapping("/prepayInterface")
    public ModelAndView prepayInterface(@RequestBody  Map<String, Object> map){
        ModelAndView modelAndView = new ModelAndView();
        SortedMap<String,Object> resultParams = new TreeMap<String,Object>();
        SysWxPayRecord record = JSON.parseObject(JSON.toJSONString(map.get("payrecord")), SysWxPayRecord.class);
        String userAgent= (String) map.get("userAgent");
        record.setCityAppId("130300");
        //????????????????????????id
        SysWxConfig ptWxConfig = WxPayUtil.getSysWxConfig(orginalId);
        SysWxPayConfig payConfig = WxPayUtil.getSysWxPayConfig(orginalId);
        if(ptWxConfig == null){
            resultParams.put("result_code", "FAIL");
            resultParams.put("err_msg","??????????????????????????????");
            return buildResponse(modelAndView,resultParams);
        }
        //????????????????????????
        record.setAppSuccUrl(record.getNotifyUrl());
        //?????????????????????????????????
        String notifyUrl = rootUrl+ "wx/pay.jsn" ;
        record.setNotifyUrl(notifyUrl);

        String appId = ptWxConfig.getAppId();
        String mchid = payConfig.getMchid();
        String payKey = payConfig.getPayKey();
        //?????? ??????????????????ID
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
        //????????????
        parameters.put("attach", guid);
        String sign = WxPayUtil.createSign("UTF-8", parameters,payKey);
        parameters.put("sign", sign);

        String requestXML = WxPayUtil.getRequestXml(parameters);
        System.out.println("requestXML:"+requestXML);
        //???????????????????????????
        try {
            Map<String,String> responseMap = WxPayUtil.unifiedOrder(requestXML);
            if("SUCCESS".equals(responseMap.get("return_code")) && "SUCCESS".equals(responseMap.get("result_code")) ){
                //?????????????????????

                resultParams.put("appId", appId);
                resultParams.put("timeStamp", System.currentTimeMillis());
                resultParams.put("nonceStr", payNonceStr);
                resultParams.put("package", "prepay_id="+responseMap.get("prepay_id"));
                resultParams.put("signType", "MD5");
                String paySign =  WxPayUtil.createSign("UTF-8", resultParams,payKey);
                resultParams.put("packageValue", "prepay_id="+responseMap.get("prepay_id"));//?????????packageValue?????????package???????????????js???????????????
                resultParams.put("result_code", "SUCCESS");
                resultParams.put("paySign", paySign);//paySign??????????????????Sign?????????????????????
                resultParams.put("sendUrl", payConfig.getSuccessUrl());//??????????????????????????????
                resultParams.put("attach", guid);//????????????
                char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
                resultParams.put("agent", new String(new char[]{agent}));//?????????????????????????????????????????????????????????????????????????????????5.0???????????????
                resultParams.put("success_url",record.getSuccessUrl());
                resultParams.put("fail_url",record.getFailUrl());
                resultParams.put("prepay_id",responseMap.get("prepay_id"));

                record.setAppId(appId);
                //??????????????????????????????
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
            return buildErrorResponse(modelAndView,"????????????,????????????!");
        }

    }

   /**
    *@Author:ZhaoSiDa
    *@Description: ?????????????????????????????????
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
        //?????? ??????????????????ID
        String guid = sysWxPayRecord.getOutTradeNo();
        String payNonceStr = WxCommonUtil.createNonceStr(16);
        resultParams.put("appId", appId);
        resultParams.put("timeStamp", System.currentTimeMillis());
        resultParams.put("nonceStr", payNonceStr);
        resultParams.put("package", "prepay_id="+ sysWxPayRecord.getPrepayId());
        resultParams.put("signType", "MD5");
        String paySign =  WxPayUtil.createSign("UTF-8", resultParams,payKey);
        resultParams.put("packageValue", "prepay_id=" + sysWxPayRecord.getPrepayId());//?????????packageValue?????????package???????????????js???????????????
        resultParams.put("result_code", "SUCCESS");
        resultParams.put("paySign" , paySign);//paySign??????????????????Sign?????????????????????
        resultParams.put("sendUrl" , payConfig.getSuccessUrl());//??????????????????????????????
        resultParams.put("attach", guid);//????????????
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
     * ????????????
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

            String result = new String(outSteam.toByteArray(), "utf-8");//????????????????????????notify_url???????????????
//            SortedMap<String, String> map = XMLUtil.doXMLParseToSortedMap(result);
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            SysWxPayRecord payRecord = sysWxConfigService.updateWxPayCallBack(map);
            //???????????????????????????--??????????????????????????????????????????????????????
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
                httpResponse.getWriter().write(WxPayUtil.setXML("FAIL", "???????????????"));
                return;
            }
        }catch (BusinessException eb){
            eb.printStackTrace();
            httpResponse.getWriter().write(WxPayUtil.setXML("FAIL", eb.getMessage()));
            return;
        }catch(Exception e){
            e.printStackTrace();
            httpResponse.getWriter().write(WxPayUtil.setXML("FAIL", "???????????????"));
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
