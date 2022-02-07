package com.edgedo.society.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("society_student_and_node")
public class SocietyStudentAndNode implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:所属课程关联表ID
	 */
	@TableField(value="OWNER_STUDENT_AND_COURSE_ID",exist=true)
	java.lang.String ownerStudentAndCourseId;
	
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
	 * 属性描述:学员ID
	 */
	@TableField(value="STUDENT_ID",exist=true)
	java.lang.String studentId;
	
	/**
	 * 属性描述:学员姓名
	 */
	@TableField(value="STUDENT_NAME",exist=true)
	java.lang.String studentName;
	
	/**
	 * 属性描述:身份证号
	 */
	@TableField(value="STUDENT_ID_CARD_NUM",exist=true)
	java.lang.String studentIdCardNum;
	
	/**
	 * 属性描述:课程小节ID
	 */
	@TableField(value="NODE_ID",exist=true)
	java.lang.String nodeId;

	/**
	 * 属性描述:课程小节名字
	 */
	@TableField(value="NODE_NAME",exist=true)
	java.lang.String nodeName;
	/**
	 * 属性描述:所属课程id
	 */
	@TableField(value="OWNER_COURSE_ID",exist=true)
	java.lang.String ownerCourseId;
	/**
	 * 属性描述:所属课程名
	 */
	@TableField(value="OWNER_COURSE_NAME",exist=true)
	java.lang.String ownerCourseName;


	/**
	 * 属性描述:课程小节进度
	 */
	@TableField(value="NODE_PROGRESS",exist=true)
	java.math.BigDecimal nodeProgress;

	/**
	 * 属性描述:上次学习位置(秒)
	 */
	@TableField(value="LAST_LEARN_LOCATION",exist=true)
	java.lang.Integer lastLearnLocation;
	
	/**
	 * 属性描述:学习是否完成(1:是,0:否)
	 */
	@TableField(value="LEARN_IS_FINISHED",exist=true)
	java.lang.String learnIsFinished;
	
	/**
	 * 属性描述:上次答题ID
	 */
	@TableField(value="LAST_QUESTION_ID",exist=true)
	java.lang.String lastQuestionId;
	
	/**
	 * 属性描述:小节习题得分
	 */
	@TableField(value="NODE_QUESTION_SCORE",exist=true)
	java.lang.Integer nodeQuestionScore;
	
	/**
	 * 属性描述:习题是否完成(1:是,0:否)
	 */
	@TableField(value="QUESTION_IS_FINISHED",exist=true)
	java.lang.String questionIsFinished;
	
	/**
	 * 属性描述:习题是否合格(1:是,0:否)
	 */
	@TableField(value="QUESTION_IS_PASS",exist=true)
	java.lang.String questionIsPass;

	/**
	 * 属性描述:习题总数
	 */
	@TableField(value="TOTAL_QUESTION_NUM",exist=true)
	java.lang.Integer totalQuestionNum;

	/**
	 * @Author WangZhen
	 * @Description 学习时长
	 * @Date 2020/5/6 20:05
	 **/
	@TableField(value="STUDY_TIME_LENGTH",exist=true)
	Integer studyTimeLength;

	/**
	 * 创建时间
	 **/
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;

	/**
	 * 数据类型
	 **/
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String datastate;

	/**
	 * 审核状态
	 **/
	@TableField(value="EXAMINE_STATE",exist=true)
	java.lang.String examineState;

	/**
	 * 完成时间
	 **/
	@TableField(value="FINISH_TIME",exist=true)
	java.util.Date finishTime;

	/**
	 * 属性描述:最大学习位置(秒)
	 */
	@TableField(value="MAX_LEARN_LOCATION",exist=true)
	java.lang.Integer maxLearnLocation;

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

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getXianquId() {
		return xianquId;
	}

	public void setXianquId(String xianquId) {
		this.xianquId = xianquId;
	}

	public String getXianquName() {
		return xianquName;
	}

	public void setXianquName(String xianquName) {
		this.xianquName = xianquName;
	}

	public Integer getMaxLearnLocation() {
		return maxLearnLocation;
	}

	public void setMaxLearnLocation(Integer maxLearnLocation) {
		this.maxLearnLocation = maxLearnLocation;
	}

	public String getExamineState() {
		return examineState;
	}

	public void setExamineState(String examineState) {
		this.examineState = examineState;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getDatastate() {
		return datastate;
	}

	public void setDatastate(String datastate) {
		this.datastate = datastate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStudyTimeLength() {
		return studyTimeLength;
	}

	public void setStudyTimeLength(Integer studyTimeLength) {
		this.studyTimeLength = studyTimeLength;
	}

	public String getOwnerSchoolName() {
		return ownerSchoolName;
	}

	public void setOwnerSchoolName(String ownerSchoolName) {
		this.ownerSchoolName = ownerSchoolName;
	}

	public String getOwnerCourseId() {
		return ownerCourseId;
	}

	public void setOwnerCourseId(String ownerCourseId) {
		this.ownerCourseId = ownerCourseId;
	}

	public String getOwnerCourseName() {
		return ownerCourseName;
	}

	public void setOwnerCourseName(String ownerCourseName) {
		this.ownerCourseName = ownerCourseName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getTotalQuestionNum() {
		return totalQuestionNum;
	}

	public void setTotalQuestionNum(Integer totalQuestionNum) {
		this.totalQuestionNum = totalQuestionNum;
	}

	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.lang.String getOwnerStudentAndCourseId(){
		return this.ownerStudentAndCourseId;
	}
	
	public void setOwnerStudentAndCourseId(java.lang.String ownerStudentAndCourseId){
		this.ownerStudentAndCourseId=ownerStudentAndCourseId;
	}
	
	
	public java.lang.String getOwnerSchoolId(){
		return this.ownerSchoolId;
	}
	
	public void setOwnerSchoolId(java.lang.String ownerSchoolId){
		this.ownerSchoolId=ownerSchoolId;
	}
	
	
	public java.lang.String getStudentId(){
		return this.studentId;
	}
	
	public void setStudentId(java.lang.String studentId){
		this.studentId=studentId;
	}
	
	
	public java.lang.String getStudentName(){
		return this.studentName;
	}
	
	public void setStudentName(java.lang.String studentName){
		this.studentName=studentName;
	}
	
	
	public java.lang.String getStudentIdCardNum(){
		return this.studentIdCardNum;
	}
	
	public void setStudentIdCardNum(java.lang.String studentIdCardNum){
		this.studentIdCardNum=studentIdCardNum;
	}
	
	
	public java.lang.String getNodeId(){
		return this.nodeId;
	}
	
	public void setNodeId(java.lang.String nodeId){
		this.nodeId=nodeId;
	}
	
	
	public java.math.BigDecimal getNodeProgress(){
		return this.nodeProgress;
	}
	
	public void setNodeProgress(java.math.BigDecimal nodeProgress){
		this.nodeProgress=nodeProgress;
	}
	
	
	public java.lang.Integer getLastLearnLocation(){
		return this.lastLearnLocation;
	}
	
	public void setLastLearnLocation(java.lang.Integer lastLearnLocation){
		this.lastLearnLocation=lastLearnLocation;
	}
	
	
	public java.lang.String getLearnIsFinished(){
		return this.learnIsFinished;
	}
	
	public void setLearnIsFinished(java.lang.String learnIsFinished){
		this.learnIsFinished=learnIsFinished;
	}
	
	
	public java.lang.String getLastQuestionId(){
		return this.lastQuestionId;
	}
	
	public void setLastQuestionId(java.lang.String lastQuestionId){
		this.lastQuestionId=lastQuestionId;
	}
	
	
	public java.lang.Integer getNodeQuestionScore(){
		return this.nodeQuestionScore;
	}
	
	public void setNodeQuestionScore(java.lang.Integer nodeQuestionScore){
		this.nodeQuestionScore=nodeQuestionScore;
	}
	
	
	public java.lang.String getQuestionIsFinished(){
		return this.questionIsFinished;
	}
	
	public void setQuestionIsFinished(java.lang.String questionIsFinished){
		this.questionIsFinished=questionIsFinished;
	}
	
	
	public java.lang.String getQuestionIsPass(){
		return this.questionIsPass;
	}
	
	public void setQuestionIsPass(java.lang.String questionIsPass){
		this.questionIsPass=questionIsPass;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", ownerStudentAndCourseId=").append(ownerStudentAndCourseId);			
			sb.append(", ownerSchoolId=").append(ownerSchoolId);			
			sb.append(", studentId=").append(studentId);			
			sb.append(", studentName=").append(studentName);			
			sb.append(", studentIdCardNum=").append(studentIdCardNum);			
			sb.append(", nodeId=").append(nodeId);			
			sb.append(", nodeProgress=").append(nodeProgress);			
			sb.append(", lastLearnLocation=").append(lastLearnLocation);			
			sb.append(", learnIsFinished=").append(learnIsFinished);			
			sb.append(", lastQuestionId=").append(lastQuestionId);			
			sb.append(", nodeQuestionScore=").append(nodeQuestionScore);			
			sb.append(", questionIsFinished=").append(questionIsFinished);			
			sb.append(", questionIsPass=").append(questionIsPass);			
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
        SocietyStudentAndNode other = (SocietyStudentAndNode) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getOwnerStudentAndCourseId() == null ? other.getId() == null : this.getOwnerStudentAndCourseId().equals(other.getOwnerStudentAndCourseId()))		
				        		&&(this.getOwnerSchoolId() == null ? other.getId() == null : this.getOwnerSchoolId().equals(other.getOwnerSchoolId()))		
				        		&&(this.getStudentId() == null ? other.getId() == null : this.getStudentId().equals(other.getStudentId()))		
				        		&&(this.getStudentName() == null ? other.getId() == null : this.getStudentName().equals(other.getStudentName()))		
				        		&&(this.getStudentIdCardNum() == null ? other.getId() == null : this.getStudentIdCardNum().equals(other.getStudentIdCardNum()))		
				        		&&(this.getNodeId() == null ? other.getId() == null : this.getNodeId().equals(other.getNodeId()))		
				        		&&(this.getNodeProgress() == null ? other.getId() == null : this.getNodeProgress().equals(other.getNodeProgress()))		
				        		&&(this.getLastLearnLocation() == null ? other.getId() == null : this.getLastLearnLocation().equals(other.getLastLearnLocation()))		
				        		&&(this.getLearnIsFinished() == null ? other.getId() == null : this.getLearnIsFinished().equals(other.getLearnIsFinished()))		
				        		&&(this.getLastQuestionId() == null ? other.getId() == null : this.getLastQuestionId().equals(other.getLastQuestionId()))		
				        		&&(this.getNodeQuestionScore() == null ? other.getId() == null : this.getNodeQuestionScore().equals(other.getNodeQuestionScore()))		
				        		&&(this.getQuestionIsFinished() == null ? other.getId() == null : this.getQuestionIsFinished().equals(other.getQuestionIsFinished()))		
				        		&&(this.getQuestionIsPass() == null ? other.getId() == null : this.getQuestionIsPass().equals(other.getQuestionIsPass()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getOwnerStudentAndCourseId() == null) ? 0 : getOwnerStudentAndCourseId().hashCode());		
		        result = prime * result + ((getOwnerSchoolId() == null) ? 0 : getOwnerSchoolId().hashCode());		
		        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());		
		        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());		
		        result = prime * result + ((getStudentIdCardNum() == null) ? 0 : getStudentIdCardNum().hashCode());		
		        result = prime * result + ((getNodeId() == null) ? 0 : getNodeId().hashCode());		
		        result = prime * result + ((getNodeProgress() == null) ? 0 : getNodeProgress().hashCode());		
		        result = prime * result + ((getLastLearnLocation() == null) ? 0 : getLastLearnLocation().hashCode());		
		        result = prime * result + ((getLearnIsFinished() == null) ? 0 : getLearnIsFinished().hashCode());		
		        result = prime * result + ((getLastQuestionId() == null) ? 0 : getLastQuestionId().hashCode());		
		        result = prime * result + ((getNodeQuestionScore() == null) ? 0 : getNodeQuestionScore().hashCode());		
		        result = prime * result + ((getQuestionIsFinished() == null) ? 0 : getQuestionIsFinished().hashCode());		
		        result = prime * result + ((getQuestionIsPass() == null) ? 0 : getQuestionIsPass().hashCode());		
		;
        return result;
    }

}
