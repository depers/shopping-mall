package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.User;

/**
 * Created by 冯晓 on 2017/6/21.
 */
public interface IUserService {

    ServerResponse<User> login(String username, String password);
}
