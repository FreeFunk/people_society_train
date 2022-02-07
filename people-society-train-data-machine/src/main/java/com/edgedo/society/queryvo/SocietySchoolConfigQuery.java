package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolConfigQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolConfigView queryObj = new SocietySchoolConfigView();

	public SocietySchoolConfigView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolConfigView queryObj) {
		this.queryObj = queryObj;
	}
}
