package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolClassAndCourseQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolClassAndCourseView queryObj = new SocietySchoolClassAndCourseView();

	public SocietySchoolClassAndCourseView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolClassAndCourseView queryObj) {
		this.queryObj = queryObj;
	}
}
