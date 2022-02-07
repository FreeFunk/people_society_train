package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_school_teacher")
public class SocietySchoolTeacher implements Serializable{
	
		
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
	 * 属性描述:讲师姓名
	 */
	@TableField(value="TEACHER_NAME",exist=true)
	java.lang.String teacherName;
	
	/**
	 * 属性描述:性别
	 */
	@TableField(value="TEACHER_SEX",exist=true)
	java.lang.String teacherSex;
	
	/**
	 * 属性描述:年龄
	 */
	@TableField(value="TEACHER_AGE",exist=true)
	java.lang.Integer teacherAge;
	
	/**
	 * 属性描述:手机号
	 */
	@TableField(value="TEACHER_PHONE_NUM",exist=true)
	java.lang.String teacherPhoneNum;
	
	/**
	 * 属性描述:身份证号
	 */
	@TableField(value="TEACHER_ID_CARD_NUM",exist=true)
	java.lang.String teacherIdCardNum;
	
	/**
	 * 属性描述:资质类型
	 */
	@TableField(value="CERT_TYPE",exist=true)
	java.lang.String certType;
	
	/**
	 * 属性描述:职业证书编号
	 */
	@TableField(value="CERT_CODE",exist=true)
	java.lang.String certCode;
	
	/**
	 * 属性描述:证书有效期
	 */
	@TableField(value="CERT_END_TIME",exist=true)
	java.lang.String certEndTime;
	
	/**
	 * 属性描述:证书图片url
	 */
	@TableField(value="CERT_IMG_URL",exist=true)
	java.lang.String certImgUrl;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;
	
	
	
	
	
	
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
	
	
	public java.lang.String getProvinceId(){
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.String provinceId){
		this.provinceId=provinceId;
	}
	
	
	public java.lang.String getProvinceName(){
		return this.provinceName;
	}
	
	public void setProvinceName(java.lang.String provinceName){
		this.provinceName=provinceName;
	}
	
	
	public java.lang.String getCityId(){
		return this.cityId;
	}
	
	public void setCityId(java.lang.String cityId){
		this.cityId=cityId;
	}
	
	
	public java.lang.String getCityName(){
		return this.cityName;
	}
	
	public void setCityName(java.lang.String cityName){
		this.cityName=cityName;
	}
	
	
	public java.lang.String getXianquId(){
		return this.xianquId;
	}
	
	public void setXianquId(java.lang.String xianquId){
		this.xianquId=xianquId;
	}
	
	
	public java.lang.String getXianquName(){
		return this.xianquName;
	}
	
