package com.fmall.controller.backend;


import com.fmall.common.ServerResponse;
import com.fmall.pojo.Product;

import com.fmall.service.IFileService;
import com.fmall.service.IProductService;

import com.fmall.util.PropertiesUtil;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 冯晓 on 2017/6/26.
 */

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(Product product){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iProductService.saveOrUpdateProduct(product);
    }


    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse SetSaleStatus(Integer productId, Integer status){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iProductService.setSaleStatus(productId, status);
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse GetDetail(Integer productId){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iProductService.manageProductDetail(productId);
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse GetList(
                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iProductService.getProductList(pageNum, pageSize);
    }


    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse getSearch(String productName, Integer productId,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iProductService.searchProduct(productName, productId, pageNum, pageSize);
    }


    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }


    @RequestMapping("richText_img_upload.do")
    @ResponseBody
    public Map richTextImgUpload(@RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        Map resultMap = Maps.newHashMap();
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        if (StringUtils.isBlank(targetFileName)){
            resultMap.put("success", false);
            resultMap.put("msg", "上传失败");
            resultMap.put("file_path", "");
            return resultMap;
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        resultMap.put("success", true);
        resultMap.put("msg", "上传成功");
        resultMap.put("file_path", url);
        response.addHeader("Access-control-Allow-Headers", "X-File_Name");
        return resultMap;
    }
}
