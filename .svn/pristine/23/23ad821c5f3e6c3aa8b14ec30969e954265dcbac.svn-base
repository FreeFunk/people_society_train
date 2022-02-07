package com.edgedo.society.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("yw_train_learn_rec")
public class YwTrainLearnRec implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	String id;

	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;

	/**
	 * 属性描述:创建人id
	 */
	@TableField(value="CREATE_USER_ID",exist=true)
	String createUserId;

	/**
	 * 属性描述:创建人名
	 */
	@TableField(value="CREATE_USER_NAME",exist=true)
	String createUserName;

	/**
	 * 属性描述:开始时间
	 */
	@TableField(value="BEGIN_TIME",exist=true)
	java.util.Date beginTime;

	/**
	 * 属性描述:最后学习时间
	 */
	@TableField(value="END_FRESH_TIME",exist=true)
	java.util.Date endFreshTime;

	/**
	 * 属性描述:本次学习分钟
	 */
	@TableField(value="LEARN_MINITE_NUM",exist=true)
	Integer learnMiniteNum;

	/**
	 * 属性描述:企业id
	 */
	@TableField(value="COMP_ID",exist=true)
	String compId;

	/**
	 * 属性描述:应用id
	 */
	@TableField(value="APP_ID",exist=true)
	String appId;

	/**
	 * 属性描述:开始的人脸
	 */
	@TableField(value="FACE_URL",exist=true)
	String faceUrl;

	/**
	 * 属性描述:所属学习记录
	 */
	@TableField(value="LEARN_REC_ID",exist=true)
	String learnRecId;

	/**
	 * 属性描述:计划id
	 */
	@TableField(value="PLAN_ID",exist=true)
	String planId;


	/**
	 * 属性描述:所属消费记录
	 */
	@TableField(value="OWNER_CONSUM_ID",exist=true)
	String ownerConsumId;

	/**
	 * 属性描述:人脸匹配得分
	 */
	@TableField(value="FACE_MATCH_SCORE",exist=true)
	BigDecimal faceMatchScore;

	/**
	 * 属性描述:人脸匹配得分
	 */
	@TableField(value="FACE_MATCH_TOKEN",exist=true)
	String faceMatchToken;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public BigDecimal getFaceMatchScore() {
		return faceMatchScore;
	}

	public void setFaceMatchScore(BigDecimal faceMatchScore) {
		this.faceMatchScore = faceMatchScore;
	}

	public String getFaceMatchToken() {
		return faceMatchToken;
	}

	public void setFaceMatchToken(String faceMatchToken) {
		this.faceMatchToken = faceMatchToken;
	}

	public String getOwnerConsumId() {
		return ownerConsumId;
	}

	public void setOwnerConsumId(String ownerConsumId) {
		this.ownerConsumId = ownerConsumId;
	}

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id=id;
	}


	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}


	public String getCreateUserId(){
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId){
		this.createUserId=createUserId;
	}


	public String getCreateUserName(){
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName){
		this.createUserName=createUserName;
	}


	public java.util.Date getBeginTime(){
		return this.beginTime;
	}

	public void setBeginTime(java.util.Date beginTime){
		this.beginTime=beginTime;
	}


	public java.util.Date getEndFreshTime(){
		return this.endFreshTime;
	}

	public void setEndFreshTime(java.util.Date endFreshTime){
		this.endFreshTime=endFreshTime;
	}


	public Integer getLearnMiniteNum(){
		return this.learnMiniteNum;
	}

	public void setLearnMiniteNum(Integer learnMiniteNum){
		this.learnMiniteNum=learnMiniteNum;
	}


	public String getCompId(){
		return this.compId;
	}

	public void setCompId(String compId){
		this.compId=compId;
	}


	public String getAppId(){
		return this.appId;
	}

	public void setAppId(String appId){
		this.appId=appId;
	}


	public String getFaceUrl(){
		return this.faceUrl;
	}

	public void setFaceUrl(String faceUrl){
		this.faceUrl=faceUrl;
	}

	public String getLearnRecId(){
		return this.learnRecId;
	}

	public void setLearnRecId(String learnRecId){
		this.learnRecId=learnRecId;
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
			sb.append(", beginTime=").append(beginTime);			
			sb.append(", endFreshTime=").append(endFreshTime);			
			sb.append(", learnMiniteNum=").append(learnMiniteNum);			
			sb.append(", compId=").append(compId);			
			sb.append(", appId=").append(appId);			
			sb.append(", faceUrl=").append(faceUrl);			
			sb.append(", learnRecId=").append(learnRecId);
			sb.append(", ownerConsumId=").append(ownerConsumId);
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
        YwTrainLearnRec other = (YwTrainLearnRec) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getBeginTime() == null ? other.getId() == null : this.getBeginTime().equals(other.getBeginTime()))		
				        		&&(this.getEndFreshTime() == null ? other.getId() == null : this.getEndFreshTime().equals(other.getEndFreshTime()))		
				        		&&(this.getLearnMiniteNum() == null ? other.getId() == null : this.getLearnMiniteNum().equals(other.getLearnMiniteNum()))		
				        		&&(this.getCompId() == null ? other.getId() == null : this.getCompId().equals(other.getCompId()))		
				        		&&(this.getAppId() == null ? other.getId() == null : this.getAppId().equals(other.getAppId()))		
				        		&&(this.getFaceUrl() == null ? other.getId() == null : this.getFaceUrl().equals(other.getFaceUrl()))		
				        		&&(this.getLearnRecId() == null ? other.getId() == null : this.getLearnRecId().equals(other.getLearnRecId()))
								&&(this.getOwnerConsumId() == null ? other.getId() == null : this.getOwnerConsumId().equals(other.getOwnerConsumId()))

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
		        result = prime * result + ((getBeginTime() == null) ? 0 : getBeginTime().hashCode());		
		        result = prime * result + ((getEndFreshTime() == null) ? 0 : getEndFreshTime().hashCode());		
		        result = prime * result + ((getLearnMiniteNum() == null) ? 0 : getLearnMiniteNum().hashCode());		
		        result = prime * result + ((getCompId() == null) ? 0 : getCompId().hashCode());		
		        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());		
		        result = prime * result + ((getFaceUrl() == null) ? 0 : getFaceUrl().hashCode());		
		        result = prime * result + ((getLearnRecId() == null) ? 0 : getLearnRecId().hashCode());
				result = prime * result + ((getOwnerConsumId() == null) ? 0 : getOwnerConsumId().hashCode());
		;
        return result;
    }

}
