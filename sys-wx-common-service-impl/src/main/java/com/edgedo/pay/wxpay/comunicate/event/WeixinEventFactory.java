package com.edgedo.pay.wxpay.comunicate.event;

import com.edgedo.pay.wxpay.comunicate.event.vo.*;
import com.edgedo.pay.wxpay.comunicate.msg.vo.EventMsg;

import java.util.Date;

/**微信事件工厂
 * @author song
 *
 */
public class WeixinEventFactory {	
	/**
	 * 关注事件类型
	 */
	public static final String SUBSCRIBE_EVENT_TYPE = "subscribe";
	/**
	 * 取消关注事件类型
	 */
	public static final String UNSUBSCRIBE_EVENT_TYPE = "unsubscribe";
	/**
	 * 已经关注过的关注事件
	 */
	public static final String SCAN_EVENT_TYPE = "SCAN";
	/**
	 * 上传地理位置事件
	 */
	public static final String LOCATION_EVENT_TYPE = "LOCATION";
	/**
	 * 点击菜单拉取事件
	 */
	public static final String CLICK_EVENT_TYPE = "CLICK";
	/**
	 * 点击菜单跳转页面事件
	 */
	public static final String VIEW_EVENT_TYPE = "VIEW";
	/**
	 * 模板消息发送成功的类型
	 */
	public static final String TEMPLATESENDJOBFINISH_TYPE = "TEMPLATESENDJOBFINISH";
	public static WeixinEventFactory weixinEventFactory = null;
	public WeixinEventFactory(){
		
	}
	
	/**创建事件实体
	 * @return
	 */
	public BaseEvent creator(EventMsg eventMsg){
		String eventType = eventMsg.getEvent();
		
		BaseEvent result = null;
		if(SUBSCRIBE_EVENT_TYPE.equals(eventType)){
			result = new SubscribeEvent();
			((SubscribeEvent)result).setEventKey(eventMsg.getEventKey());
			((SubscribeEvent)result).setTicket(eventMsg.getTicket());
		}else if(UNSUBSCRIBE_EVENT_TYPE.equals(eventType)){
			result = new UnsubscribeEvent();
		}else if(SCAN_EVENT_TYPE.equals(eventType)){
			result = new ScanEvent();
			((ScanEvent)result).setEventKey(eventMsg.getEventKey());
			((ScanEvent)result).setTicket(eventMsg.getTicket());
		}else if(LOCATION_EVENT_TYPE.equals(eventType)){
			result = new LocationEvent();
			((LocationEvent)result).setLatitude(eventMsg.getLatitude());
			((LocationEvent)result).setLongitude(eventMsg.getLongitude());
			((LocationEvent)result).setPrecision(eventMsg.getPrecision());
		}else if(CLICK_EVENT_TYPE.equals(eventType)){
			result = new ClickEvent();
			((ClickEvent)result).setEventKey(eventMsg.getEventKey());
		}else if(VIEW_EVENT_TYPE.equals(eventType)){
			result = new ViewEvent();
			((ViewEvent)result).setEventKey(eventMsg.getEventKey());
		}
		if(result != null){
			result.setMsgType("event");
			result.setEvent(eventType);
			Date date = new Date();
			result.setCreateTime(date.getTime()+"");
			result.setEncryptType(eventMsg.getEncryptType());
			result.setTimestamp(eventMsg.getTimestamp());
			result.setNonce(eventMsg.getNonce());
			result.setFromUserName(eventMsg.getFromUserName());
			result.setToUserName(eventMsg.getToUserName());
		}
		return result;
	}
	/**
	 * 取得单例
	 */
	public static WeixinEventFactory getInstance(){
		if(weixinEventFactory == null){
			weixinEventFactory = new WeixinEventFactory();
		}
		return weixinEventFactory;
	}
}
