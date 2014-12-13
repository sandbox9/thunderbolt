package sandbox9.thunderbolt.entity.catalog.client;

import sandbox9.thunderbolt.entity.catalog.Catalog;

/**
 * Created by chanwook on 2014. 12. 12..
 */
public interface CatalogApi {
    Catalog getOne(String catalogId);
}
