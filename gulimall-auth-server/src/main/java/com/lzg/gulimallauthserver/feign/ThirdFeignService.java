package com.lzg.gulimallauthserver.feign;

import com.lzg.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gulimall-third-party")
public interface ThirdFeignService {

    /**
     * 让code显示在桌面上
     * @param phone
     * @param code
     * @return
     */
    @PostMapping("/sms/send/code")
    R sendCodeToDesk(@RequestParam("phone")String phone,@RequestParam("code")String code);

}
