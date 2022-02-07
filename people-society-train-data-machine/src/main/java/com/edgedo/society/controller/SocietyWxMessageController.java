package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietyWxMessage;
import com.edgedo.society.queryvo.SocietyWxMessageQuery;
import com.edgedo.society.service.SocietyWxMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyWxMessage")
@Controller
@RequestMapping("/society/societyWxMessage")
public class SocietyWxMessageController extends BaseController{
	
	@Autowired
	private SocietyWxMessageService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyWxMessage", notes = "分页查询SocietyWxMessage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyWxMessageQuery query){
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
	@ApiOperation(value = "新增修改SocietyWxMessage", notes = "新增修改SocietyWxMessage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyWxMessage voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyWxMessage", notes = "根据ID批量删除SocietyWxMessage")
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
	@ApiOperation(value = "根据ID加载SocietyWxMessage", notes = "根据ID加载SocietyWxMessage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 添加到发送消息表中
	 * @return
	 */
	@RequestMapping(value = "/newAddWxMessageRec",method = RequestMethod.POST)
	public ModelAndView  newAddWxMessageRec(String id,String ids){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		if(ids==""){//发送所有
			service.insertMessageRec(service.loadById(id),user);
		}else {//指定发送
			service.insertAppointRec(service.loadById(id),user,ids);
		}
		return buildResponse(modelAndView);
	}

	/**
	 * 添加到发送消息表中
	 * @return
	 */
	@RequestMapping(value = "/newAddAdminWxMessageRec",method = RequestMethod.POST)
	public ModelAndView  newAddAdminWxMessageRec(String id,String ids){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		if(ids==""){//发送所有
			service.insertAdminMessageRec(service.loadById(id),user);
		}else {//指定发送
			service.insertAppointRec(service.loadById(id),user,ids);
		}
		return buildResponse(modelAndView);
	}
	
	
}
