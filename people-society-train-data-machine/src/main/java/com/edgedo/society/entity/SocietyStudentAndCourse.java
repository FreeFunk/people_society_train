package com.edgedo.society.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_and_course")
public class SocietyStudentAndCourse implements Serializable{
	
		
	/**
	 * 属性描述:ID
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:所属学校ID
	 */
	@TableField(value="OWNER_SCHOOL_ID",exist=true)
	java.lang.String ownerSchoolId;

	/**
	 * 属性描述:所属专业ID
	 */
	@TableField(value="OWNER_MAJOR_ID",exist=true)
	java.lang.String ownerMajorId;

	/**
	 * 属性描述:所属专业名称
	 */
	@TableField(value="OWNER_MAJOR_NAME",exist=true)
	java.lang.String ownerMajorName;

	/**
	 * 属性描述:班级ID
	 */
	@TableField(value="CLASS_ID",exist=true)
	java.lang.String classId;

	/**
	 * 属性描述:班级名称
	 */
	@TableField(value="CLASS_NAME",exist=true)
	java.lang.String className;

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
	 * 属性描述:课程ID
	 */
	@TableField(value="COURSE_ID",exist=true)
	java.lang.String courseId;
	
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
	 * 属性描述:课程进度
	 */
	@TableField(value="COURSE_PROGRESS",exist=true)
	java.math.BigDecimal courseProgress;
	
	/**
	 * 属性描述:上次学习小节ID
	 */
	@TableField(value="LAST_LEARN_NODE_ID",exist=true)
	java.lang.String lastLearnNodeId;
	
	/**
	 * 属性描述:学习是否完成(1:是,0:否)
	 */
	@TableField(value="LEARN_IS_FINISHED",exist=true)
	java.lang.String learnIsFinished;

	@TableField(value="FINISH_NODE_NUM",exist=true)
	Integer finishNodeNum;

	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	/**
	 * 属性描述:总学时
	 */
	@TableField(value="TOTAL_LESSONS",exist=true)
	java.lang.Integer totalLessons;

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
	 * 属性描述:支付状态（1：已支付，0：未支付）
	 */
	@TableField(value="PAY_STATE",exist=true)
	java.lang.String payState;

	/**
	 * 属性描述:支付时间
	 */
	@TableField(value="PAY_TIME",exist=true)
	java.util.Date payTime;

	/**
	 * 属性描述:预支付ID
	 */
	@TableField(value="PREPAY_ID",exist=true)
	java.lang.String prepayId;

	/**
	 * 属性描述:手续费
	 */
	@TableField(value="FEE",exist=true)
	java.math.BigDecimal fee;

	/**
	 * @Author WangZhen
	 * @Description 课程完成时间
	 * @Date 2020/5/7 9:56
	 **/
	@TableField(value="LEARN_FINISH_TIME",exist=true)
	Date learnFinishTime;

	/**
	 * 属性描述:实际收款金额
	 */
	@TableField(value="REAL_GET_MONEY",exist=true)
	java.math.BigDecimal realGetMoney;

	/**
	 * 创建时间
	 **/
	@TableField(value="CREATE_TIME",exist=true)
	Date createTime;


	/**
	 * 属性描述:班级学员关联ID
	 */
	@TableField(value="OWNER_CLASS_AND_STUDENT_ID",exist=true)
	java.lang.String ownerClassAndStudentId;
	/**
	 * 属性描述:每天课程学习次数
	 */
	@TableField(value="COURSE_STUDY_NUM",exist=true)
	java.lang.Integer courseStudyNum;

	/**
	 * 属性描述:是否需要人脸识别
	 */
	@TableField(value="COURSE_IS_NEED_FACE_CONTRAST",exist=true)
	java.lang.String courseIsNeedFaceContrast;

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

	public String getCourseIsNeedFaceContrast() {
		return courseIsNeedFaceContrast;
	}

	public void setCourseIsNeedFaceContrast(String courseIsNeedFaceContrast) {
		this.courseIsNeedFaceContrast = courseIsNeedFaceContrast;
	}

	public Integer getCourseStudyNum() {
		return courseStudyNum;
	}

