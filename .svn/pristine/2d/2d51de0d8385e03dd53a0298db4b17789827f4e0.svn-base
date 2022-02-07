package com.edgedo.pay.wxpay.comunicate.msg.vo;

/**接收消息基类
 * @author song
 *
 */
public abstract class BaseReceiveMsg{   
    /**
	 * 目标用户
	 */
	private String ToUserName="";  
    /**
     * 来源用户
     */
    private String FromUserName="";  
    /**
     * 创建时间，字符串
     */
    private String CreateTime="";  
    /**
     * 消息类型 text/image/voice/video/location/link
     */
    private String MsgType="";  
    /**
     * 消息id，64位整型  
     */
    private String MsgId=""; 
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

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
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


	/**处理吃货消息
	 * @return
	 * @throws Exception
	 */
	public abstract String dealMsg()throws Exception;

	public abstract String dealMsg(String msg)throws Exception;
	

}
