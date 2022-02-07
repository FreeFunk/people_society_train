package com.edgedo.pay.wxpay.comunicate.vo;

/**
 * 二维码实体类
 * 
 * @author tiangai
 * @since 2014-06-30 Am 10:16
 * @version 1.0
 */
public class QRCode {
	/**
	 * 获取的二维码ticket
	 */
	private String ticket;
	/**
	 * 二维码有效时间，以秒为单位， 最大不超过1800秒
	 */
	private int expireSeconds;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
