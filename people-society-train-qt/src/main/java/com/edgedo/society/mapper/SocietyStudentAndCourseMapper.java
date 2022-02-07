package com.edgedo.society.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentAndCourse;
import com.edgedo.society.queryvo.SocietyStudentAndCourseQuery;
import com.edgedo.society.queryvo.SocietyStudentAndCourseView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentAndCourseMapper  extends BaseMapper<SocietyStudentAndCourse>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentAndCourseView> listPage(SocietyStudentAndCourseQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentAndCourseView> listByObj(SocietyStudentAndCourseQuery query);

	/**
	 * @Author WangZhen
	 * @Description 根据主键和学生查询
	 * @Date 2020/5/4 11:06
	 **/
    SocietyStudentAndCourseView loadByIdAndStuId(@Param("id") String id,@Param("studentId") String studentId);

    /**
     * @Author ZhangCC
     * @Description 查询学员关联的课程
     * @Date 2020/5/10 10:54
     **/
    List<String> selectCourseIdListByStu(Map<String,Object> param);

    /**
     * @Author ZhangCC
     * @Description 根据学员查询课程列表
     * @Date 2020/5/10 10:54
     **/
    List<SocietyStudentAndCourseView> stuCourseByIdCardListPage(SocietyStudentAndCourseQuery query);
	/**
	 * @Author WangZhen
	 * @Description 更新上次学习节点
	 * @Date 2020/5/10 10:48
	 **/
    int updateLastLearnNode(SocietyStudentAndCourseView stuCourse);

    /**
     * @Author ZhangCC
     * @Description 查询学员未完成的关联课程
     * @Date 2020/5/12 19:54
     **/
    SocietyStudentAndCourseView selectOneStuCourseByCourseId(Map<String,Object> param);

    /**
     * @Author ZhangCC
     * @Description 查询学员一条完成的学习
     * @Date 2020/5/19 9:30
     **/
    SocietyStudentAndCourseView selectOneFinishedStuCourse(Map<String,Object> param);

    /**
     * @Author ZhangCC
     * @Description 查询学员是否关联课程
     * @Date 2020/5/26 13:57
     **/
    int countByMap(Map<String,Object> param);

    /**
     * @Author WangZhen
     * @Description 根据主键和身份证号获得学习
     * @Date 2020/7/14 7:46
     **/
    SocietyStudentAndCourseView loadByIdAndStuIdCardNum(
    		@Param("id") String id,@Param("idCardNum") String idCardNum);
	/**
	 * @Author WangZhen
	 * @Description 根据身份证号和课程id查询学生课程
	 * @Date 2020/7/14 7:46
	 **/
    SocietyStudentAndCourse selectOneByCourseIdAndIdCarNum(
			@Param("idCardNum") String idCardNum, @Param("courseId") String courseId);
	/**
	 * @Author WangZhen
	 * @Description 根据身份证号统计课程数量
	 * @Date 2020/7/14 9:18
	 **/
    int countCourseByStuIdCardNum(String stuIdCardNum);
	/**
	 * @Author WangZhen
	 * @Description 根据身份证号和课程id查询已经完成的课程
	 * @Date 2020/7/14 11:00
	 **/
    SocietyStudentAndCourse selectOneFinishedByIdCardAndCourse(
    		@Param("courseId") String courseId,@Param("idCardNum") String idCardNum);

}