package com.edgedo.society.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_test_paper")
public class SocietyStudentTestPaper implements Serializable{
	
		
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
	 * 属性描述:所属试卷ID
	 */
	@TableField(value="OWNER_TEST_PAPER_ID",exist=true)
	java.lang.String ownerTestPaperId;
	
	/**
	 * 属性描述:试卷名称
	 */
	@TableField(value="TEST_PAPER_NAME",exist=true)
	java.lang.String testPaperName;
	
	/**
	 * 属性描述:testPaperTimeLength
	 */
	@TableField(value="TEST_PAPER_TIME_LENGTH",exist=true)
	java.lang.Integer testPaperTimeLength;
	
	/**
	 * 属性描述:题目数
	 */
	@TableField(value="TOTAL_QUESTION_NUM",exist=true)
	java.lang.Integer totalQuestionNum;
	
	/**
	 * 属性描述:总分数
	 */
	@TableField(value="TOTAL_SCORE",exist=true)
	java.lang.Integer totalScore;
	
	/**
	 * 属性描述:及格分数
	 */
	@TableField(value="PASS_SCORE",exist=true)
	java.lang.Integer passScore;
	
	/**
	 * 属性描述:单选得分
	 */
	@TableField(value="OPTION_SCORE",exist=true)
	java.lang.Integer optionScore;
	
	/**
	 * 属性描述:判断得分
	 */
	@TableField(value="JUDGE_SCORE",exist=true)
	java.lang.Integer judgeScore;
	
	/**
	 * 属性描述:试卷得分
	 */
	@TableField(value="GET_SCORE",exist=true)
	java.lang.Integer getScore;
	
	/**
	 * 属性描述:是否及格
	 */
	@TableField(value="IS_PASS",exist=true)
	java.lang.String isPass;
	
