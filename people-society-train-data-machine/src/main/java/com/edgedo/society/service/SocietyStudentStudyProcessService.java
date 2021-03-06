package com.edgedo.society.service;
		
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentStudyProcess;
import com.edgedo.society.mapper.SocietyStudentStudyProcessMapper;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessQuery;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentStudyProcessService {
	
	
	@Autowired
	private SocietyStudentStudyProcessMapper societyStudentStudyProcessMapper;

	public List<SocietyStudentStudyProcessView> listPage(SocietyStudentStudyProcessQuery societyStudentStudyProcessQuery){
		List list = societyStudentStudyProcessMapper.listPage(societyStudentStudyProcessQuery);
		societyStudentStudyProcessQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentStudyProcess societyStudentStudyProcess) {
		societyStudentStudyProcess.setId(Guid.guid());
		societyStudentStudyProcessMapper.insert(societyStudentStudyProcess);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentStudyProcess societyStudentStudyProcess) {
		societyStudentStudyProcessMapper.updateById(societyStudentStudyProcess);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentStudyProcess societyStudentStudyProcess) {
		societyStudentStudyProcessMapper.updateAllColumnById(societyStudentStudyProcess);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentStudyProcessMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentStudyProcessMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentStudyProcess loadById(String id) {
		return societyStudentStudyProcessMapper.selectById(id);
	}


	public void updateByNodeIdAndNodeName(String nodeId, String nodeName) {
		societyStudentStudyProcessMapper.updateByNodeIdAndNodeName(nodeId,nodeName);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societyStudentStudyProcessMapper.updateByCourseIdAndCourseName(map);
	}

	public List<SocietyStudentStudyProcess> selectByStuCouId(String stuCouId) {
		return societyStudentStudyProcessMapper.selectByStuCouId(stuCouId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByListId(List<SocietyStudentStudyProcess> studyList) {
		societyStudentStudyProcessMapper.updateByListId(studyList);
	}
}
