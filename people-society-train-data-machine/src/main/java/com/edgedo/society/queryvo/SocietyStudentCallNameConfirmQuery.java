package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentCallNameConfirmQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentCallNameConfirmView queryObj = new SocietyStudentCallNameConfirmView();

	public SocietyStudentCallNameConfirmView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentCallNameConfirmView queryObj) {
		this.queryObj = queryObj;
	}
}
