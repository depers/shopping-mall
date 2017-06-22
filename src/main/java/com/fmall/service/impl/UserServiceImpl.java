package com.fmall.service.impl;

import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.dao.UserMapper;
import com.fmall.pojo.User;
import com.fmall.service.IUserService;
import com.fmall.util.MD5Util;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 冯晓 on 2017/6/21.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultInt = userMapper.checkUserName(username);
        if(resultInt == 0){
            return ServerResponse.createByErrorMessage("用户名不存在！");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null){
            return ServerResponse.createByErrorMessage("密码错误！");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);

    }

    public ServerResponse<String> register(User user) {
        ServerResponse validResponse = this.CheckValid(user.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = this.CheckValid(user.getEmail(), Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        user.setRole(Const.Role.ROLE_CUSTOMER);
        // md5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        int resultInt = userMapper.insert(user);

        if(resultInt == 0){
            return ServerResponse.createByErrorMessage("注册失败!");
        }
        return ServerResponse.createBySuccessMessage("注册成功！");
    }


    public ServerResponse<String> CheckValid(String str, String type){

        if (StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultInt = userMapper.checkUserName(str);
                if(resultInt > 0){
                    return ServerResponse.createByErrorMessage("用户名已存在！");
                }
            }

            if(Const.EMAIL.equals(type)){
                int resultInt = userMapper.checkUserEmail(str);
                if(resultInt > 0){
                    return ServerResponse.createByErrorMessage("邮箱已存在！");
                }
            }
        } else{
            return ServerResponse.createByErrorMessage("参数错误！");
        }

        return ServerResponse.createBySuccessMessage("校验成功！");

    }


    public ServerResponse<String> selectQuestion(String username){
        ServerResponse validResponse = this.CheckValid(username, Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String question = userMapper.selectQuestionByUsername(username);
        if(StringUtils.isNotBlank(question)){
            return ServerResponse.createBySuccess(question);
        }

        return ServerResponse.createByErrorMessage("找回密码的问题是空的");

    }



}
