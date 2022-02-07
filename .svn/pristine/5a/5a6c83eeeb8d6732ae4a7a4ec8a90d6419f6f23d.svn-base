package com.edgedo.pay.wxpay.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**支付公用工具类
 * @author song
 *
 */
public class PayUtil extends WxCommonUtil {


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

	/**characterEncoding
	 * @param characterEncoding 编码格式
	 * @param parameters 请求参数
	 * @param payKey 支付秘钥
	 * @return
	 */
	public static String createSign(String characterEncoding,
			SortedMap<String,Object> parameters,String payKey){
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

	/**验证签名
	 * @param characterEncoding 编码格式
	 * @param parameters 请求参数
	 * @param payKey 支付秘钥
	 * @return
	 */
	public static boolean verifySign(String characterEncoding,SortedMap<String,String> parameters,String payKey,String sign){
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
		String mySign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign.toUpperCase().equals(mySign);
	}
}
