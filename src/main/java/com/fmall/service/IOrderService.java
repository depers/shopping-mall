package com.fmall.service;

import com.fmall.common.ServerResponse;

import java.util.Map;

/**
 * Created by 冯晓 on 2017/9/5.
 */
public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse queryOrderStatus(Integer userId, Long orderNo);
}
