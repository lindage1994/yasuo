package com.iahsnil.yasuo.manage.remote;

import com.iahsnil.yasuo.manage.fallback.NineOneRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name= "nine-producer",fallback = NineOneRemoteHystrix.class)
public interface NineOneRemote {

    @RequestMapping(value = "/getList")
    List<Object> getList();

    @RequestMapping(value = "/refreshList")
    Object refreshList(@RequestParam int page);

    @RequestMapping(value = "excuteTask")
    Object excuteTask(@RequestParam int taskId);
}
