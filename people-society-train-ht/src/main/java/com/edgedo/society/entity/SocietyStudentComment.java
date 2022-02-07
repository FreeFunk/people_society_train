package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_comment")
public class SocietyStudentComment implements Serializable{
	
		
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
	 * 属性描述:所属学员ID
	 */
	@TableField(value="OWNER_STUDENT_ID",exist=true)
	java.lang.String ownerStudentId;
	
	/**
	 * 属性描述:所属学员名
	 */
	@TableField(value="OWNER_STUDENT_NAME",exist=true)
	java.lang.String ownerStudentName;
	
	/**
	 * 属性描述:学员头像
	 */
	@TableField(value="STU_HEAD_PHOTO",exist=true)
	java.lang.String stuHeadPhoto;
	
	/**
	 * 属性描述:学员昵称
	 */
	@TableField(value="STU_NICK_NAME",exist=true)
	java.lang.String stuNickName;
	
	/**
	 * 属性描述:评论内容
	 */
	@TableField(value="COMMENT_TEXT",exist=true)
	java.lang.String commentText;
	
	
	
	
	
	
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
	
	
	public java.lang.String getOwnerStudentId(){
		return this.ownerStudentId;
	}
	
	public void setOwnerStudentId(java.lang.String ownerStudentId){
		this.ownerStudentId=ownerStudentId;
	}
	
	
	public java.lang.String getOwnerStudentName(){
		return this.ownerStudentName;
	}
	
	public void setOwnerStudentName(java.lang.String ownerStudentName){
		this.ownerStudentName=ownerStudentName;
	}
	
	
	public java.lang.String getStuHeadPhoto(){
		return this.stuHeadPhoto;
	}
	
	public void setStuHeadPhoto(java.lang.String stuHeadPhoto){
		this.stuHeadPhoto=stuHeadPhoto;
	}
	
	
	public java.lang.String getStuNickName(){
		return this.stuNickName;
	}
	
	public void setStuNickName(java.lang.String stuNickName){
		this.stuNickName=stuNickName;
	}
	
	
	public java.lang.String getCommentText(){
		return this.commentText;
	}
	
	public void setCommentText(java.lang.String commentText){
		this.commentText=commentText;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", ownerSchoolName=").append(ownerSchoolName);			
			sb.append(", ownerCourseId=").append(ownerCourseId);			
			sb.append(", ownerCourseName=").append(ownerCourseName);			
			sb.append(", ownerNodeId=").append(ownerNodeId);			
			sb.append(", ownerNodeName=").append(ownerNodeName);			
			sb.append(", ownerStudentId=").append(ownerStudentId);			
			sb.append(", ownerStudentName=").append(ownerStudentName);			
			sb.append(", stuHeadPhoto=").append(stuHeadPhoto);			
			sb.append(", stuNickName=").append(stuNickName);			
			sb.append(", commentText=").append(commentText);			
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
        SocietyStudentComment other = (SocietyStudentComment) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getOwnerCourseId() == null ? other.getId() == null : this.getOwnerCourseId().equals(other.getOwnerCourseId()))		
				        		&&(this.getOwnerCourseName() == null ? other.getId() == null : this.getOwnerCourseName().equals(other.getOwnerCourseName()))		
				        		&&(this.getOwnerNodeId() == null ? other.getId() == null : this.getOwnerNodeId().equals(other.getOwnerNodeId()))		
				        		&&(this.getOwnerNodeName() == null ? other.getId() == null : this.getOwnerNodeName().equals(other.getOwnerNodeName()))		
				        		&&(this.getOwnerStudentId() == null ? other.getId() == null : this.getOwnerStudentId().equals(other.getOwnerStudentId()))		
				        		&&(this.getOwnerStudentName() == null ? other.getId() == null : this.getOwnerStudentName().equals(other.getOwnerStudentName()))		
				        		&&(this.getStuHeadPhoto() == null ? other.getId() == null : this.getStuHeadPhoto().equals(other.getStuHeadPhoto()))		
				        		&&(this.getStuNickName() == null ? other.getId() == null : this.getStuNickName().equals(other.getStuNickName()))		
				        		&&(this.getCommentText() == null ? other.getId() == null : this.getCommentText().equals(other.getCommentText()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getOwnerSchoolName() == null) ? 0 : getOwnerSchoolName().hashCode());		
		        result = prime * result + ((getOwnerCourseId() == null) ? 0 : getOwnerCourseId().hashCode());		
		        result = prime * result + ((getOwnerCourseName() == null) ? 0 : getOwnerCourseName().hashCode());		
		        result = prime * result + ((getOwnerNodeId() == null) ? 0 : getOwnerNodeId().hashCode());		
		        result = prime * result + ((getOwnerNodeName() == null) ? 0 : getOwnerNodeName().hashCode());		
		        result = prime * result + ((getOwnerStudentId() == null) ? 0 : getOwnerStudentId().hashCode());		
		        result = prime * result + ((getOwnerStudentName() == null) ? 0 : getOwnerStudentName().hashCode());		
		        result = prime * result + ((getStuHeadPhoto() == null) ? 0 : getStuHeadPhoto().hashCode());		
		        result = prime * result + ((getStuNickName() == null) ? 0 : getStuNickName().hashCode());		
		        result = prime * result + ((getCommentText() == null) ? 0 : getCommentText().hashCode());		
		;
        return result;
    }

}
