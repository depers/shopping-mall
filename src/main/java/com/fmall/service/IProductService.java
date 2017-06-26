package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Product;

/**
 * Created by 冯晓 on 2017/6/26.
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);
}
