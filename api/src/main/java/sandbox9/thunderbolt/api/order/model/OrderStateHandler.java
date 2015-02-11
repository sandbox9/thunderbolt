package sandbox9.thunderbolt.api.order.model;

/**
 * Created by chanwook on 2014. 12. 22..
 */
//TODO 여러 가지 생각을 반영해보자..
public class OrderStateHandler {
    public static OrderState createOrder() {
        //TODO 히스토리 처리 추가
        return OrderState.IN_CART;
    }
}
