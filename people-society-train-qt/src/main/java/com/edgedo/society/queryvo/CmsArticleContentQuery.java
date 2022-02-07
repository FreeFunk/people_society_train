package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class CmsArticleContentQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private CmsArticleContentView queryObj = new CmsArticleContentView();

	public CmsArticleContentView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(CmsArticleContentView queryObj) {
		this.queryObj = queryObj;
	}
}
