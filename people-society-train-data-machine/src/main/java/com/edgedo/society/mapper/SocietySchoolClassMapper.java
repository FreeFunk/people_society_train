package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolClass;
import com.edgedo.society.queryvo.SocietySchoolClassQuery;
import com.edgedo.society.queryvo.SocietySchoolClassView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolClassMapper  extends BaseMapper<SocietySchoolClass>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassView> listPage(SocietySchoolClassQuery query);

	public List<SocietySchoolClassView> examineClasslistPage(SocietySchoolClassQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolClassView> listByObj(SocietySchoolClassQuery query);

	void logicDelete(List<String> list);
	
	List<SocietySchoolClassView> listAllByMajorId(String ownerMajorId);

	int countByMajorAndNotEnd(String ownerMajorId);

	Integer count(SocietySchoolClassQuery query);
	//班级统计
    int countClassAllNum();

	int countXianQuClassAllNum(String xianquId);
    //查询学校对应的班级
    List<SocietySchoolClassView> listBySchoolId(String schoolId);
//根据班级编码统计
	int countByClassCode(String classCode);

    List<String> selectByDateStr(String dateStr);

    SocietySchoolClass selectByClassCode(String classCode);

    void updateExamineState(@Param("id") String id ,@Param("examineState") String examineState);

    void updateAdminExamineState(@Param("classId") String classId,@Param("examineState") String examineState);

    List<String> selectByClassAdminId(String classAdminId);

	List<SocietySchoolClass> selectByClassAdminIdVoObj(String id);

    List<SocietySchoolClass> selectByMajorId(String ownerMajorId);

    void updateByMajorId(String majorId, String majorName);

    void updateByCourseId(Map<String, String> map);

	List<String> selectByClassAdminIdList(@Param("classAdminId") List<String> classAdminId);

    String selectByClassAdminIdOnceClass(String classAdminId);

    List<SocietySchoolClass> selectByClassList();

}