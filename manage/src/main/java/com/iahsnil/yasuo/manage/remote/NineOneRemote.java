package com.iahsnil.yasuo.manage.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "spring-cloud-producer")
public interface NineOneRemote {

    @RequestMapping(value = "/getList")
    Object getList();

    @RequestMapping(value = "/refreshList")
    Object refreshList();

    @RequestMapping(value = "excuteTask")
    Object excuteTask(@RequestParam int taskId);
}
