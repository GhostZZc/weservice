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

import com.lzg.gulimall.ware.entity.QrtzLocksEntity;
import com.lzg.gulimall.ware.service.QrtzLocksService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;


/**
 * 
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@RestController
@RequestMapping("ware/qrtzlocks")
public class QrtzLocksController {
    @Autowired
    private QrtzLocksService qrtzLocksService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:qrtzlocks:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = qrtzLocksService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{schedName}")
    @RequiresPermissions("ware:qrtzlocks:info")
    public R info(@PathVariable("schedName") String schedName){
		QrtzLocksEntity qrtzLocks = qrtzLocksService.getById(schedName);

        return R.ok().put("qrtzLocks", qrtzLocks);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:qrtzlocks:save")
    public R save(@RequestBody QrtzLocksEntity qrtzLocks){
		qrtzLocksService.save(qrtzLocks);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:qrtzlocks:update")
    public R update(@RequestBody QrtzLocksEntity qrtzLocks){
		qrtzLocksService.updateById(qrtzLocks);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:qrtzlocks:delete")
    public R delete(@RequestBody String[] schedNames){
		qrtzLocksService.removeByIds(Arrays.asList(schedNames));

        return R.ok();
    }

}
