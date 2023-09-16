package com.lzg.gulimallauthserver.feign;

import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimallauthserver.vo.RegisterVo;
import com.lzg.gulimallauthserver.vo.UserLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("gulimall-member")
public interface MemberFeignService {


    @PostMapping("/member/member/regist")
    R regist(@RequestBody RegisterVo vo);

    @PostMapping("/member/member/login")
    R login(@RequestBody UserLoginVo userLoginVo);
}
