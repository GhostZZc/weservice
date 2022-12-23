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

import com.lzg.gulimall.ware.entity.QrtzSimpleTriggersEntity;
import com.lzg.gulimall.ware.service.QrtzSimpleTriggersService;
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
@RequestMapping("ware/qrtzsimpletriggers")
public class QrtzSimpleTriggersController {
    @Autowired
    private QrtzSimpleTriggersService qrtzSimpleTriggersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:qrtzsimpletriggers:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = qrtzSimpleTriggersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{schedName}")
    @RequiresPermissions("ware:qrtzsimpletriggers:info")
    public R info(@PathVariable("schedName") String schedName){
		QrtzSimpleTriggersEntity qrtzSimpleTriggers = qrtzSimpleTriggersService.getById(schedName);

        return R.ok().put("qrtzSimpleTriggers", qrtzSimpleTriggers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:qrtzsimpletriggers:save")
    public R save(@RequestBody QrtzSimpleTriggersEntity qrtzSimpleTriggers){
		qrtzSimpleTriggersService.save(qrtzSimpleTriggers);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:qrtzsimpletriggers:update")
    public R update(@RequestBody QrtzSimpleTriggersEntity qrtzSimpleTriggers){
		qrtzSimpleTriggersService.updateById(qrtzSimpleTriggers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:qrtzsimpletriggers:delete")
    public R delete(@RequestBody String[] schedNames){
		qrtzSimpleTriggersService.removeByIds(Arrays.asList(schedNames));

        return R.ok();
    }

}