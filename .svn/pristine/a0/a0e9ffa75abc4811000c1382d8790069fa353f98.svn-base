package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentPractiseQuestOption;
import com.edgedo.society.mapper.SocietyStudentPractiseQuestOptionMapper;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionQuery;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentPractiseQuestOptionService {
	
	
	@Autowired
	private SocietyStudentPractiseQuestOptionMapper societyStudentPractiseQuestOptionMapper;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;

	public List<SocietyStudentPractiseQuestOptionView> listPage(SocietyStudentPractiseQuestOptionQuery societyStudentPractiseQuestOptionQuery){
		List list = societyStudentPractiseQuestOptionMapper.listPage(societyStudentPractiseQuestOptionQuery);
		societyStudentPractiseQuestOptionQuery.setList(list);
		return list;
	}

	public List<SocietyStudentPractiseQuestOptionView> selectByQuesionIdlistPage(SocietyStudentPractiseQuestOptionQuery societyStudentPractiseQuestOptionQuery){
		List list = societyStudentPractiseQuestOptionMapper.selectByQuesionIdlistPage(societyStudentPractiseQuestOptionQuery);
		societyStudentPractiseQuestOptionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption) {
		societyStudentPractiseQuestOption.setId(Guid.guid());
		societyStudentPractiseQuestOptionMapper.insert(societyStudentPractiseQuestOption);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption) {
		societyStudentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption) {
		societyStudentPractiseQuestOptionMapper.updateAllColumnById(societyStudentPractiseQuestOption);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentPractiseQuestOptionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentPractiseQuestOptionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentPractiseQuestOption loadById(String id) {
		return societyStudentPractiseQuestOptionMapper.selectById(id);
	}


	public List<SocietyStudentPractiseQuestOption> selectByQuesionId(String quersionId, String ownerNodeId, String studentId) {
		return societyStudentPractiseQuestOptionMapper.selectByQuesionId( quersionId,  ownerNodeId,  studentId);
	}

	public List<SocietyStudentPractiseQuestOptionView> selectByOneOption(String schoolId, String nodeId,
																   String id,String studentId,String ownerStuCourseId) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("id",id);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		return societyStudentPractiseQuestOptionMapper.selectByOneOption(map);
	}

	public List<SocietyStudentPractiseQuestOptionView> selectByOneOptionNew(String schoolId, String nodeId,
																		 String id,String studentId,
																			String ownerStuCourseId,String ascii) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("id",id);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		map.put("ascii",ascii);
		return societyStudentPractiseQuestOptionMapper.selectByOneOptionNew(map);
	}

	public SocietyStudentPractiseQuestOptionView selectByOneOptionOnce(String schoolId, String nodeId,
																		 String id,String studentId,String ownerStuCourseId) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("id",id);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		return societyStudentPractiseQuestOptionMapper.selectByOneOptionOnce(map);
	}

	public SocietyStudentPractiseQuestOptionView selectByOneOptionOnceNew(String schoolId, String nodeId,
																	   String id,String studentId,
																		  String ownerStuCourseId,String ascii) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("id",id);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		map.put("ascii",ascii);
		return societyStudentPractiseQuestOptionMapper.selectByOneOptionOnceNew(map);
	}

	public Integer countRight(String schoolId, String nodeId,String studentId) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("studentId",studentId);
		return societyStudentPractiseQuestOptionMapper.countRight(map);
	}

	public Integer countWrong(String schoolId, String nodeId,String studentId,String ownerStuCourseId) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		return societyStudentPractiseQuestOptionMapper.countWrong(map);
	}

	public void deleteByStuId(String id) {
		societyStudentPractiseQuestOptionMapper.deleteByStuId(id);
	}

    public void updateByNodeIdAndNodeName(String nodeId, String nodeName) {
		societyStudentPractiseQuestOptionMapper.updateByNodeIdAndNodeName(nodeId,nodeName);
    }

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByStuIdAndNodeId(String nodeId, String stuCourseId, String studentId) {
		String nodeStuId = societyStudentAndNodeService.selectByStuCouIdAndNodeId(nodeId,stuCourseId);
		societyStudentPractiseQuestOptionMapper.deleteByStuIdAndNodeId(nodeStuId);
	}

	public List<SocietyStudentPractiseQuestOption> selectByStuNodeAndStuCouId(String stuCouId, String stuNodeId) {
		return societyStudentPractiseQuestOptionMapper.selectByStuNodeAndStuCouId(stuCouId,stuNodeId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByListId(List<SocietyStudentPractiseQuestOption> optionList) {
		societyStudentPractiseQuestOptionMapper.updateByListId(optionList);
	}

	public List<SocietyStudentPractiseQuestOption> selectByQuestionIdAndNodeStuId(String stuNodeId, String questionId) {
		return societyStudentPractiseQuestOptionMapper.selectByQuestionIdAndNodeStuId(stuNodeId,questionId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void inserNewtList(List<SocietyStudentPractiseQuestOption> allOptionList, String ascii) {
		societyStudentPractiseQuestOptionMapper.inserNewtList(allOptionList,ascii);
	}
}
