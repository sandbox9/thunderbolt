package sandbox9.thunderbolt.entity.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sandbox9.thunderbolt.entity.catalog.Catalog;

/**
 * Created by chanwook on 2014. 12. 10..
 */
public interface CatalogRepository extends MongoRepository<Catalog, String> {
}
