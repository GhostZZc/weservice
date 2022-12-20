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

import com.lzg.gulimall.ware.entity.SysOssEntity;
import com.lzg.gulimall.ware.service.SysOssService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;



/**
 * 文件上传
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@RestController
@RequestMapping("ware/sysoss")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:sysoss:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysOssService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ware:sysoss:info")
    public R info(@PathVariable("id") Long id){
		SysOssEntity sysOss = sysOssService.getById(id);

        return R.ok().put("sysOss", sysOss);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:sysoss:save")
    public R save(@RequestBody SysOssEntity sysOss){
		sysOssService.save(sysOss);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:sysoss:update")
    public R update(@RequestBody SysOssEntity sysOss){
		sysOssService.updateById(sysOss);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:sysoss:delete")
    public R delete(@RequestBody Long[] ids){
		sysOssService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
