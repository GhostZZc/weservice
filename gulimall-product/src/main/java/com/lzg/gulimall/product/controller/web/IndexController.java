package com.lzg.gulimall.product.controller.web;

import com.lzg.gulimall.product.entity.CategoryEntity;
import com.lzg.gulimall.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName: IndexController
 * @Description: 首页
 * @author: lzg
 * @date: 2023/2/17 10:34
 */
@Controller
public class IndexController {

    @Autowired
    PmsCategoryService categoryService;

    @GetMapping({"/","/index.html"})
    public String indexPage(Model model){
        // TODO 1、查找所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        // 视图解析器进行拼串
        // 前缀："classpath:/templates/"  后缀：".html"
        model.addAttribute("categorys", categoryEntities);
        return "index";
    }
}

