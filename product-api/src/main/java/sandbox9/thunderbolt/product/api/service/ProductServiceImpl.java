package sandbox9.thunderbolt.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.product.api.model.Product;
import sandbox9.thunderbolt.product.api.repository.ProductEventRepository;
import sandbox9.thunderbolt.product.api.repository.ProductRepository;
import sandbox9.thunderbolt.product.message.ProductSkuPriceEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository r;

    @Autowired
    private ProductEventRepository er;

    @Override
    public List<Product> find(int pageNumber, int pageSize) {
        Page<Product> productPage = r.findAll(new PageRequest(pageNumber, pageSize));
        List<Product> productList = productPage.getContent();

        Map<String, List<ProductSkuPriceEvent>> result = er.findEvent(productList);


        return productList;
    }
}
