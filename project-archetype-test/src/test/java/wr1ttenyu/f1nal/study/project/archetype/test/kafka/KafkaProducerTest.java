package wr1ttenyu.f1nal.study.project.archetype.test.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class KafkaProducerTest {

    public static void main(String[] args) {
        Map<String, Object> connectInfo = new HashMap<>();
        // kafka集群URL
        connectInfo.put("bootstrap.servers", "192.168.2.201:9092");
        // 应答级别
        connectInfo.put("acks", "all");
        // 发送失败重试次数
        connectInfo.put("retries", 3);
        // 批次大小 16k
        connectInfo.put("batch.size", 16384);
        // 等待时间
        connectInfo.put("linger.ms", "100");
        // RecordAccumulator 大小
        connectInfo.put("buffer.memory", 33554432);
        // key value 的序列化类
        connectInfo.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        connectInfo.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        connectInfo.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");

        // 添加分区器
        /*connectInfo.put("partitioner.class", "wr1ttenyu.f1nal.study.project.archetype.test.kafka.MyPartitioner");*/

        // 添加拦截器
       /* List<String> interceptors = Arrays.asList("wr1ttenyu.f1nal.study.project.archetype.test.kafka.TimeInterceptor");
        connectInfo.put("interceptor.classes", interceptors);*/

        KafkaProducer<String, String> producer = new KafkaProducer<>(connectInfo);
        for (int i = 0; i < 100000; i++) {
            UserModel model = new UserModel();
            model.setName("毛小月" + i);
            model.setAge(10 + i);
            producer.send(new ProducerRecord<>("wr1-kafka-with-gzip", "wr1ttenyu-first--" + i, JSONObject.toJSONString(model)), (recordMetadata, e) -> {
                if (e == null) {
                    System.out.println("partition : " + recordMetadata.partition() + " offset : " + recordMetadata.offset());
                } else {
                    e.printStackTrace();
                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭资源
        producer.close();

    }

}
