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
	 * 属性描述:班级ID
	 */
	@TableField(value="CLASS_ID",exist=true)
	java.lang.String classId;
	
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
	 * 属性描述:学员身份证号
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
	 * @Author WangZhen
	 * @Description 学习完成章节数
	 * @Date 2020/5/7 9:32
	 **/
	@TableField(value="FINISH_NODE_NUM",exist=true)
	Integer finishNodeNum;
	/**
	 * 属性描述:课程进度
	 */
	@TableField(value="COURSE_PROGRESS",exist=true)
	java.math.BigDecimal courseProgress;
	
	/**
	 * 属性描述:上次学习小节ID couseNodeid 不是stuCourseNodeId
	 */
	@TableField(value="LAST_LEARN_NODE_ID",exist=true)
	java.lang.String lastLearnNodeId;
	
	/**
	 * 属性描述:学习是否完成(1:是,0:否)
	 */
	@TableField(value="LEARN_IS_FINISHED",exist=true)
	java.lang.String learnIsFinished;

	/**
	 * @Author WangZhen
	 * @Description 课程完成时间
	 * @Date 2020/5/7 9:56
	 **/
	@TableField(value="LEARN_FINISH_TIME",exist=true)
	Date learnFinishTime;

	/**
	 * @Author WangZhen
	 * @Description 逻辑删除
	 * @Date 2020/5/17 11:43
	 **/
	@TableField(value="DATA_STATE",exist=true)
	String dataState;

	@TableField(value="TOTAL_LESSONS",exist=true)
	Integer totalLessons;
	/**
	 * @Author WangZhen
	 * @Description 课程单日限制学习数量
	 * @Date 2020/7/27 16:08
	 **/
	@TableField(value = "COURSE_STUDY_NUM",exist=true)
	Integer courseStudyNum;
	/**
	 * @Author WangZhen
	 * @Description 课程是否需要人脸识别
	 * @Date 2020/7/27 16:09
	 **/
	@TableField(value = "COURSE_IS_NEED_FACE_CONTRAST",exist=true)
	String courseIsNeedFaceContrast;


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
	 * 属性描述:实际收款金额
	 */
	@TableField(value="REAL_GET_MONEY",exist=true)
	java.math.BigDecimal realGetMoney;

	public Integer getCourseStudyNum() {
		return courseStudyNum;
	}

	public void setCourseStudyNum(Integer courseStudyNum) {
		this.courseStudyNum = courseStudyNum;
	}

	public String getCourseIsNeedFaceContrast() {
		return courseIsNeedFaceContrast;
	}

	public void setCourseIsNeedFaceContrast(String courseIsNeedFaceContrast) {
		this.courseIsNeedFaceContrast = courseIsNeedFaceContrast;
	}

	public Integer getTotalLessons() {
		return totalLessons;
	}

	public void setTotalLessons(Integer totalLessons) {
		this.totalLessons = totalLessons;
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

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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
	
	
	public java.lang.String getStudentId(){
		return this.studentId;
	}
	
	public void setStudentId(java.lang.String studentId){
		this.studentId=studentId;
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


	public Integer getFinishNodeNum() {
		return finishNodeNum;
	}

	public void setFinishNodeNum(Integer finishNodeNum) {
		this.finishNodeNum = finishNodeNum;
	}


	public Date getLearnFinishTime() {
		return learnFinishTime;
	}

	public void setLearnFinishTime(Date learnFinishTime) {
		this.learnFinishTime = learnFinishTime;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	@Override
	public String toString() {
		return "SocietyStudentAndCourse{" +
				"id='" + id + '\'' +
				", ownerSchoolId='" + ownerSchoolId + '\'' +
				", classId='" + classId + '\'' +
				", studentId='" + studentId + '\'' +
				", studentName='" + studentName + '\'' +
				", studentIdCardNum='" + studentIdCardNum + '\'' +
				", courseId='" + courseId + '\'' +
				", courseName='" + courseName + '\'' +
				", courseImage='" + courseImage + '\'' +
				", finishNodeNum=" + finishNodeNum +
				", courseProgress=" + courseProgress +
				", lastLearnNodeId='" + lastLearnNodeId + '\'' +
				", learnIsFinished='" + learnIsFinished + '\'' +
				", learnFinishTime=" + learnFinishTime +
				", dataState='" + dataState + '\'' +
				", totalLessons=" + totalLessons +
				", courseStudyNum=" + courseStudyNum +
				", courseIsNeedFaceContrast='" + courseIsNeedFaceContrast + '\'' +
				", courseOrgPrice=" + courseOrgPrice +
				", coursePrice=" + coursePrice +
				", payState='" + payState + '\'' +
				", payTime=" + payTime +
				", prepayId='" + prepayId + '\'' +
				", fee=" + fee +
				", realGetMoney=" + realGetMoney +
				'}';
	}
}
