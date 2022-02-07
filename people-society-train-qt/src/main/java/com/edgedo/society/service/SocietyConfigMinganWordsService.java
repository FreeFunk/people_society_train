package com.edgedo.society.service;
		
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.edgedo.common.util.BadWordsUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.RedisUtil;
import com.edgedo.society.constant.RedisKeyConstant;
import com.edgedo.society.entity.SocietyConfigMinganWords;
import com.edgedo.society.mapper.SocietyConfigMinganWordsMapper;
import com.edgedo.society.queryvo.SocietyConfigMinganWordsQuery;
import com.edgedo.society.queryvo.SocietyConfigMinganWordsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyConfigMinganWordsService {

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	private SocietyConfigMinganWordsMapper societyConfigMinganWordsMapper;

	public List<SocietyConfigMinganWordsView> listPage(SocietyConfigMinganWordsQuery societyConfigMinganWordsQuery){
		List list = societyConfigMinganWordsMapper.listPage(societyConfigMinganWordsQuery);
		societyConfigMinganWordsQuery.setList(list);
		return list;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyConfigMinganWords societyConfigMinganWords) {
		societyConfigMinganWords.setId(Guid.guid());
		societyConfigMinganWordsMapper.insert(societyConfigMinganWords);
		return "";
	}

	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyConfigMinganWords societyConfigMinganWords) {
		societyConfigMinganWordsMapper.updateById(societyConfigMinganWords);
		return "";
	}

	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyConfigMinganWords societyConfigMinganWords) {
		societyConfigMinganWordsMapper.updateAllColumnById(societyConfigMinganWords);
		return "";
	}



	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {

		return societyConfigMinganWordsMapper.deleteById(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {

		return societyConfigMinganWordsMapper.deleteBatchIds(ids);
	}



	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyConfigMinganWords loadById(String id) {
		return societyConfigMinganWordsMapper.selectById(id);
	}

	/**
	 * @Author WangZhen
	 * @Description 替换敏感词
	 * @Date 2020/5/13 11:06
	 **/
	public String replaceBadWord(String commentText) {
		Set<Object> badWordsSet = redisUtil.sGet(
				RedisKeyConstant.BAD_WORDS_SET_KEY);
		if(badWordsSet==null||badWordsSet.size()==0){
			badWordsSet = freshMinganWords();
		}
		BadWordsUtil badWordsUtil = BadWordsUtil.getInstance(badWordsSet);
		commentText =
				badWordsUtil.replaceBadWord(commentText);
		return commentText;
	}

	/**
	 * @Author WangZhen
	 * @Description 刷新敏感词
	 * @Date 2020/5/13 11:06
	 **/
	public Set<Object> freshMinganWords() {
		List<SocietyConfigMinganWordsView> list = societyConfigMinganWordsMapper.listByObj(new SocietyConfigMinganWordsQuery());
		String[] arr = new String[list.size()];
		for(int i=0;i<arr.length;i++){
			SocietyConfigMinganWordsView  wordsView = list.get(i);
			String wordText = wordsView.getWordText();
			arr[i] = wordText;
		}
		String key = RedisKeyConstant.BAD_WORDS_SET_KEY;
		redisUtil.del(key);
		redisUtil.sSet(key,arr);
		Set<Object> wordsSet = new HashSet<>();
		wordsSet.addAll(list);
		return wordsSet;
	}
}
