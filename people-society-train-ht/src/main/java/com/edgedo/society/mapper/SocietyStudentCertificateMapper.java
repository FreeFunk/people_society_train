package com.edgedo.society.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.society.entity.SocietyStudentCertificate;
import com.edgedo.society.queryvo.SocietyStudentCertificateQuery;
import com.edgedo.society.queryvo.SocietyStudentCertificateView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SocietyStudentCertificateMapper  extends BaseMapper<SocietyStudentCertificate>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCertificateView> listPage(SocietyStudentCertificateQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SocietyStudentCertificateView> listByObj(SocietyStudentCertificateQuery query);

	/*根据学员id和课程id查询证书*/
    SocietyStudentCertificate loadByStudentIdAndCourseId(@Param("studentId") String studentId,
														 @Param("courseId") String courseId);

    void deleteByStuId(String id);

    void updateByStuId(Map<String, String> map);

    void updateByCourseIdAndCourseName(Map<String, String> map);

	void updateByMajorId(@Param("majorId")String majorId, @Param("majorName")String majorName);

    void updateByStudentIdAndCourseId(@Param("studentId")String studentId, @Param("ownerCourseId")String ownerCourseId);

    SocietyStudentCertificateView selectByStuId(@Param("studentId")String studentId);

	SocietyStudentCertificateView selectByStuIdAndCourId(@Param("studentId")String studentId, @Param("courseId")String courseId);
}