package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Category;

import java.util.List;

/**
 * Created by 冯晓 on 2017/6/24.
 */
public interface ICategoryService {

    ServerResponse add(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildParallelCategory(Integer categoryId);

    ServerResponse selectCategoryAndChildrenById(Integer categoryId);
}
