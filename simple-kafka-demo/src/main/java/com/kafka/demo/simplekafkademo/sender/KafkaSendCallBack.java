package com.kafka.demo.simplekafkademo.sender;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaSendCallBack implements ProducerListener {
    
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("Message send success {}", producerRecord.toString());
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        log.error("Message send failed {}", producerRecord.toString());
    }
}
