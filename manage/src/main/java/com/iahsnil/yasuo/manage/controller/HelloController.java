package com.iahsnil.yasuo.manage.controller;

import com.iahsnil.yasuo.manage.remote.NineOneRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @Auther: zed
 * @Date: 2019/2/5 19:29
 * @Description: helloController
 */
@Controller
@Slf4j
public class HelloController {

    @Autowired
    NineOneRemote nineOneRemote;

    @RequestMapping(value = {"/","/index"})
    public String index() {
        return "home";
    }

    @RequestMapping(value = "/jobManager")
    public String jobManager() {
        return "jobManager";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin(Model model) {
        model.addAttribute("name", "超级管理员");
        return "home";
    }

    @RequestMapping("/home")
    public String home(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name, HttpServletRequest request) {
        model.addAttribute("name", name);
        Object o = request.getSession().getAttribute("springboot");
        if (ObjectUtils.isEmpty(0)) {
            request.getSession().setAttribute("springboot",1);
        }
        return "home";
    }

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "login";
    }

    @RequestMapping("/remote/getList")
    @ResponseBody
    public Object getList() {
        return nineOneRemote.getList();
    }

    @RequestMapping("/remote/refreshList")
    @ResponseBody
    public Object refreshList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        return nineOneRemote.refreshList(page);
    }
}
