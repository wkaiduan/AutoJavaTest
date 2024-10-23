package com.swagger.server;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.ILoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.CookieStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Api(value = "/",tags = "整体模型控制类",description = "这是我的全部的get方法")
public class MyGetMethod {
@RequestMapping(value = "/getcookies" , method = RequestMethod.GET)
@ApiOperation(value="模型总表信息-GET",notes="通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getcookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse 装响应信息的类
        Cookie cookie = new Cookie("login","1585dsd4s1re7a5s5we8rt5s2df2s2e5w5");
        response.addCookie(cookie);
        return "恭喜你获得getcookies信息" + "cookie值是" + cookie.getValue();
    }

    //要求客户端携带cookie访问
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookie访问",httpMethod = "GET")
    public String getWithCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "没有cookie";
        }
        for(Cookie cookie :cookies){
            if(cookie.getName().equals("login") && cookie.equals("1585dsd4s1re7a5s5we8rt5s2df2s2e5w5")){
                return "携带cookie访问成功";
            }
        }
        return "携带cookie访问成功";
    }
    //开发一个需要携带参数才能访问的get请求
    //第一种实现方式 url:key=value&key=value
    //模拟来获取商品列表
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start, @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",200);
        return myList;
    }
    //第二种实现方式 url:ip:port/get/with/param/10/20
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求的第二种",httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer>myList =new HashMap<>();
        myList.put("衣服",600);
        myList.put("鞋",500);
        myList.put("干脆面",2);
        return myList;
    }

}
