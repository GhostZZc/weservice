package com.lzg.gulimallauthserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: MyWebConfig
 * @Description: 网页配置
 * @author: lzg
 * @date: 2023/5/10 11:28
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/reg.html").setViewName("reg");
//        registry.addViewController("/login.html").setViewName("login");
    }
}
