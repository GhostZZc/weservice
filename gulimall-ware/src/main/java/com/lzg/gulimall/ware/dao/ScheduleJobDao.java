package com.lzg.gulimall.ware.dao;

import com.lzg.gulimall.ware.entity.ScheduleJobEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务
 * 
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@Mapper
public interface ScheduleJobDao extends BaseMapper<ScheduleJobEntity> {
	
}
