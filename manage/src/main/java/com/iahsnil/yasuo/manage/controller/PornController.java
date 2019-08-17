package com.iahsnil.yasuo.manage.controller;

import com.iahsnil.yasuo.manage.remote.NineOneRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: zed
 * @Date: 2019/8/17 17:24
 * @Description: porn controller
 */
@Controller
@Slf4j
@RequestMapping("/porn")
public class PornController {

    @Autowired
    NineOneRemote nineOneRemote;

    @RequestMapping({"/","/list"})
    public String list(Model model) {
        model.addAttribute("porns", nineOneRemote.getList());
        return "porn/list";
    }

    @RequestMapping("/player")
    public String player(Model model,String url) {
        model.addAttribute("url",url);
        return "porn/player";
    }

    @RequestMapping("/refreshList")
    @ResponseBody
    public Object refreshList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        return nineOneRemote.refreshList(page);
    }
}
