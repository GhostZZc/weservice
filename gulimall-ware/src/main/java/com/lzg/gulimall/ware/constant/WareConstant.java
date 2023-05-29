package com.lzg.gulimall.ware.constant;

/**
 * @ClassName: WareConstant
 * @Description: 库存常量
 * @author: lzg
 * @date: 2023/5/24 10:54
 */
public class WareConstant {
    public enum PurchaseEnum{
        NEW(0,"新建"),
        ALLOCATED(1,"已分配"),
        RECEIVED(2,"已领取"),
        COMPLETED(3,"已完成"),
        HASERROR(4,"有异常");

        private Integer code;
        private String value;

        PurchaseEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum PurchaseDetailEnum{
        NEW(0,"新建"),
        ALLOCATED(1,"已分配"),
        BUYING(2,"购买中"),
        COMPLETED(3,"已完成"),
        HASERROR(4,"采购失败");


        private Integer code;
        private String value;

        PurchaseDetailEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }


}
