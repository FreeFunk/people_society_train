package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_course_use_globle")
public class SocietySchoolCourseUseGloble implements Serializable{
	
		
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
	 * 属性描述:学校id
	 */
	@TableField(value="SCHOOL_ID",exist=true)
	java.lang.String schoolId;
	
	/**
	 * 属性描述:课程id
	 */
	@TableField(value="COURSE_ID",exist=true)
	java.lang.String courseId;
	
	/**
	 * 属性描述:课程学校id
	 */
	@TableField(value="COURSE_SCH_ID",exist=true)
	java.lang.String courseSchId;
	
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
	
	
	public java.lang.String getSchoolId(){
		return this.schoolId;
	}
	
	public void setSchoolId(java.lang.String schoolId){
		this.schoolId=schoolId;
	}
	
	
	public java.lang.String getCourseId(){
		return this.courseId;
	}
	
	public void setCourseId(java.lang.String courseId){
		this.courseId=courseId;
	}
	
	
	public java.lang.String getCourseSchId(){
		return this.courseSchId;
	}
	
	public void setCourseSchId(java.lang.String courseSchId){
		this.courseSchId=courseSchId;
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
			sb.append(", schoolId=").append(schoolId);			
			sb.append(", courseId=").append(courseId);			
			sb.append(", courseSchId=").append(courseSchId);			
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
        SocietySchoolCourseUseGloble other = (SocietySchoolCourseUseGloble) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getSchoolId() == null ? other.getId() == null : this.getSchoolId().equals(other.getSchoolId()))		
				        		&&(this.getCourseId() == null ? other.getId() == null : this.getCourseId().equals(other.getCourseId()))		
				        		&&(this.getCourseSchId() == null ? other.getId() == null : this.getCourseSchId().equals(other.getCourseSchId()))		
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
		        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());		
		        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());		
		        result = prime * result + ((getCourseSchId() == null) ? 0 : getCourseSchId().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
