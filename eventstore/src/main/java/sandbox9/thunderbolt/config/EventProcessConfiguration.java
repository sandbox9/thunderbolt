package sandbox9.thunderbolt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox9.thunderbolt.event.process.EventProcessor;
import sandbox9.thunderbolt.event.process.SimpleEventProcessor;
import sandbox9.thunderbolt.event.process.activity.ProductPriceEventActivity;
import sandbox9.thunderbolt.event.process.activity.ProductStockEventActivity;

/**
 * Created by chanwook on 2014. 12. 15..
 */
@Configuration
public class EventProcessConfiguration {

    @Bean
    public EventProcessor productEventProcessor() {
        EventProcessor processor = new SimpleEventProcessor();
        processor.addActivity(priceEventActivity());
        processor.addActivity(stockEventActivity());

        return processor;
    }

    @Bean
    public ProductPriceEventActivity priceEventActivity() {
        return new ProductPriceEventActivity();
    }

    @Bean
    public ProductStockEventActivity stockEventActivity() {
        return new ProductStockEventActivity();
    }
}
