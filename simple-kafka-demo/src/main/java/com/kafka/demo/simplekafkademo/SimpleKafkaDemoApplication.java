package com.kafka.demo.simplekafkademo;

import com.kafka.demo.simplekafkademo.sender.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleKafkaDemoApplication implements ApplicationRunner {

    @Autowired
    private KafkaSender kafkaSender;

    public static void main(String[] args) {
        SpringApplication.run(SimpleKafkaDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0 ;i < 100; i++) {
            kafkaSender.send("消息" + i);
        }
    }
}
