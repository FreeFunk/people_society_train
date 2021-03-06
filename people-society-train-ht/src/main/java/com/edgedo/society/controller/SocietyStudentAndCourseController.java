package com.edgedo.society.controller;


import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.HtmlToPdfUtil;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.service.SysUserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentAndCourse")
@Controller
@RequestMapping("/society/societyStudentAndCourse")
public class SocietyStudentAndCourseController extends BaseController{
	
	@Autowired
	private SocietyStudentAndCourseService service;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolClassAdminService societySchoolClassAdminService;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietyStudentService societyStudentService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Autowired
	private SocietyStudentTestPaperService societyStudentTestPaperService;
	@Autowired
	private SocietyStudentStudyProcessFaceService societyStudentStudyProcessFaceService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;
	@Autowired
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	@Autowired
	private SocietySchoolClassGroupAdminService societySchoolClassGroupAdminService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;

	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;

	@Autowired
	private SocietySchoolClassAndCourseService societySchoolClassAndCourseService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	private
	@Value("${wkhtmltopdf.toPdfTool}")
	String toPdfTool;

	
	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudentAndCourse", notes = "????????????SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ????????????,???????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudentAndCourse", notes = "????????????SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpageforschool",method = RequestMethod.POST)
	public ModelAndView listpageforschool(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/listpageByStuCou",method = RequestMethod.POST)
	public ModelAndView listpageByStuCou(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.listPageByStuCou(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/listpageforschoolStudentOnce",method = RequestMethod.POST)
	public ModelAndView listpageforschoolStudentOnce(@ModelAttribute SocietyStudentAndCourseQuery query){

		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBySchoolAndClass",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClass(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(80);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySchoolAndClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/selectBySupSchoolAndClass",method = RequestMethod.POST)
	public ModelAndView selectBySupSchoolAndClass(@ModelAttribute SocietyStudentAndCourseQuery query){
		/*if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(80);
			query.getQueryObj().setCourseProgressEnd(100);
		}*/
		SysUser sysUser = sysUserService.loadById(getLoginUser().getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySchoolAndClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBySupStudentProAndClass",method = RequestMethod.POST)
	public ModelAndView selectBySupStudentProAndClass(@ModelAttribute SocietyStudentAndCourseQuery query){
		/*if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(80);
			query.getQueryObj().setCourseProgressEnd(100);
		}*/
		SysUser sysUser = sysUserService.loadById(getLoginUser().getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySupStudentProAndClass(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * ????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectBySupSchoolAndClassHome",method = RequestMethod.POST)
	public ModelAndView selectBySupSchoolAndClassHome(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		SysUser sysUser = sysUserService.loadById(user.getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySchoolAndClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ??????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectBySchoolAndClassHome",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClassHome(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySchoolAndClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageforclassadmin",method = RequestMethod.POST)
	public ModelAndView listpageforclassadmin(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classId = societySchoolClassService.selectByClassAdminId(classAdminId);
		if(classId.size()==0){
			return buildResponse(modelAndView,query);
		}else {
			query.setClassIdList(classId);
			service.forclassadminlistPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	/**
	 * ??????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageforclassGroupadmin",method = RequestMethod.POST)
	public ModelAndView listpageforclassGroupadmin(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		String classGroupAdminId = societySchoolClassGroupAdminService.selectBySysUserId(user.getUserId());
		List<String> classAdminId = societySchoolClassAdminService.selectByGroupId(classGroupAdminId);
		List<String> classId = societySchoolClassService.selectByClassAdminIdList(classAdminId);
		if(classId.size()==0){
			return buildResponse(modelAndView,query);
		}else {
			query.setClassIdList(classId);
			service.forclassadminlistPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	/**
	 * ???????????????,???????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "???????????????SocietyStudentAndCourse", notes = "???????????????SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjforschool",method = RequestMethod.POST)
	public ModelAndView listByObjforschool(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listByExcObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * ?????????????????????excel?????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByNodeIsFiled",method = RequestMethod.POST)
	public ModelAndView selectByNodeIsFiled(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectByNodeIsFiled(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ???????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listByObjforschoolClassAdmin",method = RequestMethod.POST)
	public ModelAndView listByObjforschoolClassAdmin(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classId = societySchoolClassService.selectByClassAdminId(classAdminId);
		if(classId.size()==0){
			return buildResponse(modelAndView,query);
		}else {
			query.setClassIdList(classId);
			service.listByExcObjClassAdmin(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}

	}

	/**
	 * ????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listByObjforschoolRenShe",method = RequestMethod.POST)
	public ModelAndView listByObjforschoolRenShe(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listByExcRenSheObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * ????????????,????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudentAndCourse", notes = "????????????SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpageforpeoplesociety",method = RequestMethod.POST)
	public ModelAndView listpageforpeoplesociety(@ModelAttribute SocietyStudentAndCourseQuery query){
		//????????????????????????
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????????????????id
		if(query.getQueryObj().getOwnerSchoolId()!=null&&query.getQueryObj().getOwnerSchoolId().length()>0){
			SocietySchoolQuery qq = new SocietySchoolQuery();
			qq.getQueryObj().setSchoolName(query.getQueryObj().getOwnerSchoolId());
			societySchoolService.listPage(qq);
			if(qq.getList().size()>0){
				List<SocietySchoolView> listS = qq.getList();
				query.getQueryObj().setOwnerSchoolId(listS.get(0).getId());
			}
		}

		/*if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}*/
		service.listPage(query);
		//??????????????????
		List<SocietyStudentAndCourseView> listV = query.getList();
		for(SocietyStudentAndCourseView view : listV){
			SocietySchool ss = societySchoolService.loadById(view.getOwnerSchoolId());
			view.setOwnerSchoolId(ss.getSchoolName());
		}

		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ???????????????,????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "???????????????SocietyStudentAndCourse", notes = "???????????????SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjforpeoplesociety",method = RequestMethod.POST)
	public ModelAndView listByObjforpeoplesociety(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????????????????id
		if(query.getQueryObj().getOwnerSchoolId()!=null&&query.getQueryObj().getOwnerSchoolId().length()>0){
			SocietySchoolQuery qq = new SocietySchoolQuery();
			qq.getQueryObj().setSchoolName(query.getQueryObj().getOwnerSchoolId());
			societySchoolService.listPage(qq);
			if(qq.getList().size()>0){
				List<SocietySchoolView> listS = qq.getList();
				query.getQueryObj().setOwnerSchoolId(listS.get(0).getId());
			}
		}
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listByObj(query);
		//??????????????????
		List<SocietyStudentAndCourseView> listV = query.getList();
		for(SocietyStudentAndCourseView view : listV){
			SocietySchool ss = societySchoolService.loadById(view.getOwnerSchoolId());
			view.setOwnerSchoolId(ss.getSchoolName());
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * ????????????
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudentAndCourse", notes = "????????????SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentAndCourse voObj){
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
	@ApiOperation(value = "??????ID????????????SocietyStudentAndCourse", notes = "??????ID????????????SocietyStudentAndCourse")
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
	@ApiOperation(value = "??????ID??????SocietyStudentAndCourse", notes = "??????ID??????SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	@RequestMapping(value = "/selectBySchoolName",method = RequestMethod.POST)
	public ModelAndView selectBySchoolName(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectBySchoolNamelistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ?????????????????????????????????????????? ????????????
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/selectByStuIdAndSchoolId",method = RequestMethod.POST)
	public ModelAndView selectByStuIdAndSchoolId(String studentId){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<SocietyStudentAndCourseView> list =
				service.selectByStuIdAndSchoolId(studentId,user.getCompId());
		return buildResponse(modelAndView,list);
	}

	/**
	 * ????????????id ??????id ??????id ??????id ????????????????????????id
	 * @param schoolId
	 * @param classId
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/selectBySchoolIdAndClassIdAndCourseId",method = RequestMethod.POST)
	public ModelAndView selectBySchoolIdAndClassIdAndCourseId(String schoolId,String classId,String courseId,String studentId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentAndCourseView societyStudentAndCourseView =
				service.selectSchAndClaAndCouAndStu(schoolId,classId,courseId,studentId);
		return buildResponse(modelAndView,societyStudentAndCourseView);
	}

	/**
	 * ????????????id ???????????????????????????????????????
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/CourseStudyListTablelistpage",method = RequestMethod.POST)
	public ModelAndView CourseStudyListTablelistpage(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		//?????? ??????????????????????????????
		Integer num = service.selectByCourseStudyNum(courseId);
		return buildResponse(modelAndView,num);
	}

	@RequestMapping(value = "/pdf",method = RequestMethod.POST)
	public ModelAndView pdf(String urlStr){
		ModelAndView modelAndView = new ModelAndView();
		//?????? ??????????????????????????????
		HtmlToPdfUtil pdfUtil = new HtmlToPdfUtil(toPdfTool);
		pdfUtil.convert(
				urlStr,
				"E:/baidu.pdf",
				(state) ->{
					if(state.equals("success")){
						System.out.println("success");
						//TODO:??????
					}else{
						//TODO:??????
						System.out.println("fail");
					}
				});
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/studyProvePdf",method = RequestMethod.POST)
	public ModelAndView studyProvePdf(String studentId,String classId,String schoolId,String courseId,String stuAndCouId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		//????????????
		SocietyStudent societyStudent = societyStudentService.loadById(studentId);
		//????????????
		SocietySchool societySchool = societySchoolService.loadById(schoolId);
		//????????????
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		//????????????
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		//????????? ????????????
		SocietyStudentCertificateView societyStudentCertificateView
				= societyStudentCertificateService.selectByStuIdAndCourId(studentId,courseId);
		SocietyStudentAndCourseView societyStudentAndCourseViewThird = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewThird.setStudyProve(societyStudentCertificateView.getCertificateCode());
		societyStudentAndCourseViewThird.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseViewThird.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseViewThird.setSchoolName(societySchool.getSchoolName());
		societyStudentAndCourseViewThird.setClassName(societySchoolClass.getClassName());
		societyStudentAndCourseViewThird.setClassTimePeriod(getDatePeriodSecond(societySchoolClass.getClassStartTime(),
				societySchoolClass.getClassEndTime()));
		societyStudentAndCourseViewThird.setCourseName(societySchoolCourse.getCourseName());
		Integer studyLength = societyStudentAndNodeService.countStudyLength(stuAndCouId)/60;
//		Integer questionLength = societyStudentPractiseQuestionService.countQuestionLength(stuAndCouId)/60;
		societyStudentAndCourseViewThird.setStudyTimeLength(studyLength);
//		societyStudentAndCourseViewThird.setQuestionTimeLength(questionLength);
		societyStudentAndCourseViewThird.setTotalTimeLength(studyLength);
		societyStudentAndCourseViewThird.setCertificateTime(societyStudentCertificateView.getCertificateTime());
		map.put("fourth",societyStudentAndCourseViewThird);
		return buildResponse(modelAndView,map);
	}


	@RequestMapping(value = "/tempFilePdfZip",method = RequestMethod.POST)
	public ModelAndView tempFilePdfZip(String classId){
		ModelAndView modelAndView = new ModelAndView();
		List<Map<String,Object>> list = new ArrayList<>();
		List<SocietyStudentAndCourseView> stuCouList = service.selectByClassIdVoFinish(classId);
		stuCouList.forEach(stuCou->{
			Map<String,Object> map = new HashMap<>();
			String studentId = stuCou.getStudentId();
			String schoolId = stuCou.getOwnerSchoolId();
			String courseId = stuCou.getCourseId();
			String stuAndCouId = stuCou.getId();
			//????????????
			SocietyStudent societyStudent = societyStudentService.loadById(studentId);
			//????????????
			SocietySchool societySchool = societySchoolService.loadById(schoolId);
			//????????????
			SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
			//????????????
			SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
			//??????????????????8
			SocietyStudentAndCourse societyStudentAndCourse = service.loadById(stuAndCouId);
			//?????????  ???????????? ?????? ???????????? ???????????? ????????????
			map.put("conName",societySchool.getContactPerson());
			map.put("first",societyStudent);
			//????????????
			SocietySchoolClassAndStudentView societySchoolClassAndStudentView =
					societySchoolClassAndStudentService.selectVoByClassAndStudent(classId,studentId);
			map.put("studentNum",societySchoolClassAndStudentView.getArchivesCode());
			//????????? ???????????? ????????????
			SocietyStudentAndCourseView societyStudentAndCourseView = new SocietyStudentAndCourseView();
			societyStudentAndCourseView.setStudentName(societyStudent.getStudentName());
			societyStudentAndCourseView.setSex(societyStudent.getStudentSex());
			societyStudentAndCourseView.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
			societyStudentAndCourseView.setStudentPohone(societyStudent.getStudentPhoneNum());
			societyStudentAndCourseView.setRegisterTime(societyStudent.getCreateTime());
			societyStudentAndCourseView.setFinishNodeNum(societyStudentAndCourse.getFinishNodeNum());
			societyStudentAndCourseView.setClassName(societySchoolClass.getClassName());
			societyStudentAndCourseView.setClassCode(societySchoolClass.getClassCode());
			societyStudentAndCourseView.setClassTimePeriod(getDatePeriod(societySchoolClass.getClassStartTime(),
					societySchoolClass.getClassEndTime()));
			if(societyStudentAndCourse.getLearnFinishTime()==null){
				SocietyStudentAndNode societyStudentAndNode =
						societyStudentAndNodeService.selectByStuCouIdNewOnce(stuAndCouId);
				societyStudentAndCourseView.setStudyTimePeriod(getDatePeriod(societyStudentAndCourse.getCreateTime(),
						societyStudentAndNode.getFinishTime()));
			}else {
				societyStudentAndCourseView.setStudyTimePeriod(getDatePeriod(societyStudentAndCourse.getCreateTime(),
						societyStudentAndCourse.getLearnFinishTime()));
			}
			societyStudentAndCourseView.setStudyMode("??????");
			societyStudentAndCourseView.setCourseForm("??????");
			societyStudentAndCourseView.setFacePhoto(societyStudent.getFaceImageUrl());
			map.put("second",societyStudentAndCourseView);
			//????????? ????????????
			SocietyStudentAndCourseView societyStudentAndCourseViewSecond = new SocietyStudentAndCourseView();
			societyStudentAndCourseViewSecond.setCourseName(societySchoolCourse.getCourseName());
			societyStudentAndCourseViewSecond.setTotalLessons(societySchoolCourse.getTotalLessons());
			societyStudentAndCourseViewSecond.setFinishNodeNum(societyStudentAndCourse.getFinishNodeNum());
			societyStudentAndCourseViewSecond.setLearnIsFinished("??????");
			List<SocietyStudentTestPaper> testPaperList =
					societyStudentTestPaperService.selectByStuIdAndCourseIdAndSchoolIdList(studentId,courseId,schoolId);
			if(testPaperList.size()==0){
				societyStudentAndCourseViewSecond.setTestRightRate(new BigDecimal(0));
				societyStudentAndCourseViewSecond.setTestScore(0);
				societyStudentAndCourseViewSecond.setTestIsPass("???");
				societyStudentAndCourseViewSecond.setTestName("???");
			}else {
				SocietyStudentTestPaper societyStudentTestPaper = testPaperList.get(0);
				societyStudentAndCourseViewSecond.setTestRightRate(societyStudentTestPaper.getTestRightRate());
				societyStudentAndCourseViewSecond.setTestScore(societyStudentTestPaper.getGetScore());
				if("1".equals(societyStudentTestPaper.getIsPass())){
					societyStudentAndCourseView.setIsTepar("???");
				}else {
					societyStudentAndCourseView.setIsTepar("???");
				}
				societyStudentAndCourseViewSecond.setTestName(societyStudentTestPaper.getTestPaperName());
			}
			List<SocietyStudentStudyProcessFace> faceAllList =
					societyStudentStudyProcessFaceService.selectByStuCouId(stuAndCouId);
			societyStudentAndCourseViewSecond.setFaceAuthentication("?????????"+faceAllList.size()+"????????????????????????"+faceAllList.size()+"????????????0??????");
			List<SocietyStudentStudyProcessFace> faceRandomList =
					societyStudentStudyProcessFaceService.selectByStuCouIdRand(stuAndCouId);

			societyStudentAndCourseViewSecond.setFaceList(faceRandomList);
			SocietyStudentCertificateView societyStudentCertificateView
					= societyStudentCertificateService.selectByStuIdAndCourId(studentId,courseId);
			societyStudentAndCourseViewSecond.setStudyProve(societyStudentCertificateView.getCertificateCode());
			map.put("third",societyStudentAndCourseViewSecond);
			//????????? ????????????
			SocietyStudentAndCourseView societyStudentAndCourseViewThird = new SocietyStudentAndCourseView();
			societyStudentAndCourseViewThird.setId(stuAndCouId);
			societyStudentAndCourseViewThird.setStudyProve(societyStudentCertificateView.getCertificateCode());
			societyStudentAndCourseViewThird.setStudentName(societyStudent.getStudentName());
			societyStudentAndCourseViewThird.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
			societyStudentAndCourseViewThird.setSchoolName(societySchool.getSchoolName());
			societyStudentAndCourseViewThird.setClassName(societySchoolClass.getClassName());
			societyStudentAndCourseViewThird.setClassTimePeriod(getDatePeriodSecond(societySchoolClass.getClassStartTime(),
					societySchoolClass.getClassEndTime()));
			societyStudentAndCourseViewThird.setCourseName(societySchoolCourse.getCourseName());
			Integer studyLength = societyStudentAndNodeService.countStudyLength(stuAndCouId)/60;
//		Integer questionLength = societyStudentPractiseQuestionService.countQuestionLength(stuAndCouId)/60;
			societyStudentAndCourseViewThird.setStudyTimeLength(studyLength);
//		societyStudentAndCourseViewThird.setQuestionTimeLength(questionLength);
			societyStudentAndCourseViewThird.setTotalTimeLength(studyLength);
			societyStudentAndCourseViewThird.setCertificateTime(societyStudentCertificateView.getCertificateTime());
			map.put("fourth",societyStudentAndCourseViewThird);

			//????????? ??????
			SocietyStudentAndCourseView societyStudentAndCourseViewFourth = new SocietyStudentAndCourseView();
			societyStudentAndCourseViewFourth.setStudentName(societyStudent.getStudentName());
			societyStudentAndCourseViewFourth.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
			societyStudentAndCourseViewFourth.setCourseName(societySchoolCourse.getCourseName());
			List<SocietySchoolCourseNodeView> nodeList = societySchoolCourseNodeService.selectByCourseId(courseId);
			societyStudentAndCourseViewFourth.setNodeList(nodeList);
			map.put("fifth",societyStudentAndCourseViewFourth);
			list.add(map);
		});
		return buildResponse(modelAndView,list);
	}

	@RequestMapping(value = "/tempFilePdf",method = RequestMethod.POST)
	public ModelAndView tempFilePdf(String studentId,String classId,String schoolId,String courseId,String stuAndCouId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		//????????????
		SocietyStudent societyStudent = societyStudentService.loadById(studentId);
		//????????????
		SocietySchool societySchool = societySchoolService.loadById(schoolId);
		//????????????
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		//????????????
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		//??????????????????8
		SocietyStudentAndCourse societyStudentAndCourse = service.loadById(stuAndCouId);
		//?????????  ???????????? ?????? ???????????? ???????????? ????????????
		map.put("conName",societySchool.getContactPerson());
		map.put("first",societyStudent);
		//????????????
		SocietySchoolClassAndStudentView societySchoolClassAndStudentView =
				societySchoolClassAndStudentService.selectVoByClassAndStudent(classId,studentId);
		map.put("studentNum",societySchoolClassAndStudentView.getArchivesCode());
		//????????? ???????????? ????????????
		SocietyStudentAndCourseView societyStudentAndCourseView = new SocietyStudentAndCourseView();
		societyStudentAndCourseView.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseView.setSex(societyStudent.getStudentSex());
		societyStudentAndCourseView.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseView.setStudentPohone(societyStudent.getStudentPhoneNum());
		societyStudentAndCourseView.setRegisterTime(societyStudent.getCreateTime());
		societyStudentAndCourseView.setFinishNodeNum(societyStudentAndCourse.getFinishNodeNum());
		societyStudentAndCourseView.setClassName(societySchoolClass.getClassName());
		societyStudentAndCourseView.setClassCode(societySchoolClass.getClassCode());
		societyStudentAndCourseView.setClassTimePeriod(getDatePeriod(societySchoolClass.getClassStartTime(),
				societySchoolClass.getClassEndTime()));
		if(societyStudentAndCourse.getLearnFinishTime()==null){
			SocietyStudentAndNode societyStudentAndNode =
					societyStudentAndNodeService.selectByStuCouIdNewOnce(stuAndCouId);
			societyStudentAndCourseView.setStudyTimePeriod(getDatePeriod(societyStudentAndCourse.getCreateTime(),
					societyStudentAndNode.getFinishTime()));
		}else {
			societyStudentAndCourseView.setStudyTimePeriod(getDatePeriod(societyStudentAndCourse.getCreateTime(),
					societyStudentAndCourse.getLearnFinishTime()));
		}
		societyStudentAndCourseView.setStudyMode("??????");
		societyStudentAndCourseView.setCourseForm("??????");
		societyStudentAndCourseView.setFacePhoto(societyStudent.getFaceImageUrl());
		map.put("second",societyStudentAndCourseView);
		//????????? ????????????
		SocietyStudentAndCourseView societyStudentAndCourseViewSecond = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewSecond.setCourseName(societySchoolCourse.getCourseName());
		societyStudentAndCourseViewSecond.setTotalLessons(societySchoolCourse.getTotalLessons());
		societyStudentAndCourseViewSecond.setFinishNodeNum(societyStudentAndCourse.getFinishNodeNum());
		societyStudentAndCourseViewSecond.setLearnIsFinished("??????");
		List<SocietyStudentTestPaper> testPaperList =
				societyStudentTestPaperService.selectByStuIdAndCourseIdAndSchoolIdList(studentId,courseId,schoolId);
		if(testPaperList.size()==0){
			societyStudentAndCourseViewSecond.setTestRightRate(new BigDecimal(0));
			societyStudentAndCourseViewSecond.setTestScore(0);
			societyStudentAndCourseViewSecond.setTestIsPass("???");
			societyStudentAndCourseViewSecond.setTestName("???");
		}else {
			SocietyStudentTestPaper societyStudentTestPaper = testPaperList.get(0);
			societyStudentAndCourseViewSecond.setTestRightRate(societyStudentTestPaper.getTestRightRate());
			societyStudentAndCourseViewSecond.setTestScore(societyStudentTestPaper.getGetScore());
			if("1".equals(societyStudentTestPaper.getIsPass())){
				societyStudentAndCourseView.setIsTepar("???");
			}else {
				societyStudentAndCourseView.setIsTepar("???");
			}
			societyStudentAndCourseViewSecond.setTestName(societyStudentTestPaper.getTestPaperName());
		}
		List<SocietyStudentStudyProcessFace> faceAllList =
				societyStudentStudyProcessFaceService.selectByStuCouId(stuAndCouId);
		societyStudentAndCourseViewSecond.setFaceAuthentication("?????????"+faceAllList.size()+"????????????????????????"+faceAllList.size()+"????????????0??????");
		List<SocietyStudentStudyProcessFace> faceRandomList =
				societyStudentStudyProcessFaceService.selectByStuCouIdRand(stuAndCouId);

		societyStudentAndCourseViewSecond.setFaceList(faceRandomList);
		SocietyStudentCertificateView societyStudentCertificateView
				= societyStudentCertificateService.selectByStuIdAndCourId(studentId,courseId);
		societyStudentAndCourseViewSecond.setStudyProve(societyStudentCertificateView.getCertificateCode());
		map.put("third",societyStudentAndCourseViewSecond);
		//????????? ????????????
		SocietyStudentAndCourseView societyStudentAndCourseViewThird = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewThird.setStudyProve(societyStudentCertificateView.getCertificateCode());
		societyStudentAndCourseViewThird.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseViewThird.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseViewThird.setSchoolName(societySchool.getSchoolName());
		societyStudentAndCourseViewThird.setClassName(societySchoolClass.getClassName());
		societyStudentAndCourseViewThird.setClassTimePeriod(getDatePeriodSecond(societySchoolClass.getClassStartTime(),
				societySchoolClass.getClassEndTime()));
		societyStudentAndCourseViewThird.setCourseName(societySchoolCourse.getCourseName());
		Integer studyLength = societyStudentAndNodeService.countStudyLength(stuAndCouId)/60;
//		Integer questionLength = societyStudentPractiseQuestionService.countQuestionLength(stuAndCouId)/60;
		societyStudentAndCourseViewThird.setStudyTimeLength(studyLength);
//		societyStudentAndCourseViewThird.setQuestionTimeLength(questionLength);
		societyStudentAndCourseViewThird.setTotalTimeLength(studyLength);
		societyStudentAndCourseViewThird.setCertificateTime(societyStudentCertificateView.getCertificateTime());
		map.put("fourth",societyStudentAndCourseViewThird);

		//????????? ??????
		SocietyStudentAndCourseView societyStudentAndCourseViewFourth = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewFourth.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseViewFourth.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseViewFourth.setCourseName(societySchoolCourse.getCourseName());
		List<SocietySchoolCourseNodeView> nodeList = societySchoolCourseNodeService.selectByCourseId(courseId);
		societyStudentAndCourseViewFourth.setNodeList(nodeList);
		map.put("fifth",societyStudentAndCourseViewFourth);

		return buildResponse(modelAndView,map);
	}

	/**
	 * ????????????
	 * @param stuAndCouId
	 * @return
	 */
	@Pass
	@RequestMapping(value = "/tempFilePdfNew",method = RequestMethod.POST)
	public ModelAndView tempFilePdfNew(String stuAndCouId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		//??????????????????8
		SocietyStudentAndCourse societyStudentAndCourse = service.loadById(stuAndCouId);
		String studentId = societyStudentAndCourse.getStudentId();
		String courseId = societyStudentAndCourse.getCourseId();
		//????????????
		SocietyStudent societyStudent = societyStudentService.loadById(societyStudentAndCourse.getStudentId());
		//????????????
		SocietySchool societySchool = societySchoolService.loadById(societyStudentAndCourse.getOwnerSchoolId());
		//????????????
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(societyStudentAndCourse.getCourseId());
		//????????????
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(societyStudentAndCourse.getClassId());

		SocietyStudentCertificateView societyStudentCertificateView
				= societyStudentCertificateService.selectByStuIdAndCourId(studentId,courseId);
		//????????? ????????????
		SocietyStudentAndCourseView societyStudentAndCourseViewThird = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewThird.setStudyProve(societyStudentCertificateView.getCertificateCode());
		societyStudentAndCourseViewThird.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseViewThird.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseViewThird.setSchoolName(societySchool.getSchoolName());
		societyStudentAndCourseViewThird.setClassName(societySchoolClass.getClassName());
		societyStudentAndCourseViewThird.setClassTimePeriod(getDatePeriodSecond(societySchoolClass.getClassStartTime(),
				societySchoolClass.getClassEndTime()));
		societyStudentAndCourseViewThird.setCourseName(societySchoolCourse.getCourseName());
		Integer studyLength = societyStudentAndNodeService.countStudyLength(stuAndCouId)/60;
//		Integer questionLength = societyStudentPractiseQuestionService.countQuestionLength(stuAndCouId)/60;
		societyStudentAndCourseViewThird.setStudyTimeLength(studyLength);
//		societyStudentAndCourseViewThird.setQuestionTimeLength(questionLength);
		societyStudentAndCourseViewThird.setTotalTimeLength(studyLength);
		societyStudentAndCourseViewThird.setCertificateTime(societyStudentCertificateView.getCertificateTime());
		map.put("fourth",societyStudentAndCourseViewThird);

		//????????? ??????
		SocietyStudentAndCourseView societyStudentAndCourseViewFourth = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewFourth.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseViewFourth.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseViewFourth.setCourseName(societySchoolCourse.getCourseName());
		List<SocietySchoolCourseNodeView> nodeList = societySchoolCourseNodeService.selectByCourseId(courseId);
		societyStudentAndCourseViewFourth.setNodeList(nodeList);
		map.put("fifth",societyStudentAndCourseViewFourth);
		map.put("conName",societySchool.getContactPerson());
		return buildResponse(modelAndView,map);
	}


	public String getDatePeriod(Date start,Date end){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startStr = simpleDateFormat.format(start);
		String endStr = simpleDateFormat.format(end);
		return startStr+"/"+endStr;
	}

	public String getDatePeriodSecond(Date start,Date end){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd???");
		String startStr = simpleDateFormat.format(start);
		String endStr = simpleDateFormat.format(end);
		return startStr+"&nbsp;???&nbsp;"+endStr;
	}

	public String getDatePeriodThird(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startStr = simpleDateFormat.format(date);
		return startStr;
	}

	@RequestMapping(value = "/studentTrainCheckPDFInfo",method = RequestMethod.POST)
	public ModelAndView studentTrainCheckPDFInfo(String classId){
		ModelAndView modelAndView = new ModelAndView();
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		SocietySchool societySchool = societySchoolService.loadById(societySchoolClass.getOwnerSchoolId());
		//1.????????????id ??????????????????????????????
		List<String> studentIdList = societySchoolClassAndStudentService.selectByClassIdRandTenStudent(classId);
		List<SocietyStudentAndCourseView> stuCouList = new ArrayList<>();

		//3.??????????????????
		for(String studentId : studentIdList){
			SocietyStudentAndCourse societyStudentAndCourse =
					service.selectByStudentIdAndClassId(societySchoolClass.getId(),studentId,
							societySchoolClass.getOwnerSchoolId());
			SocietyStudentAndCourseView societyStudentAndCourseView = new SocietyStudentAndCourseView();
//			??????
			societyStudentAndCourseView.setStudentName(societyStudentAndCourse.getStudentName());
//			????????????
			societyStudentAndCourseView.setStudentIdCardNum(societyStudentAndCourse.getStudentIdCardNum());
//			????????????
			societyStudentAndCourseView.setClassName(societySchoolClass.getClassName());
//			????????????
			societyStudentAndCourseView.setSchoolName(societySchoolClass.getOwnerSchoolName());
//			????????????
			societyStudentAndCourseView.setCourseName(societyStudentAndCourse.getCourseName());
//			????????????
			SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(
					societyStudentAndCourse.getCourseId());
			societyStudentAndCourseView.setTotalLessons(societySchoolCourse.getTotalLessons());
//			????????????
			societyStudentAndCourseView.setFinishNodeNum(societyStudentAndCourse.getFinishNodeNum());
//			????????????????????????
			Integer studyLength = societyStudentAndNodeService.selectByStuCouIdCountStudyLength(societyStudentAndCourse.getId());
			societyStudentAndCourseView.setStudyTimeLength(studyLength);
			//			????????????????????????
			SocietyStudentAndNode societyStudentAndNode =
					societyStudentAndNodeService.selectByLastStudyTime(societyStudentAndCourse.getId());
			if(societyStudentAndNode.getFinishTime()==null){
				societyStudentAndCourseView.setLastTime("???");
			}else {
				societyStudentAndCourseView.setLastTime(getDatePeriodThird(societyStudentAndNode.getFinishTime()));
			}
//			????????????
			BigDecimal courseProgress = societyStudentAndCourse.getCourseProgress();
			if(courseProgress.compareTo(new BigDecimal(100)) == 0){
				societyStudentAndCourseView.setStudySituation("?????????");
			}else {
				societyStudentAndCourseView.setStudySituation(courseProgress+"%");
			}
//			??????????????????
			//			????????????
//			????????????
			List<SocietyStudentTestPaper> societyStudentTestPaperList =
					societyStudentTestPaperService.selectByStuIdAndCourseIdAndSchoolIdList(studentId,
							societyStudentAndCourse.getId(),societySchoolClass.getOwnerSchoolId());
			if(societyStudentTestPaperList.size()==0){
				societyStudentAndCourseView.setIsTepar("?????????");
				societyStudentAndCourseView.setTestScore(0);
				societyStudentAndCourseView.setTestNum(0);
			}else {
				SocietyStudentTestPaper societyStudentTestPaper = societyStudentTestPaperList.get(0);
				if("1".equals(societyStudentTestPaper.getIsPass())){
					societyStudentAndCourseView.setIsTepar("???");
				}else {
					societyStudentAndCourseView.setIsTepar("???");
				}
				societyStudentAndCourseView.setTestScore(societyStudentTestPaper.getGetScore());
				societyStudentAndCourseView.setTestNum(societyStudentTestPaperList.size());
			}
			stuCouList.add(societyStudentAndCourseView);
		}
		//?????????
		User user = getLoginUser();
		Map<String,Object> map = new HashMap<>();
		map.put("list",stuCouList);
		map.put("opeName",user.getUserName());
		map.put("schoolCompanyName","???????????????"+societySchool.getSchoolName());
		return buildResponse(modelAndView,map);
	}


}
