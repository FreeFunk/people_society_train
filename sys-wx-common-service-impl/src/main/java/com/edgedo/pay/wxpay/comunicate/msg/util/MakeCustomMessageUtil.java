package com.edgedo.pay.wxpay.comunicate.msg.util;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.pay.wxpay.comunicate.msg.vo.Article;
import com.edgedo.pay.wxpay.comunicate.msg.vo.Music;
import com.edgedo.pay.wxpay.util.WxCommonUtil;

import java.util.List;

/**
 * 生成客服消息工具类
 * 
 * @author songqingtao
 */
public class MakeCustomMessageUtil extends WxCommonUtil {
	/**
	 * 组装文本客服消息
	 * 
	 * @param openId
	 *            普通用户openid
	 * @param content
	 *            文本消息内容
	 * @return String
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":"
				+ "{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}

	/**
	 * 组装图片客服消息
	 * 
	 * @param openId
	 *            普通用户openid
	 * @param mediaId
	 *            发送的图片的媒体ID
	 * @return String
	 */
	public static String makeImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":"
				+ "{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装语音客服消息
	 * 
	 * @param openId
	 *            普通用户openid
	 * @param mediaId
	 *            发送的语音的媒体ID
	 * @return String
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":"
				+ "{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装视频客服消息
	 * 
	 * @param openId
	 *            普通用户openid
	 * @param mediaId
	 *            发送的语音的媒体ID
	 * @param title
	 *            视频消息的标题
	 * @param description
	 *            视频消息的描述
	 * @return String
	 */
	public static String makeVideoCustomMessage(String openId, String mediaId,
			String title, String description) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":"
				+ "{\"media_id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId, title, description);
	}

	/**
	 * 组装音乐客服消息
	 * 
	 * @param openId
	 *            普通用户openid
	 * @param music
	 *            音乐对象
	 * @return String
	 */
	public static String makeMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.toJSONString(music)
				.toString());
		return jsonMsg;
	}

	/**
	 * 组装图文客服消息
	 * 
	 * @param openId
	 *            普通用户openid
	 * @param articleList
	 *            图文消息列表
	 * @return String
	 */
	public static String makeNewsCustomMessage(String openId,
			List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(
				jsonMsg,
				openId,
				JSONObject.toJSONString(articleList).toString()
						.replace("\"", "\\\""));
		return jsonMsg;
	}

	/**
	 * 本地测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(MakeCustomMessageUtil.makeTextCustomMessage("ttt",
				"dddd"));

	}

}
