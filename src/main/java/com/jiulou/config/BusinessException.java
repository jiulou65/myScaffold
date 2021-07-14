package com.jiulou.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends RuntimeException {

    private static final Logger loger = LoggerFactory.getLogger(BusinessException.class);

    private String code;
    private String msg;

    public BusinessException(String code, String msg) {
        super();
        loger.error("出现异常："+msg);
        this.code = code;
        this.msg = msg;
    }
    public BusinessException( String msg) {
        super();
        loger.error("出现异常："+msg);
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
