package com.testng.httpclient;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;


public class myhttpClient {
    @Test
    public void test1() throws IOException {
        //用来存放我们的结果
        String result;
        // 创建HttpGet对象，指定请求的URL为百度首页
        HttpGet get = new HttpGet("http://www.baidu.com");
        //这个是用来执行get方法的
        // 创建HttpClient对象，用于执行HTTP请求
        HttpClient client = new DefaultHttpClient();
        // 执行HttpGet请求，获取响应
        HttpResponse response = client.execute(get);
        // 将响应实体的内容转换为字符串，并指定字符集为Utf-8
        result = EntityUtils.toString(response.getEntity(), "Utf-8");
        // 打印转换后的响应内容
        System.out.println(result);
    }
}
