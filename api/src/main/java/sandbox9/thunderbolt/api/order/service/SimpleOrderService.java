package sandbox9.thunderbolt.api.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.api.customer.model.Customer;
import sandbox9.thunderbolt.api.order.model.Order;
import sandbox9.thunderbolt.api.order.model.OrderState;
import sandbox9.thunderbolt.api.order.repository.CustomerJpaRepository;
import sandbox9.thunderbolt.api.order.repository.OrderJpaRepository;
import sandbox9.thunderbolt.api.order.repository.OrderJpaRepositoryCustom;

/**
 * Created by chanwook on 2014. 12. 18..
 */
@Service
public class SimpleOrderService implements OrderService {

    @Autowired
    private OrderJpaRepositoryCustom orderJpaRepositoryCustom;

    @Autowired
    private OrderJpaRepository orderJpaRepository;


    @Autowired
    private CustomerJpaRepository customerRepository;

    @Override
    public Order getCart(String customerId) {
        Order cart = orderJpaRepositoryCustom.findOne(customerId, OrderState.IN_CART);
        if (cart == null) {
            Customer customer = customerRepository.findOne(customerId);
            cart = new Order(customer, OrderState.IN_CART);
            orderJpaRepository.save(cart);
        }
        return cart;
    }
}
