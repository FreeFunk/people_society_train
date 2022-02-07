package com.edgedo.society.queryvo;

import com.edgedo.society.entity.SocietyTestPaperQuestion;
import com.edgedo.society.entity.SocietyTestPaperQuestionOption;

import java.util.ArrayList;
import java.util.List;

public class SocietyTestPaperQuestionView extends SocietyTestPaperQuestion {
    private String options;

    private String isAnswerQuestion;

    private String studentId;

    private Integer getScore;
    private String testPaperQuestionName;
    private Integer testPaperQuestionScore;

    private String testPaperQuestionId;

    private String testPaperQuestionType;

    private List<SocietyTestPaperQuestionOption> testPaperQuestionOptionList = new ArrayList<>();

    private String studentAnswer;

    private String answerIsRight;


    public String getAnswerIsRight() {
        return answerIsRight;
    }

    public void setAnswerIsRight(String answerIsRight) {
        this.answerIsRight = answerIsRight;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public List<SocietyTestPaperQuestionOption> getTestPaperQuestionOptionList() {
        return testPaperQuestionOptionList;
    }

    public void setTestPaperQuestionOptionList(List<SocietyTestPaperQuestionOption> testPaperQuestionOptionList) {
        this.testPaperQuestionOptionList = testPaperQuestionOptionList;
    }

    public String getTestPaperQuestionType() {
        return testPaperQuestionType;
    }

    public void setTestPaperQuestionType(String testPaperQuestionType) {
        this.testPaperQuestionType = testPaperQuestionType;
    }

    public String getTestPaperQuestionId() {
        return testPaperQuestionId;
    }

    public void setTestPaperQuestionId(String testPaperQuestionId) {
        this.testPaperQuestionId = testPaperQuestionId;
    }

    public String getTestPaperQuestionName() {
        return testPaperQuestionName;
    }

    public void setTestPaperQuestionName(String testPaperQuestionName) {
        this.testPaperQuestionName = testPaperQuestionName;
    }

    public Integer getTestPaperQuestionScore() {
        return testPaperQuestionScore;
    }

    public void setTestPaperQuestionScore(Integer testPaperQuestionScore) {
        this.testPaperQuestionScore = testPaperQuestionScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getGetScore() {
        return getScore;
    }

    public void setGetScore(Integer getScore) {
        this.getScore = getScore;
    }

    public String getIsAnswerQuestion() {
        return isAnswerQuestion;
    }

    public void setIsAnswerQuestion(String isAnswerQuestion) {
        this.isAnswerQuestion = isAnswerQuestion;
    }
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

}
