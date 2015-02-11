package sandbox9.thunderbolt.api.order.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chanwook on 2014. 12. 18..
 */
@Entity
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderItemId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @OneToOne(mappedBy = "orderItem", cascade = CascadeType.ALL)
    private OrderedSku orderedSku;

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderedSku(OrderedSku orderedSku) {
        this.orderedSku = orderedSku;
    }

    public OrderedSku getOrderedSku() {
        return orderedSku;
    }
}
