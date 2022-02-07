package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolClassGroupAdmin;
import com.edgedo.society.queryvo.SocietySchoolClassGroupAdminQuery;
import com.edgedo.society.queryvo.SocietySchoolClassGroupAdminView;
import com.edgedo.society.service.SocietySchoolClassGroupAdminService;
import com.edgedo.society.service.SocietySchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolClassGroupAdmin")
@Controller
@RequestMapping("/society/societySchoolClassGroupAdmin")
public class SocietySchoolClassGroupAdminController extends BaseController{
	
	@Autowired
	private SocietySchoolClassGroupAdminService service;
	@Autowired
	private SocietySchoolService societySchoolService;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolClassGroupAdmin", notes = "分页查询SocietySchoolClassGroupAdmin")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolClassGroupAdminQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolClassGroupAdmin", notes = "新增修改SocietySchoolClassGroupAdmin")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolClassGroupAdminView voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		User user  = getLoginUser();
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setDataState("1");
			SocietySchool societySchool = societySchoolService.loadById(user.getCompId());
			voObj.setOwnerSchoolId(societySchool.getId());
			voObj.setOwnerSchoolName(societySchool.getSchoolName());
			errMsg = service.insertSysAndGroup(voObj,user.getUserId(),user.getCompId());
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除SocietySchoolClassGroupAdmin", notes = "根据ID批量删除SocietySchoolClassGroupAdmin")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.deleteByIds(list);		
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SocietySchoolClassGroupAdmin", notes = "根据ID加载SocietySchoolClassGroupAdmin")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}
