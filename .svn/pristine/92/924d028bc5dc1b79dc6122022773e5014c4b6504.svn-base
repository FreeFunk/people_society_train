package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyNodeResourcesQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyNodeResourcesView queryObj = new SocietyNodeResourcesView();

	public SocietyNodeResourcesView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyNodeResourcesView queryObj) {
		this.queryObj = queryObj;
	}
}
