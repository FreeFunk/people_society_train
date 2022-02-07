package com.edgedo.society.controller;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.Signature;
import com.edgedo.society.entity.SocietyNodeResources;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.Dtree;
import com.edgedo.tyiyunoosclient.ISysTyiyunCloudStorageService;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.management.QueryEval;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "SocietySchoolCourseNode")
@Controller
@RequestMapping("/society/societySchoolCourseNode")
public class SocietySchoolCourseNodeController extends BaseController{

	@Autowired
	private SocietySchoolCourseNodeService service;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private ISysTyiyunCloudStorageService sysTyiyunCloudStorageService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
	private SocietySchoolCourseNodeOptionService societySchoolCourseNodeOptionService;
	@Autowired
	private SocietyNodeResourcesService societyNodeResourcesService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Value("${app.tcloudDomain}")
	private String tcloudDomain;


	@Value("${app.TencentSecretId}")
	private String TencentSecretId;
	@Value("${app.TencentSecretKey}")
	private String TencentSecretKey;
	@Value("${app.TencentRegion}")
	private String TencentRegion;
	@Value("${app.TencentEndPoint}")
	private String TencentEndPoint;
	@Value("${app.TencentDefinition}")
	private String TencentDefinition;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolCourseNode", notes = "分页查询SocietySchoolCourseNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolCourseNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}




	@RequestMapping(value = "/countNodeInfo",method = RequestMethod.POST)
	public ModelAndView countNodeInfo(@ModelAttribute SocietySchoolCourseNodeQuery query){
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
		//3.没有的话不展示 courseId 章节和课程
		//单独关联的章节和关联的课程都会往courseNode记录 他们resourceId 都是一样的
		// 先查出当前 条件下的课程id 判断不是自己的课程 根据courseId 查出courseNode
//		List<String> resourceIdList = societyNodeResourcesService.selectBySchoolId(schoolId);
		List<String> courseIdList = societySchoolCourseService.selectByCourseIdList(schoolId);
		List<String> nodeIdList = new ArrayList<>();
		courseIdList.forEach(courseId ->{
			//如果他用别人的章节 那他的学校id 不一致
			List<String> nodeIdListNew = service.selectByCourseIdAndSchoolIdList(courseId,schoolId);
			if (nodeIdListNew.size()!=0){
				nodeIdList.addAll(nodeIdListNew);
			}
		});

		if(nodeIdList.size()==0){
			buildResponse(modelAndView,query);
			return modelAndView;
		}else {
			query.setNodeIdList(nodeIdList);
			service.listPageCountNode(query);
			List<SocietySchoolCourseNodeView> nodeList = query.getList();
			if (!("".equals(query.getQueryObj().getMonth()))){
				String month = query.getQueryObj().getMonth();
				String monthStartDay = getFirstDayOfMonth(month);
				String monthEndDay = getLastDayOfMonth(month);
				nodeList.forEach(societySchoolCourseNodeView -> {
					//查出有多少的课程使用了该章节
					//1.查询stuAndcou
					int completNum =
							societyStudentAndNodeService.selectByCourseIdAndTime(societySchoolCourseNodeView.getId(),monthStartDay,monthEndDay);
					societySchoolCourseNodeView.setSchoolCompleteCount(completNum);
					//查询课程总数
					SocietySchoolCourse societySchoolCourse =
							societySchoolCourseService.loadById(societySchoolCourseNodeView.getOwnerCourseId());
					Integer nodeInCourseNum =
							service.selectByCourseIdAndNodeId(societySchoolCourse.getId(),societySchoolCourseNodeView.getOwnerNodeResourcesId());
					BigDecimal moneyNum = new BigDecimal((nodeInCourseNum*4)).divide(new BigDecimal(societySchoolCourse.getTotalLessons()), 2,BigDecimal.ROUND_HALF_UP);
					societySchoolCourseNodeView.setNodeInCourseMoney(moneyNum);
				});
			}else if(!("".equals(query.getQueryObj().getYear()))){
				String year = query.getQueryObj().getYear();
				String yearDayFirst = getYearFirst(new Integer(year));
				String yearDayEnd = getYearLast(new Integer(year));
				nodeList.forEach(societySchoolCourseNodeView -> {
					//查出有多少的课程使用了该章节
					//1.查询stuAndcou
					int completNum =
							societyStudentAndNodeService.selectByCourseIdAndTime(societySchoolCourseNodeView.getId(),yearDayFirst,yearDayEnd);
					societySchoolCourseNodeView.setSchoolCompleteCount(completNum);
					//查询课程总数
					SocietySchoolCourse societySchoolCourse =
							societySchoolCourseService.loadById(societySchoolCourseNodeView.getOwnerCourseId());
					Integer nodeInCourseNum =
							service.selectByCourseIdAndNodeId(societySchoolCourse.getId(),societySchoolCourseNodeView.getOwnerNodeResourcesId());
					BigDecimal moneyNum = new BigDecimal((nodeInCourseNum*4)).divide(new BigDecimal(societySchoolCourse.getTotalLessons()), 2,BigDecimal.ROUND_HALF_UP);
					societySchoolCourseNodeView.setNodeInCourseMoney(moneyNum);
				});
			}else {
				nodeList.forEach(societySchoolCourseNodeView -> {
					//查出有多少的课程使用了该章节
					//1.查询stuAndcou
					int completNum =
							societyStudentAndNodeService.selectByCourseIdAndTimeNew(societySchoolCourseNodeView.getId());
					societySchoolCourseNodeView.setSchoolCompleteCount(completNum);
					//查询课程总数
					SocietySchoolCourse societySchoolCourse =
							societySchoolCourseService.loadById(societySchoolCourseNodeView.getOwnerCourseId());
					Integer nodeInCourseNum =
							service.selectByCourseIdAndNodeId(societySchoolCourse.getId(),societySchoolCourseNodeView.getOwnerNodeResourcesId());
					BigDecimal moneyNum = new BigDecimal((nodeInCourseNum*4)).divide(new BigDecimal(societySchoolCourse.getTotalLessons()), 2,BigDecimal.ROUND_HALF_UP);
					societySchoolCourseNodeView.setNodeInCourseMoney(moneyNum);
				});
			}

			query.setList(nodeList);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}


	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public String getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currYearFirstStr = sdf.format(currYearFirst)+" 00:00:00";
		return currYearFirstStr;
	}

	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public String getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currYearLastStr = sdf.format(currYearLast)+" 23:59:59";
		return currYearLastStr;
	}

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
	 * 学校管理员分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolCourseNode", notes = "分页查询SocietySchoolCourseNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoollistpage",method = RequestMethod.POST)
	public ModelAndView schoollistpage(@ModelAttribute SocietySchoolCourseNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/schoolResoucelistpage",method = RequestMethod.POST)
	public ModelAndView schoolResoucelistpage(@ModelAttribute SocietySchoolCourseNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 平台管理员分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolCourseNode", notes = "分页查询SocietySchoolCourseNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/ptlistpage",method = RequestMethod.POST)
	public ModelAndView ptlistpage(@ModelAttribute SocietySchoolCourseNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 导入学员的时候判断是否当前已有相关章节和习题
	 * @return
	 */
	@RequestMapping(value = "/selectIsNode",method = RequestMethod.POST)
	public ModelAndView selectIsNode(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
//		int service.selectIsNode();
		return modelAndView;
	}
	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolCourseNode", notes = "新增修改SocietySchoolCourseNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolCourseNode voObj){
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
	 * 学校管理员新增修改
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/schoolsaveOrUpdate",method = RequestMethod.POST)
	public ModelAndView schoolsaveOrUpdate(SocietySchoolCourseNode voObj){
		ModelAndView modelAndView = new ModelAndView();

		//获取转码之后的视频
		if(voObj.getFileId()!=null){
			String fileUrl = getFileTranscoding(voObj.getFileId());
			voObj.setFileUrl(fileUrl);
		}else {
			voObj.setFileUrl(null);
		}

		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			//课程ID
			String courseId = voObj.getOwnerCourseId();
			//根据课程id 查出多少章节
			Integer nodeNum = service.selectByClassIdVoNodeNum(courseId);
			voObj.setOrderNum(new BigDecimal(nodeNum+1));
			SocietySchoolCourse course = societySchoolCourseService.loadById(courseId);
			voObj.setOwnerSchoolId(course.getOwnerSchoolId());
			voObj.setOwnerSchoolName(course.getOwnerSchoolName());
			voObj.setOwnerMajorId(course.getOwnerMajorId());
			voObj.setOwnerMajorName(course.getOwnerMajorName());
			voObj.setOwnerCourseClsId(course.getCourseClsId());
			voObj.setOwnerCourseClsName(course.getCourseClsName());
			voObj.setOwnerCourseId(course.getId());
			voObj.setOwnerCourseName(course.getCourseName());
			User user = getLoginUser();
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setDataState("1");
			voObj.setShState("1");
			errMsg = service.insertNew(voObj);
		}else{
			//章节表
			errMsg = service.update(voObj);
			//章节池
			SocietySchoolCourseNode societySchoolCourseNode = service.loadById(voObj.getId());
			SocietyNodeResources societyNodeResources =
					societyNodeResourcesService.loadById(societySchoolCourseNode.getOwnerNodeResourcesId());
			societyNodeResources.setFileUrl(societySchoolCourseNode.getFileUrl());
			societyNodeResources.setFileId(societySchoolCourseNode.getFileId());
			societyNodeResources.setNodeName(societySchoolCourseNode.getNodeName());
			societyNodeResources.setOrderNum(societySchoolCourseNode.getOrderNum());
			societyNodeResources.setIsOpen(societySchoolCourseNode.getIsOpen());
			societyNodeResources.setNodeTimeLength(societySchoolCourseNode.getNodeTimeLength());
			societyNodeResourcesService.update(societyNodeResources);
			//别人是否关联 根据章节池id 查询
			String resourceId = societyNodeResources.getId();
			List<SocietyNodeResourcesView> nodeResourceList = societyNodeResourcesService.selectByResourcerId(resourceId);
			if (nodeResourceList.size()!=0){
				for (SocietyNodeResourcesView societyNodeResourcesView : nodeResourceList){
					societyNodeResourcesView.setNodeName(societyNodeResources.getNodeName());
					societyNodeResourcesView.setOrderNum(societyNodeResources.getOrderNum());
					societyNodeResourcesView.setFileUrl(societyNodeResources.getFileUrl());
					societyNodeResourcesView.setFileId(societyNodeResources.getFileId());
					societyNodeResourcesView.setNodeTimeLength(societyNodeResources.getNodeTimeLength());
					SocietySchoolCourseNode societySchoolCourseNodePublic =
							service.selectByApplySchoolIdAndResourceId(societyNodeResourcesView.getApplySchoolId(),societyNodeResourcesView.getOwnerNodeResourcesId());
					if (societySchoolCourseNodePublic==null){
						continue;
					}else {
						societySchoolCourseNodePublic.setFileUrl(societyNodeResources.getFileUrl());
						societySchoolCourseNodePublic.setFileId(societyNodeResources.getFileId());
						societySchoolCourseNodePublic.setNodeTimeLength(societyNodeResources.getNodeTimeLength());
						service.updateResource(societySchoolCourseNodePublic);
					}
				}
				societyNodeResourcesService.updateByNodeNameAndNum(nodeResourceList);
			}

		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	/**
	 * 新增章节方法 新增在 章节池和章节表
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/insertResourceAndCourseNode",method = RequestMethod.POST)
	public ModelAndView insertResourceAndCourseNode(SocietySchoolCourseNode voObj){
		ModelAndView modelAndView = new ModelAndView();

		//获取转码之后的视频
		if(voObj.getFileId()!=null){
			String fileUrl = getFileTranscoding(voObj.getFileId());
			voObj.setFileUrl(fileUrl);
		}else {
			voObj.setFileUrl(null);
		}
		//章节池的唯一id
		String resourcesNodeId = Guid.guid();

		String errMsg = "";
		//课程ID
		String courseId = voObj.getOwnerCourseId();
		//根据课程id 查出多少章节
		Integer nodeNum = service.selectByClassIdVoNodeNum(courseId);
		voObj.setOrderNum(new BigDecimal(nodeNum+1));
		SocietySchoolCourse course = societySchoolCourseService.loadById(courseId);
		voObj.setOwnerSchoolId(course.getOwnerSchoolId());
		voObj.setOwnerSchoolName(course.getOwnerSchoolName());
		voObj.setOwnerMajorId(course.getOwnerMajorId());
		voObj.setOwnerMajorName(course.getOwnerMajorName());
		voObj.setOwnerCourseClsId(course.getCourseClsId());
		voObj.setOwnerCourseClsName(course.getCourseClsName());
		voObj.setOwnerCourseId(course.getId());
		voObj.setOwnerCourseName(course.getCourseName());
		User user = getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setDataState("1");
		voObj.setShState("1");
		//关联章节池id
		voObj.setOwnerNodeResourcesId(resourcesNodeId);
		//章节池名
		voObj.setOwnerNodeResourcesName(voObj.getNodeName());
		//所属学校资源id
		voObj.setOwnerNodeResourcesSchoolId(voObj.getOwnerSchoolId());
		//插入章节表
		errMsg = service.insertNew(voObj);
		//插入章节池 societyNodeResourcesService
		SocietyNodeResources societyNodeResources = new SocietyNodeResources();
		societyNodeResources.setId(resourcesNodeId);
		societyNodeResources.setCreateTime(new Date());
		societyNodeResources.setCreateUserId(user.getUserId());
		societyNodeResources.setCreateUserName(user.getUserName());
		societyNodeResources.setOwnerSchoolId(course.getOwnerSchoolId());
		societyNodeResources.setOwnerSchoolName(course.getOwnerSchoolName());
		societyNodeResources.setOwnerMajorId(course.getOwnerMajorId());
		societyNodeResources.setOwnerMajorName(course.getOwnerMajorName());
		societyNodeResources.setOwnerCourseClsId(course.getCourseClsId());
		societyNodeResources.setOwnerCourseClsName(course.getCourseClsName());
		societyNodeResources.setOwnerCourseId(course.getId());
		societyNodeResources.setOwnerCourseName(course.getCourseName());
		societyNodeResources.setNodeName(voObj.getNodeName());
		societyNodeResources.setNodeTimeLength(voObj.getNodeTimeLength());
		societyNodeResources.setImageUrl(voObj.getImageUrl());
		societyNodeResources.setFileUrl(voObj.getFileUrl());
		societyNodeResources.setQuestionNum(voObj.getQuestionNum());
		societyNodeResources.setOrderNum(voObj.getOrderNum());
		societyNodeResources.setDataState("1");
		societyNodeResources.setFileId(voObj.getFileId());
		societyNodeResources.setIsOpen(voObj.getIsOpen());
		societyNodeResourcesService.insertResourcesAndNode(societyNodeResources);
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}



	@RequestMapping(value = "/updateCourseCopy",method = RequestMethod.POST)
	public ModelAndView updateCourseCopy(SocietySchoolCourseNode voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		errMsg = service.update(voObj);
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/insertCourseCopy",method = RequestMethod.POST)
	public ModelAndView insertCourseCopy(SocietySchoolCourseNodeView voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		service.insertCourseCopy(voObj,user);
		return buildResponse(modelAndView);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除SocietySchoolCourseNode", notes = "根据ID批量删除SocietySchoolCourseNode")
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
	@ApiOperation(value = "根据ID加载SocietySchoolCourseNode", notes = "根据ID加载SocietySchoolCourseNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/*
	 * 查询学员的小节的进度
	 * */
	@RequestMapping(value = "/selectStuNodeProgress",method = RequestMethod.POST)
	public ModelAndView  selectStuNodeProgress(SocietySchoolCourseNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.selectStuNodeProgress(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 天翼云逻辑删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/updateByIds",method = RequestMethod.POST)
	public ModelAndView updateByIds(String ids,String courseId){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			//删除oss相关的视频
			SocietySchoolCourseNode societySchoolCourseNode = service.loadById(str);
			//查询当前课程有没有重复的章节 根据根据url
			int flag =
					service.selectByCourseNameAndId
							(societySchoolCourseNode.getFileUrl());
			if(flag==1){//证明有多个一样的章节  只修改状态 不删除oos视频
				if(societySchoolCourseNode.getFileUrl()!=null && !societySchoolCourseNode.getFileUrl().equals("")){
					String[] strArr = societySchoolCourseNode.getFileUrl().split(tcloudDomain+"/");
					sysTyiyunCloudStorageService.deleteObject(strArr[1]);
				}
			}
			list.add(str);
		}
		service.updateByIds(list,courseId);
		return buildResponse(modelAndView);
	}


	/**
	 * 腾讯云逻辑删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/updateByIdsTeng",method = RequestMethod.POST)
	public ModelAndView updateByIdsTeng(String ids,String courseId){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		//章节里面删除 算是移除 章节动
		for(String str : arr){
			//删除oss相关的视频
			SocietySchoolCourseNode societySchoolCourseNode = service.loadById(str);
			//查询当前课程有没有重复的章节 根据根据url
			int flag =
					service.selectByCourseNameAndId
							(societySchoolCourseNode.getFileUrl());
			if(flag==1){//证明有多个一样的章节  只修改状态 不删除腾讯云视频
				if(societySchoolCourseNode.getFileUrl()!=null && !societySchoolCourseNode.getFileUrl().equals("")){
					deleteTeng(societySchoolCourseNode.getFileId());
				}
			}
			list.add(str);
		}
		service.updateByIds(list,courseId);
		return buildResponse(modelAndView);
	}


	/***
	 * 删除oos临时文件
	 * @param tempFileName
	 * @return
	 */
	@RequestMapping(value = "/deleteTempFileName",method = RequestMethod.POST)
	public ModelAndView deleteTempFileName(String tempFileName){
		ModelAndView modelAndView = new ModelAndView();
		sysTyiyunCloudStorageService.deleteObject(tempFileName);
		return buildResponse(modelAndView);
	}

	/**
	 * 根据课程id和学校ID查询课程下的章节
	 * @param schoolId courseId
	 * @return
	 */
	@RequestMapping(value = "/listByCourseIdAndSchoolId",method = RequestMethod.POST)
	public ModelAndView  listByCourseIdAndSchoolId(String courseId,String schoolId){
		ModelAndView modelAndView = new ModelAndView();
		List<Dtree> dtreeList = service.listByMajorIdAndSchoolId(courseId,schoolId);
		return buildResponse(modelAndView, dtreeList);
	}

	/**
	 * 1.根据想要导入的课程id 查出所有的相关课程章节的记录
	 * 2.并且执行copyObject复制oos上的视频文件
	 * 3.在根据当前的被导入的课程id 上升数据库
	 * @param courseId
	 * @param schoolId
	 * @param majorId
	 * @param courseClsId
	 * @param nodeIdList
	 * @param courseNamePinYin
	 * @return
	 */
	@RequestMapping(value = "/copyCourseAndNode",method = RequestMethod.POST)
	public ModelAndView  copyCourseAndNode(String courseId,String schoolId,
										   String majorId,String courseClsId,String nodeIdList,
										   String courseNamePinYin,String courseIdOld){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		service.insertCopyNodeAndQuestionAndOption( courseId, schoolId, majorId,
				courseClsId, nodeIdList, courseNamePinYin, courseIdOld,user);
		return buildResponse(modelAndView);
	}

	/**
	 * @Author QiuTianZhu
	 * @Description: 根据课程
	 * @Param:
	 * @return:
	 * @Date 2020/7/28 9:37
	 **/
	@RequestMapping(value = "/copyCourseAndNodeSelectUrl",method = RequestMethod.POST)
	public ModelAndView  copyCourseAndNodeSelectUrl(String schoolId, String nodeId, String courseIdOld){
		ModelAndView modelAndView = new ModelAndView();
		//查看课程章节关联表视频
		SocietySchoolCourseNode societySchoolCourseNode  = service.loadById(nodeId);

		return buildResponse(modelAndView,societySchoolCourseNode);
	}

	/**
	 * @Author QiuTianZhu
	 * @Description: 替换课程章节的视频url地址
	 * @Param:
	 * @return:
	 * @Date 2020/7/23 15:59
	 **/
	@RequestMapping(value = "/updateVideoUrl",method = RequestMethod.POST)
	public ModelAndView  updateVideoUrl(SocietySchoolCourseNode voObj){
		ModelAndView modelAndView = new ModelAndView();
		service.update(voObj);
		return buildResponse(modelAndView);
	}

	/**
	 * 上传腾讯云视频
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/uploadTengXunVideo",method = RequestMethod.POST)
	public ModelAndView uploadTengXunVideo(String courseId){
		ModelAndView modelAndView = new ModelAndView();

		Credential cred = new Credential(TencentSecretId, TencentSecretKey);

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(TencentEndPoint);

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		VodUploadClient client = new VodUploadClient(TencentSecretId, TencentSecretKey);
		VodClient vodClient = new VodClient(cred, TencentRegion, clientProfile);
		/*VodUploadRequest request = new VodUploadRequest();
		request.setMediaFilePath(societySchoolCourseNode.getFileUrl());*/
		//获取文件上传分类
		SocietySchoolCourse societySchoolCourse =
				societySchoolCourseService.loadById(courseId);
		long classId = 0;//要上传的视频分类
		try {
			//获取腾讯云上所有的视频分类
			DescribeAllClassRequest reqClass = DescribeAllClassRequest.fromJsonString("{}", DescribeAllClassRequest.class);
			DescribeAllClassResponse respClass = vodClient.DescribeAllClass(reqClass);
			MediaClassInfo[] classList = respClass.getClassInfoSet();

			for (int i=0;i<classList.length;i++){
				if(societySchoolCourse.getCourseName().equals(classList[i].getClassName())){
					classId = classList[i].getClassId();
				}
			}

			if(classId==0){//证明存在于腾讯云上的分类
				//先判断是否存在一级目录
				long classMain = 0;//原有的专业分类id
				for (int i=0;i<classList.length;i++){
					if(societySchoolCourse.getOwnerMajorName().equals(classList[i].getClassName())){
						classMain = classList[i].getClassId();
					}
				}

				if(classMain!=0){//存在专业
					//专业下创建子分类
					String params = "{\"ParentId\":"+classMain+",\"ClassName\":\""+societySchoolCourse.getCourseName()+"\"}";
					CreateClassRequest req = CreateClassRequest.fromJsonString(params, CreateClassRequest.class);

					CreateClassResponse resp = vodClient.CreateClass(req);
					classId = resp.getClassId();
				}else {
					//创建新的专业分类
					String paramsMain = "{\"ParentId\":-1,\"ClassName\":\""+societySchoolCourse.getOwnerMajorName()+"\"}";
					CreateClassRequest req = CreateClassRequest.fromJsonString(paramsMain, CreateClassRequest.class);

					CreateClassResponse resp = vodClient.CreateClass(req);
					//创建子分类
					String paramZi = "{\"ParentId\":"+resp.getClassId()+",\"ClassName\":\""+societySchoolCourse.getCourseName()+"\"}";
					CreateClassRequest reqZi = CreateClassRequest.fromJsonString(paramZi, CreateClassRequest.class);

					CreateClassResponse respZi = vodClient.CreateClass(reqZi);
					classId = respZi.getClassId();
				}
			}
/*			String[] video = societySchoolCourseNode.getFileUrl().split(".");
			String params = "{\"MediaType\":\"map4\",\"MediaName\":\""+societySchoolCourseNode.getFileUrl()+"\"}";

			ApplyUploadRequest reqUp = ApplyUploadRequest.fromJsonString(params, ApplyUploadRequest.class);

			ApplyUploadResponse respUp = vodClient.ApplyUpload(reqUp);

			String param1 = "{\"VodSessionKey\":\""+respUp.getVodSessionKey()+"\"}";
			CommitUploadRequest req = CommitUploadRequest.fromJsonString(param1, CommitUploadRequest.class);

			CommitUploadResponse resp = vodClient.CommitUpload(req);
			System.out.println(CommitUploadResponse.toJsonString(resp));*/
//			request.setClassId(classId);
//			request.setProcedure("20");
//			VodUploadResponse response = client.upload(TencentRegion, request);

			/*String videoParams =  "{\"FileId\":\""+response.getFileId()+"\",\"MediaProcessTask\":{\"TranscodeTaskSet\":[{\"Definition\":"+TencentDefinition+"}]}}";
			ProcessMediaRequest req = ProcessMediaRequest.fromJsonString(videoParams, ProcessMediaRequest.class);
			ProcessMediaResponse resp = vodClient.ProcessMedia(req);
			//获取任务id
			String taskId = resp.getTaskId();
			//查询任务详情
			String taskParams = "{\"TaskId\":\""+taskId+"\"}";
			DescribeTaskDetailRequest reqTask = DescribeTaskDetailRequest.fromJsonString(taskParams, DescribeTaskDetailRequest.class);

			DescribeTaskDetailResponse respTask = vodClient.DescribeTaskDetail(reqTask);
			boolean flag = true;
			while(flag){
				if(respTask.getStatus().equals("FINISH")){
					flag = false;
				}else {
					reqTask = DescribeTaskDetailRequest.fromJsonString(taskParams, DescribeTaskDetailRequest.class);
					respTask = vodClient.DescribeTaskDetail(reqTask);
				}
			}
			//获取压缩后的视频地址
			MediaProcessTaskResult[] list = respTask.getProcedureTask().getMediaProcessResultSet();
			String fileUrl = "";
			if(list!=null){
				fileUrl = list[0].getTranscodeTask().getOutput().getUrl();
			}*/

		} catch (Exception e) {
			// 业务方进行异常处理
			e.printStackTrace();
		}
		return buildResponse(modelAndView,classId);
	}

	@Pass
	@RequestMapping(value = "/getSignature",method = RequestMethod.POST)
	public ModelAndView  getSignature(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		//获取分类id
		Integer classId = (int) getClassId(courseId);
		//获取签名
		Signature sign = new Signature();
		sign.setSecretId(TencentSecretId);
		sign.setSecretKey(TencentSecretKey);
		sign.setCurrentTime(System.currentTimeMillis() / 1000);
		sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
		sign.setSignValidDuration(3600 * 24 * 2);
		sign.setClassId(classId);//分类
		sign.setProcedure(TencentDefinition);//转码
		String signature = "";//签名
		try {
			signature = sign.getUploadSignature();
		} catch (Exception e) {
			// 业务方进行异常处理
			e.printStackTrace();
		}
		return buildResponse(modelAndView,signature);
	}


	public long getClassId(String courseId){
		Credential cred = new Credential(TencentSecretId, TencentSecretKey);

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(TencentEndPoint);

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		VodClient vodClient = new VodClient(cred, TencentRegion, clientProfile);
		//获取文件上传分类
		SocietySchoolCourse societySchoolCourse =
				societySchoolCourseService.loadById(courseId);
		long classId = 0;//要上传的视频分类
		try {
			//获取腾讯云上所有的视频分类
			DescribeAllClassRequest reqClass = DescribeAllClassRequest.fromJsonString("{}", DescribeAllClassRequest.class);
			DescribeAllClassResponse respClass = vodClient.DescribeAllClass(reqClass);
			MediaClassInfo[] classList = respClass.getClassInfoSet();

			for (int i=0;i<classList.length;i++){
				if(societySchoolCourse.getCourseName().equals(classList[i].getClassName())){
					classId = classList[i].getClassId();
				}
			}

			if(classId==0){//证明存在于腾讯云上的分类
				//先判断是否存在一级目录
				long classMain = 0;//原有的专业分类id
				for (int i=0;i<classList.length;i++){
					if(societySchoolCourse.getOwnerMajorName().equals(classList[i].getClassName())){
						classMain = classList[i].getClassId();
					}
				}

				if(classMain!=0){//存在专业
					//专业下创建子分类
					String params = "{\"ParentId\":"+classMain+",\"ClassName\":\""+societySchoolCourse.getCourseName()+"\"}";
					CreateClassRequest req = CreateClassRequest.fromJsonString(params, CreateClassRequest.class);

					CreateClassResponse resp = vodClient.CreateClass(req);
					classId = resp.getClassId();
				}else {
					//创建新的专业分类
					String paramsMain = "{\"ParentId\":-1,\"ClassName\":\""+societySchoolCourse.getOwnerMajorName()+"\"}";
					CreateClassRequest req = CreateClassRequest.fromJsonString(paramsMain, CreateClassRequest.class);

					CreateClassResponse resp = vodClient.CreateClass(req);
					//创建子分类
					String paramZi = "{\"ParentId\":"+resp.getClassId()+",\"ClassName\":\""+societySchoolCourse.getCourseName()+"\"}";
					CreateClassRequest reqZi = CreateClassRequest.fromJsonString(paramZi, CreateClassRequest.class);

					CreateClassResponse respZi = vodClient.CreateClass(reqZi);
					classId = respZi.getClassId();
				}
			}
		} catch (Exception e) {
			// 业务方进行异常处理
			e.printStackTrace();
		}
		return classId;
	}


	/**
	 * 获取转码视频文件url
	 * @param fileId
	 * @return
	 */
	public String  getFileTranscoding(String fileId){
		String fileUrl = "";
		try{

			Credential cred = new Credential(TencentSecretId, TencentSecretKey);
			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setEndpoint(TencentEndPoint);

			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);

			VodClient client = new VodClient(cred, TencentRegion, clientProfile);

			String params = "{\"FileIds\":[\""+fileId+"\"]}";
			DescribeMediaInfosRequest req = DescribeMediaInfosRequest.fromJsonString(params, DescribeMediaInfosRequest.class);

			DescribeMediaInfosResponse resp = client.DescribeMediaInfos(req);
			MediaInfo[] list = resp.getMediaInfoSet();
			MediaTranscodeItem[] listFile = list[0].getTranscodeInfo().getTranscodeSet();
			boolean flag = true;
			while(flag){
				if(listFile.length!=1){
					flag = false;
				}else {
					req = DescribeTaskDetailRequest.fromJsonString(params, DescribeMediaInfosRequest.class);
					resp = client.DescribeMediaInfos(req);
					MediaInfo[] listOnce = resp.getMediaInfoSet();
					listFile = listOnce[0].getTranscodeInfo().getTranscodeSet();
				}
			}
			fileUrl = listFile[1].getUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}


	/**
	 * 删除腾讯云上视频
	 * @param fileId 视频标识
	 * @return
	 */
	@RequestMapping(value = "/deleteTeng",method = RequestMethod.POST)
	public ModelAndView deleteTeng(String fileId){
		ModelAndView modelAndView = new ModelAndView();
		Credential cred = new Credential(TencentSecretId, TencentSecretKey);

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(TencentEndPoint);

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		VodClient vodClient = new VodClient(cred, TencentRegion, clientProfile);


		try {
			String params = "{\"FileId\":\""+fileId+"\"}";
			DeleteMediaRequest req = DeleteMediaRequest.fromJsonString(params, DeleteMediaRequest.class);

			DeleteMediaResponse resp = vodClient.DeleteMedia(req);
		} catch (Exception e) {
			// 业务方进行异常处理
			e.printStackTrace();
		}
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/deleteTengNew",method = RequestMethod.POST)
	public ModelAndView deleteTengNew(String fileId){
		ModelAndView modelAndView = new ModelAndView();
		//查询后台有没有使用这门课
		int flag =
				service.selectByCourseNameAndId
						(fileId);
		if (flag==0){
			Credential cred = new Credential(TencentSecretId, TencentSecretKey);

			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setEndpoint(TencentEndPoint);

			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);

			VodClient vodClient = new VodClient(cred, TencentRegion, clientProfile);


			try {
				String params = "{\"FileId\":\""+fileId+"\"}";
				DeleteMediaRequest req = DeleteMediaRequest.fromJsonString(params, DeleteMediaRequest.class);

				DeleteMediaResponse resp = vodClient.DeleteMedia(req);
			} catch (Exception e) {
				// 业务方进行异常处理
				e.printStackTrace();
			}
		}
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/cyunVideoList",method = RequestMethod.POST)
	public ModelAndView cyunVideoList(){
		ModelAndView modelAndView = new ModelAndView();
		Credential cred = new Credential(TencentSecretId, TencentSecretKey);

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(TencentEndPoint);

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		VodClient vodClient = new VodClient(cred, TencentRegion, clientProfile);

		List<Dtree> dtreeList = new ArrayList<>();
		try {
			String params = "{}";
			DescribeAllClassRequest req = DescribeAllClassRequest.fromJsonString(params, DescribeAllClassRequest.class);

			DescribeAllClassResponse resp = vodClient.DescribeAllClass(req);
			MediaClassInfo[] list = resp.getClassInfoSet();

			for (MediaClassInfo mediaClassInfo:list){
				if (mediaClassInfo.getParentId()==-1) {
					Dtree dtree = new Dtree();
					dtree.setId(mediaClassInfo.getClassId().toString());
					dtree.setTitle(mediaClassInfo.getClassName());
					dtree.setParentId("ROOT");
					dtree.setSpread(false);
					dtree.setLast(false);
					dtreeList.add(dtree);
				}
			}
		} catch (Exception e) {
			// 业务方进行异常处理
			e.printStackTrace();
		}
		return buildDtreeResponse(modelAndView,dtreeList);
	}

	@RequestMapping(value = "/cyunVideoListCls",method = RequestMethod.POST)
	public ModelAndView cyunVideoListCls(String parentId){
		ModelAndView modelAndView = new ModelAndView();
		Credential cred = new Credential(TencentSecretId, TencentSecretKey);

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(TencentEndPoint);

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		VodClient vodClient = new VodClient(cred, TencentRegion, clientProfile);

		List<Dtree> dtreeList = new ArrayList<>();
		try {
			String params = "{}";
			DescribeAllClassRequest req = DescribeAllClassRequest.fromJsonString(params, DescribeAllClassRequest.class);

			DescribeAllClassResponse resp = vodClient.DescribeAllClass(req);
			MediaClassInfo[] list = resp.getClassInfoSet();

			for (MediaClassInfo mediaClassInfo:list){
				if (mediaClassInfo.getParentId()==Long.parseLong(parentId)) {
					Dtree dtree = new Dtree();
					dtree.setId(mediaClassInfo.getClassId().toString());
					dtree.setTitle(mediaClassInfo.getClassName());
					dtree.setParentId(parentId);
					dtreeList.add(dtree);
				}
			}
		} catch (Exception e) {
			// 业务方进行异常处理
			e.printStackTrace();
		}
		return buildResponse(modelAndView,dtreeList);
	}


	@RequestMapping(value = "/selectByCyunVideo",method = RequestMethod.POST)
	public ModelAndView selectByCyunVideo(String id){
		ModelAndView modelAndView = new ModelAndView();
		Credential cred = new Credential(TencentSecretId, TencentSecretKey);

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(TencentEndPoint);

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		VodClient vodClient = new VodClient(cred, TencentRegion, clientProfile);
		MediaInfo[] list = null;
		String params = "{}";
		if (id!=null && !id.equals("")){
			params = "{\"ClassIds\":["+id+"]}";
		}
		try {
			SearchMediaRequest req = SearchMediaRequest.fromJsonString(params, SearchMediaRequest.class);

			SearchMediaResponse resp = vodClient.SearchMedia(req);
			//获取总数
			long num = resp.getTotalCount();
			String paramsNew = "{\"Limit\":"+num+"}";
			if (id!=null && !id.equals("")){
				paramsNew = "{\"ClassIds\":["+id+"],\"Limit\":"+num+"}";
			}
			SearchMediaRequest reqNew = SearchMediaRequest.fromJsonString(paramsNew, SearchMediaRequest.class);

			SearchMediaResponse respNew = vodClient.SearchMedia(reqNew);
			list = respNew.getMediaInfoSet();
		} catch (Exception e) {
			// 业务方进行异常处理
			e.printStackTrace();
		}
		return buildResponse(modelAndView,list);
	}



	@RequestMapping(value = "/deleteCyunVideo",method = RequestMethod.POST)
	public ModelAndView deleteCyunVideo(String fileId){
		ModelAndView modelAndView = new ModelAndView();
		Credential cred = new Credential(TencentSecretId, TencentSecretKey);

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(TencentEndPoint);

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		VodClient vodClient = new VodClient(cred, TencentRegion, clientProfile);

		try {
			String params = "{\"FileId\":\""+fileId+"\"}";
			DeleteMediaRequest req = DeleteMediaRequest.fromJsonString(params, DeleteMediaRequest.class);

			DeleteMediaResponse resp = vodClient.DeleteMedia(req);

		} catch (Exception e) {
			// 业务方进行异常处理
			e.printStackTrace();
		}
		return buildResponse(modelAndView);
	}


}
