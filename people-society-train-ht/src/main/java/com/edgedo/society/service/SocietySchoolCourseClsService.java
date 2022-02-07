package com.edgedo.society.service;
		
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseCls;
import com.edgedo.society.entity.SocietySchoolMajor;
import com.edgedo.society.mapper.SocietySchoolCourseClsMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseClsQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseClsView;
import com.edgedo.sys.entity.Dtree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseClsService {
	
	
	@Autowired
	private SocietySchoolCourseClsMapper societySchoolCourseClsMapper;
	@Autowired
	private SocietySchoolCourseClsService societySchoolCourseClsService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
	private SocietySchoolMajorService societySchoolMajorService;


	public List<SocietySchoolCourseClsView> listPage(SocietySchoolCourseClsQuery societySchoolCourseClsQuery){
		List list = societySchoolCourseClsMapper.listPage(societySchoolCourseClsQuery);
		societySchoolCourseClsQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseCls societySchoolCourseCls) {
		societySchoolCourseCls.setId(Guid.guid());
		societySchoolCourseClsMapper.insert(societySchoolCourseCls);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertMultistageAdd(SocietySchoolCourseCls societySchoolCourseCls) {
		societySchoolCourseClsMapper.insert(societySchoolCourseCls);
		return "";
	}

	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseCls societySchoolCourseCls) {
		SocietySchoolMajor societySchoolMajor =
				societySchoolMajorService.loadById(societySchoolCourseCls.getOwnerMajorId());
		societySchoolCourseCls.setOwnerMajorName(societySchoolMajor.getMajorName());
		societySchoolCourseClsMapper.updateById(societySchoolCourseCls);
		//修改课程分类中的专业的信息
		String clsId = societySchoolCourseCls.getId();
		String clsName = societySchoolCourseCls.getCourseClsName();
		String majorId = societySchoolCourseCls.getOwnerMajorId();
		String majorName = societySchoolCourseCls.getOwnerMajorName();
		//修改课程中的专业信息
		societySchoolCourseService.updateByClsId(clsId,clsName,majorId,majorName);
		//课程章节表 专业信息
		societySchoolCourseNodeService.updateByClsId(clsId,clsName,majorId,majorName);
		//章节题目 专业信息
		societySchoolCourseNodeQuestionService.updateByClsId(clsId,clsName,majorId,majorName);
		return "";
	}


	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseCls societySchoolCourseCls) {
		societySchoolCourseClsMapper.updateAllColumnById(societySchoolCourseCls);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseClsMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseClsMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseCls loadById(String id) {
		return societySchoolCourseClsMapper.selectById(id);
	}


	/*
	* 根据专业id和学校ID查询课程分类
	* */
	public List<Dtree> listByMajorIdAndSchoolId(String majorId,String schoolId) {
		Map<String,String> map = new HashMap<>();
		map.put("majorId",majorId);
		map.put("schoolId",schoolId);
		List<SocietySchoolCourseCls> courseClsList = societySchoolCourseClsMapper.listByMajorIdAndSchoolId(map);
		List<Dtree> dtreeList = new ArrayList<>();
		for(SocietySchoolCourseCls c:courseClsList){
			Dtree dtree = new Dtree();
			dtree.setId(c.getId());
			dtree.setTitle(c.getCourseClsName());
			dtree.setParentId(majorId);
			dtreeList.add(dtree);
		}
		return dtreeList;
	}

	/*
	* 根据专业id和学校ID查询课程分类
	* */
	public List<Dtree> listCourseClsByMajorId(String majorId,String schoolId) {
		Map<String,String> map = new HashMap<>();
		map.put("majorId",majorId);
		map.put("schoolId",schoolId);
		List<SocietySchoolCourseCls> courseClsList = societySchoolCourseClsMapper.listByMajorIdAndSchoolId(map);
		List<Dtree> dtreeList = new ArrayList<>();
		for(SocietySchoolCourseCls c:courseClsList){
			Dtree dtree = new Dtree();
			dtree.setId(c.getId());
			dtree.setTitle(c.getCourseClsName());
			dtree.setParentId("major_"+majorId);
			dtreeList.add(dtree);
		}
		return dtreeList;
	}

	public void logicaDeletion(List<String> list) {
		societySchoolCourseClsMapper.logicaDeletion(list);
	}

	public void updateByMajorId(String majorId, String majorName) {
		societySchoolCourseClsMapper.updateByMajorId(majorId,majorName);
	}

	public Integer count(String schoolId) {
		return societySchoolCourseClsMapper.count(schoolId);
	}
}
