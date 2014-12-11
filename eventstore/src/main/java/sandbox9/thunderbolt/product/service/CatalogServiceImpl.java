package sandbox9.thunderbolt.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.entity.catalog.Catalog;
import sandbox9.thunderbolt.entity.catalog.CatalogProduct;
import sandbox9.thunderbolt.entity.catalog.repository.CatalogProductCustomRepository;
import sandbox9.thunderbolt.entity.product.Sku;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.message.product.SkuPricingEventType;
import sandbox9.thunderbolt.product.repository.ProductEventRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 11..
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductEventRepository er;

    @Autowired
    private CatalogProductCustomRepository repository;

    @Override
    public Catalog find(String catalogId, int pageNumber, int pageSize) {
        List<CatalogProduct> productList = repository.find(catalogId, pageNumber, pageSize);

        Catalog catalog = new Catalog(catalogId, productList);

        // TODO 별도 컴포넌트로 설계해 빼내기. Product와 통합
        Map<Integer, List<ProductSkuPriceEvent>> eventMap = er.findEventById(catalog.originalProductList());

        for (CatalogProduct p : productList) {
            if (!eventMap.containsKey(p.getProductId())) {
                logger.debug(p.getProductId() + " 상품에 해당하는 이벤트가 존재하지 않습니다.");
                continue;
            }

            // 가격 이벤트 처리
            List<ProductSkuPriceEvent> eventList = eventMap.get(p.getProductId());

            for (ProductSkuPriceEvent event : eventList) {
                //TODO lowest price에 대한 처리 추가 => 전체 sku를 들고 오도록 조정?????????????? 아니면??????????????
                Sku sku = p.getStandardSku();
                if (sku == null || sku.getSkuId() != event.getSkuId()) {
                    logger.warn(p.getProductId() + " 상품에 " + event.getSkuId() + "에 해당하는 Sku 데이터가 존재하지 않습니다.");
                    continue;
                }
                long beforePrice = sku.getSalePrice();
                if (SkuPricingEventType.PLUS.equals(event.getEventType())) {
                    sku.setSalePrice(beforePrice + event.getChangeValue());
                } else if (SkuPricingEventType.MINUS.equals(event.getEventType())) {
                    sku.setSalePrice(beforePrice - event.getChangeValue());
                }
                logger.info("Sku 가격 변경 내역[" + p.getProductId() + " - " + sku.getSkuId() + "]" + beforePrice +
                        " => " + sku.getSalePrice() + "(" + event.getEventType() + ", " + event.getChangeValue() + ")");
            }
        }

        return catalog;
    }
}
