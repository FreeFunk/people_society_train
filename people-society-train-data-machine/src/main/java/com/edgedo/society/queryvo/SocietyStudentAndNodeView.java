package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietyStudentAndNode;

import java.math.BigDecimal;
import java.util.Date;

public class SocietyStudentAndNodeView extends SocietyStudentAndNode {

    private Integer rightNum;
    //人脸不合格次数
    private Integer faceIsPass;
    //规定人脸次数
    private Integer facePass;
    private String classId;

    String finishStartTime;
    String finishEndTime;
    Integer nodeLength;
    BigDecimal orderNum;
    private Date questionTime;

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public BigDecimal getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(BigDecimal orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getNodeLength() {
        return nodeLength;
    }

    public void setNodeLength(Integer nodeLength) {
        this.nodeLength = nodeLength;
    }

    public String getFinishStartTime() {
        return finishStartTime;
    }

    public void setFinishStartTime(String finishStartTime) {
        this.finishStartTime = finishStartTime;
    }

    public String getFinishEndTime() {
        return finishEndTime;
    }

    public void setFinishEndTime(String finishEndTime) {
        this.finishEndTime = finishEndTime;
    }

    public Integer getFacePass() {
        return facePass;
    }

    public void setFacePass(Integer facePass) {
        this.facePass = facePass;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getFaceIsPass() {
        return faceIsPass;
    }

    public void setFaceIsPass(Integer faceIsPass) {
        this.faceIsPass = faceIsPass;
    }

    public Integer getRightNum() {
        return rightNum;
    }

    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
    }
}
