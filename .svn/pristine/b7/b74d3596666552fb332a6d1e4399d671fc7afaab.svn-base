package com.edgedo.society.service;
		
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.society.entity.SocietyStudent;
import com.edgedo.society.entity.SocietyWxMessage;
import com.edgedo.society.entity.SocietyWxMessageRec;
import com.edgedo.society.mapper.SocietySchoolMapper;
import com.edgedo.society.mapper.SocietyStudentMapper;
import com.edgedo.society.mapper.SocietyWxMessageMapper;
import com.edgedo.society.mapper.SocietyWxMessageRecMapper;
import com.edgedo.society.queryvo.SocietyStudentView;
import com.edgedo.society.queryvo.SocietyWxMessageQuery;
import com.edgedo.society.queryvo.SocietyWxMessageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SocietyWxMessageService {
	
	
	@Autowired
	private SocietyWxMessageMapper societyWxMessageMapper;

	@Autowired
	private SocietyStudentMapper societyStudentMapper;

	@Autowired
	private SocietyWxMessageRecMapper societyWxMessageRecMapper;

	public List<SocietyWxMessageView> listPage(SocietyWxMessageQuery societyWxMessageQuery){
		List list = societyWxMessageMapper.listPage(societyWxMessageQuery);
		societyWxMessageQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SocietyWxMessage societyWxMessage) {
		societyWxMessage.setId(Guid.guid());
		societyWxMessageMapper.insert(societyWxMessage);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SocietyWxMessage societyWxMessage) {
		societyWxMessageMapper.updateById(societyWxMessage);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SocietyWxMessage societyWxMessage) {
		societyWxMessageMapper.updateAllColumnById(societyWxMessage);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return societyWxMessageMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return societyWxMessageMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SocietyWxMessage loadById(String id) {
		return societyWxMessageMapper.selectById(id);
	}


	/**
	 * 将所有的学生查出来发送所有消息
	 * @param voObj
	 */
	public void insertMessageRec(SocietyWxMessage voObj,User user) {
		//1.查出该学校下的所有学生
		List<SocietyStudentView> list = societyStudentMapper.selectAllStudent(voObj.getOwnerSchoolId());
		//2.遍历插入到Rec表
		for(SocietyStudentView societyStudentView : list){
			SocietyWxMessageRec societyWxMessageRec = new SocietyWxMessageRec();
			societyWxMessageRec.setId(Guid.guid());//主键
			societyWxMessageRec.setCreateTime(new Date());//创建时间
			societyWxMessageRec.setCreateUserId(user.getUserId());//创建人id
			societyWxMessageRec.setCreateUserName(user.getUserName());//创建人名
			societyWxMessageRec.setOwnerSchoolId(societyStudentView.getOwnerSchoolId());//学校id
			societyWxMessageRec.setOwnerSchoolName(societyStudentView.getOwnerSchoolName());//学校名
			societyWxMessageRec.setOwnerWxMessageId(voObj.getId());//消息模板id
			societyWxMessageRec.setMessageTitle(voObj.getMessageTitle());//消息模板标题
			societyWxMessageRec.setMessageText(voObj.getMessageText());//消息模板文本
			societyWxMessageRec.setStudentId(societyStudentView.getId());//学生id
			societyWxMessageRec.setStudentOpenId(societyStudentView.getWxOpenId());//微信openid
			societyWxMessageRec.setClickToUrl("");
			societyWxMessageRec.setIsRead("0");//是否已读
			societyWxMessageRec.setIsSend("0");//是否发送
			societyWxMessageRecMapper.insert(societyWxMessageRec);
		}
	}

	public void insertAppointRec(SocietyWxMessage voObj, User user, String ids) {
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		//1.遍历所有指定的学生id
		for(String id : list){
			SocietyStudent societyStudentView = societyStudentMapper.selectById(id);
			SocietyWxMessageRec societyWxMessageRec = new SocietyWxMessageRec();
			societyWxMessageRec.setId(Guid.guid());//主键
			societyWxMessageRec.setCreateTime(new Date());//创建时间
			societyWxMessageRec.setCreateUserId(user.getUserId());//创建人id
			societyWxMessageRec.setCreateUserName(user.getUserName());//创建人名
			societyWxMessageRec.setOwnerSchoolId(societyStudentView.getOwnerSchoolId());//学校id
			societyWxMessageRec.setOwnerSchoolName(societyStudentView.getOwnerSchoolName());//学校名
			societyWxMessageRec.setOwnerWxMessageId(voObj.getId());//消息模板id
			societyWxMessageRec.setMessageTitle(voObj.getMessageTitle());//消息模板标题
			societyWxMessageRec.setMessageText(voObj.getMessageText());//消息模板文本
			societyWxMessageRec.setStudentId(societyStudentView.getId());//学生id
			societyWxMessageRec.setStudentOpenId(societyStudentView.getWxOpenId());//微信openid
			societyWxMessageRec.setClickToUrl("");
			societyWxMessageRec.setIsRead("0");//是否已读
			societyWxMessageRec.setIsSend("0");//是否发送
			societyWxMessageRecMapper.insert(societyWxMessageRec);
		}
	}



	/**
	 * 将所有的学生查出来发送所有消息
	 * @param voObj
	 */
	public void insertAdminMessageRec(SocietyWxMessage voObj,User user) {
		//1.查出该学校下的所有学生
		List<SocietyStudentView> list = societyStudentMapper.selectAllList();
		//2.遍历插入到Rec表
		for(SocietyStudentView societyStudentView : list){
			SocietyWxMessageRec societyWxMessageRec = new SocietyWxMessageRec();
			societyWxMessageRec.setId(Guid.guid());//主键
			societyWxMessageRec.setCreateTime(new Date());//创建时间
			societyWxMessageRec.setCreateUserId(user.getUserId());//创建人id
			societyWxMessageRec.setCreateUserName(user.getUserName());//创建人名
			societyWxMessageRec.setOwnerSchoolId(societyStudentView.getOwnerSchoolId());//学校id
			societyWxMessageRec.setOwnerSchoolName(societyStudentView.getOwnerSchoolName());//学校名
			societyWxMessageRec.setOwnerWxMessageId(voObj.getId());//消息模板id
			societyWxMessageRec.setMessageTitle(voObj.getMessageTitle());//消息模板标题
			societyWxMessageRec.setMessageText(voObj.getMessageText());//消息模板文本
			societyWxMessageRec.setStudentId(societyStudentView.getId());//学生id
			societyWxMessageRec.setStudentOpenId(societyStudentView.getWxOpenId());//微信openid
			societyWxMessageRec.setClickToUrl("");
			societyWxMessageRec.setIsRead("0");//是否已读
			societyWxMessageRec.setIsSend("0");//是否发送
			societyWxMessageRecMapper.insert(societyWxMessageRec);
		}
	}


}
