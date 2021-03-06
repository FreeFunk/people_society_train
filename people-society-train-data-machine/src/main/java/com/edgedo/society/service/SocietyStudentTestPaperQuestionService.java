package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestion;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestionOption;
import com.edgedo.society.entity.SocietyTestPaperQuestionOption;
import com.edgedo.society.mapper.SocietyStudentTestPaperQuestionMapper;
import com.edgedo.society.mapper.SocietyStudentTestPaperQuestionOptionMapper;
import com.edgedo.society.mapper.SocietyTestPaperQuestionMapper;
import com.edgedo.society.mapper.SocietyTestPaperQuestionOptionMapper;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionView;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentTestPaperQuestionService {
	
	
	@Autowired
	private SocietyStudentTestPaperQuestionMapper societyStudentTestPaperQuestionMapper;
	@Autowired
	private SocietyTestPaperQuestionService testPaperQuestionService;
	@Autowired
	private SocietyTestPaperQuestionOptionMapper testPaperQuestionOptionMapper;
	@Autowired
	private SocietyStudentTestPaperQuestionOptionMapper societyStudentTestPaperQuestionOptionMapper;

	public List<SocietyStudentTestPaperQuestionView> listPage(SocietyStudentTestPaperQuestionQuery societyStudentTestPaperQuestionQuery){
		List list = societyStudentTestPaperQuestionMapper.listPage(societyStudentTestPaperQuestionQuery);
		societyStudentTestPaperQuestionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentTestPaperQuestion societyStudentTestPaperQuestion) {
		societyStudentTestPaperQuestion.setId(Guid.guid());
		societyStudentTestPaperQuestionMapper.insert(societyStudentTestPaperQuestion);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentTestPaperQuestion societyStudentTestPaperQuestion) {
		societyStudentTestPaperQuestionMapper.updateById(societyStudentTestPaperQuestion);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentTestPaperQuestion societyStudentTestPaperQuestion) {
		societyStudentTestPaperQuestionMapper.updateAllColumnById(societyStudentTestPaperQuestion);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentTestPaperQuestionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentTestPaperQuestionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentTestPaperQuestion loadById(String id) {
		return societyStudentTestPaperQuestionMapper.selectById(id);
	}

	/**
	 * 查出学员考试题目表所有已经答完的题目
	 * @param ownerSchoolId, ownerCourseId, ownerTestPaperId, studentId
	 * @return
	 */
	public List<SocietyStudentTestPaperQuestionView> selectByStudentTestPaperComplete(String ownerSchoolId,String ownerCourseId,
																				  String ownerTestPaperId,String studentId) {
		Map<String,String> map = new HashMap<>();
		map.put("ownerSchoolId",ownerSchoolId);
		map.put("ownerCourseId",ownerCourseId);
		map.put("ownerTestPaperId",ownerTestPaperId);
		map.put("studentId",studentId);
		return societyStudentTestPaperQuestionMapper.selectByStudentTestPaperComplete( map) ;
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 查询学员的考试详情
	 *@DateTime: 2020/6/1 22:00
	 */
	public SocietyTestPaperQuestionQuery listByTestPaperQuestionQuery(SocietyStudentTestPaperQuestionQuery societyStudentTestPaperQuestionQuery) {
		String studentId = societyStudentTestPaperQuestionQuery.getQueryObj().getStudentId();
		//去共的题库里查询公共的题目
		SocietyTestPaperQuestionQuery societyTestPaperQuestionQuery = new SocietyTestPaperQuestionQuery();
		societyTestPaperQuestionQuery.setLimit(societyStudentTestPaperQuestionQuery.getLimit());
		societyTestPaperQuestionQuery.setPage(societyStudentTestPaperQuestionQuery.getPage());
		//societyTestPaperQuestionQuery.setStart(societyStudentTestPaperQuestionQuery.getStart());
		societyTestPaperQuestionQuery.getQueryObj().setOwnerTestPaperId(societyStudentTestPaperQuestionQuery.getQueryObj().getOwnerTestPaperId());
		List<SocietyTestPaperQuestionView> testPaperQuestionViewList = testPaperQuestionService.listPage(societyTestPaperQuestionQuery);
		//查询出题目所对应的选项
		testPaperQuestionViewList.forEach(questionView ->{
			String questionId = questionView.getId();
			List<SocietyTestPaperQuestionOption> testPaperQuestionOptionList = testPaperQuestionOptionMapper.selectOptionByQuestionId(questionId);
			questionView.setTestPaperQuestionOptionList(testPaperQuestionOptionList);
			SocietyStudentTestPaperQuestion studentTestPaperQuestion = societyStudentTestPaperQuestionMapper.loadByStuIdAndQuestionId(studentId,questionView.getOwnerTestPaperId(),questionId);
			if(studentTestPaperQuestion!=null){
				//查询出学员的答案
				SocietyStudentTestPaperQuestionOption societyStudentTestPaperQuestionOption = societyStudentTestPaperQuestionOptionMapper.loadStuSelectOption(studentId,studentTestPaperQuestion.getId());
				if(societyStudentTestPaperQuestionOption!=null){
					if(questionView.getQuestionType().equals("1")){
						questionView.setStudentAnswer(societyStudentTestPaperQuestionOption.getOptionTitle());
					}else {
						questionView.setStudentAnswer(societyStudentTestPaperQuestionOption.getOptionName());
					}
					if(!societyStudentTestPaperQuestionOption.getIsRight().equals("1")){
						questionView.setAnswerIsRight("0");
					}
				}else {
					questionView.setStudentAnswer("");
				}
			}
		});
		societyTestPaperQuestionQuery.setList(testPaperQuestionViewList);
		return societyTestPaperQuestionQuery;
	}

	public void deleteByStuId(String id) {
		societyStudentTestPaperQuestionMapper.deleteByStuId(id);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societyStudentTestPaperQuestionMapper.updateByCourseIdAndCourseName(map);
	}
}
