package com.fmall.service;

import com.fmall.common.ServerResponse;

/**
 * Created by 冯晓 on 2017/8/13.
 */
public interface ICartService {

    public ServerResponse add(Integer userId, Integer productId, Integer count);
}
