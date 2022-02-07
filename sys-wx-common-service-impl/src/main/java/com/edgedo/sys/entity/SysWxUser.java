package com.edgedo.sys.entity;


import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_wx_user")
public class SysWxUser implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:原始id
	 */
	@TableField(value="ORA_ID",exist=true)
	java.lang.String oraId;
	
	/**
	 * 属性描述:微信OPENID
	 */
	@TableField(value="OPEN_ID",exist=true)
	java.lang.String openId;
	
	/**
	 * 属性描述:微信昵称
	 */
	@TableField(value="NICK_NAME",exist=true)
	java.lang.String nickName;
	
	/**
	 * 属性描述:头像地址
	 */
	@TableField(value="HEAD_PHOTO",exist=true)
	java.lang.String headPhoto;
	
	/**
	 * 属性描述:性别
	 */
	@TableField(value="GENDER",exist=true)
	java.lang.String gender;
	
	/**
	 * 属性描述:城市
	 */
	@TableField(value="CITY",exist=true)
	java.lang.String city;
	
	/**
	 * 属性描述:省份
	 */
	@TableField(value="PROVINCE",exist=true)
	java.lang.String province;
	
	/**
	 * 属性描述:国家
	 */
	@TableField(value="COUNTRY",exist=true)
	java.lang.String country;
	
	/**
	 * 属性描述:语言
	 */
	@TableField(value="LANGUAGE",exist=true)
	java.lang.String language;
	
	/**
	 * 属性描述:上次登录时间
	 */
	@TableField(value="LAST_LOGIN_TIME",exist=true)
	java.util.Date lastLoginTime;
	
	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:城市服务id
	 */
	@TableField(value="APP_ID",exist=true)
	java.lang.String appId;
	
	
	
	
	
	
	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.lang.String getOraId(){
		return this.oraId;
	}
	
	public void setOraId(java.lang.String oraId){
		this.oraId=oraId;
	}
	
	
	public java.lang.String getOpenId(){
		return this.openId;
	}
	
	public void setOpenId(java.lang.String openId){
		this.openId=openId;
	}
	
	
	public java.lang.String getNickName(){
		return this.nickName;
	}
	
	public void setNickName(java.lang.String nickName){
		this.nickName=nickName;
	}
	
	
	public java.lang.String getHeadPhoto(){
		return this.headPhoto;
	}
	
	public void setHeadPhoto(java.lang.String headPhoto){
		this.headPhoto=headPhoto;
	}
	
	
	public java.lang.String getGender(){
		return this.gender;
	}
	
	public void setGender(java.lang.String gender){
		this.gender=gender;
	}
	
	
	public java.lang.String getCity(){
		return this.city;
	}
	
	public void setCity(java.lang.String city){
		this.city=city;
	}
	
	
	public java.lang.String getProvince(){
		return this.province;
	}
	
	public void setProvince(java.lang.String province){
		this.province=province;
	}
	
	
	public java.lang.String getCountry(){
		return this.country;
	}
	
	public void setCountry(java.lang.String country){
		this.country=country;
	}
	
	
	public java.lang.String getLanguage(){
		return this.language;
	}
	
	public void setLanguage(java.lang.String language){
		this.language=language;
	}
	
	
	public java.util.Date getLastLoginTime(){
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(java.util.Date lastLoginTime){
		this.lastLoginTime=lastLoginTime;
	}
	
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}
	
	
	public java.lang.String getAppId(){
		return this.appId;
	}
	
	public void setAppId(java.lang.String appId){
		this.appId=appId;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", oraId=").append(oraId);			
			sb.append(", openId=").append(openId);			
			sb.append(", nickName=").append(nickName);			
			sb.append(", headPhoto=").append(headPhoto);			
			sb.append(", gender=").append(gender);			
			sb.append(", city=").append(city);			
			sb.append(", province=").append(province);			
			sb.append(", country=").append(country);			
			sb.append(", language=").append(language);			
			sb.append(", lastLoginTime=").append(lastLoginTime);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", appId=").append(appId);			
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
        SysWxUser other = (SysWxUser) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getOraId() == null ? other.getId() == null : this.getOraId().equals(other.getOraId()))		
				        		&&(this.getOpenId() == null ? other.getId() == null : this.getOpenId().equals(other.getOpenId()))		
				        		&&(this.getNickName() == null ? other.getId() == null : this.getNickName().equals(other.getNickName()))		
				        		&&(this.getHeadPhoto() == null ? other.getId() == null : this.getHeadPhoto().equals(other.getHeadPhoto()))		
				        		&&(this.getGender() == null ? other.getId() == null : this.getGender().equals(other.getGender()))		
				        		&&(this.getCity() == null ? other.getId() == null : this.getCity().equals(other.getCity()))		
				        		&&(this.getProvince() == null ? other.getId() == null : this.getProvince().equals(other.getProvince()))		
				        		&&(this.getCountry() == null ? other.getId() == null : this.getCountry().equals(other.getCountry()))		
				        		&&(this.getLanguage() == null ? other.getId() == null : this.getLanguage().equals(other.getLanguage()))		
				        		&&(this.getLastLoginTime() == null ? other.getId() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getAppId() == null ? other.getId() == null : this.getAppId().equals(other.getAppId()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getOraId() == null) ? 0 : getOraId().hashCode());		
		        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());		
		        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());		
		        result = prime * result + ((getHeadPhoto() == null) ? 0 : getHeadPhoto().hashCode());		
		        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());		
		        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());		
		        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());		
		        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());		
		        result = prime * result + ((getLanguage() == null) ? 0 : getLanguage().hashCode());		
		        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());		
		;
        return result;
    }
	


}
