package sandbox9.thunderbolt.product.api.service;

import sandbox9.thunderbolt.product.api.model.Product;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 6..
 */
public interface ProductService {
    List<Product> find(int pageNumber, int pageSize);
}
