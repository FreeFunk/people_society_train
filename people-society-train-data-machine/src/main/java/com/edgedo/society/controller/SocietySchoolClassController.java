package com.edgedo.society.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.mapper.SocietyStudentAndCourseMapper;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import com.edgedo.sys.entity.Dtree;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SocietySchoolClass")
@Controller
@RequestMapping("/society/societySchoolClass")
public class SocietySchoolClassController extends BaseController{
	
	@Autowired
	private SocietySchoolClassService service;
	@Autowired
	private SocietySchoolMajorService schoolMajorService;
	@Autowired
	private SocietySchoolClassAndStudentService classAndStudentService;
	@Autowired
	private SocietyStudentService studentService;
/*	@Autowired
	private SocietySchoolService societySchoolService;*/
	@Autowired
	private SocietySchoolClassAndCourseService societySchoolClassAndCourseService;
	@Autowired
	private SocietySchoolClassAdminService societySchoolClassAdminService;
	@Autowired
	private SocietySchoolMajorService societySchoolMajorService;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietyStudentService societyStudentService;
	@Autowired
	private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolCourseNodeMapper courseNodeMapper;
	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;
	@Autowired
	private SocietyStudentAndCourseMapper societyStudentAndCourseMapper;
	@Autowired
	private SocietySchoolClassGroupAdminService societySchoolClassGroupAdminService;
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolClass", notes = "分页查询SocietySchoolClass")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
    /**
     * 班级统计分页查询
     * @param query
     * @return
     */
    @ApiOperation(value = "班级统计分页查询SocietySchoolClass", notes = "班级统计分页查询SocietySchoolClass")
    @ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
    @RequestMapping(value = "/listpageforcount",method = RequestMethod.POST)
    public ModelAndView listpageforcount(@ModelAttribute SocietySchoolClassQuery query){
        ModelAndView modelAndView = new ModelAndView();
        User user = getLoginUser();
        String schoolId = user.getCompId();
        query.getQueryObj().setOwnerSchoolId(schoolId);
        if(query.getOrderBy() == null || "".equals(query.getOrderBy())){
			query.setOrderBy("CREATE_TIME ASC");
		}
        service.listPage(query);
        buildResponse(modelAndView,query);
        return modelAndView;
    }

