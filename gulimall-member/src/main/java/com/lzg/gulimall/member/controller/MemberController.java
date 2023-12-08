package com.lzg.gulimall.member.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import com.lzg.gulimall.common.exception.BizCodeEnum;
import com.lzg.gulimall.common.utils.OrikaBeanMapper;
import com.lzg.gulimall.common.vo.MemberRespVo;
import com.lzg.gulimall.member.exception.PhoneExistException;
import com.lzg.gulimall.member.exception.UserNameExistException;
import com.lzg.gulimall.member.feign.BaseService;
import com.lzg.gulimall.member.vo.MemberLoginVo;
import com.lzg.gulimall.member.vo.MemberRegistVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lzg.gulimall.member.entity.MemberEntity;
import com.lzg.gulimall.member.service.MemberService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;



/**
 * 会员
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 11:14:40
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }

    @Autowired
    private BaseService baseService;

    @RequestMapping("/coupon")
    public R getCoupon(){
        return baseService.getIt();
    }

    @RequestMapping("")
    public R addOne(){
        return baseService.addOne();
    }



    @RequestMapping("/param")
    public R param(){
        return baseService.getParam();
    };


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("/regist")
    public R regist(@RequestBody MemberRegistVo memberRegistVo){
        try{
            memberService.regist(memberRegistVo);
        }catch (UserNameExistException e){
            return R.error(BizCodeEnum.USER_EXIST_EXCEPTION.getCode(),BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        }catch (PhoneExistException e){
            return R.error(BizCodeEnum.PHONE_NULL_EXCEPTION.getCode(),BizCodeEnum.PHONE_NULL_EXCEPTION.getMsg());
        }
        return R.ok();
    }

    @PostMapping("/login")
    public R login(@RequestBody MemberLoginVo memberLoginVo){
        MemberEntity login = memberService.login(memberLoginVo);
        if (Objects.nonNull(login)){
            return R.ok().setData(OrikaBeanMapper.copy(login, MemberRespVo.class));
        }else{
            return R.error(BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION.getCode()
                    ,BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION.getMsg());
        }
    }
}
