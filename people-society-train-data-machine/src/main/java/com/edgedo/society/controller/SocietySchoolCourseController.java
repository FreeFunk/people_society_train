package com.edgedo.society.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.DateUtil;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.entity.SocietySchoolCourseCls;
import com.edgedo.society.entity.Transfer;
import com.edgedo.society.queryvo.SocietySchoolCourseQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseView;
import com.edgedo.society.queryvo.SocietyStudentAndCourseQuery;
import com.edgedo.society.queryvo.SocietyStudentQuery;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.Dtree;
import com.edgedo.tyiyunoosclient.ISysTyiyunCloudStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Api(tags = "SocietySchoolCourse")
@Controller
@RequestMapping("/society/societySchoolCourse")
public class SocietySchoolCourseController extends BaseController{

	@Autowired
	private SocietySchoolCourseService service;
	@Autowired
	private ISysTyiyunCloudStorageService sysTyiyunCloudStorageService;
	@Autowired
	private SocietySchoolCourseClsService societySchoolCourseClsService;
	@Autowired
	private SocietySchoolClassAndCourseService classAndCourseService;
	@Autowired
	private SocietySchoolCourseNodeService nodeService;

	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietySchoolCourseUseGlobleService societySchoolCourseUseGlobleService;

	@Value("${app.server.fileForder}")
	private String fileForder;

