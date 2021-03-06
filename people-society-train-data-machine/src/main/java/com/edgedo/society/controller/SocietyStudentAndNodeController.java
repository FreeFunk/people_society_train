package com.edgedo.society.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.entity.SocietyStudentAndNode;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;
import com.edgedo.society.mapper.*;
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


@Api(tags = "SocietyStudentAndNode")
@Controller
@RequestMapping("/society/societyStudentAndNode")
public class SocietyStudentAndNodeController extends BaseController{
	
	@Autowired
	private SocietyStudentAndNodeService service;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
    private SocietySchoolCourseNodeService societySchoolCourseNodeService;

	@Autowired
	private SocietyStudentPractiseQuestOptionService societyStudentPractiseQuestOptionService;
	@Autowired
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	@Autowired
	private SocietyStudentStudyProcessFaceService societyStudentStudyProcessFaceService;

	@Autowired
	private SocietyStudentAndNodeMapper societyStudentAndNodeMapper;
	@Autowired
	private SocietySchoolCourseNodeMapper nodeMapper;
	@Autowired
	private SocietySchoolCourseNodeService nodeService;
	@Autowired
	private SocietyStudentPractiseQuestionMapper studentPractiseQuestionMapper;
	@Autowired
	private SocietyStudentAndCourseMapper societyStudentAndCourseMapper;
	@Autowired
	private SocietyStudentStudyProcessFaceMapper societyStudentStudyProcessFaceMapper;
	@Autowired
	private SocietySchoolConfigService societySchoolConfigService;
	@Autowired
	private SocietySchoolConfigMapper societySchoolConfigMapper;
	@Autowired
	private SocietySchoolClassMapper societySchoolClassMapper;
	@Autowired
	private SocietySchoolClassAndStudentController societySchoolClassAndStudentController;

	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudentAndNode", notes = "????????????SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectlistpage",method = RequestMethod.POST)
	public ModelAndView selectlistpage(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectlistpage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * ?????????????????????????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "???????????????SocietyStudentAndNode", notes = "???????????????SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjForSchool",method = RequestMethod.POST)
	public ModelAndView listByObjForSchool(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBystudentAndnode",method = RequestMethod.POST)
	public ModelAndView selectBystudentAndnode(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getStudentId().equals("") && query.getQueryObj().getOwnerStudentAndCourseId().equals("")){
			return buildResponse(modelAndView,query);
		}else {
			User user = getLoginUser();
			query.getQueryObj().setOwnerSchoolId(user.getCompId());
			service.listPage(query);
			return buildResponse(modelAndView,query);
		}
	}


