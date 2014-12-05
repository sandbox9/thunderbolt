package sandbox9.thunderbolt.product.api.repository;

import sandbox9.thunderbolt.product.api.model.Product;
import sandbox9.thunderbolt.product.message.ProductSkuPriceEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 6..
 */
public interface ProductEventRepository {

    Map<String, List<ProductSkuPriceEvent>> findEvent(List<Product> productList);
}
