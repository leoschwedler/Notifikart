package order_service.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateOrderRequest {
    private String customerId;
    private String basketId;
    private BigDecimal amount;
    private BigDecimal shippingCost;
}
