package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolCourseQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolCourseView queryObj = new SocietySchoolCourseView();

	public SocietySchoolCourseView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolCourseView queryObj) {
		this.queryObj = queryObj;
	}
}
