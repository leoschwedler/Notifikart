package order_service.service;

import lombok.Data;
import order_service.enums.OrderEvent;
import order_service.enums.OrderStatus;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

@Service
@Data
public class OrderStateService {

    private final StateMachine<OrderStatus, OrderEvent> stateMachine;

    public OrderStatus processEvent(OrderStatus currentStatus, OrderEvent event) {
        stateMachine.stop();
        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(access ->
                        access.resetStateMachine(new DefaultStateMachineContext<>(currentStatus, null, null, null)));
        stateMachine.start();

        boolean accepted = stateMachine.sendEvent(event);

        if (accepted) {
            return stateMachine.getState().getId();
        } else {
            throw new IllegalStateException("Transição inválida: " + event + " a partir de " + currentStatus);
        }
    }

}