	/**
	 * 监管账号查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageSupforcount",method = RequestMethod.POST)
	public ModelAndView listpageSupforcount(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		SysUser sysUser = sysUserService.loadById(getLoginUser().getUserId());
		query.getQueryObj().setXianquId(sysUser.getXianquId());
		if(query.getOrderBy() == null || "".equals(query.getOrderBy())){
			query.setOrderBy("CREATE_TIME ASC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 查出班主任关联的班级
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageforclasscount",method = RequestMethod.POST)
	public ModelAndView listpageforclasscount(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(user.getUserId());
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		query.getQueryObj().setClassAdminId(classAdminId);
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 查出班主任组长关联的班级
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageforclassGroupcount",method = RequestMethod.POST)
	public ModelAndView listpageforclassGroupcount(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String sysUserId = user.getUserId();//组长id
		String classGroupAdminId = societySchoolClassGroupAdminService.selectBySysUserId(sysUserId);
		List<String> classAdminIdList = societySchoolClassAdminService.selectByGroupId(classGroupAdminId);
		query.getQueryObj().setOwnerSchoolId(user.getCompId());
		query.getQueryObj().setClassAdminIdList(classAdminIdList);
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 班级统计不分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "班级统计不分页查询SocietySchoolClass", notes = "班级统计不分页查询SocietySchoolClass")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listByObjforcount",method = RequestMethod.POST)
	public ModelAndView listByObjforcount(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.listByObj(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SocietySchoolClass", notes = "分页查询SocietySchoolClass")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/schoolListpage",method = RequestMethod.POST)
	public ModelAndView schoolListpage(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		if(query.getOrderBy()==null || "".equals(query.getOrderBy())){
			query.setOrderBy("CREATE_TIME ASC");
		}
		query.getQueryObj().setOwnerSchoolId(schoolId);
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 班主任查询班级
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/schoolClassAdminListpage",method = RequestMethod.POST)
	public ModelAndView schoolClassAdminListpage(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		String sysUserId = user.getUserId();//班主任id
		String classAdminId = societySchoolClassAdminService.selectBySysUserId(sysUserId);
		query.getQueryObj().setOwnerSchoolId(schoolId);
		query.getQueryObj().setClassAdminId(classAdminId);
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 班主任组长查询班级
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/schoolClassGroupAdminListpage",method = RequestMethod.POST)
	public ModelAndView schoolClassGroupAdminListpage(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		String sysUserId = user.getUserId();//组长id
		String classGroupAdminId = societySchoolClassGroupAdminService.selectBySysUserId(sysUserId);
		List<String> classAdminIdList = societySchoolClassAdminService.selectByGroupId(classGroupAdminId);
		query.getQueryObj().setOwnerSchoolId(schoolId);
		query.getQueryObj().setClassAdminIdList(classAdminIdList);
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/schoolAdminExamineClassListpage",method = RequestMethod.POST)
	public ModelAndView schoolAdminExamineClassListpage(@ModelAttribute SocietySchoolClassQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.examineClasslistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/classList",method = RequestMethod.POST)
	public ModelAndView classList(){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietySchoolClass> list = service.selectByClassList();
		buildResponse(modelAndView,list);
		return modelAndView;
	}
	/**
	 * 获取当前学校的学校名字前缀
	 * @return
	 */
	@RequestMapping(value = "/getClassCode",method = RequestMethod.POST)
	public ModelAndView getClassCode(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		SocietySchool societySchool = societySchoolService.loadById(schoolId);
		String classCode = societySchool.getSchoolNamePrefix()+getDateStr(new Date());
		//返回今天的所有班级编码
		List<String> list = service.selectByDateStr(classCode);
		if(list.size()==0){//证明今天没有加班级
			classCode = classCode+"001";
		}else {
			Integer num = list.size()+1;
			if(num<9){//10个班级以内
				String code = "00"+String.valueOf(num);
				classCode = classCode+code;
			}else if(num>=9 && num <99){//10-99
				String code = "0"+String.valueOf(num);
				classCode = classCode+code;
			}else if(num>=99){//100-999
				String code = String.valueOf(num);
				classCode = classCode+code;
			}
		}
		return buildResponse(modelAndView,classCode);
	}


	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolClass", notes = "新增修改SocietySchoolClass")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(SocietySchoolClass voObj){
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
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改SocietySchoolClass", notes = "新增修改SocietySchoolClass")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdateForSchool",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateForSchool(SocietySchoolClassView voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String msg = "";
		String id = voObj.getId();
		String classIdAndmajorId = "";
		User user = getLoginUser();
		if(voObj.getPersonInCharge() != null && !voObj.getPersonInCharge().equals("")){
			SocietySchoolClassAdmin societySchoolClassAdmin =
					societySchoolClassAdminService.loadById(voObj.getPersonInCharge());
			voObj.setPersonInCharge(societySchoolClassAdmin.getClassAdminName());//班主任名字
			voObj.setPersonInChargePhoneNum(societySchoolClassAdmin.getClassAdminPhone());//电话好
			voObj.setClassAdminId(societySchoolClassAdmin.getId());//id
		}
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			//设置位置信息
			Map<String,String> addressMap = getAddressInfo(voObj.getXianquName());
			voObj.setProvinceId(addressMap.get("proviceId"));
			voObj.setProvinceName(addressMap.get("proviceName"));
			voObj.setCityId(addressMap.get("cityId"));
			voObj.setCityName(addressMap.get("cityName"));
			voObj.setXianquId(addressMap.get("xianquNameId"));
			voObj.setXianquName(addressMap.get("xianquName"));

			String majorId = voObj.getOwnerMajorId();
			SocietySchoolMajor major = schoolMajorService.loadById(majorId);
			SocietySchool societySchool = societySchoolService.loadById(user.getCompId());
			if(major != null){
				voObj.setOwnerMajorName(major.getMajorName());
				voObj.setOwnerSchoolId(societySchool.getId());
				voObj.setOwnerSchoolName(societySchool.getSchoolName());
			}else{
				return buildErrorResponse(modelAndView,"未找到专业信息！");
			}
			voObj.setTotalLessons(0);
			voObj.setCourseTimeLength(0);
			voObj.setClassPersonNum(0);
			voObj.setFinishedPersonNum(0);
			voObj.setNotFinishedPersonNum(0);
			voObj.setId(Guid.guid());
			voObj.setDataState("1");
			errMsg = service.insert(voObj);
			classIdAndmajorId = voObj.getId()+","+majorId+","+voObj.getOwnerSchoolId();
			if(!voObj.getCourseId().equals("")){
				msg = societySchoolClassAndCourseService.insertClassAndCourseRelation(voObj.getId(),voObj.getCourseId());
			}
		}else{
			if(!"".equals(voObj.getXianquName())){
				//设置位置信息
				Map<String,String> addressMap = getAddressInfo(voObj.getXianquName());
				voObj.setProvinceId(addressMap.get("proviceId"));
				voObj.setProvinceName(addressMap.get("proviceName"));
				voObj.setCityId(addressMap.get("cityId"));
				voObj.setCityName(addressMap.get("cityName"));
				voObj.setXianquId(addressMap.get("xianquNameId"));
				voObj.setXianquName(addressMap.get("xianquName"));
			}
			errMsg = service.update(voObj);
			if(voObj.getCourseId()!=null && !voObj.getCourseId().equals("")){
				//一个班级只能关联一个课程 所以班级课程关联表班级id 唯一
				SocietySchoolClassAndCourseView societySchoolClassAndCourseView
						= societySchoolClassAndCourseService.selectClassByOne(voObj.getId());
				//证明已经关联课程
				if(societySchoolClassAndCourseView!=null){
					//判断修改动作是否影响了数据库中的课程
					if(!societySchoolClassAndCourseView.getCourseId().equals(voObj.getCourseId())){
						//先删除相关课程班级关联
						societySchoolClassAndCourseService.deleteById(societySchoolClassAndCourseView.getId());
						msg = societySchoolClassAndCourseService.insertClassAndCourseRelation(voObj.getId(),voObj.getCourseId());
					}
				}else {
					voObj.setOwnerSchoolId(user.getCompId());
					societySchoolClassAndCourseService.insertNew(voObj);
				}
			}
			classIdAndmajorId = voObj.getId()+","+voObj.getOwnerMajorId()+","+voObj.getOwnerSchoolId();
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else if(msg!=null && !msg.equals("")){
			buildErrorResponse(modelAndView, msg);
		}else{
			buildResponse(modelAndView,classIdAndmajorId);
		}
		return modelAndView;
	}

