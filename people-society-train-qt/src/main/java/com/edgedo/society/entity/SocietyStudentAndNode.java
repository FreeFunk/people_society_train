package com.edgedo.society.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("society_student_and_node")
public class SocietyStudentAndNode implements Serializable{
	/**
	 * @Author WangZhen
	 * @Description 主键
	 * @Date 2020/5/4 11:48
	 **/
	@TableId(value="ID",type= IdType.INPUT)
	private String id;
	/**
	 * @Author WangZhen
	 * @Description 创建时间
	 * @Date 2020/7/18 15:31
	 **/
	@TableField(value="CREATE_TIME",exist=true)
	private Date createTime;
	/**
	 * @Author WangZhen
	 * @Description 父表 学生课程关联表
	 * @Date 2020/5/4 11:48
	 **/
	@TableField(value="OWNER_STUDENT_AND_COURSE_ID",exist=true)
	private String ownerStudentAndCourseId;

	/**
	 * 属性描述:所属学校ID
	 */
	@TableField(value="OWNER_SCHOOL_ID",exist=true)
	java.lang.String ownerSchoolId;
	
	/**
	 * 属性描述:学员ID
	 */
	@TableField(value="STUDENT_ID",exist=true)
	java.lang.String studentId;
	/**
	 * @Author WangZhen
	 * @Description 学员姓名
	 * @Date 2020/5/6 18:51
	 **/
	@TableField(value="STUDENT_NAME",exist=true)
	java.lang.String studentName;
	/**
	 * @Author WangZhen
	 * @Description 学生身份证号
	 * @Date 2020/5/6 18:51
	 **/
	@TableField(value="STUDENT_ID_CARD_NUM",exist=true)
	java.lang.String studentIdCardNum;

	/**
	 * 属性描述:课程小节ID
	 */
	@TableField(value="NODE_ID",exist=true)
	java.lang.String nodeId;
	/**
	 * 属性描述:所属课程名
	 */
	@TableField(value="NODE_NAME",exist=true)
	java.lang.String nodeName;
	/**
	 * 属性描述:所属课程id
	 */
	@TableField(value="OWNER_COURSE_ID",exist=true)
	java.lang.String ownerCourseId;
	/**
	 * 属性描述:所属课程名
	 */
	@TableField(value="OWNER_COURSE_NAME",exist=true)
	java.lang.String ownerCourseName;

	/**
	 * 属性描述:课程小节进度
	 */
	@TableField(value="NODE_PROGRESS",exist=true)
	java.math.BigDecimal nodeProgress;

	/**
	 * @Author WangZhen
	 * @Description 学习时长
	 * @Date 2020/5/6 20:05
	 **/
	@TableField(value="STUDY_TIME_LENGTH",exist=true)
	Integer studyTimeLength;
	
	/**
	 * 属性描述:上次学习位置(秒)
	 */
	@TableField(value="LAST_LEARN_LOCATION",exist=true)
	java.lang.Integer lastLearnLocation;
	/**
	 * 属性描述:最大学习位置(秒)
	 */
	@TableField(value="MAX_LEARN_LOCATION",exist=true)
	java.lang.Integer maxLearnLocation;
	/**
	 * 属性描述:学习是否完成(1:是,0:否)
	 */
	@TableField(value="LEARN_IS_FINISHED",exist=true)
	java.lang.String learnIsFinished;
	
	/**
	 * 属性描述:上次答题ID
	 */
	@TableField(value="LAST_QUESTION_ID",exist=true)
	java.lang.String lastQuestionId;
	
	/**
	 * 属性描述:小节习题得分
	 */
	@TableField(value="NODE_QUESTION_SCORE",exist=true)
	java.lang.Integer nodeQuestionScore;
	
	/**
	 * 属性描述:习题是否完成(1:是,0:否)
	 */
	@TableField(value="QUESTION_IS_FINISHED",exist=true)
	java.lang.String questionIsFinished;
	
	/**
	 * 属性描述:习题是否合格(1:是,0:否)
	 */
	@TableField(value="QUESTION_IS_PASS",exist=true)
	java.lang.String questionIsPass;
	/**
	 * @Author WangZhen
	 * @Description 上次更新时间
	 * @Date 2020/5/7 9:10
	 **/
	@TableField(value="LAST_UP_TIME",exist=true)
	Date lastUpTime;
	/**
	 * @Author WangZhen
	 * @Description 学习结束时间
	 * @Date 2020/5/7 9:10
	 **/
	@TableField(value="FINISH_TIME",exist=true)
	Date finishTime;
	/**
	 * @Author WangZhen
	 * @Description 数据状态
	 * @Date 2020/7/18 15:30
	 **/
	@TableField(value="DATA_STATE",exist=true)
	private String dataState;

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

