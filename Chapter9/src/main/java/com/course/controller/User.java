package com.course.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@Api(value = "/",tags = "用户接口",description = "提供用户相关的操作")
public class User<name> {
    // 注入 MyBatis 的 SqlSessionTemplate
    @Autowired
    private SqlSessionTemplate template;
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户列表",notes = "这是一个查询用户信息接口", httpMethod = "GET")
    public List<User> getUserList() {
        // 使用 selectList 方法获取多条记录
        List<User> users = template.selectList("getUserList");
        return users;
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户信息",notes = "这是一个添加用户信息接口",httpMethod = "GET")
    public  int addUser(@RequestParam(value="id", required = true) String id,
                        @RequestParam(value="name", required = true) String name,
                        @RequestParam(value="age", required = true) String age,
                        @RequestParam(value="email", required = true) String email){
         return template.insert("addUser");
    }
    @RequestMapping(value = "/updateUser",  method = RequestMethod.POST)
    @ApiOperation(value = "更新用户信息",notes = "这是一个更新用户信息接口", httpMethod = "GET")
    public int updateUser(@RequestParam(value="id", required = true) int id,@RequestParam(value="name", required = true) String name, User user){
        return template.update("updateUser",user);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    @ApiOperation(value = "删除用户信息",notes = "这是一个删除用户信息接口", httpMethod = "GET")
    public int deleteUser(@RequestParam(value="id", required = true) int id){
        return template.delete("deleteUser",id);
    }
}