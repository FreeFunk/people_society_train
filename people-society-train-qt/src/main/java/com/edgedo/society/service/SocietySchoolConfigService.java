package com.edgedo.society.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.dataenum.SchoolConfigKeyEnum;
import com.edgedo.society.entity.SocietySchoolConfig;
import com.edgedo.society.entity.SocietySchoolConfigKey;
import com.edgedo.society.mapper.SocietySchoolConfigKeyMapper;
import com.edgedo.society.mapper.SocietySchoolConfigMapper;
import com.edgedo.society.queryvo.SocietySchoolConfigQuery;
import com.edgedo.society.queryvo.SocietySchoolConfigView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolConfigService {
	
	
	@Autowired
	private SocietySchoolConfigMapper societySchoolConfigMapper;
	@Autowired
	private SocietySchoolConfigKeyMapper schoolConfigKeyMapper;


	/**
	 *@Author:ZhaoSiDa
	 *@Description: 根据学校id和key查询
	 *@DateTime: 2020/7/8 11:44
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public SocietySchoolConfig selectBySchoolIdAndKey(String schoolId, String configKey) {
		return societySchoolConfigMapper.selectBySchoolIdAndKey(schoolId,configKey);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据学校id和key获得value
	 * @Date 2020/7/9 15:43
	 **/
	public String loadKeyValue(String schId, SchoolConfigKeyEnum keyEnum) {
		String keyStr = keyEnum+"";
		//1.先查学校key-value
		String value = societySchoolConfigMapper.loadValueBySchoolIdAndKey(schId,keyStr);
		//2.再查全局的key-value
		if(value==null||value.equals("")){
			value = schoolConfigKeyMapper.loadValueByKey(keyStr);
		}
		return value;
	}

}
