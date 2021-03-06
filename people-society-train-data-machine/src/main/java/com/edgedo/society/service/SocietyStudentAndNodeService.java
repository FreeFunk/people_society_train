package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.dataenum.SchoolConfigKeyEnum;
import com.edgedo.society.controller.SocietySchoolClassAndStudentController;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.*;
import com.edgedo.society.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentAndNodeService {
	
	
	@Autowired
	private SocietyStudentAndNodeMapper societyStudentAndNodeMapper;
	@Autowired
	private SocietySchoolCourseNodeMapper nodeMapper;
	@Autowired
	private SocietySchoolCourseNodeService nodeService;
	@Autowired
	private SocietyStudentPractiseQuestionMapper studentPractiseQuestionMapper;
	@Autowired
	private SocietyStudentAndCourseMapper societyStudentAndCourseMapper;
	@Autowired
	private SocietyStudentStudyProcessFaceMapper societyStudentStudyProcessFaceMapper;
	@Autowired
	private SocietySchoolConfigService societySchoolConfigService;
	@Autowired
	private SocietySchoolConfigMapper societySchoolConfigMapper;
	@Autowired
	private SocietySchoolClassMapper societySchoolClassMapper;
	@Autowired
	private SocietySchoolCourseUseGlobleService societySchoolCourseUseGlobleService;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;

	@Autowired
	private SocietySchoolClassAndStudentService societySchoolClassAndStudentService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
	@Autowired
	private SocietySchoolClassAndStudentController societySchoolClassAndStudentController;


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public List<SocietyStudentAndNodeView> listPage(SocietyStudentAndNodeQuery societyStudentAndNodeQuery){
		//????????????????????????
		List<SocietyStudentAndNodeView> stuAndnodelist = societyStudentAndNodeMapper.listByObj(societyStudentAndNodeQuery);
		//????????????????????????
		String courseId = societyStudentAndNodeQuery.getQueryObj().getOwnerCourseId();
		String courseAndStu  = societyStudentAndNodeQuery.getQueryObj().getOwnerStudentAndCourseId();
		String studentId = societyStudentAndNodeQuery.getQueryObj().getStudentId();
		List<SocietySchoolCourseNodeView> courseAllList = nodeService.selectByCourseId(courseId);

		List<SocietyStudentAndNodeView> studentAndNodeNewlist = new ArrayList();
		//??????????????????????????????
		if(stuAndnodelist.size() != courseAllList.size()){//?????????
			SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery = new SocietySchoolCourseNodeQuery();
			societySchoolCourseNodeQuery.setLimit(societyStudentAndNodeQuery.getLimit());
			societySchoolCourseNodeQuery.setPage(societyStudentAndNodeQuery.getPage());
			societySchoolCourseNodeQuery.setStart(societyStudentAndNodeQuery.getStart());
			societySchoolCourseNodeQuery.setSuccess(societyStudentAndNodeQuery.getSuccess());
			societySchoolCourseNodeQuery.setTotalCount(societyStudentAndNodeQuery.getTotalCount());
			societySchoolCourseNodeQuery.setEnd(societyStudentAndNodeQuery.getEnd());
			societySchoolCourseNodeQuery.setTotalPage(societyStudentAndNodeQuery.getTotalPage());
			societySchoolCourseNodeQuery.getQueryObj().setOwnerCourseId(courseId);
			societySchoolCourseNodeQuery.getQueryObj().setNodeName(societyStudentAndNodeQuery.getQueryObj().getNodeName());
			societySchoolCourseNodeQuery.setOrderBy("ORDER_NUM ASC");
			List<SocietySchoolCourseNodeView> list = nodeService.listPage(societySchoolCourseNodeQuery);
			for(SocietySchoolCourseNodeView societySchoolCourseNodeView : list){//?????????????????????
				//????????????id ??? ??????id ??????id ??????????????????id??????????????????????????????
				SocietyStudentAndNodeView societyStudentAndNodeView =
						societyStudentAndNodeMapper.selectByNodeIdAndStuIdOne(studentId,courseAndStu,
								courseId,societySchoolCourseNodeView.getId());
				if(societyStudentAndNodeView == null){
					SocietyStudentAndNodeView societyStudentAndNodeView1 = new SocietyStudentAndNodeView();
					societyStudentAndNodeView1.setOwnerSchoolId(societySchoolCourseNodeView.getOwnerSchoolId());
					societyStudentAndNodeView1.setOwnerSchoolName(societySchoolCourseNodeView.getOwnerSchoolName());
					societyStudentAndNodeView1.setStudentId(studentId);
					societyStudentAndNodeView1.setOwnerCourseId(societySchoolCourseNodeView.getOwnerCourseId());
					societyStudentAndNodeView1.setOwnerCourseName(societySchoolCourseNodeView.getOwnerCourseName());
					societyStudentAndNodeView1.setNodeId(societySchoolCourseNodeView.getId());
					societyStudentAndNodeView1.setNodeName(societySchoolCourseNodeView.getNodeName());
					societyStudentAndNodeView1.setNodeProgress(new BigDecimal(0));
					societyStudentAndNodeView1.setStudyTimeLength(0);
					societyStudentAndNodeView1.setLastLearnLocation(0);
					societyStudentAndNodeView1.setLearnIsFinished("0");
					societyStudentAndNodeView1.setNodeQuestionScore(0);
					societyStudentAndNodeView1.setQuestionIsFinished("0");
					societyStudentAndNodeView1.setQuestionIsPass("0");
					studentAndNodeNewlist.add(societyStudentAndNodeView1);
				}else {
					studentAndNodeNewlist.add(societyStudentAndNodeView);
				}
			}
			societyStudentAndNodeQuery.setLimit(societySchoolCourseNodeQuery.getLimit());
			societyStudentAndNodeQuery.setPage(societySchoolCourseNodeQuery.getPage());
			societyStudentAndNodeQuery.setStart(societySchoolCourseNodeQuery.getStart());
			societyStudentAndNodeQuery.setSuccess(societySchoolCourseNodeQuery.getSuccess());
			societyStudentAndNodeQuery.setTotalCount(societySchoolCourseNodeQuery.getTotalCount());
			societyStudentAndNodeQuery.setEnd(societySchoolCourseNodeQuery.getEnd());
			societyStudentAndNodeQuery.setTotalPage(societySchoolCourseNodeQuery.getTotalPage());
		}else {
			studentAndNodeNewlist = societyStudentAndNodeMapper.listPage(societyStudentAndNodeQuery);
			for(SocietyStudentAndNodeView studentAndNodeView:studentAndNodeNewlist){
				String nodeId = studentAndNodeView.getNodeId();
				SocietySchoolCourseNode node = nodeService.loadById(nodeId);
				studentAndNodeView.setOrderNum(node.getOrderNum());
			}
			Collections.sort(studentAndNodeNewlist, new Comparator<SocietyStudentAndNodeView>() {
				@Override
				public int compare(SocietyStudentAndNodeView o1, SocietyStudentAndNodeView o2) {
					return o1.getOrderNum().intValue()-o2.getOrderNum().intValue();
				}
			});
		}
		for(SocietyStudentAndNodeView studentAndNodeView:studentAndNodeNewlist){
			String studentAndNodeViewId = studentAndNodeView.getId();
			String nodeId = studentAndNodeView.getNodeId();
			SocietySchoolCourseNode node = nodeService.loadById(nodeId);
			studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
			//?????????????????????????????????
			if(studentAndNodeViewId != null){
				int countRightNum = studentPractiseQuestionMapper.countByStudentAndNodeId(studentAndNodeViewId);
				studentAndNodeView.setRightNum(countRightNum);
			}else {
				studentAndNodeView.setRightNum(0);
			}
		}
		societyStudentAndNodeQuery.setList(studentAndNodeNewlist);
		return studentAndNodeNewlist;
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public List<SocietyStudentAndNodeView> listPageNew(SocietyStudentAndNodeQuery societyStudentAndNodeQuery){
		//????????????????????????
		List<SocietyStudentAndNodeView> stuAndnodelist = societyStudentAndNodeMapper.listPage(societyStudentAndNodeQuery);
		for(SocietyStudentAndNodeView studentAndNodeView:stuAndnodelist){
			String nodeId = studentAndNodeView.getNodeId();
			SocietySchoolCourseNode node = nodeService.loadById(nodeId);
			studentAndNodeView.setOrderNum(node.getOrderNum());
			studentAndNodeView.setTotalQuestionNum(node.getQuestionNum());
			//?????????????????????????????????
			if(node != null){
				int countRightNum = studentPractiseQuestionMapper.countByStudentAndNodeId(studentAndNodeView.getId());
				studentAndNodeView.setRightNum(countRightNum);
			}else {
				studentAndNodeView.setRightNum(0);
			}
		}
		Collections.sort(stuAndnodelist, new Comparator<SocietyStudentAndNodeView>() {
			@Override
			public int compare(SocietyStudentAndNodeView o1, SocietyStudentAndNodeView o2) {
				return o1.getOrderNum().intValue()-o2.getOrderNum().intValue();
			}
		});
		societyStudentAndNodeQuery.setList(stuAndnodelist);
		return stuAndnodelist;
	}



	public List<SocietyStudentAndNodeView> listByObj(SocietyStudentAndNodeQuery societyStudentAndNodeQuery){
		List list = societyStudentAndNodeMapper.listByObj(societyStudentAndNodeQuery);
		societyStudentAndNodeQuery.setList(list);
		return list;
	}

	public List<SocietyStudentAndNodeView> selectlistpage(SocietyStudentAndNodeQuery societyStudentAndNodeQuery){
		List<SocietyStudentAndNodeView> list = societyStudentAndNodeMapper.listPage(societyStudentAndNodeQuery);
		for(SocietyStudentAndNodeView societyStudentAndNodeView : list){
			SocietySchoolCourseNode societySchoolCourseNode =
					societySchoolCourseNodeService.loadById(societyStudentAndNodeView.getNodeId());
			societyStudentAndNodeView.setOrderNum(societySchoolCourseNode.getOrderNum());
			societyStudentAndNodeView.setNodeLength(societySchoolCourseNode.getNodeTimeLength());
		}
		Collections.sort(list, new Comparator<SocietyStudentAndNodeView>() {
			@Override
			public int compare(SocietyStudentAndNodeView o1, SocietyStudentAndNodeView o2) {
				return o1.getOrderNum().intValue()-o2.getOrderNum().intValue();
			}
		});
		societyStudentAndNodeQuery.setList(list);
		return list;
	}
	public List<SocietyStudentAndNodeView> selectByCourseNamelistPage(SocietyStudentAndNodeQuery societyStudentAndNodeQuery) {
		List list = societyStudentAndNodeMapper.selectByCourseNamelistPage(societyStudentAndNodeQuery);
		societyStudentAndNodeQuery.setList(list);
		return list;
	}

	public List<SocietyStudentAndNodeView> selectByCourseNamelistPageNew(SocietyStudentAndNodeQuery societyStudentAndNodeQuery) {
		List list = societyStudentAndNodeMapper.selectStudentCourse(societyStudentAndNodeQuery);
		societyStudentAndNodeQuery.setList(list);
		return list;
	}
	
	/***
	 * ????????????
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentAndNode societyStudentAndNode) {
		societyStudentAndNode.setId(Guid.guid());
		societyStudentAndNodeMapper.insert(societyStudentAndNode);
		return "";
	}
	
	/***
	 * ??????????????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentAndNodeView societyStudentAndNode) {
		//??????????????????
		SocietyStudentAndNode societyStudentAndNode1 =
				societyStudentAndNodeMapper.selectById(societyStudentAndNode.getId());
		societyStudentAndNode.setOwnerStudentAndCourseId(societyStudentAndNode1.getOwnerStudentAndCourseId());
		societyStudentAndNode.setOwnerSchoolId(societyStudentAndNode1.getOwnerSchoolId());
		societyStudentAndNode.setOwnerSchoolName(societyStudentAndNode1.getOwnerSchoolName());
		societyStudentAndNode.setNodeId(societyStudentAndNode1.getNodeId());
		societyStudentAndNode.setNodeName(societyStudentAndNode1.getNodeName());

		societyStudentAndNode.setStudentName(societyStudentAndNode1.getStudentName());
		societyStudentAndNode.setStudentIdCardNum(societyStudentAndNode1.getStudentIdCardNum());
		societyStudentAndNode.setOwnerCourseId(societyStudentAndNode1.getOwnerCourseId());
		societyStudentAndNode.setOwnerCourseName(societyStudentAndNode1.getOwnerCourseName());
		if(societyStudentAndNode1.getTotalQuestionNum()==null){
			Integer countNum = societySchoolCourseNodeQuestionService.selectByNodeId(societyStudentAndNode1.getNodeId());
			societyStudentAndNode.setTotalQuestionNum(countNum);
		}else {
			societyStudentAndNode.setTotalQuestionNum(societyStudentAndNode1.getTotalQuestionNum());
		}
		societyStudentAndNode.setQuestionIsPass("1");
		societyStudentAndNode.setQuestionIsFinished("1");
		societySchoolClassAndStudentController.finishedStuNodeQuestionNew(societyStudentAndNode);
		int countRightNum = studentPractiseQuestionMapper.countByStudentAndNodeId(societyStudentAndNode.getId());
		BigDecimal average;
		int questNum =societyStudentAndNode.getTotalQuestionNum();
		if (questNum==0){
			average = new BigDecimal(60);
		}else {
			average = new BigDecimal( (float) countRightNum /questNum * 100).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		societyStudentAndNode.setNodeQuestionScore(average.intValue());
		societyStudentAndNodeMapper.updateById(societyStudentAndNode);
		//??????????????????
		String studentCourseId = societyStudentAndNode1.getOwnerStudentAndCourseId();
		SocietyStudentAndCourse societyStudentAndCourse = societyStudentAndCourseMapper.selectById(studentCourseId);
		SocietyStudentAndCourse societyStudentAndCourseNew = new SocietyStudentAndCourse();
		societyStudentAndCourseNew.setId(studentCourseId);
		Integer finsihNum = societyStudentAndNodeMapper.selectByStuCouId(studentCourseId);
		Integer courseNum = societyStudentAndCourse.getTotalLessons();
		societyStudentAndCourseNew.setFinishNodeNum(finsihNum);
		societyStudentAndCourseNew.setCourseProgress(new BigDecimal(finsihNum*100/courseNum));
		SocietyStudentAndNode societyStudentAndNodeDesc = societyStudentAndNodeMapper.selectByStuCouIdDesc(studentCourseId);
		societyStudentAndCourseNew.setLearnFinishTime(societyStudentAndNodeDesc.getFinishTime());
		societyStudentAndCourseMapper.updateById(societyStudentAndCourseNew);
		return "";
	}
	
	/***
	 * ?????????
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentAndNode societyStudentAndNode) {
		societyStudentAndNodeMapper.updateAllColumnById(societyStudentAndNode);
		return "";
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentAndNodeMapper.deleteById(id);
	}
	
	/**
	 * ????????????
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentAndNodeMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 */
	public SocietyStudentAndNode loadById(String id) {
		return societyStudentAndNodeMapper.selectById(id);
	}

//???????????????????????????id
	public List<String> selectNodeIdList(SocietyStudentAndNodeQuery query) {
		return societyStudentAndNodeMapper.selectNodeIdList( query);
	}

	public List<SocietyStudentAndNodeView> selectStudentCourse(SocietyStudentAndNodeQuery query) {
		List<SocietyStudentAndNodeView> studentAndNodeNewlist =
				societyStudentAndNodeMapper.selectStudentCourse( query);
		for(SocietyStudentAndNodeView studentAndNodeView:studentAndNodeNewlist){
			String studentAndNodeViewId = studentAndNodeView.getId();
			//?????????????????????????????????
			if(studentAndNodeViewId != null){
				int countRightNum = studentPractiseQuestionMapper.countByStudentAndNodeId(studentAndNodeViewId);
				studentAndNodeView.setRightNum(countRightNum);
			}else {
				studentAndNodeView.setRightNum(0);
			}
		}
		return societyStudentAndNodeMapper.selectStudentCourse( query);
	}

	public void updateByDataState(List<String> list) {
		societyStudentAndNodeMapper.updateByDataState(list);
	}

	public void deleteByStuId(String id) {
		societyStudentAndNodeMapper.deleteByStuId(id);
	}

	public void updateByStuId(Map<String,String> map) {
		societyStudentAndNodeMapper.updateByStuId(map);
	}

	public void updateByNodeIdAndNodeName(String nodeId, String nodeName) {
		societyStudentAndNodeMapper.updateByNodeIdAndNodeName(nodeId,nodeName);
	}

	public Integer selectNodeTimeUse(SocietySchoolView societySchoolView) {
		return societyStudentAndNodeMapper.selectNodeTimeUse(societySchoolView);
	}


	public SocietyStudentAndNodeView selectByStuIdAndNodeIdAndStuCourseId(String stuCourseId, String nodeId,
																		  String studentId) {
		return societyStudentAndNodeMapper.selectByStuIdAndNodeIdAndStuCourseId(studentId,nodeId,stuCourseId);
	}


	/**
	 * @Author QiuTianZhu
	 * @Description: ??????????????????id  ??????id  ??????id ??????id ???????????????????????????
	 * @Param:
	 * @return:
	 * @Date 2020/8/3 10:43
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByStuNodeInfo(String stuCourseId, String nodeId,
									String ownerCourseId, String studentId) {
		SocietyStudentAndNodeView societyStudentAndNodeView =
				societyStudentAndNodeMapper.selectByStuIdAndNodeIdAndStuCourseId(studentId,nodeId,stuCourseId);
		//????????????
		societyStudentAndNodeView.setNodeProgress(new BigDecimal(0));
		// ????????????
		societyStudentAndNodeView.setStudyTimeLength(0);
		// ?????????????????????
		societyStudentAndNodeView.setLastLearnLocation(0);
		// ??????????????????
		societyStudentAndNodeView.setMaxLearnLocation(0);
		// ????????????
		societyStudentAndNodeView.setLearnIsFinished("0");
		// ????????????
		societyStudentAndNodeView.setNodeQuestionScore(0);
		// ??????????????????
		societyStudentAndNodeView.setQuestionIsFinished("0");
		societyStudentAndNodeView.setQuestionIsPass("0");

		// ????????????  0 ????????? ??????
		societyStudentAndNodeView.setExamineState("0");
		update(societyStudentAndNodeView);
		// ????????????
		societyStudentAndNodeMapper.updateByFinishTime(societyStudentAndNodeView.getId());
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		societyStudentAndNodeMapper.updateByCourseIdAndCourseName(map);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByStuCoIdAndNodeId(String stuCourseId, String nodeId) {
		societyStudentAndNodeMapper.updateByStuCoIdAndNodeId(stuCourseId,nodeId);
	}

	public Map<String, String> selectStudentFaceIsQualified(String stuAndCouId) {
		Map<String,String> map = new TreeMap<>();
		//1.???????????????????????????
		SocietyStudentAndCourse societyStudentAndCourse =
				societyStudentAndCourseMapper.selectById(stuAndCouId);
		//2.?????????????????????????????????
		List<SocietyStudentAndNodeView> list =
				societyStudentAndNodeMapper.selectByStuIdAndCourseId(stuAndCouId);
		SocietySchoolConfig schoolConfig  = societySchoolConfigMapper.selectBySchoolIdAndKey(societyStudentAndCourse.getOwnerSchoolId(),SchoolConfigKeyEnum.NODE_FACE_QUALIFIED_NUM+"");

		if(schoolConfig == null){
			map.put("err","????????????????????????????????????");
			return map;
		}else {
			//??????????????????????????????
			String num = societySchoolConfigService.getSchoolConfigValue(societyStudentAndCourse.getOwnerSchoolId(), SchoolConfigKeyEnum.NODE_FACE_QUALIFIED_NUM+"");
			for(SocietyStudentAndNodeView societyStudentAndNodeView : list){
				//3.?????? ????????????id ??? ??????id ??????????????????
				//????????????????????????????????????
				if(societyStudentAndNodeView.getLearnIsFinished().equals("1")){
					Integer faceNum =
							societyStudentStudyProcessFaceMapper.selectByNodeIdAndStudentId(stuAndCouId,societyStudentAndNodeView.getNodeId());
					//???????????????
					Integer it = Integer.valueOf(num);
					//4.????????????????????????????????????
					if (it>faceNum){
						map.put(societyStudentAndNodeView.getNodeName(),faceNum.toString());
					}
				}
			}
		}

		return map;
	}

	public Map<String, String> selectStudentClassFaceIsQualified(String classId) {
		Map<String,String> map = new TreeMap<>();

		//1.???????????????????????????
		List<SocietyStudentAndCourseView> stuCouList = societyStudentAndCourseMapper.selectByClassId(classId);
		SocietySchoolClass societySchoolClass = societySchoolClassMapper.selectById(classId);
		if(stuCouList.size()!=0){
			SocietySchoolConfig schoolConfig  = societySchoolConfigMapper.selectBySchoolIdAndKey(societySchoolClass.getOwnerSchoolId(),SchoolConfigKeyEnum.NODE_FACE_QUALIFIED_NUM+"");

			if(schoolConfig == null){
				map.put("err","????????????????????????????????????");
				return map;
			}
		}else {
			map.put("err","??????????????????????????????!");
		}
		return map;
	}

	public List<SocietyStudentAndNodeView> selectStudentClassFaceIsQualifiedlistPage(SocietyStudentAndNodeQuery societyStudentAndNodeQuery) {
		List<SocietyStudentAndNodeView> listAll = societyStudentAndNodeMapper.selectByClassFacelistObj(societyStudentAndNodeQuery);
		if(listAll.size()!=0){
			SocietySchoolClass societySchoolClass = societySchoolClassMapper.selectById(societyStudentAndNodeQuery.getQueryObj().getClassId());
			//??????????????????????????????
			String num = societySchoolConfigService.getSchoolConfigValue(
					societySchoolClass.getOwnerSchoolId(), SchoolConfigKeyEnum.NODE_FACE_QUALIFIED_NUM+"");
			List<String> nodeList = new ArrayList<>();
			for(SocietyStudentAndNodeView societyStudentAndNodeView : listAll){
				String nodeId =
						societyStudentStudyProcessFaceMapper.selectStuNodeAndFaceNum(
								societyStudentAndNodeView.getOwnerStudentAndCourseId(),
								societyStudentAndNodeView.getNodeId(),Integer.valueOf(num));
				if (!nodeId.equals("")) {
					nodeList.add(societyStudentAndNodeView.getId());
				}
			}
			societyStudentAndNodeQuery.setNodeList(nodeList);
			if(nodeList.size()==0){//??????????????????
				return new ArrayList<>();
			}else {
				if(societyStudentAndNodeQuery.getOrderBy()==null){
					societyStudentAndNodeQuery.setOrderBy("STUDENT_NAME ASC");
				}
				List<SocietyStudentAndNodeView> list = societyStudentAndNodeMapper.selectByClassFacelistPage(societyStudentAndNodeQuery);
				for(SocietyStudentAndNodeView societyStudentAndNodeView : list){
					//3.?????? ????????????id ??? ??????id ??????????????????
					//????????????????????????????????????
					Integer faceNum =
							societyStudentStudyProcessFaceMapper.selectByNodeIdAndStudentId(
									societyStudentAndNodeView.getOwnerStudentAndCourseId(),
									societyStudentAndNodeView.getNodeId());
					//???????????????
					Integer it = Integer.valueOf(num);
					if (it>faceNum){
						societyStudentAndNodeView.setFaceIsPass(faceNum);
					}
				}
				societyStudentAndNodeQuery.setList(list);
				return list;
			}

		}else {
			return new ArrayList<>();
		}

	}

	public List<SocietyStudentAndNodeView> selectByStuAndCouId(String stuCouId) {
		return societyStudentAndNodeMapper.selectByStuIdAndCourseId(stuCouId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateById(SocietyStudentAndNodeView societyStudentAndNodeView) {
		societyStudentAndNodeMapper.updateById(societyStudentAndNodeView);
	}

	public List<SocietyStudentAndNodeView> classFaceIsQualifiedlistObj(String classId) {
		List<SocietyStudentAndNodeView> listAll = societyStudentAndNodeMapper.classFacelistObj(classId);
		if(listAll.size()!=0){
			SocietySchoolClass societySchoolClass = societySchoolClassMapper.selectById(classId);
			//??????????????????????????????
			String num = societySchoolConfigService.getSchoolConfigValue(
					societySchoolClass.getOwnerSchoolId(), SchoolConfigKeyEnum.NODE_FACE_QUALIFIED_NUM+"");
			List<String> nodeList = new ArrayList<>();
			for(SocietyStudentAndNodeView societyStudentAndNodeView : listAll){
				String nodeId =
						societyStudentStudyProcessFaceMapper.selectStuNodeAndFaceNum(
								societyStudentAndNodeView.getOwnerStudentAndCourseId(),
								societyStudentAndNodeView.getNodeId(),Integer.valueOf(num));
				if (!nodeId.equals("")) {
					nodeList.add(societyStudentAndNodeView.getId());
				}
			}
			if(nodeList.size()==0){//??????????????????
				return new ArrayList<>();
			}else {
				List<SocietyStudentAndNodeView> list = societyStudentAndNodeMapper.selectAllIsFiedStu(nodeList);
				for(SocietyStudentAndNodeView societyStudentAndNodeView : list){
					//3.?????? ????????????id ??? ??????id ??????????????????
					//????????????????????????????????????
					Integer faceNum =
							societyStudentStudyProcessFaceMapper.selectByNodeIdAndStudentId(
									societyStudentAndNodeView.getOwnerStudentAndCourseId(),
									societyStudentAndNodeView.getNodeId());
					//???????????????
					Integer it = Integer.valueOf(num);
					if (it>faceNum){
						societyStudentAndNodeView.setFaceIsPass(faceNum);
					}
				}
				return list;
			}

		}else {
			return new ArrayList<>();
		}
	}

	public String selectNodeName(String stuCoId, String ownerNodeName) {
		return societyStudentAndNodeMapper.selectNodeName(stuCoId,ownerNodeName);
	}

	public SocietyStudentAndNode selectByStuCouIdNewOnce(String stuAndCouId) {
		return societyStudentAndNodeMapper.selectByStuCouIdNewOnce(stuAndCouId);
	}

	public Integer countStudyLength(String stuAndCouId) {
		return societyStudentAndNodeMapper.countStudyLength(stuAndCouId);
	}

	public List<SocietyStudentAndNode> selectFinishTime() {
		return societyStudentAndNodeMapper.selectFinishTime();
	}


	public Integer selectByStudentId(String studentId) {
		return societyStudentAndNodeMapper.selectByStudentId(studentId);
	}

	public String selectByStuCouIdAndNodeId(String nodeId, String stuCourseId) {
		return societyStudentAndNodeMapper.selectByStuCouIdAndNodeId(nodeId,stuCourseId);
	}
}
