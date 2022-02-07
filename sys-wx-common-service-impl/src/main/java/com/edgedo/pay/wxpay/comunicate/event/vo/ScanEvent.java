package com.edgedo.pay.wxpay.comunicate.event.vo;

import com.edgedo.pay.wxpay.comunicate.event.vo.BaseEvent;

/**扫描二维码 已经关注了
 * @author song
 *
 */
public class ScanEvent extends BaseEvent {
	/**
	 * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
	 */
	private String EventKey;
	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	private String Ticket;
	
	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	@Override
	public String handEvent() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handEvent(String msg) throws Exception {
		return null;
	}


}
