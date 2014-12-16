package sandbox9.thunderbolt.event.product.message;

import sandbox9.thunderbolt.entity.product.Product;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 16..
 */
public class ProductEventCommand {
    private final List<Product> productList;

    public ProductEventCommand(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProduct(int productId) {
        for (Product p : this.productList) {
            if (productId == p.getProductId()) {
                return p;
            }
        }
        throw new IllegalArgumentException(productId + "에 해당하는 상품 정보가 존재하지 않습니다.");
    }
}
