package com.lzg.gulimall.product.vo;

import com.lzg.gulimall.product.entity.AttrGroupEntity;
import com.lzg.gulimall.product.entity.AttrEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: AttrGroupAttrVo
 * @Description: 分组&属性Vo
 * @author: lzg
 * @date: 2023/1/13 10:32
 */
@Data

public class AttrGroupAttrVo extends AttrGroupEntity {


    private List<AttrEntity> attrs;
}
