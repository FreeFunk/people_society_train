package com.edgedo.sys.queryvo;

import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SysWxConfigQuery extends QueryObj {

	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SysWxConfigView queryObj = new SysWxConfigView();

	public SysWxConfigView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SysWxConfigView queryObj) {
		this.queryObj = queryObj;
	}

	
}