	public Integer getMaxLearnLocation() {
		return maxLearnLocation;
	}

	public void setMaxLearnLocation(Integer maxLearnLocation) {
		this.maxLearnLocation = maxLearnLocation;
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
	
	
	public java.lang.String getNodeId(){
		return this.nodeId;
	}
	
	public void setNodeId(java.lang.String nodeId){
		this.nodeId=nodeId;
	}
	
	
	public java.math.BigDecimal getNodeProgress(){
		return this.nodeProgress;
	}
	
	public void setNodeProgress(java.math.BigDecimal nodeProgress){
		this.nodeProgress=nodeProgress;
	}
	
	
	public java.lang.Integer getLastLearnLocation(){
		return this.lastLearnLocation;
	}
	
	public void setLastLearnLocation(java.lang.Integer lastLearnLocation){
		this.lastLearnLocation=lastLearnLocation;
	}
	
	
	public java.lang.String getLearnIsFinished(){
		return this.learnIsFinished;
	}
	
	public void setLearnIsFinished(java.lang.String learnIsFinished){
		this.learnIsFinished=learnIsFinished;
	}
	
	
	public java.lang.String getLastQuestionId(){
		return this.lastQuestionId;
	}
	
	public void setLastQuestionId(java.lang.String lastQuestionId){
		this.lastQuestionId=lastQuestionId;
	}
	
	
	public java.lang.Integer getNodeQuestionScore(){
		return this.nodeQuestionScore;
	}
	
	public void setNodeQuestionScore(java.lang.Integer nodeQuestionScore){
		this.nodeQuestionScore=nodeQuestionScore;
	}
	
	
	public java.lang.String getQuestionIsFinished(){
		return this.questionIsFinished;
	}
	
	public void setQuestionIsFinished(java.lang.String questionIsFinished){
		this.questionIsFinished=questionIsFinished;
	}
	
	
	public java.lang.String getQuestionIsPass(){
		return this.questionIsPass;
	}
	
	public void setQuestionIsPass(java.lang.String questionIsPass){
		this.questionIsPass=questionIsPass;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerStudentAndCourseId() {
		return ownerStudentAndCourseId;
	}

	public void setOwnerStudentAndCourseId(String ownerStudentAndCourseId) {
		this.ownerStudentAndCourseId = ownerStudentAndCourseId;
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

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getOwnerCourseId() {
		return ownerCourseId;
	}

	public void setOwnerCourseId(String ownerCourseId) {
		this.ownerCourseId = ownerCourseId;
	}

	public String getOwnerCourseName() {
		return ownerCourseName;
	}

	public void setOwnerCourseName(String ownerCourseName) {
		this.ownerCourseName = ownerCourseName;
	}

	public Integer getStudyTimeLength() {
		return studyTimeLength;
	}

	public void setStudyTimeLength(Integer studyTimeLength) {
		this.studyTimeLength = studyTimeLength;
	}

	public Date getLastUpTime() {
		return lastUpTime;
	}

	public void setLastUpTime(Date lastUpTime) {
		this.lastUpTime = lastUpTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public String toString() {
		return "SocietyStudentAndNode{" +
				"id='" + id + '\'' +
				", ownerStudentAndCourseId='" + ownerStudentAndCourseId + '\'' +
				", ownerSchoolId='" + ownerSchoolId + '\'' +
				", studentId='" + studentId + '\'' +
				", studentName='" + studentName + '\'' +
				", studentIdCardNum='" + studentIdCardNum + '\'' +
				", nodeId='" + nodeId + '\'' +
				", nodeName='" + nodeName + '\'' +
				", ownerCourseId='" + ownerCourseId + '\'' +
				", ownerCourseName='" + ownerCourseName + '\'' +
				", nodeProgress=" + nodeProgress +
				", studyTimeLength=" + studyTimeLength +
				", lastLearnLocation=" + lastLearnLocation +
				", learnIsFinished='" + learnIsFinished + '\'' +
				", lastQuestionId='" + lastQuestionId + '\'' +
				", nodeQuestionScore=" + nodeQuestionScore +
				", questionIsFinished='" + questionIsFinished + '\'' +
				", questionIsPass='" + questionIsPass + '\'' +
				", lastUpTime=" + lastUpTime +
				", finishTime=" + finishTime +
				'}';
	}
}
