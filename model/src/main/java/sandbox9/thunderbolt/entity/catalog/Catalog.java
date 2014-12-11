package sandbox9.thunderbolt.entity.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Catalog와 Category는 다르다.
 * Catalog는 DM 카탈로그처럼 상품을 진열한 페이지를 말함.
 * 이건 카테고리에 매핑 될 수도 있고 안 될 수도 있다.
 * <p/>
 * Created by chanwook on 2014. 12. 10..
 */
public class Catalog implements Serializable {

    private String catalogId;

    private List<CatalogProduct> productList = new ArrayList<CatalogProduct>();

    public Catalog() {
    }

    public Catalog(String catalogId, List<CatalogProduct> productList) {
        this.catalogId = catalogId;
        this.productList = productList;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
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

    public List<Integer> originalProductList() {
        List<Integer> idList = new ArrayList<Integer>();
        for (CatalogProduct p : productList) {
            idList.add(p.getProductId());
        }
        return idList;
    }
}
