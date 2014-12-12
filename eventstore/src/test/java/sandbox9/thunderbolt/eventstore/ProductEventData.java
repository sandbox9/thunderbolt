package sandbox9.thunderbolt.eventstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.Application;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.message.product.ProductSkuStockEvent;

import java.util.Date;

import static sandbox9.thunderbolt.message.product.EventCalculationType.MINUS;
import static sandbox9.thunderbolt.message.product.EventCalculationType.PLUS;

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

        // 가격 이벤트

        m.save(new ProductSkuPriceEvent(101, 1001, PLUS, 100, new Date()));
        m.save(new ProductSkuPriceEvent(101, 1002, PLUS, 100, new Date()));
        m.save(new ProductSkuPriceEvent(101, 1003, PLUS, 100, new Date()));

        // 재고 이벤트
        m.save(new ProductSkuStockEvent(101, 1001, MINUS, 5, new Date()));
        m.save(new ProductSkuStockEvent(101, 1002, MINUS, 5, new Date()));
        m.save(new ProductSkuStockEvent(101, 1003, MINUS, 5, new Date()));
    }
}
