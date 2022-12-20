package com.lzg.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 定时任务日志
 * 
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@Data
@TableName("schedule_job_log")
public class ScheduleJobLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 任务日志id
	 */
	@TableId
	private Long logId;
	/**
	 * 任务id
	 */
	private Long jobId;
	/**
	 * spring bean名称
	 */
	private String beanName;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * 任务状态    0：成功    1：失败
	 */
	private Integer status;
	/**
	 * 失败信息
	 */
	private String error;
	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
