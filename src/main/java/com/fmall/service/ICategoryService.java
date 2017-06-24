package com.fmall.service;

import com.fmall.common.ServerResponse;

/**
 * Created by 冯晓 on 2017/6/24.
 */
public interface ICategoryService {

    ServerResponse add(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);
}
