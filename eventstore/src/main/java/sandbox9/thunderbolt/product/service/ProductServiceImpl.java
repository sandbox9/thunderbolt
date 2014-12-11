package sandbox9.thunderbolt.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;
import sandbox9.thunderbolt.product.event.finder.ProductPricingEventProcessor;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductRepository r;

    @Autowired
    private ProductPricingEventProcessor eventProcessor;

    @Override
    public List<Product> find(int pageNumber, int pageSize) {
        Page<Product> productPage = r.findAll(new PageRequest(pageNumber, pageSize));
        List<Product> productList = productPage.getContent();

        eventProcessor.processing(productList);

        return productList;
    }
}
