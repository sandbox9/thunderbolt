package sandbox9.thunderbolt.event.repository;

import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.message.product.ProductEventKey;
import sandbox9.thunderbolt.message.product.ProductSkuEvent;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.message.product.ProductSkuStockEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 6..
 */
public interface ProductEventRepository {
    Map<Integer, List<ProductSkuEvent>> findEvent(List<Product> productList, ProductEventKey eventKey);

    Map<Integer, List<ProductSkuEvent>> findEventById(List<Integer> idList, ProductEventKey eventKey);

    void save(ProductSkuPriceEvent event);

    void save(ProductSkuStockEvent event);
}
