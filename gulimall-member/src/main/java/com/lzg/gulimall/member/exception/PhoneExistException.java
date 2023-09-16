package com.lzg.gulimall.member.exception;

/**
 * @ClassName: PhoneExistException
 * @Description: 会员手机号存在异常
 * @author: lzg
 * @date: 2023/6/28 11:11
 */
public class PhoneExistException extends RuntimeException{

    public PhoneExistException() {
        super("手机号已经存在");
    }
}
