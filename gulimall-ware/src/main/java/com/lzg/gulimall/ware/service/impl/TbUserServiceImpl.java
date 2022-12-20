package com.lzg.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.TbUserDao;
import com.lzg.gulimall.ware.entity.TbUserEntity;
import com.lzg.gulimall.ware.service.TbUserService;


@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUserEntity> implements TbUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbUserEntity> page = this.page(
                new Query<TbUserEntity>().getPage(params),
                new QueryWrapper<TbUserEntity>()
        );

        return new PageUtils(page);
    }

}