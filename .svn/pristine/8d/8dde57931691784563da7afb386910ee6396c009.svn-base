package com.edgedo.society.controller;


import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentCertificate;
import com.edgedo.society.queryvo.SocietyStudentCertificateQuery;
import com.edgedo.society.queryvo.SocietyStudentCertificateView;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.SysUser;
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


@Api(tags = "SocietyStudentCertificate")
@Controller
@RequestMapping("/society/societyStudentCertificate")
public class SocietyStudentCertificateController extends BaseController{
	
	@Autowired
	private SocietyStudentCertificateService service;

	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietyStudentService societyStudentService;
	@RequestMapping(value = "/studyCertificate",method = RequestMethod.POST)
	public ModelAndView studyCertificate(String classId,String studentId,String courseId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudent societyStudent = societyStudentService.loadById(studentId);
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		Map<String,Object> map = new HashMap<>();
		map.put("studentName",societyStudent.getStudentName());
		map.put("idcard",societyStudent.getStudentIdCardNum());
		map.put("time",getDatePeriodSecond(societySchoolClass.getClassStartTime(),societySchoolClass.getClassEndTime()));
		map.put("className",societySchoolClass.getClassName()+"班");
		map.put("studyCourseNum",societySchoolCourse.getTotalLessons());
		map.put("schoolName","培训机构方："+societyStudent.getOwnerSchoolName());
		return buildResponse(modelAndView,map);
	}
	public String getDatePeriodSecond(Date start, Date end){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		String startStr = simpleDateFormat.format(start);
		String endStr = simpleDateFormat.format(end);
		return startStr+"&nbsp;至&nbsp;"+endStr;
	}
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentCertificate", notes = "分页查询SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentCertificateQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/selectByCourseIdAndStudentId",method = RequestMethod.POST)
	public ModelAndView selectByCourseIdAndStudentId(String studentId,String courseId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentCertificateView societyStudentCertificateView = service.selectByStuIdAndCourId(studentId,courseId);
		buildResponse(modelAndView,societyStudentCertificateView);
		return modelAndView;
	}


	@RequestMapping(value = "/listSuppage",method = RequestMethod.POST)
	public ModelAndView listSuppage(@ModelAttribute SocietyStudentCertificateQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SysUser sysUser = sysUserService.loadById(getLoginUser().getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * 不分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "不分页查询SocietyStudentCertificate", notes = "不分页查询SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObj",method = RequestMethod.POST)
	public ModelAndView listByObj(@ModelAttribute SocietyStudentCertificateQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 不分页查询，学校管理员
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "不分页查询SocietyStudentCertificate", notes = "不分页查询SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjForSchool",method = RequestMethod.POST)
	public ModelAndView listByObjForSchool(@ModelAttribute SocietyStudentCertificateQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		Map<String,String> map = societySchoolService.selectBySchoolAdminId(user.getUserId());
		query.getQueryObj().setOwnerSchoolId(map.get("ID"));
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/certificateListBySchoolId",method = RequestMethod.POST)
	public ModelAndView certificateListBySchoolId(@ModelAttribute SocietyStudentCertificateQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//获取当前用户id
		User user = getLoginUser();
		Map<String,String> map = societySchoolService.selectBySchoolAdminId(user.getUserId());
		query.getQueryObj().setOwnerSchoolId(map.get("ID"));
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyStudentCertificate", notes = "新增修改SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentCertificate voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentCertificate", notes = "根据ID批量删除SocietyStudentCertificate")
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
	@ApiOperation(value = "根据ID加载SocietyStudentCertificate", notes = "根据ID加载SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}
