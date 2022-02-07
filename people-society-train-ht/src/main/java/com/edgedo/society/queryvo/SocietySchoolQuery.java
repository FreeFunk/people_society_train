package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;

public class SocietySchoolQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolView queryObj = new SocietySchoolView();

	private List<String> schoolIdList;

	public List<String> getSchoolIdList() {
		return schoolIdList;
	}

	public void setSchoolIdList(List<String> schoolIdList) {
		this.schoolIdList = schoolIdList;
	}

	public SocietySchoolView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolView queryObj) {
		this.queryObj = queryObj;
	}
}
