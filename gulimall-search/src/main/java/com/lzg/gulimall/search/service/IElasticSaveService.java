package com.lzg.gulimall.search.service;

import com.lzg.gulimall.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface IElasticSaveService {


    Boolean saveProductToElastic(List<SkuEsModel> skuEsModels) throws IOException;

}
