package com.lzg.gulimallauthserver.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserLoginVo
 * @Description: 用户登录Vo
 * @author: lzg
 * @date: 2023/6/29 10:38
 */
@Data
public class UserLoginVo implements Serializable {
    /**
     * 登录账户:用户名或手机号
     */
    private String loginacct;
    /**
     * 密码
     */
    private String password;
}
