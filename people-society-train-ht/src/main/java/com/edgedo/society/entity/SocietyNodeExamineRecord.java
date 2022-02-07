package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_node_examine_record")
public class SocietyNodeExamineRecord implements Serializable{
	
		
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
	 * 属性描述:orgCourseFinishedTime
	 */
	@TableField(value="ORG_COURSE_FINISHED_TIME",exist=true)
	java.util.Date orgCourseFinishedTime;
	
	/**
	 * 属性描述:orgNodeFinishedTime
	 */
	@TableField(value="ORG_NODE_FINISHED_TIME",exist=true)
	java.util.Date orgNodeFinishedTime;
	
	/**
	 * 属性描述:lastNodeStudyLocation
	 */
	@TableField(value="LAST_NODE_STUDY_LOCATION",exist=true)
	java.lang.Integer lastNodeStudyLocation;
	
	/**
	 * 属性描述:nowNodeStudyId
	 */
	@TableField(value="NOW_NODE_STUDY_ID",exist=true)
	java.lang.String nowNodeStudyId;
	
	/**
	 * 属性描述:reason
	 */
	@TableField(value="REASON",exist=true)
	java.lang.String reason;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;


	/**
	 * 属性描述:学生课程关联id
	 */
	@TableField(value="OWNER_STUDENT_AND_COURSE_ID",exist=true)
	java.lang.String ownerStudentAndCourseId;

	/**
	 * 属性描述:学生章节id
	 */
	@TableField(value="OWNER_STUDENT_AND_NODE_ID",exist=true)
	java.lang.String ownerStudentAndNodeId;

	/**
	 * 属性描述:原学生章节id
	 */
	@TableField(value="ORG_NODE_STUDY_ID",exist=true)
	java.lang.String orgNodeStudyId;

	public String getOrgNodeStudyId() {
		return orgNodeStudyId;
	}

	public void setOrgNodeStudyId(String orgNodeStudyId) {
		this.orgNodeStudyId = orgNodeStudyId;
	}

	public String getOwnerStudentAndCourseId() {
		return ownerStudentAndCourseId;
	}

	public void setOwnerStudentAndCourseId(String ownerStudentAndCourseId) {
		this.ownerStudentAndCourseId = ownerStudentAndCourseId;
	}

	public String getOwnerStudentAndNodeId() {
		return ownerStudentAndNodeId;
	}

	public void setOwnerStudentAndNodeId(String ownerStudentAndNodeId) {
		this.ownerStudentAndNodeId = ownerStudentAndNodeId;
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
	
	
	public java.util.Date getOrgCourseFinishedTime(){
		return this.orgCourseFinishedTime;
	}
	
	public void setOrgCourseFinishedTime(java.util.Date orgCourseFinishedTime){
		this.orgCourseFinishedTime=orgCourseFinishedTime;
	}
	
	
	public java.util.Date getOrgNodeFinishedTime(){
		return this.orgNodeFinishedTime;
	}
	
	public void setOrgNodeFinishedTime(java.util.Date orgNodeFinishedTime){
		this.orgNodeFinishedTime=orgNodeFinishedTime;
	}
	
	
	public java.lang.Integer getLastNodeStudyLocation(){
		return this.lastNodeStudyLocation;
	}
	
	public void setLastNodeStudyLocation(java.lang.Integer lastNodeStudyLocation){
		this.lastNodeStudyLocation=lastNodeStudyLocation;
	}
	
	
	public java.lang.String getNowNodeStudyId(){
		return this.nowNodeStudyId;
	}
	
	public void setNowNodeStudyId(java.lang.String nowNodeStudyId){
		this.nowNodeStudyId=nowNodeStudyId;
	}
	
	
	public java.lang.String getReason(){
		return this.reason;
	}
	
	public void setReason(java.lang.String reason){
		this.reason=reason;
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
			sb.append(", orgCourseFinishedTime=").append(orgCourseFinishedTime);			
			sb.append(", orgNodeFinishedTime=").append(orgNodeFinishedTime);			
			sb.append(", lastNodeStudyLocation=").append(lastNodeStudyLocation);			
			sb.append(", nowNodeStudyId=").append(nowNodeStudyId);			
			sb.append(", reason=").append(reason);			
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
        SocietyNodeExamineRecord other = (SocietyNodeExamineRecord) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOrgCourseFinishedTime() == null ? other.getId() == null : this.getOrgCourseFinishedTime().equals(other.getOrgCourseFinishedTime()))		
				        		&&(this.getOrgNodeFinishedTime() == null ? other.getId() == null : this.getOrgNodeFinishedTime().equals(other.getOrgNodeFinishedTime()))		
				        		&&(this.getLastNodeStudyLocation() == null ? other.getId() == null : this.getLastNodeStudyLocation().equals(other.getLastNodeStudyLocation()))		
				        		&&(this.getNowNodeStudyId() == null ? other.getId() == null : this.getNowNodeStudyId().equals(other.getNowNodeStudyId()))		
				        		&&(this.getReason() == null ? other.getId() == null : this.getReason().equals(other.getReason()))		
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
		        result = prime * result + ((getOrgCourseFinishedTime() == null) ? 0 : getOrgCourseFinishedTime().hashCode());		
		        result = prime * result + ((getOrgNodeFinishedTime() == null) ? 0 : getOrgNodeFinishedTime().hashCode());		
		        result = prime * result + ((getLastNodeStudyLocation() == null) ? 0 : getLastNodeStudyLocation().hashCode());		
		        result = prime * result + ((getNowNodeStudyId() == null) ? 0 : getNowNodeStudyId().hashCode());		
		        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
