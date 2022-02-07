package com.edgedo.society.controller;


import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static java.lang.Integer.parseInt;


@Api(tags = "SocietyStudentPractiseQuestion")
@Controller
@RequestMapping("/society/societyStudentPractiseQuestion")
public class SocietyStudentPractiseQuestionController extends BaseController{
	
	@Autowired
	private SocietyStudentPractiseQuestionService service;

	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;

	@Autowired
	private SocietyStudentPractiseQuestOptionService societyStudentPractiseQuestOptionService;

	@Autowired
	private SocietySchoolCourseNodeOptionService societySchoolCourseNodeOptionService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Autowired
	private SocietyStudentService societyStudentService;

	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentPractiseQuestion", notes = "分页查询SocietyStudentPractiseQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentPractiseQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@Pass
	@RequestMapping(value = "/updateNew")
	public ModelAndView updateNew(@ModelAttribute SocietyStudentPractiseQuestionQuery societyStudentPractiseQuestion){
		ModelAndView modelAndView = new ModelAndView();

		buildResponse(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/taskAndStudyListNew",method = RequestMethod.POST)
	public ModelAndView taskAndStudyListNew(@ModelAttribute SocietyStudentPractiseQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String nodeId = query.getQueryObj().getOwnerNodeId();
		String ownerStuCourseId = query.getQueryObj().getOwnerStuCourseId();
		String courseId = query.getQueryObj().getOwnerCourseId();
		String stuAndNodeId = query.getQueryObj().getOwnerStudentAndNodeId();
		SocietyStudentAndNode societyStudentAndNode = societyStudentAndNodeService.loadById(stuAndNodeId);
		SocietyStudent societyStudent = societyStudentService.loadById(societyStudentAndNode.getStudentId());
		//1.查出公共的题库
		List<SocietySchoolCourseNodeQuestionView> questionList =
				societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeIdList(courseId,nodeId);
		//2.查出学生已经答题的记录
		List<SocietyStudentPractiseQuestionView> studentQuesList = service.selectByStuCouAndNodeId1(ownerStuCourseId,nodeId,societyStudent.getStudentIdCardAscii());
		//3.判断是否相等
		for (SocietySchoolCourseNodeQuestionView societySchoolCourseNodeQuestionView : questionList) {
			String questionId = societySchoolCourseNodeQuestionView.getId();
			//4.查出对应题目的所有选项 根据题目id
			List<SocietySchoolCourseNodeOptionView> optionList =
					societySchoolCourseNodeOptionService.selectQuestionId(questionId);
			//5.查出学生对应题目的所有选项  学生课程id 章节id 题目id -1答错 0未答 1答对
			SocietyStudentPractiseQuestion societyStudentPractiseQuestion =
					service.selectByQuesionIdAndNodeIdAndStuCouId(questionId, nodeId, ownerStuCourseId);
			if (societyStudentPractiseQuestion == null) {
				societySchoolCourseNodeQuestionView.setOptionList(optionList);
			} else {
				if (societyStudentPractiseQuestion.getAnswerState().equals("0")) {
					societySchoolCourseNodeQuestionView.setStudentAnswer(societyStudentPractiseQuestion.getAnswerState());
					societySchoolCourseNodeQuestionView.setOptionList(optionList);
				} else {
					societySchoolCourseNodeQuestionView.setStudentAnswer(societyStudentPractiseQuestion.getAnswerState());
					List<SocietyStudentPractiseQuestOption> studyList =
							societyStudentPractiseQuestOptionService.selectByStudyQuestionId(societyStudentPractiseQuestion.getId());
					for (SocietySchoolCourseNodeOptionView societySchoolCourseNodeOptionView : optionList) {
						for (SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption : studyList) {
							String option = societySchoolCourseNodeOptionView.getOptionTitle();
							String studyOption = societyStudentPractiseQuestOption.getOptionTitle();
							if (option.equals(studyOption)) {
								societySchoolCourseNodeOptionView.setIsSelect(societyStudentPractiseQuestOption.getIsSelect());
							}
						}
					}
					societySchoolCourseNodeQuestionView.setOptionList(optionList);
				}
			}
		}
		Integer sorceNum = societyStudentAndNode.getNodeQuestionScore();
		Integer rightNum = service.selectByNodeIdAndRight(stuAndNodeId);
		Integer errNum = service.selectByNodeIdAndErr(stuAndNodeId);
		Integer nullNum = service.selectByNodeIdAndNull(stuAndNodeId);
		Map<String,Object> map = new HashMap<>();
		map.put("sorceNum",sorceNum);
		map.put("rightNum",rightNum);
		map.put("errNum",errNum);
		map.put("nullNum",nullNum);
		map.put("questionList",questionList);
		buildResponse(modelAndView,map);
		return modelAndView;
	}

	@RequestMapping(value = "/taskAndStudyList",method = RequestMethod.POST)
	public ModelAndView taskAndStudyList(@ModelAttribute SocietyStudentPractiseQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String nodeId = query.getQueryObj().getOwnerNodeId();
		String ownerStuCourseId = query.getQueryObj().getOwnerStuCourseId();
		String courseId = query.getQueryObj().getOwnerCourseId();
		String stuAndNodeId = query.getQueryObj().getOwnerStudentAndNodeId();
		SocietyStudentAndCourse societyStudentAndCourse = societyStudentAndCourseService.loadById(ownerStuCourseId);
		SocietyStudent societyStudent = societyStudentService.loadById(societyStudentAndCourse.getStudentId());
		//1.查出公共的题库
		List<SocietySchoolCourseNodeQuestionView> questionList =
				societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeIdList(courseId,nodeId);
		//2.查出学生已经答题的记录
		List<SocietyStudentPractiseQuestionView> studentQuesList = service.selectByStuCouAndNodeId1(ownerStuCourseId,nodeId,societyStudent.getStudentIdCardAscii());
//		List<SocietyStudentPractiseQuestionView> studentQuesList = service.selectByStuCouAndNodeId(ownerStuCourseId,nodeId);
		//3.判断是否相等
		for (SocietySchoolCourseNodeQuestionView societySchoolCourseNodeQuestionView : questionList){
			String questionId = societySchoolCourseNodeQuestionView.getId();
			//4.查出对应题目的所有选项 根据题目id
			List<SocietySchoolCourseNodeOptionView> optionList =
					societySchoolCourseNodeOptionService.selectQuestionId(questionId);
			//5.查出学生对应题目的所有选项  学生课程id 章节id 题目id -1答错 0未答 1答对
			SocietyStudentPractiseQuestion societyStudentPractiseQuestion =
					service.selectByQuesionIdAndNodeIdAndStuCouId(questionId,nodeId,ownerStuCourseId);
			if (societyStudentPractiseQuestion==null){
				societySchoolCourseNodeQuestionView.setOptionList(optionList);
			}else {
				if(societyStudentPractiseQuestion.getAnswerState().equals("0")){
					societySchoolCourseNodeQuestionView.setOptionList(optionList);
				}else {
					List<SocietyStudentPractiseQuestOption> studyList =
							societyStudentPractiseQuestOptionService.selectByStudyQuestionId(societyStudentPractiseQuestion.getId());
					for(SocietySchoolCourseNodeOptionView societySchoolCourseNodeOptionView : optionList){
						for(SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption : studyList){
							String option = societySchoolCourseNodeOptionView.getOptionTitle();
							String studyOption = societyStudentPractiseQuestOption.getOptionTitle();
							if(option.equals(studyOption)){
								societySchoolCourseNodeOptionView.setIsSelect(societyStudentPractiseQuestOption.getIsSelect());
							}
						}
					}
					societySchoolCourseNodeQuestionView.setOptionList(optionList);
				}
			}
		}
		buildResponse(modelAndView,questionList);
		return modelAndView;
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 课后习题答题详情
	 *@DateTime: 2020/6/3 10:16
	 */
	@RequestMapping(value = "/nodeQuestionDetail",method = RequestMethod.POST)
	public ModelAndView nodeQuestionDetail(@ModelAttribute SocietySchoolCourseNodeQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPageNodeQuestionDetail(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyStudentPractiseQuestion", notes = "新增修改SocietyStudentPractiseQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentPractiseQuestion voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentPractiseQuestion", notes = "根据ID批量删除SocietyStudentPractiseQuestion")
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
	@ApiOperation(value = "根据ID加载SocietyStudentPractiseQuestion", notes = "根据ID加载SocietyStudentPractiseQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}
