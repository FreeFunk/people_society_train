package com.edgedo.society.service;
		
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestion;
import com.edgedo.society.mapper.SocietyStudentTestPaperQuestionMapper;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentTestPaperQuestionService {
	
	
	@Autowired
	private SocietyStudentTestPaperQuestionMapper societyStudentTestPaperQuestionMapper;

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
		societyStudentTestPaperQuestion.setCreateTime(new Date());
		societyStudentTestPaperQuestion.setDataState("1");
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
	 * @Author ZhangCC
	 * @Description 查询答对题目的数量
	 * @Date 2020/5/12 11:32
	 **/
	public int countRightQuesByPaper(String ownerTestPaperId){
		return societyStudentTestPaperQuestionMapper.countRightQuesByPaper(ownerTestPaperId);
	}
	

}
