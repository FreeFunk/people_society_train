package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentPractiseQuestOptionQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentPractiseQuestOptionView queryObj = new SocietyStudentPractiseQuestOptionView();

	public SocietyStudentPractiseQuestOptionView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentPractiseQuestOptionView queryObj) {
		this.queryObj = queryObj;
	}
}
