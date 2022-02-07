package com.edgedo.pay.wxpay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.edgedo.pay.wxpay.util.vo.AccessTokenVo;
import com.edgedo.pay.wxpay.util.vo.JsapiTicket;


public class WeixinUtil {
	/**
	 * 取得accessToken的url
	 */
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * 获得jsapi_ticket的url
	 */
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	/**
	 * 创建菜单的url
	 */
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";


	/**取得access token
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static AccessTokenVo getAccessToken(String appid, String appsecret) throws Exception{
		AccessTokenVo accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		System.out.println("================================access_token_url:"+requestUrl+"===========================================");
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			accessToken = new AccessTokenVo();
			accessToken.setToken(jsonObject.getString("access_token"));
			accessToken.setExpiresIn((Integer)jsonObject.get("expires_in"));
		}
		return accessToken;
	}
	
	/**取得jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据
	 * @return
	 * @throws Exception
	 */
	public static JsapiTicket getJsapiTicket(String accessToken)throws Exception{
		JsapiTicket jsapiTicket = null;
		String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
		System.out.println("================================jsapi_ticket_url:"+requestUrl+"===========================================");
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			jsapiTicket = new JsapiTicket();
			jsapiTicket.setTicket(jsonObject.getString("ticket"));
			jsapiTicket.setExpiresIn((Integer) jsonObject.get("expires_in"));
		}
		return jsapiTicket;
	}
	
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
	
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		OutputStream outputStream = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpsURLConnection httpUrlConn = null;
		try{
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			inputStream.close();
			inputStreamReader.close();
			bufferedReader.close();
			httpUrlConn.disconnect();
			jsonObject = (JSONObject) JSONObject.parseObject(buffer.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭流
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(inputStreamReader != null){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(httpUrlConn != null){
				httpUrlConn.disconnect();
			}
		}
		return jsonObject;
	}
}
