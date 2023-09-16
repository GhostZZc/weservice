package com.lzg.gulimall.cart.controller;

import com.lzg.gulimall.cart.interceptor.CartInterceptor;
import com.lzg.gulimall.cart.vo.UserInfoTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: CartController
 * @Description:
 * @author: lzg
 * @date: 2023/9/16 10:46
 */
@Controller
public class CartController {


    @GetMapping("/cartList.html")
    public String listPage(){
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        return "cartList";
    }

    @GetMapping("/success.html")
    public String listSuccessPage(){
        return "success";
    }
}
