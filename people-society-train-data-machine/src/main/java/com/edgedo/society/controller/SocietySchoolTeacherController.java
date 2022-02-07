package com.edgedo.society.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.IdCardUtil;
import com.edgedo.society.entity.SocietySchoolTeacher;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import com.edgedo.society.queryvo.SocietySchoolTeacherQuery;
import com.edgedo.society.service.SocietySchoolCourseService;
import com.edgedo.society.service.SocietySchoolTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolTeacher")
@Controller
@RequestMapping("/society/societySchoolTeacher")
public class SocietySchoolTeacherController extends BaseController{
	
	@Autowired
	private SocietySchoolTeacherService service;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;

	@Value("${app.server.fileForder}")
	private String fileForder;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolTeacher", notes = "分页查询SocietySchoolTeacher")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolTeacherQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolTeacher", notes = "新增修改SocietySchoolTeacher")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolTeacher voObj){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		String compId = loginUser.getCompId();
		String errMsg = "";
		String id = voObj.getId();
		String certImgUrl = voObj.getCertImgUrl();
		if(certImgUrl!=null && !certImgUrl.equals("")){
			String filePath = "";
			String rpath = "/"+compId+"/teacher";
			File file = new File(certImgUrl);
			try {
				filePath = rpath+ FileUtil.saveFile(file, fileForder+rpath,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setCertImgUrl(filePath);
		}else {
			voObj.setCertImgUrl(null);
		}
		if(id==null || id.trim().equals("")){
			voObj.setOwnerSchoolId(compId);
			voObj.setCreateTime(new Date());//创建时间
			voObj.setCreateUserId(loginUser.getUserId());//创建人id
			voObj.setCreateUserName(loginUser.getUserName());//创建人名
			voObj.setDataState("1");
			errMsg = service.insertNewMain(voObj);
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
	@ApiOperation(value = "根据ID批量删除SocietySchoolTeacher", notes = "根据ID批量删除SocietySchoolTeacher")
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
	@ApiOperation(value = "根据ID加载SocietySchoolTeacher", notes = "根据ID加载SocietySchoolTeacher")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 查询该学校下的所有讲师
	 * @return
	 */
	@RequestMapping(value = "/listBySchoolIdAll",method = RequestMethod.POST)
	public ModelAndView listBySchoolIdAll(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		//根据学校id查询所有的讲师
		List<SocietySchoolTeacher> schoolMajorList = service.listBySchoolId(schoolId);
		return buildResponse(modelAndView,schoolMajorList);
	}
	
	
}
