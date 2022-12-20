package com.lzg.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.QrtzLocksDao;
import com.lzg.gulimall.ware.entity.QrtzLocksEntity;
import com.lzg.gulimall.ware.service.QrtzLocksService;


@Service("qrtzLocksService")
public class QrtzLocksServiceImpl extends ServiceImpl<QrtzLocksDao, QrtzLocksEntity> implements QrtzLocksService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QrtzLocksEntity> page = this.page(
                new Query<QrtzLocksEntity>().getPage(params),
                new QueryWrapper<QrtzLocksEntity>()
        );

        return new PageUtils(page);
    }

}