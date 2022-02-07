package com.edgedo.pay.wxpay.comunicate.msg.vo;

import com.edgedo.pay.wxpay.comunicate.msg.MsgSenderFactory;

/**语音消息
 * @author song
 *
 */
public class VoiceMsg extends BaseReceiveMsg {
	/**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId="";  
    /**
     * 语音格式，如amr，speex等
     */
    private String Format="";  
    
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	@Override
	public String dealMsg() throws Exception {
		String result = "尊敬的鲜农府宾，您意见已提交，客服正在处理中，请稍后!果蔬肉蛋将会陆续上架，永远五折优惠。一元夺宝，团购返现等活动即将上线，敬请期待！";
		/** 此时，如果用户输入的是“你好”，在经过上面的过程之后，result为“你也好”类似的内容
		 *  因为最终回复给微信的也是xml格式的数据，所有需要将其封装为文本类型返回消息 ,
		 * 	明文
		 * */
		MsgSenderFactory msgSenderFactory = MsgSenderFactory.getInstance();
		TextMsgSender textMsgSender = (TextMsgSender)msgSenderFactory.creator(MsgSenderFactory.TEXT_MSG_TYPE, getEncryptType(), getTimestamp(), getNonce());

		textMsgSender.setFromUserName(getToUserName());
		textMsgSender.setToUserName(getFromUserName());
		textMsgSender.setContent(result);
		result = textMsgSender.transform();
		return result;
	}

	public String dealMsg(String msg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
