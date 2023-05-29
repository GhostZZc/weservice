package com.lzg.gulimall.product.enums;

public enum RedisKey {
    CATEGORY_KEY_TREE("categorykey_tree"),
    CATEGORY_KEY_MAP("categorykey_map")
    ;


    private String key;

    RedisKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
