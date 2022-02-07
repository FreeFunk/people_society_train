package com.edgedo.society.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.YwTrainConsumRecOld;
import com.edgedo.society.mapper.YwTrainConsumRecOldMapper;
import com.edgedo.society.queryvo.YwTrainConsumRecOldQuery;
import com.edgedo.society.queryvo.YwTrainConsumRecOldView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class YwTrainConsumRecOldService {
	
	
	@Autowired
	private YwTrainConsumRecOldMapper ywTrainConsumRecOldMapper;

	public List<YwTrainConsumRecOldView> listPage(YwTrainConsumRecOldQuery ywTrainConsumRecOldQuery){
		List list = ywTrainConsumRecOldMapper.listPage(ywTrainConsumRecOldQuery);
		ywTrainConsumRecOldQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(YwTrainConsumRecOld ywTrainConsumRecOld) {
		ywTrainConsumRecOld.setId(Guid.guid());
		ywTrainConsumRecOldMapper.insert(ywTrainConsumRecOld);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(YwTrainConsumRecOld ywTrainConsumRecOld) {
		ywTrainConsumRecOldMapper.updateById(ywTrainConsumRecOld);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(YwTrainConsumRecOld ywTrainConsumRecOld) {
		ywTrainConsumRecOldMapper.updateAllColumnById(ywTrainConsumRecOld);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return ywTrainConsumRecOldMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return ywTrainConsumRecOldMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public YwTrainConsumRecOld loadById(String id) {
		return ywTrainConsumRecOldMapper.selectById(id);
	}

	/**
	 * @Author ZhangCC
	 * @Description 批量导入记录
	 * @Date 2020/6/4 9:01
	 **/
	public void excelImportRec(List<YwTrainConsumRecOld> oweCardRecList) {
		for (YwTrainConsumRecOld recOld:oweCardRecList){
			ywTrainConsumRecOldMapper.insert(recOld);
		}
	}
	

}
