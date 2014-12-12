package sandbox9.thunderbolt.admin.service;

import sandbox9.thunderbolt.entity.product.Product;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 12..
 */
public interface ProductClientService {
    List<Product> getProductAll();

    Product getProduct(int productId);
}
