package sandbox9.thunderbolt.api.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.ApiApplication;
import sandbox9.thunderbolt.event.product.message.ProductEvent;

import java.util.Date;

import static sandbox9.thunderbolt.event.product.message.EventCalculationType.MINUS;
import static sandbox9.thunderbolt.event.product.message.EventCalculationType.PLUS;
import static sandbox9.thunderbolt.event.product.message.ProductEvent.ProductEventType.PRICE;
import static sandbox9.thunderbolt.event.product.message.ProductEvent.ProductEventType.STOCK;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
public class ProductEventData {

    @Autowired
    MongoTemplate m;

    @Test
    public void createEvent() throws Exception {
        // 가격 이벤트
        m.save(new ProductEvent(101, 1001, 100, PRICE, PLUS, new Date()));
        m.save(new ProductEvent(101, 1002, 100, PRICE, PLUS, new Date()));
        m.save(new ProductEvent(101, 1003, 100, PRICE, PLUS, new Date()));

        // 재고 이벤트
        m.save(new ProductEvent(101, 1001, 10, STOCK, MINUS, new Date()));
        m.save(new ProductEvent(101, 1002, 10, STOCK, MINUS, new Date()));
        m.save(new ProductEvent(101, 1003, 10, STOCK, MINUS, new Date()));
    }
}
