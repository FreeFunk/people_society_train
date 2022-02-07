package com.edgedo.pay.wxpay.comunicate.msg.util;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.pay.wxpay.comunicate.msg.vo.Article;
import com.edgedo.pay.wxpay.util.WxCommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成群发消息工具类
 * 
 * @author tiangai
 * @since 2014-06-30 Am 10:41
 * @version 1.0
 */
public class MakeMassMessageUtil extends WxCommonUtil {
	/**
	 * 组装上传图文素材消息
	 * 
	 * @param articles
	 *            图文组
	 * @return String
	 */
	public static String makeUploadNewsMessage(List<Article> articles) {
		String jsonMsg = "{\"articles\": \"%s\"}";
		return String.format(jsonMsg, JSONObject.toJSONString(articles).toString());
	}

	/**
	 * 组装上传视频消息
	 * 
	 * @param mediaId
	 *            媒体文件ID<br>
	 *            此处media_id需通过基础支持中的上传下载多媒体文件来得到
	 * @param title
	 *            标题
	 * @param description
	 *            描述
	 * @return String
	 */
	public static String makeUploadVideoMessage(String mediaId, String title,
			String description) {
		String jsonMsg = "{\"media_id\": \"%s\",\"title\": \"%s\",\"description\": \"%s\"}";
		return String.format(jsonMsg, mediaId, title, description);
	}

	/**
	 * 组装删除群发消息
	 * 
	 * @param msgid
	 *            信息ID
	 * @return String
	 */
	public static String makeDeleteMassMessage(String msgid) {
		String jsonMsg = "{\"msgid\":%s}";
		return String.format(jsonMsg, msgid);
	}

	/**
	 * 组装根据分组群发图文消息
	 * 
	 * @param groupid
	 *            组Id
	 * @param mediaId
	 *            媒体Id
	 * @return String
	 */
	public static String makeNewsGroupMessage(String groupid, String mediaId) {
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"mpnews\":{\"media_id\":\"%s\"},\"msgtype\":\"mpnews\"}";
		return String.format(jsonMsg, groupid, mediaId);
	}

	/**
	 * 组装根据OpenID列表群发图文消息
	 * 
	 * @param touser
	 *            普通用户openid
	 * @param mediaId
	 *            媒体Id
	 * @return String
	 */
	public static String makeNewsGroupMessage(List<String> touser,
			String mediaId) {
		String jsonMsg = "{\"touser\":%s,\"mpnews\":{\"media_id\":\"%s\"},\"msgtype\":\"mpnews\"}";
		jsonMsg = String.format(jsonMsg, JSONObject.toJSONString(touser)
				.toString(), mediaId);
		return jsonMsg;
	}

	/**
	 * 组装分组群发文本消息
	 * 
	 * @param groupid
	 *            组Id
	 * @param content
	 *            内容
	 * @return String
	 */
	public static String makeTextGroupMessage(String groupid, String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"text\":{\"content\":\"%s\"},\"msgtype\":\"text\"}";
		return String.format(jsonMsg, groupid, content);
	}

	/**
	 * 组装根据OpenID列表群发文本消息
	 * 
	 * @param touser
	 *            普通用户openid
	 * @param content
	 *            内容
	 * @return String
	 */
	public static String makeTextGroupMessage(List<String> touser,
			String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":%s, \"msgtype\": \"text\", \"text\": { \"content\": \"%s\"}}";
		jsonMsg = String.format(jsonMsg, JSONObject.toJSONString(touser)
				.toString(), content);
		return jsonMsg;
	}

	/**
	 * 组装根据分组群发语音消息
	 * 
	 * @param groupid
	 *            组Id
	 * @param mediaId
	 *            多媒体文件Id
	 * @return String
	 */
	public static String makeVoiceGroupMessage(String groupid, String mediaId) {
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"voice\":{\"media_id\":\"%s\"},\"msgtype\":\"voice\"}";
		return String.format(jsonMsg, groupid, mediaId);
	}

