package sandbox9.thunderbolt.api.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.ApiApplication;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.Sku;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
public class HelloWorldProductData {

    @Autowired
    MongoTemplate m;

    @Autowired
    ProductRepository r;

    @Test
    public void createOneProductData() throws Exception {
        int productId = 101;
        Product p = new Product(productId, "2014년 맥북프로 Mid 신형", "-", "설명..블라블라..", "Apple Inc.", 33568, new String[]{});
        p.addSku(new Sku(1001, "13인치 기본형", "최신 애플 13인치 기본형 (신상)", 1000L, 100, 20));
        p.addSku(new Sku(1002, "15인치 기본형", "최신 애플 15인치 기본형 (신상)", 2000L, 100, 100));
        p.addSku(new Sku(1003, "15인치 고급형", "최신 애플 15인치 고급형 (신상)", 3000L, 100, 500));
        p.setStandardSkuId(1001);

        r.save(p);

        // 약식 비교
        Product saved = r.findOne(productId);
        assertNotNull(saved);
        assertTrue(3 == saved.getSkuList().size());
    }
}
