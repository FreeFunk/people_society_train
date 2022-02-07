package com.edgedo.pay.wxpay.util.vo;

/**预支付订单信息实体
 * @author song
 *
 */
public class PrePayOrderInfo {
	/**
	 * 商户系统内部的订单号,32个字符内、可包含字母
	 */
	private String outTradeNo;
	/**
	 * 商品或支付单简要描述
	 */
	private String body;
	/**
	 * 支付总金额,单位是分 ，取整
	 */
	private Integer totalFee;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 订单状态 "0":未支付 ；"1"：已支付；"-1"：订单有误，无法完成支付！
	 */
	private String state;
	
	/**
	 * 订单类型
	 */
	private String billType;
	
	/**
	 * 订单id
	 */
	private String billId;


	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

}

