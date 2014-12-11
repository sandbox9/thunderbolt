package sandbox9.thunderbolt.product.repository;

import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 6..
 */
public interface ProductEventRepository {
    Map<Integer, List<ProductSkuPriceEvent>> findEvent(List<Product> productList);

    Map<Integer, List<ProductSkuPriceEvent>> findEventById(List<Integer> productIdList);

    void save(ProductSkuPriceEvent event);
}
