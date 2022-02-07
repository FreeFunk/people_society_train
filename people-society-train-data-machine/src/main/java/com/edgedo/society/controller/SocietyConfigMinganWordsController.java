package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyConfigMinganWords;
import com.edgedo.society.queryvo.SocietyConfigMinganWordsQuery;
import com.edgedo.society.service.SocietyConfigMinganWordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyConfigMinganWords")
@Controller
@RequestMapping("/society/societyConfigMinganWords")
public class SocietyConfigMinganWordsController extends BaseController{
	
	@Autowired
	private SocietyConfigMinganWordsService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyConfigMinganWords", notes = "分页查询SocietyConfigMinganWords")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyConfigMinganWordsQuery query){
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
	@ApiOperation(value = "新增修改SocietyConfigMinganWords", notes = "新增修改SocietyConfigMinganWords")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyConfigMinganWords voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyConfigMinganWords", notes = "根据ID批量删除SocietyConfigMinganWords")
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
	@ApiOperation(value = "根据ID加载SocietyConfigMinganWords", notes = "根据ID加载SocietyConfigMinganWords")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 添加记录
	 * @param societyConfigMinganWords
	 * @return
	 */
	@RequestMapping(value = "/addNewMinganWords",method = RequestMethod.POST)
	public ModelAndView  addNewMinganWords(SocietyConfigMinganWords societyConfigMinganWords){
		ModelAndView modelAndView = new ModelAndView();
		//获取当前用户
		User user = getLoginUser();
		societyConfigMinganWords.setId(Guid.guid());//主键
		societyConfigMinganWords.setCreateTime(new Date());//创建时间
		societyConfigMinganWords.setCreateUserId(user.getUserId());//创建人id
		societyConfigMinganWords.setCreateUserName(user.getUserName());//创建人名
		societyConfigMinganWords.setDataState("1");//数据状态
		//上升数据库
		service.insert(societyConfigMinganWords);
		return buildResponse(modelAndView);
	}

	/**
	 * 提交修改
	 * @param societyConfigMinganWords
	 * @return
	 */
	@RequestMapping(value = "/updateOldMingganWork",method = RequestMethod.POST)
	public ModelAndView  updateOldMingganWork(SocietyConfigMinganWords societyConfigMinganWords){
		ModelAndView modelAndView = new ModelAndView();
		//上升数据库
		service.updateById(societyConfigMinganWords);
		return buildResponse(modelAndView);
	}

	
}
