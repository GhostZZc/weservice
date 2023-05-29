package com.lzg.gulimall.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: MyRedissionConfig
 * @Description: redission配置类
 * @author: lzg
 * @date: 2023/5/18 16:31
 */
public class MyRedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.110.85:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }



}
