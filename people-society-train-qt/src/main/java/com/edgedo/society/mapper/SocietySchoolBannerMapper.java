package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolBanner;
import com.edgedo.society.queryvo.SocietySchoolBannerQuery;
import com.edgedo.society.queryvo.SocietySchoolBannerView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietySchoolBannerMapper  extends BaseMapper<SocietySchoolBanner>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolBannerView> listPage(SocietySchoolBannerQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolBannerView> listByObj(SocietySchoolBannerQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 查询学校轮播图片
	 * @Date 2020/5/10 10:50
	 **/
	List<String> selectSchoolBannerImg(String ownerSchoolId);

}