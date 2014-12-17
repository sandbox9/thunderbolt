package sandbox9.thunderbolt.eventstore.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.entity.catalog.Catalog;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;
import sandbox9.thunderbolt.entity.catalog.repository.CatalogProductCustomRepository;
import sandbox9.thunderbolt.event.product.ProductEventStore;
import sandbox9.thunderbolt.event.product.message.ProductEventCommand;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 11..
 */
@Service
public class CatalogAggregatorImpl implements CatalogAggregator {

    @Autowired
    private CatalogProductCustomRepository catalogRepository;

    @Autowired
    private ProductEventStore eventStore;

    @Override
    public Catalog find(String catalogId, int pageNumber, int pageSize) {
        List<CatalogProduct> productList = catalogRepository.find(catalogId, pageNumber, pageSize);

        Catalog catalog = new Catalog(catalogId, productList);

        ProductEventCommand command = new ProductEventCommand(catalog.originalProductList());
        eventStore.process(command);
        catalog.replaceStandardSku(catalog.originalProductList());

        return catalog;
    }
}
