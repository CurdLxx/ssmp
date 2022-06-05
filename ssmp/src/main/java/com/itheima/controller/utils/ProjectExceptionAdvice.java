package com.itheima.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception exception){
        exception.printStackTrace();
        return new Result("服务器故障，请稍后再试~");
    }
}
