package com.edgedo.society.service;
		
import java.util.*;

import com.edgedo.common.base.BusinessException;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.IocUtil;
import com.edgedo.pay.PayOperatorService;
import com.edgedo.society.entity.SysWxPayRecord;
import com.edgedo.society.mapper.SysWxPayRecordMapper;
import com.edgedo.society.queryvo.SysWxPayRecordQuery;
import com.edgedo.society.queryvo.SysWxPayRecordView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SysWxPayRecordService {
	
	
	@Autowired
	private SysWxPayRecordMapper sysWxPayRecordMapper;


	public List<SysWxPayRecordView> listPage(SysWxPayRecordQuery sysWxPayRecordQuery){
		List list = sysWxPayRecordMapper.listPage(sysWxPayRecordQuery);
		sysWxPayRecordQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SysWxPayRecord sysWxPayRecord) {
		sysWxPayRecord.setId(Guid.guid());
		sysWxPayRecordMapper.insert(sysWxPayRecord);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SysWxPayRecord sysWxPayRecord) {
		sysWxPayRecordMapper.updateById(sysWxPayRecord);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SysWxPayRecord sysWxPayRecord) {
		sysWxPayRecordMapper.updateAllColumnById(sysWxPayRecord);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return sysWxPayRecordMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return sysWxPayRecordMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SysWxPayRecord loadById(String id) {
		return sysWxPayRecordMapper.selectById(id);
	}

	/**
	 * 修改预支付记录为已处理，需判断预支付记录状态
	 * @param record
	 * @return
	 */
	public int updateSuccess(SysWxPayRecord record) {
		return sysWxPayRecordMapper.updateSuccess(record);
	}

	/**
	 * 收到支付成功回调的通知
	 * @param payRec
	 * @return
	 */
	public String updateWxPayCallBack(SysWxPayRecord payRec) {

		//BILLType是全路径类名
		String billType = payRec.getBillType();
		try {
			System.out.print("===============updateWxPayCallBack:2");
			Object operator = IocUtil.getBean(Class.forName(billType));
			if(operator==null){
				return "未找到处理方式";
			}else{
				SysWxPayRecord record = loadById(payRec.getId());
				//已支付的不能重复更新
				record.setPayResult("SUCCESS");
				record.setPayTime(new Date());
				record.setPayMoney(payRec.getPayMoney());
				record.setTransactionId(payRec.getTransactionId());
				record.setDeviceInfo(payRec.getDeviceInfo());
				record.setBankType(payRec.getBankType());
				record.setTimeEnd(payRec.getTimeEnd());
				record.setOpenid(payRec.getOpenid());
				record.setNotifyBody(payRec.getNotifyBody());
				int count = updateSuccess(record);
				if(count>0){
					PayOperatorService operatorService = (PayOperatorService)operator;
					operatorService.updatePaySuccess(record);
				}
			}
			return null;
		} catch (ClassNotFoundException e) {
			return "未找到处理方式";
		} catch (BusinessException e) {
			return e.getMessage();
		}

	}


/**
	 * 将参数签名
	 * @param param  要求这个param里面有appId这个key
	 *//*
	private static void  signParam(SortedMap<String,String> param){
		String appId = param.get("appId");
		//根据appid获取token
		String token = getAccessToken(appId);

		String sign = createSign1("UTF-8", param,token);
		param.put("sign",sign);
	}*/

/*

	*/
/**characterEncoding
	 * @param characterEncoding 编码格式
	 * @param parameters 请求参数
	 * @param payKey 支付秘钥
	 * @return
	 *//*

	public static String createSign1(String characterEncoding,
									 SortedMap<String,String> parameters,String payKey){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v)
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + payKey);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}
*/

}
