package com.edgedo.society.controller;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.DateUtil;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.Dtree;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.SysUserView;
import com.edgedo.sys.queryvo.SysXianquQuery;
import com.edgedo.sys.service.SysUserService;
import com.edgedo.sys.service.SysXianquService;
import com.edgedo.tyiyunoosclient.ISysTyiyunCloudStorageService;
import com.microsoft.schemas.office.office.STInsetMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "SocietySchool")
@Controller
@RequestMapping("/society/societySchool")
public class SocietySchoolController extends BaseController{
	
	@Autowired
	private SocietySchoolService service;

	@Autowired
	private SysXianquService sysXianquService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private ISysTyiyunCloudStorageService sysTyiyunCloudStorageService;

	@Autowired
	private SocietySchoolMajorService societySchoolMajorService;

	@Autowired
	private SocietySchoolClassService societySchoolClassService;

	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;

	@Autowired
	private SocietyStudentService societyStudentService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;

	@Autowired
	private SocietySchoolCourseUseGlobleService societySchoolCourseUseGlobleService;


	@Value("${app.server.fileForder}")
	private String fileForder;
	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietySchool", notes = "????????????SocietySchool")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 *@Author:TWQ
	 *@Description:?????????????????????
	 *@DateTime: 2020-5-17 12:10:40
	 */
	@RequestMapping(value = "/listSchoolDtree",method = RequestMethod.POST)
	public ModelAndView listSchoolDtree(){
		ModelAndView modelAndView = new ModelAndView();
		List<Dtree> dtreeList = service.listSchoolDtree();
		return buildDtreeResponse(modelAndView,dtreeList);
	}


	/**
	 *@Author:QTZ
	 *@Description:?????????????????????
	 */
	@RequestMapping(value = "/listSchoolClassInfoDtree",method = RequestMethod.POST)
	public ModelAndView listSchoolClassInfoDtree(){
		ModelAndView modelAndView = new ModelAndView();
		List<Dtree> dtreeList = service.listSchoolClassInfoDtree();
		return buildDtreeResponse(modelAndView,dtreeList);
	}

