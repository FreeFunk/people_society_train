package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyNodeResources;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.queryvo.SocietyNodeResourcesQuery;
import com.edgedo.society.queryvo.SocietyNodeResourcesView;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyNodeResourcesMapper  extends BaseMapper<SocietyNodeResources>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyNodeResourcesView> listPage(SocietyNodeResourcesQuery query);

	public List<SocietySchoolCourseNodeView> listPageCountNode(SocietyNodeResourcesQuery query);

	public List<SocietyNodeResourcesView> listPageMyNode(SocietyNodeResourcesQuery query);

	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyNodeResourcesView> listByObj(SocietyNodeResourcesQuery query);


    List<SocietyNodeResources> selectByCourseId(String courseId);

	void insertAllRecord(@Param("list") List<SocietyNodeResources> resourcesList);

    int selectBySchoolIdAndResourId(@Param("nowSchoolId")String nowSchoolId, @Param("resourcesViewId")String resourcesViewId);

	void updateByDataStateId(@Param("courseId")String courseId, @Param("ownerSchoolId")String ownerSchoolId);

    void updateByIsOpen(@Param("resourceId")String resourceId, @Param("isOpen")String isOpen);

    void updateByDateState(String ids);

	List<SocietyNodeResourcesView> selectByResourcerId(String resourceId);

	void updateByNodeNameAndNum(@Param("nodeResourceList")List<SocietyNodeResourcesView> nodeResourceList);

    List<String> selectBySchoolId(String schoolId);
}