package com.edgedo.sys.entity;


import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_city_app_info")
public class SysCityAppInfo implements Serializable{
	
		
	/**
	 * 属性描述:城市appId
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:修改时间
	 */
	@TableField(value="UPDATE_TIME",exist=true)
	java.util.Date updateTime;
	
	/**
	 * 属性描述:修改人
	 */
	@TableField(value="UPDATE_USER",exist=true)
	java.lang.String updateUser;
	
	/**
	 * 属性描述:城市名称
	 */
	@TableField(value="CITY_NAME",exist=true)
	java.lang.String cityName;
	
	/**
	 * 属性描述:城市服务前缀
	 */
	@TableField(value="CITY_URL_PREFIX",exist=true)
	java.lang.String cityUrlPrefix;
	
	/**
	 * 属性描述:安全秘钥
	 */
	@TableField(value="SECURITY_KEY",exist=true)
	java.lang.String securityKey;
	
	/**
	 * 属性描述:接口token
	 */
	@TableField(value="ACCESS_TOKEN",exist=true)
	java.lang.String accessToken;
	
	/**
	 * 属性描述:token刷新时间
	 */
	@TableField(value="FRESH_TOKEN_TIME",exist=true)
	java.util.Date freshTokenTime;
	
	/**
	 * 属性描述:手机培训宝安全秘钥
	 */
	@TableField(value="SJPXB_SECURITY_KEY",exist=true)
	java.lang.String sjpxbSecurityKey;
	
	/**
	 * 属性描述:手机培训宝接口token
	 */
	@TableField(value="SJPXB_ACCESS_TOKEN",exist=true)
	java.lang.String sjpxbAccessToken;
	
	/**
	 * 属性描述:手机培训宝刷新token时间
	 */
	@TableField(value="SJPXB_FRESH_TOKEN_TIME",exist=true)
	java.util.Date sjpxbFreshTokenTime;

	/**
	 * 属性描述:手机培训宝刷新token时间
	 */
	@TableField(value="SubscribeEventMSG",exist=true)
	java.lang.String subscribeEventMSG;


	public String getSubscribeEventMSG() {
		return subscribeEventMSG;
	}

	public void setSubscribeEventMSG(String subscribeEventMSG) {
		this.subscribeEventMSG = subscribeEventMSG;
	}

	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}
	
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime=updateTime;
	}
	
	
	public java.lang.String getUpdateUser(){
		return this.updateUser;
	}
	
	public void setUpdateUser(java.lang.String updateUser){
		this.updateUser=updateUser;
	}
	
	
	public java.lang.String getCityName(){
		return this.cityName;
	}
	
	public void setCityName(java.lang.String cityName){
		this.cityName=cityName;
	}
	
	
	public java.lang.String getCityUrlPrefix(){
		return this.cityUrlPrefix;
	}
	
	public void setCityUrlPrefix(java.lang.String cityUrlPrefix){
		this.cityUrlPrefix=cityUrlPrefix;
	}
	
	
	public java.lang.String getSecurityKey(){
		return this.securityKey;
	}
	
	public void setSecurityKey(java.lang.String securityKey){
		this.securityKey=securityKey;
	}
	
	
	public java.lang.String getAccessToken(){
		return this.accessToken;
	}
	
	public void setAccessToken(java.lang.String accessToken){
		this.accessToken=accessToken;
	}
	
	
	public java.util.Date getFreshTokenTime(){
		return this.freshTokenTime;
	}
	
	public void setFreshTokenTime(java.util.Date freshTokenTime){
		this.freshTokenTime=freshTokenTime;
	}
	
	
	public java.lang.String getSjpxbSecurityKey(){
		return this.sjpxbSecurityKey;
	}
	
	public void setSjpxbSecurityKey(java.lang.String sjpxbSecurityKey){
		this.sjpxbSecurityKey=sjpxbSecurityKey;
	}
	
	
	public java.lang.String getSjpxbAccessToken(){
		return this.sjpxbAccessToken;
	}
	
	public void setSjpxbAccessToken(java.lang.String sjpxbAccessToken){
		this.sjpxbAccessToken=sjpxbAccessToken;
	}
	
	
	public java.util.Date getSjpxbFreshTokenTime(){
		return this.sjpxbFreshTokenTime;
	}
	
	public void setSjpxbFreshTokenTime(java.util.Date sjpxbFreshTokenTime){
		this.sjpxbFreshTokenTime=sjpxbFreshTokenTime;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", updateTime=").append(updateTime);			
			sb.append(", updateUser=").append(updateUser);			
			sb.append(", cityName=").append(cityName);			
			sb.append(", cityUrlPrefix=").append(cityUrlPrefix);			
			sb.append(", securityKey=").append(securityKey);			
			sb.append(", accessToken=").append(accessToken);			
			sb.append(", freshTokenTime=").append(freshTokenTime);			
			sb.append(", sjpxbSecurityKey=").append(sjpxbSecurityKey);			
			sb.append(", sjpxbAccessToken=").append(sjpxbAccessToken);			
			sb.append(", sjpxbFreshTokenTime=").append(sjpxbFreshTokenTime);			
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
        SysCityAppInfo other = (SysCityAppInfo) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getUpdateTime() == null ? other.getId() == null : this.getUpdateTime().equals(other.getUpdateTime()))		
				        		&&(this.getUpdateUser() == null ? other.getId() == null : this.getUpdateUser().equals(other.getUpdateUser()))		
				        		&&(this.getCityName() == null ? other.getId() == null : this.getCityName().equals(other.getCityName()))		
				        		&&(this.getCityUrlPrefix() == null ? other.getId() == null : this.getCityUrlPrefix().equals(other.getCityUrlPrefix()))		
				        		&&(this.getSecurityKey() == null ? other.getId() == null : this.getSecurityKey().equals(other.getSecurityKey()))		
				        		&&(this.getAccessToken() == null ? other.getId() == null : this.getAccessToken().equals(other.getAccessToken()))		
				        		&&(this.getFreshTokenTime() == null ? other.getId() == null : this.getFreshTokenTime().equals(other.getFreshTokenTime()))		
				        		&&(this.getSjpxbSecurityKey() == null ? other.getId() == null : this.getSjpxbSecurityKey().equals(other.getSjpxbSecurityKey()))		
				        		&&(this.getSjpxbAccessToken() == null ? other.getId() == null : this.getSjpxbAccessToken().equals(other.getSjpxbAccessToken()))		
				        		&&(this.getSjpxbFreshTokenTime() == null ? other.getId() == null : this.getSjpxbFreshTokenTime().equals(other.getSjpxbFreshTokenTime()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());		
		        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());		
		        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());		
		        result = prime * result + ((getCityUrlPrefix() == null) ? 0 : getCityUrlPrefix().hashCode());		
		        result = prime * result + ((getSecurityKey() == null) ? 0 : getSecurityKey().hashCode());		
		        result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());		
		        result = prime * result + ((getFreshTokenTime() == null) ? 0 : getFreshTokenTime().hashCode());		
		        result = prime * result + ((getSjpxbSecurityKey() == null) ? 0 : getSjpxbSecurityKey().hashCode());		
		        result = prime * result + ((getSjpxbAccessToken() == null) ? 0 : getSjpxbAccessToken().hashCode());		
		        result = prime * result + ((getSjpxbFreshTokenTime() == null) ? 0 : getSjpxbFreshTokenTime().hashCode());		
		;
        return result;
    }
	


}
