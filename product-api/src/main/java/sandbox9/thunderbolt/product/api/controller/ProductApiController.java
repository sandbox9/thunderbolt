package sandbox9.thunderbolt.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sandbox9.thunderbolt.product.api.service.ProductService;
import sandbox9.thunderbolt.product.model.Product;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 5..
 */
@RestController
public class ProductApiController {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/products")
    public List<Product> getAll(@RequestParam(defaultValue = "0") int pageNumber,
                                @RequestParam(defaultValue = "10") int pageSize) {
        return service.find(pageNumber, pageSize);
    }

}
