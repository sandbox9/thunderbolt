package sandbox9.thunderbolt.api.order.service;

import sandbox9.thunderbolt.api.order.model.Order;

/**
 * Created by chanwook on 2014. 12. 18..
 */
public interface OrderService {
    Order getCart(String customerId);
}
