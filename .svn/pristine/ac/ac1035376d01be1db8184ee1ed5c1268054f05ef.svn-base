package com.edgedo.pay.wxpay.comunicate.msg.vo;


import com.edgedo.pay.wxpay.util.WxCommonUtil;

/**回复音乐消息
 * @author song
 *
 */
public class MusicMsgSender extends BaseMsgSender {
	private Music music;
	
	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
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
        sb.append("</CreateTime><MsgType><![CDATA[music]]></MsgType><Music><Title><![CDATA[");  
        sb.append(this.getMusic().getTitle());  
        sb.append("]]></Title><Description><![CDATA[");
        sb.append(this.getMusic().getDescription());
        sb.append("]]></Description><MusicUrl><![CDATA[");
        sb.append(this.getMusic().getMusicUrl());
        sb.append("]]></MusicUrl><HQMusicUrl><![CDATA[");
        sb.append(this.getMusic().getHQMusicUrl());
        sb.append("]]></HQMusicUrl><ThumbMediaId><![CDATA[");
        sb.append(this.getMusic().getThumbMediaId());
        sb.append("]]></ThumbMediaId></Music></xml>");  
        String result = sb.toString();
        if("aes".equals(getEncryptType())){
         	//加密传输或者是兼容模式传输 往回传的时候也得加密后传
         	result = WxCommonUtil.encryptMsg(getFromUserName(), result, getTimestamp(), getNonce());
         }
		return result;
	}

}
