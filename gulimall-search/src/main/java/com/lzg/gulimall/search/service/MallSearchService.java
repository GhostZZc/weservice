package com.lzg.gulimall.search.service;


import com.lzg.gulimall.search.vo.SearchParamVo;
import com.lzg.gulimall.search.vo.SearchResult;

public interface MallSearchService {
    /**
     * 检索结果
     * @param paramVo
     * @return
     */
    SearchResult search(SearchParamVo paramVo);
}
