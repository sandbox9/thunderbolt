package sandbox9.thunderbolt.eventstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.EventStoreApplication;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.message.product.ProductSkuStockEvent;

/**
 * Created by chanwook on 2014. 12. 15..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EventStoreApplication.class)
public class DataInit {

    @Autowired
    MongoTemplate m;

    @Test
    public void dropSchema() throws Exception {
        m.dropCollection(Product.class);
        m.dropCollection(CatalogProduct.class);
        m.dropCollection(ProductSkuPriceEvent.class);
        m.dropCollection(ProductSkuStockEvent.class);
    }
}
