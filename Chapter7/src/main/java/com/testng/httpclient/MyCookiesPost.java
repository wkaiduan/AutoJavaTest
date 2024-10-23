package com.testng.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesPost {
    private String url;
    private ResourceBundle bundle;

    private CookieStore store;
    //用来存储cookies信息的变量
    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url =bundle.getString("test.uri");
    }
    @Test
    public void testpostCookies() throws IOException {
        String result;
        //从配置文件中 拼接测试的url
        String uri =bundle.getString("login");
        String testurl=this.url+uri;
        //测试逻辑代码书写
        HttpPost post = new HttpPost(testurl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(post);
        result= EntityUtils.toString(response.getEntity(),"Utf-8");
        System.out.println(result);

        //获取响应的cookies信息
        this.store= client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();

        for(Cookie cookie : cookieList){
            String name =cookie.getName();
            String value =cookie.getValue();
            System.out.println("cookie name = " +name+"; cookie value =" +value);
        }
    }



    @Test(dependsOnMethods = {"testpostCookies"})
    public void testPostWithCookies() throws IOException {
        String uri=bundle.getString("test.post.with.cookies");
        //拼接最终的测试地址
        String testurl=this.url+uri;
        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testurl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","wangwu");
        param.put("age","20");

        //设置请求信息 设置header信息
        post.setHeader("Content-Type","application/json");
        //将参数信息添加到方法中
        System.out.println("post值"+post);
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        System.out.println("####"+entity);
        post.setEntity(entity);
        System.out.println(param.toString());
        //声明一个对象进行响应结果存储
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response= client.execute(post);
        //获取响应结果
        result= EntityUtils.toString(response.getEntity(),"Utf-8");
        System.out.println("****"+response.getEntity().toString());
        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串转化成为json对象
//        if(result ==null) {
//            //处理 result为 null的情况
//            System.out.println("返回结果为空");
//            return;
//        }try{
//            JSONObject resultJson = new JSONObject(result);
//            //继续处理resultJson
//        }catch (JSONException e){
//            System.out.println("转换json失败" + e.getMessage());
//        }
        JSONObject resultJson = new JSONObject(result);
        //获取到结果值
        String success =(String) resultJson.get("wangwu");
        String status =(String) resultJson.get("status");
        //具体的判断返回结果的值
        Assert.assertEquals(success,success);
        Assert.assertEquals(status,1);
    }




//    public static void main(String[] args) {
//        String filePath ="//D:/JavaWorkTest/AutoJavaTest/Chapter6/src/main/java/com/testng/moco/moco-post.json";
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            StringBuilder jsonString = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                jsonString.append(line);
//            }
//
//            JSONObject jsonObject = new JSONObject(jsonString.toString());
//            System.out.println(jsonObject.toString());
//        } catch (IOException | org.json.JSONException e) {
//            System.err.println("读取或解析 JSON 文件时出错: " + e.getMessage());
//        }
//    }
}