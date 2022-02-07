package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyFileSaveRecordQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyFileSaveRecordView queryObj = new SocietyFileSaveRecordView();

	public SocietyFileSaveRecordView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyFileSaveRecordView queryObj) {
		this.queryObj = queryObj;
	}
}
