package com.msnowqueen.mqueen.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zn* on 2018/9/27
 */
@Configuration //spring boot配置注解
@EnableSwagger2 //启用swagger2功能注解
public class Swagger2Config {

    public Docket createRestfulApi() { //api文档实例
        return new Docket(DocumentationType.SWAGGER_2) //文档类型：DocumentationType.SWAGGER_2
                .apiInfo(apiInfo()) //api信息
                .select() //构建api选择器
                .apis(RequestHandlerSelectors.basePackage("com.msnowqueen.mqueen.controller")) //api选择器选择api的包
                .paths(PathSelectors.any()) //api选择器选择包路径下任何api显示在文档中
                .build(); //创建文档
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful接口")
                //描述
                .description("接口描述, 项目地址: https://github.com/MSnowQueen/snow/tree/V1.0")
                //服务条款网站
                //.termsOfServiceUrl("项目路径: https://github.com/MSnowQueen/snow/tree/V1.0")
                //创建人
                .contact(new Contact("ZN", "", ""))
                .version("1.0")
                .build();
    }

}
