package com.iahsnil.nine.controller;

import com.iahsnil.nine.entity.VedioInfo;
import com.iahsnil.nine.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class BaseController {

    @Autowired
    private SpiderService spiderService;

    @RequestMapping("/refreshList")
    public Object refreshList(int page){
        Future<String> future = spiderService.refreshList(page);
        Thread listerTread = new Thread(() -> {
            try {
                log.info("异步调用返回结果:" + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        listerTread.setDaemon(true);
        listerTread.start();
        return "success";
    }

    @RequestMapping("/getList")
    public List<VedioInfo> getList() {
        return spiderService.getList();
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
