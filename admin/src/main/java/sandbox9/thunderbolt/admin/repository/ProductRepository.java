package sandbox9.thunderbolt.admin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sandbox9.thunderbolt.product.model.Product;

/**
 * Created by chanwook on 2014. 12. 5..
 */
public interface ProductRepository extends MongoRepository<Product, Integer> {
}
