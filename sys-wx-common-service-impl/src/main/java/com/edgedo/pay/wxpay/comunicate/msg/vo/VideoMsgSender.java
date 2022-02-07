package com.edgedo.pay.wxpay.comunicate.msg.vo;

import com.edgedo.pay.wxpay.util.WxCommonUtil;

/**回复视频消息
 * @author song
 *
 */
public class VideoMsgSender extends BaseMsgSender {
	private Video video;
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
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
        sb.append("</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[");  
        sb.append(this.getVideo().getMediaId());  
        sb.append("]]></MediaId><Title><![CDATA[");
        sb.append(this.getVideo().getTitle());
        sb.append("]]></Title><Description><![CDATA[");
        sb.append(this.getVideo().getDescription());
        sb.append("]]></Description></Video></xml>");  
        String result = sb.toString();
        if("aes".equals(getEncryptType())){
         	//加密传输或者是兼容模式传输 往回传的时候也得加密后传
         	result = WxCommonUtil.encryptMsg(getFromUserName(), result, getTimestamp(), getNonce());
         }
		return result;
	}

}
