package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.SocietyStudentCertificateQuery;
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
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietySchoolClassAndCourseService societySchoolClassAndCourseService;
	@Autowired
	private SocietySchoolMajorService societySchoolMajorService;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	
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

	@RequestMapping(value = "/updateAll",method = RequestMethod.POST)
	public ModelAndView updateAll(@ModelAttribute SocietyStudentCertificate societyStudentCertificate){
		ModelAndView modelAndView = new ModelAndView();
		String majorId = societyStudentCertificate.getOwnerMajorId();
		String classId = societyStudentCertificate.getClassId();
		String courseId = societyStudentCertificate.getCourseId();
		SocietySchoolCourse societySchoolCourse =
				societySchoolCourseService.loadById(societyStudentCertificate.getCourseId());
		if(!(societySchoolCourse.getOwnerMajorId().equals(majorId))){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "课程不属于该专业下");
			return modelAndView;
		}

		SocietySchoolClassAndCourse societySchoolClassAndCourse =
				societySchoolClassAndCourseService.selectCourseIdAndClassId(classId,courseId);
		if(societySchoolClassAndCourse==null){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "班级未关联此课程");
			return modelAndView;
		}

		//查询本课程是否和学生关联
		SocietyStudentAndCourse societyStudentAndCourse =
				societyStudentAndCourseService.selectByStuIdCardAndCourseId(societyStudentCertificate.getStudentIdCardNum(),
				societyStudentCertificate.getCourseId());
		if(societyStudentAndCourse==null){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "学生未关联此课程");
			return modelAndView;
		}
		if(!(societyStudentAndCourse.getClassId().equals(classId))){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "学生未在此班级下");
			return modelAndView;
		}
		//专业id
		SocietySchoolMajor societySchoolMajor = societySchoolMajorService.loadById(majorId);
		societyStudentCertificate.setOwnerMajorName(societySchoolMajor.getMajorName());
		//课程id
		SocietySchoolCourse societySchoolCourse1 = societySchoolCourseService.loadById(courseId);
		societyStudentCertificate.setCourseName(societySchoolCourse1.getCourseName());
		//班级id
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		societyStudentCertificate.setClassName(societySchoolClass.getClassName());


		service.update(societyStudentCertificate);
		buildResponse(modelAndView);
		return modelAndView;
	}


	@RequestMapping(value = "/insertNew",method = RequestMethod.POST)
	public ModelAndView insertNew(@ModelAttribute SocietyStudentCertificate societyStudentCertificate){
		ModelAndView modelAndView = new ModelAndView();
		String majorId = societyStudentCertificate.getOwnerMajorId();
		String classId = societyStudentCertificate.getClassId();
		String courseId = societyStudentCertificate.getCourseId();
		SocietySchoolCourse societySchoolCourse =
				societySchoolCourseService.loadById(societyStudentCertificate.getCourseId());
		if(!(societySchoolCourse.getOwnerMajorId().equals(majorId))){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "课程不属于该专业下");
			return modelAndView;
		}

		SocietySchoolClassAndCourse societySchoolClassAndCourse =
				societySchoolClassAndCourseService.selectCourseIdAndClassId(classId,courseId);
		if(societySchoolClassAndCourse==null){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "班级未关联此课程");
			return modelAndView;
		}

		//查询本课程是否和学生关联
		SocietyStudentAndCourse societyStudentAndCourse =
				societyStudentAndCourseService.selectByStuIdCardAndCourseId(societyStudentCertificate.getStudentIdCardNum(),
						societyStudentCertificate.getCourseId());
		if(societyStudentAndCourse==null){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "学生未关联此课程");
			return modelAndView;
		}
		if(!(societyStudentAndCourse.getClassId().equals(classId))){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "学生未在此班级下");
			return modelAndView;
		}
		//专业id
		SocietySchoolMajor societySchoolMajor = societySchoolMajorService.loadById(majorId);
		societyStudentCertificate.setOwnerMajorName(societySchoolMajor.getMajorName());
		//课程id
		SocietySchoolCourse societySchoolCourse1 = societySchoolCourseService.loadById(courseId);
		societyStudentCertificate.setCourseName(societySchoolCourse1.getCourseName());
		//班级id
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		societyStudentCertificate.setClassName(societySchoolClass.getClassName());
		societyStudentCertificate.setClassAndStudentId(societyStudentAndCourse.getId());
		societyStudentCertificate.setOwnerSchoolId(societySchoolCourse.getOwnerSchoolId());
		societyStudentCertificate.setOwnerSchoolName(societySchoolCourse.getOwnerSchoolName());
		societyStudentCertificate.setCertificateImageUrl(societySchoolCourse.getCourseImage());
		societyStudentCertificate.setDataState("1");
		if(societyStudentCertificate.getCertificateCode()==null || societyStudentCertificate.getCertificateCode().equals("")){
			societyStudentCertificate.setCertificateCode(Guid.guid());
		}
		service.insert(societyStudentCertificate);
		buildResponse(modelAndView);
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
