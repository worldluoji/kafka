package com.example.kafka;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.*;

public class SimpleConsumer {
    public Consumer<String, String> getConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "81.69.35.88:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
   
        // 创建Consumer实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
   
        // 订阅Topic
        consumer.subscribe(Arrays.asList("test-topic1"));
        return consumer;
    }
}
