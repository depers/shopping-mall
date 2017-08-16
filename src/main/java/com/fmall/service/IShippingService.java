package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Shipping;
import com.github.pagehelper.PageInfo;

/**
 * Created by 冯晓 on 2017/8/16.
 */
public interface IShippingService {

    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
