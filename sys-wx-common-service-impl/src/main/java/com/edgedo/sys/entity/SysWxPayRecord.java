package com.edgedo.sys.entity;


import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_wx_pay_record")
public class SysWxPayRecord implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:支付类型
	 */
	@TableField(value="PAY_TYPE",exist=true)
	java.lang.String payType;
	
	/**
	 * 属性描述:订单主键
	 */
	@TableField(value="BILL_ID",exist=true)
	java.lang.String billId;
	
	/**
	 * 属性描述:订单类型
	 */
	@TableField(value="BILL_TYPE",exist=true)
	java.lang.String billType;
	
	/**
	 * 属性描述:TRANSACTION_ID
	 */
	@TableField(value="TRANSACTION_ID",exist=true)
	java.lang.String transactionId;
	
	/**
	 * 属性描述:支付结果
	 */
	@TableField(value="PAY_RESULT",exist=true)
	java.lang.String payResult;
	
	/**
	 * 属性描述:支付金额
	 */
	@TableField(value="PAY_MONEY",exist=true)
	java.math.BigDecimal payMoney;
	
	/**
	 * 属性描述:NOTIFY_BODY
	 */
	@TableField(value="NOTIFY_BODY",exist=true)
	java.lang.String notifyBody;
	
	/**
	 * 属性描述:PREPAY_BODY
	 */
	@TableField(value="PREPAY_BODY",exist=true)
	java.lang.String prepayBody;
	
	/**
	 * 属性描述:公众账号ID
	 */
	@TableField(value="APP_ID",exist=true)
	java.lang.String appId;
	
	/**
	 * 属性描述:商户号
	 */
	@TableField(value="MCH_ID",exist=true)
	java.lang.String mchId;
	
	/**
	 * 属性描述:设备信息
	 */
	@TableField(value="DEVICE_INFO",exist=true)
	java.lang.String deviceInfo;
	
	/**
	 * 属性描述:随机字符串
	 */
	@TableField(value="NONCE_STR",exist=true)
	java.lang.String nonceStr;
	
	/**
	 * 属性描述:签名
	 */
	@TableField(value="SIGN",exist=true)
	java.lang.String sign;
	
	/**
	 * 属性描述:商品描述
	 */
	@TableField(value="BODY",exist=true)
	java.lang.String body;
	
	/**
	 * 属性描述:商品详情
	 */
	@TableField(value="DETAIL",exist=true)
	java.lang.String detail;
	
	/**
	 * 属性描述:附加数据
	 */
	@TableField(value="ATTACH",exist=true)
	java.lang.String attach;
	
	/**
	 * 属性描述:商户订单号
	 */
	@TableField(value="OUT_TRADE_NO",exist=true)
	java.lang.String outTradeNo;
	
	/**
	 * 属性描述:订单总金额
	 */
	@TableField(value="TOTAL_FEE",exist=true)
	java.math.BigDecimal totalFee;
	
	/**
	 * 属性描述:终端ip
	 */
	@TableField(value="SPBILL_CREATE_IP",exist=true)
	java.lang.String spbillCreateIp;
	
	/**
	 * 属性描述:COUPON_FEE
	 */
	@TableField(value="COUPON_FEE",exist=true)
	java.math.BigDecimal couponFee;
	
	/**
	 * 属性描述:交易起始时间
	 */
	@TableField(value="TIME_START",exist=true)
	java.lang.String timeStart;
	
	/**
	 * 属性描述:交易结束时间
	 */
	@TableField(value="TIME_EXPIRE",exist=true)
	java.lang.String timeExpire;
	
	/**
	 * 属性描述:商品标记
	 */
	@TableField(value="GOODS_TAG",exist=true)
	java.lang.String goodsTag;
	
	/**
	 * 属性描述:回调地址
	 */
	@TableField(value="NOTIFY_URL",exist=true)
	java.lang.String notifyUrl;
	
	/**
	 * 属性描述:支付方式
	 */
	@TableField(value="TRADE_TYPE",exist=true)
	java.lang.String tradeType;
	
	/**
	 * 属性描述:商品id
	 */
	@TableField(value="PRODUCT_ID",exist=true)
	java.lang.String productId;
	
	/**
	 * 属性描述:微信OPENID
	 */
	@TableField(value="OPENID",exist=true)
	java.lang.String openid;
	
	/**
	 * 属性描述:PREPAY_ID
	 */
	@TableField(value="PREPAY_ID",exist=true)
	java.lang.String prepayId;
	
	/**
	 * 属性描述:RETURN_MSG
	 */
	@TableField(value="RETURN_MSG",exist=true)
	java.lang.String returnMsg;
	
	/**
	 * 属性描述:RESULT_CODE
	 */
	@TableField(value="RESULT_CODE",exist=true)
	java.lang.String resultCode;
	
	/**
	 * 属性描述:ERR_CODE
	 */
	@TableField(value="ERR_CODE",exist=true)
	java.lang.String errCode;
	
	/**
	 * 属性描述:ERR_CODE_DES
	 */
	@TableField(value="ERR_CODE_DES",exist=true)
	java.lang.String errCodeDes;
	
	/**
	 * 属性描述:银行类型
	 */
	@TableField(value="BANK_TYPE",exist=true)
	java.lang.String bankType;
	
	/**
	 * 属性描述:TIME_END
	 */
	@TableField(value="TIME_END",exist=true)
	java.lang.String timeEnd;
	
	/**
	 * 属性描述:PREPAY_TIME
	 */
	@TableField(value="PREPAY_TIME",exist=true)
	java.util.Date prepayTime;
	
	/**
	 * 属性描述:PAY_TIME
	 */
	@TableField(value="PAY_TIME",exist=true)
	java.util.Date payTime;
	
	/**
	 * 属性描述:成功返回地址
	 */
	@TableField(value="SUCCESS_URL",exist=true)
	java.lang.String successUrl;
	/**
	 * 属性描述:成功返回地址
	 */
	@TableField(value="FAIL_URL",exist=true)
	java.lang.String failUrl;
	/**
	 * 属性描述：微信原始id
	 */
	@TableField(value="ORA_ID",exist=true)
	String oraId;
	//城市服务id
	@TableField(value="CITY_APP_ID",exist=true)
	String cityAppId;
	//城市服务回调地址
	@TableField(value="APP_SUCC_URL",exist=true)
	String appSuccUrl;


	public String getAppSuccUrl() {
		return appSuccUrl;
	}

	public void setAppSuccUrl(String appSuccUrl) {
		this.appSuccUrl = appSuccUrl;
	}

	public String getCityAppId() {
		return cityAppId;
	}

	public void setCityAppId(String cityAppId) {
		this.cityAppId = cityAppId;
	}

	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.lang.String getPayType(){
		return this.payType;
	}
	
	public void setPayType(java.lang.String payType){
		this.payType=payType;
	}
	
	
	public java.lang.String getBillId(){
		return this.billId;
	}
	
	public void setBillId(java.lang.String billId){
		this.billId=billId;
	}
	
	
	public java.lang.String getBillType(){
		return this.billType;
	}
	
	public void setBillType(java.lang.String billType){
		this.billType=billType;
	}
	
	
	public java.lang.String getTransactionId(){
		return this.transactionId;
	}
	
	public void setTransactionId(java.lang.String transactionId){
		this.transactionId=transactionId;
	}
	
	
	public java.lang.String getPayResult(){
		return this.payResult;
	}
	
	public void setPayResult(java.lang.String payResult){
		this.payResult=payResult;
	}
	
	
	public java.math.BigDecimal getPayMoney(){
		return this.payMoney;
	}
	
	public void setPayMoney(java.math.BigDecimal payMoney){
		this.payMoney=payMoney;
	}
	
	
	public java.lang.String getNotifyBody(){
		return this.notifyBody;
	}
	
	public void setNotifyBody(java.lang.String notifyBody){
		this.notifyBody=notifyBody;
	}
	
	
	public java.lang.String getPrepayBody(){
		return this.prepayBody;
	}
	
	public void setPrepayBody(java.lang.String prepayBody){
		this.prepayBody=prepayBody;
	}
	
	
	public java.lang.String getAppId(){
		return this.appId;
	}
	
	public void setAppId(java.lang.String appId){
		this.appId=appId;
	}
	
	
	public java.lang.String getMchId(){
		return this.mchId;
	}
	
	public void setMchId(java.lang.String mchId){
		this.mchId=mchId;
	}
	
	
	public java.lang.String getDeviceInfo(){
		return this.deviceInfo;
	}
	
	public void setDeviceInfo(java.lang.String deviceInfo){
		this.deviceInfo=deviceInfo;
	}
	
	
	public java.lang.String getNonceStr(){
		return this.nonceStr;
	}
	
	public void setNonceStr(java.lang.String nonceStr){
		this.nonceStr=nonceStr;
	}
	
	
	public java.lang.String getSign(){
		return this.sign;
	}
	
	public void setSign(java.lang.String sign){
		this.sign=sign;
	}
	
	
	public java.lang.String getBody(){
		return this.body;
	}
	
	public void setBody(java.lang.String body){
		this.body=body;
	}
	
	
	public java.lang.String getDetail(){
		return this.detail;
	}
	
	public void setDetail(java.lang.String detail){
		this.detail=detail;
	}
	
	
	public java.lang.String getAttach(){
		return this.attach;
	}
	
	public void setAttach(java.lang.String attach){
		this.attach=attach;
	}
	
	
	public java.lang.String getOutTradeNo(){
		return this.outTradeNo;
	}
	
	public void setOutTradeNo(java.lang.String outTradeNo){
		this.outTradeNo=outTradeNo;
	}
	
	
	public java.math.BigDecimal getTotalFee(){
		return this.totalFee;
	}
	
	public void setTotalFee(java.math.BigDecimal totalFee){
		this.totalFee=totalFee;
	}
	
	
	public java.lang.String getSpbillCreateIp(){
		return this.spbillCreateIp;
	}
	
	public void setSpbillCreateIp(java.lang.String spbillCreateIp){
		this.spbillCreateIp=spbillCreateIp;
	}
	
	
	public java.math.BigDecimal getCouponFee(){
		return this.couponFee;
	}
	
	public void setCouponFee(java.math.BigDecimal couponFee){
		this.couponFee=couponFee;
	}
	
	
	public java.lang.String getTimeStart(){
		return this.timeStart;
	}
	
	public void setTimeStart(java.lang.String timeStart){
		this.timeStart=timeStart;
	}
	
	
	public java.lang.String getTimeExpire(){
		return this.timeExpire;
	}
	
	public void setTimeExpire(java.lang.String timeExpire){
		this.timeExpire=timeExpire;
	}
	
	
	public java.lang.String getGoodsTag(){
		return this.goodsTag;
	}
	
	public void setGoodsTag(java.lang.String goodsTag){
		this.goodsTag=goodsTag;
	}
	
	
	public java.lang.String getNotifyUrl(){
		return this.notifyUrl;
	}
	
	public void setNotifyUrl(java.lang.String notifyUrl){
		this.notifyUrl=notifyUrl;
	}
	
	
	public java.lang.String getTradeType(){
		return this.tradeType;
	}
	
	public void setTradeType(java.lang.String tradeType){
		this.tradeType=tradeType;
	}
	
	
	public java.lang.String getProductId(){
		return this.productId;
	}
	
	public void setProductId(java.lang.String productId){
		this.productId=productId;
	}
	
	
	public java.lang.String getOpenid(){
		return this.openid;
	}
	
	public void setOpenid(java.lang.String openid){
		this.openid=openid;
	}
	
	
	public java.lang.String getPrepayId(){
		return this.prepayId;
	}
	
	public void setPrepayId(java.lang.String prepayId){
		this.prepayId=prepayId;
	}
	
	
	public java.lang.String getReturnMsg(){
		return this.returnMsg;
	}
	
	public void setReturnMsg(java.lang.String returnMsg){
		this.returnMsg=returnMsg;
	}
	
	
	public java.lang.String getResultCode(){
		return this.resultCode;
	}
	
	public void setResultCode(java.lang.String resultCode){
		this.resultCode=resultCode;
	}
	
	
	public java.lang.String getErrCode(){
		return this.errCode;
	}
	
	public void setErrCode(java.lang.String errCode){
		this.errCode=errCode;
	}
	
	
	public java.lang.String getErrCodeDes(){
		return this.errCodeDes;
	}
	
	public void setErrCodeDes(java.lang.String errCodeDes){
		this.errCodeDes=errCodeDes;
	}
	
	
	public java.lang.String getBankType(){
		return this.bankType;
	}
	
	public void setBankType(java.lang.String bankType){
		this.bankType=bankType;
	}
	
	
	public java.lang.String getTimeEnd(){
		return this.timeEnd;
	}
	
	public void setTimeEnd(java.lang.String timeEnd){
		this.timeEnd=timeEnd;
	}
	
	
	public java.util.Date getPrepayTime(){
		return this.prepayTime;
	}
	
	public void setPrepayTime(java.util.Date prepayTime){
		this.prepayTime=prepayTime;
	}
	
	
	public java.util.Date getPayTime(){
		return this.payTime;
	}
	
	public void setPayTime(java.util.Date payTime){
		this.payTime=payTime;
	}
	
	
	public java.lang.String getSuccessUrl(){
		return this.successUrl;
	}
	
	public void setSuccessUrl(java.lang.String successUrl){
		this.successUrl=successUrl;
	}

	public String getFailUrl() {
		return failUrl;
	}

	public void setFailUrl(String failUrl) {
		this.failUrl = failUrl;
	}

	public String getOraId() {
		return oraId;
	}

	public void setOraId(String oraId) {
		this.oraId = oraId;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", payType=").append(payType);			
			sb.append(", billId=").append(billId);			
			sb.append(", billType=").append(billType);			
			sb.append(", transactionId=").append(transactionId);			
			sb.append(", payResult=").append(payResult);			
			sb.append(", payMoney=").append(payMoney);			
			sb.append(", notifyBody=").append(notifyBody);			
			sb.append(", prepayBody=").append(prepayBody);			
			sb.append(", appId=").append(appId);			
			sb.append(", mchId=").append(mchId);			
			sb.append(", deviceInfo=").append(deviceInfo);			
			sb.append(", nonceStr=").append(nonceStr);			
			sb.append(", sign=").append(sign);			
			sb.append(", body=").append(body);			
			sb.append(", detail=").append(detail);			
			sb.append(", attach=").append(attach);			
			sb.append(", outTradeNo=").append(outTradeNo);			
			sb.append(", totalFee=").append(totalFee);			
			sb.append(", spbillCreateIp=").append(spbillCreateIp);			
			sb.append(", couponFee=").append(couponFee);			
			sb.append(", timeStart=").append(timeStart);			
			sb.append(", timeExpire=").append(timeExpire);			
			sb.append(", goodsTag=").append(goodsTag);			
			sb.append(", notifyUrl=").append(notifyUrl);			
			sb.append(", tradeType=").append(tradeType);			
			sb.append(", productId=").append(productId);			
			sb.append(", openid=").append(openid);			
			sb.append(", prepayId=").append(prepayId);			
			sb.append(", returnMsg=").append(returnMsg);			
			sb.append(", resultCode=").append(resultCode);			
			sb.append(", errCode=").append(errCode);			
			sb.append(", errCodeDes=").append(errCodeDes);			
			sb.append(", bankType=").append(bankType);			
			sb.append(", timeEnd=").append(timeEnd);			
			sb.append(", prepayTime=").append(prepayTime);			
			sb.append(", payTime=").append(payTime);			
			sb.append(", successUrl=").append(successUrl);
			sb.append(", failUrl=").append(failUrl);
        sb.append("]");
        return sb.toString();
    }

   
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysWxPayRecord other = (SysWxPayRecord) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getPayType() == null ? other.getId() == null : this.getPayType().equals(other.getPayType()))		
				        		&&(this.getBillId() == null ? other.getId() == null : this.getBillId().equals(other.getBillId()))		
				        		&&(this.getBillType() == null ? other.getId() == null : this.getBillType().equals(other.getBillType()))		
				        		&&(this.getTransactionId() == null ? other.getId() == null : this.getTransactionId().equals(other.getTransactionId()))		
				        		&&(this.getPayResult() == null ? other.getId() == null : this.getPayResult().equals(other.getPayResult()))		
				        		&&(this.getPayMoney() == null ? other.getId() == null : this.getPayMoney().equals(other.getPayMoney()))		
				        		&&(this.getNotifyBody() == null ? other.getId() == null : this.getNotifyBody().equals(other.getNotifyBody()))		
				        		&&(this.getPrepayBody() == null ? other.getId() == null : this.getPrepayBody().equals(other.getPrepayBody()))		
				        		&&(this.getAppId() == null ? other.getId() == null : this.getAppId().equals(other.getAppId()))		
				        		&&(this.getMchId() == null ? other.getId() == null : this.getMchId().equals(other.getMchId()))		
				        		&&(this.getDeviceInfo() == null ? other.getId() == null : this.getDeviceInfo().equals(other.getDeviceInfo()))		
				        		&&(this.getNonceStr() == null ? other.getId() == null : this.getNonceStr().equals(other.getNonceStr()))		
				        		&&(this.getSign() == null ? other.getId() == null : this.getSign().equals(other.getSign()))		
				        		&&(this.getBody() == null ? other.getId() == null : this.getBody().equals(other.getBody()))		
				        		&&(this.getDetail() == null ? other.getId() == null : this.getDetail().equals(other.getDetail()))		
				        		&&(this.getAttach() == null ? other.getId() == null : this.getAttach().equals(other.getAttach()))		
				        		&&(this.getOutTradeNo() == null ? other.getId() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))		
				        		&&(this.getTotalFee() == null ? other.getId() == null : this.getTotalFee().equals(other.getTotalFee()))		
				        		&&(this.getSpbillCreateIp() == null ? other.getId() == null : this.getSpbillCreateIp().equals(other.getSpbillCreateIp()))		
				        		&&(this.getCouponFee() == null ? other.getId() == null : this.getCouponFee().equals(other.getCouponFee()))		
				        		&&(this.getTimeStart() == null ? other.getId() == null : this.getTimeStart().equals(other.getTimeStart()))		
				        		&&(this.getTimeExpire() == null ? other.getId() == null : this.getTimeExpire().equals(other.getTimeExpire()))		
				        		&&(this.getGoodsTag() == null ? other.getId() == null : this.getGoodsTag().equals(other.getGoodsTag()))		
				        		&&(this.getNotifyUrl() == null ? other.getId() == null : this.getNotifyUrl().equals(other.getNotifyUrl()))		
				        		&&(this.getTradeType() == null ? other.getId() == null : this.getTradeType().equals(other.getTradeType()))		
				        		&&(this.getProductId() == null ? other.getId() == null : this.getProductId().equals(other.getProductId()))		
				        		&&(this.getOpenid() == null ? other.getId() == null : this.getOpenid().equals(other.getOpenid()))		
				        		&&(this.getPrepayId() == null ? other.getId() == null : this.getPrepayId().equals(other.getPrepayId()))		
				        		&&(this.getReturnMsg() == null ? other.getId() == null : this.getReturnMsg().equals(other.getReturnMsg()))		
				        		&&(this.getResultCode() == null ? other.getId() == null : this.getResultCode().equals(other.getResultCode()))		
				        		&&(this.getErrCode() == null ? other.getId() == null : this.getErrCode().equals(other.getErrCode()))		
				        		&&(this.getErrCodeDes() == null ? other.getId() == null : this.getErrCodeDes().equals(other.getErrCodeDes()))		
				        		&&(this.getBankType() == null ? other.getId() == null : this.getBankType().equals(other.getBankType()))		
				        		&&(this.getTimeEnd() == null ? other.getId() == null : this.getTimeEnd().equals(other.getTimeEnd()))		
				        		&&(this.getPrepayTime() == null ? other.getId() == null : this.getPrepayTime().equals(other.getPrepayTime()))		
				        		&&(this.getPayTime() == null ? other.getId() == null : this.getPayTime().equals(other.getPayTime()))		
				        		&&(this.getSuccessUrl() == null ? other.getId() == null : this.getSuccessUrl().equals(other.getSuccessUrl()))
								&&(this.getFailUrl() == null ? other.getId() == null : this.getFailUrl().equals(other.getFailUrl()))
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());		
		        result = prime * result + ((getBillId() == null) ? 0 : getBillId().hashCode());		
		        result = prime * result + ((getBillType() == null) ? 0 : getBillType().hashCode());		
		        result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());		
		        result = prime * result + ((getPayResult() == null) ? 0 : getPayResult().hashCode());		
		        result = prime * result + ((getPayMoney() == null) ? 0 : getPayMoney().hashCode());		
		        result = prime * result + ((getNotifyBody() == null) ? 0 : getNotifyBody().hashCode());		
		        result = prime * result + ((getPrepayBody() == null) ? 0 : getPrepayBody().hashCode());		
		        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());		
		        result = prime * result + ((getMchId() == null) ? 0 : getMchId().hashCode());		
		        result = prime * result + ((getDeviceInfo() == null) ? 0 : getDeviceInfo().hashCode());		
		        result = prime * result + ((getNonceStr() == null) ? 0 : getNonceStr().hashCode());		
		        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());		
		        result = prime * result + ((getBody() == null) ? 0 : getBody().hashCode());		
		        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());		
		        result = prime * result + ((getAttach() == null) ? 0 : getAttach().hashCode());		
		        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());		
		        result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());		
		        result = prime * result + ((getSpbillCreateIp() == null) ? 0 : getSpbillCreateIp().hashCode());		
		        result = prime * result + ((getCouponFee() == null) ? 0 : getCouponFee().hashCode());		
		        result = prime * result + ((getTimeStart() == null) ? 0 : getTimeStart().hashCode());		
		        result = prime * result + ((getTimeExpire() == null) ? 0 : getTimeExpire().hashCode());		
		        result = prime * result + ((getGoodsTag() == null) ? 0 : getGoodsTag().hashCode());		
		        result = prime * result + ((getNotifyUrl() == null) ? 0 : getNotifyUrl().hashCode());		
		        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());		
		        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());		
		        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());		
		        result = prime * result + ((getPrepayId() == null) ? 0 : getPrepayId().hashCode());		
		        result = prime * result + ((getReturnMsg() == null) ? 0 : getReturnMsg().hashCode());		
		        result = prime * result + ((getResultCode() == null) ? 0 : getResultCode().hashCode());		
		        result = prime * result + ((getErrCode() == null) ? 0 : getErrCode().hashCode());		
		        result = prime * result + ((getErrCodeDes() == null) ? 0 : getErrCodeDes().hashCode());		
		        result = prime * result + ((getBankType() == null) ? 0 : getBankType().hashCode());		
		        result = prime * result + ((getTimeEnd() == null) ? 0 : getTimeEnd().hashCode());		
		        result = prime * result + ((getPrepayTime() == null) ? 0 : getPrepayTime().hashCode());		
		        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());		
		        result = prime * result + ((getSuccessUrl() == null) ? 0 : getSuccessUrl().hashCode());
				result = prime * result + ((getFailUrl() == null) ? 0 : getFailUrl().hashCode());
        return result;
    }
	


}
