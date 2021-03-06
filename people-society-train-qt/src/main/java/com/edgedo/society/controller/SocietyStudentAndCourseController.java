package com.edgedo.society.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.BusinessException;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.dataenum.SchoolConfigKeyEnum;
import com.edgedo.face.entity.FaceMatchInfoExt;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.entity.*;
import com.edgedo.society.exception.StudentNoLearnFaceException;
import com.edgedo.society.queryvo.*;
import com.edgedo.society.service.*;
import freemarker.template.SimpleDate;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.jetbrains.annotations.ApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "SocietyStudentAndCourse")
@Controller
@RequestMapping("/stuCourse")
public class SocietyStudentAndCourseController extends BaseController{
	@Autowired
	RedisUtil redisUtil;
	//是否启用人脸识别
	@Value("${app.studyfacecheck}")
	private boolean studyfacecheck;

	@Autowired
	private SocietyStudentAndCourseService service;
	@Autowired
	private SocietyStudentService societyStudentService;
	@Autowired
	private SocietyStudentUniqueService studentUniqueService;
	@Autowired
	private SocietySchoolCourseService schoolCourseService;
	@Autowired
	private SocietySchoolCourseNodeService schoolCourseNodeService;
	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;
	@Autowired
	private SocietyStudentStudyProcessService studyProcessService;
	@Autowired
	private  SocietyStudentStudyProcessFaceService processFaceService;
	@Autowired
	private SocietyStudentTestPaperService testPaperService;
	@Autowired
    private SocietySchoolClassAndCourseService classAndCourseService;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietySchoolConfigService societySchoolConfigService;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;
	/**
	 * 学时证明
	 * @param stuAndCouId
	 * @return
	 */

	@RequestMapping(value = "/studentProInfo")
	@Pass
	public ModelAndView studentProInfo(String stuAndCouId){
		ModelAndView modelAndView = new ModelAndView();
		//课程学员关联8
		SocietyStudentAndCourse societyStudentAndCourse = service.loadById(stuAndCouId);
		String studentId = societyStudentAndCourse.getStudentId();
		String courseId = societyStudentAndCourse.getCourseId();
		//学生对象
		SocietyStudent societyStudent = societyStudentService.loadById(societyStudentAndCourse.getStudentId());
		//学校对象
		SocietySchool societySchool = societySchoolService.loadById(societyStudentAndCourse.getOwnerSchoolId());
		//课程对象
		SocietySchoolCourse societySchoolCourse = schoolCourseService.loadById(societyStudentAndCourse.getCourseId());
		//班级对象
		SocietySchoolClass societySchoolClass = societySchoolClassService.loadById(societyStudentAndCourse.getClassId());

		SocietyStudentCertificate societyStudentCertificate
				= societyStudentCertificateService.selectCertByStuAndCourse(studentId,courseId);
		//第四页 平台证明
		SocietyStudentAndCourseView societyStudentAndCourseView = new SocietyStudentAndCourseView();
		//姓名
		societyStudentAndCourseView.setStudentName(societyStudent.getStudentName());
		//身份正好
		societyStudentAndCourseView.setStudentIdCardNum(societyStudent.getStudentIdCardNum());
		//培训机构
		societyStudentAndCourseView.setOwnerSchoolName(societySchool.getSchoolName());
		//培训课程
		societyStudentAndCourseView.setCourseName(societySchoolCourse.getCourseName());
		//培训时间
		societyStudentAndCourseView.setStudyTime(getDatePeriodSecond(societySchoolClass.getClassStartTime(),societySchoolClass.getClassEndTime()));
		//完成学时
		societyStudentAndCourseView.setFinishNodeNum(societyStudentAndCourse.getFinishNodeNum());
		//培训平台
		societyStudentAndCourseView.setPlatformName(societySchool.getPlatformName());
		//学时证明编号
		societyStudentAndCourseView.setCenfiCode(societyStudentCertificate.getCertificateCode());
		return buildResponse(modelAndView,societyStudentAndCourseView);
	}