	/**
	 * ?????????????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listForPeopleSocietyHome",method = RequestMethod.POST)
	public ModelAndView listForPeopleSocietyHome(@ModelAttribute SocietySchoolQuery query){
		ModelAndView modelAndView = new ModelAndView();
		Map map = new HashMap();
		//??????????????????id
		User user = getLoginUser();
		//????????????
		int schoolNum = service.countSchoolAllNum();
		//????????????
		int classNum = societySchoolClassService.countClassAllNum();
		//????????????
		int courseNum = societySchoolCourseService.countCourseAllNum();
		//????????????
		int studentNum = societyStudentService.countStudentAllNum();
		map.put("schoolNum",schoolNum);
		map.put("classNum",classNum);
		map.put("courseNum",courseNum);
		map.put("studentNum",studentNum);
		buildResponse(modelAndView,map);
		return modelAndView;
	}



	/**
	 * ????????????????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listForSupPeopleHome",method = RequestMethod.POST)
	public ModelAndView listForSupPeopleHome(@ModelAttribute SocietySchoolQuery query){
		ModelAndView modelAndView = new ModelAndView();
		Map map = new HashMap();
		//??????????????????id
		User user = getLoginUser();
		//??????????????????????????????????????????
		SysUser sysUser = sysUserService.loadById(user.getUserId());
		String xianquId = sysUser.getXianquId();
		//????????????
		int schoolNum = service.countXianQuSchoolAllNum(xianquId);
		//????????????
		int classNum = societySchoolClassService.countXianQuClassAllNum(xianquId);
		//????????????
		int studentNum = societyStudentService.countXianQuStudentAllNum(xianquId);
		map.put("schoolNum",schoolNum);
		map.put("classNum",classNum);
		map.put("studentNum",studentNum);
		buildResponse(modelAndView,map);
		return modelAndView;
	}

	/**
	 * ???????????????????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "???????????????????????????????????????????????????????????????", notes = "???????????????????????????????????????????????????????????????")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listForSchoolHome",method = RequestMethod.POST)
	public ModelAndView listForSchoolHome(@ModelAttribute SocietySchoolQuery query){
		ModelAndView modelAndView = new ModelAndView();
		Map map = new HashMap();
		//??????????????????id
		User user = getLoginUser();
		//????????????
		SocietySchoolMajorQuery societySchoolMajorQuery = new SocietySchoolMajorQuery();
		societySchoolMajorQuery.getQueryObj().setOwnerSchoolId(user.getCompId());
		societySchoolMajorQuery.getQueryObj().setDataState("1");
		int zy = societySchoolMajorService.count(societySchoolMajorQuery);
		//????????????
		SocietySchoolClassQuery societySchoolClassQuery = new SocietySchoolClassQuery();
		societySchoolClassQuery.getQueryObj().setOwnerSchoolId(user.getCompId());
		societySchoolClassQuery.getQueryObj().setDataState("1");
		int bj = societySchoolClassService.count(societySchoolClassQuery);
		//????????????
		SocietySchoolCourseQuery societySchoolCourseQuery = new SocietySchoolCourseQuery();
		societySchoolCourseQuery.getQueryObj().setOwnerSchoolId(user.getCompId());
		societySchoolCourseQuery.getQueryObj().setDataState("1");
		int kc = societySchoolCourseService.count(societySchoolCourseQuery);
		//????????????
		SocietyStudentQuery societyStudentQuery = new SocietyStudentQuery();
		societyStudentQuery.getQueryObj().setOwnerSchoolId(user.getCompId());
		societyStudentQuery.getQueryObj().setDataState("1");
		int xy = societyStudentService.count(societyStudentQuery);
		//????????????????????????
		int kcStuNum = societyStudentAndCourseService.countCourseStuNum(user.getCompId());


		map.put("zy",zy);
		map.put("bj",bj);
		map.put("kc",kc);
		map.put("xy",xy);
		map.put("kcStuNum",kcStuNum);
		buildResponse(modelAndView,map);
		return modelAndView;
	}

	@RequestMapping(value = "/schoolAdminListpage",method = RequestMethod.POST)
	public ModelAndView schoolAdminListpage(@ModelAttribute SocietySchoolQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????id
		User user = getLoginUser();
		query.getQueryObj().setSysUserId(user.getUserId());
		service.schoolAdminlistpage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ???????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listAll",method = RequestMethod.POST)
	public ModelAndView listAll(@ModelAttribute SocietySchoolQuery query){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietySchoolView> societySchoolList = service.listByObj(query);
		buildResponse(modelAndView,societySchoolList);
		return modelAndView;
	}
	
	/**
	 * ????????????
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "??????ID????????????SocietySchool", notes = "??????ID????????????SocietySchool")
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
	@RequestMapping(value = "/logicDeleteByIds",method = RequestMethod.POST)
	public ModelAndView logicDeleteByIds(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.logicDelete(list);
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * ??????????????????
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "??????ID??????SocietySchool", notes = "??????ID??????SocietySchool")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	@RequestMapping(value = "/updateOldSchoolInfo",method = RequestMethod.POST)
	public ModelAndView updateOldSchoolInfo(SocietySchool societySchool){
		ModelAndView modelAndView = new ModelAndView();
		User loginUser = getLoginUser();
		String compId = loginUser.getCompId();
		String schoolAppLoginImg= societySchool.getSchoolAppLoginImg();
		if(schoolAppLoginImg !=null && !schoolAppLoginImg.equals("")){
			String filePath = "";
			String rpath = "/"+compId+"/applogin";
			File file = new File(schoolAppLoginImg);
			try {
				filePath = rpath+FileUtil.saveFile(file,fileForder+rpath,true);
				//??????????????????
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			societySchool.setSchoolAppLoginImg(filePath);
		}
		service.updateById(societySchool);
		return buildResponse(modelAndView);
	}


	/**
	 * ????????????????????????
	 * @param societySchool ???????????????????????????????????????
	 * @return
	 */
	@RequestMapping(value = "/addNewSchoolInfo",method = RequestMethod.POST)
	public ModelAndView addNewSchoolInfo(SocietySchool societySchool){
		ModelAndView modelAndView = new ModelAndView();
		String compId = societySchool.getId();
		String schoolAppLoginImg= societySchool.getSchoolAppLoginImg();
		if(schoolAppLoginImg !=null && !schoolAppLoginImg.equals("")){
			String filePath = "";
			String rpath = "/"+compId+"/applogin";
			File file = new File(schoolAppLoginImg);
			try {
				filePath = rpath+FileUtil.saveFile(file,fileForder+rpath,true);
				//??????????????????
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			societySchool.setSchoolAppLoginImg(filePath);
		}
		//????????????????????????
		User user = getLoginUser();
		societySchool.setCreateUserId(user.getUserId());//?????????id
		societySchool.setCreateTime(new Date());//????????????
		societySchool.setCreateUserName(user.getUserName());//???????????????
		//??????????????????
		Map<String,String> addressMap = getAddressInfo(societySchool.getXianquName());
		societySchool.setProvinceId(addressMap.get("proviceId"));
		societySchool.setProvinceName(addressMap.get("proviceName"));
		societySchool.setCityId(addressMap.get("cityId"));
		societySchool.setCityName(addressMap.get("cityName"));
		societySchool.setXianquId(addressMap.get("xianquNameId"));
		societySchool.setXianquName(addressMap.get("xianquName"));
		//??????????????????  ???????????????????????? AAAA
		String schoolNewName = getPinYinHeadChar(societySchool.getSchoolName()).toLowerCase();
		//????????????
		societySchool.setDataState("1");
		//???????????????????????????
		List<String> oldSchoolNameList = service.selectIsSchoolName(schoolNewName);
		if(oldSchoolNameList.size() >= 1){
			//??????????????????list????????????????????????
			String isSchoolNewName = schoolNewName + String.valueOf((int)((Math.random()*9+1)*1000));
			if(oldSchoolNameList.contains(isSchoolNewName)){//?????? ????????????????????????+1
				String regEx="[^0-9]";
				Pattern pattern = Pattern.compile(regEx);
				Matcher matcher = pattern.matcher(isSchoolNewName);
				//??????????????????
				Integer newSchoolName = new Integer(matcher.replaceAll("").trim());
				societySchool.setId(schoolNewName+(newSchoolName+1));
			}else {//????????? ???????????????????????????4????????????
				societySchool.setId(isSchoolNewName);
			}
		}else {
			//???????????????
			societySchool.setId(schoolNewName);
		}
		//???????????????????????????????????????
		//????????? ?????? ????????????id compid
		SysUser sysUser = new SysUser();
		SysUserView sysUserView = sysUserService.getAdminUserByCode(societySchool.getUserCode());
		if(sysUserView != null){
			modelAndView.addObject("success", false);
			modelAndView.addObject("errMsg", "??????????????????!");
			modelAndView.addObject("code", "0");
			return modelAndView;
		}
		//????????????????????????
		sysUser.setUserCode(societySchool.getUserCode());
		sysUser.setPhone(societySchool.getContactPhoneNum());
		//????????????123456
		sysUser.setPassword("123456");
		//????????????id
		sysUser.setDefaultRoleId(societySchool.getSchoolType());
		sysUser.setCompId(schoolNewName);//????????????
		//??? ??? ???
		sysUser.setProvinceId(addressMap.get("proviceId"));
		sysUser.setProvinceName(addressMap.get("proviceName"));
		sysUser.setCityId(addressMap.get("cityId"));
		sysUser.setCityName(addressMap.get("cityName"));
		sysUser.setXianquId(addressMap.get("xianquNameId"));
		sysUser.setXianquName(addressMap.get("xianquName"));
		sysUser.setUserName(societySchool.getSchoolName());//????????????
		//???????????????
		sysUser.setUserSex("0");
		sysUserService.insert(sysUser);
		//???????????????id
		societySchool.setSchoolNamePrefix(societySchool.getId());
		societySchool.setSysUserId(sysUser.getId());
		service.insertNewSchoolInfo(societySchool);
		return buildResponse(modelAndView);
	}

	/**
	 * ????????????????????????
	 * @param xianquIdAndxianquName ?????????+id(????????????) 12313,?????????
	 * @return
	 */
	private Map<String, String> getAddressInfo(String xianquIdAndxianquName) {
		Map<String,String> addressMap = new HashMap<>();
		//???
		addressMap.put("proviceId","HEBEI");
		addressMap.put("proviceName","?????????");
		//???
		addressMap.put("cityId","130300");
		addressMap.put("cityName","????????????");
		//??????
		if(xianquIdAndxianquName != null && !xianquIdAndxianquName.equals("")){
			String[] xianquNameArr = xianquIdAndxianquName.split(",");
			addressMap.put("xianquNameId",xianquNameArr[0]);
			addressMap.put("xianquName",xianquNameArr[1]);
		}else {
			addressMap.put("","");
			addressMap.put("","");
		}
		return addressMap;
	}

	/**
	 * ????????????????????????
	 * @param schoolName ??????????????????
	 * @return ????????????
	 */
	private String getSchoolNameAbbreviation(String schoolName,boolean isFull) {
		/***
		 * ^[\u2E80-\u9FFF]+$ ??????????????????????????????
		 * ^[\u4E00-\u9FFF]+$ ?????????????????????
		 * ^[\u4E00-\u9FA5]+$ ????????????
		 */
		String regExp="^[\u4E00-\u9FFF]+$";
		StringBuffer sb=new StringBuffer();
		//???????????????????????????????????????
		if(schoolName==null||"".equals(schoolName.trim())){
			return "";
		}
		String pinyin="";
		//???????????????????????????
		for(int i=0;i<schoolName.length();i++){
			char unit=schoolName.charAt(i);
			//????????????????????????
			if(match(String.valueOf(unit),regExp)){
				pinyin=convertSingleHanzi2Pinyin(unit);
				//??????convertSingleHanzi2Pinyin??????????????????????????????????????????
				if(isFull){
					sb.append(pinyin);
				}else {
					sb.append(pinyin.charAt(0));
				}
			}else{
				sb.append(unit);
			}
		}
		//??????????????????
		return sb.toString();
	}

	/**
	 * ??????????????????????????????????????????
	 * @param str ????????????
	 * @param regex ???????????????
	 * @return
	 */
	private static boolean match(String str,String regex){
		Pattern pattern= Pattern.compile(regex);
		Matcher matcher=pattern.matcher(str);
		return matcher.find();
	}

	/**
	 * ?????????????????????
	 * <P>
	 * ?????????????????????
	 *
	 * @param hanzi ???????????????
	 * @return ??????
	 */
	private static String convertSingleHanzi2Pinyin(char hanzi){
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] res;
		StringBuffer sb=new StringBuffer();
		try {
			res = PinyinHelper.toHanyuPinyinStringArray(hanzi,outputFormat);
			sb.append(res[0]);//???????????????????????????????????????
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return sb.toString();
	}

	/**
	 * ??????????????????
	 * @return
	 */
	@RequestMapping(value = "/listAllXianQu",method = RequestMethod.POST)
	public ModelAndView listAllXianQu(){
		ModelAndView modelAndView = new ModelAndView();
		List list = sysXianquService.selectAllXianqu();
		buildResponse(modelAndView,list);
		return modelAndView;
	}


	/*@RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
	public ModelAndView uploadImg(MultipartFile file, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		try{
			//???????????????????????????
			String fileTempPath = FileUtil.saveFile(file,temFileForder,false);
			Map<String,Object> map2=new HashMap<>();
			Map<String,Object> map=new HashMap<>();
			map.put("code",0);
			map.put("msg","");
			map.put("data",map2);
			map2.put("src",fileTempPath);
			return buildResponse(modelAndView,map);
		}catch (Exception e){
			e.printStackTrace();
		}
		Map<String,Object> map=new HashMap<>();
		map.put("code",1);
		map.put("msg","");
		return buildResponse(modelAndView,map);
	}*/

	/**
	 * ????????????logo
	 * @return
	 */
	@RequestMapping(value = "/deleteImgByAddress",method = RequestMethod.POST)
	public ModelAndView deleteImgByAddress(String imgAddressName){
		ModelAndView modelAndView = new ModelAndView();
		File file = new File(imgAddressName);
		if (file.exists()) {
			file.delete();
		}
		return buildResponse(modelAndView);
	}


/*	@RequestMapping(value = "/test",method = RequestMethod.GET)
	@Pass*/
	public ModelAndView test() throws FileNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		File file = new File("E:\\tmp\\test.jpg");
		String fileName = file.getName();
		//?????????????????????
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		//oos???????????????????????? ?????????image ?????????video
		String oosDir = "image";
		String dateStr = DateUtil.getStrDateByFormat(new Date(),"yyyy/MM/dd");
		//guid???????????????
		String guid = Guid.guid();
		//oos??????????????????????????????
		String oosFilePath = oosDir+"/"+dateStr+"/"+guid+suffix;
		//?????????????????????????????????????????????
		String realPath = sysTyiyunCloudStorageService.upload(oosFilePath,file);
		System.out.println(realPath);
		return modelAndView;
	}




