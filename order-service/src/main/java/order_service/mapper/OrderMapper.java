package order_service.mapper;

import lombok.experimental.UtilityClass;
import order_service.domain.Order;
import order_service.request.CreateOrderRequest;
import order_service.request.UpdateOrderRequest;
import order_service.response.OrderResponse;

@UtilityClass
public class OrderMapper {

    public Order toDomain(CreateOrderRequest request){
        return Order.builder()
                .customerId(request.getCustomerId())
                .basketId(request.getBasketId())
                .amount(request.getAmount())
                .shippingCost(request.getShippingCost())
                .build();
    }

    public OrderResponse toResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .basketId(order.getBasketId())
                .totalAmount(order.totalAmount())
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }
}
