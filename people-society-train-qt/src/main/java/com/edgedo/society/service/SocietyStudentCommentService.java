package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.edgedo.common.util.BadWordsUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyStudentComment;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.mapper.SocietyStudentCommentMapper;
import com.edgedo.society.queryvo.SocietyStudentCommentQuery;
import com.edgedo.society.queryvo.SocietyStudentCommentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentCommentService {
	@Autowired
	SocietyConfigMinganWordsService minganWordsService;
	@Autowired
	private SocietyStudentCommentMapper societyStudentCommentMapper;
	@Autowired
	private SocietySchoolCourseNodeService courseNodeService;

	public List<SocietyStudentCommentView> listPage(SocietyStudentCommentQuery societyStudentCommentQuery){
		List list = societyStudentCommentMapper.listPage(societyStudentCommentQuery);
		societyStudentCommentQuery.setList(list);
		return list;
	}

	/**
	 * @Author WangZhen
	 * @Description  分页显示简要信息
	 * @Date 2020/5/13 14:34
	 **/
	public List<SocietyStudentCommentView> listPageSimple(SocietyStudentCommentQuery societyStudentCommentQuery) {
		List list = societyStudentCommentMapper.listPageSimple(societyStudentCommentQuery);
		societyStudentCommentQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentComment societyStudentComment) {
		societyStudentComment.setId(Guid.guid());
		societyStudentCommentMapper.insert(societyStudentComment);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentComment societyStudentComment) {
		societyStudentCommentMapper.updateById(societyStudentComment);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentComment societyStudentComment) {
		societyStudentCommentMapper.updateAllColumnById(societyStudentComment);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentCommentMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentCommentMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentComment loadById(String id) {
		return societyStudentCommentMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据课程查询评论数量
	 * @Date 2020/5/6 18:31
	 **/
	public int countByCourseId(String ownerCourseId){
		return societyStudentCommentMapper.countByCourseId(ownerCourseId);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据课程查询评论
	 * @Date 2020/5/5 20:49
	 **/
	public List<SocietyStudentCommentView> selectCommentByCourseId(String ownerCourseId){
		return societyStudentCommentMapper.selectCommentByCourseId(ownerCourseId);
	}

	/**
	 * @Author WangZhen
	 * @Description 用户提交评论
	 * @Date 2020/5/8 15:44
	 **/
    public String insertComment(SocietyStudentComment voObj,
								SocietyStudentUnique student,String schoolId) {
    	String courseNodeId = voObj.getOwnerNodeId();

		SocietySchoolCourseNode nodeObj = courseNodeService.loadById(courseNodeId);
		//TODO:兼容课程共享的方式
		if(nodeObj == null){
			return "未找到章节";
		}
		String commentText = voObj.getCommentText();
		if(commentText==null || commentText.equals("")){
			return "no comment text";
		}
		//TODO: 敏感字过滤等待开发

		String text = minganWordsService.replaceBadWord(commentText);

		voObj.setCreateTime(new Date());
		voObj.setId(Guid.guid());
		voObj.setOwnerCourseId(nodeObj.getOwnerCourseId());
		voObj.setOwnerCourseName(nodeObj.getOwnerCourseName());
		voObj.setOwnerNodeId(nodeObj.getId());
		voObj.setOwnerNodeName(nodeObj.getNodeName());
		//TODO:兼容课程共享的方式 需要修改这里
		voObj.setOwnerSchoolId(nodeObj.getOwnerSchoolId());
		voObj.setOwnerSchoolName(nodeObj.getOwnerSchoolName());
		voObj.setOwnerStudentId(student.getId());
		voObj.setOwnerStudentName(student.getStudentName());
		voObj.setCommentText(text);
		voObj.setStuNickName(student.getNickName());
		voObj.setStuHeadPhoto(student.getHeadPhoto());
		societyStudentCommentMapper.insert(voObj);
		return null;
    }


}
