package sandbox9.thunderbolt.api.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.api.customer.model.Customer;
import sandbox9.thunderbolt.api.order.model.Order;
import sandbox9.thunderbolt.api.order.model.OrderItem;
import sandbox9.thunderbolt.api.order.model.OrderState;
import sandbox9.thunderbolt.api.order.model.OrderedSku;
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
        return cart;
    }

    @Override
    public Order createCart(String customerId) {
        Customer customer = customerRepository.findOne(customerId);
        // 이런 상태 변경을 하나의 지점으로 어떻게 합칠 것인가?
        Order cart = new Order(customer);
        orderJpaRepository.save(cart);
        return cart;
    }

    @Override
    public Order addItem(long orderId, int skuId) {
        Order cart = orderJpaRepository.findOne(orderId);
        if (cart == null) {
            //TODO HTTP 에러코드 처리
            throw new RuntimeException("아직 cart가 생성되지 않았습니다! cart를 먼저 생성해주세요");
        } else if (!cart.inState(OrderState.IN_CART)) {
            throw new RuntimeException("주문이 CART(OrderState.IN_CART) 상태일 때만 주문 ITEM 추가가가능합니다!(현재 상태: " + cart.getState() + ")");
        }

        OrderItem item = createOrderItem(cart, skuId);
        cart.addItem(item);
        orderJpaRepository.save(cart);
        return cart;
    }

    private OrderItem createOrderItem(Order cart, int skuId) {
        OrderItem item = new OrderItem();
        item.setOrder(cart);
        OrderedSku orderedSku = new OrderedSku();
        orderedSku.setOrderItem(item);
        // sku 조회 및 세팅
        // 나머지 추가
        item.setOrderedSku(orderedSku);
        return item;
    }
}
