package com.edgedo.society.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.society.entity.SocietyNodeExamineRecord;
import com.edgedo.society.queryvo.SocietyNodeExamineRecordQuery;
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


@Api(tags = "SocietyNodeExamineRecord")
@Controller
@RequestMapping("/society/societyNodeExamineRecord")
public class SocietyNodeExamineRecordController extends BaseController{
	
	@Autowired
	private SocietyNodeExamineRecordService service;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Autowired
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	@Autowired
	private SocietyStudentPractiseQuestOptionService societyStudentPractiseQuestOptionService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyNodeExamineRecord", notes = "分页查询SocietyNodeExamineRecord")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyNodeExamineRecordQuery query){
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
	@ApiOperation(value = "新增修改SocietyNodeExamineRecord", notes = "新增修改SocietyNodeExamineRecord")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyNodeExamineRecord voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyNodeExamineRecord", notes = "根据ID批量删除SocietyNodeExamineRecord")
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
	@ApiOperation(value = "根据ID加载SocietyNodeExamineRecord", notes = "根据ID加载SocietyNodeExamineRecord")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	/**
	 * @Author QiuTianZhu
	 * @Description: 变更学生的章节学习情况
	 * @Param:stuCourseId nodeId ownerCourseId studentId record
	 * @return:
	 * @Date 2020/8/3 10:06
	 **/
	@RequestMapping(value = "/examineStudentNodeInfo",method = RequestMethod.POST)
	public ModelAndView  examineStudentNodeInfo(String stuCourseId,String nodeId,
												String ownerCourseId,String studentId,String record){
		ModelAndView modelAndView = new ModelAndView();
		//四个修改分区  student_and_course student_and_node student_and_question  student_and_option
		//1.新增 审核记录表
		User user = getLoginUser();
		service.insertEeamineInfo(stuCourseId,nodeId,ownerCourseId,studentId,user,record);
		//2.删除 学员习题选项信息
		societyStudentPractiseQuestOptionService.deleteByStuIdAndNodeId(nodeId,stuCourseId,studentId);
		//3.删除 学员习题信息
		societyStudentPractiseQuestionService.deleteByStuIdAndNodeId(nodeId,stuCourseId,studentId);
		//4.清空 学员小节关联的信息
		societyStudentAndNodeService.updateByStuNodeInfo(stuCourseId,nodeId,ownerCourseId,studentId);
		//5.清空 学员课程关联的信息
		societyStudentAndCourseService.updateStudentCourseInfo(stuCourseId,nodeId,ownerCourseId,studentId);

		//6.删除证书
		societyStudentCertificateService.deleteByStudentIdAndCourseId(studentId,ownerCourseId);
		return buildResponse(modelAndView);
	}


	/**
	 * @Author QiuTianZhu
	 * @Description: 批量变更学生的章节学习情况
	 * @Param:stuCourseId finishTimePeo ownerCourseId studentId record
	 * @return:
	 * @Date 2020/8/3 10:06
	 **/
	@RequestMapping(value = "/examineStudentNodeListInfo",method = RequestMethod.POST)
	public ModelAndView  examineStudentNodeListInfo(String stuCourseId,String ownerCourseId,String studentId,
													String record,String nodeIdStr){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		service.examineStudentNodeListInfo(user,stuCourseId,ownerCourseId,studentId,record,nodeIdStr);
		return buildResponse(modelAndView);
	}
}
