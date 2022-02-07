package com.edgedo.pay.wxpay.comunicate.event.vo;

/**事件基类
 * @author song
 *
 */
public abstract class BaseEvent {
	/**
	 * 目标用户
	 */
	private String ToUserName;
	/**
	 * 来源用户
	 */
	private String FromUserName;
	/**
	 * 创建时间
	 */
	private String CreateTime;
	/**
	 * 消息类型
	 */
	private String MsgType;
	/**
	 * 事件类型
	 */
	private String Event;
	
	/**
     * 时间戳
     */
    private String timestamp;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 加密类型
     */
    private String encryptType;
	
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
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
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
	public String getEncryptType() {
		return encryptType;
	}
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}
	/**触发事件
	 * @return
	 * @throws Exception
	 */
	public abstract String handEvent()throws Exception;
	public abstract String handEvent(String msg)throws Exception;
	

}
