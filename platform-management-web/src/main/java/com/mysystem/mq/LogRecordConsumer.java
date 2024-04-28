package com.mysystem.mq;

import com.mysystem.dao.po.RequestRecordPO;
import com.mysystem.model.vo.RequestRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "consumer-1",topic = "log-record")
public class LogRecordConsumer implements RocketMQListener<RequestRecordPO> {


    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onMessage(RequestRecordPO recordPO) {
        // 判断是否重复消费
        if (!redisTemplate.opsForValue().setIfAbsent(recordPO.getRequestId(),"handle")){
            log.info("当前请求日志重复");
            return;
        }
        // 写入mongodb
        mongoTemplate.insert(recordPO);
    }
}
