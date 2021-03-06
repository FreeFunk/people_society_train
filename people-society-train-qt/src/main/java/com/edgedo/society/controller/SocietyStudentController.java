package com.edgedo.society.controller;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.constant.ThirdPartyReqKey;
import com.edgedo.common.constant.ThirdPartyType;
import com.edgedo.common.util.*;
import com.edgedo.face.entity.FaceMatchInfo;
import com.edgedo.face.entity.FaceMatchInfoExt;
import com.edgedo.face.service.IFaceAiService;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.constant.SocietyTrainContant;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.SocietyStudentCertificateQuery;
import com.edgedo.society.queryvo.SocietyStudentQuery;
import com.edgedo.society.queryvo.SocietyStudentView;
import com.edgedo.society.service.*;
import com.edgedo.text.Service.ITextFenxiAiService;
import com.edgedo.text.entity.IdCardResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "SocietyStudent")
@Controller
@RequestMapping("/stu")
public class SocietyStudentController extends BaseController{

	@Value("${app.fileForder}")
	String fileForder;

	@Autowired
	RedisUtil redisUtil;

	@Value("${app.studyfacecheck}")
	private boolean studyfacecheck;
	@Value("${app.faceai.maxfacelength}")
	private Integer app_faceai_maxfacelength;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;

	@Autowired
	private SocietyStudentService service;
	@Autowired
	private SocietyStudentUniqueService  studentUniqueService;
	@Autowired
	private SocietyStudentCertificateService certificateService;
	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;
	@Autowired
	private SocietySchoolClassAndStudentService classAndStudentService;
	//??????????????????
	@Autowired
	private IFaceAiService faceAiService;
	@Autowired
	private ITextFenxiAiService textFenxiAiService;
	//??????????????????
	@Autowired
	private SocietyStudentStudyProcessFaceService studyProcessFaceService;
	@Autowired
	private SocietySchoolService schoolService;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietySchoolConfigService schoolConfigService;
	@Autowired
	private SocietySchoolConfigKeyService schoolConfigKeyService;

	@Autowired
	private SocietySchoolCourseNodeService courseAndNodeService;

	/**
	 * ??????????????????????????????
	 * @return
	 */
	@RequestMapping("/updateStuPhoneType")
	public ModelAndView updateStuPhoneType(String stuId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudent societyStudent = service.loadById(stuId);
		String flag = "";
		if(societyStudent.getPhoneTypeFlag()==null || "0".equals(societyStudent.getPhoneTypeFlag())){
			flag = "1";
			societyStudent.setPhoneTypeFlag("1");
		}else{
			flag = "0";
			societyStudent.setPhoneTypeFlag("0");
		}
		service.update(societyStudent);
		return buildResponse(modelAndView,flag);
	}


