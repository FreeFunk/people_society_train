package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietyStudentTestPaperQuestion;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestionOption;

import java.util.List;

public class SocietyStudentTestPaperQuestionView extends SocietyStudentTestPaperQuestion {

    //试题选项列表
    private List<SocietyStudentTestPaperQuestionOptionView> questionOptionList;

    public List<SocietyStudentTestPaperQuestionOptionView> getQuestionOptionList() {
        return questionOptionList;
    }

    public void setQuestionOptionList(List<SocietyStudentTestPaperQuestionOptionView> questionOptionList) {
        this.questionOptionList = questionOptionList;
    }
}
