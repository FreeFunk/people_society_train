package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_course_node_option")
public class SocietySchoolCourseNodeOption implements Serializable{
	
		
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
	@TableField(value="OWNER_QUERSION_ID",exist=true)
	java.lang.String ownerQuersionId;
	
	/**
	 * 属性描述:题目名
	 */
	@TableField(value="OWNER_QUESTION_NAME",exist=true)
	java.lang.String ownerQuestionName;
	
	/**
	 * 属性描述:选项(A,B,C,D)
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
	 * 属性描述:排序号
	 */
	@TableField(value="ORDER_NUM",exist=true)
	java.math.BigDecimal orderNum;
	
	
	
	
	
	
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
	
	
	public java.lang.String getOwnerQuersionId(){
		return this.ownerQuersionId;
	}
	
	public void setOwnerQuersionId(java.lang.String ownerQuersionId){
		this.ownerQuersionId=ownerQuersionId;
	}
	
	
	public java.lang.String getOwnerQuestionName(){
		return this.ownerQuestionName;
	}
	
	public void setOwnerQuestionName(java.lang.String ownerQuestionName){
		this.ownerQuestionName=ownerQuestionName;
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
			sb.append(", ownerNodeId=").append(ownerNodeId);			
			sb.append(", ownerNodeName=").append(ownerNodeName);			
			sb.append(", ownerQuersionId=").append(ownerQuersionId);			
			sb.append(", ownerQuestionName=").append(ownerQuestionName);			
			sb.append(", optionTitle=").append(optionTitle);			
			sb.append(", optionName=").append(optionName);			
			sb.append(", isRight=").append(isRight);			
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
        SocietySchoolCourseNodeOption other = (SocietySchoolCourseNodeOption) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getOwnerNodeId() == null ? other.getId() == null : this.getOwnerNodeId().equals(other.getOwnerNodeId()))		
				        		&&(this.getOwnerNodeName() == null ? other.getId() == null : this.getOwnerNodeName().equals(other.getOwnerNodeName()))		
				        		&&(this.getOwnerQuersionId() == null ? other.getId() == null : this.getOwnerQuersionId().equals(other.getOwnerQuersionId()))		
				        		&&(this.getOwnerQuestionName() == null ? other.getId() == null : this.getOwnerQuestionName().equals(other.getOwnerQuestionName()))		
				        		&&(this.getOptionTitle() == null ? other.getId() == null : this.getOptionTitle().equals(other.getOptionTitle()))		
				        		&&(this.getOptionName() == null ? other.getId() == null : this.getOptionName().equals(other.getOptionName()))		
				        		&&(this.getIsRight() == null ? other.getId() == null : this.getIsRight().equals(other.getIsRight()))		
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
		        result = prime * result + ((getOwnerNodeId() == null) ? 0 : getOwnerNodeId().hashCode());		
		        result = prime * result + ((getOwnerNodeName() == null) ? 0 : getOwnerNodeName().hashCode());		
		        result = prime * result + ((getOwnerQuersionId() == null) ? 0 : getOwnerQuersionId().hashCode());		
		        result = prime * result + ((getOwnerQuestionName() == null) ? 0 : getOwnerQuestionName().hashCode());		
		        result = prime * result + ((getOptionTitle() == null) ? 0 : getOptionTitle().hashCode());		
		        result = prime * result + ((getOptionName() == null) ? 0 : getOptionName().hashCode());		
		        result = prime * result + ((getIsRight() == null) ? 0 : getIsRight().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		;
        return result;
    }

}
