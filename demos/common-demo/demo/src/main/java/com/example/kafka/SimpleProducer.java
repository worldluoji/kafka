package com.example.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import java.util.*;

public class SimpleProducer {
    public Producer<String, String>  getProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "81.69.35.88:9092");
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("linger.ms", 10);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String>  producer = new KafkaProducer<>(props);
        return producer;
    }
}
