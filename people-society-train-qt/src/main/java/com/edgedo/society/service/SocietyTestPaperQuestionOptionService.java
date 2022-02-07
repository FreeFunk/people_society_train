package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyTestPaperQuestionOption;
import com.edgedo.society.mapper.SocietyTestPaperQuestionOptionMapper;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionOptionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionOptionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyTestPaperQuestionOptionService {
	
	
	@Autowired
	private SocietyTestPaperQuestionOptionMapper societyTestPaperQuestionOptionMapper;

	public List<SocietyTestPaperQuestionOptionView> listPage(SocietyTestPaperQuestionOptionQuery societyTestPaperQuestionOptionQuery){
		List list = societyTestPaperQuestionOptionMapper.listPage(societyTestPaperQuestionOptionQuery);
		societyTestPaperQuestionOptionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyTestPaperQuestionOption societyTestPaperQuestionOption) {
		societyTestPaperQuestionOption.setId(Guid.guid());
		societyTestPaperQuestionOptionMapper.insert(societyTestPaperQuestionOption);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyTestPaperQuestionOption societyTestPaperQuestionOption) {
		societyTestPaperQuestionOptionMapper.updateById(societyTestPaperQuestionOption);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyTestPaperQuestionOption societyTestPaperQuestionOption) {
		societyTestPaperQuestionOptionMapper.updateAllColumnById(societyTestPaperQuestionOption);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyTestPaperQuestionOptionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyTestPaperQuestionOptionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyTestPaperQuestionOption loadById(String id) {
		return societyTestPaperQuestionOptionMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据试题查询试题选项
	 * @Date 2020/5/11 13:40
	 **/
	public List<SocietyTestPaperQuestionOptionView> selectOptionListByQuestion(String paperQuestionId){
		return societyTestPaperQuestionOptionMapper.selectOptionListByQuestion(paperQuestionId);
	}
	

}
