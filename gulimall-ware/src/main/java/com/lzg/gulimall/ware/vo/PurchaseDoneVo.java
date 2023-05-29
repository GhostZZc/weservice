package com.lzg.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: PurchaseDoneVo
 * @Description: 采购单完成Vo
 * @author: lzg
 * @date: 2023/5/25 10:00
 */
@Data
public class PurchaseDoneVo {
    /**
     * 采购单id
     */
    private Long id;

    private List<PurchaseItemVo> purchaseItemVoList;
}
