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

import com.lzg.gulimall.ware.entity.QrtzFiredTriggersEntity;
import com.lzg.gulimall.ware.service.QrtzFiredTriggersService;
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
@RequestMapping("ware/qrtzfiredtriggers")
public class QrtzFiredTriggersController {
    @Autowired
    private QrtzFiredTriggersService qrtzFiredTriggersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:qrtzfiredtriggers:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = qrtzFiredTriggersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{schedName}")
    @RequiresPermissions("ware:qrtzfiredtriggers:info")
    public R info(@PathVariable("schedName") String schedName){
		QrtzFiredTriggersEntity qrtzFiredTriggers = qrtzFiredTriggersService.getById(schedName);

        return R.ok().put("qrtzFiredTriggers", qrtzFiredTriggers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:qrtzfiredtriggers:save")
    public R save(@RequestBody QrtzFiredTriggersEntity qrtzFiredTriggers){
		qrtzFiredTriggersService.save(qrtzFiredTriggers);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:qrtzfiredtriggers:update")
    public R update(@RequestBody QrtzFiredTriggersEntity qrtzFiredTriggers){
		qrtzFiredTriggersService.updateById(qrtzFiredTriggers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:qrtzfiredtriggers:delete")
    public R delete(@RequestBody String[] schedNames){
		qrtzFiredTriggersService.removeByIds(Arrays.asList(schedNames));

        return R.ok();
    }

}
