package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BusinessException;
import com.edgedo.common.util.Guid;
import com.edgedo.face.entity.FaceMatchInfoExt;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietyStudentStudyProcessFaceMapper;
import com.edgedo.society.mapper.SocietyStudentStudyProcessMapper;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessQuery;
import com.edgedo.society.queryvo.SocietyStudentStudyProcessView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentStudyProcessService {


	//是否启用人脸识别
	@Value("${app.studyfacecheck}")
	private boolean studyfacecheck;
	@Autowired
	private SocietyStudentStudyProcessMapper societyStudentStudyProcessMapper;
	@Autowired
	private SocietySchoolCourseNodeService schoolCourseNodeService;
	@Autowired
	private SocietySchoolCourseService schoolCourseService;
	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;
	@Autowired
	private SocietyStudentStudyProcessFaceService studyProcessFaceService;
    @Autowired
	private SocietyStudentAndCourseService studentAndCourseService;
	@Autowired
    private SocietySchoolCourseNodeService courseNodeService;
	@Autowired
	private SocietySchoolCourseNodeQuestionService stuNodeQuestionService;

	public List<SocietyStudentStudyProcessView> listPage(SocietyStudentStudyProcessQuery societyStudentStudyProcessQuery){
		List list = societyStudentStudyProcessMapper.listPage(societyStudentStudyProcessQuery);
		societyStudentStudyProcessQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentStudyProcess societyStudentStudyProcess) {
		societyStudentStudyProcess.setId(Guid.guid());
		societyStudentStudyProcessMapper.insert(societyStudentStudyProcess);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentStudyProcess societyStudentStudyProcess) {
		societyStudentStudyProcessMapper.updateById(societyStudentStudyProcess);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentStudyProcess societyStudentStudyProcess) {
		societyStudentStudyProcessMapper.updateAllColumnById(societyStudentStudyProcess);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentStudyProcessMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentStudyProcessMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentStudyProcess loadById(String id) {
		return societyStudentStudyProcessMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 插入一条新的学习记录
	 * @Date 2020/5/4 16:47
	 **/
	@Transactional
    public SocietyStudentStudyProcess insertBeginProgress(SocietyStudent student,
									SocietyStudentAndCourseView stuCourse,
									String courseNodeId, String faceId) {
		SocietyStudentStudyProcess studyProcess = new SocietyStudentStudyProcess();
		studyProcess.setId(Guid.guid());
		studyProcess.setBeginFaceId(faceId);
		Date cur = new Date();

		studyProcess.setCreateTime(cur);
		studyProcess.setStartTime(cur);
		//上次更新时间
		studyProcess.setEndTime(cur);
		studyProcess.setDataState("1");
		studyProcess.setOwnerCourseId(stuCourse.getCourseId());
		studyProcess.setOwnerCourseName(stuCourse.getCourseName());
		studyProcess.setOwnerNodeId(courseNodeId);
		String courseNodeName = schoolCourseNodeService.selectCousrseNodeNameById(courseNodeId);
		studyProcess.setOwnerNodeName(courseNodeName);

		studyProcess.setOwnerSchoolId(student.getOwnerSchoolId());
		studyProcess.setOwnerSchoolName(student.getOwnerSchoolName());
		studyProcess.setStuCourseId(stuCourse.getId());
		studyProcess.setStudentId(student.getId());
		studyProcess.setStudentName(student.getStudentName());
		studyProcess.setStudyTimeLength(0);

		societyStudentStudyProcessMapper.insert(studyProcess);
		//判断下是否有学员的 课程章节关联,如果没有插入一个
		return studyProcess;

    }

	/**
	 * @Author WangZhen
	 * @Description 刷新
	 * @param isBegin 标识是不是最开始的那一次刷新
	 * @param studentAndNode
	 * @Date 2020/5/6 17:38
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietyStudentAndNode freshProgress(SocietyStudent student,
											   SocietyStudentUnique studentUnique,
											   SocietyStudentAndCourseView stuCourse,
											   String courseNodeId, FaceMatchInfoExt faceMatchInfoExt,
											   boolean isBegin,
											   Integer lastLearnLocation,
											   SocietyStudentAndNode studentAndNode) {
		String faceId = "none";
		if(studyfacecheck &&faceMatchInfoExt!=null){
			faceId = faceMatchInfoExt.getId();
		}
		//根据faceid,courseNodeId,stuCourseId 查询是否存在一条已经开始的的
		SocietyStudentStudyProcess process = societyStudentStudyProcessMapper.selectProgressOfStuOfCourseByFace(
				student.getId(),stuCourse.getId(),courseNodeId,faceId
		);

		if(process==null){
			//插入学习过程
			process = insertBeginProgress(student,stuCourse,courseNodeId,faceId);
		}else{
			//如果是最开始那一下就不加时长了
			if(!isBegin){
				//判断上次更新时间到现在不足18秒 就不更新时长
				Date endTime = process.getEndTime();
				long lastUpLong = endTime.getTime();
				long curLong = System.currentTimeMillis();
				int secondNum = 60;
				if((curLong-lastUpLong)<60000){//如果调用时间大于1分钟就算一分钟
					secondNum = (int)(curLong-lastUpLong)/1000;//计算时间这是第一层保险
				}

				//给学习过程加20秒
				int rows = societyStudentStudyProcessMapper.updateProcessAddSecond(
						process.getId(),secondNum);
				Integer studyTimeLength = studentAndNode.getStudyTimeLength();
				if(studyTimeLength==null)studyTimeLength=0;
				String isFinished = studentAndNode.getLearnIsFinished();
				studyTimeLength += secondNum;
				studentAndNode.setStudyTimeLength(studyTimeLength);
				studentAndNode.setLastLearnLocation(lastLearnLocation);
				//设置最大学习秒数
				Integer maxLearnLocation = studentAndNode.getMaxLearnLocation();
				if(maxLearnLocation==null){
					maxLearnLocation = lastLearnLocation;
				}else{
					if(maxLearnLocation<lastLearnLocation){
						maxLearnLocation =  lastLearnLocation;
					}
				}
				studentAndNode.setMaxLearnLocation(maxLearnLocation);
				//加时长、修改进度
				if(isFinished!=null && isFinished.equals("1")){//如果章节已经完成那么进度就是1
					studentAndNode.setNodeProgress(new BigDecimal(100));
				}else{
					SocietySchoolCourseNode node = courseNodeService.loadById(courseNodeId);
					int nodeTimeLenth = node.getNodeTimeLength();
					studentAndNode.setNodeProgress(new BigDecimal(lastLearnLocation*100/nodeTimeLenth));

					//排除拖动到末尾的
					if(studyTimeLength<maxLearnLocation && maxLearnLocation<nodeTimeLenth){

						if(maxLearnLocation-studyTimeLength>60){//计时的第二到保险
							studyTimeLength = studyTimeLength + 60;
						}else{
							studyTimeLength = maxLearnLocation;
						}
					}


				}
				studentAndNode.setStudyTimeLength(studyTimeLength);
				//尝试解决唯一索引导致死锁的问题 不更新ownerStuCourseId和nodeId
				SocietyStudentAndNode studentAndNodeParam = new SocietyStudentAndNode();
				studentAndNodeParam.setId(studentAndNode.getId());
				studentAndNodeParam.setStudyTimeLength(studentAndNode.getStudyTimeLength());
				studentAndNodeParam.setLastLearnLocation(studentAndNode.getLastLearnLocation());
				studentAndNodeParam.setMaxLearnLocation(studentAndNode.getMaxLearnLocation());
				studentAndNodeParam.setNodeProgress(studentAndNode.getNodeProgress());
				studentAndNodeService.update(studentAndNode);


				/*if(studyTimeLength==null){
					studentAndNodeService.updateStuNodeStudyTime(studentAndNode.getId(),secondNum,lastLearnLocation);
				}else{
					studentAndNodeService.updateStuNodeAddSecond(
							studentAndNode.getId(),secondNum,lastLearnLocation);
				}*/
			}
		}
		//设置这个课程当前学员学习的位置
		stuCourse.setLastLearnNodeId(courseNodeId);
		studentAndCourseService.updateLastLearnNode(stuCourse);
		//判断下这个人脸是否已经分配给了某个学习过程
		if(studyfacecheck && faceMatchInfoExt!=null){
			String oId = faceMatchInfoExt.getoId();
			if(oId == null){
				//将这个人脸记录的所属业务id更新，并且更新rendis缓存
				studyProcessFaceService.updateFaceRecGiveToLearnProgress(faceMatchInfoExt,
						process,studentUnique);
			}
		}


		return studentAndNode;
	}


	/**
	 * @Author WangZhen
	 * @Description 根据faceid,courseNodeId,stucouseId查询 一条记录
	 * @Date 2020/5/6 17:42
	 **/
	public SocietyStudentStudyProcess selectProgressOfStuOfCourseByFace(
			String stuId,String stuCourseId,
			String courseNodeId,String faceId
	){

		return societyStudentStudyProcessMapper.selectProgressOfStuOfCourseByFace(
				stuId,stuCourseId,courseNodeId,faceId
		);

	}

	/**
	 * @Author WangZhen
	 * @Description 学生学习课程章节结束
	 * 1.判断学习的时长是否和视频时长能差不多
	 * 2.设置学生课程章节学习结束状态和设置结束时间
	 * 4.设置学生课程的学习进度
	 * @Date 2020/5/7 9:03
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public SocietyStudentAndNode finishStuCourseNode(
			SocietyStudent student,
			SocietyStudentAndCourseView stuCourse,
			String courseNodeId, SocietyStudentAndNode studentAndNode,
			SocietySchoolCourseNode courseNode) {


		int studyTimeLength = studentAndNode.getStudyTimeLength();
		int nodeTimeLength = courseNode.getNodeTimeLength();
		studentAndNode.setFinishTime(new Date());
		studentAndNode.setLearnIsFinished("1");
		//判断习题个数，如果习题个数是0那么练习直接通过
		int testQueNum = stuNodeQuestionService.countQuesNumByNodeId(courseNodeId);
		if(testQueNum==0){
			studentAndNode.setNodeQuestionScore(0);
			studentAndNode.setQuestionIsPass("1");
			studentAndNode.setQuestionIsFinished("1");
		}
		//学习分钟计时不精确导致时长少 把时长设置为视频时长
		if(studyTimeLength<nodeTimeLength){
			studentAndNode.setStudyTimeLength(nodeTimeLength);
			studentAndNodeService.finishStuCourseNodeLessTime(studentAndNode);
		}else{//学习时长不少直接就结束
			studentAndNodeService.finishStuCourseNode(studentAndNode);
		}
		//刷新课程进度
		studentAndCourseService.updateFreshLearnInfo(stuCourse.getId(),stuCourse.getCourseId(),student);


        return studentAndNode;
    }

}
