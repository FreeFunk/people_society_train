package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentCallNameConfirm;
import com.edgedo.society.queryvo.SocietyStudentCallNameConfirmQuery;
import com.edgedo.society.service.SocietyStudentCallNameConfirmService;
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


@Api(tags = "SocietyStudentCallNameConfirm")
@Controller
@RequestMapping("/societyStudentCallNameConfirm")
public class SocietyStudentCallNameConfirmController extends BaseController{
	
	@Autowired
	private SocietyStudentCallNameConfirmService service;
	@Autowired
	private SocietyStudentService societyStudentService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentCallNameConfirm", notes = "分页查询SocietyStudentCallNameConfirm")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentCallNameConfirmQuery query){
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
	@ApiOperation(value = "新增修改SocietyStudentCallNameConfirm", notes = "新增修改SocietyStudentCallNameConfirm")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentCallNameConfirm voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentCallNameConfirm", notes = "根据ID批量删除SocietyStudentCallNameConfirm")
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
	@ApiOperation(value = "根据ID加载SocietyStudentCallNameConfirm", notes = "根据ID加载SocietyStudentCallNameConfirm")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/*
	* 查询当前登录用户是收到点名记录
	* */
/*	@RequestMapping(value = "/loadByStuId",method = RequestMethod.POST)
	public ModelAndView  loadByStuId(){
		ModelAndView modelAndView = new ModelAndView();
		//获取当前登录的学员
		SocietyStudent student = getLoginStudent(societyStudentService);
	*//*	SocietyStudent student = new SocietyStudent();
		student.setId("c7a4d1fcb5684a8b847b88f5b39fd97c");
		student.setStudentName("赵思达");
		student.setIsRealNameAuth("1");
		student.setOwnerSchoolId("qhdrcw");*//*
		String studentId = student.getId();
		SocietyStudentCallNameConfirm callNameConfirm = service.loadByStuId(studentId);
		return buildResponse(modelAndView, callNameConfirm);
	}*/

	/*
	 * 根据id更新确认状态
	 * */
	/*@RequestMapping(value = "/updateCallStateById",method = RequestMethod.POST)
	public ModelAndView  updateCallStateById(String id){
		ModelAndView modelAndView = new ModelAndView();
		//根据id更新确认状态
		return buildResponse(modelAndView, service.updateCallStateById(id));
	}*/
}
