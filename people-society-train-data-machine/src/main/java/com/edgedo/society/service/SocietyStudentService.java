package com.edgedo.society.service;
		
import java.io.*;
import java.util.*;

import com.edgedo.common.base.BusinessException;
import com.edgedo.common.constant.ThirdPartyType;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.*;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolClassAndStudentMapper;
import com.edgedo.society.mapper.SocietyStudentMapper;
import com.edgedo.society.queryvo.SocietyStudentQuery;
import com.edgedo.society.queryvo.SocietyStudentView;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentService {
	@Autowired
	RedisUtil redisUtil;
	
	@Autowired
	private SocietyStudentMapper societyStudentMapper;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietySchoolClassAndStudentService classAndStudentService;
	@Autowired
	private SocietySchoolClassAndCourseService classAndCourseService;
	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;
	@Autowired
	private SocietySchoolCourseService schoolCourseService;
	@Autowired
	private SocietyStudentService societyStudentService;
	@Autowired
	private SocietyStudentUniqueService societyStudentUniqueService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;

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
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	@Autowired
	private SocietyStudentPractiseQuestOptionService societyStudentPractiseQuestOptionService;
	@Autowired
	private SocietyStudentTestPaperService societyStudentTestPaperService;
	@Autowired
	private SocietyStudentTestPaperQuestionService societyStudentTestPaperQuestionService;
	@Autowired
	private SocietyStudentTestPaperQuestionOptionService societyStudentTestPaperQuestionOptionService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;

	public List<SocietyStudentView> listPage(SocietyStudentQuery societyStudentQuery){
		List list = societyStudentMapper.listPage(societyStudentQuery);
		societyStudentQuery.setList(list);
		return list;
	}

	public List<SocietyStudentView> listByObj(SocietyStudentQuery societyStudentQuery){
		List list = societyStudentMapper.listByObj(societyStudentQuery);
		societyStudentQuery.setList(list);
		return list;
	}

	/***
	 * ????????????
	 * @return
	 */
	public Integer count(SocietyStudentQuery query) {
		int num = societyStudentMapper.count(query);
		return num;
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????
	 * @Date 2020/5/4 9:33
	 **/
	public List<SocietyStudentView> classStudentListPage(SocietyStudentQuery societyStudentQuery){
		List list = societyStudentMapper.classStudentListPage(societyStudentQuery);
		societyStudentQuery.setList(list);
		return list;
	}

	public List<SocietyStudentView> schoolClassAdminStudentListpage(SocietyStudentQuery societyStudentQuery){
		List list = societyStudentMapper.classStudentListPage(societyStudentQuery);
		societyStudentQuery.setList(list);
		return list;
	}

	/**
	 * @Author ZhangCC
	 * @Description ?????????????????????????????????
	 * @Date 2020/5/5 8:51
	 **/
	public List<SocietyStudentView> classNotInStudentListPage(SocietyStudentQuery query){
		List list = societyStudentMapper.classNotInStudentListPage(query);
		query.setList(list);
		return list;
	}

	public List<SocietyStudentView> classYesInStudentListPage(SocietyStudentQuery query){
		List list = societyStudentMapper.classYesInStudentListPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * ????????????
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudent societyStudent) {
		societyStudent.setId(Guid.guid());
		societyStudent.setCreateTime(new Date());
		societyStudent.setDataState("1");
		String schoolId = societyStudent.getOwnerSchoolId();
		//???????????????
		String phoneNum = societyStudent.getStudentPhoneNum();
		SocietyStudent studentByPhone = societyStudentMapper.selectVoByPhoneNum(phoneNum,schoolId);
		if(studentByPhone != null){
			return "????????????????????????";
		}
		//??????????????????
		String idCardNum = societyStudent.getStudentIdCardNum();
		SocietyStudent studentByIdCard = societyStudentMapper.selectVoByIdCardNum(idCardNum,schoolId);
		if(studentByIdCard != null){
			return "???????????????????????????";
		}

		//??????????????????123456
		String stuId = societyStudent.getId();
		String password = "123456";
		String pwd = MD5.encode(MD5.encode(password)+stuId);
		societyStudent.setPassword(pwd);
		societyStudent.setIsRealNameAuth("0");
		//???????????????????????????
		String esg = insertStuUnique(societyStudent);
		if(!esg.equals("")){
			return esg;
		}
		return "";
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertStuUnique(SocietyStudent stu) {
		String idCard = stu.getStudentIdCardNum();
		String stuPhoneNum = stu.getStudentPhoneNum();
		SocietyStudentUnique stuOraUnique = societyStudentUniqueService.loadById(idCard);
		if(stuOraUnique==null){
			SocietyStudentUnique stuUnique = new SocietyStudentUnique();
			stuUnique.setId(stu.getStudentIdCardNum());
			stuUnique.setIsRealNameAuth(stu.getIsRealNameAuth());
			stuUnique.setFaceImageUrl(stu.getFaceImageUrl());
			stuUnique.setIdCardImage(stu.getIdCardImage());
			stuUnique.setAccessToken(stu.getAccessToken());
			stuUnique.setHeadPhoto(stu.getHeadPhoto());
			stuUnique.setIsUpPwd("0");
			stuUnique.setNickName(stu.getNickName());
			//????????????123456
			String stuId = stuUnique.getId();
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
			societyStudentMapper.insert(stu);
			return "";
		}else{
			//???????????????????????????????????????????????????????????????
			String oraPhone = stuOraUnique.getStudentPhoneNum();
			//???????????????????????????
			if(!oraPhone.equals(stuPhoneNum)){//??????????????????????????????????????????
				//???????????????????????? ???????????????
				return stuOraUnique.getStudentName()+"("+oraPhone+")";
			}else {//??????
				stu.setStudentName(stuOraUnique.getStudentName());
				stu.setStudentSex(stuOraUnique.getStudentSex());
				stu.setStudentAge(stuOraUnique.getStudentAge());
				stu.setStudentPhoneNum(stuOraUnique.getStudentPhoneNum());
				stu.setStudentIdCardNum(stuOraUnique.getStudentIdCardNum());
				stu.setIsRealNameAuth(stuOraUnique.getIsRealNameAuth());
				stu.setWxOpenId(stuOraUnique.getWxOpenId());
				stu.setIdCardImage(stuOraUnique.getIdCardImage());
				stu.setFaceImageUrl(stuOraUnique.getFaceImageUrl());
				stu.setHeadPhoto(stuOraUnique.getHeadPhoto());
				stu.setNickName(stuOraUnique.getNickName());
				stu.setAccessToken(stuOraUnique.getAccessToken());
				stu.setRealNameTime(stuOraUnique.getRealNameTime());
				stu.setRealNameType(stuOraUnique.getRealNameType());
				stu.setRealNameOperName(stuOraUnique.getRealNameOperName());
				stu.setRealNameOperId(stuOraUnique.getRealNameOperId());
				societyStudentMapper.insert(stu);
				return "";
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietyStudentUnique insertStuUniqueInNull(SocietyStudent stu) {
		SocietyStudentUnique stuUnique = new SocietyStudentUnique();
		stuUnique.setId(stu.getStudentIdCardNum());
		stuUnique.setIsRealNameAuth(stu.getIsRealNameAuth());
		stuUnique.setFaceImageUrl("");
		stuUnique.setIdCardImage("");
		stuUnique.setAccessToken(stu.getAccessToken());
		stuUnique.setHeadPhoto(stu.getHeadPhoto());
		stuUnique.setIsUpPwd("0");
		stuUnique.setNickName(stu.getNickName());
		//????????????123456
		String stuId = stuUnique.getId();
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
		return stuUnique;
	}

	/***
	 * ????????????????????????
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertForClass(SocietyStudent societyStudent, SocietySchoolClass schoolClass) {
		String errMsg = "";
		errMsg = insert(societyStudent);
		if(errMsg == null || errMsg.equals("")){
			//??????????????????????????????
			SocietySchoolClassAndStudent classAndStudent = new SocietySchoolClassAndStudent();
			classAndStudent.setOwnerSchoolId(schoolClass.getOwnerSchoolId());
			classAndStudent.setOwnerSchoolName(schoolClass.getOwnerSchoolName());
			classAndStudent.setOwnerMajorId(schoolClass.getOwnerMajorId());
			classAndStudent.setOwnerMajorName(schoolClass.getOwnerMajorName());
			classAndStudent.setStudentName(societyStudent.getStudentName());
			classAndStudent.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
			classAndStudent.setClassId(schoolClass.getId());
			classAndStudent.setStudentId(societyStudent.getId());
			classAndStudentService.insert(classAndStudent);
			//??????????????????????????????????????????
			Integer classPersonNum = schoolClass.getClassPersonNum();
			if(classPersonNum == null){
				classPersonNum = 1;
			}else{
				classPersonNum++;
			}
			schoolClass.setClassPersonNum(classPersonNum);
			Integer notFinishedPersonNum = schoolClass.getNotFinishedPersonNum();
			if(notFinishedPersonNum == null){
				notFinishedPersonNum = 1;
			}else{
				notFinishedPersonNum++;
			}
			schoolClass.setNotFinishedPersonNum(notFinishedPersonNum);
			schoolClassService.update(schoolClass);
			//??????????????????????????????
			String classId = schoolClass.getId();
			SocietyStudentAndCourse studentAndCourse = new SocietyStudentAndCourse();
			studentAndCourse.setOwnerSchoolId(societyStudent.getOwnerSchoolId());
			studentAndCourse.setClassId(classId);
			studentAndCourse.setStudentId(societyStudent.getId());
			studentAndCourse.setStudentName(societyStudent.getStudentName());
			studentAndCourse.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
			List<String> courseIdList = classAndCourseService.selectCourseIdByClass(classId);
			for(int i =0;i<courseIdList.size();i++){
				SocietySchoolCourse course = schoolCourseService.loadById(courseIdList.get(i));
				if(course != null){
					studentAndCourse.setCourseId(course.getId());
					studentAndCourse.setCourseName(course.getCourseName());
					studentAndCourse.setCourseImage(course.getCourseImage());
					studentAndCourseService.insert(studentAndCourse);
				}
			}

		}
		return errMsg;
	}
	
	/***
	 * ??????????????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudent societyStudent) {
		//?????????????????????
		String id = societyStudent.getId();
		SocietyStudent oraStudent = societyStudentMapper.selectById(id);
		/*//????????????????????????????????????????????????
		String isRealNameAuth = oraStudent.getIsRealNameAuth();
		if("1".equals(isRealNameAuth)){
			societyStudent.setStudentName(null);
			societyStudent.setStudentIdCardNum(null);
		}*/
		String oraIdCard = oraStudent.getStudentIdCardNum();
		String newIdCard = societyStudent.getStudentIdCardNum();
		if(newIdCard != null && !newIdCard.equals(oraIdCard)){
			//???????????????????????????
			SocietyStudent checkStudent = societyStudentMapper.selectVoByIdCardNum(newIdCard,societyStudent.getOwnerSchoolId());
			if(checkStudent != null){
				return "???????????????????????????";
			}
		}
		String oraPhone = oraStudent.getStudentPhoneNum();
		String newPhone = societyStudent.getStudentPhoneNum();
		if(newPhone != null && !newPhone.equals(oraPhone)){
			//????????????????????????
			SocietyStudent checkStudent = societyStudentMapper.selectVoByPhoneNum(newPhone,societyStudent.getOwnerSchoolId());
			if(checkStudent != null){
				return "????????????????????????";
			}
		}

		//?????????

		SocietyStudentUnique societyStudentUniqueOld =
				societyStudentUniqueService.loadById(oraStudent.getStudentIdCardNum());
		if(societyStudentUniqueOld==null){
			societyStudentMapper.updateById(societyStudent);
			SocietyStudent stu = loadById(societyStudent.getId());
			SocietyStudentUnique stuUnique = new SocietyStudentUnique();
			stuUnique.setId(stu.getStudentIdCardNum());
			stuUnique.setIsRealNameAuth(stu.getIsRealNameAuth());
			stuUnique.setFaceImageUrl(stu.getFaceImageUrl());
			stuUnique.setIdCardImage(stu.getIdCardImage());
			stuUnique.setAccessToken(stu.getAccessToken());
			stuUnique.setHeadPhoto(stu.getHeadPhoto());
			stuUnique.setIsUpPwd("0");
			stuUnique.setNickName(stu.getNickName());
			//????????????123456
			String stuId = stuUnique.getId();
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
		}else {
			String uniqIdCardOld = societyStudentUniqueOld.getStudentIdCardNum();
			String uniqIdCardNew = societyStudent.getStudentIdCardNum();
			if(uniqIdCardNew != null && !uniqIdCardNew.equals(uniqIdCardOld)){
				//???????????????????????????
				SocietyStudentUnique checkStudent = societyStudentUniqueService.loadById(uniqIdCardNew);
				if(checkStudent != null){
					return "???????????????????????????";
				}else {
					societyStudentUniqueOld.setStudentIdCardNum(uniqIdCardNew);
				}
			}
			String uniqPhoneOld = societyStudentUniqueOld.getStudentPhoneNum();
			String uniqPhoneNew = societyStudent.getStudentPhoneNum();
			if(uniqPhoneNew != null && !uniqPhoneNew.equals(uniqPhoneOld)){
				//????????????????????????
				SocietyStudentUnique checkStudent = societyStudentUniqueService.selectByPhone(newPhone);
				if(checkStudent != null){
					return "????????????????????????";
				}else {
					societyStudentUniqueOld.setStudentPhoneNum(uniqPhoneNew);
				}
			}

			societyStudentUniqueOld.setStudentName(societyStudent.getStudentName());
			societyStudentUniqueOld.setStudentSex(societyStudent.getStudentSex());
			societyStudentUniqueOld.setStudentAge(societyStudent.getStudentAge());
			societyStudentMapper.updateById(societyStudent);
			societyStudentUniqueService.updateByAllId(societyStudentUniqueOld);
		}

		return "";
	}
	
	/***
	 * ?????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudent societyStudent) {
		societyStudentMapper.updateAllColumnById(societyStudent);
		return "";
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentMapper.deleteById(id);
	}
	
	/**
	 * society_student
	 * society_student_unique
	 * society_school_class_and_student
	 * society_student_and_course
	 * society_student_and_node
	 * society_student_certificate
	 * society_student_practise_quest_option
	 * society_student_practise_question
	 * society_student_test_paper
	 * society_student_test_paper_question
	 * society_student_test_paper_question_option
	 * ????????????
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String deleteExcInfo(List<String> ids) {
		//1.????????????  ????????????
		for (String stuId : ids){
			SocietyStudent societyStudent = loadById(stuId);
			SocietyStudentUnique societyStudentUnique =
					societyStudentUniqueService.loadById(societyStudent.getStudentIdCardNum());
			if(societyStudent.getIsRealNameAuth().equals("1")){
				return societyStudent.getStudentName();
			}
			if(societyStudentUnique.getIsRealNameAuth().equals("1")){
				return societyStudentUnique.getStudentName();
			}
			clearStudentCatch(societyStudent);
			societyStudentUniqueService.clearStudentCatch(societyStudentUnique);
			//??????id??????society_student_and_node ?????????????????????
			societyStudentAndNodeService.deleteByStuId(societyStudent.getId());
			//????????????id??????????????????
			studentAndCourseService.deleteByStuId(societyStudent.getId());
			//????????????id ??????????????????
			societyStudentPractiseQuestionService.deleteByStuId(societyStudent.getId());
			//????????????id ????????????????????????
			societyStudentPractiseQuestOptionService.deleteByStuId(societyStudent.getId());
			//????????????id ?????????????????????
			societyStudentTestPaperService.deleteByStuId(societyStudent.getId());
			//????????????id ????????????????????? ??????
			societyStudentTestPaperQuestionService.deleteByStuId(societyStudent.getId());
			//????????????id ????????????????????? ??????
			societyStudentTestPaperQuestionOptionService.deleteByStuId(societyStudent.getId());
			//????????????id ??????????????????
			societyStudentCertificateService.deleteByStuId(societyStudent.getId());
			//????????????id ????????????????????????
			classAndStudentService.deleteByStuId(societyStudent.getId());
			//??????????????????????????????
			societyStudentMapper.deleteByStuId(societyStudent.getId());
			//??????????????????????????????????????????
			societyStudentUniqueService.deleteByStuId(societyStudent.getStudentIdCardNum());
		}
		return "";
	}


	/**
	 * society_student
	 * society_student_unique
	 * society_school_class_and_student
	 * society_student_and_course
	 * society_student_and_node
	 * society_student_certificate
	 * society_student_practise_quest_option
	 * society_student_practise_question
	 * society_student_test_paper
	 * society_student_test_paper_question
	 * society_student_test_paper_question_option
	 * ????????????
	 * @param voObj
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateExceInfo(SocietyStudent voObj) {
		SocietyStudent societyStudent = loadById(voObj.getId());

		SocietyStudentUnique societyStudentUnique =
				societyStudentUniqueService.loadById(societyStudent.getStudentIdCardNum());

		if(societyStudent.getIsRealNameAuth().equals("1")){
			return societyStudent.getStudentName();
		}
		if(societyStudentUnique.getIsRealNameAuth().equals("1")){
			return societyStudentUnique.getStudentName();
		}

		Map<String,String> map = new HashMap<>();
		map.put("stuId",voObj.getId());
		map.put("stuName",voObj.getStudentName());
		map.put("idcard",voObj.getStudentIdCardNum());
		map.put("phone",voObj.getStudentPhoneNum());
		map.put("uniqeIdCard",societyStudent.getStudentIdCardNum());
		//????????????id ??????????????????????????? ????????? ??????
		societyStudentAndNodeService.updateByStuId(map);
		//????????????id??????????????????
		studentAndCourseService.updateByStuId(map);
		//????????????id ??????????????????
		societyStudentPractiseQuestionService.updateByStuId(map);
		//????????????id ????????????????????????
//		societyStudentPractiseQuestOptionService.updateByStuId(map);
		//????????????id ?????????????????????
		societyStudentTestPaperService.updateByStuId(map);
		//????????????id ????????????????????? ??????
//		societyStudentTestPaperQuestionService.updateByStuId(map);
		//????????????id ????????????????????? ??????
//		societyStudentTestPaperQuestionOptionService.updateByStuId(map);
		//????????????id ??????????????????
		societyStudentCertificateService.updateByStuId(map);
		//????????????id ????????????????????????
		classAndStudentService.updateByStuId(map);
		//??????????????????????????????
		societyStudentMapper.updateByStuId(map);
		//??????????????????????????????????????????
		societyStudentUniqueService.updateByStuId(map);
		return "";
	}


	/**
	 *
	 * @param ids
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {

		return societyStudentMapper.deleteBatchIds(ids);
	}



	/**
	 * ????????????
	 * @param id
	 */
	public SocietyStudent loadById(String id) {
		return societyStudentMapper.selectById(id);
	}

	public void logicDelete(List<String> list){
		societyStudentMapper.logicDelete(list);
	}

	//????????????
	public int countStudentAllNum() {
		return societyStudentMapper.countStudentAllNum();
	}

	//* ??????id?????????????????????
	public void resetPwdById(String id) {
		SocietyStudent societyStudent = loadById(id);
		String password = "123456";
		String pwd = MD5.encode(MD5.encode(password)+id);
		societyStudent.setPassword(pwd);
		societyStudentMapper.updateById(societyStudent);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void excelImportStu(List<SocietyStudent> studentList) {
		Map<String,String> map = new HashMap<>();
		for (SocietyStudent s:studentList){
			String schoolId = s.getOwnerSchoolId();
			String studentName = s.getStudentName();
   			if(studentName==null || studentName.equals("")){
				continue;
			}
			String phoneNum = s.getStudentPhoneNum();
			if(phoneNum==null || phoneNum.equals("")){
				continue;
			}
			String idCardNum = s.getStudentIdCardNum();
			if(idCardNum==null || idCardNum.equals("") || !IdCardUtil.isValidatedAllIdcard(idCardNum)){
				continue;
			}
			//??????
			String studentSex = s.getStudentSex();
			if(studentSex==null || studentSex.equals("")){
				//????????????????????????
				s.setStudentSex(IdCardUtil.getGenderByIdCard(idCardNum));
			}
			//??????
			Integer studentAge = s.getStudentAge();
			if(studentAge==null || studentAge == 0){
				//????????????????????????
				s.setStudentAge(IdCardUtil.getAgeByIdCard(idCardNum));
			}
			/*map.put("schoolId",schoolId);
			map.put("phoneNum",phoneNum);
			map.put("idCardNum",idCardNum);
			//?????????????????????????????????????????????????????????
			int count = societyStudentMapper.countByPhoneNumOrIdCardNum(map);
			if(count>0){
				continue;
			}*/
			String password = "123456";
			String pwd = MD5.encode(MD5.encode(password)+s.getId());
			s.setPassword(pwd);
			//????????????
			s.setNickName(s.getStudentName());
			insertStuUnique(s);
			societyStudentMapper.insert(s);
		}
	}



	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Map<String,List<String>> excelImportStuInsert(List<SocietyStudent> studentList) {
		Map<String,List<String>> map = new HashMap<>();
		List<String> repList = new ArrayList<>();
		List<String> phoneList = new ArrayList<>();
		for (SocietyStudent s:studentList){
			String schoolId = s.getOwnerSchoolId();
			String studentName = s.getStudentName();
			if(studentName==null || studentName.equals("")){
				continue;
			}
			String phoneNum = s.getStudentPhoneNum();
			SocietyStudent studentByPhone = societyStudentMapper.selectVoByPhoneNum(phoneNum,schoolId);
			if(studentByPhone != null){
				repList.add(s.getStudentName());
				continue;
			}
			//??????????????????
			String idCardNum = s.getStudentIdCardNum();
			SocietyStudent studentByIdCard = societyStudentMapper.selectVoByIdCardNum(idCardNum,schoolId);
			if(studentByIdCard != null){
				repList.add(s.getStudentName());
				continue;
			}
			//??????
			String studentSex = s.getStudentSex();
			if(studentSex==null || studentSex.equals("")){
				//????????????????????????
				s.setStudentSex(IdCardUtil.getGenderByIdCard(idCardNum));
			}
			//??????
			Integer studentAge = s.getStudentAge();
			if(studentAge==null || studentAge == 0){
				//????????????????????????
				s.setStudentAge(IdCardUtil.getAgeByIdCard(idCardNum));
			}
			/*map.put("schoolId",schoolId);
			map.put("phoneNum",phoneNum);
			map.put("idCardNum",idCardNum);
			//?????????????????????????????????????????????????????????
			int count = societyStudentMapper.countByPhoneNumOrIdCardNum(map);
			if(count>0){
				continue;
			}*/
			if(s.getPeopleType()==null || "".equals(s.getPeopleType())){
				s.setPeopleType("ORD_STU");
			}
			String password = "123456";
			String pwd = MD5.encode(MD5.encode(password)+s.getId());
			s.setPassword(pwd);
			//????????????
			s.setNickName(s.getStudentName());
			String esgPhone = insertStuUnique(s);
			if(!esgPhone.equals("")){
				phoneList.add(esgPhone);
			}
		}
		map.put("repList",repList);
		map.put("phoneList",phoneList);
		return map;
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Map<String,Object> excelImportStuAndClassInsert(List<SocietyStudent> studentList) {
		Map<String,Object> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		List<String> phoneList = new ArrayList<>();
		List<SocietyStudent> stuList = new ArrayList<>();
		for (SocietyStudent s:studentList){
			String schoolId = s.getOwnerSchoolId();
			String studentName = s.getStudentName();
			if(studentName==null || studentName.equals("")){
				continue;
			}
			String phoneNum = s.getStudentPhoneNum();
			SocietyStudent studentByPhone = societyStudentMapper.selectVoByPhoneNum(phoneNum,schoolId);
			if(studentByPhone != null){
				list.add(s.getStudentName());
				continue;
			}
			//??????????????????
			String idCardNum = s.getStudentIdCardNum();
			SocietyStudent studentByIdCard = societyStudentMapper.selectVoByIdCardNum(idCardNum,schoolId);
			if(studentByIdCard != null){
				list.add(s.getStudentName());
				continue;
			}
			//??????
			String studentSex = s.getStudentSex();
			if(studentSex==null || studentSex.equals("")){
				//????????????????????????
				s.setStudentSex(IdCardUtil.getGenderByIdCard(idCardNum));
			}
			//??????
			Integer studentAge = s.getStudentAge();
			if(studentAge==null || studentAge == 0){
				//????????????????????????
				s.setStudentAge(IdCardUtil.getAgeByIdCard(idCardNum));
			}
			/*map.put("schoolId",schoolId);
			map.put("phoneNum",phoneNum);
			map.put("idCardNum",idCardNum);
			//?????????????????????????????????????????????????????????
			int count = societyStudentMapper.countByPhoneNumOrIdCardNum(map);
			if(count>0){
				continue;
			}*/
			if(s.getPeopleType()==null || "".equals(s.getPeopleType())){
				s.setPeopleType("ORD_STU");
			}
			String password = "123456";
			String pwd = MD5.encode(MD5.encode(password)+s.getId());
			s.setPassword(pwd);
			//????????????
			s.setNickName(s.getStudentName());
			String esg = insertStuUnique(s);
			if(!esg.equals("")){
				phoneList.add(esg);
			}else {
				stuList.add(s);
			}
		}
		map.put("list",list);
		map.put("stuList",stuList);
		map.put("phoneList",phoneList);
		return map;
	}


	public int selectVoPhoneAndCard(String ownerSchoolId, String studentPhoneNum, String studentIdCardNum) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",ownerSchoolId);
		map.put("phoneNum",studentPhoneNum);
		map.put("idCardNum",studentIdCardNum);
		return societyStudentMapper.selectVoPhoneAndCard(map);
	}

	public String selectByPhoneAndIdCard(String compId,String studentPhoneNum, String studentIdCardNum) {
		return societyStudentMapper.selectByPhoneAndIdCard(compId,studentPhoneNum,studentIdCardNum);
	}

	/**
	 * ??????list????????????  ???????????????
	 * @param studentList  ???????????????????????????
	 * @param studentAndClassList  ?????????????????????????????????
	 * @param classId ??????id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public Map<String,Object> insertStuListAndStuClassList(List<SocietyStudent> studentList, List<SocietyStudent> studentAndClassList, String classId) {
		Map<String,Object> map = new HashMap<>();
		String errStu = "";
		String errStuAndClass = "";
		if(studentList.size()>0){
			//????????????????????????
			Map<String,Object> mapStu = societyStudentService.excelImportStuAndClassInsert(studentList);
			//????????????
			errStu = classAndStudentService.relationStuAndClass((List<SocietyStudent>) mapStu.get("stuList"),classId);
			map.put("phoneList",mapStu.get("phoneList"));
			map.put("repList",mapStu.get("list"));
		}
		//???????????????????????????
		if(studentAndClassList.size()>0){
			//????????????
			errStuAndClass = classAndStudentService.relationStuAndClass(studentAndClassList,classId);
		}

		map.put("err",errStu+"???"+errStuAndClass);

		return map;
    }

	/**
	 * ????????????id ??????????????????????????????
	 * @param societyStudent
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateIdCardAndFaceImg(SocietyStudent societyStudent) {
		societyStudentMapper.updateById(societyStudent);
	}

	//???????????????  ????????????  ??????????????????????????????????????????
	public List<SocietyStudent> selectByPhoneAndIdCardAll(String studentPhoneNum, String studentIdCardNum) {
		return societyStudentMapper.selectByPhoneAndIdCardAll(studentPhoneNum,studentIdCardNum);
	}

	public SocietyStudent selectByPhone(String studentPhoneNum, String schoolId) {
		return societyStudentMapper.selectVoByPhoneNum(studentPhoneNum,schoolId);
	}

	public SocietyStudent selectByIdCard(String studentIdCardNum, String schoolId) {
		return societyStudentMapper.selectVoByIdCardNum(studentIdCardNum,schoolId);
	}


	/**
	 * ????????????????????????????????????????????????????????????????????????
	 * ???????????????????????????????????????????????????????????????????????????
	 * ???????????????????????? ??????????????????
	 * @param student
	 * @param societyStudentUnique
	 * @param faceImageUrl
	 * @param idCardImage
	 * @param studentId
	 * @param user
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateGlobleAndStu(SocietyStudent student, SocietyStudentUnique societyStudentUnique,
								   String faceImageUrl, String idCardImage, String studentId, User user) {

		//?????????????????? ???????????????????????????
		String idCard = "";//????????????????????????
		String face = "";//????????????????????????
		String idCardPrefix = globleIdcardUrl;//????????????
		String facePrefix = globleFaceUrl;//????????????
		File fileIdcard = new File(idCardImage);//????????????
		File fileFce = new File(faceImageUrl);//????????????
		try {
			idCard = idCardPrefix+ FileUtil.saveFile(fileIdcard, serverTempfile+idCardPrefix,true);
			//????????????
			//????????????????????????40k ??????????????????
			//????????????
			String faceOld = FileUtil.getImageBase64Str(fileFce);
			int base64Length = faceOld.length();
			int base64Kb = (int)(base64Length*0.75/1024);
			if(base64Kb>=app_faceai_maxfacelength){
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
			}
			face = facePrefix + FileUtil.saveBase64Img(faceOld,serverTempfile+facePrefix,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		societyStudentUnique.setFaceImageUrl(face);
		societyStudentUnique.setIdCardImage(idCard);
		societyStudentUnique.setHeadPhoto(face);
		societyStudentUnique.setIsRealNameAuth("1");
		societyStudentUnique.setRealNameTime(new Date());
		societyStudentUnique.setRealNameOperId(user.getUserId());
		societyStudentUnique.setRealNameOperName(user.getUserName());
		societyStudentUnique.setRealNameType("1");
		societyStudentUniqueService.updateById(societyStudentUnique);
		//????????????????????????????????????????????????
		List<SocietyStudent> studentList = selectByPhoneAndIdCardAll(societyStudentUnique.getStudentPhoneNum(),
				societyStudentUnique.getStudentIdCardNum());
		for(int i = 0;i<studentList.size();i++){
			SocietyStudent societyStudent = studentList.get(i);
			societyStudent.setFaceImageUrl(face);
			societyStudent.setIdCardImage(idCard);
			societyStudent.setHeadPhoto(face);
			societyStudent.setIsRealNameAuth("1");
			societyStudent.setRealNameTime(new Date());
			societyStudent.setRealNameOperId(user.getUserId());
			societyStudent.setRealNameOperName(user.getUserName());
			societyStudent.setRealNameType("1");
			updateIdCardAndFaceImg(societyStudent);
		}
	}

	/**
	 * @Author Qiutianzhu
	 * @Description ??????????????? ????????????  ??????
	 * @param prefix ????????????
	 * @param faceImageUrl ??????????????????url
	 * @param idCardImage ??????????????????url
	 * @Date 2020/7/16 10:40
	 **/
	public Map<String,String> getFaceAndIdCardUrl(String prefix,String faceImageUrl,String idCardImage){
		String idCard = "";//????????????????????????
		String face = "";//????????????????????????
		String idCardPrefix = "/"+prefix+"/idcard";//????????????
		String facePrefix = "/"+prefix+"/faceimg";//????????????
		File fileIdcard = new File(idCardImage);//????????????
		File fileFce = new File(faceImageUrl);//????????????
		try {
			idCard = idCardPrefix+ FileUtil.saveFile(fileIdcard, serverTempfile+idCardPrefix,true);
			//????????????
			String base64StuFile = FileUtil.compressImg(FileUtil.getImageBase64Str(fileFce),270);
			face = facePrefix + FileUtil.saveBase64Img(base64StuFile,serverTempfile+facePrefix,true);
			//face = facePrefix+ FileUtil.saveFile(fileFce, fileForder+facePrefix,true);
			//??????????????????
			fileIdcard.delete();
			fileFce.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,String> map = new HashMap<>();
		map.put("idCard",idCard);
		map.put("face",face);
		return map;
	}


	/**
	 * @Author WangZhen
	 * @Description ????????????????????????
	 * @Date 2020/5/20 15:48
	 **/
	public void clearStudentCatch(SocietyStudent student){
		String schoolId = student.getOwnerSchoolId();
		String openId = student.getWxOpenId();
		String accessToken = student.getAccessToken();
		//????????????
		String key1 = RedisKeyConstant.selectByOpenIdAndTypeSchoolSimple
				+ schoolId + "_" + ThirdPartyType.wechat + "_" + openId;
		String key2 = RedisKeyConstant.selectByOpenIdAndTypeSchoolSimple
				+ schoolId + "_" + ThirdPartyType.aboc + "_" + openId;
		String key3 = RedisKeyConstant.selectByAccessTokenAndSchSimple + accessToken + "_" + schoolId;
		redisUtil.del(key1,key2,key3);
	}


	/**
	 * ??????????????? faceUrl???idcardUrl
	 * ?????????????????????????????? ???????????????
	 * @param faceUrl
	 * @param idcardUrl
	 * @param student
	 * @param societyStudentUnique
	 * @param faceImageUrl
	 * @param idCardImage
	 * @param studentId
	 * @param user
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateIdCardAndFaceImgIsOneAndTwo(String faceUrl, String idcardUrl,
												  SocietyStudent student, SocietyStudentUnique societyStudentUnique,
												  String faceImageUrl, String idCardImage, String studentId, User user) {
		if(faceUrl.equals("") & !idcardUrl.equals("")){//????????? ??????????????????
			//?????????????????? ???????????????????????????
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
				face = facePrefix + FileUtil.saveBase64Img(faceOld,serverTempfile+facePrefix,true);
//				String base64File = FileUtil.compressImg(FileUtil.getImageBase64Str(fileFce),270);
//				face = facePrefix + FileUtil.saveBase64Img(base64File,fileForder+facePrefix,true);
				//face = facePrefix+ FileUtil.saveFile(fileFce, fileForder+facePrefix,true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String lastIdcardUrl = copyFaceAndIdCardUrlOnce(idcardUrl,globleIdcardUrl);
			societyStudentUnique.setFaceImageUrl(face);
			societyStudentUnique.setIdCardImage(lastIdcardUrl);
			societyStudentUnique.setHeadPhoto(face);
			societyStudentUnique.setIsRealNameAuth("1");
			societyStudentUnique.setRealNameTime(new Date());
			societyStudentUnique.setRealNameOperId(user.getUserId());
			societyStudentUnique.setRealNameOperName(user.getUserName());
			societyStudentUnique.setRealNameType("1");
			societyStudentUniqueService.updateById(societyStudentUnique);
			//????????????????????????????????????????????????
			List<SocietyStudent> studentList = selectByPhoneAndIdCardAll(societyStudentUnique.getStudentPhoneNum(),
					societyStudentUnique.getStudentIdCardNum());
			for(int i = 0;i<studentList.size();i++){
				SocietyStudent societyStudent = studentList.get(i);
				societyStudent.setFaceImageUrl(face);
				societyStudent.setIdCardImage(lastIdcardUrl);
				societyStudent.setHeadPhoto(face);
				societyStudent.setIsRealNameAuth("1");
				societyStudent.setRealNameTime(new Date());
				societyStudent.setRealNameOperId(user.getUserId());
				societyStudent.setRealNameOperName(user.getUserName());
				societyStudent.setRealNameType("1");
				updateIdCardAndFaceImg(societyStudent);
			}
		}else if(!faceUrl.equals("") & idcardUrl.equals("")){//???????????? ???????????????
			//?????????????????? ???????????????????????????
			String idCard = "";//????????????????????????
			String idCardPrefix = globleIdcardUrl;//????????????
			File fileIdcard = new File(idCardImage);//????????????
			try {
				idCard = idCardPrefix+ FileUtil.saveFile(fileIdcard, serverTempfile+idCardPrefix,true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String lastFaceImgUrl = copyFaceAndIdCardUrlOnce(faceUrl,globleFaceUrl);
			societyStudentUnique.setFaceImageUrl(lastFaceImgUrl);
			societyStudentUnique.setIdCardImage(idCard);
			societyStudentUnique.setHeadPhoto(lastFaceImgUrl);
			societyStudentUnique.setIsRealNameAuth("1");
			societyStudentUnique.setRealNameTime(new Date());
			societyStudentUnique.setRealNameOperId(user.getUserId());
			societyStudentUnique.setRealNameOperName(user.getUserName());
			societyStudentUnique.setRealNameType("1");
			societyStudentUniqueService.updateById(societyStudentUnique);
			//????????????????????????????????????????????????
			List<SocietyStudent> studentList = selectByPhoneAndIdCardAll(societyStudentUnique.getStudentPhoneNum(),
					societyStudentUnique.getStudentIdCardNum());
			for(int i = 0;i<studentList.size();i++){
				SocietyStudent societyStudent = studentList.get(i);
				societyStudent.setFaceImageUrl(lastFaceImgUrl);
				societyStudent.setIdCardImage(idCard);
				societyStudent.setHeadPhoto(lastFaceImgUrl);
				societyStudent.setIsRealNameAuth("1");
				societyStudent.setRealNameTime(new Date());
				societyStudent.setRealNameOperId(user.getUserId());
				societyStudent.setRealNameOperName(user.getUserName());
				societyStudent.setRealNameType("1");
				updateIdCardAndFaceImg(societyStudent);
			}
		}else {//?????????
			//?????????????????????????????????
			String lastFaceImgUrl = copyFaceAndIdCardUrlOnce(faceUrl,globleFaceUrl);
			String lastidCardImgUrl = copyFaceAndIdCardUrlOnce(idcardUrl,globleIdcardUrl);
			societyStudentUnique.setFaceImageUrl(lastFaceImgUrl);
			societyStudentUnique.setIdCardImage(lastidCardImgUrl);
			societyStudentUnique.setHeadPhoto(lastFaceImgUrl);
			societyStudentUnique.setIsRealNameAuth("1");
			societyStudentUnique.setRealNameTime(new Date());
			societyStudentUnique.setRealNameOperId(user.getUserId());
			societyStudentUnique.setRealNameOperName(user.getUserName());
			societyStudentUnique.setRealNameType("1");
			societyStudentUniqueService.updateById(societyStudentUnique);
			//????????????????????????????????????????????????
			List<SocietyStudent> studentList = selectByPhoneAndIdCardAll(societyStudentUnique.getStudentPhoneNum(),
					societyStudentUnique.getStudentIdCardNum());
			for(int i = 0;i<studentList.size();i++){
				SocietyStudent societyStudent = studentList.get(i);
				societyStudent.setFaceImageUrl(lastFaceImgUrl);
				societyStudent.setIdCardImage(lastidCardImgUrl);
				societyStudent.setHeadPhoto(lastFaceImgUrl);
				societyStudent.setIsRealNameAuth("1");
				societyStudent.setRealNameTime(new Date());
				societyStudent.setRealNameOperId(user.getUserId());
				societyStudent.setRealNameOperName(user.getUserName());
				societyStudent.setRealNameType("1");
				updateIdCardAndFaceImg(societyStudent);
			}
		}
	}

	/**
	 * ???????????? url ??????????????? prefix?????????????????????
	 **/
	public String copyFaceAndIdCardUrlOnce(String url, String prefix){
		//??????????????????????????????
		File fileServerUrl = new File(serverTempfile+url);//????????????
		//?????????????????????
		String lastUrl = url.replace("/tempfile",prefix);
		File fileLasUrl = new File(serverUpfile+lastUrl);//????????????
		if (!fileLasUrl.getParentFile().exists()) {
			fileLasUrl.getParentFile().mkdirs();
		}
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
			//??????
			fis = new FileInputStream(fileServerUrl);
			fos = new FileOutputStream(fileLasUrl);
			IOUtils.copyLarge(fis, fos);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (fis!=null){fis.close();}
				if (fos!=null){fos.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lastUrl;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateById(SocietyStudent societyStudent) {
		societyStudentMapper.updateById(societyStudent);
	}

	public List<String> selectByOrdAndCourseId(String courseId, String schoolId) {
		return societyStudentMapper.selectByOrdAndCourseId(courseId,schoolId);
	}

	public List<String> selectByImpAndCourseId(String courseId, String schoolId) {
		return societyStudentMapper.selectByImpAndCourseId(courseId,schoolId);
	}

	public List<String> selectByCompAndCourseId(String courseId, String schoolId) {
		return societyStudentMapper.selectByCompAndCourseId(courseId,schoolId);
	}

	public List<String> selectByOrdNullAndCourseId(String courseId, String schoolId,String classId) {
		return societyStudentMapper.selectByOrdNullAndCourseId(courseId,schoolId,classId);
	}

	public int countXianQuStudentAllNum(String xianquId) {
		return societyStudentMapper.countXianQuStudentAllNum(xianquId);
	}

	public String findFaceImageByStudentId(String studentId){
        return  societyStudentMapper.findFaceImageByStudentId(studentId);
	}

	public void updateByList(List<SocietyStudentView> studentList) {
		societyStudentMapper.updateByList(studentList);
	}
}
