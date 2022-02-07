package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietyStudentTestPaperMapper;
import com.edgedo.society.mapper.SocietyTestPaperMapper;
import com.edgedo.society.queryvo.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentTestPaperService {
	
	
	@Autowired
	private SocietyStudentTestPaperMapper societyStudentTestPaperMapper;
	@Autowired
	private SocietyTestPaperService testPaperService;
	@Autowired
	private SocietyTestPaperQuestionService testPaperQuestionService;
	@Autowired
	private SocietyStudentTestPaperQuestionService studentTestPaperQuestionService;
	@Autowired
	private SocietyTestPaperQuestionOptionService questionOptionService;
	@Autowired
	private SocietyStudentTestPaperQuestionOptionService studentQuestionOptionService;
	@Autowired
	private SocietyStudentCertificateService certificateService;
	@Autowired
	private SocietyStudentService societyStudentService;

	public List<SocietyStudentTestPaperView> listPage(SocietyStudentTestPaperQuery societyStudentTestPaperQuery){
		List list = societyStudentTestPaperMapper.listPage(societyStudentTestPaperQuery);
		societyStudentTestPaperQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentTestPaper societyStudentTestPaper) {
		societyStudentTestPaper.setId(Guid.guid());
		societyStudentTestPaper.setDataState("1");
		societyStudentTestPaper.setIsFinished("0");
		societyStudentTestPaperMapper.insert(societyStudentTestPaper);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentTestPaper societyStudentTestPaper) {
		societyStudentTestPaperMapper.updateById(societyStudentTestPaper);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentTestPaper societyStudentTestPaper) {
		societyStudentTestPaperMapper.updateAllColumnById(societyStudentTestPaper);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentTestPaperMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentTestPaperMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentTestPaper loadById(String id) {
		return societyStudentTestPaperMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询学员该课程考试的次数
	 * @Date 2020/5/11 14:41
	 **/
	public int countByStuAndCourse(String studentId,String ownerCourseId,String finishedState){
		Map<String,Object> param = new HashMap<>();
		param.put("studentId",studentId);
		param.put("ownerCourseId",ownerCourseId);
		param.put("isFinished",finishedState);
		return societyStudentTestPaperMapper.countByStuAndCourse(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description  根据学员，课程和答题情况查询
	 * @Date 2020/5/11 11:09
	 **/
	public List<SocietyStudentTestPaperView> selectPaperByStuCourListPage(SocietyStudentTestPaperQuery query){
		List list = societyStudentTestPaperMapper.selectPaperByStuCourListPage(query);
		query.setList(list);
		return list;
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询一条没有完成的试卷
	 * @Date 2020/5/11 14:48
	 **/
	public SocietyStudentTestPaperView selectNotFinishedPaper(String studentId,String ownerCourseId){
		Map<String,Object> param = new HashMap<>();
		param.put("studentId",studentId);
		param.put("ownerCourseId",ownerCourseId);
		return societyStudentTestPaperMapper.selectNotFinishedPaper(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询学员答题的最高分
	 * @Date 2020/5/11 16:50
	 **/
	public Integer selectTopScoreByCourse(String studentId, String ownerCourseId){
		Map<String,Object> param = new HashMap<>();
		param.put("studentId",studentId);
		param.put("ownerCourseId",ownerCourseId);
		return societyStudentTestPaperMapper.selectTopScoreByCourse(param);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学员和课程生成试卷
	 * @Date 2020/5/11 11:41
	 **/
	public SocietyStudentTestPaper createStuPaper(SocietyStudent student, SocietySchoolCourse course){
		String schoolId = student.getOwnerSchoolId();
		String courseId = course.getId();
		SocietyStudentTestPaper studentTestPaper = new SocietyStudentTestPaper();
		SocietyTestPaperView testPaperView = testPaperService.selectVoByCourseAndSchool(schoolId,courseId);
		if(testPaperView != null){
			//生成学员试卷
			studentTestPaper.setId(Guid.guid());
			studentTestPaper.setCreateTime(new Date());
			studentTestPaper.setCreateUserId(student.getId());
			studentTestPaper.setCreateUserName(student.getStudentName());
			studentTestPaper.setStudentId(student.getId());
			studentTestPaper.setStudentName(student.getStudentName());
			studentTestPaper.setStudentIdCardNum(student.getStudentIdCardNum());
			studentTestPaper.setOwnerSchoolId(student.getOwnerSchoolId());
			studentTestPaper.setOwnerSchoolName(student.getOwnerSchoolName());
			studentTestPaper.setOwnerCourseId(course.getId());
			studentTestPaper.setOwnerCourseName(course.getCourseName());
			studentTestPaper.setOwnerTestPaperId(testPaperView.getId());
			studentTestPaper.setTestPaperName(testPaperView.getTestPaperName());
			studentTestPaper.setTotalQuestionNum(testPaperView.getTotalQuestionNum());
			studentTestPaper.setTotalScore(testPaperView.getTotalScore());
			studentTestPaper.setPassScore(testPaperView.getPassScore());
			studentTestPaper.setTestPaperTimeLength(testPaperView.getTestPaperTimeLength());
			studentTestPaper.setOptionScore(0);
			studentTestPaper.setJudgeScore(0);
			studentTestPaper.setGetScore(0);
			String errMsg = insert(studentTestPaper);
			if(errMsg != null && errMsg.equals("")){
				//生成学员题目表
				createStuPaperQuestion(studentTestPaper,student);
			}
		}
		return studentTestPaper;
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据试卷生成学员题目
	 * @Date 2020/5/11 11:41
	 **/
	public void createStuPaperQuestion(SocietyStudentTestPaper studentTestPaper,SocietyStudent student){
		String ownerTestPaperId = studentTestPaper.getOwnerTestPaperId();
		List<SocietyTestPaperQuestionView> testPaperQuestionList = testPaperQuestionService.selectQuestionListByPaper(ownerTestPaperId);
		SocietyStudentTestPaperQuestion studentTestPaperQuestion = new SocietyStudentTestPaperQuestion();
		studentTestPaperQuestion.setCreateUserId(student.getId());
		studentTestPaperQuestion.setCreateUserName(student.getStudentName());
		studentTestPaperQuestion.setOwnerSchoolId(studentTestPaper.getOwnerSchoolId());
		studentTestPaperQuestion.setOwnerSchoolName(studentTestPaper.getOwnerSchoolName());
		studentTestPaperQuestion.setOwnerCourseId(studentTestPaper.getOwnerCourseId());
		studentTestPaperQuestion.setOwnerCourseName(studentTestPaper.getOwnerCourseName());
		studentTestPaperQuestion.setOwnerTestPaperId(studentTestPaper.getId());
		studentTestPaperQuestion.setOwnerTestPaperName(studentTestPaper.getTestPaperName());
		for(int i=0;i<testPaperQuestionList.size();i++){
			SocietyTestPaperQuestionView testPaperQuestion = testPaperQuestionList.get(i);
			studentTestPaperQuestion.setTestPaperQuestionId(testPaperQuestion.getId());
			studentTestPaperQuestion.setTestPaperQuestionName(testPaperQuestion.getQuestionName());
			studentTestPaperQuestion.setTestPaperQuestionType(testPaperQuestion.getQuestionType());
			studentTestPaperQuestion.setTestPaperQuestionScore(testPaperQuestion.getQuestionScore());
			studentTestPaperQuestion.setTestPaperQuestionAnalysis(testPaperQuestion.getQuestionAnalysis());
			studentTestPaperQuestion.setOrderNum(testPaperQuestion.getOrderNum());
			String errMsg = studentTestPaperQuestionService.insert(studentTestPaperQuestion);
			if(errMsg != null && errMsg.equals("")){
				createStuPaperQuestionOption(studentTestPaperQuestion,student);
			}
		}
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据题目生成学员选项
	 * @Date 2020/5/11 11:41
	 **/
	public void createStuPaperQuestionOption(SocietyStudentTestPaperQuestion paperQuestion,SocietyStudent student){
		String questionId = paperQuestion.getTestPaperQuestionId();
		List<SocietyTestPaperQuestionOptionView> questionOptionList = questionOptionService.selectOptionListByQuestion(questionId);
		SocietyStudentTestPaperQuestionOption studentQuestionOption = new SocietyStudentTestPaperQuestionOption();
		studentQuestionOption.setCreateUserId(student.getId());
		studentQuestionOption.setCreateUserName(student.getStudentName());
		studentQuestionOption.setStudentId(student.getId());
		studentQuestionOption.setOwnerSchoolId(student.getOwnerSchoolId());
		studentQuestionOption.setOwnerSchoolName(student.getOwnerSchoolName());
		studentQuestionOption.setOwnerCourseId(paperQuestion.getOwnerCourseId());
		studentQuestionOption.setOwnerCourseName(paperQuestion.getOwnerCourseName());
		studentQuestionOption.setOwnerTestPaperId(paperQuestion.getOwnerTestPaperId());
		studentQuestionOption.setOwnerTestPaperName(paperQuestion.getOwnerTestPaperName());
		studentQuestionOption.setOwnerTestPaperQuestionId(paperQuestion.getId());
		for(int i=0;i<questionOptionList.size();i++){
			SocietyTestPaperQuestionOption questionOption = questionOptionList.get(i);
			studentQuestionOption.setOptionTitle(questionOption.getOptionTitle());
			studentQuestionOption.setOptionName(questionOption.getOptionName());
			studentQuestionOption.setOrderNum(questionOption.getOrderNum());
			studentQuestionOption.setIsRight(questionOption.getIsRight());
			studentQuestionOptionService.insert(studentQuestionOption);
		}
	}

	/**
	 * @Author ZhangCC
	 * @Description 学员提交试卷
	 * @Date 2020/5/11 20:45
	 **/
	public void submitPaper(SocietyStudentTestPaper stuPaper,String quesIds,String selectOpIds){
		String[] quesArr = quesIds.split(",");
		String[] selectArr = selectOpIds.split(",");
		int scoreTotal = 0;
		int optionRightCount = 0;
		int optionScoreTotal = 0;
		int judgeRightCount = 0;
		int judgeScoreTotal = 0;
		for(int i=0;i<quesArr.length;i++){
			String queId = quesArr[i];
			SocietyStudentTestPaperQuestion stuQuestion = studentTestPaperQuestionService.loadById(queId);
			if(stuQuestion == null) continue;
			String selectOpId = selectArr[i];
			if(selectOpId!=null && selectOpId.equals("noanswer")){
				continue;
			}
			//选择和判断
			String questionType = stuQuestion.getTestPaperQuestionType();
			if(questionType != null && questionType.equals("3")){
				String[] stuOptionArr = selectOpId.split("@");
				boolean answerRight = false;
				for(int p=0;p<stuOptionArr.length;p++){
					String selectOpIdElem = stuOptionArr[p];
					SocietyStudentTestPaperQuestionOption stuOption =studentQuestionOptionService.loadById(selectOpIdElem);
					if(stuOption==null) continue;
					stuOption.setIsSelect("1");
					studentQuestionOptionService.update(stuOption);
					if(stuOption.getIsRight() != null){
						answerRight = stuOption.getIsRight().equals("1");
						if(!answerRight){
							break;
						}
					}
				}
				if(answerRight){
					int quesScore = stuQuestion.getTestPaperQuestionScore();
					//总分增加
					scoreTotal += quesScore;
					if(questionType != null && (questionType.equals("1")||questionType.equals("3"))){
						optionRightCount++;
						optionScoreTotal += quesScore;
					}
					if(questionType != null && questionType.equals("2")){
						judgeRightCount++;
						judgeScoreTotal += quesScore;
					}
					//更新题目获得分数
					stuQuestion.setGetScore(quesScore);
					stuQuestion.setAnswerState("1");
				}
				stuQuestion.setStuSelectOpId(selectOpId);
				studentTestPaperQuestionService.update(stuQuestion);
			}else{
				SocietyStudentTestPaperQuestionOption stuOption =studentQuestionOptionService.loadById(selectOpId);
				if(stuOption==null) continue;
				stuOption.setIsSelect("1");
				studentQuestionOptionService.update(stuOption);
				//答对的情况
				if(stuOption.getIsRight() != null && stuOption.getIsRight().equals("1")){
					int quesScore = stuQuestion.getTestPaperQuestionScore();
					//总分增加
					scoreTotal += quesScore;
					if(questionType != null && (questionType.equals("1")||questionType.equals("3"))){
						optionRightCount++;
						optionScoreTotal += quesScore;
					}
					if(questionType != null && questionType.equals("2")){
						judgeRightCount++;
						judgeScoreTotal += quesScore;
					}
					//更新题目获得分数
					stuQuestion.setGetScore(quesScore);
					stuQuestion.setAnswerState("1");
				}
				stuQuestion.setStuSelectOpId(selectOpId);
				studentTestPaperQuestionService.update(stuQuestion);
			}
		}
		stuPaper.setGetScore(scoreTotal);
		stuPaper.setOptionRightNum(optionRightCount);
		stuPaper.setOptionScore(optionScoreTotal);
		stuPaper.setJudgeRightNum(judgeRightCount);
		stuPaper.setJudgeScore(judgeScoreTotal);
		BigDecimal rightRate = new BigDecimal(0);
		if(quesArr.length > 0){
			rightRate = new BigDecimal(optionRightCount+judgeRightCount).divide(new BigDecimal(quesArr.length));
		}
		stuPaper.setTestRightRate(rightRate);
		if(scoreTotal > stuPaper.getPassScore()){
			stuPaper.setIsPass("1");
			String studentId = stuPaper.getStudentId();
			String courseId = stuPaper.getOwnerCourseId();
			SocietyStudent stu = societyStudentService.loadById(studentId);
			//合格的情况下颁发证书(没有证书的情况下)
			certificateService.insertStudentCert(courseId,stu);
		}else{
			stuPaper.setIsPass("0");
		}
		stuPaper.setIsFinished("1");
		update(stuPaper);
	}

	/**
	 * @Author WangZhen
	 * @Description
	 * @Date 2020/7/14 11:24
	 **/
    public List<SocietyStudentTestPaperView> selectPaperByStuIdCardAndCourseListPage(SocietyStudentTestPaperQuery query) {
		List list = societyStudentTestPaperMapper.selectPaperByStuIdCardAndCourseListPage(query);
		query.setList(list);
		return list;
    }

}
