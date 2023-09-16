package com.lzg.gulimall.member.service.impl;

import com.lzg.gulimall.member.entity.MemberLevelEntity;
import com.lzg.gulimall.member.exception.PhoneExistException;
import com.lzg.gulimall.member.exception.UserNameExistException;
import com.lzg.gulimall.member.service.MemberLevelService;
import com.lzg.gulimall.member.vo.MemberLoginVo;
import com.lzg.gulimall.member.vo.MemberRegistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.member.dao.MemberDao;
import com.lzg.gulimall.member.entity.MemberEntity;
import com.lzg.gulimall.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberLevelService memberLevelService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void regist(MemberRegistVo memberRegistVo) {
        MemberLevelEntity defaultLevel = memberLevelService
                .getOne(new QueryWrapper<MemberLevelEntity>().eq("default_status", 1));
        checkPhoneUnique(memberRegistVo.getPhone());
        checkUserNameUnique(memberRegistVo.getUserName());
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setLevelId(defaultLevel.getId());
        memberEntity.setUsername(memberRegistVo.getUserName());
        memberEntity.setMobile(memberRegistVo.getPhone());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(memberRegistVo.getPassword());
        memberEntity.setPassword(encode);
        memberEntity.setLevelId(defaultLevel.getId());
        this.save(memberEntity);
    }

    @Override
    public MemberEntity login(MemberLoginVo memberLoginVo) {
        MemberEntity one = this.getOne(new QueryWrapper<MemberEntity>()
                .eq("username", memberLoginVo.getLoginnacct()).or().eq("mobile", memberLoginVo.getLoginnacct()));
        if(Objects.isNull(one)){
            return null;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(memberLoginVo.getPassword(), one.getPassword());
        if (matches){
            return one;
        }
        return null;
    }

    @Override
    public void checkUserNameUnique(String userName) throws UserNameExistException {
        Long count = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("username", userName));
        if (count>0){
            throw new UserNameExistException();
        }
    }

    @Override
    public void checkPhoneUnique(String phone) throws PhoneExistException {
        Long count = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if (count>0){
            throw new PhoneExistException();
        }
    }

}
