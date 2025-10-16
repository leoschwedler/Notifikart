package order_service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import order_service.enums.OrderStatus;
import order_service.enums.PaymentMethod;

import java.math.BigDecimal;

@Builder
@Data
public class OrderResponse {
    private String id;
    private String customerId;
    private String basketId;
    private BigDecimal totalAmount;
    private OrderStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentMethod paymentMethod;
}
