package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.CmsArticleCls;
import com.edgedo.society.queryvo.CmsArticleClsQuery;
import com.edgedo.society.queryvo.CmsArticleClsView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CmsArticleClsMapper  extends BaseMapper<CmsArticleCls>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<CmsArticleClsView> listPage(CmsArticleClsQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<CmsArticleClsView> listByObj(CmsArticleClsQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 查询所有的新闻分类
	 * @Date 2020/5/19 17:27
	 **/
	public List<CmsArticleClsView> selectAllArticleCls();

}