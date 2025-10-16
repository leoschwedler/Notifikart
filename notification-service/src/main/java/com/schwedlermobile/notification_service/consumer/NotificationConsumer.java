package com.schwedlermobile.notification_service.consumer;

import com.schwedlermobile.notification_service.dto.NotificationMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationConsumer {

    @KafkaListener(topics = "${kafka.topic}")
    public void listener(NotificationMessageDto notificationMessageDto){
        log.info("Received message {}", notificationMessageDto);
    }
}
