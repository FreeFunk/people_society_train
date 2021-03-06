package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.*;
import com.edgedo.society.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.monitor.StringMonitor;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassAndStudentService {
	
	
	@Autowired
	private SocietySchoolClassAndStudentMapper societySchoolClassAndStudentMapper;
	@Autowired
	private SocietyStudentMapper studentMapper;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietySchoolClassAndCourseMapper classAndCourseMapper;
	@Autowired
	private SocietySchoolCourseService schoolCourseService;
	@Autowired
	private SocietyStudentAndCourseMapper studentAndCourseMapper;
	@Autowired
	private SocietySchoolCourseNodeMapper courseNodeMapper;
	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;
	@Autowired
	private SocietyStudentAndNodeMapper studentAndNodeMapper;
	@Autowired
	private SocietySchoolCourseNodeQuestionMapper nodeQuestionMapper;
	@Autowired
	private SocietyStudentPractiseQuestionMapper studentPractiseQuestionMapper;
	@Autowired
	private SocietySchoolCourseNodeOptionMapper nodeOptionMapper;
	@Autowired
	private SocietyStudentPractiseQuestOptionMapper studentPractiseQuestOptionMapper;
	@Autowired
	private SocietyStudentStudyProcessFaceMapper studentStudyProcessFaceMapper;
	@Autowired
	private SocietyTestPaperMapper testPaperMapper;
	@Autowired
	private SocietyTestPaperQuestionMapper testPaperQuestionMapper;
	@Autowired
	private SocietyStudentTestPaperMapper studentTestPaperMapper;
	@Autowired
	private SocietyStudentTestPaperQuestionMapper studentTestPaperQuestionMapper;
	@Autowired
	private SocietyTestPaperQuestionOptionMapper testPaperQuestionOptionMapper;
	@Autowired
	private SocietyStudentTestPaperQuestionOptionMapper studentTestPaperQuestionOptionMapper;

	@Autowired
	private SocietyStudentCertificateMapper studentCertificateMapper;

	@Autowired
	private SocietySchoolCourseUseGlobleService societySchoolCourseUseGlobleService;

	public List<SocietySchoolClassAndStudent> selectByClassId(Map<String,String> map) {
		return societySchoolClassAndStudentMapper.selectByClassId(map);
	}

	public List<SocietySchoolClassAndStudentView> listPage(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.listPage(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}

	public List<SocietySchoolClassAndStudentView> listByObj(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.listByObj(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}

	public List<SocietySchoolClassAndStudentView> selectBySchoolNamelistPage(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.selectBySchoolNamelistPage(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}

	public List<SocietySchoolClassAndStudentView> selectBySchoolAndClassBySchoolIdAndClassIdlistPage(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.selectBySchoolAndClassBySchoolIdAndClassIdlistPage(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}
	
	/***
	 * ????????????
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudent.setId(Guid.guid());
		societySchoolClassAndStudent.setCreateTime(new Date());
		societySchoolClassAndStudent.setDataState("1");
		Integer num = societySchoolClassAndStudentMapper.selectByClassAndStudentNum();
		DecimalFormat df=new DecimalFormat("000000");
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(societySchoolClassAndStudent.getClassId());
		String code = societySchoolClass.getClassCode()+"-"+df.format(num+1);
		societySchoolClassAndStudent.setArchivesCode(code);
		societySchoolClassAndStudentMapper.insert(societySchoolClassAndStudent);
		return "";
	}
	
	/***
	 * ??????????????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudentMapper.updateById(societySchoolClassAndStudent);
		return "";
	}
	
	/***
	 * ?????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudentMapper.updateAllColumnById(societySchoolClassAndStudent);
		return "";
	}

	/* *
	 * @Author ZhangCC
	 * @Description ???????????????????????????????????????
	 **/
	public SocietySchoolClassAndStudentView selectVoByClassAndStudent(String classId,String studentId){
		Map<String,Object> param = new HashMap<>();
		param.put("classId",classId);
		param.put("studentId",studentId);
		return societySchoolClassAndStudentMapper.selectVoByClassAndStudent(param);
	}

	/* *
	 * @Author ZhangCC
	 * @Description ???????????????????????????????????????
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteVoByClassAndStudent(String classId,String studentId){
		Map<String,Object> param = new HashMap<>();
		param.put("classId",classId);
		param.put("studentId",studentId);
		societySchoolClassAndStudentMapper.deleteVoByClassAndStudent(param);

		//????????????id ??? ??????id ?????????????????????????????????
		List<String> list = studentAndCourseMapper.selectByAllId(param);
		if (list.size()!=0){
			studentAndNodeService.updateByDataState(list);
		}
		//???????????????????????????????????????
		studentAndCourseMapper.updateStuAndCourse(param);

		return 1;
	}

	/**
	 * @Author ZhangCC
	 * @Description ?????????????????????????????????
	 * @Date 2020/5/4 9:17
	 **/
	public List<String> selectStudentIdByClass(String classId){
		return societySchoolClassAndStudentMapper.selectStudentIdByClass(classId);
	}

	/**
	 * @Author ZhangCC
	 * @Description ???????????????????????????????????????
	 * @Date 2020/5/4 17:51
	 **/
	public int countByClassAndLearnNotFinished(String classId){
		return societySchoolClassAndStudentMapper.countByClassAndLearnNotFinished(classId);
	}

	public List<SocietySchoolClassAndStudentView> selectNotRelationList(String courseId, String classId) {
		Map<String,String> map = new HashMap<>();
		map.put("courseId",courseId);
		map.put("classId",classId);
		return societySchoolClassAndStudentMapper.selectNotRelationList(map);
	}
	/**
	 * @Author ZhangCC
	 * @Description ??????????????????????????????
	 * @Date 2020/5/9 17:59
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertClassAndStudentRelation(SocietySchoolClass schoolClass, String stuIds){
		//????????????????????????
		String errMsg = "";
		String classId = schoolClass.getId();
		List<String> courseIdList = classAndCourseMapper.selectCourseIdByClass(classId);
		String[] stuIdArr = stuIds.split(",");
		for(int i=0;i<stuIdArr.length;i++){
			SocietyStudent student = studentMapper.selectById(stuIdArr[i]);
			if(student != null){
				//??????????????????????????????
				String err1 = insertCourseAndStudentRelation(student,courseIdList,classId);
				if(err1.equals("")){
					//??????????????????????????????
					insertClassAndStudentRelation(student,schoolClass);
					//?????? ???????????????????????????id
					String classAndStudentId =
							societySchoolClassAndStudentMapper.selectByClassIdAndStudentIdAndSchoolId
									(schoolClass.getId(), student.getId(),schoolClass.getOwnerSchoolId());
					studentAndCourseMapper.updateByClassAndStudentId(schoolClass.getId(),
							student.getId(),schoolClass.getOwnerSchoolId(),classAndStudentId);
				}else {
					if(errMsg.equals("")){
						errMsg = err1;
					}else {
						errMsg = errMsg+"???"+err1;
					}
				}
			}
		}
		//?????????????????????????????????????????????
		//????????????????????????
		int totalStuNum = societySchoolClassAndStudentMapper.countStudentByClassId(classId);
		schoolClass.setClassPersonNum(totalStuNum);
		//???????????????????????????
		for(String courseId:courseIdList){
			SocietySchoolCourse course = schoolCourseService.loadById(courseId);
			//???????????????????????????
			int countTotalStuNum = studentAndCourseMapper.countTotalStuNum(courseId);
			course.setTotalStudentNum(countTotalStuNum);
			schoolCourseService.update(course);
		}
	/*	//??????????????????
		Integer notFinishedPersonNum = schoolClass.getNotFinishedPersonNum();
		if(notFinishedPersonNum == null){
			schoolClass.setNotFinishedPersonNum(classAddStuCount);
		}else{
			schoolClass.setNotFinishedPersonNum(notFinishedPersonNum + classAddStuCount);
		}*/
		schoolClassService.update(schoolClass);
		return errMsg;
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????????????????
	 * @Date 2020/5/5 8:24
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertClassAndStudentRelation(SocietyStudent student,SocietySchoolClass schoolClass){
		SocietySchoolClassAndStudent classAndStudent = new SocietySchoolClassAndStudent();
		classAndStudent.setStudentId(student.getId());
		classAndStudent.setStudentName(student.getStudentName());
		classAndStudent.setStudentIdCardNum(student.getStudentIdCardNum());
		classAndStudent.setOwnerSchoolId(schoolClass.getOwnerSchoolId());
		classAndStudent.setOwnerSchoolName(schoolClass.getOwnerSchoolName());
		classAndStudent.setOwnerMajorId(schoolClass.getOwnerMajorId());
		classAndStudent.setOwnerMajorName(schoolClass.getOwnerMajorName());
		classAndStudent.setClassId(schoolClass.getId());
		classAndStudent.setClassName(schoolClass.getClassName());
		classAndStudent.setStudentLeranProgress(new BigDecimal(0));
		classAndStudent.setTotalNodeNum(schoolClass.getTotalLessons());
		classAndStudent.setFinishedNodeNum(0);
		classAndStudent.setLearnIsFinished("0");
		//??????????????????
		classAndStudent.setProvinceId(schoolClass.getProvinceId());
		classAndStudent.setProvinceName(schoolClass.getProvinceName());
		classAndStudent.setCityId(schoolClass.getCityId());
		classAndStudent.setCityName(schoolClass.getCityName());
		classAndStudent.setXianquId(schoolClass.getXianquId());
		classAndStudent.setXianquName(schoolClass.getXianquName());
		String errMsg = insert(classAndStudent);
		//??????????????????
		student.setProvinceId(schoolClass.getProvinceId());
		student.setProvinceName(schoolClass.getProvinceName());
		student.setCityId(schoolClass.getCityId());
		student.setCityName(schoolClass.getCityName());
		student.setXianquId(schoolClass.getXianquId());
		student.setXianquName(schoolClass.getXianquName());
		studentMapper.updateById(student);
		return errMsg;
	}

	/**
	 * @Author ZhangCC
	 * @Description ??????????????????????????????
	 * @Date 2020/5/5 8:24
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertCourseAndStudentRelation(SocietyStudent student,List<String> courseIdList,String classId){
		String err = "";
		SocietyStudentAndCourse studentAndCourse = new SocietyStudentAndCourse();
		studentAndCourse.setOwnerSchoolId(student.getOwnerSchoolId());
		studentAndCourse.setClassId(classId);
		studentAndCourse.setStudentId(student.getId());
		studentAndCourse.setStudentName(student.getStudentName());
		studentAndCourse.setStudentIdCardNum(student.getStudentIdCardNum());
		studentAndCourse.setDataState("1");
		studentAndCourse.setFinishNodeNum(0);
		studentAndCourse.setCourseProgress(new BigDecimal(0));
		studentAndCourse.setLearnIsFinished("0");
		studentAndCourse.setCreateTime(new Date());
		//????????????id ????????? ?????????
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(classId);
		//??????????????????
		studentAndCourse.setProvinceId(societySchoolClass.getProvinceId());
		studentAndCourse.setProvinceName(societySchoolClass.getProvinceName());
		studentAndCourse.setCityId(societySchoolClass.getCityId());
		studentAndCourse.setCityName(societySchoolClass.getCityName());
		studentAndCourse.setXianquId(societySchoolClass.getXianquId());
		studentAndCourse.setXianquName(societySchoolClass.getXianquName());

		studentAndCourse.setOwnerMajorId(societySchoolClass.getOwnerMajorId());
		studentAndCourse.setOwnerMajorName(societySchoolClass.getOwnerMajorName());
		studentAndCourse.setClassName(societySchoolClass.getClassName());
		Map<String,Object> map = new HashMap<>();
		map.put("phone",student.getStudentPhoneNum());
		map.put("idCard",student.getStudentIdCardNum());
		for(int i=0;i<courseIdList.size();i++){
			SocietySchoolCourse course = schoolCourseService.loadById(courseIdList.get(i));
			if(course != null){
				String courseId = course.getId();
				//??????????????????????????????????????????
				map.put("courseId",courseId);
				int count = studentAndCourseMapper.countByMap(map);
				if(count==0){
					studentAndCourse.setId(Guid.guid());
					studentAndCourse.setCourseId(course.getId());
					studentAndCourse.setCourseName(course.getCourseName());
					studentAndCourse.setCourseImage(course.getCourseImage());
					studentAndCourse.setTotalLessons(course.getTotalLessons());
					BigDecimal coursePrice = course.getCoursePrice();
					//???????????????????????????
					if(coursePrice==null || coursePrice.compareTo(BigDecimal.ZERO)==0){
						studentAndCourse.setCoursePrice(new BigDecimal(0));
						studentAndCourse.setCourseOrgPrice(course.getCourseOrgPrice());
						studentAndCourse.setPayState("1");
					}else {
						studentAndCourse.setCoursePrice(course.getCoursePrice());
						studentAndCourse.setCourseOrgPrice(course.getCourseOrgPrice());
						studentAndCourse.setPayState("0");
					}
//					studentAndCourse.setCourseStudyNum(course.getCourseStudyNum());
					//????????????????????????
					if("IMP_STU".equals(student.getPeopleType())){
						studentAndCourse.setCourseStudyNum(course.getImpStuCourseStudyNum());
					}else if("COMP_STU".equals(student.getPeopleType())){
						studentAndCourse.setCourseStudyNum(course.getCompStuCourseStudyNum());
					}else if("ORD_STU".equals(student.getPeopleType())){
						studentAndCourse.setCourseStudyNum(course.getOrdStuCourseStudyNum());
					}else {
						studentAndCourse.setCourseStudyNum(societySchoolClass.getClassCourseNum());
					}
					studentAndCourse.setCourseIsNeedFaceContrast(course.getCourseIsNeedFaceContrast());
					studentAndCourseMapper.insert(studentAndCourse);
					//??????????????????????????????
					insertCourseNodeAndStudentRelation(student,studentAndCourse);
				}else {
					if (err.equals("")){
						err = student.getStudentName();
					}else {
						err = err+"???"+ student.getStudentName();
					}
				}
			}
		}
		return err;
	}

	/**
	 * @Author ZhangCC
	 * @Description ????????????????????????????????????
	 * @Date 2020/6/1 9:04
	 **/
	public void insertCourseNodeAndStudentRelation(SocietyStudent student,SocietyStudentAndCourse studentAndCourse){
		SocietyStudentAndNode studentAndNode = new SocietyStudentAndNode();
		studentAndNode.setOwnerStudentAndCourseId(studentAndCourse.getId());
		studentAndNode.setCreateTime(new Date());
		studentAndNode.setDatastate("1");
		studentAndNode.setOwnerSchoolId(student.getOwnerSchoolId());
		studentAndNode.setOwnerSchoolName(student.getOwnerSchoolName());
		studentAndNode.setStudentId(student.getId());
		studentAndNode.setStudentName(student.getStudentName());
		studentAndNode.setStudentIdCardNum(student.getStudentIdCardNum());
		studentAndNode.setOwnerCourseId(studentAndCourse.getCourseId());
		studentAndNode.setOwnerCourseName(studentAndCourse.getCourseName());
		//??????????????????
		studentAndNode.setProvinceId(studentAndCourse.getProvinceId());
		studentAndNode.setProvinceName(studentAndCourse.getProvinceName());
		studentAndNode.setCityId(studentAndCourse.getCityId());
		studentAndNode.setCityName(studentAndCourse.getCityName());
		studentAndNode.setXianquId(studentAndCourse.getXianquId());
		studentAndNode.setXianquName(studentAndCourse.getXianquName());
		SocietySchoolCourseNodeQuery query = new SocietySchoolCourseNodeQuery();
		//???????????????????????????????????????
		SocietySchoolCourseUseGloble societySchoolCourseUseGloble =
				societySchoolCourseUseGlobleService.selectBySchoolIdAndCourseIdOnce(studentAndCourse.getOwnerSchoolId(),
						studentAndCourse.getCourseId());
		if (societySchoolCourseUseGloble!=null){
			query.getQueryObj().setOwnerSchoolId(societySchoolCourseUseGloble.getCourseSchId());
			query.getQueryObj().setOwnerCourseId(societySchoolCourseUseGloble.getCourseId());
		}else {
			query.getQueryObj().setOwnerSchoolId(studentAndCourse.getOwnerSchoolId());
			query.getQueryObj().setOwnerCourseId(studentAndCourse.getCourseId());
		}
		query.getQueryObj().setShState("1");
		List<SocietySchoolCourseNodeView> courseNodeList = courseNodeMapper.listByObj(query);
		if(courseNodeList != null && courseNodeList.size() >0){
			for(SocietySchoolCourseNodeView courseNode:courseNodeList){
				studentAndNode.setNodeId(courseNode.getId());
				studentAndNode.setNodeName(courseNode.getNodeName());
				studentAndNode.setNodeProgress(new BigDecimal(0));
				studentAndNode.setStudyTimeLength(0);
				studentAndNode.setLastLearnLocation(0);
				studentAndNode.setLearnIsFinished("0");
				studentAndNode.setNodeQuestionScore(0);
				studentAndNode.setQuestionIsFinished("0");
				studentAndNode.setQuestionIsPass("0");
				studentAndNodeService.insert(studentAndNode);
			}
		}
	}

	//????????????????????????????????????
	public List<SocietySchoolClassAndStudentView> selectVoByClassId(String classId) {
		return societySchoolClassAndStudentMapper.selectVoByClassId(classId);
	}

	/*??????????????????????????????*/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByClassAndStudent(String stuIds, String classId) {
		SocietySchoolClass schoolClass = schoolClassService.loadById(classId);
		String[] stuIdArr = stuIds.split(",");
		for(int i=0;i<stuIdArr.length;i++){
			deleteVoByClassAndStudent(classId,stuIdArr[i]);
		}
		//???????????????????????????????????????

		//?????????????????????
		int countStudentByClassId = societySchoolClassAndStudentMapper.countStudentByClassId(classId);
		schoolClass.setClassPersonNum(countStudentByClassId);
		schoolClassService.update(schoolClass);
	}
	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????ID???????????????????????????
	 *@DateTime: 2020/5/29 16:28
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String oneClickFinish(List<String> ids) {
		//????????????
		ids.forEach(id -> {
			SocietySchoolClassAndStudent classAndStudent = loadById(id);
			//???????????????????????????
			finishedStuAndClass(classAndStudent);
			String classId = classAndStudent.getClassId();
			String studentId = classAndStudent.getStudentId();
			//????????????id?????????id???????????????????????????
			List<SocietyStudentAndCourseView> studentAndCourseViewList = studentAndCourseMapper.listByClassIdAndStuId(classId,studentId);
			//???????????????????????????????????????
			studentAndCourseViewList.forEach(stuAndCourse ->{
				//????????????????????????
				finishedStuAndCourse(stuAndCourse);
				//???????????????????????????
				finishedStuAndNode(stuAndCourse);
				//???????????????????????????
				finishedStuFaceRec(stuAndCourse);
				//?????????????????????
				finishedStuCert(stuAndCourse);
				//???????????????????????????
				finishedStuTestPaper(stuAndCourse);

			});
		});
		return null;
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????ID???????????????????????????
	 *@DateTime: 2020/5/29 16:28
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String suiJiFinish(List<String> ids) {
		int maxNum = 0;
		int randNum = 0;
		double progressNum = 0.0;
		//????????????
		for (String id:ids){
			SocietySchoolClassAndStudent classAndStudent = loadById(id);
			String classId = classAndStudent.getClassId();
			String studentId = classAndStudent.getStudentId();
			//????????????id?????????id???????????????????????????
			List<SocietyStudentAndCourseView> studentAndCourseViewList = studentAndCourseMapper.listByClassIdAndStuId(classId,studentId);
			//???????????????????????????????????????
			for (SocietyStudentAndCourseView stuAndCourse:studentAndCourseViewList){
				//???????????????????????????
				int totalNodeNum = courseNodeMapper.countByCourseId(stuAndCourse.getCourseId());
				maxNum = totalNodeNum-2;
				randNum = getRandNum(maxNum);
				//???????????????????????????
				DecimalFormat df = new DecimalFormat("#.00");
				progressNum = Double.valueOf(df.format( (double)(randNum/(double)totalNodeNum)))*100;
				//????????????????????????
				suiJiFinishedStuAndCourse(stuAndCourse,randNum,progressNum);
				//???????????????????????????
				suiJiFinishedStuAndNode(stuAndCourse,randNum);
				//???????????????????????????
				suiJiFinishedStuFaceRec(stuAndCourse,randNum);
			}
			//???????????????????????????
			suiJiFinishedStuAndClass(classAndStudent,randNum,progressNum);
		}
		return null;
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ???????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuAndClass(SocietySchoolClassAndStudent classAndStudent) {
		//????????????
		classAndStudent.setStudentLeranProgress(new BigDecimal(100));
		//???????????????
		classAndStudent.setFinishedNodeNum(classAndStudent.getTotalNodeNum());
		//??????????????????
		classAndStudent.setLearnIsFinished("1");
		societySchoolClassAndStudentMapper.updateById(classAndStudent);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void suiJiFinishedStuAndClass(SocietySchoolClassAndStudent classAndStudent,int randNum,double progressNum) {
		//????????????
		classAndStudent.setStudentLeranProgress(new BigDecimal(progressNum));
		//???????????????
		classAndStudent.setFinishedNodeNum(randNum);
		//??????????????????
		classAndStudent.setLearnIsFinished("0");
		societySchoolClassAndStudentMapper.updateById(classAndStudent);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuAndCourse(SocietyStudentAndCourseView stuAndCourse) {
		//????????????
		stuAndCourse.setCourseProgress(new BigDecimal(100));
		//???????????????
		stuAndCourse.setFinishNodeNum(stuAndCourse.getTotalLessons());
		//??????????????????
		stuAndCourse.setLearnIsFinished("1");
		studentAndCourseMapper.updateById(stuAndCourse);
	}


	/**
	 *@Author:ZhaoSiDa
	 *@Description: ??????????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void suiJiFinishedStuAndCourse(SocietyStudentAndCourseView stuAndCourse,int randNum,double progressNum) {
		//????????????
		stuAndCourse.setCourseProgress(new BigDecimal(progressNum));
		//???????????????
		stuAndCourse.setFinishNodeNum(randNum);
		//??????????????????
		stuAndCourse.setLearnIsFinished("0");
		studentAndCourseMapper.updateById(stuAndCourse);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ???????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuAndNode(SocietyStudentAndCourseView stuAndCourse) {
		/*????????????????????????ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		//????????????ID??????????????????
		List<SocietySchoolCourseNodeView> nodeViewList = courseNodeMapper.listByCourseId(courseId);
		nodeViewList.forEach(node ->{
			String nodeId = node.getId();
			//?????????????????????????????????
			SocietyStudentAndNodeView studentAndNodeView  =studentAndNodeMapper.loadByStuIdAndNodeId(studentId,stuAndCourseId,nodeId);
			if(studentAndNodeView==null){
				//???????????????????????????
				studentAndNodeView = new SocietyStudentAndNodeView();
				studentAndNodeView.setId(Guid.guid());
				studentAndNodeView.setOwnerStudentAndCourseId(stuAndCourseId);
				studentAndNodeView.setOwnerSchoolId(node.getOwnerSchoolId());
				studentAndNodeView.setOwnerSchoolName(node.getOwnerSchoolName());
				studentAndNodeView.setStudentId(studentId);
				studentAndNodeView.setStudentName(stuAndCourse.getStudentName());
				studentAndNodeView.setStudentIdCardNum(stuAndCourse.getStudentIdCardNum());
				studentAndNodeView.setOwnerCourseId(node.getOwnerCourseId());
				studentAndNodeView.setOwnerCourseName(node.getOwnerCourseName());
				studentAndNodeView.setNodeId(nodeId);
				studentAndNodeView.setNodeName(node.getNodeName());
				studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
				studentAndNodeView.setNodeProgress(new BigDecimal(100));
				studentAndNodeView.setStudyTimeLength(node.getNodeTimeLength());
				studentAndNodeView.setLastLearnLocation(node.getNodeTimeLength());
				studentAndNodeView.setLearnIsFinished("1");
				studentAndNodeView.setQuestionIsPass("1");
				studentAndNodeView.setQuestionIsFinished("1");
				//TODO ???????????????
				studentAndNodeMapper.insert(studentAndNodeView);
			}else {
				studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
				studentAndNodeView.setNodeProgress(new BigDecimal(100));
				studentAndNodeView.setStudyTimeLength(node.getNodeTimeLength());
				studentAndNodeView.setLastLearnLocation(node.getNodeTimeLength());
				studentAndNodeView.setLearnIsFinished("1");
				studentAndNodeView.setQuestionIsPass("1");
				studentAndNodeView.setQuestionIsFinished("1");
				//TODO ???????????????
				studentAndNodeMapper.updateById(studentAndNodeView);
			}
			//???????????????????????????
			finishedStuNodeQuestion(studentAndNodeView);
		});
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void suiJiFinishedStuAndNode(SocietyStudentAndCourseView stuAndCourse,int randNum) {
		/*????????????????????????ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		//????????????ID??????????????????
		List<SocietySchoolCourseNodeView> nodeViewList = courseNodeMapper.listByCourseIdSuiJiNum(courseId,randNum);
		nodeViewList.forEach(node ->{
			String nodeId = node.getId();
			//?????????????????????????????????
			SocietyStudentAndNodeView studentAndNodeView  =studentAndNodeMapper.loadByStuIdAndNodeId(studentId,stuAndCourseId,nodeId);
			if(studentAndNodeView==null){
				//???????????????????????????
				studentAndNodeView = new SocietyStudentAndNodeView();
				studentAndNodeView.setId(Guid.guid());
				studentAndNodeView.setOwnerStudentAndCourseId(stuAndCourseId);
				studentAndNodeView.setOwnerSchoolId(node.getOwnerSchoolId());
				studentAndNodeView.setOwnerSchoolName(node.getOwnerSchoolName());
				studentAndNodeView.setStudentId(studentId);
				studentAndNodeView.setStudentName(stuAndCourse.getStudentName());
				studentAndNodeView.setStudentIdCardNum(stuAndCourse.getStudentIdCardNum());
				studentAndNodeView.setOwnerCourseId(node.getOwnerCourseId());
				studentAndNodeView.setOwnerCourseName(node.getOwnerCourseName());
				studentAndNodeView.setNodeId(nodeId);
				studentAndNodeView.setNodeName(node.getNodeName());
				studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
				studentAndNodeView.setNodeProgress(new BigDecimal(100));
				studentAndNodeView.setStudyTimeLength(node.getNodeTimeLength());
				studentAndNodeView.setLastLearnLocation(node.getNodeTimeLength());
				studentAndNodeView.setLearnIsFinished("1");
				studentAndNodeView.setQuestionIsPass("1");
				studentAndNodeView.setQuestionIsFinished("1");
				//TODO ???????????????
				studentAndNodeMapper.insert(studentAndNodeView);
			}else {
				studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
				studentAndNodeView.setNodeProgress(new BigDecimal(100));
				studentAndNodeView.setStudyTimeLength(node.getNodeTimeLength());
				studentAndNodeView.setLastLearnLocation(node.getNodeTimeLength());
				studentAndNodeView.setLearnIsFinished("1");
				studentAndNodeView.setQuestionIsPass("1");
				studentAndNodeView.setQuestionIsFinished("1");
				//TODO ???????????????
				studentAndNodeMapper.updateById(studentAndNodeView);
			}
			//???????????????????????????
			finishedStuNodeQuestion(studentAndNodeView);
		});
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ???????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuNodeQuestion(SocietyStudentAndNodeView studentAndNodeView) {
		/*????????????????????????ID*/
		String stuAndNodeId = studentAndNodeView.getId();
		String nodeId = studentAndNodeView.getNodeId();
		String studentId = studentAndNodeView.getStudentId();
		//????????????????????????
		Map<String,String> map = getRandMap(studentAndNodeView.getTotalQuestionNum());
		//????????????ID????????????????????????????????????
		List<SocietySchoolCourseNodeQuestionView> nodeQuestionViewList = nodeQuestionMapper.listByNodeId(nodeId);
		nodeQuestionViewList.forEach(question ->{
			String questionId = question.getId();
			int index = nodeQuestionViewList.indexOf(question);
			String answerState = "1";
			int getScore = question.getQuestionScore();
			if(map.get(index+"")!=null){
				answerState = "-1";
				getScore = 0;
			}
			//?????????????????????????????????
			SocietyStudentPractiseQuestionView studentPractiseQuestionView =studentPractiseQuestionMapper.loadByStuIdAndQuestionId(studentId,stuAndNodeId,questionId);
			if(studentPractiseQuestionView==null){
				String stuQuestionId = Guid.guid();
				List<SocietySchoolCourseNodeOption> nodeOptionList = nodeOptionMapper.selectOptionByQuestion(questionId);
				nodeOptionList.forEach(nodeOption ->{
					String nodeOptionId = nodeOption.getId();
					//??????????????????????????????
					SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuIdAndOptionId(studentId,stuAndNodeId,nodeOptionId);
					if(societyStudentPractiseQuestOption==null){
						societyStudentPractiseQuestOption = new SocietyStudentPractiseQuestOption();
						societyStudentPractiseQuestOption.setId(Guid.guid());
						societyStudentPractiseQuestOption.setCreateTime(new Date());
						societyStudentPractiseQuestOption.setOwnerStudentAndNodeId(stuAndNodeId);
						societyStudentPractiseQuestOption.setOwnerStuCourseId(studentAndNodeView.getOwnerStudentAndCourseId());
						societyStudentPractiseQuestOption.setStudentId(studentId);
						societyStudentPractiseQuestOption.setOwnerSchoolId(studentAndNodeView.getOwnerSchoolId());
						societyStudentPractiseQuestOption.setOwnerSchoolName(studentAndNodeView.getOwnerSchoolName());
						societyStudentPractiseQuestOption.setOwnerNodeId(studentAndNodeView.getNodeId());
						societyStudentPractiseQuestOption.setOwnerNodeName(studentAndNodeView.getNodeName());
						societyStudentPractiseQuestOption.setOwnerNodeQueOpId(nodeOptionId);
						societyStudentPractiseQuestOption.setOptionTitle(nodeOption.getOptionTitle());
						societyStudentPractiseQuestOption.setOptionName(nodeOption.getOptionName());
						if(question.getQuestionType().equals("1")){
							if(question.getQuestionAnswer().contains(nodeOption.getOptionTitle())){
								societyStudentPractiseQuestOption.setIsRight("1");
							}else {
								societyStudentPractiseQuestOption.setIsRight("0");
							}
						}else {
							societyStudentPractiseQuestOption.setIsRight(nodeOption.getIsRight());
						}
						societyStudentPractiseQuestOption.setIsSelect("0");
						societyStudentPractiseQuestOption.setOrderNum(nodeOption.getOrderNum());
						societyStudentPractiseQuestOption.setOwnerStudentQuersionId(stuQuestionId);
						societyStudentPractiseQuestOption.setOwnerStudentQuersionName(question.getQuestionName());
						societyStudentPractiseQuestOption.setLastAnswerTime(new Date());
						studentPractiseQuestOptionMapper.insert(societyStudentPractiseQuestOption);
						//System.out.println("societyStudentPractiseQuestOption====="+societyStudentPractiseQuestOption);
					}
				});
				studentPractiseQuestionView = new SocietyStudentPractiseQuestionView();
				studentPractiseQuestionView.setId(stuQuestionId);
				studentPractiseQuestionView.setCreateTime(new Date());
				studentPractiseQuestionView.setOwnerStudentAndNodeId(stuAndNodeId);
				studentPractiseQuestionView.setOwnerStuCourseId(studentAndNodeView.getOwnerStudentAndCourseId());
				studentPractiseQuestionView.setStudentId(studentId);
				studentPractiseQuestionView.setStudentName(studentAndNodeView.getStudentName());
				studentPractiseQuestionView.setStudentIdCardNum(studentAndNodeView.getStudentIdCardNum());
				studentPractiseQuestionView.setOwnerSchoolId(studentAndNodeView.getOwnerSchoolId());
				studentPractiseQuestionView.setOwnerSchoolName(studentAndNodeView.getOwnerSchoolName());
				studentPractiseQuestionView.setOwnerCourseId(studentAndNodeView.getOwnerCourseId());
				studentPractiseQuestionView.setOwnerCourseName(studentAndNodeView.getOwnerCourseName());
				studentPractiseQuestionView.setOwnerNodeId(studentAndNodeView.getNodeId());
				studentPractiseQuestionView.setOwnerNodeName(studentAndNodeView.getNodeName());
				studentPractiseQuestionView.setQuersionId(questionId);
				studentPractiseQuestionView.setQuestionName(question.getQuestionName());
				studentPractiseQuestionView.setQuestionType(question.getQuestionType());
				studentPractiseQuestionView.setQuestionScore(question.getQuestionScore());
				studentPractiseQuestionView.setAnswerState(answerState);
				studentPractiseQuestionView.setGetScore(getScore);
				studentPractiseQuestionView.setOrderNum(question.getOrderNum());
				//TODO  ????????????
				SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption = null;
				if(answerState.equals("1")){
					//?????????????????????????????????
					societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionId(stuQuestionId,"1");
				/*	if(societyStudentPractiseQuestOption==null){
						System.out.println("stuQuestionId========"+stuQuestionId);
					}*/
					societyStudentPractiseQuestOption.setIsSelect("1");
					studentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
				}else {
					//?????????????????????????????????
					societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionId(stuQuestionId,"0");
					societyStudentPractiseQuestOption.setIsSelect("1");
					studentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
				}
				studentPractiseQuestionView.setStuSelectOpId(societyStudentPractiseQuestOption.getId());
				studentPractiseQuestionView.setLastAnswerTime(new Date());
				studentPractiseQuestionView.setDataState("1");
				studentPractiseQuestionMapper.insert(studentPractiseQuestionView);
			}else {
				studentPractiseQuestionView.setAnswerState(answerState);
				studentPractiseQuestionView.setGetScore(getScore);
				studentPractiseQuestionView.setOrderNum(question.getOrderNum());
				//TODO  ????????????
				SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption = null;
				if(answerState.equals("1")){
					//?????????????????????????????????
					societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionId(studentPractiseQuestionView.getId(),"1");
					societyStudentPractiseQuestOption.setIsSelect("1");
					studentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
				}else {
					//?????????????????????????????????
					societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionId(studentPractiseQuestionView.getId(),"0");
					societyStudentPractiseQuestOption.setIsSelect("1");
					studentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
				}
				studentPractiseQuestionView.setStuSelectOpId(societyStudentPractiseQuestOption.getId());
				studentPractiseQuestionView.setLastAnswerTime(new Date());
				studentPractiseQuestionView.setDataState("1");
				studentPractiseQuestionMapper.updateById(studentPractiseQuestionView);
			}
		});
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ???????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuFaceRec(SocietyStudentAndCourseView stuAndCourse) {
		/*????????????????????????ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
		int count = 0;
		int consumRecListSize = 0;
		int nodeViewListSize = 0;
		//?????????????????????????????????
		List<YwTrainConsumRec> consumRecList = courseNodeMapper.selectByIdCardNum(studentIdCardNum);
		if(consumRecList!=null){
			consumRecListSize = consumRecList.size();
		}
		//????????????????????????????????????
		//????????????ID??????????????????
		List<SocietySchoolCourseNodeView> nodeViewList = courseNodeMapper.listByCourseId(courseId);
		if(nodeViewList!=null){
			nodeViewListSize = nodeViewList.size();
		}
		if(nodeViewListSize>consumRecListSize){
			count = consumRecListSize;
		}else {
			count = nodeViewListSize;
		}
		for (int i=0;i<count;i++){
			String conRecId = consumRecList.get(i).getId();
			SocietySchoolCourseNodeView nodeView = nodeViewList.get(i);
			//??????????????????id????????????????????????????????????
			List<YwTrainLearnRec> learnRecList = courseNodeMapper.selectByConRecId(conRecId);
			for (YwTrainLearnRec learnRec:learnRecList){
				//??????????????????????????????
				SocietyStudentStudyProcessFace societyStudentStudyProcessFace = new SocietyStudentStudyProcessFace();
				societyStudentStudyProcessFace.setId(Guid.guid());
				societyStudentStudyProcessFace.setCreateTime(learnRec.getCreateTime());
				societyStudentStudyProcessFace.setStuCourseId(stuAndCourseId);
				societyStudentStudyProcessFace.setOwnerSchoolId(nodeView.getOwnerSchoolId());
				societyStudentStudyProcessFace.setOwnerSchoolName(nodeView.getOwnerSchoolName());
				societyStudentStudyProcessFace.setOwnerCourseId(nodeView.getOwnerCourseId());
				societyStudentStudyProcessFace.setOwnerCourseName(nodeView.getOwnerCourseName());
				societyStudentStudyProcessFace.setOwnerNodeId(nodeView.getId());
				societyStudentStudyProcessFace.setOwnerNodeName(nodeView.getNodeName());
				societyStudentStudyProcessFace.setStudentId(studentId);
				societyStudentStudyProcessFace.setStudentName(stuAndCourse.getStudentName());
				societyStudentStudyProcessFace.setFaceImageUrl("http://trainimg.qhd12328.com/130300"+learnRec.getFaceUrl());
				societyStudentStudyProcessFace.setFaceScore(learnRec.getFaceMatchScore());
				societyStudentStudyProcessFace.setFaceType("learn");
				studentStudyProcessFaceMapper.insert(societyStudentStudyProcessFace);
			}
		}
	}
	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????????????????
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void suiJiFinishedStuFaceRec(SocietyStudentAndCourseView stuAndCourse,int randNum) {
		/*????????????????????????ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
		int count = 0;
		int consumRecListSize = 0;
		int nodeViewListSize = 0;
		//?????????????????????????????????
		List<YwTrainConsumRec> consumRecList = courseNodeMapper.selectByIdCardNum(studentIdCardNum);
		if(consumRecList!=null){
			consumRecListSize = consumRecList.size();
		}
		//????????????????????????????????????
		//????????????ID??????????????????
		List<SocietySchoolCourseNodeView> nodeViewList = courseNodeMapper.listByCourseIdSuiJiNum(courseId,randNum);
		if(nodeViewList!=null){
			nodeViewListSize = nodeViewList.size();
		}
		if(nodeViewListSize>consumRecListSize){
			count = consumRecListSize;
		}else {
			count = nodeViewListSize;
		}
		for (int i=0;i<count;i++){
			String conRecId = consumRecList.get(i).getId();
			SocietySchoolCourseNodeView nodeView = nodeViewList.get(i);
			//??????????????????id????????????????????????????????????
			List<YwTrainLearnRec> learnRecList = courseNodeMapper.selectByConRecId(conRecId);
			for (YwTrainLearnRec learnRec:learnRecList){
				//??????????????????????????????
				SocietyStudentStudyProcessFace societyStudentStudyProcessFace = new SocietyStudentStudyProcessFace();
				societyStudentStudyProcessFace.setId(Guid.guid());
				societyStudentStudyProcessFace.setCreateTime(learnRec.getCreateTime());
				societyStudentStudyProcessFace.setStuCourseId(stuAndCourseId);
				societyStudentStudyProcessFace.setOwnerSchoolId(nodeView.getOwnerSchoolId());
				societyStudentStudyProcessFace.setOwnerSchoolName(nodeView.getOwnerSchoolName());
				societyStudentStudyProcessFace.setOwnerCourseId(nodeView.getOwnerCourseId());
				societyStudentStudyProcessFace.setOwnerCourseName(nodeView.getOwnerCourseName());
				societyStudentStudyProcessFace.setOwnerNodeId(nodeView.getId());
				societyStudentStudyProcessFace.setOwnerNodeName(nodeView.getNodeName());
				societyStudentStudyProcessFace.setStudentId(studentId);
				societyStudentStudyProcessFace.setStudentName(stuAndCourse.getStudentName());
				societyStudentStudyProcessFace.setFaceImageUrl("http://trainimg.qhd12328.com/130300"+learnRec.getFaceUrl());
				societyStudentStudyProcessFace.setFaceScore(learnRec.getFaceMatchScore());
				societyStudentStudyProcessFace.setFaceType("learn");
				studentStudyProcessFaceMapper.insert(societyStudentStudyProcessFace);
			}
		}
	}



	/**
	 *@Author:ZhaoSiDa
	 *@Description: ???????????????????????????
	 *@DateTime: 2020/6/1 10:16
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuTestPaper(SocietyStudentAndCourseView stuAndCourse) {
		/*????????????????????????ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
		//??????????????????????????????
		SocietyTestPaper societyTestPaper = testPaperMapper.loadByCourseId(courseId);
		String testPaperId = societyTestPaper.getId();
		Map<String,String> map = getRandMapNew(30,societyTestPaper.getTotalQuestionNum());
		//?????????????????????????????????
		List<SocietyTestPaperQuestionView> testPaperQuestionViewList = testPaperQuestionMapper.ListByTestPaperId(testPaperId);
		testPaperQuestionViewList.forEach(question ->{
			String questionId = question.getId();
			int index = testPaperQuestionViewList.indexOf(question);
			String answerState = "1";
			BigDecimal getScore = question.getQuestionScore();
			if(map.get(index+"")!=null) {
				answerState = "-1";
				getScore = new BigDecimal(0);
			}
				//?????????????????????????????????
				SocietyStudentTestPaperQuestion studentTestPaperQuestion =studentTestPaperQuestionMapper.loadByStuIdAndQuestionId(studentId,testPaperId,questionId);
				if(studentTestPaperQuestion==null){
					String stuQuestionId = Guid.guid();
					List<SocietyTestPaperQuestionOption> questionOptionList = testPaperQuestionOptionMapper.selectOptionByQuestionId(questionId);
					questionOptionList.forEach(questionOption ->{
						String questionOptionId = questionOption.getId();
						//??????????????????????????????
						SocietyStudentTestPaperQuestionOption studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuIdAndOptionId(studentId,testPaperId,questionOptionId);
						if(studentTestPaperQuestionOption==null){
							studentTestPaperQuestionOption = new SocietyStudentTestPaperQuestionOption();
							studentTestPaperQuestionOption.setId(Guid.guid());
							studentTestPaperQuestionOption.setCreateTime(new Date());
							studentTestPaperQuestionOption.setCreateUserId(studentId);
							studentTestPaperQuestionOption.setCreateUserName(stuAndCourse.getStudentName());
							studentTestPaperQuestionOption.setStudentId(studentId);
							studentTestPaperQuestionOption.setOwnerSchoolId(questionOption.getOwnerSchoolId());
							studentTestPaperQuestionOption.setOwnerSchoolName(questionOption.getOwnerSchoolName());
							studentTestPaperQuestionOption.setOwnerCourseId(questionOption.getOwnerCourseId());
							studentTestPaperQuestionOption.setOwnerCourseName(questionOption.getOwnerCourseName());
							studentTestPaperQuestionOption.setOwnerTestPaperId(questionOption.getOwnerTestPaperId());
							studentTestPaperQuestionOption.setOwnerTestPaperName(questionOption.getOwnerTestPaperName());
							studentTestPaperQuestionOption.setOwnerTestPaperQuestionId(stuQuestionId);
							studentTestPaperQuestionOption.setOptionName(questionOption.getOptionName());
							studentTestPaperQuestionOption.setOptionTitle(questionOption.getOptionTitle());
							studentTestPaperQuestionOption.setIsSelect("0");
							studentTestPaperQuestionOption.setOrderNum(questionOption.getOrderNum());
							studentTestPaperQuestionOption.setDataState("1");
							if(question.getQuestionType().equals("1")){
								if(question.getQuestionAnswer().contains(questionOption.getOptionTitle())){
									studentTestPaperQuestionOption.setIsRight("1");
								}else {
									studentTestPaperQuestionOption.setIsRight("0");
								}
							}else {
								studentTestPaperQuestionOption.setIsRight(questionOption.getIsRight());
							}
							studentTestPaperQuestionOptionMapper.insert(studentTestPaperQuestionOption);
							//System.out.println("societyStudentPractiseQuestOption====="+societyStudentPractiseQuestOption);
						}
					});
					studentTestPaperQuestion = new SocietyStudentTestPaperQuestion();
					studentTestPaperQuestion.setId(stuQuestionId);
					studentTestPaperQuestion.setCreateTime(new Date());
					studentTestPaperQuestion.setCreateUserId(studentId);
					studentTestPaperQuestion.setCreateUserName(stuAndCourse.getStudentName());
					studentTestPaperQuestion.setStudentId(studentId);
					studentTestPaperQuestion.setOwnerSchoolId(question.getOwnerSchoolId());
					studentTestPaperQuestion.setOwnerSchoolName(question.getOwnerSchoolName());
					studentTestPaperQuestion.setOwnerCourseId(question.getOwnerCourseId());
					studentTestPaperQuestion.setOwnerCourseName(question.getOwnerCourseName());
					studentTestPaperQuestion.setOwnerTestPaperId(question.getOwnerTestPaperId());
					studentTestPaperQuestion.setOwnerTestPaperName(question.getOwnerTestPaperName());
					studentTestPaperQuestion.setTestPaperQuestionId(questionId);
					studentTestPaperQuestion.setTestPaperQuestionName(question.getQuestionName());
					studentTestPaperQuestion.setTestPaperQuestionType(question.getQuestionType());
					studentTestPaperQuestion.setTestPaperQuestionScore(question.getTestPaperQuestionScore());
					studentTestPaperQuestion.setGetScore(getScore.intValue());
					studentTestPaperQuestion.setOrderNum(question.getOrderNum());
					//TODO  ????????????
					SocietyStudentTestPaperQuestionOption studentTestPaperQuestionOption = null;
					if(answerState.equals("1")){
						//?????????????????????????????????
						studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuQuestionId(stuQuestionId,"1");
						studentTestPaperQuestionOption.setIsSelect("1");
						studentTestPaperQuestionOptionMapper.updateById(studentTestPaperQuestionOption);
					}else {
						//?????????????????????????????????
						studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuQuestionId(stuQuestionId,"0");
						studentTestPaperQuestionOption.setIsSelect("1");
						studentTestPaperQuestionOptionMapper.updateById(studentTestPaperQuestionOption);
					}
					//studentTestPaperQuestion.setStuSelectOpId(studentTestPaperQuestionOption.getId());
					//studentTestPaperQuestion.setLastAnswerTime(new Date());
					studentTestPaperQuestion.setDataState("1");
					studentTestPaperQuestionMapper.insert(studentTestPaperQuestion);
				}else {
					//studentTestPaperQuestion.setAnswerState(answerState);
					studentTestPaperQuestion.setGetScore(getScore.intValue());
					studentTestPaperQuestion.setOrderNum(question.getOrderNum());
					//TODO  ????????????
					SocietyStudentTestPaperQuestionOption studentTestPaperQuestionOption = null;
					if(answerState.equals("1")){
						//?????????????????????????????????
						studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuQuestionId(studentTestPaperQuestion.getId(),"1");
						studentTestPaperQuestionOption.setIsSelect("1");
						studentTestPaperQuestionOptionMapper.updateById(studentTestPaperQuestionOption);
					}else {
						//?????????????????????????????????
						studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuQuestionId(studentTestPaperQuestion.getId(),"0");
						studentTestPaperQuestionOption.setIsSelect("1");
						studentTestPaperQuestionOptionMapper.updateById(studentTestPaperQuestionOption);
					}
					//studentTestPaperQuestion.setStuSelectOpId(societyStudentPractiseQuestOption.getId());
					//studentTestPaperQuestion.setLastAnswerTime(new Date());
					studentTestPaperQuestion.setDataState("1");
					studentTestPaperQuestionMapper.updateById(studentTestPaperQuestion);
				}
		});
		//????????????????????????
		int countOptionNum = studentTestPaperQuestionMapper.countRightQuestionNumByType(studentId,testPaperId,"1");
		int countOptionScore = countOptionNum*societyTestPaper.getDanxuanScore().intValue();
		int countJudgeNum = studentTestPaperQuestionMapper.countRightQuestionNumByType(studentId,testPaperId,"2");
		int countJudgeScore = countJudgeNum*societyTestPaper.getPanduanScore().intValue();
		//????????????????????????
		DecimalFormat df = new DecimalFormat("#.00");
		double rightRate =Double.valueOf(df.format( (double)(countOptionNum+countJudgeNum)/(double)societyTestPaper.getTotalQuestionNum()));

		//???????????????????????????????????????
		SocietyStudentTestPaper studentTestPaper = studentTestPaperMapper.loadByStuIdAndTestPaperId(studentId,testPaperId);
		if(studentTestPaper==null){
			studentTestPaper = new SocietyStudentTestPaper();
			studentTestPaper.setId(Guid.guid());
			studentTestPaper.setCreateTime(new Date());
			studentTestPaper.setCreateUserId(studentId);
			studentTestPaper.setCreateUserName(stuAndCourse.getStudentName());
			studentTestPaper.setStudentId(studentId);
			studentTestPaper.setStudentName(stuAndCourse.getStudentName());
			studentTestPaper.setStudentIdCardNum(stuAndCourse.getStudentIdCardNum());
			studentTestPaper.setOwnerSchoolId(societyTestPaper.getOwnerSchoolId());
			studentTestPaper.setOwnerSchoolName(societyTestPaper.getOwnerSchoolName());
			studentTestPaper.setOwnerCourseId(societyTestPaper.getOwnerCourseId());
			studentTestPaper.setOwnerCourseName(societyTestPaper.getOwnerCourseName());
			studentTestPaper.setOwnerTestPaperId(societyTestPaper.getId());
			studentTestPaper.setTestPaperName(societyTestPaper.getTestPaperName());
			studentTestPaper.setTestPaperTimeLength(societyTestPaper.getTestPaperTimeLength());
			studentTestPaper.setTotalQuestionNum(societyTestPaper.getTotalQuestionNum());
			studentTestPaper.setTotalScore(societyTestPaper.getTotalScore());
			studentTestPaper.setPassScore(societyTestPaper.getPassScore());
			studentTestPaper.setIsPass("1");
			studentTestPaper.setIsFinished("1");
			studentTestPaper.setDataState("1");
			studentTestPaper.setOptionRightNum(countOptionNum+"");
			studentTestPaper.setOptionScore(countOptionScore);
			studentTestPaper.setJudgeRightNum(countJudgeNum+"");
			studentTestPaper.setJudgeScore(countJudgeScore);
			studentTestPaper.setTestRightRate(new BigDecimal(rightRate*100));
			studentTestPaper.setGetScore(countOptionScore+countJudgeScore);
			studentTestPaperMapper.insert(studentTestPaper);
		}else {
			studentTestPaper.setOptionRightNum(countOptionNum+"");
			studentTestPaper.setOptionScore(countOptionScore);
			studentTestPaper.setJudgeRightNum(countJudgeNum+"");
			studentTestPaper.setJudgeScore(countJudgeScore);
			studentTestPaper.setGetScore(countOptionScore+countJudgeScore);
			studentTestPaper.setTestRightRate(new BigDecimal(rightRate*100));
			studentTestPaperMapper.updateById(studentTestPaper);
		}
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: ?????????????????????
	 *@DateTime: 2020/6/1 14:50
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuCert(SocietyStudentAndCourseView stuAndCourse){
		/*????????????????????????ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		SocietySchoolCourse schoolCourse = schoolCourseService.loadById(courseId);
		//?????????????????????????????????
		SocietyStudentCertificate societyStudentCertificate = studentCertificateMapper.loadByStudentIdAndCourseId(studentId,courseId);
		if(societyStudentCertificate==null){
			societyStudentCertificate = new SocietyStudentCertificate();
			societyStudentCertificate.setId(Guid.guid());
			societyStudentCertificate.setCreateTime(new Date());
			societyStudentCertificate.setOwnerSchoolId(schoolCourse.getOwnerSchoolId());
			societyStudentCertificate.setOwnerSchoolName(schoolCourse.getOwnerSchoolName());
			societyStudentCertificate.setOwnerMajorId(schoolCourse.getOwnerMajorId());
			societyStudentCertificate.setOwnerMajorName(schoolCourse.getOwnerMajorName());
			societyStudentCertificate.setClassId(stuAndCourse.getClassId());
			SocietySchoolClass schoolClass = schoolClassService.loadById(stuAndCourse.getClassId());
			societyStudentCertificate.setClassName(schoolClass.getClassName());
			societyStudentCertificate.setCourseId(courseId);
			societyStudentCertificate.setCourseName(schoolCourse.getCourseName());
			societyStudentCertificate.setStudentId(studentId);
			societyStudentCertificate.setStudentName(stuAndCourse.getStudentName());
			societyStudentCertificate.setStudentIdCardNum(stuAndCourse.getStudentIdCardNum());
			//societyStudentCertificate.setClassAndStudentId(stuAndCourseId);
			societyStudentCertificate.setCertificateCode(Guid.guid());
			societyStudentCertificate.setCertificateName(schoolCourse.getCourseName()+"????????????");
			societyStudentCertificate.setCertificateTime(new Date());
			societyStudentCertificate.setDataState("1");
			studentCertificateMapper.insert(societyStudentCertificate);
		}
	}
	//???????????????
	public Map<String,String> getRandMap(int max){
		Map<String,String> map = new HashMap<>();
		int num = max/5;
		Random rand = new Random();
		for( int i=0; i<num; i++) {
			int rNum = rand.nextInt(max);
			map.put(rNum+"","");
		}
		return map;
	}
	//?????????????????????
	public Map<String,String> getRandMapNew(int num,int total){
		Map<String,String> map = new HashMap<>();
		Random rand = new Random();
		int tNum = rand.nextInt(num);
		while (map.size()<tNum){
			int nNum = rand.nextInt(total);
			if(map.get(nNum+"")==null){
				map.put(nNum+"","");
			}
		}
		return map;
	}
	//?????????????????????
	public int getRandNum(int num){
		Random rand = new Random();
		return rand.nextInt(num)+1;
	}


	/*public static void main(String[] args) {
		System.out.println(getRandMapNew(30,100));
	}*/




	/**
	 * ????????????
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietySchoolClassAndStudent loadById(String id) {
		return societySchoolClassAndStudentMapper.selectById(id);
	}

	public int selectVoClassId(String classId) {
		return societySchoolClassAndStudentMapper.selectVoClassId(classId);
	}

	public int selectVoClassAndStudentName(String classId, String studentName, String studentIdCardNum) {
		return societySchoolClassAndStudentMapper.selectVoClassAndStudentName(classId,studentName,studentIdCardNum);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String relationStuAndClass(List<SocietyStudent> studentList, String classId) {
		String err = "";
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(classId);
		List<String> courseIdList = classAndCourseMapper.selectCourseIdByClass(classId);
		for(SocietyStudent societyStudent : studentList){
			SocietyStudent student = studentMapper.selectById(societyStudent.getId());
			if(student != null){
				//??????????????????????????????
				String err1 = insertCourseAndStudentRelation(student,courseIdList,classId);
				if(err1.equals("")){
					//??????????????????????????????
					insertClassAndStudentRelation(student,societySchoolClass);
					//?????? ???????????????????????????id
					String classAndStudentId =
							societySchoolClassAndStudentMapper.selectByClassIdAndStudentIdAndSchoolId
									(societySchoolClass.getId(), student.getId(),societySchoolClass.getOwnerSchoolId());
					studentAndCourseMapper.updateByClassAndStudentId(societySchoolClass.getId(),
							student.getId(),societySchoolClass.getOwnerSchoolId(),classAndStudentId);
				}else {
					if(err.equals("")){
						err = err1;
					}else {
						err = err+"???"+err1;
					}
				}
			}
		}
		//?????????????????????????????????????????????
		//????????????????????????
		int totalStuNum = societySchoolClassAndStudentMapper.countStudentByClassId(classId);
		societySchoolClass.setClassPersonNum(totalStuNum);
		//???????????????????????????
		for(String courseId:courseIdList){
			SocietySchoolCourse course = schoolCourseService.loadById(courseId);
			//???????????????????????????
			int countTotalStuNum = studentAndCourseMapper.countTotalStuNum(courseId);
			course.setTotalStudentNum(countTotalStuNum);
			schoolCourseService.update(course);
		}

		schoolClassService.update(societySchoolClass);
		return err;
	}

	public List<String> selectBySchoolIdAndClassId(String schoolId, List<String> classId) {
		return societySchoolClassAndStudentMapper.selectBySchoolIdAndClassId(schoolId,classId);
	}

	public List<SocietySchoolClassAndStudent> selectBySchoolIdVoClassId(String classId, String schoolId) {
		return societySchoolClassAndStudentMapper.selectBySchoolIdVoClassId(classId,schoolId);
	}

	public int selectBySchoolIdVoClassIdOne(String classId, String studentOldId) {
		return societySchoolClassAndStudentMapper.selectBySchoolIdVoClassIdOne(classId,studentOldId);
	}

	public List<SocietySchoolClassAndStudentView> selectByStuIdAndSchoolIdlistPage(SocietySchoolClassAndStudentQuery query) {
		List list = societySchoolClassAndStudentMapper.selectByStuIdAndSchoolIdlistPage(query);
		query.setList(list);
		return list;
	}

	public List<SocietySchoolClassAndStudentView> selectByClassIdStuIdAndSchoolIdlistPage(SocietySchoolClassAndStudentQuery query) {
		List list = societySchoolClassAndStudentMapper.selectByClassIdStuIdAndSchoolIdlistPage(query);
		query.setList(list);
		return list;
	}

	public void deleteByStuId(String id) {
		societySchoolClassAndStudentMapper.deleteByStuId(id);
	}

	public void updateByStuId(Map<String, String> map) {
		societySchoolClassAndStudentMapper.updateByStuId(map);
	}

	public List<SocietySchoolClassAndStudentView> selectByAll() {
		return societySchoolClassAndStudentMapper.selectByAll();
	}

	public void updateByMajorId(String majorId, String majorName) {
		societySchoolClassAndStudentMapper.updateByMajorId(majorId,majorName);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseId(Map<String, String> map) {
		societySchoolClassAndStudentMapper.updateByCourseId(map);
	}

	public Integer selectCountByClassId(String classId) {
		return societySchoolClassAndStudentMapper.selectCountByClassId(classId);
	}

	public List<String> selectByRandStuId(String classId) {
		return societySchoolClassAndStudentMapper.selectByRandStuId(classId);
	}

	public List<String> selectByClassIdRandTenStudent(String classId) {
		return societySchoolClassAndStudentMapper.selectByClassIdRandTenStudent(classId);
	}

	public List<String> selectByListClassId(List<String> classId) {
		return societySchoolClassAndStudentMapper.selectByListClassId(classId);
	}

	public List<String> selectByStudentIdVoClassId(String stuId) {
		return societySchoolClassAndStudentMapper.selectByStudentIdVoClassId(stuId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByIdList(List<SocietySchoolClassAndStudentView> stuList) {
		societySchoolClassAndStudentMapper.updateByIdList(stuList);
	}
}
