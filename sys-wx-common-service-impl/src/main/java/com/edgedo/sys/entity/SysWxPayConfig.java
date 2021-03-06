package com.edgedo.sys.entity;


import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_wx_pay_config")
public class SysWxPayConfig implements Serializable{
	
		
	/**
	 * 属性描述:id
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:signType
	 */
	@TableField(value="SIGN_TYPE",exist=true)
	java.lang.String signType;
	
	/**
	 * 属性描述:mchid
	 */
	@TableField(value="MCHID",exist=true)
	java.lang.String mchid;
	
	/**
	 * 属性描述:payKey
	 */
	@TableField(value="PAY_KEY",exist=true)
	java.lang.String payKey;
	
	/**
	 * 属性描述:partner
	 */
	@TableField(value="PARTNER",exist=true)
	java.lang.String partner;
	
	/**
	 * 属性描述:partnerKey
	 */
	@TableField(value="PARTNER_KEY",exist=true)
	java.lang.String partnerKey;
	
	/**
	 * 属性描述:certPath
	 */
	@TableField(value="CERT_PATH",exist=true)
	java.lang.String certPath;
	
	/**
	 * 属性描述:successUrl
	 */
	@TableField(value="SUCCESS_URL",exist=true)
	java.lang.String successUrl;
	
	
	
	
	
	
	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.lang.String getSignType(){
		return this.signType;
	}
	
	public void setSignType(java.lang.String signType){
		this.signType=signType;
	}
	
	
	public java.lang.String getMchid(){
		return this.mchid;
	}
	
	public void setMchid(java.lang.String mchid){
		this.mchid=mchid;
	}
	
	
	public java.lang.String getPayKey(){
		return this.payKey;
	}
	
	public void setPayKey(java.lang.String payKey){
		this.payKey=payKey;
	}
	
	
	public java.lang.String getPartner(){
		return this.partner;
	}
	
	public void setPartner(java.lang.String partner){
		this.partner=partner;
	}
	
	
	public java.lang.String getPartnerKey(){
		return this.partnerKey;
	}
	
	public void setPartnerKey(java.lang.String partnerKey){
		this.partnerKey=partnerKey;
	}
	
	
	public java.lang.String getCertPath(){
		return this.certPath;
	}
	
	public void setCertPath(java.lang.String certPath){
		this.certPath=certPath;
	}
	
	
	public java.lang.String getSuccessUrl(){
		return this.successUrl;
	}
	
	public void setSuccessUrl(java.lang.String successUrl){
		this.successUrl=successUrl;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", signType=").append(signType);			
			sb.append(", mchid=").append(mchid);			
			sb.append(", payKey=").append(payKey);			
			sb.append(", partner=").append(partner);			
			sb.append(", partnerKey=").append(partnerKey);			
			sb.append(", certPath=").append(certPath);			
			sb.append(", successUrl=").append(successUrl);			
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
        SysWxPayConfig other = (SysWxPayConfig) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getSignType() == null ? other.getId() == null : this.getSignType().equals(other.getSignType()))		
				        		&&(this.getMchid() == null ? other.getId() == null : this.getMchid().equals(other.getMchid()))		
				        		&&(this.getPayKey() == null ? other.getId() == null : this.getPayKey().equals(other.getPayKey()))		
				        		&&(this.getPartner() == null ? other.getId() == null : this.getPartner().equals(other.getPartner()))		
				        		&&(this.getPartnerKey() == null ? other.getId() == null : this.getPartnerKey().equals(other.getPartnerKey()))		
				        		&&(this.getCertPath() == null ? other.getId() == null : this.getCertPath().equals(other.getCertPath()))		
				        		&&(this.getSuccessUrl() == null ? other.getId() == null : this.getSuccessUrl().equals(other.getSuccessUrl()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getSignType() == null) ? 0 : getSignType().hashCode());		
		        result = prime * result + ((getMchid() == null) ? 0 : getMchid().hashCode());		
		        result = prime * result + ((getPayKey() == null) ? 0 : getPayKey().hashCode());		
		        result = prime * result + ((getPartner() == null) ? 0 : getPartner().hashCode());		
		        result = prime * result + ((getPartnerKey() == null) ? 0 : getPartnerKey().hashCode());		
		        result = prime * result + ((getCertPath() == null) ? 0 : getCertPath().hashCode());		
		        result = prime * result + ((getSuccessUrl() == null) ? 0 : getSuccessUrl().hashCode());		
		;
        return result;
    }
	


}