	public void setCourseStudyNum(Integer courseStudyNum) {
		this.courseStudyNum = courseStudyNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOwnerClassAndStudentId() {
		return ownerClassAndStudentId;
	}

	public void setOwnerClassAndStudentId(String ownerClassAndStudentId) {
		this.ownerClassAndStudentId = ownerClassAndStudentId;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}



	public Date getLearnFinishTime() {
		return learnFinishTime;
	}

	public void setLearnFinishTime(Date learnFinishTime) {
		this.learnFinishTime = learnFinishTime;
	}

	public BigDecimal getCourseOrgPrice() {
		return courseOrgPrice;
	}

	public void setCourseOrgPrice(BigDecimal courseOrgPrice) {
		this.courseOrgPrice = courseOrgPrice;
	}

	public BigDecimal getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getRealGetMoney() {
		return realGetMoney;
	}

	public void setRealGetMoney(BigDecimal realGetMoney) {
		this.realGetMoney = realGetMoney;
	}

	public Integer getTotalLessons() {
		return totalLessons;
	}

	public void setTotalLessons(Integer totalLessons) {
		this.totalLessons = totalLessons;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public Integer getFinishNodeNum() {
		return finishNodeNum;
	}

	public void setFinishNodeNum(Integer finishNodeNum) {
		this.finishNodeNum = finishNodeNum;
	}

	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
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
	
	
	public java.lang.String getCourseId(){
		return this.courseId;
	}
	
	public void setCourseId(java.lang.String courseId){
		this.courseId=courseId;
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
	
	
	public java.math.BigDecimal getCourseProgress(){
		return this.courseProgress;
	}
	
	public void setCourseProgress(java.math.BigDecimal courseProgress){
		this.courseProgress=courseProgress;
	}
	
	
	public java.lang.String getLastLearnNodeId(){
		return this.lastLearnNodeId;
	}
	
	public void setLastLearnNodeId(java.lang.String lastLearnNodeId){
		this.lastLearnNodeId=lastLearnNodeId;
	}
	
	
	public java.lang.String getLearnIsFinished(){
		return this.learnIsFinished;
	}
	
	public void setLearnIsFinished(java.lang.String learnIsFinished){
		this.learnIsFinished=learnIsFinished;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", classId=").append(classId);			
			sb.append(", studentId=").append(studentId);			
			sb.append(", studentName=").append(studentName);			
			sb.append(", studentIdCardNum=").append(studentIdCardNum);			
			sb.append(", courseId=").append(courseId);			
			sb.append(", courseName=").append(courseName);			
			sb.append(", courseImage=").append(courseImage);			
			sb.append(", courseProgress=").append(courseProgress);			
			sb.append(", lastLearnNodeId=").append(lastLearnNodeId);			
			sb.append(", learnIsFinished=").append(learnIsFinished);			
			sb.append(", totalLessons=").append(totalLessons);
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
        SocietyStudentAndCourse other = (SocietyStudentAndCourse) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getClassId() == null ? other.getId() == null : this.getClassId().equals(other.getClassId()))		
				        		&&(this.getStudentId() == null ? other.getId() == null : this.getStudentId().equals(other.getStudentId()))		
				        		&&(this.getStudentName() == null ? other.getId() == null : this.getStudentName().equals(other.getStudentName()))		
				        		&&(this.getStudentIdCardNum() == null ? other.getId() == null : this.getStudentIdCardNum().equals(other.getStudentIdCardNum()))		
				        		&&(this.getCourseId() == null ? other.getId() == null : this.getCourseId().equals(other.getCourseId()))		
				        		&&(this.getCourseName() == null ? other.getId() == null : this.getCourseName().equals(other.getCourseName()))		
				        		&&(this.getCourseImage() == null ? other.getId() == null : this.getCourseImage().equals(other.getCourseImage()))		
				        		&&(this.getCourseProgress() == null ? other.getId() == null : this.getCourseProgress().equals(other.getCourseProgress()))		
				        		&&(this.getLastLearnNodeId() == null ? other.getId() == null : this.getLastLearnNodeId().equals(other.getLastLearnNodeId()))		
				        		&&(this.getLearnIsFinished() == null ? other.getId() == null : this.getLearnIsFinished().equals(other.getLearnIsFinished()))		
				        		&&(this.getTotalLessons() == null ? other.getId() == null : this.getTotalLessons().equals(other.getTotalLessons()))
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());		
		        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());		
		        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());		
		        result = prime * result + ((getStudentIdCardNum() == null) ? 0 : getStudentIdCardNum().hashCode());		
		        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());		
		        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());		
		        result = prime * result + ((getCourseImage() == null) ? 0 : getCourseImage().hashCode());		
		        result = prime * result + ((getCourseProgress() == null) ? 0 : getCourseProgress().hashCode());		
		        result = prime * result + ((getLastLearnNodeId() == null) ? 0 : getLastLearnNodeId().hashCode());		
		        result = prime * result + ((getLearnIsFinished() == null) ? 0 : getLearnIsFinished().hashCode());		
		        result = prime * result + ((getTotalLessons() == null) ? 0 : getTotalLessons().hashCode());
		;
        return result;
    }

}
