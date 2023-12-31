package com.lzg.gulimall.ware.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.WareInfoDao;
import com.lzg.gulimall.ware.entity.WareInfoEntity;
import com.lzg.gulimall.ware.service.WareInfoService;
import org.springframework.util.StringUtils;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfoEntity> qw = new QueryWrapper<>();
        Object wareId =  params.get("wareId");
        Object skuId = params.get("skuId");
        if(!Objects.isNull(wareId)){
            qw.eq("wareId",wareId);
        }
        if(!Objects.isNull(skuId)){
            qw.eq("skuId",skuId);
        }
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                qw
        );
        return new PageUtils(page);
    }

}
