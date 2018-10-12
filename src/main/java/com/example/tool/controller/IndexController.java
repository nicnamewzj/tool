package com.example.tool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA
 * Created By wzj
 * Date: 2018/10/11
 * Time: 20:06
 * Description:
 * Modified By:
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/index")
    public String index2(){
        return "index";
    }
}
