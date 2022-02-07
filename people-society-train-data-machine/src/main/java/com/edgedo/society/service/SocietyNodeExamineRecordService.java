package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyNodeExamineRecord;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.entity.SocietyStudentAndNode;
import com.edgedo.society.entity.SocietyStudentStudyProcessFace;
import com.edgedo.society.mapper.SocietyNodeExamineRecordMapper;
import com.edgedo.society.queryvo.SocietyNodeExamineRecordQuery;
import com.edgedo.society.queryvo.SocietyNodeExamineRecordView;
import com.edgedo.society.queryvo.SocietyStudentAndNodeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyNodeExamineRecordService {
	
	
	@Autowired
	private SocietyNodeExamineRecordMapper societyNodeExamineRecordMapper;

	@Autowired
	private SocietyStudentAndCourseService societyStudentAndCourseService;
	@Autowired
	private SocietyStudentAndNodeService societyStudentAndNodeService;
	@Autowired
	private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
	@Autowired
	private SocietyStudentPractiseQuestOptionService societyStudentPractiseQuestOptionService;
	@Autowired
	private SocietyStudentCertificateService societyStudentCertificateService;
	@Autowired
	private SocietyStudentStudyProcessFaceService societyStudentStudyProcessFaceService;

	public List<SocietyNodeExamineRecordView> listPage(SocietyNodeExamineRecordQuery societyNodeExamineRecordQuery){
		List list = societyNodeExamineRecordMapper.listPage(societyNodeExamineRecordQuery);
		societyNodeExamineRecordQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyNodeExamineRecord societyNodeExamineRecord) {
		societyNodeExamineRecord.setId(Guid.guid());
		societyNodeExamineRecordMapper.insert(societyNodeExamineRecord);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyNodeExamineRecord societyNodeExamineRecord) {
		societyNodeExamineRecordMapper.updateById(societyNodeExamineRecord);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyNodeExamineRecord societyNodeExamineRecord) {
		societyNodeExamineRecordMapper.updateAllColumnById(societyNodeExamineRecord);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyNodeExamineRecordMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyNodeExamineRecordMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyNodeExamineRecord loadById(String id) {
		return societyNodeExamineRecordMapper.selectById(id);
	}

	/**
	 * 根据已有id 补充审核记录表信息
	 * @param stuCourseId
	 * @param nodeId
	 * @param ownerCourseId
	 * @param studentId
	 * @param user
	 * @param record
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertEeamineInfo(String stuCourseId, String nodeId, String ownerCourseId,
								  String studentId, User user,String record) {
		SocietyNodeExamineRecord societyNodeExamineRecord = new SocietyNodeExamineRecord();
		//主键
		societyNodeExamineRecord.setId(Guid.guid());
		//创建时间
		societyNodeExamineRecord.setCreateTime(new Date());
		//创建人id
		societyNodeExamineRecord.setCreateUserId(user.getUserId());
		//创建人名
		societyNodeExamineRecord.setCreateUserName(user.getUserName());
		//原课程完成时间
		SocietyStudentAndCourse societyStudentAndCourse = societyStudentAndCourseService.loadById(stuCourseId);
		societyNodeExamineRecord.setOrgCourseFinishedTime(societyStudentAndCourse.getLearnFinishTime());
		//原章节完成时间
		SocietyStudentAndNodeView societyStudentAndNodeView =
				societyStudentAndNodeService.selectByStuIdAndNodeIdAndStuCourseId(stuCourseId,nodeId,studentId);
		societyNodeExamineRecord.setOrgNodeFinishedTime(societyStudentAndNodeView.getFinishTime());
		//上一次章节学习位置
		societyNodeExamineRecord.setLastNodeStudyLocation(societyStudentAndNodeView.getLastLearnLocation());
		//现学习章节位置ID 就是现在需要重学的章节id
		societyNodeExamineRecord.setNowNodeStudyId(nodeId);
		//原因
		societyNodeExamineRecord.setReason(record);
		//数据状态
		societyNodeExamineRecord.setDataState("1");
		//学生课程id
		societyNodeExamineRecord.setOwnerStudentAndCourseId(societyStudentAndCourse.getId());
		//学生章节id
		societyNodeExamineRecord.setOwnerStudentAndNodeId(societyStudentAndNodeView.getId());
		//原学生学习章节id
		societyNodeExamineRecord.setOrgNodeStudyId(societyStudentAndNodeView.getNodeId());
		societyNodeExamineRecordMapper.insert(societyNodeExamineRecord);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void examineStudentNodeListInfo(User user, String stuCourseId, String ownerCourseId,
										   String studentId, String record,String nodeIdStr) {
		//先查询出指定的学生学习情况
		String[] nodeList = nodeIdStr.split(",");
		for(String nodeId : nodeList){
			insertEeamineInfo(stuCourseId,nodeId,ownerCourseId,studentId,user,record);
			//2.删除 学员习题选项信息
			societyStudentPractiseQuestOptionService.deleteByStuIdAndNodeId(nodeId,stuCourseId,studentId);
			//3.删除 学员习题信息
			societyStudentPractiseQuestionService.deleteByStuIdAndNodeId(nodeId,stuCourseId,studentId);
			//4.清空 学员小节关联的信息
			societyStudentAndNodeService.updateByStuNodeInfo(stuCourseId,nodeId,ownerCourseId,studentId);
			//5.清空 学员课程关联的信息
			societyStudentAndCourseService.updateStudentCourseInfo(stuCourseId,nodeId,ownerCourseId,studentId);

			//6.删除证书
			societyStudentCertificateService.deleteByStudentIdAndCourseId(studentId,ownerCourseId);
			//清空人脸
			List<SocietyStudentStudyProcessFace> faceList =
					societyStudentStudyProcessFaceService.selectByStuCouIdAndNodeId(stuCourseId,nodeId);
			for(SocietyStudentStudyProcessFace societyStudentStudyProcessFace : faceList){
				societyStudentStudyProcessFace.setOperatorId(user.getUserId());
				societyStudentStudyProcessFace.setOperatorName(user.getUserName());
				societyStudentStudyProcessFace.setOperatorTime(new Date());
				societyStudentStudyProcessFace.setDataState("0");
				societyStudentStudyProcessFaceService.update(societyStudentStudyProcessFace);
			}
		}

	}
}
