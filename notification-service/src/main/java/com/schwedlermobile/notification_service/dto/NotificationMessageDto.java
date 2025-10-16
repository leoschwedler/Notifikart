package com.schwedlermobile.notification_service.dto;

import com.schwedlermobile.notification_service.enums.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessageDto {
    private String orderId;
    private String message;
    private OrderEvent event;
}
