package com.lzg.gulimall.product.vo;

import com.lzg.gulimall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: AttrVo
 * @Description: 属性视图对象
 * @author: lzg
 * @date: 2023/1/5 14:05
 */
@Data
public class AttrVo extends AttrEntity {

    private Long attrGroupId;

    private String catelogName;

    private String groupName;

    private List<Long> catelogPath;
}
