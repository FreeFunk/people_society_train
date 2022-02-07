package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentUnique;
import com.edgedo.society.queryvo.SocietyStudentUniqueQuery;
import com.edgedo.society.queryvo.SocietyStudentUniqueView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyStudentUniqueMapper  extends BaseMapper<SocietyStudentUnique>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentUniqueView> listPage(SocietyStudentUniqueQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentUniqueView> listByObj(SocietyStudentUniqueQuery query);


    void deleteByStuId(String idCard);

    void updateByStuId(Map<String, String> map);

	SocietyStudentUnique selectByPhone(String newPhone);

	void updateByAllId(SocietyStudentUnique societyStudentUniqueOld);
}