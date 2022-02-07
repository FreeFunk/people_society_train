package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolMajor;
import com.edgedo.society.mapper.SocietySchoolMajorMapper;
import com.edgedo.society.queryvo.SocietySchoolMajorQuery;
import com.edgedo.society.queryvo.SocietySchoolMajorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolMajorService {
	
	
	@Autowired
	private SocietySchoolMajorMapper societySchoolMajorMapper;

	public List<SocietySchoolMajorView> listPage(SocietySchoolMajorQuery societySchoolMajorQuery){
		List list = societySchoolMajorMapper.listPage(societySchoolMajorQuery);
		societySchoolMajorQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolMajor societySchoolMajor) {
		societySchoolMajor.setId(Guid.guid());
		societySchoolMajorMapper.insert(societySchoolMajor);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolMajor societySchoolMajor) {
		societySchoolMajorMapper.updateById(societySchoolMajor);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolMajor societySchoolMajor) {
		societySchoolMajorMapper.updateAllColumnById(societySchoolMajor);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolMajorMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolMajorMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolMajor loadById(String id) {
		return societySchoolMajorMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学校查询所有的专业
	 * @Date 2020/5/5 20:20
	 **/
	public List<SocietySchoolMajorView> listAllBySchool(String ownerSchoolId){
		return societySchoolMajorMapper.listAllBySchool(ownerSchoolId);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学校查询4个专业
	 * @Date 2020/5/8 9:51
	 **/
	public List<SocietySchoolMajorView> listBySchoolLimit4(String ownerSchoolId){
		return societySchoolMajorMapper.listBySchoolLimit4(ownerSchoolId);
	}
	

}
