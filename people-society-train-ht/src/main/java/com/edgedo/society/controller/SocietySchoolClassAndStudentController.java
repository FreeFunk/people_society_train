package com.edgedo.society.controller;


import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
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


@Api(tags = "SocietySchoolClassAndStudent")
@Controller
@RequestMapping("/society/societySchoolClassAndStudent")
public class SocietySchoolClassAndStudentController extends BaseController{
	
	@Autowired
	private SocietySchoolClassAndStudentService service;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolClassAndCourseService societySchoolClassAndCourseService;
	@Autowired
	private SocietySchoolClassAdminService societySchoolClassAdminService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	@Autowired
	private SocietySchoolClassGroupAdminService societySchoolClassGroupAdminService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;
	@RequestMapping(value = "/tempFilePdfZip",method = RequestMethod.POST)
	public ModelAndView tempFilePdfZip(String classId){
		ModelAndView modelAndView = new ModelAndView();
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(classId);
		List<SocietyStudentAndCourseView> cerList = societyStudentAndCourseService.selectByClassId(classId);
		String courseId = societySchoolClassAndCourseService.selectByClassId(classId);
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		List<SocietyStudentCertificateView> list = new ArrayList<>();
		Integer courseTotal =  societySchoolCourse.getTotalLessons();
		Date startTime = societySchoolClass.getClassStartTime();
		Date endTime = societySchoolClass.getClassEndTime();
		cerList.forEach(stu->{
			if ("1".equals(stu.getLearnIsFinished())){
				SocietyStudentCertificateView societyStudentCertificateView =
						societyStudentCertificateService.selectByStuIdAndCourId(stu.getStudentId(),stu.getCourseId());
				societyStudentCertificateView.setTimeStr(getDatePeriodSecond(startTime,endTime));
				societyStudentCertificateView.setCourseTotal(courseTotal);
				list.add(societyStudentCertificateView);
			}
		});
		return buildResponse(modelAndView,list);
	}


