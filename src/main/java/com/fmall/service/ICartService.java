package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.vo.CartVo;

/**
 * Created by 冯晓 on 2017/8/13.
 */
public interface ICartService {

    ServerResponse add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
