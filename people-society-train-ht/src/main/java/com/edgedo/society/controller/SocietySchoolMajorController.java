package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.entity.SocietySchoolCourseUseGloble;
import com.edgedo.society.entity.SocietySchoolMajor;
import com.edgedo.society.queryvo.SocietySchoolCourseUseGlobleView;
import com.edgedo.society.queryvo.SocietySchoolMajorQuery;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.Dtree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolMajor")
@Controller
@RequestMapping("/society/societySchoolMajor")
public class SocietySchoolMajorController extends BaseController{
	
	@Autowired
	private SocietySchoolMajorService service;
	@Autowired
	private SocietySchoolService schoolService;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietySchoolCourseUseGlobleService societySchoolCourseUseGlobleService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolMajor", notes = "分页查询SocietySchoolMajor")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolMajorQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 根据学校信息查询所有的专业
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolMajor", notes = "分页查询SocietySchoolMajor")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoolMajorListpage",method = RequestMethod.POST)
	public ModelAndView schoolMajorListpage(@ModelAttribute SocietySchoolMajorQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
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
	@ApiOperation(value = "新增修改SocietySchoolMajor", notes = "新增修改SocietySchoolMajor")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolMajor voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			User user = getLoginUser();
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			String ownerSchoolId = voObj.getOwnerSchoolId();
			SocietySchool school = schoolService.loadById(ownerSchoolId);
			if(school == null){
				return buildErrorResponse(modelAndView,"未查询到学校信息！");
			}
			voObj.setOwnerSchoolId(school.getId());
			voObj.setOwnerSchoolName(school.getSchoolName());
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
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolMajor", notes = "新增修改SocietySchoolMajor")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdateForSchool",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateForSchool(SocietySchoolMajor voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			User user = getLoginUser();
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			String schoolId = user.getCompId();
			//查询当前学校信息
			SocietySchool school = schoolService.loadById(schoolId);
			if(school == null){
				return buildErrorResponse(modelAndView,"未查询到学校信息！");
			}
			voObj.setOwnerSchoolId(school.getId());
			voObj.setOwnerSchoolName(school.getSchoolName());
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
	@ApiOperation(value = "根据ID批量删除SocietySchoolMajor", notes = "根据ID批量删除SocietySchoolMajor")
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
	 * 批量逻辑删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/logicDeleteByIds",method = RequestMethod.POST)
	public ModelAndView logicDeleteByIds(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			int count = schoolClassService.countByMajorAndNotEnd(str);
			if(count == 0){
				list.add(str);
			}else{
				return buildErrorResponse(modelAndView,"请删除专业下未结束的班级！");
			}
		}
		service.logicDelete(list);
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SocietySchoolMajor", notes = "根据ID加载SocietySchoolMajor")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * @Author ZhangCC
	 * @Description 根据当前登录学校管理员查询专业信息
	 **/
	@RequestMapping(value = "/listBySchoolId",method = RequestMethod.POST)
	public ModelAndView listBySchoolId(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		//查询学校是否存在
		List<SocietySchoolMajorView> schoolMajorList = service.listBySchoolId(schoolId);
		//查询公共表
//		List<SocietySchoolCourseUseGlobleView> list =
//				societySchoolCourseUseGlobleService.selectBySchoolObject(schoolId);
//
//		for (SocietySchoolCourseUseGlobleView societySchoolCourseUseGlobleView : list){
//			SocietySchoolCourse societySchoolCourse =
//					societySchoolCourseService.loadById(societySchoolCourseUseGlobleView.getCourseId());
//			SocietySchoolMajorView societySchoolMajor =
//					service.loadByIdView(societySchoolCourse.getOwnerMajorId());
//			schoolMajorList.add(societySchoolMajor);
//		}
		return buildResponse(modelAndView,schoolMajorList);
	}


	@RequestMapping(value = "/listBySchoolIdSup",method = RequestMethod.POST)
	public ModelAndView listBySchoolIdSup(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<String> schoolIdList = schoolService.selectByXianQuId(user.getXianquId());
		List<String> schoolList = new ArrayList<>();

		for(String schoolId : schoolIdList){
			List<SocietySchoolCourseUseGlobleView> list =
					societySchoolCourseUseGlobleService.selectBySchoolObject(schoolId);
			if(list.size()!=0){
				for(SocietySchoolCourseUseGlobleView societySchoolCourseUseGlobleView : list){
					schoolList.add(societySchoolCourseUseGlobleView.getCourseSchId());
				}
			}else {
				schoolList.add(schoolId);
			}
		}
		LinkedHashSet<String> hashSet = new LinkedHashSet<>(schoolList);
		ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);
		if(listWithoutDuplicates.size()!=0){
			List<SocietySchoolMajorView> schoolMajorList = service.selectBySchoolList(listWithoutDuplicates);
			return buildResponse(modelAndView,schoolMajorList);
		}else {
			return buildResponse(modelAndView);
		}


	}

	/**
	 * @Author ZhangCC
	 * @Description 根据当学校id查询学校下面的专业信息
	 **/
	@RequestMapping(value = "/listAllBySchoolId",method = RequestMethod.POST)
	public ModelAndView listAllBySchoolId(String schoolId){
		ModelAndView modelAndView = new ModelAndView();
		//查询学校是否存在
		List<SocietySchoolMajorView> schoolMajorList = service.listBySchoolId(schoolId);
		return buildResponse(modelAndView,schoolMajorList);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description:根据学校加载菜单树
	 *@DateTime: 2020/5/7 16:10
	 */
	@RequestMapping(value = "/listForDtreeBySchoolId",method = RequestMethod.POST)
	public ModelAndView listForDtreeBySchoolId(){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		//查询学校是否存在
		List<Dtree> dtreeList = service.listForDtreeBySchoolId(loginUser.getCompId());
		return buildDtreeResponse(modelAndView,dtreeList);
	}

	/**
	 *@Author:TWQ
	 *@Description:根据学校id加载专业，学校树,平台管理员课程管理
	 *@DateTime: 2020-5-17 12:10:40
	 *@param schoolId
	 */
	@RequestMapping(value = "/listMajorForDtreeBySchoolId",method = RequestMethod.POST)
	public ModelAndView listMajorForDtreeBySchoolId(String schoolId){
		ModelAndView modelAndView = new ModelAndView();
		List<Dtree> dtreeList = service.listMajorForDtreeBySchoolId(schoolId);
		return buildResponse(modelAndView,dtreeList);
	}

	/**
	 *@Author:TWQ
	 *@Description:根据学校id加载专业，学校树,平台管理员课程分类管理
	 *@DateTime: 2020-5-18 12:10:40
	 *@param schoolId
	 */
	@RequestMapping(value = "/listMajorForCourseClsBySchoolId",method = RequestMethod.POST)
	public ModelAndView listMajorForCourseClsBySchoolId(String schoolId){
		ModelAndView modelAndView = new ModelAndView();
		List<Dtree> dtreeList = service.listMajorForCourseClsBySchoolId(schoolId);
		return buildResponse(modelAndView,dtreeList);
	}
	
}
