package com.edgedo.society.controller;


import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;
	@Autowired
	private SocietyStudentService societyStudentService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;

	
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

	@RequestMapping(value = "/taskAndStudyList",method = RequestMethod.POST)
	public ModelAndView taskAndStudyList(@ModelAttribute SocietyStudentPractiseQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String schoolId = query.getQueryObj().getOwnerSchoolId();
		String nodeId = query.getQueryObj().getOwnerNodeId();
		String courseId = query.getQueryObj().getOwnerCourseId();
		String studentId = query.getQueryObj().getStudentId();
		String ownerStuCourseId = query.getQueryObj().getOwnerStuCourseId();
		Integer num = 0;
		Map<Integer,Object> map = new TreeMap<Integer, Object>(
				new Comparator<Integer>() {
					public int compare(Integer obj1, Integer obj2) {
						// 排序
						return obj1-obj2;
					}
				});
		//1.学习习题相关的所有题目  20  排序  遍历
		List<SocietyStudentPractiseQuestionView> studentQuestionList = service.taskAndStudylistByObj(query);
		if(studentQuestionList.size()==0){
			Map<Object,Object> dataMap = new HashMap<>();
			dataMap.put("rightNum",0);
			dataMap.put("wrongNum",0);
			dataMap.put("num",0);
			dataMap.put("noQuestionNum",0);
			dataMap.put("str","还未进行答题");
			buildResponse(modelAndView,dataMap);
			return modelAndView;
		}else {
			for(SocietyStudentPractiseQuestionView
					societyStudentPractiseQuestionView : studentQuestionList){
				//2.在学生习题表中查出相关学生的答题情况 list  20
				List<SocietyStudentPractiseQuestOptionView> stuOptionList =
						societyStudentPractiseQuestOptionService.selectByOneOption(schoolId,nodeId,
								societyStudentPractiseQuestionView.getId(),studentId,ownerStuCourseId);

				if(stuOptionList.size()==1){
					SocietyStudentPractiseQuestOptionView societyStudentPractiseQuestOptionView = stuOptionList.get(0);

					societyStudentPractiseQuestOptionView.setOrderNum(societyStudentPractiseQuestionView.getOrderNum());
					//3.去公共题库查出所有的选项  list  60
					List<SocietySchoolCourseNodeOption> optionList =
							societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,nodeId,societyStudentPractiseQuestionView.getQuersionId());
					//4.组成map  以学习选项为key  所有选项为value
					societyStudentPractiseQuestOptionView.setAnswerState(societyStudentPractiseQuestionView.getAnswerState());
					societyStudentPractiseQuestOptionView.setList(optionList);
					map.put(map.size()+1,societyStudentPractiseQuestOptionView);
					//5.统计总分学习习题
					if(societyStudentPractiseQuestionView.getAnswerState().equals("1")){///答对了
						Integer newNum = societyStudentPractiseQuestionView.getQuestionScore().intValue();
						num += newNum;
					}
				}else {
					String answer = "";
					String answerOption = "";
					for (SocietyStudentPractiseQuestOptionView societyStudentPractiseQuestOptionView : stuOptionList){
						if(answer.equals("")){
							answer = societyStudentPractiseQuestOptionView.getOptionTitle();
						}else {
							answer = answer+"@@"+societyStudentPractiseQuestOptionView.getOptionTitle();
						}
						if(answerOption.equals("")){
							answerOption = societyStudentPractiseQuestOptionView.getOptionName();
						}else {
							answerOption = answerOption+"@@"+societyStudentPractiseQuestOptionView.getOptionName();
						}
					}
					SocietyStudentPractiseQuestOptionView stuQuestionAndOption =
							societyStudentPractiseQuestOptionService.selectByOneOptionOnce(schoolId,nodeId,
									societyStudentPractiseQuestionView.getId(),studentId,ownerStuCourseId);
					stuQuestionAndOption.setOptionName(answerOption);
					stuQuestionAndOption.setOptionTitle(answer);
					//3.去公共题库查出所有的选项  list  60
					List<SocietySchoolCourseNodeOption> optionList =
							societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,nodeId,societyStudentPractiseQuestionView.getQuersionId());

					//5.统计总分学习习题
					if(societyStudentPractiseQuestionView.getAnswerState().equals("1")){///答对了
						Integer newNum = societyStudentPractiseQuestionView.getQuestionScore().intValue();
						num += newNum;
					}
					//4.组成map  以学习选项为key  所有选项为value
					stuQuestionAndOption.setAnswerState(societyStudentPractiseQuestionView.getAnswerState());
					stuQuestionAndOption.setList(optionList);
					map.put(map.size()+1,stuQuestionAndOption);
				}
			}
