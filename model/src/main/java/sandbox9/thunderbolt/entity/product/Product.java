package sandbox9.thunderbolt.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 5..
 */
@Document
public class Product implements Serializable {

    @Id
    private int productId;

    private String productName;

    private String displayName;

    private String description;

    private List<Sku> skuList = new ArrayList<Sku>();

    private String[] images;

    // 제조사
    @Indexed
    private String manufacturer;

    // 상품 관리 카테고리 ID
    @Indexed
    private int managementCategoryId;

    // 상품의 대표 Sku ID 지정. 지정하지 않았을 경우 skuList의 첫 번째 sku를 대표로 사용함
    @Indexed
    private int standardSkuId;

    public Product() {
    }

    public Product(int productId, String productName, String displayName, String description,
                   String manufacturer, int managementCategoryId, String[] images) {
        this.productId = productId;
        this.productName = productName;
        this.displayName = displayName;
        this.description = description;
        this.manufacturer = manufacturer;
        this.managementCategoryId = managementCategoryId;
        this.images = images;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    public void addSku(Sku sku) {
        this.skuList.add(sku);
    }

    public int getManagementCategoryId() {
        return managementCategoryId;
    }

    public int getStandardSkuId() {
        return standardSkuId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setManagementCategoryId(int managementCategoryId) {
        this.managementCategoryId = managementCategoryId;
    }

    public void setStandardSkuId(int standardSkuId) {
        this.standardSkuId = standardSkuId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Sku getSku(int skuId) {
        for (Sku sku : skuList) {
            if (skuId == sku.getSkuId()) {
                return sku;
            }
        }
        return null;
    }

    @JsonIgnore
    public Sku getStandardSku() {
        if (getStandardSkuId() <= 0) {
            throw new IllegalArgumentException("상품 ID " + getProductId() + "에 해당하는 Standard Sku id가 지정되지 않았습니다!");
        }
        return getSku(getStandardSkuId());
    }
}
