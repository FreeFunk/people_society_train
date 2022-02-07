package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyFileSaveRecord;
import com.edgedo.society.mapper.SocietyFileSaveRecordMapper;
import com.edgedo.society.queryvo.SocietyFileSaveRecordQuery;
import com.edgedo.society.queryvo.SocietyFileSaveRecordView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyFileSaveRecordService {
	
	
	@Autowired
	private SocietyFileSaveRecordMapper societyFileSaveRecordMapper;

	public List<SocietyFileSaveRecordView> listPage(SocietyFileSaveRecordQuery societyFileSaveRecordQuery){
		List list = societyFileSaveRecordMapper.listPage(societyFileSaveRecordQuery);
		societyFileSaveRecordQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyFileSaveRecord societyFileSaveRecord) {
		societyFileSaveRecord.setId(Guid.guid());
		societyFileSaveRecordMapper.insert(societyFileSaveRecord);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyFileSaveRecord societyFileSaveRecord) {
		societyFileSaveRecordMapper.updateById(societyFileSaveRecord);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyFileSaveRecord societyFileSaveRecord) {
		societyFileSaveRecordMapper.updateAllColumnById(societyFileSaveRecord);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyFileSaveRecordMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyFileSaveRecordMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyFileSaveRecord loadById(String id) {
		return societyFileSaveRecordMapper.selectById(id);
	}
	

}
