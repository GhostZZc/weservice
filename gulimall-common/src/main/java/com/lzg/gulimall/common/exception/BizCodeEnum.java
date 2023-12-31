package com.lzg.gulimall.common.exception;

/**
 * @ClassName: BizCodeEnum
 * @Description: 异常枚举
 * @author: lzg
 * @date: 2023/1/3 14:08
 */
public enum BizCodeEnum {
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    Product_EXCEPTION(10004,"商品服务异常"),
    SMS_CODE_EXCEPTION(10002,"发送验证码太频繁,请稍后再试"),
    TOO_MANY_REQUEST(10003,"请求流量过大"),
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),
    USER_EXIST_EXCEPTION(15001,"用户已存在"),
    PHONE_EXIST_EXCEPTION(15002,"手机号码已存在"),
    PHONE_NULL_EXCEPTION(15003,"未输入手机号码"),
    NO_STOCK_EXCEPTION(21000,"商品库存不足"),
    LOGINACCT_PASSWORD_INVAILD_EXCEPTION(15002,"账号或密码错误");

    private int code;
    private String msg;
    BizCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
