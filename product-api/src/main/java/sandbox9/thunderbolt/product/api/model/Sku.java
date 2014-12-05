package sandbox9.thunderbolt.product.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chanwook on 2014. 12. 5..
 */
public class Sku implements Serializable {

    private int skuId;

    private String skuName;

    private long salePrice;

    private long stock;

    private Date createdDate;

    public Sku() {
    }

    public Sku(int skuId, String skuName, long salePrice, long stock, Date createdDate) {
        this.skuId = skuId;
        this.skuName = skuName;
        this.salePrice = salePrice;
        this.stock = stock;
        this.createdDate = createdDate;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
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
}
