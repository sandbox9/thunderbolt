package sandbox9.thunderbolt.entity.catalog;

import sandbox9.thunderbolt.entity.product.Product;

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

    public List<Product> originalProductList() {
        List<Product> productList = new ArrayList<Product>();
        for (CatalogProduct p : this.productList) {
            Product original = p.createOriginalProduct();
            productList.add(original);
        }
        return productList;
    }

    public void replaceStandardSku(List<Product> productList) {
        for (Product p : productList) {
            CatalogProduct cp = getCatalogProductById(p.getProductId());
            cp.setStandardSku(p.getStandardSku());
        }
    }

    private CatalogProduct getCatalogProductById(int productId) {
        for (CatalogProduct cp : this.productList) {
            if (productId == cp.getProductId()) {
                return cp;
            }
        }
        throw new IllegalArgumentException(productId + "에 해당하는 CatalogProduct가 존재하지 않습니다.");
    }
}
