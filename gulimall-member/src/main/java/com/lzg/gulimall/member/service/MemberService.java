package com.lzg.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.member.entity.MemberEntity;
import com.lzg.gulimall.member.exception.PhoneExistException;
import com.lzg.gulimall.member.exception.UserNameExistException;
import com.lzg.gulimall.member.vo.MemberLoginVo;
import com.lzg.gulimall.member.vo.MemberRegistVo;

import java.util.Map;

/**
 * 会员
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 11:14:40
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void regist(MemberRegistVo memberRegistVo);

    MemberEntity login(MemberLoginVo memberLoginVo);

    void checkUserNameUnique(String userName) throws UserNameExistException;

    void checkPhoneUnique(String phone)throws PhoneExistException;
}

