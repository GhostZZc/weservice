package com.lzg.gulimall.member.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: MemberLoginVo
 * @Description: 会员登录Vo
 * @author: lzg
 * @date: 2023/6/29 11:08
 */
@Data
public class MemberLoginVo implements Serializable {

    /**
     * 登录账户: 用户名或手机号
     */
    private String loginacct;

    /**
     * 密码
     */
    private String password;
}
