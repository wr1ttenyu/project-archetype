package wr1ttenyu.f1nal.study.project.archetype.test.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.HashMap;
import java.util.Map;

public class KafkaTest /*extends BaseTest */ {

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

        connectInfo.put("partitioner.class", "wr1ttenyu.f1nal.study.project.archetype.test.kafka.MyPartitioner");

        KafkaProducer<String, String> producer = new KafkaProducer(connectInfo);
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord("wr1-kafka-topic-second", "wr1ttenyu", "wr1ttenyu--" + i), (recordMetadata, e) -> {
                if (e == null) {
                    System.out.println("partition : " + recordMetadata.partition() + " offset : " + recordMetadata.offset());
                } else {
                    e.printStackTrace();
                }
            });
        }

        // 关闭资源
        producer.close();

    }

}
