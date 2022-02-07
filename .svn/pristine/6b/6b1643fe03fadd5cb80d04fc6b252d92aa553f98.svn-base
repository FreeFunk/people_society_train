package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;

public class SocietySchoolCourseNodeQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolCourseNodeView queryObj = new SocietySchoolCourseNodeView();

	private String ownerStudentAndCourseId;

	private String studentId;

	private String courseId;

	private List<String> nodeIdList;

	public List<String> getNodeIdList() {
		return nodeIdList;
	}

	public void setNodeIdList(List<String> nodeIdList) {
		this.nodeIdList = nodeIdList;
	}

	public String getOwnerStudentAndCourseId() {
		return ownerStudentAndCourseId;
	}

	public void setOwnerStudentAndCourseId(String ownerStudentAndCourseId) {
		this.ownerStudentAndCourseId = ownerStudentAndCourseId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public SocietySchoolCourseNodeView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolCourseNodeView queryObj) {
		this.queryObj = queryObj;
	}
}
