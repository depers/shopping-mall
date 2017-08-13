package com.fmall.service.impl;

import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.dao.CartMapper;
import com.fmall.pojo.Cart;
import com.fmall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 冯晓 on 2017/8/13.
 */
public class ICartServiceImpl implements ICartService{

    @Autowired
    private CartMapper cartMapper;

    public ServerResponse add(Integer userId, Integer productId, Integer count){
        Cart cart = cartMapper.selectCartByUserIdProductId(userId,productId);
        if (cart == null){
            //这个产品不在这个购物车里，需要新增一个产品记录
            Cart cartItem = new Cart();
            cartItem.setQuantity(count);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setProductId(productId);
            cartItem.setUserId(userId);
            cartMapper.insert(cartItem);
        } else{
            // 如果产品已经在购物车，数量增加
            count  = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
    }
}
