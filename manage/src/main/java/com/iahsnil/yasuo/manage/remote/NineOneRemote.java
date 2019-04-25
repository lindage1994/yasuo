package com.iahsnil.yasuo.manage.remote;

import com.iahsnil.yasuo.manage.fallback.NineOneRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "nine-producer",fallback = NineOneRemoteHystrix.class)
public interface NineOneRemote {

    @RequestMapping(value = "/getList")
    Object getList();

    @RequestMapping(value = "/refreshList")
    Object refreshList(@RequestParam int page);

    @RequestMapping(value = "excuteTask")
    Object excuteTask(@RequestParam int taskId);
}
