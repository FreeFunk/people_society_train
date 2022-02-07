package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyWxMessage;
import com.edgedo.society.queryvo.SocietyWxMessageQuery;
import com.edgedo.society.queryvo.SocietyWxMessageView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyWxMessageMapper  extends BaseMapper<SocietyWxMessage>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyWxMessageView> listPage(SocietyWxMessageQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyWxMessageView> listByObj(SocietyWxMessageQuery query);
	
	

}