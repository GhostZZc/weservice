package com.lzg.gulimall.product.controller.web;

import com.lzg.gulimall.product.entity.CategoryEntity;
import com.lzg.gulimall.product.service.ICategoryService;
import com.lzg.gulimall.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndexController
 * @Description: 首页
 * @author: lzg
 * @date: 2023/2/17 10:34
 */
@Controller
public class IndexController {

    @Autowired
    ICategoryService categoryService;

    @GetMapping({"/","/index.html"})
    public String indexPage(Model model){
        // TODO 1、查找所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        // 视图解析器进行拼串
        // 前缀："classpath:/templates/"  后缀：".html"
        model.addAttribute("categorys", categoryEntities);
        return "index";
    }
    @ResponseBody
    @GetMapping("/index/json/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatelogJson() {
        Map<String, List<Catelog2Vo>> catelogJson = categoryService.getCatelogJson();
        return catelogJson;
    }



}

