package sandbox9.thunderbolt.message.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by chanwook on 2014. 12. 11..
 */
@Document
public class ProductSkuStockEvent implements ProductSkuEvent {
    @Id
    private String id;

    @Indexed
    private int productId;

    @Indexed
    private int skuId;

    private EventCalculationType calculationType;

    private long changeValue;

    private Date updatedDate;

    public ProductSkuStockEvent() {
    }

    public ProductSkuStockEvent(int productId, int skuId, EventCalculationType calculationType, long changeValue, Date updatedDate) {
        this.productId = productId;
        this.skuId = skuId;
        this.calculationType = calculationType;
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

    public EventCalculationType getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(EventCalculationType calculationType) {
        this.calculationType = calculationType;
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
        return "ProductSkuStockEvent{" +
                "id='" + id + '\'' +
                ", productId=" + productId +
                ", skuId=" + skuId +
                ", calculationType=" + calculationType +
                ", changeValue=" + changeValue +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
