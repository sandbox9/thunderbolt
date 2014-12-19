package sandbox9.thunderbolt.api.order.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sandbox9.thunderbolt.api.order.model.Order;
import sandbox9.thunderbolt.api.order.model.OrderState;

import javax.persistence.EntityManager;

/**
 * Created by chanwook on 2014. 12. 20..
 */
@Repository
public class OrderJpaRepositoryCustomImpl implements OrderJpaRepositoryCustom {
    @Autowired
    private EntityManager em;
    
    @Override
    public Order findOne(String customerId, OrderState state) {
        return null;
    }
}
