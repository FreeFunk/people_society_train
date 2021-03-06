package com.edgedo.society.controller;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.constant.ThirdPartyType;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.*;
import com.edgedo.dataenum.SchoolConfigKeyEnum;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.SocietyStudentQuery;
import com.edgedo.society.queryvo.SocietyStudentView;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudent")
@Controller
@RequestMapping("/society/societyStudent")
public class SocietyStudentController extends BaseController{

	@Autowired
	RedisUtil redisUtil;
	@Value("${app.faceai.maxfacelength}")
	private Integer app_faceai_maxfacelength;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;

	@Value("${app.globle.faceUrl}")
	private String globleFaceUrl;
	@Value("${app.globle.idcardUrl}")
	private String globleIdcardUrl;

	@Value("${app.server.temFileForder}")
	private String serverTempfile;
	@Value("${app.server.fileForder}")
	private String serverUpfile;
	@Autowired
	private SocietyStudentService service;
	@Autowired
	private SocietySchoolService schoolService;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietySchoolClassAndStudentService classAndStudentService;
	@Autowired
	private SocietySchoolClassAdminService societySchoolClassAdminService;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietySchoolConfigService societySchoolConfigService;
	@Autowired
	private SocietyStudentUniqueService societyStudentUniqueService;
	@Autowired
	private SocietySchoolClassGroupAdminService societySchoolClassGroupAdminService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;

	
	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudent", notes = "????????????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/listpageSup",method = RequestMethod.POST)
	public ModelAndView listpageSup(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SysUser sysUser = sysUserService.loadById(getLoginUser().getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ??????????????????????????????
	 * ????????????id ???????????????????????? ??? ????????????
	 * @param faceImageUrl ?????????
	 * @param idCardImage  ?????????
	 * @param studentId  ??????id
	 * @return
	 */
	@RequestMapping(value = "/idaCardAndFaceMatching",method = RequestMethod.POST)
	public ModelAndView idaCardAndFaceMatching(String faceImageUrl,String idCardImage,String studentId){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		SocietySchool school = schoolService.loadById(schoolId);
		//????????????????????????
		if(school == null){
			return buildErrorResponse(modelAndView,"???????????????????????????");
		}
		//??????????????????
		String realNameState = societySchoolConfigService.getSchoolConfigValue(schoolId, SchoolConfigKeyEnum.REAL_NAME_STATE+"");
		if("".equals(realNameState.trim())){
			//????????????????????????
			realNameState = "0";
		}
		if("0".equals(realNameState)){

			//1.?????????????????? ???????????????
			SocietyStudent student = service.loadById(studentId);
			SocietyStudentUnique societyStudentUnique =
					societyStudentUniqueService.loadById(student.getStudentIdCardNum());
			if(societyStudentUnique==null){
				societyStudentUnique = service.insertStuUniqueInNull(student);
			}
			//?????????????????????????????? ?????? ????????????????????? ?????????uniqe ?????????  ???????????????????????? ??????????????????uniq????????????
			//						???????????? ?????? ??????????????????????????? ???????????????  ????????????uniq???
			//uniq ??????/tempfile/2020/07/13/b366f90c431940a1bf78e706fa06f7c6.jpg  ?????????E:/
			String faceUrl = "";
			String idcardUrl = "";
			if(societyStudentUnique.getFaceImageUrl() != null){
				if(faceImageUrl.equals(societyStudentUnique.getFaceImageUrl())){
					//????????????????????? ??????????????? ?????? faceUrl??????????????????
					faceUrl = societyStudentUnique.getFaceImageUrl();
				}//???????????? ???????????? ????????????uniq???????????????
			}
			if(societyStudentUnique.getIdCardImage() != null){
				if(idCardImage.equals(societyStudentUnique.getIdCardImage())){
					//????????????????????? ??????????????? ?????? idcardUrl??????????????????
					idcardUrl = societyStudentUnique.getIdCardImage();
				}//???????????? ???????????? ????????????uniq???????????????
			}
			/**
			 * ?????????????????? "" in(?????????????????????????????????????????????????????????????????????????????????????????????????????????)
			 * ?????????????????? in ""(??????????????????????????????????????????????????????????????????????????????????????????????????? )
			 * ?????????????????? "" ""(????????????????????????????????????????????????)
			 * ?????????????????? in in(????????????????????????????????????????????????)
			 */
			// /in ""(?????????????????????????????????  ?????????????????? ) /"" ""(????????????) /inin(???????????????,?????? ?????????)
			if (faceUrl.equals("") & idcardUrl.equals("")){//?????????????????????
				service.updateGlobleAndStu(student,societyStudentUnique,faceImageUrl,idCardImage,studentId,user);
			}else {
				service.updateIdCardAndFaceImgIsOneAndTwo(faceUrl,idcardUrl,student,societyStudentUnique,faceImageUrl,idCardImage,studentId,user);
			}
			societyStudentUniqueService.clearStudentCatch(societyStudentUnique);
			service.clearStudentCatch(student);
		}
		return buildResponse(modelAndView);
	}

	/**
	 * ??????????????????
	 * @param faceImageUrl
	 * studentId
	 * @return
	 */
	@RequestMapping(value = "/replaceFaceImg",method = RequestMethod.POST)
	public ModelAndView replaceFaceImg(String faceImageUrl,String studentId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudent societyStudent = service.loadById(studentId);
		SocietyStudentUnique societyStudentUnique =
				societyStudentUniqueService.loadById(societyStudent.getStudentIdCardNum());

		//1.???????????????
		String face = "";//????????????????????????
		String facePrefix = globleFaceUrl;//????????????
		File fileFce = new File(faceImageUrl);//????????????
		try {
			//????????????
			//????????????????????????40k ??????????????????
			//????????????
			String faceOld = FileUtil.getImageBase64Str(fileFce);
			int base64Length = faceOld.length();
			int base64Kb = (int)(base64Length*0.75/1024);
			if(base64Kb>=app_faceai_maxfacelength){
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
			}
			face = facePrefix + FileUtil.saveBase64Img(faceOld,serverUpfile+facePrefix,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//2.??????????????????????????????
		String serverFaceUrl = societyStudentUnique.getFaceImageUrl();
		if (serverFaceUrl.indexOf("/tempfile")==-1){//????????????????????????
			File serverFaceFile = new File(serverUpfile+serverFaceUrl);
			serverFaceFile.delete();
		}
		//3.????????????????????? ???????????????????????????
		societyStudentUnique.setFaceImageUrl(face);
		societyStudentUnique.setHeadPhoto(face);
		societyStudentUniqueService.update(societyStudentUnique);
		List<SocietyStudent> list =
				service.selectByPhoneAndIdCardAll(societyStudentUnique.getStudentPhoneNum(),societyStudentUnique.getStudentIdCardNum());
		for(SocietyStudent societyStudent1 : list){
			societyStudent1.setFaceImageUrl(face);
			societyStudent1.setHeadPhoto(face);
			service.updateById(societyStudent1);
		}
		societyStudentUniqueService.clearStudentCatch(societyStudentUnique);
		service.clearStudentCatch(societyStudent);
		buildResponse(modelAndView);
		return modelAndView;
	}

	/**
	 * ????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudent", notes = "????????????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoolStudentListpage",method = RequestMethod.POST)
	public ModelAndView schoolStudentListpage(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/schoolStudentClassAdminListpage",method = RequestMethod.POST)
	public ModelAndView schoolStudentClassAdminListpage(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classId = societySchoolClassService.selectByClassAdminId(classAdminId);
		if (classId.size()==0){
			buildResponse(modelAndView,query);
			return modelAndView;
		}else {
			//???????????????????????????
			List<String> studentIdList = societySchoolClassAndStudentService.selectByListClassId(classId);
			query.setStudentIdList(studentIdList);
			service.classStudentListPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	/**
	 * ??????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/schoolStudentListpageRenShe",method = RequestMethod.POST)
	public ModelAndView schoolStudentListpageRenShe(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ??????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/schoolStudentListpageSup",method = RequestMethod.POST)
	public ModelAndView schoolStudentListpageSup(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SysUser sysUser= sysUserService.loadById(getLoginUser().getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ??????????????? ?????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/schoolClassAdminStudentListpage",method = RequestMethod.POST)
	public ModelAndView schoolClassAdminStudentListpage(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classId = societySchoolClassService.selectByClassAdminId(classAdminId);
		if(classId.size()==0){
			return buildResponse(modelAndView,query);
		}else {
			List<String> studentList = classAndStudentService.selectBySchoolIdAndClassId(schoolId,classId);
			query.setStudentIdList(studentList);
			service.schoolClassAdminStudentListpage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	/**
	 * ????????????????????? ?????????????????????????????????
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/schoolClassGroupAdminStudentListpage",method = RequestMethod.POST)
	public ModelAndView schoolClassGroupAdminStudentListpage(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		String classGroupAdminId = societySchoolClassGroupAdminService.selectBySysUserId(user.getUserId());
		List<String> classAdminId = societySchoolClassAdminService.selectByGroupId(classGroupAdminId);
		List<String> classId = societySchoolClassService.selectByClassAdminIdList(classAdminId);
		if(classId.size()==0){
			return buildResponse(modelAndView,query);
		}else {
			List<String> studentList = classAndStudentService.selectBySchoolIdAndClassId(schoolId,classId);
			if(studentList.size()!=0){
				query.setStudentIdList(studentList);
				service.schoolClassAdminStudentListpage(query);
				buildResponse(modelAndView,query);
				return modelAndView;
			}
			return buildResponse(modelAndView,query);
		}
	}

	/**
	 * ???????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "???????????????SocietyStudent", notes = "???????????????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObj",method = RequestMethod.POST)
	public ModelAndView listByObj(@ModelAttribute SocietyStudentQuery query){
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
	@ApiOperation(value = "???????????????SocietyStudent", notes = "???????????????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoolStudentlistByObj",method = RequestMethod.POST)
	public ModelAndView schoolStudentlistByObj(@ModelAttribute SocietyStudentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * ??????????????????????????????
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudent", notes = "????????????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/classStudentListpage",method = RequestMethod.POST)
	public ModelAndView classStudentListpage(@ModelAttribute SocietyStudentQuery query,String classId){
		ModelAndView modelAndView = new ModelAndView();
		List<String> studentIdList = classAndStudentService.selectStudentIdByClass(classId);
		if(studentIdList.size() > 0){
			query.setStudentIdList(studentIdList);
			service.classStudentListPage(query);
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * ????????????
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudent", notes = "????????????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudent voObj){
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

	@RequestMapping(value = "/saveOrUpdateForSchool",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateForSchool(SocietyStudent voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			//????????????
			voObj.setStudentSex(IdCardUtil.getGenderByIdCard(voObj.getStudentIdCardNum()));
			//????????????
			voObj.setStudentAge(IdCardUtil.getAgeByIdCard(voObj.getStudentIdCardNum()));
			User user = getLoginUser();
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			String schoolId = user.getCompId();
			SocietySchool school = schoolService.loadById(schoolId);
			if(school == null){
				return buildErrorResponse(modelAndView,"???????????????????????????");
			}
			voObj.setOwnerSchoolId(school.getId());
			voObj.setOwnerSchoolName(school.getSchoolName());
			voObj.setNickName(voObj.getStudentName());
			voObj.setStudentIdCardAscii(idCardInAscii(voObj.getStudentIdCardNum()));
			errMsg = service.insert(voObj);
		}else{
			voObj.setNickName(voObj.getStudentName());
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", errMsg);
			return modelAndView;
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	/**
	 * ???????????????????????? ?????? ascii???
	 * @param studentIdCard
	 * @return
	 */
	public String idCardInAscii(String studentIdCard){
		String num = "";
		if (!("".equals(studentIdCard))){
			StringBuffer sbu = new StringBuffer();
			char[] chars = studentIdCard.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if(i != chars.length - 1)
				{
					sbu.append((int)chars[i]).append(",");
				}
				else {
					sbu.append((int)chars[i]);
				}
			}
			String[] dtr = sbu.toString().split(",");
			Integer integer = 0;
			for (String s : dtr){
				integer = integer+new Integer(s);
			}
			integer = integer%100;
			if (integer < 10){
				num = "0"+integer.toString();
			}else {
				num = integer.toString();
			}
		}
		return num;
	}

	/**
	 * ?????????????????????
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "????????????SocietyStudent", notes = "????????????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdateForAdmin",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateForAdmin(SocietyStudent voObj,String classId){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		User user = getLoginUser();
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			String schoolId = voObj.getOwnerSchoolId();
			SocietySchool school = schoolService.loadById(schoolId);
			if(school == null){
				return buildErrorResponse(modelAndView,"???????????????????????????");
			}
			voObj.setOwnerSchoolId(school.getId());
			voObj.setOwnerSchoolName(school.getSchoolName());
			SocietySchoolClass schoolClass = schoolClassService.loadById(classId);
			if(schoolClass != null){
				errMsg = service.insertForClass(voObj,schoolClass);
			}else{
				errMsg = service.insert(voObj);
			}
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
	 * @Author ZhangCC
	 * @Description ????????????????????????
	 * @Date 2020/5/4 9:40
	 **/
	@ApiOperation(value = "????????????SocietyStudent", notes = "????????????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdateForClass",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateForClass(SocietyStudent voObj,String classId){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			User user = getLoginUser();
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			SocietySchoolClass schoolClass = schoolClassService.loadById(classId);
			if(schoolClass == null){
				return buildErrorResponse(modelAndView,"???????????????????????????");
			}
			voObj.setOwnerSchoolId(schoolClass.getOwnerSchoolId());
			voObj.setOwnerSchoolName(schoolClass.getOwnerSchoolName());
			errMsg = service.insertForClass(voObj,schoolClass);
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
	@ApiOperation(value = "??????ID????????????SocietyStudent", notes = "??????ID????????????SocietyStudent")
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


	@RequestMapping(value = "/deleteExcInfo",method = RequestMethod.POST)
	public ModelAndView deleteExcInfo(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		String err = service.deleteExcInfo(list);
		if (err!=""){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			return modelAndView;
		}
		return buildResponse(modelAndView);
	}

	/**
	 * ????????????id ?????????????????????????????? ???????????? ?????????
	 * @return
	 */
	@RequestMapping(value = "/updateExceInfo",method = RequestMethod.POST)
	public ModelAndView updateExceInfo(SocietyStudent voObj){
		ModelAndView modelAndView = new ModelAndView();

		String err = service.updateExceInfo(voObj);
		if (err!=""){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			return modelAndView;
		}
		return buildResponse(modelAndView);
	}

	@RequestMapping(value = "/selectByIdCardVoUrl",method = RequestMethod.POST)
	public ModelAndView selectByIdCardVoUrl(String studentIdCardNum,String studentId){
		ModelAndView modelAndView = new ModelAndView();

		SocietyStudentUnique stuUnique = societyStudentUniqueService.loadById(studentIdCardNum);
		SocietyStudent stu = service.loadById(studentId);
		if(stuUnique==null){
			stuUnique = new SocietyStudentUnique();
			stuUnique.setId(stu.getStudentIdCardNum());
			stuUnique.setIsRealNameAuth(stu.getIsRealNameAuth());
			stuUnique.setFaceImageUrl(stu.getFaceImageUrl());
			stuUnique.setIdCardImage(stu.getIdCardImage());
			stuUnique.setAccessToken(stu.getAccessToken());
			stuUnique.setHeadPhoto(stu.getHeadPhoto());
			stuUnique.setIsUpPwd("0");
			stuUnique.setNickName(stu.getNickName());
			//????????????123456
			String stuId = stuUnique.getStudentIdCardNum();
			String encodePwd = MD5.encode(MD5.encode("123456") + stuId);
			stuUnique.setPassword(encodePwd);
			stuUnique.setWxOpenId(stu.getWxOpenId());
			stuUnique.setAbcOpenId(stu.getAbcOpenId());
			stuUnique.setCreateTime(new Date());
			stuUnique.setCreateUserId(stu.getCreateUserId());
			stuUnique.setCreateUserName(stu.getCreateUserName());
			stuUnique.setRealNameOperId(stu.getRealNameOperId());
			stuUnique.setRealNameOperName(stu.getRealNameOperName());
			stuUnique.setRealNameTime(stu.getRealNameTime());
			stuUnique.setRealNameType(stu.getRealNameType());
			stuUnique.setStudentAge(stu.getStudentAge());
			stuUnique.setStudentIdCardNum(stu.getStudentIdCardNum());
			stuUnique.setStudentPhoneNum(stu.getStudentPhoneNum());
			stuUnique.setStudentName(stu.getStudentName());
			societyStudentUniqueService.insertStuAndUnique(stuUnique);
		}

		if(stuUnique.getIdCardImage()==null || stuUnique.getFaceImageUrl()==null){
			if(stu.getFaceImageUrl()!=null || stu.getIdCardImage()!=null){
				stuUnique.setFaceImageUrl(stu.getFaceImageUrl());
				stuUnique.setIdCardImage(stu.getIdCardImage());
				stuUnique.setHeadPhoto(stu.getHeadPhoto());
				societyStudentUniqueService.update(stuUnique);
			}
		}

		return buildResponse(modelAndView,stuUnique);
	}


	/*
	* ??????id?????????????????????
	* */
	@RequestMapping(value = "/resetPwdById",method = RequestMethod.POST)
	public ModelAndView resetPwdById(String id){
		ModelAndView modelAndView = new ModelAndView();
		service.resetPwdById(id);
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
		List<String> errInfo = new ArrayList<String>();
		for(String str : arr){
			//???????????????????????? ???????????????????????? ??????????????????
			List<String> classNameList = societySchoolClassAndStudentService.selectByStudentIdVoClassId(str);
			if (classNameList.size()==0){
				list.add(str);
			}else {
				SocietyStudent societyStudent = service.loadById(str);
				String citiesCommaSeparated = societyStudent.getStudentName()+"???"+String.join(",", classNameList);
				errInfo.add(citiesCommaSeparated);
			}
		}
		if (list.size()!=0){
			service.logicDelete(list);
		}
		return buildResponse(modelAndView,errInfo);
	}
	
	/**
	 * ??????????????????
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "??????ID??????SocietyStudent", notes = "??????ID??????SocietyStudent")
	@ApiImplicitParam(name = "access-token", value = "??????????????????", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????????????????????????????
	 * @Date 2020/5/4 20:41
	 **/
	@RequestMapping(value = "/addStudentListPage",method = RequestMethod.POST)
	public ModelAndView addStudentListPage(SocietyStudentQuery query,String classId){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<String> studentIdList = classAndStudentService.selectStudentIdByClass(classId);
		query.setStudentIdList(studentIdList);
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.classNotInStudentListPage(query);
		return buildResponse(modelAndView,query);
	}

	@RequestMapping(value = "/addStudentLeftListPage",method = RequestMethod.POST)
	public ModelAndView addStudentLeftListPage(SocietyStudentQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<String> studentIdList = classAndStudentService.selectStudentIdByClass(classId);
		List<String> lis = Arrays.asList(ids.split(","));
		studentIdList.addAll(lis);
		query.setStudentIdList(studentIdList);
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.classNotInStudentListPage(query);
		return buildResponse(modelAndView,query);
	}


	@RequestMapping(value = "/addStudentRightListPage",method = RequestMethod.POST)
	public ModelAndView addStudentRightListPage(SocietyStudentQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<String> studentIdList = classAndStudentService.selectStudentIdByClass(classId);
		List<String> lis = Arrays.asList(ids.split(","));
		studentIdList.addAll(lis);
		query.setStudentIdList(studentIdList);
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.classYesInStudentListPage(query);
		return buildResponse(modelAndView,query);
	}

	@RequestMapping(value = "/addStudentRightVoClassListPage",method = RequestMethod.POST)
	public ModelAndView addStudentRightVoClassListPage(SocietyStudentQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<String> studentIdList = new ArrayList<>();
		List<String> lis = Arrays.asList(ids.split(","));
		studentIdList.addAll(lis);
		query.setStudentIdList(studentIdList);
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.classYesInStudentListPage(query);
		return buildResponse(modelAndView,query);
	}


	@RequestMapping(value = "/addStudentAdminListPage",method = RequestMethod.POST)
	public ModelAndView addStudentAdminListPage(SocietyStudentQuery query,String classId){
		ModelAndView modelAndView = new ModelAndView();
		List<String> studentIdList = classAndStudentService.selectStudentIdByClass(classId);
		query.setStudentIdList(studentIdList);
		service.classNotInStudentListPage(query);
		return buildResponse(modelAndView,query);
	}

	@RequestMapping(value = "/addStudentAdminLeftListPage",method = RequestMethod.POST)
	public ModelAndView addStudentAdminLeftListPage(SocietyStudentQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		List<String> studentIdList = classAndStudentService.selectStudentIdByClass(classId);
		List<String> lis = Arrays.asList(ids.split(","));
		studentIdList.addAll(lis);
		query.setStudentIdList(studentIdList);
		service.classNotInStudentListPage(query);
		return buildResponse(modelAndView,query);
	}


	@RequestMapping(value = "/addStudentAdminRightListPage",method = RequestMethod.POST)
	public ModelAndView addStudentAdminRightListPage(SocietyStudentQuery query,String classId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		List<String> studentIdList = classAndStudentService.selectStudentIdByClass(classId);
		List<String> lis = Arrays.asList(ids.split(","));
		studentIdList.addAll(lis);
		query.setStudentIdList(studentIdList);
		service.classYesInStudentListPage(query);
		return buildResponse(modelAndView,query);
	}



	@RequestMapping(value = "/addAdminStudentListPage",method = RequestMethod.POST)
	public ModelAndView addAdminStudentListPage(SocietyStudentQuery query,String classId){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<String> studentIdList = classAndStudentService.selectStudentIdByClass(classId);
		query.setStudentIdList(studentIdList);
		service.classNotInStudentListPage(query);
		return buildResponse(modelAndView,query);
	}


	/**
	 *@Author:ZhaoSiDa
	 *@Description: ??????????????????????????? ??????????????????????????????????????????
	 *@DateTime: 2020/5/21 16:50
	 */
	@RequestMapping(value = "/excelImportStu",method = RequestMethod.POST)
	public ModelAndView excelImportStu(MultipartFile file){
		ModelAndView modelAndView = new ModelAndView();
		try {
			User user = getLoginUser();
			String compId = user.getCompId();
			String userName = user.getUserName();
			//??????excel
			String fileName = file.getOriginalFilename();
			String fileExtend = fileName.substring(fileName.lastIndexOf(".")+1);
			if(!fileExtend.equals("xls")){
				return buildErrorResponse(modelAndView,"?????????.xls??????????????????");
			}
			InputStream inputStream =null;
			Workbook book = null;
			Sheet carSheet = null;
			inputStream = file.getInputStream();
			if(inputStream.available()==0){
				return buildErrorResponse(modelAndView,"????????????????????????????????????");
			}
			book = Workbook.getWorkbook(inputStream);
			carSheet = book.getSheet(0);
			int rows = carSheet.getRows();
			int columns = carSheet.getColumns();
			if(rows<2){
				return buildErrorResponse(modelAndView,"????????????????????????????????????");
			}
			List<String> propertyEg = new ArrayList<String>();
			Cell[] headers = carSheet.getRow(0);
			for(int i=0;i<headers.length;i++){
				Cell cell = headers[i];
				String cellValue = cell.getContents();
				if(cellValue==null && cellValue.length()==0){
					propertyEg.add("none");
					continue;
				}
				propertyEg.add(fenxiHeader(cellValue));
			}
			List<SocietyStudent> studentList = new ArrayList<SocietyStudent>();//????????????????????????
			List<String> repeatStudentList = new ArrayList<>();//?????????????????????
			List<String> isIdCard = new ArrayList<>();//????????????????????????
			for(int i=1;i<rows;i++){
				Map<String,String> propertyMap= new HashMap<String,String>();
				Cell[] properties = carSheet.getRow(i);
				if(properties.length!=0){
					for(int j=0;j<properties.length;j++){
						Cell cell = properties[j];
						String key = propertyEg.get(j);
						if(key.equals("peopleType")){
							String peopleType = getPeopleType(cell.getContents().trim());
							propertyMap.put(key,peopleType);
						}else {
							propertyMap.put(key,cell.getContents().trim());
						}
					}
					SocietyStudent student = ObjectUtil.mapToBean(propertyMap,SocietyStudent.class);
					if(student == null && student.getStudentIdCardNum().equals("") &&
							student.getStudentPhoneNum().equals("") && student.getStudentName().equals("")){
						continue;
					}else {
						//??????????????????????????????????????????
						SocietyStudent studentByPhone = service.selectByPhone(student.getStudentPhoneNum(),compId);
						if(studentByPhone!=null){
							repeatStudentList.add(student.getStudentName());
							continue;
						}
						SocietyStudent studentByIdCard = service.selectByIdCard(student.getStudentIdCardNum(),compId);
						if(studentByIdCard!=null){
							repeatStudentList.add(student.getStudentName());
							continue;
						}
						//???????????????????????????
						boolean isFlag = IdCardUtil.isValidatedAllIdcard(student.getStudentIdCardNum());
						if(!isFlag){//?????????
							isIdCard.add(student.getStudentName());
							continue;
						}else {
							SocietySchool societySchool = schoolService.loadById(user.getCompId());
							student.setId(Guid.guid());
							student.setCreateTime(new Date());
							student.setCreateUserId(user.getUserId());
							student.setCreateUserName(user.getUserName());
							student.setOwnerSchoolId(societySchool.getId());
							student.setOwnerSchoolName(societySchool.getSchoolName());
							student.setDataState("1");
							student.setIsRealNameAuth("0");
							studentList.add(student);
						}
					}
				}
			}
			Map<String,List<String>> map = service.excelImportStuInsert(studentList);

			repeatStudentList.addAll(map.get("repList"));

			//????????????????????????
			if(repeatStudentList.size()!=0 || isIdCard.size()!=0 || map.get("phoneList").size()!=0){//??????
				modelAndView.addObject("code", "0");
				modelAndView.addObject("success", false);
				modelAndView.addObject("repeatStudentList", repeatStudentList);
				modelAndView.addObject("phoneList", map.get("phoneList"));
				modelAndView.addObject("isIdCard", isIdCard);
				return modelAndView;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildResponse(modelAndView);
	}

	private String getPeopleType(String peopleName) {
		if(peopleName.equals("????????????")){
			return "IMP_STU";
		}else if(peopleName.equals("????????????")){
			return "COMP_STU";
		}else {
			return "ORD_STU";
		}
	}


	/**
	 * ????????????????????????  ??????????????????????????????
	 * 1.???????????????????????? ????????????????????? ????????????????????? ???????????????????????????
	 * 2.???????????????????????? ??????????????? ????????????
	 * 2.???????????????????????? ?????????????????????????????????
	 * @param file
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/excelImportStuAndClass",method = RequestMethod.POST)
	public ModelAndView excelImportStuAndClass(MultipartFile file,String classId){
		ModelAndView modelAndView = new ModelAndView();
		try {
			User user = getLoginUser();
			String compId = user.getCompId();
			String userName = user.getUserName();
			//??????excel
			String fileName = file.getOriginalFilename();
			String fileExtend = fileName.substring(fileName.lastIndexOf(".")+1);
			if(!fileExtend.equals("xls")){
				return buildErrorResponse(modelAndView,"?????????.xls??????????????????");
			}
			InputStream inputStream =null;
			Workbook book = null;
			Sheet carSheet = null;
			inputStream = file.getInputStream();
			if(inputStream.available()==0){
				return buildErrorResponse(modelAndView,"????????????????????????????????????");
			}
			book = Workbook.getWorkbook(inputStream);
			carSheet = book.getSheet(0);
			int rows = carSheet.getRows();
			int columns = carSheet.getColumns();
			if(rows<2){
				return buildErrorResponse(modelAndView,"????????????????????????????????????");
			}
			List<String> propertyEg = new ArrayList<String>();
			Cell[] headers = carSheet.getRow(0);
			for(int i=0;i<headers.length;i++){
				Cell cell = headers[i];
				String cellValue = cell.getContents();
				if(cellValue==null && cellValue.length()==0){
					propertyEg.add("none");
					continue;
				}
				propertyEg.add(fenxiHeader(cellValue));
			}
			List<SocietyStudent> studentList = new ArrayList<SocietyStudent>();//????????????????????????
			List<SocietyStudent> studentAndClassList = new ArrayList<SocietyStudent>();//??????????????????????????????
			List<String> repeatStudentList = new ArrayList<>();//?????????????????????
			List<String> isIdCard = new ArrayList<>();//????????????????????????
			for(int i=1;i<rows;i++){
				Map<String,String> propertyMap= new HashMap<String,String>();
				Cell[] properties = carSheet.getRow(i);
				if(properties.length!=0){
					for(int j=0;j<properties.length;j++){
						Cell cell = properties[j];
						String key = propertyEg.get(j);
						if(key.equals("peopleType")){
							String peopleType = getPeopleType(cell.getContents().trim());
							propertyMap.put(key,peopleType);
						}else {
							propertyMap.put(key,cell.getContents().trim());
						}
					}
					SocietyStudent student = ObjectUtil.mapToBean(propertyMap,SocietyStudent.class);
					if(student == null && student.getStudentIdCardNum().equals("") &&
							student.getStudentPhoneNum().equals("") && student.getStudentName().equals("")){
						continue;
					}else {

						//??????????????????????????????????????????
						List<SocietyStudentView> studentByPhoneList = service.selectByPhoneList(student.getStudentPhoneNum(),compId);
						List<SocietyStudentView> studentByIdCardList = service.selectByIdCardList(student.getStudentIdCardNum(),compId);

						if(studentByPhoneList.size()>=2|| studentByIdCardList.size()>=2){
							repeatStudentList.add(student.getStudentName());
							continue;
						}
						if(studentByPhoneList.size()==0 && studentByIdCardList.size()==0){
							//???????????????????????????
							boolean isFlag = IdCardUtil.isValidatedAllIdcard(student.getStudentIdCardNum());
							if(!isFlag){//?????????
								isIdCard.add(student.getStudentName());
								continue;
							}else {
								SocietySchool societySchool = schoolService.loadById(user.getCompId());
								student.setId(Guid.guid());
								student.setCreateTime(new Date());
								student.setCreateUserId(user.getUserId());
								student.setCreateUserName(user.getUserName());
								student.setOwnerSchoolId(societySchool.getId());
								student.setOwnerSchoolName(societySchool.getSchoolName());
								student.setDataState("1");
								student.setIsRealNameAuth("0");
								studentList.add(student);
							}
						}else {
							//???????????????????????????
							//1.???????????????????????????
							String studentOldId =
									service.selectByPhoneAndIdCard(compId,student.getStudentPhoneNum(),student.getStudentIdCardNum());
							if (studentOldId==null){
								repeatStudentList.add(student.getStudentName());
								continue;
							}
							//?????????????????????????????????
							int stuFalg = classAndStudentService.selectBySchoolIdVoClassIdOne(classId,studentOldId);
							if(stuFalg!=0){//??????
								repeatStudentList.add(student.getStudentName());
								continue;
							}else {
								//???????????????????????????
								boolean isFlag = IdCardUtil.isValidatedAllIdcard(student.getStudentIdCardNum());
								if(!isFlag){//?????????
									isIdCard.add(student.getStudentName());
									continue;
								}else {//?????????????????????
									SocietyStudent societyStudent = service.loadById(studentOldId);
									studentAndClassList.add(societyStudent);
								}
							}
						}
					}
				}
			}
			Map<String,Object> map = service.insertStuListAndStuClassList(studentList,studentAndClassList,classId);//????????????
			String err = "";
			if(map.containsKey("repList")){
				repeatStudentList.addAll((List<String>) map.get("repList"));
			}
			err = (String)map.get("err");
			if (err.equals("???")){
				err = "";
			}
			List<String> phoneList = new ArrayList<>();
			if(map.containsKey("phoneList")){
				phoneList.addAll((List<String>) map.get("phoneList"));
			}

			//????????????????????????
			if(repeatStudentList.size()!=0 || isIdCard.size()!=0 || !err.equals("") || phoneList.size()!=0){//????????????
				modelAndView.addObject("code", "0");
				modelAndView.addObject("success", false);
				modelAndView.addObject("repeatStudentList", repeatStudentList);
				modelAndView.addObject("isIdCard", isIdCard);
				modelAndView.addObject("phoneList", phoneList);
				modelAndView.addObject("err", err);
				return modelAndView;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????????????????
	 * @Date 2020/7/18 9:40
	 **/
	@RequestMapping(value = "/compressStudentFaceImage",method = RequestMethod.POST)
	public ModelAndView compressStudentFaceImage(String stuId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudent stu = service.loadById(stuId);
		String oraFaceImage = stu.getFaceImageUrl();
		String filePath = serverUpfile + FileUtil.changeWebPathToFilePath(oraFaceImage);

		Map<String,Object> resultMap = new HashMap<>();
		try{
			File faceOra = new File(filePath);
			long length = faceOra.length();
			long oraFaceImgSize = length/1024;
			resultMap.put("oraFaceImgSize",oraFaceImgSize);
			if(oraFaceImgSize>=app_faceai_maxfacelength){
				String base64File = FileUtil.getImageBase64Str(faceOra);
				base64File = FileUtil.compressImg(base64File,app_faceai_imgcompresswidth);
				//????????????
				String relvePath = FileUtil.saveBase64Img(base64File,serverUpfile + File.separator +"tempfile",true);
				resultMap.put("faceImgSize",new Double((base64File.length()*0.75)/1024).intValue());
				//????????????????????????
				String faceImage = "/tempfile" + relvePath;
				resultMap.put("faceImage",faceImage);
			}else{
				resultMap.put("faceImgSize", oraFaceImgSize);
				resultMap.put("faceImage", oraFaceImage);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView,resultMap);
	}

	@RequestMapping(value = "/saveCompressFaceImg",method = RequestMethod.POST)
	public ModelAndView saveCompressFaceImg(String stuId,String compFaceImg){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudent stu = service.loadById(stuId);
		String oraFaceImage = stu.getFaceImageUrl();
		String oraFilePath = serverUpfile + FileUtil.changeWebPathToFilePath(oraFaceImage);
		String compFaceImgFilePath = serverUpfile + FileUtil.changeWebPathToFilePath(compFaceImg);
		Map<String,Object> resultMap = new HashMap<>();
		try{
			FileUtils.copyFile(new File(compFaceImgFilePath),new File(oraFilePath));
		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView,resultMap);
	}



	/**
	 * excel????????????
	 * @param cellValue
	 * @return
	 */
	private String fenxiHeader(String cellValue){
		if(cellValue.contains("??????")) {
			return "studentName";
		} else if(cellValue.contains("??????")) {
			return "studentSex";
		} else if(cellValue.contains("??????")) {
			return "studentAge";
		} else if(cellValue.contains("????????????")) {
			return "studentPhoneNum";
		} else if(cellValue.contains("????????????")) {
			return "studentIdCardNum";
		}else if(cellValue.contains("????????????????????????????????????????????????????????????")){
			return "peopleType";
		}
		return "none;";

	}
}
