package com.edgedo.pay.wxpay.util;

import com.edgedo.pay.wxpay.util.PayUtil;
import com.edgedo.pay.wxpay.util.XMLUtil;
import com.edgedo.pay.wxpay.util.constant.WxPayConstant;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**支付工具类
 * @author song
 *
 */
public class WxPayUtil extends PayUtil {

	public static final String WX_ROOT_ORGINAL_ID = "WX_ROOT_ORGINAL_ID";

	/**https请求，返回字符串
	 * @param httpsUrl https请求url
	 * @param methodType 请求方式
	 * @param xmlParam xml参数
	 * @param mchid 微信商户号
	 * @param certPath 证书路径
	 * @return
	 * @throws Exception
	 */
	public static String httpSSLRequest(String httpsUrl,String methodType,String xmlParam,String mchid,String certPath) throws Exception{
		StringBuffer strBuff = new StringBuffer();
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(certPath));
        try {
            keyStore.load(instream, mchid.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, mchid.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
//        BufferedReader bufferedReader = null;
        CloseableHttpResponse response = null;
        try {
            if("get".equals(methodType.toLowerCase())){
            	//get请求
            	HttpGet httpget = new HttpGet(httpsUrl);
            	if(xmlParam != null && xmlParam.trim().length() > 0){
            		Map<String,String> paramMap = XMLUtil.doXMLParse(xmlParam);
            		Set es = paramMap.entrySet();
            		Iterator it = es.iterator();
            		while(it.hasNext()) {
            			Map.Entry entry = (Map.Entry)it.next();
            			String k = (String)entry.getKey();
            			Object v = entry.getValue();
            			if(null != v && !"".equals(v) 
            					&& !"sign".equals(k) && !"key".equals(k)) {
            				httpget.setHeader(k, (String)v);
            			}
            		}
            	}
                response = httpclient.execute(httpget);
            }else{
            	//pose请求
            	HttpPost httppost = new HttpPost(httpsUrl);
            	httppost.setEntity(new StringEntity(xmlParam, "UTF-8"));
            	response = httpclient.execute(httppost);
            }
            
            try {
                HttpEntity entity = response.getEntity();
                String result = "";
                if(entity != null){
					result = EntityUtils.toString(entity, "UTF-8");
				}

               /* System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                    	strBuff.append(text);
                    }
                }*/
                
                //关闭流
                EntityUtils.consume(entity);
				return result;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }        
//        return strBuff.toString();
    }
	
	/**
	 * 统一支付方法
	 * @param xmlParam 参数xml
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> unifiedOrder(String xmlParam)throws Exception{
		String result = httpRequest(WxPayConstant.UNIFIED_ORDER_URL,"POST", xmlParam);
		Map<String, String> responseMap = XMLUtil.doXMLParse(result);	
		return responseMap;
	}
		
	/**微信退款接口(POST)
	 * @param xmlParam 参数xml
	 * @param mchid 微信商户号
	 * @param certPath 证书路径
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> refund(String xmlParam,String mchid,String certPath)throws Exception{
		String result = httpSSLRequest(WxPayConstant.REFUND_URL, "POST", xmlParam, mchid, certPath);
		Map<String, String> responseMap = XMLUtil.doXMLParse(result);	
		return responseMap;
	}
	
	/**订单查询接口(POST)
	 * @param xmlParam
	 * @return
	 * 返回状态码通信标识 return_code SUCCESS/FAIL
		返回信息 return_msg
		公众账号ID appid
		商户号 mch_id
		随机字符串 nonce_str
		签名 sign
		业务结果 result_code SUCCESS/FAIL
		错误代码 err_code
		错误代码描述 err_code_des
		设备号 device_info
		用户标识 openid
		是否关注公众账号 is_subscribe
		交易类型 trade_type JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
		交易状态 trade_state
				SUCCESS
				SUCCESS—支付成功
				REFUND—转入退款
				NOTPAY—未支付
				CLOSED—已关闭
				REVOKED—已撤销
				USERPAYING--用户支付中
				PAYERROR--支付失败(其他原因，如银行返回失败)
		付款银行 bank_type
		总金额 total_fee
		货币种类 fee_type
		现金支付金额 cash_fee
		现金支付货币类型 cash_fee_type
		代金券或立减优惠金额 coupon_fee
		微信支付订单号 transaction_id
		商户订单号 out_trade_no
		商家数据包 attach
		支付完成时间 time_end yyyyMMddHHmmss
		交易状态描述 trade_state_desc
	 * @throws Exception
	 */
	public static Map<String, String> checkOrder(String xmlParam)throws Exception{
		String result = httpRequest(WxPayConstant.CHECK_ORDER_URL, "POST", xmlParam);
		Map<String, String> responseMap = XMLUtil.doXMLParse(result);	
		return responseMap;
	}
	
	/**关闭订单接口(POST)
	 * @param xmlParam
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> closeOrder(String xmlParam)throws Exception{
		String result = httpRequest(WxPayConstant.CLOSE_ORDER_URL,"POST",xmlParam);
		Map<String, String> responseMap = XMLUtil.doXMLParse(result);	
		return responseMap;
	}
	
	/**退款查询接口(POST)
	 * @param xmlParam
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> checkRefund(String xmlParam)throws Exception{
		String result = httpRequest(WxPayConstant.CHECK_REFUND_URL,"POST",xmlParam);
		Map<String, String> responseMap = XMLUtil.doXMLParse(result);	
		return responseMap;
	}
	
	/**对账单接口(POST)
	 * @param xmlParam
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> downloadBill(String xmlParam)throws Exception{
		String result = httpRequest(WxPayConstant.DOWNLOAD_BILL_URL,"POST",xmlParam);
		Map<String, String> responseMap = XMLUtil.doXMLParse(result);	
		return responseMap;
	}
	
	/**短链接转换接口(POST)
	 * @param xmlParam
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> transformShortUrl(String xmlParam)throws Exception{
		String result = httpRequest(WxPayConstant.SHORT_URL,"POST",xmlParam);
		Map<String, String> responseMap = XMLUtil.doXMLParse(result);	
		return responseMap;
	}
	
	/**接口调用上报接口(POST)
	 * @param xmlParam
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> report(String xmlParam)throws Exception{
		String result = httpRequest(WxPayConstant.REPORT_URL,"POST",xmlParam);
		Map<String, String> responseMap = XMLUtil.doXMLParse(result);	
		return responseMap;
	}
	
	/**
	 * 将请求参数转换为xml格式的string
	 * @param parameters  请求参数
	 * @return
	 */
	public static String getRequestXml(SortedMap<String,Object> parameters){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object vObj = entry.getValue();

			String v = "";
			if(vObj!=null){
				v = vObj.toString();
			}
			if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
				sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
			}else {
				sb.append("<"+k+">"+v+"</"+k+">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 返回给微信的参数
	 * @param return_code 返回编码
	 * @param return_msg  返回信息
	 * @return
	 */
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}
	public static void main(String[] args){
		String xml = "";
		//订单查询接口
//		checkOrder(xml);
//		//订单关闭接口
//		closeOrder(xml);
//		//退款接口
//		refund(xml,chid,c);
//		//退款查询
//		checkRefund(xml);
//		//订单下载
//		downloadBill(xml)
//		//短连接
//		transformShortUrl(xml);
//		//上报接口
//		report(xml);
	}

	/**
	 * 生成获得用户openId的url
	 * @param appId
	 * @param secret
	 * @param code
	 * @return
	 */
	public static String genOpenIdUrl(String appId,String secret,String code){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
				"appid=" + appId +
				"&secret=" + secret +
				"&code=" + code +
				"&grant_type=authorization_code";
		return url;
	}
}
