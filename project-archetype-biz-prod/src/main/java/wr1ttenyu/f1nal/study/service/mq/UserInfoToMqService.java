package wr1ttenyu.f1nal.study.service.mq;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.service.UserService;
import wr1ttenyu.tinyplat.springboot.starter.kafka.IKafkaRecordConsumeHandler;
import wr1ttenyu.tinyplat.springboot.starter.kafka.KafkaConsumerDispatcher;
import wr1ttenyu.tinyplat.springboot.starter.kafka.KafkaDispatcherManager;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class UserInfoToMqService implements IKafkaRecordConsumeHandler {

    private static Logger logger = LoggerFactory.getLogger(UserInfoToMqService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaDispatcherManager kafkaDispatcherManager;

    private ThreadPoolExecutor userAddExecutor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @Override
    public void handleMessage(ConsumerRecords<String, String> records) {
        if (records != null && records.count() > 0) {
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                logger.info("接收到 新增用户消息 msg：{}", value);
                UserModel userModel = JSONObject.parseObject(value, UserModel.class);
                userAddExecutor.execute(() -> userService.insertUserRecord(userModel));
            }
        }
    }

    public void sendUserInfoMsg(UserModel userModel) {
        String userJson = JSONObject.toJSONString(userModel);
        ProducerRecord<String, String> record = new ProducerRecord("wr1-kafka-with-gzip", userJson);
        try {
            kafkaDispatcherManager.doSend("main", record);
        } catch (Exception e) {
            logger.error("add User Msg Send To Mq Occur Error, User Info:{} Error Msg:{}", userJson, e);
        }
    }

    @Override
    public boolean suspendConsume() {
        if (userAddExecutor.getQueue().size() > 100) return true;
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
