package com.edgedo.society.controller;


import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.BusinessException;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Api(tags = "SocietySchoolCourse")
@Controller
@RequestMapping("/cour")
public class SocietySchoolCourseController extends BaseController{
	
	@Autowired
	private SocietySchoolCourseService service;
	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietyStudentUniqueService  studentUniqueService;
	@Autowired
	private SocietySchoolCourseNodeService schoolCourseNodeService;
	@Autowired
	private SocietyStudentCommentService studentCommentService;
	@Autowired
	private SocietySchoolCourseClsService courseClsService;
	@Autowired
	private SocietySchoolMajorService majorService;

	/**
	 * 根据学校分页查询
	 * @param query
	 * @return
	 */
	//TODO:有问题
	@RequestMapping(value = "/schoolCourseListPage",method = RequestMethod.POST)
	public ModelAndView schoolCourseListPage(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
//		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		/*String ownerSchoolId = getSchoolId();
		query.getQueryObj().setOwnerSchoolId(ownerSchoolId);*/
		if(query.getOrderBy() == null){
			query.setOrderBy("CREATE_TIME DESC");
		}
		query.getQueryObj().setShState("1");
		query.getQueryObj().setIsOpen("1");
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据课程id查询详细信息、详情和评论
	 * @Date 2020/5/5 20:41
	 **/
	@Pass
	@RequestMapping("/loadCourseInfoById")
	public ModelAndView loadCourseInfoById(String courseId) {
		ModelAndView modelAndView = new ModelAndView();
//		String ownerSchoolId = getSchoolId();
		Map<String,Object> resultMap = new HashMap<>();
		//查询课程详情
		SocietySchoolCourse schoolCourse = service.selectCourseById(courseId);
		if(schoolCourse==null){
			throw new BusinessException("未找到课程!");
		}
		if(schoolCourse.getCourseScore() == null){
			schoolCourse.setCourseScore(new BigDecimal(9.6));
		}
		if(schoolCourse.getTotalStudentNum() == null){
			schoolCourse.setTotalStudentNum(0);
		}
		//课程详情
		resultMap.put("schoolCourse",schoolCourse);
		//小节信息
		List<SocietySchoolCourseNodeView> courseNodes = schoolCourseNodeService.selectCousrseNodesByCourseIdSimple(courseId);
		resultMap.put("courseNodes",courseNodes);
		//评论信息
		List<SocietyStudentCommentView> studentComs = studentCommentService.selectCommentByCourseId(courseId);
		resultMap.put("courseComs",studentComs);
		return buildResponse(modelAndView,resultMap);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学校插叙最新的四个课程
	 * @Date 2020/5/5 19:06
	 **/
	@Pass
	@RequestMapping(value = "/selectLateLyFourCourse",method = RequestMethod.POST)
	public ModelAndView selectLateLyFourCourse(String ownerMajorId){
		ModelAndView modelAndView = new ModelAndView();
		String ownerSchoolId = getSchoolId();
		List<SocietySchoolCourseView> courseViewList = service.selectLateLyFourCourse(ownerSchoolId,ownerMajorId);
	/*	for(int i=0;i<courseViewList.size();i++){
			String courseId = courseViewList.get(i).getId();
			int commentCount = studentCommentService.countByCourseId(courseId);
			courseViewList.get(i).setCommentCount(commentCount);
		}*/
		return buildResponse(modelAndView, courseViewList);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据课程分类和课程进阶程序查询3门课程
	 * @Date 2020/5/5 19:06
	 **/
	@Pass
	@RequestMapping(value = "/selectThreeCourseByCourClsAndLevel",method = RequestMethod.POST)
	public ModelAndView selectThreeCourseByCourClsAndLevel(String courseClsId){
		ModelAndView modelAndView = new ModelAndView();
		String ownerSchoolId = getSchoolId();
		List<SocietySchoolCourseView> courseViewList = service.selectThreeCourseByCourClsAndLevel(ownerSchoolId,courseClsId);
		return buildResponse(modelAndView, courseViewList);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据专业查询课程分类
	 * @Date 2020/5/5 19:06
	 **/
	@Pass
	@RequestMapping(value = "/listAllCourseClsByMajor",method = RequestMethod.POST)
	public ModelAndView listAllCourseClsByMajor(String ownerMajorId){
		ModelAndView modelAndView = new ModelAndView();
		String ownerSchoolId = getSchoolId();
		List<SocietySchoolCourseClsView> courseClsViewList = courseClsService.selectCourseClsByMajor(ownerSchoolId,ownerMajorId);
		return buildResponse(modelAndView, courseClsViewList);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学校查询专业
	 * @Date 2020/5/5 19:06
	 **/
	@Pass
	@RequestMapping(value = "/listAllMajorBySchool",method = RequestMethod.POST)
	public ModelAndView listAllMajorBySchool(){
		ModelAndView modelAndView = new ModelAndView();
		String ownerSchoolId = getSchoolId();
		List<SocietySchoolMajorView> majorViewList = majorService.listAllBySchool(ownerSchoolId);
		return buildResponse(modelAndView, majorViewList);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学校查询专业
	 * @Date 2020/5/5 19:06
	 **/
	@Pass
	@RequestMapping(value = "/listMajorBySchoolLimit4",method = RequestMethod.POST)
	public ModelAndView listMajorBySchoolLimit4(){
		ModelAndView modelAndView = new ModelAndView();
		String ownerSchoolId = getSchoolId();
		List<SocietySchoolMajorView> majorViewList = majorService.listBySchoolLimit4(ownerSchoolId);
		return buildResponse(modelAndView, majorViewList);
	}
	
}
