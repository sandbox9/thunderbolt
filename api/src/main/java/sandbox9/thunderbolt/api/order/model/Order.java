package sandbox9.thunderbolt.api.order.model;

import sandbox9.thunderbolt.api.customer.model.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 18..
 */
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private long orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

    private OrderState state;

    private Customer customer;

    public Order() {

    }

    public Order(Customer customer) {
        this.customer = customer;
        this.state = OrderStateHandler.createOrder();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean inState(OrderState state) {
        if (this.getState().equals(state)) {
            return true;
        }
        return false;
    }

    public void addItem(OrderItem item) {
        this.orderItemList.add(item);
    }
}
