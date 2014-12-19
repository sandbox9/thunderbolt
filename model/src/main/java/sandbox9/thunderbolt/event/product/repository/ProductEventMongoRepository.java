package sandbox9.thunderbolt.event.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sandbox9.thunderbolt.event.product.message.ProductEvent;

/**
 * Created by chanwook on 2014. 12. 16..
 */
public interface ProductEventMongoRepository extends MongoRepository<ProductEvent, String> {

}
