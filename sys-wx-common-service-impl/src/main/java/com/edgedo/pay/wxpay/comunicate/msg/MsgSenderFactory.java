package com.edgedo.pay.wxpay.comunicate.msg;

import com.edgedo.pay.wxpay.comunicate.msg.vo.*;

import java.util.Date;

/**
 * 消息发送工厂
 * 
 * @author song
 *
 */
public class MsgSenderFactory {
	/**
	 * 回复文本消息类型
	 */
	public static final String TEXT_MSG_TYPE = "text";
	/**
	 * 回复图片消息类型
	 */
	public static final String IMAGE_MSG_TYPE = "image";
	/**
	 * 回复音频消息类型
	 */
	public static final String VOICE_MSG_TYPE = "voice";
	/**
	 * 回复视频消息类型
	 */
	public static final String VIDEO_MSG_TYPE = "video";
	/**
	 * 回复音乐消息类型
	 */
	public static final String MUSIC_MSG_TYPE = "music";
	/**
	 * 回复新闻消息类型
	 */
	public static final String NEWS_MSG_TYPE = "news";

	private static MsgSenderFactory factory = new MsgSenderFactory();

	private MsgSenderFactory() {

	}
	
	/**取得工厂实例
	 * @return
	 */
	public static MsgSenderFactory getInstance(){
		if(factory == null){
			factory = new MsgSenderFactory();
		}
		return factory;
	}
	/**创建回复消息体实例
	 * @param msgType 消息类型 
	 * @param encryptType 加密类型
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	public BaseMsgSender creator(String msgType, String encryptType, String timestamp, String nonce){
		BaseMsgSender result = null;
		if(TEXT_MSG_TYPE.equals(msgType)){
			result = new TextMsgSender();
		}else if(IMAGE_MSG_TYPE.equals(msgType)){
			result = new ImageMsgSender();
		}else if(VOICE_MSG_TYPE.equals(msgType)){
			result = new VoiceMsgSender();
		}else if(VIDEO_MSG_TYPE.equals(msgType)){
			result = new VideoMsgSender();
		}else if(MUSIC_MSG_TYPE.equals(msgType)){
			result = new MusicMsgSender();
		}else if(NEWS_MSG_TYPE.equals(msgType)){
			result = new NewsMsgSender();
		}
		if(result != null){
			result.setMsgType(msgType);
			result.setEncryptType(encryptType);
			result.setTimestamp(timestamp);
			result.setNonce(nonce);
			Date date = new Date();  
			result.setCreateTime(date.getTime()+"");
		}
		return result;
	}
}
