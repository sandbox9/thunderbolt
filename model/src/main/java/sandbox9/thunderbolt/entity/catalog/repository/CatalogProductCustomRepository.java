package sandbox9.thunderbolt.entity.catalog.repository;

import sandbox9.thunderbolt.entity.catalog.CatalogProduct;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 10..
 */
public interface CatalogProductCustomRepository {
    List<CatalogProduct> find(String catalogId, int pageNumber, int pageSize);
}
