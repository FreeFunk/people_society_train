package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.society.entity.SocietyTestPaperQuestion;
import com.edgedo.society.entity.SocietyTestPaperQuestionOption;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionQuery;
import com.edgedo.society.queryvo.SocietyTestPaperQuestionView;
import com.edgedo.society.service.SocietyTestPaperQuestionOptionService;
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


@Api(tags = "SocietyTestPaperQuestion")
@Controller
@RequestMapping("/society/societyTestPaperQuestion")
public class SocietyTestPaperQuestionController extends BaseController{
	
	@Autowired
	private SocietyTestPaperQuestionService service;
    @Autowired
    private SocietyTestPaperQuestionOptionService societyTestPaperQuestionOptionService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyTestPaperQuestion", notes = "分页查询SocietyTestPaperQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyTestPaperQuestionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
        List<SocietyTestPaperQuestionView> questionList = query.getList();
        for(int i=0;i<questionList.size();i++){
            SocietyTestPaperQuestionView question = questionList.get(i);
            List<String> optionNameList = societyTestPaperQuestionOptionService.selectOptionNameByQuestionId(question.getId());
            if(optionNameList.size() > 0){
                String options = String.join(",",optionNameList);
                questionList.get(i).setOptions(options);
            }
        }
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyTestPaperQuestion", notes = "新增修改SocietyTestPaperQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyTestPaperQuestion voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyTestPaperQuestion", notes = "根据ID批量删除SocietyTestPaperQuestion")
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
	@ApiOperation(value = "根据ID加载SocietyTestPaperQuestion", notes = "根据ID加载SocietyTestPaperQuestion")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}
