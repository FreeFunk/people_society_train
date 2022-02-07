package com.edgedo.pay.wxpay.comunicate.msg.vo;


import com.edgedo.pay.wxpay.util.WxCommonUtil;

/**回复语音消息
 * @author song
 *
 */
public class VoiceMsgSender extends BaseMsgSender {
	private Voice voice;
	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
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
        sb.append("</CreateTime><MsgType><![CDATA[voice]]></MsgType><Voice><MediaId><![CDATA[");  
        sb.append(this.getVoice().getMediaId());  
        sb.append("]]></MediaId></Voice></xml>");  
        String result = sb.toString();
        if("aes".equals(getEncryptType())){
         	//加密传输或者是兼容模式传输 往回传的时候也得加密后传
         	result = WxCommonUtil.encryptMsg(getFromUserName(), result, getTimestamp(), getNonce());
         }
		return result;
	}

}
