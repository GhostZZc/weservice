package com.lzg.gulimall.product.exception;

/**
 * @ClassName: ProductException
 * @Description: 产品服务异常类
 * @author: lzg
 * @date: 2023/1/13 10:58
 */
public class ProductException extends RuntimeException{

    public ProductException(String message) {
        super(message);
    }
}
