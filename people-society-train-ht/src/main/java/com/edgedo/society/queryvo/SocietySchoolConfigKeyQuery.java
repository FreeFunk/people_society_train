package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolConfigKeyQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolConfigKeyView queryObj = new SocietySchoolConfigKeyView();

	public SocietySchoolConfigKeyView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolConfigKeyView queryObj) {
		this.queryObj = queryObj;
	}
}
