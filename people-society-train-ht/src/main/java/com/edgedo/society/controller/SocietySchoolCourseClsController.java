package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchoolCourseCls;
import com.edgedo.society.entity.SocietySchoolMajor;
import com.edgedo.society.queryvo.SocietySchoolCourseClsQuery;
import com.edgedo.society.service.SocietySchoolCourseClsService;
import com.edgedo.society.service.SocietySchoolMajorService;
import com.edgedo.sys.entity.Dtree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolCourseCls")
@Controller
@RequestMapping("/society/societySchoolCourseCls")
public class SocietySchoolCourseClsController extends BaseController{
	
	@Autowired
	private SocietySchoolCourseClsService service;
	@Autowired
	private SocietySchoolMajorService schoolMajorService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolCourseCls", notes = "分页查询SocietySchoolCourseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolCourseClsQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 学校管理员分页查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/schoolListpage",method = RequestMethod.POST)
	public ModelAndView schoolListpage(@ModelAttribute SocietySchoolCourseClsQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String compId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(compId);
		if (query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 平台管理员分页查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/ptListpage",method = RequestMethod.POST)
	public ModelAndView ptListpage(@ModelAttribute SocietySchoolCourseClsQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if (query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
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
	@ApiOperation(value = "新增修改SocietySchoolCourseCls", notes = "新增修改SocietySchoolCourseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolCourseCls voObj){
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
	 * 学校管理员新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolCourseCls", notes = "新增修改SocietySchoolCourseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoolSaveOrUpdate",method = RequestMethod.POST)
	public ModelAndView schoolSaveOrUpdate(SocietySchoolCourseCls voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		SocietySchoolMajor societySchoolMajor = schoolMajorService.loadById(voObj.getOwnerMajorId());
		voObj.setOwnerMajorName(societySchoolMajor.getMajorName());
		if(id==null || id.trim().equals("")){
			User user = getLoginUser();
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setOwnerSchoolId(user.getCompId());
			voObj.setOwnerSchoolName(user.getUserName());
			voObj.setDataState("1");
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
	 * 平台管理员新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolCourseCls", notes = "新增修改SocietySchoolCourseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/ptSaveOrUpdate",method = RequestMethod.POST)
	public ModelAndView ptSaveOrUpdate(SocietySchoolCourseCls voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			SocietySchoolMajor societySchoolMajor = schoolMajorService.loadById(voObj.getOwnerMajorId());
			voObj.setOwnerMajorName(societySchoolMajor.getMajorName());
			User user = getLoginUser();
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setOwnerSchoolId(societySchoolMajor.getOwnerSchoolId());
			voObj.setOwnerSchoolName(societySchoolMajor.getOwnerSchoolName());
			voObj.setDataState("1");
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
	@ApiOperation(value = "根据ID批量删除SocietySchoolCourseCls", notes = "根据ID批量删除SocietySchoolCourseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.logicaDeletion(list);
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SocietySchoolCourseCls", notes = "根据ID加载SocietySchoolCourseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 根据专业id和学校ID查询课程分类
	 * @param majorId
	 * @return
	 */
	@RequestMapping(value = "/listByMajorIdAndSchoolId",method = RequestMethod.POST)
	public ModelAndView  listByMajorIdAndSchoolId(String majorId){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser =getLoginUser();
		List<Dtree> dtreeList = service.listByMajorIdAndSchoolId(majorId,loginUser.getCompId());
		return buildResponse(modelAndView, dtreeList);
	}

	/**
	 * 根据专业id加载课程分类，学校树,平台管理员
	 * @param majorId,schoolId
	 * @return
	 */
	@RequestMapping(value = "/listCourseClsByMajorId",method = RequestMethod.POST)
	public ModelAndView  listCourseClsByMajorId(String majorId,String schoolId){
		ModelAndView modelAndView = new ModelAndView();
		List<Dtree> dtreeList = service.listCourseClsByMajorId(majorId,schoolId);
		return buildResponse(modelAndView, dtreeList);
	}
	
}
