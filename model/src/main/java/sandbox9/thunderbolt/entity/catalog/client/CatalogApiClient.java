package sandbox9.thunderbolt.entity.catalog.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sandbox9.thunderbolt.entity.catalog.Catalog;

/**
 * Created by chanwook on 2014. 12. 12..
 */
@Service
public class CatalogApiClient implements CatalogApi {

    @Value("${api.catalog.getone}")
    public String catalogGetOne;

    RestTemplate t = new RestTemplate();

    @Override
    public Catalog getOne(String catalogId) {
        Catalog catalog = t.getForObject(catalogGetOne, Catalog.class, catalogId);
        return catalog;
    }
}
