package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_test_paper_question_option")
public class SocietyStudentTestPaperQuestionOption implements Serializable{
	
		
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
	 * 属性描述:所属试卷名
	 */
	@TableField(value="OWNER_TEST_PAPER_NAME",exist=true)
	java.lang.String ownerTestPaperName;
	
	/**
	 * 属性描述:所属试卷题目ID
	 */
	@TableField(value="OWNER_TEST_PAPER_QUESTION_ID",exist=true)
	java.lang.String ownerTestPaperQuestionId;
	
	/**
	 * 属性描述:选项描述
	 */
	@TableField(value="OPTION_NAME",exist=true)
	java.lang.String optionName;
	
	/**
	 * 属性描述:optionTitle
	 */
	@TableField(value="OPTION_TITLE",exist=true)
	java.lang.String optionTitle;
	
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
	
	
	public java.lang.String getOwnerTestPaperName(){
		return this.ownerTestPaperName;
	}
	
	public void setOwnerTestPaperName(java.lang.String ownerTestPaperName){
		this.ownerTestPaperName=ownerTestPaperName;
	}
	
	
	public java.lang.String getOwnerTestPaperQuestionId(){
		return this.ownerTestPaperQuestionId;
	}
	
	public void setOwnerTestPaperQuestionId(java.lang.String ownerTestPaperQuestionId){
		this.ownerTestPaperQuestionId=ownerTestPaperQuestionId;
	}
	
	
	public java.lang.String getOptionName(){
		return this.optionName;
	}
	
	public void setOptionName(java.lang.String optionName){
		this.optionName=optionName;
	}
	
	
	public java.lang.String getOptionTitle(){
		return this.optionTitle;
	}
	
	public void setOptionTitle(java.lang.String optionTitle){
		this.optionTitle=optionTitle;
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
			sb.append(", ownerTestPaperName=").append(ownerTestPaperName);			
			sb.append(", ownerTestPaperQuestionId=").append(ownerTestPaperQuestionId);			
			sb.append(", optionName=").append(optionName);			
			sb.append(", optionTitle=").append(optionTitle);			
			sb.append(", isRight=").append(isRight);			
			sb.append(", isSelect=").append(isSelect);			
			sb.append(", orderNum=").append(orderNum);			
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
        SocietyStudentTestPaperQuestionOption other = (SocietyStudentTestPaperQuestionOption) that;
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
				        		&&(this.getOwnerTestPaperName() == null ? other.getId() == null : this.getOwnerTestPaperName().equals(other.getOwnerTestPaperName()))		
				        		&&(this.getOwnerTestPaperQuestionId() == null ? other.getId() == null : this.getOwnerTestPaperQuestionId().equals(other.getOwnerTestPaperQuestionId()))		
				        		&&(this.getOptionName() == null ? other.getId() == null : this.getOptionName().equals(other.getOptionName()))		
				        		&&(this.getOptionTitle() == null ? other.getId() == null : this.getOptionTitle().equals(other.getOptionTitle()))		
				        		&&(this.getIsRight() == null ? other.getId() == null : this.getIsRight().equals(other.getIsRight()))		
				        		&&(this.getIsSelect() == null ? other.getId() == null : this.getIsSelect().equals(other.getIsSelect()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
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
		        result = prime * result + ((getOwnerTestPaperName() == null) ? 0 : getOwnerTestPaperName().hashCode());		
		        result = prime * result + ((getOwnerTestPaperQuestionId() == null) ? 0 : getOwnerTestPaperQuestionId().hashCode());		
		        result = prime * result + ((getOptionName() == null) ? 0 : getOptionName().hashCode());		
		        result = prime * result + ((getOptionTitle() == null) ? 0 : getOptionTitle().hashCode());		
		        result = prime * result + ((getIsRight() == null) ? 0 : getIsRight().hashCode());		
		        result = prime * result + ((getIsSelect() == null) ? 0 : getIsSelect().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
