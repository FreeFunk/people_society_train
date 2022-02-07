package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.CmsArticleContent;
import com.edgedo.society.queryvo.CmsArticleContentQuery;
import com.edgedo.society.queryvo.CmsArticleContentView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CmsArticleContentMapper  extends BaseMapper<CmsArticleContent>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<CmsArticleContentView> listPage(CmsArticleContentQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<CmsArticleContentView> listByObj(CmsArticleContentQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 更新点击量
	 * @Date 2020/5/15 16:46
	 **/
	int updateClickNum(Map<String,Object> param);

}