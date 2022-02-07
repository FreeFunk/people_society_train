package com.edgedo.society.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyWxMessageRec;
import com.edgedo.society.queryvo.SocietyWxMessageRecQuery;
import com.edgedo.society.queryvo.SocietyWxMessageRecView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyWxMessageRecMapper  extends BaseMapper<SocietyWxMessageRec>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyWxMessageRecView> listPage(SocietyWxMessageRecQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyWxMessageRecView> listByObj(SocietyWxMessageRecQuery query);

    List<SocietyWxMessageRecView> selectAllRec();

	void updateOnId(String id, Date date);
}