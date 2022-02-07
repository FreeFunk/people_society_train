package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentTestPaper;
import com.edgedo.society.queryvo.SocietyStudentTestPaperQuery;
import com.edgedo.society.queryvo.SocietyStudentTestPaperView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SocietyStudentTestPaperMapper  extends BaseMapper<SocietyStudentTestPaper>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentTestPaperView> listPage(SocietyStudentTestPaperQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentTestPaperView> listByObj(SocietyStudentTestPaperQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 查询学员该课程考试的次数
	 * @Date 2020/5/11 14:41
	 **/
	int countByStuAndCourse(Map<String,Object> param);

	/**
	 * @Author ZhangCC
	 * @Description 根据学员和课程还有答题完成情况查询
	 * @Date 2020/5/11 11:08
	 **/
	List<SocietyStudentTestPaperView> selectPaperByStuCourListPage(SocietyStudentTestPaperQuery query);

	/**
	 * @Author ZhangCC
	 * @Description 查询一条没有完成的试卷
	 * @Date 2020/5/11 14:48
	 **/
	SocietyStudentTestPaperView selectNotFinishedPaper(Map<String,Object> param);

	/**
	 * @Author ZhangCC
	 * @Description 查询学员答题的最高分
	 * @Date 2020/5/11 16:50
	 **/
	Integer selectTopScoreByCourse(Map<String,Object> param);

	/**
	 * @Author WangZhen
	 * @Description 根据身份证号和课程id查询
	 * @Date 2020/7/14 11:27
	 **/
    List selectPaperByStuIdCardAndCourseListPage(SocietyStudentTestPaperQuery query);

}