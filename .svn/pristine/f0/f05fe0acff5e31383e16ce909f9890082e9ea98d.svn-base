package com.edgedo.society.controller;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.constant.CommonConstant;
import com.edgedo.society.entity.SocietyNodeResources;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.SocietyNodeResourcesService;
import com.edgedo.society.service.SocietySchoolCourseNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyNodeResources")
@Controller
@RequestMapping("/society/societyNodeResources")
public class SocietyNodeResourcesController extends BaseController{
	
	@Autowired
	private SocietyNodeResourcesService service;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;
	@Autowired
	private SocietySchoolCourseNodeMapper societySchoolCourseNodeMapper;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyNodeResources", notes = "分页查询SocietyNodeResources")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyNodeResourcesQuery query){
		ModelAndView modelAndView = new ModelAndView();
		query.getQueryObj().setIsOpen(CommonConstant.COURSE_OPON_STATUS);
		query.getQueryObj().setDataState(CommonConstant.DATA_STATUS_ON);
		List<SocietyNodeResourcesView> list = service.listPage(query);

		//查除所有的公开课 isOpen 1
		//对比当前的 操作人的id 排除课程 不能选 的 自己的课程学校id 已经选了的课程
		String nowSchoolId = getLoginUser().getCompId();

		for(SocietyNodeResourcesView societyNodeResourcesView:list) {
			//课程学校id
			String nodeSchoolId = societyNodeResourcesView.getOwnerSchoolId();
			//课程id
			String resourcesViewId = societyNodeResourcesView.getId();
			if (nowSchoolId.equals(nodeSchoolId)) {
				//自己的章节
				societyNodeResourcesView.setShowFlg(0);
			} else {
				int flag = service.selectBySchoolIdAndResourId(nowSchoolId, resourcesViewId);
				if (flag == 0) {//没有选择公共课
					societyNodeResourcesView.setShowFlg(1);
				} else {
					societyNodeResourcesView.setShowFlg(0);
				}
			}
		}
        query.setList(list);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 学校自己的章节池
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageMyNode",method = RequestMethod.POST)
	public ModelAndView listpageMyNode(@ModelAttribute SocietyNodeResourcesQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//我的章节 = 章节池关联别人的章节 + 自己上传的章节
		//         isOpen 0 是否关联公共课 1  + 是否关联公共课0 null
		// 自己的章节是没有 owner  关联别人的 有
		//                      ownerSchoolId 是别人的
		//qiancheng null
		//qiancheng  xinqidian
		//dongshun	qiancheng
		query.getQueryObj().setDataState(CommonConstant.DATA_STATUS_ON);
		String nowSchoolId = getLoginUser().getCompId();
		query.getQueryObj().setApplySchoolId(nowSchoolId);
		query.getQueryObj().setOwnerSchoolId(nowSchoolId);
		List<SocietyNodeResourcesView> list = service.listPageMyNode(query);
		query.setList(list);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 修改 章节的公开状态
	 * @param ids
	 * @param isOpen
	 * @return
	 */
	@RequestMapping(value = "/updateByIsOpen",method = RequestMethod.POST)
	public ModelAndView updateByIsOpen(String ids,String isOpen){
		ModelAndView modelAndView = new ModelAndView();
		service.updateByIsOpen(ids,isOpen);
		buildResponse(modelAndView);
		return modelAndView;
	}

	//我的课件  所属学校 和  申请学校是我的
	@RequestMapping(value = "/getMyOpenResources",method = RequestMethod.POST)
	public ModelAndView getMyOpenResources(@ModelAttribute SocietyNodeResourcesQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		SocietyNodeResourcesView societyNodeResourcesView =  query.getQueryObj();
//		societyNodeResourcesView.setIsOpen(CommonConstant.COURSE_OPON_STATUS);
		societyNodeResourcesView.setOwnerSchoolId(schoolId);
		societyNodeResourcesView.setApplySchoolId(schoolId);
		societyNodeResourcesView.setDataState(CommonConstant.DATA_STATUS_ON);
		service.listPageMyNode(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/joinToMyResources",method = RequestMethod.POST)
	public ModelAndView joinToMyCourse(String ids){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		service.joinToMyResources(user,ids);
		return buildResponse(modelAndView);
	}


	/**
	 * 添加 章节池的章节
	 * 添加自己的课程的 或者公共课的章节
	 * @param courseId 当前课程id  ids 章节池id
	 * @return
	 */
	@RequestMapping(value = "/batchResourceNode",method = RequestMethod.POST)
	public ModelAndView batchResourceNode(String courseId,String ids){
		ModelAndView modelAndView = new ModelAndView();
		//根据当前课程id 和 章节池id
		String[] idsStr = ids.split(",");
		String str = "";
		for (String s : idsStr){
			SocietySchoolCourseNode societySchoolCourseNode =
					societySchoolCourseNodeService.selectByCourseIdAndResourceIdOnIsOpen(courseId,s);
			if (societySchoolCourseNode!=null){
				if (str.equals("")){
					str = societySchoolCourseNode.getNodeName();
				}else {
					str = str +"，" +societySchoolCourseNode.getNodeName();
				}
			}
		}
		if (str.equals("")){
			User user = getLoginUser();
			service.addCourseNode(user,ids,courseId);
			return buildResponse(modelAndView);
		}else {
			modelAndView.addObject("success", false);
			modelAndView.addObject("data", str);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			return modelAndView;
		}
	}


	/*@RequestMapping(value = "/countNodeInfo",method = RequestMethod.POST)
	public ModelAndView countNodeInfo(@ModelAttribute SocietyNodeResourcesQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		if(query.getQueryObj().getMonth()==null){
			Date data = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			query.getQueryObj().setMonth(simpleDateFormat.format(data));
		}
		//1.查询当前学校有哪些章节 公开
		//2.进行遍历查询是否存在有别的学校使用
		//3.没有的话不展示
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.listPageCountNode(query);
		List<SocietyNodeResourcesView> nodeList = query.getList();
		//4.有的话去统计完成人数
		nodeList.forEach(societyNodeResourcesView -> {
			String month = query.getQueryObj().getMonth();
			String monthStartDay = getFirstDayOfMonth(month);
			String monthEndDay = getLastDayOfMonth(month);
			//1.查询stuAndcou
			int completNum =
					societyStudentAndNodeService.selectByCourseIdAndTime(societySchoolCourseNodeView.getId(),monthStartDay,monthEndDay);
			societySchoolCourseNodeView.setSchoolCompleteCount(completNum);
		});
		query.setList(nodeList);
		buildResponse(modelAndView,query);
		return modelAndView;
	}*/

	/**
	 * 获取当前月第一天
	 * @param month
	 * @return
	 */
	public String getFirstDayOfMonth(String month) {
		String[] monthStr = month.split("-");
		Calendar calendar = Calendar.getInstance();
		// 设置月份
		calendar.set(Calendar.MONTH, new Integer(monthStr[1]) - 1);
		// 获取某月最小天数
		int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最小天数
		calendar.set(Calendar.DAY_OF_MONTH, firstDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayStr = sdf.format(calendar.getTime())+" 00:00:00";
		return firstDayStr;
	}

	public String getLastDayOfMonth(String monthStr) {
		String[] monthTimeStr = monthStr.split("-");
		Calendar calendar = Calendar.getInstance();
		// 设置月份
		Integer month =  new Integer(monthTimeStr[1]);
		calendar.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay=0;
		//2月的平年瑞年天数
		if(month==2) {
			lastDay = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
		}else {
			lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		// 设置日历中月份的最大天数
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayStr = sdf.format(calendar.getTime())+" 23:59:59";
		return lastDayStr;
	}

	/**
	 * 更改章节的信息
	 * @param courseId
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/batchUpdateResourceNode",method = RequestMethod.POST)
	public ModelAndView batchUpdateResourceNode(String courseId,String ids,String nodeId){
		ModelAndView modelAndView = new ModelAndView();
		//根据当前课程id 和 章节池id
		String str = "";
		SocietySchoolCourseNode societySchoolCourseNode =
				societySchoolCourseNodeService.selectByCourseIdAndResourceIdOnIsOpen(courseId,ids);
		if (societySchoolCourseNode!=null){
			str = societySchoolCourseNode.getNodeName();
		}
		if (str.equals("")){
			User user = getLoginUser();
			service.updateMyNode(user,ids,courseId,nodeId);
			return buildResponse(modelAndView);
		}else {
			modelAndView.addObject("success", false);
			modelAndView.addObject("data", str);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			return modelAndView;
		}
	}


	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyNodeResources", notes = "新增修改SocietyNodeResources")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyNodeResources voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyNodeResources", notes = "根据ID批量删除SocietyNodeResources")
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
	@RequestMapping(value = "/updateByIdsTeng",method = RequestMethod.POST)
	public ModelAndView updateByIdsTeng(String ids){
		ModelAndView modelAndView = new ModelAndView();
		SocietyNodeResources societyNodeResources = service.loadById(ids);
		if ("1".equals(societyNodeResources.getIsRelationPublicCourse())){
			String ownReId = societyNodeResources.getOwnerNodeResourcesId();
			String applySchoolId = societyNodeResources.getApplySchoolId();
			societySchoolCourseNodeService.updateBySchoolIdAndResourceId(applySchoolId,ownReId);
		}else {
			String ownReId = societyNodeResources.getOwnerNodeResourcesId();
			String ownerSchoolId = societyNodeResources.getOwnerSchoolId();
			societySchoolCourseNodeService.updateBySchoolIdAndResourceId(ownerSchoolId,ownReId);
		}
		//根据章节池id 删除章节池记录
		service.updateByDateState(ids);
		return buildResponse(modelAndView);
	}
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SocietyNodeResources", notes = "根据ID加载SocietyNodeResources")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}
