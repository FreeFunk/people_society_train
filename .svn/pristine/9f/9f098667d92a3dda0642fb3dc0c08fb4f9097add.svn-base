package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolMajorQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolMajorView queryObj = new SocietySchoolMajorView();

	public SocietySchoolMajorView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolMajorView queryObj) {
		this.queryObj = queryObj;
	}
}
