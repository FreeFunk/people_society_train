package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietyStudentAndNode;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentAndNode")
@Controller
@RequestMapping("/society/societyStudentAndNode")
public class SocietyStudentAndNodeController extends BaseController{
	
	@Autowired
	private SocietyStudentAndNodeService service;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
    private SocietySchoolCourseNodeService societySchoolCourseNodeService;

	@Autowired
	private SocietyStudentPractiseQuestOptionService societyStudentPractiseQuestOptionService;
	@Autowired
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentAndNode", notes = "分页查询SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 不分页查询，学习管理员
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "不分页查询SocietyStudentAndNode", notes = "不分页查询SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjForSchool",method = RequestMethod.POST)
	public ModelAndView listByObjForSchool(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBystudentAndnode",method = RequestMethod.POST)
	public ModelAndView selectBystudentAndnode(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getStudentId().equals("") && query.getQueryObj().getOwnerStudentAndCourseId().equals("")){
			return buildResponse(modelAndView,query);
		}else {
			User user = getLoginUser();
			query.getQueryObj().setOwnerSchoolId(user.getCompId());
			service.listPage(query);
			return buildResponse(modelAndView,query);
		}
	}


	@RequestMapping(value = "/selectBystudentAndnodeRenShe",method = RequestMethod.POST)
	public ModelAndView selectBystudentAndnodeRenShe(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getStudentId().equals("") && query.getQueryObj().getOwnerStudentAndCourseId().equals("")){
			return buildResponse(modelAndView,query);
		}else {
			service.listPage(query);
			return buildResponse(modelAndView,query);
		}
	}
	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyStudentAndNode", notes = "新增修改SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentAndNode voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentAndNode", notes = "根据ID批量删除SocietyStudentAndNode")
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
	@ApiOperation(value = "根据ID加载SocietyStudentAndNode", notes = "根据ID加载SocietyStudentAndNode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/*
	* 人社学员查看课程章节
	* */
	@RequestMapping(value = "/selectByCourseName",method = RequestMethod.POST)
	public ModelAndView selectByCourseName(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		String courseId = query.getQueryObj().getOwnerCourseId();
		String schoolId = query.getQueryObj().getOwnerSchoolId();
		String nodeName = query.getQueryObj().getNodeName();
		String examineState = query.getQueryObj().getExamineState();
		String stuAndCouId = query.getQueryObj().getOwnerStudentAndCourseId();

		SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery =
				societySchoolCourseNodeService.selectByCourseIdAndSchoolIdlistPage(
						query.getLimit(),query.getPage(),query.getStart(),query.getOrderBy(),
						courseId,schoolId,nodeName,examineState,stuAndCouId);
		//1.循环遍历   先查出公共习题表按排序号排序 学生的习题表 双层循环添加属性
		List<SocietyStudentAndNodeView> studentList = service.selectStudentCourse(query);//学生章节习题
		List<SocietySchoolCourseNodeView> allList = societySchoolCourseNodeQuery.getList();//公共章节习题
		for(int i = 0;i<studentList.size();i++){
			for(int j = 0;j<allList.size();j++){
				if(studentList.get(i).getNodeId().equals(allList.get(j).getId())){
					allList.get(j).setStudentId(studentList.get(i).getStudentId());//学生id
					allList.get(j).setStudentName(studentList.get(i).getStudentName());//学生名字
					allList.get(j).setStudentIdCardNum(studentList.get(i).getStudentIdCardNum());//身份证号
					allList.get(j).setNodeProgress(studentList.get(i).getNodeProgress().intValue());//章节进度
					allList.get(j).setNodeQuestionScore(studentList.get(i).getNodeQuestionScore());//习题得分
					allList.get(j).setQuestionIsFinished(studentList.get(i).getQuestionIsFinished());//习题是否完成
					allList.get(j).setQuestionIsPass(studentList.get(i).getQuestionIsPass());//习题是否及格
					allList.get(j).setLearnIsFinished(studentList.get(i).getLearnIsFinished());//是否完成学习
					allList.get(j).setStudyTimeLength(studentList.get(i).getStudyTimeLength());//时长
					allList.get(j).setExamineState(studentList.get(i).getExamineState());
					allList.get(j).setRightNum(studentList.get(i).getNodeQuestionScore());
					allList.get(j).setFinishTime(studentList.get(i).getFinishTime());
				}
			}
		}
		societySchoolCourseNodeQuery.setList(allList);
		buildResponse(modelAndView,societySchoolCourseNodeQuery);
		return modelAndView;
	}

	@RequestMapping(value = "/selectByNodeExamine",method = RequestMethod.POST)
	public ModelAndView selectByNodeExamine(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//1.循环遍历   先查出公共习题表按排序号排序 学生的习题表 双层循环添加属性
		String courseId = query.getQueryObj().getOwnerCourseId();
		String schoolId = query.getQueryObj().getOwnerSchoolId();
		String nodeName = query.getQueryObj().getNodeName();
		String examineState = query.getQueryObj().getExamineState();
		String stuAndCouId = query.getQueryObj().getOwnerStudentAndCourseId();

		SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery =
				societySchoolCourseNodeService.selectByCourseIdAndSchoolIdlistPageExamin(
						query.getLimit(),query.getPage(),query.getStart(),query.getOrderBy(),
						courseId,schoolId,nodeName,examineState,stuAndCouId);
		//1.循环遍历   先查出公共习题表按排序号排序 学生的习题表 双层循环添加属性
		List<SocietyStudentAndNodeView> studentList = service.selectByCourseNamelistPageNew(query);//学生章节习题
		List<SocietySchoolCourseNodeView> allList = societySchoolCourseNodeQuery.getList();//公共章节习题

		for(int i = 0;i<studentList.size();i++){
			for(int j = 0;j<allList.size();j++){
				if(studentList.get(i).getNodeId().equals(allList.get(j).getId())){
					allList.get(j).setStudentId(studentList.get(i).getStudentId());//学生id
					allList.get(j).setStudentName(studentList.get(i).getStudentName());//学生名字
					allList.get(j).setStudentIdCardNum(studentList.get(i).getStudentIdCardNum());//身份证号
					allList.get(j).setNodeProgress(studentList.get(i).getNodeProgress().intValue());//章节进度
					allList.get(j).setNodeQuestionScore(studentList.get(i).getNodeQuestionScore());//习题得分
					allList.get(j).setQuestionIsFinished(studentList.get(i).getQuestionIsFinished());//习题是否完成
					allList.get(j).setQuestionIsPass(studentList.get(i).getQuestionIsPass());//习题是否及格
					allList.get(j).setLearnIsFinished(studentList.get(i).getLearnIsFinished());//是否完成学习
					allList.get(j).setStudyTimeLength(studentList.get(i).getStudyTimeLength());//时长
					allList.get(j).setExamineState(studentList.get(i).getExamineState());
					allList.get(j).setRightNum(studentList.get(i).getNodeQuestionScore());
					allList.get(j).setFinishTime(studentList.get(i).getFinishTime());
					allList.get(j).setStuAndNodeId(studentList.get(i).getId());
				}
			}
		}
		societySchoolCourseNodeQuery.setList(allList);
		buildResponse(modelAndView,societySchoolCourseNodeQuery);
		return modelAndView;
	}


	/**
	 *@Author:ZhaoSiDa
	 *@Description: 课后习题统计
	 *@DateTime: 2020/6/3 9:50
	 */
	@RequestMapping(value = "/selectNodeQuestion",method = RequestMethod.POST)
	public ModelAndView selectNodeQuestion(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/*
	* 人社学员查看课程章节,Excel导出
	* */
	@RequestMapping(value = "/selectByCourseNameExport",method = RequestMethod.POST)
	public ModelAndView selectByCourseNameExport(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listByObj(query);
		//1.循环遍历
		List<SocietyStudentAndNodeView> list = query.getList();
		for (SocietyStudentAndNodeView societyStudentAndNodeView : list){
			//查看习题总数是否为空或者0
			if(societyStudentAndNodeView.getTotalQuestionNum()==null){
				//查询公共习题表
				int questionNum = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeId(
						societyStudentAndNodeView.getNodeId(),
						societyStudentAndNodeView.getOwnerCourseId(),
						societyStudentAndNodeView.getOwnerSchoolId());
				societyStudentAndNodeView.setTotalQuestionNum(questionNum);
			}else if(societyStudentAndNodeView.getTotalQuestionNum()==0){
				//查询公共习题表
				int questionNum = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeId(
						societyStudentAndNodeView.getNodeId(),
						societyStudentAndNodeView.getOwnerCourseId(),
						societyStudentAndNodeView.getOwnerSchoolId());
				societyStudentAndNodeView.setTotalQuestionNum(questionNum);
			}
			if("1".equals(societyStudentAndNodeView.getLearnIsFinished())){
				societyStudentAndNodeView.setLearnIsFinished("完成");
			}else{
				societyStudentAndNodeView.setLearnIsFinished("未完成");
			}
			if("1".equals(societyStudentAndNodeView.getQuestionIsPass())){
				societyStudentAndNodeView.setQuestionIsPass("及格");
			}else{
				societyStudentAndNodeView.setQuestionIsPass("不及格");
			}
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBySchoolId",method = RequestMethod.POST)
	public ModelAndView selectBySchoolId(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//获取当前用户id
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		service.selectByCourseNamelistPage(query);
		//遍历studentId = "undefined"
		List<SocietyStudentAndNodeView> list = query.getList();
		for(SocietyStudentAndNodeView societyStudentAndNodeView : list){
			societyStudentAndNodeView.setRightNum(
					societyStudentPractiseQuestOptionService.countRight(
							societyStudentAndNodeView.getOwnerSchoolId(),
							societyStudentAndNodeView.getNodeId(),
							societyStudentAndNodeView.getStudentId()));
			if(societyStudentAndNodeView.getTotalQuestionNum()==null){
				Integer num = societySchoolCourseNodeQuestionService.selectByCourseIdAndNodeId(
						societyStudentAndNodeView.getNodeId(),
						societyStudentAndNodeView.getOwnerCourseId(),
						societyStudentAndNodeView.getOwnerSchoolId());
				societyStudentAndNodeView.setTotalQuestionNum(num);

			}
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/examineStateInfoList",method = RequestMethod.POST)
	public ModelAndView examineStateInfoList(String stuCourseId,String nodeIdStr){
		ModelAndView modelAndView = new ModelAndView();
		String[] nodeIdList = nodeIdStr.split(",");
		for(String nodeId : nodeIdList){
			service.updateByStuCoIdAndNodeId(stuCourseId,nodeId);
		}
		buildResponse(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/examineStateInfo",method = RequestMethod.POST)
	public ModelAndView examineStateInfo(String stuCourseId,String nodeId){
		ModelAndView modelAndView = new ModelAndView();
		service.updateByStuCoIdAndNodeId(stuCourseId,nodeId);
		buildResponse(modelAndView);
		return modelAndView;
	}

	/**
	 * @Author QiuTianZhu
	 * @Description: 根据学生id 和 章节id 查看学生的人脸是否合格
	 * @Param:
	 * @return:
	 * @Date 2020/8/7 16:06
	 **/
	@RequestMapping(value = "/selectStudentFaceIsQualified",method = RequestMethod.POST)
	public ModelAndView selectStudentFaceIsQualified(String stuAndCouId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,String> map = service.selectStudentFaceIsQualified(stuAndCouId);
		if (map.isEmpty()){
			map.put("无","已学完章节中人脸符合规定次数");
		}
		buildResponse(modelAndView,map);
		return modelAndView;
	}


	/**
	 * @Author QiuTianZhu
	 * @Description: 根据classId 查看学生的人脸是否合格
	 * @Param:
	 * @return:
	 * @Date 2020/8/7 16:06
	 **/
	@RequestMapping(value = "/selectStudentClassFaceIsQualified",method = RequestMethod.POST)
	public ModelAndView selectStudentClassFaceIsQualified(String classId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,String> map = service.selectStudentClassFaceIsQualified(classId);
		buildResponse(modelAndView,map);
		return modelAndView;
	}

	@RequestMapping(value = "/selectStudentClassFaceIsQualifiedlistpage",method = RequestMethod.POST)
	public ModelAndView selectStudentClassFaceIsQualifiedlistpage(@ModelAttribute SocietyStudentAndNodeQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectStudentClassFaceIsQualifiedlistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 导出人脸excel表结果
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/classFaceIsQualifiedlistObj",method = RequestMethod.POST)
	public ModelAndView classFaceIsQualifiedlistObj( String classId){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietyStudentAndNodeView> list = service.classFaceIsQualifiedlistObj(classId);
		buildResponse(modelAndView,list);
		return modelAndView;
	}


}
