package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentUniqueQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentUniqueView queryObj = new SocietyStudentUniqueView();

	public SocietyStudentUniqueView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentUniqueView queryObj) {
		this.queryObj = queryObj;
	}
}