	/**
	 * 属性描述:是否完成答题（1：是，0：否）
	 */
	@TableField(value="IS_FINISHED",exist=true)
	java.lang.String isFinished;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	/**
	 * 属性描述:学生姓名
	 */
	@TableField(value="STUDENT_NAME",exist=true)
	java.lang.String studentName;

	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="STUDENT_ID_CARD_NUM",exist=true)
	java.lang.String studentIdCardNum;


	/**
	 * 属性描述:单选答对个数
	 */
	@TableField(value="OPTION_RIGHT_NUM",exist=true)
	java.lang.String optionRightNum;

	/**
	 * 属性描述:判断答对的个数
	 */
	@TableField(value="JUDGE_RIGHT_NUM",exist=true)
	java.lang.String judgeRightNum;

	/**
	 * 属性描述:答题的正确率
	 */
	@TableField(value="TEST_RIGHT_RATE",exist=true)
	BigDecimal testRightRate;

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

	public String getOptionRightNum() {
		return optionRightNum;
	}

	public void setOptionRightNum(String optionRightNum) {
		this.optionRightNum = optionRightNum;
	}

	public String getJudgeRightNum() {
		return judgeRightNum;
	}

	public void setJudgeRightNum(String judgeRightNum) {
		this.judgeRightNum = judgeRightNum;
	}

	public BigDecimal getTestRightRate() {
		return testRightRate;
	}

	public void setTestRightRate(BigDecimal testRightRate) {
		this.testRightRate = testRightRate;
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
	
	
	public java.lang.String getOwnerTestPaperId(){
		return this.ownerTestPaperId;
	}
	
	public void setOwnerTestPaperId(java.lang.String ownerTestPaperId){
		this.ownerTestPaperId=ownerTestPaperId;
	}
	
	
	public java.lang.String getTestPaperName(){
		return this.testPaperName;
	}
	
	public void setTestPaperName(java.lang.String testPaperName){
		this.testPaperName=testPaperName;
	}
	
	
	public java.lang.Integer getTestPaperTimeLength(){
		return this.testPaperTimeLength;
	}
	
	public void setTestPaperTimeLength(java.lang.Integer testPaperTimeLength){
		this.testPaperTimeLength=testPaperTimeLength;
	}
	
	
	public java.lang.Integer getTotalQuestionNum(){
		return this.totalQuestionNum;
	}
	
	public void setTotalQuestionNum(java.lang.Integer totalQuestionNum){
		this.totalQuestionNum=totalQuestionNum;
	}
	
	
	public java.lang.Integer getTotalScore(){
		return this.totalScore;
	}
	
	public void setTotalScore(java.lang.Integer totalScore){
		this.totalScore=totalScore;
	}
	
	
	public java.lang.Integer getPassScore(){
		return this.passScore;
	}
	
	public void setPassScore(java.lang.Integer passScore){
		this.passScore=passScore;
	}
	
	
	public java.lang.Integer getOptionScore(){
		return this.optionScore;
	}
	
	public void setOptionScore(java.lang.Integer optionScore){
		this.optionScore=optionScore;
	}
	
	
	public java.lang.Integer getJudgeScore(){
		return this.judgeScore;
	}
	
	public void setJudgeScore(java.lang.Integer judgeScore){
		this.judgeScore=judgeScore;
	}
	
	
	public java.lang.Integer getGetScore(){
		return this.getScore;
	}
	
	public void setGetScore(java.lang.Integer getScore){
		this.getScore=getScore;
	}
	
	
	public java.lang.String getIsPass(){
		return this.isPass;
	}
	
	public void setIsPass(java.lang.String isPass){
		this.isPass=isPass;
	}
	
	
	public java.lang.String getIsFinished(){
		return this.isFinished;
	}
	
	public void setIsFinished(java.lang.String isFinished){
		this.isFinished=isFinished;
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
			sb.append(", studentId=").append(studentId);			
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", ownerSchoolName=").append(ownerSchoolName);			
			sb.append(", ownerCourseId=").append(ownerCourseId);			
			sb.append(", ownerCourseName=").append(ownerCourseName);			
			sb.append(", ownerTestPaperId=").append(ownerTestPaperId);			
			sb.append(", testPaperName=").append(testPaperName);			
			sb.append(", testPaperTimeLength=").append(testPaperTimeLength);			
			sb.append(", totalQuestionNum=").append(totalQuestionNum);			
			sb.append(", totalScore=").append(totalScore);			
			sb.append(", passScore=").append(passScore);			
			sb.append(", optionScore=").append(optionScore);			
			sb.append(", judgeScore=").append(judgeScore);			
			sb.append(", getScore=").append(getScore);			
			sb.append(", isPass=").append(isPass);			
			sb.append(", isFinished=").append(isFinished);			
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
        SocietyStudentTestPaper other = (SocietyStudentTestPaper) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getStudentId() == null ? other.getId() == null : this.getStudentId().equals(other.getStudentId()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getOwnerCourseId() == null ? other.getId() == null : this.getOwnerCourseId().equals(other.getOwnerCourseId()))		
				        		&&(this.getOwnerCourseName() == null ? other.getId() == null : this.getOwnerCourseName().equals(other.getOwnerCourseName()))		
				        		&&(this.getOwnerTestPaperId() == null ? other.getId() == null : this.getOwnerTestPaperId().equals(other.getOwnerTestPaperId()))		
				        		&&(this.getTestPaperName() == null ? other.getId() == null : this.getTestPaperName().equals(other.getTestPaperName()))		
				        		&&(this.getTestPaperTimeLength() == null ? other.getId() == null : this.getTestPaperTimeLength().equals(other.getTestPaperTimeLength()))		
				        		&&(this.getTotalQuestionNum() == null ? other.getId() == null : this.getTotalQuestionNum().equals(other.getTotalQuestionNum()))		
				        		&&(this.getTotalScore() == null ? other.getId() == null : this.getTotalScore().equals(other.getTotalScore()))		
				        		&&(this.getPassScore() == null ? other.getId() == null : this.getPassScore().equals(other.getPassScore()))		
				        		&&(this.getOptionScore() == null ? other.getId() == null : this.getOptionScore().equals(other.getOptionScore()))		
				        		&&(this.getJudgeScore() == null ? other.getId() == null : this.getJudgeScore().equals(other.getJudgeScore()))		
				        		&&(this.getGetScore() == null ? other.getId() == null : this.getGetScore().equals(other.getGetScore()))		
				        		&&(this.getIsPass() == null ? other.getId() == null : this.getIsPass().equals(other.getIsPass()))		
				        		&&(this.getIsFinished() == null ? other.getId() == null : this.getIsFinished().equals(other.getIsFinished()))		
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
		        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getOwnerSchoolName() == null) ? 0 : getOwnerSchoolName().hashCode());		
		        result = prime * result + ((getOwnerCourseId() == null) ? 0 : getOwnerCourseId().hashCode());		
		        result = prime * result + ((getOwnerCourseName() == null) ? 0 : getOwnerCourseName().hashCode());		
		        result = prime * result + ((getOwnerTestPaperId() == null) ? 0 : getOwnerTestPaperId().hashCode());		
		        result = prime * result + ((getTestPaperName() == null) ? 0 : getTestPaperName().hashCode());		
		        result = prime * result + ((getTestPaperTimeLength() == null) ? 0 : getTestPaperTimeLength().hashCode());		
		        result = prime * result + ((getTotalQuestionNum() == null) ? 0 : getTotalQuestionNum().hashCode());		
		        result = prime * result + ((getTotalScore() == null) ? 0 : getTotalScore().hashCode());		
		        result = prime * result + ((getPassScore() == null) ? 0 : getPassScore().hashCode());		
		        result = prime * result + ((getOptionScore() == null) ? 0 : getOptionScore().hashCode());		
		        result = prime * result + ((getJudgeScore() == null) ? 0 : getJudgeScore().hashCode());		
		        result = prime * result + ((getGetScore() == null) ? 0 : getGetScore().hashCode());		
		        result = prime * result + ((getIsPass() == null) ? 0 : getIsPass().hashCode());		
		        result = prime * result + ((getIsFinished() == null) ? 0 : getIsFinished().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
