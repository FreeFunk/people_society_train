package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("yw_train_consum_rec_old")
public class YwTrainConsumRecOld implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:姓名
	 */
	@TableField(value="STUDENT_NAME",exist=true)
	java.lang.String studentName;
	
	/**
	 * 属性描述:身份证号
	 */
	@TableField(value="STUDENT_ID_CARD",exist=true)
	java.lang.String studentIdCard;
	
	/**
	 * 属性描述:联系方式
	 */
	@TableField(value="STUDNET_PHONE_NUM",exist=true)
	java.lang.String studnetPhoneNum;
	
	/**
	 * 属性描述:培训结束时间
	 */
	@TableField(value="TRAIN_END_TIME",exist=true)
	java.util.Date trainEndTime;
	
	/**
	 * 属性描述:课时数
	 */
	@TableField(value="TRAIN_LESSION_NUM",exist=true)
	java.lang.Integer trainLessionNum;
	
	/**
	 * 属性描述:是否签协议
	 */
	@TableField(value="IS_SIGN_AGREEMENT",exist=true)
	java.lang.String isSignAgreement;
	
	/**
	 * 属性描述:资格证号
	 */
	@TableField(value="STUDENT_LICENCE_CODE",exist=true)
	java.lang.String studentLicenceCode;
	
	/**
	 * 属性描述:培训补贴金额
	 */
	@TableField(value="TRAIN_ALLOW_BILL",exist=true)
	java.lang.Integer trainAllowBill;

	/**
	 * 属性描述:培训专业
	 */
	@TableField(value="TRAIN_MAJOR",exist=true)
	java.lang.String trainMajor;
	
	/**
	 * 属性描述:期次
	 */
	@TableField(value="PERIOD",exist=true)
	java.lang.String period;
	
	/**
	 * 属性描述:就业形式
	 */
	@TableField(value="WORK_TYPE",exist=true)
	java.lang.String workType;
	
	/**
	 * 属性描述:劳动合同开始时间
	 */
	@TableField(value="WORK_AGREE_START_TIME",exist=true)
	java.util.Date workAgreeStartTime;
	
	/**
	 * 属性描述:劳动合同结束时间
	 */
	@TableField(value="WORK_AGREE_END_TIME",exist=true)
	java.util.Date workAgreeEndTime;
	
	/**
	 * 属性描述:营业执照号
	 */
	@TableField(value="CERT_NUM",exist=true)
	java.lang.String certNum;
	
	/**
	 * 属性描述:营业执照登记时间
	 */
	@TableField(value="CERT_REGIN_TIME",exist=true)
	java.util.Date certReginTime;
	
	/**
	 * 属性描述:组织机构代码
	 */
	@TableField(value="ORGAN_CODE",exist=true)
	java.lang.String organCode;
	
	/**
	 * 属性描述:就业单位名称
	 */
	@TableField(value="COMP_NAME",exist=true)
	java.lang.String compName;
	
	/**
	 * 属性描述:就业单位电话
	 */
	@TableField(value="COMP_PHONE_NUM",exist=true)
	java.lang.String compPhoneNum;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;


	public String getTrainMajor() {
		return trainMajor;
	}

	public void setTrainMajor(String trainMajor) {
		this.trainMajor = trainMajor;
	}

	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.lang.String getStudentName(){
		return this.studentName;
	}
	
	public void setStudentName(java.lang.String studentName){
		this.studentName=studentName;
	}
	
	
	public java.lang.String getStudentIdCard(){
		return this.studentIdCard;
	}
	
	public void setStudentIdCard(java.lang.String studentIdCard){
		this.studentIdCard=studentIdCard;
	}
	
	
	public java.lang.String getStudnetPhoneNum(){
		return this.studnetPhoneNum;
	}
	
	public void setStudnetPhoneNum(java.lang.String studnetPhoneNum){
		this.studnetPhoneNum=studnetPhoneNum;
	}
	
	
	public java.util.Date getTrainEndTime(){
		return this.trainEndTime;
	}
	
	public void setTrainEndTime(java.util.Date trainEndTime){
		this.trainEndTime=trainEndTime;
	}
	
	
	public java.lang.Integer getTrainLessionNum(){
		return this.trainLessionNum;
	}
	
	public void setTrainLessionNum(java.lang.Integer trainLessionNum){
		this.trainLessionNum=trainLessionNum;
	}
	
	
	public java.lang.String getIsSignAgreement(){
		return this.isSignAgreement;
	}
	
	public void setIsSignAgreement(java.lang.String isSignAgreement){
		this.isSignAgreement=isSignAgreement;
	}
	
	
	public java.lang.String getStudentLicenceCode(){
		return this.studentLicenceCode;
	}
	
	public void setStudentLicenceCode(java.lang.String studentLicenceCode){
		this.studentLicenceCode=studentLicenceCode;
	}
	
	
	public java.lang.Integer getTrainAllowBill(){
		return this.trainAllowBill;
	}
	
	public void setTrainAllowBill(java.lang.Integer trainAllowBill){
		this.trainAllowBill=trainAllowBill;
	}
	
	
	public java.lang.String getPeriod(){
		return this.period;
	}
	
	public void setPeriod(java.lang.String period){
		this.period=period;
	}
	
	
	public java.lang.String getWorkType(){
		return this.workType;
	}
	
	public void setWorkType(java.lang.String workType){
		this.workType=workType;
	}
	
	
	public java.util.Date getWorkAgreeStartTime(){
		return this.workAgreeStartTime;
	}
	
	public void setWorkAgreeStartTime(java.util.Date workAgreeStartTime){
		this.workAgreeStartTime=workAgreeStartTime;
	}
	
	
	public java.util.Date getWorkAgreeEndTime(){
		return this.workAgreeEndTime;
	}
	
	public void setWorkAgreeEndTime(java.util.Date workAgreeEndTime){
		this.workAgreeEndTime=workAgreeEndTime;
	}
	
	
	public java.lang.String getCertNum(){
		return this.certNum;
	}
	
	public void setCertNum(java.lang.String certNum){
		this.certNum=certNum;
	}
	
	
	public java.util.Date getCertReginTime(){
		return this.certReginTime;
	}
	
	public void setCertReginTime(java.util.Date certReginTime){
		this.certReginTime=certReginTime;
	}
	
	
	public java.lang.String getOrganCode(){
		return this.organCode;
	}
	
	public void setOrganCode(java.lang.String organCode){
		this.organCode=organCode;
	}
	
	
	public java.lang.String getCompName(){
		return this.compName;
	}
	
	public void setCompName(java.lang.String compName){
		this.compName=compName;
	}
	
	
	public java.lang.String getCompPhoneNum(){
		return this.compPhoneNum;
	}
	
	public void setCompPhoneNum(java.lang.String compPhoneNum){
		this.compPhoneNum=compPhoneNum;
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
			sb.append(", studentName=").append(studentName);			
			sb.append(", studentIdCard=").append(studentIdCard);			
			sb.append(", studnetPhoneNum=").append(studnetPhoneNum);			
			sb.append(", trainEndTime=").append(trainEndTime);			
			sb.append(", trainLessionNum=").append(trainLessionNum);			
			sb.append(", isSignAgreement=").append(isSignAgreement);			
			sb.append(", studentLicenceCode=").append(studentLicenceCode);			
			sb.append(", trainAllowBill=").append(trainAllowBill);			
			sb.append(", period=").append(period);			
			sb.append(", workType=").append(workType);			
			sb.append(", workAgreeStartTime=").append(workAgreeStartTime);			
			sb.append(", workAgreeEndTime=").append(workAgreeEndTime);			
			sb.append(", certNum=").append(certNum);			
			sb.append(", certReginTime=").append(certReginTime);			
			sb.append(", organCode=").append(organCode);			
			sb.append(", compName=").append(compName);			
			sb.append(", compPhoneNum=").append(compPhoneNum);			
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
        YwTrainConsumRecOld other = (YwTrainConsumRecOld) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getStudentName() == null ? other.getId() == null : this.getStudentName().equals(other.getStudentName()))		
				        		&&(this.getStudentIdCard() == null ? other.getId() == null : this.getStudentIdCard().equals(other.getStudentIdCard()))		
				        		&&(this.getStudnetPhoneNum() == null ? other.getId() == null : this.getStudnetPhoneNum().equals(other.getStudnetPhoneNum()))		
				        		&&(this.getTrainEndTime() == null ? other.getId() == null : this.getTrainEndTime().equals(other.getTrainEndTime()))		
				        		&&(this.getTrainLessionNum() == null ? other.getId() == null : this.getTrainLessionNum().equals(other.getTrainLessionNum()))		
				        		&&(this.getIsSignAgreement() == null ? other.getId() == null : this.getIsSignAgreement().equals(other.getIsSignAgreement()))		
				        		&&(this.getStudentLicenceCode() == null ? other.getId() == null : this.getStudentLicenceCode().equals(other.getStudentLicenceCode()))		
				        		&&(this.getTrainAllowBill() == null ? other.getId() == null : this.getTrainAllowBill().equals(other.getTrainAllowBill()))		
				        		&&(this.getPeriod() == null ? other.getId() == null : this.getPeriod().equals(other.getPeriod()))		
				        		&&(this.getWorkType() == null ? other.getId() == null : this.getWorkType().equals(other.getWorkType()))		
				        		&&(this.getWorkAgreeStartTime() == null ? other.getId() == null : this.getWorkAgreeStartTime().equals(other.getWorkAgreeStartTime()))		
				        		&&(this.getWorkAgreeEndTime() == null ? other.getId() == null : this.getWorkAgreeEndTime().equals(other.getWorkAgreeEndTime()))		
				        		&&(this.getCertNum() == null ? other.getId() == null : this.getCertNum().equals(other.getCertNum()))		
				        		&&(this.getCertReginTime() == null ? other.getId() == null : this.getCertReginTime().equals(other.getCertReginTime()))		
				        		&&(this.getOrganCode() == null ? other.getId() == null : this.getOrganCode().equals(other.getOrganCode()))		
				        		&&(this.getCompName() == null ? other.getId() == null : this.getCompName().equals(other.getCompName()))		
				        		&&(this.getCompPhoneNum() == null ? other.getId() == null : this.getCompPhoneNum().equals(other.getCompPhoneNum()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());		
		        result = prime * result + ((getStudentIdCard() == null) ? 0 : getStudentIdCard().hashCode());		
		        result = prime * result + ((getStudnetPhoneNum() == null) ? 0 : getStudnetPhoneNum().hashCode());		
		        result = prime * result + ((getTrainEndTime() == null) ? 0 : getTrainEndTime().hashCode());		
		        result = prime * result + ((getTrainLessionNum() == null) ? 0 : getTrainLessionNum().hashCode());		
		        result = prime * result + ((getIsSignAgreement() == null) ? 0 : getIsSignAgreement().hashCode());		
		        result = prime * result + ((getStudentLicenceCode() == null) ? 0 : getStudentLicenceCode().hashCode());		
		        result = prime * result + ((getTrainAllowBill() == null) ? 0 : getTrainAllowBill().hashCode());		
		        result = prime * result + ((getPeriod() == null) ? 0 : getPeriod().hashCode());		
		        result = prime * result + ((getWorkType() == null) ? 0 : getWorkType().hashCode());		
		        result = prime * result + ((getWorkAgreeStartTime() == null) ? 0 : getWorkAgreeStartTime().hashCode());		
		        result = prime * result + ((getWorkAgreeEndTime() == null) ? 0 : getWorkAgreeEndTime().hashCode());		
		        result = prime * result + ((getCertNum() == null) ? 0 : getCertNum().hashCode());		
		        result = prime * result + ((getCertReginTime() == null) ? 0 : getCertReginTime().hashCode());		
		        result = prime * result + ((getOrganCode() == null) ? 0 : getOrganCode().hashCode());		
		        result = prime * result + ((getCompName() == null) ? 0 : getCompName().hashCode());		
		        result = prime * result + ((getCompPhoneNum() == null) ? 0 : getCompPhoneNum().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
