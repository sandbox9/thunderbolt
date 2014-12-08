package sandbox9.thunderbolt.product.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import sandbox9.thunderbolt.product.api.model.Product;
import sandbox9.thunderbolt.product.message.ProductSkuPriceEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by chanwook on 2014. 12. 8..
 */
@Repository
public class ProductEventRepositoryImpl implements ProductEventRepository {

    @Autowired
    private MongoTemplate t;

    @Override
    public Map<Integer, List<ProductSkuPriceEvent>> findEvent(List<Product> productList) {
        List<Integer> idList = new ArrayList<Integer>();
        for (Product p : productList) {
            idList.add(p.getProductId());
        }

        Query query = query(where("productId").in(idList));
        List<ProductSkuPriceEvent> eventList = t.find(query, ProductSkuPriceEvent.class);

        Map<Integer, List<ProductSkuPriceEvent>> eventMap = new HashMap<Integer, List<ProductSkuPriceEvent>>();
        for (ProductSkuPriceEvent e : eventList) {
            if (eventMap.containsKey(e.getProductId())) {
                eventMap.get(e.getProductId()).add(e);
            } else {
                // map element 생성
                List<ProductSkuPriceEvent> list = new ArrayList<ProductSkuPriceEvent>();
                list.add(e);
                eventMap.put(e.getProductId(), list);
            }
        }
        return eventMap;
    }
}
