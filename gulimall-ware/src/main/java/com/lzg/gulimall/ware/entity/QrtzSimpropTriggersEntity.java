package com.lzg.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
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
@TableName("qrtz_simprop_triggers")
public class QrtzSimpropTriggersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String schedName;
	/**
	 * 
	 */
	private String triggerName;
	/**
	 * 
	 */
	private String triggerGroup;
	/**
	 * 
	 */
	private String strProp1;
	/**
	 * 
	 */
	private String strProp2;
	/**
	 * 
	 */
	private String strProp3;
	/**
	 * 
	 */
	private Integer intProp1;
	/**
	 * 
	 */
	private Integer intProp2;
	/**
	 * 
	 */
	private Long longProp1;
	/**
	 * 
	 */
	private Long longProp2;
	/**
	 * 
	 */
	private BigDecimal decProp1;
	/**
	 * 
	 */
	private BigDecimal decProp2;
	/**
	 * 
	 */
	private String boolProp1;
	/**
	 * 
	 */
	private String boolProp2;

}
