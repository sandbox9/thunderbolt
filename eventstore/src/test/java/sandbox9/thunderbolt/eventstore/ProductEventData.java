package sandbox9.thunderbolt.eventstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.Application;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.message.product.SkuPricingEventType;

import java.util.Date;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProductEventData {

    @Autowired
    MongoTemplate m;

    @Test
    public void createEvent() throws Exception {
        m.dropCollection(ProductSkuPriceEvent.class);

        ProductSkuPriceEvent e1 = new ProductSkuPriceEvent(101, 1001, SkuPricingEventType.PLUS, 100, new Date());
        ProductSkuPriceEvent e2 = new ProductSkuPriceEvent(101, 1002, SkuPricingEventType.PLUS, 100, new Date());
        ProductSkuPriceEvent e3 = new ProductSkuPriceEvent(101, 1003, SkuPricingEventType.PLUS, 100, new Date());

        m.save(e1);
        m.save(e2);
        m.save(e3);
    }
}
