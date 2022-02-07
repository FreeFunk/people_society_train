package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;

public class SocietyStudentQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentView queryObj = new SocietyStudentView();

	private List<String> studentIdList;

	private List<String> courseIdList;

	public List<String> getStudentIdList() {
		return studentIdList;
	}

	public void setStudentIdList(List<String> studentIdList) {
		this.studentIdList = studentIdList;
	}

	public void setCourseIdList(List<String> courseIdList) {
		this.courseIdList = courseIdList;
	}

	public SocietyStudentView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentView queryObj) {
		this.queryObj = queryObj;
	}
}
