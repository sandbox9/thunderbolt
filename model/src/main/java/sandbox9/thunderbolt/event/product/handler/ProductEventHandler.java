package sandbox9.thunderbolt.event.product.handler;

import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.event.product.message.ProductEvent;

/**
 * Created by chanwook on 2014. 12. 16..
 */
public interface ProductEventHandler {
    void process(Product product, ProductEvent event);
}
