package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_certificate")
public class SocietyStudentCertificate implements Serializable{
	
		
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
	 * 属性描述:班级ID
	 */
	@TableField(value="CLASS_ID",exist=true)
	java.lang.String classId;
	
	/**
	 * 属性描述:班级名
	 */
	@TableField(value="CLASS_NAME",exist=true)
	java.lang.String className;

	/**
	 * 属性描述:课程ID
	 */
	@TableField(value="COURSE_ID",exist=true)
	java.lang.String courseId;

	/**
	 * 属性描述:课程名
	 */
	@TableField(value="COURSE_NAME",exist=true)
	java.lang.String courseName;


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
	 * 属性描述:身份证号
	 */
	@TableField(value="STUDENT_ID_CARD_NUM",exist=true)
	java.lang.String studentIdCardNum;
	
	/**
	 * 属性描述:关联学习记录ID
	 */
	@TableField(value="CLASS_AND_STUDENT_ID",exist=true)
	java.lang.String classAndStudentId;
	
	/**
	 * 属性描述:证书编号
	 */
	@TableField(value="CERTIFICATE_CODE",exist=true)
	java.lang.String certificateCode;
	
	/**
	 * 属性描述:证书名称
	 */
	@TableField(value="CERTIFICATE_NAME",exist=true)
	java.lang.String certificateName;
	
	/**
	 * 属性描述:证书图片
	 */
	@TableField(value="CERTIFICATE_IMAGE_URL",exist=true)
	java.lang.String certificateImageUrl;
	
	/**
	 * 属性描述:发证时间
	 */
	@TableField(value="CERTIFICATE_TIME",exist=true)
	java.util.Date certificateTime;
	
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

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	
	
	public java.lang.String getClassId(){
		return this.classId;
	}
	
	public void setClassId(java.lang.String classId){
		this.classId=classId;
	}
	
	
	public java.lang.String getClassName(){
		return this.className;
	}
	
	public void setClassName(java.lang.String className){
		this.className=className;
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
	
	
	public java.lang.String getStudentIdCardNum(){
		return this.studentIdCardNum;
	}
	
	public void setStudentIdCardNum(java.lang.String studentIdCardNum){
		this.studentIdCardNum=studentIdCardNum;
	}
	
	
	public java.lang.String getClassAndStudentId(){
		return this.classAndStudentId;
	}
	
	public void setClassAndStudentId(java.lang.String classAndStudentId){
		this.classAndStudentId=classAndStudentId;
	}
	
	
	public java.lang.String getCertificateCode(){
		return this.certificateCode;
	}
	
	public void setCertificateCode(java.lang.String certificateCode){
		this.certificateCode=certificateCode;
	}
	
	
	public java.lang.String getCertificateName(){
		return this.certificateName;
	}
	
	public void setCertificateName(java.lang.String certificateName){
		this.certificateName=certificateName;
	}
	
	
	public java.lang.String getCertificateImageUrl(){
		return this.certificateImageUrl;
	}
	
	public void setCertificateImageUrl(java.lang.String certificateImageUrl){
		this.certificateImageUrl=certificateImageUrl;
	}
	
	
	public java.util.Date getCertificateTime(){
		return this.certificateTime;
	}
	
	public void setCertificateTime(java.util.Date certificateTime){
		this.certificateTime=certificateTime;
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
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", ownerSchoolName=").append(ownerSchoolName);			
			sb.append(", ownerMajorId=").append(ownerMajorId);			
			sb.append(", ownerMajorName=").append(ownerMajorName);			
			sb.append(", classId=").append(classId);			
			sb.append(", className=").append(className);			
			sb.append(", studentId=").append(studentId);			
			sb.append(", studentName=").append(studentName);			
			sb.append(", studentIdCardNum=").append(studentIdCardNum);			
			sb.append(", classAndStudentId=").append(classAndStudentId);			
			sb.append(", certificateCode=").append(certificateCode);			
			sb.append(", certificateName=").append(certificateName);			
			sb.append(", certificateImageUrl=").append(certificateImageUrl);			
			sb.append(", certificateTime=").append(certificateTime);			
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
        SocietyStudentCertificate other = (SocietyStudentCertificate) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getOwnerMajorId() == null ? other.getId() == null : this.getOwnerMajorId().equals(other.getOwnerMajorId()))		
				        		&&(this.getOwnerMajorName() == null ? other.getId() == null : this.getOwnerMajorName().equals(other.getOwnerMajorName()))		
				        		&&(this.getClassId() == null ? other.getId() == null : this.getClassId().equals(other.getClassId()))		
				        		&&(this.getClassName() == null ? other.getId() == null : this.getClassName().equals(other.getClassName()))		
				        		&&(this.getStudentId() == null ? other.getId() == null : this.getStudentId().equals(other.getStudentId()))		
				        		&&(this.getStudentName() == null ? other.getId() == null : this.getStudentName().equals(other.getStudentName()))		
				        		&&(this.getStudentIdCardNum() == null ? other.getId() == null : this.getStudentIdCardNum().equals(other.getStudentIdCardNum()))		
				        		&&(this.getClassAndStudentId() == null ? other.getId() == null : this.getClassAndStudentId().equals(other.getClassAndStudentId()))		
				        		&&(this.getCertificateCode() == null ? other.getId() == null : this.getCertificateCode().equals(other.getCertificateCode()))		
				        		&&(this.getCertificateName() == null ? other.getId() == null : this.getCertificateName().equals(other.getCertificateName()))		
				        		&&(this.getCertificateImageUrl() == null ? other.getId() == null : this.getCertificateImageUrl().equals(other.getCertificateImageUrl()))		
				        		&&(this.getCertificateTime() == null ? other.getId() == null : this.getCertificateTime().equals(other.getCertificateTime()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getOwnerSchoolName() == null) ? 0 : getOwnerSchoolName().hashCode());		
		        result = prime * result + ((getOwnerMajorId() == null) ? 0 : getOwnerMajorId().hashCode());		
		        result = prime * result + ((getOwnerMajorName() == null) ? 0 : getOwnerMajorName().hashCode());		
		        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());		
		        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());		
		        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());		
		        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());		
		        result = prime * result + ((getStudentIdCardNum() == null) ? 0 : getStudentIdCardNum().hashCode());		
		        result = prime * result + ((getClassAndStudentId() == null) ? 0 : getClassAndStudentId().hashCode());		
		        result = prime * result + ((getCertificateCode() == null) ? 0 : getCertificateCode().hashCode());		
		        result = prime * result + ((getCertificateName() == null) ? 0 : getCertificateName().hashCode());		
		        result = prime * result + ((getCertificateImageUrl() == null) ? 0 : getCertificateImageUrl().hashCode());		
		        result = prime * result + ((getCertificateTime() == null) ? 0 : getCertificateTime().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
