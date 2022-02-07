package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseNodeOption;
import com.edgedo.society.mapper.SocietySchoolCourseNodeOptionMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionView;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseNodeOptionService {
	
	
	@Autowired
	private SocietySchoolCourseNodeOptionMapper societySchoolCourseNodeOptionMapper;

	public List<SocietySchoolCourseNodeOptionView> listPage(SocietySchoolCourseNodeOptionQuery societySchoolCourseNodeOptionQuery){
		List list = societySchoolCourseNodeOptionMapper.listPage(societySchoolCourseNodeOptionQuery);
		societySchoolCourseNodeOptionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseNodeOption societySchoolCourseNodeOption) {
		societySchoolCourseNodeOption.setId(Guid.guid());
		societySchoolCourseNodeOptionMapper.insert(societySchoolCourseNodeOption);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseNodeOption societySchoolCourseNodeOption) {
		societySchoolCourseNodeOptionMapper.updateById(societySchoolCourseNodeOption);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseNodeOption societySchoolCourseNodeOption) {
		societySchoolCourseNodeOptionMapper.updateAllColumnById(societySchoolCourseNodeOption);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseNodeOptionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseNodeOptionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseNodeOption loadById(String id) {
		return societySchoolCourseNodeOptionMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据问题列表查询这些问题的答案
	 * @Date 2020/5/8 21:08
	 **/
    public List<SocietySchoolCourseNodeOptionView> selectOptionsByQuesList(List<SocietySchoolCourseNodeQuestionView> list) {
		return societySchoolCourseNodeOptionMapper.selectOptionsByQuesList(list);
    }

	/**
	 * @Author WangZhen
	 * @Description 根据题目id获得所有正确答案的id
	 * @Date 2020/7/8 16:52
	 **/
    public List<String> listRightAnswerIdsByQueId(String quesId) {
		return societySchoolCourseNodeOptionMapper.listRightAnswerIdsByQueId(quesId);
    }

}
