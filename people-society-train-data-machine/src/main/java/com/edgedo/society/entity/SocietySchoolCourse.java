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
	 * 属性描述:课程原价
	 */
	@TableField(value="COURSE_ORG_PRICE",exist=true)
	java.math.BigDecimal courseOrgPrice;
	
	/**
	 * 属性描述:课程现价
	 */
	@TableField(value="COURSE_PRICE",exist=true)
	java.math.BigDecimal coursePrice;
	
	/**
	 * 属性描述:总课时数
	 */
	@TableField(value="TOTAL_LESSONS",exist=true)
	java.lang.Integer totalLessons;
	
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
	 * 属性描述:讲师id
	 */
	@TableField(value="TEACHER_ID",exist=true)
	java.lang.String teacherId;

	/**
	 * 属性描述:讲师名字
	 */
	@TableField(value="TEACHER_NAME",exist=true)
	java.lang.String teacherName;

	/**
	 * 属性描述:普通学员每天课程学习次数
	 */
	@TableField(value="ORD_STU_COURSE_STUDY_NUM",exist=true)
	java.lang.Integer ordStuCourseStudyNum;
	/**
	 * 属性描述:重点学员每天课程学习次数
	 */
	@TableField(value="IMP_STU_COURSE_STUDY_NUM",exist=true)
	java.lang.Integer impStuCourseStudyNum;
	/**
	 * 属性描述:企业学员每天课程学习次数
	 */
	@TableField(value="COMP_STU_COURSE_STUDY_NUM",exist=true)
	java.lang.Integer compStuCourseStudyNum;

	/**
	 * 属性描述:企业学员每天课程学习次数
	 */
	@TableField(value="COURSE_STUDY_NUM",exist=true)
	java.lang.Integer courseStudyNum;

	/**
	 * 属性描述:是否需要人脸识别
	 */
	@TableField(value="COURSE_IS_NEED_FACE_CONTRAST",exist=true)
	java.lang.String courseIsNeedFaceContrast;

	public Integer getCourseStudyNum() {
		return courseStudyNum;
	}

	public void setCourseStudyNum(Integer courseStudyNum) {
		this.courseStudyNum = courseStudyNum;
	}

	public Integer getOrdStuCourseStudyNum() {
		return ordStuCourseStudyNum;
	}

	public void setOrdStuCourseStudyNum(Integer ordStuCourseStudyNum) {
		this.ordStuCourseStudyNum = ordStuCourseStudyNum;
	}

	public Integer getImpStuCourseStudyNum() {
		return impStuCourseStudyNum;
	}

	public void setImpStuCourseStudyNum(Integer impStuCourseStudyNum) {
		this.impStuCourseStudyNum = impStuCourseStudyNum;
	}

	public Integer getCompStuCourseStudyNum() {
		return compStuCourseStudyNum;
	}

	public void setCompStuCourseStudyNum(Integer compStuCourseStudyNum) {
		this.compStuCourseStudyNum = compStuCourseStudyNum;
	}

	public String getCourseIsNeedFaceContrast() {
		return courseIsNeedFaceContrast;
	}

	public void setCourseIsNeedFaceContrast(String courseIsNeedFaceContrast) {
		this.courseIsNeedFaceContrast = courseIsNeedFaceContrast;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

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
	
	
	public java.lang.Integer getTotalLessons(){
		return this.totalLessons;
	}
	
	public void setTotalLessons(java.lang.Integer totalLessons){
		this.totalLessons=totalLessons;
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
			sb.append(", courseClsId=").append(courseClsId);			
			sb.append(", courseClsName=").append(courseClsName);			
			sb.append(", courseName=").append(courseName);			
			sb.append(", courseImage=").append(courseImage);			
			sb.append(", courseDesc=").append(courseDesc);			
			sb.append(", coursePrice=").append(coursePrice);			
			sb.append(", totalLessons=").append(totalLessons);			
			sb.append(", courseTimeLength=").append(courseTimeLength);			
			sb.append(", courseScore=").append(courseScore);			
			sb.append(", totalStudentNum=").append(totalStudentNum);			
			sb.append(", finishedStudentNum=").append(finishedStudentNum);			
			sb.append(", notFinishedStudentNum=").append(notFinishedStudentNum);			
			sb.append(", orderNum=").append(orderNum);			
			sb.append(", isOpen=").append(isOpen);			
			sb.append(", shState=").append(shState);			
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
        SocietySchoolCourse other = (SocietySchoolCourse) that;
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
				        		&&(this.getCourseClsId() == null ? other.getId() == null : this.getCourseClsId().equals(other.getCourseClsId()))		
				        		&&(this.getCourseClsName() == null ? other.getId() == null : this.getCourseClsName().equals(other.getCourseClsName()))		
				        		&&(this.getCourseName() == null ? other.getId() == null : this.getCourseName().equals(other.getCourseName()))		
				        		&&(this.getCourseImage() == null ? other.getId() == null : this.getCourseImage().equals(other.getCourseImage()))		
				        		&&(this.getCourseDesc() == null ? other.getId() == null : this.getCourseDesc().equals(other.getCourseDesc()))		
				        		&&(this.getCoursePrice() == null ? other.getId() == null : this.getCoursePrice().equals(other.getCoursePrice()))		
				        		&&(this.getTotalLessons() == null ? other.getId() == null : this.getTotalLessons().equals(other.getTotalLessons()))		
				        		&&(this.getCourseTimeLength() == null ? other.getId() == null : this.getCourseTimeLength().equals(other.getCourseTimeLength()))		
				        		&&(this.getCourseScore() == null ? other.getId() == null : this.getCourseScore().equals(other.getCourseScore()))		
				        		&&(this.getTotalStudentNum() == null ? other.getId() == null : this.getTotalStudentNum().equals(other.getTotalStudentNum()))		
				        		&&(this.getFinishedStudentNum() == null ? other.getId() == null : this.getFinishedStudentNum().equals(other.getFinishedStudentNum()))		
				        		&&(this.getNotFinishedStudentNum() == null ? other.getId() == null : this.getNotFinishedStudentNum().equals(other.getNotFinishedStudentNum()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
				        		&&(this.getIsOpen() == null ? other.getId() == null : this.getIsOpen().equals(other.getIsOpen()))		
				        		&&(this.getShState() == null ? other.getId() == null : this.getShState().equals(other.getShState()))		
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
		        result = prime * result + ((getCourseClsId() == null) ? 0 : getCourseClsId().hashCode());		
		        result = prime * result + ((getCourseClsName() == null) ? 0 : getCourseClsName().hashCode());		
		        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());		
		        result = prime * result + ((getCourseImage() == null) ? 0 : getCourseImage().hashCode());		
		        result = prime * result + ((getCourseDesc() == null) ? 0 : getCourseDesc().hashCode());		
		        result = prime * result + ((getCoursePrice() == null) ? 0 : getCoursePrice().hashCode());		
		        result = prime * result + ((getTotalLessons() == null) ? 0 : getTotalLessons().hashCode());		
		        result = prime * result + ((getCourseTimeLength() == null) ? 0 : getCourseTimeLength().hashCode());		
		        result = prime * result + ((getCourseScore() == null) ? 0 : getCourseScore().hashCode());		
		        result = prime * result + ((getTotalStudentNum() == null) ? 0 : getTotalStudentNum().hashCode());		
		        result = prime * result + ((getFinishedStudentNum() == null) ? 0 : getFinishedStudentNum().hashCode());		
		        result = prime * result + ((getNotFinishedStudentNum() == null) ? 0 : getNotFinishedStudentNum().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		        result = prime * result + ((getIsOpen() == null) ? 0 : getIsOpen().hashCode());		
		        result = prime * result + ((getShState() == null) ? 0 : getShState().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