	public String getDateStr(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(date);
	}

	/**
	 * 整理县区位置信息
	 * @param xianquIdAndxianquName 县区名+id(逗号分隔) 12313,海港区
	 * @return
	 */
	private Map<String, String> getAddressInfo(String xianquIdAndxianquName) {
		Map<String,String> addressMap = new HashMap<>();
		//省
		addressMap.put("proviceId","HEBEI");
		addressMap.put("proviceName","河北省");
		//市
		addressMap.put("cityId","130300");
		addressMap.put("cityName","秦皇岛市");
		//县区
		if(xianquIdAndxianquName != null && !xianquIdAndxianquName.equals("")){
			String[] xianquNameArr = xianquIdAndxianquName.split(",");
			addressMap.put("xianquNameId",xianquNameArr[0]);
			addressMap.put("xianquName",xianquNameArr[1]);
		}else {
			addressMap.put("","");
			addressMap.put("","");
		}
		return addressMap;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除SocietySchoolClass", notes = "根据ID批量删除SocietySchoolClass")
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
	@ApiOperation(value = "根据ID加载SocietySchoolClass", notes = "根据ID加载SocietySchoolClass")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 学校提交开班审核
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/updateExamineState",method = RequestMethod.POST)
	public ModelAndView  updateExamineState(String classId){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String schoolId = user.getCompId();
		service.updateExamineState(classId,schoolId);
		return buildResponse(modelAndView);
	}

	/**
	 * 管理审核班级
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/updateAdminClassState",method = RequestMethod.POST)
	public ModelAndView  updateAdminClassState(String classId,String examineState){
		ModelAndView modelAndView = new ModelAndView();
		service.updateAdminExamineState(classId,examineState);
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description 逻辑删除
	 * @Date 2020/5/4 14:01
	 **/
	@RequestMapping(value = "/logicDeleteByIds",method = RequestMethod.POST)
	public ModelAndView logicDelete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			//判断下是否班级下面还有正在学习的学员
			int countStudent = classAndStudentService.countByClassAndLearnNotFinished(str);
			if(countStudent == 0){
				list.add(str);
			}else{
				return buildErrorResponse(modelAndView,"请先删除班级下的所有学员！");
			}
		}
		service.logicDelete(list);
		return buildResponse(modelAndView);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据专业信息查询所有的班级
	 * @Date 2020/5/4 14:01
	 **/
	@RequestMapping(value = "/listAllByMajorId",method = RequestMethod.POST)
	public ModelAndView listAllByMajorId(String majorId){
		ModelAndView modelAndView = new ModelAndView();
		List<SocietySchoolClassView> schoolClassList = service.listAllByMajorId(majorId);
		return buildResponse(modelAndView, schoolClassList);
	}


