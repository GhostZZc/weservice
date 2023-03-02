package com.lzg.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;
import com.lzg.gulimall.product.dao.PmsCommentReplayDao;
import com.lzg.gulimall.product.entity.CommentReplayEntity;
import com.lzg.gulimall.product.service.ICommentReplayService;


@Service("pmsCommentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<PmsCommentReplayDao, CommentReplayEntity> implements ICommentReplayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentReplayEntity> page = this.page(
                new Query<CommentReplayEntity>().getPage(params),
                new QueryWrapper<CommentReplayEntity>()
        );

        return new PageUtils(page);
    }

}
