package com.edgedo.society.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_class_and_student")
public class SocietySchoolClassAndStudent implements Serializable{

	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;

	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;

	/**
	 * 属性描述:所属学校ID
	 */
	@TableField(value="OWNER_SCHOOL_ID",exist=true)
	java.lang.String ownerSchoolId;

	/**
	 * 属性描述:所属学校NAME
	 */
	@TableField(value="OWNER_SCHOOL_NAME",exist=true)
	java.lang.String ownerSchoolName;

	/**
	 * 属性描述:专业ID
	 */
	@TableField(value="OWNER_MAJOR_ID",exist=true)
	java.lang.String ownerMajorId;

	/**
	 * 属性描述:专业NAME
	 */
	@TableField(value="OWNER_MAJOR_NAME",exist=true)
	java.lang.String ownerMajorName;
	
	/**
	 * 属性描述:班级ID
	 */
	@TableField(value="CLASS_ID",exist=true)
	java.lang.String classId;

	/**
	 * 属性描述:班级名字
	 */
	@TableField(value="CLASS_NAME",exist=true)
	java.lang.String className;

	/**
	 * 属性描述:学员ID
	 */
	@TableField(value="STUDENT_ID",exist=true)
	java.lang.String studentId;

	/**
	 * 属性描述:学员NAME
	 */
	@TableField(value="STUDENT_NAME",exist=true)
	java.lang.String studentName;

	/**
	 * 属性描述:学员身份证号
	 */
	@TableField(value="STUDENT_ID_CARD_NUM",exist=true)
	java.lang.String studentIdCardNum;

	/**
	 * 属性描述:开始学习时间
	 */
	@TableField(value="STUDY_START_TIME",exist=true)
	java.util.Date studyStartTime;

	/**
	 * 属性描述:结束学习时间
	 */
	@TableField(value="STUDY_END_TIME",exist=true)
	java.util.Date studyEndTime;

	/**
	 * 属性描述:学员学习进度
	 */
	@TableField(value="STUDENT_LERAN_PROGRESS",exist=true)
	java.math.BigDecimal studentLeranProgress;
	
	/**
	 * 属性描述:小节总数
	 */
	@TableField(value="TOTAL_NODE_NUM",exist=true)
	java.lang.Integer totalNodeNum;
	
	/**
	 * 属性描述:完成小节数
	 */
	@TableField(value="FINISHED_NODE_NUM",exist=true)
	java.lang.Integer finishedNodeNum;

	/**
	 * 属性描述:学习是否完成
	 */
	@TableField(value="LEARN_IS_FINISHED",exist=true)
	java.lang.String learnIsFinished;

	/**
	 * 属性描述:数据状态
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDataState() {
		return dataState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOwnerSchoolName() {
		return ownerSchoolName;
	}

	public void setOwnerSchoolName(String ownerSchoolName) {
		this.ownerSchoolName = ownerSchoolName;
	}

	public String getOwnerMajorId() {
		return ownerMajorId;
	}

	public void setOwnerMajorId(String ownerMajorId) {
		this.ownerMajorId = ownerMajorId;
	}

	public String getOwnerMajorName() {
		return ownerMajorName;
	}

	public void setOwnerMajorName(String ownerMajorName) {
		this.ownerMajorName = ownerMajorName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentIdCardNum() {
		return studentIdCardNum;
	}

	public void setStudentIdCardNum(String studentIdCardNum) {
		this.studentIdCardNum = studentIdCardNum;
	}

	public Date getStudyStartTime() {
		return studyStartTime;
	}

	public void setStudyStartTime(Date studyStartTime) {
		this.studyStartTime = studyStartTime;
	}

	public Date getStudyEndTime() {
		return studyEndTime;
	}

	public void setStudyEndTime(Date studyEndTime) {
		this.studyEndTime = studyEndTime;
	}

	public String getLearnIsFinished() {
		return learnIsFinished;
	}

	public void setLearnIsFinished(String learnIsFinished) {
		this.learnIsFinished = learnIsFinished;
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
	
	
	public java.lang.String getStudentId(){
		return this.studentId;
	}
	
	public void setStudentId(java.lang.String studentId){
		this.studentId=studentId;
	}
	
	
	public java.math.BigDecimal getStudentLeranProgress(){
		return this.studentLeranProgress;
	}
	
	public void setStudentLeranProgress(java.math.BigDecimal studentLeranProgress){
		this.studentLeranProgress=studentLeranProgress;
	}
	
	
	public java.lang.Integer getTotalNodeNum(){
		return this.totalNodeNum;
	}
	
	public void setTotalNodeNum(java.lang.Integer totalNodeNum){
		this.totalNodeNum=totalNodeNum;
	}
	
	
	public java.lang.Integer getFinishedNodeNum(){
		return this.finishedNodeNum;
	}
	
	public void setFinishedNodeNum(java.lang.Integer finishedNodeNum){
		this.finishedNodeNum=finishedNodeNum;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", classId=").append(classId);			
			sb.append(", studentId=").append(studentId);			
			sb.append(", studentLeranProgress=").append(studentLeranProgress);			
			sb.append(", totalNodeNum=").append(totalNodeNum);			
			sb.append(", finishedNodeNum=").append(finishedNodeNum);			
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
        SocietySchoolClassAndStudent other = (SocietySchoolClassAndStudent) that;
        boolean flag = true;
        return  flag;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());		
		        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());		
		        result = prime * result + ((getStudentLeranProgress() == null) ? 0 : getStudentLeranProgress().hashCode());		
		        result = prime * result + ((getTotalNodeNum() == null) ? 0 : getTotalNodeNum().hashCode());		
		        result = prime * result + ((getFinishedNodeNum() == null) ? 0 : getFinishedNodeNum().hashCode());		
		;
        return result;
    }

}
