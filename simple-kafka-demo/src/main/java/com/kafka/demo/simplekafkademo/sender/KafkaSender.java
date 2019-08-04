package com.kafka.demo.simplekafkademo.sender;

import com.google.gson.Gson;
import com.kafka.demo.simplekafkademo.dto.SimpleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class KafkaSender {

    @Autowired
    private Gson gson;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String msg) {
        SimpleMessage message = new SimpleMessage();
        message.setMsgId(UUID.randomUUID().toString());
        message.setMsgContent(msg);
        message.setSendTime(LocalDateTime.now());
        String gsonMsg = gson.toJson(message);
        log.info("Producer Send Message : {}", gsonMsg);
        kafkaTemplate.send("SimpleTopic", gsonMsg);
    }
}
