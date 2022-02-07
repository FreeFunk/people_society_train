package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolCourseNodeQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolCourseNodeView queryObj = new SocietySchoolCourseNodeView();

	public SocietySchoolCourseNodeView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolCourseNodeView queryObj) {
		this.queryObj = queryObj;
	}
}
