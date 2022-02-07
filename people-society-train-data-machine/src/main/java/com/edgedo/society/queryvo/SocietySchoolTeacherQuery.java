package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolTeacherQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolTeacherView queryObj = new SocietySchoolTeacherView();

	public SocietySchoolTeacherView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolTeacherView queryObj) {
		this.queryObj = queryObj;
	}
}
