package com.lzg.gulimall.ware.vo;

import lombok.Data;

/**
 * @ClassName: PurchaseItemVo
 * @Description: 采购需求Vo
 * @author: lzg
 * @date: 2023/5/25 10:01
 */
@Data
public class PurchaseItemVo {
    /**
     * 采购项id
     */
    private Long itemId;
    /**
     * 采购项状态
     */
    private Integer status;
    /**
     * 采购成功或失败原因
     */
    private String reason;
}
