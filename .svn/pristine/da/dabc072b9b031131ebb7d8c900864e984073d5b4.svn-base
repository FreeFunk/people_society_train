package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentAndCourseQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentAndCourseView queryObj = new SocietyStudentAndCourseView();

	public SocietyStudentAndCourseView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentAndCourseView queryObj) {
		this.queryObj = queryObj;
	}
}
