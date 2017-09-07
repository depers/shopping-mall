package com.fmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by 冯晓 on 2017/6/22.
 */
public class Const {

    public static final String CURRENT_USER = "current_user";

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";


    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }
    public interface Role{
        int ROLE_CUSTOMER = 0;   //普通用户
        int ROLE_ADMIN = 1;      //管理员
    }

    public interface Cart{
        int CHECKED = 1; //购物车选中状态
        int UN_CHECKED = 0; //购物车未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public enum ProductStatusEnum{

        ON_SALE("在线", 1);

        private String value;
        private int code;

        ProductStatusEnum(String value, int code){
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }


    public enum OrderStatusEnum{
        CANCELED(0, "已取消"),
        NO_PAY(10, "未支付"),
        PAID(20, "已支付"),
        SHIPPED(40, "已发货"),
        ORDER_SUCCESS(50, "订单完成"),
        ;

        private String value;
        private int code;

        OrderStatusEnum( int code, String value){
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }
        public int getCode() {
            return code;
        }
    }


    public interface AlipayCallback{

        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum PayPlatformEnum{

        ALIPAY(1, "支付宝"),
        WEIXINPAY(2, "微信");

        private String value;
        private int code;

        PayPlatformEnum( int code, String value){
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }
        public int getCode() {
            return code;
        }
    }


}
