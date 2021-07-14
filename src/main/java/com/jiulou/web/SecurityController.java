package com.jiulou.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "forward:/loginGG";
    }

    @RequestMapping("/loginGG")
    @ResponseBody
    public String login(){
        return "没有权限！";
    }

}
