package com.edgedo.society.controller;


import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.HtmlToPdfUtil;
import com.edgedo.society.entity.*;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietyStudentAndCourse")
@Controller
@RequestMapping("/society/societyStudentAndCourse")
public class SocietyStudentAndCourseController extends BaseController{
	
	@Autowired
	private SocietyStudentAndCourseService service;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolClassAdminService societySchoolClassAdminService;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietyStudentService societyStudentService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Autowired
	private SocietyStudentTestPaperService societyStudentTestPaperService;
	@Autowired
	private SocietyStudentStudyProcessFaceService societyStudentStudyProcessFaceService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;
	@Autowired
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	@Autowired
	private SocietySchoolClassGroupAdminService societySchoolClassGroupAdminService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SocietySchoolClassAndCourseService societySchoolClassAndCourseService;
	@Autowired
	private SocietySchoolMajorService societySchoolMajorService;

	private
	@Value("${wkhtmltopdf.toPdfTool}")
	String toPdfTool;

	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentAndCourse", notes = "分页查询SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 分页查询,学校管理员
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentAndCourse", notes = "分页查询SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpageforschool",method = RequestMethod.POST)
	public ModelAndView listpageforschool(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/listpageByStuCou",method = RequestMethod.POST)
	public ModelAndView listpageByStuCou(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.listPageByStuCou(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/listpageforschoolStudentOnce",method = RequestMethod.POST)
	public ModelAndView listpageforschoolStudentOnce(@ModelAttribute SocietyStudentAndCourseQuery query){

		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/selectBySchoolAndClass",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClass(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(80);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySchoolAndClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@RequestMapping(value = "/selectBySupSchoolAndClass",method = RequestMethod.POST)
	public ModelAndView selectBySupSchoolAndClass(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(80);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		SysUser sysUser = sysUserService.loadById(getLoginUser().getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySchoolAndClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * 县区监管首页查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectBySupSchoolAndClassHome",method = RequestMethod.POST)
	public ModelAndView selectBySupSchoolAndClassHome(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		SysUser sysUser = sysUserService.loadById(user.getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySchoolAndClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 人社首页查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectBySchoolAndClassHome",method = RequestMethod.POST)
	public ModelAndView selectBySchoolAndClassHome(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS DESC");
		}
		service.selectBySchoolAndClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 班主任下的班级的学习进度
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageforclassadmin",method = RequestMethod.POST)
	public ModelAndView listpageforclassadmin(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classId = societySchoolClassService.selectByClassAdminId(classAdminId);
		if(classId.size()==0){
			return buildResponse(modelAndView,query);
		}else {
			query.setClassIdList(classId);
			service.forclassadminlistPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	/**
	 * 班主任组长下的班级的学习进度
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageforclassGroupadmin",method = RequestMethod.POST)
	public ModelAndView listpageforclassGroupadmin(@ModelAttribute SocietyStudentAndCourseQuery query){
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		String classGroupAdminId = societySchoolClassGroupAdminService.selectBySysUserId(user.getUserId());
		List<String> classAdminId = societySchoolClassAdminService.selectByGroupId(classGroupAdminId);
		List<String> classId = societySchoolClassService.selectByClassAdminIdList(classAdminId);
		if(classId.size()==0){
			return buildResponse(modelAndView,query);
		}else {
			query.setClassIdList(classId);
			service.forclassadminlistPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	/**
	 * 不分页查询,学校管理员
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "不分页查询SocietyStudentAndCourse", notes = "不分页查询SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjforschool",method = RequestMethod.POST)
	public ModelAndView listByObjforschool(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listByExcObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * 导出章节不合格excel表结果
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selectByNodeIsFiled",method = RequestMethod.POST)
	public ModelAndView selectByNodeIsFiled(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectByNodeIsFiled(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/updateAll",method = RequestMethod.POST)
	public ModelAndView updateAll(@ModelAttribute SocietyStudentAndCourse societyStudentAndCourse){
		ModelAndView modelAndView = new ModelAndView();
		String majorId = societyStudentAndCourse.getOwnerMajorId();
		String classId = societyStudentAndCourse.getClassId();
		String courseId = societyStudentAndCourse.getCourseId();
		SocietySchoolCourse societySchoolCourse =
				societySchoolCourseService.loadById(societyStudentAndCourse.getCourseId());
		if(!(societySchoolCourse.getOwnerMajorId().equals(majorId))){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "课程不属于该专业下");
			return modelAndView;
		}

		SocietySchoolClassAndCourse societySchoolClassAndCourse =
				societySchoolClassAndCourseService.selectCourseIdAndClassId(classId,courseId);
		if(societySchoolClassAndCourse==null){
			modelAndView.addObject("success", false);
			modelAndView.addObject("code", "0");
			modelAndView.addObject("modelAndViewData", "1");
			modelAndView.addObject("errMsg", "班级未关联此课程");
			return modelAndView;
		}
		//专业id
		SocietySchoolMajor societySchoolMajor = societySchoolMajorService.loadById(majorId);
		societyStudentAndCourse.setOwnerMajorName(societySchoolMajor.getMajorName());
		//课程id
		SocietySchoolCourse societySchoolCourse1 = societySchoolCourseService.loadById(courseId);
		societyStudentAndCourse.setCourseName(societySchoolCourse1.getCourseName());
		//班级id
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		societyStudentAndCourse.setClassName(societySchoolClass.getClassName());
		service.update(societyStudentAndCourse);
		buildResponse(modelAndView);
		return modelAndView;
	}


	/**
	 * 班主任导出
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listByObjforschoolClassAdmin",method = RequestMethod.POST)
	public ModelAndView listByObjforschoolClassAdmin(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		List<String> classId = societySchoolClassService.selectByClassAdminId(classAdminId);
		if(classId.size()==0){
			return buildResponse(modelAndView,query);
		}else {
			query.setClassIdList(classId);
			service.listByExcObjClassAdmin(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}

	}

	/**
	 * 人社导出学习进度
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listByObjforschoolRenShe",method = RequestMethod.POST)
	public ModelAndView listByObjforschoolRenShe(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();

		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listByExcRenSheObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * 分页查询,人社角色
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietyStudentAndCourse", notes = "分页查询SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpageforpeoplesociety",method = RequestMethod.POST)
	public ModelAndView listpageforpeoplesociety(@ModelAttribute SocietyStudentAndCourseQuery query){
		//初始化到课率区间
		if (query.getQueryObj().getCourseProgressStart()==null){
			query.getQueryObj().setCourseProgressStart(0);
			query.getQueryObj().setCourseProgressEnd(100);
		}
		ModelAndView modelAndView = new ModelAndView();
		//根据学校名称查询学校id
		if(query.getQueryObj().getOwnerSchoolId()!=null&&query.getQueryObj().getOwnerSchoolId().length()>0){
			SocietySchoolQuery qq = new SocietySchoolQuery();
			qq.getQueryObj().setSchoolName(query.getQueryObj().getOwnerSchoolId());
			societySchoolService.listPage(qq);
			if(qq.getList().size()>0){
				List<SocietySchoolView> listS = qq.getList();
				query.getQueryObj().setOwnerSchoolId(listS.get(0).getId());
			}
		}

		/*if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}*/
		service.listPage(query);
		//查询学校名称
		List<SocietyStudentAndCourseView> listV = query.getList();
		for(SocietyStudentAndCourseView view : listV){
			SocietySchool ss = societySchoolService.loadById(view.getOwnerSchoolId());
			view.setOwnerSchoolId(ss.getSchoolName());
		}

		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 不分页查询,人社角色
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "不分页查询SocietyStudentAndCourse", notes = "不分页查询SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjforpeoplesociety",method = RequestMethod.POST)
	public ModelAndView listByObjforpeoplesociety(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//根据学校名称查询学校id
		if(query.getQueryObj().getOwnerSchoolId()!=null&&query.getQueryObj().getOwnerSchoolId().length()>0){
			SocietySchoolQuery qq = new SocietySchoolQuery();
			qq.getQueryObj().setSchoolName(query.getQueryObj().getOwnerSchoolId());
			societySchoolService.listPage(qq);
			if(qq.getList().size()>0){
				List<SocietySchoolView> listS = qq.getList();
				query.getQueryObj().setOwnerSchoolId(listS.get(0).getId());
			}
		}
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("COURSE_PROGRESS ASC");
		}
		service.listByObj(query);
		//查询学校名称
		List<SocietyStudentAndCourseView> listV = query.getList();
		for(SocietyStudentAndCourseView view : listV){
			SocietySchool ss = societySchoolService.loadById(view.getOwnerSchoolId());
			view.setOwnerSchoolId(ss.getSchoolName());
		}
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietyStudentAndCourse", notes = "新增修改SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietyStudentAndCourse voObj){
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
	@ApiOperation(value = "根据ID批量删除SocietyStudentAndCourse", notes = "根据ID批量删除SocietyStudentAndCourse")
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
	@ApiOperation(value = "根据ID加载SocietyStudentAndCourse", notes = "根据ID加载SocietyStudentAndCourse")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	@RequestMapping(value = "/selectBySchoolName",method = RequestMethod.POST)
	public ModelAndView selectBySchoolName(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.selectBySchoolNamelistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 学员管理下查看学员所在的班级 返回进度
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/selectByStuIdAndSchoolId",method = RequestMethod.POST)
	public ModelAndView selectByStuIdAndSchoolId(String studentId){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		List<SocietyStudentAndCourseView> list =
				service.selectByStuIdAndSchoolId(studentId,user.getCompId());
		return buildResponse(modelAndView,list);
	}

	/**
	 * 根据学校id 班级id 课程id 学生id 查出学生课程关联id
	 * @param schoolId
	 * @param classId
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/selectBySchoolIdAndClassIdAndCourseId",method = RequestMethod.POST)
	public ModelAndView selectBySchoolIdAndClassIdAndCourseId(String schoolId,String classId,String courseId,String studentId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentAndCourseView societyStudentAndCourseView =
				service.selectSchAndClaAndCouAndStu(schoolId,classId,courseId,studentId);
		return buildResponse(modelAndView,societyStudentAndCourseView);
	}

	/**
	 * 根据课程id 查询所有学校的课程学习人数
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/CourseStudyListTablelistpage",method = RequestMethod.POST)
	public ModelAndView CourseStudyListTablelistpage(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		//统计 课程已经在学习的人数
		Integer num = service.selectByCourseStudyNum(courseId);
		return buildResponse(modelAndView,num);
	}

	@RequestMapping(value = "/pdf",method = RequestMethod.POST)
	public ModelAndView pdf(String urlStr){
		ModelAndView modelAndView = new ModelAndView();
		//统计 课程已经在学习的人数
		HtmlToPdfUtil pdfUtil = new HtmlToPdfUtil(toPdfTool);
		pdfUtil.convert(
				urlStr,
				"E:/baidu.pdf",
				(state) ->{
					if(state.equals("success")){
						System.out.println("success");
						//TODO:成功
					}else{
						//TODO:失败
						System.out.println("fail");
					}
				});
		return buildResponse(modelAndView);
	}

	//导出pdf 文件
	@RequestMapping(value = "/tempFilePdf",method = RequestMethod.POST)
	public ModelAndView tempFilePdf(String studentId,String classId,String schoolId,String courseId,String stuAndCouId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		//学生对象
		SocietyStudent societyStudent = societyStudentService.loadById(studentId);
		//学校对象
		SocietySchool societySchool = societySchoolService.loadById(schoolId);
		//课程对象
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		//班级对象
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		//课程学员关联
		SocietyStudentAndCourse societyStudentAndCourse = service.loadById(stuAndCouId);
		//第一页  学员信息 姓名 身份证号 生成日期 平台盖章
		map.put("first",societyStudent);
		//第二页 学生信息 班级信息
		SocietyStudentAndCourseView societyStudentAndCourseView = new SocietyStudentAndCourseView();
		societyStudentAndCourseView.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseView.setSex(societyStudent.getStudentSex());
		societyStudentAndCourseView.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseView.setStudentPohone(societyStudent.getStudentPhoneNum());
		societyStudentAndCourseView.setRegisterTime(societyStudent.getCreateTime());
		societyStudentAndCourseView.setFinishNodeNum(societyStudentAndCourse.getFinishNodeNum());
		societyStudentAndCourseView.setClassName(societySchoolClass.getClassName());
		societyStudentAndCourseView.setClassCode(societySchoolClass.getClassCode());
		societyStudentAndCourseView.setClassTimePeriod(getDatePeriod(societySchoolClass.getClassStartTime(),
				societySchoolClass.getClassEndTime()));
		if(societyStudentAndCourse.getLearnFinishTime()==null){
			SocietyStudentAndNode societyStudentAndNode =
					societyStudentAndNodeService.selectByStuCouIdNewOnce(stuAndCouId);
			societyStudentAndCourseView.setStudyTimePeriod(getDatePeriod(societyStudentAndCourse.getCreateTime(),
					societyStudentAndNode.getFinishTime()));
		}else {
			societyStudentAndCourseView.setStudyTimePeriod(getDatePeriod(societyStudentAndCourse.getCreateTime(),
					societyStudentAndCourse.getLearnFinishTime()));
		}
		societyStudentAndCourseView.setStudyMode("网站");
		societyStudentAndCourseView.setCourseForm("录播");
		societyStudentAndCourseView.setFacePhoto(societyStudent.getFaceImageUrl());
		map.put("second",societyStudentAndCourseView);
		//第三页 学习记录
		SocietyStudentAndCourseView societyStudentAndCourseViewSecond = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewSecond.setCourseName(societySchoolCourse.getCourseName());
		societyStudentAndCourseViewSecond.setTotalLessons(societySchoolCourse.getTotalLessons());
		societyStudentAndCourseViewSecond.setFinishNodeNum(societyStudentAndCourse.getFinishNodeNum());
		societyStudentAndCourseViewSecond.setLearnIsFinished("完成");
		SocietyStudentTestPaper societyStudentTestPaper =
				societyStudentTestPaperService.selectByStuIdAndCourseIdAndSchoolIdOnce(courseId,studentId,schoolId);
		if(societyStudentTestPaper!=null){
			societyStudentAndCourseViewSecond.setTestRightRate(societyStudentTestPaper.getTestRightRate());
			societyStudentAndCourseViewSecond.setTestScore(societyStudentTestPaper.getGetScore());
			societyStudentAndCourseViewSecond.setTestIsPass(societyStudentTestPaper.getIsPass());
			societyStudentAndCourseViewSecond.setTestName(societyStudentTestPaper.getTestPaperName());
		}
		List<SocietyStudentStudyProcessFace> faceAllList =
				societyStudentStudyProcessFaceService.selectByStuCouId(stuAndCouId);
		societyStudentAndCourseViewSecond.setFaceAuthentication("共进行"+faceAllList.size()+"次人脸认证，成功"+faceAllList.size()+"次，失败0次。");

		Random random = new Random();
		List<SocietyStudentStudyProcessFace> faceRandomList = new ArrayList<>();
		for (int i = 0;i<5;i++){
			int n = random.nextInt(faceAllList.size());

			faceRandomList.add(faceAllList.get(n));
		}
		societyStudentAndCourseViewSecond.setFaceList(faceRandomList);
		SocietyStudentCertificateView societyStudentCertificateView
				= societyStudentCertificateService.selectByStuIdAndCourId(studentId,courseId);
		societyStudentAndCourseViewSecond.setStudyProve(societyStudentCertificateView.getCertificateCode());
		map.put("third",societyStudentAndCourseViewSecond);
		//第四页 平台证明
		SocietyStudentAndCourseView societyStudentAndCourseViewThird = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewThird.setStudyProve(societyStudentCertificateView.getCertificateCode());
		societyStudentAndCourseViewThird.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseViewThird.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseViewThird.setSchoolName(societySchool.getSchoolName());
		societyStudentAndCourseViewThird.setClassName(societySchoolClass.getClassName());
		societyStudentAndCourseViewThird.setClassTimePeriod(getDatePeriodSecond(societySchoolClass.getClassStartTime(),
				societySchoolClass.getClassEndTime()));
		societyStudentAndCourseViewThird.setCourseName(societySchoolCourse.getCourseName());
		Integer studyLength = societyStudentAndNodeService.countStudyLength(stuAndCouId)/60;
		Integer questionLength = societyStudentPractiseQuestionService.countQuestionLength(stuAndCouId)/60;
		societyStudentAndCourseViewThird.setStudyTimeLength(studyLength);
		societyStudentAndCourseViewThird.setQuestionTimeLength(questionLength);
		societyStudentAndCourseViewThird.setTotalTimeLength(studyLength+questionLength);
		societyStudentAndCourseViewThird.setCertificateTime(societyStudentCertificateView.getCertificateTime());
		map.put("fourth",societyStudentAndCourseViewThird);

		//第五页 章节
		SocietyStudentAndCourseView societyStudentAndCourseViewFourth = new SocietyStudentAndCourseView();
		societyStudentAndCourseViewFourth.setStudentName(societyStudent.getStudentName());
		societyStudentAndCourseViewFourth.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		societyStudentAndCourseViewFourth.setCourseName(societySchoolCourse.getCourseName());
		List<SocietyStudentAndNodeView> nodeList = societyStudentAndNodeService.selectByStuAndCouId(stuAndCouId);
		societyStudentAndCourseViewFourth.setNodeList(nodeList);
		if(societySchoolCourse.getTeacherName()!=null || !societySchoolCourse.getTeacherName().equals("")){
			societyStudentAndCourseViewFourth.setTeacherName(societySchoolCourse.getTeacherName());
		}
		map.put("fifth",societyStudentAndCourseViewFourth);

		return buildResponse(modelAndView,map);
	}

	public String getDatePeriod(Date start,Date end){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startStr = simpleDateFormat.format(start);
		String endStr = simpleDateFormat.format(end);
		return startStr+"/"+endStr;
	}

	public String getDatePeriodSecond(Date start,Date end){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		String startStr = simpleDateFormat.format(start);
		String endStr = simpleDateFormat.format(end);
		return startStr+"&nbsp;至&nbsp;"+endStr;
	}


}
