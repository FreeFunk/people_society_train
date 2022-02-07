package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolClassQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolClassView queryObj = new SocietySchoolClassView();

	public SocietySchoolClassView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolClassView queryObj) {
		this.queryObj = queryObj;
	}
}
