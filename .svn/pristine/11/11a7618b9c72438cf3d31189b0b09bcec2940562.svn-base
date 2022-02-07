package com.edgedo.pay.wxpay.comunicate.msg;

import com.edgedo.pay.wxpay.comunicate.msg.vo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**接收消息工厂
 * @author song
 *
 */
public class MsgReceiverFactory {
	/**
	 * 文本消息类型
	 */
	private static final String TEXT_MSG_TYPE = "text";
	/**
	 * 图片消息类型
	 */
	private static final String IMAGE_MSG_TYPE="image";
	/**
	 * 音频消息类型
	 */
	private static final String VOICE_MSG_TYPE="voice";
	/**
	 * 视频消息类型
	 */
	private static final String VIDEO_MSG_TYPE="video";
	/**
	 * 地理位置消息类型
	 */
	private static final String LOCATION_MSG_TYPE="location";
	/**
	 * 链接消息类型
	 */
	private static final String LINK_MSG_TYPE="link";
	/**
	 * 事件消息类型
	 */
	private static final String EVENT_MSG_TYPE="event";
	private static MsgReceiverFactory factory = new MsgReceiverFactory();
	
	private MsgReceiverFactory(){
		
	}
	
	/**创建消息实体
	 * @return
	 */
	public BaseReceiveMsg creator(String strXml, String encryptType, String timestamp, String nonce){
		BaseReceiveMsg result = null;
        try {  
            if (strXml.length() <= 0 || strXml == null)  {
                return null;  
            }
            // 将字符串转化为XML文档对象  
            Document document = DocumentHelper.parseText(strXml);
            // 获得文档的根节点  
            Element root = document.getRootElement();
            // 遍历根节点下所有子节点  
            Element msgTypeElement = root.element("MsgType");
            String msgType = msgTypeElement.getText();
            if(TEXT_MSG_TYPE.equals(msgType)){
            	//普通文本消息
            	result = new TextMsg();
            	Element contentElement = root.element("Content");
            	String content = contentElement.getText();
            	((TextMsg)result).setContent(content);
            }else if(IMAGE_MSG_TYPE.equals(msgType)){
            	//图片消息
            	result = new ImageMsg();
            	Element picUrlElement = root.element("PicUrl");
            	String picUrl = picUrlElement.getText();
            	Element mediaIdElement = root.element("MediaId");
            	String mediaId = mediaIdElement.getText();
            	((ImageMsg)result).setPicUrl(picUrl);
            	((ImageMsg)result).setMediaId(mediaId);
            }else if(VOICE_MSG_TYPE.equals(msgType)){
            	//音频消息
            	result = new VoiceMsg();
            	Element formatElement = root.element("Format");
            	String format = formatElement.getText();
            	Element mediaIdElement = root.element("MediaId");
            	String mediaId = mediaIdElement.getText();
            	((VoiceMsg)result).setFormat(format);
            	((VoiceMsg)result).setMediaId(mediaId);
            }else if(VIDEO_MSG_TYPE.equals(msgType)){
            	//视频消息
            	result = new VideoMsg();
            	Element mediaIdElement = root.element("MediaId");
            	String mediaId = mediaIdElement.getText();
            	Element thumbMediaIdElement = root.element("ThumbMediaId");
            	String thumbMediaId = thumbMediaIdElement.getText();
            	((VideoMsg)result).setMediaId(mediaId);
            	((VideoMsg)result).setThumbMediaId(thumbMediaId);
            }else if(LOCATION_MSG_TYPE.equals(msgType)){
            	//地理位置消息
            	result = new LocationMsg();
            	Element lxElement = root.element("Location_X");
            	String lx = lxElement.getText();
            	Element lyElement = root.element("Location_Y");
            	String ly = lyElement.getText();
            	Element scaleElement = root.element("Scale");
            	String scale = scaleElement.getText();
            	Element labelElement = root.element("Label");
            	String label = labelElement.getText();
            	((LocationMsg)result).setLocation_X(lx);
            	((LocationMsg)result).setLocation_Y(ly);
            	((LocationMsg)result).setScale(scale);
            	((LocationMsg)result).setLabel(label);
            }else if(LINK_MSG_TYPE.equals(msgType)){
            	//连接消息
            	result = new LinkMsg();
            	Element titleElement = root.element("Title");
            	String title = titleElement.getText();
            	Element descriptionElement = root.element("Description");
            	String description = descriptionElement.getText();
            	Element urlElement = root.element("Url");
            	String url = urlElement.getText();
            	((LinkMsg)result).setTitle(title);
            	((LinkMsg)result).setDescription(description);
            	((LinkMsg)result).setUrl(url);
            }else if(EVENT_MSG_TYPE.equals(msgType)){
            	//事件消息
            	result = new EventMsg();
            	Element eventElement = root.element("Event");
            	if(eventElement != null){
            		String event = eventElement.getText();
            		((EventMsg)result).setEvent(event);
            	}
            	Element eventKeyElement = root.element("EventKey");
            	if(eventKeyElement != null){
            		String eventKey = eventKeyElement.getText();
            		((EventMsg)result).setEventKey(eventKey);
            	}
            	Element ticketElement = root.element("Ticket");
            	if(ticketElement != null){
            		String ticket = ticketElement.getText();
            		((EventMsg)result).setTicket(ticket);
            	}
            	Element latitudeElement = root.element("Latitude");
            	if(latitudeElement != null){
            		String latitude = latitudeElement.getText();
            		((EventMsg)result).setLatitude(latitude);
            	}
            	Element longitudeElement = root.element("Longitude");
            	if(longitudeElement != null){
            		String longitude = longitudeElement.getText();
            		((EventMsg)result).setLongitude(longitude);
            	}
            	Element precisionElement = root.element("Precision");
            	if(precisionElement != null){
            		String precision = precisionElement.getText();
            		((EventMsg)result).setPrecision(precision);
            	}
            }
            result.setMsgType(msgType);
            result.setEncryptType(encryptType);
            result.setTimestamp(timestamp);
            result.setNonce(nonce);
            
            Element toUserNameElement = root.element("ToUserName");
            result.setToUserName(toUserNameElement.getText());
            Element fromUserNameElement = root.element("FromUserName");
            result.setFromUserName(fromUserNameElement.getText());
            Element createTimeElement = root.element("CreateTime");
            result.setCreateTime(createTimeElement.getText());
            Element msgIdElement = root.element("MsgId");
            if(msgIdElement != null){
            	result.setMsgId(msgIdElement.getText());
            }
        } catch (Exception e) {  
            // TODO: handle exception  
            System.out.println("xml 格式异常: "+ strXml);  
            e.printStackTrace();  
        }
        return result;
	}
	
	public static MsgReceiverFactory getInstance(){
		if(factory == null){
			factory = new MsgReceiverFactory();
		}
		return factory;
	}
}
