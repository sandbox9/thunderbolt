package sandbox9.thunderbolt.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.admin.model.ProductViewModel;
import sandbox9.thunderbolt.admin.model.ProductViewModelMap;
import sandbox9.thunderbolt.admin.repository.ProductRepository;
import sandbox9.thunderbolt.admin.web.ProductViewController;
import sandbox9.thunderbolt.product.model.Product;
import sandbox9.thunderbolt.product.model.Sku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 9..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class ProductViewControllerTests {
    @Autowired
    ProductViewController c;

    @Autowired
    ProductRepository r;

    @Test
    public void sendProductChangeEvent() throws Exception {
        ProductViewModelMap modelMap = new ProductViewModelMap();
        ProductViewModel model = createOriginalProduct();

        // sku 정보 업데이트 - 100원씩 가격 올리기
        List<Sku> updateSkuList = createUpdateSkuList(model);
        for (Sku sku : updateSkuList) {
            sku.plusSalePrice(100);
        }
        model.setUpdate(true);
        model.setUpdateSkuList(updateSkuList);
        modelMap.addProduct(model);

        c.updateProduct(modelMap);
    }

    private List<Sku> createUpdateSkuList(ProductViewModel model) {
        List<Sku> skuList = model.getSkuList();
        List<Sku> updateSkuList = new ArrayList<Sku>();
        for (Sku sku : skuList) {
            updateSkuList.add(new Sku(sku));
        }
        return updateSkuList;
    }

    private ProductViewModel createOriginalProduct() {
        ProductViewModel model = new ProductViewModel();
        Product p = r.findOne(101);

        model.setProductId(p.getProductId());
        model.setProductName(p.getProductName());
        model.setSkuList(p.getSkuList());
        model.setDescription(p.getDescription());
        model.setOpenDate(p.getOpenDate());
        model.setEndDate(p.getEndDate());

        return model;
    }
}
