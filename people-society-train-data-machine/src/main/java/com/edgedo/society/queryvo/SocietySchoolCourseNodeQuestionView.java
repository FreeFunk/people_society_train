package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietySchoolCourseNodeOption;
import com.edgedo.society.entity.SocietySchoolCourseNodeQuestion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SocietySchoolCourseNodeQuestionView extends SocietySchoolCourseNodeQuestion {

    //学员和章节的关联ID
    private String ownerStudentAndNodeId;
    //学员id
    private String stuId;
    //学员选项
    private String studentAnswer;
    //是否正确
    private String answerIsRight;

    private String options;
    List<SocietySchoolCourseNodeOptionView> optionList;

    public List<SocietySchoolCourseNodeOptionView> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SocietySchoolCourseNodeOptionView> optionList) {
        this.optionList = optionList;
    }

    private List<SocietySchoolCourseNodeOption> nodeOptionList = new ArrayList<>();

    public List<SocietySchoolCourseNodeOption> getNodeOptionList() {
        return nodeOptionList;
    }

    public void setNodeOptionList(List<SocietySchoolCourseNodeOption> nodeOptionList) {
        this.nodeOptionList = nodeOptionList;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getAnswerIsRight() {
        return answerIsRight;
    }

    public void setAnswerIsRight(String answerIsRight) {
        this.answerIsRight = answerIsRight;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getOwnerStudentAndNodeId() {
        return ownerStudentAndNodeId;
    }

    public void setOwnerStudentAndNodeId(String ownerStudentAndNodeId) {
        this.ownerStudentAndNodeId = ownerStudentAndNodeId;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