//		Integer num = service.countFraction(query);
			//6.判断题目状态  遍历的时候判断isRight是否==1
			Integer rightNum = service.countRight(schoolId,nodeId,studentId,ownerStuCourseId);
			Integer wrongNum = service.countWrong(schoolId,nodeId,studentId,ownerStuCourseId);
			Integer noQuestionNum = service.counNoAns(schoolId,nodeId,studentId,ownerStuCourseId);
			Map<Object,Object> dataMap = new HashMap<>();
			dataMap.put("questionAndOption",map);
			dataMap.put("rightNum",rightNum);
			dataMap.put("wrongNum",wrongNum);
			dataMap.put("num",num);
			dataMap.put("noQuestionNum",noQuestionNum);

			buildResponse(modelAndView,dataMap);
			return modelAndView;
		}

	}



	@RequestMapping(value = "/taskAndStudyListGloble",method = RequestMethod.POST)
	public ModelAndView taskAndStudyListGloble(@ModelAttribute SocietyStudentPractiseQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String schoolId = query.getQueryObj().getOwnerSchoolId();
		//查出章节池的id
		SocietySchoolCourseNode societySchoolCourseNode =
				societySchoolCourseNodeService.loadById(query.getQueryObj().getOwnerNodeId());
		String nodeId = societySchoolCourseNode.getOwnerNodeResourcesId();
		String courseId = query.getQueryObj().getOwnerCourseId();
		String studentId = query.getQueryObj().getStudentId();
		String ownerStuCourseId = query.getQueryObj().getOwnerStuCourseId();
		Integer num = 0;
		Map<Integer,Object> map = new TreeMap<Integer, Object>(
				new Comparator<Integer>() {
					public int compare(Integer obj1, Integer obj2) {
						// 排序
						return obj1-obj2;
					}
				});
		//1.学习习题相关的所有题目  20  排序  遍历
		List<SocietyStudentPractiseQuestionView> studentQuestionList = service.taskAndStudylistByObj(query);
		if(studentQuestionList.size()==0){
			Map<Object,Object> dataMap = new HashMap<>();
			dataMap.put("rightNum",0);
			dataMap.put("wrongNum",0);
			dataMap.put("num",0);
			dataMap.put("noQuestionNum",0);
			dataMap.put("str","还未进行答题");
			buildResponse(modelAndView,dataMap);
			return modelAndView;
		}else {
			for(SocietyStudentPractiseQuestionView
					societyStudentPractiseQuestionView : studentQuestionList){
				//2.在学生习题表中查出相关学生的答题情况 list  20
				List<SocietyStudentPractiseQuestOptionView> stuOptionList =
						societyStudentPractiseQuestOptionService.selectByOneOption(schoolId,nodeId,
								societyStudentPractiseQuestionView.getId(),studentId,ownerStuCourseId);

				if(stuOptionList.size()==1){
					SocietyStudentPractiseQuestOptionView societyStudentPractiseQuestOptionView = stuOptionList.get(0);

					societyStudentPractiseQuestOptionView.setOrderNum(societyStudentPractiseQuestionView.getOrderNum());
					//3.去公共题库查出所有的选项  list  60
					List<SocietySchoolCourseNodeOption> optionList =
							societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,nodeId,societyStudentPractiseQuestionView.getQuersionId());
					//4.组成map  以学习选项为key  所有选项为value
					societyStudentPractiseQuestOptionView.setAnswerState(societyStudentPractiseQuestionView.getAnswerState());
					societyStudentPractiseQuestOptionView.setList(optionList);
					map.put(map.size()+1,societyStudentPractiseQuestOptionView);
					//5.统计总分学习习题
					if(societyStudentPractiseQuestionView.getAnswerState().equals("1")){///答对了
						Integer newNum = societyStudentPractiseQuestionView.getQuestionScore().intValue();
						num += newNum;
					}
				}else {
					String answer = "";
					String answerOption = "";
					for (SocietyStudentPractiseQuestOptionView societyStudentPractiseQuestOptionView : stuOptionList){
						if(answer.equals("")){
							answer = societyStudentPractiseQuestOptionView.getOptionTitle();
						}else {
							answer = answer+"@@"+societyStudentPractiseQuestOptionView.getOptionTitle();
						}
						if(answerOption.equals("")){
							answerOption = societyStudentPractiseQuestOptionView.getOptionName();
						}else {
							answerOption = answerOption+"@@"+societyStudentPractiseQuestOptionView.getOptionName();
						}
					}
					SocietyStudentPractiseQuestOptionView stuQuestionAndOption =
							societyStudentPractiseQuestOptionService.selectByOneOptionOnce(schoolId,nodeId,
									societyStudentPractiseQuestionView.getId(),studentId,ownerStuCourseId);
					stuQuestionAndOption.setOptionName(answerOption);
					stuQuestionAndOption.setOptionTitle(answer);
					//3.去公共题库查出所有的选项  list  60
					List<SocietySchoolCourseNodeOption> optionList =
							societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,nodeId,societyStudentPractiseQuestionView.getQuersionId());

					//5.统计总分学习习题
					if(societyStudentPractiseQuestionView.getAnswerState().equals("1")){///答对了
						Integer newNum = societyStudentPractiseQuestionView.getQuestionScore().intValue();
						num += newNum;
					}
					//4.组成map  以学习选项为key  所有选项为value
					stuQuestionAndOption.setAnswerState(societyStudentPractiseQuestionView.getAnswerState());
					stuQuestionAndOption.setList(optionList);
					map.put(map.size()+1,stuQuestionAndOption);
				}
			}
