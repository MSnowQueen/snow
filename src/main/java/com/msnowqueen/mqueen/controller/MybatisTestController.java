package com.msnowqueen.mqueen.controller;

import com.msnowqueen.mqueen.dao.MUserMapper;
import com.msnowqueen.mqueen.pojo.MUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by zn* on 2018/9/28
 */
@Api(tags = {"Mybatis测试"})
@RestController
@RequestMapping("/mybatis")
public class MybatisTestController {

    @Autowired
    private MUser mUser;
    @Autowired
    private MUserMapper mUserMapper;

    @ApiOperation(value = "Mybatis测试")
    @RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
    public int mybatisTest(@ApiParam(value = "name", defaultValue = "user1")
                               @PathVariable("name") String name) {
        mUser.setMuserName(name);
        mUser.setMuserCreatetime(new Date());
        int result = mUserMapper.insertUser(mUser);
        return result;
    }

}
