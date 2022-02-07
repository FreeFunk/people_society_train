package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentAndNode;
import com.edgedo.society.mapper.SocietyStudentAndNodeMapper;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import com.edgedo.society.queryvo.SocietyStudentAndNodeQuery;
import com.edgedo.society.queryvo.SocietyStudentAndNodeView;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SocietyStudentAndNodeService {
	
	
	@Autowired
	private SocietyStudentAndNodeMapper societyStudentAndNodeMapper;
	@Autowired
	private SocietySchoolCourseNodeService courseNodeService;

	public List<SocietyStudentAndNodeView> listPage(SocietyStudentAndNodeQuery societyStudentAndNodeQuery){
		List list = societyStudentAndNodeMapper.listPage(societyStudentAndNodeQuery);
		societyStudentAndNodeQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentAndNode societyStudentAndNode) {
		societyStudentAndNodeMapper.insert(societyStudentAndNode);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentAndNode societyStudentAndNode) {
		societyStudentAndNodeMapper.updateById(societyStudentAndNode);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentAndNode societyStudentAndNode) {
		societyStudentAndNodeMapper.updateAllColumnById(societyStudentAndNode);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentAndNodeMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentAndNodeMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentAndNode loadById(String id) {
		return societyStudentAndNodeMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据学生课程关联id获得 学生章节学习情况
	 * @Date 2020/5/4 11:51
	 **/
    public List<SocietyStudentAndNodeView> selectNodeHisByOwnerCorse(String ownerStuCourseId) {
		return societyStudentAndNodeMapper.selectNodeHisByOwnerCorse(ownerStuCourseId);
    }

    /**
     * @Author WangZhen
     * @Description 根据学生的课程id 和 课程的节点获得学生的学习记录节点
     * @Date 2020/5/6 18:36
     **/
    public SocietyStudentAndNode selectStuCourseNodeByStuCourseIdAndCourseNodeId(
    		String ownerStuCourseId, String nodeId ) {

		return societyStudentAndNodeMapper
				.selectStuCourseNodeByStuCourseIdAndCourseNodeId(
					ownerStuCourseId,nodeId
				);
    }

    /**
     * @Author WangZhen
     * @Description 根据学生和学生的课程记录 和课程的节点id插入一条学生和章节节点关联
     * @Date 2020/5/6 18:44
     **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietyStudentAndNode insertByStuAndStuCourse(
			SocietyStudent student,
			SocietyStudentAndCourseView stuCourse,
			String nodeId) {
		SocietySchoolCourseNode courseNode = courseNodeService.loadById(nodeId);
		SocietyStudentAndNode stuAndNode = new SocietyStudentAndNode();
		stuAndNode.setCreateTime(new Date());
		stuAndNode.setId(Guid.guid());
		stuAndNode.setLastLearnLocation(0);
		stuAndNode.setLastQuestionId(null);
		stuAndNode.setLearnIsFinished("0");
		stuAndNode.setNodeId(nodeId);
		stuAndNode.setNodeName(courseNode.getNodeName());
		stuAndNode.setNodeProgress(new BigDecimal(0));
		stuAndNode.setNodeQuestionScore(0);
		stuAndNode.setStudyTimeLength(0);
		stuAndNode.setOwnerSchoolId(student.getOwnerSchoolId());
		stuAndNode.setOwnerStudentAndCourseId(stuCourse.getId());
		stuAndNode.setQuestionIsFinished("0");
		stuAndNode.setQuestionIsPass("0");
		stuAndNode.setLastLearnLocation(0);
		stuAndNode.setMaxLearnLocation(0);
		stuAndNode.setStudentId(student.getId());
		stuAndNode.setOwnerCourseId(stuCourse.getCourseId());
		stuAndNode.setOwnerCourseName(stuCourse.getCourseName());
		stuAndNode.setStudentIdCardNum(student.getStudentIdCardNum());
		stuAndNode.setStudentName(student.getStudentName());
		stuAndNode.setDataState("1");
		societyStudentAndNodeMapper.insert(stuAndNode);
		return stuAndNode;

	}
	/**
	 * @Author WangZhen
	 * @Description 更新学习记录的学习时长
	 * @Date 2020/5/6 20:10
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateStuNodeStudyTime(String id, int studyTimeLength) {

		return societyStudentAndNodeMapper.updateStuNodeStudyTime(id,studyTimeLength);
	}
	/**
	 * @Author WangZhen
	 * @Description 更新学习记录的学习时长 同时更细上次学到第几秒
	 * @Date 2020/5/6 20:10
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateStuNodeStudyTime(String id, int studyTimeLength, Integer lastLearnLocation) {
		if(lastLearnLocation==null){lastLearnLocation=0;}
		return societyStudentAndNodeMapper.updateStuNodeStudyTimeWithLastSec(id,studyTimeLength,lastLearnLocation);
	}
	/**
	 * @Author WangZhen
	 * @Description 给学习记录增加时长
	 * @Date 2020/5/6 20:10
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateStuNodeAddSecond(String id, int studyTimeLength) {
		return societyStudentAndNodeMapper.updateStuNodeAddSecond(id,studyTimeLength);
	}

	/**
	 * @Author WangZhen
	 * @Description 给学习记录增加时长,同时更新上次学习到位置
	 * @Date 2020/5/6 20:10
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateStuNodeAddSecond(String id, int studyTimeLength,Integer lastLearnLocation) {
		if(lastLearnLocation==null){lastLearnLocation=0;}
		return societyStudentAndNodeMapper.updateStuNodeAddSecondWithLastSec(id,studyTimeLength,lastLearnLocation);
	}


	/**
	 * @Author WangZhen
	 * @Description 学习结束
	 * @Date 2020/5/7 9:21
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public int finishStuCourseNode(SocietyStudentAndNode studentAndNode) {
		return societyStudentAndNodeMapper.finishStuCourseNode(studentAndNode);
    }

    /**
     * @Author WangZhen
     * @Description 统计已经完结的章节数量
     * @Date 2020/5/7 9:36
     **/
	public int countFinishNodeOfStuCourse(String stuCourseId) {
		return societyStudentAndNodeMapper.countFinishNodeOfStuCourse(stuCourseId);
	}

	/**
	 * @Author WangZhen
	 * @Description 学习读秒时长不够补时长
	 * @Date 2020/5/7 10:12
	 **/
	public int finishStuCourseNodeLessTime(SocietyStudentAndNode studentAndNode) {
		return societyStudentAndNodeMapper.finishStuCourseNodeLessTime(studentAndNode);
	}

	/**
	 * @Author WangZhen
	 * @Description 统计没通过习题的节点数
	 * @Date 2020/7/15 10:58
	 **/
    public int countPractiseUnPassNodeOfStuCourse(String stuCourseId) {
		return societyStudentAndNodeMapper.countPractiseUnPassNodeOfStuCourse(stuCourseId);
    }

    public List<SocietyStudentAndNodeView> selectAAA() {
		return societyStudentAndNodeMapper.selectAAA();
    }

	public void deleteById(String id) {
		SocietyStudentAndNode param =new SocietyStudentAndNode();
		param.setId(id);
		param.setDataState("0");
		societyStudentAndNodeMapper.updateById(param);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据唯一索引更新学习情况
	 * @Date 2020/8/13 9:27
	 **/
	public int updateStudyInfoByOwnerStuCourseIdAndNodeId(SocietyStudentAndNode studentAndNode) {
		return societyStudentAndNodeMapper.updateStudyInfoByOwnerStuCourseIdAndNodeId(studentAndNode);
	}
}
