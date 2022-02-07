package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;

public class SocietyStudentAndNodeQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentAndNodeView queryObj = new SocietyStudentAndNodeView();

	private List<String> nodeList;

	public List<String> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<String> nodeList) {
		this.nodeList = nodeList;
	}

	public SocietyStudentAndNodeView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentAndNodeView queryObj) {
		this.queryObj = queryObj;
	}
}
