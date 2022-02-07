package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolBanner;
import com.edgedo.society.queryvo.SocietySchoolBannerQuery;
import com.edgedo.society.queryvo.SocietySchoolBannerView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolBannerMapper  extends BaseMapper<SocietySchoolBanner>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolBannerView> listPage(SocietySchoolBannerQuery query);
	public List<SocietySchoolBannerView> schoolAdminQueryBannerlistPage(SocietySchoolBannerQuery query);

	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolBannerView> listByObj(SocietySchoolBannerQuery query);


    void updateByBannerImg(@Param("societySchoolBanner") SocietySchoolBanner societySchoolBanner);

	/**
	 * 根据id 逻辑删除记录
	 * @param ids
	 */
	void deleteByBannerId(List<String> ids);

	/**
	 * 切换轮播图的启用状态
	 * @param id
	 * @param isEnable
	 */
    void updateEnableState(@Param("id")String id, @Param("isEnable")String isEnable);
}