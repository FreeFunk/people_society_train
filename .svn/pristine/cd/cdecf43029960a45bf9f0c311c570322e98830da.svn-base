package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyWxMessageRec;
import com.edgedo.society.mapper.SocietyWxMessageRecMapper;
import com.edgedo.society.queryvo.SocietyWxMessageRecQuery;
import com.edgedo.society.queryvo.SocietyWxMessageRecView;
import com.edgedo.wxtxmsgclient.ISysWxTxMsgClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyWxMessageRecService {
	
	
	@Autowired
	private SocietyWxMessageRecMapper societyWxMessageRecMapper;


	public List<SocietyWxMessageRecView> listPage(SocietyWxMessageRecQuery societyWxMessageRecQuery){
		List list = societyWxMessageRecMapper.listPage(societyWxMessageRecQuery);
		societyWxMessageRecQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyWxMessageRec societyWxMessageRec) {
		societyWxMessageRec.setId(Guid.guid());
		societyWxMessageRecMapper.insert(societyWxMessageRec);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyWxMessageRec societyWxMessageRec) {
		societyWxMessageRecMapper.updateById(societyWxMessageRec);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyWxMessageRec societyWxMessageRec) {
		societyWxMessageRecMapper.updateAllColumnById(societyWxMessageRec);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyWxMessageRecMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyWxMessageRecMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyWxMessageRec loadById(String id) {
		return societyWxMessageRecMapper.selectById(id);
	}
	

}