	/***
	 * 根据学校id查出对应的班级组成树
	 * @param schoolId
	 * @return
	 */
	@RequestMapping(value = "/listSchoolClassDtree",method = RequestMethod.POST)
	public ModelAndView listSchoolClassDtree(String schoolId){
		ModelAndView modelAndView = new ModelAndView();
		List<Dtree> dtreeList = service.listSchoolClassDtree(schoolId);
		return buildResponse(modelAndView,dtreeList);
	}


	/**
	 * 县区监管帐号 根据班级id 查询所有的详细班级信息
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/classTrainCheckPDFInfo",method = RequestMethod.POST)
	public ModelAndView classTrainCheckPDFInfo(String classId){
		Map<String,Object> map = new HashMap<>();
		ModelAndView modelAndView = new ModelAndView();
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(classId);
		SocietySchool societySchool = societySchoolService.loadById(societySchoolClass.getOwnerSchoolId());
		String courseId = societySchoolClassAndCourseService.selectByClassId(classId);
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		SocietySchoolClassView societySchoolClassView = new SocietySchoolClassView();
//		学校名字
		societySchoolClassView.setOwnerSchoolName(societySchool.getSchoolName());
//				班级编码
		societySchoolClassView.setClassCode(societySchoolClass.getClassCode());
//		班级名称
		societySchoolClassView.setClassName(societySchoolClass.getClassName());
//		班级状态 根据开始时间和结束
		societySchoolClassView.setClassState(belongCalendar(new Date(),
				societySchoolClass.getClassStartTime(),societySchoolClass.getClassEndTime()));
		//		班级人数
		Integer classPersonNum = societySchoolClassAndStudentService.selectVoClassId(classId);
		societySchoolClassView.setClassPersonNum(classPersonNum);
//		班级进度学习进度 学完人数/总人数
		societySchoolClassView.setClassStudyProgree(getClassStudyPro(classId,classPersonNum));
//		培训平台 人社培训平台
		societySchoolClassView.setTrainShow("人社培训平台");
		//培训类型
		societySchoolClassView.setTrainType("职业技能,技能培训");
//		课程名称
		societySchoolClassView.setCourseName(societySchoolCourse.getCourseName());
//				总课时
		societySchoolClassView.setCourseTotalNum(societySchoolCourse.getTotalLessons());
//		班级开始 结束时间
		societySchoolClassView.setClassStartTime(societySchoolClass.getClassStartTime());
		societySchoolClassView.setClassEndTime(societySchoolClass.getClassEndTime());
		//抽查方式
		societySchoolClassView.setCheckType("线上抽查");
//		抽查时间
		societySchoolClassView.setCheckTime(new Date());
		//学员抽查数量
		societySchoolClassView.setCheckStudentNum("5");
//				学生记录list
		List<String> studentIdList = societySchoolClassAndStudentService.selectByRandStuId(classId);
		List<SocietyStudentView> studentList = new ArrayList<>();
		for(String stuId : studentIdList){
			SocietyStudent societyStudent = societyStudentService.loadById(stuId);
			SocietyStudentView societyStudentView = new SocietyStudentView();
			societyStudentView.setStudentName(societyStudent.getStudentName());
			societyStudentView.setStudentSex(societyStudent.getStudentSex());
			societyStudentView.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
			societyStudentView.setStudentPhoneNum(societyStudent.getStudentPhoneNum());
			SocietyStudentAndCourse societyStudentAndCourse =
					societyStudentAndCourseMapper.selectByStuIdAndCourseId(stuId,courseId);
			societyStudentView.setStudyPro(societyStudentAndCourse.getCourseProgress()+"%");
			studentList.add(societyStudentView);
		}
		societySchoolClassView.setStudentList(studentList);
		map.put("first",societySchoolClassView);
		return buildResponse(modelAndView,map);
	}

	public String getClassStudyPro(String classId,Integer classPersonNum){
		//查出所有完成学习的学生人数
		Integer finishNum = societyStudentAndCourseMapper.selectByClassIdAndStudyInfo(classId);
		if(classPersonNum==0){
			return "0%";
		}else {
			double f1 = new BigDecimal((float)finishNum/classPersonNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1+"%";
		}
	}

	/**
	 * 根据班级id 联合查出所有的相关信息
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/classDetailedInfoShow",method = RequestMethod.POST)
	public ModelAndView classDetailedInfoShow(String classId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		SocietySchoolClass societySchoolClass = service.loadById(classId);
		SocietySchool societySchool = societySchoolService.loadById(societySchoolClass.getOwnerSchoolId());
		String courseId = societySchoolClassAndCourseService.selectByClassId(classId);
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);

//		第一页： 学校名字 班级编码	 时间
		SocietySchoolClassAndStudentView societySchoolClassAndStudentViewFisrt = new SocietySchoolClassAndStudentView();
		societySchoolClassAndStudentViewFisrt.setOwnerSchoolName(societySchool.getCityName()+societySchool.getXianquName()+societySchool.getSchoolName());
		societySchoolClassAndStudentViewFisrt.setClassCode(societySchoolClass.getClassCode());
		societySchoolClassAndStudentViewFisrt.setCreateFileTime(getStringDate(new Date()));
		societySchoolClassAndStudentViewFisrt.setPlatformName(societySchool.getSchoolName());
		map.put("first",societySchoolClassAndStudentViewFisrt);
		//		第二页： 学校名字 班级编码   专业名 课程名称 课程课时 班级人数 班级培训起止
		SocietySchoolClassAndStudentView societySchoolClassAndStudentViewSecond = new SocietySchoolClassAndStudentView();
		societySchoolClassAndStudentViewSecond.setOwnerSchoolName(societySchool.getCityName()+societySchool.getXianquName()+societySchool.getSchoolName());
		societySchoolClassAndStudentViewSecond.setClassCode(societySchoolClass.getClassCode());
		societySchoolClassAndStudentViewSecond.setPlatformName(societySchool.getPlatformName());
		societySchoolClassAndStudentViewSecond.setOwnerMajorName(societySchoolCourse.getOwnerMajorName());
		societySchoolClassAndStudentViewSecond.setCourseName(societySchoolCourse.getCourseName());
		societySchoolClassAndStudentViewSecond.setCourseLength(societySchoolCourse.getTotalLessons());
		societySchoolClassAndStudentViewSecond.setClassTotalPerson(societySchoolClass.getClassPersonNum());
		societySchoolClassAndStudentViewSecond.setClassStartTimeStr(getTimeStr(societySchoolClass.getClassStartTime()));
		societySchoolClassAndStudentViewSecond.setClassEndTimeStr(getTimeStr(societySchoolClass.getClassEndTime()));
		societySchoolClassAndStudentViewSecond.setQueryTime(belongCalendar(new Date(),
				societySchoolClass.getClassStartTime(),societySchoolClass.getClassEndTime()));
		societySchoolClassAndStudentViewSecond.setQueryMode("线上抽查");
		societySchoolClassAndStudentViewSecond.setCreateFileTime(getTimeStr(new Date()));
		Integer classActPerson = societySchoolClassAndStudentService.selectCountByClassId(classId);
		societySchoolClassAndStudentViewSecond.setActualPerson(classActPerson);
		societySchoolClassAndStudentViewSecond.setClassProgre(getClassProgre(classId,classActPerson));
		map.put("second",societySchoolClassAndStudentViewSecond);
		//		第三页：学生信息
		SocietySchoolClassAndStudentView societySchoolClassAndStudentViewThird = new SocietySchoolClassAndStudentView();
		List<SocietySchoolClassAndStudentView> list = societySchoolClassAndStudentService.selectVoByClassId(classId);
		for(SocietySchoolClassAndStudentView societySchoolClassAndStudentView : list){
			SocietyStudent societyStudent = societyStudentService.loadById(societySchoolClassAndStudentView.getStudentId());
			societySchoolClassAndStudentView.setSex(societyStudent.getStudentSex());
			societySchoolClassAndStudentView.setStudentPhone(societyStudent.getStudentPhoneNum());
			societySchoolClassAndStudentView.setStudentType(getPersonType(societyStudent.getPeopleType()));
			societySchoolClassAndStudentView.setCefiType("无");
			societySchoolClassAndStudentView.setStudyInfo(getStudyInfo(societyStudent.getId(),societySchoolCourse.getTotalLessons()));
		}
		societySchoolClassAndStudentViewThird.setStuClassList(list);
		map.put("third",societySchoolClassAndStudentViewThird);
		//		第四页：课程章节
		SocietySchoolClassAndStudentView societySchoolClassAndStudentViewFourth = new SocietySchoolClassAndStudentView();
		societySchoolClassAndStudentViewFourth.setNodeList(courseNodeMapper.selectByCourseId(courseId));
		societySchoolClassAndStudentViewFourth.setCourseName(societySchoolCourse.getCourseName());
		map.put("fourth",societySchoolClassAndStudentViewFourth);
 		return buildResponse(modelAndView,map);
	}

	private String getStudyInfo(String studentId, Integer totalLessons) {
		Integer num = studentAndNodeService.selectByStudentId(studentId);
		return num+"/"+totalLessons;
	}

	public String getStringDate(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		return simpleDateFormat.format(date);
	}

	public String getTimeStr(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	public String getPersonType(String studentType){
		if("IMP_STU".equals(studentType)){
			return "重点学员";
		}else if("COMP_STU".equals(studentType)){
			return "企业学员";
		}else {
			return "普通学员";
		}
	}

	/**
	 * Description: 判断一个时间是否在一个时间段内 </br>
	 *
	 * @param nowTime 当前时间 </br>
	 * @param beginTime 开始时间 </br>
	 * @param endTime 结束时间 </br>
	 */
	private String belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if(beginTime.getTime() > nowTime.getTime()){
			return "教学未开始";
		}else if(date.after(begin) && date.before(end)){
			return "教学期间";
		}else {
			return "教学结束";
		}

	}

	public String getClassProgre(String classId,Integer classActPerson){
		if(classActPerson==0){
			return "0%&nbsp;"+"/0人";
		}else{
			Integer studyFinsih = societyStudentAndCourseMapper.selectByClassIdAndStudyInfo(classId);
			double f1 = new BigDecimal((float)studyFinsih/classActPerson).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1+"%&nbsp;"+"/"+studyFinsih+"人";
		}
	}

}
