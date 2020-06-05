package wr1ttenyu.f1nal.study.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KafkaConsumerTest /*extends BaseTest */ {

    public static void main(String[] args) {
        Map<String, Object> connectInfo = new HashMap<>();
        // kafka集群URL
        connectInfo.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.201:9092");
        // 开启自动提交
        connectInfo.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 自动提交的延时
        connectInfo.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        // key value 的序列化类
        connectInfo.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        connectInfo.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // 消费者组
        connectInfo.put(ConsumerConfig.GROUP_ID_CONFIG, "wr1ttenyu");

        KafkaConsumer<String, String> consumer = new KafkaConsumer(connectInfo);

        consumer.subscribe(Arrays.asList("wr1-kafka-topic-first", "wr1-kafka-topic-second"));

        for (int i = 0; ; i++) {
            System.out.println("--------------------第" + i + "次开始拉取消息--------------------");
            consumer.paused();
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
            System.out.println("--------------------拉取了" + records.count() + "条消息--------------------");
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("receive record，topic：" + record.topic() + " partition："
                        + record.partition() + " key：" + record.key() + " value：" + record.value());

            }
        }


    }

}