	public String getDatePeriodSecond(Date start,Date end){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startStr = simpleDateFormat.format(start);
		String endStr = simpleDateFormat.format(end);
		return startStr+" 至 "+endStr;
	}

	/**
	 * @Author WangZhen
	 * @Description  学员前端根据自己的课程信息获得课程信息
	 * @Date 2020/5/4 10:20
	 **/
	@RequestMapping("/loadCourse")
	public ModelAndView loadCourse(String stuCourseId) {
		//获得当前登录学员
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		ModelAndView modelAndView = new ModelAndView();
		String idCardNum = student.getId();
		SocietyStudentAndCourseView stuCourse = service.loadByIdAndStuIdCardNum(stuCourseId,idCardNum);
		if(stuCourse==null){
			throw new BusinessException("未找到课程!");
		}
		//查询课程详情
		SocietySchoolCourse schoolCourse = schoolCourseService.loadById(stuCourse.getCourseId());
		if(schoolCourse==null){
			throw new BusinessException("未找到课程!");
		}
		//查询课程是否停课
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(stuCourse.getClassId());
		if("1".equals(societySchoolClass.getClassStudyState())){
			throw new BusinessException("该课程已经停课，请退出当前页面");
		}else {
			Date end = societySchoolClass.getClassEndTime();
			Date start = societySchoolClass.getClassStartTime();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(end);
			calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
			end = calendar.getTime();
			calendar.setTime(start);
			start = calendar.getTime();
			Long timeStrPro = end.getTime() - new Date().getTime();
			Long timeStartStrPro = start.getTime() - new Date().getTime();
			if (timeStrPro<0){
				throw new BusinessException("已经超过了学习时间，请退出当前页面");
			}else if (timeStartStrPro>0){
				throw new BusinessException("课程未到学习时间，请退出当前页面");
			}
		}
		String schoolId = stuCourse.getOwnerSchoolId();
		String IN_STUDY_NEED_FACE = societySchoolConfigService.loadKeyValue(schoolId, SchoolConfigKeyEnum.IN_STUDY_NEED_FACE);
		if(IN_STUDY_NEED_FACE==null || IN_STUDY_NEED_FACE.trim().equals("")){
			//程序默认是必须的
			IN_STUDY_NEED_FACE = "1";
		}
		if(IN_STUDY_NEED_FACE.equals("1")){
			//清理一下学员的人脸识别
			societyStudentService.clearStudentFaceCatch(student);
		}
		List<SocietySchoolCourseNodeView>  courseNodes = schoolCourseNodeService.selectCousrseNodesByCourseIdSimple(stuCourse.getCourseId());
		List<SocietyStudentAndNodeView> stuNodes = studentAndNodeService.selectNodeHisByOwnerCorse(stuCourse.getId());
		stuCourse.setStuNodes(stuNodes);
		stuCourse.setCourseNodes(courseNodes);
		stuCourse.setCourseDesc(schoolCourse.getCourseDesc());
		stuCourse.setCourseImage(schoolCourse.getCourseImage());
		SocietyStudent societyStudent = societyStudentService.loadById(stuCourse.getStudentId());
		stuCourse.setPhoneTypeFlag(societyStudent.getPhoneTypeFlag());
		//兼容名字改动
//		stuCourse.setCourseName(schoolCourse.getCourseName());
		return buildResponse(modelAndView,stuCourse);
	}



