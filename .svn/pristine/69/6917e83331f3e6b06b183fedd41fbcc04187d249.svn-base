package com.edgedo.pay.wxpay.comunicate.service;

import com.edgedo.pay.wxpay.comunicate.msg.MsgReceiverFactory;
import com.edgedo.pay.wxpay.comunicate.msg.vo.BaseReceiveMsg;
import com.edgedo.pay.wxpay.util.WxCommonUtil;
import org.springframework.stereotype.Service;

@Service
public class ReceivedWxMsgService {

    /**根据接收的信息返回响应
     * @param xml
     * @return
     */
    public String processWechatMag(String originalId,String encryptType,String timestamp,String nonce,String msgSignature,String xml)throws Exception{
        /** 解析xml数据 */
        if("aes".equals(encryptType)){
            //如果是密文传输先解密xml
            String encrypt = WxCommonUtil.getEncrypt(xml);
            xml = WxCommonUtil.decryptMsg(originalId,timestamp,nonce,msgSignature,encrypt);
        }
        MsgReceiverFactory weixinMsgFactory = MsgReceiverFactory.getInstance();
        BaseReceiveMsg baseReceiveMsg = weixinMsgFactory.creator(xml, encryptType, timestamp, nonce);
        String result = "";
        if(baseReceiveMsg != null){
            result = baseReceiveMsg.dealMsg();
        }
        return result;
    }

}
