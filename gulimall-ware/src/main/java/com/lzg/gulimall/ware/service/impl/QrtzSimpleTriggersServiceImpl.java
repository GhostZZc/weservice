package com.lzg.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.QrtzSimpleTriggersDao;
import com.lzg.gulimall.ware.entity.QrtzSimpleTriggersEntity;
import com.lzg.gulimall.ware.service.QrtzSimpleTriggersService;


@Service("qrtzSimpleTriggersService")
public class QrtzSimpleTriggersServiceImpl extends ServiceImpl<QrtzSimpleTriggersDao, QrtzSimpleTriggersEntity> implements QrtzSimpleTriggersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QrtzSimpleTriggersEntity> page = this.page(
                new Query<QrtzSimpleTriggersEntity>().getPage(params),
                new QueryWrapper<QrtzSimpleTriggersEntity>()
        );

        return new PageUtils(page);
    }

}