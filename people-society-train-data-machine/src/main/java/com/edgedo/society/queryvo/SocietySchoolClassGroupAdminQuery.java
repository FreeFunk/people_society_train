package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolClassGroupAdminQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolClassGroupAdminView queryObj = new SocietySchoolClassGroupAdminView();

	public SocietySchoolClassGroupAdminView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolClassGroupAdminView queryObj) {
		this.queryObj = queryObj;
	}
}
