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

import com.lzg.gulimall.ware.entity.SysLogEntity;
import com.lzg.gulimall.ware.service.SysLogService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;



/**
 * 系统日志
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@RestController
@RequestMapping("ware/syslog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:syslog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ware:syslog:info")
    public R info(@PathVariable("id") Long id){
		SysLogEntity sysLog = sysLogService.getById(id);

        return R.ok().put("sysLog", sysLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:syslog:save")
    public R save(@RequestBody SysLogEntity sysLog){
		sysLogService.save(sysLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:syslog:update")
    public R update(@RequestBody SysLogEntity sysLog){
		sysLogService.updateById(sysLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:syslog:delete")
    public R delete(@RequestBody Long[] ids){
		sysLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
