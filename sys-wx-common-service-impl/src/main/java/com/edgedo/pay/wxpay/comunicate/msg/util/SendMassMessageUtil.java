package com.edgedo.pay.wxpay.comunicate.msg.util;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import com.edgedo.pay.wxpay.util.constant.WeixinHttpUrlConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 群发消息工具类
 * 
 * @author tiangai
 * @since 2014-07-01 Pm 19:30
 * @version 1.0
 */
public class SendMassMessageUtil extends WxCommonUtil {
	/**
	 * 上传图文消息素材
	 * 
	 * @param originalId
	 *            公众账号原始ID
	 * @param jsonMsg
	 *            json格式数据
	 * @return {<br/>
	 *         "type":"news", <br/>
	 *         "media_id":
	 *         "CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ"
	 *         ,<br/>
	 *         "created_at":1391857799<br/>
	 *         }<br/>
	 */
	public static JSONObject uploadNews(String originalId, String jsonMsg) {
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.UPLOAD_NEWS_URL
				.replace("ACCESS_TOKEN", accessToken);
		// 上传图文素材
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = (Integer)jsonObject.get("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 != errorCode) {
				log.error("上传图文消息素材失败 errCode:{} errormsg:{} ", errorCode,
						errorMsg);
			}
		}
		return jsonObject;
	}

	/**
	 * 上传视频素材
	 * 
	 * @param originalId
	 *            公众账号原始ID
	 * @param jsonMsg
	 *            json格式数据<br>
	 *            {<br>
	 *            "media_id":
	 *            "rF4UdIMfYK3efUfyoddYRMU50zMiRmmt_l0kszupYh_SzrcW5Gaheq05p_lHuOTQ"
	 *            ,<br>
	 *            "title": "TITLE",<br>
	 *            "description": "Description"<br>
	 *            }<br>
	 * @return {<br>
	 *         "type":"video", <br>
	 *         "media_id":
	 *         "CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ"
	 *         ,<br>
	 *         "created_at":1391857799<br/>
	 *         }<br>
	 */
	public static JSONObject uploadVideo(String originalId, String jsonMsg) {
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.UPLOAD_VIDEO_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 上传视频素材
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = (Integer)jsonObject.get("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 != errorCode) {
				log.error("上传视频素材失败 errCode:{} errormsg:{} ", errorCode,
						errorMsg);
			}
		}
		return jsonObject;
	}

	/**
	 * 群发消息方法(按照组)
	 * 
	 * @param originalId
	 *           公众账号原始ID
	 * @param jsonMsg
	 *            json格式客服消息
	 * @return true|false
	 */
	public static boolean sendMassMessageByGroup(String originalId,
			String jsonMsg) {
		boolean result = false;
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.SEND_MASS_MSG_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 群发消息
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = (Integer)jsonObject.get("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				log.error("群发消息失败 errCode:{} errormsg:{} ", errorCode, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 群发消息方法(按照OpenId)
	 * 
	 * @param originalId
	 *             公众账号原始ID
	 * @param jsonMsg
	 *            json格式数据
	 * @return true|false
	 */
	public static boolean sendMassMessageByOpenId(String originalId,
			String jsonMsg) {
		boolean result = false;
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.SEND_MASS_MSG_OPENID_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 群发消息
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = (Integer)jsonObject.get("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				log.error("群发消息失败 errCode:{} errormsg:{} ", errorCode, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 删除群发消息方法 <br>
	 * 请注意：<br>
	 * 只有已经发送成功的消息才能删除删除消息只是将消息的图文详情页失效，<br>
	 * 已经收到的用户，还是能在其本地看到消息卡片。<br>
	 * 另外，删除群发消息只能删除图文消息和视频消息，<br>
	 * 其他类型的消息一经发送，无法删除。 <br>
	 * 
	 * @param originalId
	 *            公众账号原始ID
	 * @param jsonMsg
	 *            json格式数据
	 * @return true|false
	 */
	public static boolean deleteMassMsg(String originalId, String jsonMsg) {
		log.info("消息内容：{}", jsonMsg);
		boolean result = false;
		String accessToken = getAccessTokenByOriginalId(originalId);
		String requestUrl = WeixinHttpUrlConstant.DELETE_MASS_MSG_URL.replace("ACCESS_TOKEN",
				accessToken);
		// 删除群发消息
		JSONObject jsonObject = httpRequestForJson(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = (Integer)jsonObject.get("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				log.error("删除群发消息失败 errCode:{} errormsg:{} ", errorCode,
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

		// 获取接口访问凭证
		String originalId = "";
		String TEST_USER = "";
		List<String> openid = new ArrayList<String>();
		openid.add(TEST_USER);
		String msg = MakeMassMessageUtil.makeTextGroupMessage("100", "测试群发消息");
		String msg1 = MakeMassMessageUtil.makeTextGroupMessage(openid, "测试群发消息xxxxx");
		sendMassMessageByOpenId(originalId, msg1);
		sendMassMessageByGroup(originalId, msg);

	}
}
