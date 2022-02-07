package com.edgedo.pay.wxpay.util.vo;

/**微信配置
 * @author song
 *
 */
public class WxConfig {
    /**
     * appId
     */
    private String appId;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 随机数
     */
    private String nonceStr;
    /**
     * 签名字符串
     */
    private String signature;
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    public String getNonceStr() {
        return nonceStr;
    }
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
}