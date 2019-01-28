package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.vo.OrderVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by 冯晓 on 2017/9/5.
 */
public interface IOrderService {

    // portal
    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse queryOrderStatus(Integer userId, Long orderNo);

    ServerResponse createOrder(Integer shippingId, Integer userId);

    ServerResponse<String> cancel(Integer userId, Long orderNo);

    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId, Integer pageNum, Integer pageSize);

    // backed
    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    ServerResponse<OrderVo> manageDetail(Long orderNo);

    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> manageSendGoods(Long oderNo);

    // hour个小时以内未付过款的订单，进行关闭
    void closeOrder(int hour);

}

