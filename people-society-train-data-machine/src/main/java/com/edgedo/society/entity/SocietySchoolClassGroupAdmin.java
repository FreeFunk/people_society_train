package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_class_group_admin")
public class SocietySchoolClassGroupAdmin implements Serializable{
	
		
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
	 * 属性描述:组长姓名
	 */
	@TableField(value="CLASS_GROUP_ADMIN_NAME",exist=true)
	java.lang.String classGroupAdminName;
	
	/**
	 * 属性描述:组长手机号
	 */
	@TableField(value="CLASS_GROUP_ADMIN_PHONE",exist=true)
	java.lang.String classGroupAdminPhone;
	
	/**
	 * 属性描述:组长账号
	 */
	@TableField(value="CLASS_GROUP_ADMIN_CODE",exist=true)
	java.lang.String classGroupAdminCode;
	
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
	
	
	public java.lang.String getClassGroupAdminName(){
		return this.classGroupAdminName;
	}
	
	public void setClassGroupAdminName(java.lang.String classGroupAdminName){
		this.classGroupAdminName=classGroupAdminName;
	}
	
	
	public java.lang.String getClassGroupAdminPhone(){
		return this.classGroupAdminPhone;
	}
	
	public void setClassGroupAdminPhone(java.lang.String classGroupAdminPhone){
		this.classGroupAdminPhone=classGroupAdminPhone;
	}
	
	
	public java.lang.String getClassGroupAdminCode(){
		return this.classGroupAdminCode;
	}
	
	public void setClassGroupAdminCode(java.lang.String classGroupAdminCode){
		this.classGroupAdminCode=classGroupAdminCode;
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
			sb.append(", classGroupAdminName=").append(classGroupAdminName);			
			sb.append(", classGroupAdminPhone=").append(classGroupAdminPhone);			
			sb.append(", classGroupAdminCode=").append(classGroupAdminCode);			
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
        SocietySchoolClassGroupAdmin other = (SocietySchoolClassGroupAdmin) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getClassGroupAdminName() == null ? other.getId() == null : this.getClassGroupAdminName().equals(other.getClassGroupAdminName()))		
				        		&&(this.getClassGroupAdminPhone() == null ? other.getId() == null : this.getClassGroupAdminPhone().equals(other.getClassGroupAdminPhone()))		
				        		&&(this.getClassGroupAdminCode() == null ? other.getId() == null : this.getClassGroupAdminCode().equals(other.getClassGroupAdminCode()))		
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
		        result = prime * result + ((getClassGroupAdminName() == null) ? 0 : getClassGroupAdminName().hashCode());		
		        result = prime * result + ((getClassGroupAdminPhone() == null) ? 0 : getClassGroupAdminPhone().hashCode());		
		        result = prime * result + ((getClassGroupAdminCode() == null) ? 0 : getClassGroupAdminCode().hashCode());		
		        result = prime * result + ((getSysUserId() == null) ? 0 : getSysUserId().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
