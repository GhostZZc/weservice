package com.lzg.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.QrtzSchedulerStateDao;
import com.lzg.gulimall.ware.entity.QrtzSchedulerStateEntity;
import com.lzg.gulimall.ware.service.QrtzSchedulerStateService;


@Service("qrtzSchedulerStateService")
public class QrtzSchedulerStateServiceImpl extends ServiceImpl<QrtzSchedulerStateDao, QrtzSchedulerStateEntity> implements QrtzSchedulerStateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QrtzSchedulerStateEntity> page = this.page(
                new Query<QrtzSchedulerStateEntity>().getPage(params),
                new QueryWrapper<QrtzSchedulerStateEntity>()
        );

        return new PageUtils(page);
    }

}