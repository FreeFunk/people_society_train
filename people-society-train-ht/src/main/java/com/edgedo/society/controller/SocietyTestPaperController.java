package com.edgedo.society.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.entity.SocietyTestPaper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuery;
import com.edgedo.society.service.SocietySchoolCourseNodeQuestionService;
import com.edgedo.society.service.SocietySchoolCourseService;
import com.edgedo.society.service.SocietyTestPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyTestPaper")
@Controller
@RequestMapping("/society/societyTestPaper")
public class SocietyTestPaperController extends BaseController{
	
	@Autowired
	private SocietyTestPaperService service;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyTestPaper", notes = "分页查询SocietyTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyTestPaperQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 学校管理员分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyTestPaper", notes = "分页查询SocietyTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoollistpage",method = RequestMethod.POST)
	public ModelAndView schoollistpage(@ModelAttribute SocietyTestPaperQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("CREATE_TIME ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 平台管理员分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyTestPaper", notes = "分页查询SocietyTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/ptlistpage",method = RequestMethod.POST)
	public ModelAndView ptlistpage(@ModelAttribute SocietyTestPaperQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("CREATE_TIME ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyTestPaper", notes = "新增修改SocietyTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyTestPaper voObj){
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
	 * 学校管理员新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyTestPaper", notes = "新增修改SocietyTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoolsaveOrUpdate",method = RequestMethod.POST)
	public ModelAndView schoolsaveOrUpdate(SocietyTestPaper voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("1")){
			String courseId = voObj.getOwnerCourseId();
			//校验章节习题数量是否足够
			SocietySchoolCourseNodeQuestionQuery query = new SocietySchoolCourseNodeQuestionQuery();
			query.getQueryObj().setOwnerCourseId(courseId);
			query.getQueryObj().setQuestionType("1");//单选题
			societySchoolCourseNodeQuestionService.selectByQuesionAllIdlistPage(query);
			if(query.getList().size()<voObj.getDanxuanQuestionNum()){
				errMsg = "单选题有"+query.getList().size()+"道，数量不足，请维护!";
				buildResponse(modelAndView,errMsg);
				modelAndView.addObject("check", "0");
				return modelAndView;
			}
			query.getQueryObj().setQuestionType("2");//判断题
			societySchoolCourseNodeQuestionService.selectByQuesionAllIdlistPage(query);
			if(query.getList().size()<voObj.getPanduanQuestionNum()){
				errMsg = "判断题有"+query.getList().size()+"道，数量不足，请维护!";
				buildResponse(modelAndView,errMsg);
				return modelAndView;
			}

			//插入基本信息
			SocietySchoolCourse course = societySchoolCourseService.loadById(courseId);
			voObj.setOwnerSchoolId(course.getOwnerSchoolId());
			voObj.setOwnerSchoolName(course.getOwnerSchoolName());
			voObj.setOwnerCourseId(course.getId());
			voObj.setOwnerCourseName(course.getCourseName());
			User user = getLoginUser();
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setDataState("1");
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
	@ApiOperation(value = "根据ID批量删除SocietyTestPaper", notes = "根据ID批量删除SocietyTestPaper")
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
	@ApiOperation(value = "根据ID加载SocietyTestPaper", notes = "根据ID加载SocietyTestPaper")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}
