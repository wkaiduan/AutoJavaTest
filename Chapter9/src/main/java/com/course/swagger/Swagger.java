package com.course.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket api(){
        //http://ip地址:端口/项目名/swagger-ui.html#/
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Java自动化接口实例") //网站标题
                .description("Java自动化接口实例-swagger RESTful APIs......") //网站描述
                .version("1.0") //版本
                .contact(new Contact("王开端","","wkaiduan@163.com")) //联系人
                .license("The Apache License") //协议
                .licenseUrl("http://www.baidu.com") //协议url
                .build();
        return new Docket(DocumentationType.SWAGGER_2) //swagger版本
                .pathMapping("/")
                .select()
                //扫描那些controller
                // .apis(RequestHandlerSelectors.basePackage("com.swagger"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }
}
