package com.lzg.gulimall.member.feign;

import com.lzg.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gulimall-coupon")
public interface BaseService {

    @RequestMapping("/coupon/couponspurelation/get")
    public R getIt();


    @RequestMapping("/coupon/coupon/add")
    public R addOne();

    @RequestMapping("/coupon/coupon")
    R getParam();

}
