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
	 * ????????????
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
	 * ??????????????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyNodeResources societyNodeResources) {
		societyNodeResourcesMapper.updateById(societyNodeResources);
		return "";
	}

	/***
	 * ?????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyNodeResources societyNodeResources) {
		societyNodeResourcesMapper.updateAllColumnById(societyNodeResources);
		return "";
	}



	/**
	 * ????????????
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {

		return societyNodeResourcesMapper.deleteById(id);
	}

	/**
	 * ????????????
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {

		return societyNodeResourcesMapper.deleteBatchIds(ids);
	}



	/**
	 * ????????????
	 * @param id
	 */
	public SocietyNodeResources loadById(String id) {
		return societyNodeResourcesMapper.selectById(id);
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int joinToMyResources(User user, String ids) {
		SocietyNodeResources societyNodeResources = this.loadById(ids);
		SocietySchool societySchool = societySchoolService.loadById(user.getCompId());
		/*//?????? ???
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
		//????????????
		//???????????????
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
		//1.?????????????????????????????? ??????
		String[] resourceStr = ids.split(",");
		SocietySchool societySchool = societySchoolService.loadById(user.getCompId());
		SocietySchoolCourse societySchoolCourse = societySchoolCourseService.loadById(courseId);
		List<SocietySchoolCourseNodeView> list = new ArrayList<>();
		for (String resourceId : resourceStr){
			SocietyNodeResources societyNodeResources = this.loadById(resourceId);
			//2.?????????????????????id ????????????????????????
			//?????????   ??????????????????????????? ?????? ??????????????? ??????
			//??????????????? ???????????? ???????????????????????????????????? ??????????????????????????????
			//??????????????????????????? ??????????????? ?????????????????????
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
				//???????????? ?????????????????????????????? ??????????????????????????????
				String ownerNodeResourceId = societyNodeResources.getOwnerNodeResourcesId();
				SocietyNodeResources societyNodeResourcesOnwer = this.loadById(ownerNodeResourceId);
				societySchoolCourseNode.setIsOpen("0");
				societySchoolCourseNode.setOwnerNodeResourcesId(ownerNodeResourceId);
				societySchoolCourseNode.setOwnerNodeResourcesName(societyNodeResourcesOnwer.getNodeName());
				societySchoolCourseNode.setOwnerNodeResourcesSchoolId(societyNodeResourcesOnwer.getOwnerSchoolId());
			}else {
				//?????????????????????
				societySchoolCourseNode.setIsOpen(societyNodeResources.getIsOpen());
				societySchoolCourseNode.setOwnerNodeResourcesId(societyNodeResources.getId());
				societySchoolCourseNode.setOwnerNodeResourcesName(societyNodeResources.getNodeName());
				societySchoolCourseNode.setOwnerNodeResourcesSchoolId(societyNodeResources.getOwnerSchoolId());
			}
			list.add(societySchoolCourseNode);
		}
		societySchoolCourseNodeMapper.insertAllRecord(list);
		//???????????????????????????
		int count = societySchoolCourseNodeMapper.countByCourseId(courseId);
		SocietySchoolCourse schoolCourse = societySchoolCourseService.loadById(courseId);
		schoolCourse.setTotalLessons(count);
		//????????????????????????
		societySchoolCourseService.update(schoolCourse);

	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateMyNode(User user, String ids,String courseId,String nodeId) {
		//1.????????????????????????id ?????????????????????
		SocietyNodeResources societyNodeResources = this.loadById(ids);
		//2.??????????????????id ????????????
		SocietySchoolCourseNode societySchoolCourseNode = societySchoolCourseNodeService.loadById(nodeId);
		//3.?????????????????????????????????
		societySchoolCourseNode.setNodeName(societyNodeResources.getNodeName());
		societySchoolCourseNode.setNodeTimeLength(societyNodeResources.getNodeTimeLength());
		societySchoolCourseNode.setImageUrl(societyNodeResources.getImageUrl());
		societySchoolCourseNode.setFileUrl(societyNodeResources.getFileUrl());
		societySchoolCourseNode.setQuestionNum(societyNodeResources.getQuestionNum());
		societySchoolCourseNode.setOrderNum(societyNodeResources.getOrderNum());
		societySchoolCourseNode.setFileId(societyNodeResources.getFileId());
		if ("1".equals(societyNodeResources.getIsRelationPublicCourse())) {
			//??????????????????
			SocietyNodeResources societyNodeResources1 = this.loadById(societyNodeResources.getOwnerNodeResourcesId());
			societySchoolCourseNode.setIsOpen(societyNodeResources1.getIsOpen());
			societySchoolCourseNode.setOwnerNodeResourcesId(societyNodeResources1.getId());
			societySchoolCourseNode.setOwnerNodeResourcesName(societyNodeResources1.getNodeName());
			societySchoolCourseNode.setOwnerNodeResourcesSchoolId(societyNodeResources1.getOwnerSchoolId());
		}else {
			//???????????????
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
