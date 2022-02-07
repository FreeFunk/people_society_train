package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentCallNameConfirm;
import com.edgedo.society.queryvo.SocietyStudentCallNameConfirmQuery;
import com.edgedo.society.queryvo.SocietyStudentCallNameConfirmView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyStudentCallNameConfirmMapper  extends BaseMapper<SocietyStudentCallNameConfirm>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCallNameConfirmView> listPage(SocietyStudentCallNameConfirmQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCallNameConfirmView> listByObj(SocietyStudentCallNameConfirmQuery query);

	/*根据学员id查询点名记录*/
    SocietyStudentCallNameConfirm loadByStuId(String studentId);
	/*根据id更新确认状态*/
    void updateCallStateById(String id);
	/*统计已经确认的人数*/
	int countByClassId(String classCallNameId);
}