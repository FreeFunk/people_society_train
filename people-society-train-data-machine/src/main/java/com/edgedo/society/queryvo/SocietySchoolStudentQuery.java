package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolStudentQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolStudentView queryObj = new SocietySchoolStudentView();

	public SocietySchoolStudentView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolStudentView queryObj) {
		this.queryObj = queryObj;
	}
}
