package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_study_process_face")
public class SocietyStudentStudyProcessFace implements Serializable{
	
		
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
	 * 属性描述:学生课程id
	 */
	@TableField(value="STU_COURSE_ID",exist=true)
	java.lang.String stuCourseId;
	
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
	 * 属性描述:所属课程ID
	 */
	@TableField(value="OWNER_COURSE_ID",exist=true)
	java.lang.String ownerCourseId;
	
	/**
	 * 属性描述:课程名称
	 */
	@TableField(value="OWNER_COURSE_NAME",exist=true)
	java.lang.String ownerCourseName;
	
	/**
	 * 属性描述:所属小节ID
	 */
	@TableField(value="OWNER_NODE_ID",exist=true)
	java.lang.String ownerNodeId;
	
	/**
	 * 属性描述:所属小节名称
	 */
	@TableField(value="OWNER_NODE_NAME",exist=true)
	java.lang.String ownerNodeName;
	
	/**
	 * 属性描述:所属学习过程ID
	 */
	@TableField(value="OWNER_STUDY_PROCESS_ID",exist=true)
	java.lang.String ownerStudyProcessId;
	
	/**
	 * 属性描述:学员ID----》现在是身份证号
	 */
	@TableField(value="STUDENT_ID",exist=true)
	java.lang.String studentId;
	
	/**
	 * 属性描述:学员姓名
	 */
	@TableField(value="STUDENT_NAME",exist=true)
	java.lang.String studentName;
	
	/**
	 * 属性描述:人脸照
	 */
	@TableField(value="FACE_IMAGE_URL",exist=true)
	java.lang.String faceImageUrl;
	
	/**
	 * 属性描述:人脸匹配度
	 */
	@TableField(value="FACE_SCORE",exist=true)
	java.math.BigDecimal faceScore;
	
	/**
	 * 属性描述:人脸类型(签到/抽查)
	 */
	@TableField(value="FACE_TYPE",exist=true)
	java.lang.String faceType;
	/**
	 * @Author WangZhen
	 * @Description 人脸超时时间
	 * @Date 2020/5/11 9:48
	 **/
	@TableField(value="TIME_OUT_SEC",exist=true)
	Integer timeOutSec;
	/**
	 * @Author WangZhen
	 * @Description 逻辑删除
	 * @Date 2020/8/6 9:26
	 **/
	@TableField(value="DATA_STATE",exist=true)
	String dataState;

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
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
	
	
	public java.lang.String getStuCourseId(){
		return this.stuCourseId;
	}
	
	public void setStuCourseId(java.lang.String stuCourseId){
		this.stuCourseId=stuCourseId;
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
	
	
	public java.lang.String getOwnerCourseId(){
		return this.ownerCourseId;
	}
	
	public void setOwnerCourseId(java.lang.String ownerCourseId){
		this.ownerCourseId=ownerCourseId;
	}
	
	
	public java.lang.String getOwnerCourseName(){
		return this.ownerCourseName;
	}
	
	public void setOwnerCourseName(java.lang.String ownerCourseName){
		this.ownerCourseName=ownerCourseName;
	}
	
	
	public java.lang.String getOwnerNodeId(){
		return this.ownerNodeId;
	}
	
	public void setOwnerNodeId(java.lang.String ownerNodeId){
		this.ownerNodeId=ownerNodeId;
	}
	
	
	public java.lang.String getOwnerNodeName(){
		return this.ownerNodeName;
	}
	
	public void setOwnerNodeName(java.lang.String ownerNodeName){
		this.ownerNodeName=ownerNodeName;
	}
	
	
	public java.lang.String getOwnerStudyProcessId(){
		return this.ownerStudyProcessId;
	}
	
	public void setOwnerStudyProcessId(java.lang.String ownerStudyProcessId){
		this.ownerStudyProcessId=ownerStudyProcessId;
	}
	
	
	public java.lang.String getStudentId(){
		return this.studentId;
	}
	
	public void setStudentId(java.lang.String studentId){
		this.studentId=studentId;
	}
	
	
	public java.lang.String getStudentName(){
		return this.studentName;
	}
	
	public void setStudentName(java.lang.String studentName){
		this.studentName=studentName;
	}
	
	
	public java.lang.String getFaceImageUrl(){
		return this.faceImageUrl;
	}
	
	public void setFaceImageUrl(java.lang.String faceImageUrl){
		this.faceImageUrl=faceImageUrl;
	}
	
	
	public java.math.BigDecimal getFaceScore(){
		return this.faceScore;
	}
	
	public void setFaceScore(java.math.BigDecimal faceScore){
		this.faceScore=faceScore;
	}
	
	
	public java.lang.String getFaceType(){
		return this.faceType;
	}
	
	public void setFaceType(java.lang.String faceType){
		this.faceType=faceType;
	}

	public Integer getTimeOutSec() {
		return timeOutSec;
	}

	public void setTimeOutSec(Integer timeOutSec) {
		this.timeOutSec = timeOutSec;
	}

	@Override
	public String toString() {
		return "SocietyStudentStudyProcessFace{" +
				"id='" + id + '\'' +
				", createTime=" + createTime +
				", stuCourseId='" + stuCourseId + '\'' +
				", ownerSchoolId='" + ownerSchoolId + '\'' +
				", ownerSchoolName='" + ownerSchoolName + '\'' +
				", ownerCourseId='" + ownerCourseId + '\'' +
				", ownerCourseName='" + ownerCourseName + '\'' +
				", ownerNodeId='" + ownerNodeId + '\'' +
				", ownerNodeName='" + ownerNodeName + '\'' +
				", ownerStudyProcessId='" + ownerStudyProcessId + '\'' +
				", studentId='" + studentId + '\'' +
				", studentName='" + studentName + '\'' +
				", faceImageUrl='" + faceImageUrl + '\'' +
				", faceScore=" + faceScore +
				", faceType='" + faceType + '\'' +
				", timeOutSec=" + timeOutSec +
				'}';
	}

}
