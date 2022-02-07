package com.edgedo.pay.wxpay.util.constant;

/**支付使用的常量
 * @author song
 *
 */
public class WxPayConstant {
	/**
	 * 微信支付接口地址
	 */
	//微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	//退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	//对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	//短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	//接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
	
	/**
	 * 签名加密方式 MD5
	 */
	public final static String MD5_SIGN_TYPE = "MD5";
	
	/**
	 * 微信支付分配的商户号
	 */
	public final static String CH_MCHID = "";
	
	/**
	 * 支付秘钥 加密
	 */
	public final static String CH_PAY_KEY = "";
	
	/**
	 * 吃货商户财付通账号 加密
	 */
	public static final String CH_PARTNER = "";
	
	/**
	 * 吃货商财付通密码 加密
	 */
	public static final String CH_PARTNER_KEY = "";
	
	/**
	 * 证书名
	 */
	public final static String CH_CERT_NAME = "apiclient_cert";
	
	/**
	 * 微信支付证书存放路径地址
	 */
	public final static String CH_CERT_PATH = "D:/"+CH_CERT_NAME+".p12";
	

	/**
	 * 支付类型 JSAPI
	 */
	public final static String JSAPI_TRADE_TYPE = "JSAPI";
	
	/**
	 * NATIVE
	 */
	public final static String NATIVE_TRADE_TYPE = "NATIVE";
	
	/**
	 * APP
	 */
	public final static String APP_TRADE_TYPE = "APP";
}