	/**
	 * ????????????????????????????????????
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/studyExamine",method = RequestMethod.POST)
	public ModelAndView studyExamine(String classId){
		ModelAndView modelAndView = new ModelAndView();
		//????????????
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(classId);
		//????????????
		SocietySchool societySchool = societySchoolService.loadById(societySchoolClass.getOwnerSchoolId());
		String courseId = societySchoolClassAndCourseService.selectByClassId(classId);
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		SocietySchoolClassView societySchoolClassViewFisrt = new SocietySchoolClassView();
		societySchoolClassViewFisrt.setSchoolName(societySchool.getContactPerson());
		//????????????
		societySchoolClassViewFisrt.setOwnerSchoolName(societySchool.getSchoolName());
		//????????????
		societySchoolClassViewFisrt.setClassName(societySchoolClass.getClassName());
		//????????????
		societySchoolClassViewFisrt.setClassCode(societySchoolClass.getClassCode());
		//??????
		societySchoolClassViewFisrt.setCreateTimeStr(getDatePeriodNew(new Date()));
		//????????????
		String proName = societySchoolClass.getProvinceName();
		String xianquName = societySchoolClass.getXianquName();
		if (proName==null || xianquName==null){
			String area = societySchool.getProvinceName()+"-"+societySchool.getCityName()+"-"+societySchool.getXianquName();
			societySchoolClassViewFisrt.setXianquName(area);
		}else {
			String area = societySchoolClass.getProvinceName()+"-"+societySchoolClass.getCityName()+"-"+societySchoolClass.getXianquName();
			societySchoolClassViewFisrt.setXianquName(area);
		}
		//????????????
		societySchoolClassViewFisrt.setStudyTime(getDatePeriodSecond(societySchoolClass.getClassStartTime(),societySchoolClass.getClassEndTime()));
		//????????????
		societySchoolClassViewFisrt.setStudyType(societySchoolCourse.getOwnerMajorName());
		//????????????
		societySchoolClassViewFisrt.setStudyLevel(subString(societySchoolCourse.getCourseName(),"???","???"));
		//????????????
		societySchoolClassViewFisrt.setCourseTotalNum(societySchoolCourse.getTotalLessons());
		//????????????
		societySchoolClassViewFisrt.setStudyPersonNum(service.selectCountByClassId(classId));
		//????????????
		List<SocietyStudentAndCourseView> stuCouFinishList = societyStudentAndCourseService.selectByClassIdVoFinish(classId);
		societySchoolClassViewFisrt.setClassUseNum(stuCouFinishList.size());
		societySchoolClassViewFisrt.setPeopleType("????????????");
		societySchoolClassViewFisrt.setPersonInCharge(societySchoolClass.getPersonInCharge());
		societySchoolClassViewFisrt.setPersonInChargePhoneNum(societySchoolClass.getPersonInChargePhoneNum());
		return buildResponse(modelAndView,societySchoolClassViewFisrt);
	}


	/**
	 * ????????????????????????
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/studentCountSituation",method = RequestMethod.POST)
	public ModelAndView studentCountSituation(String classId){
		ModelAndView modelAndView = new ModelAndView();
		//????????????
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(classId);
		//????????????
		SocietySchool societySchool = societySchoolService.loadById(societySchoolClass.getOwnerSchoolId());
		String courseId = societySchoolClassAndCourseService.selectByClassId(classId);
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		Map<String,Object> map = new HashMap<>();
		SocietySchoolClassView societySchoolClassViewFisrt = new SocietySchoolClassView();
		societySchoolClassViewFisrt.setOwnerSchoolName("????????????(??????)???"+societySchool.getSchoolName());
		map.put("conName",societySchool.getContactPerson());
		//????????????
		societySchoolClassViewFisrt.setPlatformName(societySchool.getPlatformName());
		//????????????
		societySchoolClassViewFisrt.setClassName(societySchoolClass.getClassName());
		//????????????
		societySchoolClassViewFisrt.setClassCode(societySchoolClass.getClassCode());
		//????????????
		societySchoolClassViewFisrt.setCreateTimeStr("????????????:"+getDatePeriod(new Date()));
		//????????????
		societySchoolClassViewFisrt.setStudyTime(getDatePeriodSecond(societySchoolClass.getClassStartTime(),societySchoolClass.getClassEndTime()));
		//????????????
		societySchoolClassViewFisrt.setStudyType(societySchoolCourse.getOwnerMajorName());
		//????????????
		societySchoolClassViewFisrt.setStudyLevel(subString(societySchoolCourse.getCourseName(),"???","???"));
		//????????????
		societySchoolClassViewFisrt.setCourseTotalNum(societySchoolCourse.getTotalLessons());
		//????????????
		societySchoolClassViewFisrt.setStudyPersonNum(service.selectCountByClassId(classId));
		//????????????
		List<SocietyStudentAndCourseView> stuCouFinishList = societyStudentAndCourseService.selectByClassIdVoFinish(classId);
		//???????????????
		List<SocietyStudentAndCourseView> stuCouNoFinishList = societyStudentAndCourseService.selectByClassIdVoNoFinish(classId);
		societySchoolClassViewFisrt.setClassUseNum(stuCouFinishList.size());
		map.put("first",societySchoolClassViewFisrt);

		stuCouFinishList.forEach(stuCou->{
			//?????????
			SocietyStudent societyStudent = studentService.loadById(stuCou.getStudentId());
			stuCou.setStuPhone(societyStudent.getStudentPhoneNum());
			stuCou.setSex(societyStudent.getStudentSex());
		});

		map.put("second",stuCouFinishList);
		if (stuCouNoFinishList.size()!=0){
			stuCouNoFinishList.forEach(stuCou->{
				//?????????
				SocietyStudent societyStudent = studentService.loadById(stuCou.getStudentId());
				stuCou.setStuPhone(societyStudent.getStudentPhoneNum());
				stuCou.setSex(societyStudent.getStudentSex());
			});
		}
		map.put("third",stuCouNoFinishList);
		return buildResponse(modelAndView,map);
	}

	/**
	 * ???????????????str??????????????? strStart???strEnd??????????????????
	 * @return
	 */
	public String subString(String str, String strStart, String strEnd) {

		/* ???????????????2???????????? ????????????????????? ?????? */
		int strStartIndex = str.indexOf(strStart);
		int strEndIndex = str.indexOf(strEnd);

		/* index ????????? ???????????????????????? ??????????????? */
		if (strStartIndex < 0) {
			return "??????";
		}
		if (strEndIndex < 0) {
			return "??????";
		}
		/* ???????????? */
		String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
		return result;
	}

	public String getDatePeriod(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startStr = simpleDateFormat.format(date);
		return startStr;
	}
	public String getDatePeriodNew(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd???");
		String startStr = simpleDateFormat.format(date);
		return startStr;
	}

