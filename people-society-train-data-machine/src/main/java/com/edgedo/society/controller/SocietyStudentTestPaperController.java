package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietyStudentTestPaper;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuery;
import com.edgedo.society.service.SocietySchoolService;
import com.edgedo.society.service.SocietyStudentTestPaperService;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentTestPaper")
@Controller
@RequestMapping("/society/societyStudentTestPaper")
public class SocietyStudentTestPaperController extends BaseController{
	
	@Autowired
	private SocietyStudentTestPaperService service;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SysUserService sysUserService;


	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentTestPaper", notes = "分页查询SocietyStudentTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentTestPaperQuery query){
		//初始化区间
		if(query.getQueryObj().getTestRightRateStart()==null){
			query.getQueryObj().setTestRightRateStart(80);
			query.getQueryObj().setTestRightRateEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/listSuppage",method = RequestMethod.POST)
	public ModelAndView listSuppage(@ModelAttribute SocietyStudentTestPaperQuery query){
		//初始化区间
		if(query.getQueryObj().getTestRightRateStart()==null){
			query.getQueryObj().setTestRightRateStart(80);
			query.getQueryObj().setTestRightRateEnd(100);
		}
		SysUser sysUser = sysUserService.loadById(getLoginUser().getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 不分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "不分页查询SocietyStudentTestPaper", notes = "不分页查询SocietyStudentTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObj",method = RequestMethod.POST)
	public ModelAndView listByObj(@ModelAttribute SocietyStudentTestPaperQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyStudentTestPaper", notes = "新增修改SocietyStudentTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentTestPaper voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentTestPaper", notes = "根据ID批量删除SocietyStudentTestPaper")
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
	@ApiOperation(value = "根据ID加载SocietyStudentTestPaper", notes = "根据ID加载SocietyStudentTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 查询当前学校的考试信息
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectSchoolTestPaperInfo",method = RequestMethod.POST)
	public ModelAndView selectSchoolTestPaperInfo(@ModelAttribute SocietyStudentTestPaperQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//初始化区间
		if(query.getQueryObj().getTestRightRateStart()==null){
			query.getQueryObj().setTestRightRateStart(0);
			query.getQueryObj().setTestRightRateEnd(100);
		}

		//获取当前用户
		User user = getLoginUser();
		Map<String,String> map = societySchoolService.selectBySchoolAdminId(user.getUserId());
		query.getQueryObj().setOwnerSchoolId(map.get("ID"));
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 查询当前学校的考试信息，不分页，导出Excel
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectSchoolTestInfo",method = RequestMethod.POST)
	public ModelAndView selectSchoolTestInfo(@ModelAttribute SocietyStudentTestPaperQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//获取当前用户
		User user = getLoginUser();
		Map<String,String> map = societySchoolService.selectBySchoolAdminId(user.getUserId());
		query.getQueryObj().setOwnerSchoolId(map.get("ID"));
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

}
