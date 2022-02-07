package com.edgedo.society.controller;

import com.alibaba.fastjson.JSON;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.util.MD5Util;
import com.edgedo.society.entity.SysWxPayRecord;
import com.edgedo.society.service.SysWxPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@RequestMapping("/pay")
@Controller
public class AppPayController extends BaseController {



    @Autowired
    SysWxPayRecordService sysWxPayRecordService;

    //微信支付结果回调的token
    @Value("${wxpay.token}")
    private String wxPayToken;

    /**
     * 微信服务的支付回调
     */
    @RequestMapping("/apppay")
    @Pass
    public void pay(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
        System.out.println("apppay我执行了");
        boolean flag = checkParamsAndSignForWxWap(request);
        System.out.println("flag=="+flag);
        Writer writer = httpResponse.getWriter();
        if(!flag){
            writer.write("SIGN FAIL");
            writer.flush();
            return;
        }
        String payRecordJson = request.getParameter("payRecord");
        System.out.println(payRecordJson);
        SysWxPayRecord payRec = JSON.parseObject(payRecordJson,SysWxPayRecord.class);
        //修改支付状态
        String errMsg = sysWxPayRecordService.updateWxPayCallBack(payRec);
        if( errMsg == null ){
            writer.write("OK");
        }else{
            writer.write("FAIL");
        }

    }

    //校验sign
    public boolean checkParamsAndSignForWxWap(HttpServletRequest request) {
        //签名
        Enumeration<String> en = request.getParameterNames();
        SortedMap<String, String> param = new TreeMap<String, String>();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            param.put(key, request.getParameter(key));
        }
        String sign = (String) param.get("sign");
        String signNew = createSign1("UTF-8", param, wxPayToken);
        if(signNew.equals(sign)){
            return true;
        }
        return false;
    }
    /**characterEncoding
     * @param characterEncoding 编码格式
     * @param parameters 请求参数
     * @param payKey 支付秘钥
     * @return
     */
    public static String createSign1(String characterEncoding,
                                     SortedMap<String,String> parameters,String payKey){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + payKey);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }


}
