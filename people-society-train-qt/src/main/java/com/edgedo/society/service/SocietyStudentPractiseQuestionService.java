package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BusinessException;
import com.edgedo.common.util.Guid;
import com.edgedo.dataenum.SchoolConfigKeyEnum;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietyStudentPractiseQuestOptionMapper;
import com.edgedo.society.mapper.SocietyStudentPractiseQuestionMapper;
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
	private SocietySchoolCourseNodeOptionService courseNodeOptionService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService courseNodeQuestionService;
	@Autowired
	private SocietyStudentPractiseQuestOptionService studentPractiseQuestOptionService;
	@Autowired
	private SocietyStudentAndNodeService stuAndNodeService;
	@Autowired
	private SocietyStudentPractiseQuestOptionMapper societyStudentPractiseQuestOptionMapper;
	@Autowired
	private SocietySchoolConfigService schoolConfigService;
	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;

	public List<SocietyStudentPractiseQuestionView> listPage(SocietyStudentPractiseQuestionQuery societyStudentPractiseQuestionQuery){
		List list = societyStudentPractiseQuestionMapper.listPage(societyStudentPractiseQuestionQuery);
		societyStudentPractiseQuestionQuery.setList(list);
		return list;
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



	/**
	 * @Author WangZhen
	 * @Description 新增或者修改一条学生和问题的关联
	 * @Date 2020/5/9 14:46
	 **/
	public SocietyStudentPractiseQuestionView insertOrUpdateStuPractisQue(
			SocietyStudentAndNode stuNode,
			String queId,
			String selectOpIds
			) {


		SocietyStudentPractiseQuestionView stuNodeQuestion =
				societyStudentPractiseQuestionMapper.selectByQueAndStuNode(
						queId,stuNode.getId()
		);
		String isRight = "1";
		int selectAnsNum = selectOpIds.split(",").length;
		//判断是否正确
		List<String> rightAnswerId = courseNodeOptionService.listRightAnswerIdsByQueId(queId);
		if(selectAnsNum!=rightAnswerId.size()){
			isRight="-1";
		}else{
			for(String ansId : rightAnswerId){
				if(selectOpIds==null || selectOpIds.indexOf(ansId)<0){
					isRight="-1";
					break;
				}
			}
		}

		if(stuNodeQuestion==null){

			stuNodeQuestion = new SocietyStudentPractiseQuestionView();

			stuNodeQuestion.setStuSelectOpId(selectOpIds);
			if(isRight.equals("1")){
				stuNodeQuestion.setAnswerState("1");
				stuNodeQuestion.setGetScore(stuNodeQuestion.getQuestionScore());
			}else{
				stuNodeQuestion.setAnswerState("-1");
				stuNodeQuestion.setGetScore(0);
			}
			SocietySchoolCourseNodeQuestion questionObj = courseNodeQuestionService.loadById(queId);
			stuNodeQuestion.setLastAnswerTime(new Date());
			//以下是新增需要设置的内容
			stuNodeQuestion.setGetScore(0);
			stuNodeQuestion.setId(Guid.guid());
			stuNodeQuestion.setCreateTime(new Date());
			stuNodeQuestion.setDataState("1");
			stuNodeQuestion.setOrderNum(questionObj.getOrderNum());
			stuNodeQuestion.setOwnerCourseId(questionObj.getOwnerCourseId());
			stuNodeQuestion.setOwnerCourseName(questionObj.getOwnerCourseName());
			stuNodeQuestion.setOwnerNodeId(questionObj.getOwnerNodeId());
			stuNodeQuestion.setOwnerNodeName(questionObj.getOwnerNodeName());
			stuNodeQuestion.setOwnerSchoolId(questionObj.getOwnerSchoolId());
			stuNodeQuestion.setOwnerSchoolName(questionObj.getOwnerSchoolName());
			stuNodeQuestion.setOwnerStudentAndNodeId(stuNode.getId());
			stuNodeQuestion.setQuersionId(questionObj.getId());
			stuNodeQuestion.setQuestionAnalysis(questionObj.getQuestionAnalysis());
			stuNodeQuestion.setQuestionName(questionObj.getQuestionName());
			Integer queScore = questionObj.getQuestionScore();
			if(queScore==null){
				queScore=1;
			}
			stuNodeQuestion.setQuestionScore(queScore);
			stuNodeQuestion.setQuestionType(questionObj.getQuestionType());
			stuNodeQuestion.setStudentId(stuNode.getStudentId());
			stuNodeQuestion.setStudentName(stuNode.getStudentName());
			stuNodeQuestion.setStudentIdCardNum(stuNode.getStudentIdCardNum());
			stuNodeQuestion.setOwnerStuCourseId(stuNode.getOwnerStudentAndCourseId());
			stuNodeQuestion.setUpdateTime(new Date());
			societyStudentPractiseQuestionMapper.insert(stuNodeQuestion);
		}else{
			stuNodeQuestion.setStuSelectOpId(selectOpIds);
			if(isRight.equals("1")){
				stuNodeQuestion.setAnswerState("1");
				stuNodeQuestion.setGetScore(stuNodeQuestion.getQuestionScore());
			}else{
				stuNodeQuestion.setAnswerState("-1");
				stuNodeQuestion.setGetScore(0);
			}
			stuNodeQuestion.setLastAnswerTime(new Date());
			//不更新索引字段以免死锁
			stuNodeQuestion.setOwnerStudentAndNodeId(null);
			stuNodeQuestion.setQuersionId(null);
			societyStudentPractiseQuestionMapper.updateById(stuNodeQuestion);
		}
		return stuNodeQuestion;

	}


	public SocietyStudentPractiseQuestionView insertOrUpdateStuPractisQueNew(
			SocietyStudentAndNode stuNode,
			String queId,
			String selectOpIds,String ascii
	) {


		SocietyStudentPractiseQuestionView stuNodeQuestion = null;
		String isRight = "1";
		int selectAnsNum = selectOpIds.split(",").length;
		SocietySchoolCourseNodeQuestion questionObj = courseNodeQuestionService.loadById(queId);
		//判断是否正确
		String reishtAnswerIds = questionObj.getQuestionAnswerId();
		String[] rightAnswerId = reishtAnswerIds.split(",");
		if(selectAnsNum!=rightAnswerId.length){
			isRight="-1";
		}else{
			for(String ansId : rightAnswerId){
				if(selectOpIds==null || selectOpIds.indexOf(ansId)<0){
					isRight="-1";
					break;
				}
			}
		}

		stuNodeQuestion = new SocietyStudentPractiseQuestionView();

		stuNodeQuestion.setStuSelectOpId(selectOpIds);
		if(isRight.equals("1")){
			stuNodeQuestion.setAnswerState("1");
			stuNodeQuestion.setGetScore(stuNodeQuestion.getQuestionScore());
		}else{
			stuNodeQuestion.setAnswerState("-1");
			stuNodeQuestion.setGetScore(0);
		}
		stuNodeQuestion.setLastAnswerTime(new Date());
		//以下是新增需要设置的内容
		stuNodeQuestion.setGetScore(0);
		stuNodeQuestion.setId(Guid.guid());
		stuNodeQuestion.setCreateTime(new Date());
		stuNodeQuestion.setDataState("1");
		stuNodeQuestion.setOrderNum(questionObj.getOrderNum());
		stuNodeQuestion.setOwnerCourseId(questionObj.getOwnerCourseId());
		stuNodeQuestion.setOwnerCourseName(questionObj.getOwnerCourseName());
		stuNodeQuestion.setOwnerNodeId(questionObj.getOwnerNodeId());
		stuNodeQuestion.setOwnerNodeName(questionObj.getOwnerNodeName());
		stuNodeQuestion.setOwnerSchoolId(questionObj.getOwnerSchoolId());
		stuNodeQuestion.setOwnerSchoolName(questionObj.getOwnerSchoolName());
		stuNodeQuestion.setOwnerStudentAndNodeId(stuNode.getId());
		stuNodeQuestion.setQuersionId(questionObj.getId());
		stuNodeQuestion.setQuestionAnalysis(questionObj.getQuestionAnalysis());
		stuNodeQuestion.setQuestionName(questionObj.getQuestionName());
		Integer queScore = questionObj.getQuestionScore();
		if(queScore==null){
			queScore=1;
		}
		stuNodeQuestion.setQuestionScore(queScore);
		stuNodeQuestion.setQuestionType(questionObj.getQuestionType());
		stuNodeQuestion.setStudentId(stuNode.getStudentId());
		stuNodeQuestion.setStudentName(stuNode.getStudentName());
		stuNodeQuestion.setStudentIdCardNum(stuNode.getStudentIdCardNum());
		stuNodeQuestion.setOwnerStuCourseId(stuNode.getOwnerStudentAndCourseId());
		stuNodeQuestion.setUpdateTime(new Date());
//		societyStudentPractiseQuestionMapper.insertNew(stuNodeQuestion,ascii);
		return stuNodeQuestion;

	}


	/**
	 * @Author WangZhen
	 * @Description 根据学生学习节点来查询所有学生的答题情况
	 * @Date 2020/7/8 15:32
	 **/
    public List<SocietyStudentPractiseQuestionView> listByOwnerStuNodeId(String stuNodeId) {
		return societyStudentPractiseQuestionMapper.listByOwnerStuNodeId(stuNodeId);
    }

	public List<SocietyStudentPractiseQuestionView> listByOwnerStuNodeIdNew(String stuNodeId,String ascii) {
		return societyStudentPractiseQuestionMapper.listByOwnerStuNodeIdNew(stuNodeId,ascii);
	}

    /**
     * @Author WangZhen
     * @Description 重新答题
     * @Date 2020/7/9 5:25
     **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void reTestPractise(SocietyStudentAndNode stuNode) {
		stuNode.setQuestionIsFinished("0");
		stuNode.setQuestionIsPass("0");
		stuNode.setNodeQuestionScore(0);
		stuAndNodeService.update(stuNode);
		//设置习题未答题
		societyStudentPractiseQuestionMapper.updateAllStuNodeQuestUnAnswer(stuNode.getId());
		//设置所有答案未选中
		studentPractiseQuestOptionService.updateAllStuNodeOptionUnSelect(stuNode.getId());

	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void reTestPractiseNew(SocietyStudentAndNode stuNode,String ascii) {
		stuNode.setQuestionIsFinished("0");
		stuNode.setQuestionIsPass("0");
		stuNode.setNodeQuestionScore(0);
		stuAndNodeService.update(stuNode);
		//设置习题未答题
		societyStudentPractiseQuestionMapper.deleteStuQuestionByStuNode(stuNode.getId(),ascii);
		//设置所有答案未选中
		studentPractiseQuestOptionService.deleteStuQuesOptionByStuNodeId(stuNode.getId(),ascii);

	}

	public int selectByStuNodeId(String stuNodeId) {
		return societyStudentPractiseQuestionMapper.selectByStuNodeId(stuNodeId);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据学生节点删除题目
	 * @Date 2020/9/8 14:59
	 **/
	public void deleteStuQuestionByStuNode(String stuNodeId,String ascii) {

		societyStudentPractiseQuestionMapper.deleteStuQuestionByStuNode(stuNodeId,ascii);

	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertNewList(List<SocietyStudentPractiseQuestionView> quetionList,String ascii) {
		societyStudentPractiseQuestionMapper.insertNewList(quetionList,ascii);
	}
}
