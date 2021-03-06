package com.edgedo.society.controller;


import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolCourseNodeOptionMapper;
import com.edgedo.society.mapper.SocietySchoolCourseNodeQuestionMapper;
import com.edgedo.society.mapper.SocietyStudentPractiseQuestOptionMapper;
import com.edgedo.society.mapper.SocietyStudentPractiseQuestionMapper;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
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


@Api(tags = "SocietySchoolClassAndStudent")
@Controller
@RequestMapping("/society/societySchoolClassAndStudent")
public class SocietySchoolClassAndStudentController extends BaseController{
	
	@Autowired
	private SocietySchoolClassAndStudentService service;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolClassAndCourseService societySchoolClassAndCourseService;
	@Autowired
	private SocietySchoolClassAdminService societySchoolClassAdminService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	@Autowired
	private SocietySchoolClassGroupAdminService societySchoolClassGroupAdminService;
	@Autowired
	private SocietySchoolCourseNodeQuestionMapper nodeQuestionMapper;
	@Autowired
	private SocietyStudentPractiseQuestionMapper studentPractiseQuestionMapper;
	@Autowired
	private SocietyStudentPractiseQuestOptionMapper studentPractiseQuestOptionMapper;
	@Autowired
	private SocietySchoolCourseNodeOptionMapper nodeOptionMapper;


	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietySchoolClassAndStudent", notes = "????????????SocietySchoolClassAndStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ????????????id ?????????id ???????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByStudentIdAllClassAndCourse",method = RequestMethod.POST)
	public ModelAndView selectByStudentIdAllClassAndCourse(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		String classId = schoolClassService.selectByClassAdminIdOnceClass(classAdminId);
		query.getQueryObj().setClassId(classId);
		service.selectByStuIdAndSchoolIdlistPage(query);
		List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
		for(SocietySchoolClassAndStudentView view : classAndcourseList){
			SocietyStudentAndCourse societyStudentAndCourse =
					societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),user.getCompId());
			if(societyStudentAndCourse == null){
				continue;
			}else {
				view.setCourseId(societyStudentAndCourse.getCourseId());
				view.setCourseAndStudentId(societyStudentAndCourse.getId());
				view.setCourseName(societyStudentAndCourse.getCourseName());
				view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
			}
		}
		query.setList(classAndcourseList);
		return buildResponse(modelAndView,query);
	}


	/**
	 * ????????????id ?????????id ???????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByStudentIdAllClassAndCourseNew",method = RequestMethod.POST)
	public ModelAndView selectByStudentIdAllClassAndCourseNew(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectByStuIdAndSchoolIdlistPage(query);
		List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
		for(SocietySchoolClassAndStudentView view : classAndcourseList){
			SocietyStudentAndCourse societyStudentAndCourse =
					societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),view.getOwnerSchoolId());
			if(societyStudentAndCourse == null){
				continue;
			}else {
				view.setCourseId(societyStudentAndCourse.getCourseId());
				view.setCourseAndStudentId(societyStudentAndCourse.getId());
				view.setCourseName(societyStudentAndCourse.getCourseName());
				view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
			}
		}
		query.setList(classAndcourseList);
		return buildResponse(modelAndView,query);
	}

	@RequestMapping(value = "/selectByStudentIdAllClassAndCourseGroup",method = RequestMethod.POST)
	public ModelAndView selectByStudentIdAllClassAndCourseGroup(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		String groupId = societySchoolClassGroupAdminService.selectBySysUserId(user.getUserId());
		List<String> classAdminId = societySchoolClassAdminService.selectByGroupId(groupId);
		List<String> classId = schoolClassService.selectByClassAdminIdList(classAdminId);
		query.getQueryObj().setClassIdList(classId);
		service.selectByStuIdAndSchoolIdlistPage(query);
		List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
		for(SocietySchoolClassAndStudentView view : classAndcourseList){
			SocietyStudentAndCourse societyStudentAndCourse =
					societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),user.getCompId());
			if(societyStudentAndCourse == null){
				continue;
			}else {
				view.setCourseId(societyStudentAndCourse.getCourseId());
				view.setCourseAndStudentId(societyStudentAndCourse.getId());
				view.setCourseName(societyStudentAndCourse.getCourseName());
				view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
			}
		}
		query.setList(classAndcourseList);
		return buildResponse(modelAndView,query);
	}

	/**
	 * ????????????
	 * ????????????id  ???????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByStudentIdAllClassAndCourseRenShe",method = RequestMethod.POST)
	public ModelAndView selectByStudentIdAllClassAndCourseRenShe(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		service.selectByStuIdAndSchoolIdlistPage(query);
		List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
		for(SocietySchoolClassAndStudentView view : classAndcourseList){
			SocietyStudentAndCourse societyStudentAndCourse =
					societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),view.getOwnerSchoolId());
			if(societyStudentAndCourse == null){
				continue;
			}else {
				view.setCourseId(societyStudentAndCourse.getCourseId());
				view.setCourseAndStudentId(societyStudentAndCourse.getId());
				view.setCourseName(societyStudentAndCourse.getCourseName());
				view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
			}
		}
		query.setList(classAndcourseList);
		return buildResponse(modelAndView,query);
	}


	/**
	 * ????????????????????????????????????
	 * ?????????????????????id
	 * ????????????id ?????????id ???????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByClassAdminStudentIdAllClassAndCourse",method = RequestMethod.POST)
	public ModelAndView selectByClassAdminStudentIdAllClassAndCourse(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		//???????????????id
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classIdlist = schoolClassService.selectByClassAdminId(classAdminId);
		if(classIdlist.size()!=0){
			query.setClassAdminId(classIdlist);
			service.selectByClassIdStuIdAndSchoolIdlistPage(query);
			List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
			for(SocietySchoolClassAndStudentView view : classAndcourseList){
				SocietyStudentAndCourse societyStudentAndCourse =
						societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),user.getCompId());
				if(societyStudentAndCourse == null){
					continue;
				}else {
					view.setCourseAndStudentId(societyStudentAndCourse.getId());
					view.setCourseName(societyStudentAndCourse.getCourseName());
					view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
				}
			}
			query.setList(classAndcourseList);
			return buildResponse(modelAndView,query);
		}else {
			return buildResponse(modelAndView,query);
		}
	}


	/**
	 * ???????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listByObj",method = RequestMethod.POST)
	public ModelAndView listByObj(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ?????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listByObjForSchool",method = RequestMethod.POST)
	public ModelAndView listByObjForSchool(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		Map<String,String> map = societySchoolService.selectBySchoolAdminId(user.getUserId());
		query.getQueryObj().setOwnerSchoolId(map.get("ID"));
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBySchoolAndClassBySchoolId",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClassBySchoolId(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> list = schoolClassService.selectByClassAdminId(classAdminId);
		query.setClassAdminId(list);
		service.selectBySchoolNamelistPage(query);
		List<SocietySchoolClassAndStudentView> classAndStuList = query.getList();
		for(SocietySchoolClassAndStudentView societySchoolClassAndStudentView : classAndStuList){
			String courseAndStudentId = societyStudentAndCourseService.selectStuAndClassId(societySchoolClassAndStudentView.getClassId(),
					societySchoolClassAndStudentView.getStudentId());
			societySchoolClassAndStudentView.setCourseAndStudentId(courseAndStudentId);
		}
		query.setList(classAndStuList);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ???????????????????????????????????????????????????
	 */
	@RequestMapping(value = "/selectBySchoolAndClassBySchoolIdAndClassId",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClassBySchoolIdAndClassId(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classId = schoolClassService.selectByClassAdminId(classAdminId);
		query.setClassAdminId(classId);
		service.selectBySchoolAndClassBySchoolIdAndClassIdlistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	//??????id ??????name -> ??????id ??????id -> ??????id ??????id  ??????id ??????id
	//					??????id ??????id
	@RequestMapping(value = "/selectBySchoolName",method = RequestMethod.POST)
	public ModelAndView selectBySchoolName(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectBySchoolNamelistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBySchoolAndClass",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClass(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectBySchoolNamelistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????????????????
	 * @Date 2020/5/4 14:01
	 **/
	@RequestMapping(value = "/insertClassAndStudentRelation",method = RequestMethod.POST)
	public ModelAndView insertClassAndStudentRelation(String stuIds,String classId){
		ModelAndView modelAndView = new ModelAndView();
		SocietySchoolClass schoolClass = schoolClassService.loadById(classId);
		if(schoolClass == null){
			return buildErrorResponse(modelAndView,"????????????????????????");
		}
		//??????????????????
		String errMsg = service.insertClassAndStudentRelation(schoolClass,stuIds);
		if(errMsg != null && !errMsg.equals("")){
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("success", false);
			modelAndView.addObject("errMsg", errMsg);
			return modelAndView;
		}
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????????????????????????????
	 * @Date 2020/5/5 8:58
	 **/
	@RequestMapping(value = "/logicDeleteClassStuRelation",method = RequestMethod.POST)
	public ModelAndView logicDeleteClassStuRelation(String stuIds,String classId){
		ModelAndView modelAndView = new ModelAndView();
		SocietySchoolClass schoolClass = schoolClassService.loadById(classId);
		if(schoolClass == null){
			return buildErrorResponse(modelAndView,"???????????????????????????");
		}
		service.deleteByClassAndStudent(stuIds,classId);
		return buildResponse(modelAndView);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????ID???????????????????????????
	 *@DateTime: 2020/5/29 16:21
	 */
	@RequestMapping(value = "/oneClickFinish",method = RequestMethod.POST)
	public ModelAndView oneClickFinish(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		String errMsg = service.oneClickFinish(list);
		return buildResponse(modelAndView,errMsg);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????ID???????????????????????????
	 *@DateTime: 2020/5/29 16:21
	 */
	@RequestMapping(value = "/suiJiFinish",method = RequestMethod.POST)
	public ModelAndView suiJiFinish(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		String errMsg = service.suiJiFinish(list);
		return buildResponse(modelAndView,errMsg);
	}

	/**
	 * ????????????????????????
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/selectVoClassId",method = RequestMethod.POST)
	public ModelAndView selectVoClassId(String classId){
		ModelAndView modelAndView = new ModelAndView();
		int numStudent = service.selectVoClassId(classId);
		String courseId = societySchoolClassAndCourseService.selectByClassId(classId);
		Map<String,Object> map = new HashMap<>();
		map.put("numStudent",numStudent);
		map.put("courseId",courseId);
		return buildResponse(modelAndView,map);
	}

	//???????????????
	public Map<String,String> getRandMap(int max){
		Map<String,String> map = new HashMap<>();
		int minRightNum = new Double(max*0.6).intValue();
		int maxWrongNum = max-minRightNum;
		Random rand = new Random();
		int num = rand.nextInt(maxWrongNum+1);
		if(num>maxWrongNum){
			num = maxWrongNum;
		}
		for( int i=0; i<num; i++) {
			int rNum = rand.nextInt(max);
			map.put(rNum+"","");
		}
		return map;
	}

	public void finishedStuNodeQuestionNew(SocietyStudentAndNodeView studentAndNodeView) {
		/*????????????????????????ID*/
		String stuAndNodeId = studentAndNodeView.getId();
		String nodeId = studentAndNodeView.getNodeId();
		String studentId = studentAndNodeView.getStudentId();
		Date questionTime = studentAndNodeView.getQuestionTime();
		//????????????????????????
		Map<String,String> map = getRandMap(studentAndNodeView.getTotalQuestionNum());
		//????????????ID????????????????????????????????????
		List<SocietySchoolCourseNodeQuestionView> nodeQuestionViewList = nodeQuestionMapper.listByNodeId(nodeId);
		nodeQuestionViewList.forEach(question ->{
			String questionId = question.getId();
			int index = nodeQuestionViewList.indexOf(question);
			String answerState = "1";
			int getScore = question.getQuestionScore();
			if(map.get(index+"")!=null){
				answerState = "-1";
				getScore = 0;
			}
			//?????????????????????????????????
			SocietyStudent societyStudent = studentService.loadById(studentId);
			String ascii  = societyStudent.getStudentIdCardAscii();
//			SocietyStudentPractiseQuestionView studentPractiseQuestionView =studentPractiseQuestionMapper.loadByStuIdAndQuestionId(studentId,stuAndNodeId,questionId);
			SocietyStudentPractiseQuestionView studentPractiseQuestionView =studentPractiseQuestionMapper.loadByStuIdAndQuestionIdNew(studentId,stuAndNodeId,questionId,ascii);

			if(studentPractiseQuestionView!=null){
				//???????????????????????????
				studentPractiseQuestOptionMapper.deleteByQuestionIdNew(studentPractiseQuestionView.getId(),ascii);
				studentPractiseQuestionMapper.deleteByIdNew(studentPractiseQuestionView.getId(),ascii);
			}
			String stuQuestionId = Guid.guid();
			/*List<SocietySchoolCourseNodeOption> nodeOptionList = nodeOptionMapper.selectOptionByQuestion(questionId);
			nodeOptionList.forEach(nodeOption ->{
				String nodeOptionId = nodeOption.getId();
				//??????????????????????????????
				SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuIdAndOptionIdNew(studentId,stuAndNodeId,nodeOptionId,ascii);
				if(societyStudentPractiseQuestOption==null){
					societyStudentPractiseQuestOption = new SocietyStudentPractiseQuestOption();
					societyStudentPractiseQuestOption.setId(Guid.guid());
					societyStudentPractiseQuestOption.setCreateTime(questionTime);
					societyStudentPractiseQuestOption.setOwnerStudentAndNodeId(stuAndNodeId);
					societyStudentPractiseQuestOption.setOwnerStuCourseId(studentAndNodeView.getOwnerStudentAndCourseId());
					societyStudentPractiseQuestOption.setStudentId(studentId);
					societyStudentPractiseQuestOption.setOwnerSchoolId(studentAndNodeView.getOwnerSchoolId());
					societyStudentPractiseQuestOption.setOwnerSchoolName(studentAndNodeView.getOwnerSchoolName());
					societyStudentPractiseQuestOption.setOwnerNodeId(studentAndNodeView.getNodeId());
					societyStudentPractiseQuestOption.setOwnerNodeName(studentAndNodeView.getNodeName());
					societyStudentPractiseQuestOption.setOwnerNodeQueOpId(nodeOptionId);
					societyStudentPractiseQuestOption.setOptionTitle(nodeOption.getOptionTitle());
					societyStudentPractiseQuestOption.setOptionName(nodeOption.getOptionName());
					if(question.getQuestionType().equals("1")){
						if(question.getQuestionAnswer().contains(nodeOption.getOptionTitle())){
							societyStudentPractiseQuestOption.setIsRight("1");
						}else {
							societyStudentPractiseQuestOption.setIsRight("0");
						}
					}else {
						societyStudentPractiseQuestOption.setIsRight(nodeOption.getIsRight());
					}
					societyStudentPractiseQuestOption.setIsSelect("0");
					societyStudentPractiseQuestOption.setOrderNum(nodeOption.getOrderNum());
					societyStudentPractiseQuestOption.setOwnerStudentQuersionId(stuQuestionId);
					societyStudentPractiseQuestOption.setOwnerStudentQuersionName(question.getQuestionName());
					societyStudentPractiseQuestOption.setLastAnswerTime(questionTime);
					studentPractiseQuestOptionMapper.insertNew(societyStudentPractiseQuestOption,societyStudent.getStudentIdCardAscii());
					//System.out.println("societyStudentPractiseQuestOption====="+societyStudentPractiseQuestOption);
				}
			});*/
			studentPractiseQuestionView = new SocietyStudentPractiseQuestionView();
			studentPractiseQuestionView.setId(stuQuestionId);
			studentPractiseQuestionView.setCreateTime(questionTime);
			studentPractiseQuestionView.setOwnerStudentAndNodeId(stuAndNodeId);
			studentPractiseQuestionView.setOwnerStuCourseId(studentAndNodeView.getOwnerStudentAndCourseId());
			studentPractiseQuestionView.setStudentId(studentId);
			studentPractiseQuestionView.setStudentName(studentAndNodeView.getStudentName());
			studentPractiseQuestionView.setStudentIdCardNum(studentAndNodeView.getStudentIdCardNum());
			studentPractiseQuestionView.setOwnerSchoolId(studentAndNodeView.getOwnerSchoolId());
			studentPractiseQuestionView.setOwnerSchoolName(studentAndNodeView.getOwnerSchoolName());
			studentPractiseQuestionView.setOwnerCourseId(studentAndNodeView.getOwnerCourseId());
			studentPractiseQuestionView.setOwnerCourseName(studentAndNodeView.getOwnerCourseName());
			studentPractiseQuestionView.setOwnerNodeId(studentAndNodeView.getNodeId());
			studentPractiseQuestionView.setOwnerNodeName(studentAndNodeView.getNodeName());
			studentPractiseQuestionView.setQuersionId(questionId);
			studentPractiseQuestionView.setQuestionName(question.getQuestionName());
			studentPractiseQuestionView.setQuestionType(question.getQuestionType());
			studentPractiseQuestionView.setQuestionScore(question.getQuestionScore());
			studentPractiseQuestionView.setAnswerState(answerState);
			studentPractiseQuestionView.setGetScore(getScore);
			studentPractiseQuestionView.setOrderNum(question.getOrderNum());
			//TODO  ????????????
			/*SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption = null;
			if(answerState.equals("1")){
				//?????????????????????????????????
				societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionIdNew(stuQuestionId,"1",societyStudent.getStudentIdCardAscii());
				*//*	if(societyStudentPractiseQuestOption==null){
						System.out.println("stuQuestionId========"+stuQuestionId);
					}*//*
				societyStudentPractiseQuestOption.setIsSelect("1");
				studentPractiseQuestOptionMapper.updateByIdNew(societyStudentPractiseQuestOption,societyStudent.getStudentIdCardAscii());
			}else {
				//?????????????????????????????????
				societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionIdNew(stuQuestionId,"0",societyStudent.getStudentIdCardAscii());
				societyStudentPractiseQuestOption.setIsSelect("1");
				studentPractiseQuestOptionMapper.updateByIdNew(societyStudentPractiseQuestOption,societyStudent.getStudentIdCardAscii());
			}*/
			if(answerState.equals("1")){
				//??????????????????
				studentPractiseQuestionView.setStuSelectOpId(question.getQuestionAnswerId());
			}else {
				//????????????????????????
				String optionId = nodeOptionMapper.selectByQuestionIdWrong(questionId);
				studentPractiseQuestionView.setStuSelectOpId(optionId);
			}

			studentPractiseQuestionView.setLastAnswerTime(questionTime);
			studentPractiseQuestionView.setDataState("1");
			studentPractiseQuestionMapper.insertNew(studentPractiseQuestionView,societyStudent.getStudentIdCardAscii());
		});
	}



}
