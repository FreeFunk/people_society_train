package com.edgedo.society.controller;


import java.util.*;
import java.util.stream.Collectors;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.constant.CommonConstant;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietySchoolCourseUseGloble;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.SocietySchoolCourseNodeService;
import com.edgedo.society.service.SocietySchoolCourseService;
import com.edgedo.society.service.SocietySchoolCourseUseGlobleService;
import com.edgedo.society.service.SocietySchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolCourseUseGloble")
@Controller
@RequestMapping("/society/societySchoolCourseUseGloble")
public class SocietySchoolCourseUseGlobleController extends BaseController{

	@Autowired
	private SocietySchoolCourseUseGlobleService service;

	@Autowired
	private SocietySchoolService societySchoolService;

	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolCourseUseGloble", notes = "分页查询SocietySchoolCourseUseGloble")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolCourseUseGlobleQuery query){
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
	@ApiOperation(value = "新增修改SocietySchoolCourseUseGloble", notes = "新增修改SocietySchoolCourseUseGloble")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolCourseUseGloble voObj){
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
	 * @Author QiuTianZhu
	 * @Description: 添加学下公共课
	 * @Param: 获取到前台的课程id 和 学校id
	 * @return:
	 * @Date 2020/7/22 16:10
	 **/
	@RequestMapping(value = "/insertGlobleCourse",method = RequestMethod.POST)
	public ModelAndView insertGlobleCourse(SocietySchoolCourseUseGloble voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setDataState("1");
		SocietySchoolCourse societySchoolCourse =
				societySchoolCourseService.loadById(voObj.getCourseId());
		voObj.setCourseSchId(societySchoolCourse.getOwnerSchoolId());
		voObj.setSchoolId(user.getCompId());
		service.insert(voObj);
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/insertNewCourse",method = RequestMethod.POST)
	public ModelAndView insertNewCourse(String courseIdStr,String schoolId) {
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String[] courseIdList = courseIdStr.split(",");
		for (String courseId : courseIdList) {
			SocietySchoolCourseUseGloble societySchoolCourseUseGloble = new SocietySchoolCourseUseGloble();
			societySchoolCourseUseGloble.setCreateTime(new Date());
			societySchoolCourseUseGloble.setCreateUserId(user.getUserId());
			societySchoolCourseUseGloble.setCreateUserName(user.getUserName());
			societySchoolCourseUseGloble.setDataState("1");
			SocietySchoolCourse societySchoolCourse =
					societySchoolCourseService.loadById(courseId);
			societySchoolCourseUseGloble.setCourseId(courseId);
			societySchoolCourseUseGloble.setCourseSchId(societySchoolCourse.getOwnerSchoolId());
			societySchoolCourseUseGloble.setSchoolId(schoolId);
			service.insert(societySchoolCourseUseGloble);
		}
		return buildResponse(modelAndView);
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除SocietySchoolCourseUseGloble", notes = "根据ID批量删除SocietySchoolCourseUseGloble")
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
	@ApiOperation(value = "根据ID加载SocietySchoolCourseUseGloble", notes = "根据ID加载SocietySchoolCourseUseGloble")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * @Author QiuTianZhu
	 * @Description: 超级管理的公共课菜单  先展示学校相关的课程信息
	 * @Param: query 学校对象
	 * @return:
	 * @Date 2020/7/22 14:17
	 **/
	@RequestMapping(value = "/listpageSchoolCourseSituation",method = RequestMethod.POST)
	public ModelAndView listAlllistpageSchoolCourseSituationXianQu(@ModelAttribute SocietySchoolCourseUseGlobleQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		//统计自己学校下的课程数和自己学校所关联的所有的公共的课程数
		List<SocietySchoolCourseUseGlobleView> list = query.getList();
		for (SocietySchoolCourseUseGlobleView societySchoolCourseUseGlobleView : list){
			SocietySchool societySchoolOwn =
					societySchoolService.loadById(societySchoolCourseUseGlobleView.getSchoolId());
			SocietySchool societySchoolCopy =
					societySchoolService.loadById(societySchoolCourseUseGlobleView.getCourseSchId());
			SocietySchoolCourse societySchoolCourse =
					societySchoolCourseService.loadById(societySchoolCourseUseGlobleView.getCourseId());
			societySchoolCourseUseGlobleView.setSchoolName(societySchoolOwn.getSchoolName());
			societySchoolCourseUseGlobleView.setContactPerson(societySchoolOwn.getContactPerson());
			societySchoolCourseUseGlobleView.setContactPhoneNum(societySchoolOwn.getContactPhoneNum());
			societySchoolCourseUseGlobleView.setCourseAndSchoolName(societySchoolCopy.getSchoolName());
			societySchoolCourseUseGlobleView.setGlobleCourseName(societySchoolCourse.getCourseName());
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/listpageSchoolCourseSituationSchoolAdmin",method = RequestMethod.POST)
	public ModelAndView listpageSchoolCourseSituationSchoolAdmin(@ModelAttribute SocietySchoolCourseUseGlobleQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User uesr = getLoginUser();
		query.getQueryObj().setSchoolId(uesr.getCompId());
		query.getQueryObj().setGlobleType("COURSE");
		service.listPage(query);
		//统计自己学校下的课程数和自己学校所关联的所有的公共的课程数
		List<SocietySchoolCourseUseGlobleView> list = query.getList();
		for (SocietySchoolCourseUseGlobleView societySchoolCourseUseGlobleView : list){
			SocietySchool societySchoolOwn =
					societySchoolService.loadById(societySchoolCourseUseGlobleView.getSchoolId());
			SocietySchool societySchoolCopy =
					societySchoolService.loadById(societySchoolCourseUseGlobleView.getCourseSchId());
			SocietySchoolCourse societySchoolCourse =
					societySchoolCourseService.loadById(societySchoolCourseUseGlobleView.getCourseId());
			societySchoolCourseUseGlobleView.setSchoolName(societySchoolOwn.getSchoolName());
			societySchoolCourseUseGlobleView.setContactPerson(societySchoolOwn.getContactPerson());
			societySchoolCourseUseGlobleView.setCourseAndSchoolName(societySchoolCopy.getSchoolName());
			societySchoolCourseUseGlobleView.setGlobleCourseName(societySchoolCourse.getCourseName());
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 查询所有公开课
	 * @param query
	 * @return
	 */

	@RequestMapping(value = "/getAllOpenCourse",method = RequestMethod.POST)
	public ModelAndView getAllOpenCourse(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		query.getQueryObj().setIsOpen(CommonConstant.COURSE_OPON_STATUS);
		query.getQueryObj().setDataState(CommonConstant.DATA_STATUS_ON);
		societySchoolCourseService.listPage(query);
		List<SocietySchoolCourseView> societySchoolCourseViewList =  query.getList();

		//查除所有的公开课 isOpen 1
		//对比当前的 操作人的id 排除课程
		//循环遍历 根据 课程id + 当前学校id
		//判断是否已经关联此公共课
		String nowSchoolId = getLoginUser().getCompId();
		/*List<SocietySchoolCourseView> list= societySchoolCourseViewList.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(sc ->
								sc.getId()+";"+nowSchoolId))),
						ArrayList::new));*/


	    for(SocietySchoolCourseView societySchoolCourseView:societySchoolCourseViewList) {
			//课程学校id
			String courseSchoolId = societySchoolCourseView.getOwnerSchoolId();
			//课程id
			String courseId = societySchoolCourseView.getId();
			if (nowSchoolId.equals(courseSchoolId)) {
				//自己的课程
				societySchoolCourseView.setShowFlg(0);
			} else {
				int flag = societySchoolCourseService.selectBySchoolIdAndCourseId(nowSchoolId, courseId);
				if (flag == 0) {//没有选择公共课
					societySchoolCourseView.setShowFlg(1);
				} else {
					societySchoolCourseView.setShowFlg(0);
				}
			}
		}
        query.setList(societySchoolCourseViewList);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/getMyOpenCourse",method = RequestMethod.POST)
	public ModelAndView getMyOpenCourse(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		SocietySchoolCourseView societySchoolCourseView =  query.getQueryObj();
		societySchoolCourseView.setIsOpen(CommonConstant.COURSE_OPON_STATUS);
		societySchoolCourseView.setDataState(CommonConstant.DATA_STATUS_ON);
//		societySchoolCourseView.setOwnerSchoolId(schoolId);
		//查询 OWNER_SCHOOL_COURSE_ID 是我的学校的课程
		societySchoolCourseView.setOwnerSchoolCourseId(schoolId);
		societySchoolCourseService.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 公共章节
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageGlobleNode",method = RequestMethod.POST)
	public ModelAndView listpageGlobleNode(@ModelAttribute SocietySchoolCourseUseGlobleQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//查出学校的所有课程id
		User user = getLoginUser();
		String schoolId = user.getCompId();
		query.getQueryObj().setSchoolId(schoolId);
		query.getQueryObj().setGlobleType("NODE");
		service.listPageNew(query);
		//统计自己学校下的课程数和自己学校所关联的所有的公共的课程数
		List<SocietySchoolCourseUseGlobleView> list = query.getList();
		for (SocietySchoolCourseUseGlobleView societySchoolCourseUseGlobleView : list){
			SocietySchoolCourseNode societySchoolCourseNode =
					societySchoolCourseNodeService.loadById(societySchoolCourseUseGlobleView.getNodeId());
			SocietySchool societySchoolOwn =
					societySchoolService.loadById(societySchoolCourseUseGlobleView.getSchoolId());
			SocietySchool societySchoolCopy =
					societySchoolService.loadById(societySchoolCourseUseGlobleView.getCourseSchId());
			societySchoolCourseUseGlobleView.setSchoolName(societySchoolOwn.getSchoolName());
			societySchoolCourseUseGlobleView.setContactPerson(societySchoolOwn.getContactPerson());
			societySchoolCourseUseGlobleView.setCourseAndSchoolName(societySchoolCopy.getSchoolName());
			societySchoolCourseUseGlobleView.setGlobleNodeName(societySchoolCourseNode.getNodeName());
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}
}
