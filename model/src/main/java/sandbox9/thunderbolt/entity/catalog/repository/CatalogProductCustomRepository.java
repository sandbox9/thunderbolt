package sandbox9.thunderbolt.entity.catalog.repository;

import sandbox9.thunderbolt.entity.catalog.CatalogProduct;

import java.util.List;

/**
 * Created by chanwook on 2014. 12. 10..
 */
//FIXME spring boot+spring data mongo+jpa를 함께 사용 시 'property not found' 에러로 진행이 안 되어
//custom 구현 방식을 사용하지 않고 별도로 선언해 사용함
public interface CatalogProductCustomRepository {
    List<CatalogProduct> find(String catalogId, int pageNumber, int pageSize);

    CatalogProduct find(int productId, int skuId);
}
