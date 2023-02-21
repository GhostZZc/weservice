package com.lzg.gulimall.product.controller.app;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.product.entity.PmsCommentReplayEntity;
import com.lzg.gulimall.product.service.PmsCommentReplayService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 商品评价回复关系
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
@RestController
@RequestMapping("product/commentreplay")
public class CommentReplayController {
    @Autowired
    private PmsCommentReplayService pmsCommentReplayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:pmscommentreplay:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsCommentReplayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:pmscommentreplay:info")
    public R info(@PathVariable("id") Long id){
		PmsCommentReplayEntity pmsCommentReplay = pmsCommentReplayService.getById(id);

        return R.ok().put("pmsCommentReplay", pmsCommentReplay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:pmscommentreplay:save")
    public R save(@RequestBody PmsCommentReplayEntity pmsCommentReplay){
		pmsCommentReplayService.save(pmsCommentReplay);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:pmscommentreplay:update")
    public R update(@RequestBody PmsCommentReplayEntity pmsCommentReplay){
		pmsCommentReplayService.updateById(pmsCommentReplay);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:pmscommentreplay:delete")
    public R delete(@RequestBody Long[] ids){
		pmsCommentReplayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
