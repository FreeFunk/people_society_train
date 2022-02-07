package com.edgedo.sys.queryvo;

import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SysCityQuery extends QueryObj {
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SysCityView queryObj = new SysCityView();

	public SysCityView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SysCityView queryObj) {
		this.queryObj = queryObj;
	}
	

}
