package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_practise_quest_option")
public class SocietyStudentPractiseQuestOption implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:createTime
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:updateTime
	 */
	@TableField(value="UPDATE_TIME",exist=true)
	java.util.Date updateTime;
	
	/**
	 * 属性描述:ownerStudentAndNodeId
	 */
	@TableField(value="OWNER_STUDENT_AND_NODE_ID",exist=true)
	java.lang.String ownerStudentAndNodeId;

	/**
	 * 属性描述:所说学生课程关联表
	 */
	@TableField(value="OWNER_STU_COURSE_ID",exist=true)
	java.lang.String ownerStuCourseId;

	/**
	 * 属性描述:学员ID
	 */
	@TableField(value="STUDENT_ID",exist=true)
	java.lang.String studentId;
	
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
	 * 属性描述:所属题目ID
	 */
	@TableField(value="OWNER_STUDENT_QUERSION_ID",exist=true)
	java.lang.String ownerStudentQuersionId;
	
	/**
	 * 属性描述:所属学员习题目
	 */
	@TableField(value="OWNER_STUDENT_QUERSION_NAME",exist=true)
	java.lang.String ownerStudentQuersionName;

	/**
	 * 属性描述:选项编号
	 */
	@TableField(value="OWNER_NODE_QUE_OP_ID",exist=true)
	java.lang.String ownerNodeQueOpId;
	/**
	 * 属性描述:选项编号
	 */
	@TableField(value="OPTION_TITLE",exist=true)
	java.lang.String optionTitle;
	
	/**
	 * 属性描述:选项描述
	 */
	@TableField(value="OPTION_NAME",exist=true)
	java.lang.String optionName;
	
	/**
	 * 属性描述:是否是正确答案(1:是:0否)
	 */
	@TableField(value="IS_RIGHT",exist=true)
	java.lang.String isRight;
	
	/**
	 * 属性描述:是否选择(1:是:0否)
	 */
	@TableField(value="IS_SELECT",exist=true)
	java.lang.String isSelect;
	
	/**
	 * 属性描述:排序号
	 */
	@TableField(value="ORDER_NUM",exist=true)
	java.math.BigDecimal orderNum;
	
	/**
	 * 属性描述:lastAnswerTime
	 */
	@TableField(value="LAST_ANSWER_TIME",exist=true)
	java.util.Date lastAnswerTime;
	
	
	
	
	
	
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
	
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime=updateTime;
	}
	
	
	public java.lang.String getOwnerStudentAndNodeId(){
		return this.ownerStudentAndNodeId;
	}
	
	public void setOwnerStudentAndNodeId(java.lang.String ownerStudentAndNodeId){
		this.ownerStudentAndNodeId=ownerStudentAndNodeId;
	}
	
	
	public java.lang.String getStudentId(){
		return this.studentId;
	}
	
	public void setStudentId(java.lang.String studentId){
		this.studentId=studentId;
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
	
	
	public java.lang.String getOwnerStudentQuersionId(){
		return this.ownerStudentQuersionId;
	}
	
	public void setOwnerStudentQuersionId(java.lang.String ownerStudentQuersionId){
		this.ownerStudentQuersionId=ownerStudentQuersionId;
	}
	
	
	public java.lang.String getOwnerStudentQuersionName(){
		return this.ownerStudentQuersionName;
	}
	
	public void setOwnerStudentQuersionName(java.lang.String ownerStudentQuersionName){
		this.ownerStudentQuersionName=ownerStudentQuersionName;
	}
	
	
	public java.lang.String getOptionTitle(){
		return this.optionTitle;
	}
	
	public void setOptionTitle(java.lang.String optionTitle){
		this.optionTitle=optionTitle;
	}
	
	
	public java.lang.String getOptionName(){
		return this.optionName;
	}
	
	public void setOptionName(java.lang.String optionName){
		this.optionName=optionName;
	}
	
	
	public java.lang.String getIsRight(){
		return this.isRight;
	}
	
	public void setIsRight(java.lang.String isRight){
		this.isRight=isRight;
	}
	
	
	public java.lang.String getIsSelect(){
		return this.isSelect;
	}
	
	public void setIsSelect(java.lang.String isSelect){
		this.isSelect=isSelect;
	}
	
	
	public java.math.BigDecimal getOrderNum(){
		return this.orderNum;
	}
	
	public void setOrderNum(java.math.BigDecimal orderNum){
		this.orderNum=orderNum;
	}
	
	
	public java.util.Date getLastAnswerTime(){
		return this.lastAnswerTime;
	}
	
	public void setLastAnswerTime(java.util.Date lastAnswerTime){
		this.lastAnswerTime=lastAnswerTime;
	}


	public String getOwnerStuCourseId() {
		return ownerStuCourseId;
	}

	public void setOwnerStuCourseId(String ownerStuCourseId) {
		this.ownerStuCourseId = ownerStuCourseId;
	}

	public String getOwnerNodeQueOpId() {
		return ownerNodeQueOpId;
	}

	public void setOwnerNodeQueOpId(String ownerNodeQueOpId) {
		this.ownerNodeQueOpId = ownerNodeQueOpId;
	}

	@Override
	public String toString() {
		return "SocietyStudentPractiseQuestOption{" +
				"id='" + id + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", ownerStudentAndNodeId='" + ownerStudentAndNodeId + '\'' +
				", ownerStuCourseId='" + ownerStuCourseId + '\'' +
				", studentId='" + studentId + '\'' +
				", ownerSchoolId='" + ownerSchoolId + '\'' +
				", ownerSchoolName='" + ownerSchoolName + '\'' +
				", ownerNodeId='" + ownerNodeId + '\'' +
				", ownerNodeName='" + ownerNodeName + '\'' +
				", ownerStudentQuersionId='" + ownerStudentQuersionId + '\'' +
				", ownerStudentQuersionName='" + ownerStudentQuersionName + '\'' +
				", ownerNodeQueOpId='" + ownerNodeQueOpId + '\'' +
				", optionTitle='" + optionTitle + '\'' +
				", optionName='" + optionName + '\'' +
				", isRight='" + isRight + '\'' +
				", isSelect='" + isSelect + '\'' +
				", orderNum=" + orderNum +
				", lastAnswerTime=" + lastAnswerTime +
				'}';
	}
}
