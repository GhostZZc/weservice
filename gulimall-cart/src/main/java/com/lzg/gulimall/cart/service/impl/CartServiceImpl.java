package com.lzg.gulimall.cart.service.impl;

import com.lzg.gulimall.cart.service.CartService;
import com.lzg.gulimall.cart.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ClassName: CartServiceImpl
 * @Description:
 * @author: lzg
 * @date: 2023/8/18 14:26
 */
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String CART_PREFIX = "gulimall:cart:";

    @Override
    public CartItem addToCart(Long skuId, Integer num) {
        return null;
    }
}
