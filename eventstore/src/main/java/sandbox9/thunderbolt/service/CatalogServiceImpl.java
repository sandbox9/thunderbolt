package sandbox9.thunderbolt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.entity.catalog.Catalog;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;
import sandbox9.thunderbolt.entity.catalog.repository.CatalogProductCustomRepository;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.event.process.EventProcessor;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 11..
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogProductCustomRepository repository;

    @Autowired
    private EventProcessor eventProcessor;

    @Override
    public Catalog find(String catalogId, int pageNumber, int pageSize) {
        List<CatalogProduct> productList = repository.find(catalogId, pageNumber, pageSize);

        Catalog catalog = new Catalog(catalogId, productList);

        List<Product> originalProductList = catalog.originalProductList();
        eventProcessor.process(originalProductList);
        catalog.replaceStandardSku(originalProductList);

        return catalog;
    }
}
