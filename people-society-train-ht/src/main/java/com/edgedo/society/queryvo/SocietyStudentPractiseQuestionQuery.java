package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentPractiseQuestionQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentPractiseQuestionView queryObj = new SocietyStudentPractiseQuestionView();
	private String studentAscii;

	public String getStudentAscii() {
		return studentAscii;
	}

	public void setStudentAscii(String studentAscii) {
		this.studentAscii = studentAscii;
	}

	public SocietyStudentPractiseQuestionView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentPractiseQuestionView queryObj) {
		this.queryObj = queryObj;
	}
}
