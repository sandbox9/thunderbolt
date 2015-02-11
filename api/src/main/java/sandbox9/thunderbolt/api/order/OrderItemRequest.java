package sandbox9.thunderbolt.api.order;

import java.io.Serializable;

/**
 * Created by chanwook on 2014. 12. 23..
 */
public class OrderItemRequest implements Serializable {

    private long orderId;

    private int skuId;

    //TODO 중복되는 프로퍼티를 OrderedSku와 합칠 것인가?
    // 주문 단위 가격 (현재는 동일한 가격만 가능). 100원을 2개 주만하면 단위 가격은 100원 총 주문 금액은 200원
    private long unitPrice;

    // 주문수량
    private long orderedVolume;

    // 총 주문 가격
    private long totalPrice;

    public OrderItemRequest(long orderId, int skuId) {
        this.orderId = orderId;
        this.skuId = skuId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getOrderedVolume() {
        return orderedVolume;
    }

    public void setOrderedVolume(long orderedVolume) {
        this.orderedVolume = orderedVolume;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void calculateTotalPrice() {
        this.totalPrice = getUnitPrice() * getOrderedVolume();
    }

    static enum OrderItemRequestType {
        ADD_ITEM;
    }
}
