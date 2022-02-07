package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolView queryObj = new SocietySchoolView();

	public SocietySchoolView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolView queryObj) {
		this.queryObj = queryObj;
	}
}
