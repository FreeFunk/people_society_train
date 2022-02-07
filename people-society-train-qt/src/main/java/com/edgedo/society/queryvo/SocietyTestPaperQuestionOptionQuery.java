package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyTestPaperQuestionOptionQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyTestPaperQuestionOptionView queryObj = new SocietyTestPaperQuestionOptionView();

	public SocietyTestPaperQuestionOptionView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyTestPaperQuestionOptionView queryObj) {
		this.queryObj = queryObj;
	}
}
