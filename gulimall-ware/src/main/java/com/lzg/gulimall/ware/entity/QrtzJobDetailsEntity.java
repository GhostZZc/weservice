package com.lzg.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;


import lombok.Data;

/**
 * 
 * 
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@Data
@TableName("qrtz_job_details")
public class QrtzJobDetailsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String schedName;
	/**
	 * 
	 */
	private String jobName;
	/**
	 * 
	 */
	private String jobGroup;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private String jobClassName;
	/**
	 * 
	 */
	private String isDurable;
	/**
	 * 
	 */
	private String isNonconcurrent;
	/**
	 * 
	 */
	private String isUpdateData;
	/**
	 * 
	 */
	private String requestsRecovery;
	/**
	 * 
	 */
	private Blob jobData;

}
