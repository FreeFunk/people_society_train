package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * @Author twq
	 * @Description 根据习题id查询选项值
	 * @Date 2020/5/9 16:13
	 **/
	public List<String> selectOptionNameByQuestionId(String questionId){
		return societyTestPaperQuestionOptionMapper.selectOptionNameByQuestionId(questionId);
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
	 * 公共选项表查出list
	 * @param ownerSchoolId
	 * @param ownerCourseId
	 * @param ownerTestPaperId
	 * @param questionId
	 * @return
	 */
	public List<SocietyTestPaperQuestionOption> selectBySchoolIdAndCourseIdAndPaperIdAndQuestId
			(String ownerSchoolId, String ownerCourseId, String ownerTestPaperId, String questionId) {
		Map<String,String> map = new HashMap<>();
		map.put("ownerSchoolId",ownerSchoolId);
		map.put("ownerCourseId",ownerCourseId);
		map.put("ownerTestPaperId",ownerTestPaperId);
		map.put("questionId",questionId);
		return societyTestPaperQuestionOptionMapper.selectBySchoolIdAndCourseIdAndPaperIdAndQuestId( map) ;

	}
}
