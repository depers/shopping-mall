package com.fmall.vo;

import java.math.BigDecimal;

/**
 * Created by 冯晓 on 2017/8/13.
 */
public class CartProductVo {

    //结合了产品和购物车的一个抽象对象

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity; // 购物车中的商品数量
    private String productName;
    private String productSubTitle;
    private String productMainImage;
    private BigDecimal productPrice;
    private BigDecimal productStatus;
}
