package com.iahsnil.nine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

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
}
