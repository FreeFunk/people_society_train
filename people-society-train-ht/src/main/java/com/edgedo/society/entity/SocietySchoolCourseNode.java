package com.edgedo.society.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_course_node")
public class SocietySchoolCourseNode implements Serializable{
	
		
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
	 * 属性描述:章节名称
	 */
	@TableField(value="NODE_NAME",exist=true)
	java.lang.String nodeName;
	
	/**
	 * 属性描述:章节时长(秒)
	 */
	@TableField(value="NODE_TIME_LENGTH",exist=true)
	java.lang.Integer nodeTimeLength;
	
	/**
	 * 属性描述:视频封面
	 */
	@TableField(value="IMAGE_URL",exist=true)
	java.lang.String imageUrl;
	
	/**
	 * 属性描述:视频地址
	 */
	@TableField(value="FILE_URL",exist=true)
	java.lang.String fileUrl;

	/**
	 * 属性描述:排序号
	 */
	@TableField(value="ORDER_NUM",exist=true)
	java.math.BigDecimal orderNum;
	
	/**
	 * 习题数
	 */
	@TableField(value="QUESTION_NUM",exist=true)
	java.lang.Integer questionNum;
	
	/**
	 * 属性描述:审核状态(1:通过,0:审核中,-1:不通过)
	 */
	@TableField(value="SH_STATE",exist=true)
	java.lang.String shState;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;
	/**
	 * 属性描述:腾讯云视频id
	 */
	@TableField(value="FILE_ID",exist=true)
	java.lang.String fileId;

	/**
	 * 属性描述:是否公开(1:是，0:否)
	 */
	@TableField(value="IS_OPEN",exist=true)
	java.lang.String isOpen;

	/**
	 * 属性描述:所属章节资源ID
	 */
	@TableField(value="OWNER_NODE_RESOURCES_ID",exist=true)
	java.lang.String ownerNodeResourcesId;

	/**
	 * 属性描述:所属章节资源名字
	 */
	@TableField(value="OWNER_NODE_RESOURCES_NAME",exist=true)
	java.lang.String ownerNodeResourcesName;

	/**
	 * 属性描述:所属章节资源学校ID
	 */
	@TableField(value="OWNER_NODE_RESOURCES_SCHOOL_ID",exist=true)
	java.lang.String ownerNodeResourcesSchoolId;

	/**
	 * 属性描述:讲师id
	 */
	@TableField(value="TEACHER_ID",exist=true)
	java.lang.String teacherId;

	/**
	 * 属性描述:讲师名字
	 */
	@TableField(value="TEACHER_NAME",exist=true)
	java.lang.String teacherName;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getOwnerNodeResourcesId() {
		return ownerNodeResourcesId;
	}

	public void setOwnerNodeResourcesId(String ownerNodeResourcesId) {
		this.ownerNodeResourcesId = ownerNodeResourcesId;
	}

	public String getOwnerNodeResourcesName() {
		return ownerNodeResourcesName;
	}

	public void setOwnerNodeResourcesName(String ownerNodeResourcesName) {
		this.ownerNodeResourcesName = ownerNodeResourcesName;
	}

	public String getOwnerNodeResourcesSchoolId() {
		return ownerNodeResourcesSchoolId;
	}

	public void setOwnerNodeResourcesSchoolId(String ownerNodeResourcesSchoolId) {
		this.ownerNodeResourcesSchoolId = ownerNodeResourcesSchoolId;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
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
	
	
	public java.lang.String getNodeName(){
		return this.nodeName;
	}
	
	public void setNodeName(java.lang.String nodeName){
		this.nodeName=nodeName;
	}
	
	
	public java.lang.Integer getNodeTimeLength(){
		return this.nodeTimeLength;
	}
	
	public void setNodeTimeLength(java.lang.Integer nodeTimeLength){
		this.nodeTimeLength=nodeTimeLength;
	}
	
	
	public java.lang.String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(java.lang.String imageUrl){
		this.imageUrl=imageUrl;
	}
	
	
	public java.lang.String getFileUrl(){
		return this.fileUrl;
	}
	
	public void setFileUrl(java.lang.String fileUrl){
		this.fileUrl=fileUrl;
	}
	
	
	public java.math.BigDecimal getOrderNum(){
		return this.orderNum;
	}
	
	public void setOrderNum(java.math.BigDecimal orderNum){
		this.orderNum=orderNum;
	}
	
	
	public java.lang.String getShState(){
		return this.shState;
	}
	
	public void setShState(java.lang.String shState){
		this.shState=shState;
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
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", ownerSchoolName=").append(ownerSchoolName);			
			sb.append(", ownerMajorId=").append(ownerMajorId);			
			sb.append(", ownerMajorName=").append(ownerMajorName);			
			sb.append(", ownerCourseClsId=").append(ownerCourseClsId);			
			sb.append(", ownerCourseClsName=").append(ownerCourseClsName);			
			sb.append(", ownerCourseId=").append(ownerCourseId);			
			sb.append(", ownerCourseName=").append(ownerCourseName);			
			sb.append(", nodeName=").append(nodeName);			
			sb.append(", nodeTimeLength=").append(nodeTimeLength);			
			sb.append(", imageUrl=").append(imageUrl);			
			sb.append(", fileUrl=").append(fileUrl);			
			sb.append(", orderNum=").append(orderNum);			
			sb.append(", shState=").append(shState);			
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
        SocietySchoolCourseNode other = (SocietySchoolCourseNode) that;
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
				        		&&(this.getNodeName() == null ? other.getId() == null : this.getNodeName().equals(other.getNodeName()))		
				        		&&(this.getNodeTimeLength() == null ? other.getId() == null : this.getNodeTimeLength().equals(other.getNodeTimeLength()))		
				        		&&(this.getImageUrl() == null ? other.getId() == null : this.getImageUrl().equals(other.getImageUrl()))		
				        		&&(this.getFileUrl() == null ? other.getId() == null : this.getFileUrl().equals(other.getFileUrl()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
				        		&&(this.getShState() == null ? other.getId() == null : this.getShState().equals(other.getShState()))		
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
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getOwnerSchoolName() == null) ? 0 : getOwnerSchoolName().hashCode());		
		        result = prime * result + ((getOwnerMajorId() == null) ? 0 : getOwnerMajorId().hashCode());		
		        result = prime * result + ((getOwnerMajorName() == null) ? 0 : getOwnerMajorName().hashCode());		
		        result = prime * result + ((getOwnerCourseClsId() == null) ? 0 : getOwnerCourseClsId().hashCode());		
		        result = prime * result + ((getOwnerCourseClsName() == null) ? 0 : getOwnerCourseClsName().hashCode());		
		        result = prime * result + ((getOwnerCourseId() == null) ? 0 : getOwnerCourseId().hashCode());		
		        result = prime * result + ((getOwnerCourseName() == null) ? 0 : getOwnerCourseName().hashCode());		
		        result = prime * result + ((getNodeName() == null) ? 0 : getNodeName().hashCode());		
		        result = prime * result + ((getNodeTimeLength() == null) ? 0 : getNodeTimeLength().hashCode());		
		        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());		
		        result = prime * result + ((getFileUrl() == null) ? 0 : getFileUrl().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		        result = prime * result + ((getShState() == null) ? 0 : getShState().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
