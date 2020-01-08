package com.iahsnil.mq.service.impl;

import com.iahsnil.mq.service.RocketMqService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RocketMqServiceImpl implements RocketMqService {
    @Resource
    private DefaultMQProducer defaultMQProducer;
    @Resource
    private ParamConfigService paramConfigService ;
    @Resource
    DefaultMQPushConsumer defaultMQPushConsumer;
    @Override
    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.rocketGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(paramConfigService.rocketTopic,
                    paramConfigService.rocketTag,
                    "open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }

}
