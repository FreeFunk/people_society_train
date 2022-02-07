package com.edgedo.pay.wxpay.comunicate.event.vo;

import com.edgedo.pay.wxpay.comunicate.event.vo.BaseEvent;

/**单击事件
 * @author song
 *
 */
public class ClickEvent extends BaseEvent {
	/**
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 */
	private String EventKey;
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	@Override
	public String handEvent() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String handEvent(String msg) throws Exception {
		return null;
	}


}
