package com.lzg.gulimall.product.utils;

public enum AttrType {
    BASE(1),
    SALE(0);

    private Integer type;

    AttrType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }



}
