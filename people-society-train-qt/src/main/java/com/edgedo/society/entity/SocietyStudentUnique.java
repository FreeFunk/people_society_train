package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_unique")
public class SocietyStudentUnique implements Serializable{
	
		
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
	 * 属性描述:学员姓名
	 */
	@TableField(value="STUDENT_NAME",exist=true)
	java.lang.String studentName;
	
	/**
	 * 属性描述:学员性别
	 */
	@TableField(value="STUDENT_SEX",exist=true)
	java.lang.String studentSex;
	
	/**
	 * 属性描述:年龄
	 */
	@TableField(value="STUDENT_AGE",exist=true)
	java.lang.Integer studentAge;
	
	/**
	 * 属性描述:手机号
	 */
	@TableField(value="STUDENT_PHONE_NUM",exist=true)
	java.lang.String studentPhoneNum;
	
	/**
	 * 属性描述:身份证号
	 */
	@TableField(value="STUDENT_ID_CARD_NUM",exist=true)
	java.lang.String studentIdCardNum;
	
	/**
	 * 属性描述:是否实名认证(1:是,0:否)
	 */
	@TableField(value="IS_REAL_NAME_AUTH",exist=true)
	java.lang.String isRealNameAuth;
	
	/**
	 * 属性描述:微信OPENID
	 */
	@TableField(value="WX_OPEN_ID",exist=true)
	java.lang.String wxOpenId;
	
	/**
	 * 属性描述:农行OPENID
	 */
	@TableField(value="ABC_OPEN_ID",exist=true)
	java.lang.String abcOpenId;
	
	/**
	 * 属性描述:登录密码
	 */
	@TableField(value="PASSWORD",exist=true)
	java.lang.String password;
	
	/**
	 * 属性描述:身份证正面照
	 */
	@TableField(value="ID_CARD_IMAGE",exist=true)
	java.lang.String idCardImage;
	
	/**
	 * 属性描述:人脸照片
	 */
	@TableField(value="FACE_IMAGE_URL",exist=true)
	java.lang.String faceImageUrl;
	
	/**
	 * 属性描述:头像
	 */
	@TableField(value="HEAD_PHOTO",exist=true)
	java.lang.String headPhoto;
	
	/**
	 * 属性描述:昵称
	 */
	@TableField(value="NICK_NAME",exist=true)
	java.lang.String nickName;
	
	/**
	 * 属性描述:登录token
	 */
	@TableField(value="ACCESS_TOKEN",exist=true)
	java.lang.String accessToken;
	
	/**
	 * 属性描述:是否修改密码(0,1)
	 */
	@TableField(value="IS_UP_PWD",exist=true)
	java.lang.String isUpPwd;
	
	/**
	 * 属性描述:认证时间
	 */
	@TableField(value="REAL_NAME_TIME",exist=true)
	java.util.Date realNameTime;
	
	/**
	 * 属性描述:认证类型
	 */
	@TableField(value="REAL_NAME_TYPE",exist=true)
	java.lang.String realNameType;
	
	/**
	 * 属性描述:认证操作人名
	 */
	@TableField(value="REAL_NAME_OPER_NAME",exist=true)
	java.lang.String realNameOperName;
	
	/**
	 * 属性描述:认证操作人id
	 */
	@TableField(value="REAL_NAME_OPER_ID",exist=true)
	java.lang.String realNameOperId;
	
	
	
	
	
	
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
	
	
	public java.lang.String getStudentName(){
		return this.studentName;
	}
	
	public void setStudentName(java.lang.String studentName){
		this.studentName=studentName;
	}
	
	
	public java.lang.String getStudentSex(){
		return this.studentSex;
	}
	
	public void setStudentSex(java.lang.String studentSex){
		this.studentSex=studentSex;
	}
	
	
	public java.lang.Integer getStudentAge(){
		return this.studentAge;
	}
	
	public void setStudentAge(java.lang.Integer studentAge){
		this.studentAge=studentAge;
	}
	
	
	public java.lang.String getStudentPhoneNum(){
		return this.studentPhoneNum;
	}
	
	public void setStudentPhoneNum(java.lang.String studentPhoneNum){
		this.studentPhoneNum=studentPhoneNum;
	}
	
	
	public java.lang.String getStudentIdCardNum(){
		return this.studentIdCardNum;
	}
	
