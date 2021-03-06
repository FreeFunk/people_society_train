package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * 学员选项表查出list
	 * @param ownerSchoolId
	 * @param ownerCourseId
	 * @param ownerTestPaperId
	 * @param questionId
	 * @return
	 */
	public List<SocietyStudentTestPaperQuestionOption> selectBySchoolIdAndCourseIdAndPaperIdAndQuestId
			(String ownerSchoolId, String ownerCourseId, String ownerTestPaperId, String questionId) {
		Map<String,String> map = new HashMap<>();
		map.put("ownerSchoolId",ownerSchoolId);
		map.put("ownerCourseId",ownerCourseId);
		map.put("ownerTestPaperId",ownerTestPaperId);
		map.put("questionId",questionId);
		return societyStudentTestPaperQuestionOptionMapper.selectBySchoolIdAndCourseIdAndPaperIdAndQuestId( map) ;
	}

	public void deleteByStuId(String id) {
		societyStudentTestPaperQuestionOptionMapper.deleteByStuId(id);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societyStudentTestPaperQuestionOptionMapper.updateByCourseIdAndCourseName(map);
	}
}
