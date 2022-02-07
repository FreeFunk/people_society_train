package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolConfig;
import com.edgedo.society.entity.SocietySchoolConfigKey;
import com.edgedo.society.mapper.SocietySchoolConfigMapper;
import com.edgedo.society.queryvo.SocietySchoolConfigQuery;
import com.edgedo.society.queryvo.SocietySchoolConfigView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolConfigService {
	
	
	@Autowired
	private SocietySchoolConfigMapper societySchoolConfigMapper;

	@Autowired
	private SocietySchoolConfigKeyService schoolConfigKeyService;

	public List<SocietySchoolConfigView> listPage(SocietySchoolConfigQuery societySchoolConfigQuery){
		List list = societySchoolConfigMapper.listPage(societySchoolConfigQuery);
		societySchoolConfigQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolConfig societySchoolConfig) {
		societySchoolConfig.setId(Guid.guid());
		societySchoolConfigMapper.insert(societySchoolConfig);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolConfig societySchoolConfig) {
		societySchoolConfigMapper.updateById(societySchoolConfig);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolConfig societySchoolConfig) {
		societySchoolConfigMapper.updateAllColumnById(societySchoolConfig);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolConfigMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolConfigMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolConfig loadById(String id) {
		return societySchoolConfigMapper.selectById(id);
	}


	/**
	 *@Author:ZhaoSiDa
	 *@Description: 根据学校id和key查询
	 *@DateTime: 2020/7/8 11:44
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietySchoolConfig selectBySchoolIdAndKey(String schoolId, String configKey) {
		return societySchoolConfigMapper.selectBySchoolIdAndKey(schoolId,configKey);
	}
	/**
	 *@Author:ZhaoSiDa
	 *@Description: 公共的获取配置的value
	 *@DateTime: 2020/7/8 11:44
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String getSchoolConfigValue(String schoolId, String configKey) {
		String configValue = "";
		SocietySchoolConfig schoolConfig  = societySchoolConfigMapper.selectBySchoolIdAndKey(schoolId,configKey);
		if(schoolConfig==null){
			SocietySchoolConfigKey schoolConfigKey = schoolConfigKeyService.loadById(configKey);
			configValue = schoolConfigKey.getConfigValue();
		}else {
			configValue = schoolConfig.getConfigValue();
		}
		return configValue;
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByIdAndKey(SocietySchoolConfig voObj) {
		societySchoolConfigMapper.updateByIdAndKey(voObj);
	}
}