	/**
	 * ????????????????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????????????????SocietySchoolCourse", notes = "????????????????????????SocietySchoolCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpageforcount",method = RequestMethod.POST)
	public ModelAndView listpageforcount(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(loginUser.getCompId());
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/listpageSchoolCourseSituation",method = RequestMethod.POST)
	public ModelAndView listpageSchoolCourseSituation(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//????????????????????? ?????????????????????????????? ???????????? ????????????????????????????????????
		//??????????????????????????????
		service.listPageCourseGloble(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ???????????????????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "???????????????????????????SocietySchoolCourse", notes = "???????????????????????????SocietySchoolCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjforcount",method = RequestMethod.POST)
	public ModelAndView listByObjforcount(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(loginUser.getCompId());
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietySchoolCourse", notes = "????????????SocietySchoolCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/*
	* ??????????????????????????????
	* */
	@RequestMapping(value = "/schoollistpage",method = RequestMethod.POST)
	public ModelAndView schoollistpage(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(loginUser.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/*
	* ??????????????????????????????
	* */
	@RequestMapping(value = "/ptlistpage",method = RequestMethod.POST)
	public ModelAndView ptlistpage(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/*
	 * ??????????????????????????????
	 * */
	@RequestMapping(value = "/addCourseListpage",method = RequestMethod.POST)
	public ModelAndView addCourseListpage(SocietySchoolCourseQuery query,String classId){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(loginUser.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		//?????????????????????
		List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
		query.setCourseIdList(courseIdList);
		service.classNotInCourseListPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/courseListpage",method = RequestMethod.POST)
	public ModelAndView courseListpage(SocietySchoolCourseQuery query,String classId){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(loginUser.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		//?????????????????????
		List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
		query.setCourseIdList(courseIdList);
		service.classNotInCourseListPage(query);
        buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ?????????????????????
	 * @return
	 */
	@RequestMapping(value = "/listCourse",method = RequestMethod.POST)
	public ModelAndView listCourse(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<SocietySchoolCourseView> list = service.selectAllCourse(user.getCompId());
		buildResponse(modelAndView,list);
		return modelAndView;
	}


	@RequestMapping(value = "/courseListLeftpage",method = RequestMethod.POST)
	public ModelAndView courseListLeftpage(SocietySchoolCourseQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(loginUser.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		//?????????????????????
		List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
		List<String> lis = Arrays.asList(ids.split(","));
		courseIdList.addAll(lis);
		query.setCourseIdList(courseIdList);
		service.classNotInCourseListPage(query);
        buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/courseListRightpage",method = RequestMethod.POST)
	public ModelAndView courseListRightpage(SocietySchoolCourseQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(loginUser.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		//?????????????????????
		List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
		List<String> lis = Arrays.asList(ids.split(","));
		courseIdList.addAll(lis);
		query.setCourseIdList(courseIdList);
		service.classYesInCourseListPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}



	@RequestMapping(value = "/courseAdminListpage",method = RequestMethod.POST)
	public ModelAndView courseAdminListpage(SocietySchoolCourseQuery query,String classId){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		//?????????????????????
		List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
		query.setCourseIdList(courseIdList);
		service.classNotInCourseListPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/courseAdminListLeftpage",method = RequestMethod.POST)
	public ModelAndView courseAdminListLeftpage(SocietySchoolCourseQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		//?????????????????????
		List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
		List<String> lis = Arrays.asList(ids.split(","));
		courseIdList.addAll(lis);
		query.setCourseIdList(courseIdList);
		service.classNotInCourseListPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/courseAdminListRightpage",method = RequestMethod.POST)
	public ModelAndView courseAdminListRightpage(SocietySchoolCourseQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		//?????????????????????
		List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
		List<String> lis = Arrays.asList(ids.split(","));
		courseIdList.addAll(lis);
		query.setCourseIdList(courseIdList);
		service.classYesInCourseListPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}





	@RequestMapping(value = "/addAdminCourseListpage",method = RequestMethod.POST)
	public ModelAndView addAdminCourseListpage(SocietySchoolCourseQuery query,String classId){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		//?????????????????????
		List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
		query.setCourseIdList(courseIdList);
		service.classNotInCourseListPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ????????????
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "????????????SocietySchoolCourse", notes = "????????????SocietySchoolCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolCourse voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		String courseImage = voObj.getCourseImage();
		if(id==null || id.trim().equals("")){
			User loginUser = getLoginUser();
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(loginUser.getUserId());
			voObj.setCreateUserName(loginUser.getUserName());
			voObj.setTotalLessons(0);
			voObj.setCourseTimeLength(0);
			voObj.setShState("1");
			voObj.setDataState("1");
			voObj.setIsOpen("1");
			SocietySchoolCourseCls schoolCourseCls = societySchoolCourseClsService.loadById(voObj.getCourseClsId());
			voObj.setOwnerSchoolId(schoolCourseCls.getOwnerSchoolId());
			voObj.setOwnerSchoolName(schoolCourseCls.getOwnerSchoolName());
			voObj.setOwnerMajorId(schoolCourseCls.getOwnerMajorId());
			voObj.setOwnerMajorName(schoolCourseCls.getOwnerMajorName());
			voObj.setCourseClsName(schoolCourseCls.getCourseClsName());
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
	 * ???????????????????????????
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "????????????SocietySchoolCourse", notes = "????????????SocietySchoolCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoolSaveOrUpdate",method = RequestMethod.POST)
	public ModelAndView schoolSaveOrUpdate(SocietySchoolCourse voObj){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		String compId = loginUser.getCompId();
		String errMsg = "";
		String id = voObj.getId();
		String courseImage = voObj.getCourseImage();
		if(courseImage!=null && !courseImage.equals("")){
			String filePath = "";
			String rpath = "/"+compId+"/course";
			File file = new File(courseImage);
			try {
				filePath = rpath+FileUtil.saveFile(file, fileForder+rpath,true);
				//??????????????????
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setCourseImage(filePath);
		}else {
			voObj.setCourseImage(null);
		}
		if(id==null || id.trim().equals("")){
			//?????????????????????????????????
			if(voObj.getCoursePrice() == null){
				voObj.setCoursePrice(new BigDecimal(0));
			}
			if(voObj.getCourseOrgPrice() == null){
				voObj.setCourseOrgPrice(new BigDecimal(0));
			}
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(loginUser.getUserId());
			voObj.setCreateUserName(loginUser.getUserName());
			voObj.setTotalLessons(0);
			voObj.setCourseTimeLength(0);
			voObj.setShState("1");
			voObj.setDataState("1");
			voObj.setIsOpen("1");
			voObj.setTotalStudentNum(0);
			voObj.setFinishedStudentNum(0);
			voObj.setNotFinishedStudentNum(0);
			SocietySchoolCourseCls schoolCourseCls = societySchoolCourseClsService.loadById(voObj.getCourseClsId());
			voObj.setOwnerSchoolId(schoolCourseCls.getOwnerSchoolId());
			voObj.setOwnerSchoolName(schoolCourseCls.getOwnerSchoolName());
			voObj.setOwnerMajorId(schoolCourseCls.getOwnerMajorId());
			voObj.setOwnerMajorName(schoolCourseCls.getOwnerMajorName());
			voObj.setCourseClsName(schoolCourseCls.getCourseClsName());
			//??????????????????
			int courseNum = service.selectBySchoolId(schoolCourseCls.getOwnerSchoolId());
			voObj.setOrderNum(new BigDecimal(courseNum));
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
	 * ????????????  ?????? ???????????? ??????
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/multistageAdd",method = RequestMethod.POST)
	public ModelAndView multistageAdd(SocietySchoolCourse voObj){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		String compId = loginUser.getCompId();
		String errMsg = "";
		String courseImage = voObj.getCourseImage();
		if(courseImage!=null && !courseImage.equals("")){
			String filePath = "";
			String rpath = "/"+compId+"/course";
			File file = new File(courseImage);
			try {
				filePath = rpath+FileUtil.saveFile(file, fileForder+rpath,true);
				//??????????????????
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setCourseImage(filePath);
		}else {
			voObj.setCourseImage(null);
		}
		//?????????????????????????????????
		if(voObj.getCoursePrice() == null){
			voObj.setCoursePrice(new BigDecimal(0));
		}
		if(voObj.getCourseOrgPrice() == null){
			voObj.setCourseOrgPrice(new BigDecimal(0));
		}
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(loginUser.getUserId());
		voObj.setCreateUserName(loginUser.getUserName());
		voObj.setTotalLessons(0);
		voObj.setCourseTimeLength(0);
		voObj.setShState("1");
		voObj.setDataState("1");
		voObj.setIsOpen("1");
		voObj.setTotalStudentNum(0);
		voObj.setFinishedStudentNum(0);
		voObj.setNotFinishedStudentNum(0);
		voObj.setOwnerSchoolId(loginUser.getCompId());
		//??????????????????
		int courseNum = service.selectBySchoolId(loginUser.getCompId());
		voObj.setOrderNum(new BigDecimal(courseNum));
		errMsg = service.insertMultistageAdd(voObj);

		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	//
	@RequestMapping(value = "/schoolSaveOrUpdateTeacher",method = RequestMethod.POST)
	public ModelAndView schoolSaveOrUpdateTeacher(SocietySchoolCourseView voObj){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		String compId = loginUser.getCompId();
		String errMsg = "";
		String id = voObj.getId();
		String courseImage = voObj.getCourseImage();
		if(courseImage!=null && !courseImage.equals("")){
			String filePath = "";
			String rpath = "/"+compId+"/course";
			File file = new File(courseImage);
			try {
				filePath = rpath+FileUtil.saveFile(file, fileForder+rpath,true);
				//??????????????????
				file.delete();
			} catch (Exception e) {  
				e.printStackTrace();
			}
			voObj.setCourseImage(filePath);
		}else {
			voObj.setCourseImage(null);
		}
		voObj.setOwnerSchoolId(compId);
		errMsg = service.updateTeacher(voObj);
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
	@ApiOperation(value = "??????ID????????????SocietySchoolCourse", notes = "??????ID????????????SocietySchoolCourse")
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
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/updateByIds",method = RequestMethod.POST)
	public ModelAndView updateByIds(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.updateByIds(list);
		return buildResponse(modelAndView);
	}

	/**
	 * ??????????????????
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/updateById",method = RequestMethod.POST)
	public ModelAndView updateById(String ids){
		ModelAndView modelAndView = new ModelAndView();
		//????????????????????????????????????
		int count = nodeService.countByCourseId(ids);
		if(count>0){
			return buildErrorResponse(modelAndView,"???????????????????????????????????????");
		}else {
			service.updateByIdNew(ids);
			return buildResponse(modelAndView);
		}
	}


	/**
	 * ?????????????????????????????????
	 * @param classId ??????id ownerSchoolId ??????id id ??????id
	 * @return
	 */
	@RequestMapping(value = "/deteleByIds",method = RequestMethod.POST)
	public ModelAndView deteleByIds(String id ,String classId ,String ownerSchoolId){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = id.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}//28cd601b3c134f6e9dd66062515c1039	SHANLIHONGJIAOYU	cbecfb77409a4f498ca500af1d83cd3b	471414ab33f3474e912508c11aa78bc4				0	0
		//classAndCourseService.deteleToClassIdAndSchoolIdAndId(list,classId,ownerSchoolId);
		//????????????
		classAndCourseService.updateToClassIdAndSchoolIdAndId(list,classId,ownerSchoolId);
		return buildResponse(modelAndView);
	}

	/**
	 * ??????????????????
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "??????ID??????SocietySchoolCourse", notes = "??????ID??????SocietySchoolCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * ??????????????????????????????
	 * @param query
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/classCourseListpage",method = RequestMethod.POST)
	public ModelAndView classCourseListpage(@ModelAttribute SocietySchoolCourseQuery query, String classId){
		ModelAndView modelAndView = new ModelAndView();
		List<String> courseIdList = classAndCourseService.selectCousreIdByClass(classId);
		if(courseIdList.size() > 0){
			query.setCourseIdList(courseIdList);
			service.classCourseListPage(query);
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 *???????????????????????????
	 */
	@RequestMapping(value = "/listForDtreeCourseId",method = RequestMethod.POST)
	public ModelAndView listForDtreeCourseId(){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		List<Dtree> dtreeList = service.listForDtreeBySchoolId(loginUser.getCompId());
		return buildDtreeResponse(modelAndView,dtreeList);
	}

	@RequestMapping(value = "/courseList",method = RequestMethod.POST)
	public ModelAndView courseList(){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietySchoolCourse> list = service.selectAll();
		return buildResponse(modelAndView,list);
	}

	@RequestMapping(value = "/listMajorId",method = RequestMethod.POST)
	public ModelAndView listMajorId(String majorId){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		if (majorId!=""){
			List<SocietySchoolCourse> list = service.listMajorId(loginUser.getCompId(),majorId);
			return buildResponse(modelAndView,list);
		}else {
			List<SocietySchoolCourseView> list = service.selectAllCourse(loginUser.getCompId());
			return buildResponse(modelAndView,list);
		}
	}

	@RequestMapping(value = "/listCourseAll",method = RequestMethod.POST)
	public ModelAndView listCourseAll(){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietySchoolCourse> list = service.selectAll();
		return buildResponse(modelAndView,list);
	}

	@RequestMapping(value = "/listSchoolId",method = RequestMethod.POST)
	public ModelAndView listSchoolId(String schoolId){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietySchoolCourseView> list = service.selectCourseAllIsNoSchool(schoolId);
		return buildResponse(modelAndView,list);
	}

	@RequestMapping(value = "/isSchollIdList",method = RequestMethod.POST)
	public ModelAndView isSchollIdList(String schoolId){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietySchoolCourseView> list = service.selectCourseAllIsSchool(schoolId);
		return buildResponse(modelAndView,list);
	}

/*	*//**
	 * ????????????id ???????????????????????????????????????
	 * @param query
	 * @return
	 *//*
	@RequestMapping(value = "/CourseStudyListTablelistpage",method = RequestMethod.POST)
	public ModelAndView CourseStudyListTablelistpage(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//?????? ??????????????????????????????
		service.listPageCountCourse(query);
		return buildResponse(modelAndView,query);
	}*/
}
