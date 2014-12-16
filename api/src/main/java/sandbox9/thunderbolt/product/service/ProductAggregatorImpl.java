package sandbox9.thunderbolt.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;
import sandbox9.thunderbolt.event.product.message.ProductEventCommand;
import sandbox9.thunderbolt.event.product.ProductEventStore;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Service
public class ProductAggregatorImpl implements ProductAggregator {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEventStore eventStore;

    @Override
    public List<Product> find(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAll(new PageRequest(pageNumber, pageSize));
        List<Product> productList = productPage.getContent();

        ProductEventCommand command = new ProductEventCommand(productList);
        eventStore.process(command);

        return productList;
    }

    @Override
    public Product findOne(int productId) {
        Product product = productRepository.findOne(productId);
        ProductEventCommand command =
                new ProductEventCommand(Arrays.asList(product));
        eventStore.process(command);
        return product;
    }
}
