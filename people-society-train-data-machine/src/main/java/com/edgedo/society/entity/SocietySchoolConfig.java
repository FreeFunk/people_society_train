package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_config")
public class SocietySchoolConfig implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:学校配置KEYID
	 */
	@TableField(value="CONFIG_KEY_ID",exist=true)
	java.lang.String configKeyId;
	
	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:更新时间
	 */
	@TableField(value="UPDATE_TIME",exist=true)
	java.util.Date updateTime;
	
	/**
	 * 属性描述:所属学校ID
	 */
	@TableField(value="OWNER_SCHOOL_ID",exist=true)
	java.lang.String ownerSchoolId;
	
	/**
	 * 属性描述:所属学校名
	 */
	@TableField(value="OWNER_SCHOOL_NAME",exist=true)
	java.lang.String ownerSchoolName;
	
	/**
	 * 属性描述:键
	 */
	@TableField(value="CONFIG_KEY",exist=true)
	java.lang.String configKey;
	
	/**
	 * 属性描述:值
	 */
	@TableField(value="CONFIG_VALUE",exist=true)
	java.lang.String configValue;
	
	/**
	 * 属性描述:备注
	 */
	@TableField(value="REMARK",exist=true)
	java.lang.String remark;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	
	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.lang.String getConfigKeyId(){
		return this.configKeyId;
	}
	
	public void setConfigKeyId(java.lang.String configKeyId){
		this.configKeyId=configKeyId;
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
	
	
	public java.lang.String getOwnerSchoolId(){
		return this.ownerSchoolId;
	}
	
	public void setOwnerSchoolId(java.lang.String ownerSchoolId){
		this.ownerSchoolId=ownerSchoolId;
	}
	
	
	public java.lang.String getOwnerSchoolName(){
		return this.ownerSchoolName;
	}
	
	public void setOwnerSchoolName(java.lang.String ownerSchoolName){
		this.ownerSchoolName=ownerSchoolName;
	}
	
	
	public java.lang.String getConfigKey(){
		return this.configKey;
	}
	
	public void setConfigKey(java.lang.String configKey){
		this.configKey=configKey;
	}
	
	
	public java.lang.String getConfigValue(){
		return this.configValue;
	}
	
	public void setConfigValue(java.lang.String configValue){
		this.configValue=configValue;
	}
	
	
	public java.lang.String getRemark(){
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark){
		this.remark=remark;
	}
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", configKeyId=").append(configKeyId);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", updateTime=").append(updateTime);			
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", ownerSchoolName=").append(ownerSchoolName);			
			sb.append(", configKey=").append(configKey);			
			sb.append(", configValue=").append(configValue);			
			sb.append(", remark=").append(remark);			
			sb.append(", dataState=").append(dataState);			
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
        SocietySchoolConfig other = (SocietySchoolConfig) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getConfigKeyId() == null ? other.getId() == null : this.getConfigKeyId().equals(other.getConfigKeyId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getUpdateTime() == null ? other.getId() == null : this.getUpdateTime().equals(other.getUpdateTime()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getConfigKey() == null ? other.getId() == null : this.getConfigKey().equals(other.getConfigKey()))		
				        		&&(this.getConfigValue() == null ? other.getId() == null : this.getConfigValue().equals(other.getConfigValue()))		
				        		&&(this.getRemark() == null ? other.getId() == null : this.getRemark().equals(other.getRemark()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getConfigKeyId() == null) ? 0 : getConfigKeyId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getOwnerSchoolName() == null) ? 0 : getOwnerSchoolName().hashCode());		
		        result = prime * result + ((getConfigKey() == null) ? 0 : getConfigKey().hashCode());		
		        result = prime * result + ((getConfigValue() == null) ? 0 : getConfigValue().hashCode());		
		        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
