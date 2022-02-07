package com.edgedo.society.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_class_and_course")
public class SocietySchoolClassAndCourse implements Serializable{

	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:所属学校ID
	 */
	@TableField(value="OWNER_SCHOOL_ID",exist=true)
	java.lang.String ownerSchoolId;
	
	/**
	 * 属性描述:班级ID
	 */
	@TableField(value="CLASS_ID",exist=true)
	java.lang.String classId;
	
	/**
	 * 属性描述:课程ID
	 */
	@TableField(value="COURSE_ID",exist=true)
	java.lang.String courseId;
	
	/**
	 * 属性描述:学习总人数
	 */
	@TableField(value="TOTAL_STUDENT_NUM",exist=true)
	java.lang.Integer totalStudentNum;
	
	/**
	 * 属性描述:完成人数
	 */
	@TableField(value="FINISHED_STUDENT_NUM",exist=true)
	java.lang.Integer finishedStudentNum;
	
	/**
	 * 属性描述:未完成人数
	 */
	@TableField(value="NOT_FINISHED_STUDENT_NUM",exist=true)
	java.lang.Integer notFinishedStudentNum;

	/**
	 * 属性描述:课时数
	 */
	@TableField(value="TOTAL_LESSONS",exist=true)
	java.lang.Integer totalLessons;

	/**
	 * 属性描述:课程时长(秒)
	 */
	@TableField(value="COURSE_TIME_LENGTH",exist=true)
	java.lang.Integer courseTimeLength;
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public Integer getTotalLessons() {
		return totalLessons;
	}

	public void setTotalLessons(Integer totalLessons) {
		this.totalLessons = totalLessons;
	}

	public Integer getCourseTimeLength() {
		return courseTimeLength;
	}

	public void setCourseTimeLength(Integer courseTimeLength) {
		this.courseTimeLength = courseTimeLength;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.lang.String getOwnerSchoolId(){
		return this.ownerSchoolId;
	}
	
	public void setOwnerSchoolId(java.lang.String ownerSchoolId){
		this.ownerSchoolId=ownerSchoolId;
	}
	
	
	public java.lang.String getClassId(){
		return this.classId;
	}
	
	public void setClassId(java.lang.String classId){
		this.classId=classId;
	}
	
	
	public java.lang.String getCourseId(){
		return this.courseId;
	}
	
	public void setCourseId(java.lang.String courseId){
		this.courseId=courseId;
	}
	
	
	public java.lang.Integer getTotalStudentNum(){
		return this.totalStudentNum;
	}
	
	public void setTotalStudentNum(java.lang.Integer totalStudentNum){
		this.totalStudentNum=totalStudentNum;
	}
	
	
	public java.lang.Integer getFinishedStudentNum(){
		return this.finishedStudentNum;
	}
	
	public void setFinishedStudentNum(java.lang.Integer finishedStudentNum){
		this.finishedStudentNum=finishedStudentNum;
	}
	
	
	public java.lang.Integer getNotFinishedStudentNum(){
		return this.notFinishedStudentNum;
	}
	
	public void setNotFinishedStudentNum(java.lang.Integer notFinishedStudentNum){
		this.notFinishedStudentNum=notFinishedStudentNum;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", classId=").append(classId);			
			sb.append(", courseId=").append(courseId);			
			sb.append(", totalStudentNum=").append(totalStudentNum);			
			sb.append(", finishedStudentNum=").append(finishedStudentNum);			
			sb.append(", notFinishedStudentNum=").append(notFinishedStudentNum);			
        sb.append("]");
        return sb.toString();
    }

   
  /*  public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SocietySchoolClassAndCourse other = (SocietySchoolClassAndCourse) that;
        boolean flag = true;
        return  flag
        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getClassId() == null ? other.getId() == null : this.getClassId().equals(other.getClassId()))		
				        		&&(this.getCourseId() == null ? other.getId() == null : this.getCourseId().equals(other.getCourseId()))		
				        		&&(this.getTotalStudentNum() == null ? other.getId() == null : this.getTotalStudentNum().equals(other.getTotalStudentNum()))		
				        		&&(this.getFinishedStudentNum() == null ? other.getId() == null : this.getFinishedStudentNum().equals(other.getFinishedStudentNum()))		
				        		&&(this.getNotFinishedStudentNum() == null ? other.getId() == null : this.getNotFinishedStudentNum().equals(other.getNotFinishedStudentNum()))		
				;
    }*/

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());		
		        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());		
		        result = prime * result + ((getTotalStudentNum() == null) ? 0 : getTotalStudentNum().hashCode());		
		        result = prime * result + ((getFinishedStudentNum() == null) ? 0 : getFinishedStudentNum().hashCode());		
		        result = prime * result + ((getNotFinishedStudentNum() == null) ? 0 : getNotFinishedStudentNum().hashCode());		
		;
        return result;
    }

}
