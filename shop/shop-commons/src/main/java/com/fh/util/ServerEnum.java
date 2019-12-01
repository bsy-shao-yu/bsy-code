package com.fh.util;

/**
 * @author shangfeng
 * @Title: ServerEnum
 * @Package com.fh.commons.response
 * @Description: ${todo}
 * @date 2019/7/2  10:28
 */
public enum ServerEnum {
    SERVER_ERROR(6003,"HttpIO流异常"),
    SERVER_BUSYNESS(6002,"响应超时"),
    SERVER_CONNECT_ERROR(6001,"服务器连接超时异常"),
    SUCCESS(200,"操作成功"),
    PHONE_NULL(203,"手机号或者验证码不能为空"),
    PHONYZM_ERROR(204,"验证码错误"),
    PHONYZMNULL_ERROR(205,"验证码不存在！"),
    DEL_DEPT_SCUCCESS(201,"删除部门成功"),
    LOGIN_ISNULL(5000,"用户名或者密码为空"),
    USERNAME_NOTEXIST(5001,"用户名输入有误。"),
    PASSWORD_WRONG(5002,"密码输入错误，请检查"),
    LOGIN_SUCCESS(5003,"登陆成功"),
    LOGIN_EXPIRED(5004,"登录超时，请重新登陆"),
    SECRET_ERROR(5005,"传入的token值有误，不能通过签名验证"),
    TOKEN_TIMEOUT(5006,"登录失效，请重新登录"),
    TOKEN_ISNULL(5008,"获取到的Token值为空"),
    NO_MENU_RIGHT(6000,"没有权限访问该菜单，请联系管理员"),
    NOT_DATA(7001,"没有要导出的数据"),
    HTTP_URL_ISNULL(8002,"你传递的URL路径为空了"),
    SERVER_TIMEOUT(8004,"服务连接请求超时"),
    HTTP_ERROR(8003,"接口访问失败"),
    SERVER_STOP(8005,"服务连接不上"),
    SAFETY_ERROR(9000,"接口验签失败"),
    SAFETY_BAD(9001,"接口被非法攻击"),
    SAFETY_TIMEOUT(9002,"接口访问超时"),
    SAFETY_INVALID(9003,"签名值无效"),
    SAFETY_REPLAY_ATTACK(9004,"接口被重放攻击"),
    ERROR(500,"操作失败");

    private ServerEnum(int code ,String msg){
        this.code=code;
        this.msg=msg;
    }

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
