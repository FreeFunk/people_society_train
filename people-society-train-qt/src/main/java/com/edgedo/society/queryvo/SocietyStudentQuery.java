package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentView queryObj = new SocietyStudentView();

	public SocietyStudentView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentView queryObj) {
		this.queryObj = queryObj;
	}
}
