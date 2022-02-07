package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudentComment;
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
	private SocietyStudentCommentMapper societyStudentCommentMapper;

	public List<SocietyStudentCommentView> listPage(SocietyStudentCommentQuery societyStudentCommentQuery){
		List list = societyStudentCommentMapper.listPage(societyStudentCommentQuery);
		societyStudentCommentQuery.setList(list);
		return list;
	}

	public List<SocietyStudentCommentView> listByObj(SocietyStudentCommentQuery societyStudentCommentQuery){
		List list = societyStudentCommentMapper.listByObj(societyStudentCommentQuery);
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
	

}
