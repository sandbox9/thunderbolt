package sandbox9.thunderbolt.site.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.client.ProductApi;

/**
 * Created by chanwook on 2014. 12. 12..
 */
@Controller
public class ProductViewController {

    @Autowired
    private ProductApi productApi;

    @RequestMapping(value = "/product/{productId}")
    public String viewCatalog(@PathVariable int productId, ModelMap model) {
        Product product = productApi.getOne(productId);
        model.put("product", product);
        return "prdview";
    }
}
