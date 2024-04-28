package com.mysystem.mq;

import com.mysystem.dao.po.RequestRecordPO;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogRecordProducer {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    String topic = "log-record";

    public void sendMsg(RequestRecordPO po) {
        rocketMQTemplate.asyncSend(topic, po, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }
}
