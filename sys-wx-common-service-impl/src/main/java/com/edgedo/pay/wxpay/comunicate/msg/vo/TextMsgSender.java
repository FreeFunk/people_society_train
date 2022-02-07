package com.edgedo.pay.wxpay.comunicate.msg.vo;


import com.edgedo.pay.wxpay.util.WxCommonUtil;

/**回复文本消息
 * @author song
 *
 */
public class TextMsgSender extends BaseMsgSender {
	/**
	 * 回复文本
	 */
	private String Content = "";
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String transform() throws Exception {
		StringBuffer sb = new StringBuffer();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(getToUserName());  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(getFromUserName());  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(getCreateTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(getContent());  
        sb.append("]]></Content></xml>");  
        String result = sb.toString();
        if("aes".equals(getEncryptType())){
         	//加密传输或者是兼容模式传输 往回传的时候也得加密后传
         	result = WxCommonUtil.encryptMsg(getFromUserName(), result, getTimestamp(), getNonce());
         }
		return result;
	}

}
