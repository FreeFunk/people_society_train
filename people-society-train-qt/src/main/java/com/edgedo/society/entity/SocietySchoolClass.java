package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_class")
public class SocietySchoolClass implements Serializable{
	
		
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
	 * 属性描述:所属专业ID
	 */
	@TableField(value="OWNER_MAJOR_ID",exist=true)
	java.lang.String ownerMajorId;
	
	/**
	 * 属性描述:所属专业名
	 */
	@TableField(value="OWNER_MAJOR_NAME",exist=true)
	java.lang.String ownerMajorName;
	
	/**
	 * 属性描述:负责人名
	 */
	@TableField(value="PERSON_IN_CHARGE",exist=true)
	java.lang.String personInCharge;
	
	/**
	 * 属性描述:负责人电话
	 */
	@TableField(value="PERSON_IN_CHARGE_PHONE_NUM",exist=true)
	java.lang.String personInChargePhoneNum;
	
	/**
	 * 属性描述:班级名
	 */
	@TableField(value="CLASS_NAME",exist=true)
	java.lang.String className;
	
	/**
	 * 属性描述:开始时间
	 */
	@TableField(value="CLASS_START_TIME",exist=true)
	java.util.Date classStartTime;
	
	/**
	 * 属性描述:结束时间
	 */
	@TableField(value="CLASS_END_TIME",exist=true)
	java.util.Date classEndTime;
	
	/**
	 * 属性描述:培训人数
	 */
	@TableField(value="CLASS_PERSON_NUM",exist=true)
	java.lang.Integer classPersonNum;
	
	/**
	 * 属性描述:总课时数
	 */
	@TableField(value="TOTAL_LESSONS",exist=true)
	java.lang.Integer totalLessons;
	
	/**
	 * 属性描述:已完成人数
	 */
	@TableField(value="FINISHED_PERSON_NUM",exist=true)
	java.lang.Integer finishedPersonNum;
	
	/**
	 * 属性描述:未完成人数
	 */
	@TableField(value="NOT_FINISHED_PERSON_NUM",exist=true)
	java.lang.Integer notFinishedPersonNum;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	/**
	 * 属性描述:班级学习状态
	 */
	@TableField(value="CLASS_STUDY_STATE",exist=true)
	java.lang.String classStudyState;

	public String getClassStudyState() {
		return classStudyState;
	}

