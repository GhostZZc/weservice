package com.lzg.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.QrtzBlobTriggersDao;
import com.lzg.gulimall.ware.entity.QrtzBlobTriggersEntity;
import com.lzg.gulimall.ware.service.QrtzBlobTriggersService;


@Service("qrtzBlobTriggersService")
public class QrtzBlobTriggersServiceImpl extends ServiceImpl<QrtzBlobTriggersDao, QrtzBlobTriggersEntity> implements QrtzBlobTriggersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QrtzBlobTriggersEntity> page = this.page(
                new Query<QrtzBlobTriggersEntity>().getPage(params),
                new QueryWrapper<QrtzBlobTriggersEntity>()
        );

        return new PageUtils(page);
    }

}