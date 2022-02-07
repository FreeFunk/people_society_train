package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_wx_message_rec")
public class SocietyWxMessageRec implements Serializable{
	
		
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
	 * 属性描述:发送时间
	 */
	@TableField(value="OWNER_WX_MESSAGE_ID",exist=true)
	java.lang.String ownerWxMessageId;
	
	/**
	 * 属性描述:消息标题
	 */
	@TableField(value="MESSAGE_TITLE",exist=true)
	java.lang.String messageTitle;
	
	/**
	 * 属性描述:消息内容
	 */
	@TableField(value="MESSAGE_TEXT",exist=true)
	java.lang.String messageText;
	
	/**
	 * 属性描述:学员ID
	 */
	@TableField(value="STUDENT_ID",exist=true)
	java.lang.String studentId;
	
	/**
	 * 属性描述:学员openid
	 */
	@TableField(value="STUDENT_OPEN_ID",exist=true)
	java.lang.String studentOpenId;
	
	/**
	 * 属性描述:跳转链接
	 */
	@TableField(value="CLICK_TO_URL",exist=true)
	java.lang.String clickToUrl;
	
	/**
	 * 属性描述:是否已读
	 */
	@TableField(value="IS_READ",exist=true)
	java.lang.String isRead;
	
	/**
	 * 属性描述:是否发送
	 */
	@TableField(value="IS_SEND",exist=true)
	java.lang.String isSend;
	
	/**
	 * 属性描述:发送时间
	 */
	@TableField(value="SEND_TIME",exist=true)
	java.util.Date sendTime;
	
	
	
	
	
	
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
	
	
	public java.lang.String getOwnerWxMessageId(){
		return this.ownerWxMessageId;
	}
	
	public void setOwnerWxMessageId(java.lang.String ownerWxMessageId){
		this.ownerWxMessageId=ownerWxMessageId;
	}
	
	
	public java.lang.String getMessageTitle(){
		return this.messageTitle;
	}
	
	public void setMessageTitle(java.lang.String messageTitle){
		this.messageTitle=messageTitle;
	}
	
	
	public java.lang.String getMessageText(){
		return this.messageText;
	}
	
	public void setMessageText(java.lang.String messageText){
		this.messageText=messageText;
	}
	
	
	public java.lang.String getStudentId(){
		return this.studentId;
	}
	
	public void setStudentId(java.lang.String studentId){
		this.studentId=studentId;
	}
	
	
	public java.lang.String getStudentOpenId(){
		return this.studentOpenId;
	}
	
	public void setStudentOpenId(java.lang.String studentOpenId){
		this.studentOpenId=studentOpenId;
	}
	
	
	public java.lang.String getClickToUrl(){
		return this.clickToUrl;
	}
	
	public void setClickToUrl(java.lang.String clickToUrl){
		this.clickToUrl=clickToUrl;
	}
	
	
	public java.lang.String getIsRead(){
		return this.isRead;
	}
	
	public void setIsRead(java.lang.String isRead){
		this.isRead=isRead;
	}
	
	
	public java.lang.String getIsSend(){
		return this.isSend;
	}
	
	public void setIsSend(java.lang.String isSend){
		this.isSend=isSend;
	}
	
	
	public java.util.Date getSendTime(){
		return this.sendTime;
	}
	
	public void setSendTime(java.util.Date sendTime){
		this.sendTime=sendTime;
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
			sb.append(", ownerWxMessageId=").append(ownerWxMessageId);			
			sb.append(", messageTitle=").append(messageTitle);			
			sb.append(", messageText=").append(messageText);			
			sb.append(", studentId=").append(studentId);			
			sb.append(", studentOpenId=").append(studentOpenId);			
			sb.append(", clickToUrl=").append(clickToUrl);			
			sb.append(", isRead=").append(isRead);			
			sb.append(", isSend=").append(isSend);			
			sb.append(", sendTime=").append(sendTime);			
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
        SocietyWxMessageRec other = (SocietyWxMessageRec) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getOwnerWxMessageId() == null ? other.getId() == null : this.getOwnerWxMessageId().equals(other.getOwnerWxMessageId()))		
				        		&&(this.getMessageTitle() == null ? other.getId() == null : this.getMessageTitle().equals(other.getMessageTitle()))		
				        		&&(this.getMessageText() == null ? other.getId() == null : this.getMessageText().equals(other.getMessageText()))		
				        		&&(this.getStudentId() == null ? other.getId() == null : this.getStudentId().equals(other.getStudentId()))		
				        		&&(this.getStudentOpenId() == null ? other.getId() == null : this.getStudentOpenId().equals(other.getStudentOpenId()))		
				        		&&(this.getClickToUrl() == null ? other.getId() == null : this.getClickToUrl().equals(other.getClickToUrl()))		
				        		&&(this.getIsRead() == null ? other.getId() == null : this.getIsRead().equals(other.getIsRead()))		
				        		&&(this.getIsSend() == null ? other.getId() == null : this.getIsSend().equals(other.getIsSend()))		
				        		&&(this.getSendTime() == null ? other.getId() == null : this.getSendTime().equals(other.getSendTime()))		
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
		        result = prime * result + ((getOwnerWxMessageId() == null) ? 0 : getOwnerWxMessageId().hashCode());		
		        result = prime * result + ((getMessageTitle() == null) ? 0 : getMessageTitle().hashCode());		
		        result = prime * result + ((getMessageText() == null) ? 0 : getMessageText().hashCode());		
		        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());		
		        result = prime * result + ((getStudentOpenId() == null) ? 0 : getStudentOpenId().hashCode());		
		        result = prime * result + ((getClickToUrl() == null) ? 0 : getClickToUrl().hashCode());		
		        result = prime * result + ((getIsRead() == null) ? 0 : getIsRead().hashCode());		
		        result = prime * result + ((getIsSend() == null) ? 0 : getIsSend().hashCode());		
		        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());		
		;
        return result;
    }

}
