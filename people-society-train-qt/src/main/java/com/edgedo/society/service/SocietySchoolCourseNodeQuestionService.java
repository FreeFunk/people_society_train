package com.edgedo.society.service;
		
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietySchoolCourseNode;
import com.edgedo.society.entity.SocietySchoolCourseNodeOption;
import com.edgedo.society.entity.SocietySchoolCourseNodeQuestion;
import com.edgedo.society.entity.SocietyStudentPractiseQuestion;
import com.edgedo.society.mapper.SocietySchoolCourseNodeQuestionMapper;
import com.edgedo.society.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietySchoolCourseNodeQuestionService {
	
	
	@Autowired
	private SocietySchoolCourseNodeQuestionMapper societySchoolCourseNodeQuestionMapper;
	@Autowired
	private SocietySchoolCourseNodeOptionService optionService;
	@Autowired
	private SocietyStudentPractiseQuestionService practiseQuestionService;
	@Autowired
	private SocietySchoolCourseNodeService societySchoolCourseNodeService;

	public List<SocietySchoolCourseNodeQuestionView> listPage(SocietySchoolCourseNodeQuestionQuery societySchoolCourseNodeQuestionQuery){
		List<SocietySchoolCourseNodeQuestionView> list = societySchoolCourseNodeQuestionMapper.listPage(societySchoolCourseNodeQuestionQuery);
		societySchoolCourseNodeQuestionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion) {
		societySchoolCourseNodeQuestion.setId(Guid.guid());
		societySchoolCourseNodeQuestionMapper.insert(societySchoolCourseNodeQuestion);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion) {
		societySchoolCourseNodeQuestionMapper.updateById(societySchoolCourseNodeQuestion);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietySchoolCourseNodeQuestion societySchoolCourseNodeQuestion) {
		societySchoolCourseNodeQuestionMapper.updateAllColumnById(societySchoolCourseNodeQuestion);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societySchoolCourseNodeQuestionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societySchoolCourseNodeQuestionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietySchoolCourseNodeQuestion loadById(String id) {
		return societySchoolCourseNodeQuestionMapper.selectById(id);
	}


    public List<SocietySchoolCourseNodeQuestionView> listPageWithStuAnswer(
    		SocietySchoolCourseNodeQuestionQuery societySchoolCourseNodeQuestionQuery,
			List<SocietyStudentPractiseQuestOptionView> stuOpAnswerList) {

		List<SocietySchoolCourseNodeQuestionView> list = societySchoolCourseNodeQuestionMapper.listPage(societySchoolCourseNodeQuestionQuery);


		//将题目的答案查出来
		int queSize = list.size();
		if(queSize>0){
			//查询当前人的答题情况
			List<SocietyStudentPractiseQuestionView> stuPractiseQuestList = practiseQuestionService.listByOwnerStuNodeId(societySchoolCourseNodeQuestionQuery.getQueryObj().getStuNodeId());

			List<SocietySchoolCourseNodeOptionView> optionList = optionService.selectOptionsByQuesList(list);
			Map<String,SocietySchoolCourseNodeQuestionView>
					queMap = new HashMap<>();
			list.forEach((que)->{
				//处理一下正确答案的显示
				String queAnswer = que.getQuestionAnswer();
				if(queAnswer!=null){
					if(queAnswer.indexOf("@@")>=0){
						//设置题目类型多选
						que.setQuestionType("3");
					}
					queAnswer = queAnswer.replaceAll("@@",",");
					que.setQuestionAnswer(queAnswer);
				}
				que.setAnswerState("0");
				queMap.put(que.getId(),que);
			});

			Map<String,String> stuAnswerMap = new HashMap<>();
			stuOpAnswerList.forEach((answerOp)->{
				String queOpId = answerOp.getOwnerNodeQueOpId();
				String selectAnswers = stuAnswerMap.get(queOpId);
				if(selectAnswers !=null && selectAnswers.length()>0){
					selectAnswers = selectAnswers+","+queOpId;
					stuAnswerMap.put(queOpId,selectAnswers);
				}else{
					stuAnswerMap.put(queOpId,queOpId);
				}
			});
			//设置答题状态
			stuPractiseQuestList.forEach((stuPractiseQuest)->{
				String ownQueId = stuPractiseQuest.getQuersionId();
				SocietySchoolCourseNodeQuestionView que = queMap.get(ownQueId);
				if(que != null){
					String answerState = stuPractiseQuest.getAnswerState();
					if(answerState!=null&&answerState.length()>0){
						que.setAnswerState(answerState);
					}
				}
			});
			optionList.forEach((op)->{
				String ownQueId = op.getOwnerQuersionId();
				SocietySchoolCourseNodeQuestionView que = queMap.get(ownQueId);
				if(que!=null){
					que.getOps().add( op );
					String curOpId = op.getId();
					//判断选项是否被用户选择了
					String selectAnswers = stuAnswerMap.get(op.getId());
					//判断是否选中
					if(		selectAnswers!=null
							&& selectAnswers.length()>0
							&& selectAnswers.indexOf(curOpId)>=0){
						que.setSelectOpId(selectAnswers);
						op.setIsSelect("1");
					}
				}
			});
			queMap.clear();
			optionList.clear();
		}
		societySchoolCourseNodeQuestionQuery.setList(list);
		return list;
    }


	public List<SocietySchoolCourseNodeQuestionView> listPageWithStuAnswerNew(
			SocietySchoolCourseNodeQuestionQuery societySchoolCourseNodeQuestionQuery,
			String ascii) {

		//根据章节池id 查询题目
		SocietySchoolCourseNode societySchoolCourseNode =
				societySchoolCourseNodeService.loadById(societySchoolCourseNodeQuestionQuery.getQueryObj().getOwnerNodeId());
		societySchoolCourseNodeQuestionQuery.getQueryObj().setOwnerNodeId(societySchoolCourseNode.getOwnerNodeResourcesId());
		List<SocietySchoolCourseNodeQuestionView> list = societySchoolCourseNodeQuestionMapper.listPage(societySchoolCourseNodeQuestionQuery);


		//将题目的答案查出来
		int queSize = list.size();
		if(queSize>0){
			//查询当前人的答题情况
			List<SocietyStudentPractiseQuestionView> stuPractiseQuestList =
					practiseQuestionService.listByOwnerStuNodeIdNew(societySchoolCourseNodeQuestionQuery.getQueryObj().getStuNodeId(),ascii);

			List<SocietySchoolCourseNodeOptionView> optionList = optionService.selectOptionsByQuesList(list);
			Map<String,SocietySchoolCourseNodeQuestionView>
					queMap = new HashMap<>();
			list.forEach((que)->{
				//处理一下正确答案的显示
				String queAnswer = que.getQuestionAnswer();
				if(queAnswer!=null){
					if(queAnswer.indexOf("@@")>=0){
						//设置题目类型多选
						que.setQuestionType("3");
					}
					queAnswer = queAnswer.replaceAll("@@",",");
					que.setQuestionAnswer(queAnswer);
				}
				que.setAnswerState("0");
				queMap.put(que.getId(),que);
			});


			//设置答题状态
			stuPractiseQuestList.forEach((stuPractiseQuest)->{
				String ownQueId = stuPractiseQuest.getQuersionId();
				String  selecOpIds = stuPractiseQuest.getStuSelectOpId();
				SocietySchoolCourseNodeQuestionView que = queMap.get(ownQueId);
				if(que != null){
					String answerState = stuPractiseQuest.getAnswerState();
					if(answerState!=null&&answerState.length()>0){
						que.setAnswerState(answerState);
					}
					que.setSelectOpId(selecOpIds);
				}
			});
			optionList.forEach((op)->{
				String ownQueId = op.getOwnerQuersionId();
				SocietySchoolCourseNodeQuestionView que = queMap.get(ownQueId);
				if(que!=null){
					que.getOps().add( op );
					String curOpId = op.getId();
					//判断选项是否被用户选择了
					String selectAnswers = que.getSelectOpId();
					//判断是否选中
					if(		selectAnswers!=null
							&& selectAnswers.length()>0
							&& selectAnswers.indexOf(curOpId)>=0){
						que.setSelectOpId(selectAnswers);
						op.setIsSelect("1");
					}
				}
			});
			queMap.clear();
			optionList.clear();
		}
		societySchoolCourseNodeQuestionQuery.setList(list);
		return list;
	}

	/**
	 * @Author WangZhen
	 * @Description 根据课程章节id拿到习题的个数
	 * @Date 2020/7/10 11:09
	 **/
    public int countQuesNumByNodeId(String ownerNodeId) {
		return societySchoolCourseNodeQuestionMapper.countQuesNumByNodeId(ownerNodeId);
    }
}
