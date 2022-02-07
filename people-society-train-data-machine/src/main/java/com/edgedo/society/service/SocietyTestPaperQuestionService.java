package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyTestPaperQuestion;
import com.edgedo.society.mapper.SocietyTestPaperQuestionMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyTestPaperQuestionService {
	
	
	@Autowired
	private SocietyTestPaperQuestionMapper societyTestPaperQuestionMapper;

	public List<SocietyTestPaperQuestionView> listPage(SocietyTestPaperQuestionQuery societyTestPaperQuestionQuery){
		List list = societyTestPaperQuestionMapper.listPage(societyTestPaperQuestionQuery);
		societyTestPaperQuestionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyTestPaperQuestion societyTestPaperQuestion) {
		societyTestPaperQuestion.setId(Guid.guid());
		societyTestPaperQuestionMapper.insert(societyTestPaperQuestion);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyTestPaperQuestion societyTestPaperQuestion) {
		societyTestPaperQuestionMapper.updateById(societyTestPaperQuestion);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyTestPaperQuestion societyTestPaperQuestion) {
		societyTestPaperQuestionMapper.updateAllColumnById(societyTestPaperQuestion);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyTestPaperQuestionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyTestPaperQuestionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyTestPaperQuestion loadById(String id) {
		return societyTestPaperQuestionMapper.selectById(id);
	}

	/**
	 * 考试题目表根据学校id 课程id 试卷id  查出一套试卷的所有题目
	 * @param ownerSchoolId, ownerCourseId, ownerTestPaperId, studentId
	 * @return
	 */
	public List<SocietyTestPaperQuestion> selectTestPaperAll(String ownerSchoolId,String ownerCourseId,
															 String ownerTestPaperId,String studentId) {
		Map<String,String> map = new HashMap<>();
		map.put("ownerSchoolId",ownerSchoolId);
		map.put("ownerCourseId",ownerCourseId);
		map.put("ownerTestPaperId",ownerTestPaperId);
		map.put("studentId",studentId);
		return societyTestPaperQuestionMapper.selectTestPaperAll( map);
	}

	public SocietyTestPaperQuestionQuery selectByAllQuestionlistPage(
			Integer limit,Integer page,Integer start,
			String ownerSchoolId, String ownerCourseId, String ownerTestPaperId, String studentId) {
		SocietyTestPaperQuestionQuery societyTestPaperQuestionQuery = new SocietyTestPaperQuestionQuery();
		societyTestPaperQuestionQuery.getQueryObj().setOwnerSchoolId(ownerSchoolId);
		societyTestPaperQuestionQuery.getQueryObj().setOwnerCourseId(ownerCourseId);
		societyTestPaperQuestionQuery.getQueryObj().setOwnerTestPaperId(ownerTestPaperId);
		societyTestPaperQuestionQuery.setLimit(limit);
		societyTestPaperQuestionQuery.setPage(page);
		societyTestPaperQuestionQuery.setStart(start);
		societyTestPaperQuestionQuery.setSuccess(true);
		List<SocietyTestPaperQuestionView> list =
				societyTestPaperQuestionMapper.selectByAllQuestionlistPage(societyTestPaperQuestionQuery);
		societyTestPaperQuestionQuery.setList(list);
		return societyTestPaperQuestionQuery;
	}
}
