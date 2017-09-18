package com.fmall.vo;

import com.fmall.pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 冯晓 on 2017/9/18.
 */
public class OrderProductVo {

    private List<OrderItem> orderItemList;

    private BigDecimal productTotalPrice;

    private String imageHost;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
