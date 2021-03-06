package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.BusinessException;
import com.edgedo.dataenum.SchoolConfigKeyEnum;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentPractiseQuestion")
@Controller
@RequestMapping("/practis")
public class SocietyStudentPractiseQuestionController extends BaseController{
	
	@Autowired
	private SocietyStudentPractiseQuestionService stuQueService;
	@Autowired
	private SocietyStudentPractiseQuestOptionService stuQuestOptionService;
	//课程章节的习题服务
	@Autowired
	private SocietySchoolCourseNodeQuestionService courseNodeQuestionService;

	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;

	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietyStudentUniqueService studentUniqueService;
	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;
	@Autowired
	private SocietySchoolConfigService schoolConfigService;

	@Autowired
	private SocietySchoolCourseNodeOptionService courseNodeOptionService;

	@Autowired
	private SocietyStudentPractiseQuestOptionService studentPractiseQuestOptionService;

	/**
	 * 根据章节加载习题
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentPractiseQuestion", notes = "分页查询SocietyStudentPractiseQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByNode",method = RequestMethod.POST)
	public ModelAndView listByNode(
			@ModelAttribute SocietySchoolCourseNodeQuestionQuery query){
		String stuNodeId = query.getQueryObj().getStuNodeId();
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		String courseNodeId = query.getQueryObj().getOwnerNodeId();
		if(courseNodeId==null || courseNodeId.equals("")){
			return buildErrorResponse(modelAndView,"未找到课程章节");
		}
		//加载课程中的题目
		query.setOrderBy(" ORDER_NUM asc ");

		//获得用户选择的答案返回前台在前台显示组装
		String idCardNum = studentUnique.getId();
		List<SocietyStudentPractiseQuestOptionView> stuOpAnswerList = stuQuestOptionService.listByStuAndCouseNodeOfSelect(
				idCardNum,courseNodeId,stuNodeId);

		courseNodeQuestionService.listPageWithStuAnswer(query,stuOpAnswerList);

		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@ApiOperation(value = "分页查询SocietyStudentPractiseQuestion", notes = "分页查询SocietyStudentPractiseQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByNodeNew",method = RequestMethod.POST)
	public ModelAndView listByNodeNew(
			@ModelAttribute SocietySchoolCourseNodeQuestionQuery query){
		String stuNodeId = query.getQueryObj().getStuNodeId();
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		String courseNodeId = query.getQueryObj().getOwnerNodeId();
		if(courseNodeId==null || courseNodeId.equals("")){
			return buildErrorResponse(modelAndView,"未找到课程章节");
		}
		//加载课程中的题目
		query.setOrderBy(" ORDER_NUM asc ");
		//查出学生的ascii
		SocietyStudentAndNode societyStudentAndNode = studentAndNodeService.loadById(stuNodeId);
		SocietyStudent societyStudent  = studentService.loadById(societyStudentAndNode.getStudentId());
		courseNodeQuestionService.listPageWithStuAnswerNew(query,societyStudent.getStudentIdCardAscii());

		buildResponse(modelAndView,query);
		return modelAndView;

	}

	/**
	 * 用户点击提交练习
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentPractiseQuestion", notes = "分页查询SocietyStudentPractiseQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/submitPractis",method = RequestMethod.POST)
	public ModelAndView submitPractis(
		String stuNodeId,
		String queIds,
		String selectIds,
		String nextNodeId
	){
		ModelAndView modelAndView = new ModelAndView();
		if(queIds.length()==0 ){
			return buildErrorResponse(modelAndView,"param error");
		}
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		SocietyStudentAndNode stuNode = studentAndNodeService.loadById(stuNodeId);
		//判断这个学生点匹配学员情况
		String stuIdCardNum = stuNode.getStudentIdCardNum();
		if(!studentUnique.getId().equals(stuIdCardNum)){
			return buildErrorResponse(modelAndView,"not same stu id");
		}
		String stuId = stuNode.getStudentId();
		SocietyStudent schStu =  studentService.loadById(stuId);
		if(schStu == null){
			return buildErrorResponse(modelAndView,"未查询到学员信息！");
		}
		stuQuestOptionService.updateAllStuNodeOptionUnSelect(stuNodeId);
		String answerStates = submitPractisPrivate(stuNode,queIds,selectIds);
		//判断课程是否完成
		String questionIsPass = stuNode.getQuestionIsPass();
		if(questionIsPass!=null && questionIsPass.equals("1")){
			studentAndCourseService.updateFreshLearnInfo(stuNode.getOwnerStudentAndCourseId(),stuNode.getOwnerCourseId(),schStu);
			//学习下一个课程
			if(nextNodeId!=null && nextNodeId.length()>0){
				SocietyStudentAndCourseView param = new SocietyStudentAndCourseView();
				param.setId(stuNode.getOwnerStudentAndCourseId());
				param.setLastLearnNodeId(nextNodeId);
				studentAndCourseService.updateLastLearnNode(param);
			}
		}

		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("answerStates",answerStates);
		resMap.put("stuNode",stuNode);
		return buildResponse(modelAndView,resMap);
	}

	/**
	 * @Author WangZhen
	 * @Description 提交练习题答案,一般不会并发如果有并发可放到缓存
	 * @Date 2020/5/9 11:25
	 **/
	public String submitPractisPrivate(
			SocietyStudentAndNode stuNode,
			String queIds,
			String selectIds) {
		String NODE_LEARN_DONE_PRACTISE_CAN =  schoolConfigService.loadKeyValue(stuNode.getOwnerSchoolId(), SchoolConfigKeyEnum.NODE_LEARN_DONE_PRACTISE_CAN);
		//确定是否需要学习节点是已经完成
		if(
				NODE_LEARN_DONE_PRACTISE_CAN!=null
						&& NODE_LEARN_DONE_PRACTISE_CAN.trim().equals("1")
				){//后台配置了且值是1为是 ，否则没配置就是不需要
			String learnisFinished = stuNode.getLearnIsFinished();
			if(learnisFinished==null || !learnisFinished.equals("1") ){
				throw new BusinessException("请先听完课程再答题。");
			}
		}

//		//TODO: 删除选项答案
//		studentPractiseQuestOptionService.deleteStuQuesOptionByStuNodeId(stuNode.getId());
//		//TODO: 删除题目
//		stuQueService.deleteStuQuestionByStuNode(stuNode.getId());
		String answerStates = "";
		String[] quesArr = queIds.split(",");
		String[] selectArr = selectIds.split("@,@");
		int correctNum = 0;
		for(int i=0;i<quesArr.length;i++){
			String queId = quesArr[i];
			String selectOpIds = selectArr[i];
			selectOpIds = selectOpIds.replaceAll("@","");
			if(selectOpIds!=null && selectOpIds.equals("noanswer")){
				if(answerStates.equals("")){
					answerStates+="0";
				}else{
					answerStates+=",0";
				}
				continue;
			}
			//1.找到学员关联的习题设置习题相关
			SocietyStudentPractiseQuestionView stQuestion =
					stuQueService.insertOrUpdateStuPractisQue(
							stuNode,queId,selectOpIds);
			if(stQuestion==null){//设置成未作答
				if(answerStates.equals("")){
					answerStates+="0";
				}else{
					answerStates+=",0";
				}
				continue;
			}
			//设置已答题
			if(answerStates.equals("")){
				answerStates+=stQuestion.getAnswerState();
			}else{
				answerStates+=","+stQuestion.getAnswerState();
			}
			String[] selOpArr = selectOpIds.split(",");
			//将多选的答案都放到关联表里面
			for(String selectOpId:selOpArr){
				SocietySchoolCourseNodeOption courseNodeOption =
						courseNodeOptionService.loadById(selectOpId);
				if(courseNodeOption==null)continue;
				//2.找到学员关联的选项,设置选项
				studentPractiseQuestOptionService
						.insertOrUpdateStuPractisOptionSel(
								stuNode,courseNodeOption,stQuestion
						);
			}
			String answerState = stQuestion.getAnswerState();
			if(answerState!=null&&answerState.equals("1")){
				correctNum++;
			}
		}
		//3.统计学员的练习得分情况更新到节点上 把这个字段存放正确率
		int rightRate = correctNum*100/quesArr.length;
		stuNode.setNodeQuestionScore(rightRate);
		//是否合格 默认超过百分之六十以上答对就合格 后台配置可配
		String PASS_PRACTISE_RIGHT_RATE =  schoolConfigService.loadKeyValue(stuNode.getOwnerSchoolId(), SchoolConfigKeyEnum.PASS_PRACTISE_RIGHT_RATE);
		double passRate = 0;
		if(PASS_PRACTISE_RIGHT_RATE!=null && PASS_PRACTISE_RIGHT_RATE.length()>0){
			try{
				passRate = new Double(PASS_PRACTISE_RIGHT_RATE);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		if(rightRate>=passRate){
			stuNode.setQuestionIsPass("1");
		}else{
			stuNode.setQuestionIsPass("0");
		}
		stuNode.setQuestionIsFinished("1");
		//尝试解决死锁的问题
		SocietyStudentAndNode stuNodeParam = new SocietyStudentAndNode();
		stuNodeParam.setId(stuNode.getId());
		stuNodeParam.setNodeQuestionScore(stuNode.getNodeQuestionScore());
		stuNodeParam.setQuestionIsPass(stuNode.getQuestionIsPass());
		stuNodeParam.setQuestionIsFinished(stuNode.getQuestionIsFinished());
		studentAndNodeService.update(stuNodeParam);


		return answerStates;
	}

	/**
	 * 用户点击重新答题
	 * @return
	 */
	@ApiOperation(value = "用户点击重新答题", notes = "用户点击重新答题")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/reTestPractise",method = RequestMethod.POST)
	public ModelAndView reTestPractise(
			String stuNodeId
	){
		ModelAndView modelAndView = new ModelAndView();

		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		SocietyStudentAndNode stuNode = studentAndNodeService.loadById(stuNodeId);
		//判断这个学生点匹配学员情况
		String stuIdCardNum = stuNode.getStudentIdCardNum();
		if(!studentUnique.getId().equals(stuIdCardNum)){
			return buildErrorResponse(modelAndView,"not same stu id");
		}
		stuQueService.reTestPractise(stuNode);
		return buildResponse(modelAndView,stuNode);
	}

	/**
	 * 用户点击重新答题
	 * @return
	 */
	@ApiOperation(value = "用户点击重新答题", notes = "用户点击重新答题")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/reTestPractiseNew",method = RequestMethod.POST)
	public ModelAndView reTestPractiseNew(
			String stuNodeId
	){
		ModelAndView modelAndView = new ModelAndView();

		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		SocietyStudentAndNode stuNode = studentAndNodeService.loadById(stuNodeId);
		//判断这个学生点匹配学员情况
		//获取学生ascii
		SocietyStudent societyStudent = studentService.loadById(stuNode.getStudentId());
		String stuIdCardNum = stuNode.getStudentIdCardNum();
		if(!studentUnique.getId().equals(stuIdCardNum)){
			return buildErrorResponse(modelAndView,"not same stu id");
		}
		stuQueService.reTestPractiseNew(stuNode,societyStudent.getStudentIdCardAscii());
		return buildResponse(modelAndView,stuNode);
	}


	/**
	 * 用户点击提交练习
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentPractiseQuestion", notes = "分页查询SocietyStudentPractiseQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/submitPractisNew",method = RequestMethod.POST)
	public ModelAndView submitPractisNew(
			String stuNodeId,
			String queIds,
			String selectIds,
			String nextNodeId
	){
		ModelAndView modelAndView = new ModelAndView();
		if(queIds.length()==0 ){
			return buildErrorResponse(modelAndView,"param error");
		}
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		SocietyStudentAndNode stuNode = studentAndNodeService.loadById(stuNodeId);
		//判断这个学生点匹配学员情况
		String stuIdCardNum = stuNode.getStudentIdCardNum();
		if(!studentUnique.getId().equals(stuIdCardNum)){
			return buildErrorResponse(modelAndView,"not same stu id");
		}
		String stuId = stuNode.getStudentId();
		SocietyStudent schStu =  studentService.loadById(stuId);
		if(schStu == null){
			return buildErrorResponse(modelAndView,"未查询到学员信息！");
		}
		//获取学生的ascii
//		stuQuestOptionService.updateAllStuNodeOptionUnSelectNew(stuNodeId,schStu.getStudentIdCardAscii());
		String answerStates = submitPractisPrivateNew(stuNode,queIds,selectIds,schStu.getStudentIdCardAscii());
		//判断课程是否完成
		String questionIsPass = stuNode.getQuestionIsPass();
		if(questionIsPass!=null && questionIsPass.equals("1")){
			studentAndCourseService.updateFreshLearnInfo(stuNode.getOwnerStudentAndCourseId(),stuNode.getOwnerCourseId(),schStu);
			//学习下一个课程
			if(nextNodeId!=null && nextNodeId.length()>0){
				SocietyStudentAndCourseView param = new SocietyStudentAndCourseView();
				param.setId(stuNode.getOwnerStudentAndCourseId());
				param.setLastLearnNodeId(nextNodeId);
				studentAndCourseService.updateLastLearnNode(param);
			}
		}

		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("answerStates",answerStates);
		resMap.put("stuNode",stuNode);
		return buildResponse(modelAndView,resMap);
	}

	/**
	 * @Author WangZhen
	 * @Description 提交练习题答案,一般不会并发如果有并发可放到缓存
	 * @Date 2020/5/9 11:25
	 **/
	public String submitPractisPrivateNew(
			SocietyStudentAndNode stuNode,
			String queIds,
			String selectIds,
			String ascii) {
		String NODE_LEARN_DONE_PRACTISE_CAN =  schoolConfigService.loadKeyValue(stuNode.getOwnerSchoolId(), SchoolConfigKeyEnum.NODE_LEARN_DONE_PRACTISE_CAN);
		//确定是否需要学习节点是已经完成
		if(
				NODE_LEARN_DONE_PRACTISE_CAN!=null
						&& NODE_LEARN_DONE_PRACTISE_CAN.trim().equals("1")
		){//后台配置了且值是1为是 ，否则没配置就是不需要
			String learnisFinished = stuNode.getLearnIsFinished();
			if(learnisFinished==null || !learnisFinished.equals("1") ){
				throw new BusinessException("请先听完课程再答题。");
			}
		}
		//TODO: 删除选项答案
		studentPractiseQuestOptionService.deleteStuQuesOptionByStuNodeId(stuNode.getId(),ascii);
		//TODO: 删除题目
		stuQueService.deleteStuQuestionByStuNode(stuNode.getId(),ascii);

		String answerStates = "";
		String[] quesArr = queIds.split(",");
		String[] selectArr = selectIds.split("@,@");
		int correctNum = 0;
		//学生习题list
		List<SocietyStudentPractiseQuestionView> quetionList = new ArrayList<>();
		//学生选项list
		List<SocietyStudentPractiseQuestOption> optionList = new ArrayList<>();
		for(int i=0;i<quesArr.length;i++){
			String queId = quesArr[i];
			String selectOpIds = selectArr[i];
			selectOpIds = selectOpIds.replaceAll("@","");
			if(selectOpIds!=null && selectOpIds.equals("noanswer")){
				if(answerStates.equals("")){
					answerStates+="0";
				}else{
					answerStates+=",0";
				}
				continue;
			}
			//1.找到学员关联的习题设置习题相关
			SocietyStudentPractiseQuestionView stQuestion =
					stuQueService.insertOrUpdateStuPractisQueNew(
							stuNode,queId,selectOpIds,ascii);
			quetionList.add(stQuestion);
			if(stQuestion==null){//设置成未作答
				if(answerStates.equals("")){
					answerStates+="0";
				}else{
					answerStates+=",0";
				}
				continue;
			}
			//设置已答题
			if(answerStates.equals("")){
				answerStates+=stQuestion.getAnswerState();
			}else{
				answerStates+=","+stQuestion.getAnswerState();
			}
			/*String[] selOpArr = selectOpIds.split(",");
			//将多选的答案都放到关联表里面
			for(String selectOpId:selOpArr){
				SocietySchoolCourseNodeOption courseNodeOption =
						courseNodeOptionService.loadById(selectOpId);
				if(courseNodeOption==null)continue;
				//2.找到学员关联的选项,设置选项
				SocietyStudentPractiseQuestOption option =  studentPractiseQuestOptionService
						.insertOrUpdateStuPractisOptionSelNew(
								stuNode,courseNodeOption,stQuestion,ascii
						);
				optionList.add(option);
			}*/
			String answerState = stQuestion.getAnswerState();
			if(answerState!=null&&answerState.equals("1")){
				correctNum++;
			}
		}
		//集合插入
		stuQueService.insertNewList(quetionList,ascii);
//		studentPractiseQuestOptionService.insertNewList(optionList,ascii);
		//3.统计学员的练习得分情况更新到节点上 把这个字段存放正确率
		int rightRate = correctNum*100/quesArr.length;
		stuNode.setNodeQuestionScore(rightRate);
		//是否合格 默认超过百分之六十以上答对就合格 后台配置可配
		String PASS_PRACTISE_RIGHT_RATE =  schoolConfigService.loadKeyValue(stuNode.getOwnerSchoolId(), SchoolConfigKeyEnum.PASS_PRACTISE_RIGHT_RATE);
		double passRate = 0;
		if(PASS_PRACTISE_RIGHT_RATE!=null && PASS_PRACTISE_RIGHT_RATE.length()>0){
			try{
				passRate = new Double(PASS_PRACTISE_RIGHT_RATE);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		if(rightRate>=passRate){
			stuNode.setQuestionIsPass("1");
		}else{
			stuNode.setQuestionIsPass("0");
		}
		stuNode.setQuestionIsFinished("1");
		//尝试解决死锁的问题
		SocietyStudentAndNode stuNodeParam = new SocietyStudentAndNode();
		stuNodeParam.setId(stuNode.getId());
		stuNodeParam.setNodeQuestionScore(stuNode.getNodeQuestionScore());
		stuNodeParam.setQuestionIsPass(stuNode.getQuestionIsPass());
		stuNodeParam.setQuestionIsFinished(stuNode.getQuestionIsFinished());
		studentAndNodeService.update(stuNodeParam);
		return answerStates;
	}

}
