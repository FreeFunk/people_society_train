package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.*;
import com.edgedo.society.mapper.*;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import com.edgedo.society.queryvo.SocietyTestPaperQuery;
import com.edgedo.society.queryvo.SocietyTestPaperView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyTestPaperService {
	
	
	@Autowired
	private SocietyTestPaperMapper societyTestPaperMapper;
	@Autowired
	private SocietyTestPaperQuestionMapper societyTestPaperQuestionMapper;
	@Autowired
	private SocietyTestPaperQuestionOptionMapper societyTestPaperQuestionOptionMapper;
	@Autowired
	private SocietySchoolCourseNodeQuestionMapper societySchoolCourseNodeQuestionMapper;
    @Autowired
    private SocietySchoolCourseNodeOptionMapper societySchoolCourseNodeOptionMapper;

	public List<SocietyTestPaperView> listPage(SocietyTestPaperQuery societyTestPaperQuery){
		List list = societyTestPaperMapper.listPage(societyTestPaperQuery);
		societyTestPaperQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法,试卷插入时，生成习题
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyTestPaper societyTestPaper) {
		societyTestPaper.setId(Guid.guid());
		int danxuanQuestionNum = societyTestPaper.getDanxuanQuestionNum();
		//插入试卷习题,单选题
		Map map = new HashMap();
		map.put("courseId",societyTestPaper.getOwnerCourseId());
		map.put("questionType","1");
		map.put("num",societyTestPaper.getDanxuanQuestionNum());
		List<SocietySchoolCourseNodeQuestionView> list = societySchoolCourseNodeQuestionMapper.selectQuestionsForRound(map);
        List<SocietySchoolCourseNodeOption> listO = null;
		SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion = null;
        SocietySchoolCourseNodeOption societySchoolCourseNodeOption = null;
		SocietyTestPaperQuestion societyTestPaperQuestion = null;
		SocietyTestPaperQuestionOption societyTestPaperQuestionOption = null;
		for(int i=0; i<list.size(); i++){
			societySchoolCourseNodeQuestion = list.get(i);
			//插入习题
            societyTestPaperQuestion = new SocietyTestPaperQuestion();
			societyTestPaperQuestion.setId(Guid.guid());
			societyTestPaperQuestion.setCreateTime(new Date());
			societyTestPaperQuestion.setCreateUserId(societyTestPaper.getCreateUserId());
			societyTestPaperQuestion.setCreateUserName(societyTestPaper.getCreateUserName());
			societyTestPaperQuestion.setDataState("1");
			societyTestPaperQuestion.setOwnerSchoolId(societyTestPaper.getOwnerSchoolId());
			societyTestPaperQuestion.setOwnerSchoolName(societyTestPaper.getOwnerSchoolName());
			societyTestPaperQuestion.setOwnerCourseId(societyTestPaper.getOwnerCourseId());
			societyTestPaperQuestion.setOwnerCourseName(societyTestPaper.getOwnerCourseName());
			societyTestPaperQuestion.setOwnerTestPaperId(societyTestPaper.getId());
			societyTestPaperQuestion.setOwnerTestPaperName(societyTestPaper.getTestPaperName());
			societyTestPaperQuestion.setQuestionName(societySchoolCourseNodeQuestion.getQuestionName());
			societyTestPaperQuestion.setQuestionType(societySchoolCourseNodeQuestion.getQuestionType());
			societyTestPaperQuestion.setQuestionScore(societyTestPaper.getDanxuanScore());
			societyTestPaperQuestion.setQuestionAnalysis(societySchoolCourseNodeQuestion.getQuestionAnalysis());
			societyTestPaperQuestion.setQuestionAnswer(societySchoolCourseNodeQuestion.getQuestionAnswer());
			societyTestPaperQuestion.setOrderNum(BigDecimal.valueOf(i+1));
            societyTestPaperQuestionMapper.insert(societyTestPaperQuestion);

			//插入选项
            listO = societySchoolCourseNodeOptionMapper.selectOptionByQuestion(societySchoolCourseNodeQuestion.getId());
            for(int j=0; j<listO.size(); j++){
                societySchoolCourseNodeOption = listO.get(j);
                societyTestPaperQuestionOption = new SocietyTestPaperQuestionOption();
                societyTestPaperQuestionOption.setId(Guid.guid());
                societyTestPaperQuestionOption.setCreateTime(new Date());
                societyTestPaperQuestionOption.setCreateUserId(societyTestPaper.getCreateUserId());
                societyTestPaperQuestionOption.setCreateUserName(societyTestPaper.getCreateUserName());
                societyTestPaperQuestionOption.setDataState("1");
                societyTestPaperQuestionOption.setOwnerSchoolId(societyTestPaper.getOwnerSchoolId());
                societyTestPaperQuestionOption.setOwnerSchoolName(societyTestPaper.getOwnerSchoolName());
                societyTestPaperQuestionOption.setOwnerCourseId(societyTestPaper.getOwnerCourseId());
                societyTestPaperQuestionOption.setOwnerCourseName(societyTestPaper.getOwnerCourseName());
                societyTestPaperQuestionOption.setOwnerTestPaperId(societyTestPaper.getId());
                societyTestPaperQuestionOption.setOwnerTestPaperName(societyTestPaper.getTestPaperName());
                societyTestPaperQuestionOption.setOwnerTestPaperQuestionId(societyTestPaperQuestion.getId());
                societyTestPaperQuestionOption.setOptionName(societySchoolCourseNodeOption.getOptionName());
                societyTestPaperQuestionOption.setOptionTitle(societySchoolCourseNodeOption.getOptionTitle());
                societyTestPaperQuestionOption.setIsRight(societySchoolCourseNodeOption.getIsRight());
                societyTestPaperQuestionOption.setOrderNum(societySchoolCourseNodeOption.getOrderNum());
                societyTestPaperQuestionOptionMapper.insert(societyTestPaperQuestionOption);
            }
		}
        //插入试卷习题,判断题
        map = new HashMap();
        map.put("courseId",societyTestPaper.getOwnerCourseId());
        map.put("questionType","2");
        map.put("num",societyTestPaper.getPanduanQuestionNum());
        list = societySchoolCourseNodeQuestionMapper.selectQuestionsForRound(map);
        for(int i=0; i<list.size(); i++){
            societySchoolCourseNodeQuestion = list.get(i);
            //插入习题
            societyTestPaperQuestion = new SocietyTestPaperQuestion();
            societyTestPaperQuestion.setId(Guid.guid());
            societyTestPaperQuestion.setCreateTime(new Date());
            societyTestPaperQuestion.setCreateUserId(societyTestPaper.getCreateUserId());
            societyTestPaperQuestion.setCreateUserName(societyTestPaper.getCreateUserName());
            societyTestPaperQuestion.setDataState("1");
            societyTestPaperQuestion.setOwnerSchoolId(societyTestPaper.getOwnerSchoolId());
            societyTestPaperQuestion.setOwnerSchoolName(societyTestPaper.getOwnerSchoolName());
            societyTestPaperQuestion.setOwnerCourseId(societyTestPaper.getOwnerCourseId());
            societyTestPaperQuestion.setOwnerCourseName(societyTestPaper.getOwnerCourseName());
            societyTestPaperQuestion.setOwnerTestPaperId(societyTestPaper.getId());
            societyTestPaperQuestion.setOwnerTestPaperName(societyTestPaper.getTestPaperName());
            societyTestPaperQuestion.setQuestionName(societySchoolCourseNodeQuestion.getQuestionName());
            societyTestPaperQuestion.setQuestionType(societySchoolCourseNodeQuestion.getQuestionType());
            societyTestPaperQuestion.setQuestionScore(societyTestPaper.getPanduanScore());
            societyTestPaperQuestion.setQuestionAnalysis(societySchoolCourseNodeQuestion.getQuestionAnalysis());
            societyTestPaperQuestion.setQuestionAnswer(societySchoolCourseNodeQuestion.getQuestionAnswer());
            societyTestPaperQuestion.setOrderNum(BigDecimal.valueOf(i+1+danxuanQuestionNum));
            societyTestPaperQuestionMapper.insert(societyTestPaperQuestion);

            //插入选项
            listO = societySchoolCourseNodeOptionMapper.selectOptionByQuestion(societySchoolCourseNodeQuestion.getId());
            for(int j=0; j<listO.size(); j++){
                societySchoolCourseNodeOption = listO.get(j);
                societyTestPaperQuestionOption = new SocietyTestPaperQuestionOption();
                societyTestPaperQuestionOption.setId(Guid.guid());
                societyTestPaperQuestionOption.setCreateTime(new Date());
                societyTestPaperQuestionOption.setCreateUserId(societyTestPaper.getCreateUserId());
                societyTestPaperQuestionOption.setCreateUserName(societyTestPaper.getCreateUserName());
                societyTestPaperQuestionOption.setDataState("1");
                societyTestPaperQuestionOption.setOwnerSchoolId(societyTestPaper.getOwnerSchoolId());
                societyTestPaperQuestionOption.setOwnerSchoolName(societyTestPaper.getOwnerSchoolName());
                societyTestPaperQuestionOption.setOwnerCourseId(societyTestPaper.getOwnerCourseId());
                societyTestPaperQuestionOption.setOwnerCourseName(societyTestPaper.getOwnerCourseName());
                societyTestPaperQuestionOption.setOwnerTestPaperId(societyTestPaper.getId());
                societyTestPaperQuestionOption.setOwnerTestPaperName(societyTestPaper.getTestPaperName());
                societyTestPaperQuestionOption.setOwnerTestPaperQuestionId(societyTestPaperQuestion.getId());
                societyTestPaperQuestionOption.setOptionName(societySchoolCourseNodeOption.getOptionName());
                societyTestPaperQuestionOption.setOptionTitle(societySchoolCourseNodeOption.getOptionTitle());
                societyTestPaperQuestionOption.setIsRight(societySchoolCourseNodeOption.getIsRight());
                societyTestPaperQuestionOption.setOrderNum(societySchoolCourseNodeOption.getOrderNum());
                societyTestPaperQuestionOptionMapper.insert(societyTestPaperQuestionOption);
            }
        }
        //插入试卷
		societyTestPaperMapper.insert(societyTestPaper);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyTestPaper societyTestPaper) {
		societyTestPaperMapper.updateById(societyTestPaper);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyTestPaper societyTestPaper) {
		societyTestPaperMapper.updateAllColumnById(societyTestPaper);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyTestPaperMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyTestPaperMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyTestPaper loadById(String id) {
		return societyTestPaperMapper.selectById(id);
	}
	

}
