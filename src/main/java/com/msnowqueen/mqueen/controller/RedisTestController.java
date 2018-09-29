package com.msnowqueen.mqueen.controller;

import com.msnowqueen.mqueen.redis.TestRedisDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zn* on 2018/9/28
 */
@Api(tags = {"Redis测试"})
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private TestRedisDao testRedisDao;

    @ApiOperation(value = "Redis测试")
    @RequestMapping(value = "/test/{key}/{value}", method = RequestMethod.GET)
    public String redisTest(@ApiParam(value = "key", defaultValue = "name")
                                @PathVariable("key") String key,
                            @ApiParam(value = "value", defaultValue = "zhou")
                                @PathVariable("value") String value){
        testRedisDao.setKey(key, value);
        String result = testRedisDao.getValue(key);
        return result;
    }

}
