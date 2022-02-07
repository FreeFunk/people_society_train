package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietyStudentCertificateQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietyStudentCertificateView queryObj = new SocietyStudentCertificateView();

	public SocietyStudentCertificateView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietyStudentCertificateView queryObj) {
		this.queryObj = queryObj;
	}
}
