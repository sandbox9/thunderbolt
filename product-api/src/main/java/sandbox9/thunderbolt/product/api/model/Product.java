package sandbox9.thunderbolt.product.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 5..
 */
public class Product implements Serializable {

    private int productId;

    private String productName;

    private String description;

    private Date openDate;

    private Date endDate;

    private List<Sku> skuList = new ArrayList<Sku>();

    public Product() {
    }

    public Product(int productId, String productName, String description, Date openDate, Date endDate, List<Sku> skuList) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.openDate = openDate;
        this.endDate = endDate;
        this.skuList = skuList;
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

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
