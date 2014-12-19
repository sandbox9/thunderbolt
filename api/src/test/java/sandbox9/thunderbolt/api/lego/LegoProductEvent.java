package sandbox9.thunderbolt.api.lego;

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
import static sandbox9.thunderbolt.api.lego.LegoProductData.*;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
public class LegoProductEvent {

    @Autowired
    MongoTemplate m;

    @Test
    public void createEvent() throws Exception {
        // 가격 이벤트
        m.save(new ProductEvent(LEGO_PRODUCT_1, SKU_1_1, 10, PRICE, PLUS, new Date()));

        // 재고 이벤트
        m.save(new ProductEvent(LEGO_PRODUCT_1, SKU_1_1, 20, STOCK, MINUS, new Date()));
        m.save(new ProductEvent(LEGO_PRODUCT_1, SKU_1_2, 10, STOCK, MINUS, new Date()));
        m.save(new ProductEvent(LEGO_PRODUCT_1, SKU_1_3, 5, STOCK, MINUS, new Date()));
    }
}
