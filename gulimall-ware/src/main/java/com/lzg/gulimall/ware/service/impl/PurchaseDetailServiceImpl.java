package com.lzg.gulimall.ware.service.impl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.ParamUtil;
import com.lzg.gulimall.common.utils.Query;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.lzg.gulimall.ware.dao.PurchaseDetailDao;
import com.lzg.gulimall.ware.entity.PurchaseDetailEntity;
import com.lzg.gulimall.ware.service.PurchaseDetailService;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PurchaseDetailEntity> qw = new QueryWrapper<>();
        String key = ParamUtil.getParamToString(params, "key");
        String status = ParamUtil.getParamToString(params, "status");
        String wareId = ParamUtil.getParamToString(params, "wareId");

        if(Strings.isNotEmpty(key)){
            qw.eq("purchase_id",key).or().eq("sku_id",key);
        }
        if(Strings.isNotEmpty(status)){
            qw.eq("status",status);
        }
        if(Strings.isNotEmpty(wareId)){
            qw.eq("wareId",wareId);
        }

        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                qw
        );

        return new PageUtils(page);
    }

    @Override
    public List<PurchaseDetailEntity> getListByPurchaseIds(List<Long> purchaseIds) {
        QueryWrapper<PurchaseDetailEntity> qw = new QueryWrapper<>();
        qw.in("purchase_id",purchaseIds);
        return this.list(qw);
    }

}
