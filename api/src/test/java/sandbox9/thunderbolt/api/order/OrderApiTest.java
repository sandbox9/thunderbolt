package sandbox9.thunderbolt.api.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import sandbox9.thunderbolt.ApiApplication;
import sandbox9.thunderbolt.api.lego.LegoProductData;
import sandbox9.thunderbolt.api.order.message.OrderRequest;
import sandbox9.thunderbolt.api.order.model.Order;
import sandbox9.thunderbolt.api.order.model.OrderItem;
import sandbox9.thunderbolt.api.order.model.OrderState;
import sandbox9.thunderbolt.api.order.model.OrderedSku;

import static org.junit.Assert.*;
import static sandbox9.thunderbolt.api.order.OrderItemRequest.OrderItemRequestType.ADD_ITEM;
import static sandbox9.thunderbolt.api.order.message.OrderRequestType.CART_CREATE;

/**
 * Created by chanwook on 2014. 12. 23..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
public class OrderApiTest {

    public static final String CUSTOMER_ID = "user01";
    public static final String HTTP_GET_CART = "http://localhost:10002/cart?customerId=" + CUSTOMER_ID;

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    @Qualifier("orderQueueTemplate")
    RabbitTemplate rabbitTemplate;

    @Test
    public void createAndGetCart() throws Exception {
        // cart 조회 - 없음
        ResponseEntity<Order> cartEntity = restTemplate.getForEntity(HTTP_GET_CART, Order.class);
        assertEquals(HttpStatus.MOVED_PERMANENTLY, cartEntity.getStatusCode());

        // cart 생성 op
        OrderResponse response = (OrderResponse) rabbitTemplate.convertSendAndReceive(CART_CREATE.name(),
                new OrderRequest(CUSTOMER_ID));

        assertNotNull(response.getOrderId());
        assertEquals(OrderState.IN_CART, response.getOrderState());

        // 다시 cart 조회
        cartEntity = restTemplate.getForEntity(HTTP_GET_CART, Order.class);

        Order cart = cartEntity.getBody();
        assertCart(response, cart);
        assertTrue(0 == cart.getOrderItemList().size());

        // add item - 일단 이 요청은 reply 처리를 해보지 말자
        int targetSkuId = LegoProductData.SKU_1_1;
        OrderItemRequest orderItemRequest = new OrderItemRequest(cart.getOrderId(), targetSkuId);
        orderItemRequest.setOrderedVolume(2);
        orderItemRequest.setUnitPrice(1000); // sku 가격을 가져고 와서 테스트에서 입력?
        orderItemRequest.calculateTotalPrice();
        rabbitTemplate.convertAndSend(ADD_ITEM.name(), orderItemRequest);

        // 다시 조회
        cartEntity = restTemplate.getForEntity(HTTP_GET_CART, Order.class);
        assertCart(response, cart);
        assertTrue(1 == cart.getOrderItemList().size());
        OrderItem orderItem = cart.getOrderItemList().get(0);
        OrderedSku orderedSku = orderItem.getOrderedSku();
        assertEquals(targetSkuId, orderedSku.getTargetSku().getSkuId());
        assertEquals(orderItemRequest.getTotalPrice(), orderedSku.getTotalPrice());
        assertEquals(orderItemRequest.getOrderedVolume(), orderedSku.getOrderedVolume());
        assertEquals(orderItemRequest.getUnitPrice(), orderedSku.getUnitPrice());

    }

    private void assertCart(OrderResponse response, Order cart) {
        assertEquals(response.getOrderId(), cart.getOrderId());
        assertEquals(OrderState.IN_CART, cart.getState());
        assertEquals(CUSTOMER_ID, cart.getCustomer().getCustomerId());
    }
}
