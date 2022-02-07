package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.FormSelectsObject;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolClassAdmin;
import com.edgedo.society.queryvo.SocietySchoolClassAdminQuery;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import com.edgedo.society.service.SocietySchoolClassAdminService;
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


@Api(tags = "SocietySchoolClassAdmin")
@Controller
@RequestMapping("/society/societySchoolClassAdmin")
public class SocietySchoolClassAdminController extends BaseController{
	
	@Autowired
	private SocietySchoolClassAdminService service;
	@Autowired
	private SocietySchoolService societySchoolService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolClassAdmin", notes = "分页查询SocietySchoolClassAdmin")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolClassAdminQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolClassAdmin", notes = "新增修改SocietySchoolClassAdmin")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolClassAdmin voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		User getUser = getLoginUser();
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());//创建时间
			voObj.setCreateUserId(getUser.getUserId());//创建人Id
			voObj.setCreateUserName(getUser.getUserName());//创建名字
			voObj.setDataState("1");//数据状态
			SocietySchool societySchool = societySchoolService.loadById(getUser.getCompId());
			voObj.setOwnerSchoolId(societySchool.getId());//学校id
			voObj.setOwnerSchoolName(societySchool.getSchoolName());//学校名字
			errMsg = service.insert(voObj,getUser.getUserId(),getUser.getCompId());
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
	 * 获取所有的班主任信息
	 * @return
	 */
	@RequestMapping(value = "/getClassAdminName",method = RequestMethod.POST)
	public ModelAndView getClassAdminName(String groupId){
		ModelAndView modelAndView = new ModelAndView();
		User user =getLoginUser();
		List<FormSelectsObject> list = service.getClassAdminName(user.getCompId(),groupId);
		return buildResponse(modelAndView,list);
	}


	/*
	 * 根据id重置班主任的密码
	 * */
	@RequestMapping(value = "/resetPwdById",method = RequestMethod.POST)
	public ModelAndView resetPwdById(String id){
		ModelAndView modelAndView = new ModelAndView();
		service.resetPwdById(id);
		return buildResponse(modelAndView);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除SocietySchoolClassAdmin", notes = "根据ID批量删除SocietySchoolClassAdmin")
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
	@ApiOperation(value = "根据ID加载SocietySchoolClassAdmin", notes = "根据ID加载SocietySchoolClassAdmin")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 查出当前学校的所有的班主任
	 * @return
	 */
	@RequestMapping(value = "/listClassAdmin",method = RequestMethod.POST)
	public ModelAndView listClassAdmin(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		//查询学校是否存在
		List<SocietySchoolClassAdmin> classAdminList = service.listByClassAdmin(schoolId);
		return buildResponse(modelAndView,classAdminList);
	}
	
}
