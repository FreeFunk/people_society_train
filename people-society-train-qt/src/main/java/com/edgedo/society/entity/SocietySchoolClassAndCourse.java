package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_class_and_course")
public class SocietySchoolClassAndCourse implements Serializable{
	
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



}
