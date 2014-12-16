package sandbox9.thunderbolt.event.product;

import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.event.product.message.ProductEvent;
import sandbox9.thunderbolt.event.product.message.ProductEventCommand;

/**
 * Created by chanwook on 2014. 12. 16..
 */
public interface ProductEventStore {
    void process(ProductEventCommand command);

    //TODO 위 메서드와 통합할 것인지??
    void process(ProductEvent event, Product product);
}
