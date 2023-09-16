package com.lzg.gulimall.cart.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: Cart
 * @Description:
 * @author: lzg
 * @date: 2023/8/18 14:07
 */
public class Cart {
    List<CartItem> cartItems;
    private Integer countNum; //商品的数量
    private Integer coutType; //商品的类型数量
    private BigDecimal totalAmount; //商品的总价格
    private BigDecimal reduce = new BigDecimal("0.00"); //减免价格

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getCountNum() {
        int count = 0;
        if(cartItems!=null&&cartItems.size()>0){
            for (CartItem cartItem : cartItems) {
                count+= cartItem.getCount();
            }
        }
        return count;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getCoutType() {
        int count = 0;
        if(cartItems!=null&&cartItems.size()>0){
            for (CartItem cartItem : cartItems) {
                count+=1;
            }
        }
        return count;
    }

    public void setCoutType(Integer coutType) {
        this.coutType = coutType;
    }

    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal("0");
        // 1.计算购物项总价
        if(cartItems!=null&&cartItems.size()>0){
            for (CartItem cartItem : cartItems) {
                BigDecimal totalPrice = cartItem.getTotalPrice();
                amount = amount.add(totalPrice);
            }
        }
        //2.减免价格
        BigDecimal subtract = amount.subtract(getReduce());
        return subtract;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}
