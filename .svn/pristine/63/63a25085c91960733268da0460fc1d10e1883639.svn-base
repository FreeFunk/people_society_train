package com.edgedo.pay.wxpay.comunicate.msg.util;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import com.edgedo.pay.wxpay.util.constant.WeixinHttpUrlConstant;

/**
 * 发送客服消息工具类
 * 
 * @author tiangai
 * @since 2014-06-30 Am 10:49
 * @version 1.0
 */
public class SendCustomMessageUtil extends WxCommonUtil {
	/**
	 * 发送客服消息方法
	 * 
	 * @param originalId
	 *            公众账号原始ID
	 * @param jsonMsg
	 *            json格式客服消息
	 * @return true|false
	 */
	public static boolean sendCustomMessage(String originalId, String jsonMsg) {
		boolean result = false;
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.SEND_CUSTOM_URL
				.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = (Integer) jsonObject.get("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				log.error("发送客服消息失败 errCode:{} errormsg:{} ", errorCode,
						errorMsg);
			}
		}
		return result;
	}

	/**
	 * 本地测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String originalId = "";
		String openId = "";
		// 组装文本客服消息
		String jsonTextMsg = MakeCustomMessageUtil.makeTextCustomMessage(
				openId, "测试发送客服消息");
		// 发送客服消息(测试本接口的前提是需要你先跟公众账号会话且在48小时内)
		sendCustomMessage(originalId, jsonTextMsg);
	}
}
