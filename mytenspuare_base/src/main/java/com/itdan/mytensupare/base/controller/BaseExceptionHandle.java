package com.itdan.mytensupare.base.controller;

import entiy.Result;
import entiy.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 */

@RestControllerAdvice
public class BaseExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(false,StatusCode.ERROR,e.getMessage());
    }
}
