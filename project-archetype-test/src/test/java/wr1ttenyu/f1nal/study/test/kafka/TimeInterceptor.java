package wr1ttenyu.f1nal.study.test.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeInterceptor implements ProducerInterceptor<String, String> {

    private AtomicInteger successNum = new AtomicInteger();

    private AtomicInteger failNum = new AtomicInteger();

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + record.value();

        return new ProducerRecord<String, String>(record.topic(), record.key(), value);
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) successNum.incrementAndGet();
        else failNum.incrementAndGet();
    }

    @Override
    public void close() {
        System.out.println(MessageFormat.format("成功发送 {0} 条", successNum.get()));
        System.out.println(MessageFormat.format("发送失败 {0} 条", failNum.get()));
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