	@RequestMapping(value = "/selectBystudentAndnodeNew",method = RequestMethod.POST)
	public ModelAndView selectBystudentAndnodeNew(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getStudentId().equals("") && query.getQueryObj().getOwnerStudentAndCourseId().equals("")){
			return buildResponse(modelAndView,query);
		}else {
			service.listPageNew(query);
			return buildResponse(modelAndView,query);
		}
	}


	@RequestMapping(value = "/selectBystudentAndnodeRenShe",method = RequestMethod.POST)
	public ModelAndView selectBystudentAndnodeRenShe(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getStudentId().equals("") && query.getQueryObj().getOwnerStudentAndCourseId().equals("")){
			return buildResponse(modelAndView,query);
		}else {
			service.listPage(query);
			return buildResponse(modelAndView,query);
		}
	}
	/**
	 * ????????????
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudentAndNode", notes = "????????????SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentAndNodeView voObj){
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



	@RequestMapping(value = "/saveOrUpdateNew",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateNew(SocietyStudentAndNodeView societyStudentAndNode){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		//?????? ??????????????????????????????????????? ??????????????????????????????????????????
		SocietyStudentStudyProcessFace societyStudentStudyProcessFace =
				societyStudentStudyProcessFaceService.selectByStuCouIdAndNodeIdOnce(societyStudentAndNode.getOwnerStudentAndCourseId(),
						societyStudentAndNode.getNodeId());
		long faceLastTime = societyStudentStudyProcessFace.getCreateTime().getTime();
		long quesTionTime = societyStudentAndNode.getQuestionTime().getTime();
		long nodeFinishTime = societyStudentAndNode.getFinishTime().getTime();
		if(faceLastTime>quesTionTime){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "?????????????????????????????????");
			return modelAndView;
		}
		if (faceLastTime>nodeFinishTime){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "???????????????????????????????????????");
			return modelAndView;
		}
		if (nodeFinishTime>quesTionTime){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "??????????????????????????????????????????");
			return modelAndView;
		}
//		errMsg = service.update(voObj);
		//??????????????????
		SocietyStudentAndNode societyStudentAndNode1 =
				societyStudentAndNodeMapper.selectById(societyStudentAndNode.getId());
		societyStudentAndNode.setOwnerStudentAndCourseId(societyStudentAndNode1.getOwnerStudentAndCourseId());
		societyStudentAndNode.setOwnerSchoolId(societyStudentAndNode1.getOwnerSchoolId());
		societyStudentAndNode.setOwnerSchoolName(societyStudentAndNode1.getOwnerSchoolName());
		societyStudentAndNode.setNodeId(societyStudentAndNode1.getNodeId());
		societyStudentAndNode.setNodeName(societyStudentAndNode1.getNodeName());

		societyStudentAndNode.setStudentName(societyStudentAndNode1.getStudentName());
		societyStudentAndNode.setStudentIdCardNum(societyStudentAndNode1.getStudentIdCardNum());
		societyStudentAndNode.setOwnerCourseId(societyStudentAndNode1.getOwnerCourseId());
		societyStudentAndNode.setOwnerCourseName(societyStudentAndNode1.getOwnerCourseName());
		if(societyStudentAndNode1.getTotalQuestionNum()==null){
			Integer countNum = societySchoolCourseNodeQuestionService.selectByNodeId(societyStudentAndNode1.getNodeId());
			societyStudentAndNode.setTotalQuestionNum(countNum);
		}else {
			societyStudentAndNode.setTotalQuestionNum(societyStudentAndNode1.getTotalQuestionNum());
		}
		societyStudentAndNode.setQuestionIsPass("1");
		societyStudentAndNode.setQuestionIsFinished("1");
		societySchoolClassAndStudentController.finishedStuNodeQuestionNew(societyStudentAndNode);
		int countRightNum = studentPractiseQuestionMapper.countByStudentAndNodeId(societyStudentAndNode.getId());
		BigDecimal average;
		int questNum =societyStudentAndNode.getTotalQuestionNum();
		if (questNum==0){
			average = new BigDecimal(60);
		}else {
			average = new BigDecimal( (float) countRightNum /questNum * 100).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		societyStudentAndNode.setNodeQuestionScore(average.intValue());
		societyStudentAndNodeMapper.updateById(societyStudentAndNode);
		//??????????????????
		String studentCourseId = societyStudentAndNode1.getOwnerStudentAndCourseId();
		SocietyStudentAndCourse societyStudentAndCourse = societyStudentAndCourseMapper.selectById(studentCourseId);
		SocietyStudentAndCourse societyStudentAndCourseNew = new SocietyStudentAndCourse();
		societyStudentAndCourseNew.setId(studentCourseId);
		Integer finsihNum = societyStudentAndNodeMapper.selectByStuCouId(studentCourseId);
		Integer courseNum = societyStudentAndCourse.getTotalLessons();
		societyStudentAndCourseNew.setFinishNodeNum(finsihNum);
		societyStudentAndCourseNew.setCourseProgress(new BigDecimal(finsihNum*100/courseNum));
		SocietyStudentAndNode societyStudentAndNodeDesc = societyStudentAndNodeMapper.selectByStuCouIdDesc(studentCourseId);
		societyStudentAndCourseNew.setLearnFinishTime(societyStudentAndNodeDesc.getFinishTime());
		societyStudentAndCourseMapper.updateById(societyStudentAndCourseNew);
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	/**
	 * ????????????
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "??????ID????????????SocietyStudentAndNode", notes = "??????ID????????????SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
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
	 * ??????????????????
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "??????ID??????SocietyStudentAndNode", notes = "??????ID??????SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/*
	* ??????????????????????????????
	* */
	@RequestMapping(value = "/selectByCourseName",method = RequestMethod.POST)
	public ModelAndView selectByCourseName(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String courseId = query.getQueryObj().getOwnerCourseId();
		String schoolId = query.getQueryObj().getOwnerSchoolId();
		String nodeName = query.getQueryObj().getNodeName();
		String examineState = query.getQueryObj().getExamineState();
		String stuAndCouId = query.getQueryObj().getOwnerStudentAndCourseId();

		SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery =
				societySchoolCourseNodeService.selectByCourseIdAndSchoolIdlistPage(
						query.getLimit(),query.getPage(),query.getStart(),query.getOrderBy(),
						courseId,schoolId,nodeName,examineState,stuAndCouId);
		//1.????????????   ?????????????????????????????????????????? ?????????????????? ????????????????????????
		List<SocietyStudentAndNodeView> studentList = service.selectStudentCourse(query);//??????????????????
		List<SocietySchoolCourseNodeView> allList = societySchoolCourseNodeQuery.getList();//??????????????????
		for(int i = 0;i<studentList.size();i++){
			for(int j = 0;j<allList.size();j++){
				if(studentList.get(i).getNodeId().equals(allList.get(j).getId())){
					allList.get(j).setStudentId(studentList.get(i).getStudentId());//??????id
					allList.get(j).setStudentName(studentList.get(i).getStudentName());//????????????
					allList.get(j).setStudentIdCardNum(studentList.get(i).getStudentIdCardNum());//????????????
					allList.get(j).setNodeProgress(studentList.get(i).getNodeProgress().intValue());//????????????
					allList.get(j).setNodeQuestionScore(studentList.get(i).getNodeQuestionScore());//????????????
					allList.get(j).setQuestionIsFinished(studentList.get(i).getQuestionIsFinished());//??????????????????
					allList.get(j).setQuestionIsPass(studentList.get(i).getQuestionIsPass());//??????????????????
					allList.get(j).setLearnIsFinished(studentList.get(i).getLearnIsFinished());//??????????????????
					allList.get(j).setStudyTimeLength(studentList.get(i).getStudyTimeLength());//??????
					allList.get(j).setExamineState(studentList.get(i).getExamineState());
					allList.get(j).setRightNum(studentList.get(i).getNodeQuestionScore());
					allList.get(j).setFinishTime(studentList.get(i).getFinishTime());
				}
			}
		}
		societySchoolCourseNodeQuery.setList(allList);
		buildResponse(modelAndView,societySchoolCourseNodeQuery);
		return modelAndView;
	}

	@RequestMapping(value = "/selectByNodeExamine",method = RequestMethod.POST)
	public ModelAndView selectByNodeExamine(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//1.????????????   ?????????????????????????????????????????? ?????????????????? ????????????????????????
		String courseId = query.getQueryObj().getOwnerCourseId();
		String schoolId = query.getQueryObj().getOwnerSchoolId();
		String nodeName = query.getQueryObj().getNodeName();
		String examineState = query.getQueryObj().getExamineState();
		String stuAndCouId = query.getQueryObj().getOwnerStudentAndCourseId();

		SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery =
				societySchoolCourseNodeService.selectByCourseIdAndSchoolIdlistPageExamin(
						query.getLimit(),query.getPage(),query.getStart(),query.getOrderBy(),
						courseId,schoolId,nodeName,examineState,stuAndCouId);
		//1.????????????   ?????????????????????????????????????????? ?????????????????? ????????????????????????
		List<SocietyStudentAndNodeView> studentList = service.selectByCourseNamelistPageNew(query);//??????????????????
		List<SocietySchoolCourseNodeView> allList = societySchoolCourseNodeQuery.getList();//??????????????????

		for(int i = 0;i<studentList.size();i++){
			for(int j = 0;j<allList.size();j++){
				if(studentList.get(i).getNodeId().equals(allList.get(j).getId())){
					allList.get(j).setStudentId(studentList.get(i).getStudentId());//??????id
					allList.get(j).setStudentName(studentList.get(i).getStudentName());//????????????
					allList.get(j).setStudentIdCardNum(studentList.get(i).getStudentIdCardNum());//????????????
					allList.get(j).setNodeProgress(studentList.get(i).getNodeProgress().intValue());//????????????
					allList.get(j).setNodeQuestionScore(studentList.get(i).getNodeQuestionScore());//????????????
					allList.get(j).setQuestionIsFinished(studentList.get(i).getQuestionIsFinished());//??????????????????
					allList.get(j).setQuestionIsPass(studentList.get(i).getQuestionIsPass());//??????????????????
					allList.get(j).setLearnIsFinished(studentList.get(i).getLearnIsFinished());//??????????????????
					allList.get(j).setStudyTimeLength(studentList.get(i).getStudyTimeLength());//??????
					allList.get(j).setLastLearnLocation(studentList.get(i).getLastLearnLocation());//??????
					allList.get(j).setExamineState(studentList.get(i).getExamineState());
					allList.get(j).setRightNum(studentList.get(i).getNodeQuestionScore());
					allList.get(j).setFinishTime(studentList.get(i).getFinishTime());
					allList.get(j).setStuAndNodeId(studentList.get(i).getId());
				}
			}
		}
		societySchoolCourseNodeQuery.setList(allList);
		buildResponse(modelAndView,societySchoolCourseNodeQuery);
		return modelAndView;
	}


	/**
	 *@Author:ZhaoSiDa
	 *@Description: ??????????????????
	 *@DateTime: 2020/6/3 9:50
	 */
	@RequestMapping(value = "/selectNodeQuestion",method = RequestMethod.POST)
	public ModelAndView selectNodeQuestion(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/*
	* ??????????????????????????????,Excel??????
	* *//*
	@RequestMapping(value = "/selectByCourseNameExport",method = RequestMethod.POST)
	public ModelAndView selectByCourseNameExport(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listByObj(query);
		//1.????????????
		List<SocietyStudentAndNodeView> list = query.getList();
		for (SocietyStudentAndNodeView societyStudentAndNodeView : list){
			//????????????????????????????????????0
			if(societyStudentAndNodeView.getTotalQuestionNum()==null){
				//?????????????????????
				int questionNum = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeId(
						societyStudentAndNodeView.getNodeId(),
						societyStudentAndNodeView.getOwnerCourseId(),
						societyStudentAndNodeView.getOwnerSchoolId());
				societyStudentAndNodeView.setTotalQuestionNum(questionNum);
			}else if(societyStudentAndNodeView.getTotalQuestionNum()==0){
				//?????????????????????
				int questionNum = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeId(
						societyStudentAndNodeView.getNodeId(),
						societyStudentAndNodeView.getOwnerCourseId(),
						societyStudentAndNodeView.getOwnerSchoolId());
				societyStudentAndNodeView.setTotalQuestionNum(questionNum);
			}
			if("1".equals(societyStudentAndNodeView.getLearnIsFinished())){
				societyStudentAndNodeView.setLearnIsFinished("??????");
			}else{
				societyStudentAndNodeView.setLearnIsFinished("?????????");
			}
			if("1".equals(societyStudentAndNodeView.getQuestionIsPass())){
				societyStudentAndNodeView.setQuestionIsPass("??????");
			}else{
				societyStudentAndNodeView.setQuestionIsPass("?????????");
			}
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}*/

	/*@RequestMapping(value = "/selectBySchoolId",method = RequestMethod.POST)
	public ModelAndView selectBySchoolId(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????id
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		service.selectByCourseNamelistPage(query);
		//??????studentId = "undefined"
		List<SocietyStudentAndNodeView> list = query.getList();
		for(SocietyStudentAndNodeView societyStudentAndNodeView : list){
			societyStudentAndNodeView.setRightNum(
					societyStudentPractiseQuestOptionService.countRight(
							societyStudentAndNodeView.getOwnerSchoolId(),
							societyStudentAndNodeView.getNodeId(),
							societyStudentAndNodeView.getStudentId()));
			if(societyStudentAndNodeView.getTotalQuestionNum()==null){
				Integer num = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeId(
						societyStudentAndNodeView.getNodeId(),
						societyStudentAndNodeView.getOwnerCourseId(),
						societyStudentAndNodeView.getOwnerSchoolId());
				societyStudentAndNodeView.setTotalQuestionNum(num);

			}
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}*/


	@RequestMapping(value = "/examineStateInfoList",method = RequestMethod.POST)
	public ModelAndView examineStateInfoList(String stuCourseId,String nodeIdStr){
		ModelAndView modelAndView = new ModelAndView();
		String[] nodeIdList = nodeIdStr.split(",");
		for(String nodeId : nodeIdList){
			service.updateByStuCoIdAndNodeId(stuCourseId,nodeId);
		}
		buildResponse(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/examineStateInfo",method = RequestMethod.POST)
	public ModelAndView examineStateInfo(String stuCourseId,String nodeId){
		ModelAndView modelAndView = new ModelAndView();
		service.updateByStuCoIdAndNodeId(stuCourseId,nodeId);
		buildResponse(modelAndView);
		return modelAndView;
	}

	/**
	 * @Author QiuTianZhu
	 * @Description: ????????????id ??? ??????id ?????????????????????????????????
	 * @Param:
	 * @return:
	 * @Date 2020/8/7 16:06
	 **/
	@RequestMapping(value = "/selectStudentFaceIsQualified",method = RequestMethod.POST)
	public ModelAndView selectStudentFaceIsQualified(String stuAndCouId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,String> map = service.selectStudentFaceIsQualified(stuAndCouId);
		if (map.isEmpty()){
			map.put("???","??????????????????????????????????????????");
		}
		buildResponse(modelAndView,map);
		return modelAndView;
	}


	/**
	 * @Author QiuTianZhu
	 * @Description: ??????classId ?????????????????????????????????
	 * @Param:
	 * @return:
	 * @Date 2020/8/7 16:06
	 **/
	@RequestMapping(value = "/selectStudentClassFaceIsQualified",method = RequestMethod.POST)
	public ModelAndView selectStudentClassFaceIsQualified(String classId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,String> map = service.selectStudentClassFaceIsQualified(classId);
		buildResponse(modelAndView,map);
		return modelAndView;
	}

	@RequestMapping(value = "/selectStudentClassFaceIsQualifiedlistpage",method = RequestMethod.POST)
	public ModelAndView selectStudentClassFaceIsQualifiedlistpage(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectStudentClassFaceIsQualifiedlistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ????????????excel?????????
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/classFaceIsQualifiedlistObj",method = RequestMethod.POST)
	public ModelAndView classFaceIsQualifiedlistObj( String classId){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietyStudentAndNodeView> list = service.classFaceIsQualifiedlistObj(classId);
		buildResponse(modelAndView,list);
		return modelAndView;
	}


}
