package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class YwTrainConsumRecOldQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private YwTrainConsumRecOldView queryObj = new YwTrainConsumRecOldView();

	public YwTrainConsumRecOldView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(YwTrainConsumRecOldView queryObj) {
		this.queryObj = queryObj;
	}
}
