package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentStudyProcessQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentStudyProcessView queryObj = new SocietyStudentStudyProcessView();

	public SocietyStudentStudyProcessView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentStudyProcessView queryObj) {
		this.queryObj = queryObj;
	}
}
