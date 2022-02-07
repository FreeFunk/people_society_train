package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class CmsArticleClsQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private CmsArticleClsView queryObj = new CmsArticleClsView();

	public CmsArticleClsView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(CmsArticleClsView queryObj) {
		this.queryObj = queryObj;
	}
}
