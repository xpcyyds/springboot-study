package com.cdcompany.controller;

import com.cdcompany.exception.BusinessException;
import com.cdcompany.exception.SystemException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    //1.系统异常处理
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        //记录日志
        //发消息给运维
        //发送邮件给开发人员
        return new Result(ex.getMessage(),ex.getCode());
    }

    //2.业务异常处理
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){
        return new Result(ex.getMessage(),ex.getCode());
    }

    //3.其他异常处理
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        System.out.println("嘿嘿，异常你哪里跑！");
        String msg = "TOKEN失效，请重新登录";
        return new Result(666,msg);
    }
}
