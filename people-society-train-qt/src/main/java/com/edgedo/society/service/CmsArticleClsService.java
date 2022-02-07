package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.CmsArticleCls;
import com.edgedo.society.mapper.CmsArticleClsMapper;
import com.edgedo.society.queryvo.CmsArticleClsQuery;
import com.edgedo.society.queryvo.CmsArticleClsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class CmsArticleClsService {
	
	
	@Autowired
	private CmsArticleClsMapper cmsArticleClsMapper;

	public List<CmsArticleClsView> listPage(CmsArticleClsQuery cmsArticleClsQuery){
		List list = cmsArticleClsMapper.listPage(cmsArticleClsQuery);
		cmsArticleClsQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(CmsArticleCls cmsArticleCls) {
		cmsArticleCls.setId(Guid.guid());
		cmsArticleClsMapper.insert(cmsArticleCls);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(CmsArticleCls cmsArticleCls) {
		cmsArticleClsMapper.updateById(cmsArticleCls);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(CmsArticleCls cmsArticleCls) {
		cmsArticleClsMapper.updateAllColumnById(cmsArticleCls);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return cmsArticleClsMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return cmsArticleClsMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public CmsArticleCls loadById(String id) {
		return cmsArticleClsMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 查询所有的新闻分类
	 * @Date 2020/5/19 17:27
	 **/
	public List<CmsArticleClsView> selectAllArticleCls(){
		return cmsArticleClsMapper.selectAllArticleCls();
	}
	

}
