package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestionOption;
import com.edgedo.society.mapper.SocietyStudentTestPaperQuestionOptionMapper;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionOptionQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuestionOptionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentTestPaperQuestionOptionService {
	
	
	@Autowired
	private SocietyStudentTestPaperQuestionOptionMapper societyStudentTestPaperQuestionOptionMapper;

	public List<SocietyStudentTestPaperQuestionOptionView> listPage(SocietyStudentTestPaperQuestionOptionQuery societyStudentTestPaperQuestionOptionQuery){
		List list = societyStudentTestPaperQuestionOptionMapper.listPage(societyStudentTestPaperQuestionOptionQuery);
		societyStudentTestPaperQuestionOptionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentTestPaperQuestionOption societyStudentTestPaperQuestionOption) {
		societyStudentTestPaperQuestionOption.setId(Guid.guid());
		societyStudentTestPaperQuestionOption.setCreateTime(new Date());
		societyStudentTestPaperQuestionOption.setDataState("1");
		societyStudentTestPaperQuestionOptionMapper.insert(societyStudentTestPaperQuestionOption);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentTestPaperQuestionOption societyStudentTestPaperQuestionOption) {
		societyStudentTestPaperQuestionOptionMapper.updateById(societyStudentTestPaperQuestionOption);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentTestPaperQuestionOption societyStudentTestPaperQuestionOption) {
		societyStudentTestPaperQuestionOptionMapper.updateAllColumnById(societyStudentTestPaperQuestionOption);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentTestPaperQuestionOptionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentTestPaperQuestionOptionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentTestPaperQuestionOption loadById(String id) {
		return societyStudentTestPaperQuestionOptionMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description  根据问题查询选项列表
	 * @Date 2020/5/11 15:32
	 **/
	public List<SocietyStudentTestPaperQuestionOptionView> selectOptionByQuestion(String studentQuestionId){
		return societyStudentTestPaperQuestionOptionMapper.selectOptionByQuestion(studentQuestionId);
	}


}
