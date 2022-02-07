package com.edgedo.sys.queryvo;

import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SysDwzDynamicQuery extends QueryObj {
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SysDwzDynamicView queryObj = new SysDwzDynamicView();

	public SysDwzDynamicView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SysDwzDynamicView queryObj) {
		this.queryObj = queryObj;
	}
	
}
