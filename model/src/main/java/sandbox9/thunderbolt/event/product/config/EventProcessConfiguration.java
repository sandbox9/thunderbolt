package sandbox9.thunderbolt.event.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox9.thunderbolt.event.product.ProductEventStore;
import sandbox9.thunderbolt.event.product.SimpleProductEventStore;
import sandbox9.thunderbolt.event.product.handler.ProductPriceEventHandler;
import sandbox9.thunderbolt.event.product.handler.ProductStockEventHandler;
import sandbox9.thunderbolt.event.product.message.ProductEvent;

/**
 * Created by chanwook on 2014. 12. 15..
 */
@Configuration
public class EventProcessConfiguration {

    @Bean
    public ProductEventStore productEventStore() {
        SimpleProductEventStore eventStore = new SimpleProductEventStore();
        eventStore.addEventHandler(ProductEvent.ProductEventType.PRICE, new ProductPriceEventHandler());
        eventStore.addEventHandler(ProductEvent.ProductEventType.STOCK, new ProductStockEventHandler());
        return eventStore;
    }
}
