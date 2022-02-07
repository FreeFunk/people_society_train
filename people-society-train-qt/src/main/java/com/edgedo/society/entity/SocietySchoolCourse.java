package com.edgedo.society.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_course")
public class SocietySchoolCourse implements Serializable{
	
		
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
	 * 属性描述:所属课程分类ID
	 */
	@TableField(value="COURSE_CLS_ID",exist=true)
	java.lang.String courseClsId;
	
	/**
	 * 属性描述:所属课程分类名
	 */
	@TableField(value="COURSE_CLS_NAME",exist=true)
	java.lang.String courseClsName;
	
	/**
	 * 属性描述:课程名称
	 */
	@TableField(value="COURSE_NAME",exist=true)
	java.lang.String courseName;
	
	/**
	 * 属性描述:课程封面
	 */
	@TableField(value="COURSE_IMAGE",exist=true)
	java.lang.String courseImage;
	
	/**
	 * 属性描述:课程简介
	 */
	@TableField(value="COURSE_DESC",exist=true)
	java.lang.String courseDesc;

	/**
	 * 属性描述:课程原价格
	 */
	@TableField(value="COURSE_ORG_PRICE",exist=true)
	java.math.BigDecimal courseOrgPrice;
	
	/**
	 * 属性描述:课程价格
	 */
	@TableField(value="COURSE_PRICE",exist=true)
	java.math.BigDecimal coursePrice;
	
	/**
	 * 属性描述:课程时长(秒)
	 */
	@TableField(value="COURSE_TIME_LENGTH",exist=true)
	java.lang.Integer courseTimeLength;
	
	/**
	 * 属性描述:课程评分
	 */
	@TableField(value="COURSE_SCORE",exist=true)
	java.math.BigDecimal courseScore;
	
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
	 * 属性描述:排序号
	 */
	@TableField(value="ORDER_NUM",exist=true)
	java.math.BigDecimal orderNum;
	
	/**
	 * 属性描述:是否公开(1:是，0:否)
	 */
	@TableField(value="IS_OPEN",exist=true)
	java.lang.String isOpen;
	
	/**
	 * 属性描述:审核状态(1:通过,0:审核中,-1:不通过)
	 */
	@TableField(value="SH_STATE",exist=true)
	java.lang.String shState;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	/**
	 * @Author WangZhen
	 * @Description 课程章节总数
	 * @Date 2020/5/7 9:43
	 **/
	@TableField(value="TOTAL_LESSONS",exist=true)
	Integer totalLessons;

	public BigDecimal getCourseOrgPrice() {
		return courseOrgPrice;
	}

	public void setCourseOrgPrice(BigDecimal courseOrgPrice) {
		this.courseOrgPrice = courseOrgPrice;
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
	
	
	public java.lang.String getCourseClsId(){
		return this.courseClsId;
	}
	
	public void setCourseClsId(java.lang.String courseClsId){
		this.courseClsId=courseClsId;
	}
	
	
	public java.lang.String getCourseClsName(){
		return this.courseClsName;
	}
	
	public void setCourseClsName(java.lang.String courseClsName){
		this.courseClsName=courseClsName;
	}
	
	
	public java.lang.String getCourseName(){
		return this.courseName;
	}
	
	public void setCourseName(java.lang.String courseName){
		this.courseName=courseName;
	}
	
	
	public java.lang.String getCourseImage(){
		return this.courseImage;
	}
	
	public void setCourseImage(java.lang.String courseImage){
		this.courseImage=courseImage;
	}
	
	
	public java.lang.String getCourseDesc(){
		return this.courseDesc;
	}
	
	public void setCourseDesc(java.lang.String courseDesc){
		this.courseDesc=courseDesc;
	}
	
	
	public java.math.BigDecimal getCoursePrice(){
		return this.coursePrice;
	}
	
	public void setCoursePrice(java.math.BigDecimal coursePrice){
		this.coursePrice=coursePrice;
	}
	
	
	public java.lang.Integer getCourseTimeLength(){
		return this.courseTimeLength;
	}
	
	public void setCourseTimeLength(java.lang.Integer courseTimeLength){
		this.courseTimeLength=courseTimeLength;
	}
	
	
	public java.math.BigDecimal getCourseScore(){
		return this.courseScore;
	}
	
	public void setCourseScore(java.math.BigDecimal courseScore){
		this.courseScore=courseScore;
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
	
	
	public java.math.BigDecimal getOrderNum(){
		return this.orderNum;
	}
	
	public void setOrderNum(java.math.BigDecimal orderNum){
		this.orderNum=orderNum;
	}
	
	
	public java.lang.String getIsOpen(){
		return this.isOpen;
	}
	
	public void setIsOpen(java.lang.String isOpen){
		this.isOpen=isOpen;
	}
	
	
	public java.lang.String getShState(){
		return this.shState;
	}
	
	public void setShState(java.lang.String shState){
		this.shState=shState;
	}
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
	}

	public Integer getTotalLessons() {
		return totalLessons;
	}

	public void setTotalLessons(Integer totalLessons) {
		this.totalLessons = totalLessons;
	}

	@Override
	public String toString() {
		return "SocietySchoolCourse{" +
				"id='" + id + '\'' +
				", createTime=" + createTime +
				", createUserId='" + createUserId + '\'' +
				", createUserName='" + createUserName + '\'' +
				", ownerSchoolId='" + ownerSchoolId + '\'' +
				", ownerSchoolName='" + ownerSchoolName + '\'' +
				", ownerMajorId='" + ownerMajorId + '\'' +
				", ownerMajorName='" + ownerMajorName + '\'' +
				", courseClsId='" + courseClsId + '\'' +
				", courseClsName='" + courseClsName + '\'' +
				", courseName='" + courseName + '\'' +
				", courseImage='" + courseImage + '\'' +
				", courseDesc='" + courseDesc + '\'' +
				", coursePrice=" + coursePrice +
				", courseTimeLength=" + courseTimeLength +
				", courseScore=" + courseScore +
				", totalStudentNum=" + totalStudentNum +
				", finishedStudentNum=" + finishedStudentNum +
				", notFinishedStudentNum=" + notFinishedStudentNum +
				", orderNum=" + orderNum +
				", isOpen='" + isOpen + '\'' +
				", shState='" + shState + '\'' +
				", dataState='" + dataState + '\'' +
				", totalLessons=" + totalLessons +
				'}';
	}

}
