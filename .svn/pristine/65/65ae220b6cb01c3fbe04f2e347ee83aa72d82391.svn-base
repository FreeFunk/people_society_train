package com.edgedo.society.service;
		
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.mapper.SocietySchoolMapper;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import com.edgedo.society.queryvo.SocietySchoolQuery;
import com.edgedo.society.queryvo.SocietySchoolView;
import com.edgedo.sys.entity.Dtree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolService {
	
	
	@Autowired
	private SocietySchoolMapper societySchoolMapper;

	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Autowired
	private SocietySchoolCourseUseGlobleService societySchoolCourseUseGlobleService;

	public List<SocietySchoolView> listPage(SocietySchoolQuery societySchoolQuery){
		List list = societySchoolMapper.listPage(societySchoolQuery);
		societySchoolQuery.setList(list);
		return list;
	}

	public List<SocietySchoolView> listPageCourseCount(SocietySchoolQuery societySchoolQuery){
		//遍历所有学校
		List<SocietySchool> listShool = selectByAll();
		SocietySchoolCourse societySchoolCourse =
				societySchoolCourseService.loadById(societySchoolQuery.getQueryObj().getOwnerCourseId());
		List<String> listSchoolId = new ArrayList<>();
		for(SocietySchool societySchool : listShool){
			//查询 该课程是否关联学校
			int index =
					societySchoolCourseService.selectSchoolIdAndCourseId(societySchool.getId(),societySchoolCourse.getId());
			//查询是否有公共课
			int index1 =
					societySchoolCourseUseGlobleService.selectSchoolIdAndCourseId(societySchool.getId(),societySchoolCourse.getId());

			if (index==0 & index1==0){
				continue;
			}else {
				listSchoolId.add(societySchool.getId());
			}
		}
		societySchoolQuery.setSchoolIdList(listSchoolId);
		List<SocietySchoolView> list = societySchoolMapper.listPage(societySchoolQuery);
		//遍历
		for(SocietySchoolView societySchoolView : list){
			//统计课程一个学校完成了多少人
			Integer compNum =
					societyStudentAndCourseService.selectByCourseIdAndSchoolId(societySchoolView.getId(),societySchoolCourse.getId());
			societySchoolView.setSchoolCompleteCount(compNum);
			//统计课程一个学校学习了多少人
			Integer useNum =
					societyStudentAndCourseService.selectByUseCourseIdAndSchoolId(societySchoolView.getId(),societySchoolCourse.getId());
			societySchoolView.setSchoolUseCourseCount(useNum);

			//课程名称
			societySchoolView.setOwnerCourseName(societySchoolCourse.getCourseName());
		}
		societySchoolQuery.setList(list);
		return list;
	}

	public List<SocietySchoolView> listPageCourseMonthCount(SocietySchoolQuery societySchoolQuery){
		//遍历所有学校
		List<SocietySchool> listShool = selectByAll();
		SocietySchoolCourse societySchoolCourse =
				societySchoolCourseService.loadById(societySchoolQuery.getQueryObj().getOwnerCourseId());
		List<String> listSchoolId = new ArrayList<>();
		for(SocietySchool societySchool : listShool){
			//查询 该课程是否关联学校
			int index =
					societySchoolCourseService.selectSchoolIdAndCourseId(societySchool.getId(),societySchoolCourse.getId());
			//查询是否有公共课
			int index1 =
					societySchoolCourseUseGlobleService.selectSchoolIdAndCourseId(societySchool.getId(),societySchoolCourse.getId());

			if (index==0 & index1==0){
				continue;
			}else {
				listSchoolId.add(societySchool.getId());
			}
		}
		societySchoolQuery.setSchoolIdList(listSchoolId);
		List<SocietySchoolView> list = societySchoolMapper.listPage(societySchoolQuery);
		//遍历
		for(SocietySchoolView societySchoolView : list){
			//统计课程章节下的每一个学校对应的当前完成时间 查询人数
			societySchoolView.setOwnerCourseId(societySchoolCourse.getId());
			societySchoolView.setMonthDayStart(societySchoolQuery.getQueryObj().getMonthDayStart());
			societySchoolView.setMonthDayEnd(societySchoolQuery.getQueryObj().getMonthDayEnd());
			//完成人数
			Integer numCom =
					societyStudentAndCourseService.selectNodeTime(societySchoolView);
			societySchoolView.setSchoolCompleteCount(numCom);
			//学习人数
			Integer numUse =
					societyStudentAndNodeService.selectNodeTimeUse(societySchoolView);
			societySchoolView.setSchoolUseCourseCount(numUse);

			//课程名称
			societySchoolView.setOwnerCourseName(societySchoolCourse.getCourseName());
		}
		societySchoolQuery.setList(list);
		return list;
	}


	public List<SocietySchoolView> schoolAdminlistpage(SocietySchoolQuery societySchoolQuery){
		List list = societySchoolMapper.schoolAdminlistpage(societySchoolQuery);
		societySchoolQuery.setList(list);
		return list;
	}

	public List<SocietySchoolView> listByObj(SocietySchoolQuery societySchoolQuery){
		List list = societySchoolMapper.listByObj(societySchoolQuery);
		societySchoolQuery.setList(list);
		return list;
	}

	/*初始化菜单树*/
	public List<Dtree> listSchoolDtree() {
		SocietySchoolQuery query = new SocietySchoolQuery();
		query.getQueryObj().setDataState("1");
		List<SocietySchoolView> List = societySchoolMapper.listByObj(query);;
		List<Dtree> dtreeList = new ArrayList<>();
		for (SocietySchoolView m:List){
			Dtree dtree = new Dtree();
			dtree.setId(m.getId());
			dtree.setTitle(m.getSchoolName());
			dtree.setParentId("ROOT");
			dtree.setSpread(false);
			dtree.setLast(false);
			dtreeList.add(dtree);
		}
		return  dtreeList;
	}

	/*初始化菜单树*/
	public List<Dtree> listSchoolClassInfoDtree() {
		SocietySchoolQuery query = new SocietySchoolQuery();
		query.getQueryObj().setDataState("1");
		List<SocietySchoolView> List = societySchoolMapper.listByObj(query);;
		List<Dtree> dtreeList = new ArrayList<>();
		for (SocietySchoolView m:List){
			Dtree dtree = new Dtree();
			dtree.setId(m.getId());
			dtree.setTitle(m.getSchoolName());
			dtree.setParentId("ROOT");
			dtree.setSpread(false);
			dtree.setLast(true);
			dtreeList.add(dtree);
		}
		return  dtreeList;
	}


	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchool societySchool) {
		societySchool.setId(Guid.guid());
		societySchoolMapper.insert(societySchool);
		return "";
	}


	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchool societySchool) {
		societySchoolMapper.updateById(societySchool);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchool societySchool) {
		societySchoolMapper.updateAllColumnById(societySchool);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {

		return societySchoolMapper.deleteBatchIds(ids);
	}

	/**
	 * @Author ZhangCC
	 * @Description 批量逻辑删除
	 * @Date 2020/5/4 17:23
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void logicDelete(List<String> list){
		societySchoolMapper.logicDelete(list);
	}

	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchool loadById(String id) {
		return societySchoolMapper.selectById(id);
	}


	public void insertNewSchoolInfo(SocietySchool societySchool) {
		societySchoolMapper.insert(societySchool);
	}

	public List<String> selectIsSchoolName(String schoolNewName) {
		List<String> oldSchoolNameList = societySchoolMapper.selectOldCreateSchoolName(schoolNewName);
		return oldSchoolNameList;
	}

	public void updateById(SocietySchool societySchool) {
		societySchoolMapper.updateByIdAndDataState(societySchool);
	}

	public Map<String, String> selectBySchoolAdminId(String userId) {
		return societySchoolMapper.selectBySchoolAdminId(userId);
	}

	//学校统计
	public int countSchoolAllNum() {
		return societySchoolMapper.countSchoolAllNum();
	}


	public int countXianQuSchoolAllNum(String xianquId) {
		return societySchoolMapper.countXianQuSchoolAllNum( xianquId);
	}
	public List<SocietySchool> selectByAll() {
		return societySchoolMapper.selectByAll();
	}

	public List<SocietySchool> selectByNotOwnSchoolAll(String schoolId) {
		return societySchoolMapper.selectByNotOwnSchoolAll(schoolId);
	}
}
