package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietyStudent;

import java.util.Date;
import java.util.List;

public class SocietySchoolClassView extends SocietySchoolClass {

    //生成日期
    private String createTimeStr;
    //平台名称
    private String platformName;
    //培训时间
    private String studyTime;
    //培训工种
    private String studyType;
    //培训等级
    private String studyLevel;
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    private String courseId;

    private String courseName;
    private List<String> classAdminIdList;

    //班级状态
    private String classState;
    //班级进度
    private String classStudyProgree;
    //培训平台
    private String trainShow;
    //培训类型
    private String trainType;
    //课时
    private Integer courseTotalNum;
    //抽查方式
    private String checkType;
    //抽查时间
    private Date checkTime;
    //抽查学员数量
    private String checkStudentNum;
    //学员信息
    private List<SocietyStudentView> studentList;
    //开班时间
    private String classStartTimeStr;
    //结束时间
    private String classEndTimeStr;
    //实际培训人数
    private int studyPersonNum;
    //实名人数
    private int isRealNameNum;
    //实名认证率
    private String isRealNameRate;
    //学习人数
    private int classStudyNum;
    //学习率
    private String classStudyRate;
    //完成人数
    private int classUseNum;
    //完成率
    private String classUseRate;
    //未完成人数
    private int noStudyNum;
    //人员类型
    private String peopleType;

    public String getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }

    public int getIsRealNameNum() {
        return isRealNameNum;
    }

    public void setIsRealNameNum(int isRealNameNum) {
        this.isRealNameNum = isRealNameNum;
    }

    public String getIsRealNameRate() {
        return isRealNameRate;
    }

    public void setIsRealNameRate(String isRealNameRate) {
        this.isRealNameRate = isRealNameRate;
    }

    public int getClassStudyNum() {
        return classStudyNum;
    }

    public void setClassStudyNum(int classStudyNum) {
        this.classStudyNum = classStudyNum;
    }

    public String getClassStudyRate() {
        return classStudyRate;
    }

    public void setClassStudyRate(String classStudyRate) {
        this.classStudyRate = classStudyRate;
    }

    public int getClassUseNum() {
        return classUseNum;
    }

    public void setClassUseNum(int classUseNum) {
        this.classUseNum = classUseNum;
    }

    public String getClassUseRate() {
        return classUseRate;
    }

    public void setClassUseRate(String classUseRate) {
        this.classUseRate = classUseRate;
    }

    public int getNoStudyNum() {
        return noStudyNum;
    }

    public void setNoStudyNum(int noStudyNum) {
        this.noStudyNum = noStudyNum;
    }

    public int getStudyPersonNum() {
        return studyPersonNum;
    }

    public void setStudyPersonNum(int studyPersonNum) {
        this.studyPersonNum = studyPersonNum;
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

    public Integer getCourseTotalNum() {
        return courseTotalNum;
    }

    public void setCourseTotalNum(Integer courseTotalNum) {
        this.courseTotalNum = courseTotalNum;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckStudentNum() {
        return checkStudentNum;
    }

    public void setCheckStudentNum(String checkStudentNum) {
        this.checkStudentNum = checkStudentNum;
    }

    public List<SocietyStudentView> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<SocietyStudentView> studentList) {
        this.studentList = studentList;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainShow() {
        return trainShow;
    }

    public void setTrainShow(String trainShow) {
        this.trainShow = trainShow;
    }

    public String getClassStudyProgree() {
        return classStudyProgree;
    }

    public void setClassStudyProgree(String classStudyProgree) {
        this.classStudyProgree = classStudyProgree;
    }

    public String getClassState() {
        return classState;
    }

    public void setClassState(String classState) {
        this.classState = classState;
    }

    public List<String> getClassAdminIdList() {
        return classAdminIdList;
    }

    public void setClassAdminIdList(List<String> classAdminIdList) {
        this.classAdminIdList = classAdminIdList;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
