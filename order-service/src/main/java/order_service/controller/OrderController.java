package order_service.controller;

import lombok.RequiredArgsConstructor;
import order_service.mapper.OrderMapper;
import order_service.request.CreateOrderRequest;
import order_service.request.UpdateOrderRequest;
import order_service.response.OrderResponse;
import order_service.service.CreateOrderService;
import order_service.service.UpdateOrderService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderService createOrderService;
    private final UpdateOrderService updateOrderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request){
        OrderResponse response = createOrderService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody UpdateOrderRequest request){
        OrderResponse response = updateOrderService.execute(request);
        return ResponseEntity.ok(response);
    }
}
