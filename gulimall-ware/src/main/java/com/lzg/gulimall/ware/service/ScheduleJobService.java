package com.lzg.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.ware.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

