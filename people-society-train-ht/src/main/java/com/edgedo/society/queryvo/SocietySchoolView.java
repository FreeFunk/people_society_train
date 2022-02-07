package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietySchool;

public class SocietySchoolView extends SocietySchool {

    //课程学习人数
    private Integer schoolUseCourseCount;
    //课程完成人数
    private Integer schoolCompleteCount;
    //课程id
    private String ownerCourseId;
    //课程名称
    private String ownerCourseName;

    //月份
    private String month;
    //月份开始
    private String monthDayStart;
    //月份末尾
    private String monthDayEnd;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getSchoolCompleteCount() {
        return schoolCompleteCount;
    }

    public void setSchoolCompleteCount(Integer schoolCompleteCount) {
        this.schoolCompleteCount = schoolCompleteCount;
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

    public String getOwnerCourseName() {
        return ownerCourseName;
    }

    public void setOwnerCourseName(String ownerCourseName) {
        this.ownerCourseName = ownerCourseName;
    }

    public String getOwnerCourseId() {
        return ownerCourseId;
    }

    public void setOwnerCourseId(String ownerCourseId) {
        this.ownerCourseId = ownerCourseId;
    }

    public Integer getSchoolUseCourseCount() {
        return schoolUseCourseCount;
    }

    public void setSchoolUseCourseCount(Integer schoolUseCourseCount) {
        this.schoolUseCourseCount = schoolUseCourseCount;
    }
}
