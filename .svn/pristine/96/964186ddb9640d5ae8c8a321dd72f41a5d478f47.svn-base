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

	public List<SocietyStudentPractiseQuestionView> taskAndStudylistByObjNew(SocietyStudentPractiseQuestionQuery societyStudentPractiseQuestionQuery){
		return societyStudentPractiseQuestionMapper.listByObjNew(societyStudentPractiseQuestionQuery);
	}
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentPractiseQuestion societyStudentPractiseQuestion) {
		societyStudentPractiseQuestion.setId(Guid.guid());
		societyStudentPractiseQuestionMapper.insert(societyStudentPractiseQuestion);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentPractiseQuestion societyStudentPractiseQuestion) {
		societyStudentPractiseQuestionMapper.updateById(societyStudentPractiseQuestion);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentPractiseQuestion societyStudentPractiseQuestion) {
		societyStudentPractiseQuestionMapper.updateAllColumnById(societyStudentPractiseQuestion);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentPractiseQuestionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentPractiseQuestionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentPractiseQuestion loadById(String id) {
		return societyStudentPractiseQuestionMapper.selectById(id);
	}


	public Integer countFraction(SocietyStudentPractiseQuestionQuery societyStudentPractiseQuestionQuery) {
		return societyStudentPractiseQuestionMapper.countFraction(societyStudentPractiseQuestionQuery);
	}
	/*课后习题答题详情*/
	public List<SocietySchoolCourseNodeQuestionView> listPageNodeQuestionDetail(SocietySchoolCourseNodeQuestionQuery query){
		String stuId = query.getQueryObj().getStuId();
		String studentAndNodeId = query.getQueryObj().getOwnerStudentAndNodeId();
		query.setOrderBy("ORDER_NUM ASC");
		//查询章节下面的习题
		List<SocietySchoolCourseNodeQuestionView> list = nodeQuestionMapper.listPage(query);
		//查询学员习题
		list.forEach( question -> {
			String questionId = question.getId();
			List<SocietySchoolCourseNodeOption> nodeOptionList = nodeOptionMapper.selectOptionByQuestion(questionId);
			question.setNodeOptionList(nodeOptionList);
			SocietyStudentPractiseQuestionView studentPractiseQuestionView = societyStudentPractiseQuestionMapper.loadByStuIdAndQuestionId(stuId,studentAndNodeId,questionId);
			if(studentPractiseQuestionView!=null){
				//查询学员的选项
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

	public Integer countRightNew(String ownerStudentAndNodeId,String ascii) {
		Map<String,String> map = new HashMap<>();
		map.put("ownerStudentAndNodeId",ownerStudentAndNodeId);
		map.put("ascii",ascii);
		return societyStudentPractiseQuestionMapper.countRightNew(map);
	}

	public Integer countWrongNew(String ownerStudentAndNodeId,String ascii) {
		Map<String,String> map = new HashMap<>();
		map.put("ownerStudentAndNodeId",ownerStudentAndNodeId);
		map.put("ascii",ascii);
		return societyStudentPractiseQuestionMapper.countWrongNew(map);
	}
	public Integer counNoAnsNew(String schoolId, String nodeId, String studentId, String ownerStuCourseId,String ascii) {
		Map<String,String> map = new HashMap<>();
		map.put("schoolId",schoolId);
		map.put("nodeId",nodeId);
		map.put("studentId",studentId);
		map.put("ownerStuCourseId",ownerStuCourseId);
		map.put("ascii",ascii);
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

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByListId2(List<SocietyStudentPractiseQuestionView> questionList,String ascii) {
		societyStudentPractiseQuestionMapper.updateByListId2(questionList,ascii);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByListIdNew(List<SocietyStudentPractiseQuestion> questionList,String ascii) {
		societyStudentPractiseQuestionMapper.updateByListIdNew(questionList,ascii);
	}

	public Integer countQuestionLength(String stuAndCouId) {
		return societyStudentPractiseQuestionMapper.countQuestionLength(stuAndCouId);
	}

	public Date selectByNodeId(String stuNodeId) {
		return societyStudentPractiseQuestionMapper.selectByNodeId(stuNodeId);
	}

	public List<SocietyStudentPractiseQuestion> selectByStudentId(String studentId) {
		return societyStudentPractiseQuestionMapper.selectByStudentId(studentId);
	}

	public List<SocietyStudentPractiseQuestion> selectByStudentIdAscii(String studentId, String ascii) {
		return societyStudentPractiseQuestionMapper.selectByStudentIdAscii(studentId,ascii);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void inserNewtList(List<SocietyStudentPractiseQuestion> questionList, String ascii) {
		societyStudentPractiseQuestionMapper.inserNewtList(questionList,ascii);
	}

	public List<SocietyStudentPractiseQuestionView> selectByStudentAndNodeId(String ownerStudentAndNodeId,String ascii) {
		return societyStudentPractiseQuestionMapper.selectByStudentAndNodeId(ownerStudentAndNodeId,ascii);
	}

	public SocietyStudentPractiseQuestionView selectByStuNodeIdAndQuestionId(String stuNodeId,
																			 String questionId, String ascii) {
		return societyStudentPractiseQuestionMapper.selectByStuNodeIdAndQuestionId(stuNodeId,questionId,ascii);
	}

	public SocietyStudentPractiseQuestionView selectByQuestionIdAndAscii(String questionId, String ascii) {
		return societyStudentPractiseQuestionMapper.selectByQuestionIdAndAscii(questionId,ascii);
	}

	public List<SocietyStudentPractiseQuestionView> selectBySchoolIdAndNodeId(String schoolId, String oldNodeId,String ascii) {
		return societyStudentPractiseQuestionMapper.selectBySchoolIdAndNodeId(schoolId,oldNodeId,ascii);
	}
}
