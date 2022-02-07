package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.ArrayList;
import java.util.List;

public class SocietySchoolCourseQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolCourseView queryObj = new SocietySchoolCourseView();

	private List<String> courseIdList = new ArrayList<>();

	public List<String> getCourseIdList() {
		return courseIdList;
	}

	public void setCourseIdList(List<String> courseIdList) {
		this.courseIdList = courseIdList;
	}

	public SocietySchoolCourseView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolCourseView queryObj) {
		this.queryObj = queryObj;
	}
}