	public String getDatePeriodSecond(Date start,Date end){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startStr = simpleDateFormat.format(start);
		String endStr = simpleDateFormat.format(end);
		return startStr+"&nbsp;???&nbsp;"+endStr;
	}
	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietySchoolClassAndStudent", notes = "????????????SocietySchoolClassAndStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ????????????id ?????????id ???????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByStudentIdAllClassAndCourse",method = RequestMethod.POST)
	public ModelAndView selectByStudentIdAllClassAndCourse(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		String classId = schoolClassService.selectByClassAdminIdOnceClass(classAdminId);
		query.getQueryObj().setClassId(classId);
		service.selectByStuIdAndSchoolIdlistPage(query);
		List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
		for(SocietySchoolClassAndStudentView view : classAndcourseList){
			SocietyStudentAndCourse societyStudentAndCourse =
					societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),user.getCompId());
			if(societyStudentAndCourse == null){
				continue;
			}else {
				view.setCourseId(societyStudentAndCourse.getCourseId());
				view.setCourseAndStudentId(societyStudentAndCourse.getId());
				view.setCourseName(societyStudentAndCourse.getCourseName());
				view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
			}
		}
		query.setList(classAndcourseList);
		return buildResponse(modelAndView,query);
	}


	@RequestMapping(value = "/selectByStudentIdAllClassAndCourseGroup",method = RequestMethod.POST)
	public ModelAndView selectByStudentIdAllClassAndCourseGroup(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		String groupId = societySchoolClassGroupAdminService.selectBySysUserId(user.getUserId());
		List<String> classAdminId = societySchoolClassAdminService.selectByGroupId(groupId);
		List<String> classId = schoolClassService.selectByClassAdminIdList(classAdminId);
		query.getQueryObj().setClassIdList(classId);
		service.selectByStuIdAndSchoolIdlistPage(query);
		List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
		for(SocietySchoolClassAndStudentView view : classAndcourseList){
			SocietyStudentAndCourse societyStudentAndCourse =
					societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),user.getCompId());
			if(societyStudentAndCourse == null){
				continue;
			}else {
				view.setCourseId(societyStudentAndCourse.getCourseId());
				view.setCourseAndStudentId(societyStudentAndCourse.getId());
				view.setCourseName(societyStudentAndCourse.getCourseName());
				view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
			}
		}
		query.setList(classAndcourseList);
		return buildResponse(modelAndView,query);
	}

	/**
	 * ????????????
	 * ????????????id  ???????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByStudentIdAllClassAndCourseRenShe",method = RequestMethod.POST)
	public ModelAndView selectByStudentIdAllClassAndCourseRenShe(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		service.selectByStuIdAndSchoolIdlistPage(query);
		List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
		for(SocietySchoolClassAndStudentView view : classAndcourseList){
			SocietyStudentAndCourse societyStudentAndCourse =
					societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),view.getOwnerSchoolId());
			if(societyStudentAndCourse == null){
				continue;
			}else {
				view.setCourseId(societyStudentAndCourse.getCourseId());
				view.setCourseAndStudentId(societyStudentAndCourse.getId());
				view.setCourseName(societyStudentAndCourse.getCourseName());
				view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
			}
		}
		query.setList(classAndcourseList);
		return buildResponse(modelAndView,query);
	}


	/**
	 * ????????????????????????????????????
	 * ?????????????????????id
	 * ????????????id ?????????id ???????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByClassAdminStudentIdAllClassAndCourse",method = RequestMethod.POST)
	public ModelAndView selectByClassAdminStudentIdAllClassAndCourse(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		//???????????????id
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classIdlist = schoolClassService.selectByClassAdminId(classAdminId);
		if(classIdlist.size()!=0){
			query.setClassAdminId(classIdlist);
			service.selectByClassIdStuIdAndSchoolIdlistPage(query);
			List<SocietySchoolClassAndStudentView> classAndcourseList = query.getList();
			for(SocietySchoolClassAndStudentView view : classAndcourseList){
				SocietyStudentAndCourse societyStudentAndCourse =
						societyStudentAndCourseService.selectByStudentIdAndClassId(view.getClassId(),view.getStudentId(),user.getCompId());
				if(societyStudentAndCourse == null){
					continue;
				}else {
					view.setCourseAndStudentId(societyStudentAndCourse.getId());
					view.setCourseName(societyStudentAndCourse.getCourseName());
					view.setCourseProgress(societyStudentAndCourse.getCourseProgress());
				}
			}
			query.setList(classAndcourseList);
			return buildResponse(modelAndView,query);
		}else {
			return buildResponse(modelAndView,query);
		}
	}


	/**
	 * ???????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listByObj",method = RequestMethod.POST)
	public ModelAndView listByObj(@ModelAttribute SocietySchoolClassAndStudentQuery query){
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
	@RequestMapping(value = "/listByObjForSchool",method = RequestMethod.POST)
	public ModelAndView listByObjForSchool(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		Map<String,String> map = societySchoolService.selectBySchoolAdminId(user.getUserId());
		query.getQueryObj().setOwnerSchoolId(map.get("ID"));
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBySchoolAndClassBySchoolId",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClassBySchoolId(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> list = schoolClassService.selectByClassAdminId(classAdminId);
		query.setClassAdminId(list);
		service.selectBySchoolNamelistPage(query);
		List<SocietySchoolClassAndStudentView> classAndStuList = query.getList();
		for(SocietySchoolClassAndStudentView societySchoolClassAndStudentView : classAndStuList){
			String courseAndStudentId = societyStudentAndCourseService.selectStuAndClassId(societySchoolClassAndStudentView.getClassId(),
					societySchoolClassAndStudentView.getStudentId());
			societySchoolClassAndStudentView.setCourseAndStudentId(courseAndStudentId);
		}
		query.setList(classAndStuList);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ???????????????????????????????????????????????????
	 */
	@RequestMapping(value = "/selectBySchoolAndClassBySchoolIdAndClassId",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClassBySchoolIdAndClassId(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classId = schoolClassService.selectByClassAdminId(classAdminId);
		query.setClassAdminId(classId);
		service.selectBySchoolAndClassBySchoolIdAndClassIdlistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	//??????id ??????name -> ??????id ??????id -> ??????id ??????id  ??????id ??????id
	//					??????id ??????id
	@RequestMapping(value = "/selectBySchoolName",method = RequestMethod.POST)
	public ModelAndView selectBySchoolName(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectBySchoolNamelistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBySchoolAndClass",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClass(@ModelAttribute SocietySchoolClassAndStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectBySchoolNamelistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????????????????
	 * @Date 2020/5/4 14:01
	 **/
	@RequestMapping(value = "/insertClassAndStudentRelation",method = RequestMethod.POST)
	public ModelAndView insertClassAndStudentRelation(String stuIds,String classId){
		ModelAndView modelAndView = new ModelAndView();
		SocietySchoolClass schoolClass = schoolClassService.loadById(classId);
		if(schoolClass == null){
			return buildErrorResponse(modelAndView,"????????????????????????");
		}
		//??????????????????
		String errMsg = service.insertClassAndStudentRelation(schoolClass,stuIds);
		if(errMsg != null && !errMsg.equals("")){
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("success", false);
			modelAndView.addObject("errMsg", errMsg);
			return modelAndView;
		}
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????????????????????????????
	 * @Date 2020/5/5 8:58
	 **/
	@RequestMapping(value = "/logicDeleteClassStuRelation",method = RequestMethod.POST)
	public ModelAndView logicDeleteClassStuRelation(String stuIds,String classId){
		ModelAndView modelAndView = new ModelAndView();
		SocietySchoolClass schoolClass = schoolClassService.loadById(classId);
		if(schoolClass == null){
			return buildErrorResponse(modelAndView,"???????????????????????????");
		}
		service.deleteByClassAndStudent(stuIds,classId);
		return buildResponse(modelAndView);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????ID???????????????????????????
	 *@DateTime: 2020/5/29 16:21
	 */
	@RequestMapping(value = "/oneClickFinish",method = RequestMethod.POST)
	public ModelAndView oneClickFinish(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		String errMsg = service.oneClickFinish(list);
		return buildResponse(modelAndView,errMsg);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????ID???????????????????????????
	 *@DateTime: 2020/5/29 16:21
	 */
	@RequestMapping(value = "/suiJiFinish",method = RequestMethod.POST)
	public ModelAndView suiJiFinish(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		String errMsg = service.suiJiFinish(list);
		return buildResponse(modelAndView,errMsg);
	}

	/**
	 * ????????????????????????
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/selectVoClassId",method = RequestMethod.POST)
	public ModelAndView selectVoClassId(String classId){
		ModelAndView modelAndView = new ModelAndView();
		int numStudent = service.selectVoClassId(classId);
		String courseId = societySchoolClassAndCourseService.selectByClassId(classId);
		Map<String,Object> map = new HashMap<>();
		map.put("numStudent",numStudent);
		map.put("courseId",courseId);
		return buildResponse(modelAndView,map);
	}

}
