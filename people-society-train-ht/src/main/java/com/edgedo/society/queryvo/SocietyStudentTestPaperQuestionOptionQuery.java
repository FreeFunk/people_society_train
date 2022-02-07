package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentTestPaperQuestionOptionQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentTestPaperQuestionOptionView queryObj = new SocietyStudentTestPaperQuestionOptionView();

	public SocietyStudentTestPaperQuestionOptionView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentTestPaperQuestionOptionView queryObj) {
		this.queryObj = queryObj;
	}
}
