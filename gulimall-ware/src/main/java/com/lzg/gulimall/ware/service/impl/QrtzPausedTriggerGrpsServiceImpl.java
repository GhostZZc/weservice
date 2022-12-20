package com.lzg.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.QrtzPausedTriggerGrpsDao;
import com.lzg.gulimall.ware.entity.QrtzPausedTriggerGrpsEntity;
import com.lzg.gulimall.ware.service.QrtzPausedTriggerGrpsService;


@Service("qrtzPausedTriggerGrpsService")
public class QrtzPausedTriggerGrpsServiceImpl extends ServiceImpl<QrtzPausedTriggerGrpsDao, QrtzPausedTriggerGrpsEntity> implements QrtzPausedTriggerGrpsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QrtzPausedTriggerGrpsEntity> page = this.page(
                new Query<QrtzPausedTriggerGrpsEntity>().getPage(params),
                new QueryWrapper<QrtzPausedTriggerGrpsEntity>()
        );

        return new PageUtils(page);
    }

}