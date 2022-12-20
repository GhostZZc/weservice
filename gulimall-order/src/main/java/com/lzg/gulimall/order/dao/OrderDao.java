package com.lzg.gulimall.order.dao;

import com.lzg.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 11:50:33
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
