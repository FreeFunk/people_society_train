package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentAndNodeQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentAndNodeView queryObj = new SocietyStudentAndNodeView();

	public SocietyStudentAndNodeView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentAndNodeView queryObj) {
		this.queryObj = queryObj;
	}
}
