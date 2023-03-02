package com.lzg.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.product.entity.SpuInfoEntity;
import com.lzg.gulimall.product.vo.spvsavevo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
public interface ISpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuVo(SpuSaveVo spuSaveVo);
}

