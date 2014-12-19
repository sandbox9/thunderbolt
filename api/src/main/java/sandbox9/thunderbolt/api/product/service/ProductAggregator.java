package sandbox9.thunderbolt.api.product.service;

import sandbox9.thunderbolt.entity.product.Product;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 6..
 */
public interface ProductAggregator {

    List<Product> find(int pageNumber, int pageSize);

    Product findOne(int productId);
}
