package com.fh.exeception;

import com.fh.util.ServerEnum;

public class AuthorizationExeception extends RuntimeException {

    private Integer code ;


    public AuthorizationExeception(ServerEnum serverEnum){
        super(serverEnum.getMsg());
        this.code=serverEnum.getCode();
    }

    public Integer getcode(){
        return code;
    }


}
