package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietySchoolCourse;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;

public class SocietySchoolCourseView extends SocietySchoolCourse {

    private  String isUpdateStudentCourseState;
    private  int showFlg ;
    //月份开始
    private String monthDayStart;
    //月份末尾
    private String monthDayEnd;

    //月份
    private String month;
    //年份
    private String year;
    //课程学习人数
    private Integer schoolUseCourseCount;
    //课程完成人数
    private Integer schoolCompleteCount;

    //文件标识
    private String fileIdentification;
    //确认日期
    private String isTimeStr;
    //付款学校
    private String paymentSchoolName;
    //收款学校
    private String collectionSchoolName;
    //收款金额
    private String paymentMoney;

    List<SocietySchoolClassAndCourseView> classCourseList;

    //合计
    private String totalMoney;

    public String getFileIdentification() {
        return fileIdentification;
    }

    public void setFileIdentification(String fileIdentification) {
        this.fileIdentification = fileIdentification;
    }

    public String getIsTimeStr() {
        return isTimeStr;
    }

    public void setIsTimeStr(String isTimeStr) {
        this.isTimeStr = isTimeStr;
    }

    public String getPaymentSchoolName() {
        return paymentSchoolName;
    }

    public void setPaymentSchoolName(String paymentSchoolName) {
        this.paymentSchoolName = paymentSchoolName;
    }

    public String getCollectionSchoolName() {
        return collectionSchoolName;
    }

    public void setCollectionSchoolName(String collectionSchoolName) {
        this.collectionSchoolName = collectionSchoolName;
    }

    public String getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(String paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public List<SocietySchoolClassAndCourseView> getClassCourseList() {
        return classCourseList;
    }

    public void setClassCourseList(List<SocietySchoolClassAndCourseView> classCourseList) {
        this.classCourseList = classCourseList;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getSchoolUseCourseCount() {
        return schoolUseCourseCount;
    }

    public void setSchoolUseCourseCount(Integer schoolUseCourseCount) {
        this.schoolUseCourseCount = schoolUseCourseCount;
    }

    public Integer getSchoolCompleteCount() {
        return schoolCompleteCount;
    }

    public void setSchoolCompleteCount(Integer schoolCompleteCount) {
        this.schoolCompleteCount = schoolCompleteCount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthDayStart() {
        return monthDayStart;
    }

    public void setMonthDayStart(String monthDayStart) {
        this.monthDayStart = monthDayStart;
    }

    public String getMonthDayEnd() {
        return monthDayEnd;
    }

    public void setMonthDayEnd(String monthDayEnd) {
        this.monthDayEnd = monthDayEnd;
    }

    public int getShowFlg() {
        return showFlg;
    }

    public void setShowFlg(int showFlg) {
        this.showFlg = showFlg;
    }

    private String nowSchoolId;

    public String getNowSchoolId() {
        return nowSchoolId;
    }

    public void setNowSchoolId(String nowSchoolId) {
        this.nowSchoolId = nowSchoolId;
    }

    //课程学习
    private Integer courseStuStudyNum;

    public Integer getCourseStuStudyNum() {
        return courseStuStudyNum;
    }

    public void setCourseStuStudyNum(Integer courseStuStudyNum) {
        this.courseStuStudyNum = courseStuStudyNum;
    }

    public String getIsUpdateStudentCourseState() {
        return isUpdateStudentCourseState;
    }

    public void setIsUpdateStudentCourseState(String isUpdateStudentCourseState) {
        this.isUpdateStudentCourseState = isUpdateStudentCourseState;
    }
}