	/**
	 * @Author WangZhen
	 * @Description 返回node信息
	 * @Date 2020/7/11 8:14
	 **/
	@RequestMapping("/freshLearnNode_v1")
	public ModelAndView freshLearnNode_v1(
			String courseNodeId,
			String stuCourseId,boolean isBegin,
			Integer lastLearnLocation) {

		//获得当前登录学员
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		FaceMatchInfoExt faceMatchInfo =null;

		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentAndCourseView stuCourse = service.loadByIdAndStuIdCardNum(stuCourseId,student.getId());
		if(stuCourse == null){
			throw new BusinessException("未找到课程!");
		}
		String key = RedisKeyConstant.CURRENT_STU_STUDY_NODE + student.getStudentIdCardNum();
		if(isBegin){//如果是isBegin切换当前正在学习的课程
			//存1天吧
			redisUtil.set(key,courseNodeId,86400000);
//			throw new StudentNoLearnFaceException();
		}else{
			String temCourseNodeId = (String)redisUtil.get(key);
			if(temCourseNodeId==null){ //如果缓存中没有那么补充上去
				//存1天吧
				redisUtil.set(key,courseNodeId,86400000);
			}else{
				//判断是否相同
				if(!temCourseNodeId.equals(courseNodeId)){
					BusinessException notSameNodeExc = new BusinessException("系统检测到您正在同时学习多个章节！");
					notSameNodeExc.setErrCode("cur_node_num_overflow");
					throw notSameNodeExc;
				}
			}
		}

		SocietyStudent schStu =  societyStudentService.loadById(stuCourse.getStudentId());
		SocietyStudentAndNode studentAndNode = studentAndNodeService
				.selectStuCourseNodeByStuCourseIdAndCourseNodeId(
						stuCourse.getId(),courseNodeId
				);
		if(studentAndNode==null){
			//插入一条新的
			studentAndNode = studentAndNodeService.insertByStuAndStuCourse(schStu,stuCourse,courseNodeId);
		}

		String learnIsFinished = studentAndNode.getLearnIsFinished();
		if(learnIsFinished==null || !learnIsFinished.equals("1")){
			//判断课程学习数量的限制是否达到  --同理调用结束的那个段代码
			checkLearnNumOfCurrentDay(stuCourse,courseNodeId);
		}


		//默认就是允许
		String isNeedFace =  stuCourse.getCourseIsNeedFaceContrast();
		if(isNeedFace==null || isNeedFace.equals("") || isNeedFace.equals("1")){
			//判断课程是否需要人脸识别 --同理调用结束的那个段代码
			if(studyfacecheck){
				faceMatchInfo  = societyStudentService.checkLearnFace(student);
			}
		}
		//如果没有插入一条新的学习过程记录
		SocietyStudentAndNode stuNode = studyProcessService.freshProgress(
				schStu,student,stuCourse,courseNodeId,
				faceMatchInfo,isBegin,lastLearnLocation,studentAndNode);
		return buildResponse(modelAndView,stuNode);
	}

