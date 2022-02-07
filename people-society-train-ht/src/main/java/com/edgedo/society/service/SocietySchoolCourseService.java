package com.edgedo.society.service;

import java.math.BigDecimal;
import java.util.*;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.SocietySchoolCourseMapper;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.mapper.SocietyStudentAndCourseMapper;
import com.edgedo.society.queryvo.*;
import com.edgedo.sys.entity.CheckArr;
import com.edgedo.sys.entity.Dtree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SocietySchoolCourseService {


    @Autowired
    private SocietySchoolCourseMapper societySchoolCourseMapper;
    @Autowired
    private SocietyStudentAndCourseMapper studentAndCourseMapper;
    @Autowired
    private SocietySchoolCourseNodeMapper societySchoolCourseNodeMapper;

    @Autowired
    private SocietySchoolCourseNodeService societySchoolCourseNodeService;
    @Autowired
    private SocietyStudentAndNodeService societyStudentAndNodeService;
    @Autowired
    private SocietyStudentPractiseQuestionService societyStudentPractiseQuestionService;
    @Autowired
    private SocietyStudentCertificateService societyStudentCertificateService;
    @Autowired
    private SocietyStudentStudyProcessService societyStudentStudyProcessService;
    @Autowired
    private SocietyStudentStudyProcessFaceService societyStudentStudyProcessFaceService;
    @Autowired
    private SocietyStudentTestPaperService societyStudentTestPaperService;
    @Autowired
    private SocietyStudentTestPaperQuestionService societyStudentTestPaperQuestionService;
    @Autowired
    private SocietyStudentTestPaperQuestionOptionService societyStudentTestPaperQuestionOptionService;
    @Autowired
    private SocietySchoolCourseNodeQuestionService societySchoolCourseNodeQuestionService;
    @Autowired
    private SocietySchoolService societySchoolService;
    @Autowired
    private SocietySchoolMajorService societySchoolMajorService;
    @Autowired
    private SocietySchoolCourseClsService societySchoolCourseClsService;
    @Autowired
    private SocietyStudentAndCourseService societyStudentAndCourseService;
    @Autowired
    private SocietySchoolCourseUseGlobleService societySchoolCourseUseGlobleService;
    @Autowired
    private SocietyNodeResourcesService societyNodeResourcesService;

    public List<SocietySchoolCourseView> listPage(SocietySchoolCourseQuery societySchoolCourseQuery) {
        List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPage(societySchoolCourseQuery);
        list.forEach(course -> {
            String courseId = course.getId();
            String schoolId = course.getOwnerSchoolId();
            //统计完成课程的人数
            int finishedCourseStuNum = studentAndCourseMapper.countFinishedCourseStuNum(schoolId, courseId);
            course.setFinishedStudentNum(finishedCourseStuNum);
        });
        societySchoolCourseQuery.setList(list);
        return list;
    }

    /**
     * 获取所有公开课
     *
     * @param societySchoolCourseQuery
     * @return
     */
    public List<SocietySchoolCourseView> getAllOpenCourse(SocietySchoolCourseQuery societySchoolCourseQuery) {
        List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPage(societySchoolCourseQuery);
        societySchoolCourseQuery.setList(list);
        return list;
    }

    /**
     * 加入课程 通过公共课
     *
     * @param * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int joinToMyCourse(User user,String ids) {
        SocietySchoolCourse societySchoolCourse = this.loadById(ids);
        SocietySchool societySchool = societySchoolService.loadById(user.getCompId());
        SocietySchoolMajor societySchoolMajor =
                societySchoolMajorService.loadById(societySchoolCourse.getOwnerMajorId());
        SocietySchoolCourseCls societySchoolCourseCls =
                societySchoolCourseClsService.loadById(societySchoolCourse.getCourseClsId());
        String userName = user.getUserName();
        String userId = user.getUserId();
        //专业名称
        societySchoolMajor.setId(Guid.guid());
        societySchoolMajor.setCreateTime(new Date());
        societySchoolMajor.setCreateUserName(userName);
        societySchoolMajor.setCreateUserId(userId);
        societySchoolMajor.setOwnerSchoolName(societySchool.getSchoolName());
        societySchoolMajor.setOwnerSchoolId(societySchool.getId());
        societySchoolMajorService.insertMultistageAdd(societySchoolMajor);
        //课程分类名称
        societySchoolCourseCls.setId(Guid.guid());
        societySchoolCourseCls.setCreateTime(new Date());
        societySchoolCourseCls.setCreateUserName(userName);
        societySchoolCourseCls.setCreateUserId(userId);
        societySchoolCourseCls.setOwnerSchoolName(societySchool.getSchoolName());
        societySchoolCourseCls.setOwnerSchoolId(societySchool.getId());
        societySchoolCourseCls.setOwnerMajorId(societySchoolMajor.getId());
        societySchoolCourseCls.setOwnerMajorName(societySchoolMajor.getMajorName());
        societySchoolCourseClsService.insertMultistageAdd(societySchoolCourseCls);
        //申请方设置  想要添加公共课的申请方
        societySchoolCourse.setOwnerMajorId(societySchoolMajor.getId());
        societySchoolCourse.setOwnerMajorName(societySchoolMajor.getMajorName());
        societySchoolCourse.setCourseClsId(societySchoolCourseCls.getId());
        societySchoolCourse.setCourseClsName(societySchoolCourseCls.getCourseClsName());
        societySchoolCourse.setOwnerCourseId(ids);
        societySchoolCourse.setOwnerCourseName(societySchoolCourse.getCourseName());
        societySchoolCourse.setOwnerSchoolCourseId(societySchoolCourse.getOwnerSchoolId());
        societySchoolCourse.setCreateUserId(userId);
        societySchoolCourse.setCreateUserName(userName);
        societySchoolCourse.setCreateTime(new Date());
        societySchoolCourse.setTeacherId(null);
        societySchoolCourse.setTeacherName(null);
        String copyCourseId = Guid.guid();
        //取消公开状态

        societySchoolCourse.setOwnerSchoolId(societySchool.getId());
        societySchoolCourse.setOwnerSchoolName(societySchool.getSchoolName());
        societySchoolCourse.setIsOpen("0");
        societySchoolCourse.setId(copyCourseId);
        societySchoolCourseMapper.insert(societySchoolCourse);

        //查出所有的课程章节  章节表
        List<SocietySchoolCourseNodeView> societySchoolCourseNodeList = societySchoolCourseNodeService.selectByCourseId(ids);

        //查出章节池
        //如果保持 章节池不变唯一 查询
        //俩list ?
        societySchoolCourseNodeList.forEach(s->{
            //修改id
            s.setId(Guid.guid());
            s.setOwnerCourseId(copyCourseId);
            //修改专业id 分类id
            s.setOwnerMajorId(societySchoolMajor.getId());
            s.setOwnerMajorName(societySchoolMajor.getMajorName());
            s.setOwnerCourseClsId(societySchoolCourseCls.getId());
            s.setOwnerCourseClsName(societySchoolCourseCls.getCourseClsName());
            //修改学校id
            s.setOwnerSchoolId(societySchool.getId());
            s.setOwnerSchoolName(societySchool.getSchoolName());
            //创建人
            s.setCreateTime(new Date());
            s.setCreateUserId(userId);
            s.setCreateUserName(userName);
            //修改公开状态 0
            s.setIsOpen("0");
        });
        societySchoolCourseNodeMapper.insertAllRecord(societySchoolCourseNodeList);
        //查询所有的章节池的课程
        /*List<SocietyNodeResources> resourcesList = societyNodeResourcesService.selectByCourseId(ids);
        resourcesList.forEach(societyNodeResources->{

//            societyNodeResources.setOwnerCourseId(copyCourseId);
            //修改学校id
//            societyNodeResources.setOwnerSchoolId(societySchool.getId());
//            societyNodeResources.setOwnerSchoolName(societySchool.getSchoolName());
            //创建人
            societyNodeResources.setCreateTime(new Date());
            societyNodeResources.setCreateUserId(userId);
            societyNodeResources.setCreateUserName(userName);
            //修改公开状态 0
            societyNodeResources.setIsOpen("0");
            //关联申请学校
            societyNodeResources.setApplySchoolId(societySchool.getId());
            societyNodeResources.setOwnerNodeResourcesId(societyNodeResources.getId());
            societyNodeResources.setIsRelationPublicCourse("1");
            //修改id
            societyNodeResources.setId(Guid.guid());
        });
        societyNodeResourcesService.insertAllRecord(resourcesList);*/
        return 1;
    }

    public List<SocietySchoolCourseView> listPageCourseGloble(SocietySchoolCourseQuery societySchoolCourseQuery) {
        List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPageCourseGloble(societySchoolCourseQuery);
        societySchoolCourseQuery.setList(list);
        return list;
    }

    public List<SocietySchoolCourseView> listPageCourseGlobleNew(SocietySchoolCourseQuery societySchoolCourseQuery) {
        List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPageCourseGlobleNew(societySchoolCourseQuery);
        societySchoolCourseQuery.setList(list);
        return list;
    }

    /**
     * 根据学校id 查出的所有的课程使用情况
     * @param societySchoolCourseQuery
     * @return
     */
    public List<SocietySchoolCourseView> listPageCourseCountAll(SocietySchoolCourseQuery societySchoolCourseQuery) {
        //根据课程id 去公共课 查出记录
        List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPage(societySchoolCourseQuery);
        //课程固定了 多对1的关系
        for(SocietySchoolCourseView societySchoolCourseView : list){
            //统计课程一个学校完成了多少人
            Integer compNum =
                    societyStudentAndCourseService.selectByCourseIdAndSchoolId(societySchoolCourseView.getOwnerSchoolId(),societySchoolCourseView.getId());
            societySchoolCourseView.setSchoolCompleteCount(compNum);
            //统计课程一个学校学习了多少人
            Integer useNum =
                    societyStudentAndCourseService.selectByUseCourseIdAndSchoolId(societySchoolCourseView.getOwnerSchoolId(),societySchoolCourseView.getId());
            societySchoolCourseView.setSchoolUseCourseCount(useNum);
        }
        //3.拿到相关的课程id 和学校id

        //4.排除 自己学校的课程使用情况

        return list;
    }


    public List<SocietySchoolCourseView> listPageCountCourse(SocietySchoolCourseQuery societySchoolCourseQuery) {
        List<SocietySchoolCourseView> list = societySchoolCourseMapper.listPage(societySchoolCourseQuery);
        for (SocietySchoolCourseView societySchoolCourseView : list) {
            //统计课程学习人数
            Integer num = studentAndCourseMapper.selectByCourseStudyNum(societySchoolCourseView.getId());
            societySchoolCourseView.setCourseStudyNum(num);
        }
        societySchoolCourseQuery.setList(list);
        return list;
    }

    public List<SocietySchoolCourseView> listByObj(SocietySchoolCourseQuery societySchoolCourseQuery) {
        List list = societySchoolCourseMapper.listByObj(societySchoolCourseQuery);
        societySchoolCourseQuery.setList(list);
        return list;
    }

    /***
     * 统计数量
     * @return
     */
    public Integer count(SocietySchoolCourseQuery query) {
        int num = societySchoolCourseMapper.count(query);
        return num;
    }

    /**
     * 班级课程列表
     *
     * @param societySchoolCourseQuery
     * @return
     */
    public List<SocietySchoolCourseView> classCourseListPage(SocietySchoolCourseQuery societySchoolCourseQuery) {
        List list = societySchoolCourseMapper.classCourseListPage(societySchoolCourseQuery);
        societySchoolCourseQuery.setList(list);
        return list;
    }

    /***
     * 新增方法
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insert(SocietySchoolCourse societySchoolCourse) {
        if (societySchoolCourse.getTeacherName() != null
                && !societySchoolCourse.getTeacherName().equals("")) {
            String[] str = societySchoolCourse.getTeacherName().split("@@");
            societySchoolCourse.setTeacherId(str[0]);
            societySchoolCourse.setTeacherName(str[1]);
        }
        societySchoolCourseMapper.insert(societySchoolCourse);
        return "";
    }


    /**
     * 多级添加  专业 课程分类 课程
     *
     * @param societySchoolCourse
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertMultistageAdd(SocietySchoolCourse societySchoolCourse) {
        SocietySchool societySchool = societySchoolService.loadById(societySchoolCourse.getOwnerSchoolId());
        //专业名
        SocietySchoolMajor societySchoolMajor = new SocietySchoolMajor();
        societySchoolMajor.setId(Guid.guid());
        societySchoolMajor.setCreateUserId(societySchoolCourse.getCreateUserId());
        societySchoolMajor.setCreateTime(societySchoolCourse.getCreateTime());
        societySchoolMajor.setCreateUserName(societySchoolCourse.getCreateUserName());
        societySchoolMajor.setOwnerSchoolId(societySchool.getId());
        societySchoolMajor.setOwnerSchoolName(societySchool.getSchoolName());
        societySchoolMajor.setMajorName(societySchoolCourse.getOwnerMajorName());
        societySchoolMajor.setDataState("1");
        societySchoolMajor.setOrderNum(new BigDecimal(societySchoolMajorService.countAll(societySchool.getId()) + 1));
        societySchoolMajorService.insertMultistageAdd(societySchoolMajor);
        //课程分类
        SocietySchoolCourseCls societySchoolCourseCls = new SocietySchoolCourseCls();
        societySchoolCourseCls.setId(Guid.guid());
        societySchoolCourseCls.setCreateTime(societySchoolCourse.getCreateTime());
        societySchoolCourseCls.setCreateUserId(societySchoolCourse.getCreateUserId());
        societySchoolCourseCls.setCreateUserName(societySchoolCourse.getCreateUserName());
        societySchoolCourseCls.setOwnerMajorId(societySchoolMajor.getId());
        societySchoolCourseCls.setOwnerMajorName(societySchoolMajor.getMajorName());
        societySchoolCourseCls.setOwnerSchoolId(societySchool.getId());
        societySchoolCourseCls.setOwnerSchoolName(societySchool.getSchoolName());
        societySchoolCourseCls.setCourseClsName(societySchoolCourse.getCourseClsName());
        societySchoolCourseCls.setDataState("1");
        societySchoolCourseCls.setOrderNum(new BigDecimal(societySchoolCourseClsService.count(societySchool.getId()) + 1));
        societySchoolCourseClsService.insertMultistageAdd(societySchoolCourseCls);
        //插入课程
        societySchoolCourse.setOwnerSchoolName(societySchool.getSchoolName());
        societySchoolCourse.setOwnerMajorId(societySchoolMajor.getId());
        societySchoolCourse.setCourseClsId(societySchoolCourseCls.getId());
        if (societySchoolCourse.getTeacherName() != null
                && !societySchoolCourse.getTeacherName().equals("")) {
            String[] str = societySchoolCourse.getTeacherName().split("@@");
            societySchoolCourse.setTeacherId(str[0]);
            societySchoolCourse.setTeacherName(str[1]);
        }
        societySchoolCourse.setId(Guid.guid());
        societySchoolCourseMapper.insert(societySchoolCourse);
        return "";
    }

    /***
     * 动态修改方法
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String update(SocietySchoolCourse societySchoolCourse) {
        societySchoolCourseMapper.updateById(societySchoolCourse);
        return "";
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateTeacher(SocietySchoolCourseView societySchoolCourse) {
        if (societySchoolCourse.getTeacherName().indexOf("@@") != -1) {
            String[] str = societySchoolCourse.getTeacherName().split("@@");
            societySchoolCourse.setTeacherId(str[0]);
            societySchoolCourse.setTeacherName(str[1]);
        }

        if (societySchoolCourse.getIsUpdateStudentCourseState() != null &&
                societySchoolCourse.getIsUpdateStudentCourseState().equals("是")) {
            Map<String, Object> map = new HashMap<>();
            map.put("courseId", societySchoolCourse.getId());
            map.put("schoolId", societySchoolCourse.getOwnerSchoolId());
            map.put("ordStuCourseStudyNum", societySchoolCourse.getOrdStuCourseStudyNum());
            map.put("impStuCourseStudyNum", societySchoolCourse.getImpStuCourseStudyNum());
            map.put("compStuCourseStudyNum", societySchoolCourse.getCompStuCourseStudyNum());
            map.put("faceNum", societySchoolCourse.getCourseIsNeedFaceContrast());
            societyStudentAndCourseService.updateByCourseId(map);
        }
        societySchoolCourseMapper.updateById(societySchoolCourse);
        String courseId = societySchoolCourse.getId();
        String courseName = societySchoolCourse.getCourseName();
        Map<String, String> map = new HashMap<>();
        map.put("courseId", courseId);
        map.put("courseName", courseName);
        //society_school_course_node
        societySchoolCourseNodeService.updateByCourseIdAndCourseName(map);
        //society_school_course_node_question
        societySchoolCourseNodeQuestionService.updateByCourseIdAndCourseName(map);
        //society_student_and_course
        studentAndCourseMapper.updateByCourseIdAndCourseName(map);
        //society_student_and_node
        societyStudentAndNodeService.updateByCourseIdAndCourseName(map);
        //society_student_practise_question
        societyStudentPractiseQuestionService.updateByCourseIdAndCourseName(map);
        //society_student_certificate
        societyStudentCertificateService.updateByCourseIdAndCourseName(map);
        //society_student_study_process
        societyStudentStudyProcessService.updateByCourseIdAndCourseName(map);
        //society_student_study_process_face
        societyStudentStudyProcessFaceService.updateByCourseIdAndCourseName(map);
        //society_student_test_paper
        societyStudentTestPaperService.updateByCourseIdAndCourseName(map);
        //society_student_test_paper_question
        societyStudentTestPaperQuestionService.updateByCourseIdAndCourseName(map);
        //society_student_test_paper_question_option
        societyStudentTestPaperQuestionOptionService.updateByCourseIdAndCourseName(map);
        return "";
    }


    /***
     * 全修改
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateAll(SocietySchoolCourse societySchoolCourse) {
        societySchoolCourseMapper.updateAllColumnById(societySchoolCourse);
        return "";
    }


    /**
     * 单个删除
     *
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(String id) {

        return societySchoolCourseMapper.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByIds(List<String> ids) {

        return societySchoolCourseMapper.deleteBatchIds(ids);
    }

    /**
     * 批量修改
     *
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateByIds(List<String> ids) {
        societySchoolCourseMapper.updateByIds(ids);
    }


    /**
     * 加载单个
     *
     * @param id
     */
    public SocietySchoolCourse loadById(String id) {
        return societySchoolCourseMapper.selectById(id);
    }


    public List<SocietySchoolCourseView> classNotInCourseListPage(SocietySchoolCourseQuery query) {
        List list = societySchoolCourseMapper.classNotInCourseListPage(query);
        query.setList(list);
        return list;
    }

    public List<SocietySchoolCourseView> courseTableListpage(SocietySchoolCourseQuery query) {
        List list = societySchoolCourseMapper.courseTablepage(query);
        query.setList(list);
        return list;
    }

    public List<SocietySchoolCourseView> classYesInCourseListPage(SocietySchoolCourseQuery query) {
        List list = societySchoolCourseMapper.classYesInCourseListPage(query);
        query.setList(list);
        return list;
    }

    //课程统计
    public int countCourseAllNum() {
        return societySchoolCourseMapper.countCourseAllNum();
    }

    /*
     * 逻辑删除该课程并统计章节数
     * */
    public void updateByIdNew(String id) {
        societySchoolCourseMapper.updateByIdNew(id);
    }

    public List<SocietySchoolCourseView> selectAllCourse(String schoolId) {
        return societySchoolCourseMapper.selectAllCourse(schoolId);
    }


    public int selectBySchoolId(String ownerSchoolId) {
        return societySchoolCourseMapper.selectBySchoolId(ownerSchoolId);
    }

    public int selectBySchoolIdAndTeacher(String schoolId, String id) {
        return societySchoolCourseMapper.selectBySchoolIdAndTeacher(schoolId, id);
    }

    public List<Dtree> listForDtreeBySchoolId(String schoolId) {
        List<SocietySchoolCourseView> societySchoolCourseViewList = societySchoolCourseMapper.listBySchoolId(schoolId);
        List<Dtree> dtreeList = new ArrayList<>();
        CheckArr checkArr = new CheckArr();
        checkArr.setChecked("0");
        checkArr.setType("0");
        List<CheckArr> list = new ArrayList<>();
        list.add(checkArr);
        for (SocietySchoolCourseView societySchoolCourseView : societySchoolCourseViewList) {
            Dtree dtree = new Dtree();
            dtree.setId(societySchoolCourseView.getId());
            dtree.setTitle(societySchoolCourseView.getCourseName());
            dtree.setParentId("ROOT");
            dtree.setSpread(false);
            dtree.setLast(false);
            dtree.setCheckArr(list);
            dtreeList.add(dtree);
        }
        return dtreeList;
    }

    public List<SocietySchoolCourse> selectAll() {
        return societySchoolCourseMapper.selectAll();
    }

    public List<SocietySchoolCourse> listMajorId(String schoolId, String majorId) {
        return societySchoolCourseMapper.listMajorId(schoolId, majorId);
    }

    public List<SocietySchoolCourseView> selectCourseAllIsNoSchool(String schoolId) {
        return societySchoolCourseMapper.selectCourseAllIsNoSchool(schoolId);
    }

    public List<SocietySchoolCourseView> selectCourseAllIsSchool(String schoolId) {
        return societySchoolCourseMapper.selectCourseAllIsSchool(schoolId);
    }

    public void updateByMajorId(String majorId, String majorName) {
        societySchoolCourseMapper.updateByMajorId(majorId, majorName);
    }

    public void updateByClsId(String clsId, String clsName, String majorId, String majorName) {
        societySchoolCourseMapper.updateByClsId(clsId, clsName, majorId, majorName);
    }

    public void updateByTeacherId(String teacherId, String teacherName) {
        societySchoolCourseMapper.updateByTeacherId(teacherId, teacherName);
    }

    public int selectSchoolIdAndCourseId(String schoolId, String courseId) {
        return societySchoolCourseMapper.selectSchoolIdAndCourseId(schoolId, courseId);
    }

    public int selectBySchoolIdAndCourseId(String nowSchoolId, String courseId) {
        return societySchoolCourseMapper.selectBySchoolIdAndCourseId(nowSchoolId,courseId);
    }

    public int selectByOwnerCourseId(String courseId) {
        return societySchoolCourseMapper.selectByOwnerCourseId(courseId);
    }

    public void updateByIsOpen(String courseId, String isOpen) {
        societySchoolCourseMapper.updateByIsOpen(courseId,isOpen);
    }

    public List<String> listByOwnerSchoolId(String schoolId) {
        return societySchoolCourseMapper.listByOwnerSchoolId(schoolId);
    }

    public List<String> selectByCourseIdList(String schoolId) {
        return societySchoolCourseMapper.selectByCourseIdList(schoolId);
    }

    public SocietySchoolCourse selectBySchoolIdAndOwnerCourseId(String ownerSchoolId, String ownerCourseId) {
        return societySchoolCourseMapper.selectBySchoolIdAndOwnerCourseId(ownerSchoolId,ownerCourseId);
    }
}
