package order_service.service;

import lombok.RequiredArgsConstructor;
import order_service.domain.Order;
import order_service.enums.OrderStatus;
import order_service.mapper.OrderMapper;
import order_service.repository.OrderRepository;
import order_service.request.CreateOrderRequest;
import order_service.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateOrderService {

    private final OrderRepository repository;

    public OrderResponse execute(CreateOrderRequest request){
        Order order = OrderMapper.toDomain(request);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order = repository.save(order);
        return OrderMapper.toResponse(order);
    }
}
