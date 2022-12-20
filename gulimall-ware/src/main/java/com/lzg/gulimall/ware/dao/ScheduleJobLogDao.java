package com.lzg.gulimall.ware.dao;

import com.lzg.gulimall.ware.entity.ScheduleJobLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 * 
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {
	
}
