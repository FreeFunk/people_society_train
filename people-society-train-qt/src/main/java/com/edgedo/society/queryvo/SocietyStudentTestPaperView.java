package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietyStudentTestPaper;

public class SocietyStudentTestPaperView extends SocietyStudentTestPaper {

    private int optionQuestionCount;

    private int judgeQuestionCount;

    public int getOptionQuestionCount() {
        return optionQuestionCount;
    }

    public void setOptionQuestionCount(int optionQuestionCount) {
        this.optionQuestionCount = optionQuestionCount;
    }

    public int getJudgeQuestionCount() {
        return judgeQuestionCount;
    }

    public void setJudgeQuestionCount(int judgeQuestionCount) {
        this.judgeQuestionCount = judgeQuestionCount;
    }
}
