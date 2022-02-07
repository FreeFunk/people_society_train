package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseNodeService {
	
	
	@Autowired
	private SocietySchoolCourseNodeMapper societySchoolCourseNodeMapper;

	public List<SocietySchoolCourseNodeView> listPage(SocietySchoolCourseNodeQuery societySchoolCourseNodeQuery){
		List list = societySchoolCourseNodeMapper.listPage(societySchoolCourseNodeQuery);
		societySchoolCourseNodeQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseNode societySchoolCourseNode) {
		societySchoolCourseNode.setId(Guid.guid());
		societySchoolCourseNodeMapper.insert(societySchoolCourseNode);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseNode societySchoolCourseNode) {
		societySchoolCourseNodeMapper.updateById(societySchoolCourseNode);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseNode societySchoolCourseNode) {
		societySchoolCourseNodeMapper.updateAllColumnById(societySchoolCourseNode);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseNodeMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseNodeMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseNode loadById(String id) {
		return societySchoolCourseNodeMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据课程id 查询课程章节
	 * @Date 2020/5/4 13:51
	 **/
    public List<SocietySchoolCourseNodeView> selectCousrseNodesByCourseIdSimple(String courseId) {
		return societySchoolCourseNodeMapper.selectCousrseNodesByCourseIdSimple(courseId);
    }

	/**
	 * @Author WangZhen
	 * @Description 根据主键查询节点名
	 * @Date 2020/5/4 16:58
	 **/
	public String selectCousrseNodeNameById(String id) {
		return societySchoolCourseNodeMapper.selectCousrseNodeNameById(id);
	}

}
