package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietySchoolClassAndCourse;

public class SocietySchoolClassAndCourseView extends SocietySchoolClassAndCourse {

    //课程名称
    private String courseName;
    //合同约定比例
    private String contractRegulations;
    //班级名称
    private String className;
    //补贴标准
    private String subsidyStandard;
    //培训人数
    private int classStudyNum;
    //技术费用
    private String technologyMoney;
    //服务费用
    private String serviceMoney;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getContractRegulations() {
        return contractRegulations;
    }

    public void setContractRegulations(String contractRegulations) {
        this.contractRegulations = contractRegulations;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubsidyStandard() {
        return subsidyStandard;
    }

    public void setSubsidyStandard(String subsidyStandard) {
        this.subsidyStandard = subsidyStandard;
    }

    public int getClassStudyNum() {
        return classStudyNum;
    }

    public void setClassStudyNum(int classStudyNum) {
        this.classStudyNum = classStudyNum;
    }

    public String getTechnologyMoney() {
        return technologyMoney;
    }

    public void setTechnologyMoney(String technologyMoney) {
        this.technologyMoney = technologyMoney;
    }

    public String getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
        this.serviceMoney = serviceMoney;
    }
}
