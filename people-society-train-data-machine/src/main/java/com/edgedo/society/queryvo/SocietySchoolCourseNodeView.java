package com.edgedo.society.queryvo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.edgedo.society.entity.SocietySchoolCourseNode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SocietySchoolCourseNodeView extends SocietySchoolCourseNode {

    /**
     * 属性描述:学员ID
     */
    java.lang.String studentId;

    /**
     * 属性描述:学员姓名
     */
    java.lang.String studentName;

    /**
     * 属性描述:身份证号
     */
    java.lang.String studentIdCardNum;

    /*
    * 题目数量
    * */
    private Integer questionNum;

    /*
    * 学习进度
    * */
    Integer nodeProgress;
    private java.lang.Integer nodeQuestionScore;
    private java.lang.String questionIsFinished;
    private java.lang.String questionIsPass;
    String learnIsFinished;


    Integer studyTimeLength;

    private String nodeId;
    private String examineState;
    private String stuAndCouId;
    private Integer rightNum;
    java.lang.Integer lastLearnLocation;

    public Integer getLastLearnLocation() {
        return lastLearnLocation;
    }

    public void setLastLearnLocation(Integer lastLearnLocation) {
        this.lastLearnLocation = lastLearnLocation;
    }

    //完成时间
    private Date finishTime;

    private String stuAndNodeId;

    public String getStuAndNodeId() {
        return stuAndNodeId;
    }

    public void setStuAndNodeId(String stuAndNodeId) {
        this.stuAndNodeId = stuAndNodeId;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }


    public Integer getRightNum() {
        return rightNum;
    }

    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
    }

    public String getStuAndCouId() {
        return stuAndCouId;
    }

    public void setStuAndCouId(String stuAndCouId) {
        this.stuAndCouId = stuAndCouId;
    }

    public String getExamineState() {
        return examineState;
    }

    public void setExamineState(String examineState) {
        this.examineState = examineState;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    private List<String> copyCourseId;

    public List<String> getCopyCourseId() {
        return copyCourseId;
    }

    public void setCopyCourseId(List<String> copyCourseId) {
        this.copyCourseId = copyCourseId;
    }

    public Integer getStudyTimeLength() {
        return studyTimeLength;
    }

    public void setStudyTimeLength(Integer studyTimeLength) {
        this.studyTimeLength = studyTimeLength;
    }

    @Override
    public Integer getQuestionNum() {
        return questionNum;
    }

    @Override
    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentIdCardNum() {
        return studentIdCardNum;
    }

    public void setStudentIdCardNum(String studentIdCardNum) {
        this.studentIdCardNum = studentIdCardNum;
    }

    public Integer getNodeProgress() {
        return nodeProgress;
    }

    public void setNodeProgress(Integer nodeProgress) {
        this.nodeProgress = nodeProgress;
    }

    public Integer getNodeQuestionScore() {
        return nodeQuestionScore;
    }

    public void setNodeQuestionScore(Integer nodeQuestionScore) {
        this.nodeQuestionScore = nodeQuestionScore;
    }

    public String getQuestionIsFinished() {
        return questionIsFinished;
    }

    public void setQuestionIsFinished(String questionIsFinished) {
        this.questionIsFinished = questionIsFinished;
    }

    public String getQuestionIsPass() {
        return questionIsPass;
    }

    public void setQuestionIsPass(String questionIsPass) {
        this.questionIsPass = questionIsPass;
    }


    public String getLearnIsFinished() {
        return learnIsFinished;
    }

    public void setLearnIsFinished(String learnIsFinished) {
        this.learnIsFinished = learnIsFinished;
    }
}
