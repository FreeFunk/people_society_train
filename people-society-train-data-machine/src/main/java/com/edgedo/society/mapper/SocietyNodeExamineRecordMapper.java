package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyNodeExamineRecord;
import com.edgedo.society.queryvo.SocietyNodeExamineRecordQuery;
import com.edgedo.society.queryvo.SocietyNodeExamineRecordView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyNodeExamineRecordMapper  extends BaseMapper<SocietyNodeExamineRecord>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyNodeExamineRecordView> listPage(SocietyNodeExamineRecordQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyNodeExamineRecordView> listByObj(SocietyNodeExamineRecordQuery query);


    String selectByNodeId(String stuNodeId);

}