package com.lzg.gulimallauthserver;

import java.util.UUID;

/**
 * @ClassName: testMain
 * @Description:
 * @author: lzg
 * @date: 2023/6/29 14:26
 */
public class testMain {
    public static void main(String[] args) {
        String code = UUID.randomUUID().toString().substring(0,5);
        System.out.println(code);
    }
}
