package com.msnowqueen.mqueen.controller;

import com.msnowqueen.mqueen.config.TestError;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zn* on 2018/9/27
 */
@RestController//接口注解
@Api(value = "用户接口", tags = {"testApi"})//接口简要标注，对中文的支持不太好
@RequestMapping(value = "/swagger")//接口基本路径
public class Swagger2TestController {

    //接口需要的参数，可以有多个，这里只写了一个，它的paramType还有path、query、body、form几种
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Token",
                    value = "token", dataType = "String", required = true,
                    defaultValue = "123")
    })
    //接口功能描述
    @ApiOperation(value = "测试Swagger2")
    //接口响应信息，这里定义了一个401，当出现401，接口返回的是自定的错误AnimalError的实例。当然可以定义多个。
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "请求未通过认证.", response = TestError.class)
    })
    @RequestMapping(value = "/test/{num}", method = RequestMethod.GET)
    public int testSwagger2(
            @ApiParam(defaultValue = "123")//@ApiParam和@RequestParam注解作用效果相同
            @PathVariable("num") int num){
        return num;
    }

}
