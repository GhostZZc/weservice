package com.lzg.gulimall.search.controller;

import com.lzg.gulimall.search.service.MallSearchService;
import com.lzg.gulimall.search.vo.SearchParamVo;
import com.lzg.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

    @Autowired
    MallSearchService mallSearchService;


    @GetMapping(value = {"/search.html","/"})
    public String lisgPage(SearchParamVo paramVo, Model model, HttpServletRequest request){
        //根据页面传递的数据查询参数，去es中检索商品
        String queryString = request.getQueryString();
        paramVo.set_queryString(queryString);
        SearchResult reslut = mallSearchService.search(paramVo);
        model.addAttribute("result",reslut);


        return "list";
    }
}
