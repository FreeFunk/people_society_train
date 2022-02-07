package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyConfigMinganWords;
import com.edgedo.society.mapper.SocietyConfigMinganWordsMapper;
import com.edgedo.society.queryvo.SocietyConfigMinganWordsQuery;
import com.edgedo.society.queryvo.SocietyConfigMinganWordsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyConfigMinganWordsService {
	
	
	@Autowired
	private SocietyConfigMinganWordsMapper societyConfigMinganWordsMapper;

	public List<SocietyConfigMinganWordsView> listPage(SocietyConfigMinganWordsQuery societyConfigMinganWordsQuery){
		List list = societyConfigMinganWordsMapper.listPage(societyConfigMinganWordsQuery);
		societyConfigMinganWordsQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyConfigMinganWords societyConfigMinganWords) {
		societyConfigMinganWords.setId(Guid.guid());
		societyConfigMinganWordsMapper.insert(societyConfigMinganWords);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyConfigMinganWords societyConfigMinganWords) {
		societyConfigMinganWordsMapper.updateById(societyConfigMinganWords);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyConfigMinganWords societyConfigMinganWords) {
		societyConfigMinganWordsMapper.updateAllColumnById(societyConfigMinganWords);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyConfigMinganWordsMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		return societyConfigMinganWordsMapper.deleteByListId(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyConfigMinganWords loadById(String id) {
		return societyConfigMinganWordsMapper.selectById(id);
	}


	public void updateById(SocietyConfigMinganWords societyConfigMinganWords) {
		societyConfigMinganWordsMapper.updateByWorkId(societyConfigMinganWords);
	}
}
