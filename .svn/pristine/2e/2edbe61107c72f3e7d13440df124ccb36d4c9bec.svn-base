package com.edgedo.pay.wxpay.util;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.common.util.IocUtil;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.constant.WxCommonReidsKeyConstant;
import com.edgedo.pay.wxpay.util.aes.WXBizMsgCrypt;
import com.edgedo.pay.wxpay.util.constant.WeixinCommonConstant;
import com.edgedo.pay.wxpay.util.vo.WxConfig;
import com.edgedo.sys.entity.SysWxConfig;
import com.edgedo.sys.entity.SysWxPayConfig;
import com.edgedo.sys.entity.SysWxUser;
import com.edgedo.sys.service.SysWxConfigService;
import com.edgedo.sys.service.SysWxPayConfigService;
import com.edgedo.sys.service.SysWxUserService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WxCommonUtil {
	//查询数据库
	private static  SysWxConfigService wxConfigService;
	//查询缓存
	private static RedisUtil redisUtil;
	/**
	 * 日志文件
	 */
	protected final static Logger log = LoggerFactory.getLogger(WxCommonUtil.class);

	/**
	 * 返回密文
	 * 
	 * @param strXml
	 * @return
	 */
	public static String getEncrypt(String strXml) {
		String result = "";
		try {
			if (strXml.length() <= 0 || strXml == null) {
				return null;
			}
			// 将字符串转化为XML文档对象
			Document document = DocumentHelper.parseText(strXml);
			// 获得文档的根节点
			Element root = document.getRootElement();
			// 密文节点
			Element encryptElement = root.element("Encrypt");
			if (encryptElement != null) {
				result = encryptElement.getText();
			}

		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("xml 格式异常: " + strXml);
			log.error("xml 格式异常: {}", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * URL编码(utf-8)
	 * 
	 * @param source
	 * @return String
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * 方法名称:transMapToString 
	 * 传入参数:map 
	 * 返回值:String 形如 username'chenziwen^password'1234 
	*/  
	public static String transMapToString(Map map){  
	  Map.Entry entry;
	  StringBuffer sb = new StringBuffer();
	  for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
	  {
	    entry = (Map.Entry)iterator.next();
	      sb.append(entry.getKey().toString()).append( "'" ).append(null==entry.getValue()?"":  
	      entry.getValue().toString()).append (iterator.hasNext() ? "^" : "");  
	  }  
	  return sb.toString();  
	}  
	
	/**取得微信配置信息
	 * @param originalId
	 * @return
	 */
	public static SysWxConfig getSysWxConfig(String originalId){
		if(redisUtil==null){
			redisUtil = IocUtil.getBean(RedisUtil.class);
		}

		SysWxConfig wxConfig = (SysWxConfig)redisUtil.hget(WxCommonReidsKeyConstant.WX_CONFIG_MAP_KEY,originalId);
		try {
			if(wxConfig==null){
				if(wxConfigService==null){
					wxConfigService = (SysWxConfigService) IocUtil.getBean(SysWxConfigService.class);
				}
				wxConfig = wxConfigService.loadById(originalId);
			}
			StringEncryptor enc = new SysStringEncryptor();
			String appId = wxConfig.getAppId();
			String appSecret = wxConfig.getAppSecret();
			String token = wxConfig.getToken();
			String encodingAesKey = wxConfig.getEncodingAesKey();
			String ticket = wxConfig.getTicket();
			String accessToken = wxConfig.getAccessToken();
			if(appId!=null && appId.length()>0){
				wxConfig.setAppId(enc.decrypt(appId));
			}
			if(appSecret!=null && appSecret.length()>0){
				wxConfig.setAppSecret(enc.decrypt(appSecret));
			}
			if(token!=null && token.length()>0){
				wxConfig.setToken(enc.decrypt(token));
			}
			if(encodingAesKey!=null && encodingAesKey.length()>0){
				wxConfig.setEncodingAesKey(enc.decrypt(encodingAesKey));
			}
			if(ticket!=null && ticket.length()>0){
				wxConfig.setTicket(enc.decrypt(ticket));
			}
			if(accessToken!=null && accessToken.length()>0){
				wxConfig.setAccessToken(enc.decrypt(accessToken));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("从ocs取微信公众号配置信息失败: {}", e.getMessage());
		}

		return wxConfig;
	}


	public static SysWxPayConfig getSysWxPayConfig(String orginalId) {
		StringEncryptor enc = new SysStringEncryptor();
		SysWxPayConfigService wxConfigService = (SysWxPayConfigService) IocUtil.getBean(SysWxPayConfigService.class);
		SysWxPayConfig config = wxConfigService.loadById(orginalId);
		config.setMchid(enc.decrypt(config.getMchid()));
		config.setPayKey(enc.decrypt(config.getPayKey()));
		return config;
	}

	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 *
	 * @param accessToken
	 *            网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * @param openId
	 *            用户的唯一标识
	 * @return SNSUserInfo
	 */
	public static JSONObject getSNSUserInfo(String accessToken, String openId) {
		String wxUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		String requestUrl = wxUserInfoUrl.replace("ACCESS_TOKEN",
				accessToken).replace("OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = httpRequestForJson(requestUrl, "GET", null);
		return jsonObject;
	}



	/**
	 * 根据公众好的原始ID取得access token
	 * @param originalId
	 * @return
	 */
	public static String getAccessTokenByOriginalId(String originalId) {
		SysWxConfig wxMpset = getSysWxConfig(originalId);
		return wxMpset.getAccessToken();
	}

	/**
	 * 取得ticket
	 * 
	 * @param originalId
	 * @return
	 */
	public static String getTicketByOriginalId(String originalId) {
		SysWxConfig wxConfig = getSysWxConfig(originalId);
		return wxConfig.getTicket();
	}

	/**
	 * 读取文件返回内容
	 * 
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static String readJsonFile(String folder, String fileName) {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		String sRootDir = context.getServletContext().getRealPath(folder);
		String filePath = sRootDir + "/" + fileName;
		File file = new File(filePath);
		String result = null;
		if (file.exists()) {
			StringBuilder string = new StringBuilder();
			FileInputStream fis = null;
			BufferedReader reader = null;
			try {
				fis = new FileInputStream(file);
				reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					string.append(line);
				}
				fis.close();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("读取access token 异常！: {}", e.getMessage());
				//System.out.println("读取access token 异常！原因：" + e.getMessage());
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("读取access token 异常！: {}", e1.getMessage());
						//System.out.println("读取access token 异常！原因：" + e1.getMessage());
					}
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						log.error("读取access token 异常！: {}", e2.getMessage());
						//System.out.println("读取access token 异常！原因：" + e2.getMessage());
					}
				}
			}
			result = string.toString();
		}

		return result;
	}

	/**
	 * aes加密
	 * 
	 * @return
	 */
	public static String encryptMsg(String originalId, String replyMsg, String timestamp, String nonce)
			throws Exception {
		//MpSetFactory mpSetFactory = MpSetFactory.getInstance();
		//PtWxConfig PtWxConfig = mpSetFactory.creator(originalId);
		SysWxConfig PtWxConfig = getSysWxConfig(originalId);
		
		String myToken = PtWxConfig.getToken();
		String encodingAesKey = PtWxConfig.getEncodingAesKey();
		String appId = PtWxConfig.getAppId();
		WXBizMsgCrypt pc = new WXBizMsgCrypt(myToken, encodingAesKey, appId);
		String miwen = pc.encryptMsg(replyMsg, timestamp, nonce);
		//System.out.println("加密后: " + miwen);
		return miwen;
	}

	/**
	 * aes解密
	 * 
	 * @return
	 */
	public static String decryptMsg(String originalId, String timestamp, String nonce, String msgSignature,
			String encrypt) throws Exception {
		//MpSetFactory mpSetFactory = MpSetFactory.getInstance();
		//PtWxConfig PtWxConfig = mpSetFactory.creator(originalId);
		SysWxConfig PtWxConfig = getSysWxConfig(originalId);
		
		String myToken = PtWxConfig.getToken();
		String encodingAesKey = PtWxConfig.getEncodingAesKey();
		String appId = PtWxConfig.getAppId();

		WXBizMsgCrypt pc = new WXBizMsgCrypt(myToken, encodingAesKey, appId);
		String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
		String fromXML = String.format(format, encrypt);

		// 第三方收到公众号平台发送的消息
		String mingwen = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
		//System.out.println("解密后明文: " + mingwen);
		return mingwen;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequestForJson(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		String responseStr = httpRequest(requestUrl, requestMethod, outputStr);
		if (responseStr != null && responseStr.length() > 0) {
			jsonObject = JSONObject.parseObject(responseStr);
		}
		return jsonObject;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();

		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		OutputStream outputStream = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpsURLConnection httpUrlConn = null;
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			httpUrlConn = getProxyHttpsUrlConn(requestUrl);

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
		} catch (Exception e) {
			e.printStackTrace();
			log.error("http 请求异常！: {}", e.getMessage());
		} finally {
			// 关闭流
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("http 请求异常！: {}", e.getMessage());
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("http 请求异常！: {}", e.getMessage());
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("http 请求异常！: {}", e.getMessage());
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("http 请求异常！: {}", e.getMessage());
				}
			}
			if (httpUrlConn != null) {
				httpUrlConn.disconnect();
			}
		}
		return buffer.toString();
	}

	/**http请求返回json object
	 * @param requestUrl
	 * @return
	 */
	public static JSONObject hRequest(String requestUrl){
		JSONObject jsonObject = null;
		InputStream l_urlStream = null;
		BufferedReader in = null;
		try{
			URLConnection connection = getProxyUrlConn(requestUrl);
			/**
			 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
			 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
			 */
			connection.setDoOutput(true);
			String res="";
			l_urlStream = connection.getInputStream();
			in = new BufferedReader(new InputStreamReader(l_urlStream, "UTF-8"));
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			l_urlStream.close();
			in.close();
			String responseStr = sb.toString();
			
			if (responseStr != null && responseStr.length() > 0) {
				jsonObject = (JSONObject)JSONObject.toJSON(responseStr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(l_urlStream != null){
					l_urlStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
	/**
	 * 发起http请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return
	 */
	public static String httpRequestByHttp(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();

		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		OutputStream outputStream = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpURLConnection httpUrlConn = null;
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
//			SSLSocketFactory ssf = sslContext.getSocketFactory();
			httpUrlConn = (HttpURLConnection)getProxyUrlConn(requestUrl);
			
//			httpUrlConn.setSSLSocketFactory(ssf);

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
		} catch (Exception e) {
			e.printStackTrace();
			log.error("http 请求异常！: {}", e.getMessage());
		} finally {
			// 关闭流
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("http 请求异常！: {}", e.getMessage());
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("http 请求异常！: {}", e.getMessage());
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("http 请求异常！: {}", e.getMessage());
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("http 请求异常！: {}", e.getMessage());
				}
			}
			if (httpUrlConn != null) {
				httpUrlConn.disconnect();
			}
		}
		return buffer.toString();
	}
	/**
	 * 发送请求
	 * 
	 * @param urlString
	 * @return
	 * @throws Exception
	 */
	public static String oauth2Request(String urlString) throws Exception {
		//URL url = new URL(urlString);
		HttpsURLConnection connect = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			connect = getProxyHttpsUrlConn(urlString);
			
			connect.setDoInput(true);
			connect.setRequestMethod("POST");
			connect.connect();
			inputStream = connect.getInputStream();

			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (inputStreamReader != null) {

				inputStreamReader.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (connect != null) {
				connect.disconnect();
			}
		}

		return buffer.toString();
	}

	/**
	 * 取得随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String createNonceStr(int length) {
		StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(rand.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * 取得客户端的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
    	if(ip != null && ip.length() > 0 && !"unKnown".equalsIgnoreCase(ip)){
    		//多次反向代理后会有多个ip值，第一个ip才是真实ip
    		int index = ip.indexOf(",");
    		if(index != -1){
    			return ip.substring(0,index);
    		}else{
    			return ip;
    		}
    	}
    	ip = request.getHeader("X-Real-IP");
    	if(ip != null && ip.length() > 0 && !"unKnown".equalsIgnoreCase(ip)){
    		return ip;
    	}
    	return request.getRemoteAddr();
	}

	/**
	 * 验证ip是否正确
	 * @param ipAddress
	 * @return
	 */
	public static boolean isboolIP(String ipAddress) {
		String ip = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

	/**
	 * 根据用户id取得用户的openId
	 * @return
	 */
/*	public static String getWeixinIdByUserId(String userId){
		String openId = "";
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		UserDAOImpl userDao = (UserDAOImpl)ctx.getBean("userDao");
		User user = userDao.selectByPrimaryKey(userId);
		if(user !=null && user.getWeixin() != null){
			openId = user.getWeixin();
		}
		return openId;
	}*/
	/**
	 * 取得代理的https链接
	 */
	public static HttpsURLConnection getProxyHttpsUrlConn(String requestUrl)throws Exception{
		URL url = new URL(requestUrl);
		HttpsURLConnection httpsUrlConn = null;
		if(WeixinCommonConstant.PROXY_IP != null && WeixinCommonConstant.PROXY_IP.length() > 0 &&
				WeixinCommonConstant.PROXY_PORT > -1){
			//使用代理
			Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(WeixinCommonConstant.PROXY_IP, WeixinCommonConstant.PROXY_PORT));
			httpsUrlConn = (HttpsURLConnection) url.openConnection();
		}else{
			httpsUrlConn = (HttpsURLConnection) url.openConnection();
		}
		return httpsUrlConn;
	}
	
	/**取得代理url链接
	 * @param urlString
	 * @return
	 * @throws Exception
	 */
	public static URLConnection getProxyUrlConn(String urlString) throws Exception{
		URL url = new URL(urlString);
		URLConnection connection = null;
		if(WeixinCommonConstant.PROXY_IP != null && WeixinCommonConstant.PROXY_IP.length() > 0 &&
				WeixinCommonConstant.PROXY_PORT > -1){
			Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(WeixinCommonConstant.PROXY_IP, WeixinCommonConstant.PROXY_PORT));
			connection = url.openConnection(proxy);
		}else{
			connection = url.openConnection();
		}
		return connection;
	}


	/**
	 * 获得微信jsconfig
	 * @param orginalId
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static WxConfig getWxJsConfig(String orginalId, String url) throws Exception {
		WxConfig wxConfig = new WxConfig();
		String appId = "";
		long timestamp = 0l;
		String nonceStr = "";
		String urlDecode = "";
		SysWxConfig ptWxConfig = WxCommonUtil.getSysWxConfig(orginalId);
		appId = ptWxConfig.getAppId();
		timestamp = System.currentTimeMillis();
		nonceStr = WxCommonUtil.createNonceStr(16);

		urlDecode = java.net.URLDecoder.decode(url, "UTF-8");

		String jsapiTicket = ptWxConfig.getTicket();
		String str = "jsapi_ticket="+jsapiTicket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+urlDecode;

		String signature = SignUtil.getSHAString(str);

		wxConfig.setAppId(appId);
		wxConfig.setTimestamp(timestamp);
		wxConfig.setNonceStr(nonceStr);
		wxConfig.setSignature(signature);
		return wxConfig;
	}


	public static SysWxUser getWxUserByUserId(String userId){
		SysWxUserService wxUserService = (SysWxUserService) IocUtil.getBean(SysWxUserService.class);
		return wxUserService.loadById(userId);
	}




}
