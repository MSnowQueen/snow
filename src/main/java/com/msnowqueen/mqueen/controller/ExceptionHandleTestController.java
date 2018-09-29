package com.msnowqueen.mqueen.controller;

import com.msnowqueen.mqueen.common.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zn* on 2018/9/29
 */
@Controller
public class ExceptionHandleTestController {

    @RequestMapping("/error/global")
    public void errorGlobal(){
        int i = 1 / 0;
    }

    @RequestMapping("/error/json")
    public void errorJson() throws MyException {
        throw new MyException("123");
    }

}
