package sandbox9.thunderbolt.event.product.repository;

import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.event.product.message.ProductEvent;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 16..
 */
public interface ProductEventCustomRepository {
    List<ProductEvent> find(List<Product> productEventCommand);
}
