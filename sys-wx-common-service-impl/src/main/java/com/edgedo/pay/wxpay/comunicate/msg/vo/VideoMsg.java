package com.edgedo.pay.wxpay.comunicate.msg.vo;

/**视频消息
 * @author song
 *
 */
public class VideoMsg extends BaseReceiveMsg{
	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String MediaId = "";
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String ThumbMediaId = "";
	
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	@Override
	public String dealMsg() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	public String dealMsg(String msg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
