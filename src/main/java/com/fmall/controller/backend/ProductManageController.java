package com.fmall.controller.backend;

import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.Product;
import com.fmall.pojo.User;
import com.fmall.service.IFileService;
import com.fmall.service.IProductService;
import com.fmall.service.IUserService;
import com.fmall.util.CookieUtil;
import com.fmall.util.JsonUtil;
import com.fmall.util.PropertiesUtil;
import com.fmall.util.RedisShardedPoolUtil;
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
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 冯晓 on 2017/6/26.
 */

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpServletRequest request, Product product){
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息。");
        }

        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加的业务逻辑
            return iProductService.saveOrUpdateProduct(product);
        } else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse SetSaleStatus(HttpServletRequest request, Integer productId, Integer status){
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息。");
        }

        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加的业务逻辑
            return iProductService.setSaleStatus(productId, status);
        } else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse GetDetail(HttpServletRequest request, Integer productId){
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息。");
        }

        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加的业务逻辑
            return iProductService.manageProductDetail(productId);
        } else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse GetList(HttpServletRequest request,
                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息。");
        }

        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加的业务逻辑
            return iProductService.getProductList(pageNum, pageSize);
        } else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse getSearch(HttpServletRequest request,
                                  String productName,
                                  Integer productId,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息。");
        }

        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加的业务逻辑
            return iProductService.searchProduct(productName, productId, pageNum, pageSize);
        } else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request){
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息。");
        }

        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加的业务逻辑
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);
            return ServerResponse.createBySuccess(fileMap);
        } else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("richText_img_upload.do")
    @ResponseBody
    public Map richTextImgUpload(@RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request,
                                 HttpSession session,
                                 HttpServletResponse response){
        Map resultMap = Maps.newHashMap();
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            resultMap.put("success", false);
            resultMap.put("msg", "请登录管理员");
            resultMap.put("file_path", "");
            return resultMap;
        }

        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user == null){
            resultMap.put("success", false);
            resultMap.put("msg", "请登录管理员");
            resultMap.put("file_path", "");
            return resultMap;
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
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

        } else{
            resultMap.put("success", false);
            resultMap.put("msg", "无权限操作");
            resultMap.put("file_path", "");
            return resultMap;
        }
    }
}
