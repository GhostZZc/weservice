package com.lzg.gulimall.coupon.controller;

import com.lzg.gulimall.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CouponController
 * @Description: Coupon控制器
 * @author: lzg
 * @date: 2022/12/16 10:08
 */
@RestController
@RefreshScope
@RequestMapping("coupon/coupon")
public class CouponController {

    @Value("${coupon.user.name}")
    private String name;

    @Value("${coupon.user.age}")
    private Integer age;

    @Value("${user.name}")
    private String username;


//    @Value("${user.password}")
//    private Integer password;

    @RequestMapping()
    public R read(){
        return R.ok().put("name",name).put("age",age).put("username",username);
    }




    @RequestMapping("/add")
    public R addOne(){
        return R.ok().put("msg","添加成功");
    }





}
