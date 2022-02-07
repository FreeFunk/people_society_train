package com.edgedo.sys.queryvo;

import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SysCityAppInfoQuery extends QueryObj {
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SysCityAppInfoView queryObj = new SysCityAppInfoView();

	public SysCityAppInfoView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SysCityAppInfoView queryObj) {
		this.queryObj = queryObj;
	}

}
