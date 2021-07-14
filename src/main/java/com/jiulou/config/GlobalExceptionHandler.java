package com.jiulou.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

// @ControllerAdvice(basePackages ="com.example.demo.controller")
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Map<String,Object> exceptionHandler(BusinessException e){
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("code",e.getCode());
        map.put("msg",e.getMsg());
        //其他业务代码...
        return map;
    }

}
