package order_service.request;

import lombok.Builder;
import lombok.Data;
import order_service.enums.OrderEvent;

@Data
@Builder
public class UpdateOrderRequest {
    private String orderId;
    private OrderEvent orderEvent;
}
