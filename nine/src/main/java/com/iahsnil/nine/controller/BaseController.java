package com.iahsnil.nine.controller;

import com.iahsnil.nine.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    private SpiderService spiderService;

    @RequestMapping("/refreshList")
    public Object refreshList(){

        return "refresh";
    }

    @RequestMapping("/getList")
    public Object getList() {

        return "list";
    }

    @RequestMapping("excuteTask")
    public Object excuteTask(int taskId) {
        return taskId + "success";
    }

    @RequestMapping("process")
    public Object process() {
        spiderService.startSpider();
        return "done";
    }
}
