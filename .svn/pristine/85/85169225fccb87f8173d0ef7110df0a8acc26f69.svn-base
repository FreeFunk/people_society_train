package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyTestPaperQuestion;
import com.edgedo.society.mapper.SocietyTestPaperQuestionMapper;
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
	 * @Author ZhangCC
	 * @Description 根据试卷和题目类型查询数量
	 * @Date 2020/5/11 14:25
	 **/
	public int countByPaperAndType(String ownerTestPaperId,String questionType){
		Map<String,Object> param = new HashMap<>();
		param.put("ownerTestPaperId",ownerTestPaperId);
		param.put("questionType",questionType);
		return societyTestPaperQuestionMapper.countByPaperAndType(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据试卷查询试题
	 * @Date 2020/5/11 13:07
	 **/
	public List<SocietyTestPaperQuestionView> selectQuestionListByPaper(String ownerTestPaperId){
		return societyTestPaperQuestionMapper.selectQuestionListByPaper(ownerTestPaperId);
	}
	

}
