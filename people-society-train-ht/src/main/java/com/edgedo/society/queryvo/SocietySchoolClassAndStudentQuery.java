package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;

public class SocietySchoolClassAndStudentQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolClassAndStudentView queryObj = new SocietySchoolClassAndStudentView();

	private List<String> classAdminId;

	public List<String> getClassAdminId() {
		return classAdminId;
	}

	public void setClassAdminId(List<String> classAdminId) {
		this.classAdminId = classAdminId;
	}

	public SocietySchoolClassAndStudentView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolClassAndStudentView queryObj) {
		this.queryObj = queryObj;
	}
}
