package sandbox9.thunderbolt.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.Application;
import sandbox9.thunderbolt.admin.model.ProductViewModel;
import sandbox9.thunderbolt.admin.model.ProductViewModelMap;
import sandbox9.thunderbolt.admin.web.ProductViewController;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.Sku;
import sandbox9.thunderbolt.entity.product.client.ProductApi;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 9..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class ProductEventCreateTests {
    @Autowired
    ProductViewController c;

    @Autowired
    ProductRepository r;

    @Autowired
    ProductApi clientService;

    @Test
    public void sendProductChangeEvent() throws Exception {
        for (int i = 0; i < 10; i++) {

            ProductViewModelMap modelMap = new ProductViewModelMap();
            ProductViewModel model = createOriginalProduct();

            // sku 정보 업데이트
            List<Sku> updateSkuList = createUpdateSkuList(model);
            for (Sku sku : updateSkuList) {
                sku.plusSalePrice(100); // 100원씩 가격 올리기
                sku.minusStock(5); // 재고 5 감소
            }
            model.setUpdate(true);
            model.setUpdateSkuList(updateSkuList);
            modelMap.addProduct(model);

            c.updateProduct(modelMap);

            Thread.sleep(1000L);
        }
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
        //TODO 이렇게 하면 안되고 event 처리가 같이 들어간 로직을 호출해서 사용해야 함 @@
        Product p = clientService.getOne(101);

        //TODO 필드 변경 시마다 해주지 않도록 객체 복사 기능 적용
        model.setProductId(p.getProductId());
        model.setProductName(p.getProductName());
        model.setSkuList(p.getSkuList());
        model.setDescription(p.getDescription());
        model.setDisplayName(p.getDisplayName());
        model.setManagementCategoryId(p.getManagementCategoryId());
        model.setManufacturer(p.getManufacturer());
        model.setStandardSkuId(p.getStandardSkuId());

        return model;
    }
}
