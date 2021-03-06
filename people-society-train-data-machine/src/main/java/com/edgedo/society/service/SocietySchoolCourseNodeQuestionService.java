package com.edgedo.society.service;
		
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietySchoolCourseNodeOption;
import com.edgedo.society.entity.SocietySchoolCourseNodeQuestion;
import com.edgedo.society.mapper.SocietySchoolCourseNodeMapper;
import com.edgedo.society.mapper.SocietySchoolCourseNodeOptionMapper;
import com.edgedo.society.mapper.SocietySchoolCourseNodeQuestionMapper;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeOptionView;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionQuery;
import com.edgedo.society.queryvo.SocietySchoolCourseNodeQuestionView;
import com.edgedo.society.queryvo.SocietyStudentPractiseQuestionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseNodeQuestionService {
	
	
	@Autowired
	private SocietySchoolCourseNodeQuestionMapper nodeQuestionMapper;
	@Autowired
	private SocietySchoolCourseNodeMapper courseNodeMapper;
	@Autowired
	private SocietySchoolCourseNodeOptionService nodeOptionService;

	public List<SocietySchoolCourseNodeQuestionView> listPage(SocietySchoolCourseNodeQuestionQuery societySchoolCourseNodeQuestionQuery){
		List list = nodeQuestionMapper.listPage(societySchoolCourseNodeQuestionQuery);
		societySchoolCourseNodeQuestionQuery.setList(list);
		return list;
	}


	/**
	 * 查询所有相关的小节的题目
	 * @param societySchoolCourseNodeQuestionQuery
	 * @return
	 */
	public List<SocietySchoolCourseNodeQuestionView> selectByQuesionAllIdlistPage(SocietySchoolCourseNodeQuestionQuery societySchoolCourseNodeQuestionQuery){
		List list = nodeQuestionMapper.selectByQuesionAllIdlistPage(societySchoolCourseNodeQuestionQuery);
		societySchoolCourseNodeQuestionQuery.setList(list);
		return list;
	}

	/**
	 * 根据课程id与习题类型随从查询n条记录，试卷管理生成试题使用
	 * @param map
	 * @return
	 */
	public List<SocietySchoolCourseNodeQuestionView> selectQuestionsForRound(Map map){
		List list = nodeQuestionMapper.selectQuestionsForRound(map);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion) {
		societySchoolCourseNodeQuestion.setId(Guid.guid());
		societySchoolCourseNodeQuestion.setCreateTime(new Date());
		nodeQuestionMapper.insert(societySchoolCourseNodeQuestion);
		return "";
	}

	public String insertCopy(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion) {
		nodeQuestionMapper.insert(societySchoolCourseNodeQuestion);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion) {
		nodeQuestionMapper.updateById(societySchoolCourseNodeQuestion);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion) {
		nodeQuestionMapper.updateAllColumnById(societySchoolCourseNodeQuestion);
		return "";
	}

	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return nodeQuestionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return nodeQuestionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseNodeQuestion loadById(String id) {
		return nodeQuestionMapper.selectById(id);
	}

	/***
	 * 学校添加习题和题目选项
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertQuestionAndOption(SocietySchoolCourseNodeQuestion question,String[] optionArr) {
		String ownerNodeId = question.getOwnerNodeId();
		SocietySchoolCourseNode node = courseNodeMapper.selectById(ownerNodeId);
		if(node != null){
			question.setOwnerSchoolId(node.getOwnerSchoolId());
			question.setOwnerSchoolName(node.getOwnerSchoolName());
			question.setOwnerMajorId(node.getOwnerMajorId());
			question.setOwnerMajorName(node.getOwnerMajorName());
			question.setOwnerCourseClsId(node.getOwnerCourseClsId());
			question.setOwnerCourseClsName(node.getOwnerCourseClsName());
			question.setOwnerCourseId(node.getOwnerCourseId());
			question.setOwnerCourseName(node.getOwnerCourseName());
			question.setOwnerNodeId(node.getId());
			question.setOwnerNodeName(node.getNodeName());
			question.setQuestionScore(1);
			String result = result = insert(question);
			if(result != null && result.equals("")){
				String[] titleArr = new String[]{};
				String[] questionAnswer =new String[]{};
				if(!question.getQuestionType().equals("2")){
					titleArr = getOptionTitleArr(optionArr.length);
					questionAnswer = question.getQuestionAnswer().split("@@");
				}else if(question.getQuestionType().equals("2")){
					titleArr = new String[]{"正确","错误"};
				}
				//插入选项
				SocietySchoolCourseNodeOption nodeOption = new SocietySchoolCourseNodeOption();
				nodeOption.setCreateUserId(question.getCreateUserId());
				nodeOption.setCreateUserName(question.getCreateUserName());
				nodeOption.setOwnerSchoolId(question.getOwnerSchoolId());
				nodeOption.setOwnerSchoolName(question.getOwnerSchoolName());
				nodeOption.setOwnerNodeId(question.getOwnerNodeId());
				nodeOption.setOwnerNodeName(question.getOwnerNodeName());
				nodeOption.setOwnerQuersionId(question.getId());
				nodeOption.setOwnerQuestionName(question.getQuestionName());
				for(int i=0;i<optionArr.length;i++){
					String optionTitle = titleArr[i];

					if(question.getQuestionType().equals("2")){
						if(optionTitle != null && optionTitle.equals("正确")){
							optionTitle = "√";
						}else{
							optionTitle = "×";
						}
						if(question.getQuestionAnswer() != null && question.getQuestionAnswer().equals(optionTitle)){
							nodeOption.setIsRight("1");
						}else{
							nodeOption.setIsRight("0");
						}
					}else {
						String flag = "";
						for(int j=0;j<questionAnswer.length;j++){
							String answer = questionAnswer[j];
							if(flag == ""){
								if(answer != null && answer.equals(optionTitle)){
									nodeOption.setIsRight("1");
									flag = "1";
								}else{
									nodeOption.setIsRight("0");
								}
							}else {
								flag = "";
								break;
							}
						}
					}
					nodeOption.setOptionTitle(titleArr[i]);
					nodeOption.setOptionName(optionArr[i]);
					nodeOption.setOrderNum(new BigDecimal(i));
					nodeOptionService.insert(nodeOption);
				}
			}
			//统计该章节下题目的数量
			int questionNum = nodeQuestionMapper.countQuestionNumByNodeId(ownerNodeId);
			//更新习题数量
			node.setQuestionNum(questionNum);
			courseNodeMapper.updateById(node);
		}
		return "";
	}

	private String[] getOptionTitleArr(int length) {
		String title = "";
		for (int i = 0 ;i<length;i++){
			if(title.equals("")){
				title = String.valueOf(Character.toUpperCase( (char)(97+i)));
			}else {
				title = title +","+ String.valueOf(Character.toUpperCase( (char)(97+i)));
			}
		}
		return title.split(",");
	}

	/**
	 * @Author ZhangCC
	 * @Description 学校添加习题和题目选项
	 * @Date 2020/5/9 16:53
	 **/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateQuestionAndOption(SocietySchoolCourseNodeQuestion question,String[] optionArr) {
		String result = update(question);
		if(result != null && result.equals("")){
			List<SocietySchoolCourseNodeOption> nodeOptionList = nodeOptionService.selectOptionByQuestion(question.getId());
			//判断当前选项数是否和传过来的选项一致，
			if(optionArr.length == nodeOptionList.size()){//题目类型没有改变
				String[] answer = question.getQuestionAnswer().split("@@");
				for(int i=0;i<nodeOptionList.size();i++){
					if(question.getQuestionType().equals("1")){
						String flag = "";
						for(int j=0;j<answer.length;j++){
							String qtAnswer = answer[j];
							if(flag == ""){
								if(qtAnswer != null && qtAnswer.equals(nodeOptionList.get(i).getOptionTitle())){
									nodeOptionList.get(i).setIsRight("1");
									flag = "1";
								}else{
									nodeOptionList.get(i).setIsRight("0");
								}
							}else {
								flag = "";
								break;
							}
						}
						nodeOptionList.get(i).setOptionName(optionArr[i]);
						nodeOptionService.update(nodeOptionList.get(i));
					}else {
						if(nodeOptionList.get(i).getOptionName().equals(question.getQuestionAnswer())){
							nodeOptionList.get(i).setIsRight("1");
						}else {
							nodeOptionList.get(i).setIsRight("0");
						}
						nodeOptionList.get(i).setOptionName(optionArr[i]);
						nodeOptionService.update(nodeOptionList.get(i));
					}

				}
			}else {//题目类型改变 将数据库的nodeOptionList全部删除  新加入optionArr
				question = nodeQuestionMapper.selectById(question.getId());
				nodeOptionService.deletenodeOptionList(nodeOptionList);
				String[] titleArr = new String[]{};
				String[] questionAnswer = new String[]{};
				if(!question.getQuestionType().equals("2")){
                    titleArr = getOptionTitleArr(optionArr.length);
					questionAnswer = question.getQuestionAnswer().split("@@");
                }else if(question.getQuestionType().equals("2")){
					titleArr = new String[]{"正确","错误"};
				}

				//插入选项
				SocietySchoolCourseNodeOption nodeOption = new SocietySchoolCourseNodeOption();
				nodeOption.setCreateUserId(question.getCreateUserId());
				nodeOption.setCreateUserName(question.getCreateUserName());
				nodeOption.setOwnerSchoolId(question.getOwnerSchoolId());
				nodeOption.setOwnerSchoolName(question.getOwnerSchoolName());
				nodeOption.setOwnerNodeId(question.getOwnerNodeId());
				nodeOption.setOwnerNodeName(question.getOwnerNodeName());
				nodeOption.setOwnerQuersionId(question.getId());
				nodeOption.setOwnerQuestionName(question.getQuestionName());
				for(int i=0;i<optionArr.length;i++) {
					String optionTitle = titleArr[i];
					if(question.getQuestionType().equals("2")){
						if(optionTitle != null && optionTitle.equals("正确")){
							optionTitle = "√";
						}else{
							optionTitle = "×";
						}
						if(question.getQuestionAnswer() != null && question.getQuestionAnswer().equals(optionTitle)){
							nodeOption.setIsRight("1");
						}else{
							nodeOption.setIsRight("0");
						}
					}else {
						String flag = "";
						for(int j=0;j<questionAnswer.length;j++){
							String answer = questionAnswer[j];
							if(flag == ""){
								if(answer != null && answer.equals(optionTitle)){
									nodeOption.setIsRight("1");
									flag = "1";
								}else{
									nodeOption.setIsRight("0");
								}
							}else {
								flag = "";
								break;
							}
						}
					}
					nodeOption.setOptionTitle(titleArr[i]);
					nodeOption.setOptionName(optionArr[i]);
					nodeOption.setOrderNum(new BigDecimal(i));
					nodeOptionService.insert(nodeOption);
				}
			}
		}
		return "";
	}

	/**
	 * 查询总共的题目数  公共表
	 * @param nodeId
	 * @param ownerCourseId
	 * @return
	 */
	public int selectByCourseIdAndNodeId(String nodeId, String ownerCourseId) {
		Map<String,String> map = new HashMap<>();
		map.put("nodeId",nodeId);
		map.put("ownerCourseId",ownerCourseId);
		return nodeQuestionMapper.selectByCourseIdAndNodeId(map);
	}

	public int countNodeQuestionNum(String ownerNodeId) {
		return nodeQuestionMapper.countQuestionNumByNodeId(ownerNodeId);
	}

	public Integer selectQuestionNum(String ownerNodeId) {
		return nodeQuestionMapper.selectQuestionNum(ownerNodeId);
	}

	public List<SocietySchoolCourseNodeQuestionView> selectByCourseIdAndNodeIdList(
			String courseId, String nodeId) {
		Map<String,String> map = new HashMap<>();
		map.put("courseId",courseId);
		map.put("nodeId",nodeId);
		return nodeQuestionMapper.selectByCourseIdAndNodeIdList(map);
	}

	public int countQuestionNumByNodeId(String id) {
		return nodeQuestionMapper.countQuestionNumByNodeId(id);
	}

	public void updateByMajorId(String majorId, String majorName) {
		nodeQuestionMapper.updateByMajorId(majorId,majorName);
	}

	public void updateByClsId(String clsId, String clsName, String majorId, String majorName) {
		nodeQuestionMapper.updateByClsId(clsId,clsName,majorId,majorName);
	}

	public void updateByNodeIdAndNodeName(String nodeId, String nodeName) {
		nodeQuestionMapper.updateByNodeIdAndNodeName(nodeId,nodeName);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateByCourseIdAndCourseName(Map<String, String> map) {
		nodeQuestionMapper.updateByCourseIdAndCourseName(map);
	}

	public String selectCourseIdAndquesName(String questionName, String courseId, String nodeId) {
		return nodeQuestionMapper.selectCourseIdAndquesName(questionName,courseId,nodeId);
	}


	public Integer selectByNodeId(String nodeId) {
		return nodeQuestionMapper.selectByNodeId(nodeId);
	}
}
