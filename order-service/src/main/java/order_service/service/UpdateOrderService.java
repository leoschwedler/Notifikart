package order_service.service;

import lombok.RequiredArgsConstructor;
import order_service.domain.Order;
import order_service.enums.OrderStatus;
import order_service.mapper.OrderMapper;
import order_service.repository.OrderRepository;
import order_service.request.UpdateOrderRequest;
import order_service.response.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateOrderService {

    private final OrderRepository repository;
    private final OrderStateService service;

    public OrderResponse execute(UpdateOrderRequest request){
        Order order = repository.findById(request.getOrderId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")
        );
        OrderStatus orderStatus = service.processEvent(order.getStatus(), request.getOrderEvent());
        order.setStatus(orderStatus);
        order.setUpdatedAt(LocalDateTime.now());
        order = repository.save(order);
        return OrderMapper.toResponse(order);
    }
}
