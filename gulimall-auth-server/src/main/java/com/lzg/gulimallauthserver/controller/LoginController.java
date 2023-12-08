package com.lzg.gulimallauthserver.controller;

import com.alibaba.fastjson.TypeReference;
import com.lzg.gulimall.common.constant.AuthServerConstant;
import com.lzg.gulimall.common.constant.SmsConstant;
import com.lzg.gulimall.common.exception.BizCodeEnum;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.common.vo.MemberRespVo;
import com.lzg.gulimallauthserver.feign.MemberFeignService;
import com.lzg.gulimallauthserver.feign.ThirdFeignService;
import com.lzg.gulimallauthserver.vo.RegisterVo;
import com.lzg.gulimallauthserver.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName: LoginController
 * @Description: 首页模块
 * @author: lzg
 * @date: 2023/6/9 10:46
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private ThirdFeignService thirdFeignService;

    @Resource
    private MemberFeignService memberFeignService;


    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @GetMapping("/sms/sendcode")
    @ResponseBody
    public R sendCode(@RequestParam("phone")String phone){
        //todo 接口防刷
        if(!StringUtils.hasText(phone)){
            return R.error("手机号不能为空");
        }
        String codeTime = redisTemplate.opsForValue().get(SmsConstant.SMS_CODE_CACHE_PREFIX + phone);
        if(StringUtils.hasText(codeTime)){
            long saveTime = Long.parseLong(codeTime.split("_")[1]);
            if(System.currentTimeMillis()-saveTime<60000){
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(),BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        String code = UUID.randomUUID().toString().substring(0,5);
        redisTemplate.opsForValue().set(SmsConstant.SMS_CODE_CACHE_PREFIX + phone,code+"_"+System.currentTimeMillis(),10, TimeUnit.MINUTES);
        thirdFeignService.sendCodeToDesk(phone,code);
        return R.ok();
    }



    @GetMapping("/login.html")
    public String loginPage(HttpSession session){
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if(Objects.nonNull(attribute)){
            return "redirect:http://gulimall.com";
        }else{
            return "login";
        }
    }


    @GetMapping("/reg.html")
    public String regPage(HttpServletRequest request){
        log.info("请求路径为:"+request.getRequestURI());
        return "reg";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterVo registerVo, BindingResult bingingResult, RedirectAttributes redirectAttributes){
        //校验出错,转发到注册页
        if(bingingResult.hasErrors()){
            Map<String, String> errors = bingingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://auth.gulimall.com/reg.html";
        }
        //验证码校验
        String code = registerVo.getCode();
        String s = redisTemplate.opsForValue().get(SmsConstant.SMS_CODE_CACHE_PREFIX + registerVo.getPhone());
        if(StringUtils.hasText(s)){
            if(code.equals(s.split("_")[0])){
                redisTemplate.delete(SmsConstant.SMS_CODE_CACHE_PREFIX + registerVo.getPhone());
                //调用注册服务
                R regist = memberFeignService.regist(registerVo);
                if (regist.getCode()==0){
                    return "redirect:http://auth.gulimall.com/login.html";
                }else{
                    Map<String,String> errors = new HashMap<>();
                    errors.put("msg", regist.getData2("msg",new TypeReference<String>(){}));
                    redirectAttributes.addFlashAttribute("errors",errors);
                    return "redirect:http://auth.gulimall.com/reg.html";
                }
            }else{
                //验证码错误
                Map<String,String> errors = new HashMap<>();
                errors.put("code","验证码错误");
                redirectAttributes.addFlashAttribute("errors",errors);
                return "redirect:http://auth.gulimall.com/reg.html";
            }
        }else{
            //验证码错误
            Map<String,String> errors = new HashMap<>();
            errors.put("code","验证码过期");
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://auth.gulimall.com/reg.html";
        }

    }

    @PostMapping("/login")
    public String login(UserLoginVo userLoginVo, RedirectAttributes redirectAttributes, HttpSession session){
        //远程服务调用
        R login = memberFeignService.login(userLoginVo);
        if(login.getCode()==0){
            MemberRespVo data = login.getData(new TypeReference<MemberRespVo>() {
            });
            session.setAttribute(AuthServerConstant.LOGIN_USER,data);
            return "redirect:http://gulimall.com";
        }else{
            Map<String,String> errors = new HashMap<>();
            errors.put("msg", login.getData2("msg",new TypeReference<String>(){}));
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://auth.gulimall.com/login.html";
        }

    }

}
