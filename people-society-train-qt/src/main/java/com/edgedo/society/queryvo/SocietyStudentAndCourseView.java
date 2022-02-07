package com.edgedo.society.queryvo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.entity.SocietyStudentAndNode;

import java.util.List;
import java.util.Map;

public class SocietyStudentAndCourseView extends SocietyStudentAndCourse {

    /**
     * 属性描述:课程简介
     */
    java.lang.String courseDesc;
    //课程中的章节
    List<SocietySchoolCourseNodeView> courseNodes = null;
    //学员的学习记录
    List<SocietyStudentAndNodeView> stuNodes = null;

    //课程的考试状态
    private String paperState;
    //剩余时间
    private String remainingTime;
    //是否能学习
    private String isStudy;
    //学生手机机型
    private String phoneTypeFlag;
    //班级学习状态
    private String classState;
    //培训机构名字
    private String ownerSchoolName;
    //培训期间
    private String studyTime;
    //平台名称
    private String platformName;
    //证书编号
    private String cenfiCode;

    public String getClassState() {
        return classState;
    }

    public void setClassState(String classState) {
        this.classState = classState;
    }

    public String getOwnerSchoolName() {
        return ownerSchoolName;
    }

    public void setOwnerSchoolName(String ownerSchoolName) {
        this.ownerSchoolName = ownerSchoolName;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getCenfiCode() {
        return cenfiCode;
    }

    public void setCenfiCode(String cenfiCode) {
        this.cenfiCode = cenfiCode;
    }

    public String getPhoneTypeFlag() {
        return phoneTypeFlag;
    }

    public void setPhoneTypeFlag(String phoneTypeFlag) {
        this.phoneTypeFlag = phoneTypeFlag;
    }



    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getIsStudy() {
        return isStudy;
    }

    public void setIsStudy(String isStudy) {
        this.isStudy = isStudy;
    }

    public String getPaperState() {
        return paperState;
    }

    public void setPaperState(String paperState) {
        this.paperState = paperState;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public List<SocietySchoolCourseNodeView> getCourseNodes() {
        return courseNodes;
    }

    public void setCourseNodes(List<SocietySchoolCourseNodeView> courseNodes) {
        this.courseNodes = courseNodes;
    }

    public List<SocietyStudentAndNodeView> getStuNodes() {
        return stuNodes;
    }

    public void setStuNodes(List<SocietyStudentAndNodeView> stuNodes) {
        this.stuNodes = stuNodes;
    }
}
