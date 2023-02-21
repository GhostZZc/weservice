package com.lzg.gulimall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: GulimallElasticSearchConfig
 * @Description:
 * @author: lzg
 * @date: 2023/2/15 15:40
 */
@Configuration
public class GulimallElasticSearchConfig {
    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient esRestClient() {
        RestClientBuilder builder = null;
        // 可以指定多个es
        builder = RestClient.builder(new HttpHost("192.168.110.85",9200,"http"));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }
}

