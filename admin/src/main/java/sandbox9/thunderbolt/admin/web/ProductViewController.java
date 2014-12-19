package sandbox9.thunderbolt.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sandbox9.thunderbolt.admin.model.ProductViewModelMap;
import sandbox9.thunderbolt.admin.service.ProductEventHandleService;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.client.ProductApi;
import sandbox9.thunderbolt.entity.product.repository.ProductMongoRepository;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 8..
 */
@Controller
public class ProductViewController {

    @Autowired
    private ProductMongoRepository pr;

    @Autowired
    private ProductEventHandleService eventService;

    @Autowired
    private ProductApi clientService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String viewProductPage(ModelMap model) {
        // 어디서 상품 정보를 가져올 것인가? (일단 Product 상품을 기준이라고 하고 매장을 Snapshot으로 보는 것이 어떨까?)
        List<Product> list = clientService.getProductAll();
        model.put("prdList", list);
        return "";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String updateProduct(ProductViewModelMap model) {
        // 화면에서 변경 정보를 가지고 온다

        eventService.handle(model);

        return "redirect:/products";
    }
}
