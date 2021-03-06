package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.Test;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolClassAndCourseMapper;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassAndCourseService {
	
	
	@Autowired
	private SocietySchoolClassAndCourseMapper societySchoolClassAndCourseMapper;

	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;

	@Autowired
	private SocietySchoolClassAndStudentService classAndStudentService;

	@Autowired
	private SocietySchoolClassService classService;

	@Autowired
	private SocietySchoolCourseService courseService;

	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;
	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietySchoolCourseNodeMapper courseNodeMapper;
	@Autowired
	private SocietySchoolService societySchoolService;

	public List<SocietySchoolClassAndCourseView> listPage(SocietySchoolClassAndCourseQuery societySchoolClassAndCourseQuery){
		List list = societySchoolClassAndCourseMapper.listPage(societySchoolClassAndCourseQuery);
		societySchoolClassAndCourseQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassAndCourse societySchoolClassAndCourse) {
		societySchoolClassAndCourse.setId(Guid.guid());
		societySchoolClassAndCourseMapper.insert(societySchoolClassAndCourse);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassAndCourse societySchoolClassAndCourse) {
		societySchoolClassAndCourseMapper.updateById(societySchoolClassAndCourse);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassAndCourse societySchoolClassAndCourse) {
		societySchoolClassAndCourseMapper.updateAllColumnById(societySchoolClassAndCourse);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassAndCourseMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolClassAndCourseMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolClassAndCourse loadById(String id) {
		return societySchoolClassAndCourseMapper.selectById(id);
	}


	/*
	* 根据班级查询关联的课程
	* */
	public List<String> selectCourseIdByClass(String classId) {
		return societySchoolClassAndCourseMapper.selectCourseIdByClass(classId);
	}

	public List<String> selectCourseIdByClassNotId(String classId,String[] courseId) {
		return societySchoolClassAndCourseMapper.selectCourseIdByClassNotId(classId,courseId);
	}

	public List<String> selectCourseIdByClassYesId(String classId,String[] courseId) {
		return societySchoolClassAndCourseMapper.selectCourseIdByClassYesId(classId,courseId);
	}

	//插入课程和班级的关联
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public String insertClassAndCourseRelation(String classId,String courseIds) {
		//查询班级
		SocietySchoolClass schoolClass =classService.loadById(classId);
		String[] courseIdArr = courseIds.split(",");
		//遍历
		for(String courseId:courseIdArr){
			SocietySchoolCourse course = courseService.loadById(courseId);
			SocietySchoolClassAndCourse classAndCourse = new SocietySchoolClassAndCourse();
			classAndCourse.setOwnerSchoolId(schoolClass.getOwnerSchoolId());
			classAndCourse.setClassId(schoolClass.getId());
			classAndCourse.setCourseId(course.getId());
			classAndCourse.setTotalLessons(course.getTotalLessons());
			classAndCourse.setCourseTimeLength(course.getCourseTimeLength());
			classAndCourse.setCreateTime(new Date());
			classAndCourse.setDataState("1");
			//插入课程和班级的关联
			insert(classAndCourse);
			//插入课程和该班级学员的关联
			insertCourseAndStudent(course,classId);
			//统计课程的培训人数更新
			int countByCourseId = studentAndCourseService.countByCourseId(courseId);
			course.setTotalStudentNum(countByCourseId);
			courseService.update(course);
		}
		//统计该班级的所有的课程课时数
		int sumLesson = societySchoolClassAndCourseMapper.SumLesson(schoolClass.getId());
		//修改班级课时时长
		int sumCourseTimeLength = societySchoolClassAndCourseMapper.SumCourseTimeLength(schoolClass.getId());
		schoolClass.setTotalLessons(sumLesson);
		schoolClass.setCourseTimeLength(sumCourseTimeLength);
		classService.update(schoolClass);
		return "";
	}

	//插入课程和该班级学员的关联
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertCourseAndStudent(SocietySchoolCourse course, String classId) {
		//查询没有关联该课程的该班级的学员
		List<SocietySchoolClassAndStudentView> classAndStudentViewList = classAndStudentService.selectNotRelationList(course.getId(),classId);
		SocietySchoolClass societySchoolClass = classService.loadById(classId);
		List<SocietyStudentAndCourse> studentAndCourseList = new ArrayList<>();
		for (SocietySchoolClassAndStudentView s:classAndStudentViewList){
			SocietyStudentAndCourse societyStudentAndCourse = new SocietyStudentAndCourse();
			societyStudentAndCourse.setId(Guid.guid());
			societyStudentAndCourse.setOwnerSchoolId(societySchoolClass.getOwnerSchoolId());
			societyStudentAndCourse.setClassId(classId);
			societyStudentAndCourse.setStudentId(s.getStudentId());
			societyStudentAndCourse.setStudentName(s.getStudentName());
			societyStudentAndCourse.setStudentIdCardNum(s.getStudentIdCardNum());
			societyStudentAndCourse.setCourseId(course.getId());
			societyStudentAndCourse.setCourseName(course.getCourseName());
			societyStudentAndCourse.setCourseImage(course.getCourseImage());
			societyStudentAndCourse.setFinishNodeNum(0);
			societyStudentAndCourse.setCourseProgress(new BigDecimal("0"));
			societyStudentAndCourse.setLearnIsFinished("0");
			societyStudentAndCourse.setTotalLessons(course.getTotalLessons());
			societyStudentAndCourse.setDataState("1");
			//更新位置信息
			societyStudentAndCourse.setProvinceId(societySchoolClass.getProvinceId());
			societyStudentAndCourse.setProvinceName(societySchoolClass.getProvinceName());
			societyStudentAndCourse.setCityId(societySchoolClass.getCityId());
			societyStudentAndCourse.setCityName(societySchoolClass.getCityName());
			societyStudentAndCourse.setXianquId(societySchoolClass.getXianquId());
			societyStudentAndCourse.setXianquName(societySchoolClass.getXianquName());
			BigDecimal coursePrice = course.getCoursePrice();
			//判断课程是否是免费
			if(coursePrice==null || coursePrice.compareTo(BigDecimal.ZERO)==0){
				societyStudentAndCourse.setCoursePrice(new BigDecimal(0));
				societyStudentAndCourse.setCourseOrgPrice(course.getCourseOrgPrice());
				societyStudentAndCourse.setPayState("1");
			}else {
				societyStudentAndCourse.setCoursePrice(course.getCoursePrice());
				societyStudentAndCourse.setCourseOrgPrice(course.getCourseOrgPrice());
				societyStudentAndCourse.setPayState("0");
			}
			studentAndCourseList.add(societyStudentAndCourse);
			//插入学员和课程
		}
		//批量插入
		if(studentAndCourseList!=null && studentAndCourseList.size()>0){
				//将该课程的小节与学员进行关联
			for(int i=0;i<studentAndCourseList.size();i++){
				SocietyStudentAndCourse studentAndCourse = studentAndCourseList.get(i);
				insertCourseNodeAndStudent(course,studentAndCourse);
			}
			studentAndCourseService.insertCourseAndStudent(studentAndCourseList);
		}
	}

	/**
	 * @Author ZhangCC
	 * @Description 插入课程小节和该班级学员的关联
	 * @Date 2020/6/1 11:12
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertCourseNodeAndStudent(SocietySchoolCourse course, SocietyStudentAndCourse studentAndCourse){
		SocietySchool societySchool = societySchoolService.loadById(studentAndCourse.getOwnerSchoolId());
		SocietyStudentAndNode studentAndNode = new SocietyStudentAndNode();
		studentAndNode.setOwnerSchoolId(societySchool.getId());
		studentAndNode.setOwnerSchoolName(societySchool.getSchoolName());
		studentAndNode.setOwnerStudentAndCourseId(studentAndCourse.getId());
		studentAndNode.setStudentId(studentAndCourse.getStudentId());
		studentAndNode.setStudentName(studentAndCourse.getStudentName());
		studentAndNode.setStudentIdCardNum(studentAndCourse.getStudentIdCardNum());
		studentAndNode.setOwnerCourseId(course.getId());
		studentAndNode.setOwnerCourseName(course.getCourseName());
		//更新位置信息
		studentAndNode.setProvinceId(studentAndCourse.getProvinceId());
		studentAndNode.setProvinceName(studentAndCourse.getProvinceName());
		studentAndNode.setCityId(studentAndCourse.getCityId());
		studentAndNode.setCityName(studentAndCourse.getCityName());
		studentAndNode.setXianquId(studentAndCourse.getXianquId());
		studentAndNode.setXianquName(studentAndCourse.getXianquName());
		//查询该课程关联的小节
		SocietySchoolCourseNodeQuery query = new SocietySchoolCourseNodeQuery();
		query.getQueryObj().setOwnerSchoolId(course.getOwnerSchoolId());
		query.getQueryObj().setOwnerCourseId(course.getId());
		query.getQueryObj().setShState("1");
		List<SocietySchoolCourseNodeView> courseNodeList = courseNodeMapper.listByObj(query);
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

	//插入章节和学员的关联
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertStudentAndNode(SocietySchoolCourse course, String classId) {
		//查询没有关联该章节的该班级的学员
		List<SocietySchoolClassAndStudentView> classAndStudentViewList = classAndStudentService.selectNotRelationList(course.getId(),classId);
		List<SocietyStudentAndCourse> studentAndCourseList = new ArrayList<>();
		for (SocietySchoolClassAndStudentView s:classAndStudentViewList){
			SocietyStudentAndCourse societyStudentAndCourse = new SocietyStudentAndCourse();
			societyStudentAndCourse.setId(Guid.guid());
			societyStudentAndCourse.setOwnerSchoolId(course.getOwnerSchoolId());
			societyStudentAndCourse.setClassId(classId);
			societyStudentAndCourse.setStudentId(s.getStudentId());
			societyStudentAndCourse.setStudentName(s.getStudentName());
			societyStudentAndCourse.setStudentIdCardNum(s.getStudentIdCardNum());
			societyStudentAndCourse.setCourseId(course.getId());
			societyStudentAndCourse.setCourseName(course.getCourseName());
			societyStudentAndCourse.setCourseImage(course.getCourseImage());
			societyStudentAndCourse.setFinishNodeNum(0);
			societyStudentAndCourse.setCourseProgress(new BigDecimal("0"));
			societyStudentAndCourse.setLearnIsFinished("0");
			studentAndCourseList.add(societyStudentAndCourse);
		}
		//批量插入
		if(studentAndCourseList!=null && studentAndCourseList.size()>0){
			studentAndCourseService.insertCourseAndStudent(studentAndCourseList);
		}
	}

	/**
	 * 根据班级id 查询相关联的课程
	 * @param classId
	 * @return
	 */
	public List<String> selectCousreIdByClass(String classId) {
		return societySchoolClassAndCourseMapper.selectCourseIdByClass( classId);
	}

	/**
	 * 批量逻辑删除
	 * @param list classId ownerSchoolId
	 */
	public void deteleToClassIdAndSchoolIdAndId(List<String> list, String classId, String ownerSchoolId) {
		societySchoolClassAndCourseMapper.deteleToClassIdAndSchoolIdAndId(list,  classId,  ownerSchoolId);
	}

	/**
	 * 批量逻辑删除
	 * @param list classId ownerSchoolId
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateToClassIdAndSchoolIdAndId(List<String> list, String classId, String ownerSchoolId) {
		societySchoolClassAndCourseMapper.updateToClassIdAndSchoolIdAndId(list,  classId,  ownerSchoolId);
		//删除该该班级所有学生和课程的关联
		studentAndCourseService.updateByClassIdAndCourseId(list,classId);
	}

    public String selectByClassId(String classId) {
		return societySchoolClassAndCourseMapper.selectByClassId(classId);
    }

	public SocietySchoolClassAndCourseView selectClassByOne(String id) {
		return societySchoolClassAndCourseMapper.selectClassByOne(id);
	}

	public void deleteById(String id) {
		societySchoolClassAndCourseMapper.deleteByIdOne(id);
	}

	public void insertNew(SocietySchoolClassView voObj) {
		SocietySchoolClassAndCourse classAndCourse = new SocietySchoolClassAndCourse();
		classAndCourse.setOwnerSchoolId(voObj.getOwnerSchoolId());
		classAndCourse.setClassId(voObj.getId());
		classAndCourse.setCourseId(voObj.getCourseId());
		classAndCourse.setTotalLessons(voObj.getTotalLessons());
		classAndCourse.setCourseTimeLength(voObj.getCourseTimeLength());
		classAndCourse.setDataState("1");
		classAndCourse.setCreateTime(new Date());
		//插入课程和班级的关联
		insert(classAndCourse);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseId(Map<String, String> map) {
		societySchoolClassAndCourseMapper.updateByCourseId(map);
	}

	public List<String> selectByCourseId(String courseId) {
		return societySchoolClassAndCourseMapper.selectByCourseId(courseId);
	}

	public SocietySchoolClassAndCourse selectCourseIdAndClassId(String classId, String courseId) {
		return societySchoolClassAndCourseMapper.selectCourseIdAndClassId(classId,courseId);
	}
}
