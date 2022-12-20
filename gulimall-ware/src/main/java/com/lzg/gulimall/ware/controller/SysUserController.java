package com.lzg.gulimall.ware.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lzg.gulimall.ware.entity.SysUserEntity;
import com.lzg.gulimall.ware.service.SysUserService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;



/**
 * 系统用户
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@RestController
@RequestMapping("ware/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:sysuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("ware:sysuser:info")
    public R info(@PathVariable("userId") Long userId){
		SysUserEntity sysUser = sysUserService.getById(userId);

        return R.ok().put("sysUser", sysUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:sysuser:save")
    public R save(@RequestBody SysUserEntity sysUser){
		sysUserService.save(sysUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:sysuser:update")
    public R update(@RequestBody SysUserEntity sysUser){
		sysUserService.updateById(sysUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:sysuser:delete")
    public R delete(@RequestBody Long[] userIds){
		sysUserService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
