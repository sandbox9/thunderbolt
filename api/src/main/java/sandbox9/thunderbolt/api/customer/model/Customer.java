package sandbox9.thunderbolt.api.customer.model;

import sandbox9.thunderbolt.api.order.model.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 18..
 */
@Entity
public class Customer implements Serializable {

    @Id
    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @OneToMany
    private List<Address> addressList = new ArrayList<Address>();

    public Customer() {
    }

    public Customer(String customerId, String customerName, List<Address> addressList) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressList = addressList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
