package com.edgedo.society.exception;

import com.edgedo.common.base.BusinessException;

/**
 * @author WangZhen
 * @description 学生没有绑定异常 根据这个异常返回 errType:notbind
 * @date 2020/5/4
 */
public class StudentNotBindException extends BusinessException {



    public StudentNotBindException() {
        super();
        this.setErrCode("notBind");
    }

    public StudentNotBindException(String errmsg) {
        super(errmsg);
        this.setErrCode("notBind");
    }

    public StudentNotBindException(String message, Throwable cause) {
        super(message, cause);
        this.setErrCode("notBind");
    }

    public StudentNotBindException(Throwable cause) {
        super(cause);
        this.setErrCode("notBind");
    }

}
