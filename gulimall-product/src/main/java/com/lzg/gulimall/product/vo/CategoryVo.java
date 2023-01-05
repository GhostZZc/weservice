package com.lzg.gulimall.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lzg.gulimall.product.entity.CategoryEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: CategoryVo
 * @Description: 分类数据模型
 * @author: lzg
 * @date: 2022/12/22 14:26
 */
@Data
@Accessors(chain = true)
public class CategoryVo extends CategoryEntity {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryVo> children;


}
