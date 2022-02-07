package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolClassAdminQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolClassAdminView queryObj = new SocietySchoolClassAdminView();

	public SocietySchoolClassAdminView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolClassAdminView queryObj) {
		this.queryObj = queryObj;
	}
}
