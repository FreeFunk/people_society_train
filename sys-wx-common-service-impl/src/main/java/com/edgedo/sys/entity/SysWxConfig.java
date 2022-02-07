package com.edgedo.sys.entity;


import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_wx_config")
public class SysWxConfig implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:微信账号
	 */
	@TableField(value="WEIXIN_CODE",exist=true)
	java.lang.String weixinCode;
	
	/**
	 * 属性描述:昵称
	 */
	@TableField(value="NICK_NAME",exist=true)
	java.lang.String nickName;
	
	/**
	 * 属性描述:订阅号或者服务号
	 */
	@TableField(value="MP_TYPE",exist=true)
	java.lang.String mpType;
	
	/**
	 * 属性描述:描述
	 */
	@TableField(value="REMARK",exist=true)
	java.lang.String remark;
	
	/**
	 * 属性描述:邮箱地址
	 */
	@TableField(value="EMAIL",exist=true)
	java.lang.String email;
	
	/**
	 * 属性描述:应用id
	 */
	@TableField(value="APP_ID",exist=true)
	java.lang.String appId;
	
	/**
	 * 属性描述:应用秘钥
	 */
	@TableField(value="APP_SECRET",exist=true)
	java.lang.String appSecret;
	
	/**
	 * 属性描述:令牌
	 */
	@TableField(value="TOKEN",exist=true)
	java.lang.String token;
	
	/**
	 * 属性描述:消息加密钥匙
	 */
	@TableField(value="ENCODING_AES_KEY",exist=true)
	java.lang.String encodingAesKey;
	
	/**
	 * 属性描述:服务器地址
	 */
	@TableField(value="URL",exist=true)
	java.lang.String url;
	
	/**
	 * 属性描述:消息加密方式
	 */
	@TableField(value="SECRET_TYPE",exist=true)
	java.lang.String secretType;
	
	/**
	 * 属性描述:微信token
	 */
	@TableField(value="ACCESS_TOKEN",exist=true)
	java.lang.String accessToken;
	
	/**
	 * 属性描述:微信TICKET
	 */
	@TableField(value="TICKET",exist=true)
	java.lang.String ticket;
	
	/**
	 * 属性描述:操作时间
	 */
	@TableField(value="OPERATE_TIME",exist=true)
	java.util.Date operateTime;
	
	/**
	 * 属性描述:微信ip地址
	 */
	@TableField(value="WX_IP",exist=true)
	java.lang.String wxIp;
	/**
	 * 属性描述:微信菜单json
	 */
	@TableField(value="WX_MENUE_JSON",exist=true)
	java.lang.String wxMenueJson;
	
	
	
	
	
	
	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.lang.String getWeixinCode(){
		return this.weixinCode;
	}
	
	public void setWeixinCode(java.lang.String weixinCode){
		this.weixinCode=weixinCode;
	}
	
	
	public java.lang.String getNickName(){
		return this.nickName;
	}
	
	public void setNickName(java.lang.String nickName){
		this.nickName=nickName;
	}
	
	
	public java.lang.String getMpType(){
		return this.mpType;
	}
	
	public void setMpType(java.lang.String mpType){
		this.mpType=mpType;
	}
	
	
	public java.lang.String getRemark(){
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark){
		this.remark=remark;
	}
	
	
	public java.lang.String getEmail(){
		return this.email;
	}
	
	public void setEmail(java.lang.String email){
		this.email=email;
	}
	
	
	public java.lang.String getAppId(){
		return this.appId;
	}
	
	public void setAppId(java.lang.String appId){
		this.appId=appId;
	}
	
	
	public java.lang.String getAppSecret(){
		return this.appSecret;
	}
	
	public void setAppSecret(java.lang.String appSecret){
		this.appSecret=appSecret;
	}
	
	
	public java.lang.String getToken(){
		return this.token;
	}
	
	public void setToken(java.lang.String token){
		this.token=token;
	}
	
	
	public java.lang.String getEncodingAesKey(){
		return this.encodingAesKey;
	}
	
	public void setEncodingAesKey(java.lang.String encodingAesKey){
		this.encodingAesKey=encodingAesKey;
	}
	
	
	public java.lang.String getUrl(){
		return this.url;
	}
	
	public void setUrl(java.lang.String url){
		this.url=url;
	}
	
	
	public java.lang.String getSecretType(){
		return this.secretType;
	}
	
	public void setSecretType(java.lang.String secretType){
		this.secretType=secretType;
	}
	
	
	public java.lang.String getAccessToken(){
		return this.accessToken;
	}
	
	public void setAccessToken(java.lang.String accessToken){
		this.accessToken=accessToken;
	}
	
	
	public java.lang.String getTicket(){
		return this.ticket;
	}
	
	public void setTicket(java.lang.String ticket){
		this.ticket=ticket;
	}
	
	
	public java.util.Date getOperateTime(){
		return this.operateTime;
	}
	
	public void setOperateTime(java.util.Date operateTime){
		this.operateTime=operateTime;
	}
	
	
	public java.lang.String getWxIp(){
		return this.wxIp;
	}
	
	public void setWxIp(java.lang.String wxIp){
		this.wxIp=wxIp;
	}

	public String getWxMenueJson() {
		return wxMenueJson;
	}

	public void setWxMenueJson(String wxMenueJson) {
		this.wxMenueJson = wxMenueJson;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", weixinCode=").append(weixinCode);			
			sb.append(", nickName=").append(nickName);			
			sb.append(", mpType=").append(mpType);			
			sb.append(", remark=").append(remark);			
			sb.append(", email=").append(email);			
			sb.append(", appId=").append(appId);			
			sb.append(", appSecret=").append(appSecret);			
			sb.append(", token=").append(token);			
			sb.append(", encodingAesKey=").append(encodingAesKey);			
			sb.append(", url=").append(url);			
			sb.append(", secretType=").append(secretType);			
			sb.append(", accessToken=").append(accessToken);			
			sb.append(", ticket=").append(ticket);			
			sb.append(", operateTime=").append(operateTime);			
			sb.append(", wxIp=").append(wxIp);			
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
        SysWxConfig other = (SysWxConfig) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getWeixinCode() == null ? other.getId() == null : this.getWeixinCode().equals(other.getWeixinCode()))		
				        		&&(this.getNickName() == null ? other.getId() == null : this.getNickName().equals(other.getNickName()))		
				        		&&(this.getMpType() == null ? other.getId() == null : this.getMpType().equals(other.getMpType()))		
				        		&&(this.getRemark() == null ? other.getId() == null : this.getRemark().equals(other.getRemark()))		
				        		&&(this.getEmail() == null ? other.getId() == null : this.getEmail().equals(other.getEmail()))		
				        		&&(this.getAppId() == null ? other.getId() == null : this.getAppId().equals(other.getAppId()))		
				        		&&(this.getAppSecret() == null ? other.getId() == null : this.getAppSecret().equals(other.getAppSecret()))		
				        		&&(this.getToken() == null ? other.getId() == null : this.getToken().equals(other.getToken()))		
				        		&&(this.getEncodingAesKey() == null ? other.getId() == null : this.getEncodingAesKey().equals(other.getEncodingAesKey()))		
				        		&&(this.getUrl() == null ? other.getId() == null : this.getUrl().equals(other.getUrl()))		
				        		&&(this.getSecretType() == null ? other.getId() == null : this.getSecretType().equals(other.getSecretType()))		
				        		&&(this.getAccessToken() == null ? other.getId() == null : this.getAccessToken().equals(other.getAccessToken()))		
				        		&&(this.getTicket() == null ? other.getId() == null : this.getTicket().equals(other.getTicket()))		
				        		&&(this.getOperateTime() == null ? other.getId() == null : this.getOperateTime().equals(other.getOperateTime()))		
				        		&&(this.getWxIp() == null ? other.getId() == null : this.getWxIp().equals(other.getWxIp()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getWeixinCode() == null) ? 0 : getWeixinCode().hashCode());		
		        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());		
		        result = prime * result + ((getMpType() == null) ? 0 : getMpType().hashCode());		
		        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());		
		        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());		
		        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());		
		        result = prime * result + ((getAppSecret() == null) ? 0 : getAppSecret().hashCode());		
		        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());		
		        result = prime * result + ((getEncodingAesKey() == null) ? 0 : getEncodingAesKey().hashCode());		
		        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());		
		        result = prime * result + ((getSecretType() == null) ? 0 : getSecretType().hashCode());		
		        result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());		
		        result = prime * result + ((getTicket() == null) ? 0 : getTicket().hashCode());		
		        result = prime * result + ((getOperateTime() == null) ? 0 : getOperateTime().hashCode());		
		        result = prime * result + ((getWxIp() == null) ? 0 : getWxIp().hashCode());		
		;
        return result;
    }
	


}
