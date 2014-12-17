package sandbox9.thunderbolt.entity.catalog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by chanwook on 2014. 12. 11..
 */
public class CatalogProductRepositoryImpl implements CatalogProductCustomRepository {

    @Autowired
    private MongoTemplate t;

    @Override
    public List<CatalogProduct> find(String catalogId, int pageNumber, int pageSize) {
        Query query = query(where("catalogId").is(catalogId))
                .skip((pageNumber - 1) * pageSize).limit(pageSize);
        List<CatalogProduct> productList = t.find(query, CatalogProduct.class);
        return productList;
    }

    @Override
    public CatalogProduct find(int productId, int skuId) {
        Query query = query(where("productId").is(productId).and("standardSku.skuId").is(skuId));
        return t.findOne(query, CatalogProduct.class);
    }
}
