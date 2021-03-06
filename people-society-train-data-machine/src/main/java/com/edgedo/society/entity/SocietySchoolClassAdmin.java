package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_class_admin")
public class SocietySchoolClassAdmin implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:创建人ID
	 */
	@TableField(value="CREATE_USER_ID",exist=true)
	java.lang.String createUserId;
	
	/**
	 * 属性描述:创建人
	 */
	@TableField(value="CREATE_USER_NAME",exist=true)
	java.lang.String createUserName;
	
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
	 * 属性描述:班主任姓名
	 */
	@TableField(value="CLASS_ADMIN_NAME",exist=true)
	java.lang.String classAdminName;
	
	/**
	 * 属性描述:班主任手机号
	 */
	@TableField(value="CLASS_ADMIN_PHONE",exist=true)
	java.lang.String classAdminPhone;
	
	/**
	 * 属性描述:班主任账号
	 */
	@TableField(value="CLASS_ADMIN_CODE",exist=true)
	java.lang.String classAdminCode;
	
	/**
	 * 属性描述:SYS_USER_ID
	 */
	@TableField(value="SYS_USER_ID",exist=true)
	java.lang.String sysUserId;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;


	/**
	 * 属性描述:所属组长ID
	 */
	@TableField(value="OWNER_CLASS_GROUP_ID",exist=true)
	java.lang.String ownerClassGroupId;

	public String getOwnerClassGroupId() {
		return ownerClassGroupId;
	}

	public void setOwnerClassGroupId(String ownerClassGroupId) {
		this.ownerClassGroupId = ownerClassGroupId;
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
	
	
	public java.lang.String getCreateUserId(){
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.String createUserId){
		this.createUserId=createUserId;
	}
	
	
	public java.lang.String getCreateUserName(){
		return this.createUserName;
	}
	
	public void setCreateUserName(java.lang.String createUserName){
		this.createUserName=createUserName;
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
	
	
	public java.lang.String getClassAdminName(){
		return this.classAdminName;
	}
	
	public void setClassAdminName(java.lang.String classAdminName){
		this.classAdminName=classAdminName;
	}
	
	
	public java.lang.String getClassAdminPhone(){
		return this.classAdminPhone;
	}
	
	public void setClassAdminPhone(java.lang.String classAdminPhone){
		this.classAdminPhone=classAdminPhone;
	}
	
	
	public java.lang.String getClassAdminCode(){
		return this.classAdminCode;
	}
	
	public void setClassAdminCode(java.lang.String classAdminCode){
		this.classAdminCode=classAdminCode;
	}
	
	
	public java.lang.String getSysUserId(){
		return this.sysUserId;
	}
	
	public void setSysUserId(java.lang.String sysUserId){
		this.sysUserId=sysUserId;
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
			sb.append(", createTime=").append(createTime);			
			sb.append(", createUserId=").append(createUserId);			
			sb.append(", createUserName=").append(createUserName);			
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", ownerSchoolName=").append(ownerSchoolName);			
			sb.append(", classAdminName=").append(classAdminName);			
			sb.append(", classAdminPhone=").append(classAdminPhone);			
			sb.append(", classAdminCode=").append(classAdminCode);			
			sb.append(", sysUserId=").append(sysUserId);			
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
        SocietySchoolClassAdmin other = (SocietySchoolClassAdmin) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getClassAdminName() == null ? other.getId() == null : this.getClassAdminName().equals(other.getClassAdminName()))		
				        		&&(this.getClassAdminPhone() == null ? other.getId() == null : this.getClassAdminPhone().equals(other.getClassAdminPhone()))		
				        		&&(this.getClassAdminCode() == null ? other.getId() == null : this.getClassAdminCode().equals(other.getClassAdminCode()))		
				        		&&(this.getSysUserId() == null ? other.getId() == null : this.getSysUserId().equals(other.getSysUserId()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());		
		        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getOwnerSchoolName() == null) ? 0 : getOwnerSchoolName().hashCode());		
		        result = prime * result + ((getClassAdminName() == null) ? 0 : getClassAdminName().hashCode());		
		        result = prime * result + ((getClassAdminPhone() == null) ? 0 : getClassAdminPhone().hashCode());		
		        result = prime * result + ((getClassAdminCode() == null) ? 0 : getClassAdminCode().hashCode());		
		        result = prime * result + ((getSysUserId() == null) ? 0 : getSysUserId().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