	/**
	 * ?????????????????????
	 *
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * @Author QiuTianZhu
	 * @Description: ????????????????????????????????????
	 * @Param:
	 * @return:
	 * @Date 2020/7/22 15:47
	 **/
	@RequestMapping(value = "/listSchool",method = RequestMethod.POST)
	public ModelAndView listSchool(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<SocietySchool> list = service.selectByNotOwnSchoolAll(user.getCompId());
		return buildResponse(modelAndView,list);
	}


	/**
	 * ????????????id ??? ??????id ???????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/SchoolCourseStudyListTablelistpage",method = RequestMethod.POST)
	public ModelAndView SchoolCourseStudyListTablelistpage(@ModelAttribute SocietySchoolQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//??????????????????
		service.listPageCourseCount(query);
		buildResponse(modelAndView,query);
		return buildResponse(modelAndView,query);
	}

	/**
	 * ????????????id ??? ??????id ???????????? ???????????????????????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/MonthSchoolCourseStudyListTablelistpage",method = RequestMethod.POST)
	public ModelAndView MonthSchoolCourseStudyListTablelistpage(@ModelAttribute SocietySchoolQuery query){
		ModelAndView modelAndView = new ModelAndView();
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
		//??????????????????
		service.listPageCourseMonthCount(query);
		buildResponse(modelAndView,query);
		return buildResponse(modelAndView,query);
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


}
