package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.society.entity.SocietyStudentPractiseQuestOption;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionQuery;
import com.edgedo.society.service.SocietyStudentPractiseQuestOptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentPractiseQuestOption")
@Controller
@RequestMapping("/society/societyStudentPractiseQuestOption")
public class SocietyStudentPractiseQuestOptionController extends BaseController{
	
	@Autowired
	private SocietyStudentPractiseQuestOptionService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentPractiseQuestOption", notes = "分页查询SocietyStudentPractiseQuestOption")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentPractiseQuestOptionQuery query){
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
	@ApiOperation(value = "新增修改SocietyStudentPractiseQuestOption", notes = "新增修改SocietyStudentPractiseQuestOption")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentPractiseQuestOption voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentPractiseQuestOption", notes = "根据ID批量删除SocietyStudentPractiseQuestOption")
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
	@ApiOperation(value = "根据ID加载SocietyStudentPractiseQuestOption", notes = "根据ID加载SocietyStudentPractiseQuestOption")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 根据题目id 小节id 学生id定位到一个题目下所有选项记录
	 * @param quersionId 题目id
	 * @param ownerNodeId 小节id
	 * @param studentId 学生id
	 * @return
	 */
	/*@RequestMapping(value = "/selectByQuesionId",method = RequestMethod.POST)
	public ModelAndView  selectByQuesionId(String quersionId,String ownerNodeId,String studentId){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietyStudentPractiseQuestOption> list = service.selectByQuesionId(quersionId, ownerNodeId, studentId);
		return buildResponse(modelAndView,list);
	}*/
	@RequestMapping(value = "/selectByQuesionId",method = RequestMethod.POST)
	public ModelAndView selectByQuesionId(@ModelAttribute SocietyStudentPractiseQuestOptionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectByQuesionIdlistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	
	
}