	public void setStudentIdCardNum(java.lang.String studentIdCardNum){
		this.studentIdCardNum=studentIdCardNum;
	}
	
	
	public java.lang.String getIsRealNameAuth(){
		return this.isRealNameAuth;
	}
	
	public void setIsRealNameAuth(java.lang.String isRealNameAuth){
		this.isRealNameAuth=isRealNameAuth;
	}
	
	
	public java.lang.String getWxOpenId(){
		return this.wxOpenId;
	}
	
	public void setWxOpenId(java.lang.String wxOpenId){
		this.wxOpenId=wxOpenId;
	}
	
	
	public java.lang.String getAbcOpenId(){
		return this.abcOpenId;
	}
	
	public void setAbcOpenId(java.lang.String abcOpenId){
		this.abcOpenId=abcOpenId;
	}
	
	
	public java.lang.String getPassword(){
		return this.password;
	}
	
	public void setPassword(java.lang.String password){
		this.password=password;
	}
	
	
	public java.lang.String getIdCardImage(){
		return this.idCardImage;
	}
	
	public void setIdCardImage(java.lang.String idCardImage){
		this.idCardImage=idCardImage;
	}
	
	
	public java.lang.String getFaceImageUrl(){
		return this.faceImageUrl;
	}
	
	public void setFaceImageUrl(java.lang.String faceImageUrl){
		this.faceImageUrl=faceImageUrl;
	}
	
	
	public java.lang.String getHeadPhoto(){
		return this.headPhoto;
	}
	
	public void setHeadPhoto(java.lang.String headPhoto){
		this.headPhoto=headPhoto;
	}
	
	
	public java.lang.String getNickName(){
		return this.nickName;
	}
	
	public void setNickName(java.lang.String nickName){
		this.nickName=nickName;
	}
	
	
	public java.lang.String getAccessToken(){
		return this.accessToken;
	}
	
	public void setAccessToken(java.lang.String accessToken){
		this.accessToken=accessToken;
	}
	
	
	public java.lang.String getIsUpPwd(){
		return this.isUpPwd;
	}
	
	public void setIsUpPwd(java.lang.String isUpPwd){
		this.isUpPwd=isUpPwd;
	}
	
	
	public java.util.Date getRealNameTime(){
		return this.realNameTime;
	}
	
	public void setRealNameTime(java.util.Date realNameTime){
		this.realNameTime=realNameTime;
	}
	
	
	public java.lang.String getRealNameType(){
		return this.realNameType;
	}
	
	public void setRealNameType(java.lang.String realNameType){
		this.realNameType=realNameType;
	}
	
	
	public java.lang.String getRealNameOperName(){
		return this.realNameOperName;
	}
	
	public void setRealNameOperName(java.lang.String realNameOperName){
		this.realNameOperName=realNameOperName;
	}
	
	
	public java.lang.String getRealNameOperId(){
		return this.realNameOperId;
	}
	
