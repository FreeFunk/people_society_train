package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_course_node_question")
public class SocietySchoolCourseNodeQuestion implements Serializable{
	
		
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
	@TableField(value="OWNER_COURSE_CLS_ID",exist=true)
	java.lang.String ownerCourseClsId;
	
	/**
	 * 属性描述:所属课程分类名
	 */
	@TableField(value="OWNER_COURSE_CLS_NAME",exist=true)
	java.lang.String ownerCourseClsName;
	
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
	 * 属性描述:题目名
	 */
	@TableField(value="QUESTION_NAME",exist=true)
	java.lang.String questionName;
	
	/**
	 * 属性描述:题目类型(1:单选,2:判断)
	 */
	@TableField(value="QUESTION_TYPE",exist=true)
	java.lang.String questionType;

	/**
	 * 属性描述:题目答案
	 */
	@TableField(value="QUESTION_ANSWER",exist=true)
	java.lang.String questionAnswer;
	
	/**
	 * 属性描述:题目分数
	 */
	@TableField(value="QUESTION_SCORE",exist=true)
	java.lang.Integer questionScore;
	
	/**
	 * 属性描述:题目解析
	 */
	@TableField(value="QUESTION_ANALYSIS",exist=true)
	java.lang.String questionAnalysis;
	
	/**
	 * 属性描述:排序号
	 */
	@TableField(value="ORDER_NUM",exist=true)
	java.math.BigDecimal orderNum;

	@TableField(value="QUESTION_ANSWER_ID",exist=true)
	java.lang.String questionAnswerId;

	public String getQuestionAnswerId() {
		return questionAnswerId;
	}

	public void setQuestionAnswerId(String questionAnswerId) {
		this.questionAnswerId = questionAnswerId;
	}
	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
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
	
	
	public java.lang.String getOwnerCourseClsId(){
		return this.ownerCourseClsId;
	}
	
	public void setOwnerCourseClsId(java.lang.String ownerCourseClsId){
		this.ownerCourseClsId=ownerCourseClsId;
	}
	
	
	public java.lang.String getOwnerCourseClsName(){
		return this.ownerCourseClsName;
	}
	
	public void setOwnerCourseClsName(java.lang.String ownerCourseClsName){
		this.ownerCourseClsName=ownerCourseClsName;
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
	
	
	public java.lang.String getQuestionName(){
		return this.questionName;
	}
	
	public void setQuestionName(java.lang.String questionName){
		this.questionName=questionName;
	}
	
	
	public java.lang.String getQuestionType(){
		return this.questionType;
	}
	
	public void setQuestionType(java.lang.String questionType){
		this.questionType=questionType;
	}
	
	
	public java.lang.Integer getQuestionScore(){
		return this.questionScore;
	}
	
	public void setQuestionScore(java.lang.Integer questionScore){
		this.questionScore=questionScore;
	}
	
	
	public java.lang.String getQuestionAnalysis(){
		return this.questionAnalysis;
	}
	
	public void setQuestionAnalysis(java.lang.String questionAnalysis){
		this.questionAnalysis=questionAnalysis;
	}
	
	
	public java.math.BigDecimal getOrderNum(){
		return this.orderNum;
	}
	
	public void setOrderNum(java.math.BigDecimal orderNum){
		this.orderNum=orderNum;
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
			sb.append(", ownerCourseClsId=").append(ownerCourseClsId);			
			sb.append(", ownerCourseClsName=").append(ownerCourseClsName);			
			sb.append(", ownerCourseId=").append(ownerCourseId);			
			sb.append(", ownerCourseName=").append(ownerCourseName);			
			sb.append(", ownerNodeId=").append(ownerNodeId);			
			sb.append(", ownerNodeName=").append(ownerNodeName);			
			sb.append(", questionName=").append(questionName);			
			sb.append(", questionType=").append(questionType);			
			sb.append(", questionScore=").append(questionScore);			
			sb.append(", questionAnalysis=").append(questionAnalysis);			
			sb.append(", orderNum=").append(orderNum);			
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
        SocietySchoolCourseNodeQuestion other = (SocietySchoolCourseNodeQuestion) that;
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
				        		&&(this.getOwnerCourseClsId() == null ? other.getId() == null : this.getOwnerCourseClsId().equals(other.getOwnerCourseClsId()))		
				        		&&(this.getOwnerCourseClsName() == null ? other.getId() == null : this.getOwnerCourseClsName().equals(other.getOwnerCourseClsName()))		
				        		&&(this.getOwnerCourseId() == null ? other.getId() == null : this.getOwnerCourseId().equals(other.getOwnerCourseId()))		
				        		&&(this.getOwnerCourseName() == null ? other.getId() == null : this.getOwnerCourseName().equals(other.getOwnerCourseName()))		
				        		&&(this.getOwnerNodeId() == null ? other.getId() == null : this.getOwnerNodeId().equals(other.getOwnerNodeId()))		
				        		&&(this.getOwnerNodeName() == null ? other.getId() == null : this.getOwnerNodeName().equals(other.getOwnerNodeName()))		
				        		&&(this.getQuestionName() == null ? other.getId() == null : this.getQuestionName().equals(other.getQuestionName()))		
				        		&&(this.getQuestionType() == null ? other.getId() == null : this.getQuestionType().equals(other.getQuestionType()))		
				        		&&(this.getQuestionScore() == null ? other.getId() == null : this.getQuestionScore().equals(other.getQuestionScore()))		
				        		&&(this.getQuestionAnalysis() == null ? other.getId() == null : this.getQuestionAnalysis().equals(other.getQuestionAnalysis()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
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
		        result = prime * result + ((getOwnerCourseClsId() == null) ? 0 : getOwnerCourseClsId().hashCode());		
		        result = prime * result + ((getOwnerCourseClsName() == null) ? 0 : getOwnerCourseClsName().hashCode());		
		        result = prime * result + ((getOwnerCourseId() == null) ? 0 : getOwnerCourseId().hashCode());		
		        result = prime * result + ((getOwnerCourseName() == null) ? 0 : getOwnerCourseName().hashCode());		
		        result = prime * result + ((getOwnerNodeId() == null) ? 0 : getOwnerNodeId().hashCode());		
		        result = prime * result + ((getOwnerNodeName() == null) ? 0 : getOwnerNodeName().hashCode());		
		        result = prime * result + ((getQuestionName() == null) ? 0 : getQuestionName().hashCode());		
		        result = prime * result + ((getQuestionType() == null) ? 0 : getQuestionType().hashCode());		
		        result = prime * result + ((getQuestionScore() == null) ? 0 : getQuestionScore().hashCode());		
		        result = prime * result + ((getQuestionAnalysis() == null) ? 0 : getQuestionAnalysis().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		;
        return result;
    }

}
