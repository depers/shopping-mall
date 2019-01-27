package com.fmall.controller.backend;

import com.fmall.common.ServerResponse;
import com.fmall.service.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 冯晓 on 2017/6/24.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManagerController {


    @Autowired
    private ICategoryService iCategoryService;


    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId){
        // 全部通过拦截器验证是否登录以及权限
        return iCategoryService.add(categoryName, parentId);
    }


    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(Integer categoryId, String categoryName){
        // 全部通过拦截器验证是否登录以及权限
        //更新categoryName
        return iCategoryService.updateCategoryName(categoryId, categoryName);
    }


    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildParallelCategory(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId){
        // 全部通过拦截器验证是否登录以及权限
        //查询子节点的category信息
        return iCategoryService.getChildParallelCategory(categoryId);
    }


    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId){
        // 全部通过拦截器验证是否登录以及权限
        //查询当前节点的id和递归子节点的id
        return iCategoryService.selectCategoryAndChildrenById(categoryId);

    }


}
