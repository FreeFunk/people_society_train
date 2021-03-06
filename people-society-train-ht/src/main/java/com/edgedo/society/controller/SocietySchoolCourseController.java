package com.edgedo.society.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
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
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.Dtree;
import com.edgedo.tyiyunoosclient.ISysTyiyunCloudStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
	@Autowired
	private SocietyNodeResourcesService societyNodeResourcesService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	@Autowired
	private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;

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


	@RequestMapping(value = "/countCourseInfo",method = RequestMethod.POST)
	public ModelAndView countCourseInfo(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		if(query.getQueryObj().getMonth()==null){
			Date data = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			query.getQueryObj().setMonth(simpleDateFormat.format(data));
		}
		//1.?????????????????????????????????
		//2.???????????????????????????????????????????????????
		//3.?????????????????????
		query.getQueryObj().setOwnerSchoolCourseId(schoolId);
		service.classCourseListPage(query);
		List<SocietySchoolCourseView> courseList = query.getList();
		//4.??????????????????????????????
		if (!("".equals(query.getQueryObj().getMonth()))){
			String month = query.getQueryObj().getMonth();
			String monthStartDay = getFirstDayOfMonth(month);
			String monthEndDay = getLastDayOfMonth(month);
			courseList.forEach(societySchoolCourseView -> {
				//1.??????stuAndcou
				int completNum =
						societyStudentAndCourseService.selectByCourseIdAndTime(societySchoolCourseView.getId(),monthStartDay,monthEndDay);
				societySchoolCourseView.setSchoolCompleteCount(completNum);
			});
		}else if(!("".equals(query.getQueryObj().getYear()))){
			String year = query.getQueryObj().getYear();
			String yearDayFirst = getYearFirst(new Integer(year));
			String yearDayEnd = getYearLast(new Integer(year));
			courseList.forEach(societySchoolCourseView -> {
				//1.??????stuAndcou
				int completNum =
						societyStudentAndCourseService.selectByCourseIdAndTime(societySchoolCourseView.getId(),yearDayFirst,yearDayEnd);
				societySchoolCourseView.setSchoolCompleteCount(completNum);
			});
		}else {
			courseList.forEach(societySchoolCourseView -> {
				//1.??????stuAndcou
				int completNum =
						societyStudentAndCourseService.selectByCourseIdAndTimeNew(societySchoolCourseView.getId());
				societySchoolCourseView.setSchoolCompleteCount(completNum);
			});
		}

		query.setList(courseList);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ????????????
	 * @param subsidyMoney  ????????????
	 * @param ownerCourseId ?????????id
	 * @param ownerSchoolId ?????????????????????id???????????????????????????id???
	 * @return
	 */
	@RequestMapping(value = "/printDetailedInfoPDF",method = RequestMethod.POST)
	public ModelAndView countCprintDetailedInfoPDFourseInfo(String subsidyMoney,String ownerCourseId,String ownerSchoolId){
		ModelAndView modelAndView = new ModelAndView();
		//????????????????????????
		SocietySchoolCourse societySchoolCourseNew =
				service.selectBySchoolIdAndOwnerCourseId(ownerSchoolId,ownerCourseId);
		//????????????????????????
		SocietySchoolCourse societySchoolCourseOld = service.loadById(ownerCourseId);
		SocietySchoolCourseView societySchoolCourseView = new SocietySchoolCourseView();
		//1.?????? ????????????
		societySchoolCourseView.setFileIdentification("?????????????????????????????????:");
		societySchoolCourseView.setId("?????????"+Guid.guid());
		//????????????
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd???");
		societySchoolCourseView.setIsTimeStr("???????????????"+simpleDateFormat.format(date));
		//2.???????????? ????????????
		societySchoolCourseView.setPaymentSchoolName(societySchoolCourseNew.getOwnerSchoolName());
		societySchoolCourseView.setCollectionSchoolName(societySchoolCourseOld.getOwnerSchoolName());
		//3.?????????????????????????????????
		List<SocietySchoolClassAndCourseView> classCourseList =
				classAndCourseService.selectBySchoolIdAndCourseId(ownerSchoolId,societySchoolCourseNew.getId());

		Integer totalMoney = new Integer(0);
		for (SocietySchoolClassAndCourseView societySchoolClassAndCourseView : classCourseList){
			//????????????
			societySchoolClassAndCourseView.setCourseName(societySchoolCourseOld.getCourseName());
			//????????????
			societySchoolClassAndCourseView.setContractRegulations("???????????????????????????????????????????????????????????????????????????5%?????????");
			//????????????
			SocietySchoolClass societySchoolClass =
					societySchoolClassService.loadById(societySchoolClassAndCourseView.getClassId());
			societySchoolClassAndCourseView.setClassName(societySchoolClass.getClassName());
			//????????????
			societySchoolClassAndCourseView.setSubsidyStandard(subsidyMoney);
			//????????????
			Integer personNum = societySchoolClassAndStudentService.selectCountByClassId(societySchoolClass.getId());
			societySchoolClassAndCourseView.setClassStudyNum(personNum);
			//????????????
			Integer totalStudentMoney = new Integer(subsidyMoney)*personNum;

			Double technologyMoney = totalStudentMoney*0.05;
			societySchoolClassAndCourseView.setTechnologyMoney(technologyMoney.toString());
			//????????????
			societySchoolClassAndCourseView.setServiceMoney(technologyMoney.toString());
			totalMoney += new Integer((technologyMoney.intValue())*2);
		}

		societySchoolCourseView.setClassCourseList(classCourseList);
		//??????
		societySchoolCourseView.setTotalMoney(totalMoney.toString());
		//????????????
		String moneyStr = "???"+totalMoney+"???????????????"+getMoneyChiness(totalMoney.toString())+"?????????";
		societySchoolCourseView.setPaymentMoney(moneyStr);
		buildResponse(modelAndView,societySchoolCourseView);
		return modelAndView;
	}

	public String getMoneyChiness(String NumStr){
		String HanDigiStr[] = new String[] { "???", "???", "???", "???", "???", "???",
				"???", "???", "???", "???" };
		String HanDiviStr[] = new String[] { "", "???", "???", "???", "???", "???",
				"???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???",
				"???", "???", "???", "???", "???" };

		String RMBStr = "";
		boolean lastzero = false;
		boolean hasvalue = false; // ?????????????????????????????????
		int len, n;
		len = NumStr.length();
		if (len > 15){
			return "????????????!";
		}
		for (int i = len - 1; i >= 0; i--) {
			if (NumStr.charAt(len - i - 1) == ' '){
				continue;
			}

			n = NumStr.charAt(len - i - 1) - '0';
			if (n < 0 || n > 9){
				return "????????????????????????!";
			}


			if (n != 0) {
				if (lastzero){
					// ????????????????????????????????????????????????
					RMBStr += HanDigiStr[0];
				}

				// ????????????????????????????????????
				// if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) ) //
				// ?????????????????????????????????????????????
				if (!(n == 1 && (i % 4) == 1 && i == len - 1)){
					// ????????????????????????????????????
					RMBStr += HanDigiStr[n];
				}

				RMBStr += HanDiviStr[i]; // ????????????????????????????????????
				hasvalue = true; // ???????????????????????????

			} else {
				if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)){
					RMBStr += HanDiviStr[i];
				}
			}
			if (i % 8 == 0){
				// ????????????????????????????????????
				hasvalue = false;
			}
			lastzero = (n == 0) && (i % 4 != 0);
		}

		if (RMBStr.length() == 0){
			// ??????????????????"0"?????????"???"
			return HanDigiStr[0];
		}
		return RMBStr;
	}

	/**
	 * ???????????????????????????
	 * @param year ??????
	 * @return Date
	 */
	public String getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currYearFirstStr = sdf.format(currYearFirst)+" 00:00:00";
		return currYearFirstStr;
	}

	/**
	 * ??????????????????????????????
	 * @param year ??????
	 * @return Date
	 */
	public String getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		// ???????????????
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currYearLastStr = sdf.format(currYearLast)+" 23:59:59";
		return currYearLastStr;
	}

	/**
	 * ????????????????????????
	 * @param month
	 * @return
	 */
	public String getFirstDayOfMonth(String month) {
		String[] monthStr = month.split("-");
		Calendar calendar = Calendar.getInstance();
		// ????????????
		calendar.set(Calendar.MONTH, new Integer(monthStr[1]) - 1);
		// ????????????????????????
		int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		// ????????????????????????????????????
		calendar.set(Calendar.DAY_OF_MONTH, firstDay);
		// ???????????????
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayStr = sdf.format(calendar.getTime())+" 00:00:00";
		return firstDayStr;
	}

	public String getLastDayOfMonth(String monthStr) {
		String[] monthTimeStr = monthStr.split("-");
		Calendar calendar = Calendar.getInstance();
		// ????????????
		Integer month =  new Integer(monthTimeStr[1]);
		calendar.set(Calendar.MONTH, month - 1);
		// ????????????????????????
		int lastDay=0;
		//2????????????????????????
		if(month==2) {
			lastDay = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
		}else {
			lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		// ????????????????????????????????????
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		// ???????????????
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayStr = sdf.format(calendar.getTime())+" 23:59:59";
		return lastDayStr;
	}

	@RequestMapping(value = "/listpageSchoolCourseSituationSchoolAdmin",method = RequestMethod.POST)
	public ModelAndView listpageSchoolCourseSituationSchoolAdmin(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//????????????????????? ?????????????????????????????? ???????????? ????????????????????????????????????
		User user = getLoginUser();
		query.getQueryObj().setNowSchoolId(user.getCompId());

		//??????????????????????????????
		service.listPageCourseGlobleNew(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ?????? ?????????????????????
	 * @param ids
	 * @param isOpen
	 * @return
	 */
	@RequestMapping(value = "/updateByIsOpen",method = RequestMethod.POST)
	public ModelAndView updateByIsOpen(String ids,String isOpen){
		ModelAndView modelAndView = new ModelAndView();
		service.updateByIsOpen(ids,isOpen);
		buildResponse(modelAndView);
		return modelAndView;
	}

	/**
	 * ??????????????????id ????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/courseAllCount",method = RequestMethod.POST)
	public ModelAndView courseAllCount(@ModelAttribute SocietySchoolCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolCourseId(user.getCompId());
		if(query.getQueryObj().getMonth()==null || query.getQueryObj().getMonth().equals("")){
			//??????????????????
			String startDay = getMonthFirstDay(new Date());
			String endDay = getMonthLastDay(new Date());
			query.getQueryObj().setMonthDayStart(startDay);
			query.getQueryObj().setMonthDayEnd(endDay);
		}else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			String startDay = null;
			String endDay = null;
			try {
				startDay = getMonthFirstDay(simpleDateFormat.parse(query.getQueryObj().getMonth()));
				endDay = getMonthLastDay(simpleDateFormat.parse(query.getQueryObj().getMonth()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			query.getQueryObj().setMonthDayStart(startDay);
			query.getQueryObj().setMonthDayEnd(endDay);
		}
		service.listPageCourseCountAll(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ???????????????????????????????????????
	 *
	 * @param nowTime
	 * @return
	 */
	private static String getMonthLastDay(Date nowTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.setTime(nowTime);
		now.add(Calendar.DATE, -1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(nowTime);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(simpleDateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last);
		day_last = endStr.toString();
		StringBuffer str = new StringBuffer().append(day_last);
		return str.toString();

	}



	/**
	 * ????????????????????????????????????
	 *
	 * @param nowTime
	 * @return
	 */
	private static String getMonthFirstDay(Date nowTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowTime);
		Date theDate = calendar.getTime();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();
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
			String courseId = Guid.guid();
			voObj.setId(courseId);
			//?????????????????????????????????????????????
			voObj.setOwnerSchoolCourseId(compId);
			voObj.setOwnerCourseName(voObj.getCourseName());
			voObj.setOwnerCourseId(courseId);
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
	 * ???????????????  ?????????????????????
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/joinToMyCourse",method = RequestMethod.POST)
	public ModelAndView joinToMyCourse(String ids){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		service.joinToMyCourse(user,ids);
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
		SocietySchoolCourse societySchoolCourse = service.loadById(ids);
		//????????????????????????????????????
		if ("0".equals(societySchoolCourse.getIsOpen())){
			//?????????
//			societyNodeResourcesService.updateById(societySchoolCourse.getOwnerCourseId(),societySchoolCourse.getOwnerSchoolId());
			//?????????
			nodeService.updateByDataStateId(societySchoolCourse.getId());
			service.updateByIdNew(ids);
			return buildResponse(modelAndView);
		}else {
			int count = nodeService.countByCourseId(ids);
			if(count>0){
				return buildErrorResponse(modelAndView,"???????????????????????????????????????");
			}else {
				//?????????????????? ????????????
				int flag = service.selectByOwnerCourseId(ids);
				if (flag>0){
					return buildErrorResponse(modelAndView,"??????????????????????????????????????????????????????????????????");
				}else {
					service.updateByIdNew(ids);
					return buildResponse(modelAndView);
				}
			}
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
