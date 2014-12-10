package sandbox9.thunderbolt.entity.catalog;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Catalog와 Category는 다르다.
 * Catalog는 DM 카탈로그처럼 상품을 진열한 페이지를 말함.
 * 이건 카테고리에 매핑 될 수도 있고 안 될 수도 있다.
 * Created by chanwook on 2014. 12. 10..
 */
@Document
public class Catalog implements Serializable {

    @Id
    private String catalogId;

    // 전시 카테고리 ID
    @Indexed
    private String displayCategoryId;

    private List<CatalogProduct> productList = new ArrayList<CatalogProduct>();

    private Date openDate;

    private Date endDate;

    private Date created;

    public Catalog() {
    }

    public Catalog(String catalogId, String displayCategoryId, Date openDate, Date endDate, Date created) {
        this.catalogId = catalogId;
        this.displayCategoryId = displayCategoryId;
        this.openDate = openDate;
        this.endDate = endDate;
        this.created = created;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getDisplayCategoryId() {
        return displayCategoryId;
    }

    public void setDisplayCategoryId(String displayCategoryId) {
        this.displayCategoryId = displayCategoryId;
    }

    public List<CatalogProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<CatalogProduct> productList) {
        this.productList = productList;
    }

    public void addProduct(CatalogProduct catalogProduct) {
        this.productList.add(catalogProduct);
    }
}
