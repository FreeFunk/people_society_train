package com.edgedo.society.service;
		
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyNodeResources;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolCourse;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.mapper.SocietyNodeResourcesMapper;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.queryvo.SocietyNodeResourcesQuery;
import com.edgedo.society.queryvo.SocietyNodeResourcesView;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyNodeResourcesService {
	
	
	@Autowired
	private SocietyNodeResourcesMapper societyNodeResourcesMapper;
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;
	@Autowired
	private SocietySchoolCourseService societySchoolCourseService;
	@Autowired
	private SocietySchoolCourseNodeMapper societySchoolCourseNodeMapper;

	public List<SocietyNodeResourcesView> listPage(SocietyNodeResourcesQuery societyNodeResourcesQuery){
		List list = societyNodeResourcesMapper.listPage(societyNodeResourcesQuery);
		societyNodeResourcesQuery.setList(list);
		return list;
	}

	public List<SocietySchoolCourseNodeView> listPageCountNode(SocietyNodeResourcesQuery societyNodeResourcesQuery){
		List list = societyNodeResourcesMapper.listPageCountNode(societyNodeResourcesQuery);
		societyNodeResourcesQuery.setList(list);
		return list;
	}

	public List<SocietyNodeResourcesView> listPageMyNode(SocietyNodeResourcesQuery societyNodeResourcesQuery){
		List list = societyNodeResourcesMapper.listPageMyNode(societyNodeResourcesQuery);
		societyNodeResourcesQuery.setList(list);
		return list;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyNodeResources societyNodeResources) {
		societyNodeResources.setId(Guid.guid());
		societyNodeResourcesMapper.insert(societyNodeResources);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertResourcesAndNode(SocietyNodeResources societyNodeResources) {
		societyNodeResourcesMapper.insert(societyNodeResources);
		return "";
	}

	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyNodeResources societyNodeResources) {
		societyNodeResourcesMapper.updateById(societyNodeResources);
		return "";
	}

	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyNodeResources societyNodeResources) {
		societyNodeResourcesMapper.updateAllColumnById(societyNodeResources);
		return "";
	}



	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {

		return societyNodeResourcesMapper.deleteById(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {

		return societyNodeResourcesMapper.deleteBatchIds(ids);
	}



	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyNodeResources loadById(String id) {
		return societyNodeResourcesMapper.selectById(id);
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int joinToMyResources(User user, String ids) {
		SocietyNodeResources societyNodeResources = this.loadById(ids);
		SocietySchool societySchool = societySchoolService.loadById(user.getCompId());
		/*//小节 表
		String courseId = societyNodeResources.getOwnerCourseId();
		SocietySchoolCourseNode societySchoolCourseNode =
				societySchoolCourseNodeService.selectByCourseIdAndResourceId(courseId,ids);
		societySchoolCourseNode.setId(Guid.guid());
		societySchoolCourseNode.setCreateUserName(user.getUserName());
		societySchoolCourseNode.setCreateUserId(user.getUserId());
		societySchoolCourseNode.setCreateTime(new Date());
		societySchoolCourseNode.setOwnerSchoolId(societySchool.getId());
		societySchoolCourseNode.setOwnerSchoolName(societySchool.getSchoolName());
		societySchoolCourseNode.setIsOpen("0");
		societySchoolCourseNode.setOwnerNodeResourcesId(ids);
		societySchoolCourseNode.setOwnerNodeResourcesName(societyNodeResources.getNodeName());
		societySchoolCourseNode.setOwnerNodeResourcesSchoolId(societyNodeResources.getOwnerSchoolId());
		societySchoolCourseNode.setOwnerMajorId(null);
		societySchoolCourseNode.setOwnerMajorName(null);
		societySchoolCourseNode.setOwnerCourseClsId(null);
		societySchoolCourseNode.setOwnerCourseClsName(null);
		societySchoolCourseNode.setOwnerCourseId(null);
		societySchoolCourseNode.setOwnerCourseName(null);
		societySchoolCourseNodeService.insert(societySchoolCourseNode);*/
		//章节池表
		//申请方设置
		societyNodeResources.setId(Guid.guid());
		societyNodeResources.setCreateUserName(user.getUserName());
		societyNodeResources.setCreateUserId(user.getUserId());
		societyNodeResources.setCreateTime(new Date());
//		societyNodeResources.setOwnerSchoolId(societySchool.getId());
//		societyNodeResources.setOwnerSchoolName(societySchool.getSchoolName());
		societyNodeResources.setIsOpen("0");
		societyNodeResources.setApplySchoolId(societySchool.getId());
		societyNodeResources.setOwnerNodeResourcesId(ids);
		societyNodeResources.setIsRelationPublicCourse("1");
		return societyNodeResourcesMapper.insert(societyNodeResources);

	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addCourseNode(User user, String ids,String courseId) {
		//1.获取所有的章节池信息 遍历
		String[] resourceStr = ids.split(",");
		SocietySchool societySchool = societySchoolService.loadById(user.getCompId());
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		List<SocietySchoolCourseNodeView> list = new ArrayList<>();
		for (String resourceId : resourceStr){
			SocietyNodeResources societyNodeResources = this.loadById(resourceId);
			//2.根据当前的课程id 新增课程章节表中
			//章节池   自己刚刚关联的章节 平台 自己的章节 自有
			//无论是平台 还是自有 都是选择当前章节池的记录 去新增一条章节表记录
			//并且要判断当前是否 平台的章节 不是就是自己课
			SocietySchoolCourseNodeView societySchoolCourseNode = new SocietySchoolCourseNodeView();
			societySchoolCourseNode.setId(Guid.guid());
			societySchoolCourseNode.setCreateUserName(user.getUserName());
			societySchoolCourseNode.setCreateUserId(user.getUserId());
			societySchoolCourseNode.setCreateTime(new Date());
			societySchoolCourseNode.setOwnerSchoolId(societySchool.getId());
			societySchoolCourseNode.setOwnerSchoolName(societySchool.getSchoolName());
			societySchoolCourseNode.setOwnerMajorId(societySchoolCourse.getOwnerMajorId());
			societySchoolCourseNode.setOwnerMajorName(societySchoolCourse.getOwnerMajorName());
			societySchoolCourseNode.setOwnerCourseClsId(societySchoolCourse.getCourseClsId());
			societySchoolCourseNode.setOwnerCourseClsName(societySchoolCourse.getCourseClsName());
			societySchoolCourseNode.setOwnerCourseId(societySchoolCourse.getId());
			societySchoolCourseNode.setOwnerCourseName(societySchoolCourse.getCourseName());
			societySchoolCourseNode.setNodeName(societyNodeResources.getNodeName());
			societySchoolCourseNode.setNodeTimeLength(societyNodeResources.getNodeTimeLength());
			societySchoolCourseNode.setImageUrl(societyNodeResources.getImageUrl());
			societySchoolCourseNode.setFileUrl(societyNodeResources.getFileUrl());
			societySchoolCourseNode.setQuestionNum(societyNodeResources.getQuestionNum());
			societySchoolCourseNode.setOrderNum(societyNodeResources.getOrderNum());
			societySchoolCourseNode.setDataState("1");
			societySchoolCourseNode.setFileId(societyNodeResources.getFileId());
			if ("1".equals(societyNodeResources.getIsRelationPublicCourse())){
				//为公共课 需要找到原来的章节池 使用的公共课不能公开
				String ownerNodeResourceId = societyNodeResources.getOwnerNodeResourcesId();
				SocietyNodeResources societyNodeResourcesOnwer = this.loadById(ownerNodeResourceId);
				societySchoolCourseNode.setIsOpen("0");
				societySchoolCourseNode.setOwnerNodeResourcesId(ownerNodeResourceId);
				societySchoolCourseNode.setOwnerNodeResourcesName(societyNodeResourcesOnwer.getNodeName());
				societySchoolCourseNode.setOwnerNodeResourcesSchoolId(societyNodeResourcesOnwer.getOwnerSchoolId());
			}else {
				//关联自己的课程
				societySchoolCourseNode.setIsOpen(societyNodeResources.getIsOpen());
				societySchoolCourseNode.setOwnerNodeResourcesId(societyNodeResources.getId());
				societySchoolCourseNode.setOwnerNodeResourcesName(societyNodeResources.getNodeName());
				societySchoolCourseNode.setOwnerNodeResourcesSchoolId(societyNodeResources.getOwnerSchoolId());
			}
			list.add(societySchoolCourseNode);
		}
		societySchoolCourseNodeMapper.insertAllRecord(list);
		//统计课程的章节数量
		int count = societySchoolCourseNodeMapper.countByCourseId(courseId);
		SocietySchoolCourse schoolCourse = societySchoolCourseService.loadById(courseId);
		schoolCourse.setTotalLessons(count);
		//修改课程的章节数
		societySchoolCourseService.update(schoolCourse);

	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateMyNode(User user, String ids,String courseId,String nodeId) {
		//1.根据当前的章节池id 查出章节池信息
		SocietyNodeResources societyNodeResources = this.loadById(ids);
		//2.根据章节表的id 查出信息
		SocietySchoolCourseNode societySchoolCourseNode = societySchoolCourseNodeService.loadById(nodeId);
		//3.将原来的章节表信息修改
		societySchoolCourseNode.setNodeName(societyNodeResources.getNodeName());
		societySchoolCourseNode.setNodeTimeLength(societyNodeResources.getNodeTimeLength());
		societySchoolCourseNode.setImageUrl(societyNodeResources.getImageUrl());
		societySchoolCourseNode.setFileUrl(societyNodeResources.getFileUrl());
		societySchoolCourseNode.setQuestionNum(societyNodeResources.getQuestionNum());
		societySchoolCourseNode.setOrderNum(societyNodeResources.getOrderNum());
		societySchoolCourseNode.setFileId(societyNodeResources.getFileId());
		if ("1".equals(societyNodeResources.getIsRelationPublicCourse())) {
			//选择的公共课
			SocietyNodeResources societyNodeResources1 = this.loadById(societyNodeResources.getOwnerNodeResourcesId());
			societySchoolCourseNode.setIsOpen(societyNodeResources1.getIsOpen());
			societySchoolCourseNode.setOwnerNodeResourcesId(societyNodeResources1.getId());
			societySchoolCourseNode.setOwnerNodeResourcesName(societyNodeResources1.getNodeName());
			societySchoolCourseNode.setOwnerNodeResourcesSchoolId(societyNodeResources1.getOwnerSchoolId());
		}else {
			//自己的课程
			societySchoolCourseNode.setIsOpen(societyNodeResources.getIsOpen());
			societySchoolCourseNode.setOwnerNodeResourcesId(societyNodeResources.getId());
			societySchoolCourseNode.setOwnerNodeResourcesName(societyNodeResources.getNodeName());
			societySchoolCourseNode.setOwnerNodeResourcesSchoolId(societyNodeResources.getOwnerSchoolId());
		}
		societySchoolCourseNodeService.update(societySchoolCourseNode);
	}


	public List<SocietyNodeResources> selectByCourseId(String courseId) {
		return societyNodeResourcesMapper.selectByCourseId(courseId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertAllRecord(List<SocietyNodeResources> resourcesList) {
		societyNodeResourcesMapper.insertAllRecord(resourcesList);
	}

	public int selectBySchoolIdAndResourId(String nowSchoolId, String resourcesViewId) {
		return societyNodeResourcesMapper.selectBySchoolIdAndResourId(nowSchoolId,resourcesViewId);
	}

	public void updateById(String courseId, String ownerSchoolId) {
		societyNodeResourcesMapper.updateByDataStateId(courseId,ownerSchoolId);
	}

	public void updateByIsOpen(String resourceId, String isOpen) {
		societyNodeResourcesMapper.updateByIsOpen(resourceId,isOpen);
	}

	public void updateByDateState(String ids) {
		societyNodeResourcesMapper.updateByDateState(ids);
	}

	public List<SocietyNodeResourcesView> selectByResourcerId(String resourceId) {
		return societyNodeResourcesMapper.selectByResourcerId(resourceId);
	}

	public void updateByNodeNameAndNum(List<SocietyNodeResourcesView> nodeResourceList) {
		societyNodeResourcesMapper.updateByNodeNameAndNum(nodeResourceList);
	}

    public List<String> selectBySchoolId(String schoolId) {
		return societyNodeResourcesMapper.selectBySchoolId(schoolId);
    }
}
