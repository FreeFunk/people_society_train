package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolMajor;
import com.edgedo.society.mapper.SocietySchoolMajorMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseUseGlobleView;
import com.edgedo.society.queryvo.SocietySchoolMajorQuery;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import com.edgedo.sys.entity.Dtree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolMajorService {
	
	
	@Autowired
	private SocietySchoolMajorMapper societySchoolMajorMapper;
	@Autowired
	private SocietySchoolCourseClsService societySchoolCourseClsService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	@Autowired
	private SocietySchoolClassService societySchoolClassService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;

	public List<SocietySchoolMajorView> listPage(SocietySchoolMajorQuery societySchoolMajorQuery){
		List list = societySchoolMajorMapper.listPage(societySchoolMajorQuery);
		societySchoolMajorQuery.setList(list);
		return list;
	}

	/***
	 * 统计数量
	 * @return
	 */
	public Integer count(SocietySchoolMajorQuery query) {
		int num = societySchoolMajorMapper.count(query);
		return num;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolMajor societySchoolMajor) {
		societySchoolMajor.setId(Guid.guid());
		societySchoolMajor.setDataState("1");
		societySchoolMajor.setCreateTime(new Date());
		societySchoolMajorMapper.insert(societySchoolMajor);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertMultistageAdd(SocietySchoolMajor societySchoolMajor) {
		societySchoolMajorMapper.insert(societySchoolMajor);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolMajor societySchoolMajor) {
		//修改专业表信息
		societySchoolMajorMapper.updateById(societySchoolMajor);
		String majorId = societySchoolMajor.getId();
		String majorName = societySchoolMajor.getMajorName();
		societySchoolClassService.updateByMajorId(majorId,majorName);
		//修改课程分类中的专业的信息
		societySchoolCourseClsService.updateByMajorId(majorId,majorName);
		//修改课程中的专业信息
		societySchoolCourseService.updateByMajorId(majorId,majorName);
		//班级学生关联 专业信息
		societySchoolClassAndStudentService.updateByMajorId(majorId,majorName);
		//课程章节表 专业信息
		societySchoolCourseNodeService.updateByMajorId(majorId,majorName);
		//章节题目 专业信息
		societySchoolCourseNodeQuestionService.updateByMajorId(majorId,majorName);
		//学生课程关联表 专业信息
		societyStudentAndCourseService.updateByMajorId(majorId,majorName);
		//学生证书
		societyStudentCertificateService.updateByMajorId(majorId,majorName);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolMajor societySchoolMajor) {
		societySchoolMajorMapper.updateAllColumnById(societySchoolMajor);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolMajorMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolMajorMapper.deleteBatchIds(ids);
	}

	/**
	 * @Author ZhangCC
	 * @Description 批量逻辑删除
	 * @Date 2020/5/4 17:16
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void logicDelete(List<String> list){
		societySchoolMajorMapper.logicDelete(list);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolMajor loadById(String id) {
		return societySchoolMajorMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学校查询下面的所有专业
	 **/
	public List<SocietySchoolMajorView> listBySchoolId(String ownerSchoolId){
		return societySchoolMajorMapper.listBySchoolId(ownerSchoolId);
	}


	/*初始化菜单树*/
	public List<Dtree> listForDtreeBySchoolId(String schoolId) {
		List<SocietySchoolMajorView> societySchoolMajorViewList = societySchoolMajorMapper.listBySchoolId(schoolId);
		List<Dtree> dtreeList = new ArrayList<>();
		for (SocietySchoolMajorView m:societySchoolMajorViewList){
			Dtree dtree = new Dtree();
			dtree.setId(m.getId());
			dtree.setTitle(m.getMajorName());
			dtree.setParentId("ROOT");
			dtree.setSpread(false);
			dtree.setLast(false);
			dtreeList.add(dtree);
		}
		return  dtreeList;
	}

	/**
	 *@Author:TWQ
	 *@Description:根据学校id加载专业，学校树
	 *@DateTime: 2020-5-17 12:10:40
	 *@param schoolId
	 */
	public List<Dtree> listMajorForDtreeBySchoolId(String schoolId) {
		List<SocietySchoolMajorView> societySchoolMajorViewList = societySchoolMajorMapper.listBySchoolId(schoolId);
		List<Dtree> dtreeList = new ArrayList<>();
		for (SocietySchoolMajorView m:societySchoolMajorViewList){
			Dtree dtree = new Dtree();
			dtree.setId(m.getId());
			dtree.setTitle(m.getMajorName());
			dtree.setParentId("school_"+schoolId);
			dtree.setSpread(false);
			dtree.setLast(false);
			dtreeList.add(dtree);
		}
		return  dtreeList;
	}

	/**
	 *@Author:TWQ
	 *@Description:根据学校id加载专业，学校树,,平台管理员课程分类管理
	 *@DateTime: 2020-5-18 12:10:40
	 *@param schoolId
	 */
	public List<Dtree> listMajorForCourseClsBySchoolId(String schoolId) {
		List<SocietySchoolMajorView> societySchoolMajorViewList = societySchoolMajorMapper.listBySchoolId(schoolId);
		List<Dtree> dtreeList = new ArrayList<>();
		for (SocietySchoolMajorView m:societySchoolMajorViewList){
			Dtree dtree = new Dtree();
			dtree.setId(m.getId());
			dtree.setTitle(m.getMajorName());
			dtree.setParentId("school_"+schoolId);
			dtreeList.add(dtree);
		}
		return  dtreeList;
	}

	public SocietySchoolMajor selectByMajorId(String majorId) {
		return societySchoolMajorMapper.selectById(majorId);
	}

	public SocietySchoolMajorView loadByIdView(String ownerMajorId) {
		return societySchoolMajorMapper.selectByIdView(ownerMajorId);
	}

	public Integer countAll(String schoolId) {
		return societySchoolMajorMapper.countAll(schoolId);
	}

	public List<SocietySchoolMajorView> selectBySchoolList(List<String> schoolIdList) {
		return societySchoolMajorMapper.selectBySchoolList(schoolIdList);
	}
}
