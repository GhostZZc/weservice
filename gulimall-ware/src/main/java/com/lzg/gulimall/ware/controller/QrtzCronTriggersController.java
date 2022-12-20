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

import com.lzg.gulimall.ware.entity.QrtzCronTriggersEntity;
import com.lzg.gulimall.ware.service.QrtzCronTriggersService;
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
@RequestMapping("ware/qrtzcrontriggers")
public class QrtzCronTriggersController {
    @Autowired
    private QrtzCronTriggersService qrtzCronTriggersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:qrtzcrontriggers:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = qrtzCronTriggersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{schedName}")
    @RequiresPermissions("ware:qrtzcrontriggers:info")
    public R info(@PathVariable("schedName") String schedName){
		QrtzCronTriggersEntity qrtzCronTriggers = qrtzCronTriggersService.getById(schedName);

        return R.ok().put("qrtzCronTriggers", qrtzCronTriggers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:qrtzcrontriggers:save")
    public R save(@RequestBody QrtzCronTriggersEntity qrtzCronTriggers){
		qrtzCronTriggersService.save(qrtzCronTriggers);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:qrtzcrontriggers:update")
    public R update(@RequestBody QrtzCronTriggersEntity qrtzCronTriggers){
		qrtzCronTriggersService.updateById(qrtzCronTriggers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:qrtzcrontriggers:delete")
    public R delete(@RequestBody String[] schedNames){
		qrtzCronTriggersService.removeByIds(Arrays.asList(schedNames));

        return R.ok();
    }

}