	public void setXianquName(java.lang.String xianquName){
		this.xianquName=xianquName;
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
	
	
	public java.lang.String getTeacherName(){
		return this.teacherName;
	}
	
	public void setTeacherName(java.lang.String teacherName){
		this.teacherName=teacherName;
	}
	
	
	public java.lang.String getTeacherSex(){
		return this.teacherSex;
	}
	
	public void setTeacherSex(java.lang.String teacherSex){
		this.teacherSex=teacherSex;
	}
	
	
	public java.lang.Integer getTeacherAge(){
		return this.teacherAge;
	}
	
	public void setTeacherAge(java.lang.Integer teacherAge){
		this.teacherAge=teacherAge;
	}
	
	
	public java.lang.String getTeacherPhoneNum(){
		return this.teacherPhoneNum;
	}
	
	public void setTeacherPhoneNum(java.lang.String teacherPhoneNum){
		this.teacherPhoneNum=teacherPhoneNum;
	}
	
	
	public java.lang.String getTeacherIdCardNum(){
		return this.teacherIdCardNum;
	}
	
	public void setTeacherIdCardNum(java.lang.String teacherIdCardNum){
		this.teacherIdCardNum=teacherIdCardNum;
	}
	
	
	public java.lang.String getCertType(){
		return this.certType;
	}
	
	public void setCertType(java.lang.String certType){
		this.certType=certType;
	}
	
	
	public java.lang.String getCertCode(){
		return this.certCode;
	}
	
	public void setCertCode(java.lang.String certCode){
		this.certCode=certCode;
	}
	
	
	public java.lang.String getCertEndTime(){
		return this.certEndTime;
	}
	
	public void setCertEndTime(java.lang.String certEndTime){
		this.certEndTime=certEndTime;
	}
	
	
	public java.lang.String getCertImgUrl(){
		return this.certImgUrl;
	}
	
	public void setCertImgUrl(java.lang.String certImgUrl){
		this.certImgUrl=certImgUrl;
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
			sb.append(", provinceId=").append(provinceId);			
			sb.append(", provinceName=").append(provinceName);			
			sb.append(", cityId=").append(cityId);			
			sb.append(", cityName=").append(cityName);			
			sb.append(", xianquId=").append(xianquId);			
			sb.append(", xianquName=").append(xianquName);			
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", ownerSchoolName=").append(ownerSchoolName);			
			sb.append(", teacherName=").append(teacherName);			
			sb.append(", teacherSex=").append(teacherSex);			
			sb.append(", teacherAge=").append(teacherAge);			
			sb.append(", teacherPhoneNum=").append(teacherPhoneNum);			
			sb.append(", teacherIdCardNum=").append(teacherIdCardNum);			
			sb.append(", certType=").append(certType);			
			sb.append(", certCode=").append(certCode);			
			sb.append(", certEndTime=").append(certEndTime);			
			sb.append(", certImgUrl=").append(certImgUrl);			
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
        SocietySchoolTeacher other = (SocietySchoolTeacher) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getProvinceId() == null ? other.getId() == null : this.getProvinceId().equals(other.getProvinceId()))		
				        		&&(this.getProvinceName() == null ? other.getId() == null : this.getProvinceName().equals(other.getProvinceName()))		
				        		&&(this.getCityId() == null ? other.getId() == null : this.getCityId().equals(other.getCityId()))		
				        		&&(this.getCityName() == null ? other.getId() == null : this.getCityName().equals(other.getCityName()))		
				        		&&(this.getXianquId() == null ? other.getId() == null : this.getXianquId().equals(other.getXianquId()))		
				        		&&(this.getXianquName() == null ? other.getId() == null : this.getXianquName().equals(other.getXianquName()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getOwnerSchoolName() == null ? other.getId() == null : this.getOwnerSchoolName().equals(other.getOwnerSchoolName()))		
				        		&&(this.getTeacherName() == null ? other.getId() == null : this.getTeacherName().equals(other.getTeacherName()))		
				        		&&(this.getTeacherSex() == null ? other.getId() == null : this.getTeacherSex().equals(other.getTeacherSex()))		
				        		&&(this.getTeacherAge() == null ? other.getId() == null : this.getTeacherAge().equals(other.getTeacherAge()))		
				        		&&(this.getTeacherPhoneNum() == null ? other.getId() == null : this.getTeacherPhoneNum().equals(other.getTeacherPhoneNum()))		
				        		&&(this.getTeacherIdCardNum() == null ? other.getId() == null : this.getTeacherIdCardNum().equals(other.getTeacherIdCardNum()))		
				        		&&(this.getCertType() == null ? other.getId() == null : this.getCertType().equals(other.getCertType()))		
				        		&&(this.getCertCode() == null ? other.getId() == null : this.getCertCode().equals(other.getCertCode()))		
				        		&&(this.getCertEndTime() == null ? other.getId() == null : this.getCertEndTime().equals(other.getCertEndTime()))		
				        		&&(this.getCertImgUrl() == null ? other.getId() == null : this.getCertImgUrl().equals(other.getCertImgUrl()))		
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
		        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());		
		        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());		
		        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());		
		        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());		
		        result = prime * result + ((getXianquId() == null) ? 0 : getXianquId().hashCode());		
		        result = prime * result + ((getXianquName() == null) ? 0 : getXianquName().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getOwnerSchoolName() == null) ? 0 : getOwnerSchoolName().hashCode());		
		        result = prime * result + ((getTeacherName() == null) ? 0 : getTeacherName().hashCode());		
		        result = prime * result + ((getTeacherSex() == null) ? 0 : getTeacherSex().hashCode());		
		        result = prime * result + ((getTeacherAge() == null) ? 0 : getTeacherAge().hashCode());		
		        result = prime * result + ((getTeacherPhoneNum() == null) ? 0 : getTeacherPhoneNum().hashCode());		
		        result = prime * result + ((getTeacherIdCardNum() == null) ? 0 : getTeacherIdCardNum().hashCode());		
		        result = prime * result + ((getCertType() == null) ? 0 : getCertType().hashCode());		
		        result = prime * result + ((getCertCode() == null) ? 0 : getCertCode().hashCode());		
		        result = prime * result + ((getCertEndTime() == null) ? 0 : getCertEndTime().hashCode());		
		        result = prime * result + ((getCertImgUrl() == null) ? 0 : getCertImgUrl().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
