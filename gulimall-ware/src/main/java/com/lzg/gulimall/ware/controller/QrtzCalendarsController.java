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

import com.lzg.gulimall.ware.entity.QrtzCalendarsEntity;
import com.lzg.gulimall.ware.service.QrtzCalendarsService;
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
@RequestMapping("ware/qrtzcalendars")
public class QrtzCalendarsController {
    @Autowired
    private QrtzCalendarsService qrtzCalendarsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:qrtzcalendars:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = qrtzCalendarsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{schedName}")
    @RequiresPermissions("ware:qrtzcalendars:info")
    public R info(@PathVariable("schedName") String schedName){
		QrtzCalendarsEntity qrtzCalendars = qrtzCalendarsService.getById(schedName);

        return R.ok().put("qrtzCalendars", qrtzCalendars);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:qrtzcalendars:save")
    public R save(@RequestBody QrtzCalendarsEntity qrtzCalendars){
		qrtzCalendarsService.save(qrtzCalendars);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:qrtzcalendars:update")
    public R update(@RequestBody QrtzCalendarsEntity qrtzCalendars){
		qrtzCalendarsService.updateById(qrtzCalendars);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:qrtzcalendars:delete")
    public R delete(@RequestBody String[] schedNames){
		qrtzCalendarsService.removeByIds(Arrays.asList(schedNames));

        return R.ok();
    }

}
