package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolClassAndStudentQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolClassAndStudentView queryObj = new SocietySchoolClassAndStudentView();

	public SocietySchoolClassAndStudentView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolClassAndStudentView queryObj) {
		this.queryObj = queryObj;
	}
}
