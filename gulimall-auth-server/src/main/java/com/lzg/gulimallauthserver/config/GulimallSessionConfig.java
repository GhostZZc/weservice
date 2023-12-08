package com.lzg.gulimallauthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class GulimallSessionConfig {
    /**
     * 子域问题共享解决
     */
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setDomainName("gulimall.com");
        cookieSerializer.setCookieName("GULIMALLSESSION");
        return cookieSerializer;
    }

    /**
     * 使用json序列化方式来序列化对象数据到redis中
     */
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
       return new GenericJackson2JsonRedisSerializer();
    }
}
