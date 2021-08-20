package com.kafka.demo.simplekafkademo.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class GsonConfig {
    @Bean
    public Gson getGsonTool() {
        return new GsonBuilder().create();
    }
}
