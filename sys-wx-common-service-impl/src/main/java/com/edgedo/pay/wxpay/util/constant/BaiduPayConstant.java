package com.edgedo.pay.wxpay.util.constant;

/**百度支付常量
 * @author song
 *
 */
public class BaiduPayConstant {
	/**ServiceCode列表
	 * 即时到账支付
	 */
	public static final String SERVICE_CODE_PAY = "1";
	/**ServiceCode列表
	 * 按订单号查询支付结果
	 */
	public static final String SERVICE_CODE_QUERY = "11";
	
	/**
	 * 币种：人民币
	 */
	public static final String MONEY_TYPE_RMB="1";
	
	/** 即时到帐接口中支付方式列表   || 查单接口和通知接口中支付方式
	 * 余额支付（必须登录百度钱包）
	 */
	public static final String PAY_TYPE_YE="1";
	/** 即时到帐接口中支付方式列表   || 查单接口和通知接口中支付方式
	 * 网银支付（在百度钱包页面上选择银行，可以不登录百度钱包）
	 */
	public static final String PAY_TYPE_WY="2";
	/** 即时到帐接口中支付方式列表   || 查单接口和通知接口中支付方式
	 * 银行网关支付（直接跳到银行的支付页面，无需登录百度钱包）
	 */
	public static final String PAY_TYPE_YHWG="3";
	
	/**摘要算法列表
	 * MD5
	 */
	public static final String ALGORITHM_MD5="1";
	/**摘要算法列表
	 * SHA-1
	 */
	public static final String ALGORITHM_SHA="2";
	
	/**支付结果列表
	 * 支付成功
	 */
	public static final String PAY_RESULT_SUCCESS="1";
	/**支付结果列表
	 * 等待支付
	 */
	public static final String PAY_RESULT_WAIT="2";
	/**支付结果列表
	 * 退款成功
	 */
	public static final String PAY_RESULT_REFUND="3";
	
	/**响应数据格式列表
	 * XML
	 */
	public static final String RESPONSE_DATA_FORMAT="1";
	
	/**字符编码列表
	 * GBK
	 */
	public static final String ENCODE_GBK="1";
	
	/**订单查询错误码列表
	 * 查询到结果
	 */
	public static final String ORDER_QUERY_RESULT_0="0";
	/**订单查询错误码列表
	 * 查询结果为空
	 */
	public static final String ORDER_QUERY_RESULT_1002="1002";
	/**订单查询错误码列表
	 * 缺少请求参数
	 */
	public static final String ORDER_QUERY_RESULT_5801="5801";
	/**订单查询错误码列表
	 * 请求参数非法
	 */
	public static final String ORDER_QUERY_RESULT_5802="5802";
	/**订单查询错误码列表
	 * 不支持的签名算法
	 */
	public static final String ORDER_QUERY_RESULT_5803="5803";
	/**订单查询错误码列表
	 * 验签失败
	 */
	public static final String ORDER_QUERY_RESULT_5804="5804";
	/**订单查询错误码列表
	 * 服务器内部错误
	 */
	public static final String ORDER_QUERY_RESULT_5806="5806";
	
	/**银行编号列表
	 * 银联
	 */
	public static final String BANK_NO_UNIONPAY="11";
	/**银行编号列表
	 * 工商银行
	 */
	public static final String BANK_NO_ICBC = "101";
	/**银行编号列表
	 * 中国招商银行
	 */
	public static final String BANK_NO_CMB = "201";
	/**银行编号列表
	 * 中国建设银行
	 */
	public static final String BANK_NO_CCB = "301";
	/**银行编号列表
	 * 中国农业银行
	 */
	public static final String BANK_NO_ABC = "401";
	/**银行编号列表
	 * 中信银行
	 */
	public static final String BANK_NO_CNCB = "501";
	/**银行编号列表
	 * 浦东发展银行
	 */
	public static final String BANK_NO_SPDB = "601";
	/**银行编号列表
	 * 中国光大银行
	 */
	public static final String BANK_NO_CEB = "701";
	/**银行编号列表
	 * 深圳发展银行
	 */
	public static final String BANK_NO_SDB = "801";
	/**银行编号列表
	 * 交通银行
	 */
	public static final String BANK_NO_BCM = "1101";
	
	/** TODO
	 * 为了保证商户系统和百度钱包系统之间通信的真实性，百度钱包与商户签约时，为每个商户分配一个保密的、独一无二的key，我们把这个key称为百度钱包合作密钥。
	 * 百度钱包合作密钥将用于对通信数据的签名，必须确保只有商户和百度钱包知道，否则通信数据可能被篡改或伪造。
	 */
	public static final String PAY_KEY="ypqhAuPQrwVDKZbm4JL45M3rWyFakVxS";
	/** TODO
	 * 百度钱包为接入百度钱包系统的每家商户分配一个独一无二的ID，作为商户身份的唯一标识，我们把这个ID称为百度钱包商户号。
	 * 百度钱包商户号在商户与百度钱包签约时分配。
	 */
	public static final String APP_ID="1000046135";
	
	/**即时到账支付接口（不要求登录百度钱包）
	 * 请求方式：GET   https://wallet.baidu.com/api/0/pay/0/direct";
	 * 参数参考支付文档5.1
	 */
	public static final String PAY_URL="https://www.baifubao.com/api/0/pay/0/direct/";
	
	/**即时到账WAP支付接口（不要求登录百度钱包）
	 * 请求方式：GET   https://wallet.baidu.com/api/0/pay/0/direct";
	 * 参数参考支付文档5.1
	 */
	public static final String WAP_PAY_URL="https://www.baifubao.com/api/0/pay/0/wapdirect";
	
	/**即时到账支付接口（要求登录百度钱包）
	 * 其他方面，与 即时到账支付接口（不要求登录百度钱包） 基本相同。
	 * 不同点包括：请求参数pay_type不支持3（银行网关支付）。
	 */
	public static final String PAY_URL_LONGIN_WALLET= "https://www.baifubao.com/api/0/pay/0/direct/0";
	
	/**按订单号查询支付结果接口
	 * 请求方式：GET
	 * 请求参数见文档5.3
	 */
	public static final String QUERY_PAY_RESULT_BY_ORDER_NO= " https://www.baifubao.com/api/0/query/0/pay_result_by_order_no";

	/**
	 * 前台回调地址
	 */
	public static final String PAY_PAGE_URL = WeixinCommonConstant.APP_URL+"mkb/chihuo/alipaySucc.html";
	/**
	 * pc前台回调
	 */
	public static final String PC_PAY_PAGE_URL = WeixinCommonConstant.APP_URL+"/diancai_h/paySucc.html";
	/**
	 * 后台回调地址
	 */
	public static final String PAY_RETURN_URL= WeixinCommonConstant.APP_URL+"doBaiduPayAction_baiduBillPaySuccessForCh.action";
	
	/**字符编码列表
	 * GBK
	 */
	public static final String VERSION="2";
	
	/**支付结果
	 * 1	支付成功
	 */
	public static final String PAY_RESULT_SUCC="1";
	
	/**支付结果
	 * 2 等待支付
	 */
	public static final String PAY_RESULTWAIT="2";
	
	/**支付结果
	 * 3 退款成功
	 */
	public static final String PAY_RESULT_TRADSUCC ="3";
	
	/**支付结果
	 * 3 退款成功
	 */
	public static final String PAY_SUCC_HTML ="<HTML><head><meta name=\"VIP_BFB_PAYMENT\" content=\"BAIFUBAO\"></head></HTML>";
}
