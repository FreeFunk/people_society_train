package com.edgedo.society.service;

import java.math.BigDecimal;
import java.util.*;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.mapper.SocietyStudentAndNodeMapper;
import com.edgedo.society.mapper.SocietyStudentMapper;
import com.edgedo.society.queryvo.*;
import com.edgedo.sys.entity.CheckArr;
import com.edgedo.sys.entity.Dtree;
import com.edgedo.tyiyunoosclient.ISysTyiyunCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseNodeService {


	@Autowired
	private SocietySchoolCourseNodeMapper societySchoolCourseNodeMapper;

	@Autowired
	private SocietySchoolCourseService courseService;

	@Autowired
	private SocietyStudentAndNodeMapper studentAndNodeMapper;

	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
	private SocietySchoolCourseNodeOptionService societySchoolCourseNodeOptionService;
	@Autowired
	private ISysTyiyunCloudStorageService sysTyiyunCloudStorageService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Value("${app.tcloudDomain}")
	private String tcloudDomain;

	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Autowired
	private SocietyStudentPractiseQuestOptionService societyStudentPractiseQuestOptionService;
	@Autowired
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	@Autowired
	private SocietyStudentStudyProcessService societyStudentStudyProcessService;
	@Autowired
	private SocietyStudentStudyProcessFaceService societyStudentStudyProcessFaceService;
	@Autowired
	private SocietySchoolTeacherService societySchoolTeacherService;
	@Autowired
	private SocietyNodeResourcesService societyNodeResourcesService;



	public List<SocietySchoolCourseNodeView> listPage(SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery){
		List<SocietySchoolCourseNodeView> list = societySchoolCourseNodeMapper.listPage(societySchoolCourseNodeQuery);
		list.forEach(node -> {
//			String nodeId = node.getId();
			String resourseId = node.getOwnerNodeResourcesId();
			//统计章节下习题的数量
			int countNodeQuestionNum = societySchoolCourseNodeQuestionService.countNodeQuestionNum(resourseId);
			node.setQuestionNum(countNodeQuestionNum);
		});
		societySchoolCourseNodeQuery.setList(list);
		return list;
	}

	public List<String> selectByNodeId(SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery){
		List<String> list = societySchoolCourseNodeMapper.selectByNodeId(societySchoolCourseNodeQuery);
		return list;
	}

	public List<SocietySchoolCourseNodeView> listPageCountNode(SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery){
		List list = societySchoolCourseNodeMapper.listPageCountNode(societySchoolCourseNodeQuery);
		societySchoolCourseNodeQuery.setList(list);
		return list;
	}

	/*
	 * 查询学员的小节的进度
	 * */
	public List<SocietySchoolCourseNodeView> selectStuNodeProgress(SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery){
		List<SocietySchoolCourseNodeView> nodeViewList = societySchoolCourseNodeMapper.listPage(societySchoolCourseNodeQuery);
		SocietyStudent societyStudent= studentService.loadById(societySchoolCourseNodeQuery.getStudentId());
		Map<String,Object> map = new HashMap<>();
		map.put("studentId",societySchoolCourseNodeQuery.getStudentId());
		map.put("courseId",societySchoolCourseNodeQuery.getQueryObj().getOwnerCourseId());
		map.put("ownerStudentAndCourseId",societySchoolCourseNodeQuery.getOwnerStudentAndCourseId());
		for(SocietySchoolCourseNodeView nodeView:nodeViewList){
			nodeView.setStudentId(societyStudent.getId());
			nodeView.setStudentName(societyStudent.getStudentName());
			nodeView.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
			String nodeId= nodeView.getId();
			map.put("nodeId",nodeId);
			SocietyStudentAndNodeView studentAndNodeView = studentAndNodeMapper.selectByNodeIdAndStuId(map);
			if(studentAndNodeView!=null){
				nodeView.setNodeProgress(studentAndNodeView.getNodeProgress().intValue());
				nodeView.setLearnIsFinished(studentAndNodeView.getLearnIsFinished());
				nodeView.setStudyTimeLength(studentAndNodeView.getStudyTimeLength());
			}else {
				nodeView.setNodeProgress(0);
				nodeView.setLearnIsFinished("0");
			}
		}
		societySchoolCourseNodeQuery.setList(nodeViewList);
		return nodeViewList;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseNode societySchoolCourseNode) {
//		societySchoolCourseNode.setId(Guid.guid());
		societySchoolCourseNodeMapper.insert(societySchoolCourseNode);
		return "";
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertNew(SocietySchoolCourseNode societySchoolCourseNode) {
		if (!("".equals(societySchoolCourseNode.getTeacherId()))){
			SocietySchoolTeacher societySchoolTeacher =
					societySchoolTeacherService.loadById(societySchoolCourseNode.getTeacherId());
			societySchoolCourseNode.setTeacherName(societySchoolTeacher.getTeacherName());
		}
		societySchoolCourseNode.setId(Guid.guid());
		societySchoolCourseNodeMapper.insert(societySchoolCourseNode);
		//新增章节的同时修改课程的课时数和课程的时长
		String courseId = societySchoolCourseNode.getOwnerCourseId();
		SocietySchoolCourse course = courseService.loadById(courseId);
		//统计课程下面的小节数
		int countNodes = societySchoolCourseNodeMapper.countNodes(courseId);
		//统计总的课程时长
		int sumTimeLength = societySchoolCourseNodeMapper.sumTimeLength(courseId);
		course.setTotalLessons(countNodes);
		course.setCourseTimeLength(sumTimeLength);
		courseService.update(course);
		return "";
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertCopy(SocietySchoolCourseNode societySchoolCourseNode) {
		societySchoolCourseNodeMapper.insert(societySchoolCourseNode);
		//新增章节的同时修改课程的课时数和课程的时长
		String courseId = societySchoolCourseNode.getOwnerCourseId();
		SocietySchoolCourse course = courseService.loadById(courseId);
		//统计课程下面的小节数
		int countNodes = societySchoolCourseNodeMapper.countNodes(courseId);
		//统计总的课程时长
		int sumTimeLength = societySchoolCourseNodeMapper.sumTimeLength(courseId);
		course.setTotalLessons(countNodes);
		course.setCourseTimeLength(sumTimeLength);
		courseService.update(course);
		return "";
	}

	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseNode societySchoolCourseNode) {
		if (societySchoolCourseNode.getTeacherId()!=null && !societySchoolCourseNode.getTeacherId().equals("")){
			SocietySchoolTeacher societySchoolTeacher =
					societySchoolTeacherService.loadById(societySchoolCourseNode.getTeacherId());
			societySchoolCourseNode.setTeacherName(societySchoolTeacher.getTeacherName());
		}
		societySchoolCourseNodeMapper.updateById(societySchoolCourseNode);
		//统计总的课程时长
		//新增章节的同时修改课程的课时数和课程的时长
		String courseId = societySchoolCourseNode.getOwnerCourseId();
		SocietySchoolCourse course = courseService.loadById(courseId);
		//统计课程下面的小节数
		int countNodes = societySchoolCourseNodeMapper.countNodes(courseId);
		//统计总的课程时长
		int sumTimeLength = societySchoolCourseNodeMapper.sumTimeLength(courseId);
		course.setTotalLessons(countNodes);
		course.setCourseTimeLength(sumTimeLength);
		courseService.update(course);
		//修改所有和章节关联的章节名
		String nodeId = societySchoolCourseNode.getId();
		String nodeName = societySchoolCourseNode.getNodeName();
		//society_school_course_node_option
		societySchoolCourseNodeOptionService.updateByNodeIdAndNodeName(nodeId,nodeName);
		//society_school_course_node_question
		societySchoolCourseNodeQuestionService.updateByNodeIdAndNodeName(nodeId,nodeName);
		//society_student_and_node
		societyStudentAndNodeService.updateByNodeIdAndNodeName(nodeId,nodeName);
		//society_student_practise_quest_option
		societyStudentPractiseQuestOptionService.updateByNodeIdAndNodeName(nodeId,nodeName);
		//society_student_practise_question
		societyStudentPractiseQuestionService.updateByNodeIdAndNodeName(nodeId,nodeName);
		//society_student_study_process
		societyStudentStudyProcessService.updateByNodeIdAndNodeName(nodeId,nodeName);
		//society_student_study_process_face
		societyStudentStudyProcessFaceService.updateByNodeIdAndNodeName(nodeId,nodeName);
		return "";
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateResource(SocietySchoolCourseNode societySchoolCourseNode) {
		societySchoolCourseNodeMapper.updateById(societySchoolCourseNode);
		//统计总的课程时长
		//新增章节的同时修改课程的课时数和课程的时长
		String courseId = societySchoolCourseNode.getOwnerCourseId();
		SocietySchoolCourse course = courseService.loadById(courseId);
		//统计课程下面的小节数
		int countNodes = societySchoolCourseNodeMapper.countNodes(courseId);
		//统计总的课程时长
		int sumTimeLength = societySchoolCourseNodeMapper.sumTimeLength(courseId);
		course.setTotalLessons(countNodes);
		course.setCourseTimeLength(sumTimeLength);
		courseService.update(course);
		return "";
	}

	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseNode societySchoolCourseNode) {
		societySchoolCourseNodeMapper.updateAllColumnById(societySchoolCourseNode);
		return "";
	}

	public SocietySchoolCourseNodeQuery selectByCourseIdAndSchoolIdlistPageExamin(
			Integer limit,Integer page,Integer start,String ordeBy,
			String courseId, String schoolId,String nodeName,String examineState,String stuAndCouId) {
		SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery =
				new SocietySchoolCourseNodeQuery();
		societySchoolCourseNodeQuery.getQueryObj().setOwnerCourseId(courseId);
		societySchoolCourseNodeQuery.getQueryObj().setNodeName(nodeName);
		societySchoolCourseNodeQuery.getQueryObj().setExamineState(examineState);
		societySchoolCourseNodeQuery.getQueryObj().setStuAndCouId(stuAndCouId);
		societySchoolCourseNodeQuery.setLimit(limit);
		societySchoolCourseNodeQuery.setPage(page);
		societySchoolCourseNodeQuery.setStart(start);
		societySchoolCourseNodeQuery.setSuccess(true);
		if(ordeBy==null){//默认排序升序
			societySchoolCourseNodeQuery.setOrderBy("ORDER_NUM ASC");
		}else {
			societySchoolCourseNodeQuery.setOrderBy(ordeBy);
		}
		List<SocietySchoolCourseNodeView> list =
				societySchoolCourseNodeMapper.listPageNew(societySchoolCourseNodeQuery);
		societySchoolCourseNodeQuery.setList(list);
		return societySchoolCourseNodeQuery;
	}


	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {

		return societySchoolCourseNodeMapper.deleteById(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {

		return societySchoolCourseNodeMapper.deleteBatchIds(ids);
	}



	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseNode loadById(String id) {
		return societySchoolCourseNodeMapper.selectById(id);
	}


	/*
	 * 根据课程ID统计下面的章节数量
	 * */
	public int countByCourseId(String courseId) {
		return societySchoolCourseNodeMapper.countByCourseId(courseId);
	}

	/*逻辑删除*/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByIds(List<String> ids,String courseId) {
		societySchoolCourseNodeMapper.updateByIds(ids);
		//统计课程的章节数量
		int count = societySchoolCourseNodeMapper.countByCourseId(courseId);
		SocietySchoolCourse schoolCourse = courseService.loadById(courseId);
		schoolCourse.setTotalLessons(count);
		//修改课程的章节数
		courseService.update(schoolCourse);
	}

	public SocietySchoolCourseNodeQuery selectByCourseIdAndSchoolIdlistPage(
			Integer limit,Integer page,Integer start,String ordeBy,
			String courseId, String schoolId,String nodeName,String examineState,String stuAndCouId) {
		SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery =
				new SocietySchoolCourseNodeQuery();
		societySchoolCourseNodeQuery.getQueryObj().setOwnerCourseId(courseId);
		societySchoolCourseNodeQuery.getQueryObj().setNodeName(nodeName);
		societySchoolCourseNodeQuery.getQueryObj().setExamineState(examineState);
		societySchoolCourseNodeQuery.getQueryObj().setStuAndCouId(stuAndCouId);
		societySchoolCourseNodeQuery.setLimit(limit);
		societySchoolCourseNodeQuery.setPage(page);
		societySchoolCourseNodeQuery.setStart(start);
		societySchoolCourseNodeQuery.setSuccess(true);
		if(ordeBy==null){//默认排序升序
			societySchoolCourseNodeQuery.setOrderBy("ORDER_NUM ASC");
		}else {
			societySchoolCourseNodeQuery.setOrderBy(ordeBy);
		}
		List<SocietySchoolCourseNodeView> list =
				societySchoolCourseNodeMapper.listPage(societySchoolCourseNodeQuery);
		societySchoolCourseNodeQuery.setList(list);
		return societySchoolCourseNodeQuery;
	}

	public Integer selectByClassIdVoNodeNum(String courseId) {
		return societySchoolCourseNodeMapper.selectByClassIdVoNodeNum(courseId);
	}

	public void updateByIdAndQuestionNum(String ownerNodeId, Integer questionNum) {
		societySchoolCourseNodeMapper.updateByIdAndQuestionNum(ownerNodeId,questionNum);
	}

	public List<Dtree> listByMajorIdAndSchoolId(String courseId, String schoolId) {
		Map<String,String> map = new HashMap<>();
		map.put("courseId",courseId);
		map.put("schoolId",schoolId);
		List<SocietySchoolCourseNodeView> courseNodeList = societySchoolCourseNodeMapper.listByMajorIdAndSchoolId(map);
		List<Dtree> dtreeList = new ArrayList<>();
		CheckArr checkArr = new CheckArr();
		checkArr.setChecked("0");
		checkArr.setType("0");
		List<CheckArr> list = new ArrayList<>();
		list.add(checkArr);
		for(SocietySchoolCourseNodeView societySchoolCourseNodeView:courseNodeList){
			Dtree dtree = new Dtree();
			dtree.setId(societySchoolCourseNodeView.getId());
			dtree.setTitle(societySchoolCourseNodeView.getNodeName());
			dtree.setParentId(courseId);
			dtree.setCheckArr(list);
			dtreeList.add(dtree);
		}
		return dtreeList;
	}

	/**
	 * 根据学校id和被复制的课程id查出课程章节的关联记录
	 * @param schoolId
	 * @param courseId
	 * @return
	 */
	public SocietySchoolCourseNode selectByCourseIdAndSchoolId(String schoolId, String courseId) {
		return societySchoolCourseNodeMapper.selectByCourseIdAndSchoolId(schoolId,courseId);
	}

	public void updateById(SocietySchoolCourseNode societySchoolCourseNodeNew) {
		societySchoolCourseNodeMapper.updateById(societySchoolCourseNodeNew);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertCopyNodeAndQuestionAndOption(String courseId, String schoolId,
												   String majorId, String courseClsId,
												   String nodeIdList, String courseNamePinYin,
												   String courseIdOld,User user) {
		//1.切割课程id
		String[] courseArr = nodeIdList.split(",");
		//循环所有的课程id
		for(String arrId : courseArr){
			String resourcesNodeId = Guid.guid();
			//1.查出课程章节关联的记录
			SocietySchoolCourseNode societySchoolCourseNode = loadById(arrId);
			//3.创建对象
			SocietySchoolCourseNode societySchoolCourseNodeNew = new SocietySchoolCourseNodeView();

			if (societySchoolCourseNode.getFileUrl()!=null){
				societySchoolCourseNodeNew.setFileUrl(societySchoolCourseNode.getFileUrl());
			}
			societySchoolCourseNodeNew.setFileId(societySchoolCourseNode.getFileId());
			//根据课程id 查出多少章节
			societySchoolCourseNodeNew.setOrderNum(societySchoolCourseNode.getOrderNum());
			SocietySchoolCourse course = societySchoolCourseService.loadById(courseId);
			societySchoolCourseNodeNew.setOwnerSchoolId(course.getOwnerSchoolId());
			societySchoolCourseNodeNew.setOwnerSchoolName(course.getOwnerSchoolName());
			societySchoolCourseNodeNew.setOwnerMajorId(course.getOwnerMajorId());
			societySchoolCourseNodeNew.setOwnerMajorName(course.getOwnerMajorName());
			societySchoolCourseNodeNew.setOwnerCourseClsId(course.getCourseClsId());
			societySchoolCourseNodeNew.setOwnerCourseClsName(course.getCourseClsName());
			societySchoolCourseNodeNew.setOwnerCourseId(course.getId());
			societySchoolCourseNodeNew.setOwnerCourseName(course.getCourseName());
			societySchoolCourseNodeNew.setCreateTime(new Date());
			societySchoolCourseNodeNew.setCreateUserId(user.getUserId());
			societySchoolCourseNodeNew.setCreateUserName(user.getUserName());
			societySchoolCourseNodeNew.setDataState("1");
			societySchoolCourseNodeNew.setShState("1");
			societySchoolCourseNodeNew.setIsOpen(societySchoolCourseNode.getIsOpen());
			societySchoolCourseNodeNew.setNodeName(societySchoolCourseNode.getNodeName());
			societySchoolCourseNodeNew.setNodeTimeLength(societySchoolCourseNode.getNodeTimeLength());
			societySchoolCourseNodeNew.setTeacherId(societySchoolCourseNode.getTeacherId());
			societySchoolCourseNodeNew.setTeacherName(societySchoolCourseNode.getTeacherName());
			societySchoolCourseNodeNew.setId(Guid.guid());
			societySchoolCourseNodeNew.setOwnerNodeResourcesId(resourcesNodeId);
			societySchoolCourseNodeNew.setOwnerNodeResourcesName(societySchoolCourseNodeNew.getNodeName());
			societySchoolCourseNodeNew.setOwnerNodeResourcesSchoolId(societySchoolCourseNodeNew.getOwnerSchoolId());
			insertCopy(societySchoolCourseNodeNew);//课程章节关联
			//插入章节池
			SocietyNodeResources societyNodeResources = new SocietyNodeResources();
			societyNodeResources.setId(resourcesNodeId);
			societyNodeResources.setCreateTime(new Date());
			societyNodeResources.setCreateUserId(user.getUserId());
			societyNodeResources.setCreateUserName(user.getUserName());
			societyNodeResources.setOwnerSchoolId(course.getOwnerSchoolId());
			societyNodeResources.setOwnerSchoolName(course.getOwnerSchoolName());
			societyNodeResources.setOwnerMajorId(course.getOwnerMajorId());
			societyNodeResources.setOwnerMajorName(course.getOwnerMajorName());
			societyNodeResources.setOwnerCourseClsId(course.getCourseClsId());
			societyNodeResources.setOwnerCourseClsName(course.getCourseClsName());
			societyNodeResources.setOwnerCourseId(course.getId());
			societyNodeResources.setOwnerCourseName(course.getCourseName());
			societyNodeResources.setNodeName(societySchoolCourseNodeNew.getNodeName());
			societyNodeResources.setNodeTimeLength(societySchoolCourseNodeNew.getNodeTimeLength());
			societyNodeResources.setImageUrl(societySchoolCourseNodeNew.getImageUrl());
			societyNodeResources.setFileUrl(societySchoolCourseNodeNew.getFileUrl());
			societyNodeResources.setQuestionNum(societySchoolCourseNodeNew.getQuestionNum());
			societyNodeResources.setOrderNum(societySchoolCourseNodeNew.getOrderNum());
			societyNodeResources.setDataState("1");
			societyNodeResources.setFileId(societySchoolCourseNodeNew.getFileId());
			societyNodeResources.setIsOpen(societySchoolCourseNodeNew.getIsOpen());
			societyNodeResourcesService.insertResourcesAndNode(societyNodeResources);
			//1.携带题目  根据课程id 学校id 章节id 专业id 分类id查出list
			List<SocietySchoolCourseNodeQuestion> list = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeIdList(
					courseIdOld,schoolId,arrId);
			for(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion : list){
				SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestionNew = new SocietySchoolCourseNodeQuestion();
				societySchoolCourseNodeQuestionNew.setId(Guid.guid());
				societySchoolCourseNodeQuestionNew.setCreateUserId(user.getUserId());
				societySchoolCourseNodeQuestionNew.setCreateTime(new Date());
				societySchoolCourseNodeQuestionNew.setCreateUserName(user.getUserName());
				societySchoolCourseNodeQuestionNew.setOwnerSchoolId(schoolId);
				societySchoolCourseNodeQuestionNew.setOwnerSchoolName(societySchoolCourseNodeQuestion.getOwnerSchoolName());
				societySchoolCourseNodeQuestionNew.setOwnerMajorId(societySchoolCourseNodeQuestion.getOwnerMajorId());
				societySchoolCourseNodeQuestionNew.setOwnerMajorName(societySchoolCourseNodeQuestion.getOwnerMajorName());
				societySchoolCourseNodeQuestionNew.setOwnerCourseClsId(societySchoolCourseNodeQuestion.getOwnerCourseClsId());
				societySchoolCourseNodeQuestionNew.setOwnerCourseClsName(societySchoolCourseNodeQuestion.getOwnerCourseClsName());
				societySchoolCourseNodeQuestionNew.setOwnerCourseId(societySchoolCourseNodeNew.getOwnerCourseId());
				societySchoolCourseNodeQuestionNew.setOwnerCourseName(societySchoolCourseNodeNew.getOwnerCourseName());
				//关联章节池的id
				societySchoolCourseNodeQuestionNew.setOwnerNodeId(resourcesNodeId);
				societySchoolCourseNodeQuestionNew.setOwnerNodeName(societySchoolCourseNodeNew.getNodeName());
				societySchoolCourseNodeQuestionNew.setQuestionName(societySchoolCourseNodeQuestion.getQuestionName());
				societySchoolCourseNodeQuestionNew.setQuestionType(societySchoolCourseNodeQuestion.getQuestionType());
				societySchoolCourseNodeQuestionNew.setQuestionScore(societySchoolCourseNodeQuestion.getQuestionScore());
				societySchoolCourseNodeQuestionNew.setQuestionAnalysis(societySchoolCourseNodeQuestion.getQuestionAnalysis());
				societySchoolCourseNodeQuestionNew.setQuestionAnswer(societySchoolCourseNodeQuestion.getQuestionAnswer());
				societySchoolCourseNodeQuestionNew.setOrderNum(societySchoolCourseNodeQuestion.getOrderNum());
				societySchoolCourseNodeQuestionNew.setQuestionAnswerId(societySchoolCourseNodeQuestion.getQuestionAnswerId());
				societySchoolCourseNodeQuestionService.insertCopy(societySchoolCourseNodeQuestionNew);
				//1.根据原有习题id查出相关的选项 list
				List<String> optionIdList = new ArrayList<>();
				List<SocietySchoolCourseNodeOption> optionList = societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,
						societySchoolCourseNodeQuestion.getOwnerNodeId(),societySchoolCourseNodeQuestion.getId());
				for(SocietySchoolCourseNodeOption societySchoolCourseNodeOption : optionList){
					SocietySchoolCourseNodeOption societySchoolCourseNodeOptionNew = new SocietySchoolCourseNodeOption();
					societySchoolCourseNodeOptionNew.setId(Guid.guid());
					societySchoolCourseNodeOptionNew.setCreateTime(new Date());
					societySchoolCourseNodeOptionNew.setCreateUserName(user.getUserName());
					societySchoolCourseNodeOptionNew.setCreateUserId(user.getUserId());
					societySchoolCourseNodeOptionNew.setOwnerSchoolId(schoolId);
					societySchoolCourseNodeOptionNew.setOwnerSchoolName(societySchoolCourseNodeOption.getOwnerSchoolName());
					societySchoolCourseNodeOptionNew.setOwnerNodeId(resourcesNodeId);
					societySchoolCourseNodeOptionNew.setOwnerNodeName(societySchoolCourseNodeNew.getNodeName());
					societySchoolCourseNodeOptionNew.setOwnerQuersionId(societySchoolCourseNodeQuestionNew.getId());
					societySchoolCourseNodeOptionNew.setOwnerQuestionName(societySchoolCourseNodeQuestionNew.getQuestionName());
					societySchoolCourseNodeOptionNew.setOptionTitle(societySchoolCourseNodeOption.getOptionTitle());
					societySchoolCourseNodeOptionNew.setOptionName(societySchoolCourseNodeOption.getOptionName());
					societySchoolCourseNodeOptionNew.setIsRight(societySchoolCourseNodeOption.getIsRight());
					if ("1".equals(societySchoolCourseNodeOption.getIsRight())){
						optionIdList.add(societySchoolCourseNodeOptionNew.getId());
					}
					societySchoolCourseNodeOptionNew.setOrderNum(societySchoolCourseNodeOption.getOrderNum());
					societySchoolCourseNodeOptionService.insertCopy(societySchoolCourseNodeOptionNew);
				}
				//更新题目答案id
				societySchoolCourseNodeQuestionNew.setQuestionAnswerId(String.join(",",optionIdList));
				societySchoolCourseNodeQuestionService.update(societySchoolCourseNodeQuestionNew);
			}
			int questionNum =
					societySchoolCourseNodeQuestionService.countQuestionNumByNodeId(societySchoolCourseNodeNew.getId());
			societySchoolCourseNodeNew.setQuestionNum(questionNum);
			updateById(societySchoolCourseNodeNew);
		}

	}

	public List<SocietySchoolCourseNode> selectByCourseIdAndSchoolIdAll(String schoolId,String ownerCourseId) {
		return societySchoolCourseNodeMapper.selectByCourseIdAndSchoolIdAll(schoolId,ownerCourseId);
	}

	public int selectByCourseNameAndId(String fileUrl) {
		return societySchoolCourseNodeMapper.selectByCourseNameAndId(fileUrl);
	}

	public void updateByMajorId(String majorId, String majorName) {
		societySchoolCourseNodeMapper.updateByMajorId(majorId,majorName);
	}
	public void updateByClsId(String clsId, String clsName, String majorId, String majorName) {
		societySchoolCourseNodeMapper.updateByClsId(clsId,clsName,majorId,majorName);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertCourseCopy(SocietySchoolCourseNodeView voboj, User user) {
		String schoolId = user.getCompId();
		String nodeId  = voboj.getNodeId();
		String courseId = voboj.getOwnerCourseId();
		String resourceNodeId = Guid.guid();
		//1.查出课程章节关联的记录
		SocietySchoolCourseNode societySchoolCourseNode = loadById(nodeId);
		//3.创建对象
		SocietySchoolCourseNode societySchoolCourseNodeNew = new SocietySchoolCourseNodeView();
		societySchoolCourseNodeNew.setFileUrl(voboj.getFileUrl());
		if (societySchoolCourseNode.getFileId()!=null){
			societySchoolCourseNodeNew.setFileId(societySchoolCourseNode.getFileId());
		}
		societySchoolCourseNodeNew.setOrderNum(societySchoolCourseNode.getOrderNum());
		SocietySchoolCourse course = societySchoolCourseService.loadById(courseId);
		societySchoolCourseNodeNew.setOwnerSchoolId(course.getOwnerSchoolId());
		societySchoolCourseNodeNew.setOwnerSchoolName(course.getOwnerSchoolName());
		societySchoolCourseNodeNew.setOwnerMajorId(course.getOwnerMajorId());
		societySchoolCourseNodeNew.setOwnerMajorName(course.getOwnerMajorName());
		societySchoolCourseNodeNew.setOwnerCourseClsId(course.getCourseClsId());
		societySchoolCourseNodeNew.setOwnerCourseClsName(course.getCourseClsName());
		societySchoolCourseNodeNew.setOwnerCourseId(course.getId());
		societySchoolCourseNodeNew.setOwnerCourseName(course.getCourseName());
		societySchoolCourseNodeNew.setCreateTime(new Date());
		societySchoolCourseNodeNew.setCreateUserId(user.getUserId());
		societySchoolCourseNodeNew.setCreateUserName(user.getUserName());
		societySchoolCourseNodeNew.setDataState("1");
		societySchoolCourseNodeNew.setShState("1");
		societySchoolCourseNodeNew.setIsOpen(societySchoolCourseNode.getIsOpen());
		societySchoolCourseNodeNew.setNodeName(societySchoolCourseNode.getNodeName());
		societySchoolCourseNodeNew.setNodeTimeLength(voboj.getNodeTimeLength());
		societySchoolCourseNodeNew.setId(Guid.guid());
		societySchoolCourseNodeNew.setTeacherId(societySchoolCourseNode.getTeacherId());
		societySchoolCourseNodeNew.setTeacherName(societySchoolCourseNode.getTeacherName());
		societySchoolCourseNodeNew.setOwnerNodeResourcesId(resourceNodeId);
		societySchoolCourseNodeNew.setOwnerNodeResourcesName(societySchoolCourseNodeNew.getNodeName());
		societySchoolCourseNodeNew.setOwnerNodeResourcesSchoolId(societySchoolCourseNodeNew.getOwnerSchoolId());
		insertCopy(societySchoolCourseNodeNew);//课程章节关联
		//插入章节池
		SocietyNodeResources societyNodeResources = new SocietyNodeResources();
		societyNodeResources.setId(resourceNodeId);
		societyNodeResources.setCreateTime(new Date());
		societyNodeResources.setCreateUserId(user.getUserId());
		societyNodeResources.setCreateUserName(user.getUserName());
		societyNodeResources.setOwnerSchoolId(course.getOwnerSchoolId());
		societyNodeResources.setOwnerSchoolName(course.getOwnerSchoolName());
		societyNodeResources.setOwnerMajorId(course.getOwnerMajorId());
		societyNodeResources.setOwnerMajorName(course.getOwnerMajorName());
		societyNodeResources.setOwnerCourseClsId(course.getCourseClsId());
		societyNodeResources.setOwnerCourseClsName(course.getCourseClsName());
		societyNodeResources.setOwnerCourseId(course.getId());
		societyNodeResources.setOwnerCourseName(course.getCourseName());
		societyNodeResources.setNodeName(societySchoolCourseNodeNew.getNodeName());
		societyNodeResources.setNodeTimeLength(societySchoolCourseNodeNew.getNodeTimeLength());
		societyNodeResources.setImageUrl(societySchoolCourseNodeNew.getImageUrl());
		societyNodeResources.setFileUrl(societySchoolCourseNodeNew.getFileUrl());
		societyNodeResources.setQuestionNum(societySchoolCourseNodeNew.getQuestionNum());
		societyNodeResources.setOrderNum(societySchoolCourseNodeNew.getOrderNum());
		societyNodeResources.setDataState("1");
		societyNodeResources.setFileId(societySchoolCourseNodeNew.getFileId());
		societyNodeResources.setIsOpen(societySchoolCourseNodeNew.getIsOpen());
		societyNodeResourcesService.insertResourcesAndNode(societyNodeResources);
		//1.携带题目  根据课程id 学校id 章节id 专业id 分类id查出list
		List<SocietySchoolCourseNodeQuestion> list = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeIdList(
				societySchoolCourseNode.getOwnerCourseId(),schoolId,nodeId);
		for(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion : list){
			SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestionNew = new SocietySchoolCourseNodeQuestion();
			societySchoolCourseNodeQuestionNew.setId(Guid.guid());
			societySchoolCourseNodeQuestionNew.setCreateUserId(user.getUserId());
			societySchoolCourseNodeQuestionNew.setCreateTime(new Date());
			societySchoolCourseNodeQuestionNew.setCreateUserName(user.getUserName());
			societySchoolCourseNodeQuestionNew.setOwnerSchoolId(schoolId);
			societySchoolCourseNodeQuestionNew.setOwnerSchoolName(societySchoolCourseNodeQuestion.getOwnerSchoolName());
			societySchoolCourseNodeQuestionNew.setOwnerMajorId(societySchoolCourseNodeQuestion.getOwnerMajorId());
			societySchoolCourseNodeQuestionNew.setOwnerMajorName(societySchoolCourseNodeQuestion.getOwnerMajorName());
			societySchoolCourseNodeQuestionNew.setOwnerCourseClsId(societySchoolCourseNodeQuestion.getOwnerCourseClsId());
			societySchoolCourseNodeQuestionNew.setOwnerCourseClsName(societySchoolCourseNodeQuestion.getOwnerCourseClsName());
			societySchoolCourseNodeQuestionNew.setOwnerCourseId(societySchoolCourseNodeNew.getOwnerCourseId());
			societySchoolCourseNodeQuestionNew.setOwnerCourseName(societySchoolCourseNodeNew.getOwnerCourseName());
			//题目章节id 不能在关联章节表 要关联对应的章节池Id
			societySchoolCourseNodeQuestionNew.setOwnerNodeId(resourceNodeId);
			societySchoolCourseNodeQuestionNew.setOwnerNodeName(societySchoolCourseNodeNew.getNodeName());
			societySchoolCourseNodeQuestionNew.setQuestionName(societySchoolCourseNodeQuestion.getQuestionName());
			societySchoolCourseNodeQuestionNew.setQuestionType(societySchoolCourseNodeQuestion.getQuestionType());
			societySchoolCourseNodeQuestionNew.setQuestionScore(societySchoolCourseNodeQuestion.getQuestionScore());
			societySchoolCourseNodeQuestionNew.setQuestionAnalysis(societySchoolCourseNodeQuestion.getQuestionAnalysis());
			societySchoolCourseNodeQuestionNew.setQuestionAnswer(societySchoolCourseNodeQuestion.getQuestionAnswer());
			societySchoolCourseNodeQuestionNew.setOrderNum(societySchoolCourseNodeQuestion.getOrderNum());
			societySchoolCourseNodeQuestionNew.setQuestionAnswerId(societySchoolCourseNodeQuestion.getQuestionAnswerId());
			societySchoolCourseNodeQuestionService.insertCopy(societySchoolCourseNodeQuestionNew);
			//1.根据原有习题id查出相关的选项 list
			List<String> optionIdList = new ArrayList<>();
			List<SocietySchoolCourseNodeOption> optionList = societySchoolCourseNodeOptionService.selectOptionByOptionList(schoolId,
					societySchoolCourseNodeQuestion.getOwnerNodeId(),societySchoolCourseNodeQuestion.getId());
			for(SocietySchoolCourseNodeOption societySchoolCourseNodeOption : optionList){
				SocietySchoolCourseNodeOption societySchoolCourseNodeOptionNew = new SocietySchoolCourseNodeOption();
				societySchoolCourseNodeOptionNew.setId(Guid.guid());
				societySchoolCourseNodeOptionNew.setCreateTime(new Date());
				societySchoolCourseNodeOptionNew.setCreateUserName(user.getUserName());
				societySchoolCourseNodeOptionNew.setCreateUserId(user.getUserId());
				societySchoolCourseNodeOptionNew.setOwnerSchoolId(schoolId);
				societySchoolCourseNodeOptionNew.setOwnerSchoolName(societySchoolCourseNodeOption.getOwnerSchoolName());
				societySchoolCourseNodeOptionNew.setOwnerNodeId(resourceNodeId);
				societySchoolCourseNodeOptionNew.setOwnerNodeName(societySchoolCourseNodeNew.getNodeName());
				societySchoolCourseNodeOptionNew.setOwnerQuersionId(societySchoolCourseNodeQuestionNew.getId());
				societySchoolCourseNodeOptionNew.setOwnerQuestionName(societySchoolCourseNodeQuestionNew.getQuestionName());
				societySchoolCourseNodeOptionNew.setOptionTitle(societySchoolCourseNodeOption.getOptionTitle());
				societySchoolCourseNodeOptionNew.setOptionName(societySchoolCourseNodeOption.getOptionName());
				societySchoolCourseNodeOptionNew.setIsRight(societySchoolCourseNodeOption.getIsRight());
				if ("1".equals(societySchoolCourseNodeOption.getIsRight())){
					optionIdList.add(societySchoolCourseNodeOptionNew.getId());
				}
				societySchoolCourseNodeOptionNew.setOrderNum(societySchoolCourseNodeOption.getOrderNum());
				societySchoolCourseNodeOptionService.insertCopy(societySchoolCourseNodeOptionNew);
			}
			//更新题目答案id
			societySchoolCourseNodeQuestionNew.setQuestionAnswerId(String.join(",",optionIdList));
			societySchoolCourseNodeQuestionService.update(societySchoolCourseNodeQuestionNew);
		}
		int questionNum =
				societySchoolCourseNodeQuestionService.countQuestionNumByNodeId(societySchoolCourseNodeNew.getId());
		societySchoolCourseNodeNew.setQuestionNum(questionNum);
		updateById(societySchoolCourseNodeNew);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societySchoolCourseNodeMapper.updateByCourseIdAndCourseName(map);
	}

	public List<SocietySchoolCourseNodeView> selectByCourseId(String courseId) {
		return societySchoolCourseNodeMapper.selectByCourseId(courseId);
	}

	public String selectCourseIdAndNodeName(String nodeName, String courseId) {
		return societySchoolCourseNodeMapper.selectCourseIdAndNodeName(nodeName,courseId);
	}

	public List<String> selectAll() {
		return societySchoolCourseNodeMapper.selectAll();
	}

	public void updateByList(List<SocietySchoolCourseNodeView> nodeList) {
		societySchoolCourseNodeMapper.updateByList(nodeList);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByListAndReo(List<SocietySchoolCourseNodeView> nodeViewList) {
		societySchoolCourseNodeMapper.updateByListAndReo(nodeViewList);
	}

	public SocietySchoolCourseNode selectByCourseIdAndResourceId(String courseId, String resourceId) {
		return societySchoolCourseNodeMapper.selectByCourseIdAndResourceId(courseId,resourceId);
	}

	public SocietySchoolCourseNode selectByCourseIdAndResourceIdOnIsOpen(String courseId, String resourceId) {
		return societySchoolCourseNodeMapper.selectByCourseIdAndResourceIdOnIsOpen(courseId,resourceId);
	}

	public void updateByDataStateId(String courseId) {
		societySchoolCourseNodeMapper.updateByDataStateId(courseId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateBySchoolIdAndResourceId(String applySchoolId, String ownReId) {
		societySchoolCourseNodeMapper.updateBySchoolIdAndResourceId(applySchoolId,ownReId);
		SocietySchoolCourseNodeView societySchoolCourseNode =
				societySchoolCourseNodeMapper.selectBySchoolIdAndResoId(applySchoolId,ownReId);
		if (societySchoolCourseNode!=null){
			int count = societySchoolCourseNodeMapper.countByCourseId(societySchoolCourseNode.getOwnerCourseId());
			SocietySchoolCourse schoolCourse = courseService.loadById(societySchoolCourseNode.getOwnerCourseId());
			schoolCourse.setTotalLessons(count);
			//修改课程的章节数
			courseService.update(schoolCourse);
		}
	}

	public SocietySchoolCourseNode selectByApplySchoolIdAndResourceId(String applySchoolId, String ownerNodeResourcesId) {
		return societySchoolCourseNodeMapper.selectByApplySchoolIdAndResourceId(applySchoolId,ownerNodeResourcesId);
	}

    public List<String> selectByResourceId(String ownerNodeResourcesId,String schoolId) {
		return societySchoolCourseNodeMapper.selectByResourceId(ownerNodeResourcesId,schoolId);
    }

    public List<String> selectByCourseIdList(String schoolId) {
        return societySchoolCourseNodeMapper.selectByCourseIdList(schoolId);
    }

	public List<String> selectByCourseIdAndSchoolIdList(String courseId, String schoolId) {
		return societySchoolCourseNodeMapper.selectByCourseIdAndSchoolIdList(courseId,schoolId);
	}

	public Integer selectByCourseIdAndNodeId(String courseId, String ownerNodeResourcesId) {
		return societySchoolCourseNodeMapper.selectByCourseIdAndNodeId(courseId,ownerNodeResourcesId);
	}
}

