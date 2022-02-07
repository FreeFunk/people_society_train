package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyWxMessageRecQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyWxMessageRecView queryObj = new SocietyWxMessageRecView();

	public SocietyWxMessageRecView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyWxMessageRecView queryObj) {
		this.queryObj = queryObj;
	}
}
