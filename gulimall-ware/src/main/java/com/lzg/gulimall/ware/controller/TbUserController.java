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

import com.lzg.gulimall.ware.entity.TbUserEntity;
import com.lzg.gulimall.ware.service.TbUserService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;



/**
 * 用户
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@RestController
@RequestMapping("ware/tbuser")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:tbuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tbUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("ware:tbuser:info")
    public R info(@PathVariable("userId") Long userId){
		TbUserEntity tbUser = tbUserService.getById(userId);

        return R.ok().put("tbUser", tbUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:tbuser:save")
    public R save(@RequestBody TbUserEntity tbUser){
		tbUserService.save(tbUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:tbuser:update")
    public R update(@RequestBody TbUserEntity tbUser){
		tbUserService.updateById(tbUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:tbuser:delete")
    public R delete(@RequestBody Long[] userIds){
		tbUserService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
