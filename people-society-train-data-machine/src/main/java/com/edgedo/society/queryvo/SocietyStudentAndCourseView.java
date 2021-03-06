package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.entity.SocietyStudentAndNode;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SocietyStudentAndCourseView extends SocietyStudentAndCourse {
    Integer courseProgressStart;
    Integer courseProgressEnd;
    /*课程时长*/
    private Integer courseTimeLength;
    //学习时长
    private Integer studyTimeLength;

    private String stuPhone;
    private String isRealName;

    //是否评价
    private String isComment;
    //是否通过考试
    private String isTepar;

    //学校名字
    private String schoolName;

    //章节名称
    private String nodeName;
    //审核状态
    private String examineState;
    //审核原因
    private String examineReson;
    //班级编码
    private String classCode;

    //性别
    private String sex;
    //联系电话
    private String studentPohone;
    //平台注册时间
    private Date registerTime;
    //班级期限
    private String classTimePeriod;
    //学习起止时间
    private String studyTimePeriod;
    //学习方式
    private String studyMode;
    //课程形式
    private String courseForm;
    //人脸照
    private String facePhoto;


    //学习证明
    private String studyProve;
    //人脸认证
    private String faceAuthentication;
    //课程人脸
    private List<SocietyStudentStudyProcessFace> faceList;
    //考试正确率
    private BigDecimal testRightRate;
    //考试是否及格
    private String testIsPass;
    //考试得分
    private Integer testScore;
    //试卷名称
    private String testName;

    //习题学习时长
    private Integer questionTimeLength;
    //总学习时长
    private Integer totalTimeLength;

    //平台发证日期
    private Date certificateTime;
    //章节
    private List<SocietyStudentAndNodeView> nodeList;
    //讲师
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<SocietyStudentAndNodeView> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<SocietyStudentAndNodeView> nodeList) {
        this.nodeList = nodeList;
    }

    public Date getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(Date certificateTime) {
        this.certificateTime = certificateTime;
    }

    public Integer getTotalTimeLength() {
        return totalTimeLength;
    }

    public void setTotalTimeLength(Integer totalTimeLength) {
        this.totalTimeLength = totalTimeLength;
    }

    public Integer getQuestionTimeLength() {
        return questionTimeLength;
    }

    public void setQuestionTimeLength(Integer questionTimeLength) {
        this.questionTimeLength = questionTimeLength;
    }

    public BigDecimal getTestRightRate() {
        return testRightRate;
    }

    public void setTestRightRate(BigDecimal testRightRate) {
        this.testRightRate = testRightRate;
    }

    public String getTestIsPass() {
        return testIsPass;
    }

    public void setTestIsPass(String testIsPass) {
        this.testIsPass = testIsPass;
    }

    public Integer getTestScore() {
        return testScore;
    }

    public void setTestScore(Integer testScore) {
        this.testScore = testScore;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getStudyProve() {
        return studyProve;
    }

    public void setStudyProve(String studyProve) {
        this.studyProve = studyProve;
    }

    public String getFaceAuthentication() {
        return faceAuthentication;
    }

    public void setFaceAuthentication(String faceAuthentication) {
        this.faceAuthentication = faceAuthentication;
    }

    public List<SocietyStudentStudyProcessFace> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<SocietyStudentStudyProcessFace> faceList) {
        this.faceList = faceList;
    }

    public String getFacePhoto() {
        return facePhoto;
    }

    public void setFacePhoto(String facePhoto) {
        this.facePhoto = facePhoto;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentPohone() {
        return studentPohone;
    }

    public void setStudentPohone(String studentPohone) {
        this.studentPohone = studentPohone;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getClassTimePeriod() {
        return classTimePeriod;
    }

    public void setClassTimePeriod(String classTimePeriod) {
        this.classTimePeriod = classTimePeriod;
    }

    public String getStudyTimePeriod() {
        return studyTimePeriod;
    }

    public void setStudyTimePeriod(String studyTimePeriod) {
        this.studyTimePeriod = studyTimePeriod;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public String getCourseForm() {
        return courseForm;
    }

    public void setCourseForm(String courseForm) {
        this.courseForm = courseForm;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getExamineState() {
        return examineState;
    }

    public void setExamineState(String examineState) {
        this.examineState = examineState;
    }

    public String getExamineReson() {
        return examineReson;
    }

    public void setExamineReson(String examineReson) {
        this.examineReson = examineReson;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
    }

    public String getIsTepar() {
        return isTepar;
    }

    public void setIsTepar(String isTepar) {
        this.isTepar = isTepar;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }

    public Integer getCourseTimeLength() {
        return courseTimeLength;
    }

    public void setCourseTimeLength(Integer courseTimeLength) {
        this.courseTimeLength = courseTimeLength;
    }

    public Integer getStudyTimeLength() {
        return studyTimeLength;
    }

    public void setStudyTimeLength(Integer studyTimeLength) {
        this.studyTimeLength = studyTimeLength;
    }

    public Integer getCourseProgressStart() {
        return courseProgressStart;
    }

    public void setCourseProgressStart(Integer courseProgressStart) {
        this.courseProgressStart = courseProgressStart;
    }

    public Integer getCourseProgressEnd() {
        return courseProgressEnd;
    }

    public void setCourseProgressEnd(Integer courseProgressEnd) {
        this.courseProgressEnd = courseProgressEnd;
    }
}
