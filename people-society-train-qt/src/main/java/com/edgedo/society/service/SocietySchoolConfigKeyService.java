package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolConfigKey;
import com.edgedo.society.mapper.SocietySchoolConfigKeyMapper;
import com.edgedo.society.queryvo.SocietySchoolConfigKeyQuery;
import com.edgedo.society.queryvo.SocietySchoolConfigKeyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolConfigKeyService {
	
	
	@Autowired
	private SocietySchoolConfigKeyMapper societySchoolConfigKeyMapper;

	public List<SocietySchoolConfigKeyView> listPage(SocietySchoolConfigKeyQuery societySchoolConfigKeyQuery){
		List list = societySchoolConfigKeyMapper.listPage(societySchoolConfigKeyQuery);
		societySchoolConfigKeyQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolConfigKey societySchoolConfigKey) {
		societySchoolConfigKey.setId(Guid.guid());
		societySchoolConfigKeyMapper.insert(societySchoolConfigKey);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolConfigKey societySchoolConfigKey) {
		societySchoolConfigKeyMapper.updateById(societySchoolConfigKey);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolConfigKey societySchoolConfigKey) {
		societySchoolConfigKeyMapper.updateAllColumnById(societySchoolConfigKey);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolConfigKeyMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolConfigKeyMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolConfigKey loadById(String id) {
		return societySchoolConfigKeyMapper.selectById(id);
	}
	

}
