package com.Newcity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/9/1.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "customer/controller";
    }

}
