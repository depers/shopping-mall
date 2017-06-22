package com.fmall.common;

/**
 * Created by 冯晓 on 2017/6/22.
 */
public class Const {

    public static final String CURRENT_USER = "current_user";

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    public interface Role{
        int ROLE_CUSTOMER = 0;   //普通用户
        int ROLE_ADMIN = 1;      //管理员
    }
}
