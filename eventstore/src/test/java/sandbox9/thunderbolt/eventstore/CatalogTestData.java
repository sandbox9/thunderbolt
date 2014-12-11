package sandbox9.thunderbolt.eventstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.Application;
import sandbox9.thunderbolt.entity.catalog.Catalog;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;
import sandbox9.thunderbolt.entity.catalog.repository.CatalogRepository;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;

import java.util.Date;

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
    CatalogRepository catalogRepository;

    @Test
    public void createOneProductData() throws Exception {
        int productId = 101;
        String catalogId = "C1001";
        String displayCategoryId = "D1001";

        Product master = productRepository.findOne(productId);

        Catalog catalog = new Catalog(catalogId, displayCategoryId, new Date());
        CatalogProduct catalogProduct = createCatalogProduct(master);
        catalogProduct.setLowestSalePrice(master.seekLowestSalePrice());
        catalogProduct.setStandardSku(master.getStandardSku());

        catalog.addProduct(catalogProduct);

        catalogRepository.save(catalog);

        // assertion
        Catalog saved = catalogRepository.findOne(catalogId);
        assertNotNull(saved);
        assertTrue(1 == saved.getProductList().size());
        assertTrue(master.seekLowestSalePrice() == saved.getProductList().get(0).getLowestSalePrice());
        assertEquals(master.getStandardSku().getSkuId(), saved.getProductList().get(0).getStandardSku().getSkuId());
    }

    private CatalogProduct createCatalogProduct(Product master) {
        return new CatalogProduct(master.getProductId(), master.getProductName(), master.getDisplayName(), master.getManufacturer());
    }
}
