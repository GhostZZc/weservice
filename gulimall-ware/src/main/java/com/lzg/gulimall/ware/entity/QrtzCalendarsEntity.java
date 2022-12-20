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
@TableName("qrtz_calendars")
public class QrtzCalendarsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String schedName;
	/**
	 * 
	 */
	private String calendarName;
	/**
	 * 
	 */
	private Blob calendar;

}
