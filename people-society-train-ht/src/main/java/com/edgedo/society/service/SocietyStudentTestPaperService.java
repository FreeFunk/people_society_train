package com.edgedo.society.service;
		
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentTestPaper;
import com.edgedo.society.mapper.SocietyStudentTestPaperMapper;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentTestPaperService {
	
	
	@Autowired
	private SocietyStudentTestPaperMapper societyStudentTestPaperMapper;

	public List<SocietyStudentTestPaperView> listPage(SocietyStudentTestPaperQuery societyStudentTestPaperQuery){
		List list = societyStudentTestPaperMapper.listPage(societyStudentTestPaperQuery);
		societyStudentTestPaperQuery.setList(list);
		return list;
	}

	public List<SocietyStudentTestPaperView> listByObj(SocietyStudentTestPaperQuery societyStudentTestPaperQuery){
		List list = societyStudentTestPaperMapper.listByObj(societyStudentTestPaperQuery);
		societyStudentTestPaperQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentTestPaper societyStudentTestPaper) {
		societyStudentTestPaper.setId(Guid.guid());
		societyStudentTestPaperMapper.insert(societyStudentTestPaper);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentTestPaper societyStudentTestPaper) {
		societyStudentTestPaperMapper.updateById(societyStudentTestPaper);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentTestPaper societyStudentTestPaper) {
		societyStudentTestPaperMapper.updateAllColumnById(societyStudentTestPaper);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentTestPaperMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentTestPaperMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentTestPaper loadById(String id) {
		return societyStudentTestPaperMapper.selectById(id);
	}


	public void deleteByStuId(String id) {
		societyStudentTestPaperMapper.deleteByStuId(id);
	}

	public void updateByStuId(Map<String, String> map) {
		societyStudentTestPaperMapper.updateByStuId(map);
	}

	public String selectByStuIdAndCourseIdAndSchoolId(String courseId, String studentId, String ownerSchoolId) {
		return societyStudentTestPaperMapper.selectByStuIdAndCourseIdAndSchoolId(courseId,studentId,ownerSchoolId);
	}

	public SocietyStudentTestPaper selectByStuIdAndCourseIdAndSchoolIdOnce(String courseId, String studentId, String ownerSchoolId) {
		return societyStudentTestPaperMapper.selectByStuIdAndCourseIdAndSchoolIdOnce(courseId,studentId,ownerSchoolId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societyStudentTestPaperMapper.updateByCourseIdAndCourseName(map);
	}

	public List<SocietyStudentTestPaper> selectByStuIdAndCourseIdAndSchoolIdList(String studentId, String courseId, String ownerSchoolId) {
		return societyStudentTestPaperMapper.selectByStuIdAndCourseIdAndSchoolIdList( studentId,  courseId,  ownerSchoolId);
	}
}
