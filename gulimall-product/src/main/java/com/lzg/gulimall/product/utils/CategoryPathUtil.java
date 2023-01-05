package com.lzg.gulimall.product.utils;

import com.lzg.gulimall.product.vo.AttrGroupVo;
import com.lzg.gulimall.product.vo.CategoryVo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: CategoryPathUtil
 * @Description: 分类路径工具类
 * @author: lzg
 * @date: 2023/1/5 13:57
 */
public class CategoryPathUtil {

    /**
     * 此方法用于找属性分组的完整分类路径
     * @param list
     * @param catelogId
     * @return
     */
    public static List<Long> getCatePath(List<CategoryVo> list, Long catelogId){
        ArrayList<Long> path = new ArrayList<>();
        for (CategoryVo categoryVo : list) {
            if(categoryVo.getCatId().equals(catelogId)){
                simpleMethod(path,list,categoryVo);
            }
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * 简化代码
     * @param path
     * @param list
     * @param categoryVo
     */
    private static void simpleMethod(ArrayList<Long> path, List<CategoryVo> list, CategoryVo categoryVo){
        path.add(categoryVo.getCatId());
        findPPath(path,list,categoryVo.getParentCid());
    }

    private static void findPPath(ArrayList<Long> path, List<CategoryVo> list, Long pid){
        for (CategoryVo categoryVo : list) {
            if (categoryVo.getCatId().equals(pid)){
                simpleMethod(path,list,categoryVo);
            }
        }
    }

}
