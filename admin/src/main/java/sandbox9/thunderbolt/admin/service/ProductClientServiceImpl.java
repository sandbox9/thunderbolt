package sandbox9.thunderbolt.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sandbox9.thunderbolt.entity.product.Product;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 12..
 */
@Service
public class ProductClientServiceImpl implements ProductClientService {

    RestTemplate t = new RestTemplate();

    @Override
    public List<Product> getProductAll() {

        return null;
    }

    @Override
    public Product getProduct(int productId) {
        Product product = t.getForObject("http://localhost:8080/product/{productId}", Product.class, productId);
        return product;
    }
}
