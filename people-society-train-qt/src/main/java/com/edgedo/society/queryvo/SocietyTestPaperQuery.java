package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyTestPaperQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyTestPaperView queryObj = new SocietyTestPaperView();

	public SocietyTestPaperView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyTestPaperView queryObj) {
		this.queryObj = queryObj;
	}
}
