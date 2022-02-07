package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestionOption;
import com.edgedo.society.entity.SocietyTestPaperQuestionOption;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionOptionQuery;
import com.edgedo.society.service.SocietyStudentTestPaperQuestionOptionService;
import com.edgedo.society.service.SocietyTestPaperQuestionOptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentTestPaperQuestionOption")
@Controller
@RequestMapping("/society/societyStudentTestPaperQuestionOption")
public class SocietyStudentTestPaperQuestionOptionController extends BaseController{
	
	@Autowired
	private SocietyStudentTestPaperQuestionOptionService service;

	@Autowired
	private SocietyTestPaperQuestionOptionService societyTestPaperQuestionOptionService;

	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentTestPaperQuestionOption", notes = "分页查询SocietyStudentTestPaperQuestionOption")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentTestPaperQuestionOptionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


/*	@RequestMapping(value = "/selectByQuesionId",method = RequestMethod.POST)
	public ModelAndView selectByQuesionId(@ModelAttribute SocietyStudentTestPaperQuestionOptionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//1.查出学生关联的试卷题目  和公共表题目
		service.select
		return modelAndView;
	}*/


	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyStudentTestPaperQuestionOption", notes = "新增修改SocietyStudentTestPaperQuestionOption")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentTestPaperQuestionOption voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentTestPaperQuestionOption", notes = "根据ID批量删除SocietyStudentTestPaperQuestionOption")
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
	@ApiOperation(value = "根据ID加载SocietyStudentTestPaperQuestionOption", notes = "根据ID加载SocietyStudentTestPaperQuestionOption")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 点击某个题目 定位选项 学校id 课程id 试卷id 题目id
	 * @param societyStudentTestPaperQuestionOptionQuery
	 * @return
	 */
	@RequestMapping(value = "/selectAllOption",method = RequestMethod.POST)
	public ModelAndView selectAllOption(@ModelAttribute SocietyStudentTestPaperQuestionOptionQuery societyStudentTestPaperQuestionOptionQuery){
		ModelAndView modelAndView = new ModelAndView();
		String ownerSchoolId = societyStudentTestPaperQuestionOptionQuery.getQueryObj().getOwnerSchoolId();
		String ownerCourseId = societyStudentTestPaperQuestionOptionQuery.getQueryObj().getOwnerCourseId();
		String ownerTestPaperId = societyStudentTestPaperQuestionOptionQuery.getQueryObj().getOwnerTestPaperId();
		String questionId = societyStudentTestPaperQuestionOptionQuery.getQueryObj().getOwnerTestPaperQuestionId();
		//1.去学员选项表查出list
		List<SocietyStudentTestPaperQuestionOption> studentOptionList =
				service.selectBySchoolIdAndCourseIdAndPaperIdAndQuestId
						(ownerSchoolId,ownerCourseId,ownerTestPaperId,questionId);
		if(studentOptionList.size()==0){
			List<SocietyStudentTestPaperQuestionOption> list = new ArrayList<>();
			//如果没有 则该学员没有答题 进入公共选项表查出选项
			List<SocietyTestPaperQuestionOption> publicOptionList = societyTestPaperQuestionOptionService.selectBySchoolIdAndCourseIdAndPaperIdAndQuestId
					(ownerSchoolId,ownerCourseId,ownerTestPaperId,questionId);
			for(SocietyTestPaperQuestionOption societyTestPaperQuestionOption : publicOptionList){
				SocietyStudentTestPaperQuestionOption societyStudentTestPaperQuestionOption = new SocietyStudentTestPaperQuestionOption();

				//学校id 学校名 ownerSchoolId
				societyStudentTestPaperQuestionOption.setOwnerSchoolId(ownerSchoolId);
				societyStudentTestPaperQuestionOption.setOwnerSchoolName(societyTestPaperQuestionOption.getOwnerSchoolName());
				//课程id 课程名字 ownerCourseId
				societyStudentTestPaperQuestionOption.setOwnerCourseId(ownerSchoolId);
				societyStudentTestPaperQuestionOption.setOwnerCourseName(societyTestPaperQuestionOption.getOwnerCourseName());
				//试卷id 试卷名 ownerTestPaperId
				societyStudentTestPaperQuestionOption.setOwnerTestPaperId(ownerSchoolId);
				societyStudentTestPaperQuestionOption.setOwnerTestPaperName(societyTestPaperQuestionOption.getOwnerTestPaperName());
				//题目id questionId
				societyStudentTestPaperQuestionOption.setOwnerTestPaperQuestionId(questionId);
				//选项描述
				societyStudentTestPaperQuestionOption.setOptionName(societyTestPaperQuestionOption.getOptionName());
				//是否是正确答案
				societyStudentTestPaperQuestionOption.setIsRight(societyTestPaperQuestionOption.getIsRight());
				//是否选择 否 0
				societyStudentTestPaperQuestionOption.setIsSelect("0");
				//选项编号
				societyStudentTestPaperQuestionOption.setOptionTitle(societyTestPaperQuestionOption.getOptionTitle());
				list.add(societyStudentTestPaperQuestionOption);
			}
			societyStudentTestPaperQuestionOptionQuery.setList(list);
			buildResponse(modelAndView,societyStudentTestPaperQuestionOptionQuery);
			return modelAndView;
		}
		societyStudentTestPaperQuestionOptionQuery.setList(studentOptionList);
		buildResponse(modelAndView,societyStudentTestPaperQuestionOptionQuery);
		return modelAndView;
	}
}
