package com.edgedo.society.service;
		
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseNodeOption;
import com.edgedo.society.mapper.SocietySchoolCourseNodeOptionMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseNodeOptionService {
	
	
	@Autowired
	private SocietySchoolCourseNodeOptionMapper societySchoolCourseNodeOptionMapper;

	public List<SocietySchoolCourseNodeOptionView> listPage(SocietySchoolCourseNodeOptionQuery societySchoolCourseNodeOptionQuery){
		List list = societySchoolCourseNodeOptionMapper.listPage(societySchoolCourseNodeOptionQuery);
		societySchoolCourseNodeOptionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseNodeOption societySchoolCourseNodeOption) {
		societySchoolCourseNodeOption.setId(Guid.guid());
		societySchoolCourseNodeOption.setCreateTime(new Date());
		societySchoolCourseNodeOptionMapper.insert(societySchoolCourseNodeOption);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertCopy(SocietySchoolCourseNodeOption societySchoolCourseNodeOption) {
		societySchoolCourseNodeOptionMapper.insert(societySchoolCourseNodeOption);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseNodeOption societySchoolCourseNodeOption) {
		societySchoolCourseNodeOptionMapper.updateById(societySchoolCourseNodeOption);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseNodeOption societySchoolCourseNodeOption) {
		societySchoolCourseNodeOptionMapper.updateAllColumnById(societySchoolCourseNodeOption);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseNodeOptionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseNodeOptionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseNodeOption loadById(String id) {
		return societySchoolCourseNodeOptionMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据习题查询选项值
	 * @Date 2020/5/9 16:13
	 **/
	public List<String> selectOptionNameByQuestion(String questionId){
		return societySchoolCourseNodeOptionMapper.selectOptionNameByQuestion(questionId);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据习题查询选项
	 * @Date 2020/5/9 16:13
	 **/
	public List<SocietySchoolCourseNodeOption> selectOptionByQuestion(String questionId){
		return societySchoolCourseNodeOptionMapper.selectOptionByQuestion(questionId);
	}

	public List<SocietySchoolCourseNodeOptionView> selectQuestionId(String questionId){
		return societySchoolCourseNodeOptionMapper.selectQuestionId(questionId);
	}

	public List<SocietySchoolCourseNodeOption> selectOptionByOptionList(String schoolId, String nodeId, String quersionId) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("quersionId",quersionId);
		return societySchoolCourseNodeOptionMapper.selectOptionByOptionList(map);
	}

	public void deletenodeOptionList(List<SocietySchoolCourseNodeOption> nodeOptionList) {
		societySchoolCourseNodeOptionMapper.deletenodeOptionList(nodeOptionList);
	}

	public void updateByNodeIdAndNodeName(String nodeId, String nodeName) {
		societySchoolCourseNodeOptionMapper.updateByNodeIdAndNodeName(nodeId,nodeName);
	}
}
