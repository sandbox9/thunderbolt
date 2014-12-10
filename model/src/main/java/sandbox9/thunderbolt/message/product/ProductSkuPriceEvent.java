package sandbox9.thunderbolt.message.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Document
public class ProductSkuPriceEvent implements Serializable {

    @Id
    private String id;

    @Indexed
    private int productId;

    @Indexed
    private int skuId;

    private SkuPricingEventType eventType;

    private long changeValue;

    private Date updatedDate;

    public ProductSkuPriceEvent() {
    }

    public ProductSkuPriceEvent(int productId, int skuId, SkuPricingEventType eventType, long changeValue, Date updatedDate) {
        this.productId = productId;
        this.skuId = skuId;
        this.eventType = eventType;
        this.changeValue = changeValue;
        this.updatedDate = updatedDate;
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

    public SkuPricingEventType getEventType() {
        return eventType;
    }

    public void setEventType(SkuPricingEventType eventType) {
        this.eventType = eventType;
    }

    public long getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(long changeValue) {
        this.changeValue = changeValue;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "ProductSkuPriceEvent{" +
                "id='" + id + '\'' +
                ", productId=" + productId +
                ", skuId=" + skuId +
                ", eventType=" + eventType +
                ", changeValue=" + changeValue +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
