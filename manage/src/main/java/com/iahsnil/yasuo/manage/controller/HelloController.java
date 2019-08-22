package com.iahsnil.yasuo.manage.controller;

import com.iahsnil.yasuo.manage.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    RegisterService registerService;
    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping(value = {"/","/index"})
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "/jobManager")
    public String jobManager() {
        return "jobManager";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
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
        return "hello";
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

    @RequestMapping("/register")
    public String register(HttpServletRequest request, Model model, String username, String password, String email) {
        registerService.register(username, password, email);
        //进行授权登录
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try{
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authenticatedUser = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        } catch( AuthenticationException e ){
            System.out.println("Authentication failed: " + e.getMessage());
            return "redirect:/register";
        }
        //跳到首页
        return "redirect:/hello";
    }

    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    @RequestMapping("/500")
    public String error500() {
        return "500";
    }
}
