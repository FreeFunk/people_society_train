package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SysWxPayRecord;
import com.edgedo.society.queryvo.SysWxPayRecordQuery;
import com.edgedo.society.queryvo.SysWxPayRecordView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SysWxPayRecordMapper  extends BaseMapper<SysWxPayRecord>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxPayRecordView> listPage(SysWxPayRecordQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysWxPayRecordView> listByObj(SysWxPayRecordQuery query);


	/**
	 * 修改预支付记录为已处理，需判断预支付记录状态
	 * @param record
	 * @return
	 */
	int updateSuccess(SysWxPayRecord record);
}