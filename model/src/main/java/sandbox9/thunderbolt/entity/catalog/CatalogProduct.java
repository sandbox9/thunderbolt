package sandbox9.thunderbolt.entity.catalog;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import sandbox9.thunderbolt.entity.product.Sku;

import java.io.Serializable;

/**
 * Created by chanwook on 2014. 12. 10..
 */
@Document
public class CatalogProduct implements Serializable {

    @Id
    private String catalogProductId;

    @Indexed
    private int productId;

    @Indexed
    private String displayCategoryId;

    @Indexed
    private String catalogId;

    private String productName;

    private String displayName;

    // sku 가격 중 가장 낮은 가격
    private long lowestSalePrice;

    // 대표 Sku를 지정해 처리할 수 있도록함
    private Sku standardSku;

    // 제조사
    @Indexed
    private String manufacturer;

    public CatalogProduct() {
    }

    public CatalogProduct(String catalogProductId, int productId, String catalogId, String displayCategoryId, String productName,
                          String displayName, long lowestSalePrice, Sku standardSku, String manufacturer) {
        this.catalogProductId = catalogProductId;
        this.productId = productId;
        this.catalogId = catalogId;
        this.displayCategoryId = displayCategoryId;
        this.productName = productName;
        this.displayName = displayName;
        this.lowestSalePrice = lowestSalePrice;
        this.standardSku = standardSku;
        this.manufacturer = manufacturer;
    }

    public String getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDisplayCategoryId() {
        return displayCategoryId;
    }

    public void setDisplayCategoryId(String displayCategoryId) {
        this.displayCategoryId = displayCategoryId;
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

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}
