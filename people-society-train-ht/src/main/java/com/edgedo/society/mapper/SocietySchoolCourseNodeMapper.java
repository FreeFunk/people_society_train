package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.YwTrainConsumRec;
import com.edgedo.society.entity.YwTrainLearnRec;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietySchoolCourseNodeMapper  extends BaseMapper<SocietySchoolCourseNode>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeView> listPage(SocietySchoolCourseNodeQuery query);

	public List<SocietySchoolCourseNodeView> listPageCountNode(SocietySchoolCourseNodeQuery query);

	public List<SocietySchoolCourseNodeView> listPageNew(SocietySchoolCourseNodeQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietySchoolCourseNodeView> listByObj(SocietySchoolCourseNodeQuery query);


	public List<String> selectByNodeId(SocietySchoolCourseNodeQuery query);

    int countNodes(String courseId);

	int sumTimeLength(String courseId);
	/*
	 * 根据课程ID统计下面的章节数量
	 * */
    int countByCourseId(String courseId);
	/*逻辑删除*/
	void updateByIds(List<String> ids);
	/**
	 *@Author:ZhaoSiDa
	 *@Description: 根据课程ID查询所有小节
	 *@DateTime: 2020/5/29 16:55
	 */
    List<SocietySchoolCourseNodeView> listByCourseId(@Param("courseId") String courseId);
	/**
	 *@Author:ZhaoSiDa
	 *@Description: 根据课程ID和随机数查询小节
	 *@DateTime: 2020/5/29 16:55
	 */
	List<SocietySchoolCourseNodeView> listByCourseIdSuiJiNum(@Param("courseId") String courseId,
															 @Param("randNum") int randNum);
	/*根据身份证号查询出学习记录*/
    List<YwTrainConsumRec> selectByIdCardNum(String studentIdCardNum);
	/*根据学习记录id查询学习过程中的人脸记录*/
	List<YwTrainLearnRec> selectByConRecId(String conRecId);

    Integer selectByClassIdVoNodeNum(String courseId);

    void updateByIdAndQuestionNum(@Param("ownerNodeId")String ownerNodeId,
								  @Param("questionNum")Integer questionNum);

    List<SocietySchoolCourseNodeView> listByMajorIdAndSchoolId(Map<String, String> map);

    SocietySchoolCourseNode selectByCourseIdAndSchoolId(@Param("schoolId")String schoolId, @Param("courseId")String courseId);

    List<SocietySchoolCourseNode> selectByCourseIdAndSchoolIdAll(@Param("schoolId")String schoolId,
																 @Param("ownerCourseId")String ownerCourseId);

    int selectByCourseNameAndId(@Param("fileUrl")String fileUrl);

    void updateByMajorId(@Param("majorId")String majorId,
						 @Param("majorName")String majorName);


	void updateByClsId(@Param("clsId")String clsId,
					   @Param("clsName")String clsName,
					   @Param("majorId")String majorId,
					   @Param("majorName")String majorName);

    void updateByCourseIdAndCourseName(Map<String, String> map);

    List<SocietySchoolCourseNodeView> selectByCourseId(@Param("courseId")String courseId);

	String selectCourseIdAndNodeName(@Param("nodeName")String nodeName, @Param("courseId")String courseId);

	int insertAllRecord(List<SocietySchoolCourseNodeView> list);

    List<String> selectAll();

	void updateByList(@Param("nodeList")List<SocietySchoolCourseNodeView> nodeList);

    void updateByListAndReo(@Param("nodeList")List<SocietySchoolCourseNodeView> nodeList);

    SocietySchoolCourseNode selectByCourseIdAndResourceId(@Param("courseId")String courseId,
														  @Param("resourceId")String resourceId);

	SocietySchoolCourseNode selectByCourseIdAndResourceIdOnIsOpen(@Param("courseId")String courseId,
														  @Param("resourceId")String resourceId);

    void updateByDataStateId(String courseId);

    void updateBySchoolIdAndResourceId(@Param("applySchoolId")String applySchoolId, @Param("ownReId")String ownReId);

	SocietySchoolCourseNodeView selectBySchoolIdAndResoId(@Param("applySchoolId")String applySchoolId,
														  @Param("ownReId")String ownReId);

	SocietySchoolCourseNodeView selectByApplySchoolIdAndResourceId(@Param("applySchoolId")String applySchoolId,
														  @Param("ownReId")String ownReId);

	List<String> selectByResourceId(@Param("ownerNodeResourcesId")String ownerNodeResourcesId,
									@Param("schoolId")String schoolId);

	List<String> selectByCourseIdList(String schoolId);

    List<String> selectByCourseIdAndSchoolIdList(@Param("courseId")String courseId, @Param("schoolId")String schoolId);

    Integer selectByCourseIdAndNodeId(@Param("courseId")String courseId,
									  @Param("ownerNodeResourcesId")String ownerNodeResourcesId);

}