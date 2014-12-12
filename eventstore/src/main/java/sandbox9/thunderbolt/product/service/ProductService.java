package sandbox9.thunderbolt.product.service;

import sandbox9.thunderbolt.entity.product.Product;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 6..
 */
public interface ProductService {

    List<Product> find(int pageNumber, int pageSize);

}
