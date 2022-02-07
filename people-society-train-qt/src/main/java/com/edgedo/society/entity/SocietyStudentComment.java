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
	 * 属性描述:评论内容
	 */
	@TableField(value="COMMENT_TEXT",exist=true)
	java.lang.String commentText;

	/**
	 * @Author WangZhen
	 * @Description 昵称
	 * @Date 2020/5/8 16:43
	 **/
	@TableField(value="STU_NICK_NAME",exist=true)
	String  stuNickName;

	@TableField(value="STU_HEAD_PHOTO",exist=true)
	String  stuHeadPhoto;
	
	
	
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
	
	
	public java.lang.String getCommentText(){
		return this.commentText;
	}
	
	public void setCommentText(java.lang.String commentText){
		this.commentText=commentText;
	}


	public String getStuNickName() {
		return stuNickName;
	}

	public void setStuNickName(String stuNickName) {
		this.stuNickName = stuNickName;
	}

	public String getStuHeadPhoto() {
		return stuHeadPhoto;
	}

	public void setStuHeadPhoto(String stuHeadPhoto) {
		this.stuHeadPhoto = stuHeadPhoto;
	}
}
