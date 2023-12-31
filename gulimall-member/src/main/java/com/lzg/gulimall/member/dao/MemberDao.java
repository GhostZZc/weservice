package com.lzg.gulimall.member.dao;

import com.lzg.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 11:14:40
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
