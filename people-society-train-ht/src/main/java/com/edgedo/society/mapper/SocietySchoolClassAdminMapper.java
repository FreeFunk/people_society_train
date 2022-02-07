package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.FormSelectsObject;
import com.edgedo.society.entity.SocietySchoolClassAdmin;
import com.edgedo.society.queryvo.SocietySchoolClassAdminQuery;
import com.edgedo.society.queryvo.SocietySchoolClassAdminView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolClassAdminMapper  extends BaseMapper<SocietySchoolClassAdmin>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassAdminView> listPage(SocietySchoolClassAdminQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassAdminView> listByObj(SocietySchoolClassAdminQuery query);

//查出当前学校的所有的班主任
    List<SocietySchoolClassAdmin> listByClassAdmin(String schoolId);

    String selectBySysUserId(String sysUserId);

    int updateByState(List<String> ids);

    void updateByClassGroupId(List<String> ids);

    List<FormSelectsObject> getClassAdminName(@Param("schoolId") String schoolId,@Param("groupId")String groupId);

	void updateByGroupId(@Param("list") List<String> classAdminIdList,@Param("groupId") String groupId);

	List<String> selectByGroupId(String groupId);

}