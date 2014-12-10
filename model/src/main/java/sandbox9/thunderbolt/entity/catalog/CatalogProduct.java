package sandbox9.thunderbolt.entity.catalog;

import org.springframework.data.mongodb.core.index.Indexed;
import sandbox9.thunderbolt.entity.product.Sku;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chanwook on 2014. 12. 10..
 */
public class CatalogProduct implements Serializable {

    private int productId;

    private String productName;

    private String displayName;

    private Date openDate;

    private Date endDate;

    // sku 가격 중 가장 낮은 가격
    private long lowestSalePrice;

    // 대표 Sku를 지정해 처리할 수 있도록함
    private Sku standardSku;

    // 제조사
    @Indexed
    private String manufacturer;

    public CatalogProduct() {
    }

    public CatalogProduct(int productId, String productName, String displayName, Date openDate, Date endDate, String manufacturer) {
        this.productId = productId;
        this.productName = productName;
        this.displayName = displayName;
        this.openDate = openDate;
        this.endDate = endDate;
        this.manufacturer = manufacturer;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getLowestSalePrice() {
        return lowestSalePrice;
    }

    public void setLowestSalePrice(long lowestSalePrice) {
        this.lowestSalePrice = lowestSalePrice;
    }

    public Sku getStandardSku() {
        return standardSku;
    }

    public void setStandardSku(Sku standardSku) {
        this.standardSku = standardSku;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
