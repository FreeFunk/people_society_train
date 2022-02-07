package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyNodeExamineRecordQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyNodeExamineRecordView queryObj = new SocietyNodeExamineRecordView();

	public SocietyNodeExamineRecordView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyNodeExamineRecordView queryObj) {
		this.queryObj = queryObj;
	}
}
