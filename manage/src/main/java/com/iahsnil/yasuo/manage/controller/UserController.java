package com.iahsnil.yasuo.manage.controller;

import com.iahsnil.yasuo.manage.service.UserService;
import com.iahsnil.yasuo.manage.entity.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zed
 * @Date: 2019/2/5 19:26
 * @Description: 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/user/list";
    }

    @RequestMapping("/list")
    @PreAuthorize("hasPermission('userInfo/userList', 'userInfo:view')")
    public String list(Model model) {
        List<UserInfo> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(UserInfo user) {
        userService.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, int id) {
        UserInfo user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(UserInfo user) {
        userService.edit(user);
        return "redirect:/user/list";
    }


    @RequestMapping("/delete")
    public String delete(int id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }
}
