package com.msnowqueen.mqueen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zn* on 2018/9/27
 */
@RestController
@Api(tags = "LogBack测试")
@RequestMapping("/logback")
@Slf4j
public class LogbackTestController {

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Token",
                    value = "token", dataType = "String", required = true,
                    defaultValue = "123")
    })
    @ApiOperation(value = "LogBack测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public int logbackTest() {
        log.info("info配置");
        log.error("error配置");
        log.debug("debug配置");
        log.warn("warn配置");
        return 1;
    }

}
