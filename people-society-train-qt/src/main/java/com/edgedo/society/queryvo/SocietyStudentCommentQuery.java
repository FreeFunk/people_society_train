package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentCommentQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentCommentView queryObj = new SocietyStudentCommentView();

	public SocietyStudentCommentView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentCommentView queryObj) {
		this.queryObj = queryObj;
	}
}
