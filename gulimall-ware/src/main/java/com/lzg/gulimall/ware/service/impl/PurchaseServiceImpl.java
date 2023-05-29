package com.lzg.gulimall.ware.service.impl;

import com.lzg.gulimall.common.exception.RRException;
import com.lzg.gulimall.ware.constant.WareConstant;
import com.lzg.gulimall.ware.dao.WareSkuDao;
import com.lzg.gulimall.ware.entity.PurchaseDetailEntity;
import com.lzg.gulimall.ware.service.PurchaseDetailService;
import com.lzg.gulimall.ware.service.WareSkuService;
import com.lzg.gulimall.ware.vo.PurchaseDoneVo;
import com.lzg.gulimall.ware.vo.PurchaseItemVo;
import com.lzg.gulimall.ware.vo.PurchaseMergeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.PurchaseDao;
import com.lzg.gulimall.ware.entity.PurchaseEntity;
import com.lzg.gulimall.ware.service.PurchaseService;
import org.springframework.util.CollectionUtils;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {
    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Autowired
    private WareSkuService wareSkuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryUnReceiveList(Map<String, Object> params) {
        QueryWrapper<PurchaseEntity> qw = new QueryWrapper<>();
        qw.eq("status", WareConstant.PurchaseEnum.ALLOCATED.getCode()).or().eq("status",WareConstant.PurchaseEnum.NEW.getCode());
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                qw
        );
        return new PageUtils(page);
    }

    @Override
    public void purchaseMerge(PurchaseMergeVo purchaseMergeVo) {
        //无purchaseId,就新建
        Long purchaseId = purchaseMergeVo.getPurchaseId();
        Long FinalPurchaseId = purchaseId;
        if(Objects.isNull(purchaseId)){
            PurchaseEntity purchaseEntity = new PurchaseEntity();
            purchaseEntity.setStatus(WareConstant.PurchaseEnum.NEW.getCode());
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            this.save(purchaseEntity);
            FinalPurchaseId = purchaseEntity.getId();
        }
        List<Long> item = purchaseMergeVo.getItem();
        Long finalFinalPurchaseId = FinalPurchaseId;
        List<PurchaseDetailEntity> collect = item.stream()
                .filter(it->{
                    PurchaseDetailEntity entity = purchaseDetailService.getById(it);
                    if(WareConstant.PurchaseEnum.NEW.getCode().equals(entity.getStatus())
                            ||WareConstant.PurchaseEnum.ALLOCATED.getCode().equals(entity.getStatus())){
                        return true;
                    }else{
                        return false;
                    }
                })
                .map(it -> {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            detailEntity.setId(it);
            detailEntity.setPurchaseId(finalFinalPurchaseId);
            detailEntity.setStatus(WareConstant.PurchaseDetailEnum.ALLOCATED.getCode());
            return detailEntity;
        }).collect(Collectors.toList());
        purchaseDetailService.updateBatchById(collect);
    }

    @Override
    public void receivedPurchaseOrder(List<Long> item) {
        //领取采购单只能领取状态为新建和已分配的采购单(即状态为未领取前的)
        List<PurchaseEntity> purchaseEntities = this.listByIds(item);
        List<PurchaseEntity> collect = purchaseEntities.stream().filter(it -> {
            Integer status = it.getStatus();
            if (WareConstant.PurchaseEnum.NEW.getCode().equals(status) || WareConstant.PurchaseEnum.ALLOCATED.getCode().equals(status)) {
                return true;
            } else {
                return false;
            }
        }).map(it -> {
            it.setStatus(WareConstant.PurchaseEnum.RECEIVED.getCode());
            return it;
        }).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            if(!this.updateBatchById(collect)){
                throw new RRException("领取采购单失败(修改采购单状态失败)");
            }
        }
        //修改采购需求的状态
        List<Long> resultList = collect.stream().map(PurchaseEntity::getId).collect(Collectors.toList());
        List<PurchaseDetailEntity> detailList = purchaseDetailService.getListByPurchaseIds(resultList);
        List<PurchaseDetailEntity> resultDetailList = detailList.stream().map(it -> {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            detailEntity.setId(it.getId());
            detailEntity.setStatus(WareConstant.PurchaseDetailEnum.BUYING.getCode());
            return detailEntity;
        }).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(resultDetailList)){
            if (!purchaseDetailService.updateBatchById(resultDetailList)){
                throw new RRException("领取采购单失败(修改采购需求状态失败)");
            }
        }

    }

    @Override
    public void purchaseDone(PurchaseDoneVo purchaseDoneVo) {
        //更新
        boolean flag = true;
        //如果失败了,本次采购就算失败
        for (PurchaseItemVo purchaseItemVo : purchaseDoneVo.getPurchaseItemVoList()) {
            if(Objects.isNull(purchaseItemVo)||WareConstant.PurchaseDetailEnum.HASERROR.getCode().equals(purchaseItemVo.getStatus())){
                flag=false;
            }else{
                PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
                detailEntity.setId(purchaseItemVo.getItemId());
                List<PurchaseDetailEntity> list = purchaseDetailService.list(new QueryWrapper<PurchaseDetailEntity>(detailEntity));
                PurchaseDetailEntity entity = list.get(0);
                //修改库存信息
                if (Objects.isNull(entity)){
                    throw new RRException(purchaseItemVo.getItemId()+"的采购项不存在,此采购单出现问题");
                }
                wareSkuService.updateWareStock(entity.getSkuId(), entity.getWareId(),entity.getSkuNum());
            }
            PurchaseDetailEntity updateDetailEntity = new PurchaseDetailEntity();
            updateDetailEntity.setId(purchaseItemVo.getItemId());
            updateDetailEntity.setStatus(purchaseItemVo.getStatus());
            purchaseDetailService.updateById(updateDetailEntity);
        }
        //修改采购单的状态
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseDoneVo.getId());
        purchaseEntity.setStatus(flag?WareConstant.PurchaseEnum.COMPLETED.getCode() : WareConstant.PurchaseEnum.HASERROR.getCode());
        this.updateById(purchaseEntity);
    }

}
