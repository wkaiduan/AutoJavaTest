package com.swagger.server;

import com.swagger.controller.user;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",tags = "整体模型控制类Post",description = "这是我的全部的post请求")
public class MyPostMethod {

    private static Cookie cookie;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口,获取cookie信息", httpMethod = "POST")
    public String login(HttpServletResponse response, @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) {
        if (username.equals("admin") && password.equals("123456")) {
            cookie = new Cookie("login", "1585dsd4s1re7a5s5we8rt5s2df2s2e5w5");
            response.addCookie(cookie);
            return "恭喜你登录成功";
        }
        return "用户名或者密码错误";
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUesr(HttpServletRequest request, @RequestBody User u) {
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookies是否合法
        for (Cookie c : cookies) {
            if (c.getName().equals("login")
                    && c.getValue().equals("1585dsd4s1re7a5s5we8rt5s2df2s2e5w5")
                    && u.getUsername().equals("wangwu")
                    && u.getPassword().equals("123456")){
            }

        }
      return  u.toString();
    }
}
