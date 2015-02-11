package sandbox9.thunderbolt.api.order.listener.order;

import sandbox9.thunderbolt.api.order.OrderResponse;
import sandbox9.thunderbolt.api.order.message.OrderRequest;
import sandbox9.thunderbolt.api.order.model.Order;
import sandbox9.thunderbolt.api.order.service.OrderService;

/**
 * Created by chanwook on 2014. 12. 18..
 */
public class CartRequestMessageListener {

    private OrderService orderService;

    public CartRequestMessageListener(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderResponse handleMessage(OrderRequest orderRequest) {
        Order cart = orderService.createCart(orderRequest.getCustomerId());
        return new OrderResponse(cart.getOrderId(), cart.getState());
    }
}
