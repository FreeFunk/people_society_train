package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.queryvo.SocietySchoolQuery;
import com.edgedo.society.queryvo.SocietySchoolView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolMapper  extends BaseMapper<SocietySchool>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolView> listPage(SocietySchoolQuery query);

	public List<SocietySchoolView> schoolAdminlistpage(SocietySchoolQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolView> listByObj(SocietySchoolQuery query);
	List<SocietySchoolView> listByObjGloble(String schoolId);

	void logicDelete(List<String> list);

    List<String> selectOldCreateSchoolName(@Param("schoolNewName") String schoolNewName);

    void updateByIdAndDataState(@Param("societySchool") SocietySchool societySchool);

    Map<String, String> selectBySchoolAdminId(@Param("userId") String userId);

	//学校统计
    int countSchoolAllNum();

	//县区 学校统计
	int countXianQuSchoolAllNum(String xianquId);

    List<SocietySchool> selectByAll();


    List<SocietySchool> selectByNotOwnSchoolAll(@Param("schoolId")String schoolId);

    List<String> selectByXianQuId(String xianquId);


}