package com.yuzhi.home.controller;

import com.yuzhi.home.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 网站管理
 * @author soso
 * @date 2018-8-29 11:34:58
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    ContactService contactService;

    @RequestMapping("/index")
    public String index(){
        return "work/admin";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "work/contact";
    }
}
