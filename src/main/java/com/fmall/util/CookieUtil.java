package com.fmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 冯晓 on 2019/1/21.
 */
@Slf4j
public class CookieUtil {

    private final static String COOKIE_DOMAIN = ".bravedawn.cn";
    private final static String COOKIE_NAME = "fmall_login_token";


    public static String readLoginToken(HttpServletRequest request){
        Cookie[] cks = request.getCookies();
        if (cks != null){
            for (Cookie ck : cks){
                log.info("read cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)){
                    log.info("return cookieName:{}, cookieValue:{}",ck.getName(), ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    //X.domain:".happyname.com"--a,b,c,d,e都可以拿到X的cookie
    //a:A.happymall.com                 cookie:domain=A.happymall.com; path="/"
    //b:B.happymall.com                 cookie:domain=B.happymall.com; path="/"
    //c:A.happymall.com/test/cc         cookie:domain=A.happymall.com; path="/test/cc"
    //d:A.happymall.com/test/bb         cookie:domain=A.happymall.com; path="/test/bb"
    //e:A.happymall.com/test            cookie:domain=A.happymall.com; path="/test"

    /**
     * a和b是同级域名两个人都拿不到对方的cookie
     * c和d可以共享a的，e的cookie
     * c拿不到d的，d也拿不到c的
     * c和d拿不到b的
     *
     * 总结：若A域名中包含B域名，那A就能拿到B域名的cookie;
     *      若A域名和B域名同级，则互相都拿不到对方的cookie;
     */

    public static void writeLoginToken(HttpServletResponse response, String token){
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setPath("/");// 代表设置在根目录下，只有在该目录下的代码才能访问到cookie
        cookie.setHttpOnly(true); //防止脚本攻击造成信息泄露风险
        // maxage单位是秒，如果这个maxage不设置的话，cookie就不会写入硬盘，而是写入内存，这样的话如果页面关了cookie也就消失了，只在当前的页面有效。
        // maxage如果设置为-1的话，代表永久
        cookie.setMaxAge(60 * 60 * 24 * 365);
        log.info("write cookieName:{}, cookieValue:{}", cookie.getName(), cookie.getValue());
        response.addCookie(cookie);
    }


    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cks = request.getCookies();
        if (cks != null){
            for (Cookie ck : cks){
                if(StringUtils.equals(ck.getName(), COOKIE_NAME)){
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setMaxAge(0);// 设置为0，代表删除此cookie
                    log.info("del cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }

}
