package com.edgedo.pay.wxpay.comunicate.msg.vo;


import com.edgedo.pay.wxpay.util.WxCommonUtil;

/**图片对象
 * @author song
 *
 */
public class ImageMsgSender extends BaseMsgSender {
	
	private Image image;
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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
        sb.append("</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[");  
        sb.append(this.getImage().getMediaId());  
        sb.append("]]></MediaId></Image></xml>");  
        String result = sb.toString();
        if("aes".equals(getEncryptType())){
         	//加密传输或者是兼容模式传输 往回传的时候也得加密后传
         	result = WxCommonUtil.encryptMsg(getFromUserName(), result, getTimestamp(), getNonce());
         }
		return result;
	}
}
