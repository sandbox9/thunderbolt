package sandbox9.thunderbolt.eventstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.Application;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;
import sandbox9.thunderbolt.entity.catalog.repository.CatalogProductCustomRepository;
import sandbox9.thunderbolt.entity.catalog.repository.CatalogProductRepository;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;

import static org.junit.Assert.*;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CatalogTestData {

    @Autowired
    MongoTemplate m;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CatalogProductCustomRepository catalogProductCustomRepository;

    @Autowired
    CatalogProductRepository catalogProductRepository;

    @Test
    public void createOneProductData() throws Exception {
        catalogProductRepository.deleteAll();

        int productId = 101;
        //TODO catalogid와 categoryid는 매핑해서 알도록 분리
        String catalogId = "C1001";
        String displayCategoryId = "D1001";
        String catalogProductId = "CP1100";

        Product master = productRepository.findOne(productId);
        CatalogProduct catalogProduct = createCatalogProduct(catalogProductId, catalogId, displayCategoryId, master);
        catalogProduct.setLowestSalePrice(master.seekLowestSalePrice());
        catalogProduct.setStandardSku(master.getStandardSku());
        catalogProduct.setDisplayCategoryId(displayCategoryId);

        catalogProductRepository.save(catalogProduct);

        // assertion
        CatalogProduct saved = catalogProductRepository.findOne(catalogProductId);
        assertNotNull(saved);
        assertTrue(master.seekLowestSalePrice() == saved.getLowestSalePrice());
        assertEquals(master.getStandardSku().getSkuId(), saved.getStandardSku().getSkuId());
        assertEquals(catalogId, saved.getCatalogId());
        assertEquals(displayCategoryId, saved.getDisplayCategoryId());
    }

    private CatalogProduct createCatalogProduct(String catalogProductId, String catalogId, String displayCategoryId, Product master) {
        return new CatalogProduct(catalogProductId, master.getProductId(), catalogId, displayCategoryId, master.getProductName(),
                master.getDisplayName(), master.seekLowestSalePrice(), master.getStandardSku(),
                master.getManufacturer());
    }
}
