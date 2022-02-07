package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyFileSaveRecord;
import com.edgedo.society.queryvo.SocietyFileSaveRecordQuery;
import com.edgedo.society.queryvo.SocietyFileSaveRecordView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyFileSaveRecordMapper  extends BaseMapper<SocietyFileSaveRecord>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyFileSaveRecordView> listPage(SocietyFileSaveRecordQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyFileSaveRecordView> listByObj(SocietyFileSaveRecordQuery query);
	
	

}