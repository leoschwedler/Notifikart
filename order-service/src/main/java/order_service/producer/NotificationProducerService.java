package order_service.producer;

import lombok.RequiredArgsConstructor;
import order_service.dto.NotificationMessageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducerService {

    @Value("${kafka.topic}")
    private String kafkaTopic;

    private final KafkaTemplate<String, NotificationMessageDto> kafkaTemplate;

    public void sendNotification(NotificationMessageDto notificationMessageDto){
        kafkaTemplate.send(kafkaTopic, notificationMessageDto);
    }
}
