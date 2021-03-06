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
		map.put("className",societySchoolClass.getClassName()+"???");
		map.put("studyCourseNum",societySchoolCourse.getTotalLessons());
		map.put("schoolName","??????????????????"+societyStudent.getOwnerSchoolName());
		return buildResponse(modelAndView,map);
	}
	public String getDatePeriodSecond(Date start, Date end){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd???");
		String startStr = simpleDateFormat.format(start);
		String endStr = simpleDateFormat.format(end);
		return startStr+"&nbsp;???&nbsp;"+endStr;
	}
	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudentCertificate", notes = "????????????SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
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
	 * ???????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "???????????????SocietyStudentCertificate", notes = "???????????????SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObj",method = RequestMethod.POST)
	public ModelAndView listByObj(@ModelAttribute SocietyStudentCertificateQuery query){
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
	@ApiOperation(value = "???????????????SocietyStudentCertificate", notes = "???????????????SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
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
		//??????????????????id
		User user = getLoginUser();
		Map<String,String> map = societySchoolService.selectBySchoolAdminId(user.getUserId());
		query.getQueryObj().setOwnerSchoolId(map.get("ID"));
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * ????????????
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudentCertificate", notes = "????????????SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
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
	 * ????????????
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "??????ID????????????SocietyStudentCertificate", notes = "??????ID????????????SocietyStudentCertificate")
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
	@ApiOperation(value = "??????ID??????SocietyStudentCertificate", notes = "??????ID??????SocietyStudentCertificate")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}
