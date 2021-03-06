package com.edgedo.society.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student")
public class SocietyStudent implements Serializable{
	
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
	 * @Author WangZhen
	 * @Description 身份证图片
	 * @Date 2020/5/5 10:38
	 **/
	@TableField(value="ID_CARD_IMAGE",exist=true)
	java.lang.String idCardImage;

	/**
	 * @Author WangZhen
	 * @Description 人脸图片
	 * @Date 2020/5/5 10:38
	 **/
	@TableField(value="FACE_IMAGE_URL",exist=true)
	java.lang.String faceImageUrl;

	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	/**
	 * 属性描述:密码
	 */
	@TableField(value="PASSWORD",exist=true)
	java.lang.String password;
	/*
	* 昵称
	* */
	@TableField(value="NICK_NAME",exist=true)
	java.lang.String nickName;

	/**
	 * 微信openid
	 */
	@TableField(value="WX_OPEN_ID",exist=true)
	java.lang.String wxOpenId;

	/**
	 * 人员类型
	 */
	@TableField(value="PEOPLE_TYPE",exist=true)
	java.lang.String peopleType;

	/**
	 * 头像
	 */
	@TableField(value="HEAD_PHOTO",exist=true)
	java.lang.String headPhoto;

	/**
	 * 实名认证方式  1.后台实名
	 */
	@TableField(value="REAL_NAME_TYPE",exist=true)
	java.lang.String realNameType;

	/**
	 * 认证时间
	 */
	@TableField(value="REAL_NAME_TIME",exist=true)
	java.util.Date realNameTime;
	/**
	 * 认证操作人名
	 */
	@TableField(value="REAL_NAME_OPER_NAME",exist=true)
	java.lang.String realNameOperName;
	/**
	 * 认证操作人id
	 */
	@TableField(value="REAL_NAME_OPER_ID",exist=true)
	java.lang.String realNameOperId;

	@TableField(value="ABC_OPEN_ID",exist=true)
	String abcOpenId;

	@TableField(value="ACCESS_TOKEN",exist=true)
	java.lang.String accessToken;

	@TableField(value="STUDENT_ID_CARD_ASCII",exist=true)
	java.lang.String studentIdCardAscii;
	@TableField(value="OWNER_STUDENT_NUM",exist=true)
	java.lang.String ownerStudentNum;

	public String getOwnerStudentNum() {
		return ownerStudentNum;
	}

	public void setOwnerStudentNum(String ownerStudentNum) {
		this.ownerStudentNum = ownerStudentNum;
	}

	public String getStudentIdCardAscii() {
		return studentIdCardAscii;
	}

	public void setStudentIdCardAscii(String studentIdCardAscii) {
		this.studentIdCardAscii = studentIdCardAscii;
	}

	public String getAbcOpenId() {
		return abcOpenId;
	}

	public void setAbcOpenId(String abcOpenId) {
		this.abcOpenId = abcOpenId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getRealNameTime() {
		return realNameTime;
	}

	public void setRealNameTime(Date realNameTime) {
		this.realNameTime = realNameTime;
	}

	public String getRealNameOperName() {
		return realNameOperName;
	}

	public void setRealNameOperName(String realNameOperName) {
		this.realNameOperName = realNameOperName;
	}

	public String getRealNameOperId() {
		return realNameOperId;
	}

	public void setRealNameOperId(String realNameOperId) {
		this.realNameOperId = realNameOperId;
	}

	public String getRealNameType() {
		return realNameType;
	}

	public void setRealNameType(String realNameType) {
		this.realNameType = realNameType;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

	public String getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
	}


	public String getIdCardImage() {
		return idCardImage;
	}

	public void setIdCardImage(String idCardImage) {
		this.idCardImage = idCardImage;
	}

	public String getFaceImageUrl() {
		return faceImageUrl;
	}

	public void setFaceImageUrl(String faceImageUrl) {
		this.faceImageUrl = faceImageUrl;
	}

	@Override
	public String toString() {
		return "SocietyStudent{" +
				"id='" + id + '\'' +
				", createTime=" + createTime +
				", createUserId='" + createUserId + '\'' +
				", createUserName='" + createUserName + '\'' +
				", provinceId='" + provinceId + '\'' +
				", provinceName='" + provinceName + '\'' +
				", cityId='" + cityId + '\'' +
				", cityName='" + cityName + '\'' +
				", xianquId='" + xianquId + '\'' +
				", xianquName='" + xianquName + '\'' +
				", ownerSchoolId='" + ownerSchoolId + '\'' +
				", ownerSchoolName='" + ownerSchoolName + '\'' +
				", studentName='" + studentName + '\'' +
				", studentSex='" + studentSex + '\'' +
				", studentAge=" + studentAge +
				", studentPhoneNum='" + studentPhoneNum + '\'' +
				", studentIdCardNum='" + studentIdCardNum + '\'' +
				", isRealNameAuth='" + isRealNameAuth + '\'' +
				", idCardImage='" + idCardImage + '\'' +
				", faceImageUrl='" + faceImageUrl + '\'' +
				", dataState='" + dataState + '\'' +
				'}';
	}
}
