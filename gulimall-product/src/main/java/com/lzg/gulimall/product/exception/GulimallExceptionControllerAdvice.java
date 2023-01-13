package com.lzg.gulimall.product.exception;


import com.lzg.gulimall.common.exception.BizCodeEnum;
import com.lzg.gulimall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: GulimallExceptionControllerAdevice
 * @Description: 全局异常处理类
 * @author: lzg
 * @date: 2023/1/3 14:05
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.lzg.gulimall.product.controller")
public class GulimallExceptionControllerAdvice {
    //如果能够精确匹配到该异常就会执行这个方法，否则执行下面的方法
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String,String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        //不再自己指定响应状态码和状态，而是封装一个枚举类
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("data",errorMap);
    }

    @ExceptionHandler(value = ProductException.class)
    public R handleProductException(ProductException e){
        return R.error(BizCodeEnum.Product_EXCEPTION.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }
}

