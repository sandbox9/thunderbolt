package sandbox9.thunderbolt.product.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.message.product.ProductEventKey;
import sandbox9.thunderbolt.message.product.ProductSkuEvent;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.message.product.ProductSkuStockEvent;

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
    public Map<Integer, List<ProductSkuEvent>> findEvent(List<Product> productList, ProductEventKey eventKey) {
        List<Integer> idList = new ArrayList<Integer>();
        for (Product p : productList) {
            idList.add(p.getProductId());
        }

        return findEventById(idList, eventKey);
    }

    @Override
    public Map<Integer, List<ProductSkuEvent>> findEventById(List<Integer> idList, ProductEventKey eventKey) {
        Query query = query(where("productId").in(idList));
        //TODO 분리해내기
        Class<?> eventClass = null;
        if (ProductEventKey.PRICE.equals(eventKey)) {
            eventClass = ProductSkuPriceEvent.class;
        } else if (ProductEventKey.STOCK.equals(eventKey)) {
            eventClass = ProductSkuStockEvent.class;
        } else {
            throw new IllegalArgumentException(eventKey + "에 해당하는 이벤트 클래스가 존재하지 않습니다.");
        }
        List<?> eventList = t.find(query, eventClass);

        Map<Integer, List<ProductSkuEvent>> eventMap = new HashMap<Integer, List<ProductSkuEvent>>();
        for (int i = 0; i < eventList.size(); i++) {
            ProductSkuEvent e = (ProductSkuEvent) eventList.get(i);
            if (eventMap.containsKey(e.getProductId())) {
                eventMap.get(e.getProductId()).add(e);
            } else {
                // map element 생성
                List<ProductSkuEvent> list = new ArrayList<ProductSkuEvent>();
                list.add(e);
                eventMap.put(e.getProductId(), list);
            }
        }
        return eventMap;
    }

    @Override
    public void save(ProductSkuPriceEvent event) {
        t.save(event);
    }

    @Override
    public void save(ProductSkuStockEvent event) {
        t.save(event);
    }
}
