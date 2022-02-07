package com.edgedo.pay.wxpay.comunicate.msg.vo;

/**发送消息基类
 * @author song
 *
 */
public abstract class BaseMsgSender {
	/**
	 * 目标用户
	 */
	private String ToUserName = "";
	/**
	 * 发送者
	 */
	private String FromUserName = "";
	/**
	 * 创建时间 字符串 时间戳
	 */
	private String CreateTime = "";
	/**
	 * 消息类型
	 */
	private String MsgType = "";
	
	/**
	 * 加密类型
	 */
	private String encryptType;
	/**
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * 随机数
	 */
	private String nonce; 
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	public String getEncryptType() {
		return encryptType;
	}
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	/**转换成消息文本
	 * @return
	 * @throws Exception
	 */
	public abstract String transform()throws Exception;
}
