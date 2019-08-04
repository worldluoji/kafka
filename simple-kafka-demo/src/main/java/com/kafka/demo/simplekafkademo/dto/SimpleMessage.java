package com.kafka.demo.simplekafkademo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleMessage {
    private String msgId;
    private String msgContent;
    private LocalDateTime sendTime;
}
