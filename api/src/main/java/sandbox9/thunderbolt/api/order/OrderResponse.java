package sandbox9.thunderbolt.api.order;

import sandbox9.thunderbolt.api.order.model.OrderState;

import java.io.Serializable;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public class OrderResponse implements Serializable {

    private long orderId;

    private OrderState orderState;

    public OrderResponse() {
    }

    public OrderResponse(long orderId, OrderState orderState) {
        this.orderId = orderId;
        this.orderState = orderState;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
