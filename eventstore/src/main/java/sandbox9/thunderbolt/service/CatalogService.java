package sandbox9.thunderbolt.service;

import sandbox9.thunderbolt.entity.catalog.Catalog;

/**
 * Created by chanwook on 2014. 12. 11..
 */
public interface CatalogService {
    Catalog find(String catalogId, int pageNumber, int pageSize);
}