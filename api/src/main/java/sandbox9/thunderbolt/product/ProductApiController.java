package sandbox9.thunderbolt.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.product.service.ProductAggregator;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 5..
 */
@RestController
public class ProductApiController {

    @Autowired
    private ProductAggregator service;

    @RequestMapping(value = "/products")
    public List<Product> getAll(@RequestParam(defaultValue = "0") int pageNumber,
                                @RequestParam(defaultValue = "10") int pageSize) {
        return service.find(pageNumber, pageSize);
    }

    @RequestMapping(value = "/product/{productId}")
    public Product getProduct(@PathVariable("productId") int productId) {
        Product p = service.findOne(productId);
        return p;
    }
}
