package sandbox9.thunderbolt.api.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sandbox9.thunderbolt.entity.catalog.Catalog;
import sandbox9.thunderbolt.api.catalog.service.CatalogAggregator;

/**
 * Created by chanwook on 2014. 12. 5..
 */
@RestController
public class CatalogApiController {

    @Autowired
    private CatalogAggregator service;

    @RequestMapping(value = "/catalog/{catalogId}")
    public Catalog getAll(@PathVariable String catalogId,
                          @RequestParam(defaultValue = "0") int pageNumber,
                          @RequestParam(defaultValue = "10") int pageSize) {
        return service.find(catalogId, pageNumber, pageSize);
    }

}
