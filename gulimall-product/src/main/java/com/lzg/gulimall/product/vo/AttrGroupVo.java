package com.lzg.gulimall.product.vo;

import com.lzg.gulimall.product.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: AttrgroupVo
 * @Description: 属性分组视图模型
 * @author: lzg
 * @date: 2023/1/4 18:17
 */
@Data
public class AttrGroupVo extends AttrGroupEntity {

    private List<Long> catelogPath;
}