	/**
	 * 组装根据OpenID列表群发语音消息
	 * 
	 * @param touser
	 *            普通用户openid
	 * @param mediaId
	 *            媒体Id
	 * @return String
	 */
	public static String makeVoiceGroupMessage(List<String> touser,
			String mediaId) {
		String jsonMsg = "{\"touser\":%s,\"voice\":{\"media_id\":\"%s\"},\"msgtype\":\"voice\"}";
		jsonMsg = String.format(jsonMsg, JSONObject.toJSONString(touser)
				.toString(), mediaId);
		return jsonMsg;
	}

	/**
	 * 组装根据分组群发图片消息
	 * 
	 * @param groupid
	 *            组Id
	 * @param mediaId
	 *            多媒体文件Id
	 * @return String
	 */
	public static String makePicGroupMessage(String groupid, String mediaId) {
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"image\":{\"media_id\":\"%s\"},\"msgtype\":\"image\"}";
		return String.format(jsonMsg, groupid, mediaId);
	}

	/**
	 * 组装根据OpenID列表群发图片消息
	 * 
	 * @param touser
	 *            普通用户openid
	 * @param mediaId
	 *            媒体Id
	 * @return String
	 */
	public static String makePicGroupMessage(List<String> touser, String mediaId) {
		String jsonMsg = "{\"touser\":%s,\"image\":{\"media_id\":\"%s\"},\"msgtype\":\"image\"}";
		jsonMsg = String.format(jsonMsg, JSONObject.toJSONString(touser)
				.toString(), mediaId);
		return jsonMsg;
	}

	/**
	 * 组装根据分组群发视频消息
	 * 
	 * @param groupid
	 *            组Id
	 * @param mediaId
	 *            多媒体文件Id
	 * @return String
	 */
	public static String makeVideoGroupMessage(String groupid, String mediaId) {
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"mpvideo\":{\"media_id\":\"%s\",},\"msgtype\":\"mpvideo\"}";
		return String.format(jsonMsg, groupid, mediaId);
	}

	/**
	 * 组装根据OpenID列表群发视频消息
	 * 
	 * @param touser
	 *            普通用户openid
	 * @param mediaId
	 *            媒体Id
	 * @param title
	 *            标题
	 * @param description
	 *            描述
	 * @return String
	 */
	public static String makeVideoGroupMessage(List<String> touser,
			String mediaId, String title, String description) {
		String jsonMsg = "{\"touser\":%s,\"video\":{\"media_id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"},\"msgtype\":\"video\"}";
		jsonMsg = String.format(jsonMsg, JSONObject.toJSONString(touser)
				.toString(), mediaId, title, description);
		return jsonMsg;
	}

	public static void main(String[] args) {
		String groupId = "groupId";
		String mediaId = "mediaId";
		List<String> touser = new ArrayList<String>();
		touser.add("user1");
		touser.add("user2");
		// 根据分组群发图文消息
		System.out.println(makeNewsGroupMessage(groupId, mediaId));
		// 根据OpenID列表群发图文消息
		System.out.println(makeNewsGroupMessage(touser, mediaId));
		// 根据分组群发文本消息
		System.out.println(makeTextGroupMessage(touser, "content"));
		// 组装根据OpenID列表群发文本消息
		System.out.println(makeTextGroupMessage(touser, "content"));
		// 根据分组群发语音消息
		System.out.println(makeVoiceGroupMessage(groupId, mediaId));
		// 组装根据OpenID列表群发语音消息
		System.out.println(makeVoiceGroupMessage(touser, mediaId));
		// 根据分组群发图片消息
		System.out.println(makePicGroupMessage(groupId, mediaId));
		// 根据OpenID列表群发图片消息
		System.out.println(makePicGroupMessage(touser, mediaId));
		// 根据分组群发视频消息
		System.out.println(makeVideoGroupMessage(groupId, mediaId));
		// 根据OpenID列表群发视频消息
		System.out.println(makeVideoGroupMessage(touser, mediaId, "title",
				"description"));

	}
}
