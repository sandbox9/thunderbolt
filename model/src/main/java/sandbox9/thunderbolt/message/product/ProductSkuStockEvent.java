package sandbox9.thunderbolt.message.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.Sku;

import java.util.Date;

import static sandbox9.thunderbolt.message.product.EventCalculationType.MINUS;
import static sandbox9.thunderbolt.message.product.EventCalculationType.PLUS;

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

    @Override
    public void updateProduct(Product product) {
        Sku sku = product.getSku(this.skuId);
        long current = sku.getStock();
        //TODO 전체 처리 단위를 SKU에서 PRODUT 단위로 변경하기. 그러니 일단은 sku 별로 조회해서 하는 것으로 처리한다잉~
        if (MINUS.equals(this.calculationType)) {
            if (current - this.changeValue < 0) {
                throw new RuntimeException("상품 ID -  " + productId + ", sku ID - " + skuId + " 재고 동기화 작업이 중단됐습니다. 가격이 0 이하로 계산되었습니다.");
            }
            sku.setStock(current - this.changeValue);
        } else if (PLUS.equals(this.calculationType)) {
            sku.setStock(current + this.changeValue);
        }
    }
}
