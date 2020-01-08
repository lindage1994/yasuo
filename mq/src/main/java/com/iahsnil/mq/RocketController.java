package com.iahsnil.mq;

import com.iahsnil.mq.service.RocketMqService;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RocketController {

    @Resource
    private RocketMqService rocketMqService ;

    @RequestMapping("/sendMsg")
    public SendResult sendMsg (String msg){
        SendResult sendResult = null;
        try {
            sendResult = rocketMqService.openAccountMsg(msg) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }

}
