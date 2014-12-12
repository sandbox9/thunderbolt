package sandbox9.thunderbolt.entity.product;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chanwook on 2014. 12. 5..
 */
public class Sku implements Serializable {

    private int skuId;

    private String skuName;

    private String displayName;

    private long salePrice;

    // 재고: 몇 개 남았냐. 판매 가능한 상품이 몇 개 남았는지
    private long stock;

    // 판매량: 몇 개 팔았냐. 현재 몰에서 상품을 몇 개 주문 처리 했는지
    private long saleVolume;

    private Date createdDate;

    public Sku() {
    }

    public Sku(int skuId, String skuName, String displayName, long salePrice, long stock, long saleVolume, Date createdDate) {
        this.skuId = skuId;
        this.skuName = skuName;
        this.displayName = displayName;
        this.salePrice = salePrice;
        this.saleVolume = saleVolume;
        this.stock = stock;
        this.createdDate = createdDate;
    }

    public Sku(Sku source) {
        this.skuId = source.getSkuId();
        this.skuName = source.getSkuName();
        this.displayName = source.getDisplayName();
        this.salePrice = source.getSalePrice();
        this.saleVolume = source.getSaleVolume();
        this.stock = source.getStock();
        this.createdDate = source.getCreatedDate();
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(long salePrice) {
        this.salePrice = salePrice;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(long saleVolume) {
        this.saleVolume = saleVolume;
    }

    public void plusSalePrice(int additionalPrice) {
        setSalePrice(this.salePrice + additionalPrice);
    }

    public void minusStock(long volume) {
        setStock(this.stock - volume);
    }

}
