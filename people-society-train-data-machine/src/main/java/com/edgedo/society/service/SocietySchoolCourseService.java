package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolCourseMapper;
import com.edgedo.society.mapper.SocietyStudentAndCourseMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseView;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import com.edgedo.sys.entity.CheckArr;
import com.edgedo.sys.entity.Dtree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseService {
	
	
	@Autowired
	private SocietySchoolCourseMapper societySchoolCourseMapper;
	@Autowired
	private SocietyStudentAndCourseMapper studentAndCourseMapper;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Autowired
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;
	@Autowired
	private SocietyStudentStudyProcessService societyStudentStudyProcessService;
	@Autowired
	private SocietyStudentStudyProcessFaceService societyStudentStudyProcessFaceService;
	@Autowired
	private SocietyStudentTestPaperService societyStudentTestPaperService;
	@Autowired
	private SocietyStudentTestPaperQuestionService societyStudentTestPaperQuestionService;
	@Autowired
	private SocietyStudentTestPaperQuestionOptionService societyStudentTestPaperQuestionOptionService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolMajorService societySchoolMajorService;
	@Autowired
	private SocietySchoolCourseClsService societySchoolCourseClsService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;

	public List<SocietySchoolCourseView> listPage(SocietySchoolCourseQuery societySchoolCourseQuery){
		List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPage(societySchoolCourseQuery);
		list.forEach(course ->{
			String courseId = course.getId();
			String schoolId = course.getOwnerSchoolId();
			//???????????????????????????
			int finishedCourseStuNum = studentAndCourseMapper.countFinishedCourseStuNum(schoolId,courseId);
			course.setFinishedStudentNum(finishedCourseStuNum);
		});
		societySchoolCourseQuery.setList(list);
		return list;
	}

	public List<SocietySchoolCourseView> listPageCourseGloble(SocietySchoolCourseQuery societySchoolCourseQuery){
		List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPageCourseGloble(societySchoolCourseQuery);
		societySchoolCourseQuery.setList(list);
		return list;
	}

	public List<SocietySchoolCourseView> listPageCountCourse(SocietySchoolCourseQuery societySchoolCourseQuery){
		List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPage(societySchoolCourseQuery);
		for(SocietySchoolCourseView societySchoolCourseView : list){
			//????????????????????????
			Integer num = studentAndCourseMapper.selectByCourseStudyNum(societySchoolCourseView.getId());
			societySchoolCourseView.setCourseStudyNum(num);
		}
		societySchoolCourseQuery.setList(list);
		return list;
	}

	public List<SocietySchoolCourseView> listByObj(SocietySchoolCourseQuery societySchoolCourseQuery){
		List list = societySchoolCourseMapper.listByObj(societySchoolCourseQuery);
		societySchoolCourseQuery.setList(list);
		return list;
	}

	/***
	 * ????????????
	 * @return
	 */
	public Integer count(SocietySchoolCourseQuery query) {
		int num = societySchoolCourseMapper.count(query);
		return num;
	}

	/**
	 * ??????????????????
	 * @param societySchoolCourseQuery
	 * @return
	 */
	public List<SocietySchoolCourseView> classCourseListPage(SocietySchoolCourseQuery societySchoolCourseQuery){
		List list = societySchoolCourseMapper.classCourseListPage(societySchoolCourseQuery);
		societySchoolCourseQuery.setList(list);
		return list;
	}
	/***
	 * ????????????
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourse societySchoolCourse) {
		if(societySchoolCourse.getTeacherName() != null
				&& !societySchoolCourse.getTeacherName().equals("")){
			String[] str = societySchoolCourse.getTeacherName().split("@@");
			societySchoolCourse.setTeacherId(str[0]);
			societySchoolCourse.setTeacherName(str[1]);
		}
		societySchoolCourse.setId(Guid.guid());
		societySchoolCourseMapper.insert(societySchoolCourse);
		return "";
	}


	/**
	 * ????????????  ?????? ???????????? ??????
	 * @param societySchoolCourse
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertMultistageAdd(SocietySchoolCourse societySchoolCourse) {
		SocietySchool societySchool = societySchoolService.loadById(societySchoolCourse.getOwnerSchoolId());
		//?????????
		SocietySchoolMajor societySchoolMajor = new SocietySchoolMajor();
		societySchoolMajor.setId(Guid.guid());
		societySchoolMajor.setCreateUserId(societySchoolCourse.getCreateUserId());
		societySchoolMajor.setCreateTime(societySchoolCourse.getCreateTime());
		societySchoolMajor.setCreateUserName(societySchoolCourse.getCreateUserName());
		societySchoolMajor.setOwnerSchoolId(societySchool.getId());
		societySchoolMajor.setOwnerSchoolName(societySchool.getSchoolName());
		societySchoolMajor.setMajorName(societySchoolCourse.getOwnerMajorName());
		societySchoolMajor.setDataState("1");
		societySchoolMajor.setOrderNum(new BigDecimal(societySchoolMajorService.countAll(societySchool.getId())+1));
		societySchoolMajorService.insertMultistageAdd(societySchoolMajor);
		//????????????
		SocietySchoolCourseCls societySchoolCourseCls = new SocietySchoolCourseCls();
		societySchoolCourseCls.setId(Guid.guid());
		societySchoolCourseCls.setCreateTime(societySchoolCourse.getCreateTime());
		societySchoolCourseCls.setCreateUserId(societySchoolCourse.getCreateUserId());
		societySchoolCourseCls.setCreateUserName(societySchoolCourse.getCreateUserName());
		societySchoolCourseCls.setOwnerMajorId(societySchoolMajor.getId());
		societySchoolCourseCls.setOwnerMajorName(societySchoolMajor.getMajorName());
		societySchoolCourseCls.setOwnerSchoolId(societySchool.getId());
		societySchoolCourseCls.setOwnerSchoolName(societySchool.getSchoolName());
		societySchoolCourseCls.setCourseClsName(societySchoolCourse.getCourseClsName());
		societySchoolCourseCls.setDataState("1");
		societySchoolCourseCls.setOrderNum(new BigDecimal(societySchoolCourseClsService.count(societySchool.getId())+1));
		societySchoolCourseClsService.insertMultistageAdd(societySchoolCourseCls);
		//????????????
		societySchoolCourse.setOwnerSchoolName(societySchool.getSchoolName());
		societySchoolCourse.setOwnerMajorId(societySchoolMajor.getId());
		societySchoolCourse.setCourseClsId(societySchoolCourseCls.getId());
		if(societySchoolCourse.getTeacherName() != null
				&& !societySchoolCourse.getTeacherName().equals("")){
			String[] str = societySchoolCourse.getTeacherName().split("@@");
			societySchoolCourse.setTeacherId(str[0]);
			societySchoolCourse.setTeacherName(str[1]);
		}
		societySchoolCourse.setId(Guid.guid());
		societySchoolCourseMapper.insert(societySchoolCourse);
		return "";
	}

	/***
	 * ??????????????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourse societySchoolCourse) {
		societySchoolCourseMapper.updateById(societySchoolCourse);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateTeacher(SocietySchoolCourseView societySchoolCourse) {
		if(societySchoolCourse.getTeacherName().indexOf("@@") != -1){
			String[] str = societySchoolCourse.getTeacherName().split("@@");
			societySchoolCourse.setTeacherId(str[0]);
			societySchoolCourse.setTeacherName(str[1]);
		}

		if(societySchoolCourse.getIsUpdateStudentCourseState()!=null &&
				societySchoolCourse.getIsUpdateStudentCourseState().equals("???")){
			Map<String,Object> map = new HashMap<>();
			map.put("courseId",societySchoolCourse.getId());
			map.put("schoolId",societySchoolCourse.getOwnerSchoolId());
			map.put("ordStuCourseStudyNum",societySchoolCourse.getOrdStuCourseStudyNum());
			map.put("impStuCourseStudyNum",societySchoolCourse.getImpStuCourseStudyNum());
			map.put("compStuCourseStudyNum",societySchoolCourse.getCompStuCourseStudyNum());
			map.put("faceNum",societySchoolCourse.getCourseIsNeedFaceContrast());
			societyStudentAndCourseService.updateByCourseId(map);
		}
		societySchoolCourseMapper.updateById(societySchoolCourse);
		String courseId = societySchoolCourse.getId();
		String courseName = societySchoolCourse.getCourseName();
		Map<String,String> map = new HashMap<>();
		map.put("courseId",courseId);
		map.put("courseName",courseName);
		//society_school_course_node
		societySchoolCourseNodeService.updateByCourseIdAndCourseName(map);
		//society_school_course_node_question
		societySchoolCourseNodeQuestionService.updateByCourseIdAndCourseName(map);
		//society_student_and_course
		studentAndCourseMapper.updateByCourseIdAndCourseName(map);
		//society_student_and_node
		societyStudentAndNodeService.updateByCourseIdAndCourseName(map);
		//society_student_practise_question
		societyStudentPractiseQuestionService.updateByCourseIdAndCourseName(map);
		//society_student_certificate
		societyStudentCertificateService.updateByCourseIdAndCourseName(map);
		//society_student_study_process
		societyStudentStudyProcessService.updateByCourseIdAndCourseName(map);
		//society_student_study_process_face
		societyStudentStudyProcessFaceService.updateByCourseIdAndCourseName(map);
		//society_student_test_paper
		societyStudentTestPaperService.updateByCourseIdAndCourseName(map);
		//society_student_test_paper_question
		societyStudentTestPaperQuestionService.updateByCourseIdAndCourseName(map);
		//society_student_test_paper_question_option
		societyStudentTestPaperQuestionOptionService.updateByCourseIdAndCourseName(map);
		return "";
	}


	/***
	 * ?????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourse societySchoolCourse) {
		societySchoolCourseMapper.updateAllColumnById(societySchoolCourse);
		return "";
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseMapper.deleteById(id);
	}
	
	/**
	 * ????????????
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseMapper.deleteBatchIds(ids);
	}

	/**
	 * ????????????
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByIds(List<String> ids) {
		societySchoolCourseMapper.updateByIds(ids);
	}


	/**
	 * ????????????
	 * @param id
	 */
	public SocietySchoolCourse loadById(String id) {
		return societySchoolCourseMapper.selectById(id);
	}


	public List<SocietySchoolCourseView> classNotInCourseListPage(SocietySchoolCourseQuery query) {
		List list = societySchoolCourseMapper.classNotInCourseListPage(query);
		query.setList(list);
		return list;
	}

	public List<SocietySchoolCourseView> courseTableListpage(SocietySchoolCourseQuery query) {
		List list = societySchoolCourseMapper.courseTablepage(query);
		query.setList(list);
		return list;
	}
	public List<SocietySchoolCourseView> classYesInCourseListPage(SocietySchoolCourseQuery query) {
		List list = societySchoolCourseMapper.classYesInCourseListPage(query);
		query.setList(list);
		return list;
	}

	//????????????
	public int countCourseAllNum() {
		return societySchoolCourseMapper.countCourseAllNum();
	}
	/*
	* ???????????????????????????????????????
	* */
	public void updateByIdNew(String id) {
		societySchoolCourseMapper.updateByIdNew(id);
	}

	public List<SocietySchoolCourseView> selectAllCourse(String schoolId) {
		return societySchoolCourseMapper.selectAllCourse(schoolId);
	}


	public int selectBySchoolId(String ownerSchoolId) {
		return societySchoolCourseMapper.selectBySchoolId(ownerSchoolId);
	}

	public int selectBySchoolIdAndTeacher(String schoolId, String id) {
		return societySchoolCourseMapper.selectBySchoolIdAndTeacher(schoolId,id);
	}

	public List<Dtree> listForDtreeBySchoolId(String schoolId) {
		List<SocietySchoolCourseView> societySchoolCourseViewList = societySchoolCourseMapper.listBySchoolId(schoolId);
		List<Dtree> dtreeList = new ArrayList<>();
		CheckArr checkArr = new CheckArr();
		checkArr.setChecked("0");
		checkArr.setType("0");
		List<CheckArr> list = new ArrayList<>();
		list.add(checkArr);
		for (SocietySchoolCourseView societySchoolCourseView:societySchoolCourseViewList){
			Dtree dtree = new Dtree();
			dtree.setId(societySchoolCourseView.getId());
			dtree.setTitle(societySchoolCourseView.getCourseName());
			dtree.setParentId("ROOT");
			dtree.setSpread(false);
			dtree.setLast(false);
			dtree.setCheckArr(list);
			dtreeList.add(dtree);
		}
		return  dtreeList;
	}

	public List<SocietySchoolCourse> selectAll() {
		return societySchoolCourseMapper.selectAll();
	}

	public List<SocietySchoolCourse> listMajorId(String schoolId, String majorId) {
		return societySchoolCourseMapper.listMajorId(schoolId,majorId);
	}

	public List<SocietySchoolCourseView> selectCourseAllIsNoSchool(String schoolId) {
		return societySchoolCourseMapper.selectCourseAllIsNoSchool(schoolId);
	}

	public List<SocietySchoolCourseView> selectCourseAllIsSchool(String schoolId) {
		return societySchoolCourseMapper.selectCourseAllIsSchool(schoolId);
	}
	public void updateByMajorId(String majorId, String majorName) {
		societySchoolCourseMapper.updateByMajorId(majorId,majorName);
	}

	public void updateByClsId(String clsId, String clsName, String majorId, String majorName) {
		societySchoolCourseMapper.updateByClsId(clsId,clsName,majorId,majorName);
	}

	public void updateByTeacherId(String teacherId, String teacherName) {
		societySchoolCourseMapper.updateByTeacherId(teacherId,teacherName);
	}

	public int selectSchoolIdAndCourseId(String schoolId, String courseId) {
		return societySchoolCourseMapper.selectSchoolIdAndCourseId(schoolId,courseId);
	}
}
