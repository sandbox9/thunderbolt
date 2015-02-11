package sandbox9.thunderbolt.api.order.listener;

import sandbox9.thunderbolt.api.order.OrderItemRequest;
import sandbox9.thunderbolt.api.order.service.OrderService;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public class OrderItemRequestMessageListener {
    private final OrderService orderService;

    public OrderItemRequestMessageListener(OrderService orderService) {
        this.orderService = orderService;
    }

    public void handleMessage(OrderItemRequest orderItemRequest) {
        
    }
}
