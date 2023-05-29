package com.lzg.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.ware.entity.PurchaseEntity;
import com.lzg.gulimall.ware.vo.PurchaseDoneVo;
import com.lzg.gulimall.ware.vo.PurchaseMergeVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询未领取的采购单
     * @return
     */
    PageUtils queryUnReceiveList(Map<String, Object> params);

    /**
     * 合并采购需求
     * @param purchaseMergeVo
     */
    @Transactional(rollbackFor = Throwable.class)
    void purchaseMerge(PurchaseMergeVo purchaseMergeVo);


    /**
     * 领取采购单
     * @param item
     */
    @Transactional(rollbackFor = Throwable.class)
    void receivedPurchaseOrder(List<Long> item);

    /**
     * 完成采购单
     * @param purchaseDoneVo
     */
    @Transactional(rollbackFor = Throwable.class)
    void purchaseDone(PurchaseDoneVo purchaseDoneVo);

}

