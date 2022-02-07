package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.society.entity.CmsArticleCls;
import com.edgedo.society.entity.CmsArticleContent;
import com.edgedo.society.queryvo.CmsArticleClsView;
import com.edgedo.society.queryvo.CmsArticleContentQuery;
import com.edgedo.society.queryvo.CmsArticleContentView;
import com.edgedo.society.service.CmsArticleClsService;
import com.edgedo.society.service.CmsArticleContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "CmsArticleContent")
@Controller
@RequestMapping("/article")
public class CmsArticleContentController extends BaseController{
	
	@Autowired
	private CmsArticleContentService service;
	@Autowired
	private CmsArticleClsService articleClsService;
	
	/**
	 * @Author ZhangCC
	 * @Description 查询推送信息
	 * @Date 2020/5/15 10:26
	 **/
	@Pass
	@RequestMapping(value = "/selectActicleListPage",method = RequestMethod.POST)
	public ModelAndView selectActicleListPage(@ModelAttribute CmsArticleContentQuery query){
		ModelAndView modelAndView = new ModelAndView();
		query.setOrderBy("PUBLISH_TIME desc");
		query.getQueryObj().setDataState("1");
		query.getQueryObj().setShState("1");
		query.getQueryObj().setArtState("1");
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询推送信息
	 * @Date 2020/5/15 10:26
	 **/
	@Pass
	@RequestMapping(value = "/selectVoById",method = RequestMethod.POST)
	public ModelAndView selectVoById(String id){
		ModelAndView modelAndView = new ModelAndView();
		CmsArticleContent content = service.loadById(id);
		buildResponse(modelAndView,content);
		return modelAndView;
	}

	/**
	 * @Author ZhangCC
	 * @Description 修改浏览次数
	 * @Date 2020/5/15 10:26
	 **/
	@Pass
	@RequestMapping(value = "/editClickNumById",method = RequestMethod.POST)
	public ModelAndView editClickNumById(@ModelAttribute CmsArticleContent article){
		ModelAndView modelAndView = new ModelAndView();
		String articleId = article.getId();
		CmsArticleContent content = service.loadById(articleId);
		if(content == null){
			return buildErrorResponse(modelAndView,"未查询到相关信息！");
		}
		service.updateClickNum(articleId,article.getClickNum());
		buildResponse(modelAndView);
		return modelAndView;
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询新闻分类
	 * @Date 2020/5/19 17:13
	 **/
	@Pass
	@RequestMapping(value = "/selectArticleCls",method = RequestMethod.POST)
	public ModelAndView selectArticleCls(){
		ModelAndView modelAndView = new ModelAndView();
		List<CmsArticleClsView> artiCleClsList = articleClsService.selectAllArticleCls();
		return buildResponse(modelAndView,artiCleClsList);
	}
	
	
}
