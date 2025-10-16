package order_service.service;

import lombok.RequiredArgsConstructor;
import order_service.domain.Order;
import order_service.dto.NotificationMessageDto;
import order_service.enums.OrderEvent;
import order_service.enums.OrderStatus;
import order_service.mapper.OrderMapper;
import order_service.producer.NotificationProducerService;
import order_service.repository.OrderRepository;
import order_service.request.UpdateOrderRequest;
import order_service.response.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateOrderService {

    private final OrderRepository repository;
    private final OrderStateService service;
    private final NotificationProducerService producer;

    @Transactional
    public OrderResponse execute(UpdateOrderRequest request){
        Order order = repository.findById(request.getOrderId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")
        );
        OrderStatus orderStatus = service.processEvent(order.getStatus(), request.getOrderEvent());
        order.setStatus(orderStatus);
        order.setUpdatedAt(LocalDateTime.now());
        producer.sendNotification(
                NotificationMessageDto.builder()
                        .orderId(order.getId())
                        .message("New order created")
                        .event(OrderEvent.CREATE)
                        .build()
        );
        order = repository.save(order);
        return OrderMapper.toResponse(order);
    }
}
