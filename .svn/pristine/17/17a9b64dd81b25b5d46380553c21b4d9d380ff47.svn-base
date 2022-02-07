package com.edgedo.society.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentPractiseQuestOption;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionQuery;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentPractiseQuestOptionMapper  extends BaseMapper<SocietyStudentPractiseQuestOption>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentPractiseQuestOptionView> listPage(SocietyStudentPractiseQuestOptionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentPractiseQuestOptionView> listByObj(SocietyStudentPractiseQuestOptionQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据学生id，课程章节id，学生章节记录查询正确答案
	 * @Date 2020/5/9 9:45
	 **/
    List<SocietyStudentPractiseQuestOptionView> listByStuAndCouseNodeOfSelect(
			@Param("studentId") String studentId, //学生id
			@Param("ownerNodeId") String ownerNodeId, //所属课程章节
			@Param("ownerStudentAndNodeId") String ownerStudentAndNodeId//学生学习章节记录
	);

	List<SocietyStudentPractiseQuestOptionView> listByStuAndCouseNodeOfSelectNew(
			@Param("studentId") String studentId, //学生id
			@Param("ownerNodeId") String ownerNodeId, //所属课程章节
			@Param("ownerStudentAndNodeId") String ownerStudentAndNodeId,//学生学习章节记录
			@Param("ascii")String ascii
	);

    /**
     * @Author WangZhen
     * @Description 根据选项id和学生学习章节记录找到已经插入的选项
     * @Date 2020/5/9 15:36
     **/
    SocietyStudentPractiseQuestOption selectByCouseOpIdAndStuNodeId(
    		@Param("ownerNodeQueOpId") String ownerNodeQueOpId,
			@Param("ownerStudentAndNodeId") String ownerStudentAndNodeId);

	SocietyStudentPractiseQuestOption selectByCouseOpIdAndStuNodeIdNew(
			@Param("ownerNodeQueOpId") String ownerNodeQueOpId,
			@Param("ownerStudentAndNodeId") String ownerStudentAndNodeId,@Param("ascii")String ascii);
    /**
     * @Author WangZhen
     * @Description  根据学生练习题关联id设置选项未选中
     * @Date 2020/5/9 15:54
     **/
	int updateUnSelAllOption(String ownerStudentQuersionId);
	/**
	 * @Author WangZhen
	 * @Description 将学生学习节点的所有的习题选项设置为未选择
	 * @Date 2020/7/9 5:29
	 **/
    int updateAllStuNodeOptionUnSelect(String stuNodeId);

	int updateAllStuNodeOptionUnSelectNew(@Param("stuNodeId")String stuNodeId,
										  @Param("ascii")String ascii);

	void updateByIdNew(@Param("option")SocietyStudentPractiseQuestOption option,@Param("ascii") String ascii);

	void insertNew(@Param("option")SocietyStudentPractiseQuestOption option, @Param("ascii")String ascii);

	/**
	 * @Author WangZhen
	 * @Description 删除学生节点的习题选项
	 * @Date 2020/9/8 15:23
	 **/
	int deleteStuQuesOptionByStuNodeId(@Param("stuNodeId")String stuNodeId,@Param("ascii")String ascii);

	void insertNewList(@Param("optionList")List<SocietyStudentPractiseQuestOption> optionList,
					   @Param("ascii")String ascii);
}