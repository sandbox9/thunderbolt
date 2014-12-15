package sandbox9.thunderbolt.eventstore.lego;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.EventStoreApplication;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.message.product.ProductSkuStockEvent;

import java.util.Date;

import static sandbox9.thunderbolt.eventstore.lego.LegoProductData.*;
import static sandbox9.thunderbolt.message.product.EventCalculationType.MINUS;
import static sandbox9.thunderbolt.message.product.EventCalculationType.PLUS;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EventStoreApplication.class)
public class LegoProductEvent {

    @Autowired
    MongoTemplate m;

    @Test
    public void createEvent() throws Exception {
        m.dropCollection(ProductSkuPriceEvent.class);
        m.dropCollection(ProductSkuStockEvent.class);

        // 가격 이벤트
        m.save(new ProductSkuPriceEvent(LEGO_PRODUCT_1, SKU_1_1, PLUS, 10, new Date()));

        // 재고 이벤트
        m.save(new ProductSkuStockEvent(LEGO_PRODUCT_1, SKU_1_1, MINUS, 10, new Date()));
        m.save(new ProductSkuStockEvent(LEGO_PRODUCT_1, SKU_1_2, MINUS, 7, new Date()));
        m.save(new ProductSkuStockEvent(LEGO_PRODUCT_1, SKU_1_3, MINUS, 3, new Date()));
    }
}
