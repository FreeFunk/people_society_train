package com.edgedo.pay.wxpay.comunicate.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**微信消息工具类
 * @author song
 *
 */
public class WeixinMsgTools {
	/** 
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果 
     * @param content 
     * @return 
     */  
    public static String getTulingResult(String content){  
        /** 此处为图灵api接口 */  
        String apiUrl = "http://www.tuling123.com/openapi/api?key=e0160043d46dab1255d6c088a3bea822&info=";  
        String param = "";  
        try {  
            param = apiUrl+URLEncoder.encode(content, "utf-8"); ;  
        } catch (UnsupportedEncodingException e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        } //将参数转为url编码  
        
        /** 发送httpget请求 */  
        HttpGet request = new HttpGet(param);
        String result = "";  
        try {  
        	//Cloc HttpClients.createDefault()
        	CloseableHttpClient httpclient = HttpClients.createDefault();
        	 
        	 //判断是否使用代理
            //区分https和http
            if(param != null && param.indexOf("https") == -1  ){
            	//调用https的代理
            	//取得代理
            	String host = System.getProperty("http.proxyHost");
            	String port = System.getProperty("http.proxyPort");
            	if(host != null && !"".equals(host.trim())){
            		//环境配置了代理
            		// 依次是代理地址，代理端口号，协议类型  
            		  HttpHost proxy = new HttpHost(host, Integer.parseInt(port), "http");
                    RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                    request.setConfig(config);
            	}
            	//环境没有配置代理
            }else{
            	//调用http的代理
            	//取得代理
            	String host = System.getProperty("https.proxyHost");
            	String port = System.getProperty("https.proxyPort");
            	if(host != null && !"".equals(host.trim())){
            		//环境配置了代理
            		// 依次是代理地址，代理端口号，协议类型  
                    HttpHost proxy = new HttpHost(host, Integer.parseInt(port), "https");
                    RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                    request.setConfig(config);
            	}
            }
            
            HttpResponse response = httpclient.execute(request);
            if(response.getStatusLine().getStatusCode()==200){  
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }  
        } catch (ClientProtocolException e) {
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        /** 请求失败处理 */  
        if(null==result){  
            return "对不起，你说的话真是太高深了……";  
        }
        JSONObject json = (JSONObject)JSONObject.toJSON(result);
        //以code=100000为例，参考图灵机器人api文档
        if(100000==(Integer) json.get("code")){
            result = json.getString("text");
        }
        return result;  
    }
    
//    public static void main(String[] args){
//    	System.setProperty("http.proxyHost","10.150.100.111");
//    	System.setProperty("http.proxyPort","80");
//    	System.out.println(getTulingResult("你好"));
//    }
}
