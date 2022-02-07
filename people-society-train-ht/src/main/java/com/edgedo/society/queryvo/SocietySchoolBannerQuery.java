package com.edgedo.society.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SocietySchoolBannerQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SocietySchoolBannerView queryObj = new SocietySchoolBannerView();

	public SocietySchoolBannerView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SocietySchoolBannerView queryObj) {
		this.queryObj = queryObj;
	}
}
