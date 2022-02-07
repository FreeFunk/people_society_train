package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyConfigMinganWords;
import com.edgedo.society.queryvo.SocietyConfigMinganWordsQuery;
import com.edgedo.society.queryvo.SocietyConfigMinganWordsView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyConfigMinganWordsMapper  extends BaseMapper<SocietyConfigMinganWords>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyConfigMinganWordsView> listPage(SocietyConfigMinganWordsQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyConfigMinganWordsView> listByObj(SocietyConfigMinganWordsQuery query);

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
    int deleteByListId(List<String> ids);

	/**
	 * 根据id修改
	 * @param societyConfigMinganWords
	 */
    void updateByWorkId(@Param("societyConfigMinganWords") SocietyConfigMinganWords societyConfigMinganWords);
}