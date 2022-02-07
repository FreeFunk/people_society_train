package com.edgedo.society.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietySchoolCourseNodeQuestion;
import com.edgedo.society.mapper.SocietySchoolCourseNodeQuestionMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import com.edgedo.society.service.SocietySchoolCourseNodeOptionService;
import com.edgedo.society.service.SocietySchoolCourseNodeQuestionService;
import com.edgedo.society.service.SocietySchoolCourseNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolCourseNodeQuestion")
@Controller
@RequestMapping("/society/societySchoolCourseNodeQuestion")
public class SocietySchoolCourseNodeQuestionController extends BaseController{
	
	@Autowired
	private SocietySchoolCourseNodeQuestionService service;
	@Autowired
	private SocietySchoolCourseNodeOptionService nodeOptionService;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolCourseNodeQuestion", notes = "分页查询SocietySchoolCourseNodeQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolCourseNodeQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.listPage(query);
		List<SocietySchoolCourseNodeQuestionView> questionList = query.getList();
		for(int i=0;i<questionList.size();i++){
			SocietySchoolCourseNodeQuestionView question = questionList.get(i);
			List<String> optionNameList = nodeOptionService.selectOptionNameByQuestion(question.getId());
			if(optionNameList.size() > 0){
				String options = String.join("|@@|",optionNameList);
				questionList.get(i).setOptions(options);
			}
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolCourseNodeQuestion", notes = "新增修改SocietySchoolCourseNodeQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolCourseNodeQuestion voObj){
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
	 * 学校新增修改习题和选项
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/schoolSaveOrUpdateQuestion",method = RequestMethod.POST)
	public ModelAndView schoolSaveOrUpdateQuestion(SocietySchoolCourseNodeQuestion voObj,String options){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String errMsg = "";//cad
		String id = voObj.getId();
		String[] optionArr = null;
		if(options != null){
			optionArr = options.split("@@");
		}else{
			optionArr = new String[]{"√","×"};
		}
		if(id==null || id.trim().equals("")){
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			int num = service.countNodeQuestionNum(voObj.getOwnerNodeId());
			voObj.setOrderNum(new BigDecimal(num+1));
			errMsg = service.insertQuestionAndOption(voObj,optionArr);
		}else{
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			errMsg = service.updateQuestionAndOption(voObj,optionArr);
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
	@ApiOperation(value = "根据ID批量删除SocietySchoolCourseNodeQuestion", notes = "根据ID批量删除SocietySchoolCourseNodeQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		//减去相关练的小节题数
		SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion = service.loadById(arr[0]);
		service.deleteByIds(list);
		Integer questionNum = service.selectQuestionNum(societySchoolCourseNodeQuestion.getOwnerNodeId());
		societySchoolCourseNodeService.updateByIdAndQuestionNum(societySchoolCourseNodeQuestion.getOwnerNodeId(),questionNum);

		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SocietySchoolCourseNodeQuestion", notes = "根据ID加载SocietySchoolCourseNodeQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 根据学校id 课程id 题目id 去公共题库查出所有相关题目选项 答案
	 * @param societySchoolCourseNodeQuestionQuery
	 * @return
	 */
	@RequestMapping(value = "/selectByQuesionAllId",method = RequestMethod.POST)
	public ModelAndView  selectByQuesionAllId(@ModelAttribute SocietySchoolCourseNodeQuestionQuery societySchoolCourseNodeQuestionQuery){
		ModelAndView modelAndView = new ModelAndView();
		service.selectByQuesionAllIdlistPage(societySchoolCourseNodeQuestionQuery);
		return buildResponse(modelAndView,societySchoolCourseNodeQuestionQuery);
	}

}
