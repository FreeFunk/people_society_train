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
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolBannerMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolBanner loadById(String id) {
		return societySchoolBannerMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 根据学校id查询轮播图片
	 * @Date 2020/5/5 19:05
	 **/
	public List<String> selectSchoolBannerImg(String ownerSchoolId){
		return societySchoolBannerMapper.selectSchoolBannerImg(ownerSchoolId);
	}
	

}
