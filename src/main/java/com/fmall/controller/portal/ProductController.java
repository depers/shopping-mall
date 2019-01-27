package com.fmall.controller.portal;

import com.fmall.common.ServerResponse;
import com.fmall.service.IProductService;
import com.fmall.vo.ProductDetailVo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 冯晓 on 2017/6/28.
 */
@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId){
        return iProductService.getProductDetail(productId);
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword", required = false)String keyword,
                                         @RequestParam(value = "categoryId", required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum", defaultValue = "1")int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                                         @RequestParam(value = "orderBy", defaultValue = "")String orderBy){
        return iProductService.getProductByKeywordCategory(keyword,categoryId, pageNum, pageSize, orderBy);
    }

    //http://www.happymall.com/product/手机/100012/1/10/price_asc
    @RequestMapping("/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(@PathVariable(value = "keyword")String keyword,
                                         @PathVariable(value = "categoryId")Integer categoryId,
                                         @PathVariable(value = "pageNum")Integer pageNum,
                                         @PathVariable(value = "pageSize")Integer pageSize,
                                         @PathVariable(value = "orderBy")String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageNum = 10;
        }
        if (StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory(keyword,categoryId, pageNum, pageSize, orderBy);
    }

    // Ambiguous handler methods mapped for HTTP path 'http://www.happymall.com/product/100012/1/10/price_asc'
    @RequestMapping("/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTfulBadcase(@PathVariable(value = "categoryId")Integer categoryId,
                                                @PathVariable(value = "pageNum")Integer pageNum,
                                                @PathVariable(value = "pageSize")Integer pageSize,
                                                @PathVariable(value = "orderBy")String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageNum = 10;
        }
        if (StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory("",categoryId, pageNum, pageSize, orderBy);
    }

    //Ambiguous handler methods mapped for HTTP path 'http://www.happymall.com/product/100012/1/10/price_asc'
    @RequestMapping("/{keyword}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTfulBadcase(@PathVariable(value = "keyword")String keyword,
                                                @PathVariable(value = "pageNum")Integer pageNum,
                                                @PathVariable(value = "pageSize")Integer pageSize,
                                                @PathVariable(value = "orderBy")String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageNum = 10;
        }
        if (StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory(keyword,null, pageNum, pageSize, orderBy);
    }

    //http://www.happymall.com/product/keyword/手机/1/10/price_asc
    @RequestMapping("/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(@PathVariable(value = "keyword")String keyword,
                                                       @PathVariable(value = "pageNum")Integer pageNum,
                                                       @PathVariable(value = "pageSize")Integer pageSize,
                                                       @PathVariable(value = "orderBy")String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageNum = 10;
        }
        if (StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory(keyword,null, pageNum, pageSize, orderBy);
    }

    //http://www.happymall.com/product/category/100012/1/10/price_asc
    @RequestMapping("/category/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(@PathVariable(value = "categoryId")Integer categoryId,
                                                       @PathVariable(value = "pageNum")Integer pageNum,
                                                       @PathVariable(value = "pageSize")Integer pageSize,
                                                       @PathVariable(value = "orderBy")String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageNum = 10;
        }
        if (StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory("",categoryId, pageNum, pageSize, orderBy);
    }

}
