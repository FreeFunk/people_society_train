package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietySchoolCourseNodeQuestion;

import java.util.ArrayList;
import java.util.List;

public class SocietySchoolCourseNodeQuestionView extends SocietySchoolCourseNodeQuestion {
    //学生的节点
    private String stuNodeId;
    //选择的那个节点
    private String selectOpId;
    /**
     * @Author WangZhen
     * @Description 答题0 未答题，1正确,-1错误
     * @Date 2020/7/8 15:37
     **/
    private String answerState;

    private List<SocietySchoolCourseNodeOptionView> ops = new ArrayList<>();

    public List<SocietySchoolCourseNodeOptionView> getOps() {
        return ops;
    }

    public void setOps(List<SocietySchoolCourseNodeOptionView> ops) {
        this.ops = ops;
    }

    public String getStuNodeId() {
        return stuNodeId;
    }

    public void setStuNodeId(String stuNodeId) {
        this.stuNodeId = stuNodeId;
    }

    public String getSelectOpId() {
        return selectOpId;
    }

    public void setSelectOpId(String selectOpId) {
        this.selectOpId = selectOpId;
    }

    public String getAnswerState() {
        return answerState;
    }

    public void setAnswerState(String answerState) {
        this.answerState = answerState;
    }


}
