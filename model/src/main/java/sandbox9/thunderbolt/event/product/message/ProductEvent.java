package sandbox9.thunderbolt.event.product.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chanwook on 2014. 12. 16..
 */
@Document
public class ProductEvent implements Serializable {

    @Id
    private String id;

    @Indexed
    private int productId;

    @Indexed
    private int skuId;

    private Object value;

    private ProductEventType type;

    private EventCalculationType calculationType;

    private Date created;

    public static enum ProductEventType {
        STOCK, PRICE;
    }

    public ProductEvent() {
    }

    public ProductEvent(int productId, int skuId, Object value, ProductEventType type,
                        EventCalculationType calculationType, Date created) {
        this.productId = productId;
        this.skuId = skuId;
        this.value = value;
        this.type = type;
        this.calculationType = calculationType;
        this.created = created;
    }

    public ProductEvent(String id, int productId, int skuId, Object value, ProductEventType type, EventCalculationType calculationType, Date created) {
        this.id = id;
        this.productId = productId;
        this.skuId = skuId;
        this.value = value;
        this.type = type;
        this.calculationType = calculationType;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ProductEventType getType() {
        return type;
    }

    public void setType(ProductEventType type) {
        this.type = type;
    }

    public EventCalculationType getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(EventCalculationType calculationType) {
        this.calculationType = calculationType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
