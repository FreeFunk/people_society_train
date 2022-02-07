package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchool;
import com.edgedo.society.mapper.SocietySchoolMapper;
import com.edgedo.society.queryvo.SocietySchoolQuery;
import com.edgedo.society.queryvo.SocietySchoolView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolService {
	
	
	@Autowired
	private SocietySchoolMapper societySchoolMapper;

	//默认学校id
	@Value("${app.society.defaultSchoolId}")
	java.lang.String defaultSchoolId;

	//默认学校名称
	@Value("${app.society.defaultSchoolName}")
	java.lang.String defaultSchoolName;

	//默认登录logo
	java.lang.String schoolAppLoginImg="images/logon.png";

	public List<SocietySchoolView> listPage(SocietySchoolQuery societySchoolQuery){
		List list = societySchoolMapper.listPage(societySchoolQuery);
		societySchoolQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchool societySchool) {
		societySchool.setId(Guid.guid());
		societySchoolMapper.insert(societySchool);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchool societySchool) {
		societySchoolMapper.updateById(societySchool);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchool societySchool) {
		societySchoolMapper.updateAllColumnById(societySchool);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchool loadById(String id) {
		return societySchoolMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据主键查询学习信息同loadById
	 * @Date 2020/5/9 20:00
	 **/
    public SocietySchoolView selectSimpleSchoolById(String id) {
		return societySchoolMapper.selectSimpleSchoolById(id);
    }

    /**
     * @Author WangZhen
     * @Description 获得默认学校
     * @Date 2020/7/15 7:45
     **/
    public SocietySchoolView selectSimpleDefaultSchool() {
		SocietySchoolView defaultSch = new SocietySchoolView();
		defaultSch.setId(defaultSchoolId);
		defaultSch.setSchoolName(defaultSchoolName);
		defaultSch.setSchoolAppLoginImg(schoolAppLoginImg);
		return defaultSch;
    }

}
