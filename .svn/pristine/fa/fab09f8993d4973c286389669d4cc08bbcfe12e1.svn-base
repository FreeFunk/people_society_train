package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.entity.SocietySchoolConfig;
import com.edgedo.society.entity.SocietySchoolConfigKey;
import com.edgedo.society.mapper.SocietySchoolConfigKeyMapper;
import com.edgedo.society.mapper.SocietySchoolConfigMapper;
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
	@Autowired
	private SocietySchoolService societySchoolService;
	@Autowired
	private SocietySchoolConfigMapper societySchoolConfigMapper;

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
		societySchoolConfigKey.setId(societySchoolConfigKey.getConfigKey());
		societySchoolConfigKeyMapper.insert(societySchoolConfigKey);
		//给学校配置当前的配置
		List<SocietySchool> list = societySchoolService.selectByAll();
		for(SocietySchool societySchool : list){//societySchoolConfigMapper
			SocietySchoolConfig societySchoolConfigOld =
					societySchoolConfigMapper.selectBySchoolIdAndKey(societySchool.getId(),societySchoolConfigKey.getConfigKey());
			if(societySchoolConfigOld==null){
				SocietySchoolConfig societySchoolConfig = new SocietySchoolConfig();
				societySchoolConfig.setId(Guid.guid());
				societySchoolConfig.setConfigKeyId(societySchoolConfigKey.getId());
				societySchoolConfig.setCreateTime(new Date());
				societySchoolConfig.setOwnerSchoolId(societySchool.getId());
				societySchoolConfig.setOwnerSchoolName(societySchool.getSchoolName());
				societySchoolConfig.setConfigKey(societySchoolConfigKey.getConfigKey());
				societySchoolConfig.setConfigValue(societySchoolConfigKey.getConfigValue());
				societySchoolConfig.setRemark(societySchoolConfigKey.getRemark());
				societySchoolConfig.setDataState("1");
				societySchoolConfigMapper.insert(societySchoolConfig);
			}else {
				continue;
			}
		}
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolConfigKey societySchoolConfigKey) {
		societySchoolConfigKeyMapper.updateAllById(societySchoolConfigKey);
		//更新所有的学校的配置
		List<SocietySchool> list = societySchoolService.selectByAll();
		for(SocietySchool societySchool : list){//societySchoolConfigMapper
			SocietySchoolConfig societySchoolConfigOld =
					societySchoolConfigMapper.selectBySchoolIdAndKey(societySchool.getId(),societySchoolConfigKey.getConfigKey());
			if(societySchoolConfigOld==null){
				SocietySchoolConfig societySchoolConfig = new SocietySchoolConfig();
				societySchoolConfig.setId(Guid.guid());
				societySchoolConfig.setConfigKeyId(societySchoolConfigKey.getId());
				societySchoolConfig.setCreateTime(new Date());
				societySchoolConfig.setOwnerSchoolId(societySchool.getId());
				societySchoolConfig.setOwnerSchoolName(societySchool.getSchoolName());
				societySchoolConfig.setConfigKey(societySchoolConfigKey.getConfigKey());
				societySchoolConfig.setConfigValue(societySchoolConfigKey.getConfigValue());
				societySchoolConfig.setRemark(societySchoolConfigKey.getRemark());
				societySchoolConfig.setDataState("1");
				societySchoolConfigMapper.insert(societySchoolConfig);
			}else {
				societySchoolConfigOld.setConfigKeyId(societySchoolConfigKey.getId());
				societySchoolConfigOld.setUpdateTime(new Date());
				societySchoolConfigOld.setConfigKey(societySchoolConfigKey.getConfigKey());
				societySchoolConfigOld.setConfigValue(societySchoolConfigKey.getConfigValue());
				societySchoolConfigOld.setRemark(societySchoolConfigKey.getRemark());
				societySchoolConfigMapper.updateByIdAndSchoolId(societySchoolConfigOld);
			}
		}
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
		societySchoolConfigKeyMapper.logicalDeletion(ids);
		List<SocietySchool> list = societySchoolService.selectByAll();
		for(SocietySchool societySchool : list){
			for(String configId : ids){
				societySchoolConfigMapper.updateByIdAndKeyId(societySchool.getId(),configId);
			}
		}
		return 1;
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolConfigKey loadById(String id) {
		return societySchoolConfigKeyMapper.selectById(id);
	}
	

}
