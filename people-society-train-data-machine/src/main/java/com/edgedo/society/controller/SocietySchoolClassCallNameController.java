package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietySchoolClassCallName;
import com.edgedo.society.queryvo.SocietySchoolClassCallNameQuery;
import com.edgedo.society.service.SocietySchoolClassCallNameService;
import com.edgedo.society.service.SocietySchoolClassService;
import com.google.common.collect.Interner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolClassCallName")
@Controller
@RequestMapping("/society/societySchoolClassCallName")
public class SocietySchoolClassCallNameController extends BaseController{
	
	@Autowired
	private SocietySchoolClassCallNameService service;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Value("${app.timeLength}")
	private Integer timeLength;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolClassCallName", notes = "分页查询SocietySchoolClassCallName")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolClassCallNameQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 分页查询,学校管理员
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolClassCallName", notes = "分页查询SocietySchoolClassCallName")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpageForSchool",method = RequestMethod.POST)
	public ModelAndView listpageForSchool(@ModelAttribute SocietySchoolClassCallNameQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("CREATE_TIME DESC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolClassCallName", notes = "新增修改SocietySchoolClassCallName")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolClassCallName voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			errMsg = service.insert(voObj);
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
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
	@ApiOperation(value = "根据ID批量删除SocietySchoolClassCallName", notes = "根据ID批量删除SocietySchoolClassCallName")
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
	@ApiOperation(value = "根据ID加载SocietySchoolClassCallName", notes = "根据ID加载SocietySchoolClassCallName")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 一键点名,学校管理员
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/autoCallNameByclassId",method = RequestMethod.POST)
	public ModelAndView  autoCallNameByclassId(String classId){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		SocietySchoolClassCallName voObj = new SocietySchoolClassCallName();
		User user = getLoginUser();
		SocietySchoolClass clas = societySchoolClassService.loadById(classId);
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setOwnerSchoolId(clas.getOwnerSchoolId());
		voObj.setOwnerSchoolName(clas.getOwnerSchoolName());
		voObj.setClassId(clas.getId());
		voObj.setClassName(clas.getClassName());
		voObj.setTotalStudentNum(clas.getClassPersonNum());
		voObj.setConfirmStudentNum(0);
		voObj.setTimeLength(timeLength);
		voObj.setCallState("1");
		errMsg = service.insertAutoCallName(voObj);
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	/**
	 * 手动点名,学校管理员
	 * @param stuIds,classId
	 * @return
	 */
	@RequestMapping(value = "/manualCallName",method = RequestMethod.POST)
	public ModelAndView  manualCallName(String stuIds, String classId){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		SocietySchoolClassCallName voObj = new SocietySchoolClassCallName();
		User user = getLoginUser();
		SocietySchoolClass clas = societySchoolClassService.loadById(classId);
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setOwnerSchoolId(clas.getOwnerSchoolId());
		voObj.setOwnerSchoolName(clas.getOwnerSchoolName());
		voObj.setClassId(clas.getId());
		voObj.setClassName(clas.getClassName());
		voObj.setTotalStudentNum(clas.getClassPersonNum());
		voObj.setConfirmStudentNum(0);
		voObj.setTimeLength(timeLength);
		voObj.setCallState("1");
		String[] arr = stuIds.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		errMsg = service.insertmanualCallName(voObj,list);
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}
	
}
