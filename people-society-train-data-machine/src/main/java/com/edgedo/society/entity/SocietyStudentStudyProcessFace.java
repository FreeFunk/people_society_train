package com.edgedo.society.entity;

import java.io.Serializable;
import java.util.Date;

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
	 * 属性描述:学员ID
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
	 * 属性描述:随机的超时时间
	 */
	@TableField(value="TIME_OUT_SEC",exist=true)
	java.lang.Integer timeOutSec;

	/**
	 * 属性描述:人脸类型(签到/抽查)
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	/**
	 * 属性描述:省ID
	 */
	@TableField(value="PROVINCE_ID",exist=true)
	java.lang.String provinceId;

	/**
	 * 属性描述:省名
	 */
	@TableField(value="PROVINCE_NAME",exist=true)
	java.lang.String provinceName;

	/**
	 * 属性描述:城市ID
	 */
	@TableField(value="CITY_ID",exist=true)
	java.lang.String cityId;

	/**
	 * 属性描述:城市名
	 */
	@TableField(value="CITY_NAME",exist=true)
	java.lang.String cityName;

	/**
	 * 属性描述:县区ID
	 */
	@TableField(value="XIANQU_ID",exist=true)
	java.lang.String xianquId;

	/**
	 * 属性描述:县区名
	 */
	@TableField(value="XIANQU_NAME",exist=true)
	java.lang.String xianquName;

	/**
	 * 属性描述:操作人id
	 */
	@TableField(value="OPERATOR_ID",exist=true)
	java.lang.String operatorId;
	/**
	 * 属性描述:操作人名字
	 */
	@TableField(value="OPERATOR_NAME",exist=true)
	java.lang.String operatorName;

	/**
	 * 属性描述:操作人名字
	 */
	@TableField(value="OPERATOR_TIME",exist=true)
	java.util.Date operatorTime;

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getXianquId() {
		return xianquId;
	}

	public void setXianquId(String xianquId) {
		this.xianquId = xianquId;
	}

	public String getXianquName() {
		return xianquName;
	}

	public void setXianquName(String xianquName) {
		this.xianquName = xianquName;
	}

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
	
	
	public java.lang.Integer getTimeOutSec(){
		return this.timeOutSec;
	}
	
	public void setTimeOutSec(java.lang.Integer timeOutSec){
		this.timeOutSec=timeOutSec;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", stuCourseId=").append(stuCourseId);			
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", ownerSchoolName=").append(ownerSchoolName);			
			sb.append(", ownerCourseId=").append(ownerCourseId);			
			sb.append(", ownerCourseName=").append(ownerCourseName);			
			sb.append(", ownerNodeId=").append(ownerNodeId);			
			sb.append(", ownerNodeName=").append(ownerNodeName);			
			sb.append(", ownerStudyProcessId=").append(ownerStudyProcessId);			
			sb.append(", studentId=").append(studentId);			
			sb.append(", studentName=").append(studentName);			
			sb.append(", faceImageUrl=").append(faceImageUrl);			
			sb.append(", faceScore=").append(faceScore);			
			sb.append(", faceType=").append(faceType);			
			sb.append(", timeOutSec=").append(timeOutSec);			
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
        SocietyStudentStudyProcessFace other = (SocietyStudentStudyProcessFace) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getStuCourseId() == null ? other.getId() == null : this.getStuCourseId().equals(other.getStuCourseId()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getOwnerCourseId() == null ? other.getId() == null : this.getOwnerCourseId().equals(other.getOwnerCourseId()))		
				        		&&(this.getOwnerCourseName() == null ? other.getId() == null : this.getOwnerCourseName().equals(other.getOwnerCourseName()))		
				        		&&(this.getOwnerNodeId() == null ? other.getId() == null : this.getOwnerNodeId().equals(other.getOwnerNodeId()))		
				        		&&(this.getOwnerNodeName() == null ? other.getId() == null : this.getOwnerNodeName().equals(other.getOwnerNodeName()))		
				        		&&(this.getOwnerStudyProcessId() == null ? other.getId() == null : this.getOwnerStudyProcessId().equals(other.getOwnerStudyProcessId()))		
				        		&&(this.getStudentId() == null ? other.getId() == null : this.getStudentId().equals(other.getStudentId()))		
				        		&&(this.getStudentName() == null ? other.getId() == null : this.getStudentName().equals(other.getStudentName()))		
				        		&&(this.getFaceImageUrl() == null ? other.getId() == null : this.getFaceImageUrl().equals(other.getFaceImageUrl()))		
				        		&&(this.getFaceScore() == null ? other.getId() == null : this.getFaceScore().equals(other.getFaceScore()))		
				        		&&(this.getFaceType() == null ? other.getId() == null : this.getFaceType().equals(other.getFaceType()))		
				        		&&(this.getTimeOutSec() == null ? other.getId() == null : this.getTimeOutSec().equals(other.getTimeOutSec()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getStuCourseId() == null) ? 0 : getStuCourseId().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getOwnerSchoolName() == null) ? 0 : getOwnerSchoolName().hashCode());		
		        result = prime * result + ((getOwnerCourseId() == null) ? 0 : getOwnerCourseId().hashCode());		
		        result = prime * result + ((getOwnerCourseName() == null) ? 0 : getOwnerCourseName().hashCode());		
		        result = prime * result + ((getOwnerNodeId() == null) ? 0 : getOwnerNodeId().hashCode());		
		        result = prime * result + ((getOwnerNodeName() == null) ? 0 : getOwnerNodeName().hashCode());		
		        result = prime * result + ((getOwnerStudyProcessId() == null) ? 0 : getOwnerStudyProcessId().hashCode());		
		        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());		
		        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());		
		        result = prime * result + ((getFaceImageUrl() == null) ? 0 : getFaceImageUrl().hashCode());		
		        result = prime * result + ((getFaceScore() == null) ? 0 : getFaceScore().hashCode());		
		        result = prime * result + ((getFaceType() == null) ? 0 : getFaceType().hashCode());		
		        result = prime * result + ((getTimeOutSec() == null) ? 0 : getTimeOutSec().hashCode());		
		;
        return result;
    }

}
