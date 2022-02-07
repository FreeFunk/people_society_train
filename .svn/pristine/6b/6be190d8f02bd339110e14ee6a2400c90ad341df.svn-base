package com.edgedo.society.controller;


import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentTestPaper")
@Controller
@RequestMapping("/test")
public class SocietyStudentTestPaperController extends BaseController{
	
	@Autowired
	private SocietyStudentTestPaperService service;
	@Autowired
	private SocietySchoolCourseService schoolCourseService;
	@Autowired
	private SocietyStudentService studentService;
	@Autowired
	private SocietyStudentUniqueService studentUniqueService;
	@Autowired
	private SocietyStudentTestPaperQuestionService studentQuestionService;
	@Autowired
	private SocietyTestPaperService testPaperService;
	@Autowired
	private SocietyTestPaperQuestionService testPaperQuestionService;
	@Autowired
	private SocietyStudentTestPaperQuestionOptionService studentOptionService;
	@Autowired
	private SocietyStudentAndCourseService studentAndCourseService;

	/**
	 * @Author ZhangCC
	 * @Description 根据课程查询
	 * @Date 2020/5/16 9:41有没有安排考试
	 **/
	@RequestMapping("/checkTestPaperByCourse")
	public ModelAndView checkTestPaperByCourse(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		SocietySchoolCourse course = schoolCourseService.loadById(courseId);
		if(course == null){
			return buildErrorResponse(modelAndView,"未查询到课程信息！");
		}
		//判断一下学员该门课程是否已经完成学习
		SocietyStudentAndCourse studentAndCourse = studentAndCourseService.selectOneFinishedByIdCardAndCourse(courseId,studentUnique.getId());
		if(studentAndCourse == null){
			return buildErrorResponse(modelAndView,"课程还未完成，无法进行考试！");
		}
		SocietyTestPaperView testPaper = testPaperService.selectVoByCourseAndSchool(course.getOwnerSchoolId(),courseId);
		return buildResponse(modelAndView,testPaper);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据课程查询试卷信息
	 * @Date 2020/5/11 14:29
	 **/
	@RequestMapping("/selectTestPaper")
	public ModelAndView selectTestPaper(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);

		SocietySchoolCourse course = schoolCourseService.loadById(courseId);
		if(course == null){
			return buildErrorResponse(modelAndView,"未查询信息！");
		}
		String ownerSchoolId = course.getOwnerSchoolId();
		SocietyTestPaperView testPaper = testPaperService.selectVoByCourseAndSchool(ownerSchoolId,courseId);
		//查询单选题数量
		String ownerTestPaperId = testPaper.getId();
		int optionQuestionCount = testPaperQuestionService.countByPaperAndType(ownerTestPaperId,"1");
		testPaper.setOptionQuestionCount(optionQuestionCount);
		//查询判断题数量
		int judgeQuestionCount = testPaperQuestionService.countByPaperAndType(ownerTestPaperId,"2");
		testPaper.setJudgeQuestionCount(judgeQuestionCount);
		//获得stuCourse
		SocietyStudentAndCourse stuAndCourse = studentAndCourseService.selectOneByCourseIdAndIdCarNum(studentUnique.getId(),courseId);
		String stuId = stuAndCourse.getStudentId();
		//查询学员答题的最高分
		Integer topScore = service.selectTopScoreByCourse(stuId,courseId);
		if(topScore == null){
			testPaper.setTopScore(-1);
		}else{
			testPaper.setTopScore(topScore);
		}
		return buildResponse(modelAndView,testPaper);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询学员考试试卷
	 * @Date 2020/5/11 13:55
	 **/
	@RequestMapping("/selectStudentTestPaper")
	public ModelAndView selectStudentTestPaper(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		SocietySchoolCourse course = schoolCourseService.loadById(courseId);
		if(course == null){
			return buildErrorResponse(modelAndView,"未查询到课程信息！");
		}
		//获得stuCourse
		SocietyStudentAndCourse stuAndCourse = studentAndCourseService.selectOneByCourseIdAndIdCarNum(studentUnique.getId(),courseId);
		String stuId = stuAndCourse.getStudentId();
		SocietyStudent student = studentService.loadById(stuId);
		SocietyStudentTestPaper studentPaper = service.selectNotFinishedPaper(student.getId(),courseId);
		if(studentPaper == null){
			studentPaper = service.createStuPaper(student,course);
		}
		return buildResponse(modelAndView,studentPaper);
	}

	/**
	 * @Author ZhangCC
	 * @Description 计算一下考试时长
	 * @Date 2020/5/20 17:00
	 **/
	@RequestMapping("/selectPaperTimeLength")
	public ModelAndView selectPaperTimeLength(String studentPaperId){
		ModelAndView modelAndView = new ModelAndView();
		int paperTimeLength = 0;
		SocietyStudentTestPaper studentPaper = service.loadById(studentPaperId);
		String isFinished = studentPaper.getIsFinished();
		if(isFinished != null && isFinished.equals("1")){
			paperTimeLength = -1;
			return buildResponse(modelAndView,paperTimeLength);
		}
		//计算一下考试时长
		Date nowDate = new Date();
		Date paperDate = studentPaper.getCreateTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowDateStr = sdf.format(nowDate);
		String paperDateStr = sdf.format(paperDate);
		try{
			nowDate = sdf.parse(nowDateStr);
			paperDate = sdf.parse(paperDateStr);
		}catch (Exception e){
			e.printStackTrace();
		}
		Long nowTime = nowDate.getTime();
		Long paperTime = paperDate.getTime();
		Long diffMinute = (paperTime - nowTime)/60/1000;
		paperTimeLength = studentPaper.getTestPaperTimeLength()+diffMinute.intValue();
		if(paperTimeLength <= 0){
			paperTimeLength = -1;
			return buildResponse(modelAndView,paperTimeLength);
		}
		return buildResponse(modelAndView,paperTimeLength);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询学员考试试卷
	 * @Date 2020/5/11 13:55
	 **/
	@RequestMapping("/selectStudentTestPaperById")
	public ModelAndView selectStudentTestPaperById(String stuPaperId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> resultMap = new HashMap<>();
		SocietyStudentTestPaper stuPaper = service.loadById(stuPaperId);
		if(stuPaper == null){
			return buildErrorResponse(modelAndView,"未查询到试卷信息！");
		}
		//统计一下答对题目数量
		int rightQuesCount = studentQuestionService.countRightQuesByPaper(stuPaperId);
		resultMap.put("stuPaper",stuPaper);
		resultMap.put("rightQuesCount",rightQuesCount);
		return buildResponse(modelAndView,resultMap);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询学员考试试卷的试题和选项
	 * @Date 2020/5/11 13:55
	 **/
	@RequestMapping("/selectStudentTestPaperQuestion")
	public ModelAndView selectStudentTestPaperQuestion(SocietyStudentTestPaperQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		query.setOrderBy("ORDER_NUM asc");
		List<SocietyStudentTestPaperQuestionView> studentQuestionList = studentQuestionService.listPage(query);
		if(studentQuestionList != null){
			for(int i=0;i<studentQuestionList.size();i++){
				String studentQuestionId = studentQuestionList.get(i).getId();
				List<SocietyStudentTestPaperQuestionOptionView> questionPaperList = studentOptionService.selectOptionByQuestion(studentQuestionId);
				studentQuestionList.get(i).setQuestionOptionList(questionPaperList);
			}
		}
		return buildResponse(modelAndView,query);
	}

	/**
	 * @Author ZhangCC
	 * @Description 学员提交试卷
	 * @Date 2020/5/11 20:39
	 **/
	@RequestMapping("/submitPaper")
	public ModelAndView submitPaper(String quesIds,String selectOpIds,String testPaperId){
		ModelAndView modelAndView = new ModelAndView();
		if(quesIds.length()==0 ){
			return buildErrorResponse(modelAndView,"param error");
		}
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		SocietyStudentTestPaper stuPaper = service.loadById(testPaperId);
		//判断这个学生点匹配学员情况
		String studentIdCardNum = stuPaper.getStudentIdCardNum();
		if(!studentUnique.getId().equals(studentIdCardNum)){
			return buildErrorResponse(modelAndView,"not same stu id");
		}
		service.submitPaper(stuPaper,quesIds,selectOpIds);
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询考试列表
	 * @Date 2020/5/12 15:29
	 **/
	@RequestMapping("/selectExamRecList")
	public ModelAndView selectExamRecList(SocietyStudentTestPaperQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique studentUnique = getLoginStudent(studentUniqueService);
		query.getQueryObj().setStudentIdCardNum(studentUnique.getId());
		String courseId = query.getQueryObj().getOwnerCourseId();
		SocietySchoolCourse course = schoolCourseService.loadById(courseId);
		if(course == null){
			return buildErrorResponse(modelAndView,"未查询到内容！");
		}
		service.selectPaperByStuIdCardAndCourseListPage(query);
		return buildResponse(modelAndView,query);
	}

}
