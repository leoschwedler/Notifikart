package order_service.dto;

import lombok.Builder;
import lombok.Data;
import order_service.enums.OrderEvent;

@Builder
@Data
public class NotificationMessageDto {
    private String orderId;
    private String message;
    private OrderEvent event;
}
