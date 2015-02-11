package sandbox9.thunderbolt.api.order.message;

import java.io.Serializable;

/**
 * Created by chanwook on 2014. 12. 22..
 */
public class OrderRequest implements Serializable {

    private String customerId;

    public OrderRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
