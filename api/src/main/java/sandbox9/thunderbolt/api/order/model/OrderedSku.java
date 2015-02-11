package sandbox9.thunderbolt.api.order.model;

import sandbox9.thunderbolt.entity.product.Sku;

import javax.persistence.*;

/**
 * Created by chanwook on 2014. 12. 22..
 */
@Entity
public class OrderedSku {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // 주문 단위 가격 (현재는 동일한 가격만 가능). 100원을 2개 주만하면 단위 가격은 100원 총 주문 금액은 200원
    private long unitPrice;

    // 주문수량
    private long orderedVolume;

    // 총 주문 가격
    private long totalPrice;

    private Sku targetSku;

    @OneToOne
    private OrderItem orderItem;

    public OrderedSku() {
    }

    public OrderedSku(long unitPrice, long orderedVolume, long totalPrice, Sku targetSku, OrderItem orderItem) {
        this.unitPrice = unitPrice;
        this.orderedVolume = orderedVolume;
        this.totalPrice = totalPrice;
        this.targetSku = targetSku;
        this.orderItem = orderItem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Sku getTargetSku() {
        return targetSku;
    }

    public void setTargetSku(Sku targetSku) {
        this.targetSku = targetSku;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
}