	public void setRealNameOperId(java.lang.String realNameOperId){
		this.realNameOperId=realNameOperId;
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
			sb.append(", studentName=").append(studentName);			
			sb.append(", studentSex=").append(studentSex);			
			sb.append(", studentAge=").append(studentAge);			
			sb.append(", studentPhoneNum=").append(studentPhoneNum);			
			sb.append(", studentIdCardNum=").append(studentIdCardNum);			
			sb.append(", isRealNameAuth=").append(isRealNameAuth);			
			sb.append(", wxOpenId=").append(wxOpenId);			
			sb.append(", abcOpenId=").append(abcOpenId);			
			sb.append(", password=").append(password);			
			sb.append(", idCardImage=").append(idCardImage);			
			sb.append(", faceImageUrl=").append(faceImageUrl);			
			sb.append(", headPhoto=").append(headPhoto);			
			sb.append(", nickName=").append(nickName);			
			sb.append(", accessToken=").append(accessToken);			
			sb.append(", isUpPwd=").append(isUpPwd);			
			sb.append(", realNameTime=").append(realNameTime);			
			sb.append(", realNameType=").append(realNameType);			
			sb.append(", realNameOperName=").append(realNameOperName);			
			sb.append(", realNameOperId=").append(realNameOperId);			
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
        SocietyStudentUnique other = (SocietyStudentUnique) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getStudentName() == null ? other.getId() == null : this.getStudentName().equals(other.getStudentName()))		
				        		&&(this.getStudentSex() == null ? other.getId() == null : this.getStudentSex().equals(other.getStudentSex()))		
				        		&&(this.getStudentAge() == null ? other.getId() == null : this.getStudentAge().equals(other.getStudentAge()))		
				        		&&(this.getStudentPhoneNum() == null ? other.getId() == null : this.getStudentPhoneNum().equals(other.getStudentPhoneNum()))		
				        		&&(this.getStudentIdCardNum() == null ? other.getId() == null : this.getStudentIdCardNum().equals(other.getStudentIdCardNum()))		
				        		&&(this.getIsRealNameAuth() == null ? other.getId() == null : this.getIsRealNameAuth().equals(other.getIsRealNameAuth()))		
				        		&&(this.getWxOpenId() == null ? other.getId() == null : this.getWxOpenId().equals(other.getWxOpenId()))		
				        		&&(this.getAbcOpenId() == null ? other.getId() == null : this.getAbcOpenId().equals(other.getAbcOpenId()))		
				        		&&(this.getPassword() == null ? other.getId() == null : this.getPassword().equals(other.getPassword()))		
				        		&&(this.getIdCardImage() == null ? other.getId() == null : this.getIdCardImage().equals(other.getIdCardImage()))		
				        		&&(this.getFaceImageUrl() == null ? other.getId() == null : this.getFaceImageUrl().equals(other.getFaceImageUrl()))		
				        		&&(this.getHeadPhoto() == null ? other.getId() == null : this.getHeadPhoto().equals(other.getHeadPhoto()))		
				        		&&(this.getNickName() == null ? other.getId() == null : this.getNickName().equals(other.getNickName()))		
				        		&&(this.getAccessToken() == null ? other.getId() == null : this.getAccessToken().equals(other.getAccessToken()))		
				        		&&(this.getIsUpPwd() == null ? other.getId() == null : this.getIsUpPwd().equals(other.getIsUpPwd()))		
				        		&&(this.getRealNameTime() == null ? other.getId() == null : this.getRealNameTime().equals(other.getRealNameTime()))		
				        		&&(this.getRealNameType() == null ? other.getId() == null : this.getRealNameType().equals(other.getRealNameType()))		
				        		&&(this.getRealNameOperName() == null ? other.getId() == null : this.getRealNameOperName().equals(other.getRealNameOperName()))		
				        		&&(this.getRealNameOperId() == null ? other.getId() == null : this.getRealNameOperId().equals(other.getRealNameOperId()))		
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
		        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());		
		        result = prime * result + ((getStudentSex() == null) ? 0 : getStudentSex().hashCode());		
		        result = prime * result + ((getStudentAge() == null) ? 0 : getStudentAge().hashCode());		
		        result = prime * result + ((getStudentPhoneNum() == null) ? 0 : getStudentPhoneNum().hashCode());		
		        result = prime * result + ((getStudentIdCardNum() == null) ? 0 : getStudentIdCardNum().hashCode());		
		        result = prime * result + ((getIsRealNameAuth() == null) ? 0 : getIsRealNameAuth().hashCode());		
		        result = prime * result + ((getWxOpenId() == null) ? 0 : getWxOpenId().hashCode());		
		        result = prime * result + ((getAbcOpenId() == null) ? 0 : getAbcOpenId().hashCode());		
		        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());		
		        result = prime * result + ((getIdCardImage() == null) ? 0 : getIdCardImage().hashCode());		
		        result = prime * result + ((getFaceImageUrl() == null) ? 0 : getFaceImageUrl().hashCode());		
		        result = prime * result + ((getHeadPhoto() == null) ? 0 : getHeadPhoto().hashCode());		
		        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());		
		        result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());		
		        result = prime * result + ((getIsUpPwd() == null) ? 0 : getIsUpPwd().hashCode());		
		        result = prime * result + ((getRealNameTime() == null) ? 0 : getRealNameTime().hashCode());		
		        result = prime * result + ((getRealNameType() == null) ? 0 : getRealNameType().hashCode());		
		        result = prime * result + ((getRealNameOperName() == null) ? 0 : getRealNameOperName().hashCode());		
		        result = prime * result + ((getRealNameOperId() == null) ? 0 : getRealNameOperId().hashCode());		
		;
        return result;
    }

}
