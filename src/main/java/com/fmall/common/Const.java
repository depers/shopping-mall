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

}
