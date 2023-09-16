package com.zlg.gulimall.third.party.controller;

import com.lzg.gulimall.common.utils.R;
import com.zlg.gulimall.third.party.component.SmsComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SmsController
 * @Description:
 * @author: lzg
 * @date: 2023/6/13 17:05
 */
@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsController {

    @Autowired
    private SmsComponent smsComponent;

    @PostMapping("/send/code")
    public R sendCode(@RequestParam("phone")String phone,@RequestParam("code")String code){
        log.info(code);
        smsComponent.sendCode(phone,code);
        return R.ok();
    }






}
