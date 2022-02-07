package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyTestPaper;
import com.edgedo.society.mapper.SocietyTestPaperMapper;
import com.edgedo.society.queryvo.SocietyTestPaperQuery;
import com.edgedo.society.queryvo.SocietyTestPaperView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyTestPaperService {
	
	
	@Autowired
	private SocietyTestPaperMapper societyTestPaperMapper;

	public List<SocietyTestPaperView> listPage(SocietyTestPaperQuery societyTestPaperQuery){
		List list = societyTestPaperMapper.listPage(societyTestPaperQuery);
		societyTestPaperQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyTestPaper societyTestPaper) {
		societyTestPaper.setId(Guid.guid());
		societyTestPaperMapper.insert(societyTestPaper);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyTestPaper societyTestPaper) {
		societyTestPaperMapper.updateById(societyTestPaper);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyTestPaper societyTestPaper) {
		societyTestPaperMapper.updateAllColumnById(societyTestPaper);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyTestPaperMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyTestPaperMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyTestPaper loadById(String id) {
		return societyTestPaperMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据课程和学校查询试卷
	 * @Date 2020/5/11 12:49
	 **/
	public SocietyTestPaperView selectVoByCourseAndSchool(String ownerSchoolId,String ownerCourseId){
		Map<String,Object> param = new HashMap<>();
		param.put("ownerSchoolId",ownerSchoolId);
		param.put("ownerCourseId",ownerCourseId);
		return societyTestPaperMapper.selectVoByCourseAndSchool(param);
	}
	

}
