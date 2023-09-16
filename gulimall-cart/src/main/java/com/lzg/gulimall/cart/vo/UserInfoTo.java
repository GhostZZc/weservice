package com.lzg.gulimall.cart.vo;

import lombok.Data;

/**
 * @ClassName: UserInfoTo
 * @Description:
 * @author: lzg
 * @date: 2023/8/18 14:38
 */
@Data
public class UserInfoTo {
    private Long userId;
    private String userKey;
    private Boolean tempUser =false; //标识位



}