	/**
	 * @Author WangZhen
	 * @Description 获得课程当日完成数量
	 * @Date 2020/7/27 16:23
	 **/
	private void checkLearnNumOfCurrentDay(SocietyStudentAndCourseView stuCourse,String courseNodeId){
		Integer studyTimeLimit = stuCourse.getCourseStudyNum();
		if(studyTimeLimit==null){
			studyTimeLimit = 4;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dayStr = sdf.format(new Date());
		String key = "daylearn_" + dayStr + "_" + stuCourse.getId();
		String learnNodeIds = (String)redisUtil.get(key);
		int todayLearnNum = 0;
		if(learnNodeIds==null || learnNodeIds.length()==0){
		}else{
		    if(learnNodeIds.indexOf(courseNodeId)>=0){//如果是已经学的课程不用校验
		        return;
            }
			String[] arr = learnNodeIds.split(",");
			todayLearnNum= arr.length;
		}
		return 1;

		//判断课程学习数量的限制是否达到  --同理调用结束的那个段代码
		if(studyTimeLimit != null && studyTimeLimit>0){
			//判断redis 里面的学习数量
			if(todayLearnNum>=studyTimeLimit ){
				BusinessException learnLimit = new BusinessException("今日本课程已达到学习上限数量" +studyTimeLimit);
				learnLimit.setErrCode("course_reach_learn_limit");
				throw learnLimit;
			}
		}
	}



    /**
     * @Author WangZhen
     * @Description 获得课程当日完成数量
     * @Date 2020/7/27 16:23
     **/
    private void finishLearnNumOfCurrentDay(
            SocietyStudentAndCourseView stuCourse,
            String courseNodeId){
        if(courseNodeId==null || courseNodeId.length()==0){
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dayStr = sdf.format(new Date());
        String key = "daylearn_" + dayStr + "_" + stuCourse.getId();
        String learnNodeIds = (String)redisUtil.get(key);
        if(learnNodeIds==null || learnNodeIds.length()==0){
            learnNodeIds = courseNodeId;
            redisUtil.set(key,learnNodeIds,86400);
        }else{
            if(learnNodeIds.indexOf(courseNodeId)>=0){//如果是已经学的课程不用校验
                return;
            }else{
                learnNodeIds = learnNodeIds + "," + courseNodeId;
                redisUtil.set(key,learnNodeIds,86400);
            }
        }

    }


	/**
	 * @Author WangZhen
	 * @Description 播放某个课程章节到最后完结
	 * @Date 2020/5/7 9:01
	 **/
	@RequestMapping("/curNodePlayDone")
	public ModelAndView curNodePlayDone(
			String courseNodeId,
			String stuCourseId) {
		ModelAndView modelAndView = new ModelAndView();
		//获得当前登录学员
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);

		SocietyStudentAndCourseView stuCourse = service.loadByIdAndStuIdCardNum(stuCourseId,student.getId());
		if(stuCourse == null){
			throw new BusinessException("未找到课程!");
		}

		SocietyStudent schStu =  societyStudentService.loadById(stuCourse.getStudentId());
		if(schStu == null){
			return buildErrorResponse(modelAndView,"未查询到学员信息！");
		}
		//找到分配给学生的学习记录
		SocietyStudentAndNode studentAndNode = studentAndNodeService
				.selectStuCourseNodeByStuCourseIdAndCourseNodeId(
						stuCourse.getId(),courseNodeId
				);
		String learnIsFinish = studentAndNode.getLearnIsFinished();
		if(learnIsFinish!=null && learnIsFinish.equals("1")){
			return buildResponse(modelAndView,studentAndNode);//已经结束了不用再次设置
		}
		//检查是否到达学习限制
		checkLearnNumOfCurrentDay(stuCourse,courseNodeId);
		//默认就是允许
		String isNeedFace =  stuCourse.getCourseIsNeedFaceContrast();
		if(isNeedFace==null || isNeedFace.equals("") || isNeedFace.equals("1")){
			if(studyfacecheck){
				FaceMatchInfoExt faceMatchInfo = societyStudentService.checkLearnFace(student);
			}
			//判断人脸次数是否大于4
			int count = processFaceService.selectCountByStuCourseAndNode(stuCourseId,courseNodeId);

			String quesIsFinish = studentAndNode.getQuestionIsFinished();
			String quesIsPass = studentAndNode.getQuestionIsPass();
			int limit = 4;
			if(quesIsFinish!=null && quesIsFinish.equals("1") && quesIsPass!=null && quesIsPass.equals("1")){
				limit = 5;
				System.out.println(stuCourseId+"-" + courseNodeId +"-" + count);
			}
			if(count<limit){
				BusinessException busException = new BusinessException("本节人脸识别次数未达到" + limit + "次,再观看一段时间。");
				busException.setErrCode("face_num_not_enough");
				throw busException;
			}
		}else{
			System.out.println(stuCourseId+"-" + courseNodeId +"-是否有这里的");
		}

		//找到课程章节
		SocietySchoolCourseNode courseNode = schoolCourseNodeService.loadById(courseNodeId);
		//对比学习时长
		int studyTimeLength = studentAndNode.getStudyTimeLength();
		int nodeTimeLength = courseNode.getNodeTimeLength();
		//百分之10的容错
		System.out.println("time cha :" + (nodeTimeLength-studyTimeLength));
		if(nodeTimeLength-studyTimeLength>300){

			//max-study  两个人脸的时间差值是否大于 nodeTimeLength
			SocietyStudentStudyProcessFace societyStudentStudyProcessFaceStart =
					processFaceService.selectByCourseNodeIdAndStuCourseIdStart(courseNodeId,stuCourseId);
			SocietyStudentStudyProcessFace societyStudentStudyProcessFaceEnd =
					processFaceService.selectByCourseNodeIdAndStuCourseIdEnd(courseNodeId,stuCourseId);
			long startTime = societyStudentStudyProcessFaceStart.getCreateTime().getTime();
			long endTime = societyStudentStudyProcessFaceEnd.getCreateTime().getTime();
			long proTime = endTime-startTime;
			if (proTime<nodeTimeLength){
				throw new BusinessException("课程学习时长不足请继续学习");
			}
		}
		//插入一条新的学习过程记录
		SocietyStudentAndNode stuNode = studyProcessService.finishStuCourseNode(schStu,stuCourse,courseNodeId,studentAndNode,courseNode);
        //没啥异常就将学习完的记录到缓存中
        finishLearnNumOfCurrentDay(stuCourse,courseNodeId);

		return buildResponse(modelAndView,stuNode);
	}


	/**
	 * @Author ZhangCC
	 * @Description 根据学员分页查询关联的课程
	 * @Date 2020/5/7 16:20
	 **/
	@RequestMapping("/stuCourseListPage")
	public ModelAndView stuCourseListPage(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		String stuIdCardNum = student.getId();
		query.getQueryObj().setStudentIdCardNum(stuIdCardNum);
		service.stuCourseByIdCardListPage(query);
		List<SocietyStudentAndCourseView> stuAndCourseList = query.getList();
		int siz = stuAndCourseList.size();
		if(siz>0){
			Map<String,SocietyStudentAndCourseView> stuCourseMap = new HashMap<>();
			for(int i=0;i<siz;i++){
				SocietyStudentAndCourseView tempStuCourse = stuAndCourseList.get(i);
				if(tempStuCourse.getCourseProgress() == null){
					tempStuCourse.setCourseProgress(new BigDecimal(0));
				}
				stuCourseMap.put(tempStuCourse.getCourseId(),tempStuCourse);
				//获取班级id
				String classId = tempStuCourse.getClassId();
				SocietySchoolClass societySchoolClass = schoolClassService.loadById(classId);
				if("1".equals(societySchoolClass.getClassStudyState())){
					tempStuCourse.setClassState("0");
					tempStuCourse.setRemainingTime("该课程已经停课，具体开课时间请联系相关班主任");
				}else {
					Date end = societySchoolClass.getClassEndTime();
					Date start = societySchoolClass.getClassStartTime();
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(end);
					calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
					end = calendar.getTime();
					calendar.setTime(start);
					start = calendar.getTime();
					Long timeStrPro = end.getTime() - new Date().getTime();
					Long timeStartStrPro = start.getTime() - new Date().getTime();
					if (timeStrPro<0){
						tempStuCourse.setIsStudy("0");
						tempStuCourse.setRemainingTime("已经超过了学习时间，不能学习");
					}else if(timeStartStrPro>0){
						tempStuCourse.setIsStudy("0");
						tempStuCourse.setRemainingTime("未到课程学习时间，不能学习");
					}else {
						tempStuCourse.setIsStudy("1");
						tempStuCourse.setRemainingTime(getDateTimeProStr(timeStrPro));
					}
				}
			}
			List<SocietySchoolCourseView> schCourseList = schoolCourseService.listSchCourseByStuCourse(stuAndCourseList);
			schCourseList.forEach((schCourse)->{
				SocietyStudentAndCourseView stuCourse = stuCourseMap.get(schCourse.getId());
				if(stuCourse!=null){
					stuCourse.setTotalLessons(schCourse.getTotalLessons());
				}
			});
		}

		return buildResponse(modelAndView,query);
	}

	public String getDateTimeProStr(Long ms){
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

		StringBuffer sb = new StringBuffer();
		if(day > 0) {
			sb.append(day+"天");
		}
		if(hour > 0) {
			sb.append(hour+"小时");
		}
		if(minute > 0) {
			sb.append(minute+"分");
		}
		return sb.toString();
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学员查询需要考试的课程
	 * @Date 2020/5/7 16:20
	 **/
	//TODO:可能有问题
	@RequestMapping("/stuCourseListPageForExam")
	public ModelAndView stuCourseListPageForExam(@ModelAttribute SocietyStudentAndCourseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		String stuIdCardNum = student.getId();
		query.getQueryObj().setStudentIdCardNum(stuIdCardNum);
		service.stuCourseByIdCardListPage(query);
		List<SocietyStudentAndCourseView> stuAndCourseList = query.getList();
		for(int i=0;i<stuAndCourseList.size();i++){
			SocietyStudentAndCourseView stuCourse = stuAndCourseList.get(i);
			String courseId = stuCourse.getCourseId();
			String studentId = stuCourse.getStudentId();
			//查询是否答过试卷
			SocietyStudentTestPaperQuery paperQuery = new SocietyStudentTestPaperQuery();
			paperQuery.getQueryObj().setStudentId(studentId);
			paperQuery.getQueryObj().setOwnerCourseId(courseId);
			paperQuery.getQueryObj().setIsFinished("1");
			List<SocietyStudentTestPaperView> testPaperList = testPaperService.selectPaperByStuCourListPage(paperQuery);
			if(testPaperList != null && testPaperList.size() > 0){
				stuAndCourseList.get(i).setPaperState("finished");
			}else{
				stuAndCourseList.get(i).setPaperState("notFinished");
			}
		}
		return buildResponse(modelAndView,query);
	}

	/**
	 * @Author ZhangCC
	 * @Description 学员根据课程查询一条关联课程
	 * @Date 2020/5/12 19:46
	 **/
	//TODO:有问题
	@RequestMapping("/selectOneStuCourseById")
	public ModelAndView selectOneStuCourseById(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		SocietyStudentAndCourse stuAndCourse = service.selectOneByCourseIdAndIdCarNum(student.getId(),courseId);
		return buildResponse(modelAndView,stuAndCourse);
	}

	/**
	 * @Author wangzhen
	 * @Description 用户点击立即学习按钮
	 * @Date 2020/5/12 19:46
	 **/
	//TODO:有问题
	@RequestMapping("/goToStudyCourseById")
	public ModelAndView goToStudyCourseById(String courseId){
		ModelAndView modelAndView = new ModelAndView();
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);

		SocietyStudentAndCourse stuAndCourse = service.selectOneByCourseIdAndIdCarNum(student.getId(),courseId);
		//TODO: 添加开关动态配置是否允许添加课程
		if(stuAndCourse==null){
			String schoolId = stuAndCourse.getOwnerSchoolId();
			//添加班级和课程
			//1.查询最新的班级
			String clsId = classAndCourseService.selectLatestClassIdBySchAndCourse(schoolId,courseId);
			if(clsId!=null){
				SocietySchoolClass clsObj = schoolClassService.loadById(clsId);
				if(clsObj!=null){
					//2.将人加入到班级里免去  将studentcourse 返回
					stuAndCourse = societyStudentService.updateStuAndClassInfo(student,clsObj);
				}
			}
		}else{
			return buildErrorResponse(modelAndView,"未查询到课程信息！");
		}
		return buildResponse(modelAndView,stuAndCourse);
	}

	/**
	 * 购买课程预支付
	 * @param stuCourseId
	 * @return
	 */
	@RequestMapping("/submitBillForWxPay")
	public ModelAndView chargeWxPrepay(String stuCourseId){
		ModelAndView modelAndView = new ModelAndView();
		//System.out.println("======taocanId:" + stuCourseId);
		//获得当前登录学员
		SocietyStudentUnique student = getLoginStudent(studentUniqueService);
		String openid = student.getWxOpenId();
		//System.out.println("openid==="+openid);
		try {
			String prePayId = service.updateWxWapPrepayCourse(stuCourseId,getIpAddr(), getUserAgent(),openid);
			buildResponse(modelAndView,prePayId);
			return modelAndView;
		} catch (BusinessException e) {
			return  buildErrorResponse(modelAndView,e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			return  buildErrorResponse(modelAndView,e.getMessage());
		}

	}




}
