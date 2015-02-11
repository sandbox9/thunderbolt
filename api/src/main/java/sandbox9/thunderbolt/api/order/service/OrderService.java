package sandbox9.thunderbolt.api.order.service;

import sandbox9.thunderbolt.api.order.model.Order;
import sandbox9.thunderbolt.api.order.model.OrderItem;

/**
 * Created by chanwook on 2014. 12. 18..
 */
public interface OrderService {
    Order getCart(String customerId);

    Order createCart(String customerId);

    Order addItem(long cartId, int item);
}
