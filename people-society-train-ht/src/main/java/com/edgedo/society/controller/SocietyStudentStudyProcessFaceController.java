package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessFaceQuery;
import com.edgedo.society.service.SocietyStudentStudyProcessFaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentStudyProcessFace")
@Controller
@RequestMapping("/society/societyStudentStudyProcessFace")
public class SocietyStudentStudyProcessFaceController extends BaseController{
	
	@Autowired
	private SocietyStudentStudyProcessFaceService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentStudyProcessFace", notes = "分页查询SocietyStudentStudyProcessFace")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentStudyProcessFaceQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("OWNER_NODE_NAME ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/listpageHuiFu",method = RequestMethod.POST)
	public ModelAndView listpageHuiFu(@ModelAttribute SocietyStudentStudyProcessFaceQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("OWNER_NODE_NAME ASC");
		}
		service.listpageHuiFu(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyStudentStudyProcessFace", notes = "新增修改SocietyStudentStudyProcessFace")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentStudyProcessFace voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentStudyProcessFace", notes = "根据ID批量删除SocietyStudentStudyProcessFace")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		User user = getLoginUser();
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.deleteByIds(list,user);
		return buildResponse(modelAndView);
	}

	/**
	 * 找回人脸
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/retrieveFace",method = RequestMethod.POST)
	public ModelAndView retrieveFace(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.retrieveFaceByIds(list);
		return buildResponse(modelAndView);
	}


	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SocietyStudentStudyProcessFace", notes = "根据ID加载SocietyStudentStudyProcessFace")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}
