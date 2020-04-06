package wr1ttenyu.f1nal.study.project.archetype.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.project.archetype.service.KafkaConsumeService;
import wr1ttenyu.f1nal.study.project.archetype.service.UserService;
import wr1ttenyu.tinyplat.springboot.starter.kafka.IKafkaRecordConsumeHandler;
import wr1ttenyu.tinyplat.springboot.starter.kafka.KafkaConsumerDispatcher;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaConsumeServiceImpl implements KafkaConsumeService, IKafkaRecordConsumeHandler {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;

    private ThreadPoolExecutor userAddExecutor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @Override
    public void handleMessage(ConsumerRecords<String, String> records) {
        if (records != null && records.count() > 0) {
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                logger.info("接收到消息 msg：{}", value);
                UserModel userModel = JSONObject.parseObject(value, UserModel.class);
                userAddExecutor.execute(() -> {
                    userService.insertUserRecord(userModel);
                });

            }
        }
    }

    @Override
    public boolean suspendConsume() {
        if (userAddExecutor.getQueue().size() > 10000) return true;
        return false;
    }

    @Override
    public String getConsumerInstanceName() {
        return KafkaConsumerDispatcher.DEF_CONSUMER_BIZ_NAME;
    }

    @Override
    public List<String> getSubscribTopics() {
        return Arrays.asList("wr1-kafka-with-gzip");
    }
}
