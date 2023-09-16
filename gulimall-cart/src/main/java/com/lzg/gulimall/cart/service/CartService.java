package com.lzg.gulimall.cart.service;

import com.lzg.gulimall.cart.vo.CartItem;

/**
 * @ClassName: CartService
 * @Description:
 * @author: lzg
 * @date: 2023/8/18 14:25
 */
public interface CartService {

    CartItem addToCart(Long skuId,Integer num);
}
