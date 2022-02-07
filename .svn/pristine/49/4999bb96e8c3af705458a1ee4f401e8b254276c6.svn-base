package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.CmsArticleContent;
import com.edgedo.society.mapper.CmsArticleContentMapper;
import com.edgedo.society.queryvo.CmsArticleContentQuery;
import com.edgedo.society.queryvo.CmsArticleContentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class CmsArticleContentService {
	
	
	@Autowired
	private CmsArticleContentMapper cmsArticleContentMapper;

	public List<CmsArticleContentView> listPage(CmsArticleContentQuery cmsArticleContentQuery){
		List list = cmsArticleContentMapper.listPage(cmsArticleContentQuery);
		cmsArticleContentQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(CmsArticleContent cmsArticleContent) {
		cmsArticleContent.setId(Guid.guid());
		cmsArticleContentMapper.insert(cmsArticleContent);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(CmsArticleContent cmsArticleContent) {
		cmsArticleContentMapper.updateById(cmsArticleContent);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(CmsArticleContent cmsArticleContent) {
		cmsArticleContentMapper.updateAllColumnById(cmsArticleContent);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return cmsArticleContentMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return cmsArticleContentMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public CmsArticleContent loadById(String id) {
		return cmsArticleContentMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 更新点击量
	 * @Date 2020/5/15 16:46
	 **/
	public int updateClickNum(String id,int clickNum){
		Map<String,Object> param = new HashMap<>();
		param.put("id",id);
		param.put("clickNum",clickNum);
		return cmsArticleContentMapper.updateClickNum(param);
	}

}
