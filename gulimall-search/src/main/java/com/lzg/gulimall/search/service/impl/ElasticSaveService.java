package com.lzg.gulimall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.lzg.gulimall.common.to.es.SkuEsModel;
import com.lzg.gulimall.search.GulimallSearchApplication;
import com.lzg.gulimall.search.config.GulimallElasticSearchConfig;
import com.lzg.gulimall.search.service.IElasticSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: ElasticSaveService
 * @Description: 商品保存elastic服务类
 * @author: lzg
 * @date: 2023/2/28 11:05
 */
@Service
@Slf4j
public class ElasticSaveService implements IElasticSaveService {


    @Autowired
    private RestHighLevelClient client;

    /**
     * 保存商品信息到elastic
     * @param skuEsModels
     * @return
     * @throws IOException
     */
    @Override
    public Boolean saveProductToElastic(List<SkuEsModel> skuEsModels) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            IndexRequest indexRequest = new IndexRequest();
            indexRequest.id(skuEsModel.getSkuId().toString());
            String s = JSON.toJSONString(skuEsModel);
            indexRequest.source(s);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulk = client.bulk(bulkRequest, GulimallElasticSearchConfig.COMMON_OPTIONS);
        return bulk.hasFailures();
    }
}
