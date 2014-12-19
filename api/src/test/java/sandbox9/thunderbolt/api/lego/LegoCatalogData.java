package sandbox9.thunderbolt.api.lego;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.ApiApplication;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;
import sandbox9.thunderbolt.entity.catalog.repository.CatalogProductMongoRepository;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.repository.ProductMongoRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static sandbox9.thunderbolt.api.lego.LegoProductData.*;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
public class LegoCatalogData {

    @Autowired
    MongoTemplate m;

    @Autowired
    ProductMongoRepository productRepository;

    @Autowired
    CatalogProductMongoRepository catalogProductRepository;

    @Test
    public void createProductData() throws Exception {
        //TODO catalogid와 categoryid는 매핑해서 알도록 분리
        String catalogId = "C1011";
        String displayCategoryId = "D1011";

        cerateAndAssertionCatalogProduct(catalogId, displayCategoryId, "CP" + LEGO_PRODUCT_1, LEGO_PRODUCT_1);
        cerateAndAssertionCatalogProduct(catalogId, displayCategoryId, "CP" + LEGO_PRODUCT_2, LEGO_PRODUCT_2);
        cerateAndAssertionCatalogProduct(catalogId, displayCategoryId, "CP" + LEGO_PRODUCT_3, LEGO_PRODUCT_3);
    }

    private void cerateAndAssertionCatalogProduct(String catalogId, String displayCategoryId,
                                                  String catalogProductId, int productId) {
        CatalogProduct catalogProduct =
                createCatalogProduct(catalogProductId, catalogId, displayCategoryId, productId);

        catalogProductRepository.save(catalogProduct);

        // assertion
        CatalogProduct saved = catalogProductRepository.findOne(catalogProductId);

        assertNotNull(saved);
        assertEquals(catalogProduct.getStandardSku().getSkuId(), saved.getStandardSku().getSkuId());
        assertEquals(catalogId, saved.getCatalogId());
        assertEquals(displayCategoryId, saved.getDisplayCategoryId());
    }

    private CatalogProduct createCatalogProduct(String catalogProductId, String catalogId, String displayCategoryId, int productId) {
        Product master = productRepository.findOne(productId);

        CatalogProduct catalogProduct = new CatalogProduct(catalogProductId, master.getProductId(), catalogId, displayCategoryId, master.getProductName(),
                master.getDisplayName(), master.getStandardSku(), master.getManufacturer());
        catalogProduct.setStandardSku(master.getStandardSku());
        catalogProduct.setDisplayCategoryId(displayCategoryId);
        return catalogProduct;
    }
}
