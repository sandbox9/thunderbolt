import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.Application;
import sandbox9.thunderbolt.product.repository.ProductRepository;
import sandbox9.thunderbolt.product.model.Product;
import sandbox9.thunderbolt.product.model.Sku;

import java.util.Date;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProductTestData {

    @Autowired
    MongoTemplate m;

    @Autowired
    ProductRepository r;

    @Test
    public void createProductData() throws Exception {
        r.deleteAll();

        Product p = new Product(101, "sample product", "설명..블라블라..", new Date(), new Date());
        p.addSku(new Sku(1001, "sample sku1", 1000L, 100L, new Date()));
        p.addSku(new Sku(1002, "sample sku2", 1000L, 100L, new Date()));
        p.addSku(new Sku(1003, "sample sku3", 1000L, 100L, new Date()));

        r.save(p);
    }
}
