package com.edgedo.society.service;
		
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseNodeOption;
import com.edgedo.society.entity.SocietyStudentAndNode;
import com.edgedo.society.entity.SocietyStudentPractiseQuestOption;
import com.edgedo.society.mapper.SocietyStudentPractiseQuestOptionMapper;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionQuery;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestOptionView;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyStudentPractiseQuestOptionService {
	
	
	@Autowired
	private SocietyStudentPractiseQuestOptionMapper societyStudentPractiseQuestOptionMapper;
	@Autowired
	private SocietyStudentAndNodeService studentAndNodeService;

	public List<SocietyStudentPractiseQuestOptionView> listPage(SocietyStudentPractiseQuestOptionQuery societyStudentPractiseQuestOptionQuery){
		List list = societyStudentPractiseQuestOptionMapper.listPage(societyStudentPractiseQuestOptionQuery);
		societyStudentPractiseQuestOptionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption) {
		societyStudentPractiseQuestOption.setId(Guid.guid());
		societyStudentPractiseQuestOptionMapper.insert(societyStudentPractiseQuestOption);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption) {
		societyStudentPractiseQuestOptionMapper.updateById(societyStudentPractiseQuestOption);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyStudentPractiseQuestOption societyStudentPractiseQuestOption) {
		societyStudentPractiseQuestOptionMapper.updateAllColumnById(societyStudentPractiseQuestOption);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyStudentPractiseQuestOptionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyStudentPractiseQuestOptionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyStudentPractiseQuestOption loadById(String id) {
		return societyStudentPractiseQuestOptionMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 根据学生id和章节id查询用户选择的正确答案
	 * @Date 2020/5/8 21:32
	 **/
    public List<SocietyStudentPractiseQuestOptionView> listByStuAndCouseNodeOfSelect(
    		String stuIdCardNum, String ownerNodeId,String stuNodeId) {
		SocietyStudentAndNode stuAndNode = studentAndNodeService.loadById(stuNodeId);
		if(stuAndNode!=null && stuIdCardNum.equals(stuAndNode.getStudentIdCardNum())){
			String stuId = stuAndNode.getStudentId();
			return societyStudentPractiseQuestOptionMapper.listByStuAndCouseNodeOfSelect(
					stuId,ownerNodeId,stuNodeId);
		}
		return new ArrayList<>();
    }

	public List<SocietyStudentPractiseQuestOptionView> listByStuAndCouseNodeOfSelectNew(
			String stuIdCardNum, String ownerNodeId,String stuNodeId,String ascii) {
		SocietyStudentAndNode stuAndNode = studentAndNodeService.loadById(stuNodeId);
		if(stuAndNode!=null && stuIdCardNum.equals(stuAndNode.getStudentIdCardNum())){
			String stuId = stuAndNode.getStudentId();
			return societyStudentPractiseQuestOptionMapper.listByStuAndCouseNodeOfSelectNew(
					stuId,ownerNodeId,stuNodeId,ascii);
		}
		return new ArrayList<>();
	}

    public void insertOrUpdateStuPractisOptionSel(
    		SocietyStudentAndNode stuNode ,
			SocietySchoolCourseNodeOption courseNodeOption,
			SocietyStudentPractiseQuestionView stQuestion) {

    	//先将已经选择的设置成未选择

		SocietyStudentPractiseQuestOption option =
				societyStudentPractiseQuestOptionMapper.selectByCouseOpIdAndStuNodeId(
						courseNodeOption.getId(),stuNode.getId()
		);
		Date cur = new Date();
		if(option!=null){
			String isSelect = option.getIsSelect();
			if(isSelect!=null && isSelect.equals("1")){
				return;
			}

			option.setLastAnswerTime(cur);
			option.setIsSelect("1");
//			option.setUpdateTime(cur);
			//不更新索引字段省着造成死锁
			option.setOwnerStudentAndNodeId(null);
			societyStudentPractiseQuestOptionMapper.updateById(option);

		}else{

			option = new SocietyStudentPractiseQuestOption();
//			option.setUpdateTime(cur);
			option.setIsSelect("1");
			option.setCreateTime(cur);
			option.setLastAnswerTime(cur);
			option.setId(Guid.guid());
			option.setOptionName(courseNodeOption.getOptionName());
			option.setOptionTitle(courseNodeOption.getOptionTitle());
			option.setOrderNum(courseNodeOption.getOrderNum());
			option.setOwnerNodeId(courseNodeOption.getOwnerNodeId());
			option.setOwnerNodeName(courseNodeOption.getOwnerNodeName());
			option.setOwnerNodeQueOpId(courseNodeOption.getId());
			option.setOwnerSchoolId(courseNodeOption.getOwnerSchoolId());
			option.setOwnerSchoolName(courseNodeOption.getOwnerSchoolName());
			option.setOwnerStuCourseId(stuNode.getOwnerStudentAndCourseId());
			option.setOwnerStudentAndNodeId(stuNode.getId());
			option.setOwnerStudentQuersionId(stQuestion.getId());
			option.setOwnerStudentQuersionName(stQuestion.getQuestionName());
			option.setStudentId(stuNode.getStudentId());
			option.setIsRight(courseNodeOption.getIsRight());
			societyStudentPractiseQuestOptionMapper.insert(option);

		}


    }


	public SocietyStudentPractiseQuestOption insertOrUpdateStuPractisOptionSelNew(
			SocietyStudentAndNode stuNode ,
			SocietySchoolCourseNodeOption courseNodeOption,
			SocietyStudentPractiseQuestionView stQuestion,String ascii) {

		//先将已经选择的设置成未选择

		SocietyStudentPractiseQuestOption option = null;
		Date cur = new Date();
		option = new SocietyStudentPractiseQuestOption();
//			option.setUpdateTime(cur);
		option.setIsSelect("1");
		option.setCreateTime(cur);
		option.setLastAnswerTime(cur);
		option.setId(Guid.guid());
		option.setOptionName(courseNodeOption.getOptionName());
		option.setOptionTitle(courseNodeOption.getOptionTitle());
		option.setOrderNum(courseNodeOption.getOrderNum());
		option.setOwnerNodeId(courseNodeOption.getOwnerNodeId());
		option.setOwnerNodeName(courseNodeOption.getOwnerNodeName());
		option.setOwnerNodeQueOpId(courseNodeOption.getId());
		option.setOwnerSchoolId(courseNodeOption.getOwnerSchoolId());
		option.setOwnerSchoolName(courseNodeOption.getOwnerSchoolName());
		option.setOwnerStuCourseId(stuNode.getOwnerStudentAndCourseId());
		option.setOwnerStudentAndNodeId(stuNode.getId());
		option.setOwnerStudentQuersionId(stQuestion.getId());
		option.setOwnerStudentQuersionName(stQuestion.getQuestionName());
		option.setStudentId(stuNode.getStudentId());
		option.setIsRight(courseNodeOption.getIsRight());
//		societyStudentPractiseQuestOptionMapper.insertNew(option,ascii);
		return option;

	}

	/**
	 * @Author WangZhen
	 * @Description 将学生学习节点的所有的习题选项设置为未选择
	 * @Date 2020/7/9 5:29
	 **/
    public void updateAllStuNodeOptionUnSelect(String stuNodeId) {
		int i = societyStudentPractiseQuestOptionMapper.updateAllStuNodeOptionUnSelect(stuNodeId);
    }

	public void updateAllStuNodeOptionUnSelectNew(String stuNodeId,String ascii) {
		int i = societyStudentPractiseQuestOptionMapper.updateAllStuNodeOptionUnSelectNew(stuNodeId,ascii);
	}

	/**
	 * @Author WangZhen
	 * @Description
	 * @Date 2020/9/8 15:22
	 **/
	public int deleteStuQuesOptionByStuNodeId(String stuNodeId,String ascii) {
		return societyStudentPractiseQuestOptionMapper.deleteStuQuesOptionByStuNodeId(stuNodeId,ascii);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertNewList(List<SocietyStudentPractiseQuestOption> optionList, String ascii) {
		societyStudentPractiseQuestOptionMapper.insertNewList(optionList,ascii);
	}
}
