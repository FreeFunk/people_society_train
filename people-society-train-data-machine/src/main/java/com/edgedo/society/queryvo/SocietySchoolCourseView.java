package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietySchoolCourse;

public class SocietySchoolCourseView extends SocietySchoolCourse {

    private  String isUpdateStudentCourseState;


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
