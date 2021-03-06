package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.dataenum.SchoolConfigKeyEnum;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolClassAndCourseMapper;
import com.edgedo.society.mapper.SocietySchoolClassAndStudentMapper;
import com.edgedo.society.mapper.SocietySchoolClassMapper;
import com.edgedo.society.queryvo.SocietySchoolClassQuery;
import com.edgedo.society.queryvo.SocietySchoolClassView;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import com.edgedo.sys.entity.Dtree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolClassService {
	
	
	@Autowired
	private SocietySchoolClassMapper societySchoolClassMapper;
	@Autowired
	private SocietySchoolClassAndStudentMapper classAndStudentMapper;
	@Autowired
	private SocietySchoolClassAndCourseMapper classAndCourseMapper;
	@Autowired
	private SocietySchoolCourseService schoolCourseService;
	@Autowired
	private SocietySchoolConfigKeyService schoolConfigKeyService;
	@Autowired
	private SocietySchoolConfigService schoolConfigService;
	@Autowired
	private SocietySchoolCourseNodeService nodeService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;

	public List<SocietySchoolClassView> listPage(SocietySchoolClassQuery societySchoolClassQuery){
		List<SocietySchoolClassView> list = societySchoolClassMapper.listPage(societySchoolClassQuery);
		list.forEach(classView->{
			String classId = classView.getId();
			String schoolId = classView.getOwnerSchoolId();
			//统计班级人数
			int countStudentByClassId = classAndStudentMapper.countStudentByClassId(classId);
			classView.setClassPersonNum(countStudentByClassId);
			//统计完成培训的人数
			int finishedStuNum = societyStudentAndCourseService.countFinishedClassStuNum(schoolId,classId);
			//查询班级关联的课程
			String courseId = classAndCourseMapper.selectByClassId(classId);
			if(courseId!=null && !"".equals(courseId)){
				SocietySchoolCourse schoolCourse = schoolCourseService.loadById(courseId);
				classView.setCourseId(courseId);
				classView.setCourseName(schoolCourse.getCourseName());
				classView.setFinishedPersonNum(finishedStuNum);
				int countByCourseId = nodeService.countByCourseId(courseId);
				classView.setTotalLessons(countByCourseId);
			}
		});
		societySchoolClassQuery.setList(list);
		return list;
	}
	public List<SocietySchoolClassView> listByObj(SocietySchoolClassQuery societySchoolClassQuery){
		List list = societySchoolClassMapper.listByObj(societySchoolClassQuery);
		societySchoolClassQuery.setList(list);
		return list;
	}

	public List<SocietySchoolClassView> examineClasslistPage(SocietySchoolClassQuery societySchoolClassQuery){
		List<SocietySchoolClassView> list = societySchoolClassMapper.examineClasslistPage(societySchoolClassQuery);
		list.forEach(classView->{
			String classId = classView.getId();
			String schoolId = classView.getOwnerSchoolId();
			//统计完成培训的人数
			int finishedStuNum = classAndStudentMapper.countFinishedStuNum(schoolId,classId);
			classView.setFinishedPersonNum(finishedStuNum);
		});
		societySchoolClassQuery.setList(list);
		return list;
	}

	/***
	 * 统计数量
	 * @return
	 */
	public Integer count(SocietySchoolClassQuery query) {
		int num = societySchoolClassMapper.count(query);
		return num;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolClass societySchoolClass) {
		societySchoolClassMapper.insert(societySchoolClass);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolClass societySchoolClass) {
		societySchoolClassMapper.updateById(societySchoolClass);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolClass societySchoolClass) {
		societySchoolClassMapper.updateAllColumnById(societySchoolClass);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolClassMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolClassMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolClass loadById(String id) {
		return societySchoolClassMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 逻辑批量删除
	 * @Date 2020/5/4 16:54
	 **/
	public void logicDelete(List<String> list){
		//删除班级信息
		societySchoolClassMapper.logicDelete(list);
		//删除班级课程关联信息
		classAndCourseMapper.logicDelete(list);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据专业信息查询所有的班级信息
	 * @Date 2020/5/4 14:00
	 **/
	public List<SocietySchoolClassView> listAllByMajorId(String ownerMajorId){
		return societySchoolClassMapper.listAllByMajorId(ownerMajorId);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询是否有没有结束的班级
	 * @Date 2020/5/4 19:13
	 **/
	public int countByMajorAndNotEnd(String ownerMajorId){
		return societySchoolClassMapper.countByMajorAndNotEnd(ownerMajorId);
	}

	//班级统计
	public int countClassAllNum() {
		return societySchoolClassMapper.countClassAllNum();
	}

	public int countXianQuClassAllNum(String xianquId) {
		return societySchoolClassMapper.countXianQuClassAllNum( xianquId);
	}

	/**
	 * 根据学校id查出对应的班级组成树
	 * @param schoolId
	 * @return
	 */
	public List<Dtree> listSchoolClassDtree(String schoolId) {
		List<SocietySchoolClassView> societySchoolClassViewList = societySchoolClassMapper.listBySchoolId(schoolId);
		List<Dtree> dtreeList = new ArrayList<>();
		for (SocietySchoolClassView societySchoolClassView:societySchoolClassViewList){
			Dtree dtree = new Dtree();
			dtree.setId(societySchoolClassView.getId());
			dtree.setTitle(societySchoolClassView.getClassName());
			dtree.setParentId("school_"+schoolId);
			dtree.setSpread(false);
			dtree.setLast(false);
			dtreeList.add(dtree);
		}
		return null;
	}
	/*根据班级编码统计*/
	public int countByClassCode(String classCode) {
		return societySchoolClassMapper.countByClassCode(classCode);
	}

	public List<String> selectByDateStr(String dateStr) {
		return societySchoolClassMapper.selectByDateStr(dateStr);
	}

	public SocietySchoolClass selectByClassCode(String classCode) {
		return societySchoolClassMapper.selectByClassCode(classCode);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 开班申请优化
	 *@DateTime: 2020/7/8 23:45
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateExamineState(String id,String schoolId) {
		//先从学校配置里获取开班申请的标识，找不到的话从公共配置获取
		String classApplyState = schoolConfigService.getSchoolConfigValue(schoolId, SchoolConfigKeyEnum.CLASS_APPLY_STATE+"");
		if("".equals(classApplyState.trim())){
			//默认自动通过
			classApplyState = "1";
		}
		societySchoolClassMapper.updateExamineState(id,classApplyState);
	}

	public void updateAdminExamineState(String classId, String examineState) {
		societySchoolClassMapper.updateAdminExamineState(classId,examineState);
	}

	/**
	 * 根据班主任id 查出相关连的班级id
	 * @param classAdminId
	 * @return
	 */
	public List<String> selectByClassAdminId(String classAdminId) {
		return societySchoolClassMapper.selectByClassAdminId(classAdminId);
	}

	public List<SocietySchoolClass> selectByClassAdminIdVoObj(String id) {
		return societySchoolClassMapper.selectByClassAdminIdVoObj(id);
	}

	public List<SocietySchoolClass> selectByMajorId(String ownerMajorId) {
		return societySchoolClassMapper.selectByMajorId(ownerMajorId);
	}

	public void updateByMajorId(String majorId, String majorName) {
		societySchoolClassMapper.updateByMajorId(majorId,majorName);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseId(Map<String, String> map) {
		societySchoolClassMapper.updateByCourseId(map);
	}

	public List<String> selectByClassAdminIdList(List<String> classAdminId) {
		return societySchoolClassMapper.selectByClassAdminIdList(classAdminId);
	}

	public String selectByClassAdminIdOnceClass(String classAdminId) {
		return societySchoolClassMapper.selectByClassAdminIdOnceClass(classAdminId);
	}

	public List<SocietySchoolClass> selectByClassList() {
		return societySchoolClassMapper.selectByClassList();
	}
}
