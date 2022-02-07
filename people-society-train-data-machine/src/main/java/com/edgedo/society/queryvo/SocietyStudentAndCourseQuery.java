package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;

public class SocietyStudentAndCourseQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentAndCourseView queryObj = new SocietyStudentAndCourseView();

	private List<String> classIdList;

	private String isRealNameAuth;

	public String getIsRealNameAuth() {
		return isRealNameAuth;
	}

	public void setIsRealNameAuth(String isRealNameAuth) {
		this.isRealNameAuth = isRealNameAuth;
	}

	public List<String> getClassIdList() {
		return classIdList;
	}

	public void setClassIdList(List<String> classIdList) {
		this.classIdList = classIdList;
	}

	public SocietyStudentAndCourseView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentAndCourseView queryObj) {
		this.queryObj = queryObj;
	}
}
