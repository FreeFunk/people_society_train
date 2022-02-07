package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentComment;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.queryvo.SocietyStudentCommentQuery;
import com.edgedo.society.service.SocietyStudentCommentService;
import com.edgedo.society.service.SocietyStudentService;
import com.edgedo.society.service.SocietyStudentUniqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentComment")
@Controller
@RequestMapping("/comment")
public class SocietyStudentCommentController extends BaseController{


	@Autowired
	private SocietyStudentCommentService service;
	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietyStudentUniqueService studentUniqueService;
	
	/**
	 * 根据课程节点加载课程节点评论
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentComment", notes = "分页查询SocietyStudentComment")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByNode",method = RequestMethod.POST)
	public ModelAndView listByCourseNode(
			@ModelAttribute SocietyStudentCommentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String courseNodeId = query.getQueryObj().getOwnerNodeId();
		if(courseNodeId==null || courseNodeId.equals("")){
			return buildErrorResponse(modelAndView,"未找到课程章节");
		}
		query.setOrderBy(" CREATE_TIME desc ");
		service.listPageSimple(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 *  学生提交评论
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "学生提交评论", notes = "学生提交评论")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/submitText",method = RequestMethod.POST)
	public ModelAndView submitText(@ModelAttribute SocietyStudentComment voObj){
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		String schoolId = getSchoolId();
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		String commentText = voObj.getCommentText();
		commentText = commentText.replaceAll("[^\\u4E00-\\u9FA5]", "");
		voObj.setCommentText(commentText);
		if(id==null || id.trim().equals("")){
			errMsg = service.insertComment(voObj,student,schoolId);
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentComment", notes = "根据ID批量删除SocietyStudentComment")
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
	
	
}
