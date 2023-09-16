package com.lzg.gulimall.member.exception;

/**
 * @ClassName: UserNameExistException
 * @Description: 会员名称存在异常
 * @author: lzg
 * @date: 2023/6/28 11:09
 */
public class UserNameExistException extends RuntimeException{
    public UserNameExistException() {
        super("用户名已经存在");
    }
}
