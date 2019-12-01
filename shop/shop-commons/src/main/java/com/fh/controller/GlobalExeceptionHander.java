package com.fh.controller;

import com.fh.exeception.AuthorizationExeception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExeceptionHander {


    @ExceptionHandler(AuthorizationExeception.class)
    public void authenticateException(AuthorizationExeception e, HttpServletRequest request, HttpServletResponse response ){
        response.setHeader("NOLOGIN",e.getcode().toString());

    }

    @ExceptionHandler(Exception.class)
    public void execeptionHandler(Exception e, HttpServletResponse response,HttpServletRequest request){

        response.setHeader("EXCEPTION",e.getMessage());

    }


}
