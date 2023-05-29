package com.lzg.gulimall.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ThreadPoolConfigProperties
 * @Description: 线程池配置读取配置类
 * @author: lzg
 * @date: 2023/5/25 16:23
 */
@ConfigurationProperties(prefix = "gulimall.thread")
@Component
@Data
public class ThreadPoolConfigProperties {
    /**
     * 核心线程数
     */
    private Integer coreThreadSize;
    /**
     * 最大线程数
     */
    private Integer maxThreadSize;
    /**
     * 除核心线程数其他线程终止之前等待其他任务的最大时间
     */
    private Integer keepAliveTime;
}
