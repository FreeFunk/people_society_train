package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.queryvo.SocietySchoolClassView;
import com.edgedo.society.queryvo.SocietySchoolQuery;
import com.edgedo.society.queryvo.SocietySchoolView;
import com.edgedo.society.service.SocietySchoolBannerService;
import com.edgedo.society.service.SocietySchoolClassService;
import com.edgedo.society.service.SocietySchoolService;
import com.edgedo.society.service.SocietyStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchool")
@Controller
@RequestMapping("/school")
public class SocietySchoolController extends BaseController{
	
	@Autowired
	private SocietySchoolService service;
	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietySchoolBannerService bannerService;
	@Autowired
	private SocietySchoolClassService schoolClassService;

	/**
	 * @Author ZhangCC
	 * @Description 获取学校名称
	 * @Date 2020/5/10 8:58
	 **/
	@Pass
	@RequestMapping(value = "/getSchoolName",method = RequestMethod.POST)
	public ModelAndView getSchoolName(){
		ModelAndView modelAndView = new ModelAndView();
		String schoolId = getSchoolId();
		SocietySchool school = service.loadById(schoolId);
		return buildResponse(modelAndView,school);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学校查询学校轮播图
	 * @Date 2020/5/5 19:06
	 **/
	@Pass
	@RequestMapping(value = "/selectSchoolBanner",method = RequestMethod.POST)
	public ModelAndView selectSchoolBanner(){
		ModelAndView modelAndView = new ModelAndView();
		String ownerSchoolId = getSchoolId();
		List<String> bannerImgList = bannerService.selectSchoolBannerImg(ownerSchoolId);
		return buildResponse(modelAndView, bannerImgList);
	}

	/**
	 * @Author WangZhen
	 * @Description 获得根据主键获得学校信息
	 * @Date 2020/5/10 11:15
	 **/
	@RequestMapping(value = "/getSchInfo")
	@Pass
	public ModelAndView getSchInfo(){
		ModelAndView modelAndView = new ModelAndView();
		SocietySchoolView sch =  getSchoolInfo(service);
		return buildResponse(modelAndView, sch);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询学校所有的班级
	 * @Date 2020/5/26 11:27
	 **/
	@RequestMapping(value = "/selectAllClassBySchool")
	public ModelAndView selectAllClassBySchool(){
		ModelAndView modelAndView = new ModelAndView();
		String ownerSchoolId = getSchoolId();
		List<SocietySchoolClassView> schoolClassList = schoolClassService.selectAllClassBySchool(ownerSchoolId);
		return buildResponse(modelAndView,schoolClassList);
	}
	
	
}
