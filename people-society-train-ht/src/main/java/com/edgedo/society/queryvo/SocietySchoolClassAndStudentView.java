package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietySchoolClassAndStudent;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SocietySchoolClassAndStudentView extends SocietySchoolClassAndStudent {
    private Integer studentLeranProgressStart;
    private Integer studentLeranProgressEnd;
    private String courseAndStudentId;

    private String courseName;

    private BigDecimal courseProgress;
    private String courseId;

    private List<String> classIdList;


    //班级编码
    private String classCode;
    //文件生成时间  抽查时间
    private String createFileTime;
    //平台名称
    private String platformName;
    //总课时
    private Integer courseLength;
    //班级总人数
    private Integer classTotalPerson;
    //班级培训时间开始
    private String classStartTimeStr;
    //班级培训结束
    private String classEndTimeStr;
    //抽查阶段
    private String queryTime;
    //抽查方式
    private String queryMode;
    //实际班级人数
    private Integer actualPerson;
    //班级完成进度
    private String classProgre;

    private List<SocietySchoolClassAndStudentView> stuClassList;

    //性别
    private String sex;
    //人员类型
    private String studentType;
    //学习情况
    private String studyInfo;
    //证书类别
    private String cefiType;
    //电话
    private String studentPhone;

    //章节
    private List<SocietySchoolCourseNodeView> nodeList;

    public List<SocietySchoolClassAndStudentView> getStuClassList() {
        return stuClassList;
    }

    public void setStuClassList(List<SocietySchoolClassAndStudentView> stuClassList) {
        this.stuClassList = stuClassList;
    }

    public List<SocietySchoolCourseNodeView> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<SocietySchoolCourseNodeView> nodeList) {
        this.nodeList = nodeList;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getStudyInfo() {
        return studyInfo;
    }

    public void setStudyInfo(String studyInfo) {
        this.studyInfo = studyInfo;
    }

    public String getCefiType() {
        return cefiType;
    }

    public void setCefiType(String cefiType) {
        this.cefiType = cefiType;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public String getQueryMode() {
        return queryMode;
    }

    public void setQueryMode(String queryMode) {
        this.queryMode = queryMode;
    }

    public Integer getActualPerson() {
        return actualPerson;
    }

    public void setActualPerson(Integer actualPerson) {
        this.actualPerson = actualPerson;
    }

    public String getClassProgre() {
        return classProgre;
    }

    public void setClassProgre(String classProgre) {
        this.classProgre = classProgre;
    }

    public String getClassStartTimeStr() {
        return classStartTimeStr;
    }

    public void setClassStartTimeStr(String classStartTimeStr) {
        this.classStartTimeStr = classStartTimeStr;
    }

    public String getClassEndTimeStr() {
        return classEndTimeStr;
    }

    public void setClassEndTimeStr(String classEndTimeStr) {
        this.classEndTimeStr = classEndTimeStr;
    }

    public Integer getClassTotalPerson() {
        return classTotalPerson;
    }

    public void setClassTotalPerson(Integer classTotalPerson) {
        this.classTotalPerson = classTotalPerson;
    }

    public Integer getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(Integer courseLength) {
        this.courseLength = courseLength;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getCreateFileTime() {
        return createFileTime;
    }

    public void setCreateFileTime(String createFileTime) {
        this.createFileTime = createFileTime;
    }

    public List<String> getClassIdList() {
        return classIdList;
    }

    public void setClassIdList(List<String> classIdList) {
        this.classIdList = classIdList;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getCourseProgress() {
        return courseProgress;
    }

    public void setCourseProgress(BigDecimal courseProgress) {
        this.courseProgress = courseProgress;
    }

    public String getCourseAndStudentId() {
        return courseAndStudentId;
    }

    public void setCourseAndStudentId(String courseAndStudentId) {
        this.courseAndStudentId = courseAndStudentId;
    }

    public Integer getStudentLeranProgressStart() {
        return studentLeranProgressStart;
    }

    public Integer getStudentLeranProgressEnd() {
        return studentLeranProgressEnd;
    }

    public void setStudentLeranProgressStart(Integer studentLeranProgressStart) {
        this.studentLeranProgressStart = studentLeranProgressStart;
    }

    public void setStudentLeranProgressEnd(Integer studentLeranProgressEnd) {
        this.studentLeranProgressEnd = studentLeranProgressEnd;
    }



}
