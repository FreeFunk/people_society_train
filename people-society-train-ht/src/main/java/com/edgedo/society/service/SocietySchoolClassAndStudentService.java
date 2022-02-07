package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.*;
import com.edgedo.society.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.monitor.StringMonitor;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassAndStudentService {
	
	
	@Autowired
	private SocietySchoolClassAndStudentMapper societySchoolClassAndStudentMapper;
	@Autowired
	private SocietyStudentMapper studentMapper;
	@Autowired
	private SocietySchoolClassService schoolClassService;
	@Autowired
	private SocietySchoolClassAndCourseMapper classAndCourseMapper;
	@Autowired
	private SocietySchoolCourseService schoolCourseService;
	@Autowired
	private SocietyStudentAndCourseMapper studentAndCourseMapper;
	@Autowired
	private SocietySchoolCourseNodeMapper courseNodeMapper;
	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;
	@Autowired
	private SocietyStudentAndNodeMapper studentAndNodeMapper;
	@Autowired
	private SocietySchoolCourseNodeQuestionMapper nodeQuestionMapper;
	@Autowired
	private SocietyStudentPractiseQuestionMapper studentPractiseQuestionMapper;
	@Autowired
	private SocietySchoolCourseNodeOptionMapper nodeOptionMapper;
	@Autowired
	private SocietyStudentPractiseQuestOptionMapper studentPractiseQuestOptionMapper;
	@Autowired
	private SocietyStudentStudyProcessFaceMapper studentStudyProcessFaceMapper;
	@Autowired
	private SocietyTestPaperMapper testPaperMapper;
	@Autowired
	private SocietyTestPaperQuestionMapper testPaperQuestionMapper;
	@Autowired
	private SocietyStudentTestPaperMapper studentTestPaperMapper;
	@Autowired
	private SocietyStudentTestPaperQuestionMapper studentTestPaperQuestionMapper;
	@Autowired
	private SocietyTestPaperQuestionOptionMapper testPaperQuestionOptionMapper;
	@Autowired
	private SocietyStudentTestPaperQuestionOptionMapper studentTestPaperQuestionOptionMapper;

	@Autowired
	private SocietyStudentCertificateMapper studentCertificateMapper;

	@Autowired
	private SocietySchoolCourseUseGlobleService societySchoolCourseUseGlobleService;

	public List<SocietySchoolClassAndStudent> selectByClassId(Map<String,String> map) {
		return societySchoolClassAndStudentMapper.selectByClassId(map);
	}

	public List<SocietySchoolClassAndStudentView> listPage(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.listPage(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}

	public List<SocietySchoolClassAndStudentView> listByObj(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.listByObj(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}

	public List<SocietySchoolClassAndStudentView> selectBySchoolNamelistPage(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.selectBySchoolNamelistPage(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}

	public List<SocietySchoolClassAndStudentView> selectBySchoolAndClassBySchoolIdAndClassIdlistPage(SocietySchoolClassAndStudentQuery societySchoolClassAndStudentQuery){
		List list = societySchoolClassAndStudentMapper.selectBySchoolAndClassBySchoolIdAndClassIdlistPage(societySchoolClassAndStudentQuery);
		societySchoolClassAndStudentQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudent.setId(Guid.guid());
		societySchoolClassAndStudent.setCreateTime(new Date());
		societySchoolClassAndStudent.setDataState("1");
		Integer num = societySchoolClassAndStudentMapper.selectByClassAndStudentNum();
		DecimalFormat df=new DecimalFormat("000000");
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(societySchoolClassAndStudent.getClassId());
		String code = societySchoolClass.getClassCode()+"-"+df.format(num+1);
		societySchoolClassAndStudent.setArchivesCode(code);
		societySchoolClassAndStudentMapper.insert(societySchoolClassAndStudent);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudentMapper.updateById(societySchoolClassAndStudent);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClassAndStudent societySchoolClassAndStudent) {
		societySchoolClassAndStudentMapper.updateAllColumnById(societySchoolClassAndStudent);
		return "";
	}

	/* *
	 * @Author ZhangCC
	 * @Description 根据班级和学员查看关联记录
	 **/
	public SocietySchoolClassAndStudentView selectVoByClassAndStudent(String classId,String studentId){
		Map<String,Object> param = new HashMap<>();
		param.put("classId",classId);
		param.put("studentId",studentId);
		return societySchoolClassAndStudentMapper.selectVoByClassAndStudent(param);
	}

	/* *
	 * @Author ZhangCC
	 * @Description 根据班级和学员删除关联记录
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteVoByClassAndStudent(String classId,String studentId){
		Map<String,Object> param = new HashMap<>();
		param.put("classId",classId);
		param.put("studentId",studentId);
		societySchoolClassAndStudentMapper.deleteVoByClassAndStudent(param);

		//根据学生id 和 班级id 查出所有的学员章节关联
		List<String> list = studentAndCourseMapper.selectByAllId(param);
		if (list.size()!=0){
			studentAndNodeService.updateByDataState(list);
		}
		//删除学员和该班级的课程关联
		studentAndCourseMapper.updateStuAndCourse(param);

		return 1;
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据班级查询关联的学员
	 * @Date 2020/5/4 9:17
	 **/
	public List<String> selectStudentIdByClass(String classId){
		return societySchoolClassAndStudentMapper.selectStudentIdByClass(classId);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询下未完成学习的关联学员
	 * @Date 2020/5/4 17:51
	 **/
	public int countByClassAndLearnNotFinished(String classId){
		return societySchoolClassAndStudentMapper.countByClassAndLearnNotFinished(classId);
	}

	public List<SocietySchoolClassAndStudentView> selectNotRelationList(String courseId, String classId) {
		Map<String,String> map = new HashMap<>();
		map.put("courseId",courseId);
		map.put("classId",classId);
		return societySchoolClassAndStudentMapper.selectNotRelationList(map);
	}
	/**
	 * @Author ZhangCC
	 * @Description 插入学员和班级的关联
	 * @Date 2020/5/9 17:59
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertClassAndStudentRelation(SocietySchoolClass schoolClass, String stuIds){
		//根据班级查询课程
		String errMsg = "";
		String classId = schoolClass.getId();
		List<String> courseIdList = classAndCourseMapper.selectCourseIdByClass(classId);
		String[] stuIdArr = stuIds.split(",");
		for(int i=0;i<stuIdArr.length;i++){
			SocietyStudent student = studentMapper.selectById(stuIdArr[i]);
			if(student != null){
				//插入学员与课程的关联
				String err1 = insertCourseAndStudentRelation(student,courseIdList,classId);
				if(err1.equals("")){
					//插入学员与班级的关联
					insertClassAndStudentRelation(student,schoolClass);
					//更新 学员课程的班级关联id
					String classAndStudentId =
							societySchoolClassAndStudentMapper.selectByClassIdAndStudentIdAndSchoolId
									(schoolClass.getId(), student.getId(),schoolClass.getOwnerSchoolId());
					studentAndCourseMapper.updateByClassAndStudentId(schoolClass.getId(),
							student.getId(),schoolClass.getOwnerSchoolId(),classAndStudentId);
				}else {
					if(errMsg.equals("")){
						errMsg = err1;
					}else {
						errMsg = errMsg+"，"+err1;
					}
				}
			}
		}
		//更新一下班级学员数和未完成人数
		//统计该班级的人数
		int totalStuNum = societySchoolClassAndStudentMapper.countStudentByClassId(classId);
		schoolClass.setClassPersonNum(totalStuNum);
		//统计课程的培训人数
		for(String courseId:courseIdList){
			SocietySchoolCourse course = schoolCourseService.loadById(courseId);
			//统计课程的培训人数
			int countTotalStuNum = studentAndCourseMapper.countTotalStuNum(courseId);
			course.setTotalStudentNum(countTotalStuNum);
			schoolCourseService.update(course);
		}
	/*	//统计已经完成
		Integer notFinishedPersonNum = schoolClass.getNotFinishedPersonNum();
		if(notFinishedPersonNum == null){
			schoolClass.setNotFinishedPersonNum(classAddStuCount);
		}else{
			schoolClass.setNotFinishedPersonNum(notFinishedPersonNum + classAddStuCount);
		}*/
		schoolClassService.update(schoolClass);
		return errMsg;
	}

	/**
	 * @Author ZhangCC
	 * @Description 插入学员与班级的关联
	 * @Date 2020/5/5 8:24
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertClassAndStudentRelation(SocietyStudent student,SocietySchoolClass schoolClass){
		SocietySchoolClassAndStudent classAndStudent = new SocietySchoolClassAndStudent();
		classAndStudent.setStudentId(student.getId());
		classAndStudent.setStudentName(student.getStudentName());
		classAndStudent.setStudentIdCardNum(student.getStudentIdCardNum());
		classAndStudent.setOwnerSchoolId(schoolClass.getOwnerSchoolId());
		classAndStudent.setOwnerSchoolName(schoolClass.getOwnerSchoolName());
		classAndStudent.setOwnerMajorId(schoolClass.getOwnerMajorId());
		classAndStudent.setOwnerMajorName(schoolClass.getOwnerMajorName());
		classAndStudent.setClassId(schoolClass.getId());
		classAndStudent.setClassName(schoolClass.getClassName());
		classAndStudent.setStudentLeranProgress(new BigDecimal(0));
		classAndStudent.setTotalNodeNum(schoolClass.getTotalLessons());
		classAndStudent.setFinishedNodeNum(0);
		classAndStudent.setLearnIsFinished("0");
		//更新位置信息
		classAndStudent.setProvinceId(schoolClass.getProvinceId());
		classAndStudent.setProvinceName(schoolClass.getProvinceName());
		classAndStudent.setCityId(schoolClass.getCityId());
		classAndStudent.setCityName(schoolClass.getCityName());
		classAndStudent.setXianquId(schoolClass.getXianquId());
		classAndStudent.setXianquName(schoolClass.getXianquName());
		String errMsg = insert(classAndStudent);
		//更新学生位置
		student.setProvinceId(schoolClass.getProvinceId());
		student.setProvinceName(schoolClass.getProvinceName());
		student.setCityId(schoolClass.getCityId());
		student.setCityName(schoolClass.getCityName());
		student.setXianquId(schoolClass.getXianquId());
		student.setXianquName(schoolClass.getXianquName());
		studentMapper.updateById(student);
		return errMsg;
	}

	/**
	 * @Author ZhangCC
	 * @Description 插入学员与课程的关联
	 * @Date 2020/5/5 8:24
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertCourseAndStudentRelation(SocietyStudent student,List<String> courseIdList,String classId){
		String err = "";
		SocietyStudentAndCourse studentAndCourse = new SocietyStudentAndCourse();
		studentAndCourse.setOwnerSchoolId(student.getOwnerSchoolId());
		studentAndCourse.setClassId(classId);
		studentAndCourse.setStudentId(student.getId());
		studentAndCourse.setStudentName(student.getStudentName());
		studentAndCourse.setStudentIdCardNum(student.getStudentIdCardNum());
		studentAndCourse.setDataState("1");
		studentAndCourse.setFinishNodeNum(0);
		studentAndCourse.setCourseProgress(new BigDecimal(0));
		studentAndCourse.setLearnIsFinished("0");
		studentAndCourse.setCreateTime(new Date());
		//获取专业id 专业名 班级名
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(classId);
		//更新位置信息
		studentAndCourse.setProvinceId(societySchoolClass.getProvinceId());
		studentAndCourse.setProvinceName(societySchoolClass.getProvinceName());
		studentAndCourse.setCityId(societySchoolClass.getCityId());
		studentAndCourse.setCityName(societySchoolClass.getCityName());
		studentAndCourse.setXianquId(societySchoolClass.getXianquId());
		studentAndCourse.setXianquName(societySchoolClass.getXianquName());

		studentAndCourse.setOwnerMajorId(societySchoolClass.getOwnerMajorId());
		studentAndCourse.setOwnerMajorName(societySchoolClass.getOwnerMajorName());
		studentAndCourse.setClassName(societySchoolClass.getClassName());
		Map<String,Object> map = new HashMap<>();
		map.put("phone",student.getStudentPhoneNum());
		map.put("idCard",student.getStudentIdCardNum());
		for(int i=0;i<courseIdList.size();i++){
			SocietySchoolCourse course = schoolCourseService.loadById(courseIdList.get(i));
			if(course != null){
				String courseId = course.getId();
				//判断该班级学员是否关联该课程
				map.put("courseId",courseId);
				int count = studentAndCourseMapper.countByMap(map);
				if(count==0){
					studentAndCourse.setId(Guid.guid());
					studentAndCourse.setCourseId(course.getId());
					studentAndCourse.setCourseName(course.getCourseName());
					studentAndCourse.setCourseImage(course.getCourseImage());
					studentAndCourse.setTotalLessons(course.getTotalLessons());
					BigDecimal coursePrice = course.getCoursePrice();
					//判断课程是否是免费
					if(coursePrice==null || coursePrice.compareTo(BigDecimal.ZERO)==0){
						studentAndCourse.setCoursePrice(new BigDecimal(0));
						studentAndCourse.setCourseOrgPrice(course.getCourseOrgPrice());
						studentAndCourse.setPayState("1");
					}else {
						studentAndCourse.setCoursePrice(course.getCoursePrice());
						studentAndCourse.setCourseOrgPrice(course.getCourseOrgPrice());
						studentAndCourse.setPayState("0");
					}
//					studentAndCourse.setCourseStudyNum(course.getCourseStudyNum());
					//首先判断学生类型
					if("IMP_STU".equals(student.getPeopleType())){
						studentAndCourse.setCourseStudyNum(course.getImpStuCourseStudyNum());
					}else if("COMP_STU".equals(student.getPeopleType())){
						studentAndCourse.setCourseStudyNum(course.getCompStuCourseStudyNum());
					}else if("ORD_STU".equals(student.getPeopleType())){
						studentAndCourse.setCourseStudyNum(course.getOrdStuCourseStudyNum());
					}else {
						studentAndCourse.setCourseStudyNum(societySchoolClass.getClassCourseNum());
					}
					studentAndCourse.setCourseIsNeedFaceContrast(course.getCourseIsNeedFaceContrast());
					studentAndCourseMapper.insert(studentAndCourse);
					//插入学员与章节的关联
					insertCourseNodeAndStudentRelation(student,studentAndCourse);
				}else {
					if (err.equals("")){
						err = student.getStudentName();
					}else {
						err = err+"，"+ student.getStudentName();
					}
				}
			}
		}
		return err;
	}

	/**
	 * @Author ZhangCC
	 * @Description 插入学员与课程小结的关联
	 * @Date 2020/6/1 9:04
	 **/
	public void insertCourseNodeAndStudentRelation(SocietyStudent student,SocietyStudentAndCourse studentAndCourse){
		SocietyStudentAndNode studentAndNode = new SocietyStudentAndNode();
		studentAndNode.setOwnerStudentAndCourseId(studentAndCourse.getId());
		studentAndNode.setCreateTime(new Date());
		studentAndNode.setDatastate("1");
		studentAndNode.setOwnerSchoolId(student.getOwnerSchoolId());
		studentAndNode.setOwnerSchoolName(student.getOwnerSchoolName());
		studentAndNode.setStudentId(student.getId());
		studentAndNode.setStudentName(student.getStudentName());
		studentAndNode.setStudentIdCardNum(student.getStudentIdCardNum());
		studentAndNode.setOwnerCourseId(studentAndCourse.getCourseId());
		studentAndNode.setOwnerCourseName(studentAndCourse.getCourseName());
		//更新位置信息
		studentAndNode.setProvinceId(studentAndCourse.getProvinceId());
		studentAndNode.setProvinceName(studentAndCourse.getProvinceName());
		studentAndNode.setCityId(studentAndCourse.getCityId());
		studentAndNode.setCityName(studentAndCourse.getCityName());
		studentAndNode.setXianquId(studentAndCourse.getXianquId());
		studentAndNode.setXianquName(studentAndCourse.getXianquName());
		SocietySchoolCourseNodeQuery query = new SocietySchoolCourseNodeQuery();
		//从公共表查出是否使用公共课
		SocietySchoolCourseUseGloble societySchoolCourseUseGloble =
				societySchoolCourseUseGlobleService.selectBySchoolIdAndCourseIdOnce(studentAndCourse.getOwnerSchoolId(),
						studentAndCourse.getCourseId());
		if (societySchoolCourseUseGloble!=null){
			query.getQueryObj().setOwnerSchoolId(societySchoolCourseUseGloble.getCourseSchId());
			query.getQueryObj().setOwnerCourseId(societySchoolCourseUseGloble.getCourseId());
		}else {
			query.getQueryObj().setOwnerSchoolId(studentAndCourse.getOwnerSchoolId());
			query.getQueryObj().setOwnerCourseId(studentAndCourse.getCourseId());
		}
		query.getQueryObj().setShState("1");
		List<SocietySchoolCourseNodeView> courseNodeList = courseNodeMapper.listByObj(query);
		if(courseNodeList != null && courseNodeList.size() >0){
			for(SocietySchoolCourseNodeView courseNode:courseNodeList){
				studentAndNode.setNodeId(courseNode.getId());
				studentAndNode.setNodeName(courseNode.getNodeName());
				studentAndNode.setNodeProgress(new BigDecimal(0));
				studentAndNode.setStudyTimeLength(0);
				studentAndNode.setLastLearnLocation(0);
				studentAndNode.setLearnIsFinished("0");
				studentAndNode.setNodeQuestionScore(0);
				studentAndNode.setQuestionIsFinished("0");
				studentAndNode.setQuestionIsPass("0");
				studentAndNodeService.insert(studentAndNode);
			}
		}
	}

	//查询该班级下的所有的学生
	public List<SocietySchoolClassAndStudentView> selectVoByClassId(String classId) {
		return societySchoolClassAndStudentMapper.selectVoByClassId(classId);
	}

	/*删除学员和班级的关联*/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByClassAndStudent(String stuIds, String classId) {
		SocietySchoolClass schoolClass = schoolClassService.loadById(classId);
		String[] stuIdArr = stuIds.split(",");
		for(int i=0;i<stuIdArr.length;i++){
			deleteVoByClassAndStudent(classId,stuIdArr[i]);
		}
		//删除学员与该班级所有的关联

		//更新班级的人数
		int countStudentByClassId = societySchoolClassAndStudentMapper.countStudentByClassId(classId);
		schoolClass.setClassPersonNum(countStudentByClassId);
		schoolClassService.update(schoolClass);
	}
	/**
	 *@Author:ZhaoSiDa
	 *@Description: 根据学员和班级关联表的ID一键完成学员的学习
	 *@DateTime: 2020/5/29 16:28
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String oneClickFinish(List<String> ids) {
		//遍历集合
		ids.forEach(id -> {
			SocietySchoolClassAndStudent classAndStudent = loadById(id);
			//完成学员的班级进度
			finishedStuAndClass(classAndStudent);
			String classId = classAndStudent.getClassId();
			String studentId = classAndStudent.getStudentId();
			//根据班级id和学员id查询学员关联的课程
			List<SocietyStudentAndCourseView> studentAndCourseViewList = studentAndCourseMapper.listByClassIdAndStuId(classId,studentId);
			//遍历所有课程，查询课程小节
			studentAndCourseViewList.forEach(stuAndCourse ->{
				//完成学员课程进度
				finishedStuAndCourse(stuAndCourse);
				//完成学员的小节进度
				finishedStuAndNode(stuAndCourse);
				//完成学员的人脸记录
				finishedStuFaceRec(stuAndCourse);
				//完成学员的证书
				finishedStuCert(stuAndCourse);
				//完成学员的考试记录
				finishedStuTestPaper(stuAndCourse);

			});
		});
		return null;
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 根据学员和班级关联表的ID一键完成学员的学习
	 *@DateTime: 2020/5/29 16:28
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String suiJiFinish(List<String> ids) {
		int maxNum = 0;
		int randNum = 0;
		double progressNum = 0.0;
		//遍历集合
		for (String id:ids){
			SocietySchoolClassAndStudent classAndStudent = loadById(id);
			String classId = classAndStudent.getClassId();
			String studentId = classAndStudent.getStudentId();
			//根据班级id和学员id查询学员关联的课程
			List<SocietyStudentAndCourseView> studentAndCourseViewList = studentAndCourseMapper.listByClassIdAndStuId(classId,studentId);
			//遍历所有课程，查询课程小节
			for (SocietyStudentAndCourseView stuAndCourse:studentAndCourseViewList){
				//统计课程下的小节数
				int totalNodeNum = courseNodeMapper.countByCourseId(stuAndCourse.getCourseId());
				maxNum = totalNodeNum-2;
				randNum = getRandNum(maxNum);
				//计算随机的完成进度
				DecimalFormat df = new DecimalFormat("#.00");
				progressNum = Double.valueOf(df.format( (double)(randNum/(double)totalNodeNum)))*100;
				//完成学员课程进度
				suiJiFinishedStuAndCourse(stuAndCourse,randNum,progressNum);
				//完成学员的小节进度
				suiJiFinishedStuAndNode(stuAndCourse,randNum);
				//完成学员的人脸记录
				suiJiFinishedStuFaceRec(stuAndCourse,randNum);
			}
			//完成学员的班级进度
			suiJiFinishedStuAndClass(classAndStudent,randNum,progressNum);
		}
		return null;
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 完成学员的班级进度
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuAndClass(SocietySchoolClassAndStudent classAndStudent) {
		//班级进度
		classAndStudent.setStudentLeranProgress(new BigDecimal(100));
		//完成课时数
		classAndStudent.setFinishedNodeNum(classAndStudent.getTotalNodeNum());
		//课程是否完成
		classAndStudent.setLearnIsFinished("1");
		societySchoolClassAndStudentMapper.updateById(classAndStudent);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 随机完成学员的班级进度
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void suiJiFinishedStuAndClass(SocietySchoolClassAndStudent classAndStudent,int randNum,double progressNum) {
		//班级进度
		classAndStudent.setStudentLeranProgress(new BigDecimal(progressNum));
		//完成课时数
		classAndStudent.setFinishedNodeNum(randNum);
		//课程是否完成
		classAndStudent.setLearnIsFinished("0");
		societySchoolClassAndStudentMapper.updateById(classAndStudent);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 完成学员课程进度
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuAndCourse(SocietyStudentAndCourseView stuAndCourse) {
		//课程进度
		stuAndCourse.setCourseProgress(new BigDecimal(100));
		//完成课时数
		stuAndCourse.setFinishNodeNum(stuAndCourse.getTotalLessons());
		//课程是否完成
		stuAndCourse.setLearnIsFinished("1");
		studentAndCourseMapper.updateById(stuAndCourse);
	}


	/**
	 *@Author:ZhaoSiDa
	 *@Description: 随机完成学员课程进度
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void suiJiFinishedStuAndCourse(SocietyStudentAndCourseView stuAndCourse,int randNum,double progressNum) {
		//课程进度
		stuAndCourse.setCourseProgress(new BigDecimal(progressNum));
		//完成课时数
		stuAndCourse.setFinishNodeNum(randNum);
		//课程是否完成
		stuAndCourse.setLearnIsFinished("0");
		studentAndCourseMapper.updateById(stuAndCourse);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 完成学员的小节进度
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuAndNode(SocietyStudentAndCourseView stuAndCourse) {
		/*学员和课程关联表ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		//根据课程ID查询所有小节
		List<SocietySchoolCourseNodeView> nodeViewList = courseNodeMapper.listByCourseId(courseId);
		nodeViewList.forEach(node ->{
			String nodeId = node.getId();
			//判断学员是否关联该小节
			SocietyStudentAndNodeView studentAndNodeView  =studentAndNodeMapper.loadByStuIdAndNodeId(studentId,stuAndCourseId,nodeId);
			if(studentAndNodeView==null){
				//插入学员和小节关联
				studentAndNodeView = new SocietyStudentAndNodeView();
				studentAndNodeView.setId(Guid.guid());
				studentAndNodeView.setOwnerStudentAndCourseId(stuAndCourseId);
				studentAndNodeView.setOwnerSchoolId(node.getOwnerSchoolId());
				studentAndNodeView.setOwnerSchoolName(node.getOwnerSchoolName());
				studentAndNodeView.setStudentId(studentId);
				studentAndNodeView.setStudentName(stuAndCourse.getStudentName());
				studentAndNodeView.setStudentIdCardNum(stuAndCourse.getStudentIdCardNum());
				studentAndNodeView.setOwnerCourseId(node.getOwnerCourseId());
				studentAndNodeView.setOwnerCourseName(node.getOwnerCourseName());
				studentAndNodeView.setNodeId(nodeId);
				studentAndNodeView.setNodeName(node.getNodeName());
				studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
				studentAndNodeView.setNodeProgress(new BigDecimal(100));
				studentAndNodeView.setStudyTimeLength(node.getNodeTimeLength());
				studentAndNodeView.setLastLearnLocation(node.getNodeTimeLength());
				studentAndNodeView.setLearnIsFinished("1");
				studentAndNodeView.setQuestionIsPass("1");
				studentAndNodeView.setQuestionIsFinished("1");
				//TODO 学员的得分
				studentAndNodeMapper.insert(studentAndNodeView);
			}else {
				studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
				studentAndNodeView.setNodeProgress(new BigDecimal(100));
				studentAndNodeView.setStudyTimeLength(node.getNodeTimeLength());
				studentAndNodeView.setLastLearnLocation(node.getNodeTimeLength());
				studentAndNodeView.setLearnIsFinished("1");
				studentAndNodeView.setQuestionIsPass("1");
				studentAndNodeView.setQuestionIsFinished("1");
				//TODO 学员的得分
				studentAndNodeMapper.updateById(studentAndNodeView);
			}
			//完成学员的章节习题
			finishedStuNodeQuestion(studentAndNodeView);
		});
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 随机完成学员的小节进度
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void suiJiFinishedStuAndNode(SocietyStudentAndCourseView stuAndCourse,int randNum) {
		/*学员和课程关联表ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		//根据课程ID查询所有小节
		List<SocietySchoolCourseNodeView> nodeViewList = courseNodeMapper.listByCourseIdSuiJiNum(courseId,randNum);
		nodeViewList.forEach(node ->{
			String nodeId = node.getId();
			//判断学员是否关联该小节
			SocietyStudentAndNodeView studentAndNodeView  =studentAndNodeMapper.loadByStuIdAndNodeId(studentId,stuAndCourseId,nodeId);
			if(studentAndNodeView==null){
				//插入学员和小节关联
				studentAndNodeView = new SocietyStudentAndNodeView();
				studentAndNodeView.setId(Guid.guid());
				studentAndNodeView.setOwnerStudentAndCourseId(stuAndCourseId);
				studentAndNodeView.setOwnerSchoolId(node.getOwnerSchoolId());
				studentAndNodeView.setOwnerSchoolName(node.getOwnerSchoolName());
				studentAndNodeView.setStudentId(studentId);
				studentAndNodeView.setStudentName(stuAndCourse.getStudentName());
				studentAndNodeView.setStudentIdCardNum(stuAndCourse.getStudentIdCardNum());
				studentAndNodeView.setOwnerCourseId(node.getOwnerCourseId());
				studentAndNodeView.setOwnerCourseName(node.getOwnerCourseName());
				studentAndNodeView.setNodeId(nodeId);
				studentAndNodeView.setNodeName(node.getNodeName());
				studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
				studentAndNodeView.setNodeProgress(new BigDecimal(100));
				studentAndNodeView.setStudyTimeLength(node.getNodeTimeLength());
				studentAndNodeView.setLastLearnLocation(node.getNodeTimeLength());
				studentAndNodeView.setLearnIsFinished("1");
				studentAndNodeView.setQuestionIsPass("1");
				studentAndNodeView.setQuestionIsFinished("1");
				//TODO 学员的得分
				studentAndNodeMapper.insert(studentAndNodeView);
			}else {
				studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
				studentAndNodeView.setNodeProgress(new BigDecimal(100));
				studentAndNodeView.setStudyTimeLength(node.getNodeTimeLength());
				studentAndNodeView.setLastLearnLocation(node.getNodeTimeLength());
				studentAndNodeView.setLearnIsFinished("1");
				studentAndNodeView.setQuestionIsPass("1");
				studentAndNodeView.setQuestionIsFinished("1");
				//TODO 学员的得分
				studentAndNodeMapper.updateById(studentAndNodeView);
			}
			//完成学员的章节习题
			finishedStuNodeQuestion(studentAndNodeView);
		});
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 完成学员的章节习题
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuNodeQuestion(SocietyStudentAndNodeView studentAndNodeView) {
		/*学员和小节关联表ID*/
		String stuAndNodeId = studentAndNodeView.getId();
		String nodeId = studentAndNodeView.getNodeId();
		String studentId = studentAndNodeView.getStudentId();
		//设置随机错误答案
		Map<String,String> map = getRandMap(studentAndNodeView.getTotalQuestionNum());
		//根据小节ID查询小节下面的所有的习题
		List<SocietySchoolCourseNodeQuestionView> nodeQuestionViewList = nodeQuestionMapper.listByNodeId(nodeId);
		nodeQuestionViewList.forEach(question ->{
			String questionId = question.getId();
			int index = nodeQuestionViewList.indexOf(question);
			String answerState = "1";
			int getScore = question.getQuestionScore();
			if(map.get(index+"")!=null){
				answerState = "-1";
				getScore = 0;
			}
			//判断学员是否关联该习题
			SocietyStudentPractiseQuestionView studentPractiseQuestionView =studentPractiseQuestionMapper.loadByStuIdAndQuestionId(studentId,stuAndNodeId,questionId);
			if(studentPractiseQuestionView==null){
				String stuQuestionId = Guid.guid();
				List<SocietySchoolCourseNodeOption> nodeOptionList = nodeOptionMapper.selectOptionByQuestion(questionId);
				nodeOptionList.forEach(nodeOption ->{
					String nodeOptionId = nodeOption.getId();
					//判断是否已经有该选项
					SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuIdAndOptionId(studentId,stuAndNodeId,nodeOptionId);
					if(societyStudentPractiseQuestOption==null){
						societyStudentPractiseQuestOption = new SocietyStudentPractiseQuestOption();
						societyStudentPractiseQuestOption.setId(Guid.guid());
						societyStudentPractiseQuestOption.setCreateTime(new Date());
						societyStudentPractiseQuestOption.setOwnerStudentAndNodeId(stuAndNodeId);
						societyStudentPractiseQuestOption.setOwnerStuCourseId(studentAndNodeView.getOwnerStudentAndCourseId());
						societyStudentPractiseQuestOption.setStudentId(studentId);
						societyStudentPractiseQuestOption.setOwnerSchoolId(studentAndNodeView.getOwnerSchoolId());
						societyStudentPractiseQuestOption.setOwnerSchoolName(studentAndNodeView.getOwnerSchoolName());
						societyStudentPractiseQuestOption.setOwnerNodeId(studentAndNodeView.getNodeId());
						societyStudentPractiseQuestOption.setOwnerNodeName(studentAndNodeView.getNodeName());
						societyStudentPractiseQuestOption.setOwnerNodeQueOpId(nodeOptionId);
						societyStudentPractiseQuestOption.setOptionTitle(nodeOption.getOptionTitle());
						societyStudentPractiseQuestOption.setOptionName(nodeOption.getOptionName());
						if(question.getQuestionType().equals("1")){
							if(question.getQuestionAnswer().contains(nodeOption.getOptionTitle())){
								societyStudentPractiseQuestOption.setIsRight("1");
							}else {
								societyStudentPractiseQuestOption.setIsRight("0");
							}
						}else {
							societyStudentPractiseQuestOption.setIsRight(nodeOption.getIsRight());
						}
						societyStudentPractiseQuestOption.setIsSelect("0");
						societyStudentPractiseQuestOption.setOrderNum(nodeOption.getOrderNum());
						societyStudentPractiseQuestOption.setOwnerStudentQuersionId(stuQuestionId);
						societyStudentPractiseQuestOption.setOwnerStudentQuersionName(question.getQuestionName());
						societyStudentPractiseQuestOption.setLastAnswerTime(new Date());
						studentPractiseQuestOptionMapper.insert(societyStudentPractiseQuestOption);
						//System.out.println("societyStudentPractiseQuestOption====="+societyStudentPractiseQuestOption);
					}
				});
				studentPractiseQuestionView = new SocietyStudentPractiseQuestionView();
				studentPractiseQuestionView.setId(stuQuestionId);
				studentPractiseQuestionView.setCreateTime(new Date());
				studentPractiseQuestionView.setOwnerStudentAndNodeId(stuAndNodeId);
				studentPractiseQuestionView.setOwnerStuCourseId(studentAndNodeView.getOwnerStudentAndCourseId());
				studentPractiseQuestionView.setStudentId(studentId);
				studentPractiseQuestionView.setStudentName(studentAndNodeView.getStudentName());
				studentPractiseQuestionView.setStudentIdCardNum(studentAndNodeView.getStudentIdCardNum());
				studentPractiseQuestionView.setOwnerSchoolId(studentAndNodeView.getOwnerSchoolId());
				studentPractiseQuestionView.setOwnerSchoolName(studentAndNodeView.getOwnerSchoolName());
				studentPractiseQuestionView.setOwnerCourseId(studentAndNodeView.getOwnerCourseId());
				studentPractiseQuestionView.setOwnerCourseName(studentAndNodeView.getOwnerCourseName());
				studentPractiseQuestionView.setOwnerNodeId(studentAndNodeView.getNodeId());
				studentPractiseQuestionView.setOwnerNodeName(studentAndNodeView.getNodeName());
				studentPractiseQuestionView.setQuersionId(questionId);
				studentPractiseQuestionView.setQuestionName(question.getQuestionName());
				studentPractiseQuestionView.setQuestionType(question.getQuestionType());
				studentPractiseQuestionView.setQuestionScore(question.getQuestionScore());
				studentPractiseQuestionView.setAnswerState(answerState);
				studentPractiseQuestionView.setGetScore(getScore);
				studentPractiseQuestionView.setOrderNum(question.getOrderNum());
				//TODO  学员所选
				SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption = null;
				if(answerState.equals("1")){
					//查询出正确答案关联上，
					societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionId(stuQuestionId,"1");
				/*	if(societyStudentPractiseQuestOption==null){
						System.out.println("stuQuestionId========"+stuQuestionId);
					}*/
					societyStudentPractiseQuestOption.setIsSelect("1");
					studentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
				}else {
					//查询出错误答案关联上，
					societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionId(stuQuestionId,"0");
					societyStudentPractiseQuestOption.setIsSelect("1");
					studentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
				}
				studentPractiseQuestionView.setStuSelectOpId(societyStudentPractiseQuestOption.getId());
				studentPractiseQuestionView.setLastAnswerTime(new Date());
				studentPractiseQuestionView.setDataState("1");
				studentPractiseQuestionMapper.insert(studentPractiseQuestionView);
			}else {
				studentPractiseQuestionView.setAnswerState(answerState);
				studentPractiseQuestionView.setGetScore(getScore);
				studentPractiseQuestionView.setOrderNum(question.getOrderNum());
				//TODO  学员所选
				SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption = null;
				if(answerState.equals("1")){
					//查询出正确答案关联上，
					societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionId(studentPractiseQuestionView.getId(),"1");
					societyStudentPractiseQuestOption.setIsSelect("1");
					studentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
				}else {
					//查询出错误答案关联上，
					societyStudentPractiseQuestOption = studentPractiseQuestOptionMapper.loadByStuQuestionId(studentPractiseQuestionView.getId(),"0");
					societyStudentPractiseQuestOption.setIsSelect("1");
					studentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
				}
				studentPractiseQuestionView.setStuSelectOpId(societyStudentPractiseQuestOption.getId());
				studentPractiseQuestionView.setLastAnswerTime(new Date());
				studentPractiseQuestionView.setDataState("1");
				studentPractiseQuestionMapper.updateById(studentPractiseQuestionView);
			}
		});
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 完成学员的人脸记录
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuFaceRec(SocietyStudentAndCourseView stuAndCourse) {
		/*学员和课程关联表ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
		int count = 0;
		int consumRecListSize = 0;
		int nodeViewListSize = 0;
		//根据身份证号查询出记录
		List<YwTrainConsumRec> consumRecList = courseNodeMapper.selectByIdCardNum(studentIdCardNum);
		if(consumRecList!=null){
			consumRecListSize = consumRecList.size();
		}
		//目前人脸记录和课程关联上
		//根据课程ID查询所有小节
		List<SocietySchoolCourseNodeView> nodeViewList = courseNodeMapper.listByCourseId(courseId);
		if(nodeViewList!=null){
			nodeViewListSize = nodeViewList.size();
		}
		if(nodeViewListSize>consumRecListSize){
			count = consumRecListSize;
		}else {
			count = nodeViewListSize;
		}
		for (int i=0;i<count;i++){
			String conRecId = consumRecList.get(i).getId();
			SocietySchoolCourseNodeView nodeView = nodeViewList.get(i);
			//根据学习记录id查询学习过程中的人脸记录
			List<YwTrainLearnRec> learnRecList = courseNodeMapper.selectByConRecId(conRecId);
			for (YwTrainLearnRec learnRec:learnRecList){
				//查询出所有的人脸记录
				SocietyStudentStudyProcessFace societyStudentStudyProcessFace = new SocietyStudentStudyProcessFace();
				societyStudentStudyProcessFace.setId(Guid.guid());
				societyStudentStudyProcessFace.setCreateTime(learnRec.getCreateTime());
				societyStudentStudyProcessFace.setStuCourseId(stuAndCourseId);
				societyStudentStudyProcessFace.setOwnerSchoolId(nodeView.getOwnerSchoolId());
				societyStudentStudyProcessFace.setOwnerSchoolName(nodeView.getOwnerSchoolName());
				societyStudentStudyProcessFace.setOwnerCourseId(nodeView.getOwnerCourseId());
				societyStudentStudyProcessFace.setOwnerCourseName(nodeView.getOwnerCourseName());
				societyStudentStudyProcessFace.setOwnerNodeId(nodeView.getId());
				societyStudentStudyProcessFace.setOwnerNodeName(nodeView.getNodeName());
				societyStudentStudyProcessFace.setStudentId(studentId);
				societyStudentStudyProcessFace.setStudentName(stuAndCourse.getStudentName());
				societyStudentStudyProcessFace.setFaceImageUrl("http://trainimg.qhd12328.com/130300"+learnRec.getFaceUrl());
				societyStudentStudyProcessFace.setFaceScore(learnRec.getFaceMatchScore());
				societyStudentStudyProcessFace.setFaceType("learn");
				studentStudyProcessFaceMapper.insert(societyStudentStudyProcessFace);
			}
		}
	}
	/**
	 *@Author:ZhaoSiDa
	 *@Description: 随机完成学员的人脸记录
	 *@DateTime: 2020/5/29 17:07
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void suiJiFinishedStuFaceRec(SocietyStudentAndCourseView stuAndCourse,int randNum) {
		/*学员和课程关联表ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
		int count = 0;
		int consumRecListSize = 0;
		int nodeViewListSize = 0;
		//根据身份证号查询出记录
		List<YwTrainConsumRec> consumRecList = courseNodeMapper.selectByIdCardNum(studentIdCardNum);
		if(consumRecList!=null){
			consumRecListSize = consumRecList.size();
		}
		//目前人脸记录和课程关联上
		//根据课程ID查询所有小节
		List<SocietySchoolCourseNodeView> nodeViewList = courseNodeMapper.listByCourseIdSuiJiNum(courseId,randNum);
		if(nodeViewList!=null){
			nodeViewListSize = nodeViewList.size();
		}
		if(nodeViewListSize>consumRecListSize){
			count = consumRecListSize;
		}else {
			count = nodeViewListSize;
		}
		for (int i=0;i<count;i++){
			String conRecId = consumRecList.get(i).getId();
			SocietySchoolCourseNodeView nodeView = nodeViewList.get(i);
			//根据学习记录id查询学习过程中的人脸记录
			List<YwTrainLearnRec> learnRecList = courseNodeMapper.selectByConRecId(conRecId);
			for (YwTrainLearnRec learnRec:learnRecList){
				//查询出所有的人脸记录
				SocietyStudentStudyProcessFace societyStudentStudyProcessFace = new SocietyStudentStudyProcessFace();
				societyStudentStudyProcessFace.setId(Guid.guid());
				societyStudentStudyProcessFace.setCreateTime(learnRec.getCreateTime());
				societyStudentStudyProcessFace.setStuCourseId(stuAndCourseId);
				societyStudentStudyProcessFace.setOwnerSchoolId(nodeView.getOwnerSchoolId());
				societyStudentStudyProcessFace.setOwnerSchoolName(nodeView.getOwnerSchoolName());
				societyStudentStudyProcessFace.setOwnerCourseId(nodeView.getOwnerCourseId());
				societyStudentStudyProcessFace.setOwnerCourseName(nodeView.getOwnerCourseName());
				societyStudentStudyProcessFace.setOwnerNodeId(nodeView.getId());
				societyStudentStudyProcessFace.setOwnerNodeName(nodeView.getNodeName());
				societyStudentStudyProcessFace.setStudentId(studentId);
				societyStudentStudyProcessFace.setStudentName(stuAndCourse.getStudentName());
				societyStudentStudyProcessFace.setFaceImageUrl("http://trainimg.qhd12328.com/130300"+learnRec.getFaceUrl());
				societyStudentStudyProcessFace.setFaceScore(learnRec.getFaceMatchScore());
				societyStudentStudyProcessFace.setFaceType("learn");
				studentStudyProcessFaceMapper.insert(societyStudentStudyProcessFace);
			}
		}
	}



	/**
	 *@Author:ZhaoSiDa
	 *@Description: 完成学员的考试记录
	 *@DateTime: 2020/6/1 10:16
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuTestPaper(SocietyStudentAndCourseView stuAndCourse) {
		/*学员和课程关联表ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
		//获取和课程关联的试卷
		SocietyTestPaper societyTestPaper = testPaperMapper.loadByCourseId(courseId);
		String testPaperId = societyTestPaper.getId();
		Map<String,String> map = getRandMapNew(30,societyTestPaper.getTotalQuestionNum());
		//获取试卷下面所有的试题
		List<SocietyTestPaperQuestionView> testPaperQuestionViewList = testPaperQuestionMapper.ListByTestPaperId(testPaperId);
		testPaperQuestionViewList.forEach(question ->{
			String questionId = question.getId();
			int index = testPaperQuestionViewList.indexOf(question);
			String answerState = "1";
			BigDecimal getScore = question.getQuestionScore();
			if(map.get(index+"")!=null) {
				answerState = "-1";
				getScore = new BigDecimal(0);
			}
				//判断学员是否关联该试题
				SocietyStudentTestPaperQuestion studentTestPaperQuestion =studentTestPaperQuestionMapper.loadByStuIdAndQuestionId(studentId,testPaperId,questionId);
				if(studentTestPaperQuestion==null){
					String stuQuestionId = Guid.guid();
					List<SocietyTestPaperQuestionOption> questionOptionList = testPaperQuestionOptionMapper.selectOptionByQuestionId(questionId);
					questionOptionList.forEach(questionOption ->{
						String questionOptionId = questionOption.getId();
						//判断是否已经有该选项
						SocietyStudentTestPaperQuestionOption studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuIdAndOptionId(studentId,testPaperId,questionOptionId);
						if(studentTestPaperQuestionOption==null){
							studentTestPaperQuestionOption = new SocietyStudentTestPaperQuestionOption();
							studentTestPaperQuestionOption.setId(Guid.guid());
							studentTestPaperQuestionOption.setCreateTime(new Date());
							studentTestPaperQuestionOption.setCreateUserId(studentId);
							studentTestPaperQuestionOption.setCreateUserName(stuAndCourse.getStudentName());
							studentTestPaperQuestionOption.setStudentId(studentId);
							studentTestPaperQuestionOption.setOwnerSchoolId(questionOption.getOwnerSchoolId());
							studentTestPaperQuestionOption.setOwnerSchoolName(questionOption.getOwnerSchoolName());
							studentTestPaperQuestionOption.setOwnerCourseId(questionOption.getOwnerCourseId());
							studentTestPaperQuestionOption.setOwnerCourseName(questionOption.getOwnerCourseName());
							studentTestPaperQuestionOption.setOwnerTestPaperId(questionOption.getOwnerTestPaperId());
							studentTestPaperQuestionOption.setOwnerTestPaperName(questionOption.getOwnerTestPaperName());
							studentTestPaperQuestionOption.setOwnerTestPaperQuestionId(stuQuestionId);
							studentTestPaperQuestionOption.setOptionName(questionOption.getOptionName());
							studentTestPaperQuestionOption.setOptionTitle(questionOption.getOptionTitle());
							studentTestPaperQuestionOption.setIsSelect("0");
							studentTestPaperQuestionOption.setOrderNum(questionOption.getOrderNum());
							studentTestPaperQuestionOption.setDataState("1");
							if(question.getQuestionType().equals("1")){
								if(question.getQuestionAnswer().contains(questionOption.getOptionTitle())){
									studentTestPaperQuestionOption.setIsRight("1");
								}else {
									studentTestPaperQuestionOption.setIsRight("0");
								}
							}else {
								studentTestPaperQuestionOption.setIsRight(questionOption.getIsRight());
							}
							studentTestPaperQuestionOptionMapper.insert(studentTestPaperQuestionOption);
							//System.out.println("societyStudentPractiseQuestOption====="+societyStudentPractiseQuestOption);
						}
					});
					studentTestPaperQuestion = new SocietyStudentTestPaperQuestion();
					studentTestPaperQuestion.setId(stuQuestionId);
					studentTestPaperQuestion.setCreateTime(new Date());
					studentTestPaperQuestion.setCreateUserId(studentId);
					studentTestPaperQuestion.setCreateUserName(stuAndCourse.getStudentName());
					studentTestPaperQuestion.setStudentId(studentId);
					studentTestPaperQuestion.setOwnerSchoolId(question.getOwnerSchoolId());
					studentTestPaperQuestion.setOwnerSchoolName(question.getOwnerSchoolName());
					studentTestPaperQuestion.setOwnerCourseId(question.getOwnerCourseId());
					studentTestPaperQuestion.setOwnerCourseName(question.getOwnerCourseName());
					studentTestPaperQuestion.setOwnerTestPaperId(question.getOwnerTestPaperId());
					studentTestPaperQuestion.setOwnerTestPaperName(question.getOwnerTestPaperName());
					studentTestPaperQuestion.setTestPaperQuestionId(questionId);
					studentTestPaperQuestion.setTestPaperQuestionName(question.getQuestionName());
					studentTestPaperQuestion.setTestPaperQuestionType(question.getQuestionType());
					studentTestPaperQuestion.setTestPaperQuestionScore(question.getTestPaperQuestionScore());
					studentTestPaperQuestion.setGetScore(getScore.intValue());
					studentTestPaperQuestion.setOrderNum(question.getOrderNum());
					//TODO  学员所选
					SocietyStudentTestPaperQuestionOption studentTestPaperQuestionOption = null;
					if(answerState.equals("1")){
						//查询出正确答案关联上，
						studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuQuestionId(stuQuestionId,"1");
						studentTestPaperQuestionOption.setIsSelect("1");
						studentTestPaperQuestionOptionMapper.updateById(studentTestPaperQuestionOption);
					}else {
						//查询出错误答案关联上，
						studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuQuestionId(stuQuestionId,"0");
						studentTestPaperQuestionOption.setIsSelect("1");
						studentTestPaperQuestionOptionMapper.updateById(studentTestPaperQuestionOption);
					}
					//studentTestPaperQuestion.setStuSelectOpId(studentTestPaperQuestionOption.getId());
					//studentTestPaperQuestion.setLastAnswerTime(new Date());
					studentTestPaperQuestion.setDataState("1");
					studentTestPaperQuestionMapper.insert(studentTestPaperQuestion);
				}else {
					//studentTestPaperQuestion.setAnswerState(answerState);
					studentTestPaperQuestion.setGetScore(getScore.intValue());
					studentTestPaperQuestion.setOrderNum(question.getOrderNum());
					//TODO  学员所选
					SocietyStudentTestPaperQuestionOption studentTestPaperQuestionOption = null;
					if(answerState.equals("1")){
						//查询出正确答案关联上，
						studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuQuestionId(studentTestPaperQuestion.getId(),"1");
						studentTestPaperQuestionOption.setIsSelect("1");
						studentTestPaperQuestionOptionMapper.updateById(studentTestPaperQuestionOption);
					}else {
						//查询出错误答案关联上，
						studentTestPaperQuestionOption = studentTestPaperQuestionOptionMapper.loadByStuQuestionId(studentTestPaperQuestion.getId(),"0");
						studentTestPaperQuestionOption.setIsSelect("1");
						studentTestPaperQuestionOptionMapper.updateById(studentTestPaperQuestionOption);
					}
					//studentTestPaperQuestion.setStuSelectOpId(societyStudentPractiseQuestOption.getId());
					//studentTestPaperQuestion.setLastAnswerTime(new Date());
					studentTestPaperQuestion.setDataState("1");
					studentTestPaperQuestionMapper.updateById(studentTestPaperQuestion);
				}
		});
		//统计答对题目个数
		int countOptionNum = studentTestPaperQuestionMapper.countRightQuestionNumByType(studentId,testPaperId,"1");
		int countOptionScore = countOptionNum*societyTestPaper.getDanxuanScore().intValue();
		int countJudgeNum = studentTestPaperQuestionMapper.countRightQuestionNumByType(studentId,testPaperId,"2");
		int countJudgeScore = countJudgeNum*societyTestPaper.getPanduanScore().intValue();
		//计算答题的正确率
		DecimalFormat df = new DecimalFormat("#.00");
		double rightRate =Double.valueOf(df.format( (double)(countOptionNum+countJudgeNum)/(double)societyTestPaper.getTotalQuestionNum()));

		//判断学员是否已经关联该试卷
		SocietyStudentTestPaper studentTestPaper = studentTestPaperMapper.loadByStuIdAndTestPaperId(studentId,testPaperId);
		if(studentTestPaper==null){
			studentTestPaper = new SocietyStudentTestPaper();
			studentTestPaper.setId(Guid.guid());
			studentTestPaper.setCreateTime(new Date());
			studentTestPaper.setCreateUserId(studentId);
			studentTestPaper.setCreateUserName(stuAndCourse.getStudentName());
			studentTestPaper.setStudentId(studentId);
			studentTestPaper.setStudentName(stuAndCourse.getStudentName());
			studentTestPaper.setStudentIdCardNum(stuAndCourse.getStudentIdCardNum());
			studentTestPaper.setOwnerSchoolId(societyTestPaper.getOwnerSchoolId());
			studentTestPaper.setOwnerSchoolName(societyTestPaper.getOwnerSchoolName());
			studentTestPaper.setOwnerCourseId(societyTestPaper.getOwnerCourseId());
			studentTestPaper.setOwnerCourseName(societyTestPaper.getOwnerCourseName());
			studentTestPaper.setOwnerTestPaperId(societyTestPaper.getId());
			studentTestPaper.setTestPaperName(societyTestPaper.getTestPaperName());
			studentTestPaper.setTestPaperTimeLength(societyTestPaper.getTestPaperTimeLength());
			studentTestPaper.setTotalQuestionNum(societyTestPaper.getTotalQuestionNum());
			studentTestPaper.setTotalScore(societyTestPaper.getTotalScore());
			studentTestPaper.setPassScore(societyTestPaper.getPassScore());
			studentTestPaper.setIsPass("1");
			studentTestPaper.setIsFinished("1");
			studentTestPaper.setDataState("1");
			studentTestPaper.setOptionRightNum(countOptionNum+"");
			studentTestPaper.setOptionScore(countOptionScore);
			studentTestPaper.setJudgeRightNum(countJudgeNum+"");
			studentTestPaper.setJudgeScore(countJudgeScore);
			studentTestPaper.setTestRightRate(new BigDecimal(rightRate*100));
			studentTestPaper.setGetScore(countOptionScore+countJudgeScore);
			studentTestPaperMapper.insert(studentTestPaper);
		}else {
			studentTestPaper.setOptionRightNum(countOptionNum+"");
			studentTestPaper.setOptionScore(countOptionScore);
			studentTestPaper.setJudgeRightNum(countJudgeNum+"");
			studentTestPaper.setJudgeScore(countJudgeScore);
			studentTestPaper.setGetScore(countOptionScore+countJudgeScore);
			studentTestPaper.setTestRightRate(new BigDecimal(rightRate*100));
			studentTestPaperMapper.updateById(studentTestPaper);
		}
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 完成学员的证书
	 *@DateTime: 2020/6/1 14:50
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void finishedStuCert(SocietyStudentAndCourseView stuAndCourse){
		/*学员和课程关联表ID*/
		String stuAndCourseId = stuAndCourse.getId();
		String courseId = stuAndCourse.getCourseId();
		String studentId = stuAndCourse.getStudentId();
		SocietySchoolCourse schoolCourse = schoolCourseService.loadById(courseId);
		//判断课程的证书是否生成
		SocietyStudentCertificate societyStudentCertificate = studentCertificateMapper.loadByStudentIdAndCourseId(studentId,courseId);
		if(societyStudentCertificate==null){
			societyStudentCertificate = new SocietyStudentCertificate();
			societyStudentCertificate.setId(Guid.guid());
			societyStudentCertificate.setCreateTime(new Date());
			societyStudentCertificate.setOwnerSchoolId(schoolCourse.getOwnerSchoolId());
			societyStudentCertificate.setOwnerSchoolName(schoolCourse.getOwnerSchoolName());
			societyStudentCertificate.setOwnerMajorId(schoolCourse.getOwnerMajorId());
			societyStudentCertificate.setOwnerMajorName(schoolCourse.getOwnerMajorName());
			societyStudentCertificate.setClassId(stuAndCourse.getClassId());
			SocietySchoolClass schoolClass = schoolClassService.loadById(stuAndCourse.getClassId());
			societyStudentCertificate.setClassName(schoolClass.getClassName());
			societyStudentCertificate.setCourseId(courseId);
			societyStudentCertificate.setCourseName(schoolCourse.getCourseName());
			societyStudentCertificate.setStudentId(studentId);
			societyStudentCertificate.setStudentName(stuAndCourse.getStudentName());
			societyStudentCertificate.setStudentIdCardNum(stuAndCourse.getStudentIdCardNum());
			//societyStudentCertificate.setClassAndStudentId(stuAndCourseId);
			societyStudentCertificate.setCertificateCode(Guid.guid());
			societyStudentCertificate.setCertificateName(schoolCourse.getCourseName()+"合格证书");
			societyStudentCertificate.setCertificateTime(new Date());
			societyStudentCertificate.setDataState("1");
			studentCertificateMapper.insert(societyStudentCertificate);
		}
	}
	//获取随机数
	public Map<String,String> getRandMap(int max){
		Map<String,String> map = new HashMap<>();
		int num = max/5;
		Random rand = new Random();
		for( int i=0; i<num; i++) {
			int rNum = rand.nextInt(max);
			map.put(rNum+"","");
		}
		return map;
	}
	//考试获取随机数
	public Map<String,String> getRandMapNew(int num,int total){
		Map<String,String> map = new HashMap<>();
		Random rand = new Random();
		int tNum = rand.nextInt(num);
		while (map.size()<tNum){
			int nNum = rand.nextInt(total);
			if(map.get(nNum+"")==null){
				map.put(nNum+"","");
			}
		}
		return map;
	}
	//获取一个随机数
	public int getRandNum(int num){
		Random rand = new Random();
		return rand.nextInt(num)+1;
	}


	/*public static void main(String[] args) {
		System.out.println(getRandMapNew(30,100));
	}*/




	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietySchoolClassAndStudent loadById(String id) {
		return societySchoolClassAndStudentMapper.selectById(id);
	}

	public int selectVoClassId(String classId) {
		return societySchoolClassAndStudentMapper.selectVoClassId(classId);
	}

	public int selectVoClassAndStudentName(String classId, String studentName, String studentIdCardNum) {
		return societySchoolClassAndStudentMapper.selectVoClassAndStudentName(classId,studentName,studentIdCardNum);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String relationStuAndClass(List<SocietyStudent> studentList, String classId) {
		String err = "";
		SocietySchoolClass societySchoolClass = schoolClassService.loadById(classId);
		List<String> courseIdList = classAndCourseMapper.selectCourseIdByClass(classId);
		for(SocietyStudent societyStudent : studentList){
			SocietyStudent student = studentMapper.selectById(societyStudent.getId());
			if(student != null){
				//插入学员与课程的关联
				String err1 = insertCourseAndStudentRelation(student,courseIdList,classId);
				if(err1.equals("")){
					//插入学员与班级的关联
					insertClassAndStudentRelation(student,societySchoolClass);
					//更新 学员课程的班级关联id
					String classAndStudentId =
							societySchoolClassAndStudentMapper.selectByClassIdAndStudentIdAndSchoolId
									(societySchoolClass.getId(), student.getId(),societySchoolClass.getOwnerSchoolId());
					studentAndCourseMapper.updateByClassAndStudentId(societySchoolClass.getId(),
							student.getId(),societySchoolClass.getOwnerSchoolId(),classAndStudentId);
				}else {
					if(err.equals("")){
						err = err1;
					}else {
						err = err+"，"+err1;
					}
				}
			}
		}
		//更新一下班级学员数和未完成人数
		//统计该班级的人数
		int totalStuNum = societySchoolClassAndStudentMapper.countStudentByClassId(classId);
		societySchoolClass.setClassPersonNum(totalStuNum);
		//统计课程的培训人数
		for(String courseId:courseIdList){
			SocietySchoolCourse course = schoolCourseService.loadById(courseId);
			//统计课程的培训人数
			int countTotalStuNum = studentAndCourseMapper.countTotalStuNum(courseId);
			course.setTotalStudentNum(countTotalStuNum);
			schoolCourseService.update(course);
		}

		schoolClassService.update(societySchoolClass);
		return err;
	}

	public List<String> selectBySchoolIdAndClassId(String schoolId, List<String> classId) {
		return societySchoolClassAndStudentMapper.selectBySchoolIdAndClassId(schoolId,classId);
	}

	public List<SocietySchoolClassAndStudent> selectBySchoolIdVoClassId(String classId, String schoolId) {
		return societySchoolClassAndStudentMapper.selectBySchoolIdVoClassId(classId,schoolId);
	}

	public int selectBySchoolIdVoClassIdOne(String classId, String studentOldId) {
		return societySchoolClassAndStudentMapper.selectBySchoolIdVoClassIdOne(classId,studentOldId);
	}

	public List<SocietySchoolClassAndStudentView> selectByStuIdAndSchoolIdlistPage(SocietySchoolClassAndStudentQuery query) {
		List list = societySchoolClassAndStudentMapper.selectByStuIdAndSchoolIdlistPage(query);
		query.setList(list);
		return list;
	}

	public List<SocietySchoolClassAndStudentView> selectByClassIdStuIdAndSchoolIdlistPage(SocietySchoolClassAndStudentQuery query) {
		List list = societySchoolClassAndStudentMapper.selectByClassIdStuIdAndSchoolIdlistPage(query);
		query.setList(list);
		return list;
	}

	public void deleteByStuId(String id) {
		societySchoolClassAndStudentMapper.deleteByStuId(id);
	}

	public void updateByStuId(Map<String, String> map) {
		societySchoolClassAndStudentMapper.updateByStuId(map);
	}

	public List<SocietySchoolClassAndStudentView> selectByAll() {
		return societySchoolClassAndStudentMapper.selectByAll();
	}

	public void updateByMajorId(String majorId, String majorName) {
		societySchoolClassAndStudentMapper.updateByMajorId(majorId,majorName);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseId(Map<String, String> map) {
		societySchoolClassAndStudentMapper.updateByCourseId(map);
	}

	public Integer selectCountByClassId(String classId) {
		return societySchoolClassAndStudentMapper.selectCountByClassId(classId);
	}

	public List<String> selectByRandStuId(String classId) {
		return societySchoolClassAndStudentMapper.selectByRandStuId(classId);
	}

	public List<String> selectByClassIdRandTenStudent(String classId) {
		return societySchoolClassAndStudentMapper.selectByClassIdRandTenStudent(classId);
	}

	public List<String> selectByListClassId(List<String> classId) {
		return societySchoolClassAndStudentMapper.selectByListClassId(classId);
	}

	public List<String> selectByStudentIdVoClassId(String stuId) {
		return societySchoolClassAndStudentMapper.selectByStudentIdVoClassId(stuId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByIdList(List<SocietySchoolClassAndStudentView> stuList) {
		societySchoolClassAndStudentMapper.updateByIdList(stuList);
	}
}
