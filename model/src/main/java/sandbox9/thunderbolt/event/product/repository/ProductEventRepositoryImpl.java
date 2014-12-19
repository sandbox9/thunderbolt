package sandbox9.thunderbolt.event.product.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.event.product.message.ProductEvent;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by chanwook on 2014. 12. 16..
 */
@Repository
public class ProductEventRepositoryImpl implements ProductEventCustomRepository {

    @Autowired
    private MongoTemplate t;

    @Override
    public List<ProductEvent> find(List<Product> productList) {
        List<Integer> idList = new ArrayList<Integer>();
        for (Product p : productList) {
            idList.add(p.getProductId());
        }

        Query query = query(where("productId").in(idList));
        List<ProductEvent> eventList = t.find(query, ProductEvent.class);
        return eventList;
    }
}
