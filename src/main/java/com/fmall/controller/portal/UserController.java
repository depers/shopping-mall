package com.fmall.controller.portal;

import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.User;
import com.fmall.service.IUserService;
import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 冯晓 on 2017/6/21.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;


    /**
     * 用户登录
     * @param username
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession httpSession){
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            httpSession.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }


    @RequestMapping(value = "checkValid.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type){
        return iUserService.CheckValid(str, type);
    }


    @RequestMapping(value = "getUserInfo.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }

        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息。");
    }


    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetQuestion(String username){
        return forgetQuestion(username);
    }

}
