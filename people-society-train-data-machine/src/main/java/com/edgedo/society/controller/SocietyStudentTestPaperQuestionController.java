package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.society.entity.SocietyStudentTestPaperQuestion;
import com.edgedo.society.entity.SocietyTestPaperQuestion;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.SocietyStudentTestPaperQuestionService;
import com.edgedo.society.service.SocietyTestPaperQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentTestPaperQuestion")
@Controller
@RequestMapping("/society/societyStudentTestPaperQuestion")
public class SocietyStudentTestPaperQuestionController extends BaseController{
	
	@Autowired
	private SocietyStudentTestPaperQuestionService service;

	@Autowired
	private SocietyTestPaperQuestionService societyTestPaperQuestionService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentTestPaperQuestion", notes = "分页查询SocietyStudentTestPaperQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentTestPaperQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyStudentTestPaperQuestion", notes = "新增修改SocietyStudentTestPaperQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentTestPaperQuestion voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			errMsg = service.insert(voObj);
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除SocietyStudentTestPaperQuestion", notes = "根据ID批量删除SocietyStudentTestPaperQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.deleteByIds(list);		
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SocietyStudentTestPaperQuestion", notes = "根据ID加载SocietyStudentTestPaperQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 查询该学员在当前课程 下的考试题目 学员考试题目表(已答完题目)考试题目表(所有题目)
	 * @param  societyStudentTestPaperQuestionQuery
	 * @return
	 */
	@RequestMapping(value = "/selectAllQuestion",method = RequestMethod.POST)
	public ModelAndView  selectAllQuestion(@ModelAttribute SocietyStudentTestPaperQuestionQuery societyStudentTestPaperQuestionQuery){
		ModelAndView modelAndView = new ModelAndView();
		//1.查出学员考试题目表所有已经答完的题目
		String ownerSchoolId = societyStudentTestPaperQuestionQuery.getQueryObj().getOwnerSchoolId();
		String ownerCourseId = societyStudentTestPaperQuestionQuery.getQueryObj().getOwnerCourseId();
		String ownerTestPaperId = societyStudentTestPaperQuestionQuery.getQueryObj().getOwnerTestPaperId();
		String studentId = societyStudentTestPaperQuestionQuery.getQueryObj().getStudentId();

		List<SocietyStudentTestPaperQuestionView> completePaperList =
				service.selectByStudentTestPaperComplete( ownerSchoolId, ownerCourseId,
				 ownerTestPaperId, studentId);
		//2.考试题目表根据学校id 课程id 试卷id 查出一套试卷的所有题目
		List<SocietyTestPaperQuestion> allPaperList =
				societyTestPaperQuestionService.selectTestPaperAll(ownerSchoolId, ownerCourseId,
				ownerTestPaperId, studentId);
		//分页
		SocietyTestPaperQuestionQuery societyTestPaperQuestionQuery =
				societyTestPaperQuestionService.selectByAllQuestionlistPage
						(societyStudentTestPaperQuestionQuery.getLimit(),
								societyStudentTestPaperQuestionQuery.getPage(),
								societyStudentTestPaperQuestionQuery.getStart(),
								ownerSchoolId, ownerCourseId, ownerTestPaperId, studentId);
		//3.两个list对比那些已经答完 还没答完的
		if(allPaperList.size()==completePaperList.size()){//证明该学生此试卷已答完
			List<SocietyStudentTestPaperQuestionView> list =
					service.listPage(societyStudentTestPaperQuestionQuery);
			for(SocietyStudentTestPaperQuestionView societyStudentTestPaperQuestionView : list){
				if(societyStudentTestPaperQuestionView.getGetScore()==0){//没答对
					societyStudentTestPaperQuestionView.setIsAnswerQuestion("答题错误");
				}else {
					societyStudentTestPaperQuestionView.setIsAnswerQuestion("答题正确");
				}
			}
			buildResponse(modelAndView,societyStudentTestPaperQuestionQuery);
			return modelAndView;
		}else if(completePaperList.size()==0){//一道题没答
			List<SocietyTestPaperQuestionView> allList = societyTestPaperQuestionQuery.getList();//公共考试习题
			for(SocietyTestPaperQuestionView societyTestPaperQuestionView : allList){
				societyTestPaperQuestionView.setStudentId(studentId);
				societyTestPaperQuestionView.setGetScore(new Integer(0));
				//题目名
				societyTestPaperQuestionView.setTestPaperQuestionName(societyTestPaperQuestionView.getQuestionName());
				//题目id
				societyTestPaperQuestionView.setTestPaperQuestionId(societyTestPaperQuestionView.getId());
				//题目分数
				societyTestPaperQuestionView.setTestPaperQuestionScore(societyTestPaperQuestionView.getQuestionScore().intValue());
				//题目类型
				societyTestPaperQuestionView.setTestPaperQuestionType(societyTestPaperQuestionView.getQuestionType());
				societyTestPaperQuestionView.setIsAnswerQuestion("未答题");//答题情况
			}
			societyTestPaperQuestionQuery.setList(allList);
			buildResponse(modelAndView,societyTestPaperQuestionQuery);
			return modelAndView;
		}else {//有些题目没有答，依据公共的试卷题目表补充
			List<SocietyStudentTestPaperQuestionView> list = new ArrayList<>();
			//学员已经答过的题目
			List<SocietyTestPaperQuestionView> allList = societyTestPaperQuestionQuery.getList();//公共考试习题
			for(int i = 0;i<allList.size();i++){
				for (int j = 0;j<completePaperList.size();j++){
					//判断题目id
					if(completePaperList.get(j).getTestPaperQuestionId().equals(allList.get(i).getId())){
						//证明已经答题
						if(completePaperList.get(j).getGetScore()==0){//为答错
							allList.get(i).setStudentId(studentId);
							allList.get(i).setGetScore(completePaperList.get(j).getGetScore());
							allList.get(i).setIsAnswerQuestion("答题错误");//答题情况
							//题目名
							allList.get(i).setTestPaperQuestionName(allList.get(i).getQuestionName());
							//题目id
							allList.get(i).setTestPaperQuestionId(allList.get(i).getId());
							//题目分数
							allList.get(i).setTestPaperQuestionScore(allList.get(i).getQuestionScore().intValue());
							//题目类型
							allList.get(i).setTestPaperQuestionType(allList.get(i).getQuestionType());
						}else {
							allList.get(i).setStudentId(studentId);
							allList.get(i).setGetScore(completePaperList.get(j).getGetScore());
							allList.get(i).setIsAnswerQuestion("答题正确");//答题情况
							//题目名
							allList.get(i).setTestPaperQuestionName(allList.get(i).getQuestionName());
							//题目id
							allList.get(i).setTestPaperQuestionId(allList.get(i).getId());
							//题目分数
							allList.get(i).setTestPaperQuestionScore(allList.get(i).getQuestionScore().intValue());
							//题目类型
							allList.get(i).setTestPaperQuestionType(allList.get(i).getQuestionType());
						}
					}
				}
				//证明没有答题
				allList.get(i).setStudentId(studentId);
				allList.get(i).setGetScore(0);
				allList.get(i).setIsAnswerQuestion("未答题");//答题情况
				//题目名
				allList.get(i).setTestPaperQuestionName(allList.get(i).getQuestionName());
				//题目id
				allList.get(i).setTestPaperQuestionId(allList.get(i).getId());
				//题目分数
				allList.get(i).setTestPaperQuestionScore(allList.get(i).getQuestionScore().intValue());
				//题目类型
				allList.get(i).setTestPaperQuestionType(allList.get(i).getQuestionType());
			}
			societyTestPaperQuestionQuery.setList(allList);
			buildResponse(modelAndView,societyTestPaperQuestionQuery);
			return modelAndView;
		}
	}



	/**
	 *@Author:ZhaoSiDa
	 *@Description: 查询学员的考试详情
	 *@DateTime: 2020/6/1 21:52
	 */
	@RequestMapping(value = "/selectStuTestDetail",method = RequestMethod.POST)
	public ModelAndView selectStuTestDetail(@ModelAttribute SocietyStudentTestPaperQuestionQuery societyStudentTestPaperQuestionQuery){
		ModelAndView modelAndView = new ModelAndView();
		buildResponse(modelAndView,service.listByTestPaperQuestionQuery(societyStudentTestPaperQuestionQuery));
		return modelAndView;
	}
	
}
