package sandbox9.thunderbolt.api.order.repository;

import sandbox9.thunderbolt.api.order.model.Order;
import sandbox9.thunderbolt.api.order.model.OrderState;

/**
 * Created by chanwook on 2014. 12. 20..
 */
public interface OrderJpaRepositoryCustom {
    Order findOne(String customerId, OrderState state);
}
