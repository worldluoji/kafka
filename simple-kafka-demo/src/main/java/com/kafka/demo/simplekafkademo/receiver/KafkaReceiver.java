package com.kafka.demo.simplekafkademo.receiver;

import com.kafka.demo.simplekafkademo.dto.SimpleMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
public class KafkaReceiver {
    
    @KafkaListener(topics = {"test-topic1"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            if (message instanceof SimpleMessage) {
                SimpleMessage simpleMessage = (SimpleMessage) kafkaMessage.get();
                log.info("Consumer receive msg:info->{}, sendTime->{}, receiveTime->{}",
                        simpleMessage.getMsgContent(), simpleMessage.getSendTime(), LocalDateTime.now());
            } else {
                log.info("Receive Other msg->{}", message);
            }
        }
    }
}
