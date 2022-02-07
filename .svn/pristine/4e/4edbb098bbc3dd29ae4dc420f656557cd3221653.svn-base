package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolBanner;
import com.edgedo.society.mapper.SocietySchoolBannerMapper;
import com.edgedo.society.queryvo.SocietySchoolBannerQuery;
import com.edgedo.society.queryvo.SocietySchoolBannerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolBannerService {
	
	
	@Autowired
	private SocietySchoolBannerMapper societySchoolBannerMapper;

	public List<SocietySchoolBannerView> listPage(SocietySchoolBannerQuery societySchoolBannerQuery){
		List list = societySchoolBannerMapper.listPage(societySchoolBannerQuery);
		societySchoolBannerQuery.setList(list);
		return list;
	}

	public List<SocietySchoolBannerView> schoolAdminQueryBannerlistPage(SocietySchoolBannerQuery societySchoolBannerQuery){
		List list = societySchoolBannerMapper.schoolAdminQueryBannerlistPage(societySchoolBannerQuery);
		societySchoolBannerQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolBanner societySchoolBanner) {
		societySchoolBanner.setId(Guid.guid());
		societySchoolBannerMapper.insert(societySchoolBanner);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolBanner societySchoolBanner) {
		societySchoolBannerMapper.updateById(societySchoolBanner);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolBanner societySchoolBanner) {
		societySchoolBannerMapper.updateAllColumnById(societySchoolBanner);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolBannerMapper.deleteById(id);
	}
	
	/**
	 * 根据id删除轮播图记录 逻辑删除记录
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIds(List<String> ids) {
		societySchoolBannerMapper.deleteByBannerId(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolBanner loadById(String id) {
		return societySchoolBannerMapper.selectById(id);
	}


	public void updateByBannerImg(SocietySchoolBanner societySchoolBanner) {
		societySchoolBannerMapper.updateByBannerImg(societySchoolBanner);
	}

	/**
	 * 切换轮播图的启用状态
	 * @param id
	 * @param isEnable
	 */
	public void updateEnableState(String id, String isEnable) {
		societySchoolBannerMapper.updateEnableState( id,  isEnable);
	}
}
