package com.edgedo.pay.wxpay.comunicate.msg.vo;


import com.edgedo.pay.wxpay.comunicate.msg.MsgSenderFactory;

/**文本消息
 * @author song
 *
 */
public class TextMsg extends BaseReceiveMsg {
	private String Content;
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}	
	/**处理吃货消息回复
	 * @return
	 * @throws Exception
	 */
	public String dealMsg()throws Exception{
//		String result = WeixinMsgTools.getTulingResult(Content);
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

	/**处理吃货消息回复
	 * @return
	 * @throws Exception
	 */
	public String dealMsg(String msg)throws Exception{
		String result =msg;
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
	
}
