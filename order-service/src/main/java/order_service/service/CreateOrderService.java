package order_service.service;

import lombok.RequiredArgsConstructor;
import order_service.domain.Order;
import order_service.dto.NotificationMessageDto;
import order_service.enums.OrderEvent;
import order_service.enums.OrderStatus;
import order_service.mapper.OrderMapper;
import order_service.producer.NotificationProducerService;
import order_service.repository.OrderRepository;
import order_service.request.CreateOrderRequest;
import order_service.response.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateOrderService {

    private final OrderRepository repository;
    private final NotificationProducerService producer;

    @Transactional
    public OrderResponse execute(CreateOrderRequest request){
        Order order = OrderMapper.toDomain(request);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
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
