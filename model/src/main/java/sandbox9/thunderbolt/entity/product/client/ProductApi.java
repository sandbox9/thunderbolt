package sandbox9.thunderbolt.entity.product.client;

import sandbox9.thunderbolt.entity.product.Product;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 12..
 */
public interface ProductApi {
    List<Product> getProductAll();

    Product getOne(int productId);
}
