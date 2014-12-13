package sandbox9.thunderbolt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;
import sandbox9.thunderbolt.event.process.EventProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository r;

    @Autowired
    private EventProcessor eventProcessor;

    @Override
    public List<Product> find(int pageNumber, int pageSize) {
        Page<Product> productPage = r.findAll(new PageRequest(pageNumber, pageSize));
        List<Product> productList = productPage.getContent();

        eventProcessor.process(productList);

        return productList;
    }

    @Override
    public Product findOne(int productId) {
        Product product = r.findOne(productId);
        eventProcessor.process(Arrays.asList(product));
        return product;
    }
}
