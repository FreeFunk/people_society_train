package com.edgedo.society.service;
		
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolCourseNodeOptionMapper;
import com.edgedo.society.mapper.SocietySchoolCourseNodeQuestionMapper;
import com.edgedo.society.mapper.SocietyStudentPractiseQuestOptionMapper;
import com.edgedo.society.mapper.SocietyStudentPractiseQuestionMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestionQuery;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentPractiseQuestionService {
	
	
	@Autowired
	private SocietyStudentPractiseQuestionMapper societyStudentPractiseQuestionMapper;
	@Autowired
	private SocietySchoolCourseNodeQuestionMapper nodeQuestionMapper;
	@Autowired
	private SocietySchoolCourseNodeOptionMapper nodeOptionMapper;
	@Autowired
	private SocietyStudentPractiseQuestOptionMapper studentPractiseQuestOptionMapper;

	public List<SocietyStudentPractiseQuestionView> listPage(SocietyStudentPractiseQuestionQuery societyStudentPractiseQuestionQuery){
		List list = societyStudentPractiseQuestionMapper.listPage(societyStudentPractiseQuestionQuery);
		societyStudentPractiseQuestionQuery.setList(list);
		return list;
	}

	public List<SocietyStudentPractiseQuestionView> taskAndStudylistPage(SocietyStudentPractiseQuestionQuery societyStudentPractiseQuestionQuery){
		List list = societyStudentPractiseQuestionMapper.taskAndStudylistPage(societyStudentPractiseQuestionQuery);
		societyStudentPractiseQuestionQuery.setList(list);
		return list;
	}

	public List<SocietyStudentPractiseQuestionView> taskAndStudylistByObj(SocietyStudentPractiseQuestionQuery societyStudentPractiseQuestionQuery){
		return societyStudentPractiseQuestionMapper.listByObj(societyStudentPractiseQuestionQuery);
	}

	/***
	 * ????????????
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentPractiseQuestion societyStudentPractiseQuestion) {
		societyStudentPractiseQuestion.setId(Guid.guid());
		societyStudentPractiseQuestionMapper.insert(societyStudentPractiseQuestion);
		return "";
	}
	
	/***
	 * ??????????????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentPractiseQuestion societyStudentPractiseQuestion) {
		societyStudentPractiseQuestionMapper.updateById(societyStudentPractiseQuestion);
		return "";
	}
	
	/***
	 * ?????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentPractiseQuestion societyStudentPractiseQuestion) {
		societyStudentPractiseQuestionMapper.updateAllColumnById(societyStudentPractiseQuestion);
		return "";
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentPractiseQuestionMapper.deleteById(id);
	}
	
	/**
	 * ????????????
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentPractiseQuestionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	public SocietyStudentPractiseQuestion loadById(String id) {
		return societyStudentPractiseQuestionMapper.selectById(id);
	}


	public Integer countFraction(SocietyStudentPractiseQuestionQuery societyStudentPractiseQuestionQuery) {
		return societyStudentPractiseQuestionMapper.countFraction(societyStudentPractiseQuestionQuery);
	}
	/*????????????????????????*/
	public List<SocietySchoolCourseNodeQuestionView> listPageNodeQuestionDetail(SocietySchoolCourseNodeQuestionQuery query){
		String stuId = query.getQueryObj().getStuId();
		String studentAndNodeId = query.getQueryObj().getOwnerStudentAndNodeId();
		query.setOrderBy("ORDER_NUM ASC");
		//???????????????????????????
		List<SocietySchoolCourseNodeQuestionView> list = nodeQuestionMapper.listPage(query);
		//??????????????????
		list.forEach( question -> {
			String questionId = question.getId();
			List<SocietySchoolCourseNodeOption> nodeOptionList = nodeOptionMapper.selectOptionByQuestion(questionId);
			question.setNodeOptionList(nodeOptionList);
			SocietyStudentPractiseQuestionView studentPractiseQuestionView = societyStudentPractiseQuestionMapper.loadByStuIdAndQuestionId(stuId,studentAndNodeId,questionId);
			if(studentPractiseQuestionView!=null){
				//?????????????????????
				String stuSelectOpId = studentPractiseQuestionView.getStuSelectOpId();
				SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption =studentPractiseQuestOptionMapper.selectById(stuSelectOpId);
				question.setAnswerIsRight(studentPractiseQuestionView.getAnswerState());
				if(question.getQuestionType().equals("1")){
					question.setStudentAnswer(societyStudentPractiseQuestOption.getOptionTitle());
				}else {
					question.setStudentAnswer(societyStudentPractiseQuestOption.getOptionName());
				}
			}else {
				question.setStudentAnswer("");
			}
		});
		query.setList(list);
		return list;
	}


	public void deleteByStuId(String id) {
		societyStudentPractiseQuestionMapper.deleteByStuId(id);
	}

	public void updateByStuId(Map<String, String> map) {
		societyStudentPractiseQuestionMapper.updateByStuId(map);
	}

	public Integer countRight(String schoolId, String nodeId, String studentId, String ownerStuCourseId) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		return societyStudentPractiseQuestionMapper.countRight(map);
	}

	public Integer countWrong(String schoolId, String nodeId, String studentId, String ownerStuCourseId) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		return societyStudentPractiseQuestionMapper.countWrong(map);
	}
	public Integer counNoAns(String schoolId, String nodeId, String studentId, String ownerStuCourseId) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		return societyStudentPractiseQuestionMapper.counNoAns(map);
	}

	public void updateByNodeIdAndNodeName(String nodeId, String nodeName) {
		societyStudentPractiseQuestionMapper.updateByNodeIdAndNodeName(nodeId,nodeName);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByStuIdAndNodeId(String nodeId, String stuCourseId, String studentId) {
		societyStudentPractiseQuestionMapper.deleteByStuIdAndNodeId(nodeId,  stuCourseId,  studentId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societyStudentPractiseQuestionMapper.updateByCourseIdAndCourseName(map);
	}

	public List<SocietyStudentPractiseQuestion> selectStuNodeId(String stuNodeId) {
		return societyStudentPractiseQuestionMapper.selectStuNodeId(stuNodeId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateById(SocietyStudentPractiseQuestionView societyStudentPractiseQuestionView) {
		societyStudentPractiseQuestionMapper.updateById(societyStudentPractiseQuestionView);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByListId(List<SocietyStudentPractiseQuestion> questionList) {
		societyStudentPractiseQuestionMapper.updateByListId(questionList);
	}

	public Integer countQuestionLength(String stuAndCouId) {
		return societyStudentPractiseQuestionMapper.countQuestionLength(stuAndCouId);
	}

	public Date selectByNodeId(String stuNodeId) {
		return societyStudentPractiseQuestionMapper.selectByNodeId(stuNodeId);
	}

	public List<SocietyStudentPractiseQuestionView> selectByStuCouAndNodeId(String ownerStuCourseId, String nodeId) {
		return societyStudentPractiseQuestionMapper.selectByStuCouAndNodeId(ownerStuCourseId,nodeId);
	}

	public SocietyStudentPractiseQuestion selectByQuesionIdAndNodeIdAndStuCouId(String questionId, String nodeId, String ownerStuCourseId) {
		return societyStudentPractiseQuestionMapper.selectByQuesionIdAndNodeIdAndStuCouId(questionId,nodeId,ownerStuCourseId);
	}

	public Integer selectByNodeIdAndRight(String stuAndNodeId) {
		return societyStudentPractiseQuestionMapper.selectByNodeIdAndRight(stuAndNodeId);
	}

	public Integer selectByNodeIdAndErr(String stuAndNodeId) {
		return societyStudentPractiseQuestionMapper.selectByNodeIdAndErr(stuAndNodeId);
	}
	public Integer selectByNodeIdAndNull(String stuAndNodeId) {
		return societyStudentPractiseQuestionMapper.selectByNodeIdAndNull(stuAndNodeId);
	}

	public List<SocietyStudentPractiseQuestionView> selectByStuCouAndNodeId1(String ownerStuCourseId, String nodeId,String ascii) {
		return societyStudentPractiseQuestionMapper.selectByStuCouAndNodeId1(ownerStuCourseId,nodeId,ascii);
	}
}