	public void setClassStudyState(String classStudyState) {
		this.classStudyState = classStudyState;
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
	
	
	public java.lang.String getOwnerMajorId(){
		return this.ownerMajorId;
	}
	
	public void setOwnerMajorId(java.lang.String ownerMajorId){
		this.ownerMajorId=ownerMajorId;
	}
	
	
	public java.lang.String getOwnerMajorName(){
		return this.ownerMajorName;
	}
	
	public void setOwnerMajorName(java.lang.String ownerMajorName){
		this.ownerMajorName=ownerMajorName;
	}
	
	
	public java.lang.String getPersonInCharge(){
		return this.personInCharge;
	}
	
	public void setPersonInCharge(java.lang.String personInCharge){
		this.personInCharge=personInCharge;
	}
	
	
	public java.lang.String getPersonInChargePhoneNum(){
		return this.personInChargePhoneNum;
	}
	
	public void setPersonInChargePhoneNum(java.lang.String personInChargePhoneNum){
		this.personInChargePhoneNum=personInChargePhoneNum;
	}
	
	
	public java.lang.String getClassName(){
		return this.className;
	}
	
	public void setClassName(java.lang.String className){
		this.className=className;
	}
	
	
	public java.util.Date getClassStartTime(){
		return this.classStartTime;
	}
	
	public void setClassStartTime(java.util.Date classStartTime){
		this.classStartTime=classStartTime;
	}
	
	
	public java.util.Date getClassEndTime(){
		return this.classEndTime;
	}
	
	public void setClassEndTime(java.util.Date classEndTime){
		this.classEndTime=classEndTime;
	}
	
	
	public java.lang.Integer getClassPersonNum(){
		return this.classPersonNum;
	}
	
	public void setClassPersonNum(java.lang.Integer classPersonNum){
		this.classPersonNum=classPersonNum;
	}
	
	
	public java.lang.Integer getTotalLessons(){
		return this.totalLessons;
	}
	
	public void setTotalLessons(java.lang.Integer totalLessons){
		this.totalLessons=totalLessons;
	}
	
	
	public java.lang.Integer getFinishedPersonNum(){
		return this.finishedPersonNum;
	}
	
	public void setFinishedPersonNum(java.lang.Integer finishedPersonNum){
		this.finishedPersonNum=finishedPersonNum;
	}
	
	
	public java.lang.Integer getNotFinishedPersonNum(){
		return this.notFinishedPersonNum;
	}
	
	public void setNotFinishedPersonNum(java.lang.Integer notFinishedPersonNum){
		this.notFinishedPersonNum=notFinishedPersonNum;
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
			sb.append(", ownerMajorId=").append(ownerMajorId);			
			sb.append(", ownerMajorName=").append(ownerMajorName);			
			sb.append(", personInCharge=").append(personInCharge);			
			sb.append(", personInChargePhoneNum=").append(personInChargePhoneNum);			
			sb.append(", className=").append(className);			
			sb.append(", classStartTime=").append(classStartTime);			
			sb.append(", classEndTime=").append(classEndTime);			
			sb.append(", classPersonNum=").append(classPersonNum);			
			sb.append(", totalLessons=").append(totalLessons);			
			sb.append(", finishedPersonNum=").append(finishedPersonNum);			
			sb.append(", notFinishedPersonNum=").append(notFinishedPersonNum);			
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
        SocietySchoolClass other = (SocietySchoolClass) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getOwnerMajorId() == null ? other.getId() == null : this.getOwnerMajorId().equals(other.getOwnerMajorId()))		
				        		&&(this.getOwnerMajorName() == null ? other.getId() == null : this.getOwnerMajorName().equals(other.getOwnerMajorName()))		
				        		&&(this.getPersonInCharge() == null ? other.getId() == null : this.getPersonInCharge().equals(other.getPersonInCharge()))		
				        		&&(this.getPersonInChargePhoneNum() == null ? other.getId() == null : this.getPersonInChargePhoneNum().equals(other.getPersonInChargePhoneNum()))		
				        		&&(this.getClassName() == null ? other.getId() == null : this.getClassName().equals(other.getClassName()))		
				        		&&(this.getClassStartTime() == null ? other.getId() == null : this.getClassStartTime().equals(other.getClassStartTime()))		
				        		&&(this.getClassEndTime() == null ? other.getId() == null : this.getClassEndTime().equals(other.getClassEndTime()))		
				        		&&(this.getClassPersonNum() == null ? other.getId() == null : this.getClassPersonNum().equals(other.getClassPersonNum()))		
				        		&&(this.getTotalLessons() == null ? other.getId() == null : this.getTotalLessons().equals(other.getTotalLessons()))		
				        		&&(this.getFinishedPersonNum() == null ? other.getId() == null : this.getFinishedPersonNum().equals(other.getFinishedPersonNum()))		
				        		&&(this.getNotFinishedPersonNum() == null ? other.getId() == null : this.getNotFinishedPersonNum().equals(other.getNotFinishedPersonNum()))		
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
		        result = prime * result + ((getOwnerMajorId() == null) ? 0 : getOwnerMajorId().hashCode());		
		        result = prime * result + ((getOwnerMajorName() == null) ? 0 : getOwnerMajorName().hashCode());		
		        result = prime * result + ((getPersonInCharge() == null) ? 0 : getPersonInCharge().hashCode());		
		        result = prime * result + ((getPersonInChargePhoneNum() == null) ? 0 : getPersonInChargePhoneNum().hashCode());		
		        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());		
		        result = prime * result + ((getClassStartTime() == null) ? 0 : getClassStartTime().hashCode());		
		        result = prime * result + ((getClassEndTime() == null) ? 0 : getClassEndTime().hashCode());		
		        result = prime * result + ((getClassPersonNum() == null) ? 0 : getClassPersonNum().hashCode());		
		        result = prime * result + ((getTotalLessons() == null) ? 0 : getTotalLessons().hashCode());		
		        result = prime * result + ((getFinishedPersonNum() == null) ? 0 : getFinishedPersonNum().hashCode());		
		        result = prime * result + ((getNotFinishedPersonNum() == null) ? 0 : getNotFinishedPersonNum().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