	/**
	 * @Author ZhangCC
	 * @Description ?????????????????????
	 * @Date 2020/5/29 9:55
	 **/
	@RequestMapping("/getCurrentStuRole")
	public ModelAndView getCurrentStuRole(){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????
	 * @Date 2020/5/5 18:52
	 **/
	@RequestMapping("/studentLogin")
	@Pass
	public ModelAndView studentLogin(
			String studentPhoneNum,
			String password,
			HttpServletResponse response
	){
		ModelAndView modelAndView = new ModelAndView();
		if(studentPhoneNum!=null){
			studentPhoneNum = studentPhoneNum.trim();
		}
		//????????????????????????
		SocietyStudentUnique  studentUnique = studentUniqueService.selectOneStuByPhoneNum(studentPhoneNum);
		if(studentUnique == null){
			return buildErrorResponse(modelAndView,"??????????????????");
		}
		String stuId = studentUnique.getId();
		String encodePwd = MD5.encode(MD5.encode(password)+stuId);
		String stuPwd = studentUnique.getPassword();
		if(stuPwd != null && !stuPwd.equals(encodePwd)){
			return buildErrorResponse(modelAndView,"???????????????");
		}
		String accessToken = getAccessToken();

		if(isWx()){
			//????????????????????????openid
			String wxOpenId = getThirdPartyLoginOpenId();
			studentUnique.setWxOpenId(wxOpenId);
			//????????????openid?????????de openid
			SocietyStudentUnique oraStudent = studentUniqueService.selectByOpenIdAndType(ThirdPartyType.wechat,wxOpenId);
			if(oraStudent != null){
				String oraStuId = oraStudent.getId();
				if(!oraStuId.equals(stuId)){
					studentUniqueService.deleteStuWxOpenId(oraStudent.getId());
					studentUniqueService.clearStudentCatch(oraStudent);
				}
			}
		}


		if(accessToken==null){
			accessToken = Guid.guid();
			//???token??????????????????cookie
			Cookie cookie = new Cookie("access-token",accessToken);
			cookie.setPath("/");
			response.addCookie(cookie);
		}//7C92097FC4442E1C92DA1D3980FD0CAB
		studentUnique.setAccessToken(accessToken);
		studentUniqueService.update(studentUnique);
		studentUniqueService.clearStudentCatch(studentUnique);
		return buildResponse(modelAndView,studentUnique);
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????
	 * @Date 2020/5/5 18:52
	 **/
	@RequestMapping("/studentLoginWx")
	public ModelAndView studentLoginWx(
			String studentPhoneNum,
			String password,
			HttpServletResponse response
	){
		return studentLogin(studentPhoneNum,password,response);
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????
	 * @Date 2020/5/12 20:07
	 **/
	@RequestMapping("/studentLogout")
	public ModelAndView studentLogout(HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = getAccessToken();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		if(isWx()){
			studentUniqueService.deleteStuWxOpenId(student.getId());
		}
		studentUniqueService.deleteStuAccessToken(accessToken);
		studentUniqueService.clearStudentCatch(student);
		//TODO:???????????????
		//???cookie??????
		/*Cookie cookie = new Cookie("access-token",getAccessToken());
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);*/
		return buildResponse(modelAndView);
	}


	/**
	 * @Author ZhangCC
	 * @Description ??????????????????
	 * @Date 2020/5/10 9:06
	 **/
	@RequestMapping("/studentUpPwd")
	public ModelAndView studentUpPwd(String oldPassword,String newPassword,String conPassword){
		ModelAndView modelAndView = new ModelAndView();
		if(oldPassword == null || oldPassword.equals("")){
			return buildErrorResponse(modelAndView,"???????????????????????????");
		}
		SocietyStudentUnique loginStu = getLoginStudent(studentUniqueService);
		String stuId = loginStu.getId();
		SocietyStudentUnique student = studentUniqueService.loadById(stuId);

		oldPassword = MD5.encode(MD5.encode(oldPassword)+stuId);
		String oraPwd = student.getPassword();
		if(!oldPassword.equals(oraPwd)){
			return buildErrorResponse(modelAndView,"?????????????????????");
		}
		if(newPassword == null || newPassword.equals("")){
			return buildErrorResponse(modelAndView,"?????????????????????");
		}
		if(conPassword == null || conPassword.equals("")){
			return buildErrorResponse(modelAndView,"????????????????????????");
		}
		if(!newPassword.equals(conPassword)){
			return buildErrorResponse(modelAndView,"??????????????????????????????");
		}
		String encodePwd = MD5.encode(MD5.encode(newPassword)+stuId);
		student.setPassword(encodePwd);
		student.setIsUpPwd("1");
		studentUniqueService.update(student);
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????
	 * @Date 2020/5/7 15:21
	 **/
	@RequestMapping("/getUserInfo")
	public ModelAndView getUserInfo(){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> resultMap = new HashMap<>();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
//		SocietyStudent student = service.loadById(loginStudent.getId());
		resultMap.put("student",student);
		//??????????????????????????????
		/*String ownerSchoolId= student.getOwnerSchoolId();
		SocietySchoolClassAndStudent classAndStudent = classAndStudentService.selectClassByStuId(ownerSchoolId,student.getId());
		if(classAndStudent != null){
			resultMap.put("className",classAndStudent.getClassName());
		}*/
		return buildResponse(modelAndView,resultMap);
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????????????????????????????
	 * @Date 2020/5/7 15:21
	 **/
	@RequestMapping("/getUserInfoNotRealName")
	public ModelAndView getUserInfoNotRealName(){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique loginStudent = getLoginStudentIgnoreRealName(studentUniqueService);
		String studentId = loginStudent.getId();
		SocietyStudentUnique student = studentUniqueService.loadById(studentId);
		if(student != null){
			String isRealNameAuth = student.getIsRealNameAuth();
			if(isRealNameAuth != null && isRealNameAuth.equals("1")){
				//??????redis????????????????????????
				studentUniqueService.clearStudentCatch(student);
			}
		}
		return buildResponse(modelAndView,student);
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????????????????????????????
	 * @Date 2020/5/7 15:21
	 **/
	@RequestMapping("/updateHeadPhotoAndClass")
	public ModelAndView updateHeadPhotoAndClass(SocietyStudent student,String classId){
		String school = getSchoolId();
		if(school==null || school.trim().equals("")){//????????????????????????????????????????????????
			school = "globle";
		}
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique loginStu = getLoginStudentIgnoreRealName(studentUniqueService);
		student.setId(loginStu.getId());

		String headPhoto = student.getHeadPhoto();
		try{
			if(headPhoto != null&&!headPhoto.equals("")){
				if(headPhoto != null && headPhoto.indexOf("head") == -1){
					String headForder = fileForder + File.separator + school + File.separator + "head";
					String headPhotoPath = FileUtil.saveBase64Img(headPhoto,headForder,true);
					headPhotoPath = "/" + school +"/head" + headPhotoPath;
					SocietyStudentUnique param  = new SocietyStudentUnique();
					param.setId(loginStu.getId());
					param.setHeadPhoto(headPhotoPath);
					loginStu.setHeadPhoto(headPhotoPath);
					studentUniqueService.update(param);
				}
			}
			//add by wangzhen ????????????
			studentUniqueService.clearStudentCatch(loginStu);
		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????????????????????????????
	 * @Date 2020/5/7 15:21
	 **/
	@RequestMapping("/getUserInfoAndExtendInfo")
	public ModelAndView getUserInfoAndExtendInfo(){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> resultMap = new HashMap<>();
		SocietyStudentUnique student = getLoginStudentIgnoreRealName(studentUniqueService);
		String stuIdCardNum = student.getId();
		resultMap.put("student",student);
		//??????????????????
		int certCount = certificateService.countStudentCertByStudenIdCard(stuIdCardNum);
		resultMap.put("certCount",certCount);
		//??????????????????
		int  courseCount = studentAndCourseService.countCourseByStuIdCardNum(stuIdCardNum);
		resultMap.put("courseCount",courseCount);
		return buildResponse(modelAndView,resultMap);
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????
	 * @Date 2020/5/7 19:10
	 **/
	@RequestMapping("/studentRealName")
	public ModelAndView studentRealName(@ModelAttribute SocietyStudent student){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique loginStu = getLoginStudentIgnoreRealName(studentUniqueService);
		String realNameState = loginStu.getIsRealNameAuth();
		if(realNameState!=null && realNameState.equals("1")){
			return buildErrorResponse(modelAndView,"???????????????????????????");
		}
		try{
			String idCardImage = student.getIdCardImage();
			if(idCardImage == null || idCardImage.equals("")){
				return buildErrorResponse(modelAndView,"?????????????????????????????????");
			}
			String faceImageUrl = student.getFaceImageUrl();
			if(faceImageUrl == null || faceImageUrl.equals("")){
				return buildErrorResponse(modelAndView,"?????????????????????????????????");
			}
			if(idCardImage != null && idCardImage.indexOf("tempfile") == -1){
//				idCardImage = FileUtil.compressImg(idCardImage,600);
				String idCardImgPath = FileUtil.saveBase64Img(idCardImage,fileForder + File.separator + "tempfile",true);

				idCardImgPath = "/tempfile" + idCardImgPath;
				loginStu.setIdCardImage(idCardImgPath);
			}
			if(faceImageUrl != null && faceImageUrl.indexOf("tempfile") == -1){
				int base64Length = faceImageUrl.length();
				int base64Kb = (int)(base64Length*0.75/1024);
				if(base64Kb>=app_faceai_maxfacelength){
					faceImageUrl = FileUtil.compressImg(faceImageUrl,app_faceai_imgcompresswidth);
				}
				String faceImagePath = FileUtil.saveBase64Img(faceImageUrl,fileForder + File.separator + "tempfile",true);
				faceImagePath = "/tempfile"+faceImagePath;
				loginStu.setFaceImageUrl(faceImagePath);
			}
			//??????????????????
			String faceToken = faceAiService.submitFaceMatch(
					idCardImage,FaceMatchInfo.FaceTypeEnum.IDCARD,
					faceImageUrl,FaceMatchInfo.FaceTypeEnum.LIVE);
			//?????????????????????
			String idCardToken = textFenxiAiService.submitIdCardFenxi(idCardImage);
			//????????????token??????????????????
			Map<String,Object> tokenMap = new HashMap<>();
			tokenMap.put("faceToken",faceToken);
			tokenMap.put("idCardToken",idCardToken);
			//???????????????????????????????????????????????????5??????
			redisUtil.hmset(RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX+getAccessToken(),tokenMap,300);
			//??????????????????????????????????????????,??????????????????????????????????????????
			loginStu.setIsRealNameAuth("0");//0????????????
			//?????????????????????
//			loginStu.setRealNameErrMsg("");
			studentUniqueService.update(loginStu);


		}catch (Exception e){
			e.printStackTrace();
		}

		return buildResponse(modelAndView);
	}



	/**
	 * @Author WangZhen
	 * @Description ???????????????????????????
	 * @Date 2020/5/10 19:18
	 **/
	@RequestMapping("/checkRealName")
	public ModelAndView checkRealName(){
		ModelAndView modelAndView = new ModelAndView();
		String schoolId = getSchoolId();
		SocietyStudentUnique stu = getLoginStudentIgnoreRnWhole(studentUniqueService);
		String at = getAccessToken();
		String key = RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX+at;
		Map<String,Object > result = new HashMap<>();
		String state = "notpass";
		String failReason = "";
		Object faceToken = redisUtil.hget(key,"faceToken");
		Double score = faceAiService.queryFaceMatchScore(faceToken+"");
		if(score!=null) {
			if (score.doubleValue() == -99) {//??????
				state = "wait";
			}else if(score.doubleValue()>60){//??????????????????
				state = "pass";
			}else{
				state = "notpass";
				failReason = "???????????????????????????????????????,???????????????????????????????????????!";
			}
		}
		String name = "";
		if(state.equals("pass")){
			Object idCardToken = redisUtil.hget(key,"idCardToken");
			IdCardResult idResult = textFenxiAiService.queryIdCard(idCardToken+"");
			if(idResult != null){
				String idCardState = idResult.getState();
				if(idCardState!=null && idCardState.equals("success")){
					String idCard = idResult.getIdCardNum();
					name = idResult.getName();
					if(
							idCard!=null && idCard.equals(stu.getStudentIdCardNum())
							/*&& name!=null && name.equals(stu.getStudentName())*/
					){
						state = "pass";
					}else{
						state = "notpass";
						failReason = "????????????????????????????????????,???????????????!";
					}
				}else{
					state="notpass";
					failReason = "????????????????????????,????????????????????????!";
				}
			}else{
				state="wait";
			}
		}
		// ?????????????????????????????????????????????
		if(state.equals("pass")){
			//TODO:  ???????????????????????????????????????
			String temFaceImageUrl = stu.getFaceImageUrl();
			String temIdCardImage = stu.getIdCardImage();
			String faceFilePath = fileForder + FileUtil.changeWebPathToFilePath(temFaceImageUrl);
			String idCardFilePath =  fileForder + FileUtil.changeWebPathToFilePath(temIdCardImage);

			try {
				String faceWebPath = FileUtil.saveFile(
						new File(faceFilePath),
						fileForder +
								File.separator
								+ schoolId
								+ File.separator + "faceimg", true);
				String idCardWebPath = FileUtil.saveFile(
						new File(idCardFilePath),
						fileForder +
								File.separator
								+ schoolId
								+ File.separator + "idcard", true);
				stu.setFaceImageUrl("/" + schoolId + "/faceimg" + faceWebPath);
				stu.setIdCardImage("/" + schoolId + "/idcard" + idCardWebPath);
				stu.setIsRealNameAuth("1");
				stu.setNickName(stu.getStudentName());
				stu.setHeadPhoto(stu.getFaceImageUrl());
				stu.setStudentName(name);
				SocietyStudent societyStudent = service.selectOneByIdCardNumAndSchool(stu.getId(),schoolId);
				societyStudent.setFaceImageUrl(stu.getFaceImageUrl());
				societyStudent.setIdCardImage(stu.getIdCardImage());
				societyStudent.setHeadPhoto(stu.getFaceImageUrl());
				societyStudent.setIsRealNameAuth("1");
				service.update(societyStudent);
				studentUniqueService.updateStudentRealName(stu);
				studentUniqueService.clearStudentCatch(stu);
			}catch (Exception e){
				e.printStackTrace();
				stu.setIsRealNameAuth("0");
				studentUniqueService.updateStudentRealName(stu);
				studentUniqueService.clearStudentCatch(stu);
			}

		}

		result.put("state",state);
		result.put("failReason",failReason);
		return buildResponse(modelAndView,result);

	}

	/**
	 * @Author ZhangCC
	 * @Description ???????????????????????????????????????
	 * @Date 2020/5/25 16:14
	 **/
	@Pass
	@RequestMapping("/studentSubmitRealName")
	public ModelAndView studentSubmitRealName(@ModelAttribute SocietyStudentUnique student){
		ModelAndView modelAndView = new ModelAndView();
		/*if(true){//TODO:???????????????????????????????????????
			return buildErrorResponse(modelAndView,"???????????????????????????.");
		}*/
		Map<String,Object> stuRealNameInfo = new HashMap<>();
		try{
			String idCardImage = student.getIdCardImage();
			if(idCardImage == null || idCardImage.equals("")){
				return buildErrorResponse(modelAndView,"?????????????????????????????????");
			}
			String faceImageUrl = student.getFaceImageUrl();
			if(faceImageUrl == null || faceImageUrl.equals("")){
				return buildErrorResponse(modelAndView,"?????????????????????????????????");
			}
			if(idCardImage != null && idCardImage.indexOf("tempfile") == -1){
				String idCardImgPath = FileUtil.saveBase64Img(idCardImage,fileForder + File.separator + "tempfile",true);
				idCardImgPath = "/tempfile" + idCardImgPath;
				stuRealNameInfo.put("idCardImgPath",idCardImgPath);
			}
			if(faceImageUrl != null && faceImageUrl.indexOf("tempfile") == -1){
				String faceImagePath = FileUtil.saveBase64Img(faceImageUrl,fileForder + File.separator + "tempfile",true);
				faceImagePath = "/tempfile"+faceImagePath;
				stuRealNameInfo.put("faceImagePath",faceImagePath);
			}
			//??????????????????
			String faceToken = faceAiService.submitFaceMatch(
					idCardImage,FaceMatchInfo.FaceTypeEnum.IDCARD,
					faceImageUrl,FaceMatchInfo.FaceTypeEnum.LIVE);
			//?????????????????????
			String idCardToken = textFenxiAiService.submitIdCardFenxi(idCardImage);
			//????????????token??????????????????
			Map<String,Object> tokenMap = new HashMap<>();
			tokenMap.put("faceToken",faceToken);
			tokenMap.put("idCardToken",idCardToken);
			//???????????????????????????????????????????????????5??????
			redisUtil.hmset(RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX+getAccessToken(),tokenMap,300);
		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView,stuRealNameInfo);
	}

	/**
	 * @Author ZhangCC
	 * @Description ???????????????????????????????????????
	 * @Date 2020/5/25 16:33
	 **/
	@Pass
	@RequestMapping("/checkStudentRegisterRealNameInfo")
	public ModelAndView checkStudentRegisterRealNameInfo(@ModelAttribute SocietyStudentView student, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		/*if(true){//TODO:???????????????????????????????????????
			return buildErrorResponse(modelAndView,"???????????????????????????.");
		}*/
		String at = getAccessToken();
		String key = RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX+at;
		Map<String,Object > result = new HashMap<>();
		String state = "notpass";
		String failReason = "";
		Object faceToken = redisUtil.hget(key,"faceToken");
		Double score = faceAiService.queryFaceMatchScore(faceToken+"");
		if(score!=null) {
			if((score.intValue()+99)==0){//??????
				state = "wait";
			}else if(score.doubleValue()>60){//??????????????????
				state = "pass";
			}else{
				state = "notpass";
				failReason = "???????????????????????????????????????!";
				System.out.println("notpass:" + score);
			}
		}
		if(state.equals("pass")){
			Object idCardToken = redisUtil.hget(key,"idCardToken");
			IdCardResult idResult = textFenxiAiService.queryIdCard(idCardToken+"");
			if(idResult != null){
				String idCardState = idResult.getState();
				if(idCardState!=null && idCardState.equals("success")){
					//??????????????????????????????????????????
					String schoolId = getSchoolId();
					String idCardNum = idResult.getIdCardNum();
					SocietyStudent checkStu = service.selectOneByIdCardNumAndSchool(idCardNum,schoolId);
					if(checkStu != null){
						//???????????????????????????????????????
						String checkPhoneNum = checkStu.getStudentPhoneNum();
						String stuPhoneNum = student.getStudentPhoneNum();
						if(checkPhoneNum != null && !checkPhoneNum.equals(stuPhoneNum)){
							SocietyStudent checkPhoneStu = service.selectOneByPhoneNumAndSchool(stuPhoneNum,schoolId);
							if(checkPhoneStu != null){
								return buildErrorResponse(modelAndView,"?????????????????????????????????");
							}
						}
						String isRealNameAuth = checkStu.getIsRealNameAuth();
						if(isRealNameAuth != null && isRealNameAuth.equals("1")){
							//???????????????
							String stuId = checkStu.getId();
							checkStu.setStudentPhoneNum(student.getStudentPhoneNum());
							String encodePwd = MD5.encode(MD5.encode(student.getPassword())+stuId);
							checkStu.setPassword(encodePwd);
							service.update(checkStu);

							//??????redis????????????????????????
							service.clearStudentCatch(checkStu);

							Map<String,Object> param = new HashMap<>();
							param.put("studentPhoneNum",student.getStudentPhoneNum());
							param.put("password",student.getPassword());
							param.put("state","already_real_name");
							return buildResponse(modelAndView,param);
						}else{
							student.setId(checkStu.getId());
						}
					}else{
						//??????????????????????????????????????????????????????
						String stuPhoneNum = student.getStudentPhoneNum();
						SocietyStudent checkPhoneStu = service.selectOneByPhoneNumAndSchool(stuPhoneNum,schoolId);
						if(checkPhoneStu != null){
							return buildErrorResponse(modelAndView,"?????????????????????????????????");
						}
					}

					student.setStudentIdCardNum(idResult.getIdCardNum());
					student.setStudentName(idResult.getName());
					student.setStudentSex(idResult.getSex());
					int age = IdCardUtil.getAgeByIdCard(idResult.getIdCardNum());
					student.setStudentAge(age);
				}else{
					state="notpass";
					failReason = "????????????????????????!";
				}
			}else{
				state="wait";
			}
		}
		// ?????????????????????????????????????????????
		if(state.equals("pass")){
			String temFaceImageUrl = student.getFaceImageUrlParam();
			String temIdCardImage = student.getIdCardImageParam();
			String faceFilePath = fileForder + FileUtil.changeWebPathToFilePath(temFaceImageUrl);
			String idCardFilePath =  fileForder + FileUtil.changeWebPathToFilePath(temIdCardImage);
			String schoolId = getSchoolId();
			new Thread(()->{
				try {
					String faceWebPath = FileUtil.saveFile(
							new File(faceFilePath),
							fileForder +
									File.separator
									+ schoolId
									+ File.separator + "faceimg", true);
					String idCardWebPath = FileUtil.saveFile(
							new File(idCardFilePath),
							fileForder +
									File.separator
									+ schoolId
									+ File.separator + "idcard", true);
					student.setFaceImageUrl("/" + schoolId + "/faceimg" + faceWebPath);
					student.setIdCardImage("/" + schoolId + "/idcard" + idCardWebPath);
					student.setHeadPhoto(student.getFaceImageUrl());
					student.setNickName(student.getNickName());
					student.setIsRealNameAuth("1");
					SocietySchool school = schoolService.loadById(schoolId);
					student.setOwnerSchoolId(schoolId);
					student.setOwnerSchoolName(school.getSchoolName());
					service.insertOrUpdateInfoAndEncodePwd(student);
					//??????redis????????????????????????
					service.clearStudentCatch(student);
				}catch (Exception e){
					e.printStackTrace();
				}
			}).start();
		}
		result.put("state",state);
		result.put("failReason",failReason);
		return buildResponse(modelAndView,result);
	}

	/**
	 * @Author WangZhen
	 * @Description ????????????????????????????????????????????????????????????????????????
	 * @Date 2020/5/5 10:29
	 **/
	@RequestMapping("/subLeaFaceMat")
	public ModelAndView subLeaFaceMat( String face){
		String accessToken =  getAccessToken();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		String faceUrl = student.getFaceImageUrl();
		ModelAndView mav = new ModelAndView();
		//1.???????????????????????????
		String relativePath = FileUtil.changeWebPathToFilePath(faceUrl);
		String realPath  = fileForder + relativePath;
		//????????????
		String face1 = FileUtil.getImageBase64Str(new File(realPath));
		//??????????????????
		try {
			//????????????????????????40k ??????????????????
			int base64Length = face.length();
			int base64Kb = (int)(base64Length*0.75/1024);
			if(base64Kb>=app_faceai_maxfacelength){
				face = FileUtil.compressImg(face,app_faceai_imgcompresswidth);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//2.??????????????????
		String token = faceAiService.submitFaceMatch(face1,face);
		//3.???????????????????????????
		try {
			String path = FileUtil.saveBase64Img(face,fileForder + File.separator + "tempfile",true);
			path = "/tempfile" + path;
			//?????????????????????
			FaceMatchInfoExt faceMatchInfo = new FaceMatchInfoExt();
			faceMatchInfo.setFace1(faceUrl);
			faceMatchInfo.setFace2(path);
			faceMatchInfo.setId(token);
			faceMatchInfo.setAt(accessToken);
			//????????????????????????5??????
			String key = RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX + student.getId();
			redisUtil.set(key,faceMatchInfo,300);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//4.???token??????
		return buildResponse(mav,token);

	}


	/**
	 * @Author WangZhen
	 * @Description ????????????????????????????????????????????????????????????????????????
	 * @Date 2020/5/5 10:29
	 **/
	@RequestMapping("/checkLeaFaceMat")
	public ModelAndView checkLeaFaceMat(String token,String nodeId,String stuCourseId){
		String schoolId = getSchoolId();
		if(schoolId==null && schoolId.trim().equals("")){
			schoolId = "globle";
		}
		String accessToken =  getAccessToken();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		String key = RedisKeyConstant.STUDENT_LEARN_FACE_PREFIX + student.getId();
		ModelAndView mav = new ModelAndView();
		FaceMatchInfoExt faceMatchInfo = (FaceMatchInfoExt)redisUtil.get(key);
		String faceState = "notpass";
		if(faceMatchInfo != null){
			//??????token
			String acctoken = faceMatchInfo.getAt();
			//??????token
			String faceToken = faceMatchInfo.getId();

			if(accessToken.equals(acctoken) && token.equals(faceToken)){
				boolean passSta = faceMatchInfo.isPass();
				if(passSta){
					faceState = "pass";
				}else{
					//????????????????????????????????????
					Double score = faceAiService.queryFaceMatchScore(token);
					if(score!=null){
						if(score.doubleValue()==-99){//??????
							faceState = "wait";
						}else{
							if(score.doubleValue()>60){//??????????????????
								faceState = "pass";
								//??????????????????????????????????????????
								faceMatchInfo.setPass(true);
								faceMatchInfo.setScore(score);
								//TODO:?????????????????????????????????,??????????????????????????????
								//TODO: ??????????????????????????????
								//?????????????????????????????????????????????????????????????????????????????????????????????
								String faceMatchMinute = "";
								SocietySchoolConfig schoolConfig = schoolConfigService.selectBySchoolIdAndKey(schoolId,"FACE_MATCH_MINUTE");
								if(schoolConfig==null){
									SocietySchoolConfigKey schoolConfigKey = schoolConfigKeyService.loadById("FACE_MATCH_MINUTE");
									faceMatchMinute = schoolConfigKey.getConfigValue();
								}else {
									faceMatchMinute = schoolConfig.getConfigValue();
								}
								/*int maxMinute = 100;//??????100??????
								int minMinute = 10;//??????10??????
								int random = new Random().nextInt(maxMinute);
								if(random<minMinute){
									random = minMinute;
								}*/
								//?????????????????????????????????
								SocietySchoolCourseNode node = null;
								if(nodeId!=null && nodeId.length()>0){
									node  = courseAndNodeService.loadById(nodeId);
									if(node!=null){//????????????
										//???????????????????????????20??????6????????????
										int nodeTimeLength = node.getNodeTimeLength();
										if(nodeTimeLength/60<20){
											faceMatchMinute = "6";
										}

									}
								}
								//?????????????????????
								redisUtil.set(
										key,faceMatchInfo,
										Integer.parseInt(faceMatchMinute)*60);

								//?????????????????????
								studyProcessFaceService.insertFaceMatch(
										student,faceMatchInfo,schoolId,node,stuCourseId);

							}else{
								faceState = "notpass";
							}

						}
					}

				}
			}

		}

		return buildResponse(mav,faceState);

	}

	/**
	 * @Author WangZhen
	 * @Description ????????????????????????????????????????????????????????????????????????
	 * @Date 2020/5/5 10:29
	 **/
	@RequestMapping("/subFaceDetect")
	public ModelAndView subFaceDetect(String face){
		ModelAndView mav = new ModelAndView();
		if(studyfacecheck) {
			String accessToken = getAccessToken();
//		SocietyStudent student = getLoginStudent(service);

			//1.??????????????????
			String token = faceAiService.submitFaceDetect(face);
			//2.???token??????
			return buildResponse(mav, token);
		}else{
			return buildResponse(mav, Guid.guid());
		}
	}

	/**
	 * @Author WangZhen
	 * @Description ????????????????????????????????????????????????????????????????????????
	 * @Date 2020/5/5 10:29
	 **/
	@RequestMapping("/checkFaceDetect")
	public ModelAndView checkFaceDetect(String token){
		String accessToken =  getAccessToken();
//		SocietyStudent student = getLoginStudent(service);
		ModelAndView mav = new ModelAndView();
		//??????????????????????????????????????????
		if(studyfacecheck) {
			Integer faceNum = faceAiService.queryFaceDetectNum(token);
			if (faceNum == null) {
				faceNum = 0;
			}
			//?????????-99 ??????????????????????????????????????????
			return buildResponse(mav, faceNum);
		}else{
			return buildResponse(mav, 1);
		}

	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????
	 * @Date 2020/5/19 15:10
	 **/
	@RequestMapping("/selectStuCertListPage")
	public ModelAndView selectStuCertListPage(@ModelAttribute SocietyStudentCertificateQuery certQuery){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		certQuery.getQueryObj().setStudentIdCardNum(student.getId());
		certificateService.listPage(certQuery);
		return  buildResponse(modelAndView,certQuery);
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????
	 * @Date 2020/5/19 16:03
	 **/
	@RequestMapping("/selectStuCertInfo")
	public ModelAndView selectStuCertInfo(String stuCertId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		SocietyStudentCertificate stuCert = certificateService.loadById(stuCertId);
		String idCard = student.getId();
		String idCardCert = stuCert.getStudentIdCardNum();
		if(!idCard.equals(idCardCert)){
			return buildErrorResponse(modelAndView,"???????????????");
		}
		return  buildResponse(modelAndView,stuCert);
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????????????????????????????
	 * @Date 2020/5/19 16:03
	 **/
	//TODO:?????????
	@RequestMapping("/selectStuCertInfoByCourse")
	public ModelAndView selectStuCertInfoByCourse(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		SocietyStudentCertificate stuCert = certificateService.selectCertByStuIdCardAndCourse(student.getId(),courseId);
		if(stuCert == null){
			return buildErrorResponse(modelAndView,"???????????????????????????");
		}
		return  buildResponse(modelAndView,stuCert);
	}



	
}
