package sandbox9.thunderbolt.product.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sandbox9.thunderbolt.product.api.model.Product;

/**
 * Created by chanwook on 2014. 12. 5..
 */
public interface ProductRepository extends MongoRepository<Product, Long> {
}