//		Integer num = service.countFraction(query);
			//6.判断题目状态  遍历的时候判断isRight是否==1
			Integer rightNum = service.countRight(schoolId,nodeId,studentId,ownerStuCourseId);
			Integer wrongNum = service.countWrong(schoolId,nodeId,studentId,ownerStuCourseId);
			Integer noQuestionNum = service.counNoAns(schoolId,nodeId,studentId,ownerStuCourseId);
			Map<Object,Object> dataMap = new HashMap<>();
			dataMap.put("questionAndOption",map);
			dataMap.put("rightNum",rightNum);
			dataMap.put("wrongNum",wrongNum);
			dataMap.put("num",num);
			dataMap.put("noQuestionNum",noQuestionNum);

			buildResponse(modelAndView,dataMap);
			return modelAndView;
		}

	}


	/*@RequestMapping(value = "/taskAndStudyListNew",method = RequestMethod.POST)
	public ModelAndView taskAndStudyListNew(@ModelAttribute SocietyStudentPractiseQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String schoolId = query.getQueryObj().getOwnerSchoolId();
		//查出章节池的id
		SocietySchoolCourseNode societySchoolCourseNode =
				societySchoolCourseNodeService.loadById(query.getQueryObj().getOwnerNodeId());
//		String nodeId = societySchoolCourseNode.getOwnerNodeResourcesId();
		String nodeId = query.getQueryObj().getOwnerNodeId();
		String courseId = query.getQueryObj().getOwnerCourseId();
		String studentId = query.getQueryObj().getStudentId();
		String ownerStuCourseId = query.getQueryObj().getOwnerStuCourseId();
		Integer num = 0;
		Map<Integer,Object> map = new TreeMap<Integer, Object>(
				new Comparator<Integer>() {
					public int compare(Integer obj1, Integer obj2) {
						// 排序
						return obj1-obj2;
					}
				});
		//1.学习习题相关的所有题目  20  排序  遍历
		SocietyStudent societyStudent = societyStudentService.loadById(studentId);
		String ascii = societyStudent.getStudentIdCardAscii();
		query.setStudentAscii(ascii);
		List<SocietyStudentPractiseQuestionView> studentQuestionListNew = service.taskAndStudylistByObjNew(query);
		if(studentQuestionListNew.size()==0){
			List<SocietyStudentPractiseQuestionView> studentQuestionList = service.taskAndStudylistByObj(query);
			if(studentQuestionList.size()==0){
				Map<Object,Object> dataMap = new HashMap<>();
				dataMap.put("rightNum",0);
				dataMap.put("wrongNum",0);
				dataMap.put("num",0);
				dataMap.put("noQuestionNum",0);
				dataMap.put("str","还未进行答题");
				buildResponse(modelAndView,dataMap);
				return modelAndView;
			}else {
				for(SocietyStudentPractiseQuestionView
						societyStudentPractiseQuestionView : studentQuestionList){
					//2.在学生习题表中查出相关学生的答题情况 list  20
					List<SocietyStudentPractiseQuestOptionView> stuOptionList =
							societyStudentPractiseQuestOptionService.selectByOneOption(schoolId,nodeId,
									societyStudentPractiseQuestionView.getId(),studentId,ownerStuCourseId);

					if(stuOptionList.size()==1){
						SocietyStudentPractiseQuestOptionView societyStudentPractiseQuestOptionView = stuOptionList.get(0);

						societyStudentPractiseQuestOptionView.setOrderNum(societyStudentPractiseQuestionView.getOrderNum());
						//3.去公共题库查出所有的选项  list  60
						List<SocietySchoolCourseNodeOption> optionList =
								societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,nodeId,societyStudentPractiseQuestionView.getQuersionId());
						//4.组成map  以学习选项为key  所有选项为value
						societyStudentPractiseQuestOptionView.setAnswerState(societyStudentPractiseQuestionView.getAnswerState());
						societyStudentPractiseQuestOptionView.setList(optionList);
						map.put(map.size()+1,societyStudentPractiseQuestOptionView);
						//5.统计总分学习习题
						if(societyStudentPractiseQuestionView.getAnswerState().equals("1")){///答对了
							Integer newNum = societyStudentPractiseQuestionView.getQuestionScore().intValue();
							num += newNum;
						}
					}else {
						String answer = "";
						String answerOption = "";
						for (SocietyStudentPractiseQuestOptionView societyStudentPractiseQuestOptionView : stuOptionList){
							if(answer.equals("")){
								answer = societyStudentPractiseQuestOptionView.getOptionTitle();
							}else {
								answer = answer+"@@"+societyStudentPractiseQuestOptionView.getOptionTitle();
							}
							if(answerOption.equals("")){
								answerOption = societyStudentPractiseQuestOptionView.getOptionName();
							}else {
								answerOption = answerOption+"@@"+societyStudentPractiseQuestOptionView.getOptionName();
							}
						}
						SocietyStudentPractiseQuestOptionView stuQuestionAndOption =
								societyStudentPractiseQuestOptionService.selectByOneOptionOnce(schoolId,nodeId,
										societyStudentPractiseQuestionView.getId(),studentId,ownerStuCourseId);
						stuQuestionAndOption.setOptionName(answerOption);
						stuQuestionAndOption.setOptionTitle(answer);
						//3.去公共题库查出所有的选项  list  60
						List<SocietySchoolCourseNodeOption> optionList =
								societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,nodeId,societyStudentPractiseQuestionView.getQuersionId());

						//5.统计总分学习习题
						if(societyStudentPractiseQuestionView.getAnswerState().equals("1")){///答对了
							Integer newNum = societyStudentPractiseQuestionView.getQuestionScore().intValue();
							num += newNum;
						}
						//4.组成map  以学习选项为key  所有选项为value
						stuQuestionAndOption.setAnswerState(societyStudentPractiseQuestionView.getAnswerState());
						stuQuestionAndOption.setList(optionList);
						map.put(map.size()+1,stuQuestionAndOption);
					}
				}
				//		Integer num = service.countFraction(query);
				//6.判断题目状态  遍历的时候判断isRight是否==1
				Integer rightNum = service.countRight(schoolId,nodeId,studentId,ownerStuCourseId);
				Integer wrongNum = service.countWrong(schoolId,nodeId,studentId,ownerStuCourseId);
				Integer noQuestionNum = service.counNoAns(schoolId,nodeId,studentId,ownerStuCourseId);
				Map<Object,Object> dataMap = new HashMap<>();
				dataMap.put("questionAndOption",map);
				dataMap.put("rightNum",rightNum);
				dataMap.put("wrongNum",wrongNum);
				dataMap.put("num",num);
				dataMap.put("noQuestionNum",noQuestionNum);

				buildResponse(modelAndView,dataMap);
				return modelAndView;
			}
		}else {
			for(SocietyStudentPractiseQuestionView
					societyStudentPractiseQuestionView : studentQuestionListNew){
				//2.在学生习题表中查出相关学生的答题情况 list  20
				List<SocietyStudentPractiseQuestOptionView> stuOptionList =
						societyStudentPractiseQuestOptionService.selectByOneOptionNew(schoolId,nodeId,
								societyStudentPractiseQuestionView.getId(),studentId,ownerStuCourseId,ascii);

				if(stuOptionList.size()==1){
					SocietyStudentPractiseQuestOptionView societyStudentPractiseQuestOptionView = stuOptionList.get(0);

					societyStudentPractiseQuestOptionView.setOrderNum(societyStudentPractiseQuestionView.getOrderNum());
					//3.去公共题库查出所有的选项  list  60
					List<SocietySchoolCourseNodeOption> optionList =
							societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,nodeId,societyStudentPractiseQuestionView.getQuersionId());
					//4.组成map  以学习选项为key  所有选项为value
					societyStudentPractiseQuestOptionView.setAnswerState(societyStudentPractiseQuestionView.getAnswerState());
					societyStudentPractiseQuestOptionView.setList(optionList);
					map.put(map.size()+1,societyStudentPractiseQuestOptionView);
					//5.统计总分学习习题
					if(societyStudentPractiseQuestionView.getAnswerState().equals("1")){///答对了
						Integer newNum = societyStudentPractiseQuestionView.getQuestionScore().intValue();
						num += newNum;
					}
				}else {
					String answer = "";
					String answerOption = "";
					for (SocietyStudentPractiseQuestOptionView societyStudentPractiseQuestOptionView : stuOptionList){
						if(answer.equals("")){
							answer = societyStudentPractiseQuestOptionView.getOptionTitle();
						}else {
							answer = answer+"@@"+societyStudentPractiseQuestOptionView.getOptionTitle();
						}
						if(answerOption.equals("")){
							answerOption = societyStudentPractiseQuestOptionView.getOptionName();
						}else {
							answerOption = answerOption+"@@"+societyStudentPractiseQuestOptionView.getOptionName();
						}
					}
					SocietyStudentPractiseQuestOptionView stuQuestionAndOption =
							societyStudentPractiseQuestOptionService.selectByOneOptionOnceNew(schoolId,nodeId,
									societyStudentPractiseQuestionView.getId(),studentId,ownerStuCourseId,ascii);
					stuQuestionAndOption.setOptionName(answerOption);
					stuQuestionAndOption.setOptionTitle(answer);
					//3.去公共题库查出所有的选项  list  60
					List<SocietySchoolCourseNodeOption> optionList =
							societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,nodeId,societyStudentPractiseQuestionView.getQuersionId());

					//5.统计总分学习习题
					if(societyStudentPractiseQuestionView.getAnswerState().equals("1")){///答对了
						Integer newNum = societyStudentPractiseQuestionView.getQuestionScore().intValue();
						num += newNum;
					}
					//4.组成map  以学习选项为key  所有选项为value
					stuQuestionAndOption.setAnswerState(societyStudentPractiseQuestionView.getAnswerState());
					stuQuestionAndOption.setList(optionList);
					map.put(map.size()+1,stuQuestionAndOption);
				}
			}
			//		Integer num = service.countFraction(query);
			//6.判断题目状态  遍历的时候判断isRight是否==1
			Integer rightNum = service.countRightNew(schoolId,nodeId,studentId,ownerStuCourseId,ascii);
			Integer wrongNum = service.countWrongNew(schoolId,nodeId,studentId,ownerStuCourseId,ascii);
			Integer noQuestionNum = service.counNoAnsNew(schoolId,nodeId,studentId,ownerStuCourseId,ascii);
			Map<Object,Object> dataMap = new HashMap<>();
			dataMap.put("questionAndOption",map);
			dataMap.put("rightNum",rightNum);
			dataMap.put("wrongNum",wrongNum);
			dataMap.put("num",num);
			dataMap.put("noQuestionNum",noQuestionNum);

			buildResponse(modelAndView,dataMap);
			return modelAndView;
		}
	}*/


	@RequestMapping(value = "/taskAndStudyListNew1",method = RequestMethod.POST)
	public ModelAndView taskAndStudyListNew1(@ModelAttribute SocietyStudentPractiseQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String schoolId = query.getQueryObj().getOwnerSchoolId();
		String nodeId = query.getQueryObj().getOwnerNodeId();
		String courseId = query.getQueryObj().getOwnerCourseId();
		String studentId = query.getQueryObj().getStudentId();
		String ownerStuCourseId = query.getQueryObj().getOwnerStuCourseId();
		String ownerStudentAndNodeId = query.getQueryObj().getOwnerStudentAndNodeId();
		SocietyStudent societyStudent = societyStudentService.loadById(studentId);
		SocietyStudentAndNode societyStudentAndNode =
				societyStudentAndNodeService.loadById(ownerStudentAndNodeId);
		SocietySchoolCourseNode societySchoolCourseNode = societySchoolCourseNodeService.loadById(nodeId);
		//1.拿到所有的题目
		String resourceId = societySchoolCourseNode.getOwnerNodeResourcesId();
		List<SocietySchoolCourseNodeQuestionView> questionList =
				societySchoolCourseNodeQuestionService.selectByNodeId(resourceId);

		//拿到学生ascii
		String ascii = societyStudent.getStudentIdCardAscii();
		//2.拿到学生所有的答题记录
		List<SocietyStudentPractiseQuestionView> stuQuesList =
				service.selectByStudentAndNodeId(ownerStudentAndNodeId,ascii);
		if (stuQuesList.size()==0){
			Map<Object,Object> dataMap = new HashMap<>();
			dataMap.put("rightNum",0);
			dataMap.put("wrongNum",0);
			dataMap.put("score",0);
			dataMap.put("noQuestionNum",0);
			dataMap.put("str","还未进行答题");
			buildResponse(modelAndView,dataMap);
			return modelAndView;
		}else{
			for(SocietySchoolCourseNodeQuestionView question : questionList){
				//1.每次循环去学生集合忠查看是否答题了
				SocietyStudentPractiseQuestionView stuQuestion =
						service.selectByStuNodeIdAndQuestionId(ownerStudentAndNodeId,question.getId(),ascii);

				if (stuQuestion!=null){
					//3.有查看选择了那些选项
					//4.如果正确
					//5.如果多选答错 选对的选项页面展示按照isSelect颜色显示
					if (stuQuestion.getStuSelectOpId().equals(question.getQuestionAnswerId())){
						//选对
						question.setStudentIsAnswer("1");
						List<SocietySchoolCourseNodeOptionView> optionList =
								societySchoolCourseNodeOptionService.selectOptionByQuestionNew(question.getId());
						for (SocietySchoolCourseNodeOptionView optionView : optionList){
							if (optionView.getIsRight().equals("1")){
								optionView.setIsSelect("1");
							}else {
								optionView.setIsSelect("0");
							}
						}
						question.setOptionList(optionList);
					}else {
						//证明选错
						question.setStudentIsAnswer("-1");
						List<SocietySchoolCourseNodeOptionView> optionList =
								societySchoolCourseNodeOptionService.selectOptionByQuestionNew(question.getId());
						for (SocietySchoolCourseNodeOptionView optionView : optionList){
							String optionId = optionView.getId();
							if (optionId.indexOf(stuQuestion.getStuSelectOpId())!=-1){
								//证明学生选择了这个选项
								optionView.setIsSelect("1");
							}else {
								optionView.setIsSelect("0");
							}
						}
						question.setOptionList(optionList);
					}
				}else {
					//2.如果没有视为未答 添加一个答题
					question.setStudentIsAnswer("0");
					//查出所有的选项
					List<SocietySchoolCourseNodeOptionView> optionList =
							societySchoolCourseNodeOptionService.selectOptionByQuestionNew(question.getId());
					for (SocietySchoolCourseNodeOptionView optionView : optionList){
						optionView.setIsSelect("0");
					}
					question.setOptionList(optionList);
				}
			}

			Integer rightNum = service.countRightNew(ownerStudentAndNodeId,ascii);
			Integer wrongNum = service.countWrongNew(ownerStudentAndNodeId,ascii);
			Integer noQuestionNum = questionList.size()-stuQuesList.size();

			Map<String,Object> map = new HashMap<>();
			map.put("questionList",questionList);
			map.put("rightNum",rightNum);
			map.put("wrongNum",wrongNum);
			map.put("noQuestionNum",noQuestionNum);
			map.put("score",societyStudentAndNode.getNodeQuestionScore());
			buildResponse(modelAndView,map);
			return modelAndView;
		}
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
