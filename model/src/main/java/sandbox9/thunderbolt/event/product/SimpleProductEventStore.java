package sandbox9.thunderbolt.event.product;

import org.springframework.beans.factory.annotation.Autowired;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.event.product.handler.ProductEventHandler;
import sandbox9.thunderbolt.event.product.message.ProductEvent;
import sandbox9.thunderbolt.event.product.message.ProductEventCommand;
import sandbox9.thunderbolt.event.product.repository.ProductEventRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 16..
 */
public class SimpleProductEventStore implements ProductEventStore {

    @Autowired
    private ProductEventRepository r;

    private Map<ProductEvent.ProductEventType, ProductEventHandler> eventHandlerMap =
            new HashMap<ProductEvent.ProductEventType, ProductEventHandler>();

    @Override
    public void process(ProductEventCommand command) {
        List<ProductEvent> eventList = r.find(command.getProductList());

        for (ProductEvent event : eventList) {
            Product product = command.getProduct(event.getProductId());
            process(event, product);
        }
    }

    @Override
    public void process(ProductEvent event, Product product) {
        eventHandlerMap.get(event.getType()).process(product, event);
    }

    public void addEventHandler(ProductEvent.ProductEventType type,
                                ProductEventHandler handler) {
        //TODO 중복 체크 로직
        this.eventHandlerMap.put(type, handler);
    }
}
