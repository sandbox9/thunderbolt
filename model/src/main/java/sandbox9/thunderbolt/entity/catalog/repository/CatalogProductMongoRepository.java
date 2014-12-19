package sandbox9.thunderbolt.entity.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;

/**
 * Created by chanwook on 2014. 12. 11..
 */
public interface CatalogProductMongoRepository extends MongoRepository<CatalogProduct, String> {

}
