package wr1ttenyu.f1nal.study.project.archetype.test.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyPartitioner implements Partitioner {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int i = atomicInteger.getAndIncrement() % 3;
        System.out.println("分区结果：" + i);
        return i;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
