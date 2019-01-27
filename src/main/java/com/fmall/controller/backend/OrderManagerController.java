package com.fmall.controller.backend;

import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.User;
import com.fmall.service.IOrderService;
import com.fmall.service.IUserService;
import com.fmall.util.CookieUtil;
import com.fmall.util.JsonUtil;
import com.fmall.util.RedisShardedPoolUtil;
import com.fmall.vo.OrderVo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 冯晓 on 2017/9/19.
 */
@Controller
@RequestMapping("/manage/order/")
public class OrderManagerController {

    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iOrderService.manageList(pageNum, pageSize);
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderList(Long orderNo){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iOrderService.manageDetail(orderNo);
    }


    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> search(Long orderNo,
                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iOrderService.manageSearch(orderNo, pageNum, pageSize);
    }

    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> sendGoods(HttpServletRequest request, Long orderNo){
        // 全部通过拦截器验证是否登录以及权限
        //填充我们增加的业务逻辑
        return iOrderService.manageSendGoods(orderNo);
    }


}
