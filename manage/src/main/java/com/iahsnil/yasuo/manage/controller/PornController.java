package com.iahsnil.yasuo.manage.controller;

import com.iahsnil.yasuo.manage.remote.NineOneRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;


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
    public String list(Model model, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum) {
        List<Object> list = nineOneRemote.getList();
        model.addAttribute("porns", list.stream().skip((pageNum - 1) * 20).limit(20).collect(Collectors.toList()));
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("totalPages",list.size() / 20 + 1);
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
