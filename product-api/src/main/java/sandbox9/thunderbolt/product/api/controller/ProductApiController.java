package sandbox9.thunderbolt.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sandbox9.thunderbolt.product.api.model.Product;
import sandbox9.thunderbolt.product.api.repository.ProductRepository;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 5..
 */
@RestController
public class ProductApiController {

    @Autowired
    private ProductRepository repository;

    @RequestMapping(value = "/products")
    public List<Product> getAll(@RequestParam(defaultValue = "0") int pageNumber,
                                @RequestParam(defaultValue = "10") int pageSize) {
        Page<Product> productPage = repository.findAll(new PageRequest(pageNumber, pageSize));
        return productPage.getContent();
    }

}
