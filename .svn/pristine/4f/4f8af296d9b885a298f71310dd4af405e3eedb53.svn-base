package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.StringTool;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietyNodeExamineRecordMapper;
import com.edgedo.society.mapper.SocietySchoolClassMapper;
import com.edgedo.society.mapper.SocietyStudentAndCourseMapper;
import com.edgedo.society.mapper.SocietyStudentAndNodeMapper;
import com.edgedo.society.queryvo.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentAndCourseService {
	
	
	@Autowired
	private SocietyStudentAndCourseMapper societyStudentAndCourseMapper;
	@Autowired
	private SocietySchoolCourseService courseService;
	@Autowired
	private SocietyStudentAndNodeMapper studentAndNodeMapper;
	@Autowired
	private SocietySchoolCourseNodeService nodeService;
	@Autowired
	private SocietyStudentService societyStudentService;
	@Autowired
	private SocietyStudentTestPaperService societyStudentTestPaperService;
	@Autowired
	private SocietySchoolClassMapper societySchoolClassMapper;
	@Autowired
	private SocietyNodeExamineRecordMapper societyNodeExamineRecordMapper;
	@Autowired
	private SocietySchoolClassAndCourseService societySchoolClassAndCourseService;
	@Autowired
	private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;
	public List<SocietyStudentAndCourseView> listPage(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.listPage(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//学生手机号  是否实名认证
			SocietyStudent societyStudent = societyStudentService.loadById(stuAndCourse.getStudentId());
			if(societyStudent.getIsRealNameAuth()==null){
				stuAndCourse.setIsRealName("0");
			}else {
				stuAndCourse.setIsRealName(societyStudent.getIsRealNameAuth());
			}
			stuAndCourse.setStuPhone(societyStudent.getStudentPhoneNum());
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
			//身份证号加密
			/*String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
			stuAndCourse.setStudentIdCardNum(StringTool.encodeImportentNum(studentIdCardNum));*/
		});
		if (societyStudentAndCourseQuery.getIsRealNameAuth()!=null){
			Collections.sort(list, new Comparator<SocietyStudentAndCourseView>() {
				@Override
				public int compare(SocietyStudentAndCourseView o1, SocietyStudentAndCourseView o2) {
					return new Integer(o1.getIsRealName())-new Integer(o2.getIsRealName());
				}
			});
		}
		societyStudentAndCourseQuery.setList(list);
		return list;
	}

	public Integer selectByCourseStudyNum(String courseId){
		return societyStudentAndCourseMapper.selectByCourseStudyNum(courseId);
	}

	public List<SocietyStudentAndCourseView> forclassadminlistPage(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.forclassadminlistPage(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
			//身份证号加密
			/*String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
			stuAndCourse.setStudentIdCardNum(StringTool.encodeImportentNum(studentIdCardNum));*/
		});
		societyStudentAndCourseQuery.setList(list);
		return list;
	}

	public List<SocietyStudentAndCourseView> listPageByStuCou(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.listPage(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
		});
		societyStudentAndCourseQuery.setList(list);
		return list;
	}

	public List<SocietyStudentAndCourseView> selectBySupStudentProAndClass(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.listPage(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
			SocietyStudent societyStudent = societyStudentService.loadById(stuAndCourse.getStudentId());
			stuAndCourse.setStudentPohone(societyStudent.getStudentPhoneNum());
		});
		societyStudentAndCourseQuery.setList(list);
		return list;
	}

	public List<SocietyStudentAndCourseView> selectBySchoolAndClasslistPage(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.selectBySchoolAndClasslistPage(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//学生手机号  是否实名认证
			SocietyStudent societyStudent = societyStudentService.loadById(stuAndCourse.getStudentId());
			if(societyStudent.getIsRealNameAuth()==null){
				stuAndCourse.setIsRealName("0");
			}else {
				stuAndCourse.setIsRealName(societyStudent.getIsRealNameAuth());
			}
			stuAndCourse.setStuPhone(societyStudent.getStudentPhoneNum());
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
			//补充学校
			stuAndCourse.setSchoolName(societyStudent.getOwnerSchoolName());
		});
		Collections.sort(list, new Comparator<SocietyStudentAndCourseView>() {
			@Override
			public int compare(SocietyStudentAndCourseView o1, SocietyStudentAndCourseView o2) {
				return new Integer(o1.getIsRealName())-new Integer(o2.getIsRealName());
			}
		});
		societyStudentAndCourseQuery.setList(list);
		return list;
	}

	public List<SocietyStudentAndCourseView> listByObj(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.listByObj(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
			/*//身份证号加密
			String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
			stuAndCourse.setStudentIdCardNum(StringTool.encodeImportentNum(studentIdCardNum));*/
		});
		societyStudentAndCourseQuery.setList(list);
		return list;
	}


	public List<SocietyStudentAndCourseView> listByExcObj(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.listByObj(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
			//身份证号加密
			String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
//			stuAndCourse.setStudentIdCardNum(StringTool.encodeImportentNum(studentIdCardNum));
			stuAndCourse.setStudentIdCardNum(studentIdCardNum);
			//手机号
			SocietyStudent societyStudent = societyStudentService.loadById(stuAndCourse.getStudentId());
			stuAndCourse.setStuPhone(societyStudent.getStudentPhoneNum());
			stuAndCourse.setIsComment("否");
			if(societyStudent.getIsRealNameAuth()==null || societyStudent.getIsRealNameAuth().equals("0")){
				stuAndCourse.setIsRealName("否");
			}else {
				stuAndCourse.setIsRealName("是");
			}
			//档案编号
			String classId = stuAndCourse.getClassId();
			SocietySchoolClassAndStudentView societySchoolClassAndStudentView =
					societySchoolClassAndStudentService.selectVoByClassAndStudent(classId,societyStudent.getId());
			stuAndCourse.setArchivesCode(societySchoolClassAndStudentView.getArchivesCode());
			//是否完成考试
			String isFinished = societyStudentTestPaperService.selectByStuIdAndCourseIdAndSchoolId
					(stuAndCourse.getCourseId(),stuAndCourse.getStudentId(),stuAndCourse.getOwnerSchoolId());
			if(isFinished==null || isFinished.equals("0")){
				stuAndCourse.setIsTepar("无");
			}else {
				stuAndCourse.setIsTepar("是");
			}
			if (stuAndCourse.getLearnIsFinished().equals("1") && stuAndCourse.getCourseProgress().toString().equals("100.00")){
				stuAndCourse.setLearnIsFinished("已完成");
			}else {
				if(stuAndCourse.getCourseProgress() == null|| stuAndCourse.getCourseProgress().toString().equals("0.00")){//
					stuAndCourse.setLearnIsFinished("未学习");
				}else {
					stuAndCourse.setLearnIsFinished("学习中");
				}
			}
		});
		societyStudentAndCourseQuery.setList(list);
		return list;
	}

	public List<SocietyStudentAndCourseView> selectByNodeIsFiled(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.listByObjIsFiled(societyStudentAndCourseQuery);
		List<SocietyStudentAndCourseView> listNew = new ArrayList<>();
		list.forEach(stuAndCourse ->{
			//查询班级信息
			SocietySchoolClass societySchoolClass = societySchoolClassMapper.selectById(stuAndCourse.getClassId());
			//查出每个学生对应的章节下是否存在 不合格章节
			List<SocietyStudentAndNodeView> nodeStuList =
					studentAndNodeMapper.selectByStuIdAndCourseIdIsFiled(stuAndCourse.getId());
			if (nodeStuList.size()!=0){
				for(SocietyStudentAndNodeView societyStudentAndNodeView : nodeStuList){
					SocietyStudentAndCourseView societyStudentAndCourseView = new SocietyStudentAndCourseView();
					societyStudentAndCourseView.setStudentName(societyStudentAndNodeView.getStudentName());
					societyStudentAndCourseView.setStudentIdCardNum(societyStudentAndNodeView.getStudentIdCardNum());
					societyStudentAndCourseView.setCourseName(societyStudentAndNodeView.getOwnerCourseName());
					societyStudentAndCourseView.setNodeName(societyStudentAndNodeView.getNodeName());
					societyStudentAndCourseView.setExamineState("失败");
					String reason = societyNodeExamineRecordMapper.selectByNodeId(societyStudentAndNodeView.getId());
					societyStudentAndCourseView.setExamineReson(reason);
					societyStudentAndCourseView.setClassName(societySchoolClass.getClassName());
					societyStudentAndCourseView.setClassCode(societySchoolClass.getClassCode());
					listNew.add(societyStudentAndCourseView);
				}
			}
		});
		societyStudentAndCourseQuery.setList(listNew);
		return list;
	}


	public List<SocietyStudentAndCourseView> listByExcObjClassAdmin(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.listByExcObjClassAdmin(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
			//身份证号加密
			String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
			stuAndCourse.setStudentIdCardNum(StringTool.encodeImportentNum(studentIdCardNum));
		});
		societyStudentAndCourseQuery.setList(list);
		return list;
	}


	public List<SocietyStudentAndCourseView> listByExcRenSheObj(SocietyStudentAndCourseQuery societyStudentAndCourseQuery){
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.listByExcRenSheObj(societyStudentAndCourseQuery);
		list.forEach(stuAndCourse ->{
			//查询课程的章节数不对的话更新
			String courseId = stuAndCourse.getCourseId();
			int sumNodeNum = nodeService.countByCourseId(courseId);
			stuAndCourse.setTotalLessons(sumNodeNum);
			//身份证号加密
			String studentIdCardNum = stuAndCourse.getStudentIdCardNum();
			stuAndCourse.setStudentIdCardNum(StringTool.encodeImportentNum(studentIdCardNum));
			//手机号
			SocietyStudent societyStudent = societyStudentService.loadById(stuAndCourse.getStudentId());
			stuAndCourse.setStuPhone(societyStudent.getStudentPhoneNum());
			stuAndCourse.setIsComment("否");
			if(societyStudent.getIsRealNameAuth()==null || societyStudent.getIsRealNameAuth().equals("0")){
				stuAndCourse.setIsRealName("否");
			}else {
				stuAndCourse.setIsRealName("是");
			}
			//是否完成考试
			String isFinished = societyStudentTestPaperService.selectByStuIdAndCourseIdAndSchoolId
					(stuAndCourse.getCourseId(),stuAndCourse.getStudentId(),stuAndCourse.getOwnerSchoolId());
			if(isFinished==null || isFinished.equals("0")){
				stuAndCourse.setIsTepar("无");
			}else {
				stuAndCourse.setIsTepar("是");
			}
			if (stuAndCourse.getLearnIsFinished().equals("1") && stuAndCourse.getCourseProgress().toString().equals("100.00")){
				stuAndCourse.setLearnIsFinished("已完成");
			}else {
				if(stuAndCourse.getCourseProgress() == null|| stuAndCourse.getCourseProgress().toString().equals("0.00")){//
					stuAndCourse.setLearnIsFinished("未学习");
				}else {
					stuAndCourse.setLearnIsFinished("学习中");
				}
			}
			//补充学校名称
			stuAndCourse.setSchoolName(societyStudent.getOwnerSchoolName());
		});
		societyStudentAndCourseQuery.setList(list);
		return list;
	}

	public List selectBySchoolNamelistPage(SocietyStudentAndCourseQuery societySchoolClassAndCourseQuery) {
		List<SocietyStudentAndCourseView> list = societyStudentAndCourseMapper.selectBySchoolNamelistPage(societySchoolClassAndCourseQuery);
		//查询课程的时长和学员的学习时长
		list.forEach(stuAndCourse ->{
			String stuAndCourseId = stuAndCourse.getId();
			String courseId = stuAndCourse.getCourseId();
			SocietySchoolCourse schoolCourse =courseService.loadById(courseId);
			stuAndCourse.setCourseTimeLength(schoolCourse.getCourseTimeLength());
			//统计学员的课程所学的时长
			int studyTimeLength = studentAndNodeMapper.sumStudyTimeLength(stuAndCourseId);
			stuAndCourse.setStudyTimeLength(studyTimeLength);
		});
		societySchoolClassAndCourseQuery.setList(list);
		return list;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentAndCourse societyStudentAndCourse) {
		societyStudentAndCourse.setId(Guid.guid());
		societyStudentAndCourseMapper.insert(societyStudentAndCourse);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentAndCourse societyStudentAndCourse) {
		societyStudentAndCourseMapper.updateById(societyStudentAndCourse);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentAndCourse societyStudentAndCourse) {
		societyStudentAndCourseMapper.updateAllColumnById(societyStudentAndCourse);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentAndCourseMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentAndCourseMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentAndCourse loadById(String id) {
		return societyStudentAndCourseMapper.selectById(id);
	}


	public String selectByOwnerStudentAndNodeId(String ownerStudentAndNodeId) {
		return societyStudentAndCourseMapper.selectByOwnerStudentAndNodeId(ownerStudentAndNodeId);
	}


	/*批量插入关联*/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public void insertCourseAndStudent(List<SocietyStudentAndCourse> studentAndCourseList) {
		societyStudentAndCourseMapper.insertCourseAndStudent(studentAndCourseList);
    }

	//统计课程的培训人
    public int countByCourseId(String courseId) {
		return societyStudentAndCourseMapper.countByCourseId(courseId);
    }

	//删除该该班级所有学生和课程的关联
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByClassIdAndCourseId(List<String> list, String classId) {
		societyStudentAndCourseMapper.updateByClassIdAndCourseId(list,classId);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学员和课程查询一条关联
	 * @Date 2020/6/1 10:13
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietyStudentAndCourseView selectVoByStudentAndCourse(String studentId,String courseId){
		return societyStudentAndCourseMapper.selectVoByStudentAndCourse(studentId,courseId);
	}

	public String selectStuAndClassId(String classId, String studentId) {
		return societyStudentAndCourseMapper.selectStuAndClassId(classId,studentId);
	}

	public SocietyStudentAndCourse selectByStudentIdAndClassId(String classId, String studentId,String schoolId) {
		return societyStudentAndCourseMapper.selectByStudentIdAndClassId(classId,studentId,schoolId);
	}

	public SocietyStudentAndCourse findStudentAndCourseByClassIdAndStudentId(Map<String,String> map) {
		return societyStudentAndCourseMapper.findStudentAndCourseByClassIdAndStudentId(map);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int countFinishedClassStuNum(String classId) {
		return societyStudentAndCourseMapper.countFinishedClassStuNum(classId);
	}

	public void deleteByStuId(String id) {
		societyStudentAndCourseMapper.deleteByStuId(id);
	}

	public void updateByStuId(Map<String, String> map) {
		societyStudentAndCourseMapper.updateByStuId(map);
	}

	public int countCourseStuNum(String schoolId) {
		return societyStudentAndCourseMapper.countCourseStuNum(schoolId);
	}

	public List<SocietyStudentAndCourse> countIsNotCertifi() {
		return societyStudentAndCourseMapper.countIsNotCertifi();
	}

	public List<SocietyStudentAndCourseView> selectByStuIdAndSchoolId(String studentId, String schoolId) {
		return societyStudentAndCourseMapper.selectByStuIdAndSchoolId(studentId,schoolId);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateById(SocietyStudentAndCourse societyStudentAndCourse) {
		societyStudentAndCourseMapper.updateOnceId(societyStudentAndCourse);
	}

	public void updateByMajorId(String majorId, String majorName) {
		societyStudentAndCourseMapper.updateByMajorId(majorId,majorName);
	}

	public SocietyStudentAndCourseView selectSchAndClaAndCouAndStu(String schoolId, String classId, String courseId, String studentId) {
		return societyStudentAndCourseMapper.selectSchAndClaAndCouAndStu(schoolId,classId,courseId,studentId);
	}

	public Integer selectByCourseIdAndSchoolId(String schoolId, String ownerCourseId) {
		return societyStudentAndCourseMapper.selectByCourseIdAndSchoolId(schoolId,ownerCourseId);
	}

	public Integer selectByUseCourseIdAndSchoolId(String schoolId, String ownerCourseId) {
		return societyStudentAndCourseMapper.selectByUseCourseIdAndSchoolId(schoolId,ownerCourseId);
	}

	public Integer selectNodeTime(SocietySchoolView societySchoolView) {
		return societyStudentAndCourseMapper.selectNodeTime(societySchoolView);
	}


	/**
	 * 根据已有id 修改学生课程关联表记录
	 * @param stuCourseId
	 * @param nodeId
	 * @param ownerCourseId
	 * @param studentId
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateStudentCourseInfo(String stuCourseId, String nodeId, String ownerCourseId, String studentId) {
		SocietyStudentAndCourse societyStudentAndCourse = loadById(stuCourseId);
		// 删减学习进度
			//1.根据所有小节求和相除
		//开始设置学生本次课程的学习进度
		//拿到课程中的课程章节数
		SocietySchoolCourse course = courseService.loadById(ownerCourseId);
		int totalCount = course.getTotalLessons();
		//统计已经完结的章节数据
		int finishCount = studentAndNodeMapper.countFinishNodeOfStuCourse(stuCourseId);
		societyStudentAndCourse.setCourseProgress(new BigDecimal(finishCount*100.0/totalCount));

		// 上一次学习章节id
		societyStudentAndCourse.setLastLearnNodeId(nodeId);
		// 更新完成状态
		societyStudentAndCourse.setLearnIsFinished("0");
		// 完成小节
		societyStudentAndCourse.setFinishNodeNum(finishCount);

		update(societyStudentAndCourse);

		// 完成学习时间 清空
		societyStudentAndCourseMapper.updateFinishTime(stuCourseId);
	}

	public List<SocietyStudentAndCourseView> selectByClassId(String classId) {
		return societyStudentAndCourseMapper.selectByClassId(classId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseId(Map<String, Object> map) {
		//1.查出所有的学生课程关联记录
		//2.学生表查出各个学生类型
		String courseId = (String)map.get("courseId");
		String schoolId = (String)map.get("schoolId");
		List<String> ordStuId = societyStudentService.selectByOrdAndCourseId(courseId,schoolId);
		List<String> impStuId = societyStudentService.selectByImpAndCourseId(courseId,schoolId);
		List<String> compStuId = societyStudentService.selectByCompAndCourseId(courseId,schoolId);
		//3.判断
		Integer ordStuCourseStudyNum = (Integer) map.get("ordStuCourseStudyNum");
		Integer impStuCourseStudyNum = (Integer) map.get("impStuCourseStudyNum");
		Integer compStuCourseStudyNum = (Integer) map.get("compStuCourseStudyNum");
		String faceNum = (String)map.get("faceNum");
		if (ordStuId.size()!=0){
			societyStudentAndCourseMapper.updateByOrdStu(ordStuId,courseId,ordStuCourseStudyNum,faceNum);
		}
		if (impStuId.size()!=0){
			societyStudentAndCourseMapper.updateByOrdStu(impStuId,courseId,impStuCourseStudyNum,faceNum);
		}
		if (compStuId.size()!=0){
			societyStudentAndCourseMapper.updateByOrdStu(compStuId,courseId,compStuCourseStudyNum,faceNum);
		}
		//课程id 查出所有关联班级
		List<String> classIdList = societySchoolClassAndCourseService.selectByCourseId(courseId);
		//查询没有学生类型的学生
		for(String classId : classIdList){
			SocietySchoolClass societySchoolClass = societySchoolClassMapper.selectById(classId);
			Integer studyNum = societySchoolClass.getClassCourseNum();
			if(studyNum == null){
				studyNum = 0;
			}
			List<String> stuId = societyStudentService.selectByOrdNullAndCourseId(courseId,schoolId,classId);
			if(stuId.size()!=0){
				societyStudentAndCourseMapper.updateByOrdStu(stuId,courseId,studyNum,faceNum);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByClassIdList(List<SocietyStudentAndCourseView> list, SocietySchoolClass societySchoolClass) {
		societyStudentAndCourseMapper.updateByClassIdList(list,societySchoolClass);
	}

	public int selectByCourseIdAndTime(String courseId,String monthStartDay ,String monthEndDay) {
		Map<String,String> map = new HashMap<>();
		map.put("courseId",courseId);
		map.put("monthStartDay",monthStartDay);
		map.put("monthEndDay",monthEndDay);
		return societyStudentAndCourseMapper.selectByCourseIdAndTime(map);
	}

	public int selectByCourseIdAndTimeNew(String courseId) {
		Map<String,String> map = new HashMap<>();
		return societyStudentAndCourseMapper.selectByCourseIdAndTimeNew(courseId);
	}

	public List<SocietyStudentAndCourseView> selectByCourseIdAndSchoolId2(String courseIdOld, String schoolId) {
		return societyStudentAndCourseMapper.selectByCourseIdAndSchoolId2(courseIdOld,schoolId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateList(List<SocietyStudentAndCourseView> stuCouList) {
		societyStudentAndCourseMapper.updateList(stuCouList);
	}

	public List<SocietyStudentAndCourseView> selectByClassIdVoFinish(String classId) {
		return societyStudentAndCourseMapper.selectByClassIdVoFinish(classId);
	}

	public List<SocietyStudentAndCourseView> selectByClassIdVoNoFinish(String classId) {
		return societyStudentAndCourseMapper.selectByClassIdVoNoFinish(classId);
	}
}
