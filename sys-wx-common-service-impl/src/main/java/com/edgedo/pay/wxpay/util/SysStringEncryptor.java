package com.edgedo.pay.wxpay.util;


import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SysStringEncryptor implements StringEncryptor {

    public SysStringEncryptor() {
        this.pbeEncryptor = new StandardPBEStringEncryptor();
        this.pbeEncryptor.setPassword("chuheridangwuhandihexiatu");
    }

    @Override
    public String decrypt(String string) {
        String retStr = swapChar("1F8B" + string, 20);
        byte[] strBytes = CommonUtils.fromHexadecimal(retStr);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(strBytes);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPInputStream is = new GZIPInputStream(bis);
            byte[] buffer = new byte[500];
            int c = 0;
            while((c = is.read(buffer)) != -1) {
                bos.write(buffer, 0, c);
            }
            byte[] retBytes = bos.toByteArray();
            is.close();
            retStr = new String(retBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        retStr = swapChar(retStr, 0);
        return this.pbeEncryptor.decrypt(retStr);
    }

    @Override
    public String encrypt(String string) {
        String retStr = this.pbeEncryptor.encrypt(string);
        retStr = swapChar(retStr, 0);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] retBytes;
        try {
            GZIPOutputStream os = new GZIPOutputStream(bos);
            os.write(retStr.getBytes("UTF-8"));
            os.close();
            retBytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        retStr = CommonUtils.toHexadecimal(retBytes);
        retStr = swapChar(retStr, 20);
        return retStr.substring(4);
    }

    public String swapChar(String string, int start) {
        StringBuilder retStr = new StringBuilder();
        int strLength = string.length();
        for (int i = 0; i < strLength; i++) {
            char ch = string.charAt(i);
            if (i >= start && ++i < strLength) {
                retStr.append(string.charAt(i));
            }
            retStr.append(ch);
        }

        return retStr.toString();
    }


    private StandardPBEStringEncryptor pbeEncryptor;

    public static void main(String[] args){
        SysStringEncryptor enc = new SysStringEncryptor();
        String AppID = "wx99d0bb7c7b7e6347";
        String AppSecret = "e1706968f93f901f5cb157626d23dc6c";
        String enk = "XGJuuCHpPZXG4bZkaqhWAaRDyW0uXfsliwCVYp6nR1c";
        String token = "1tl1leOe85UA9IgXjFLlKZfefSkNFs";
        System.out.println("AppID==" + enc.encrypt(AppID));
        System.out.println("AppSecret==" + enc.encrypt(AppSecret));
        System.out.println("enk==" + enc.encrypt(enk));
        System.out.print("token=="+enc.encrypt(token));


    }


}

