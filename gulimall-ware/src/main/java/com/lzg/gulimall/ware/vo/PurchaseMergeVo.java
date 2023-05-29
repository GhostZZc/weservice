package com.lzg.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: PurchaseMergeVo
 * @Description: 合并采购需求Vo
 * @author: lzg
 * @date: 2023/5/24 15:33
 */
@Data
public class PurchaseMergeVo {

    /**
     * 采购订单Id
     */
    private Long purchaseId;

    /**
     * 需要合并的采购详情id
     */
    private List<Long> item;





}
